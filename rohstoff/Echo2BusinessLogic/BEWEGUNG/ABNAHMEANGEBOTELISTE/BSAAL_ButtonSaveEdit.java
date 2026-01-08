package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class BSAAL_ButtonSaveEdit extends MyE2_Button 
{

	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	
	
	
	public BSAAL_ButtonSaveEdit(BSAAL__ModulContainerLIST	oModulContainer) 
	{
		super(E2_ResourceIcon.get_RI("save.png"), true);
		
		this.oModulContainerList = oModulContainer;
		
		this.add_IDValidator(new BSAAL_Validator_Kopf_IS_NOT_CLOSED());
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Preiseingaben abspeichern ...").CTrans());
		
		this.set_bMustSet_MILLISECONDSTAMP_TO_Session(false);

		//2015-12-15: die infobutton-jump-funktion einschalten
		this.add_oActionAgent(new BSAAL_ActionSetInfoButtonsInListe_Status(this.oModulContainerList.get_oNaviList(), true));

	}
	
//	private class ownActionAgent extends XX_ActionAgent
//	{
//
//		public void executeAgentCode(ExecINFO oExecInfo) 
//		{
//			BSAAL_ButtonSaveEdit oThis = BSAAL_ButtonSaveEdit.this;
//
//			E2_NavigationList oNavList = oThis.oModulContainerList.get_oNaviList();
//			
//			try
//			{
//				Vector<String> vSQLStack	= new Vector<String>();
//				Vector<String> vChanges 	= new Vector<String>();
//				
//				for (E2_ComponentMAP oMap: oNavList.get_vComponentMAPS())
//				{
//					SQLResultMAP oResult = oMap.get_oInternalSQLResultMAP();			// vorige resultmap
//
//					SQLMaskInputMAP oInputMap = oMap.MakeCompleteCycle_of_Validation_After_Input(oResult,bibMSG.MV(),E2_ComponentMAP.STATUS_EDIT);
//					/*
//					 * zuerst pruefen, ob der satz im netz geaendert wurde,
//					 */
//					vChanges.addAll(oMap.get_ChangedFieldResults());
//
//					
//					if (vChanges.size()>0)
//					{
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurden Daten während der Bearbeitung von einem anderen Benutzer verändert !"));
//
//						for (int k=0;k<vChanges.size();k++)
//							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT((String)vChanges.get(k)));
//						
//						break;
//					}
//					
//					if (bibMSG.get_bHasAlarms())
//					{
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind Fehler aufgetreten !"));
//						break;
//					}
//					vSQLStack.addAll(oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oResult,oInputMap));
//					
//				}
//				
//				
//				if (bibMSG.get_bIsOK())
//				{
//					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLStack,true));
//					
//					if (bibMSG.get_bIsOK())
//					{
//						try
//						{
//							Vector<String> vOldCheckedRows = oNavList.get_vSelectedIDs_Unformated();   // letzte markierungen
//							oNavList.BUILD_ComponentMAP_Vector_from_ActualSegment();
//							oNavList.FILL_GRID_From_InternalComponentMAPs(true, true);
//							oNavList.set_SelectIDs(vOldCheckedRows);
//	
//							if (oThis.oModulContainerList.get_oDisabler() != null)
//							{
//								oThis.oModulContainerList.get_oDisabler().setEnabled();
//								oThis.oModulContainerList.set_oDisabler(null);
//							}
//							
//						}
//						catch (myException ex)
//						{
//							ex.printStackTrace();
//							bibMSG.add_MESSAGE(ex.get_ErrorMessage());
//						}
//						
//						bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl gespeicherte Preise :"+vSQLStack.size()));
//					}
//					else
//					{
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("SQL-Speicherfehler ..."));
//						bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vSQLStack);
//					}
//				}
//
//			}
//			catch (myException ex)
//			{
//				ex.printStackTrace();
//				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
//			}
//		}
//	}
//	
	
	
//	/**
//	 * 2013-05-10: neue save-action-agent, verhindert dass ein zwischengeschobener aenderungsvorgang
//	 *             eines belibigen kollegen im Netzwerk das speichern einer langen liste sabotiert 
//	 * @author martin
//	 *
//	 */
//	private class ownActionAgent extends XX_ActionAgent
//	{
//
//		public void executeAgentCode(ExecINFO oExecInfo) 
//		{
//			BSAAL_ButtonSaveEdit oThis = BSAAL_ButtonSaveEdit.this;
//
//			E2_NavigationList oNavList = oThis.oModulContainerList.get_oNaviList();
//			
//			try
//			{
//				Vector<String> vSQLStack	= new Vector<String>();
//				
//				/*
//				 * Vector speichert die von andererseite im Netz veraenderten eintraege
//				 */
//				Vector<String> 			vID_im_NetzVeraendert = new Vector<String>(); 
//				MyE2_MessageVector  	oMV_ChangeMeldungen = new MyE2_MessageVector();
//				
//				for (E2_ComponentMAP oMap: oNavList.get_vComponentMAPS())
//				{
//					SQLResultMAP oResult = oMap.get_oInternalSQLResultMAP();			// vorige resultmap
//
//					SQLMaskInputMAP oInputMap = oMap.MakeCompleteCycle_of_Validation_After_Input(oResult,bibMSG.MV(),E2_ComponentMAP.STATUS_EDIT);
//
//					Vector<String> vChangesFormOtherUser 	= new Vector<String>();
//					/*
//					 * zuerst pruefen, ob der satz im netz geaendert wurde,
//					 */
//					vChangesFormOtherUser.addAll(oMap.get_ChangedFieldResults());
//
//					if (vChangesFormOtherUser.size()>0)	{
//						oMV_ChangeMeldungen.add_MESSAGE(new MyE2_Alarm_Message("Es wurden Daten während der Bearbeitung von einem anderen Benutzer verändert !"));
//
//						for (int k=0;k<vChangesFormOtherUser.size();k++) {
//							oMV_ChangeMeldungen.add_MESSAGE(new MyE2_Alarm_Message_OT((String)vChangesFormOtherUser.get(k)));
//						}
//						vID_im_NetzVeraendert.add(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
//					} else {
//						vSQLStack.addAll(oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oResult,oInputMap));
//					}
//						
//					if (bibMSG.get_bHasAlarms()) {
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind Fehler aufgetreten !"));
//						break;
//					}
//					
//				}
//				
//				
//				/*
//				 * die sql-daemons im hintergrund ausschalten, damit eine veraenderung hier keine speicherprobleme verursacht
//				 */
//				Vector<String> vSQL_NoDeamons= new Vector<String>();
//				for (String cSQL: vSQLStack) {
//					vSQL_NoDeamons.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+cSQL);
//				}
//				
//				
//				if (bibMSG.get_bIsOK()) 	{
//					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_NoDeamons,true));
//					
//					if (bibMSG.get_bIsOK()) {
//						try {
//							Vector<String> vOldCheckedRows = oNavList.get_vSelectedIDs_Unformated();   // letzte markierungen
//							oNavList.BUILD_ComponentMAP_Vector_from_ActualSegment();
//							oNavList.FILL_GRID_From_InternalComponentMAPs(true, true);
//							oNavList.set_SelectIDs(vOldCheckedRows);
//	
//							if (oThis.oModulContainerList.get_oDisabler() != null) 	{
//								oThis.oModulContainerList.get_oDisabler().setEnabled();
//								oThis.oModulContainerList.set_oDisabler(null);
//							}
//							
//						}
//						catch (myException ex)
//						{
//							ex.printStackTrace();
//							bibMSG.add_MESSAGE(ex.get_ErrorMessage());
//						}
//						
//						bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl gespeicherte Preise :"+vSQLStack.size()));
//						if (vSQLStack.size() != oNavList.get_vComponentMAPS().size()) {
//							bibMSG.add_MESSAGE(new MyE2_Warning_Message("Nicht gespeichert wegen Netzwerkkollision :"+(vSQLStack.size()-oNavList.get_vComponentMAPS().size())));
//						}
//						if (oMV_ChangeMeldungen.size()>0) {
//							bibMSG.add_MESSAGE(oMV_ChangeMeldungen);
//							
//							//dann schleife ueber alle zeilen und das preisfeld gelb markieren bei den nicht gespeicherten
//							for (E2_ComponentMAP oMap: oNavList.get_vComponentMAPS())	{
//								if (vID_im_NetzVeraendert.contains(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()))	{
//									if (oMap.get__Comp(_DB.VPOS_RG$EINZELPREIS) instanceof MyE2_DB_TextField) {
//										((MyE2_DB_TextField)oMap.get__Comp(_DB.VPOS_RG$EINZELPREIS)).setBackground(new E2_ColorHelpBackground());
//									}
//								}
//							}
//						}
//					}
//					else
//					{
//						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("SQL-Speicherfehler ..."));
//						bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vSQLStack);
//					}
//				}
//
//			}
//			catch (myException ex)
//			{
//				ex.printStackTrace();
//				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
//			}
//		}
//	}
//	
//	
	
	/**
	 * 2013-05-15: weitere Version, da die methode, die daemonen auszuschalten zu fehlern bei der Fremdwaerungsrechnung fuehren
	 *             neue save-action-agent, verhindert dass ein zwischengeschobener aenderungsvorgang
	 *             eines beliebigen kollegen im Netzwerk das speichern einer langen liste sabotiert 
	 * @author martin
	 *
	 */
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			BSAAL_ButtonSaveEdit oThis = BSAAL_ButtonSaveEdit.this;

			E2_NavigationList oNavList = oThis.oModulContainerList.get_oNaviList();
			
			try
			{
				//2013-05-15: jeder vector wird einzeln gespeichert mit separater fehler-behandlung 
				HashMap<String,Vector<String>> hmSQLStack	= new HashMap<String,Vector<String>>();
				
				/*
				 * Vector speichert die von andererseite im Netz veraenderten eintraege
				 */
				Vector<String> 			vIDs_mit_Fehler = new Vector<String>(); 
				MyE2_MessageVector  	oMV_ChangeMeldungen = new MyE2_MessageVector();
				
				for (E2_ComponentMAP oMap: oNavList.get_vComponentMAPS())
				{
					SQLResultMAP oResult = oMap.get_oInternalSQLResultMAP();			// vorige resultmap

					SQLMaskInputMAP oInputMap = oMap.MakeCompleteCycle_of_Validation_After_Input(oResult,bibMSG.MV(),E2_ComponentMAP.STATUS_EDIT);

					Vector<String> vChangesFromOtherUser 	= new Vector<String>();
					/*
					 * zuerst pruefen, ob der satz im netz geaendert wurde (standard-methode ueber vergleich der oMAP-Inhalte)
					 */
					vChangesFromOtherUser.addAll(oMap.get_ChangedFieldResults());

					if (vChangesFromOtherUser.size()>0)	{
						oMV_ChangeMeldungen.add_MESSAGE(new MyE2_Alarm_Message(
								new MyE2_String("Es wurden Daten während der Bearbeitung von einem anderen Benutzer verändert,  Zeile mit der ID(Pos): ",true,
												oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(),false)));

						for (int k=0;k<vChangesFromOtherUser.size();k++) {
							oMV_ChangeMeldungen.add_MESSAGE(new MyE2_Alarm_Message_OT((String)vChangesFromOtherUser.get(k)));
						}
						vIDs_mit_Fehler.add(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
					} else {
						hmSQLStack.put(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(),oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oResult,oInputMap));
						DEBUG.System_println(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(), "");
					}
						
					if (bibMSG.get_bHasAlarms()) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind Fehler aufgetreten !"));
						break;
					}
				}
				
				
				Vector<String> 			vIDsSaveLines = new Vector<String>();
				
				MyE2_MessageVector 		oMV_GoodMessages = new MyE2_MessageVector();
				MyE2_MessageVector 		oMV_ErrorMessagesIntegrityWatcher = new MyE2_MessageVector();
						
				
				if (bibMSG.get_bIsOK()) 	{
					
					//2013-05-15: einzelne zeilen separat speichern
					for (String cID: hmSQLStack.keySet()) {
						MyE2_MessageVector oMV = bibDB.ExecMultiSQLVector(hmSQLStack.get(cID),true);
						if (oMV.get_bIsOK()) {
							vIDsSaveLines.add(cID);
							oMV_GoodMessages.add_MESSAGE(oMV);
						} else {
							vIDs_mit_Fehler.add(cID);
							oMV_ErrorMessagesIntegrityWatcher.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Weiterer Speicher-Fehler (vermutlich Kollision mit Speichervorgang eines andere Benutzers) in Zeile mit der ID(Pos): ",true,cID,false)));
						}
					}
						
					try {
						Vector<String> vOldCheckedRows = oNavList.get_vSelectedIDs_Unformated();   // letzte markierungen
						oNavList.BUILD_ComponentMAP_Vector_from_ActualSegment();
						oNavList.FILL_GRID_From_InternalComponentMAPs(true, true);
						oNavList.set_SelectIDs(vOldCheckedRows);

						if (oThis.oModulContainerList.get_oDisabler() != null) 	{
							oThis.oModulContainerList.get_oDisabler().setEnabled();
							oThis.oModulContainerList.set_oDisabler(null);
						}
					}
					catch (myException ex)
					{
						ex.printStackTrace();
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
					}
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl gespeicherte Preise :"+vIDsSaveLines.size()));
					
					
					if (vIDs_mit_Fehler.size() >0) {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Nicht gespeichert wegen Netzwerkkollision :"+(vIDs_mit_Fehler.size())));
					}

					//die positiven meldungen zeigen
					if (oMV_GoodMessages.size()>0) {
						bibMSG.add_MESSAGE(oMV_GoodMessages);
					}
					
					//jetzt die beiden fehlervectoren anzeigen
					if (oMV_ChangeMeldungen.size()>0) {
						bibMSG.add_MESSAGE(oMV_ChangeMeldungen);
					}
					
					if (oMV_ErrorMessagesIntegrityWatcher.size()>0) {
						bibMSG.add_MESSAGE(oMV_ErrorMessagesIntegrityWatcher);
					}

					//dann schleife ueber alle zeilen und das preisfeld gelb markieren bei den nicht gespeicherten
					for (E2_ComponentMAP oMap: oNavList.get_vComponentMAPS())	{
						if (vIDs_mit_Fehler.contains(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()))	{
							if (oMap.get__Comp(_DB.VPOS_RG$EINZELPREIS) instanceof MyE2_DB_TextField) {
								((MyE2_DB_TextField)oMap.get__Comp(_DB.VPOS_RG$EINZELPREIS)).setBackground(new E2_ColorHelpBackground());
							}
						}
					}
				}

			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
}
