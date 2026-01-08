/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MyMailHelper;

public class BAMF_MASK_SendMailToUsers extends XX_ActionAgent
{
	private BAMF_MASK_ModulContainer oMaskContainer = null;
	public BAMF_MASK_SendMailToUsers(BAMF_MASK_ModulContainer maskcontainer)
	{
		super();
		this.oMaskContainer = maskcontainer;
	}

	public void executeAgentCode(ExecINFO oExecInfo)
	{
		try
		{
			MyE2_Button oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			BAMF_MASK_ComponentMAP  oMap = (BAMF_MASK_ComponentMAP)oButton.EXT().get_oComponentMAP();

			BAMF_SQLFieldMAP        oSqlMap = (BAMF_SQLFieldMAP)oMap.get_oSQLFieldMAP();
			
			//info beschaffen, zu welcher fuhre / fuhrenort die bam gehoert
			String     				cID_VPOS_TPA_FUHRE = 		null;
			String     				cID_VPOS_TPA_FUHRE_ORT = 	null;
			
			SQLField                ofID_VPOS_TPA_FUHRE = 		oSqlMap.get_("ID_VPOS_TPA_FUHRE");
			SQLField                ofID_VPOS_TPA_FUHRE_ORT = 	oSqlMap.get_("ID_VPOS_TPA_FUHRE_ORT");
			
			//es kann entweder eines oder beide vorhanden sein, oder aber (wenn aus der BAM-Liste)
			//gar keines, dann ist es aber ein Edit-Vorgang
			if     (ofID_VPOS_TPA_FUHRE_ORT != null && ofID_VPOS_TPA_FUHRE_ORT instanceof SQLFieldForRestrictTableRange)
			{
				cID_VPOS_TPA_FUHRE_ORT =  ((SQLFieldForRestrictTableRange)ofID_VPOS_TPA_FUHRE_ORT).get_cRestictionValue_IN_DB_FORMAT(); 
			}
			else if (ofID_VPOS_TPA_FUHRE != null && ofID_VPOS_TPA_FUHRE instanceof SQLFieldForRestrictTableRange)
			{
				cID_VPOS_TPA_FUHRE =  ((SQLFieldForRestrictTableRange)ofID_VPOS_TPA_FUHRE).get_cRestictionValue_IN_DB_FORMAT();
			}
			else
			{
				RECORD_FBAM  recFBAM = new RECORD_FBAM( oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				
				cID_VPOS_TPA_FUHRE = recFBAM.get_ID_VPOS_TPA_FUHRE_cUF();
				
				if (cID_VPOS_TPA_FUHRE == null)
				{
					cID_VPOS_TPA_FUHRE_ORT = recFBAM.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				}
			}
				

			if (cID_VPOS_TPA_FUHRE==null && cID_VPOS_TPA_FUHRE_ORT==null)
			{
				throw new myException("Error: Cannot find Fuhre to BAM !!");
			}
			
			
			Save_BAMF oSave = new Save_BAMF(this.oMaskContainer,null);   // schreibt eigene warnungen
			
			if (oSave.get_bISOK())
			{
				String cID_FBAM = oSave.get_cActualMaskID_Unformated();
				// dann neu laden in den zustand edit (wie speichern und schliessen und wieder oeffnen
				this.oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID_FBAM);
				
				MyDBToolBox oDB = bibALL.get_myDBToolBox();
				String[][] cEMAIL_LIST = 
					oDB.EinzelAbfrageInArray("SELECT EMAIL FROM "+bibE2.cTO()+".JT_FBAM_USER,"+bibE2.cTO()+".JD_USER " +
							" WHERE JT_FBAM_USER.ID_USER=JD_USER.ID_USER AND JD_USER.EMAIL IS NOT NULL AND ID_FBAM="+cID_FBAM); 
				
				if (cEMAIL_LIST == null || cEMAIL_LIST.length==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Keine passende e-Mail-Adresse gefunden !"));
				}
				else
				{
					
					String cLieferant = "";
					String cLadestation = "";
					String cAbnehmer  = "";
					String cAbladestation  = "";
					String cSorte = "";
					
					
					RECORD_VPOS_TPA_FUHRE 		recFuhre = 		null;
					RECORD_VPOS_TPA_FUHRE_ORT 	recFuhreOrt = 	null;
					
					
					if (cID_VPOS_TPA_FUHRE != null)
					{
						recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
						
//						cLieferant = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_NAME1_cF_NN("");
//						cAbnehmer  = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_NAME1_cF_NN("");;

						cLieferant = 		recFuhre.get_UP_RECORD_ADRESSE_id_adresse_start().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
						cAbnehmer  = 		recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
						cLadestation = 		recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
						cAbladestation  = 	recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
						
						
						cSorte = recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get___KETTE(bibALL.get_Vector("ANR1", "ARTBEZ1"));
					}
					else
					{
						recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT);
						recFuhre    =  recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
						if (recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
						{
							cLieferant = 		recFuhreOrt.get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
							cLadestation = 		recFuhreOrt.get_UP_RECORD_ADRESSE_id_adresse_lager().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
							cAbnehmer  = 		"<Fuhrenort Ladeseite>";
							cAbladestation  = 	"<Fuhrenort Ladeseite>";
						}
						else
						{
							cLieferant = 		"<Fuhrenort Abladeseite>";
							cAbladestation = 	"<Fuhrenort Abladeseite>";
							cAbnehmer  = 		recFuhreOrt.get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
							cAbladestation  = 	recFuhreOrt.get_UP_RECORD_ADRESSE_id_adresse_lager().get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT));
						}
						cSorte = recFuhreOrt.get_UP_RECORD_ARTIKEL_id_artikel().get___KETTE(bibALL.get_Vector("ANR1", "ARTBEZ1"));
					}
					
					
					
					String cBetreff = new MyE2_String("Interne Meldung: Weigerung einer Transport-Position!").CTrans();
					String cMessageContent = "Buchungsnummer BAM: "+((MyE2IF__DB_Component)oMap.get("BUCHUNGSNUMMER")).get_cActualDBValueFormated();
					
					cMessageContent += "\n\n"+"ID-Fuhre:\n"+	recFuhre.get_ID_VPOS_TPA_FUHRE_cF();
					if (recFuhreOrt!=null)
					{
						cMessageContent += "\n"+"ID-F.Ort:\n"+	recFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cF();
					}
					
					cMessageContent += "\n\n"+"Lieferant:\n"+	cLieferant;
					if (!cLieferant.equals(cLadestation))
					{
						cMessageContent += "\n"+"(Ladestation:"+	cLadestation+")";
					}
					cMessageContent += "\n\n"+"Abnehmer:\n"+	cAbnehmer;
					if (!cAbnehmer.equals(cAbladestation))
					{
						cMessageContent += "\n"+"(Abladestation:"+	cAbladestation+")";
					}
					
					
					cMessageContent += "\n\n"+"Sorte:\n"+		cSorte;
					cMessageContent += "\n\n"+"BAM-Grund:\n"+	bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("BAM_GRUND")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Fehlerursache:\n"+bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("FEHLERURSACHE")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Fehlerbeschreibung:\n"+bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("FEHLERBESCHREIBUNG")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Auswirkungen:\n"+bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("AUSWIRKUNGEN")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Behebungsvorschlag:\n"+bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("BEHEB_VORSCHLAG")).get_cActualDBValueFormated());
					cMessageContent += "\n\n"+"Behebungsvermerk:\n"+bibALL.ChangeUmlaute_CleanString(((MyE2IF__DB_Component)oMap.get("BEHEB_VERMERK")).get_cActualDBValueFormated());
					
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
			ex.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BAM_ActionAgents:ActionAgentSendMailToUsers:Error : "));
			bibMSG.add_MESSAGE(ex.get_ErrorMessage());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("BAM_ActionAgents:ActionAgentSendMailToUsers:Unknown Error: "+ex.getLocalizedMessage(),false)));
		}
		
		
	}
}