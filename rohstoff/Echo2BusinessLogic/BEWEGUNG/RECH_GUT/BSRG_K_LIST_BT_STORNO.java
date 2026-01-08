package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL.__JASPER_EXEC_Pruefe_Original_per_Mail;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO.DataRecordCopySQL_Kopf;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO.SQL_StatementBuilderForPositions;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;


public class BSRG_K_LIST_BT_STORNO extends MyE2_Button 
{

	private E2_NavigationList 					oNaviList = 					null;
	private BSRG_K_LIST__ModulContainer  		oBSRG_LIST_ModuleContainer = 	null;
	
	private ownActionAgentPrintStornoBelege    oActionPrintStornoBelegte = null;
	
	//2015-05-08: neuer vector fuer die pruefung, ob bei drucken die mailmaske unterdrueckt werden soll
	private Vector<String>		   				vStornierteBelege = new Vector<String>();
	
	public BSRG_K_LIST_BT_STORNO(BSRG_K_LIST__ModulContainer  BSRG_LIST_ModuleContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("storno.png"));
		
		this.oBSRG_LIST_ModuleContainer = BSRG_LIST_ModuleContainer;
		this.oNaviList = this.oBSRG_LIST_ModuleContainer.get_oNavigationList();
		
		this.add_GlobalAUTHValidator_AUTO("STORNIERE_VORGANG");
		this.setToolTipText(new MyE2_String("Beleg(e) durch einen Gegenbeleg stornieren, neuen, nicht abgeschlossenen Beleg erzeugen ").CTrans());

		this.add_IDValidator(new ownValidatorIstStornoErlaubt());   //schon der ausloeser des popup bekommt den validierer, damit die meldung schon vor dem popup erscheint
																	// im popup-save-button ist der gleiche validiere nochmal (sicherheitshalber)
		
		this.oActionPrintStornoBelegte=new ownActionAgentPrintStornoBelege(
														new MyE2_String("Drucke / Maile Rechnung oder Gutschrift"), 
														null, 
														"rechnung_gutschrift",
														oNaviList, 
														null,
														BSRG_LIST_ModuleContainer.get_SETTING(), 
														"id_vkopf_rg", 
														BSRG_LIST_ModuleContainer.get_MODUL_IDENTIFIER(), 
														false);

