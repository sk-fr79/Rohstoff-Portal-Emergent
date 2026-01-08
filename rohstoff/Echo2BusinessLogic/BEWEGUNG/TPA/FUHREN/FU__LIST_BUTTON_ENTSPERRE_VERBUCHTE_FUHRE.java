package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO.SQL_StatementBuilderForPositions;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_RG;

public class FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE extends MyE2_Button
{
//	private String 				ID_VPOS_TPA_FUHRE = null;
	private E2_NavigationList   NaviList = 			null;
	
	private Vector<String>   	vIDs_To_Entsperren = new Vector<String>();
	
	//benutzung aus  dem popup-meune
	public FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE(E2_NavigationList   oNaviList)
	{
		super(new MyE2_String("(Teil-)gebuchte Fuhren öffnen"));
		
		this.vIDs_To_Entsperren.removeAllElements();
		
		this.NaviList = oNaviList;
		
		this.add_GlobalAUTHValidator_AUTO("VERBUCHTE_FUHRE_OEFFNEN");
		this.add_IDValidator(new ownValidator());
		
		this.add_oActionAgent(new ownActionAgentStartYesNO_Multi());
	}


	
	//benutzung aus dem Buchungsbutton (immer nur eine Fuhre)
	public FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE(E2_NavigationList   oNaviList, E2_ResourceIcon oIcon, String cID_VPOS_TPA_FUHRE)
	{
		super(oIcon);
		
		this.vIDs_To_Entsperren.removeAllElements();
		this.vIDs_To_Entsperren.add(cID_VPOS_TPA_FUHRE);
		
		this.NaviList = oNaviList;
		
		this.add_GlobalAUTHValidator_AUTO("VERBUCHTE_FUHRE_OEFFNEN");
		this.add_IDValidator(new ownValidator());
		
		this.add_oActionAgent(new ownActionAgentStartYesNO_Single());
	}

	
	
	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated) 	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VPOS_TPA_FUHRE)	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
			
			if (!((recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-100))==myCONST.STATUS_FUHRE__GANZGEBUCHT) || (recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-100))==myCONST.STATUS_FUHRE__TEILSGEBUCHT)))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Das Öffnen einer Fuhre kann nur bei komplett oder teilweise gebuchten Fuhren erfolgen !!"));
			}
			
			return oMV;
		}
		
	}
	
	
	private  class ownActionAgentStartYesNO_Single extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE oThis = FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE.this;
			
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(oThis.vIDs_To_Entsperren));
			
			if (bibMSG.get_bIsOK())
			{
					ownYESNOContainer oContainerZustimmung = new ownYESNOContainer(
				    		new MyE2_String("Sicherheitsabfrage"),
				    		new MyE2_String("Buchung ZURÜCKNEHMEN"),
				    		new MyE2_String("Buchungssätze der Fuhre STORNIEREN / LÖSCHEN ?"),
				    		new MyE2_String("JA"),
				    		new MyE2_String("Nein"),
				    		new Extent(300),
				    		new Extent(250)
				    		);
				    oContainerZustimmung.set_ActionAgentForOK(new ownActionAgentOeffneFuhre());
				    oContainerZustimmung.show_POPUP_BOX();
				
			}
		}
	}


	
	private  class ownActionAgentStartYesNO_Multi extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE oThis = FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE.this;
			
			oThis.vIDs_To_Entsperren.removeAllElements();
			oThis.vIDs_To_Entsperren.addAll(oThis.NaviList.get_vSelectedIDs_Unformated());
			
			if (oThis.vIDs_To_Entsperren.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Für diese Funktion muss mindestens EINE Fuhre selektiert sein !!"));
				return;
			}
			
			
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(oThis.vIDs_To_Entsperren));
			
			if (bibMSG.get_bIsOK())
			{
					ownYESNOContainer oContainerZustimmung = new ownYESNOContainer(
				    		new MyE2_String("Sicherheitsabfrage"),
				    		new MyE2_String("Buchung ZURÜCKNEHMEN"),
				    		new MyE2_String("Buchungssätze der Fuhren STORNIEREN / LÖSCHEN ?"),
				    		new MyE2_String("JA"),
				    		new MyE2_String("Nein"),
				    		new Extent(300),
				    		new Extent(250)
				    		);
				    oContainerZustimmung.set_ActionAgentForOK(new ownActionAgentOeffneFuhre());
				    oContainerZustimmung.show_POPUP_BOX();
				
			}
		}
	}

	
	
	/*
	 * eigene popup-klasse zum groessenspeichern
	 */
	private class ownYESNOContainer extends E2_ConfirmBasicModuleContainer
	{
	
		public ownYESNOContainer(MyE2_String windowtitle,
				MyE2_String texttitle, MyE2_String innerTextblock,
				MyE2_String labelOKButton, MyE2_String labelCancelButton,
				Extent width, Extent height)  throws myException
		{
			super(windowtitle, texttitle, innerTextblock, labelOKButton, labelCancelButton,	width, height);
		}
		
	}

	
	private class ownActionAgentOeffneFuhre extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE oThis = FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE.this;
			
			Vector<String> vSQL = new Vector<String>();
			
			for (String _ID_VPOS_TPA_FUHRE: oThis.vIDs_To_Entsperren)
			{
			
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(_ID_VPOS_TPA_FUHRE);
				
				RECLIST_VPOS_RG  recListVPosRG_ZuStornieren =	new RECLIST_VPOS_RG();   	//variante A: positionen, die bereits in einem abgeschlossenen VKOPF_RG sind, werden storniert
				RECLIST_VPOS_RG  recListVPosRG_ZuLoeschen = 	new RECLIST_VPOS_RG();     	//variante B: positionen, die entweder frei, oder in einer ungedruckten kopf-zuordnung sind, werden geloescht
				
				//zuerst die fuhre bearbeiten (EK-Seite)
				RECLIST_VPOS_RG  recListPosEK = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord("NVL(DELETED,'N')='N' AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL AND LAGER_VORZEICHEN=1", "ID_VPOS_RG DESC", true);
				if (recListPosEK.size()>0)
				{
					this.sortierePositionEin(recListPosEK.get(0), recListVPosRG_ZuStornieren, recListVPosRG_ZuLoeschen);
				}
				//zuerst die fuhre bearbeiten (VK-Seite)
				RECLIST_VPOS_RG  recListPosVK = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord("NVL(DELETED,'N')='N' AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL  AND LAGER_VORZEICHEN=-1", "ID_VPOS_RG DESC", true);
				if (recListPosVK.size()>0)
				{
					this.sortierePositionEin(recListPosVK.get(0), recListVPosRG_ZuStornieren, recListVPosRG_ZuLoeschen);
				}
				
				//jetzt die orte noch mitnehmen
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistFuhreOrt = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
				
				for (int i=0;i<reclistFuhreOrt.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recORT = reclistFuhreOrt.get(i);
					//zuerst die fuhre bearbeiten (EK-Seite)
					RECLIST_VPOS_RG  recListPos = recORT.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord("NVL(DELETED,'N')='N'", "ID_VPOS_RG DESC", true);
					if (recListPos.size()>0)
					{
						this.sortierePositionEin(recListPos.get(0), recListVPosRG_ZuStornieren, recListVPosRG_ZuLoeschen);
					}
				}
				
				
				
				//jetzt die statements ausfuellen
				//zuerst die stornos:
				SQL_StatementBuilderForPositions oStornoStatements = new SQL_StatementBuilderForPositions(recListVPosRG_ZuStornieren,null,null,false);
				vSQL.addAll(oStornoStatements.get_vSQL_Statements());
				
				//dann die loesch-vorgaenge
				for (int i=0;i<recListVPosRG_ZuLoeschen.get_vKeyValues().size();i++)
				{
					recListVPosRG_ZuLoeschen.get(i).set_NEW_VALUE_DELETED("Y");
					recListVPosRG_ZuLoeschen.get(i).set_NEW_VALUE_DEL_GRUND("AUTOMATISCHE FUHRENOEFFNUNG");
					recListVPosRG_ZuLoeschen.get(i).set_NEW_VALUE_DEL_DATE(bibALL.get_cDateNOW());
					vSQL.add(recListVPosRG_ZuLoeschen.get(i).get_SQL_UPDATE_STATEMENT(null, true));
					
					//jetzt noch nachsehen, ob der Kopf damit "leer" ist
					RECORD_VKOPF_RG recVkopf = recListVPosRG_ZuLoeschen.get(i).get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
					if (recVkopf != null)
					{
						RECLIST_VPOS_RG recTestOffeneRestPos = recVkopf.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N' AND ID_VPOS_RG<>"+recListVPosRG_ZuLoeschen.get(i).get_ID_VPOS_RG_cUF(),null,true);
						if (recTestOffeneRestPos.get_vKeyValues().size()==0)
						{
							recVkopf.set_NEW_VALUE_DELETED("Y");
							recVkopf.set_NEW_VALUE_DEL_GRUND("AUTOMATISCHE FUHRENOEFFNUNG");
							recVkopf.set_NEW_VALUE_DEL_DATE(bibALL.get_cDateNOW());
							
							//dann den kopf auch loeschen
							vSQL.add(recVkopf.get_SQL_UPDATE_STATEMENT(null, true));
						}
					}
				}
				
			}
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			oThis.NaviList.Refresh_ComponentMAP(oThis.vIDs_To_Entsperren, E2_ComponentMAP.STATUS_VIEW);
		}
		
		private void sortierePositionEin(RECORD_VPOS_RG recVPosRG, RECLIST_VPOS_RG recListVPosRG_ZuStornieren, RECLIST_VPOS_RG recListVPosRG_ZuLoeschen) throws myException
		{
			PRUEF_RECORD_VPOS_RG recToTest = new PRUEF_RECORD_VPOS_RG(recVPosRG);      //immer den letzten record untersuchen
			
			
			if (!recToTest.get_bIsTeilEinesStornoZyklus())   //sonst muss nix gemacht werden
			{
				//jetzt entscheiden, ob bereits in einem kopf
				if (S.isFull(recToTest.get_ID_VKOPF_RG_cUF_NN("")))
				{
					//bereits verbucht ?
					if (recToTest.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
					{
						//muss storniert werden
						recListVPosRG_ZuStornieren.ADD(recToTest,false);
					}
					else
					{
						//muss geloescht werden
						recListVPosRG_ZuLoeschen.ADD(recToTest,false);
					}
				}
				else
				{
					recListVPosRG_ZuLoeschen.ADD(recToTest,false);
				}
			}
			
		}
		
	}
	
	
	
}
