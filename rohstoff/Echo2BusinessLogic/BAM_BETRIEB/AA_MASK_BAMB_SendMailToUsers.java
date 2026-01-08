/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MyMailHelper;

public class AA_MASK_BAMB_SendMailToUsers extends XX_ActionAgent
{
	private BAMB_MASK_ModulContainer 	oMaskContainer = null;
	private E2_NavigationList			oNaviList = null;

	
	public AA_MASK_BAMB_SendMailToUsers(BAMB_MASK_ModulContainer maskcontainer, E2_NavigationList oNavList)
	{
		super();
		this.oMaskContainer = maskcontainer;
		this.oNaviList = oNavList;
		
	}

	public void executeAgentCode(ExecINFO oExecInfo)
	{
		try
		{
			MyE2_Button oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			BAMB_MASK_ComponentMAP  oMap = (BAMB_MASK_ComponentMAP)oButton.EXT().get_oComponentMAP();

			Save_BAMB oSave = new Save_BAMB(this.oMaskContainer,this.oNaviList);   // schreibt eigene warnungen
			if (oSave.get_bISOK())
			{
				String cID_FBAM = oSave.get_cActualMaskID_Unformated();
				// dann neu laden in den zustand edit (wie speichern und schliessen und wieder oeffnen
				this.oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID_FBAM);
				
				MyDBToolBox oDB = bibALL.get_myDBToolBox();
				String[][] cEMAIL_LIST = 
					oDB.EinzelAbfrageInArray("SELECT EMAIL FROM "+bibE2.cTO()+".JT_FBAM_USER,"+bibE2.cTO()+".JD_USER " +
							" WHERE JT_FBAM_USER.ID_USER=JD_USER.ID_USER AND JD_USER.EMAIL IS NOT NULL AND NVL(JD_USER.AKTIV,'N')='Y' AND ID_FBAM="+cID_FBAM); 
				
				if (cEMAIL_LIST == null || cEMAIL_LIST.length==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Keine passende e-Mail-Adresse gefunden !"));
				}
				else
				{
					String cBetreff = new MyE2_String("Beanstandungsmeldung").CTrans();
					String cMessageContent = "Buchungsnummer BAM:"+		((MyE2IF__DB_Component)oMap.get("BUCHUNGSNUMMER")).get_cActualDBValueFormated();
					cMessageContent += "\n\n"+"BAM-Grund:\n"+			bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("BAM_GRUND")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Fehlerursache:\n"+		bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("FEHLERURSACHE")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Fehlerbeschreibung:\n"+	bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("FEHLERBESCHREIBUNG")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Auswirkungen:\n"+		bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("AUSWIRKUNGEN")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Behebungsvorschlag:\n"+	bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("BEHEB_VORSCHLAG")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Behebungsvermerk:\n"+	bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("BEHEB_VERMERK")).get_cActualDBValueFormated());
					
					/*
					 * jetzt noch die zusatzinfos raussuchen
					 */
					BAMB_Daughter_Info DT_Info = (BAMB_Daughter_Info)oMap.get(BAMB_MASK_ModulContainer.FIELDNAME_DAUGHTERTABLE_INFOS);
					Vector<E2_ComponentMAP> vInfoMap = DT_Info.get_oNavigationList().get_vComponentMAPS();
					vInfoMap.addAll(DT_Info.get_oNavigationList().get_vComponentMAPS_NEW());
					
					if (vInfoMap.size()>0)
					{
						String cZusatzInfos = "";
						for (int i=0;i<vInfoMap.size();i++)
						{
							E2_ComponentMAP	 	oIMap = (E2_ComponentMAP)vInfoMap.get(i);
							MyE2_DB_TextArea 	oInfoText = (MyE2_DB_TextArea)oIMap.get("INFOBLOCK");
							
							cZusatzInfos = cZusatzInfos+oInfoText.get_cActualDBValueFormated()+"\n";
						}
						
						cMessageContent += "\n\n"+"Zusatzinformationen:\n"+cZusatzInfos;
					}
					
					
					MyMailHelper[] arrayMailObjects = new MyMailHelper[cEMAIL_LIST.length];
					for (int i=0;i<cEMAIL_LIST.length;i++)
					{
						arrayMailObjects[i] = new MyMailHelper(bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),cEMAIL_LIST[i][0],cBetreff,cMessageContent,null);
					}

					/*
					 * jetzt senden
					 */
					int iOK = 0;
					int iError = 0;
					Vector<MyString> vErrorMessages = new Vector<MyString>();
					for (int i=0;i<cEMAIL_LIST.length;i++)
					{
						if (arrayMailObjects[i].doSendWith_REAL_Adress())
							iOK++;
						else
						{
							vErrorMessages.addAll(arrayMailObjects[i].get_vFortgangMessages());
							iError++;
						}

					}
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Verschickte Mails: "+iOK));
					
					if (iError>0)
					{
						E2_ConfirmBasicModuleContainer oMessageBox = new E2_ConfirmBasicModuleContainer(
										new MyE2_String("Mail-Fehler"),
										new MyE2_String("Fehler beim Senden ..."),
										vErrorMessages,
										new MyE2_String("OK"), 
										new MyE2_String("Abbruch"),
										new Extent(500),
										new Extent(500));
						
						oMessageBox.show_POPUP_BOX();
					}
					
				}
			}

			
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BAM_ActionAgents:ActionAgentSendMailToUsers:Error "));
			bibMSG.add_MESSAGE(ex.get_ErrorMessage());
		}
		catch (Exception ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BAM_ActionAgents:ActionAgentSendMailToUsers:Unknown Error: "+ex.getLocalizedMessage()));
		}
		
		
	}
}