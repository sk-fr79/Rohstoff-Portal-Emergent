package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ApplicationInstanceWithServerPushUpdateTask;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_OWN_RECLIST_MAIL_AUS_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_StartMail_PopUP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_100_percent;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField_WithSelektor;
import panter.gmbh.Echo2.components.MyE2_TextField_WithSelektorEASY;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.barcode.Barcode_PopupContainer;
import panter.gmbh.Echo2.components.specialValidation.ExplicitRuleSetter;
import panter.gmbh.Echo2.components.unboundDataFields.UB_CheckBox;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_Float_Nullable;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_Integer;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WAAGE_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAAGE_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAAGE_STANDORT;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO__CONST;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase.ENUM_WaageResultStatus;
import rohstoff.Echo2BusinessLogic.WAAGE.HANDLER.WaageHandlerBase;
import rohstoff.Echo2BusinessLogic.WAAGE.HANDLER.WaageHandlerFactory;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BT_PRINT.ENUM_DruckTyp;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_SELEKTOR_MaschinenTyp;
import echopointng.Separator;
import echopointng.Strut;

/*
 * modul, zur schnellen erfassung von fahrplanpositionen (fuer mitarbeiter mit nicht allen
 * kenntnissen
 */
public class WK_Erfassung_Waegung extends Project_BasicModuleContainer {
	private static final long serialVersionUID = -6854963116255277433L;

	/**
	 * NEU: neue Maske zum erfassen der Daten STAMMDATEN: die Stammdaten sind
	 * schon gespeichert, d.h. die Wiegekarte wurde schon gespeichert WAEGUNG1:
	 * die 1. Wägung wurde durchgeführt WAEGUNG2: die 2. Wägung wurde
	 * durchgeführt. FUHRE_OK: das Gewicht / WK-ID wurde schon in der Fuhre
	 * eingetragen
	 * 
	 * @author manfred
	 * 
	 */
	public enum ENUM_ZustandMaske { 
		NEU, STAMMDATEN, WAEGUNG1, WAEGUNG2, GEDRUCKT
	}
	
	
	
		
	private ExplicitRuleSetter m_explicitRuleSetter  = null;

	private ENUM_ZustandMaske m_enumZustandMaske = ENUM_ZustandMaske.NEU;

	private E2_ApplicationInstanceWithServerPushUpdateTask m_ServerPushTask = null;

	private E2_NavigationList m_navigationList = null;
	private boolean m_bIsDirty = false;
	private String m_IdUser = null;

	/**
	 * die Wiegekarte-ID ist in der Fuhre gesetzt, d.h. zugeordnet
	 */
	private boolean m_FuhreIstZugeordnet = false;
	/**
	 * die Fuhre-ID ist in der Wiegekarte gesetzt
	 */
	private boolean m_FuhreIstGesetzt = false;

	/**
	 * die fuhrenID in der Wiegekarte und die Fuhre, in der die Wiegekarte
	 * gesetzt ist stimmen überein.
	 */
	private boolean m_FuhrenzuordnungIstOK = false;

	
	/**
	 * Schalter der festlegt, ob bei einer Folgewägung automatisch ein Gewicht gelesen und eingetragen wird
	 * oder nicht
	 */
	boolean m_bLeseGewichtNachFolgewaegung = false;
	
	
	
	// /** true, wenn die Kennzeichenprüfung durchlaufen wurde */
	// private boolean m_KennzeichenOK = false;
	// /** das Kennzeichen das geprüft wurde wird hier nochmal
	// zwischengespeichert, falls es nochmal geändert wird **/
	// private String m_KennzeichenGeprüft = null;
	// /** AdressID, wenn die Kennzeichenprüfung eine Adresse ergeben hat. */

	// popup für Mailversendung
	MMC_StartMail_PopUP 					m_popup_menuMailVersand = null;
	
	// Speichern-Button für die Wiegekarte
	private MyE2_Button 					m_btnSaveStamm = null;
	private MyE2_Button 					m_btnSaveStammAndExit = null;
	private MyE2_Button 					m_btnCreateFolge = null;
	private MyE2_Button 					m_btnCancel = null;
	private MyE2_Button 					m_btnSaveStamm2 = null;

	// Druckbuttons und Eingaben
	private WK_LIST_BT_PRINT 				m_btnPrintWiegekarte = null;
	private WK_LIST_BT_PRINT 				m_btnPrintWiegekarteEingangsschein = null;
	private WK_LIST_BT_PRINT 				m_btnPrintEingangsscheinLieferschein = null;
	
	private UB_TextField 					tfAnzahlEtiketten = null;
	private WK_LIST_BT_PRINT 				m_btnPrintEtikett = null;
	private WK_LIST_BT_PRINT 				m_btnPrintBuero = null;
	
	private WK_LIST_BT_PRINT				m_btnPrintHofschein = null;

	
	
	// Fuhre/ Fuhrenort
	private UB_TextField 					tfFuhre = null;
	private UB_TextField 					tfFuhreOrt = null;
	private WK_SearchFieldFuhre 			oSearchFuhre = null;
	private MyE2_Label 						lblStatusFuhre = null;
	
	private MyE2_Button 					m_btnReadBarcode = null;
	private Barcode_PopupContainer 			m_barcodePopup = null;
	
	private MyE2_Button						m_btnRemoveLinkToFuhre = null;
	
	private MyE2_Label 						m_lblEingangsscheinNr = null;
	private MyE2_Label					 	m_lblIDWiegekarte = null;
	private MyE2_Label 						m_lblStorno = null;
	private MyE2_Label 						m_lblInfo = null;
	

	// Wiegekarte_typ
	private MyE2_CheckBox 					cbWiegekarte_Wiegeschein = null;
	private MyE2_CheckBox 					cbWiegekarte_Strecke = null;
	private MyE2_CheckBox 					cbWiegekarte_Fremdwiegung = null;
	private MyE2_CheckBox 					cbWiegekarte_Wiegeschein_Lieferschein = null;
	private MyE2_CheckBox 					cbWiegekarte_Retoure = null;
	private MyE2_CheckBox 					cbWiegekarte_Lager = null;
	private MyE2_CheckBox 					cbWiegekarte_Leerfuhre = null;
	private MyE2_CheckBox 					cbWiegekarte_Dokumentarisch = null;
	
	private ActionAgent_RadioFunction_CheckBoxList agentWatchdogWiegekartenTyp = null;


	private MyE2_Column 					colCB_WE = null;
	private MyE2_Column 					colCB_WA = null;
	private MyE2_CheckBox 					cbWarenEingang = null;
	private MyE2_CheckBox 					cbWarenAusgang = null;
	private ActionAgent_RadioFunction_CheckBoxList agentWatchdogWEWA = null;

	
	private MyE2_TextField_WithSelektor 	tfKennzeichen = null;
	private MyE2_CheckBox					cbErlaubeMehrfachwiegungen = null;

	// Kunden-Adresse
	private WK_UB_SearchFieldAdresse 		oSearchAdresse = null;
	private UB_CheckBox 					cbAdresseNeu = null;
	private UB_TextArea 					tfAdresse = null;

	// Sorte
	private WK_UB_SearchFieldSorte 			oSearchSorte = null;
	private MyE2_SelectFieldWithParameters 	cmbSorteKunde = null;
	private UB_CheckBox 					cbSorteHand = null;
	private UB_TextArea						tfSortenInfo = null;
	
	// Speditions-Adresse
	private WK_UB_SearchFieldAdresse 		oSearchAdresseSpedition = null;
	private UB_CheckBox 					cbAdresseSpeditonNeu = null;
	private UB_TextArea 					tfAdresseSpedion = null;

	// Streckenabnehmer
	private WK_UB_SearchFieldAdresse 		oSearchAdresseAbnehmerStrecke = null;

	// Bemerkungsfelder
	private UB_TextField 					tfBemerkung1 = null;
	
	// 2018-05-15
	private UB_TextField					tfContainerNr = null;
	private UB_TextField					tfSiegelNr = null;
	//
	
	private UB_TextField 					tfBemerkung2 = null;
	private UB_TextField 					tfBemerkungIntern = null;
	private UB_TextArea 					tfBefund = null;
	

	// Wägung 1
	private UB_TextField 					tfW1Datum = null;
	private UB_TextField 					tfW1Zeit = null;
	private UB_TextField 					tfW1Gewicht = null;
	private UB_TextField 					tfW1Id = null;
	private UB_CheckBox 					cbW1Hand = null;
	private MyE2_Button 					oButtonW1ReadData = null;
	private MyE2_TextField_WithSelektorEASY tfW1HandDesc = null;

	
	// Wägung 2
	private UB_TextField 					tfW2Datum = null;
	private UB_TextField 					tfW2Zeit = null;
	private UB_TextField 					tfW2Gewicht = null;
	private UB_TextField 					tfW2Id = null;
	private UB_CheckBox 					cbW2Hand = null;
	private MyE2_Button 					oButtonW2ReadData = null;
	private MyE2_TextField_WithSelektorEASY tfW2HandDesc = null;

	
	// Abzugsfelder
	private MyE2_LabelWithBorder 			lblGewicht = null;
	private MyE2_LabelWithBorder 			lblGewichtAbzug = null;
	private UB_TextField 					tfGrundAbzug = null;
	private MyE2_LabelWithBorder 			lblGewichtNachAbzug = null;
	private MyE2_LabelWithBorder			lblGewichtAbzugFuhre = null;
	private MyE2_LabelWithBorder			lblGewichtNachAbzugFuhre = null;
	
	

	// 2. Spalte Gesamtverwiegung und Transportgebinde 
	private MyE2_CheckBox 					cbGesamtverwiegung = null;
	private MyE2_CheckBox 					cbRadioaktivitaetGeprueft = null;
	private WK_MASK_Daughter_Abzuege_Gebinde m_oAbzuegeGebinde = null;
	private WK_MASK_Daughter_Abzuege_Mengen	 m_oAbzuegeMengen = null;
	private WK_MASK_Daughter_Container		 m_oContainer = null;
	

	// Waagenanzeige
	private RECLIST_WAAGE_SETTINGS 			m_listWaageSettings = null;
	private ArrayList<WK_Row_Waagedisplay> 	m_listWaageDisplays = null;
	private MyE2_Grid 						m_oGridWaagen = null;

	private MyE2_SelectField				m_selWaageBediener = null;
	
	
	//
	// Sonstiges
	// 

	private String 							m_IDWiegekarte = null;
	private WK_WiegekartenHandler 			m_oWKHandler = null;


	private String 							m_IdAdresseLager = null;
	private String							m_IdStandort = null;
	
	private String 							m_LagerName = null;
	private MyE2_Label 						lblLagerName = null;


	private VECTOR_UB_FIELDS 				vUnboundFields = new VECTOR_UB_FIELDS();


	private Alignment 						m_alignRight = new Alignment(Alignment.RIGHT,	Alignment.CENTER);



	private boolean 						m_bOpenAsCopy = false;
	private boolean 						m_bCreateFolgewiegekarte = true;
	private String  						m_IDFolgewiegekarte = null;
	
	// Druck-Handler für die automatischen Drucke
	private WK_Print_Handler  				m_WK_PrintHandler = null;
	
	// Mandantenweite Einstellungen bzgl. Mindestlast 
	private boolean 						m_bMindestlastErforderlich = true;
	private boolean 						m_bWarnungDoppelteMindestlastBeiWE = false;
	private boolean 						m_bDruckWennNurHandwaegung = false;
	
	private boolean 						m_bShowContainerverwaltung = false;
	 
	
	/**
	 * Initialisieren einer Wagung durch die NavigationList. Wird nur aktiv,
	 * wenn genau 1 Eintrag der Liste gewählt wurde
	 * 
	 * @param navigationlist
	 *            - die aktuelle navigationlist
	 * @param useCurrentSelectedID
	 *            - wenn true, wird geprüft ob ein Eintrag gewählt ist ; wenn
	 *            false, wird eine neue Wägung initiiert
	 * @author manfred
	 * @throws myException
	 */
	public WK_Erfassung_Waegung(E2_NavigationList navigationlist, boolean useCurrentSelectedID, String IDLager, String IDStandort, boolean bOpenAsCopy, boolean bCreateFolge) throws myException {
		super(E2_MODULNAMES.NAME_MODUL_WAEGUNG_ERFASSUNG);

		m_navigationList = navigationlist;
		m_IdAdresseLager = IDLager;
		m_IdStandort = IDStandort;
		
		m_bOpenAsCopy = bOpenAsCopy;
		m_bCreateFolgewiegekarte = bCreateFolge;

		if (useCurrentSelectedID) {
			// übernehmen der aktuell gewählten Wiegekarte (wenn eine gewählt
			// wurde)
			Vector<String> vSelectedIDs = this.m_navigationList.get_vSelectedIDs_Unformated();
			if (vSelectedIDs.size() == 1) {
				m_IDWiegekarte = vSelectedIDs.get(0);
			}
		}

		
		// den Print-Handler initialisieren, damit die Instanz immer vorhanden ist und die Temporären Dateien erst gelöscht werden, wenn die Maske gelöscht wird
		// bzw. das Objekt zerstört wird
		m_WK_PrintHandler = new  WK_Print_Handler();
		
		
		m_oWKHandler = new WK_WiegekartenHandler();
		
		// Maske aufbauen
		initGUI();
		
		// Maske initialisieren
		this.init_ErfassungWaegung(navigationlist, m_IDWiegekarte /*, IDLager*/ );
	}

	
	
	

	/**
	 * Initialisieren einer neuen (Folge)Wägung durch eine Wiegekarten-Id d.h.
	 * alle Wiegekarten-Daten werden kopiert in eine neue Wiegekarte. Dann wird
	 * eine Wägung ausgelöst auf der gleichen Waage, die auch die zweite Wägung
	 * der ursprünglichen Waegung ausgeführt hat.
	 * 
	 * @param IDWiegekarte
	 * @throws myException
	 */
	public WK_Erfassung_Waegung(E2_NavigationList navigationlist, String IDWiegekarte, String IDLager, String IDStandort, boolean bOpenAsCopy, boolean bCreateFolge) throws myException {
		super(E2_MODULNAMES.NAME_MODUL_WAEGUNG_ERFASSUNG);

		
		m_bOpenAsCopy = bOpenAsCopy;
		m_bCreateFolgewiegekarte = bCreateFolge;
		
		m_navigationList = navigationlist;
		m_IdAdresseLager = IDLager;
		m_IdStandort = IDStandort;
		

		m_oWKHandler = new WK_WiegekartenHandler();
		
		// Maske aufbauen
		initGUI();

		// Maske initialisieren
		this.init_ErfassungWaegung(m_navigationList, IDWiegekarte /*,m_IdAdresseLager*/);

	}

	
	