		this.add_oActionAgent(new XX_ActionAgent()     //bestaetigungsfenster aufrufen
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSRG_K_LIST_BT_STORNO oThis = BSRG_K_LIST_BT_STORNO.this;
				
				Vector<String> vIDsKopf =  oThis.oNaviList.get_vSelectedIDs_Unformated();
				oThis.vStornierteBelege.removeAllElements();
				oThis.vStornierteBelege.addAll(vIDsKopf);
				
				
				if (vIDsKopf.size()==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte wählen Sie mindestens einen Beleg aus !"));
					return;
				}
				
				
				bibMSG.add_MESSAGE(execInfo.make_ID_Validation(vIDsKopf));
				
				if (bibMSG.get_bIsOK())
				{
					ownConfirmContainer oPopup = new ownConfirmContainer(new MyE2_String("Sind Sie sicher"),
																							   new MyE2_String("STORNIEREN ?"),
																							   new MyE2_String(""),
																							   new MyE2_String("JA"),
																							   new MyE2_String("NEIN"),
																							   new Extent(400),
																							   new Extent(200));
					Vector<XX_ActionAgent> vAgents = new Vector<XX_ActionAgent>();
					vAgents.add(new ownActionAgentMakeStorno());
					vAgents.add(oThis.oActionPrintStornoBelegte);
					oPopup.set_ActionAgentForOK(vAgents);
					oPopup.get_oButtonOK().add_IDValidator(new ownValidatorIstStornoErlaubt());
					
					oPopup.show_POPUP_BOX();
				}
			}
			
		});
				
	}

	
	private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
	{

		public ownConfirmContainer(MyE2_String windowtitle,
				MyE2_String texttitle, MyE2_String innerTextblock,
				MyE2_String labelOKButton, MyE2_String labelCancelButton,
				Extent width, Extent height)  throws myException
		{
			super(windowtitle, texttitle, innerTextblock, labelOKButton, labelCancelButton,
					width, height);
		}
		
	}
	
	
	private class ownValidatorIstStornoErlaubt extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID) 	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			RECORD_VKOPF_RG  recVKOPF = new RECORD_VKOPF_RG(cID);
			
			if (recVKOPF.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Rechnung/Gutschrift wurde bereits gelöscht !"));
			}
			
			if (recVKOPF.is_ABGESCHLOSSEN_NO())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Rechnung/Gutschrift kann erst storniert werden, wenn der Vorgang geschlossen ist !"));
			}
			
			if (S.isFull(recVKOPF.get_ID_VKOPF_RG_STORNO_VORGAENGER_cUF()) || S.isFull(recVKOPF.get_ID_VKOPF_RG_STORNO_NACHFOLGER_cUF()))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Belege aus einem vorigen Storno-Zyklus können nicht nochmal storniert werden !"));
			}
			
			for (int i=0;i<recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().size();i++)
			{
				if (S.isFull(recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().get(i).get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) ||
					S.isFull(recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().get(i).get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Belegt enthält einzelne Storno-Positionen und kann daher nicht mehr storniert werden!"));
				}
			}
			
			
			
			return oMV;
		}
		
	}
	
	
	private class ownActionAgentMakeStorno extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			
			
			Vector<String> vIDsKopf =  BSRG_K_LIST_BT_STORNO.this.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDsKopf.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte wählen Sie mindestens einen Beleg aus !"));
				return;
			}
			
			bibMSG.add_MESSAGE(oExecInfo.make_ID_Validation(vIDsKopf));
			
			if (bibMSG.get_bIsOK())
			{
				Vector<String> vSQL_Stack = new Vector<String>();
				Vector<String> vNewIDsVKOPF_Sammler_Fuer_Liste = new Vector<String>();             //die neuen id_vkopf_rg werden vorab generiert, da es fuer die uebersicht der liste noetig ist
																				//diese direkt nach dem vorgang anzuzeigen
				
				Vector<String>  vStornoIDs_to_Print = new Vector<String>();              //sammler fuer den ausdruck der stornierten IDs
				
				
				for (int i=0;i<vIDsKopf.size();i++)
				{
					RECORD_VKOPF_RG   recVKopfRG_zu_stornieren = new RECORD_VKOPF_RG(vIDsKopf.get(i));

					String cID_VKOPF_STORNO = bibDB.EinzelAbfrage("SELECT SEQ_VKOPF_RG.NEXTVAL FROM DUAL");
					String cID_VKOPF_NEU    = bibDB.EinzelAbfrage("SELECT SEQ_VKOPF_RG.NEXTVAL FROM DUAL");
					
					vNewIDsVKOPF_Sammler_Fuer_Liste.add(cID_VKOPF_STORNO);
					vNewIDsVKOPF_Sammler_Fuer_Liste.add(cID_VKOPF_NEU);

					vStornoIDs_to_Print.add(cID_VKOPF_STORNO);        //der erste erzeugte, d.h. der stornosatz wird gleich gedruckt
					
					//die beiden Kopfsaetze
					vSQL_Stack.add(new DataRecordCopySQL_Kopf(recVKopfRG_zu_stornieren, cID_VKOPF_STORNO, true).get_cINSERT_String());
					vSQL_Stack.add(new DataRecordCopySQL_Kopf(recVKopfRG_zu_stornieren, cID_VKOPF_NEU, false).get_cINSERT_String());
					//jetzt den original-satz mit dem storno-nachfolger beschriften
					recVKopfRG_zu_stornieren.set_NEW_VALUE_ID_VKOPF_RG_STORNO_NACHFOLGER(cID_VKOPF_STORNO);
					vSQL_Stack.add(recVKopfRG_zu_stornieren.get_SQL_UPDATE_STATEMENT(null, true));
					
					vSQL_Stack.addAll(new SQL_StatementBuilderForPositions(	recVKopfRG_zu_stornieren.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'","ID_VPOS_RG",true),
																			cID_VKOPF_STORNO,
																			cID_VKOPF_NEU, 
																			true).get_vSQL_Statements());
					
				}
				
				
				/*
				 * den druck-actionAgent anweisen, nur die storno-belege auszudrucken
				 */
				BSRG_K_LIST_BT_STORNO.this.oActionPrintStornoBelegte.set_vIDs_Statt_Navilist(vStornoIDs_to_Print);
				
				
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_Stack, true));
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Storno durchgeführt: ",true,""+vIDsKopf.size(),false," Belege wurden bearbeitet ",true)));
						BSRG_K_LIST_BT_STORNO.this.oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(vNewIDsVKOPF_Sammler_Fuer_Liste);
						BSRG_K_LIST_BT_STORNO.this.oNaviList._REBUILD_ACTUAL_SITE("");
						BSRG_K_LIST_BT_STORNO.this.oNaviList.Check_ID_IF_IN_Page(vIDsKopf);
						BSRG_K_LIST_BT_STORNO.this.oNaviList.Check_ID_IF_IN_Page(vNewIDsVKOPF_Sammler_Fuer_Liste);
					}
				}
			}
		}
	}

	
	
	
	
	
	
	


	/*
	 * weiterer actionagent, der die belege, die als storno definiert wurden,
	 * gleich in den druck gibt
	 */
	private class ownActionAgentPrintStornoBelege extends BS_K_LIST_ActionAgent_Mail_Print
	{
		public ownActionAgentPrintStornoBelege(	MyE2_String 		fensterTitel,	
												XX_ActionAgent 		actionAfterPrintOrMail, 
												String 				jasperFileName,
												E2_NavigationList 	navList, 
												Vector<String> 		vIDs_statt_NaviList,
												BS__SETTING 		setting, 
												String 				parameterNameOfHeadIDField,
												String 				MODUL_KENNER, 
												boolean 			preview) throws myException
		{
			super(fensterTitel, actionAfterPrintOrMail, jasperFileName, navList,vIDs_statt_NaviList, setting, parameterNameOfHeadIDField, MODUL_KENNER,preview, false);
			//2016-01-26: close-button unterdruecken
			this.set_suppress_window_close_button(true);
		}

		@Override
		public void complete_JasperHASH(E2_JasperHASH jasperHASH)	throws myException
		{
			//2015-01-07: einsprungpunkt fuer die verarbeitung der Belegoriginale
			jasperHASH.get_vExecuters().add(new __JASPER_EXEC_Pruefe_Original_per_Mail(false));
		}

		@Override
		public void manipulate_IDS_To_Print_From_Other_Source(Vector<String> vIDs)	throws myException 
		{
		}

		
		@Override
		public boolean bACTION_BEFORE_START___TO_OVERWRITE() throws myException
		{
			this.set_bExplicitSupressMail(false);
			
			//jetzt nachsehen, ob evtl. alle zu druckenden RG-Belege mit direktmail-vermerkt sind, wenn ja, print/mail-abfrage unterdruecken
			Vector<String> vID_RG = new Vector<String>();
			vID_RG.addAll(BSRG_K_LIST_BT_STORNO.this.vStornierteBelege);
				
			boolean all_prints_are_directMail = true;
				
			for (String cID: vID_RG) {
				RECORD_VKOPF_RG  recRG = new RECORD_VKOPF_RG(cID);
					 
				RECORD_ADRESSE_extend adrExt = new RECORD_ADRESSE_extend(recRG.get_UP_RECORD_ADRESSE_id_adresse());
					
				if (!adrExt.get_bAdresse_is_4_MailingOriginal_RG()) {
					all_prints_are_directMail = false;
					break;
				}
			}
				
			//dann wird die abfrage mail/druck direkt mit druck beantwortet
			this.set_bExplicitSupressMail(all_prints_are_directMail);
			
			return true;
		}

		
	}

	
	

}
