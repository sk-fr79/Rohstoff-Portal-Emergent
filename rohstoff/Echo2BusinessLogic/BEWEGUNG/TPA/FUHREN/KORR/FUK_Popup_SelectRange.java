package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KORR;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_PROTOKOLLE_BATCH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__DropDownSelectorLieferbedingungen;
import echopointng.Separator;

public class FUK_Popup_SelectRange extends E2_BasicModuleContainer {
	
	public static String       PROTOKOLL_KENNUNG = "HANDKORREKTUR_MEHRFACH_"; 
	
	private E2_NavigationList   oNaviList = 				null;
	private Vector<String>		vIDsToProcess= 				new Vector<String>();
	
	
	private MyE2_SelectField    oSelFieldLieferbedingung = 	null;
	private MyE2_CheckBox       oCB_AlleInListe = 			null;
	private MyE2_CheckBox       oCB_AlleAufSeite = 			null;
	private MyE2_CheckBox       oCB_AlleSelektierten = 		null;
	
	private MyE2_CheckBox       oCB_WE_Seite = 				new MyE2_CheckBox(new MyE2_String("WE-Seite (beim Lieferanten)"),true,false);
	private MyE2_CheckBox       oCB_WA_Seite = 				new MyE2_CheckBox(new MyE2_String("WA-Seite (beim Abnehmer)"));
	
	private MyE2_CheckBox       oCB_AnwendenAufLeere = 		new MyE2_CheckBox(new MyE2_String("Fuhren ohne Lieferbed."),true,false);
	private MyE2_CheckBox       oCB_AnwendenAufVolle = 		new MyE2_CheckBox(new MyE2_String("Fuhren mit Lieferbed."));
	
	private Vector<String> 		vID_AlleInListe = 			new Vector<String>();
	private Vector<String> 		vID_AlleAufSeite = 			new Vector<String>();
	private Vector<String> 		vID_AlleSelektierten = 		new Vector<String>();

	private HashMap<MyE2_CheckBox, Vector<String>>  hmZuordnungButtonZuVectorIDs = new HashMap<MyE2_CheckBox, Vector<String>>();
	
	
	private int iCountMandantenAdresse 					= 0;
	private int iCountVorhandene_LFB_Ueberschrieben 	= 0;
	private int iCountLeere_LFB_Ueberschrieben 			= 0;
	private int iCount_FehlerBeimSchreiben				= 0;
	private int iCount_Uebersprungen					= 0;

	private MyE2_Button  bt_Start = null;
	private MyE2_Button  bt_Abbruch = null;
	private MyE2_Button  bt_Schliessen = null;
	
	private MyE2_Grid    oGridErgebnisse = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
	
	
	public FUK_Popup_SelectRange(E2_NavigationList naviList) throws myException {
		super();
		this.oNaviList = naviList;
	
		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE_NO_CLOSE());
		
		String cHelpArr[][] = {
				{"-",""},
				{"<Lieferbedingung LEEREN>","0"}};
		
		
		
		this.oSelFieldLieferbedingung = new MyE2_SelectField(
				bibARR.add_array_inFront(new BS__DropDownSelectorLieferbedingungen(false,false).getDD(),cHelpArr), null, false);
		
		this.vID_AlleInListe.addAll(this.oNaviList.get_vectorSegmentation());
		this.vID_AlleAufSeite.addAll(this.oNaviList.get_vActualID_Segment());
		this.vID_AlleSelektierten.addAll(this.oNaviList.get_vSelectedIDs_Unformated());
		
		this.oCB_AlleInListe = 		new MyE2_CheckBox(new MyE2_String("Alle Fuhren in der momentanen Auswahl",true,"  ("+vID_AlleInListe.size()+")",false));
		this.oCB_AlleAufSeite = 	new MyE2_CheckBox(new MyE2_String("Alle Fuhren auf der momentanen Seite",true,"  ("+vID_AlleAufSeite.size()+")",false));
		this.oCB_AlleSelektierten = new MyE2_CheckBox(new MyE2_String("Alle ausgewählten Fuhren",true,"  ("+vID_AlleSelektierten.size()+")",false));

		
		this.hmZuordnungButtonZuVectorIDs.put(this.oCB_AlleInListe, 		this.vID_AlleInListe);
		this.hmZuordnungButtonZuVectorIDs.put(this.oCB_AlleAufSeite, 		this.vID_AlleAufSeite);
		this.hmZuordnungButtonZuVectorIDs.put(this.oCB_AlleSelektierten, 	this.vID_AlleSelektierten);
		