	/**
	 * Setzt die Regelfelder in der Maske für die definierbaren Regeln
	 * @author manfred
	 * @date   12.03.2012
	 * @throws myException
	 */
	private void initRuleSetter() throws myException {
		m_explicitRuleSetter = new ExplicitRuleSetter(E2_MODULNAMES.NAME_MODUL_WAEGUNG_ERFASSUNG);
		m_explicitRuleSetter.addRule("BEMERKUNG_WIEGEKARTE", tfBemerkung1);
		m_explicitRuleSetter.addRule("BEMRKUNG_INTERN", tfBemerkung2);
	}
	

	
	/**
	 * Hauptroutine zum initialisieren der Wiegkarten-Erfassungsmaske
	 * 
	 * @param navigationlist
	 * @param IDWiegekarte
	 * @param IDLager
	 * @param createFolgewaegung
	 * @throws myException
	 */
	private void init_ErfassungWaegung(E2_NavigationList navigationlist, String IDWiegekarte /*, String IDLager*/) throws myException {
		m_enumZustandMaske = ENUM_ZustandMaske.NEU;

		m_IDWiegekarte = IDWiegekarte;

		// lager finden, wenn es schon eine Wiegekarte gibt
		if (m_IDWiegekarte != null) {
			//
		}

		// Kundensorten setzen. Muss einmal gemacht werden damit die Combobox
		// initialisiert ist
		refreshSorteKunden();

		
		// Serverpush-Task initialisieren
		if (m_ServerPushTask != null){
			m_ServerPushTask.stop();
		}
		m_ServerPushTask = new E2_ApplicationInstanceWithServerPushUpdateTask(3000);

		
		m_ServerPushTask.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getTempWaegungen();
			}
		});
	

		// Einstellungen der Mandaten-Settings laden
		m_bDruckWennNurHandwaegung 			= bib_Settigs_Mandant.get_Waage_IstDruckeWiegekarteWennNurHandwaegung();
		m_bMindestlastErforderlich 			= bib_Settigs_Mandant.get_Waage_IstSPERRUNGBeiMindestlastUnterschreitung();
		m_bWarnungDoppelteMindestlastBeiWE 	= bib_Settigs_Mandant.get_Waage_IstWARNUNGBeiMindestlastUnterschreitungWE();
				
		
		// Daten Initialisieren
		initData();

		// den Status der Sortenauswahl-Elemente setzen
		setStatusSorteneingabe();

		// Status der Datenfelder
		setFieldStatus();
	}

	
	
	/**
	 * Initialisieren der Maske
	 * 
	 * @throws myException
	 */
	private void initGUI() throws myException {

		// Anzeige Containerverwaltung ein/ausschalten  
		m_bShowContainerverwaltung = ENUM_MANDANT_DECISION.USE_WIEGEKARTE_CONTAINER.is_YES();
		
		
		actionRefreshKundenSorte oActionAgentRefreshKundensorte = new actionRefreshKundenSorte();
		actionCB_WE_WA oActionWEWA = new actionCB_WE_WA();

		MutableStyle styleTemp = null;

		m_btnSaveStamm = new MyE2_Button(new MyE2_String("Speichern").CTrans(),
				E2_ResourceIcon.get_RI("save.png"),
				E2_ResourceIcon.get_RI("leer.png"));
		m_btnSaveStamm.add_oActionAgent(new actionSaveWiegekarte(false));

		m_btnSaveStammAndExit = new MyE2_Button(new MyE2_String(
				"Speichern & Schließen").CTrans(),
				E2_ResourceIcon.get_RI("save.png"),
				E2_ResourceIcon.get_RI("leer.png"));
		m_btnSaveStammAndExit.add_oActionAgent(new actionSaveWiegekarte(true));

		m_btnCancel = new MyE2_Button(new MyE2_String("Abbrechen").CTrans(),
				E2_ResourceIcon.get_RI("cancel.png"),
				E2_ResourceIcon.get_RI("leer.png"));
		m_btnCancel.add_oActionAgent(new XX_ActionAgent() {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				// versuchen in der Navigationlist den Datensatz zu kennzeichnen
				if (m_navigationList != null) {
					m_navigationList.RefreshList();
					m_navigationList.Mark_ID_IF_IN_Page(m_IDWiegekarte);
				}
				WK_Erfassung_Waegung.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		});

		// Druckbuttons
		m_btnPrintWiegekarte = new WK_LIST_BT_PRINT(this, "Wiegekarte",ENUM_DruckTyp.WIEGEKARTE);
		m_btnPrintWiegekarte.add_oActionAgent(new actionReloadWiegekartenData());
		
		m_btnPrintWiegekarteEingangsschein = new WK_LIST_BT_PRINT(this,"Wiegekarte + Eingangs-/Lieferschein", ENUM_DruckTyp.WK_EINGANGSSCHEIN);
		m_btnPrintWiegekarteEingangsschein.add_oActionAgent(new actionReloadWiegekartenData());

		m_btnPrintEingangsscheinLieferschein = new WK_LIST_BT_PRINT(this,"Eingangsschein/Lieferschein", ENUM_DruckTyp.EINGANGS_LIEFERSCHEIN);
		m_btnPrintEingangsscheinLieferschein.add_oActionAgent(new actionReloadWiegekartenData());
		
		m_btnPrintEtikett = new WK_LIST_BT_PRINT(this, "Etikett",ENUM_DruckTyp.ETIKETT);
		
		m_btnPrintBuero = new WK_LIST_BT_PRINT(this, "Ausgang Büro",ENUM_DruckTyp.BUERO);
		m_btnPrintHofschein = new WK_LIST_BT_PRINT(this, "Hofschein", ENUM_DruckTyp.HOFSCHEIN);
		

		
		// Anzahl der Etiketten zu drucken
		tfAnzahlEtiketten = new UB_TextField("ANZAHL_ETIKETTEN", true, "", 40,20);
		tfAnzahlEtiketten.add_InputValidator(new VALIDATE_INPUT_Float_Nullable());
		tfAnzahlEtiketten.setAlignment(m_alignRight);
		tfAnzahlEtiketten.setText("1");

		// Folgewaegung
		m_btnCreateFolge = new MyE2_Button(
				new MyE2_String("Folge-Wägung").CTrans(),
				E2_ResourceIcon.get_RI("new.png"),
				E2_ResourceIcon.get_RI("leer.png"));
		m_btnCreateFolge.add_oActionAgent(new actionGenerateFolgewaegung());

		// Kundenadresse
		oSearchAdresse = new WK_UB_SearchFieldAdresse("ID_ADRESSE", true, 200,100);
		oSearchAdresse.set_bLabel4AnzeigeStattText4Anzeige(true);

		oSearchAdresse.get_oTextForAnzeige().setWidth(new Extent(390));
		oSearchAdresse.get_oTextForAnzeige().setFont(new E2_FontPlain(+2));
		oSearchAdresse.get_oLabel4Anzeige().setWidth(new Extent(390));
		oSearchAdresse.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain(+2));
		oSearchAdresse.addActionAgent_ValueChanged(oActionAgentRefreshKundensorte);

		// alternative Kundenadresse/ nicht im System
		cbAdresseNeu = new UB_CheckBox("ADRESSE_NEU", new MyE2_String(
				"Adresse Hand").CTrans(), null);
		cbAdresseNeu.add_oActionAgent(new actionCBAdresseNeu());
		cbAdresseNeu.setSelected(false);
		styleTemp = (MutableStyle) cbAdresseNeu.getStyle();
		styleTemp.setProperty(CheckBox.PROPERTY_BORDER, null);

		tfAdresse = new UB_TextArea("ADRESSE_LIEFERANT", true, "", 500, 2, 500);
		tfAdresse.set_bEnabled_For_Edit(false);
		tfAdresse.set_iMaxInputSize(500);

		// Spedition
		oSearchAdresseSpedition = new WK_UB_SearchFieldAdresse(	"ID_ADRESSE_SPEDITION", true, 200, 100);
		oSearchAdresseSpedition.get_oTextForAnzeige().setWidth(new Extent(390));
		oSearchAdresseSpedition.get_oTextForAnzeige().setFont(new E2_FontPlain());
		
		cbAdresseSpeditonNeu = new UB_CheckBox("ADRESSE_SPEDITION_NEU",	new MyE2_String("Spedition Zusatz").CTrans(), null);
		cbAdresseSpeditonNeu.add_oActionAgent(new actionCBAdresseSpeditionNeu());
		cbAdresseSpeditonNeu.setSelected(false);
		styleTemp = (MutableStyle) cbAdresseSpeditonNeu.getStyle();
		styleTemp.setProperty(CheckBox.PROPERTY_BORDER, null);
		
		tfAdresseSpedion = new UB_TextArea("ADRESSE_SPEDITION_HAND", true, "",	500, 2, 500);
		tfAdresseSpedion.set_bEnabled_For_Edit(false);
		tfAdresseSpedion.set_iMaxInputSize(500);

		
		// LKW - Kennzeichen oder allgemeine Kennzeichnung
		String sqlLKWs = " SELECT UPPER( REPLACE(M.KFZKENNZEICHEN,'-',' ') ) "
				+ " FROM  "
				+ bibE2.cTO()
				+ ".JT_MASCHINENTYP  T INNER JOIN "
				+ bibE2.cTO()
				+ ".JT_MASCHINEN M  ON   ( T.ID_MASCHINENTYP = M.ID_MASCHINENTYP    ) "
				+ " WHERE     NVL(T.IST_LKW ,'N') = 'Y' AND NVL(M.AKTIV,'N') = 'Y' ORDER BY 1  "
				+ " ";
		tfKennzeichen = new MyE2_TextField_WithSelektor(sqlLKWs, 200);
		tfKennzeichen.get_oTextField().setFont(new E2_FontBold(4));
		tfKennzeichen.get_oTextField().set_iMaxInputSize(20);
		tfKennzeichen.get_oPopUp().add_ActionAgentToPopupButtons(new actionSetSpedition(), false);
		if (tfKennzeichen.get_oPopUp().get_ButtonCount() > 14) {
			tfKennzeichen.get_oPopUp().setPopUpTopOffset( 20 * (tfKennzeichen.get_oPopUp().get_ButtonCount() - 14));
		}

		// Mehrfachwiegungen erlauben
		cbErlaubeMehrfachwiegungen = new MyE2_CheckBox(new MyE2_String("Erlaube Mehrfachwiegungen"));
		cbErlaubeMehrfachwiegungen.setToolTipText(new MyE2_String("Aktivieren, wenn ein Fahrzeug mehrmals die gleiche Sorte am gleichen Tag laden oder abladen darf").CTrans());
		styleTemp = (MutableStyle) cbErlaubeMehrfachwiegungen.getStyle();
		styleTemp.setProperty(CheckBox.PROPERTY_BORDER, null);
		
		// Sorte
		oSearchSorte = new WK_UB_SearchFieldSorte("ID_ARTIKEL_BEZ", true);
		oSearchSorte.get_oTextForAnzeige().setWidth(new Extent(390));
		oSearchSorte.get_oTextForAnzeige().setFont(new E2_FontPlain());

		cbSorteHand = new UB_CheckBox("SORTE_HAND", new MyE2_String("Sorte Hand").CTrans(), null);
		styleTemp = (MutableStyle) cbSorteHand.getStyle();
		styleTemp.setProperty(CheckBox.PROPERTY_BORDER, null);
		cbSorteHand.add_oActionAgent(oActionAgentRefreshKundensorte, true);
		cbSorteHand.add_oActionAgent(new actionCBSorteHand());

		
		// ermittlung des AVV-Codes durch Subselect
		String sSqlAVV = " || ' AVV: ' || "   
			+ "     ( "
			+ "     SELECT "
			+ "      JT_EAK_BRANCHE.KEY_BRANCHE||'-'|| "
			+ "      JT_EAK_GRUPPE.KEY_GRUPPE||'-'|| "
			+ "      JT_EAK_CODE.KEY_CODE||' '|| "
			+ "      TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ') "
			+ "   FROM "
			+ "     JT_EAK_CODE "
			+ "     LEFT OUTER JOIN JT_EAK_GRUPPE ON (JT_EAK_CODE.ID_EAK_GRUPPE          = JT_EAK_GRUPPE.ID_EAK_GRUPPE) "
			+ "     LEFT OUTER JOIN JT_EAK_BRANCHE ON (JT_EAK_GRUPPE.ID_EAK_BRANCHE   = JT_EAK_BRANCHE.ID_EAK_BRANCHE) "
			+ "   WHERE "
			+ "     JT_EAK_CODE.ID_EAK_CODE=nvl( "
			+ "                                 (SELECT MAX(ABL1.ID_EAK_CODE) FROM JT_ARTIKELBEZ_LIEF ABL1 "
			+ "                                          	WHERE ABL1.ID_ADRESSE          = A.ID_ADRESSE "
			+ "                                          	AND ABL1.ID_ARTIKEL_BEZ        = B.ID_ARTIKEL_BEZ "
			+ "                                          	AND ABL1.ARTBEZ_TYP            =  nvl('#EKVK#','-')) "
			+ "                                  , 													"
			+ "                                  ( SELECT CASE WHEN 'EK'= nvl('#EKVK#','-') " 
			+ "                                                        THEN   A1.ID_EAK_CODE "
			+ "                                                        ELSE   A1.ID_EAK_CODE_EX_MANDANT " 
			+ "                                                        END "
			+ "                                                  FROM JT_ARTIKEL A1 "
			+ "                                                  INNER JOIN JT_ARTIKEL_BEZ B1 ON A1.ID_ARTIKEL = B1.ID_ARTIKEL "
			+ "                                                  WHERE B1.ID_ARTIKEL_BEZ = B.ID_ARTIKEL_BEZ "   
			+ "                                           ) "
			+ "                                 ) "
			+ "     ) ";
		
		
		// dynamisch angepasste Sortenauswahl
		cmbSorteKunde = new MyE2_SelectFieldWithParameters(
				"select AR.ANR1 || '-' || B.ANR2 || ' ' || B.ARTBEZ1 || NVL2(B.ARTBEZ2, ' ' || B.ARTBEZ2, '')  || ' (' || to_char(B.ID_ARTIKEL_BEZ) || ')' " + sSqlAVV +" , "
						+ " B.ID_ARTIKEL_BEZ "
						+ " FROM "
						+ bibE2.cTO()
						+ ".JT_ADRESSE A "
						+ " INNER JOIN "
						+ bibE2.cTO()
						+ ".JT_ARTIKELBEZ_LIEF  L 	ON A.ID_ADRESSE = L.ID_ADRESSE "
						+ " INNER JOIN "
						+ bibE2.cTO()
						+ ".JT_ARTIKEL_BEZ B  		ON L.ID_ARTIKEL_BEZ = B.ID_ARTIKEL_BEZ "
						+ " INNER JOIN "
						+ bibE2.cTO()
						+ ".JT_ARTIKEL AR           ON B.ID_ARTIKEL = AR.ID_ARTIKEL "
						+ " WHERE A.ID_MANDANT =  "	+ bibALL.get_ID_MANDANT()
						+ " AND L.ARTBEZ_TYP = nvl('#EKVK#','-')"
						+ " AND A.ID_ADRESSE = nvl(#ID#,-1)" 
						+ " AND A.ADRESSTYP = 1 " 
						+ " AND nvl(AR.AKTIV,'N') = 'Y' " 
						+ " AND nvl(B.AKTIV,'N') = 'Y' " 
						+ " " 
						+ " UNION " 
						+ " "  
						+" SELECT AR.ANR1 || '-' || B.ANR2 || ' ' || B.ARTBEZ1 || NVL2(B.ARTBEZ2, ' ' || B.ARTBEZ2, '')  || ' (' || to_char(B.ID_ARTIKEL_BEZ) || ')' " + sSqlAVV +" , "
						+ " B.ID_ARTIKEL_BEZ "
						+ " FROM "
						+ bibE2.cTO()+ ".JT_ADRESSE AL INNER JOIN " 
						+ bibE2.cTO()+ ".JT_LIEFERADRESSE LA 	ON AL.ID_ADRESSE = LA.ID_ADRESSE_LIEFER " 
						+ " INNER JOIN "
						+ bibE2.cTO()+ ".JT_ADRESSE A 			ON A.ID_ADRESSE = LA.ID_ADRESSE_BASIS "
						+ " INNER JOIN "
						+ bibE2.cTO()+ ".JT_ARTIKELBEZ_LIEF  L 	ON A.ID_ADRESSE = L.ID_ADRESSE "
						+ " INNER JOIN "
						+ bibE2.cTO()+ ".JT_ARTIKEL_BEZ B  		ON L.ID_ARTIKEL_BEZ = B.ID_ARTIKEL_BEZ "
						+ " INNER JOIN "
						+ bibE2.cTO()+ ".JT_ARTIKEL AR          ON B.ID_ARTIKEL = AR.ID_ARTIKEL "
						+ " WHERE A.ID_MANDANT =  "	+ bibALL.get_ID_MANDANT()
						+ " AND L.ARTBEZ_TYP = nvl('#EKVK#','-') "
						+ " AND AL.ID_ADRESSE = nvl(#ID#,-1) " 
						+ " AND AL.ADRESSTYP = 5 "
						+ " AND nvl(AR.AKTIV,'N') = 'Y' " 
						+ " AND nvl(B.AKTIV,'N') = 'Y' " 
						+ " ORDER BY 1 ",
				false, true, true, false);

		cmbSorteKunde.AddParameter("#ID#");
		cmbSorteKunde.AddParameter("#EKVK#");
		cmbSorteKunde.setToolTipText(new MyE2_String("Dem Kunden zugewiesene Sorten.").CTrans());
		cmbSorteKunde.add_oActionAgent(new actionCMBSorte());

		tfSortenInfo = new UB_TextArea("SORTEN_INFO", true, "", 500, 2, 500);
		tfSortenInfo.setFont(new E2_FontBold(+2));
		tfSortenInfo.setBackground(new E2_ColorBase());
		tfSortenInfo.set_bEnabled_For_Edit(false);
		
		
		// Checkbox ob Wareneingang oder Warenausgang
		// Kapseln in einer Column, um die Hintergrund-Farben korrekt
		// einzustellen.
		// das geht nicht über den CB-Style, da dieser nur verzögert
		// aktualisiert wird...
		colCB_WA = new MyE2_Column();
		colCB_WE = new MyE2_Column();

		MutableStyle oStyleCB = MyE2_CheckBox
				.STYLE_NORMAL_NO_BORDER_NO_INSETS();
		oStyleCB.setProperty(CheckBox.PROPERTY_FONT, new E2_FontPlain(3));

		cbWarenEingang = new MyE2_CheckBox(new MyE2_String("Wareneingang "),oStyleCB, true, false);
		cbWarenAusgang = new MyE2_CheckBox(new MyE2_String("Warenausgang "),oStyleCB, false, false);

		agentWatchdogWEWA = new ActionAgent_RadioFunction_CheckBoxList(false);
		agentWatchdogWEWA.add_CheckBox(cbWarenEingang);
		agentWatchdogWEWA.add_CheckBox(cbWarenAusgang);

		cbWarenEingang.add_oActionAgent(oActionAgentRefreshKundensorte);
		cbWarenAusgang.add_oActionAgent(oActionAgentRefreshKundensorte);

		cbWarenEingang.add_oActionAgent(oActionWEWA);
		cbWarenAusgang.add_oActionAgent(oActionWEWA);

		colCB_WE.add(cbWarenEingang, E2_INSETS.I_2_2_2_2);
		colCB_WA.add(cbWarenAusgang, E2_INSETS.I_2_2_2_2);

		// Befund
		tfBefund = new UB_TextArea("BEFUND", true, "", 500, 2, 200);
		tfBefund.set_iMaxInputSize(200);

		// Auswahlbox Waage
		m_IdUser = bibALL.get_ID_USER();

		// Array mit den Waagedisplays
		m_listWaageDisplays = new ArrayList<WK_Row_Waagedisplay>();

		// objekt für die Waagen
		m_selWaageBediener = new MyE2_SelectField();
		m_selWaageBediener.add_oActionAgent(new actionSelectWaageBediener());
		
		
		// Objekte für die 1. Wägung
		tfW1Datum = new UB_TextField("W1DATUM", true, "", 80, 10);
		tfW1Zeit = new UB_TextField("W1ZEIT", true, "", 80, 8);
		tfW1Id = new UB_TextField("W1ID", true, "", 80, 10);
		tfW1Gewicht = new UB_TextField("W1GEWICHT", true, "", 80, 20);
		tfW1Gewicht.add_InputValidator(new VALIDATE_INPUT_Float_Nullable());

		tfW1Datum.set_bEnabled_For_Edit(false);
		tfW1Zeit.set_bEnabled_For_Edit(false);
		tfW1Id.set_bEnabled_For_Edit(false);
		tfW1Gewicht.set_bEnabled_For_Edit(false);
		tfW1Gewicht.setAlignment(m_alignRight);

		oButtonW1ReadData = new MyE2_Button(new MyE2_String("Speichern!"));
		oButtonW1ReadData.add_oActionAgent(new ActionSaveWaegungHand(1));

		cbW1Hand = new UB_CheckBox("W1HAND", new MyE2_String("Handeingabe"),null);
		cbW1Hand.add_oActionAgent(new actionCBWaegung1Hand());

		// Objekte für die 2. Wägung
		tfW2Datum = new UB_TextField("W2DATUM", true, "", 80, 10);
		tfW2Zeit = new UB_TextField("W2ZEIT", true, "", 80, 8);
		tfW2Id = new UB_TextField("W2ID", true, "", 80, 10);
		tfW2Gewicht = new UB_TextField("W2GEWICHT", true, "", 80, 20);
		tfW2Gewicht.add_InputValidator(new VALIDATE_INPUT_Float_Nullable());
		tfW2Datum.set_bEnabled_For_Edit(false);
		tfW2Zeit.set_bEnabled_For_Edit(false);
		tfW2Id.set_bEnabled_For_Edit(false);
		tfW2Gewicht.set_bEnabled_For_Edit(false);
		tfW2Gewicht.setAlignment(m_alignRight);

		oButtonW2ReadData = new MyE2_Button(new MyE2_String("Speichern!"));
		oButtonW2ReadData.add_oActionAgent(new ActionSaveWaegungHand(2));

		cbW2Hand = new UB_CheckBox("W2HAND", new MyE2_String("Handeingabe"), null);
		cbW2Hand.add_oActionAgent(new actionCBWaegung2Hand());

		tfFuhre = new UB_TextField("IDFUHRE", false, "", 80, 10);
		tfFuhre.set_bEnabled_For_Edit(false);
		tfFuhre.add_InputValidator(new VALIDATE_INPUT_Integer());

		tfFuhreOrt = new UB_TextField("IDFUHREORT", false, "", 80, 10);
		tfFuhreOrt.set_bEnabled_For_Edit(false);
		tfFuhreOrt.add_InputValidator(new VALIDATE_INPUT_Integer());

		oSearchFuhre = new WK_SearchFieldFuhre(tfFuhre, tfFuhreOrt, m_IdAdresseLager, new actionButtonFuhrenDatenLaden(),new actionButtonFuhrenDatenLaden());
		oSearchFuhre.get_oTextForAnzeige().setWidth(new Extent(400));
		oSearchFuhre.get_oTextForAnzeige().setFont(new E2_FontPlain());
		lblStatusFuhre = new MyE2_Label("");
		lblStatusFuhre.setForeground(Color.RED);

		// den Namen des Lagers
		lblLagerName = new MyE2_Label();
		lblLagerName.setStyle(MyE2_Label.STYLE_TITEL_NORMAL());

		//
		// Anpassungen
		//

		// Wiegekarten-Typ
		cbWiegekarte_Wiegeschein = new MyE2_CheckBox(new MyE2_String("Wiegeschein"), MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Strecke = new MyE2_CheckBox(new MyE2_String("Strecke"),MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Fremdwiegung = new MyE2_CheckBox(new MyE2_String("Fremdwiegung"), MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Wiegeschein_Lieferschein = new MyE2_CheckBox(new MyE2_String("Wiegeschein + Lieferschein"),	MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Retoure = new MyE2_CheckBox(new MyE2_String("Retoure"),MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Lager = new MyE2_CheckBox(	new MyE2_String("Lagerlieferung"),	MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Leerfuhre = new MyE2_CheckBox(	new MyE2_String("Leerfuhre"),	MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Dokumentarisch = new MyE2_CheckBox (new MyE2_String("Dokumentarische Verwiegung"),	MyE2_CheckBox.STYLE_NORMAL(), false, false);
		cbWiegekarte_Dokumentarisch.setToolTipText(new MyE2_String("Keine Prüfung auf Mindestlast! Nicht zur Verrechnung der Gewichte erlaubt.").CTrans());
		

		
		agentWatchdogWiegekartenTyp = new ActionAgent_RadioFunction_CheckBoxList(false);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Wiegeschein);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Strecke);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Fremdwiegung);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Wiegeschein_Lieferschein);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Lager);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Retoure);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Leerfuhre);
		agentWatchdogWiegekartenTyp.add_CheckBox(cbWiegekarte_Dokumentarisch);
		cbWiegekarte_Wiegeschein.setSelected(true);

		// Gesamtverwiegung
		cbGesamtverwiegung = new MyE2_CheckBox(new MyE2_String("Gesamtverwiegung"),MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS(), true, false);
		cbRadioaktivitaetGeprueft = new MyE2_CheckBox(new MyE2_String("Auf Radioaktivität geprüft"),MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS(), true, false);

		// Strecken-Abnehmer
		oSearchAdresseAbnehmerStrecke = new WK_UB_SearchFieldAdresse("ABNEHMER_STRECKE", true, 200, 100);
		oSearchAdresseAbnehmerStrecke.get_oTextForAnzeige().setWidth(new Extent(390));
		oSearchAdresseAbnehmerStrecke.get_oTextForAnzeige().setFont(new E2_FontPlain());

		// Bemerkungsfelder
		tfBemerkung1 = new UB_TextField("BEMERKUNG1", true, "", 500, 250);
		tfBemerkung2 = new UB_TextField("BEMERKUNG2", true, "", 500, 250);
		tfBemerkungIntern = new UB_TextField("BEMERKUNGINTERN", true, "", 500,	250);
		
		// 2018-05-15
		tfContainerNr 	= new UB_TextField("CONTAINER_NR",true,"",200,50);
		tfSiegelNr 		= new UB_TextField("SIEGEL_NR",true,"",200,50);		

		// Gewicht
		lblGewicht = new MyE2_LabelWithBorder(	MyE2_LabelWithBorder.STYLE_BORDER_DDARKCOLOR(),	MyE2_LabelWithBorder.CONTENT_LAYOUT_RIGHT);
		lblGewicht.setFont(new E2_FontBold(6));

		// Abzugsmengen
		lblGewichtAbzug = new MyE2_LabelWithBorder(	MyE2_LabelWithBorder.STYLE_BORDER_DDARKCOLOR(),	MyE2_LabelWithBorder.CONTENT_LAYOUT_RIGHT);
		lblGewichtAbzug.setFont(new E2_FontBold(6));

		lblGewichtNachAbzug = new MyE2_LabelWithBorder(	MyE2_LabelWithBorder.STYLE_BORDER_DDARKCOLOR(),	MyE2_LabelWithBorder.CONTENT_LAYOUT_RIGHT);
		lblGewichtNachAbzug.setFont(new E2_FontBold(6));
		tfGrundAbzug = new UB_TextField("GRUND_ABZUG", true, "", 400, 250);

		lblGewichtAbzugFuhre = new MyE2_LabelWithBorder(	MyE2_LabelWithBorder.STYLE_BORDER_DDARKCOLOR(),	MyE2_LabelWithBorder.CONTENT_LAYOUT_RIGHT);
		lblGewichtAbzugFuhre.setFont(new E2_FontBold(6));

		lblGewichtNachAbzugFuhre = new MyE2_LabelWithBorder(	MyE2_LabelWithBorder.STYLE_BORDER_DDARKCOLOR(),	MyE2_LabelWithBorder.CONTENT_LAYOUT_RIGHT);
		lblGewichtNachAbzugFuhre.setFont(new E2_FontBold(6));
		
		
		
		
		// ChildGrid für die Gebindeabzüge initialisieren
		Project_SQLFieldMAP oSqlMap = new Project_SQLFieldMAP("JT_WIEGEKARTE",	null, true);
		m_oAbzuegeGebinde = new WK_MASK_Daughter_Abzuege_Gebinde(oSqlMap, null);
		m_oAbzuegeGebinde.UB_PREPARE_4_NEW(false);
		
		// Child für die Mengenabzüge initialisieren
		Project_SQLFieldMAP oSqlMapAbzFuhre = new Project_SQLFieldMAP("JT_WIEGEKARTE", null, true);
		m_oAbzuegeMengen = new WK_MASK_Daughter_Abzuege_Mengen(oSqlMapAbzFuhre, null);
		m_oAbzuegeMengen.UB_PREPARE_4_NEW(false);
		
		
		// Child für die Container initialisieren
		Project_SQLFieldMAP oSqlMapContainer = new Project_SQLFieldMAP("JT_WIEGEKARTE", null, true);
		m_oContainer = new WK_MASK_Daughter_Container(oSqlMapContainer, null);
		m_oContainer.UB_PREPARE_4_NEW(false);
				
		

		// Speichernbutton bei den Abzügen
		m_btnSaveStamm2 = new MyE2_Button(new MyE2_String("Speichern").CTrans());
		m_btnSaveStamm2.add_oActionAgent(new actionSaveWiegekarte(false));
		m_btnSaveStamm2.set_bEnabled_For_Edit(false);

		// this.vUnboundFields.add(tfGewichtAbzug);
		this.vUnboundFields.add(tfW1Gewicht);
		this.vUnboundFields.add(tfW2Gewicht);
		this.vUnboundFields.add(tfAnzahlEtiketten);

		this.m_lblEingangsscheinNr = new MyE2_Label();
		this.m_lblIDWiegekarte = new MyE2_Label( MyE2_Label.STYLE_TITEL_BIG());
		this.m_lblStorno = new MyE2_Label();
		this.m_lblStorno.setBackground(Color.RED);

		this.m_lblInfo  = new  MyE2_Label();
		
		this.m_btnReadBarcode = new MyE2_Button("Strichcode lesen...",
				E2_ResourceIcon.get_RI("barcode.png"),
				E2_ResourceIcon.get_RI("barcode.png"));
		m_btnReadBarcode.add_oActionAgent(new actionDisplayBarcodePopup());

		this.m_btnRemoveLinkToFuhre = new MyE2_Button("Fuhre löschen",	E2_ResourceIcon.get_RI("delete_mini.png"),E2_ResourceIcon.get_RI("delete_mini__.png"));
		m_btnRemoveLinkToFuhre.add_oActionAgent(new actionDeleteFuhrenID());
		
		//---- Bemerkungsfelder für die Handverwiegung
		this.tfW1HandDesc = new MyE2_TextField_WithSelektorEASY(WK_LIST_CONST.KENNER_TEXT_HANDWIEGUNG,280);
		this.tfW2HandDesc = new MyE2_TextField_WithSelektorEASY(WK_LIST_CONST.KENNER_TEXT_HANDWIEGUNG,280);
		//----

		
		// ******************************
		// Layout
		// ******************************
		Insets oIn = new Insets(5, 1, 0, 1);
		Insets oIn_Korr = new Insets(4, 1, 0, 1);
		Insets oIn_4_10_2_0 = new Insets(4, 10, 2, 1);

		
		MyE2_Grid m_GridButtons = new MyE2_Grid(10,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		//2014-05-09:  eMail-Versende-Makros einblenden, wenn vorhanden
		if (S.isFull(this.get_MODUL_IDENTIFIER()))
		{
			MMC_OWN_RECLIST_MAIL_AUS_MASK  oMMR = new MMC_OWN_RECLIST_MAIL_AUS_MASK(this.get_MODUL_IDENTIFIER());
			
			if (oMMR.get_vKeyValues().size()>0) {
				RowLayoutData  oRL = new RowLayoutData();
				oRL.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
				m_popup_menuMailVersand = new MMC_StartMail_PopUP(oMMR, new MMC_ERSETZUNGS_HASH_WIEGEKARTE(m_oWKHandler));
			}
		}

		
		// 1. Zeile
		m_GridButtons.add(m_btnSaveStammAndExit);
		m_GridButtons.add(m_btnSaveStamm, E2_INSETS.I_10_0_0_0);
		m_GridButtons.add(m_btnCancel, E2_INSETS.I_10_0_0_0);
		m_GridButtons.add(m_btnCreateFolge, E2_INSETS.I_10_0_0_0);
		m_GridButtons.add(m_btnPrintWiegekarte, new Insets(100, 0, 0, 0));
		m_GridButtons.add(m_btnPrintEingangsscheinLieferschein, E2_INSETS.I_10_0_0_0);
		m_GridButtons.add(m_btnPrintWiegekarteEingangsschein, E2_INSETS.I_10_0_0_0);
		m_GridButtons.add(tfAnzahlEtiketten, E2_INSETS.I_10_0_0_0);
		m_GridButtons.add(m_btnPrintEtikett, E2_INSETS.I_2_0_0_0);
		m_GridButtons.add(m_btnPrintBuero, E2_INSETS.I_10_0_0_0);

		
		// 2. Zeile
		
		// wenn der Button auf der Seite anzgeigt werden soll..
		boolean bHofschein = bib_Settigs_Mandant.IS__Value("WAAGE_HOFSCHEIN_DRUCKEN_ANZEIGE_BUTTON", "N","N");
		if (bHofschein || m_popup_menuMailVersand != null) {
			
			// falls der mailversand definiert ist, dann ganz links in die Zeile
			if (m_popup_menuMailVersand != null) {
				m_GridButtons.add(m_popup_menuMailVersand);
			} else {
				m_GridButtons.add(new MyE2_Label(""));
			}
			
			m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_10_0_0_0);
			m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_10_0_0_0);
			m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_10_0_0_0);
			
			m_GridButtons.add(new MyE2_Label(""), new Insets(100, 0, 0, 0));
			m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_10_0_0_0);
			m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_10_0_0_0);
			m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_10_0_0_0);
			m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_2_0_0_0);
			// wenn der Hofschein gedruckt werden soll, dann anzeigen, sonst leer
			if (bHofschein){
				m_GridButtons.add(m_btnPrintHofschein, E2_INSETS.I_10_0_0_0);
			} else {
				m_GridButtons.add(new MyE2_Label(""), E2_INSETS.I_10_0_0_0);
			}
			
		}
		
		this.add(m_GridButtons);
		
		
		MyE2_Grid oGridBase = new MyE2_Grid(3,
				MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());

		// Zeile
		MyE2_Row oRowTempHeading = new MyE2_Row( MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowTempHeading.add(new MyE2_Label(new MyE2_String("Wiegekarte"), MyE2_Label.STYLE_TITEL_BIG()),  oIn_4_10_2_0);
		oRowTempHeading.add(m_lblIDWiegekarte,  oIn_4_10_2_0);
		oGridBase.add(oRowTempHeading, 3, oIn_Korr);
		

		// Zeile
		MyE2_Row oRowTempIDs = new MyE2_Row( MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowTempIDs.add(new MyE2_Label(new MyE2_String("EingangsscheinNr: "),MyE2_Label.STYLE_NORMAL_PLAIN()), E2_INSETS.I_2_1_2_1);
		oRowTempIDs.add(m_lblEingangsscheinNr, oIn_Korr);
		oRowTempIDs.add(m_lblStorno, E2_INSETS.I_10_1_2_1);
		oRowTempIDs.add(m_lblInfo, E2_INSETS.I_10_1_2_1);
		oGridBase.add(oRowTempIDs, 3, oIn_Korr);

		// Zeile
		oGridBase.add(new Separator(), 3, new Insets(0, 0, 0, 0));

		// Zeile
		MyE2_Row oRowTempFuhre = new MyE2_Row(
				MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowTempFuhre.add(
				new MyE2_Label(new MyE2_String("Fuhre"), MyE2_Label
						.STYLE_NORMAL_PLAIN()), oIn);
		oRowTempFuhre.add(tfFuhre, oIn);
		oRowTempFuhre.add(tfFuhreOrt, oIn);
		oRowTempFuhre.add(oSearchFuhre, oIn);
		oRowTempFuhre.add(m_btnReadBarcode, E2_INSETS.I_10_0_0_0);
		oRowTempFuhre.add(m_btnRemoveLinkToFuhre, E2_INSETS.I_10_0_0_0);
		oGridBase.add(oRowTempFuhre, 3, oIn_Korr);
		// Status der Fuhre eintragen
		oGridBase.add(lblStatusFuhre, 3, E2_INSETS.I_10_0_0_0);

		// Zeile
		oGridBase.add(new Separator(), 3, new Insets(0, 0, 0, 2));

		// Zeile
		MyE2_Grid_100_percent oGridWiegescheinTyp = new MyE2_Grid_100_percent(
				1, 0);
		MyE2_Row oRowTyp = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowTyp.add(cbWiegekarte_Wiegeschein, E2_INSETS.I_0_0_0_0);
		oRowTyp.add(cbWiegekarte_Strecke, E2_INSETS.I_10_0_0_0);
		oRowTyp.add(cbWiegekarte_Fremdwiegung, E2_INSETS.I_10_0_0_0);
		oRowTyp.add(cbWiegekarte_Wiegeschein_Lieferschein, E2_INSETS.I_10_0_0_0);

		oRowTyp.add(cbWiegekarte_Lager, E2_INSETS.I_10_0_0_0);
		oRowTyp.add(cbWiegekarte_Retoure, E2_INSETS.I_10_0_0_0);
		oRowTyp.add(cbWiegekarte_Leerfuhre, E2_INSETS.I_10_0_0_0);
		oRowTyp.add(cbWiegekarte_Dokumentarisch, E2_INSETS.I_10_0_0_0);
		

		oGridWiegescheinTyp.add(oRowTyp);

		oGridBase.add(oGridWiegescheinTyp, 3, E2_INSETS.I_0_0_0_0);

		// Zeile
		oGridBase.add(new Separator(), 3, new Insets(0, 0, 0, 2));

		MyE2_Grid oGridWiegekarte = new MyE2_Grid(5,
				MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(), 1, oIn);
		MyE2_Row oRowWEWA = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowWEWA.add(colCB_WE);
		oRowWEWA.add(colCB_WA, E2_INSETS.I_20_0_0_0);
		oRowWEWA.add(cbErlaubeMehrfachwiegungen,E2_INSETS.I_20_0_0_0);
		oGridWiegekarte.add(oRowWEWA, 4, oIn);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Kennzeichen"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridWiegekarte.add(tfKennzeichen, 4, oIn);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Adresse"),	MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridWiegekarte.add(oSearchAdresse, 4, oIn_Korr);

		// Zeile
		oGridWiegekarte.add(cbAdresseNeu, 1, oIn);
		oGridWiegekarte.add(tfAdresse, 4, oIn);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Sorte"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridWiegekarte.add(cmbSorteKunde, 3, oIn);
		oGridWiegekarte.add(new MyE2_Label(""), 1, oIn);

		// Zeile Sorteninfo
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridWiegekarte.add(tfSortenInfo, 3, oIn);
		oGridWiegekarte.add(new MyE2_Label(""), 1, oIn);
		tfSortenInfo.setBackground(new E2_ColorBase());
		
		// Zeile
		// -sorte Hand
		oGridWiegekarte.add(cbSorteHand, 1, oIn);
		oGridWiegekarte.add(oSearchSorte, 4, oIn_Korr);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Spedition"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridWiegekarte.add(oSearchAdresseSpedition, 4, oIn_Korr);

		// Zeile
		oGridWiegekarte.add(cbAdresseSpeditonNeu, 1, oIn);
		oGridWiegekarte.add(tfAdresseSpedion, 4, oIn);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Abnehmer Strecke"),	MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridWiegekarte.add(oSearchAdresseAbnehmerStrecke, 4, oIn_Korr);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Bemerkung Wiegekarte"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1,oIn);
		oGridWiegekarte.add(tfBemerkung1, 4, oIn);

		// 2018-05-15
		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Container/Siegel Nr."), MyE2_Label.STYLE_NORMAL_PLAIN()), 1,oIn);
		MyE2_Row oRowCont = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowCont.add(tfContainerNr);
		oRowCont.add(tfSiegelNr,E2_INSETS.I_20_0_0_0);
		oGridWiegekarte.add(oRowCont, 4, oIn);
		
		// Zeile
		// oGridWiegekarte.add(new MyE2_Label(new
		// MyE2_String("Bemerkung 2"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		// oGridWiegekarte.add(tfBemerkung2,4,oIn);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Bemerkung Eingangsschein"), MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridWiegekarte.add(tfBemerkungIntern, 4, oIn);

		// Zeile
		oGridWiegekarte.add(new MyE2_Label(new MyE2_String("Befund"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridWiegekarte.add(tfBefund, 4, oIn);

		// Zeile
		oGridBase.add(oGridWiegekarte, 1, E2_INSETS.I_0_0_0_0);

		// senkrechter Trennstrich
		Strut strut = new Strut();
		strut.setBackground(Color.DARKGRAY);
		strut.setHeight(new Extent(100, Extent.PERCENT));
		strut.setWidth(new Extent(1));
		oGridBase.add(strut, 1, E2_INSETS.I_5_0_0_0);

		// Das Grid für die Zusatzfelder
		MyE2_Grid oGridMengen = new MyE2_Grid(3, 0);

		// Zeile
		oGridMengen.add(cbGesamtverwiegung, 3, E2_INSETS.I_0_0_0_10);
		oGridMengen.add(cbRadioaktivitaetGeprueft, 3, E2_INSETS.I_0_0_0_10);

		// Zeile
		MyE2_TabbedPane tabbedPaneAbzuege = new MyE2_TabbedPane(null);
		tabbedPaneAbzuege.setWidth(new Extent(600,Extent.PX));
		tabbedPaneAbzuege.setHeight(new Extent(270,Extent.PX));

		MyE2_Grid 	gridTabAbzug1  = new MyE2_Grid(1);
		MyE2_Grid   gridTabAbzug2 = new MyE2_Grid(1);
		
		gridTabAbzug1.add(new MyE2_Label(new MyE2_String("Abzüge Transportgebinde"), MyE2_Label.STYLE_TITEL_BIG()));
		gridTabAbzug1.add(m_oAbzuegeGebinde);
		
		gridTabAbzug2.add(new MyE2_Label(new MyE2_String("Abzüge Mengen"), MyE2_Label.STYLE_TITEL_BIG()));
		gridTabAbzug2.add(m_oAbzuegeMengen);

		tabbedPaneAbzuege.add_Tabb(new MyString("Abzüge Gebinde"), gridTabAbzug1);
		tabbedPaneAbzuege.add_Tabb(new MyString("Abzüge Fuhre"), gridTabAbzug2);

		// Container-Reiter nur zeigen, wenn auch im Mandanten-Schalter eingeschaltet
		if (m_bShowContainerverwaltung){
			MyE2_Grid   gridTabAbzug3 = new MyE2_Grid(1);
			gridTabAbzug3.add(new MyE2_Label(new MyE2_String("Container"), MyE2_Label.STYLE_TITEL_BIG()));
			gridTabAbzug3.add(m_oContainer);
			tabbedPaneAbzuege.add_Tabb(new MyString("Container"), gridTabAbzug3);
		}
		
		
		
		// Zeile
		oGridMengen.add(tabbedPaneAbzuege,3,oIn);
		
		
		// Zeile
		MyE2_Grid rowBtn2 = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		rowBtn2.add(m_btnSaveStamm2, MyE2_Grid.LAYOUT_RIGHT_CENTER(null));
		oGridMengen.add(rowBtn2, 3, oIn);

		// Zeile
		oGridBase.add(oGridMengen, 1, 1, E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);

		// Zeile
		oGridBase.add(new Separator(), 3, new Insets(0, 5, 0, 0));

		// Zeile
		//
		// WAAGE-Auswahl
		//
		MyE2_Grid rowWaagenHeading = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		rowWaagenHeading.setColumnWidth(0, new Extent(130, Extent.PX));
		rowWaagenHeading.setColumnWidth(1, new Extent(65, Extent.PX));
		rowWaagenHeading.setColumnWidth(2, new Extent(200, Extent.PX));
		
		rowWaagenHeading.add(new MyE2_Label(new MyE2_String("Waagen"), MyE2_Label.STYLE_TITEL_BIG()), oIn);
		rowWaagenHeading.add(new MyE2_Label(new MyE2_String("Bediener:")), oIn);
		rowWaagenHeading.add(m_selWaageBediener,oIn);
		
		oGridBase.add(rowWaagenHeading,3,oIn);
		
		// anlegen des Grids für die Waagen, Die Inhalte werden dynamisch je nach Standort eingebunden
		m_oGridWaagen = new MyE2_Grid(5, 0);

		oGridBase.add(m_oGridWaagen, 3, oIn);

		
		
		// Zeile
		oGridBase.add(new Separator(), 3, new Insets(0, 5, 0, 0));

		//
		// Grid für Wägungen
		//
		MyE2_Grid oGridWaegungen = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());

		//
		// Wägung 1
		//
		MyE2_Grid oGridW1 = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());

		// Zeile 1
		oGridW1.add(new MyE2_Label(new MyE2_String("1. Wägung"), MyE2_Label.STYLE_TITEL_BIG()), 1, new Insets(0, 5, 0, 5));
		oGridW1.add(cbW1Hand, 1, oIn);
		oGridW1.add(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_PLAIN()), 2, oIn);

		// zeile
		oGridW1.add(new MyE2_Label(new MyE2_String("Datum"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridW1.add(new MyE2_Label(new MyE2_String("Zeit"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridW1.add(new MyE2_Label(new MyE2_String("Gewicht"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, 1, oIn, m_alignRight);
		oGridW1.add(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);

		// Zeile
		oGridW1.add(tfW1Datum, 1, oIn);
		oGridW1.add(tfW1Zeit, 1, oIn);
		oGridW1.add(tfW1Gewicht, 1, oIn);
		oGridW1.add(oButtonW1ReadData, 1, oIn);

		// Zeile
		oGridW1.add(tfW1HandDesc,4,oIn);
		
		oGridWaegungen.add(oGridW1, 1, oIn);

		//
		// Wägung 2
		//
		MyE2_Grid oGridW2 = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());

		// zeile
		oGridW2.add(new MyE2_Label(new MyE2_String("2. Wägung"), MyE2_Label.STYLE_TITEL_BIG()), 1, new Insets(0, 5, 0, 5));
		oGridW2.add(cbW2Hand, 1, oIn);
		oGridW2.add(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_PLAIN()), 2, oIn);

		// zeile
		oGridW2.add(new MyE2_Label(new MyE2_String("Datum"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridW2.add(new MyE2_Label(new MyE2_String("Zeit"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);
		oGridW2.add(new MyE2_Label(new MyE2_String("Gewicht"), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, 1, oIn, m_alignRight);
		oGridW2.add(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_PLAIN()), 1, oIn);

		// Zeile
		oGridW2.add(tfW2Datum, 1, oIn);
		oGridW2.add(tfW2Zeit, 1, oIn);
		oGridW2.add(tfW2Gewicht, 1, oIn);
		oGridW2.add(oButtonW2ReadData, 1, oIn);
		
		// Zeile
		oGridW2.add(tfW2HandDesc,4,oIn);
		
		oGridWaegungen.add(oGridW2, 1, E2_INSETS.I_20_0_0_0);

		//
		// Grid für Abzüge
		//
		MyE2_Grid oGridAbzug = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		oGridAbzug.setColumnWidth(0, new Extent(100));
		oGridAbzug.setColumnWidth(1, new Extent(100));
		oGridAbzug.setColumnWidth(2, new Extent(100));
		oGridAbzug.setColumnWidth(3, new Extent(100));
		oGridAbzug.setColumnWidth(4, new Extent(100));
		// Zeile
		oGridAbzug.add(new MyE2_Label(new MyE2_String("Nettogewichte / Abzüge"),MyE2_Label.STYLE_TITEL_BIG()), 5, new Insets(0, 5, 0, 5));

		// Zeile
		oGridAbzug.add(new MyE2_Label(new MyE2_String("Gewicht"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, 1, E2_INSETS.I_5_1_5_0,m_alignRight);
		oGridAbzug.add(new MyE2_Label(new MyE2_String("Verpackung"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, 1, E2_INSETS.I_5_1_5_0,m_alignRight);
		oGridAbzug.add(new MyE2_Label(new MyE2_String("Netto"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, 1, E2_INSETS.I_5_1_5_0,m_alignRight);
		oGridAbzug.add(new MyE2_Label(new MyE2_String("Abzüge"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, 1, E2_INSETS.I_5_1_5_0,m_alignRight);
		oGridAbzug.add(new MyE2_Label(new MyE2_String("Netto"),MyE2_Label.STYLE_NORMAL_PLAIN()), 1, 1, E2_INSETS.I_5_1_5_0,m_alignRight);
		
		// Zeile
		oGridAbzug.add(lblGewicht, 1, 1, oIn, m_alignRight);
		oGridAbzug.add(lblGewichtAbzug, 1, 1, oIn, m_alignRight);
		oGridAbzug.add(lblGewichtNachAbzug, 1, 1, oIn, m_alignRight);
		oGridAbzug.add(lblGewichtAbzugFuhre, 1, 1, oIn, m_alignRight);
		oGridAbzug.add(lblGewichtNachAbzugFuhre, 1, 1, oIn, m_alignRight);

		oGridWaegungen.add(oGridAbzug, 1, E2_INSETS.I_20_0_0_0);

		oGridBase.add(oGridWaegungen, 3, oIn);

		// Zeile
		oGridBase.add(new Separator(), 3, new Insets(0, 5, 0, 0));

		// oGridBase.add(oGridAbzug,3,E2_INSETS.I_0_0_0_0);

		//
		// das Haupt-Grid dem Fenster zuordnen
		//
		this.add(oGridBase, E2_INSETS.I_2_2_2_2);

		
		// Eingaberegeln setzen
		initRuleSetter();
		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this) {
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
				if (m_bIsDirty) {
					if (m_navigationList != null) {
						m_navigationList.RefreshList();
					}
				}
				m_bIsDirty = false;
			}
		});

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500),
				new MyE2_String("Erfassung einer Wägung"));
	}

	/**
	 * initialisieren der Daten nach dem Laden
	 * 
	 * @throws myException
	 */
	private void initData() throws myException {

		// task evtl stoppen:
		if (m_ServerPushTask != null) {
			m_ServerPushTask.stop();
		}
		

		m_IDFolgewiegekarte = null;

		// Handler initialisieren
		m_oWKHandler.InitWK(m_IDWiegekarte);
		
		
		
		WK_Wiegekarte_Buchungssatz oWiegekarteSatz = null;
		if (m_bOpenAsCopy){

			// zuerst den Wiegekartensatz holen als Kopie
			oWiegekarteSatz = m_oWKHandler.get_BuchungWiegekarteAsCopyAndClearOriginal(m_bCreateFolgewiegekarte);
			m_IDFolgewiegekarte = oWiegekarteSatz.getiD_WIEGEKARTE_PARENT();
			
			// dann den Handler wieder initialisieren
			m_oWKHandler.InitWK(null);
		} else {
			oWiegekarteSatz = m_oWKHandler.get_BuchungWiegekarte();
		}

		// Daten der Wiegekarte lesen
		this.initWiegekarteData(oWiegekarteSatz);

		// nach dem initialisieren der WK muss der Zustand auf neu gesetzt werden
		if (m_bOpenAsCopy){
			m_enumZustandMaske = ENUM_ZustandMaske.NEU;
			m_bCreateFolgewiegekarte = false;
			m_bOpenAsCopy = false;
		}

		// Daten der Wägungen lesen
		this.initWaegungData();
		
		// WE/WA-Status festlegen
		setStatusWE_WA();

		// die Waagen anzeigen
		initWaageSettingsData();
		
		
		// Rulesetters
		m_explicitRuleSetter.ValidateRules_OnInit();
		
	}

	/**
	 * Verarbeiten der Wiegekarten-Daten, die im WiegekartenHandler ermittelt
	 * wurden
	 * @throws myException 
	 */
	private void initWiegekarteData(WK_Wiegekarte_Buchungssatz oWiegekarteSatz) throws myException {

		// lesen der Wiegekarte
		m_enumZustandMaske = ENUM_ZustandMaske.NEU;

		// alle Felder enablen

		cbW2Hand.setVisible(true);
		
		cbWarenEingang.set_bEnabled_For_Edit(true);
		cbWarenAusgang.set_bEnabled_For_Edit(true);

		oSearchFuhre.set_bEnabled_For_Edit(true);
		m_btnReadBarcode.set_bEnabled_For_Edit(true);

		tfKennzeichen.set_bEnabled_For_Edit(true);
		
		
		cbAdresseNeu.set_bEnabled_For_Edit(true);
		tfAdresse.set_bEnabled_For_Edit(false);
		oSearchAdresse.set_bEnabled_For_Edit(true);
		
		
		cbAdresseSpeditonNeu.set_bEnabled_For_Edit(true);
		tfAdresseSpedion.set_bEnabled_For_Edit(false);
		oSearchAdresseSpedition.set_bEnabled_For_Edit(true);
		
		oSearchAdresseAbnehmerStrecke.set_bEnabled_For_Edit(true);

		cbGesamtverwiegung.set_bEnabled_For_Edit(true);
		cbRadioaktivitaetGeprueft.set_bEnabled_For_Edit(true);

		cbSorteHand.set_bEnabled_For_Edit(true);
		cmbSorteKunde.set_bEnabled_For_Edit(true);
		oSearchSorte.set_bEnabled_For_Edit(true);
		
		// Fuhre aktualisierbar machen
		m_btnRemoveLinkToFuhre.set_bEnabled_For_Edit(true);
		
		// mehrfachwiegungen erlauben
		cbErlaubeMehrfachwiegungen.set_bEnabled_For_Edit(true);
		cbErlaubeMehrfachwiegungen.setSelected(false);

		// Druck-Knöpfe aktualisieren
		m_btnPrintWiegekarte.setIDWiegekarte(null);
		m_btnPrintWiegekarteEingangsschein.setIDWiegekarte(null);
		m_btnPrintEingangsscheinLieferschein.setIDWiegekarte(null);
		m_btnPrintBuero.setIDWiegekarte(null);
		m_btnPrintEtikett.setIDWiegekarte(null);
		m_btnPrintHofschein.setIDWiegekarte(null);
		

		m_btnPrintBuero.set_bEnabled_For_Edit(false);
		m_btnPrintEtikett.set_bEnabled_For_Edit(false);
		tfAnzahlEtiketten.set_bEnabled_For_Edit(false);
		m_btnPrintWiegekarte.set_bEnabled_For_Edit(false);
		m_btnPrintWiegekarteEingangsschein.set_bEnabled_For_Edit(false);
		m_btnPrintEingangsscheinLieferschein.set_bEnabled_For_Edit(false);
		m_btnPrintHofschein.set_bEnabled_For_Edit(false);
		
		tfBemerkung1.set_bEnabled_For_Edit(true);
		tfBemerkung2.set_bEnabled_For_Edit(true);
		tfBefund.set_bEnabled_For_Edit(true);
		tfBemerkungIntern.set_bEnabled_For_Edit(true);
		
		//2015-05-15
		tfContainerNr.set_bEnabled_For_Edit(true);
		tfSiegelNr.set_bEnabled_For_Edit(true);
		//
		
		// wenn schon die 2. Wägung durchgeführt wurde, kann auch eine
		// Folgewägung durchgeführt werden
		m_btnCreateFolge.set_bEnabled_For_Edit(true);

		// alle wiegekartentypen enablen
		agentWatchdogWiegekartenTyp.set_AllEnabled();

		
		
		// INHALTE DER FELDER INITIALISIEREN
		// löschen aller Datenfelder
		tfKennzeichen.get_oTextField().setText("");

		oSearchAdresse.get_oTextFieldForSearchInput().setText("");
		oSearchAdresse.get_oTextForAnzeige().setText("");

		oSearchSorte.get_oTextFieldForSearchInput().setText("");
		oSearchSorte.get_oTextForAnzeige().setText("");

		oSearchFuhre.get_oTextFieldForSearchInput().setText("");
		oSearchFuhre.get_oTextForAnzeige().setText("");

		oSearchAdresseAbnehmerStrecke.get_oTextFieldForSearchInput().setText("");
		oSearchAdresseAbnehmerStrecke.get_oTextForAnzeige().setText("");

		oSearchAdresseSpedition.get_oTextFieldForSearchInput().setText("");
		oSearchAdresseSpedition.get_oTextForAnzeige().setText("");

		// cbWarenEingang.setSelected(false) ;
		tfBefund.setText("");
		tfAdresse.setText("");
		tfFuhre.setText("");
		tfFuhreOrt.setText("");

		tfBemerkung1.setText("");
		tfBemerkung2.setText("");
		tfBemerkungIntern.setText("");
		
		tfContainerNr.setText("");
		tfSiegelNr.setText("");

		lblGewichtAbzug.set_Text("-");
		tfGrundAbzug.setText("");
		lblGewichtNachAbzug.set_Text("-");
		lblGewicht.set_Text("-");
		lblGewichtAbzugFuhre.set_Text("-");
		lblGewichtNachAbzugFuhre.set_Text("-");

		cbGesamtverwiegung.setSelected(false);
		cbRadioaktivitaetGeprueft.setSelected(true);

		m_lblEingangsscheinNr.setText("-");
		m_lblIDWiegekarte.setText("-");
		m_lblStorno.setText("");
		m_lblInfo.setText("");
		

		cbWarenEingang.setSelected(false);
		cbWarenAusgang.setSelected(false);

		m_FuhreIstGesetzt = false;
		m_FuhreIstZugeordnet = false;
		m_FuhrenzuordnungIstOK = false;
		
		lblStatusFuhre.setText("");
		lblStatusFuhre.setVisible(false);
		
		setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,	cbWiegekarte_Wiegeschein);

		lblLagerName.setText(m_LagerName);

		String sInfo = "";
		
		try {

			if (oWiegekarteSatz != null) {
				if (m_IdStandort == null){
					m_IdStandort = oWiegekarteSatz.getID_WAAGE_STANDORT();
				}

				
				if (m_bCreateFolgewiegekarte ){
					sInfo += "Erstellung einer Folge- oder Einzelverwiegung";
				} else if (m_bOpenAsCopy){
					sInfo += "Anlegen einer Wiegekarten-Kopie";
				} else if (oWiegekarteSatz.getERZEUGT_AM() != null){
					sInfo += "Erstellt am: " + myDateHelper.ChangeDBFormatStringToNormalString(oWiegekarteSatz.getERZEUGT_AM()) + ". ";
					sInfo += (oWiegekarteSatz.getDRUCKZAEHLER() != null && oWiegekarteSatz.getDRUCKZAEHLER().compareTo(BigDecimal.ZERO)> 0 ? "WK wurde gedruckt. " : "");
				}
				
				// Wenn noch keine wiegekarten-ID vorhanden ist, dann ist es
				// eine kopie durch folgewiegung, und demnach noch im
				// zustand "NEU"
				m_enumZustandMaske = ENUM_ZustandMaske.STAMMDATEN;

				m_lblEingangsscheinNr.setText(oWiegekarteSatz.getEINGANGSSCHEIN_NR() != null ? oWiegekarteSatz.getEINGANGSSCHEIN_NR() : "-");
				m_lblIDWiegekarte.setText(oWiegekarteSatz.getLFD_NR() != null ? oWiegekarteSatz.getLFD_NR() : "-");
				String sStorno = bibALL.null2leer(oWiegekarteSatz.getSTORNO());
				m_lblStorno.setText(sStorno.equals("Y") ? "STORNO" : "");

				// das Kennzeichen war wohl schon vorher geprüft
				// m_KennzeichenOK = true;
				// m_KennzeichenGeprüft = oWiegekarteSatz.getKENNZEICHEN();

				if (oWiegekarteSatz.getID_VPOS_TPA_FUHRE() != null) {
					String sFuhre = oWiegekarteSatz.getID_VPOS_TPA_FUHRE()+ "-"+ bibALL.null2leer(oWiegekarteSatz.getID_VPOS_TPA_FUHRE_ORT());
					oSearchFuhre.get_oTextFieldForSearchInput().setText(sFuhre);
					oSearchFuhre.FillLabelWithDBQuery(sFuhre);
				}

				tfFuhre.setText(oWiegekarteSatz.getID_VPOS_TPA_FUHRE());
				tfFuhreOrt.setText(oWiegekarteSatz.getID_VPOS_TPA_FUHRE_ORT());

				//
				// Fuhrenstatus
				//
				WK_RECORD_FuhrenInfo oRecFI = null;
				m_FuhreIstGesetzt = false;
				String sFuhrenstatus = "";
				if (!bibALL.isEmpty(oWiegekarteSatz.getID_VPOS_TPA_FUHRE())) {
					m_FuhreIstGesetzt = true;

				}
				
				m_FuhreIstZugeordnet = false;
				if (!bibALL.isEmpty(oWiegekarteSatz.getID_VPOS_TPA_FUHRE_AUS_FUHRE())) {
					m_FuhreIstZugeordnet = true;
				}

				// prüfen, ob die zuordnung überinstimmt
				m_FuhrenzuordnungIstOK  = false;
				if (m_FuhreIstGesetzt) {
					String f1 = bibALL.null2leer(oWiegekarteSatz.getID_VPOS_TPA_FUHRE()) + bibALL.null2leer(oWiegekarteSatz.getID_VPOS_TPA_FUHRE_ORT());
					String f2 = bibALL.null2leer(oWiegekarteSatz.getID_VPOS_TPA_FUHRE_AUS_FUHRE())+ bibALL.null2leer(oWiegekarteSatz.getID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE());
					m_FuhrenzuordnungIstOK = f1.equals(f2);
					
					if (!m_FuhrenzuordnungIstOK && !f2.equals("")) {

						sFuhrenstatus += "Die Fuhren-IDs stimmen nicht überein.";

					} else {
						
						if (m_FuhrenzuordnungIstOK){
							sInfo += "Die Wiegekarte ist in der Fuhre gesetzt. ";
						} 
						
						// wenn nicht, dann Anzeigen:
						oRecFI = ladeFuhrenDaten();
						if (bibALL.null2leer(oRecFI.getID_ARTIKEL_BEZ()).equals(bibALL.null2leer(oWiegekarteSatz.getID_ARTIKEL_BEZ())) == false) {
							sFuhrenstatus += "Die Sorten in der WK und der Fuhre stimmen nicht überein. ";
						}

						// wenn es ein Lieferant ist, dann muss in der Fuhre der
						// Abnehmer das Lager sein
						if (oRecFI.getIST_LIEFERANT().equalsIgnoreCase("Y")) {
							if (bibALL.null2leer(
									oWiegekarteSatz.getID_ADRESSE_LIEFERANT()).equals(bibALL.null2leer(oRecFI.getID_ADRESSE_START())) == false) {
								sFuhrenstatus += "Der Lieferant stimmt nicht überein. ";

							}
						} else {
							if (bibALL.null2leer(
									oWiegekarteSatz.getID_ADRESSE_LIEFERANT()).equals(bibALL.null2leer(oRecFI.getID_ADRESSE_ZIEL())) == false) {
								sFuhrenstatus += "Der Kunde stimmt nicht überein. ";

							}
						}

						if (bibALL.null2leer(oRecFI.getID_ADRESSE_SPEDITION()).equals(bibALL.null2leer(oWiegekarteSatz.getID_ADRESSE_SPEDITION())) == false) {
							sFuhrenstatus += "Die Spedition stimmt nicht überein. ";
						}
					}

					if (sFuhrenstatus.length() >= 1) {
						lblStatusFuhre.setVisible(true);
						lblStatusFuhre.setText(sFuhrenstatus);
					} else {
						lblStatusFuhre.setText("");
						lblStatusFuhre.setVisible(false);
					}

				}

				//
				// Wiegschein-Typ
				String sTyp = oWiegekarteSatz.getTYP_WIEGEKARTE();
				if (sTyp == null || sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_WIEGESCHEIN)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Wiegeschein);
				} else if (sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_STRECKE)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Strecke);
				} else if (sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_FREMDWIEGUNG)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Fremdwiegung);
				} else if (sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_WIEGESCHEIN_LIEFERSCHEIN)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Wiegeschein_Lieferschein);
				} else if (sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_LAGER)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Lager);
				} else if (sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_RETOURE)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Retoure);
				} else if (sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_LEERFUHRE)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Leerfuhre);
				} else if (sTyp.equals(WK_LIST_CONST.TYP_WIEGEKARTE_DOKUMENTARISCH)) {
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Dokumentarisch);
					agentWatchdogWiegekartenTyp.set_AllDisabled();
				} 
				
				//
				// Ist Lieferant (EK/VK)
				boolean bLief = oWiegekarteSatz.getIST_LIEFERANT() == null ? false : oWiegekarteSatz.getIST_LIEFERANT().equalsIgnoreCase("Y");
				if (!bLief) {
					setCheckboxOfWatchdogAgent(agentWatchdogWEWA,cbWarenAusgang);
				} else {
					setCheckboxOfWatchdogAgent(agentWatchdogWEWA,cbWarenEingang);
				}

				// Kennzeichen lesen
				tfKennzeichen.get_oTextField().setText(oWiegekarteSatz.getKENNZEICHEN());

				// Kunden-Adresse
				if (oWiegekarteSatz.getADRESSE_LIEFERANT() != null) {
					tfAdresse.setText(oWiegekarteSatz.getADRESSE_LIEFERANT());
					tfAdresse.set_bEnabled_For_Edit(true);
					oSearchAdresse.set_bEnabled_For_Edit(false);
					cbAdresseNeu.setSelected(true);
				} else {
					cbAdresseNeu.setSelected(false);
					tfAdresse.setText(null);

					oSearchAdresse.get_oTextFieldForSearchInput().setText(oWiegekarteSatz.getID_ADRESSE_LIEFERANT());
					try {
						oSearchAdresse.FillLabelWithDBQuery(oWiegekarteSatz.getID_ADRESSE_LIEFERANT());
					} catch (Exception e) {
						oSearchAdresse.get_oTextFieldForSearchInput().setText(null);
					}
				}

				// Speditionsadresse
				oSearchAdresseSpedition.get_oTextFieldForSearchInput().setText(oWiegekarteSatz.getID_ADRESSE_SPEDITION());
				try {
					oSearchAdresseSpedition.FillLabelWithDBQuery(oWiegekarteSatz.getID_ADRESSE_SPEDITION());
				} catch (Exception e) {
					oSearchAdresseSpedition.get_oTextFieldForSearchInput().setText(null);
				}
				
				if (oWiegekarteSatz.getADRESSE_SPEDITION() != null) {
					tfAdresseSpedion.setText(oWiegekarteSatz.getADRESSE_SPEDITION());
					tfAdresseSpedion.set_bEnabled_For_Edit(true);
					cbAdresseSpeditonNeu.setSelected(true);
				} else 	{
					cbAdresseSpeditonNeu.setSelected(false);
					tfAdresseSpedion.setText(null);
				}
				

				// Abnehmer Strecke
				if (oWiegekarteSatz.getID_ADRESSE_ABN_STRECKE() != null) {
					oSearchAdresseAbnehmerStrecke.set_bEnabled_For_Edit(true);
					oSearchAdresseAbnehmerStrecke.get_oTextFieldForSearchInput().setText(oWiegekarteSatz.getID_ADRESSE_ABN_STRECKE());
					try {
						oSearchAdresseAbnehmerStrecke.FillLabelWithDBQuery(oWiegekarteSatz.getID_ADRESSE_ABN_STRECKE());
					} catch (Exception e) {
						oSearchAdresseAbnehmerStrecke.get_oTextFieldForSearchInput().setText(null);
					}
				}

				// Befund
				tfBefund.setText(oWiegekarteSatz.getBEFUND());

				// Bemerkungen
				tfBemerkung1.setText(oWiegekarteSatz.getBEMERKUNG1());
				tfBemerkung2.setText(oWiegekarteSatz.getBEMERKUNG2());
				tfBemerkungIntern.setText(oWiegekarteSatz.getBEMERKUNG_INTERN());

				// 2018-05-15
				tfContainerNr.setText(oWiegekarteSatz.getCONTAINER_NR());
				tfSiegelNr.setText(oWiegekarteSatz.getSIEGEL_NR());
				
				
				// Gesamtverwiegeung
				cbGesamtverwiegung.setSelected(bibALL.null2leer(oWiegekarteSatz.getIST_GESAMTVERWIEGUNG()).equals("Y"));

				// Radioktivität
				cbRadioaktivitaetGeprueft.setSelected(bibALL.null2leer(oWiegekarteSatz.getIST_RADIOAKTIVITAETGEPRUEFT()).equals("Y"));

				
				// Abzüge der Gebinde
				try {
					m_oAbzuegeGebinde.set_UB_MOTHER_ID(oWiegekarteSatz.getID_WIEGEKARTE());
					m_oAbzuegeGebinde.UB_PopulateDaughterWithMother_4_EDIT();
				} catch (Exception e) {
					// die Mutterid war noch nicht gesetzt;
				}
				
				try {
					m_oAbzuegeMengen.set_UB_MOTHER_ID(oWiegekarteSatz.getID_WIEGEKARTE());
					m_oAbzuegeMengen.UB_PopulateDaughterWithMother_4_EDIT();
				} catch (Exception e) {
					// die Mutterid war noch nicht gesetzt;
				}
				
				
				//
				// Containerverwaltung
				//
				if (m_bShowContainerverwaltung){
					try {
						m_oContainer.set_UB_MOTHER_ID(oWiegekarteSatz.getID_WIEGEKARTE());
						m_oContainer.UB_PopulateDaughterWithMother_4_EDIT();
					} catch (Exception e) {
						// 
					}
				}
				
				
				
				m_btnSaveStamm2.set_bEnabled_For_Edit(true);

				// Gewichtsdaten
				String sTemp = null;
				sTemp = bibALL.convertBigDecimalToString(oWiegekarteSatz.getNETTOGEWICHT(), 3);
				lblGewicht.set_Text(sTemp != null ? sTemp : "-");

				sTemp = bibALL.convertBigDecimalToString(oWiegekarteSatz.getGEWICHT_ABZUG(), 3);
				lblGewichtAbzug.set_Text(sTemp != null ? sTemp : "-");

				tfGrundAbzug.setText(oWiegekarteSatz.getGRUND_ABZUG());

				sTemp = bibALL.convertBigDecimalToString(oWiegekarteSatz.getGEWICHT_NACH_ABZUG(), 3);
				lblGewichtNachAbzug.set_Text(sTemp != null ? sTemp : "-");

				sTemp = bibALL.convertBigDecimalToString(oWiegekarteSatz.getGEWICHT_ABZUG_FUHRE(), 3);
				lblGewichtAbzugFuhre.set_Text(sTemp != null ? sTemp : "-");
				
				sTemp = bibALL.convertBigDecimalToString(oWiegekarteSatz.getGEWICHT_NACH_ABZUG_FUHRE(), 3);
				lblGewichtNachAbzugFuhre.set_Text(sTemp != null ? sTemp : "-");
				
				// die Kundenspezifischen Sorten laden
				refreshSorteKunden();

				// Sorte festlegen
				setSorteInMaske(oWiegekarteSatz.getID_ARTIKEL_BEZ() , oWiegekarteSatz.getID_ADRESSE_LIEFERANT(),bLief);

				// info-Feld drucken
				m_lblInfo.setText(sInfo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Setzen einer Checkbox einer 1 aus N-Checkbox-Gruppe
	 * 
	 * @param watchdog
	 * @param checkbox
	 */
	private void setCheckboxOfWatchdogAgent(ActionAgent_RadioFunction_CheckBoxList watchdog,MyE2_CheckBox checkbox) {
		watchdog.executeAgentCodePassiv(new ExecINFO(new MyActionEvent(	new ActionEvent(checkbox, null)), true));
	}

	
	
	/**
	 * nimmt die Sortenid und setzt die Sortenfelder in der Maske korrekt
	 * 
	 * @param idSorteBez
	 * @throws myException
	 */
	private void setSorteInMaske(String idSorteBez, String idAdresse, boolean bLief ) throws myException {
		// suchen, ob die Sorte in den Kundensorten drin steht
		if (idSorteBez == null) {
			cbSorteHand.setSelected(false);
			setStatusSorteneingabe();
			return;
		}
		

		if (cmbSorteKunde.get_DataToView().get_vDataValues().contains(MyNumberFormater.formatDez(idSorteBez, true))) {
			// Sorte Setzen
			cmbSorteKunde.set_ActiveValue_OR_FirstValue(MyNumberFormater.formatDez(idSorteBez, true));
		
			
			// Handauswahl sorte
			cbSorteHand.setSelected(false);
		} else {

			// Handauswahl sorte
			cbSorteHand.setSelected(true);

			// sorte Festlegen
			oSearchSorte.get_oTextFieldForSearchInput().setText(idSorteBez);
			try {
				oSearchSorte.FillLabelWithDBQuery(idSorteBez);
			} catch (myException e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(
						"Die Sorte konnte nicht gefunden werden!")));
			}
		}
		
		// den Status der Sortenauswahl-Elemente setzen
		setStatusSorteneingabe();
	}

	
	/**
	 * setzt die Zusatzinfos der Artikelsorte / Kunden-Kombination
	 * @param idSorteBez
	 * @param idAdresse
	 * @param bLief
	 * @throws myException 
	 */
	private void setZusatzinfosSorte () throws myException{
		tfSortenInfo.setText(null);
		tfSortenInfo.setBackground(new E2_ColorBase());
		
		String idSorteBez = cmbSorteKunde.get_ActualWert().replace(".", "");
		
		String idAdresse = null;
		MyLong l = new MyLong(oSearchAdresse.get_oTextFieldForSearchInput().getText(), null, null);
		idAdresse = (l != null ? l.get_cUF_LongString() : null);
		
		boolean bLief = cbWarenEingang.isSelected();
		String sEKVK = bLief ? "'EK'" : "'VK'";

		if (bibALL.isEmpty(idSorteBez)  || bibALL.isEmpty(idAdresse)  || (cbWarenEingang.isSelected() == false && cbWarenAusgang.isSelected()== false) ){
			return;
		}
		
		
		// jetzt noch die Beschreibung aus dem Artikelstamm lesen, falls es einen gibt.
		String sQuery = " SELECT NVL(A.VERARBEITUNGS_INFO,'') FROM " +
				bibE2.cTO() + ".JT_ARTIKELBEZ_LIEF  A WHERE A.ID_ADRESSE = ( " +
				"     SELECT NVL(L.ID_ADRESSE_BASIS,A1.ID_ADRESSE) FROM " + 
					  bibE2.cTO() + ".JT_ADRESSE A1" +
				"     LEFT OUTER JOIN " + 
					  bibE2.cTO() + ".JT_LIEFERADRESSE L " +
				"    ON L.ID_ADRESSE_LIEFER = A1.ID_ADRESSE WHERE A1.ID_ADRESSE = " + idAdresse + "  ) " +
				" AND A.ID_ARTIKEL_BEZ = " + idSorteBez + 
				" AND A.ARTBEZ_TYP = " + sEKVK ;
		
		
		String sInfo;
		try {
			sInfo = bibDB.EinzelAbfrage(sQuery);

			if (sInfo.equalsIgnoreCase("null") ){
				sInfo = null;
			}
			
		} catch (Exception e) {
			sInfo = null;
		}
		
		if (!bibALL.isEmpty(sInfo)){
			tfSortenInfo.setBackground(new E2_ColorAlarm());
		}
		
		tfSortenInfo.setText(sInfo);
		
	}
	
	
	/**
	 * setzt den Status der Felder cbSorteHand cmbSorteKunde oSearchSorte
	 * 
	 * @throws myException
	 */
	private void setStatusSorteneingabe() throws myException {
		boolean bHand = cbSorteHand.isSelected();

		oSearchSorte.set_bEnabled_For_Edit(bHand);
		cmbSorteKunde.set_bEnabled_For_Edit(!bHand);

		if (bHand) {
			cmbSorteKunde.setSelectedIndex(0);
		} else {
			oSearchSorte.clean();
		}
		
		setZusatzinfosSorte();
	}

	/**
	 * Setzt den Status von GUI-Elementen abhängig ob WE oder WA gesetzt ist.
	 */
	private void setStatusWE_WA() {
		boolean bWE = cbWarenEingang.isSelected();
		boolean bWA = cbWarenAusgang.isSelected();
		/**
		 * Keine der beiden Checkboxen ist gesetzt
		 */
		if (!bWE && !bWA) {
			colCB_WE.setBackground(Color.RED);
			colCB_WA.setBackground(Color.RED);
		} else if (bWE) {
			colCB_WE.setBackground(Color.GREEN);
			colCB_WA.setBackground(new E2_ColorBase());
			try {
				if (cbWiegekarte_Wiegeschein_Lieferschein.isSelected()) {
					this.setCheckboxOfWatchdogAgent(
							agentWatchdogWiegekartenTyp,
							cbWiegekarte_Wiegeschein);
				}
				cbWiegekarte_Wiegeschein_Lieferschein.set_bEnabled_For_Edit(false);
			} catch (myException e) {
				e.printStackTrace();
			}
		} else if (bWA) {
			colCB_WE.setBackground(new E2_ColorBase());
			colCB_WA.setBackground(Color.GREEN);
			try {
				// wenn nicht vorher durch setAllDisabled() alle checkboxen disaled wurden, kann man sie wieder einschalten.
				if (!agentWatchdogWiegekartenTyp.get_AllDisabled() ){
					cbWiegekarte_Wiegeschein_Lieferschein.set_bEnabled_For_Edit(true);
				}
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialisieren der Wägungsdaten Wägung1 und Wägung2
	 */
	private void initWaegungData() {
		// alle Daten löschen...
		tfW1Datum.setText("");
		tfW1Zeit.setText("");
		tfW1Gewicht.setText("");
		tfW1HandDesc.get_oTextField().setText("");
		// tfW1Gewicht.add_InputValidator(new VALIDATE_INPUT_Float());
		cbW1Hand.setSelected(false);

		tfW2Datum.setText("");
		tfW2Zeit.setText("");
		tfW2Gewicht.setText("");
		tfW2HandDesc.get_oTextField().setText("");
		// tfW2Gewicht.add_InputValidator(new VALIDATE_INPUT_Float());
		cbW2Hand.setSelected(false);

		WK_Waegung_Buchungssatz oWaegung1Satz = null;
		WK_Waegung_Buchungssatz oWaegung2Satz = null;

		if (m_oWKHandler != null) {
			try {

				oWaegung1Satz = m_oWKHandler.get_BuchungWaegung1();
				oWaegung2Satz = m_oWKHandler.get_BuchungWaegung2();

				// die Waage-Nr
				String sWaageNr = null;

				if (oWaegung1Satz != null) {
					// 1. Wägung
					tfW1Datum.setText(oWaegung1Satz.getDATUM());
					tfW1Zeit.setText(oWaegung1Satz.getZEIT());
					tfW1Gewicht.setText(bibALL.convertBigDecimalToString(oWaegung1Satz.getGEWICHT(), 3));
					tfW1HandDesc.get_oTextField().setText(oWaegung1Satz.getHANDEINGABE_BEM());
					cbW1Hand.setSelected(oWaegung1Satz.getHANDEINGABE() == null ? false	: oWaegung1Satz.getHANDEINGABE().equalsIgnoreCase("Y"));
					if (sWaageNr == null) {
						sWaageNr = oWaegung1Satz.getW_WAAGEN_NR();
					}

					m_enumZustandMaske = ENUM_ZustandMaske.WAEGUNG1;
					
				}

				if (oWaegung2Satz != null) {
					// 1. Wägung
					tfW2Datum.setText(oWaegung2Satz.getDATUM());
					tfW2Zeit.setText(oWaegung2Satz.getZEIT());
					tfW2Gewicht.setText(bibALL.convertBigDecimalToString(oWaegung2Satz.getGEWICHT(), 3));
					tfW2HandDesc.get_oTextField().setText(oWaegung2Satz.getHANDEINGABE_BEM());
					cbW2Hand.setSelected(oWaegung2Satz.getHANDEINGABE() == null ? false : oWaegung2Satz.getHANDEINGABE().equalsIgnoreCase("Y"));
					if (sWaageNr == null) {
						sWaageNr = oWaegung2Satz.getW_WAAGEN_NR();
					}

					m_enumZustandMaske = ENUM_ZustandMaske.WAEGUNG2;
					
					
					//
					// prüfung, ob gedruckt, wenn ja, dann Zustand == GEDRUCKT.
					// Egal ob die Wiegekarte oder der Eingangsschein (geht momentan nur bei Strecke)
					// 
					if (m_oWKHandler.get_BuchungWiegekarte().getDRUCKZAEHLER() != null){
						if (m_oWKHandler.get_BuchungWiegekarte().getDRUCKZAEHLER().compareTo(BigDecimal.ZERO) > 0){
							m_enumZustandMaske = ENUM_ZustandMaske.GEDRUCKT;
						}
					} else if (m_oWKHandler.get_BuchungWiegekarte().getDRUCKZAEHLER_ES() != null){
						if (m_oWKHandler.get_BuchungWiegekarte().getDRUCKZAEHLER_ES().compareTo(BigDecimal.ZERO) > 0){
							m_enumZustandMaske = ENUM_ZustandMaske.GEDRUCKT;
						}
					}
				}
				
				// wenn der Standort nicht durch den Aufruf festgelegt wurde, versuchen den Standort hier zu ermitteln
				if (m_IdStandort == null){
					if (oWaegung1Satz != null){
						String idSettings = m_oWKHandler.get_REC_Waegung1().get_ID_WAAGE_SETTINGS_cUF();
						RECORD_WAAGE_SETTINGS settings = new RECORD_WAAGE_SETTINGS(idSettings);
						m_IdStandort = settings.get_ID_WAAGE_STANDORT_cUF();
					}
				}

				
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	
	
	
	/**
	 * ermitteln des aktuellen Lagers zu dem der Benutzer gehört, aus den
	 * Waage-Settings-Daten
	 * 
	 * @return
	 * @throws myException
	 */
	private void initWaageSettingsData() throws myException {


		
		// ermitteln der Waage-Bediener
		String sWhere = "AND W.ID_WAAGE_STANDORT = " + m_IdStandort;
		if (bibALL.isEmpty(m_IdStandort)){
			sWhere = "";
		}
		
		this.m_selWaageBediener.set_ListenInhalt ("SELECT U.NAME1  || ', ' ||  U.VORNAME, W.ID_USER FROM  " +
				bibE2.cTO()+ ".JT_WAAGE_USER W inner join JD_USER  U on U.ID_USER = W.ID_USER "
				+ " WHERE W.ID_MANDANT = " + bibALL.get_ID_MANDANT() + 
				sWhere +
				" ORDER BY sortierung", 
				false, 
				true,		
				false, 
				false);

		
		// der aktuelle Benutzer
		m_IdUser = bibALL.get_ID_USER();
		
		// die dem Benutzer zugeordnete Waagen / und Lager
		String sSqlSettings = "SELECT JT_WAAGE_SETTINGS.* "
				+ " FROM "
				+ bibE2.cTO()
				+ ".JT_WAAGE_SETTINGS  INNER JOIN  "
				+ bibE2.cTO()+ ".JT_WAAGE_USER  "
				+ " ON    (  JT_WAAGE_SETTINGS.ID_MANDANT = JT_WAAGE_USER.ID_MANDANT   )   "
				+ " AND   ( JT_WAAGE_SETTINGS.ID_WAAGE_STANDORT = JT_WAAGE_USER.ID_WAAGE_STANDORT  ) " 
				+ " WHERE JT_WAAGE_USER.ID_USER = " + m_IdUser;

		if (!bibALL.isEmpty(m_IdAdresseLager)) {
			sSqlSettings += " AND JT_WAAGE_SETTINGS.ID_ADRESSE_LAGER = "+ m_IdAdresseLager;
		} else {
			throw new myException("Keine Lagerzuordnung vorhanden!");
		}
		
		if (!bibALL.isEmpty(m_IdStandort)){
			sSqlSettings += " AND JT_WAAGE_SETTINGS.ID_WAAGE_STANDORT = " + m_IdStandort;
		}

		sSqlSettings += " ORDER BY JT_WAAGE_SETTINGS.SORTIERUNG";

		// ermitteln der Waagen
		m_listWaageSettings = new RECLIST_WAAGE_SETTINGS(sSqlSettings);


		//
		// die Waagedaten und GUI erzeugen
		//
		m_oGridWaagen.removeAll();
		m_listWaageDisplays.clear();
		
		if (m_listWaageSettings != null  && m_listWaageSettings.size() > 0 )
		{	
			for (int i = 0; i < m_listWaageSettings.get_vKeyValues().size(); i++) {
				
				RECORD_WAAGE_SETTINGS oSettings = m_listWaageSettings.get(i);
				WK_Row_Waagedisplay o = new WK_Row_Waagedisplay(oSettings);
	
				// Waagedisplays merken
				m_listWaageDisplays.add(o);
				o.setActionAgent(new actionSaveWaegung(o));
	
				// Waagedisplays auf die Maske
				m_oGridWaagen.add(new MyE2_Label(new MyE2_String(oSettings.get_BEZEICHNUNG_cUF_NN("-"))), 1, new Insets(5, 1, 0, 1) );
				m_oGridWaagen.add(o, 4, new Insets(5, 1, 0, 1));
			}
		} else {
			m_oGridWaagen.add(new MyE2_Label(new MyE2_String("Es sind keine Waage-Informationen vorhanden.")), 5, new Insets(5, 1, 0, 1));
		}
		
	}

	
	
	/**
	 * Enablen-Disablen der Felder, je nachdem ob schon Daten eingegeben wurden
	 * oder nicht.
	 * 
	 * @throws myException
	 */
	private void setFieldStatus() throws myException {
		
		boolean bStorno = false;
		if (m_oWKHandler.get_BuchungWiegekarte() !=null){
			bStorno = bibALL.null2leer(m_oWKHandler.get_BuchungWiegekarte().getSTORNO()).equals("Y");
		}
	
		boolean bTrue = true && !bStorno;
		
		m_btnSaveStamm.set_bEnabled_For_Edit(bTrue);
		m_btnSaveStammAndExit.set_bEnabled_For_Edit(bTrue);
		m_btnSaveStamm2.set_bEnabled_For_Edit(bTrue);
		
		
		
		cbWarenEingang.set_bEnabled_For_Edit(false);
		cbWarenAusgang.set_bEnabled_For_Edit(false);
		
		m_btnPrintBuero.set_bEnabled_For_Edit(false);
		m_btnPrintEtikett.set_bEnabled_For_Edit(false);
		tfAnzahlEtiketten.set_bEnabled_For_Edit(false);
		m_btnPrintWiegekarte.set_bEnabled_For_Edit(false);
		m_btnPrintWiegekarteEingangsschein.set_bEnabled_For_Edit(false);
		m_btnPrintEingangsscheinLieferschein.set_bEnabled_For_Edit(false);
		m_btnPrintHofschein.set_bEnabled_For_Edit(false);
		
		m_btnReadBarcode.set_bEnabled_For_Edit(false);
		
		m_btnRemoveLinkToFuhre.set_bEnabled_For_Edit(bTrue);
	

		switch (m_enumZustandMaske) {
		case NEU:
			m_btnCreateFolge.set_bEnabled_For_Edit(false);
			m_btnReadBarcode.set_bEnabled_For_Edit(bTrue);
			
			cbWarenEingang.set_bEnabled_For_Edit(bTrue);
			cbWarenAusgang.set_bEnabled_For_Edit(bTrue);

			break;
			
		case STAMMDATEN:
			m_btnCreateFolge.set_bEnabled_For_Edit(false);
			break;
		case WAEGUNG1:
			m_btnCreateFolge.set_bEnabled_For_Edit(false);
			// Hofschein ist nur nach der 1.Waegung aktiv
			m_btnPrintHofschein.setIDWiegekarte(m_oWKHandler);
			m_btnPrintHofschein.set_bEnabled_For_Edit(bTrue);
			break;

		case GEDRUCKT:
			m_oAbzuegeGebinde.set_bEnabled_For_Edit(false);
			m_oAbzuegeMengen.set_bEnabled_For_Edit(false);
			
			if (m_bShowContainerverwaltung){
				m_oContainer.set_bEnabled_For_Edit(false);
			}
			
			
			m_btnSaveStamm2.set_bEnabled_For_Edit(false);
			
			// büro und eingangsschein-lieferschein nur wenn die Wiegekarte schon gedruckt war
			m_btnPrintBuero.setIDWiegekarte(m_oWKHandler);
			m_btnPrintEingangsscheinLieferschein.setIDWiegekarte(m_oWKHandler);
			
			
			m_btnPrintBuero.set_bEnabled_For_Edit(bTrue);
			m_btnPrintEingangsscheinLieferschein.set_bEnabled_For_Edit(bTrue);
			
			cbRadioaktivitaetGeprueft.set_bEnabled_For_Edit(false);
			cbGesamtverwiegung.set_bEnabled_For_Edit(false);

			// alle wiegekartentypen disablen
			agentWatchdogWiegekartenTyp.set_AllDisabled();
			
			oSearchFuhre.set_bEnabled_For_Edit(false);
			tfKennzeichen.set_bEnabled_For_Edit(false);
			cbErlaubeMehrfachwiegungen.set_bEnabled_For_Edit(false);
			
			cbAdresseNeu.set_bEnabled_For_Edit(false);
			tfAdresse.set_bEnabled_For_Edit(false);
			oSearchAdresse.set_bEnabled_For_Edit(false);
			
			
			cbAdresseSpeditonNeu.set_bEnabled_For_Edit(false);
			tfAdresseSpedion.set_bEnabled_For_Edit(false);
			oSearchAdresseSpedition.set_bEnabled_For_Edit(false);
			
			oSearchAdresseAbnehmerStrecke.set_bEnabled_For_Edit(false);
			
			cbSorteHand.set_bEnabled_For_Edit(false);
			cmbSorteKunde.set_bEnabled_For_Edit(false);
			oSearchSorte.set_bEnabled_For_Edit(false);
			
			tfBemerkung1.set_bEnabled_For_Edit(false);
			tfBefund.set_bEnabled_For_Edit(false);
			tfBemerkung2.set_bEnabled_For_Edit(false);
			tfBemerkungIntern.set_bEnabled_For_Edit(false);
			
			//2018-05-15
			tfContainerNr.set_bEnabled_For_Edit(false);
			tfSiegelNr.set_bEnabled_For_Edit(false);
			
			
		case WAEGUNG2:
			
			// Druck-Knöpfe aktualisieren
			m_btnPrintWiegekarte.setIDWiegekarte(m_oWKHandler);
			m_btnPrintWiegekarteEingangsschein.setIDWiegekarte(m_oWKHandler);
			m_btnPrintEingangsscheinLieferschein.setIDWiegekarte(m_oWKHandler);
			m_btnPrintEtikett.setIDWiegekarte(m_oWKHandler);


			m_btnPrintEtikett.set_bEnabled_For_Edit(bTrue);
			tfAnzahlEtiketten.set_bEnabled_For_Edit(bTrue);
			m_btnPrintWiegekarte.set_bEnabled_For_Edit(bTrue);
			m_btnPrintWiegekarteEingangsschein.set_bEnabled_For_Edit(bTrue);
			
			// wenn schon die 2. Wägung durchgeführt wurde, kann auch eine
			// Folgewägung durchgeführt werden
			m_btnCreateFolge.set_bEnabled_For_Edit(bTrue);
			
			// Falls der Typ der Wiegekarte die Strecke ist, darf auch direkt der Eingangsschein gedruckt werden!
			String sTypWK = bibALL.null2leer(m_oWKHandler.get_REC_Wiegekarte().get_TYP_WIEGEKARTE_cUF() );
			if (sTypWK.equals(WK_LIST_CONST.TYP_WIEGEKARTE_STRECKE)){
				m_btnPrintEingangsscheinLieferschein.set_bEnabled_For_Edit(bTrue);
				
				// falls der Typ Strecke ist, darf keine Wiegekarte gedruckt werden dürfen
				m_btnPrintWiegekarte.set_bEnabled_For_Edit(false);
				m_btnPrintWiegekarteEingangsschein.set_bEnabled_For_Edit(false);
				m_btnPrintBuero.set_bEnabled_For_Edit(false);
				
			}

			// falls 2x Handwägung gewählt wurde, darf keine Wiegekarte gedruckt werden, ausser wenn der Schalte explizit gesetzt ist.
			if (cbW1Hand.isSelected() && cbW2Hand.isSelected() && !m_bDruckWennNurHandwaegung){
				m_btnPrintWiegekarte.set_bEnabled_For_Edit(false);
				m_btnPrintWiegekarteEingangsschein.set_bEnabled_For_Edit(false);
				m_btnPrintBuero.set_bEnabled_For_Edit(false);
			}

			break;
		default:
			// alles Gesperrt
			break;
		}

		
		
		
		//
		// Sonderbehandlung WAAGE-Zustände
		//
		cbW1Hand.set_bEnabled_For_Edit(false);
		
		tfW1Datum.set_bEnabled_For_Edit(false);
		tfW1Zeit.set_bEnabled_For_Edit(false);
		tfW1Id.set_bEnabled_For_Edit(false);
		tfW1Gewicht.set_bEnabled_For_Edit(false);
		tfW1HandDesc.set_bEnabled_For_Edit(false);

		cbW2Hand.set_bEnabled_For_Edit(false);
		tfW2Datum.set_bEnabled_For_Edit(false);
		tfW2Zeit.set_bEnabled_For_Edit(false);
		tfW2Id.set_bEnabled_For_Edit(false);
		tfW2Gewicht.set_bEnabled_For_Edit(false);
		tfW2HandDesc.set_bEnabled_For_Edit(false);

		oButtonW1ReadData.set_bEnabled_For_Edit(false);
		oButtonW2ReadData.set_bEnabled_For_Edit(false);

		

		
		switch (m_enumZustandMaske) {
		case NEU:

			// wägung 1 offen
			// wägung 2 gesperrt
			cbW1Hand.set_bEnabled_For_Edit(bTrue);
			if (cbW1Hand.isSelected()) {
				tfW1Gewicht.set_bEnabled_For_Edit(bTrue);
				tfW1HandDesc.set_bEnabled_For_Edit(bTrue);
				oButtonW1ReadData.set_bEnabled_For_Edit(bTrue);
				oButtonW1ReadData.setText(new MyE2_String("Speichern!").CTrans());
			} else {
				tfW1Gewicht.set_bEnabled_For_Edit(false);
				tfW1HandDesc.set_bEnabled_For_Edit(false);
				oButtonW1ReadData.set_bEnabled_For_Edit(false);
			}

			// Buttons für alle Waagen setzen
			for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
				WK_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
				oDisplay.setRowEnabled(!cbW1Hand.isSelected());
				oDisplay.setRequestingWeight(!cbW1Hand.isSelected());
				
				oDisplay.setButtonText(new MyE2_String("Wägung 1 durchführen!"));
			}

			break;

		case STAMMDATEN:
			// wägung 1 offen
			// wägung 2 gesperrt

			cbW1Hand.set_bEnabled_For_Edit(bTrue);
			if (cbW1Hand.isSelected()) {
				tfW1Gewicht.set_bEnabled_For_Edit(bTrue);
				tfW1HandDesc.set_bEnabled_For_Edit(bTrue);
				oButtonW1ReadData.set_bEnabled_For_Edit(bTrue);
				oButtonW1ReadData.setText(new MyE2_String("Speichern!").CTrans());
			} else {
				tfW1Gewicht.set_bEnabled_For_Edit(false);
				tfW1HandDesc.set_bEnabled_For_Edit(false);
				oButtonW1ReadData.set_bEnabled_For_Edit(false);
			}

			// Buttons für alle Waagen setzen
			for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
				WK_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
				oDisplay.setRowEnabled(!cbW1Hand.isSelected());
				oDisplay.setRequestingWeight(!cbW1Hand.isSelected());
				
				oDisplay.setButtonText(new MyE2_String("Wägung 1 durchführen!"));
			}

			break;

		case WAEGUNG1:
			// wägung1 gesperrt
			// wägung2 offen
			// Wägung2 Hand möglich wenn W1 kein Hand war

			// 2011-05-30 : 2mal Handwägung erlauben
//			boolean bHand1 = cbW1Hand.isSelected();
//			cbW2Hand.set_bEnabled_For_Edit(!bHand1);
			cbW2Hand.set_bEnabled_For_Edit(bTrue);
			
			
			if (cbW2Hand.isSelected()) {
				tfW2Gewicht.set_bEnabled_For_Edit(bTrue);
				tfW2HandDesc.set_bEnabled_For_Edit(bTrue);
				oButtonW2ReadData.set_bEnabled_For_Edit(bTrue);
				oButtonW2ReadData.setText(new MyE2_String("Speichern").CTrans());
			} else {
				tfW2Gewicht.set_bEnabled_For_Edit(false);
				tfW2HandDesc.set_bEnabled_For_Edit(false);
				oButtonW2ReadData.set_bEnabled_For_Edit(false);
			}

			// schauen, welche Waage bei der 1. Wägung benutzt wurde,
			// nur diese darf auch für die 2. Wägung aktiv sein.
			String id_WaageSettings = m_oWKHandler.get_BuchungWaegung1().getID_WAAGE_SETTINGS();
			
			// prüfen, ob in dem der Waagen zugeordneten Standort möglich ist, dass bei der zweitverwiegung auch
			// mehrere Waagen erlaubt sind.
			
			
			boolean bWaageZwang = true;    // true: die Waage für die Zweitwägung muss die gleiche sein wie die Waage für die Erstwägung
			if (m_listWaageDisplays.size()>0){
				WK_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(0);
				RECORD_WAAGE_STANDORT oStandort  = new RECORD_WAAGE_STANDORT(oDisplay.getWaageSettings().get_ID_WAAGE_STANDORT_cUF());
				if (oStandort.get_WAAGE_ZWANG_cUF_NN("Y").equals("N")){
					bWaageZwang = false;
				}
			}
			
			
			// Buttons für alle Waagen setzen
			for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
				WK_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
				String idWaageSettingsRow = oDisplay.getWaageSettings().get_ID_WAAGE_SETTINGS_cUF();
				if (	id_WaageSettings == null  // 1. Wägung war Handwägung 
						|| id_WaageSettings.equals(idWaageSettingsRow)
						|| bWaageZwang == false ) { 
					oDisplay.setRowEnabled(!cbW2Hand.isSelected());
					oDisplay.setRequestingWeight(!cbW2Hand.isSelected());
					oDisplay.setButtonText(new MyE2_String("Wägung 2 durchführen!"));
				} else {
					oDisplay.setRowEnabled(false);
					oDisplay.setRequestingWeight(false);
					oDisplay.setButtonText(new MyE2_String("Waage wurde nicht bei der Erstwägung verwendet."));
				}
			}
			break;
			
		case GEDRUCKT:
		case WAEGUNG2:
			// Buttons für alle Waagen setzen
			for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
				WK_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
				oDisplay.setRowEnabled(false);
				oDisplay.setRequestingWeight(false);
			}

	  		break;
		default:
			// alles Gesperrt
			break;
		}
	}

	
	
	/**
	 * Schaltet zwischen den Adressfeldern Standard und Alternativ um. Es wird
	 * der jeweils anderer Bereich deaktiviert und gelöscht!
	 * 
	 * @param bStandard
	 * @throws myException
	 */
	private void setAktiveAdresse(MyE2_CheckBox cbAdresseNeu,
			MyE2_TextArea tfAdresse, MyE2_MaskSearchField oSearchAdresse,
			boolean bStandard) throws myException {

		cbAdresseNeu.setSelected(!bStandard);
		tfAdresse.set_bEnabled_For_Edit(!bStandard);
		oSearchAdresse.set_bEnabled_For_Edit(bStandard);
		// leeren des jeweils anderen Feldes
		if (!bStandard) {
			oSearchAdresse.clean();
		} else {
			tfAdresse.setText(null);
		}
	}

	/**
	 * liest die Waage aus, die angegeben ist
	 * 
	 * @param Command
	 * @return
	 * @throws myException
	 */
	private WK_Waegung_Buchungssatz readWaage(RECORD_WAAGE_SETTINGS recSettings, WaageHandlerBase.ENUM_Commands Command)	throws myException {
		WK_Waegung_Buchungssatz oBuchung = null;
		WaageHandlerBase hWaage = null;
		WaageSatzBase oSatzBase = null;

		String sWaageErgebnis = null;

		if (recSettings != null) {
			hWaage = WaageHandlerFactory.getWaageHandler(recSettings);
		}

		
		if (hWaage != null) {
			hWaage.setCommand(Command);
			try {
				sWaageErgebnis = hWaage.leseWaage();
			} catch (Exception e) {
				sWaageErgebnis = null;
				throw new myException(e.getMessage());
			}
		}

		// jetzt den Buchungssatz aus dem Waage-Satzaufbau erstellen
		if (sWaageErgebnis != null) {
			oSatzBase = hWaage.getWaageSatz();
			
			boolean bOK = oSatzBase.getStatus().equals(ENUM_WaageResultStatus.OK);

			oBuchung = new WK_Waegung_Buchungssatz(oSatzBase);
			if (bOK) {
				oBuchung.setWAAGE_DS_ORI(sWaageErgebnis);
				oBuchung.setID_WAAGE_SETTINGS(recSettings.get_ID_WAAGE_SETTINGS_cUF());
			} 
		}
		
		oSatzBase = null;
		hWaage = null;
		
		return oBuchung;
	}

	/**
	 * prüft das Kennzeichen und setzt - wenn gefunden - das Adressfeld des
	 * Lieferanten es wird sich gemerkt, ob das Kennzeichen ok ist und die
	 * AdressID des Kennzeichen-Halters
	 * 
	 * @throws myException
	 */
	private void pruefeKennzeichen() throws myException {
		// m_KennzeichenGeprüft = null;
		// m_KennzeichenOK = false;

		WK_Kennzeichen oKenn = new WK_Kennzeichen(tfKennzeichen	.get_oTextField().getText());
		tfKennzeichen.get_oTextField().setText(oKenn.getKennzeichen());

		// Normalisierung des Kennzeichens
		// m_KennzeichenOK = (oKenn.getKennzeichen() != null &&
		// !oKenn.getKennzeichen().equals(""));
		// m_KennzeichenGeprüft = oKenn.getKennzeichen();

	}

	/**
	 * Lädt Fuhrendaten, die in den Feldern Fuhre und Fuhrenort eingetragen
	 * wurden.
	 * 
	 * @throws myException
	 */
	private WK_RECORD_FuhrenInfo ladeFuhrenDaten() throws myException {
		WK_RECORD_FuhrenInfo oRecFI = null;

		String sIDFuhre = null;
		String sIDFuhreOrt = null;

		tfFuhre.show_InputStatus(false);
		tfFuhreOrt.show_InputStatus(false);

		// wenn beide Felder leer sind, dann laden eines Dialogs zur Auswahl...
		if (bibALL.isEmpty(tfFuhre.getText()) && bibALL.isEmpty(tfFuhreOrt.getText())) {
			// TODO Aufruf des Auwahldialogs
		} else {

			// Ansonsten prüfen
			boolean bErr = false;
			MyE2_MessageVector oMV1 = tfFuhre.get_MV_InputOK();
			tfFuhre.show_InputStatus(oMV1.get_bHasAlarms());
			if (oMV1.get_bHasAlarms()) {
				// FOCUS SETZEN
				bErr = true;
				ApplicationInstance.getActive().setFocusedComponent((Component) tfFuhre);
				bibMSG.add_MESSAGE(oMV1);
			} else {
				sIDFuhre = tfFuhre.getText();
			}

			if (!bibALL.isEmpty(tfFuhreOrt.getText())) {
				oMV1 = tfFuhreOrt.get_MV_InputOK();
				tfFuhreOrt.show_InputStatus(oMV1.get_bHasAlarms());
				if (oMV1.get_bHasAlarms()) {
					// FOCUS SETZEN
					if (!bErr)
						ApplicationInstance.getActive().setFocusedComponent((Component) tfFuhreOrt);
					bibMSG.add_MESSAGE(oMV1);
				} else {
					sIDFuhreOrt = tfFuhreOrt.getText();
				}
			}
 
			if (S.isFull(sIDFuhre)  /* !bibMSG.get_bHasAlarms()*/  ) {
				WK_FuhrenInfo oFI = new WK_FuhrenInfo();
				oRecFI = oFI.getFuhrenInfo(sIDFuhre, sIDFuhreOrt,m_IdAdresseLager);
			}
		}

		return oRecFI;

	}

	/**
	 * Nimmt einen FuhrenInfoRecord und prüft die Daten in der Maske gegen
	 * 
	 * @param oRecFI
	 * @throws myException
	 */
	private void setzeFuhrendatenInMaske(WK_RECORD_FuhrenInfo oRecFI)
			throws myException {

		if (oRecFI == null)
			return;
		boolean bIstDokumentarisch = cbWiegekarte_Dokumentarisch.isSelected();

		String idAdresseKunde = null;
		String idAdresseStrecke = null;

		// Standards setzen
		oSearchAdresseAbnehmerStrecke.clean();
		
		
		// Wenn es eine dokumentarische Verwiegung war, darf nichst anderes gesetzt werden.
		if (!bIstDokumentarisch){
			setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Wiegeschein);
		}
		

		
		// wenn es ein Lieferant ist, dann muss in der Fuhre der Abnehmer das
		// Lager sein
		boolean bIstStrecke = oRecFI.getIST_STRECKE().equalsIgnoreCase("Y");
		
		boolean bLief = oRecFI.getIST_LIEFERANT().equalsIgnoreCase("Y");
		if (bLief) {
			if (!oRecFI.getLagerListe().contains(oRecFI.getID_ADRESSE_ZIEL()) && !bIstStrecke ) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Lager ist nicht in der Liste der akzeptierten Lager eingetragen!")));
			}
			idAdresseKunde = oRecFI.getID_ADRESSE_START();
			if (bIstStrecke){
				idAdresseStrecke = oRecFI.getID_ADRESSE_ZIEL();
			}
		} else {
			if (!oRecFI.getLagerListe().contains(oRecFI.getID_ADRESSE_START())) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message( new MyE2_String("Das Lager ist nicht in der Liste der akzeptierten Lager eingetragen!")));
			}
			idAdresseKunde = oRecFI.getID_ADRESSE_ZIEL();
		}

		// wenn das Lager stimmt, dann die Werte übernehmen...
		if (bibMSG.get_bIsOK()) {

			if (!bibALL.isEmpty(oRecFI.getIST_LIEFERANT())) {
				if (oRecFI.getIST_LIEFERANT().equalsIgnoreCase("Y")) {
					setCheckboxOfWatchdogAgent(agentWatchdogWEWA,cbWarenEingang);
				} else {
					setCheckboxOfWatchdogAgent(agentWatchdogWEWA,cbWarenAusgang);
				}
			}
			this.setStatusWE_WA();

			// Auf Suchfeld Adresse setzen
			this.setAktiveAdresse(this.cbAdresseNeu, this.tfAdresse,this.oSearchAdresse, true);

			// Lieferanten-Adresse festlegen
			oSearchAdresse.get_oTextFieldForSearchInput().setText(idAdresseKunde);
			try {
				oSearchAdresse.FillLabelWithDBQuery(idAdresseKunde);
			} catch (Exception e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse konnte nicht gefunden werden!")));
			}

			// Streckenadresse
			if (bIstStrecke){
				oSearchAdresseAbnehmerStrecke.get_oTextFieldForSearchInput().setText(idAdresseStrecke);
				try {
					oSearchAdresseAbnehmerStrecke.FillLabelWithDBQuery(idAdresseStrecke);
				} catch (Exception e) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse des Streckenziels konnte nicht gefunden werden!")));
				}
				if (!bIstDokumentarisch){
					setCheckboxOfWatchdogAgent(agentWatchdogWiegekartenTyp,cbWiegekarte_Strecke);
				}
				
			}
			
			
			// Kundensorten laden
			this.refreshSorteKunden();

			// suchen, ob die Sorte in den Kundensorten drin steht
			setSorteInMaske(oRecFI.getID_ARTIKEL_BEZ(),idAdresseKunde, bLief );

			// Kennzeichen
			if (!bibALL.isEmpty(oRecFI.getTRANSPORTKENNZEICHEN())) {
				this.tfKennzeichen.get_oTextField().setText(oRecFI.getTRANSPORTKENNZEICHEN());
				pruefeKennzeichen();
			}

			// SPEDITION
			// Auf Suchfeld Adresse setzen
			this.setAktiveAdresse(this.cbAdresseSpeditonNeu, this.tfAdresseSpedion, this.oSearchAdresseSpedition, true);

			// Lieferanten-Adresse festlegen
			oSearchAdresseSpedition.get_oTextFieldForSearchInput().setText(	oRecFI.getID_ADRESSE_SPEDITION());
			try {
				oSearchAdresseSpedition.FillLabelWithDBQuery(oRecFI .getID_ADRESSE_SPEDITION());
			} catch (Exception e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	"Die Speditions-Adresse konnte nicht gefunden werden!")));
			}

			// wenn keine Spedition angegeben ist, prüfen, ob das Kennzeichen zu
			// den eigenen gehört.
			if (oSearchAdresseSpedition.get_bIsEmpty() && tfAdresseSpedion.get_bIsEmpty()){
				String sKennzeichen = tfKennzeichen.get_oTextField().getText();
				String [][] varianten = tfKennzeichen.get_Varianten();
				if (varianten != null){
					for (int i=0;i<varianten.length;i++)
					{
						if (varianten[i][0].equals(sKennzeichen)){
							oSearchAdresseSpedition.get_oTextFieldForSearchInput().setText(	bibALL.get_ID_ADRESS_MANDANT());
							try {
								oSearchAdresseSpedition.FillLabelWithDBQuery(bibALL.get_ID_ADRESS_MANDANT());
							} catch (Exception e) {
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	"Die Speditions-Adresse konnte nicht gefunden werden!")));
							}
							break;
						}
					}
				}
			}
			
			
		}
	}

	/**
	 * Setzt die Adresse im Adressen-Suchfeld
	 * 
	 * @param oSearchFieldAdresse
	 * @param idAdresse
	 */
	private void setAdresseInSearchfield(
			WK_UB_SearchFieldAdresse oSearchFieldAdresse, String idAdresse) {
		// Lieferanten-Adresse festlegen
		oSearchFieldAdresse.get_oTextFieldForSearchInput().setText(idAdresse);
		try {
			oSearchFieldAdresse.FillLabelWithDBQuery(idAdresse);
		} catch (Exception e) {
			oSearchFieldAdresse.get_oTextFieldForSearchInput().setText(null);
		}
	}

	/**
	 * prüft die übergebenen Felder auf vollständigkeit und noch andere
	 * Kriterien
	 * 
	 * @param IDSorte
	 * @param IDAdresseKunde
	 *            // ID Kunde
	 * @param AdresseLieferant
	 *            // Textuelle Adresse, falls keine ID vorhanden ist
	 * @param Kennzeichen
	 * @throws myException
	 */
	private void pruefeEingabenWiegekarte(String IDSorte,
			String IDAdresseKunde, String AdresseLieferant, String Kennzeichen)
			throws myException {

		bibMSG.add_MESSAGE(vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());

		// Wareneingang oder Ausgang muss gesetzt werden
		if (!cbWarenEingang.isSelected() && !cbWarenAusgang.isSelected()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wareneingang oder Warenausgang muss gesetzt werden.")));
		}

		// prüfen, ob das Kennzeichen vorhanden ist
		if (bibALL.isEmpty(Kennzeichen)) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Kennzeichen angegeben sein.")));
			ApplicationInstance.getActive().setFocusedComponent((Component) tfKennzeichen);
			tfKennzeichen.show_InputStatus(true);
		}

		// 2012-01-18: Manfred: die Adresse muss erst bei der 2. Wägung festgelegt sein!
		// prüfen, ob die Kundenadresse angegeben ist
