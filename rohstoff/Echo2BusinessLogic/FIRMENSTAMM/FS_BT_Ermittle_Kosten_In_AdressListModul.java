package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE.TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis;

public class FS_BT_Ermittle_Kosten_In_AdressListModul extends MyE2_Button {

//	//private ownKostenermittler  oKostenSucher = null;
//	private E2_NavigationList  	oNaviList = null;
//	private Vector<String>      vIdAdressenZuBearbeiten = new Vector<String>();
//	
	 
	public FS_BT_Ermittle_Kosten_In_AdressListModul(E2_NavigationList  naviList) {
		super(new MyE2_String("Kostenrelationen: Preise ermitteln/füllen"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
//		this.oNaviList = naviList;
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.LIST_VALID_ERMITTLE_KOSTEN);

	}
 
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			//zuerst die auswahlbox anzeigen: welcher adressbereich soll genutzt werden ?
			//new ownPopupWaehleGewuenschteAdressen().ShowPopup(FS_BT_Ermittle_Kosten_In_AdressListModul.this.oNaviList, new MyE2_String("Aufbau der Kostenrelatioen..."));
			
			new ownKOSTEN_SUCHE_MittlererRealer_TP_Preis().SHOW_SettingsWindow();
		}			
	}
	 
	 
	private class ownKOSTEN_SUCHE_MittlererRealer_TP_Preis extends TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis {

		public ownKOSTEN_SUCHE_MittlererRealer_TP_Preis() throws myException {
			super(bibALL.get_cDateFirstDateActualMonth(), true);
		}

		@Override
		public MyE2_MessageVector do_After_UpdateStatements() throws myException {
			return new MyE2_MessageVector();
		}
		
	}
	
	
//	private class ownPopupWaehleGewuenschteAdressen extends POPUP_QueryInList_SELECTED_SEITE_ALL {
//
//		@Override
//		public void do_when_ok_is_clicked(Vector<String> vIDs) 	throws myException {
//			
//			//Adressen merken und weiter mit der Frage der Modalitaeten der Kostenermittlung
//			FS_BT_Ermittle_Kosten_In_AdressListModul oThis = FS_BT_Ermittle_Kosten_In_AdressListModul.this;
//			
//			oThis.vIdAdressenZuBearbeiten.removeAllElements();
//			oThis.vIdAdressenZuBearbeiten.addAll(vIDs);
//			
//			oThis.oKostenSucher = new ownKostenermittler();
//			
//			//dann zeigt er die auswahl, in welchem Zeitraum geschaut werden soll
//			oThis.oKostenSucher.SHOW_SettingsWindow();
//		}
//		
//	}
	
//	private class ownKostenermittler extends TP_KOSTEN_SUCHE_MittlererRealerTransportpreis {
//	
//		public ownKostenermittler() throws myException {
//			super(bibALL.get_cDateFirstDateActualMonth(), true);
//		}
//
//		@Override
//		public XX_ActionAgent get_ActionAgentAfterSaveSettingBox() throws myException {
//			return new ownActionDoAfterPopup();
//		}
//	}
//
//	
//
//	//hier findet dann die eigengliche aktion statt
//	private class ownActionDoAfterPopup extends XX_ActionAgent {
//		MyE2_MessageVector  oMV = new MyE2_MessageVector();
//
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			FS_BT_Ermittle_Kosten_In_AdressListModul oThis = FS_BT_Ermittle_Kosten_In_AdressListModul.this;
//
//			//jetzt pruefen, ob die temporaere tabelle vorliegt
//			Prepare_StandardTempTable  oPrepStandard = new Prepare_StandardTempTable("KOSTEN_AUS_FUHREN");
//			
//			if (oPrepStandard.CHECK_IF_MUST_BE_EXECUTED()) {
//				oPrepStandard.EXECUTED_BEFORE_REPORT();
//			}
//
//			
//			
//			//alle anzahl < 20 werden ohne fortschrittsbalken bearbeitet
//			if (oThis.vIdAdressenZuBearbeiten.size()<20) {
//				for (String cID: oThis.vIdAdressenZuBearbeiten) {
//					
//					RECLIST_KOSTEN_LIEFERBED_ADR  rlKosten = new RECLIST_KOSTEN_LIEFERBED_ADR(_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS+"="+cID,
//																							_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS);
//					if (rlKosten.get_vKeyValues().size()>0) {
//						for (RECORD_KOSTEN_LIEFERBED_ADR recKosten: rlKosten.values()){
//							oThis.oKostenSucher.ErmittleUndSchreibe_KostenWert(recKosten, oMV);
//						}
//					}
//				}
//				bibMSG.add_MESSAGE(new MyE2_Info_Message(FS_BT_Ermittle_Kosten_In_AdressListModul.this.oKostenSucher.get_cSTATUS_Meldung()));
//			} else {
//				
//				new E2_ServerPushMessageContainer_STD(	new Extent(400),
//						new Extent(200),
//						new MyE2_String("Ich ermittle die Kostensätze der ausgewählten Adressen ..."),
//						true,
//						true,
//						10000,
//						oThis.vIdAdressenZuBearbeiten.size(),
//						20,
//						new MyE2_Label(new MyE2_String("Anzahl Adressen: ",true, ""+oThis.vIdAdressenZuBearbeiten.size(),false)),
//						null)
//				{
//	
//					@Override
//					public void Run_Loop() throws myException {
//						
//						int iCounter=0;
//						for (String cID: FS_BT_Ermittle_Kosten_In_AdressListModul.this.vIdAdressenZuBearbeiten) {
//							
//							RECLIST_KOSTEN_LIEFERBED_ADR  rlKosten = new RECLIST_KOSTEN_LIEFERBED_ADR(_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS+"="+cID,
//																									_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS);
//							if (rlKosten.get_vKeyValues().size()>0) {
//								for (RECORD_KOSTEN_LIEFERBED_ADR recKosten: rlKosten.values()){
//									FS_BT_Ermittle_Kosten_In_AdressListModul.this.oKostenSucher.ErmittleUndSchreibe_KostenWert(recKosten, oMV);
//									if (this.get_oBalken()!=null){
////										this.get_oBalken().set_Wert(iCounter++);
//										this.get_oGrid4AddonInfos().removeAll();
//										this.get_oGrid4AddonInfos().add(new MyE2_Label(new MyE2_String("Anzahl Adressen: ",true, ""+(iCounter++)+" ..  / .. "+
//																							FS_BT_Ermittle_Kosten_In_AdressListModul.this.vIdAdressenZuBearbeiten.size(),false)));
//									}
//								}
//							}
//							
//							if (this.get_bIsInterupted()) {	break;}
//						}
//						
//						if (oMV.get_bHasAlarms()) {
//							bibMSG.add_MESSAGE(oMV);
//						}
//						bibMSG.add_MESSAGE(new MyE2_Info_Message(FS_BT_Ermittle_Kosten_In_AdressListModul.this.oKostenSucher.get_cSTATUS_Meldung()));
//						
//					}
//	
//					@Override
//					public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane)	throws myException {
//					}
//				};
//			}
//			
//		}
//	}

	
}