		if (this.vID_AlleSelektierten.size()>0) {
			this.oCB_AlleSelektierten.setSelected(true);
		} else {
			this.oCB_AlleAufSeite.setSelected(true);
		}
		
		
		ActionAgent_RadioFunction_CheckBoxList  oRadioBT_AuswahlRange = new ActionAgent_RadioFunction_CheckBoxList(false);
		ActionAgent_RadioFunction_CheckBoxList  oRadioBT_WA_WE = new ActionAgent_RadioFunction_CheckBoxList(false);
		
		oRadioBT_AuswahlRange.add_CheckBox(this.oCB_AlleInListe);
		oRadioBT_AuswahlRange.add_CheckBox(this.oCB_AlleAufSeite);
		oRadioBT_AuswahlRange.add_CheckBox(this.oCB_AlleSelektierten);
		
		oRadioBT_WA_WE.add_CheckBox(this.oCB_WE_Seite);
		oRadioBT_WA_WE.add_CheckBox(this.oCB_WA_Seite);
	
		this.bt_Start = new MyE2_Button(	new MyE2_String("Starte Änderungen"),
													MyE2_Button.StyleImageButtonCenteredWithBlackBorder(),
													new MyE2_String("Startet die gewünschten Änderungen"),
													new ownActionStarteAenderung());

		this.bt_Abbruch = new MyE2_Button(	new MyE2_String("Abbruch"),
													MyE2_Button.StyleImageButtonCenteredWithBlackBorder(),
													new MyE2_String("Abbruch der Aktion"),
													new ownActionAbbruch());

		
		this.bt_Schliessen = new MyE2_Button(	new MyE2_String("Schliessen"),
													MyE2_Button.StyleImageButtonCenteredWithBlackBorder(),
													new MyE2_String("Fenster schließen"),
													new ownActionSchliessen());
		
		
		
		bt_Start.setWidth(new Extent(150));
		bt_Abbruch.setWidth(new Extent(150));
		bt_Schliessen.setWidth(new Extent(150));
		
		bt_Schliessen.set_bEnabled_For_Edit(false);
		
		
		//das ergebniss-grid mit leeren werten aufbauen
		this.fuelleErgebnissGrid("");
		
		//aufbau der maske
		MyE2_Grid oGridInnen = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		this.add(oGridInnen, E2_INSETS.I(5,5,5,5));
		
		//Z1
		oGridInnen.add(new MyE2_Label(new MyE2_String("Mehrfach-Vergabe der Lieferbedingung für die Fuhren (ES WERDEN KEINE FUHRENORTE BEHANDELT!)"),new E2_FontBold(2),true),3, E2_INSETS.I(2,5,2,5));
		
		//Z2
		oGridInnen.add(new MyE2_Label(new MyE2_String("Neue Lieferbedingung:"),new E2_FontPlain(),true),1, E2_INSETS.I(2,5,2,5));
		oGridInnen.add(this.oSelFieldLieferbedingung,2, E2_INSETS.I(10,5,2,5));
		
		oGridInnen.add(new Separator(),3, E2_INSETS.I(2,2,2,2));
		
		//Z3
		oGridInnen.add(new MyE2_Label(new MyE2_String("Wareneingangs- oder Warenausgangsseite"),new E2_FontPlain(),true),1, E2_INSETS.I(2,5,2,5));
		oGridInnen.add(this.oCB_WE_Seite,1, E2_INSETS.I(10,5,2,5));
		oGridInnen.add(this.oCB_WA_Seite,1, E2_INSETS.I(2,5,2,5));

		oGridInnen.add(new Separator(),3, E2_INSETS.I(2,2,2,2));
		