//		if ((bibALL.isEmpty(IDAdresseKunde) || IDAdresseKunde.equals("0"))
//				&& bibALL.isEmpty(AdresseLieferant)) {
//			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Adresse angegeben werden.")));
//		}
		
		
		

		//
		// Lageradresse
		if (m_IdAdresseLager == null || m_IdAdresseLager.equals("0")) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Lager ausgewählt werden!")));
		}

		//
		// Feldlängen
		tfBefund.show_InputStatus(false);
		if (tfBefund.getText().length() > tfBefund.get_iMaxInputSize()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Text im Feld Befund ist zu lang. Bitte kürzen Sie den Text.").CTrans()));
			tfBefund.show_InputStatus(true);
		}

		tfAdresse.show_InputStatus(false);
		if (tfAdresse.getText().length() > tfAdresse.get_iMaxInputSize()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Text im Feld Adresse ist zu lang. Bitte kürzen Sie den Text.").CTrans()));
			tfAdresse.show_InputStatus(true);
		}

		MyE2_MessageVector oMV1 = null;
		try {
			oMV1 = tfBefund.get_MV_InputOK();
			if (oMV1.get_bHasAlarms()) {
				tfBefund.show_InputStatus(false);
			}
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bibMSG.add_MESSAGE(oMV1);
	}

	/**
	 * Füllt / ändert den WiegekartenBuchungs-Satz
	 * 
	 * @return
	 * @throws myException
	 */
	private boolean createOrUpdateWiegekarteBuchung() throws myException {
		String sIDAdresseKunde = null;
		String sIDSorte = null;
		String sKennzeichen = null;
		String sBefund = null;
		String sIstLieferant = null;
		String sAdresseLieferant = null;
		String sAdresseSpedition = null;

		String sIDFuhre = null;
		String sIDFuhreOrt = null;
		String sIDArtikelBez = null;
		String sSorteHand = null;

		String sTYP_WIEGEKARTE = null;
		String sID_ADRESSE_SPEDITION = null;
		String sBEMERKUNG1 = null;
		String sBEMERKUNG2 = null;
		String sBEMERKUNG_INTERN = null;
		String sIST_GESAMTVERWIEGUNG = null;
		String sIST_RADIOAKTIVITAETGEPRUEFT = null;

		BigDecimal nGEWICHT_ABZUG = null;
		String sGRUND_ABZUG = null;
		// BigDecimal nGEWICHT_NACH_ABZUG = null;
		String sID_Adresse_Abn_Strecke = null;
		
		// 2018-05-15
		String sContainerNr = null;
		String sSiegelNr = null;
		

		// Prüfungen
		//
		// Sorte ermitteln
		sSorteHand = null;
		if (!cbSorteHand.isSelected()) {
			sIDArtikelBez = cmbSorteKunde.get_ActualWert().replace(".", "");
		} else {
			MyLong l = new MyLong(oSearchSorte.get_oTextFieldForSearchInput()
					.getText(), null, null);
			sIDArtikelBez = (l != null ? l.get_cUF_LongString() : null);
			sSorteHand = "Y";
		}

		if (!bibALL.isEmpty(sIDArtikelBez)) {
			RECORD_ARTIKEL_BEZ oBez = new RECORD_ARTIKEL_BEZ(sIDArtikelBez);
			sIDSorte = oBez.get_ID_ARTIKEL_cUF();
		}

		//
		// Kennzeichen ermitteln
		sKennzeichen = tfKennzeichen.get_oTextField().getText();

		//
		// Adresse ermitteln
		if (cbAdresseNeu.isSelected()) {
			sAdresseLieferant = tfAdresse.getText();
			sIDAdresseKunde = null;
		} else {
			sAdresseLieferant = null;
			MyLong l = new MyLong(oSearchAdresse.get_oTextFieldForSearchInput().getText(), null, null);
			sIDAdresseKunde = (l != null ? l.get_cUF_LongString() : null);
		}

		//
		// Speditions-Adressen ermitteln
		MyLong l = new MyLong(oSearchAdresseSpedition.get_oTextFieldForSearchInput().getText(), null, null);
		sID_ADRESSE_SPEDITION = (l != null ? l.get_cUF_LongString() : null);
		
		if (cbAdresseSpeditonNeu.isSelected()) {
			sAdresseSpedition = tfAdresseSpedion.getText();
		} else {
			sAdresseSpedition = null;
		}
	
		

		// Eingaben prüfen
		pruefeEingabenWiegekarte(sIDArtikelBez, sIDAdresseKunde, sAdresseLieferant, sKennzeichen);

		// beenden, falls es ein Problem gibt
		if (bibMSG.get_bHasAlarms()) {
			return false;
		}

		// die ID der Abnehmeradresse unformatiert
		sID_Adresse_Abn_Strecke = oSearchAdresseAbnehmerStrecke.get_cString4Database().replace(".", "");

		// den Typ der Wiegekarte ermitteln (es kann immer nur einen geben)
		sTYP_WIEGEKARTE = "";
		sTYP_WIEGEKARTE += cbWiegekarte_Wiegeschein.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_WIEGESCHEIN	: "";
		sTYP_WIEGEKARTE += cbWiegekarte_Strecke.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_STRECKE	: "";
		sTYP_WIEGEKARTE += cbWiegekarte_Fremdwiegung.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_FREMDWIEGUNG	: "";
		sTYP_WIEGEKARTE += cbWiegekarte_Wiegeschein_Lieferschein.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_WIEGESCHEIN_LIEFERSCHEIN	: "";
		sTYP_WIEGEKARTE += cbWiegekarte_Lager.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_LAGER	: "";
		sTYP_WIEGEKARTE += cbWiegekarte_Retoure.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_RETOURE	: "";
		sTYP_WIEGEKARTE += cbWiegekarte_Leerfuhre.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_LEERFUHRE	: "";
		sTYP_WIEGEKARTE += cbWiegekarte_Dokumentarisch.isSelected() ? WK_LIST_CONST.TYP_WIEGEKARTE_DOKUMENTARISCH	: "";
		

		// WE/WA
		boolean bLief = cbWarenEingang.isSelected();
		sIstLieferant = bLief ? "Y" : null;

		sBefund = tfBefund.getText();

		sIDFuhre = tfFuhre.getText();
		sIDFuhreOrt = tfFuhreOrt.getText();

		sBEMERKUNG1 = tfBemerkung1.getText();
		sBEMERKUNG2 = tfBemerkung2.getText();
		sBEMERKUNG_INTERN = tfBemerkungIntern.getText();
		
		// 2018-05-15
		sContainerNr = tfContainerNr.getText();
		sSiegelNr = tfSiegelNr.getText();
		//
		

		sIST_GESAMTVERWIEGUNG = cbGesamtverwiegung.isSelected() ? "Y" : "N";
		sIST_RADIOAKTIVITAETGEPRUEFT = cbRadioaktivitaetGeprueft.isSelected() ? "Y" : "N";

		
		if (bibMSG.get_bHasAlarms()) {
			return false;
		}

		String sWiegekarten_LFDNr = "SEQ_" + bibALL.get_ID_MANDANT() + "_WIEGEKARTENNR.NEXTVAL";

		//
		WK_Wiegekarte_Buchungssatz oWiegekarteSatz = m_oWKHandler.get_BuchungWiegekarte();

		if (oWiegekarteSatz == null) {
			
			// neuer Satz : INSERT
			oWiegekarteSatz = new WK_Wiegekarte_Buchungssatz(
					"SEQ_WIEGEKARTE.NEXTVAL", 
					m_IdUser, 
					"SYSDATE",
					sWiegekarten_LFDNr, 
					m_IdAdresseLager, 
					sIDAdresseKunde,
					sKennzeichen, 
					sIDSorte, 
					sBefund, 
					sIDFuhre, 
					sIDFuhreOrt,
					null, 
					sIstLieferant, 
					null, 
					sAdresseLieferant,
					sIDArtikelBez, 
					sTYP_WIEGEKARTE, 
					sID_ADRESSE_SPEDITION,
					sBEMERKUNG1, 
					sBEMERKUNG2, 
					sBEMERKUNG_INTERN,
					sIST_GESAMTVERWIEGUNG, 
					nGEWICHT_ABZUG, 
					sGRUND_ABZUG,
					null,
					sID_Adresse_Abn_Strecke,
					m_IDFolgewiegekarte, 
					null, 
					sAdresseSpedition,
					sSorteHand,
					null,
					null,
					null,
					null,
					sIST_RADIOAKTIVITAETGEPRUEFT,
					m_IdStandort,
					sContainerNr,
					sSiegelNr);
			

			m_oWKHandler.set_BuchungWiegekarte(oWiegekarteSatz);

		} else {
			// Update
			oWiegekarteSatz.setBEFUND(sBefund);
			oWiegekarteSatz.setID_ADRESSE_LIEFERANT(sIDAdresseKunde);
			oWiegekarteSatz.setID_ARTIKEL_SORTE(sIDSorte);
			oWiegekarteSatz.setIST_LIEFERANT(sIstLieferant);
			oWiegekarteSatz.setKENNZEICHEN(sKennzeichen);
			oWiegekarteSatz.setADRESSE_LIEFERANT(sAdresseLieferant);
			oWiegekarteSatz.setID_VPOS_TPA_FUHRE(sIDFuhre);
			oWiegekarteSatz.setID_VPOS_TPA_FUHRE_ORT(sIDFuhreOrt);
			oWiegekarteSatz.setID_ARTIKEL_BEZ(sIDArtikelBez);

			oWiegekarteSatz.setTYP_WIEGEKARTE(sTYP_WIEGEKARTE);
			oWiegekarteSatz.setID_ADRESSE_SPEDITION(sID_ADRESSE_SPEDITION);
			oWiegekarteSatz.setADRESSE_SPEDITION(sAdresseSpedition);
			oWiegekarteSatz.setBEMERKUNG1(sBEMERKUNG1);
			oWiegekarteSatz.setBEMERKUNG2(sBEMERKUNG2);
			oWiegekarteSatz.setBEMERKUNG_INTERN(sBEMERKUNG_INTERN);
			oWiegekarteSatz.setIST_GESAMTVERWIEGUNG(sIST_GESAMTVERWIEGUNG);
			oWiegekarteSatz.setGEWICHT_ABZUG(nGEWICHT_ABZUG);
			oWiegekarteSatz.setGRUND_ABZUG(sGRUND_ABZUG);
			oWiegekarteSatz.setID_ADRESSE_ABN_STRECKE(sID_Adresse_Abn_Strecke);
			oWiegekarteSatz.setSORTE_HAND(sSorteHand);
			oWiegekarteSatz.setIST_RADIOAKTIVITAETGEPRUEFT(sIST_RADIOAKTIVITAETGEPRUEFT);
			// 2018-05-15
			oWiegekarteSatz.setCONTAINER_NR(sContainerNr);
			oWiegekarteSatz.setSIEGEL_NR(sSiegelNr);
		}

		return true;
	}

	/**
	 * Speichert eine Wägung x ab KEINE HANDWÄGUNG!!
	 * 
	 * @param Wägungsposition
	 *            1 oder 2
	 */
	private WK_Waegung_Buchungssatz createWaegungBuchung(
			RECORD_WAAGE_SETTINGS recSetting, 
			String idUserWaegung)		throws myException {
		
		
		// Feststellen welche Wägung durchgeführt werden soll:
		int iWaegungPos = 0;
		if (m_enumZustandMaske.equals(ENUM_ZustandMaske.NEU) || m_enumZustandMaske.equals(ENUM_ZustandMaske.STAMMDATEN)) {
			iWaegungPos = 1;
		} else if (m_enumZustandMaske.equals(ENUM_ZustandMaske.WAEGUNG1)) {
			iWaegungPos = 2;
		} else {
			iWaegungPos = 0;
		}

		String sWaegungPos = new Integer(iWaegungPos).toString();

		// neuer Buchunssatz
		WK_Waegung_Buchungssatz oSatz = null;
		// WK_Waegung_Buchungssatz oWaegung1Satz;
		//
		// String sGewicht = null;

		switch (iWaegungPos) {
		case 1:
			if (m_oWKHandler.get_BuchungWaegung1() != null) {
				throw new myException(new MyE2_String("Wägung 1 wurde schon durchgeführt!").CTrans());
			}
			break;
		case 2:

			if (m_oWKHandler.get_BuchungWaegung2() != null) {
				throw new myException(new MyE2_String("Wägung 2 wurde schon durchgeführt!").CTrans());
			}

			// Prüfung ob schon eine Adresse eingegeben wurde: Zur schnelleren Wägung reicht es, die Adresse vor der 2. Wägung eingegeben zu haben
			if (bibALL.isEmpty(m_oWKHandler.get_BuchungWiegekarte().getID_ARTIKEL_BEZ() ) || 
				       ( 
						bibALL.isEmpty(m_oWKHandler.get_BuchungWiegekarte().getID_ADRESSE_LIEFERANT() ) &&
						bibALL.isEmpty(m_oWKHandler.get_BuchungWiegekarte().getADRESSE_LIEFERANT())
				       )
				){
				throw new myException(new MyE2_String("Vor der 2. Wägung muss die Adresse und die Sorte ausgewählt sein.").CTrans());
			}
			
			
			break;
		default:
			throw new myException(new MyE2_String("Es wurde keine gültige Wägungsnummer erkannt!").CTrans());
		}

		String sIDWiegekarte = null;
		if (m_enumZustandMaske.equals(ENUM_ZustandMaske.NEU)) {
			sIDWiegekarte = "SEQ_WIEGEKARTE.CURRVAL";
		} else {
			sIDWiegekarte = m_IDWiegekarte;
		}

		// holen des aktuellen Waagewertes
		oSatz = this.getWaegung(recSetting);

		//
		// prüfen, ob die Wägung unter der mindeslast der Waage liegt. Wenn ja, keine Wägung erlauben
		// ausser der Wiegekartentyp setzt die Mindestlast ausser Funktion.
		//
//		boolean bMindestlastErforderlich = bib_Settigs_Mandant.get_Waage_IstSPERRUNGBeiMindestlastUnterschreitung();
		
		if (m_bMindestlastErforderlich && getWiegekartenTypErfodertMindestlast()) {
			BigDecimal bdMindestlast = recSetting.get_MINDESTLAST_bdValue(BigDecimal.ONE.negate());
			
			//
			// Prüfung ob das Gewicht der Mindestlast entspricht 
			//
			if (bdMindestlast.compareTo(BigDecimal.ZERO) < 0){
				throw new myException(new MyE2_String("Mindestlast der Waage ist nicht angegeben! Keine Wägung möglich!").CTrans());
			} else {
				if (oSatz.getGEWICHT().compareTo(bdMindestlast) < 0 ){
					throw new myException(new MyE2_String("Mindestlast der Waage ist unterschritten! Keine Wägung möglich!").CTrans());
				}
			}
			
			//
			// Prüfung, ob bei 2.Waegung das Nettogewicht über der Mindestlast liegt
			// 
			if(iWaegungPos == 2){
				WK_Waegung_Buchungssatz oBuchung1 = m_oWKHandler.get_BuchungWaegung1();
				if (oBuchung1 != null){
					BigDecimal bdGewicht1 = oBuchung1.getGEWICHT();
					if ( oSatz.getGEWICHT().subtract(bdGewicht1).abs().compareTo(bdMindestlast) < 0 ){
						throw new myException(new MyE2_String("Nettogewicht wird kleiner als die Mindestlast! Keine Wägung möglich!").CTrans());
					}
				}
			}
		}

		
		
		
		if (oSatz != null && oSatz.get_WaageResultStatus().equals(ENUM_WaageResultStatus.OK)) {

			oSatz.setWAEGUNG_POS(sWaegungPos);
			oSatz.setID_WIEGEKARTE(sIDWiegekarte);
			oSatz.setID_WAEGUNG("SEQ_WAEGUNG.NEXTVAL");
			oSatz.setID_USER_WAEGUNG(idUserWaegung);

			switch (iWaegungPos) {
			case 1:
				m_oWKHandler.set_BuchungWaegung1(oSatz);
				break;
			case 2:
				m_oWKHandler.set_BuchungWaegung2(oSatz);
				break;
			default:
				throw new myException(new MyE2_String("Es wurde keine gültige Wägungsnummer angegeben!").CTrans());
			}
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oSatz.get_WaageFehlerBeschreibung()));
		}
		
		return oSatz;
	}

	/**
	 * Speichert eine Handwägung x ab
	 * 
	 * @param iWaegungPos
	 *            1 oder 2
	 */
	private WK_Waegung_Buchungssatz createWaegungBuchungHand(int iWaegungPos, String idUserWaegung)
			throws myException {
		// Feststellen welche Wägung durchgeführt werden soll:

		WK_Waegung_Buchungssatz oSatz = null;
		String sGewicht = null;
		String sWaegungPos = new Integer(iWaegungPos).toString();
		String sHandBeschreibung = null;

		// Bestehende Sätze
		WK_Waegung_Buchungssatz oWaegung1Satz = m_oWKHandler.get_BuchungWaegung1();
		WK_Waegung_Buchungssatz oWaegung2Satz = m_oWKHandler.get_BuchungWaegung2();

		// Wenn Handeingabe...
		switch (iWaegungPos) {
		case 1:
			if (oWaegung1Satz != null) {
				throw new myException(new MyE2_String("Wägung 1 wurde schon durchgeführt!").CTrans());
			}

			tfW1Gewicht.show_InputStatus(true);
			tfW1HandDesc.show_InputStatus(true);
			
			MyE2_MessageVector oMV1 = tfW1Gewicht.get_MV_InputOK();

			// jetzt noch prüfen, ob auch was drin steht
			if (tfW1Gewicht.getText().trim().isEmpty()){
				oMV1.add(new MyE2_Alarm_Message("Es ist kein Gewicht eingetragen."));
			}
			
			if (tfW1HandDesc.get_oTextField().getText().trim().isEmpty()){
				oMV1.add(new MyE2_Alarm_Message("Es ist keine Beschreibung der Handeingabe eingetragen."));
			}
			
			if (oMV1.get_bHasAlarms()){
				tfW1Gewicht.show_InputStatus(false);
				tfW1HandDesc.show_InputStatus(false);
			}
			
			bibMSG.add_MESSAGE(oMV1);

			if (bibMSG.get_bHasAlarms()) {
				return null;
			}

			// Gewicht holen
			sGewicht = tfW1Gewicht.getText();
			sHandBeschreibung = tfW1HandDesc.get_oTextField().getText();
			

			break;
		case 2:
			if (oWaegung2Satz != null) {
				throw new myException(new MyE2_String("Wägung 2 wurde schon durchgeführt!").CTrans());
			}
			
			// Prüfung ob schon eine Adresse eingegeben wurde: Zur schnelleren Wägung reicht es, die Adresse vor der 2. Wägung eingegeben zu haben
			// Prüfung ob schon eine Sorte eingetragen ist, sonst gibts keine Abschliessende Wägung			
			if ( bibALL.isEmpty(m_oWKHandler.get_BuchungWiegekarte().getID_ARTIKEL_BEZ() ) || 
				       ( 
						bibALL.isEmpty(m_oWKHandler.get_BuchungWiegekarte().getID_ADRESSE_LIEFERANT() ) &&
						bibALL.isEmpty(m_oWKHandler.get_BuchungWiegekarte().getADRESSE_LIEFERANT())
				       )
				){
				throw new myException(new MyE2_String("Vor der 2. Wägung muss die Adresse und die Sorte ausgewählt sein.").CTrans());
			}
			
			tfW2Gewicht.show_InputStatus(true);
			tfW2HandDesc.show_InputStatus(true);

			MyE2_MessageVector oMV2 = tfW2Gewicht.get_MV_InputOK();
			
			// jetzt noch prüfen, ob auch was drin steht
			if (tfW2Gewicht.getText().trim().isEmpty()){
				oMV2.add(new MyE2_Alarm_Message("Es ist kein Gewicht eingetragen."));
			}
			
			if (tfW2HandDesc.get_oTextField().getText().trim().isEmpty()){
				oMV2.add(new MyE2_Alarm_Message("Es ist keine Beschreibung der Handeingabe eingetragen."));
			}
			
			if (oMV2.get_bHasAlarms()){
				tfW2Gewicht.show_InputStatus(false);
				tfW2HandDesc.show_InputStatus(false);
			}
			
			bibMSG.add_MESSAGE(oMV2);

			if (bibMSG.get_bHasAlarms()) {
				return null;
			}

			sGewicht = tfW2Gewicht.getText();
			sHandBeschreibung = tfW2HandDesc.get_oTextField().getText();
			break;

		default:
			throw new myException(new MyE2_String(
					"Es wurde keine gültige Wägungsnummer erkannt!").CTrans());
		}

		String sIDWiegekarte = null;

		if (m_enumZustandMaske.equals(ENUM_ZustandMaske.NEU)) {
			sIDWiegekarte = "SEQ_WIEGEKARTE.CURRVAL";
		} else {
			sIDWiegekarte = m_IDWiegekarte;
		}

		// erzeugen der BuchungsSätze
		oSatz = new WK_Waegung_Buchungssatz("SEQ_WAEGUNG.NEXTVAL",
				sIDWiegekarte, null, "Y",
				bibALL.convertTextToBigDecimal(sGewicht), "SYSDATE",
				"TO_CHAR (SYSDATE, 'HH24:MI')", sWaegungPos, null, null, null,
				null, null, null, null, null, null, null, "kg", null, null,
				null, null, null, idUserWaegung, null,sHandBeschreibung
				);

		
		switch (iWaegungPos) {
		case 1:
			m_oWKHandler.set_BuchungWaegung1(oSatz);
			break;
		case 2:
			m_oWKHandler.set_BuchungWaegung2(oSatz);
			break;
		default:
			throw new myException(new MyE2_String(
					"Es wurde keine gültige Wägungsnummer angegeben!").CTrans());
		}

		return oSatz;
	}

	
	/**
	 * Löscht die Anzeigen aller Waagedisplays die angezeigt werden
	 */
	private void clearWaageDisplays(){
		for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
			WK_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
			oDisplay.clear();
		}	
	}
	
	
	
	
	
	/**
	 * Lesen der Temporären Wägungen zur ständigen Anzeige
	 */
	private void getTempWaegungen() {

		// die Wägungen müssen nur durchgeführt werden, wenn noch gewogen werden
		// muss...
		WK_Waegung_Buchungssatz oSatz = null;
		for (int i = 0; i < this.m_listWaageDisplays.size(); i++) {
			WK_Row_Waagedisplay oDisplay = m_listWaageDisplays.get(i);
			try {
				
				// wenn das Waagedisplay kein Gewicht mehr anfodern soll, dann einfach löschen...
				if (!oDisplay.isRequestingWeight()){
					oDisplay.clear();
					continue;
				}
				
				oSatz = readWaage(oDisplay.getWaageSettings(),	WaageHandlerBase.ENUM_Commands.CMD_READTEMP);
				
				if (oSatz != null) {
					if (oSatz.get_WaageResultStatus().equals(ENUM_WaageResultStatus.OK)){
						oDisplay.setGewicht(oSatz.getW_BRUTTO_GEWICHT());
	

						//
						// prüfen, ob die Wägung unter der mindeslast der Waage liegt. Wenn ja, keine Wägung erlauben
						//
						oDisplay.setRowEnabled(true);
						if (m_bMindestlastErforderlich && getWiegekartenTypErfodertMindestlast()) {

							RECORD_WAAGE_SETTINGS oSettings = oDisplay.getWaageSettings();
							BigDecimal bdMindestlast = oSettings.get_MINDESTLAST_bdValue(BigDecimal.ZERO);
							if (bdMindestlast.compareTo(BigDecimal.ZERO)<0){
								oDisplay.setFehler(new MyE2_String(	"Mindestlast der Waage ist nicht angegeben! Keine Wägung möglich!"));
								oDisplay.setRowEnabled(false);
								continue;
							} else if (oSatz.getGEWICHT().compareTo(bdMindestlast) < 0 ){
									oDisplay.setFehler(new MyE2_String(	"Mindestlast der Waage (" + bibALL.convertBigDecimalToString(bdMindestlast,0) + ") ist unterschritten! Keine Wägung möglich!"));
									oDisplay.setRowEnabled(false);
									continue;
								
							} else if(m_enumZustandMaske.equals(ENUM_ZustandMaske.WAEGUNG1)){
								// Falls es die Zweitwägung ist, noch prüfen, ob das Nettogewicht < Mindestlast wird
								WK_Waegung_Buchungssatz oBuchung1 = m_oWKHandler.get_BuchungWaegung1();
								if (oBuchung1 != null){
									BigDecimal bdGewicht1 = oBuchung1.getGEWICHT();
									if ( oSatz.getGEWICHT().subtract(bdGewicht1).abs().compareTo(bdMindestlast) < 0 ){
										oDisplay.setFehler(new MyE2_String(	"Nettogewicht wird kleiner als die Mindestlast! Keine Wägung möglich!"));
										oDisplay.setRowEnabled(false);
										continue;
									} 
								}
							} 
						}
						
	
						//
						// prüfen, ob bei WE die doppelte Mindestlast erreicht ist, sonst Meldung wenn gewünscht
						//
						if (m_enumZustandMaske.equals(ENUM_ZustandMaske.NEU) || m_enumZustandMaske.equals(ENUM_ZustandMaske.STAMMDATEN)){
							if (cbWarenEingang.isSelected()){
								// Alle Vorrausetzungen gegeben. Jetzt kann man prüfen, ob die Warnung gegeben werden muss.
								if (m_bWarnungDoppelteMindestlastBeiWE && getWiegekartenTypErfodertMindestlast()) {
									RECORD_WAAGE_SETTINGS oSettings = oDisplay.getWaageSettings();
									BigDecimal bdMindestlast = oSettings.get_MINDESTLAST_bdValue(BigDecimal.ZERO);
									BigDecimal bdMindestlastDoppelt = bdMindestlast.multiply(new BigDecimal(2));
									if (bdMindestlast.compareTo(BigDecimal.ZERO) > 0){
										if (oSatz.getGEWICHT().compareTo(bdMindestlastDoppelt) < 0 ){
											oDisplay.setFehler(new MyE2_String(	"ACHTUNG: Bei Differenzverwiegung muss das Eingangsgewicht mindestens die doppelte Mindestlast (" + bibALL.convertBigDecimalToString(bdMindestlast,0) + ") betragen: " ));
										} 
									}
								}
							} 
						}
						
						
						
						// prüfen, ob es die 2.Wägung ist, wenn ja prüfen, ob
						// Brutto/Tara mit WE/WA passen
						boolean bBruttoTaraOk = true;
						if (m_enumZustandMaske == ENUM_ZustandMaske.WAEGUNG1) {
							BigDecimal bdGewicht1 = m_oWKHandler.get_BuchungWaegung1().getGEWICHT();
							BigDecimal bdGewicht2 = oSatz.getGEWICHT();
	
							if (bdGewicht2 != null) {
								String sLiefer = m_oWKHandler.get_BuchungWiegekarte().getIST_LIEFERANT();
	
								if (sLiefer == null || sLiefer.equals("N")) {
									bBruttoTaraOk = bdGewicht1.compareTo(bdGewicht2) < 0;
								} else {
									bBruttoTaraOk = bdGewicht1.compareTo(bdGewicht2) > 0;
								}
							}
						}
						if (!bBruttoTaraOk) {
							oDisplay.setFehler(new MyE2_String(	"Achtung: Brutto/Tara passt nicht zur Wiegung!"));
						}
					} else {
						oDisplay.setFehler(new MyE2_String(oSatz.get_WaageFehlerBeschreibung()));
					}
				}
			} catch (myException e) {
				oDisplay.setFehler(new MyE2_String(e.ErrorMessage));
			} catch (Exception ex){
				// allgemeiner Fehler
				oDisplay.setFehler(new MyE2_String(ex.getMessage()));
			}
		}
	}

	/**
	 * führt eine Wägung durch und gibt einen initialisierten Buchungssatz zurück
	 * 
	 * UNTERSCHIED zu READTEMP: Die Wägung wird in den Speicher der Waage geschrieben, weil das Kommando ein 
	 * anderes ist.
	 * 
	 * @param RECORD_WAAGE_SETTINGS  recSettings
	 * @throws myException
	 */
	private WK_Waegung_Buchungssatz getWaegung(RECORD_WAAGE_SETTINGS recSettings) throws myException {
		// Wägung initiieren!
		WK_Waegung_Buchungssatz oSatz = null;
		try {
			oSatz = readWaage(recSettings, WaageHandlerBase.ENUM_Commands.CMD_READ);
		} catch (myException e) {
			throw new myException(e.getMessage());
		}
		return oSatz;
	}

	/**
	 * Füllt die Combobox mit dem kundenspezifischen Sorten abhängig ob EK oder
	 * VK
	 * 
	 * @throws myException
	 */
	private void refreshSorteKunden() throws myException {

		if (cbSorteHand.isSelected()) {
			cmbSorteKunde.setSelectedIndex(0);
			cmbSorteKunde.removeAll();
			setZusatzinfosSorte();
			return;
		}

		String sID = "";
		String sEKVK = (cbWarenEingang.isSelected() ? "EK" : "VK");

		if (!cbAdresseNeu.isSelected()) {
			sID = new MyLong(oSearchAdresse.get_oTextFieldForSearchInput().getText(), new Long(0), new Long(0)).get_cUF_LongString();
		}

		cmbSorteKunde.setSelectedIndex(0);
		cmbSorteKunde.SetParameter("#ID#", (!bibALL.isEmpty(sID) ? sID : "-1"));
		cmbSorteKunde.SetParameter("#EKVK#", (!bibALL.isEmpty(sEKVK) ? sEKVK : "-"));
		cmbSorteKunde.RefreshComboboxFromSQL();
		
		setZusatzinfosSorte();

	}

	/**
	 * Speichern der Maske
	 * 
	 * @throws myException
	 */
	private boolean save() throws myException {
		boolean bRet = false;
		if (bibMSG.get_bHasAlarms()) {
			return bRet;
		}

		Vector<String> vAbzuege = new Vector<String>();
		if (m_enumZustandMaske != ENUM_ZustandMaske.NEU) {
			vAbzuege.addAll( m_oAbzuegeGebinde.UB_get_vSQLStack());
			m_oAbzuegeMengen.refresh_list();
			vAbzuege.addAll( m_oAbzuegeMengen.UB_get_vSQLStack());
			
			if (m_bShowContainerverwaltung){
				vAbzuege.addAll( m_oContainer.UB_get_vSQLStack());
			}
			
		}

		
		
		
		// vor dem speichern der Maske bei der zweitwaegung muss die Prüfung der Felder aus der DB erfolgen 
		if (m_enumZustandMaske == ENUM_ZustandMaske.WAEGUNG1 || m_enumZustandMaske == ENUM_ZustandMaske.WAEGUNG2){
			bibMSG.MV().addAll( m_explicitRuleSetter.ValidateRules_OnSave());
			if (bibMSG.get_bHasAlarms()){
				return bRet;
			}
		}
		
		
		// speichern der Wiegekarte
		m_oWKHandler.set_ErlaubeMehrfachwiegungen(cbErlaubeMehrfachwiegungen.isSelected());
		
		bRet = m_oWKHandler.saveWiegekarte(null, vAbzuege);
		if ( bRet) {
			
			// wenn alles gut gegangen ist, dann auch noch die Fuhre updaten
			if (!bibMSG.get_bHasAlarms()) {
				m_bIsDirty = true;

				// die Wiegekarte merken
				m_IDWiegekarte = m_oWKHandler.get_BuchungWiegekarte().getID_WIEGEKARTE();
				
				//
				// falls die 1. Wägung zum ersten mal abgespeichert wurde, dann den Hofschein drucken
				// bei waegung 1
				//
				if (m_oWKHandler.get_BuchungWaegung1() != null && m_oWKHandler.get_REC_Waegung1() == null){
					druckeWaageHofschein();
				}
				
				// falls schon Gebinde eingetragen waren, müssen diese neu geleaden werden
				// Abzüge der Gebinde
				try {
					m_oAbzuegeGebinde.set_UB_MOTHER_ID(m_IDWiegekarte);
					m_oAbzuegeGebinde.UB_PopulateDaughterWithMother_4_EDIT();
				} catch (Exception e) {
					// die Mutterid war noch nicht gesetzt;
				}
				try {
					m_oAbzuegeMengen.set_UB_MOTHER_ID(m_IDWiegekarte);
					m_oAbzuegeMengen.UB_PopulateDaughterWithMother_4_EDIT();
				} catch (Exception e) {
					// die Mutterid war noch nicht gesetzt;
				}
				//
				// Containerverwaltung
				//
				if (m_bShowContainerverwaltung){
					try {
						m_oContainer.set_UB_MOTHER_ID(m_IDWiegekarte);
						m_oContainer.UB_PopulateDaughterWithMother_4_EDIT();
					} catch (Exception e) {
						// 
					}
				}
		
				
				//
				// Bei jeder Wägung muss einmal geprüft werden, ob eine automatische Kamera-Aufnahme durchgeführt werden soll.
				//
				// Zweitwägung
				if (m_oWKHandler.get_BuchungWaegung2() != null && m_oWKHandler.get_REC_Waegung2() == null){
					m_oWKHandler.captureImages(m_oWKHandler.get_BuchungWaegung2());
				}
				// Erstwägung
				if (m_oWKHandler.get_BuchungWaegung1() != null && m_oWKHandler.get_REC_Waegung1() == null){
					m_oWKHandler.captureImages(m_oWKHandler.get_BuchungWaegung1());
				}
				
				
			}
		}

		// versuchen in der Navigationlist den Datensatz zu kennzeichnen
		if (m_navigationList != null) {
			m_navigationList.RefreshList();
			m_navigationList.Mark_ID_IF_IN_Page(m_IDWiegekarte);
		}

		return bRet;
	}


	
	/**
	 * prüft und druckt den Waage-Hofschein
	 * @author manfred
	 * @date   06.11.2013
	 */
	private void druckeWaageHofschein(){
		DEBUG.System_println("PRÜFE Hofschein drucken...", DEBUG.DEBUG_FLAG_DIVERS1);
		
		if (bibALL.isEmpty( m_IDWiegekarte) ){
			DEBUG.System_println("Hofschein drucken: keine ID_WIEGEKARTE", DEBUG.DEBUG_FLAG_DIVERS1);
			return;
		}

		Boolean bIstLieferant = null; 
		boolean bDruckeWE = bib_Settigs_Mandant.IS__Value("WAAGE_HOFSCHEIN_DRUCKEN_WE", "N","N");
		boolean bDruckeWA = bib_Settigs_Mandant.IS__Value("WAAGE_HOFSCHEIN_DRUCKEN_WA", "N","N");
		boolean bDrucke = false;
		
		if ( m_IDWiegekarte != null){
			// ist_lieferant ist "Y" oder "N" bzw. null
			bIstLieferant = "Y".equalsIgnoreCase( m_oWKHandler.get_BuchungWiegekarte().getIST_LIEFERANT() );
			
			// festlegen ob gedruckt werden soll
			bDrucke = (bDruckeWE && bIstLieferant);
			bDrucke |= (bDruckeWA && !bIstLieferant);

			
		} else {
			DEBUG.System_println("Hofschein drucken: kein Wiegekarten-Buchungssatz", DEBUG.DEBUG_FLAG_DIVERS1);
		}
		
		if (bDrucke){
			DEBUG.System_println("Hofschein drucken: OK, drucken...", DEBUG.DEBUG_FLAG_DIVERS1);

			m_WK_PrintHandler = new WK_Print_Handler(m_IDWiegekarte);
			m_WK_PrintHandler.setExtraHashValue(bIstLieferant ? "WE" : "WA");
			
			
			String sIDWaageStandort;
			String sIDAdresseKunde;
			String sIDAdresseLager;
			String sIDAdresseSpedition;
			String sIDADresseAbnStrecke;
			try {
				sIDWaageStandort = m_oWKHandler.m_REC_Wiegekarte.get_ID_WAAGE_STANDORT_cUF_NN("");
				sIDAdresseKunde = m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_LIEFERANT_cUF_NN("");
				sIDAdresseLager = m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_LAGER_cUF_NN("");
				sIDAdresseSpedition = m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_SPEDITION_cUF_NN("");
				sIDADresseAbnStrecke = m_oWKHandler.m_REC_Wiegekarte.get_ID_ADRESSE_ABN_STRECKE_cUF_NN("");
				
			} catch (myException e1) {
				sIDWaageStandort = "";
				sIDAdresseKunde = "";
				sIDAdresseLager = "";
				sIDAdresseSpedition = "";
				sIDADresseAbnStrecke = "";
			}
			
			m_WK_PrintHandler.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_WAAGE_STANDORT.Name(), sIDWaageStandort);
			m_WK_PrintHandler.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_KUNDE.Name(), sIDAdresseKunde);
			m_WK_PrintHandler.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_LAGER.Name(), sIDAdresseLager);
			m_WK_PrintHandler.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_SPEDITION.Name(), sIDAdresseSpedition);
			m_WK_PrintHandler.addAdditionalParameter(WK_Print_Handler.ENUM_ADDITIONAL_VALUES.ID_ADRESSE_ABN_STRECKE.Name(), sIDADresseAbnStrecke);
			
			
			m_WK_PrintHandler.set_PrintHofschein(true);
			try {
				m_WK_PrintHandler.Print();
			} catch (myException e) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Fehler: Der Hofschein konnte nicht gedruckt werden.")));
			}
		} else {
			DEBUG.System_println("Hofschein NICHT drucken!", DEBUG.DEBUG_FLAG_DIVERS1);
		}
	}

	
	
	
	/**
	 * Initialisieren einer Folgewägung
	 * 
	 * @throws myException
	 */
	private void createFolgeWaegung() throws myException {

		// zuerst die aktuelle Maske speichern
		pruefeKennzeichen();
		createOrUpdateWiegekarteBuchung();
		if (!this.save()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Speichern der Wiegekarte"));
			return;
		}

		// prüfen, ob die Wiegekarte schon abgeschlossen ist, denn nur dann kann
		// eine Folgewägung stattfinden
		String sIDWaageSettings = null;
		String sIDUser = null;
		RECORD_WAAGE_SETTINGS oSettings = null;

		// wenn möglich die 1. Wägung automatisch ausführen ...
		if (m_oWKHandler.get_REC_Waegung2() != null) {

			// zuerst die zweitwägung prüfen...
			sIDWaageSettings = m_oWKHandler.get_REC_Waegung2()
					.get_ID_WAAGE_SETTINGS_cUF();

			if (sIDWaageSettings != null) {
				sIDUser = m_oWKHandler.get_REC_Waegung2()
						.get_ID_USER_WAEGUNG_cUF();
				oSettings = new RECORD_WAAGE_SETTINGS(sIDWaageSettings);
			} else {
				// dann die Erstwägung
				sIDWaageSettings = m_oWKHandler.get_REC_Waegung1().get_ID_WAAGE_SETTINGS_cUF();
				if (sIDWaageSettings != null) {
					sIDUser = m_oWKHandler.get_REC_Waegung1().get_ID_USER_WAEGUNG_cUF();
					
					oSettings = new RECORD_WAAGE_SETTINGS(sIDWaageSettings);
				}
			}
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Eine Folgewägung kann nur stattfinden, wenn die Zweitwägung durchgeführt wurde!"));
			return;
		}

		// die bestehenden IDs merken:
		String idWiegekarte_old = m_oWKHandler.get_ID_Wiegekarte();
		String idWiegekarteParent_old = m_oWKHandler.get_REC_Wiegekarte().get_ID_WIEGEKARTE_PARENT_cUF();
		String sIDWiegekarteParent = idWiegekarteParent_old != null ? idWiegekarteParent_old: idWiegekarte_old;

		// bei einer Folgeverwiegung muss der Wiegekarten-Typ, wenn er Dokumentarisch war, auf Wiegekarte gesetzt werden
		
		
		//
		// jetzt die wiegekarte kopieren
		if (m_oWKHandler.saveWiegekarteAsCopy(true, true)) {
			String sIDWiegekarteCopy = m_oWKHandler.get_ID_Wiegekarte();

			// die aktuelle Wiegekarte setzen
			this.m_IDWiegekarte = sIDWiegekarteCopy;

			// oThis.exit();
			initData();

			// Status der Datenfelder
			setFieldStatus();

			//
			// Wenn die Folgewägung automatisch erzeugt werden soll, dann muss der Schalter 
			// auf true gesetzt werden
			//
			if (m_bLeseGewichtNachFolgewaegung){
				if (oSettings != null) {
					
					// dann die 1. Wägung
					WK_Waegung_Buchungssatz  oSatz = createWaegungBuchung(oSettings, sIDUser);
					if (oSatz.get_WaageResultStatus().equals(ENUM_WaageResultStatus.OK)){
						boolean bSaveOK = save();
						
						// wenn das Speichern geklappt hat, dann beenden des Dialogs
						if (bSaveOK) {
							// oThis.exit();
							initData();
							// Status der Datenfelder
							setFieldStatus();
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der automatischen Generierung der Folgewägung."));
						}
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oSatz.get_WaageFehlerBeschreibung()));
					}
					
				} else {
					
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Zweitwägung war Handeingabe. Deshalb keine automatisch Wägung möglich."));
				}
			}
		}
	}

	
	/**
	 * gibt die Anzahl der Etiketten zurück, die in das Feld eingetragen sind
	 * 
	 * @return
	 */
	public Integer getAnzahlEtiketten() throws myException {
		Integer nRet = null;
		if (!bibALL.isEmpty(tfAnzahlEtiketten.getText())) {
			try {
				nRet = new Integer(tfAnzahlEtiketten.getText());
			} catch (NumberFormatException e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(
						"Es muss eine gültige Zahl in das Anzahlfeld eingetragen werden!"));
				nRet = null;
			}
		}
		return nRet;
	}

	
	/**
	 * Methode prüft, ob der gewählte Wiegekarten-Typ die Mindestlast-Bedingungen erfordert die in der Waage angegeben sind
	 * Aktuell sind alle Wiegekarten-Typen an die Mindestlast gebunden, ausser der Dokumentarischen Verwiegung
	 * @author manfred
	 * @date   11.11.2015
	 *
	 * @return
	 */
	private boolean getWiegekartenTypErfodertMindestlast(){
		if (cbWiegekarte_Dokumentarisch.isSelected()){
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * Beenden des Wiegekarten-Dialogs
	 * 
	 * @throws myException
	 */
	private void exit() throws myException {
		if (m_ServerPushTask != null){
			m_ServerPushTask.stop();
			m_ServerPushTask = null;
		}
		
		CLOSE_AND_DESTROY_POPUPWINDOW(true);
	}

	
	
	@Override
	public void dispose() {
		if (m_ServerPushTask != null) {
			m_ServerPushTask.stop();
		}

		// TODO Auto-generated method stub
		super.dispose();
	}
	
	

	private class actionButtonFuhrenDatenLaden extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;

			WK_RECORD_FuhrenInfo oFI = oThis.ladeFuhrenDaten();

			if (bibMSG.get_bIsOK() && oFI != null) {
				oThis.setzeFuhrendatenInMaske(oFI);
			}
		}
	}

	
	
	private class actionCMBSorte extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			
			oThis.setZusatzinfosSorte();
		}
	}

	
	
	/**
	 * ActionEvent für das aktivieren der Handauswahl Sorte
	 * 
	 * @author manfred
	 * 
	 */
	private class actionCBSorteHand extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.setStatusSorteneingabe();
		}
	}

	/**
	 * ActionEvent für das neuladen der Kundensorten
	 * 
	 * @author manfred
	 * 
	 */
	private class actionRefreshKundenSorte extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.refreshSorteKunden();
		}
	}

	/**
	 * ActionEvent für das neuladen der Kundensorten
	 * 
	 * @author manfred
	 * 
	 */
	private class actionCB_WE_WA extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.setStatusWE_WA();
		}
	}

	/**
	 * Lokaler Eventhandler Handeingabe Adresse
	 * 
	 * @author manfred
	 * 
	 */
	private class actionCBAdresseNeu extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.setAktiveAdresse(oThis.cbAdresseNeu, oThis.tfAdresse,
					oThis.oSearchAdresse, !oThis.cbAdresseNeu.isSelected());
		}
	}

	/**
	 * Lokaler Eventhandler Handeingabe Speditionsadresse
	 * NEU: 2011-06-03: Speditionsadressenzusatz additiv zur Spedition, nicht als Ersatz.
	 * 
	 * @author manfred
	 * 
	 */
	private class actionCBAdresseSpeditionNeu extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			if (oThis.cbAdresseSpeditonNeu.isSelected()){
				oThis.tfAdresseSpedion.set_bEnabled_For_Edit(true);
			} else {
				oThis.tfAdresseSpedion.set_bEnabled_For_Edit(false);
				oThis.tfAdresseSpedion.setText(null);
			}