		//Z4-6
		oGridInnen.add(new MyE2_Label(new MyE2_String("Welcher Fuhrenbereich:"),new E2_FontPlain(),true),1, E2_INSETS.I(2,5,2,5));
		oGridInnen.add(this.oCB_AlleInListe,2, E2_INSETS.I(10,5,2,5));
		oGridInnen.add(new MyE2_Label(new MyE2_String(""),new E2_FontPlain(),true),1, E2_INSETS.I(2,5,2,5));
		oGridInnen.add(this.oCB_AlleAufSeite,2, E2_INSETS.I(10,5,2,5));
		oGridInnen.add(new MyE2_Label(new MyE2_String(""),new E2_FontPlain(),true),1, E2_INSETS.I(2,5,2,5));
		oGridInnen.add(this.oCB_AlleSelektierten,2, E2_INSETS.I(10,5,2,5));
		
		oGridInnen.add(new Separator(),3, E2_INSETS.I(2,2,2,2));
		
		//Z7
		oGridInnen.add(new MyE2_Label(new MyE2_String("Änderungen anwenden auf:"),new E2_FontPlain(),true),1, E2_INSETS.I(2,5,2,5));
		oGridInnen.add(this.oCB_AnwendenAufLeere,1, E2_INSETS.I(10,5,2,5));
		oGridInnen.add(this.oCB_AnwendenAufVolle,1, E2_INSETS.I(2,5,2,5));
		
		oGridInnen.add(new Separator(),3, E2_INSETS.I(2,2,2,2));
		
		oGridInnen.add(new MyE2_Label(new MyE2_String("Ergebnisse:"),new E2_FontPlain(),true),1, E2_INSETS.I(2,5,2,5));
		oGridInnen.add(this.oGridErgebnisse,2, E2_INSETS.I(10,5,2,5));
		
		oGridInnen.add(new Separator(),3, E2_INSETS.I(2,2,2,2));
		
		oGridInnen.add(new E2_ComponentGroupHorizontal(0, bt_Start, bt_Abbruch , bt_Schliessen, E2_INSETS.I(0,0,10,0)), 3, E2_INSETS.I(2,5,2,5));
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(550), new MyE2_String("Korrektur der Lieferbedingungen ..."));
		
		this.add_CloseActions(new ownCloseAction());
		
	}
	
	
	
	
	
	
	private class ownActionStarteAenderung extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			FUK_Popup_SelectRange oThis = FUK_Popup_SelectRange.this;

			//klassenvariable resetten
			oThis.vIDsToProcess.removeAllElements();
			oThis.iCountMandantenAdresse 				= 0;
			oThis.iCountVorhandene_LFB_Ueberschrieben 	= 0;
			oThis.iCountLeere_LFB_Ueberschrieben 		= 0;
			oThis.iCount_FehlerBeimSchreiben			= 0;
			oThis.iCount_Uebersprungen					= 0;

			
			//jetzt die selektion suchen
			for (MyE2_CheckBox  oCB: oThis.hmZuordnungButtonZuVectorIDs.keySet()) {
				if (oCB.isSelected()) {
					oThis.vIDsToProcess.addAll(oThis.hmZuordnungButtonZuVectorIDs.get(oCB));
				}
			}
			if (oThis.vIDsToProcess.size()==0) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie einen Fuhrenbereich aus, der Fuhren enthält!")));
				return;
			}
			
			if (!(oThis.oCB_AnwendenAufLeere.isSelected() || oThis.oCB_AnwendenAufVolle.isSelected())) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen aus: Änderungen anwenden auf: Fuhren ohne oder Fuhren mit Lieferbedingungen oder beide!")));
				return;
			}

			//jetzt die lieferbedingung raussuchen
			String cID_LIEFERBED = S.NN(oThis.oSelFieldLieferbedingung.get_ActualWert()).trim();
			
			if (S.isEmpty(cID_LIEFERBED)) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte selektieren Sie eine gültige Lieferbedingung (oder die Variante <Lieferbedingungen Leeren>")));
				return;
			}

			String cLIEFERBED = null;
			if (cID_LIEFERBED.equals("0")) {
				cLIEFERBED = "";
			} else {
				RECORD_LIEFERBEDINGUNGEN recLFB = new RECORD_LIEFERBEDINGUNGEN(cID_LIEFERBED);
				cLIEFERBED = recLFB.get_KURZBEZEICHNUNG_cUF_NN(recLFB.get_BEZEICHNUNG_cUF_NN(""));
			}
			
			
			//hier gehts los
			for (String cID_VPOS_TPA_FUHRE: oThis.vIDsToProcess) {
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
				
				String cFeldNameLFBD = oThis.oCB_WE_Seite.isSelected()? _DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK: _DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK;
				String cFeldNameADRESSE = oThis.oCB_WE_Seite.isSelected()? _DB.VPOS_TPA_FUHRE$ID_ADRESSE_START: _DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL;
				
				if (recFuhre.get_UnFormatedValue(cFeldNameADRESSE,"").equals(bibALL.get_ID_ADRESS_MANDANT())) {
					iCountMandantenAdresse ++;
				} else {
					boolean bWertVorhanden = S.isFull(recFuhre.get_UnFormatedValue(cFeldNameLFBD,""));
					
					if (bWertVorhanden && oThis.oCB_AnwendenAufVolle.isSelected()) {
						if (this.SchreibeLFB(recFuhre, cLIEFERBED, cFeldNameLFBD)) {
							iCountVorhandene_LFB_Ueberschrieben++;
						} else {
							iCount_FehlerBeimSchreiben++;
						}
					} else 	if (!bWertVorhanden && oThis.oCB_AnwendenAufLeere.isSelected()) {
						if (this.SchreibeLFB(recFuhre, cLIEFERBED, cFeldNameLFBD)) {
							iCountLeere_LFB_Ueberschrieben++;
						} else {
							iCount_FehlerBeimSchreiben++;
						}
						} else {
							iCount_Uebersprungen++;
						}
					}
			}


			oThis.fuelleErgebnissGrid(S.isEmpty(cLIEFERBED)?"<LEER>":cLIEFERBED);
//			oThis.bt_Start.set_bEnabled_For_Edit(false);
//			oThis.bt_Abbruch.set_bEnabled_For_Edit(false);

			oThis.bt_Schliessen.set_bEnabled_For_Edit(true);
			
		}
		
		
		private boolean SchreibeLFB(RECORD_VPOS_TPA_FUHRE recFuhre, String cLIEFBED, String cZIELFELD) throws myException {
			recFuhre.set_NewValueForDatabase(cZIELFELD,cLIEFBED);
			boolean bRueck = false;
			if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(recFuhre.get_SQL_UPDATE_STD(), true)) {
				DEBUG.System_println("Fehler: "+recFuhre.get_SQL_UPDATE_STD());
			} else {
				DEBUG.System_println("Aktualisierung: "+recFuhre.get_SQL_UPDATE_STD());
				bRueck = true;
				this.schreibe_ProtokollEintrag(recFuhre,cLIEFBED);
			}
			return bRueck;
		}
		
		
		private boolean schreibe_ProtokollEintrag(MyRECORD  oRecord, String cLIEF_BED_NEU) throws myException {
			//protokoll schreiben 
			FUK_Popup_SelectRange oThis = FUK_Popup_SelectRange.this;

			RECORDNEW_PROTOKOLLE_BATCH recNew = new RECORDNEW_PROTOKOLLE_BATCH();
			recNew.set_NEW_VALUE_TABLE_NAME_BASE(oRecord.get_TABLE_NAME().substring(3));
			recNew.set_NEW_VALUE_ID_TABLE(oRecord.get_UnFormatedValue("ID_"+oRecord.get_TABLE_NAME().substring(3)));
			recNew.set_NEW_VALUE_JOB_KENNUNG(FUK_Popup_SelectRange.PROTOKOLL_KENNUNG+(oThis.oCB_WE_Seite.isSelected()?"WE":"WA")+"_"+S.NN(cLIEF_BED_NEU,"<LEER>")+"_"+bibALL.get_KUERZEL());
			recNew.set_NEW_VALUE_JOB_INFO_LANG("Mehrfach-Korrektur der alternativen Lieferbedingung aus der Fuhrenzentrale, durchgefuehrt von "+bibALL.get_RECORD_USER().get_VORNAME_cF_NN("<vorname>")+" "+bibALL.get_RECORD_USER().get_NAME1_cUF_NN("<name>"));
			MySqlStatementBuilder  oSTB = recNew.get__StatementBuilder(true, true);
			oSTB.addSQL_Paar(_DB.PROTOKOLLE_BATCH$ZEITSTEMPEL, "SYSDATE", false);
			return bibDB.ExecSQL(oSTB.get_CompleteInsertString(_DB.PROTOKOLLE_BATCH, bibE2.cTO()), true);
		}

		
	}
	
	
	private class ownActionSchliessen extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FUK_Popup_SelectRange.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
		
	}
	
	
	private class ownActionAbbruch extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FUK_Popup_SelectRange.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
		
	}
	
	
	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow {
		public ownCloseAction() {
			super(FUK_Popup_SelectRange.this);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (this.get_oContainer().get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_SAVE)) {
				FUK_Popup_SelectRange.this.oNaviList._REBUILD_ACTUAL_SITE(null);
			}
		}
	}
	
	
	
	
	private void fuelleErgebnissGrid(String cNeuLieferBed) {
		this.oGridErgebnisse.removeAll();

		this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Anzahl Fuhren:"),this.vIDsToProcess.size()));
		this.oGridErgebnisse.add(new ownLabel(this.vIDsToProcess.size()));
		
		this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Eigene Lager:"),this.iCountMandantenAdresse));
		this.oGridErgebnisse.add(new ownLabel(this.iCountMandantenAdresse));
		
		this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Übersprungen:"),this.iCount_Uebersprungen));
		this.oGridErgebnisse.add(new ownLabel(this.iCount_Uebersprungen));
		
		if (S.isEmpty(cNeuLieferBed)) {
			this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Vorhandene überschrieben:"),this.iCountVorhandene_LFB_Ueberschrieben));
			this.oGridErgebnisse.add(new ownLabel(this.iCountVorhandene_LFB_Ueberschrieben));
		
			this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Leere überschrieben:"),this.iCountLeere_LFB_Ueberschrieben));
			this.oGridErgebnisse.add(new ownLabel(this.iCountLeere_LFB_Ueberschrieben));
		} else {
			this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Vorhandene überschrieben mit ",true, "<"+cNeuLieferBed+">",false),this.iCountVorhandene_LFB_Ueberschrieben));
			this.oGridErgebnisse.add(new ownLabel(this.iCountVorhandene_LFB_Ueberschrieben));
		
			this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Leere überschrieben mit ",true,"<"+cNeuLieferBed+">",false),this.iCountLeere_LFB_Ueberschrieben));
			this.oGridErgebnisse.add(new ownLabel(this.iCountLeere_LFB_Ueberschrieben));
		}
		this.oGridErgebnisse.add(new ownLabel(new MyE2_String("Fehler beim Schreiben:"),this.iCount_FehlerBeimSchreiben));
		this.oGridErgebnisse.add(new ownLabel(this.iCount_FehlerBeimSchreiben));

	}


	private class ownLabel extends MyE2_Label {
		
		public ownLabel(long lAnzahl) {
			super(""+lAnzahl);
			this.setze_style_Center(lAnzahl);
		}
		
		public ownLabel(MyE2_String oText, long lAnzahl) {
			super(oText);
			this.setze_style_left(lAnzahl);
		}
		
		
		private void setze_style_Center(long lAnzahl) {
			if (lAnzahl>0) {
				this.setLayoutData(MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,0,2,0), new E2_ColorHelpBackground(), 1));
				this.setStyle(MyE2_Label.STYLE_SMALL_BOLD());
			} else {
				this.setLayoutData(MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,0,2,0), new E2_ColorBase(), 1));
				this.setStyle(MyE2_Label.STYLE_SMALL_PLAIN());
			}

		}
		
		private void setze_style_left(long lAnzahl) {
			if (lAnzahl>0) {
				this.setLayoutData(MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,0,2,0), new E2_ColorHelpBackground(), 1));
				this.setStyle(MyE2_Label.STYLE_SMALL_BOLD());
			} else {
				this.setLayoutData(MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,0,2,0), new E2_ColorBase(), 1));
				this.setStyle(MyE2_Label.STYLE_SMALL_PLAIN());
			}

		}

		
	}
	
}