//			oThis.setAktiveAdresse(oThis.cbAdresseSpeditonNeu,	oThis.tfAdresseSpedion, oThis.oSearchAdresseSpedition,!oThis.cbAdresseSpeditonNeu.isSelected());
		}
	}

	
	/**
	 * gibt die aktuelle ID des angegebenen Benutzers zurück.
	 * @return
	 */
	private int getIDUserWaegung(){
		int idRet = 0;
		
		try {
			String sRet = m_selWaageBediener.get_ActualWert();
			idRet = Integer.parseInt(sRet);
		} catch (Exception e) {
			idRet = -1;
		}
		
		return idRet;
	}
	

	
	/**
	 * Eventhändler für die Checkbox zum aktivieren der Handauswahl der 1.Wägung
	 * 
	 * @author manfred
	 */
	private class actionCBWaegung1Hand extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;

			oThis.pruefeKennzeichen();

			oThis.tfW1Gewicht.setText(null);
			oThis.tfW1HandDesc.get_oTextField().setText(null);
			oThis.setFieldStatus();

			if (oThis.cbW1Hand.isSelected()) {
				ApplicationInstance.getActive().setFocusedComponent(
						(Component) oThis.tfW1Gewicht);
			}
		}
	}

	
	
	/**
	 * Eventhändler für die Checkbox zum aktivieren der Handauswahl der 2.Wägung
	 * 
	 * @author manfred
	 */
	private class actionCBWaegung2Hand extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.pruefeKennzeichen();

			oThis.tfW2Gewicht.setText(null);
			oThis.setFieldStatus();

			if (oThis.cbW2Hand.isSelected()) {
				ApplicationInstance.getActive().setFocusedComponent(
						(Component) oThis.tfW2Gewicht);
			}
		}

	}

	
	/**
	 * Eventhandler für die Handwägung der 1. Wägung
	 * 
	 * @author manfred
	 * 
	 */
	private class ActionSaveWaegungHand extends XX_ActionAgent {

		private int m_NumHand = 0;
		
		/**
		 * Initialisiert die Handwägung
		 * Nur die Parameterwerte 1 und 2 sind zulässig
		 * @param NumHandWaegung  : 1,2 sind zulässig
		 */
		public ActionSaveWaegungHand(int NumHandWaegung) {
			m_NumHand = NumHandWaegung;
			if (m_NumHand < 1) m_NumHand = 1;
			if (m_NumHand > 2) m_NumHand = 2;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.pruefeKennzeichen();

			if (oThis.createOrUpdateWiegekarteBuchung()) {

				// Wiegekarte speichern
				boolean bSaveOK = oThis.save();
				if (!bSaveOK || bibMSG.get_bHasAlarms()) {
					return;
				}

				
				// auch bei der Handbedienung muss ein Waagebediner ausgewählt werden
				int idUserWaegung = oThis.getIDUserWaegung();
				if (idUserWaegung <= 0 ) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Waage-Bediener ausgewählt werden!") ) ) ;
					return;
				}
				
				WK_Waegung_Buchungssatz oSatz =  oThis.createWaegungBuchungHand(m_NumHand, Integer.toString(idUserWaegung) );
				if (oThis.save()) {
					// oThis.exit();
					initData();
					// Status der Datenfelder
					setFieldStatus();
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Handwägung konnte nicht gespeichert werden.")));
				}
			}
		}
	}

	
	
	
	
	/**
	 * Speichern der Wägung in der Liste der verschiedenen Waagen
	 * Jede Zeile in der Waage-anzeige bekommt einen eigenen Eventhandler
	 * 
	 * @author manfred
	 * 
	 */
	private class actionSaveWaegung extends XX_ActionAgent {

		private RECORD_WAAGE_SETTINGS m_recSettings = null;
		private WK_Row_Waagedisplay m_oRowWaage = null;

		public actionSaveWaegung(WK_Row_Waagedisplay oRow) {
			super();
			m_oRowWaage = oRow;
			m_recSettings = oRow.getWaageSettings();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			boolean bSaveOK = false;
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;

			oThis.pruefeKennzeichen();
			
			
			int idUserWaegung = oThis.getIDUserWaegung();
			if (idUserWaegung <= 0 ) {
				m_oRowWaage.setFehler(new MyE2_String("Es muss ein Waage-Bediener ausgewählt werden!"));
				return;
			}

			
			try {
				if (oThis.createOrUpdateWiegekarteBuchung()) {
					// Wiegekarte speichern
					bSaveOK = oThis.save();
					if (!bSaveOK || bibMSG.get_bHasAlarms()) {
						return;
					}
					
					// danach Wägung speichern...
					WK_Waegung_Buchungssatz oSatz = oThis.createWaegungBuchung(m_recSettings, new Integer(idUserWaegung).toString());
					if (oSatz.get_WaageResultStatus().equals(ENUM_WaageResultStatus.OK)){
						oThis.save();
					} else {
						bSaveOK = false;
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oSatz.get_WaageFehlerBeschreibung()));
					}
				}
			} finally {
				if (bSaveOK){
					// oThis.exit();
					oThis.initData();
					// Status der Datenfelder
					setFieldStatus();
				}
			}
		}
	}

	
	/**
	 * Eventhandler zum Speichern der Wiegekarte-Daten
	 * 
	 * @author manfred
	 *
	 */
	private class actionSaveWiegekarte extends XX_ActionAgent {

		boolean m_bExit = true;

		public actionSaveWiegekarte(boolean bExit) {
			m_bExit = bExit;
		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.pruefeKennzeichen();
			
			

			oThis.createOrUpdateWiegekarteBuchung();
			boolean bOk = oThis.save();

			if (bOk) {
				if (m_bExit) {
					oThis.exit();
				} else {
					// oThis.exit();
					oThis.initData();
					// Status der Datenfelder
					setFieldStatus();
				}

			}
		}
	}


	
	/**
	 * ActionAgent zum aktualisieren der Fuhren-Daten in der wiegekarte und der
	 * Fuhre
	 * 
	 * @author manfred
	 * 
	 */
	private class actionSetWiegekartenIDundGewichtInFuhre extends
			XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.m_oWKHandler.setWiegekartenInFuhre();
			
			initData();
			setFieldStatus();

			oThis.m_navigationList.RefreshList();
		}
	}

	
	/**
	 * ActionAgent zum aktualisieren der Fuhren-Daten in der wiegekarte und der
	 * Fuhre
	 * 
	 * @author manfred
	 * 
	 */
	private class actionDeleteFuhrenID extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			
			if ( oThis.m_oWKHandler.get_BuchungWiegekarte() != null && 
				 oThis.m_oWKHandler.get_BuchungWiegekarte().getID_VPOS_TPA_FUHRE() != null){
			
				oThis.tfFuhre.setText("");
				oThis.tfFuhreOrt.setText("");
				oThis.oSearchFuhre.clean();
				
				oThis.createOrUpdateWiegekarteBuchung();
				oThis.m_oWKHandler.saveWiegekarte();
				
				initData();
				setFieldStatus();

				oThis.m_navigationList.RefreshList();
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Fuhre wurde von dieser Wiegekarte entkoppelt."));
			}
		}
	}

	
	
	/**
	 * Eventhandler für die Erfassung der Folgewägung
	 * 
	 * @author manfred
	 * 
	 */
	private class actionGenerateFolgewaegung extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.createFolgeWaegung();
		}
	}

	
	
	/**
	 * Eventhandler für das setzen der Spedition
	 * 
	 * @author manfred
	 * 
	 */
	private class actionSetSpedition extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.setAdresseInSearchfield(oSearchAdresseSpedition,
					bibALL.get_ID_ADRESS_MANDANT());
		}
	}

	
	/**
	 * ActionAgent zum aktualisieren der Fuhren-Daten in der wiegekarte und der
	 * Fuhre
	 * 
	 * @author manfred
	 * 
	 */
	private class actionReloadWiegekartenData extends	XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			oThis.initData();
			oThis.setFieldStatus();
			oThis.m_navigationList.RefreshList();
		}
	}

	
	/**
	 * Action Agent der an der Selectbox für die Auswahl des Waagebedieners angehängt ist.
	 * 
	 * @author manfred
	 * 
	 */
	private class actionSelectWaageBediener extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			String id = oThis.m_selWaageBediener.get_ActualWert();
			if (m_ServerPushTask != null){
				if (bibALL.isEmpty(id)){
					oThis.m_ServerPushTask.stop();
					oThis.clearWaageDisplays();
					
				} else {
					
					if (oThis.m_listWaageDisplays.size() > 0
							&& oThis.m_enumZustandMaske != ENUM_ZustandMaske.WAEGUNG2
							&& oThis.m_enumZustandMaske != ENUM_ZustandMaske.GEDRUCKT) {

						// dann den Pushdienst starten...
						oThis.getTempWaegungen();
						oThis.m_ServerPushTask.start();
					}

				}
			}
		}
	}

	
	
	
	private class actionDisplayBarcodePopup extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;

			oThis.m_barcodePopup = new Barcode_PopupContainer("Barcode erfassen", "Barcode scannen...");
			oThis.m_barcodePopup.setActionAgentOK(new actionAfterReadBarcode());
			oThis.m_barcodePopup.addPrefix("FU"); // FUHRE
			oThis.m_barcodePopup.addPrefix("BF"); // BUCHUNGSNUMMER_FUHRE
			oThis.m_barcodePopup.ShowPopup();
		}
	}

	
	private class actionAfterReadBarcode extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// daten einsammeln und validieren
			WK_Erfassung_Waegung oThis = WK_Erfassung_Waegung.this;
			String sFuhre = m_barcodePopup.getScanValue();

			oSearchFuhre.get_oTextFieldForSearchInput().setText(sFuhre);
			oSearchFuhre.get_buttonStartSearch().doActionPassiv();

		}
	}

}
