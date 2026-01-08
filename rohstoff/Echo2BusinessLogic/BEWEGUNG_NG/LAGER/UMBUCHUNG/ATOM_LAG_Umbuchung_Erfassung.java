package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.UMBUCHUNG;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.event.ActionEvent;
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
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField_With_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_DATE;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_Float;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.BL_BEWEGUNG_HANDLER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.JtBewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.JtBewegung_Atom;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.JtBewegung_Station;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.JtBewegung_Vektor;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.JtBewegung_Vektor_Pos;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.bibDate;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.REC_Bewegung_Atom_Kosten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_SaldoDaten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler_KALKULATORISCH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG.ATOM_LAG_BEW_DataRowStatus;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG.ATOM_LAG_BEW_StatusErmittlung_Ext;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG.ATOM_LAG_Mengenermittlung_ext;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_DBSelectField_Factory_ForLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_UB_MaskSearchField_Sorte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

/*
 * 
 * 
 */
public class ATOM_LAG_Umbuchung_Erfassung extends Project_BasicModuleContainer
{
	private static final long serialVersionUID = 7996861209234530484L;
	
	// Parameter-Werte
	// wenn aufgerufen aus MaskeLager-Bestand
	private E2_NavigationList				m_NavListRecordLagerKonto 	= null;
	
	// Wenn aufgerufen aus Lager-Inventur
	private String 							m_IDSorte 			= null;
	private String 							m_IDLager		 	= null;
	private BigDecimal						m_MengeKorrektur	= null;
	private Date							m_DatumKorrektur	= null;
	private E2_NavigationList				m_NavListToRefresh	= null;
	
	 
	
	// Variablen
	private EnumLAGUmbuchungDisplayOption 	m_enumDisplayOption = EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH;
	private boolean						m_bIsDirty			= false;

    // Korrekturbuchungsbereich oder Sortenquelle
	private UTIL_UB_MaskSearchField_Sorte 			oSearchSorteQuelle = null;
	private UTIL_DBSelectField_Factory_ForLager 	oFactoryForSelectFieldQuelle = null;
	private MyE2_SelectField						oSelectFieldLagerQuelle 	= null;
	
	// Saldo und Preise absolut
	private UB_TextField					tfSaldo					= null;
	private UB_TextField					tfSaldo_AvgPreis  		= null;
	private UB_TextField					tfSaldo_Kostenanteil	= null;
	private MyE2_Label 						lblSaldo_EinheitPreis 	= null;
	private MyE2_Label 						lblSaldo_EinheitMenge	= null;

	// Saldo und Preise zum Buchungstag
	private UB_TextField					tfSaldo_Buchungsdatum				= null;
	private UB_TextField					tfSaldo_Buchungsdatum_AvgPreis  	= null;
	private UB_TextField					tfSaldo_Buchungsdatum_Kostenanteil	= null;
	private MyE2_Label 						lblSaldo_Buchungsdatum_EinheitPreis	= null;
	private MyE2_Label 						lblSaldo_Buchungsdatum_EinheitMenge	= null;
	
	
	// aktualisierung des Setzkastens
	private MyE2_Button						oButtonAktualisiereSetzkasten = null;
	
	
	
	
	
	
	// für die prozentuale Umbuchung mit Preisanpassung
	private MyE2_CheckBox					cbPreisanpassung = null;
	private UB_TextField					tfMengeAbbuchung   = null;
	private UB_TextField					tfKostenanteil_Abbuchung = null;
	private UB_TextField					tfPreis_Abbuchung   = null;
	private MyE2_CheckBox					cbProzentual	 = null;
	private UB_TextField					tfProzent 		 = null;
	private MyE2_Button  					oButtonRecalcPct = null;
		
		
	// für die Umbuchung zwischen Einheiten:
	private MyE2_CheckBox					cbEinheitenUmbuchung 				= null;
	private UB_TextField					tfEinheitenumbuchung_MengeAbbuchung = null;
	private UB_TextField					tfEinheitenumbuchung_FaktorMenge   	= null;
	private UB_TextField					tfEinheitenumbuchung_FaktorPreis	= null;
	private MyE2_Label 						lblEinheitenumbuchung_Einheit	= null;	
	private MyE2_Button  					oButtonRecalcEinheitenumbuchung 	= null;
	
	
	// Bei Korrekturbuchung: Menge und Preise bei Ab- oder Zubuchung
	// Bei Umbuchung:        Menge und Preise der Ab- und Zubuchung
	private UB_TextField 		  			tfMengeZiel 		= null;
	private UB_TextField					tfPreisZiel			= null;
	private UB_TextField					tfPreisZielKosten   = null;
	private UB_TextArea						tfBemerkung			= null;
	// explizite unterscheidung, ob eine Korrekturbuchung eine Zu- oder Abbuchung ist
	// damit alle Gewichtswerte positiv sein können
	private MyE2_CheckBox					cbLagerZubuchung = null;
	private MyE2_CheckBox					cbLagerAbbuchung = null;
	// Buchungsdatum
	private UB_TextField_With_DatePOPUP_OWN  tfDatum = null;
	private MyE2_Button  					oButtonSaveBuchung 	= null;

	
	private ActionAgent_RadioFunction_CheckBoxList agentWatchdogZUAB = null;
		
	private MyE2_Button						oButtonRefreshSaldo = null; 
	private MyE2_Button						oButtonRefreshLagerPreis = null;
	private VECTOR_UB_FIELDS  				vUnboundFields 		= new VECTOR_UB_FIELDS();
	
	
	// Objekte für die Splitbuchung
	private UTIL_DBSelectField_Factory_ForLager 	oFactoryForSelectFieldZiel 	= null;
	private MyE2_SelectField						oSelectFieldLagerZiel 		= null;	
	private boolean								m_bZiellagerIsDirty 		= false;  // wird true, wenn das Ziellager selektiert wurde
	private UTIL_UB_MaskSearchField_Sorte  			oSearchSorteZiel			= null;
	
	
	// Labels für die Einheiten von Mengen und Preisen
	MyE2_Label 								lblEinheitPreis 					= null;
	MyE2_Label 								lblEinheitMenge						= null;

	
	
	


	
	// Umschalter Sortenumbuchung
	MyE2_Label 								lblGridZielsorte = null;
	MyE2_Grid 								oGridZielsorte = null;
	MyE2_CheckBox							cbSortenumbuchung = null;
	MyE2_Label 								lblHeading = null;
	MyE2_Label 								lblHeadingSorte = null;
	MyE2_Label 								lblHeadingFunction = null;
	
	

	
	
	// Vector für die Objekte der Umbuchung
	private Vector<Component>				vComponentsUmbuchung = null;
	private Vector<Component>				vComponentsKorrekturbuchung = null;
	
	
	// Werte des aktuellen Quell-Lagers
	BigDecimal								m_bdSaldo_Aktuell = null;
	BigDecimal								m_bdPreis_Aktuell = null;
	BigDecimal								m_bdKosten_aktuell = null;
	BigDecimal 								m_bdMengeSetzkasten_aktuell 	= null;

	
	// Werte des Quell-Lagers zum Buchugnszeitpunkt
	BigDecimal								m_bdSaldo_Buchungsdatum = null;
	cPreis  								m_oPreis_Buchungsdatum = null;
	
	
	private boolean						m_bKostenberuecksichtigung = true;
	
	
	/**
	 * Erfassungsmaske wird initialisiert für die Verwendung mit der Navigationsliste
	 * @param navigationlist
	 * @param displayOption
	 * @throws myException
	 */
	public ATOM_LAG_Umbuchung_Erfassung(E2_NavigationList navigationlist, EnumLAGUmbuchungDisplayOption displayOption) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGER_ERFASSUNG);
		
		this.m_enumDisplayOption = displayOption;
		this.m_NavListRecordLagerKonto = navigationlist;
		
		this.initMask();
		
		this.initData();
		
	}

	/**
	 * Erfassungsmaske wird initialisiert für die verwendung einer direkten Buchung, ohne NavigationList
	 * Es ist immer eine einfache Korrekturbuchung
	 * @param IdLager
	 * @param IdSorte
	 * @param bdMenge
	 * @param dtBuchung
	 * @throws myException
	 */
	public ATOM_LAG_Umbuchung_Erfassung(String IdLager, String IdSorte, BigDecimal bdMenge, Date dtBuchung, E2_NavigationList navListToRefresh ) throws myException
	{
		this(IdLager,IdSorte,bdMenge,dtBuchung,navListToRefresh,true);
	}
	
	
	/**
	 * Erfassungsmaske wird initialisiert für die verwendung einer direkten Buchung, ohne NavigationList
	 * Es kann übergeben werden, ob es eine Korrekturbuchung gibt oder eine Umbuchung
	 * @param IdLager
	 * @param IdSorte
	 * @param bdMenge
	 * @param dtBuchung
	 * @param bKorrekturbuchung: true=Korrekturbuchung false=Umbuchung
	 * @throws myException
	 */
	public ATOM_LAG_Umbuchung_Erfassung(String IdLager, String IdSorte, BigDecimal bdMenge, Date dtBuchung, E2_NavigationList navListToRefresh, boolean bKorrekturbuchung ) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGER_ERFASSUNG);
		
		if (bKorrekturbuchung){
			this.m_enumDisplayOption = EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH;
		} else {
			this.m_enumDisplayOption = EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT;
		}
		
		this.m_NavListRecordLagerKonto = null;
		this.m_NavListToRefresh = navListToRefresh;
		this.m_IDLager = IdLager;
		this.m_IDSorte = IdSorte;
		this.m_MengeKorrektur = bdMenge;
		this.m_DatumKorrektur = dtBuchung;
		
		this.initMask();

		this.initData();
		
	}


	

	private void initMask() throws myException {

		E2_MutableStyle oStyleCheckboxNoBorder = new E2_MutableStyle();
		oStyleCheckboxNoBorder.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));

		MyE2_String oToolTipText = null;

		/**
		 * Umbuchung oder Korrekturbuchung 
		 */
		cbSortenumbuchung = new MyE2_CheckBox(new MyString("Sorten-Umbuchung"), oStyleCheckboxNoBorder);
		if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT)){
			cbSortenumbuchung.setSelected(true);
		}
		cbSortenumbuchung.add_oActionAgent(new actionChangeBuchungsTyp());
		
		
		/**
		 * Ausgangsseite der Verbuchung (bei Korrekturbuchung die einzige Lagerangabe)
		 */
		oFactoryForSelectFieldQuelle 	= new UTIL_DBSelectField_Factory_ForLager();
		oSelectFieldLagerQuelle 		= oFactoryForSelectFieldQuelle.getSelectField(cbSortenumbuchung.isSelected() ? false : true);
		
		oSelectFieldLagerQuelle.add_oActionAgent(new actionAfterChangeOfSelectionQuellLager());
		
		oSearchSorteQuelle 	= new UTIL_UB_MaskSearchField_Sorte("ID_ARTIKEL",true);
		
		oSearchSorteQuelle.set_oMaskActionAfterMaskValueIsFound(new actionAfterFoundSorte());
		oSearchSorteQuelle.get_oTextForAnzeige().setWidth(new Extent(300));

		tfSaldo				= new UB_TextField("MENGE_SALDO",false,"",120,15);
		tfSaldo.set_bEnabled_For_Edit(false);

		tfSaldo_AvgPreis     = new UB_TextField("LAGERPREIS_AVG", false, "", 80,15);
		tfSaldo_AvgPreis.set_bEnabled_For_Edit(false);

		tfSaldo_Kostenanteil     = new UB_TextField("LAGERPREIS_KOSTEN", false, "", 80,15);
		tfSaldo_Kostenanteil.set_bEnabled_For_Edit(false);
		
		tfSaldo_Buchungsdatum		= new UB_TextField("MENGE_SALDO_BUCHUNGSDATUM",false,"",120,15);
		tfSaldo_Buchungsdatum.set_bEnabled_For_Edit(false);

		tfSaldo_Buchungsdatum_AvgPreis     = new UB_TextField("LAGERPREIS_AVG_BUCHUNGSDATUM", false, "", 80,15);
		tfSaldo_Buchungsdatum_AvgPreis.set_bEnabled_For_Edit(false);
		
		tfSaldo_Buchungsdatum_Kostenanteil		= new UB_TextField("LAGERPREIS_KOSTEN_BUCHUNGSDATUM", false, "", 80,15);
		tfSaldo_Buchungsdatum_Kostenanteil.set_bEnabled_For_Edit(false);
		
		
		lblSaldo_EinheitMenge = new MyE2_Label("");
		lblSaldo_EinheitPreis = new MyE2_Label("");
		lblSaldo_Buchungsdatum_EinheitPreis = new MyE2_Label("");
		lblSaldo_Buchungsdatum_EinheitMenge = new MyE2_Label("");

		oButtonRefreshSaldo = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png")); 
//		oButtonRefreshSaldo.add_oActionAgent(new actionRefreshSaldo());
		oButtonRefreshSaldo.add_oActionAgent(new actionRefreshLagerPreis());
		oToolTipText = new MyE2_String("Lagersaldo aktualisieren. Wenn ein Buchungsdatum angegeben ist, dann wird der Saldo zum angegebenen Buchungsdatum ermittelt. Sonst der Gesamtsaldo.");
		oButtonRefreshSaldo.setToolTipText(oToolTipText.toString());
		
		// Setzkasten neu verbuchen
		oButtonAktualisiereSetzkasten = new MyE2_Button("Setzkasten aktualisieren",E2_ResourceIcon.get_RI("warnschild_22.png"),E2_ResourceIcon.get_RI("warnschild_22__.png")); 
		oButtonAktualisiereSetzkasten.add_oActionAgent(new actionSetzkastenAktualisieren());
		oToolTipText = new MyE2_String("Aktualisiert den Seztkasten für das gewählte Lager und die gewählte Sorte.");
		oButtonAktualisiereSetzkasten.setToolTipText(oToolTipText.toString());
		
		
		/**
		 * Zielseite der Verbuchung (nur bei Umbuchung)
		 */
		// Ziellager
		oFactoryForSelectFieldZiel = new UTIL_DBSelectField_Factory_ForLager();
		oSelectFieldLagerZiel = oFactoryForSelectFieldZiel.getSelectField(cbSortenumbuchung.isSelected() ? false : true);
		
		oSelectFieldLagerZiel.add_oActionAgent(new actionAfterChangeOfSelectionZielLager());
		if (!bIsLagerumbuchungErlaubt()){
			oSelectFieldLagerZiel.set_bEnabled_For_Edit(false);
		}
		
		
		// Sortenangabe der Zielsorte
		oSearchSorteZiel	= new UTIL_UB_MaskSearchField_Sorte("ID_ARTIKEL",true);
		
		oSearchSorteZiel.set_oMaskActionAfterMaskValueIsFound(new actionAfterFoundSorteZiel());
		oSearchSorteZiel.get_oTextForAnzeige().setWidth(new Extent(300));
		
		// prozentuale Umrechung
		tfPreis_Abbuchung		= new UB_TextField("PREIS_ABBUCHUNG", true, "",80,15);
		tfKostenanteil_Abbuchung= new UB_TextField("PREIS_ABBUCHUNG_KOSTEN",true,"",80,15);
		tfMengeAbbuchung 		= new UB_TextField("MENGE_ABBUCHUNG",true,"",80,15);
		tfProzent			= new UB_TextField("PROZENT",true,"",50,3);
		oButtonRecalcPct	= new MyE2_Button(new MyString("Menge/Preis berechnen"), E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png"));
		oButtonRecalcPct.add_oActionAgent(new actionBerechneProzentualeUmbuchung());
		
		cbPreisanpassung    = new MyE2_CheckBox(new MyE2_String("Preis anpassen"), oStyleCheckboxNoBorder, true, false);
		cbProzentual		= new MyE2_CheckBox(new MyE2_String("Prozentuale Umbuchung"), oStyleCheckboxNoBorder, false, false); 
		cbProzentual.add_oActionAgent(new actionSetProzentualeBerechnung());
		
		
		// Einheiten-bezogene Umbuchung
		cbEinheitenUmbuchung 				= new MyE2_CheckBox(new MyE2_String("Einheiten Umrechnen"), oStyleCheckboxNoBorder, false, false); 
		tfEinheitenumbuchung_MengeAbbuchung 	= new UB_TextField("MENGE_BASIS_EINHEITENUMRECHNUNG", true, "",80,15);
		tfEinheitenumbuchung_FaktorMenge   	= new UB_TextField("FAKTOR_EINHEITENUMRECHNUNG_MENGE", true, "",80,15);
		tfEinheitenumbuchung_FaktorPreis		= new UB_TextField("FAKTOR_EINHEITENUMRECHNUNG_PREIS", true, "",80,15);
		
		lblEinheitenumbuchung_Einheit	= new MyE2_Label("");;
		cbEinheitenUmbuchung.add_oActionAgent(new actionSetEinheitenUmrechnung());
		
		oButtonRecalcEinheitenumbuchung 	= new MyE2_Button(new MyString("Menge/Preis berechnen"), E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png"));
		oButtonRecalcEinheitenumbuchung.add_oActionAgent(new actionBerechneEinheitenUmrechnung());

		
		
		/**
		 * Buchungsdaten
		 */
		// Lager-Zu/Abbuchen (nur bei Korrekturbuchung wichtig) 
		cbLagerZubuchung = new MyE2_CheckBox(new MyE2_String("Menge ins Lager zubuchen "),oStyleCheckboxNoBorder, true, false);
		cbLagerAbbuchung = new MyE2_CheckBox(new MyE2_String("Menge aus Lager abbuchen "),oStyleCheckboxNoBorder, false, false);
		agentWatchdogZUAB = new ActionAgent_RadioFunction_CheckBoxList(false);
		agentWatchdogZUAB.add_CheckBox(cbLagerZubuchung);
		agentWatchdogZUAB.add_CheckBox(cbLagerAbbuchung);
		agentWatchdogZUAB.set_AllUnselected();
		
		
		
		tfMengeZiel 		= new UB_TextField("MENGE_NEU",false,"",120,15);
		this.tfMengeZiel.add_InputValidator(new VALIDATE_INPUT_Float());
		this.tfMengeZiel.set_bEmptyAllowd(false);
		this.tfMengeZiel.set_bEnabled_For_Edit(true);
		this.tfMengeZiel.set_StyleForInput(true);

		tfPreisZiel			= new UB_TextField("PREIS_NEU",false,"",80,15);
		this.tfPreisZiel.add_InputValidator(new VALIDATE_INPUT_Float());
		this.tfPreisZiel.set_bEmptyAllowd(false);
		this.tfPreisZiel.set_StyleForInput(true);
		
		if (bibALL.get_bIST_SUPERVISOR()){
			this.tfPreisZiel.set_bEnabled_For_Edit(true);
		} else {
			this.tfPreisZiel.set_bEnabled_For_Edit(false);
		}

		// Kostenanteil des Preises
		tfPreisZielKosten	= new UB_TextField("PREIS_NEU_KOSTEN",false,"",80,15);
		this.tfPreisZielKosten.add_InputValidator(new VALIDATE_INPUT_Float());
		this.tfPreisZielKosten.set_bEmptyAllowd(false);
		this.tfPreisZielKosten.set_StyleForInput(true);
		
		if (bibALL.get_bIST_SUPERVISOR()){
			this.tfPreisZielKosten.set_bEnabled_For_Edit(true);
		} else {
			this.tfPreisZielKosten.set_bEnabled_For_Edit(false);
		}
		
		
		lblEinheitPreis 	= new MyE2_Label("");
		lblEinheitMenge 	= new MyE2_Label("");
		
		
		tfDatum 			= new UB_TextField_With_DatePOPUP_OWN("BUCHUNGSDATUM", false, null, 80);
		tfDatum.add_InputValidator(new VALIDATE_INPUT_DATE());
		tfDatum.set_bEmptyAllowd(false);
		tfDatum.set_bEnabled_For_Edit(true);
		tfDatum.set_StyleForInput(true);
		tfDatum.get_vActionAgentsZusatz().add( new actionRefreshLagerPreis());
		

		tfBemerkung			= new UB_TextArea("",300,3,500);
		tfBemerkung.set_iMaxInputSize(500);
				
		oButtonSaveBuchung = new MyE2_Button("Buchung ausführen");
		oButtonSaveBuchung.setFont(new E2_FontBold(1));
		oButtonSaveBuchung.add_oActionAgent(new actionSaveBuchung());
			
		this.oButtonRefreshLagerPreis = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png"));
		this.oButtonRefreshLagerPreis.add_oActionAgent(new actionRefreshLagerPreis());
		oToolTipText = new MyE2_String("Durchschnittlicher Lagerpreis ermitteln.");
		this.oButtonRefreshLagerPreis.setToolTipText(oToolTipText.toString());
		

		MyE2_Row oRowSaldo = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowSaldo.add(tfSaldo,E2_INSETS.I_0_0_10_0);
		oRowSaldo.add(lblSaldo_EinheitMenge);
		oRowSaldo.add(new MyE2_Label(new MyE2_String("Durchschnittspreis"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_20_0_0_0);
		oRowSaldo.add(tfSaldo_AvgPreis,E2_INSETS.I_10_0_0_0);
		oRowSaldo.add(lblSaldo_EinheitPreis);
		oRowSaldo.add(new MyE2_Label(new MyE2_String("davon Kosten"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_20_0_0_0);
		oRowSaldo.add(tfSaldo_Kostenanteil,E2_INSETS.I_10_0_0_0);
		
		oRowSaldo.add(oButtonRefreshSaldo);
		

		MyE2_Row oRowSaldoBuchungsdatum = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowSaldoBuchungsdatum.add(tfSaldo_Buchungsdatum,E2_INSETS.I_0_0_10_0);
		oRowSaldoBuchungsdatum.add(lblSaldo_Buchungsdatum_EinheitMenge);
		oRowSaldoBuchungsdatum.add(new MyE2_Label(new MyE2_String("Durchschnittspreis"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_20_0_0_0);
		oRowSaldoBuchungsdatum.add(tfSaldo_Buchungsdatum_AvgPreis,E2_INSETS.I_10_0_0_0);
		oRowSaldoBuchungsdatum.add(lblSaldo_Buchungsdatum_EinheitPreis);
		oRowSaldoBuchungsdatum.add(new MyE2_Label(new MyE2_String("davon Kosten"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_20_0_0_0);
		oRowSaldoBuchungsdatum.add(tfSaldo_Buchungsdatum_Kostenanteil,E2_INSETS.I_10_0_0_0);
		oRowSaldoBuchungsdatum.add(oButtonAktualisiereSetzkasten, E2_INSETS.I_20_0_0_0);
		
		
		MyE2_Row oRowButtonBuchung = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowButtonBuchung.add(oButtonSaveBuchung);
				
		
		//die felder in einen vector zusammenfassen, damit es einfacher geht
		this.vUnboundFields.add(oSearchSorteQuelle);
		this.vUnboundFields.add(tfMengeZiel);
		this.vUnboundFields.add(tfPreisZiel);
		this.vUnboundFields.add(tfPreisZielKosten);
		this.vUnboundFields.add(tfDatum);
		this.vUnboundFields.add(tfMengeAbbuchung);
		this.vUnboundFields.add(tfKostenanteil_Abbuchung);
		this.vUnboundFields.add(tfPreis_Abbuchung);
		this.vUnboundFields.add(tfProzent);
		
		
		Alignment align_right_center = new Alignment(Alignment.RIGHT,Alignment.CENTER);
		tfSaldo.setAlignment(align_right_center);
		tfSaldo_AvgPreis.setAlignment(align_right_center);
		tfSaldo_Buchungsdatum.setAlignment(align_right_center);
		tfSaldo_Buchungsdatum_AvgPreis.setAlignment(align_right_center);
	
		tfPreisZiel.setAlignment(align_right_center);
		tfPreisZielKosten.setAlignment(align_right_center);
		tfMengeZiel.setAlignment(align_right_center);

		tfDatum.setAlignment(align_right_center);

		tfPreis_Abbuchung.setAlignment(align_right_center);
		tfKostenanteil_Abbuchung.setAlignment(align_right_center);
		tfProzent.setAlignment(align_right_center);
		tfMengeAbbuchung.setAlignment(align_right_center);
		
		tfEinheitenumbuchung_FaktorMenge.setAlignment(align_right_center);
		tfEinheitenumbuchung_FaktorPreis.setAlignment(align_right_center);
		tfEinheitenumbuchung_MengeAbbuchung.setAlignment(align_right_center);
		
		
		vComponentsUmbuchung = new Vector<Component>();
		vComponentsKorrekturbuchung = new Vector<Component>();
	
		
		Insets oIn = new Insets(4,1,2,1);
		Insets oInSep = new Insets(0,5,0,0);

		//
		// Layout
		//
		MyE2_Grid oGridBase = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		lblHeading = new MyE2_Label(new MyE2_String("--"),MyE2_Label.STYLE_TITEL_BIG());
		oGridBase.add(lblHeading,5, new Insets(4,10,2,0));
		
		oGridBase.add(cbSortenumbuchung,1, E2_INSETS.I_0_2_2_2);
		oGridBase.add(new MyE2_Label(""),4,E2_INSETS.I_0_2_2_2);
		
		oGridBase.add(new Separator(),5,oInSep);	

		/*
		 * Lager 
		 */
		
//		oGridBase.add(new Separator(),5,oInSep);	

		/*
		 * Ausgangssorte und Ausgangslager
		 */

		lblHeadingSorte = new MyE2_Label(new MyE2_String("--"),MyE2_Label.STYLE_TITEL_NORMAL());
		oGridBase.add(lblHeadingSorte,5, E2_INSETS.I_0_2_2_2);

		oGridBase.add(new MyE2_Label(new MyE2_String("Lager"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(new E2_ComponentGroupHorizontal(0,oSelectFieldLagerQuelle,E2_INSETS.I_0_0_0_0),4, oIn);

		oGridBase.add(new MyE2_Label(new MyE2_String("Sorte"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(oSearchSorteQuelle,4, oIn);
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Lagersaldo aktuell"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(oRowSaldo,4,oIn);

		oGridBase.add(new MyE2_Label(new MyE2_String("Lagersaldo Buchungstag"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(oRowSaldoBuchungsdatum,4,oIn);


		/*
		 *  Umbuchung
    	 */
		Separator sep = new Separator();
		oGridBase.add(sep,5,oInSep);	
		vComponentsUmbuchung.add(sep);
		
		lblHeadingFunction = new MyE2_Label(new MyE2_String("--"),MyE2_Label.STYLE_TITEL_NORMAL());
		oGridBase.add(lblHeadingFunction,5, E2_INSETS.I_0_2_2_2);
		vComponentsUmbuchung.add(lblHeadingFunction);

		// Ziel-Lager
		MyE2_Label lblZiellager =new MyE2_Label(new MyE2_String("Lager"),MyE2_Label.STYLE_NORMAL_PLAIN()); 
		oGridBase.add(lblZiellager,1, oIn);
		vComponentsUmbuchung.add(lblZiellager);
		E2_ComponentGroupHorizontal oCompGroupLagerZiel = new E2_ComponentGroupHorizontal(0,oSelectFieldLagerZiel,E2_INSETS.I_0_0_0_0);
		oGridBase.add(oCompGroupLagerZiel,4, oIn);
		vComponentsUmbuchung.add(oCompGroupLagerZiel);
		
		// Zielsorte
		MyE2_Label lblZielsorte =new MyE2_Label(new MyE2_String("Zielsorte"),MyE2_Label.STYLE_NORMAL_PLAIN()); 
		oGridBase.add(lblZielsorte ,1,oIn);
		vComponentsUmbuchung.add(lblZielsorte);
		oGridBase.add(oSearchSorteZiel,4, oIn);
		vComponentsUmbuchung.add(oSearchSorteZiel);
		
		
		// Umrechnung prozentuale Verbuchung
		oGridZielsorte = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		// Begrenzung nach oben
		Separator sep2 = new Separator();
		oGridZielsorte.add(sep2,5,oInSep);	

		
		oGridZielsorte.add(cbProzentual,1,oIn);
		MyE2_Row oRowPct1 = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowPct1.add(cbPreisanpassung, E2_INSETS.I_0_0_0_0);
		oGridZielsorte.add(oRowPct1,4,oIn);
		
		oGridZielsorte.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);

		MyE2_Grid oGridMengePct = new MyE2_Grid(6, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridMengePct.add(new MyE2_Label(new MyE2_String("Abbuchungsmenge: "),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_RIGHT_CENTER(E2_INSETS.I_0_0_0_0));
		oGridMengePct.add(tfMengeAbbuchung,E2_INSETS.I_5_0_0_0);
		oGridMengePct.add(new MyE2_Label(new MyE2_String(" "),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
		oGridMengePct.add(new MyE2_Label(new MyE2_String("Umbuchungssatz in %: "),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_RIGHT_CENTER(E2_INSETS.I_10_0_0_0));
		oGridMengePct.add(tfProzent,E2_INSETS.I_5_0_0_0);
		oGridMengePct.add(new MyE2_Label(new MyE2_String(" "),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I_0_0_0_0);
		
		oGridMengePct.add(new MyE2_Label(new MyE2_String("Ausgangspreis: "),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_0_0_0_0);
		oGridMengePct.add(tfPreis_Abbuchung,E2_INSETS.I_5_0_0_0);
		oGridMengePct.add(new MyE2_Label(new MyE2_String(" "),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
		oGridMengePct.add(new MyE2_Label(new MyE2_String("davon Kosten: "),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_10_0_0_0);
		oGridMengePct.add(tfKostenanteil_Abbuchung,E2_INSETS.I_5_0_0_0);
		oGridMengePct.add(oButtonRecalcPct,E2_INSETS.I_10_0_0_0);
		
		oGridZielsorte.add(oGridMengePct,4, oIn);
		
		MyE2_Label lblGridProzentabzug = new MyE2_Label("");
		oGridBase.add(lblGridProzentabzug,1,oIn);
		oGridBase.add(oGridZielsorte,4,oIn);
		
		vComponentsUmbuchung.add(lblGridProzentabzug);
		vComponentsUmbuchung.add(oGridZielsorte);
		
		Separator sep3 = new Separator();
		oGridZielsorte.add(sep3,5,oInSep);	
		
		// Umbuchung bei verschiedenen Einheiten
		oGridZielsorte.add(cbEinheitenUmbuchung,1,1,E2_INSETS.I(0,5,0,0),Alignment.ALIGN_TOP);
		MyE2_Grid oGridEinheiten = new MyE2_Grid(6, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridEinheiten.add(new MyE2_Label(new MyE2_String("Abbuchungsmenge: "),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
		oGridEinheiten.add(tfEinheitenumbuchung_MengeAbbuchung,E2_INSETS.I_5_0_0_0);
		oGridEinheiten.add(lblEinheitenumbuchung_Einheit,E2_INSETS.I_5_0_0_0);
		oGridEinheiten.add(new MyE2_Label(new MyE2_String("Umrechnungsfaktor Menge: "),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_10_0_0_0));
		oGridEinheiten.add(tfEinheitenumbuchung_FaktorMenge,E2_INSETS.I_5_0_0_0);
		oGridEinheiten.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
		
		oGridEinheiten.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
		oGridEinheiten.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
		oGridEinheiten.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
		oGridEinheiten.add(new MyE2_Label(new MyE2_String("Umrechnungsfaktor Preis: "),MyE2_Label.STYLE_NORMAL_PLAIN()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_10_0_0_0));
		oGridEinheiten.add(tfEinheitenumbuchung_FaktorPreis,E2_INSETS.I_5_0_0_0);
		oGridEinheiten.add(oButtonRecalcEinheitenumbuchung,E2_INSETS.I_10_0_0_0);
		oGridZielsorte.add(oGridEinheiten,4, oIn);

		
		//
		// Buchungsdaten
		//
		oGridBase.add(new Separator(),5,oInSep);	
		oGridBase.add(new MyE2_Label(new MyE2_String("Buchungsdaten"),MyE2_Label.STYLE_TITEL_NORMAL()),5, E2_INSETS.I_0_2_2_2);
		
		// Buchungsdatum / Zeit
		oGridBase.add(new MyE2_Label(new MyE2_String("Buchungsdatum"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(new E2_ComponentGroupHorizontal(0,tfDatum,E2_INSETS.I_0_0_0_0),4, oIn);

//		oGridBase.add(new MyE2_Label(new MyE2_String("Buchungszeit"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
//		oGridBase.add(new E2_ComponentGroupHorizontal(0,tfZeit,E2_INSETS.I_0_0_0_0),4, oIn);
		oGridBase.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),5, oIn);

		// Schalter Zu/Abbuchung
		// wird nur angezeigt, wenn Korrekturbuchung, sonst ausgeblendet
		MyE2_Label lblBuchungsart = new MyE2_Label(new MyE2_String("Zu- / Abbuchung:"),MyE2_Label.STYLE_NORMAL_PLAIN());
		oGridBase.add(lblBuchungsart,1,oIn);
		MyE2_Row   rowBuchungsart = new MyE2_Row(); 
		rowBuchungsart.add(cbLagerZubuchung, E2_INSETS.I_0_0_10_0);
		rowBuchungsart.add(cbLagerAbbuchung);
		oGridBase.add(rowBuchungsart,4,oIn);
		
		vComponentsKorrekturbuchung.add(lblBuchungsart);
		vComponentsKorrekturbuchung.add(rowBuchungsart);
		vComponentsKorrekturbuchung.add(cbLagerAbbuchung);
		vComponentsKorrekturbuchung.add(cbLagerZubuchung);
		
		
		
		// Menge
		oGridBase.add(new MyE2_Label(new MyE2_String("Menge"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		MyE2_Row oRowMenge =  new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowMenge.add(tfMengeZiel,E2_INSETS.I_0_0_10_0);
		oRowMenge.add(lblEinheitMenge);
		oGridBase.add(oRowMenge,4, oIn);
			
		// Preis
		oGridBase.add(new MyE2_Label(new MyE2_String("Preis"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		MyE2_Row oRowPreis =  new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowPreis.add(tfPreisZiel,E2_INSETS.I_0_0_10_0);
		oRowPreis.add(lblEinheitPreis);
		oRowPreis.add(new MyE2_Label(new MyE2_String("davon Kosten:"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_10_0_0_0);
		oRowPreis.add(tfPreisZielKosten,E2_INSETS.I_0_0_10_0);
		oRowPreis.add(oButtonRefreshLagerPreis);
		oGridBase.add( oRowPreis ,4,oIn);
		
		// Bemerkungsfeld
		oGridBase.add(new MyE2_Label(new MyE2_String("Bemerkung"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(tfBemerkung, 4,oIn);

		// Speichern-Button
		oGridBase.add(new MyE2_Label("",MyE2_Label.STYLE_NORMAL_PLAIN()),1,oIn);
		oGridBase.add(oRowButtonBuchung,4,new Insets(4,10,2,1));

		this.add(oGridBase,E2_INSETS.I_2_2_2_2);
		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (m_NavListToRefresh != null){
					m_NavListToRefresh.RefreshList();
				}
				
				
				if (m_bIsDirty && m_NavListRecordLagerKonto != null){
					m_NavListRecordLagerKonto.RefreshList();
				}
				
				m_bIsDirty = false;
			}
		});

		if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH)){
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Erfassung einer Lagerbuchung / Korrekturbuchung"));
		} else {
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Erfassung einer Lagerumbuchung"));
		}
	}
	
	
	/**
	 * true, wenn der Dialog ein Korrekturbuchungsdialog ist,
	 * false wenn es ein Umbuchungsdialog ist
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private boolean bIsKorrekturbuchung (){
		return m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH);
	}
	
	/**
	 * true, wenn der Dialog ein Umbuchung ist,
	 * false wenn es ein Korrekturbuchung ist
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private boolean bIsUmbuchung (){
		return m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT);
	}
	
	
	
	/**
	 * gibt Anhand der Checkboxen cbLagerAbbuchung, ob es eine Abbuchung ist 
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private boolean bIsBuchungEineAbbuchung(){
		return cbLagerAbbuchung.isSelected();
	}
	
	/**
	 * gibt Anhand der Checkboxen cbLagerzubuchung, ob es eine Zubuchung ist 
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private boolean bIsBuchungEineZubuchung(){
		return cbLagerZubuchung.isSelected();
	}
	
	
	
	
	/**
	 * Setzt die richtige Checkbox für die Zu- oder Abbuchung 
	 * @author manfred
	 * @date   30.03.2012
	 * @param bAbbuchung
	 */
	private void setBuchungIsAbbuchung(boolean bAbbuchung){
		if (bAbbuchung){
			setCheckboxOfWatchdogAgent(agentWatchdogZUAB, cbLagerAbbuchung);
		} else {
			setCheckboxOfWatchdogAgent(agentWatchdogZUAB, cbLagerZubuchung);
		}
	}
	
	

	/**
	 * Setzen einer Checkbox einer 1 aus N-Checkbox-Gruppe
	 * 
	 * @param watchdog
	 * @param checkbox
	 */
	private void setCheckboxOfWatchdogAgent(
			ActionAgent_RadioFunction_CheckBoxList watchdog,
			MyE2_CheckBox checkbox) {
		watchdog.executeAgentCodePassiv(new ExecINFO(new MyActionEvent(
				new ActionEvent(checkbox, null)), true));
	}

	
	
	/**
	 * Setzt den Status der Felder abhängig ob eine Off-Buchung oder Umbuchung durchgeführt werden soll.
	 * Abhängig von dem Setting der Display-Option SHOW_BUCHUNG_EINFACH/SHOW_BUCHUNG_SPLIT
	 * @throws myException
	 */
	private void setBuchungsDisplayOption() throws myException{
		if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT)){
			lblHeading.setText(new MyString("Erfassung einer Sorten-Umbuchung").CTrans());
			lblHeadingSorte.setText(new MyString("Auswahl der Ausgangssorte (Menge wird abgebucht)").CTrans());
			lblHeadingFunction.setText(new MyString("Auswahl der Zielsorte / Buchungsberechnungen (Menge wird zugebucht)").CTrans());
			oSearchSorteZiel.set_bEnabled_For_Edit(true);
			
			cbProzentual.set_bEnabled_For_Edit(true);
			cbEinheitenUmbuchung.set_bEnabled_For_Edit(true);
			
			for (Component c : vComponentsUmbuchung){
				c.setVisible(true);
			}
			for (Component c : vComponentsKorrekturbuchung){
				c.setVisible(false);
			}

			// selectfield neu initialisieren: Keine Strecke für Umbuchung
			this.oFactoryForSelectFieldZiel.refreshSelectfield(false);
			this.oFactoryForSelectFieldQuelle.refreshSelectfield(false);
			
		} else {
			lblHeading.setText(new MyString("Erfassung einer Korrekturbuchung").CTrans());
			lblHeadingSorte.setText(new MyString("Auswahl der Sorte").CTrans());
			lblHeadingFunction.setText(new MyString("Neubuchung einer Menge").CTrans());
			
			oSearchSorteZiel.get_oTextFieldForSearchInput().setText("");
			oSearchSorteZiel.get_oTextForAnzeige().setText("");
			oSearchSorteZiel.set_bEnabled_For_Edit(false);
			
			cbProzentual.setSelected(false);
			cbProzentual.set_bEnabled_For_Edit(false);
			
			cbEinheitenUmbuchung.setSelected(false);
			cbEinheitenUmbuchung.set_bEnabled_For_Edit(false);
			
			for (Component c : vComponentsUmbuchung){
				c.setVisible(false);
			}
			for (Component c : vComponentsKorrekturbuchung){
				c.setVisible(true);
			}
			
			// für die Korrekturbuchung muss die Strecke angezeigt werden. 
			this.oFactoryForSelectFieldQuelle.refreshSelectfield(true);
			this.oFactoryForSelectFieldZiel.refreshSelectfield(true);
			
		}
		

		setProzentrechnungStatus();
		setUmrechnungStatus();
	}
	
	
	
	/**
	 * Setzt den status der Felder die bei der Umbuchung für die prozentuale Berechnung nötig sind
	 * @throws myException
	 */
	private void setProzentrechnungStatus() throws myException{
		boolean bOn = cbProzentual.isSelected();
		
		this.tfMengeAbbuchung.set_bEnabled_For_Edit(bOn);
		this.tfMengeAbbuchung.set_StyleForInput(bOn);
		this.tfMengeAbbuchung.set_bEmptyAllowd(!bOn);
			
		this.tfPreis_Abbuchung.set_bEnabled_For_Edit(bOn);
		this.tfPreis_Abbuchung.set_StyleForInput(bOn);
		this.tfPreis_Abbuchung.set_bEmptyAllowd(!bOn);
		
		this.tfKostenanteil_Abbuchung.set_bEnabled_For_Edit(bOn);
		this.tfKostenanteil_Abbuchung.set_StyleForInput(bOn);
		this.tfKostenanteil_Abbuchung.set_bEmptyAllowd(!bOn);
		
		this.tfProzent.set_bEnabled_For_Edit(bOn);
		this.tfProzent.set_StyleForInput(bOn);
		this.tfProzent.set_bEmptyAllowd(!bOn);

		this.cbPreisanpassung.set_bEnabled_For_Edit(bOn);
		this.oButtonRecalcPct.set_bEnabled_For_Edit(bOn);
		

		setStatusMengeZiel();
	
		if (!bOn){
			tfMengeAbbuchung.get_vInputValidator().removeAllElements();
			tfPreis_Abbuchung.get_vInputValidator().removeAllElements();
			tfKostenanteil_Abbuchung.get_vInputValidator().removeAllElements();
			
			tfProzent.get_vInputValidator().removeAllElements();
			tfMengeAbbuchung.setText(null);
			tfPreis_Abbuchung.setText(null);
			tfKostenanteil_Abbuchung.setText(null);
			tfProzent.setText(null);
		} else {
			tfMengeAbbuchung.get_vInputValidator().removeAllElements();
			tfPreis_Abbuchung.get_vInputValidator().removeAllElements();
			tfKostenanteil_Abbuchung.get_vInputValidator().removeAllElements();
			tfProzent.get_vInputValidator().removeAllElements();
			
			tfMengeAbbuchung.add_InputValidator(new VALIDATE_INPUT_Float());
			tfPreis_Abbuchung.add_InputValidator(new VALIDATE_INPUT_Float());
			tfKostenanteil_Abbuchung.add_InputValidator(new VALIDATE_INPUT_Float());
			tfProzent.add_InputValidator(new VALIDATE_INPUT_Float());

		}
	}
	
	
	
	/**
	 * Setzt den Status der Felder die bei der Umbuchung für Einheiten nötig sind
	 * @throws myException
	 */
	private void setUmrechnungStatus() throws myException{
		boolean bOn = cbEinheitenUmbuchung.isSelected();
		
		this.tfEinheitenumbuchung_MengeAbbuchung.set_bEnabled_For_Edit(bOn);
		this.tfEinheitenumbuchung_MengeAbbuchung.set_StyleForInput(bOn);
		this.tfEinheitenumbuchung_MengeAbbuchung.set_bEmptyAllowd(!bOn);
			
		this.tfEinheitenumbuchung_FaktorMenge.set_bEnabled_For_Edit(bOn);
		this.tfEinheitenumbuchung_FaktorMenge.set_StyleForInput(bOn);
		this.tfEinheitenumbuchung_FaktorMenge.set_bEmptyAllowd(!bOn);
		
		this.tfEinheitenumbuchung_FaktorPreis.set_bEnabled_For_Edit(bOn);
		this.tfEinheitenumbuchung_FaktorPreis.set_StyleForInput(bOn);
		this.tfEinheitenumbuchung_FaktorPreis.set_bEmptyAllowd(!bOn);

		this.oButtonRecalcEinheitenumbuchung.set_bEnabled_For_Edit(bOn);
		
		// Zielmenge darf nicht editiert werden.
		setStatusMengeZiel();
		
		if (!bOn){
			tfEinheitenumbuchung_MengeAbbuchung.get_vInputValidator().removeAllElements();
			tfEinheitenumbuchung_FaktorMenge.get_vInputValidator().removeAllElements();
			tfEinheitenumbuchung_FaktorPreis.get_vInputValidator().removeAllElements();
			tfEinheitenumbuchung_MengeAbbuchung.setText(null);
			tfEinheitenumbuchung_FaktorMenge.setText(null);
			tfEinheitenumbuchung_FaktorPreis.setText(null);
			
		} else {
			tfEinheitenumbuchung_MengeAbbuchung.get_vInputValidator().removeAllElements();
			tfEinheitenumbuchung_FaktorMenge.get_vInputValidator().removeAllElements();
			tfEinheitenumbuchung_FaktorPreis.get_vInputValidator().removeAllElements();
			tfEinheitenumbuchung_MengeAbbuchung.add_InputValidator(new VALIDATE_INPUT_Float());
			tfEinheitenumbuchung_FaktorMenge.add_InputValidator(new VALIDATE_INPUT_Float());
			tfEinheitenumbuchung_FaktorPreis.add_InputValidator(new VALIDATE_INPUT_Float());
		}
	}


	/**
	 *  setzt den Status der Zielmenge
	 *  in Abhängigkeit der Schalter
	 *  - prozentuale Umbuchung
	 *  - Einheiten Umrechnen
	 * 
	 * @throws myException
	 */
	private void setStatusMengeZiel() throws myException{
		boolean bOn = cbEinheitenUmbuchung.isSelected() || cbProzentual.isSelected();
		this.tfMengeZiel.set_bEnabled_For_Edit(!bOn);
	}
	
	
	
	/**
	 * Rechnet die Ausgangsmenge prozentual in die Zielmenge um und passt wenn gewünscht den Preis
	 * so an, dass der Wert erhalten bleibt
	 */
	private void doProzentualeUmrechnung(){
		
		if (cbProzentual.isSelected()==false){
			return;
		}
		
		VECTOR_UB_FIELDS  				vUnboundFields 		= new VECTOR_UB_FIELDS();
		vUnboundFields.add(tfMengeAbbuchung);
		vUnboundFields.add(tfProzent);

		
		try {
			bibMSG.add_MESSAGE(vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
		} catch (myException e1) {
			//
		}
		
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		BigDecimal bdMengeAusgang ;
		cPreis     oPreisAusgang = new cPreis();
		BigDecimal bdProzent ;
		
		BigDecimal bdMengeZiel 				= BigDecimal.ZERO;
		BigDecimal bdPreisZiel 				= BigDecimal.ZERO;
		BigDecimal bdPreisZielOhneKosten 	= BigDecimal.ZERO;

		try {
			tfProzent.get_MV_InputOK();
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		
		bdProzent = bibALL.convertTextToBigDecimal(tfProzent.getText());
		bdMengeAusgang = bibALL.convertTextToBigDecimal(tfMengeAbbuchung.getText());
		
		// zuerst den Preis berechnen, der beim Lager-Status für die Menge anfallen würde
		try {
			oPreisAusgang = getLagerPreisZumBuchungszeitpunkt( bdMengeAusgang, tfDatum.get_oDBFormatedDateFromTextField() );
		    tfPreis_Abbuchung.setText( bibALL.convertBigDecimalToString(oPreisAusgang.getPreisMitKosten(), 2));
		    tfKostenanteil_Abbuchung.setText( bibALL.convertBigDecimalToString(oPreisAusgang.getKostenanteil(), 2));
		    
		} catch (myException e) {
			
		}

		bdMengeZiel = bdMengeAusgang.multiply(bdProzent.divide(new BigDecimal(100)));
		
		if (cbPreisanpassung.isSelected()){
			if (bdMengeZiel.compareTo(BigDecimal.ZERO) != 0){
				bdPreisZiel 			= oPreisAusgang.getPreisMitKosten().multiply( bdMengeAusgang.divide(bdMengeZiel,2,RoundingMode.HALF_UP));
				bdPreisZielOhneKosten 	= oPreisAusgang.getPreisNetto().multiply( bdMengeAusgang.divide(bdMengeZiel,2,RoundingMode.HALF_UP));
			} else {
				// division / 0
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Zielmenge darf nicht null sein!"));
			}
		} else {
			bdPreisZiel 			= oPreisAusgang.getPreisMitKosten();
			bdPreisZielOhneKosten 	= oPreisAusgang.getPreisNetto();
		}
		
		// zuweisung der Feldinhalte
		tfMengeZiel.setText( bibALL.convertBigDecimalToString(bdMengeZiel, 3));
		tfPreisZiel.setText( bibALL.convertBigDecimalToString(bdPreisZiel, 2));
		tfPreisZielKosten.setText(bibALL.convertBigDecimalToString(bdPreisZiel.subtract(bdPreisZielOhneKosten), 2));
		
	}
	
	
	/**
	 * Rechnet die Ausgangsmenge prozentual in die Zielmenge um und passt wenn gewünscht den Preis
	 * so an, dass der Wert erhalten bleibt
	 */
	private void doEinheitenUmrechnung(){
		
		if (cbEinheitenUmbuchung.isSelected()==false){
			return;
		}
		
		VECTOR_UB_FIELDS  				vUnboundFields 		= new VECTOR_UB_FIELDS();
		vUnboundFields.add(tfEinheitenumbuchung_MengeAbbuchung);
		vUnboundFields.add(tfEinheitenumbuchung_FaktorMenge);
		vUnboundFields.add(tfEinheitenumbuchung_FaktorPreis);

		
		try {
			bibMSG.add_MESSAGE(vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
		} catch (myException e1) {
			//
		}
		
			
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		BigDecimal bdMengeAusgang ;
		cPreis     oPreisAusgang = new cPreis();
		BigDecimal bdFaktorMenge ;
		BigDecimal bdFaktorPreis ;
		
		BigDecimal bdMengeZiel 				= BigDecimal.ZERO;
		BigDecimal bdPreisZiel 				= BigDecimal.ZERO;
		BigDecimal bdPreisZielOhneKosten 	= BigDecimal.ZERO;

		try {
			tfEinheitenumbuchung_FaktorMenge.get_MV_InputOK();
			tfEinheitenumbuchung_FaktorPreis.get_MV_InputOK();
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		
		bdFaktorMenge = bibALL.convertTextToBigDecimal(tfEinheitenumbuchung_FaktorMenge.getText());
		bdFaktorPreis = bibALL.convertTextToBigDecimal(tfEinheitenumbuchung_FaktorPreis.getText());
		bdMengeAusgang = bibALL.convertTextToBigDecimal(tfEinheitenumbuchung_MengeAbbuchung.getText());
		
		// zuerst den Preis berechnen, der beim Lager-Status für die Menge anfallen würde
		try {
			oPreisAusgang = getLagerPreisZumBuchungszeitpunkt( bdMengeAusgang, tfDatum.get_oDBFormatedDateFromTextField() );
		    tfPreis_Abbuchung.setText( bibALL.convertBigDecimalToString(oPreisAusgang.getPreisMitKosten(), 2));
		    tfKostenanteil_Abbuchung.setText( bibALL.convertBigDecimalToString(oPreisAusgang.getKostenanteil(), 2));
		    
		} catch (myException e) {
			
		}

		bdMengeZiel = bdMengeAusgang.multiply(bdFaktorMenge);
		bdFaktorPreis = (bdFaktorPreis == null ? BigDecimal.ZERO : bdFaktorPreis);
		
		
		
		if ( bdMengeZiel.compareTo(BigDecimal.ZERO ) != 0 && 
			 oPreisAusgang.getPreisMitKosten() != null &&
			 oPreisAusgang.getPreisNetto() != null	){
			bdPreisZiel				= oPreisAusgang.getPreisMitKosten().multiply( bdFaktorPreis );
			bdPreisZielOhneKosten 	= oPreisAusgang.getPreisNetto().multiply( bdFaktorPreis );
		} 
		
		// zuweisung der Feldinhalte
		tfMengeZiel.setText( bibALL.convertBigDecimalToString(bdMengeZiel, 3));
		tfPreisZiel.setText( bibALL.convertBigDecimalToString(bdPreisZiel, 2));
		tfPreisZielKosten.setText(bibALL.convertBigDecimalToString(bdPreisZiel.subtract(bdPreisZielOhneKosten), 2));
	}

	
	

	
	/**
	 * getter für das Quell-Sortensuchfeld
	 * @author manfred
	 * @date   
	 * @return
	 */
	public UTIL_UB_MaskSearchField_Sorte get_oSearchSorte()
	{
		return oSearchSorteQuelle;
	}
	
	
	
	
	/**
	 * setzen der Daten bei der Initialisierung der Maske
	 * @throws myException 
	 */
	private void initData() throws myException{
		
		/*
		 * falls eine NavigationList übergeben wurde:
		 * festlegen der ausgewaehlten sorte/lager
		 */
		setBuchungsDisplayOption();

		
		if (m_NavListRecordLagerKonto != null){
			Vector<String> vSelectedIDs = this.m_NavListRecordLagerKonto.get_vSelectedIDs_Unformated();
			
			if (vSelectedIDs.size()==1)
			{
				RECORD_BEWEGUNG_STATION recStation = new RECORD_BEWEGUNG_STATION(vSelectedIDs.get(0));
				RECORD_BEWEGUNG_ATOM 	recAtom    = recStation.get_UP_RECORD_BEWEGUNG_ATOM_id_bewegung_atom();
				
				String idLager = recStation.get_ID_ADRESSE_cUF();
				String idSorte = recAtom.get_ID_ARTIKEL_cUF();
				
				oSelectFieldLagerQuelle.set_ActiveValue_OR_FirstValue(idLager);
				oSelectFieldLagerZiel.set_ActiveValue_OR_FirstValue(idLager);
				
				oSearchSorteQuelle.get_oTextFieldForSearchInput().setText(idSorte);
				oSearchSorteQuelle.FillLabelWithDBQuery(idSorte);
				
				this.initLagerbestandAbsolut();
			
				this.initZielBuchungsdaten();
				
			}
		} else {
			if (m_IDLager != null){
				oSelectFieldLagerQuelle.set_ActiveValue_OR_FirstValue(m_IDLager);
				oSelectFieldLagerZiel.set_ActiveValue_OR_FirstValue(m_IDLager);
			}
			
			// als Menge immer eine positive Menge eingeben
			if (m_MengeKorrektur != null){
				tfMengeZiel.setText(bibALL.convertBigDecimalToString(m_MengeKorrektur.abs(),3));
				// abhängig vom Vorzheichen die checkboxen setzen (unabhängig davon, ob Korrektur- oder Umbuchung
				setBuchungIsAbbuchung(m_MengeKorrektur.compareTo(BigDecimal.ZERO) < 0 );
			}
									
			if (m_DatumKorrektur != null){
				tfDatum.get_oTextField().setText(new SimpleDateFormat("dd.MM.yyyy").format(m_DatumKorrektur));
			}
			
			
			if (m_IDSorte != null){
				if (bIsKorrekturbuchung()){
					// bei einer Korrekturbuchung ist es ok, wenn man die Sorte einfach wählt.
					oSearchSorteQuelle.get_oTextFieldForSearchInput().setText(m_IDSorte);
					oSearchSorteQuelle.FillLabelWithDBQuery(m_IDSorte);
				} else {
					// bei einer Umbuchung kommt es drauf an, ob die Menge positiv oder negativ ist
					if (m_MengeKorrektur.compareTo(BigDecimal.ZERO) >= 0){
						oSearchSorteZiel.get_oTextFieldForSearchInput().setText(m_IDSorte);
						oSearchSorteZiel.FillLabelWithDBQuery(m_IDSorte);
					} else {
						oSearchSorteQuelle.get_oTextFieldForSearchInput().setText(m_IDSorte);
						oSearchSorteQuelle.FillLabelWithDBQuery(m_IDSorte);
					}
				}
				this.initLagerbestandAbsolut();
				this.initZielBuchungsdaten();
			}
		}
	}
	
	
	
	/**
	 * Initialisiert die Saldowerte des Lagers
	 * @throws myException 
	 */
	private void initLagerbestandAbsolut() throws myException{
		
		this.readSaldoUndAvgPreis_Ausgangssorte_Absolut();
		this.setEinheitAusgangssorte();
	}

	
	
	/**
	 * Setzen der
	 * - Einheiten der Zielbuchung
	 * - des Preises für die Buchung
	 * - aktualisieren des Lagersaldos und Preises für das aktuelle Datum
	 * @author manfred
	 * @date   30.03.2012
	 * @throws myException
	 */
	private void initZielBuchungsdaten() throws myException{
		// zieldaten lesen
		this.setEinheitMengePreisZiel();
		this.readSaldoUndAvgPreis_Ausgangssorte_Zum_Buchungstag();
	}
	
	
	/**
	 * 
	 * @throws myException
	 *
	 * Autor:	 manfred
	 * Erstellt: 07.05.2014
	 *
	 */
	private void aktualisiereSetzkasten() throws myException{
		

		String sIDSorte = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		// Lager
		String sIDLager = null;
		try {
			sIDLager = new MyLong(oSelectFieldLagerQuelle.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
		} catch (myException e) {
			sIDLager = null;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sDatum  = df.format(new Date());
		
		ATOM_SetzkastenHandler_KALKULATORISCH oSetzkastenHandlerK = new ATOM_SetzkastenHandler_KALKULATORISCH(null);
		oSetzkastenHandlerK.ReorganiseLagerEntries(/*bibALL.get_ID_MANDANT(),*/ sIDLager, sIDSorte, sDatum ,"2009-01-01");
		
		ATOM_SetzkastenHandler oSetzkastenHandler = new ATOM_SetzkastenHandler(null);
		oSetzkastenHandler.ReorganiseLagerEntries(/*bibALL.get_ID_MANDANT(),*/ sIDLager, sIDSorte, sDatum ,"2009-01-01");
		
		this.initLagerbestandAbsolut();
		
	}

	
	
	/**
	 * Ermitteln des Saldos der gewählten Ausgangslager-Sorten-Kombination
	 * Ermittelt auch den Durchschnittslagerpreise
	 * 
	 * Author: manfred
	 * 
	 * 05.05.2014
	 * @return true, wenn lager und sorte nicht null sind.
	 */
	private boolean readSaldoUndAvgPreis_Ausgangssorte_Absolut(){
		boolean bRet = false;
		String sIDLager = null;
		String sIDSorte = null;
		
		sIDSorte = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();

		try {
			sIDLager = new MyLong(oSelectFieldLagerQuelle.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
		} catch (myException e) {
			sIDLager = null;
		}

		// das Buchungsdatum ermitteln wenn vorhanden
		tfSaldo.setText("-");
		tfSaldo_AvgPreis.setText("-");
		
		if (sIDSorte == null || sIDLager == null){
			return bRet ;
		}
		
		
		// der aktuelle Mengensaldo des Lagers und der Sorte
		m_bdSaldo_Aktuell = readSaldoForSelectedLagerSorte(sIDLager, sIDSorte, null);
		
		if (m_bdSaldo_Aktuell != null){
			tfSaldo.setText(bibALL.convertBigDecimalToString(m_bdSaldo_Aktuell,3));
		} else {
			m_bdSaldo_Aktuell = BigDecimal.ZERO;
		}
		
		//
		//  jetzt den aktuellen Setzkasten-Zustand der Sorte ermitteln
		//
		ATOM_LAG_BEW_DataRowStatus oStatus = readLagerstatusForSelectedLagerSorte(sIDLager, sIDSorte, null);
		m_bdMengeSetzkasten_aktuell = BigDecimal.ZERO;
		if (oStatus != null ){
			m_bdPreis_Aktuell = oStatus.getAvg_Restwert_Menge_Preise_nicht_Null();
			if (m_bdPreis_Aktuell != null){
				tfSaldo_AvgPreis.setText(bibALL.convertBigDecimalToString(m_bdPreis_Aktuell,2));
			}
			
			m_bdKosten_aktuell = oStatus.get_Kosten_Restwert_Menge_Preise_nicht_Null();
			if (m_bdKosten_aktuell != null){
				tfSaldo_Kostenanteil.setText(bibALL.convertBigDecimalToString(m_bdKosten_aktuell,2));
			}
			
			m_bdMengeSetzkasten_aktuell = oStatus.getMenge_Gesamt();
		}

		if (m_bdMengeSetzkasten_aktuell == null){
			m_bdMengeSetzkasten_aktuell = BigDecimal.ZERO;
		}
		
		if ( m_bdMengeSetzkasten_aktuell.compareTo(BigDecimal.ZERO) > 0  &&
				m_bdSaldo_Aktuell.compareTo(m_bdMengeSetzkasten_aktuell) != 0){
			oButtonAktualisiereSetzkasten.setVisible(true);
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Menge Saldo und Menge Setzkasten (" + bibALL.convertBigDecimalToString(m_bdMengeSetzkasten_aktuell, 0) + ") sind nicht gleich. Bitte Setzkasten aktualisieren!"));
		} else {
			oButtonAktualisiereSetzkasten.setVisible(false);
		}
		
		return bRet;
	}


	
	/**
	 * Anzeige des Lagerpreises für die Umbuchung
	 * für den aktuellen Bestand sowie auch für die umzubuchnende Menge
	 * @author manfred
	 * @date   26.03.2012
	 * @throws myException
	 */
	private void readSaldoUndAvgPreis_Ausgangssorte_Zum_Buchungstag() throws myException{

		//
		// Ermitteln des Preises für die Menge zum Buchungstag
		// 
		
		// Menge
		BigDecimal dMenge = bibALL.convertTextToBigDecimal(tfMengeZiel.getText());
		
		// Umbuchungspreis zum Buchungsdatum Preis lesen
		m_oPreis_Buchungsdatum = getLagerPreisZumBuchungszeitpunkt(dMenge, tfDatum.get_oDBFormatedDateFromTextField() );
		
		this.tfPreisZiel.setText		( m_oPreis_Buchungsdatum != null 	? bibALL.convertBigDecimalToString(m_oPreis_Buchungsdatum.getPreisMitKosten(),2) 	: null) ;
		this.tfPreisZielKosten.setText	( m_oPreis_Buchungsdatum != null 	? bibALL.convertBigDecimalToString(m_oPreis_Buchungsdatum.getKostenanteil(),2) 		: null);
		
		
		//
		// Saldo-und Durchschnittspreis zum Buchungszeitpunkt ermitteln
		//
		
		// Sorte
		String sIDSorte = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		// Lager
		String sIDLager = null;
		try {
			sIDLager = new MyLong(oSelectFieldLagerQuelle.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
		} catch (myException e) {
			sIDLager = null;
		}

		// Gesamtmenge zum Buchungszeitraum
		BigDecimal bdMengeAll = this.readSaldoForSelectedLagerSorte(sIDLager,sIDSorte, tfDatum.get_oDBFormatedDateFromTextField());
		this.tfSaldo_Buchungsdatum.setText((bdMengeAll != null ? bibALL.convertBigDecimalToString(bdMengeAll,3) : null));
		
		
		// Durchschnittspreis der Gesamtmenge zum Buchungszeitraum
		ATOM_LAG_BEW_DataRowStatus oRow =	this.readLagerstatusForSelectedLagerSorte(sIDLager, sIDSorte, tfDatum.get_oDBFormatedDateFromTextField());
		BigDecimal bdPreisAll = null;

		BigDecimal bdKosten = null;
		if (oRow != null){
			bdPreisAll = oRow.getAvg_Restwert_Menge_Preise_nicht_Null();
			bdKosten = oRow.get_Kosten_Restwert_Menge_Preise_nicht_Null();
		}
		
		this.tfSaldo_Buchungsdatum_AvgPreis.setText((bdPreisAll != null ? bibALL.convertBigDecimalToString(bdPreisAll,2) : null));
		this.tfSaldo_Buchungsdatum_Kostenanteil.setText((bdKosten != null ? bibALL.convertBigDecimalToString(bdKosten,2) : null));

	}
	
	
	
	
	
	
	
	/**
	 * Ermittelt den Saldo aus der vorgegebenen Sorte zum angegebenen Buchungsdatum
	 * aus der Saldo-Ermittlungs-Logik
	 * 
	 * Author: manfred
	 * 
	 * 05.05.2014
	 * @return true,
	 */
	private BigDecimal readSaldoForSelectedLagerSorte(String sIDLager, String sIDSorte, String Buchungsdatum){
		BigDecimal bdRet = null;

		if (sIDSorte == null || sIDLager == null){
			return bdRet;
		}
		
		ATOM_LagerSaldoErmittlung oATOM_LagerSaldo = new ATOM_LagerSaldoErmittlung();
		try {
			// Lagersaldo am ende des Tages ohne Inventurbuchungen ermitteln
			oATOM_LagerSaldo.readSaldoDaten (sIDLager, sIDSorte, null,Buchungsdatum, false );
			ATOM_SaldoDaten oSaldo = oATOM_LagerSaldo.getData(sIDLager, sIDSorte);
			bdRet = oSaldo.get_Saldo();
		} catch (myException e) {
			e.printStackTrace();
		}
				
		return bdRet;
	}

		
	
	
	/**
	 * Ermittelt die Durchschnittslagerpreise aus der vorgegebenen Sorte zum angegebenen Buchungsdatum
	 * 
	 * Author: manfred
	 * 
	 * 05.05.2014
	 * @return 
	 */
	private ATOM_LAG_BEW_DataRowStatus readLagerstatusForSelectedLagerSorte(String sIDLager, String sIDSorte, String Buchungsdatum){
		ATOM_LAG_BEW_DataRowStatus oRow = null;

		if (sIDSorte == null || sIDLager == null){
			return oRow;
		}

		
		ATOM_LAG_BEW_StatusErmittlung_Ext oStatusExt = new ATOM_LAG_BEW_StatusErmittlung_Ext(m_bKostenberuecksichtigung);
		try {
			oRow = oStatusExt.ErmittleLagerstatus(sIDLager, null, sIDSorte, Buchungsdatum);
		} catch (myException e1) {
			e1.printStackTrace();
		}
		
		return oRow;
	}
	

	
	
	
	
	
	/**
	 * Ermittelt die Mengen und Preiseinheiten der Quellsorte
	 */
	private void setEinheitAusgangssorte(){

		String sIDSorteQuelle = null;

		sIDSorteQuelle = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		
		RECORD_EINHEIT oEinheitSaldo = null;
		RECORD_EINHEIT oEinheitPreis = null;
		String 			sEinheitSaldo = "?";
		String 			sEinheitMenge = "?";
		String 			sEinheitPreis = "?";

		try
		{
			RECORD_ARTIKEL o = new RECORD_ARTIKEL(sIDSorteQuelle);
			oEinheitSaldo = o.get_UP_RECORD_EINHEIT_id_einheit();
			sEinheitSaldo = oEinheitSaldo.get_EINHEITKURZ_cUF();
			
			oEinheitPreis = o.get_UP_RECORD_EINHEIT_id_einheit_preis();
			sEinheitPreis = oEinheitPreis.get_EINHEITKURZ_cUF();
			sEinheitMenge = sEinheitSaldo;
			
			lblSaldo_EinheitMenge.set_Text(sEinheitMenge);
			lblSaldo_Buchungsdatum_EinheitMenge.set_Text(sEinheitMenge);
			lblSaldo_EinheitPreis.set_Text(" / " + sEinheitPreis);
			lblSaldo_Buchungsdatum_EinheitPreis.set_Text(" / " + sEinheitPreis);
			
			lblEinheitenumbuchung_Einheit.set_Text( sEinheitMenge);
			

		} catch (myException e1)
		{
			lblSaldo_EinheitMenge.set_Text("?");
			lblSaldo_EinheitPreis.set_Text(" / ?");
			lblSaldo_Buchungsdatum_EinheitMenge.set_Text("?");
			lblSaldo_Buchungsdatum_EinheitPreis.set_Text(" / ?");
			
			lblEinheitenumbuchung_Einheit.set_Text("?");
			
		}
	}

	
	

	

	/**
	 * Ermittelt die Einheiten für Menge und Preis
	 * @author manfred
	 * @date  
	 */
	private void setEinheitMengePreisZiel(){

		String sIDSorteQuelle = null;
		String sIDSorteZiel = null;

		sIDSorteQuelle = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT)){
			sIDSorteZiel = new MyLong(oSearchSorteZiel.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		}
		
		
		RECORD_EINHEIT oEinheitZiel = null;
		RECORD_EINHEIT oEinheitPreis = null;
		String 			sEinheitMenge = "?";
		String 			sEinheitPreis = "?";

		try
		{
			RECORD_ARTIKEL o = new RECORD_ARTIKEL(sIDSorteQuelle);
			
			if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT)){
				
				try
				{
					RECORD_ARTIKEL oZ = new RECORD_ARTIKEL(sIDSorteZiel);
					oEinheitZiel = oZ.get_UP_RECORD_EINHEIT_id_einheit();
					sEinheitMenge  = oEinheitZiel.get_EINHEITKURZ_cUF();
					
					oEinheitPreis = oZ.get_UP_RECORD_EINHEIT_id_einheit_preis();
					sEinheitPreis = oEinheitPreis.get_EINHEITKURZ_cUF();
				} catch (Exception e)
				{
					// nix machen, da noch kein Preis da ist...
				}
			} else {
				oEinheitPreis = o.get_UP_RECORD_EINHEIT_id_einheit_preis();
				sEinheitPreis = oEinheitPreis.get_EINHEITKURZ_cUF();
				oEinheitZiel = o.get_UP_RECORD_EINHEIT_id_einheit();
				sEinheitMenge = oEinheitZiel.get_EINHEITKURZ_cUF();
				
			}
			
			lblEinheitMenge.set_Text(sEinheitMenge);
			lblEinheitPreis.set_Text(" / " + sEinheitPreis);
			
			
		} catch (myException e1)
		{
			lblEinheitMenge.set_Text("?");
			lblEinheitPreis.set_Text("?");
		}
		
			
		
	}
	
	
	

	
	private cPreis getLagerPreisZumBuchungszeitpunkt( BigDecimal dMenge, String sDatum) throws myException{
		String sIDLager 	= null;
		String sIDSorte 	= null;
		BigDecimal dPreis	= null;
		cPreis     oRet     = new cPreis();
		
		// Sorte
		sIDSorte = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		
		// Lager
		try {
			sIDLager = new MyLong(oSelectFieldLagerQuelle.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
		} catch (myException e) {
			sIDLager = null;
		}
		
		
		// Ermitteln für welchen Tag die Lagerbuchung ermittelt werden soll
		// da Buchungen in der Vergangenheit ganz andere Preise haben können
		// und die können aus dem Setzkasten nachträglich nicht mehr korrekt ermittelt werden.
		if (bibALL.isEmpty(sDatum)){
			sDatum = null;
		} 
		
		if (sIDSorte != null && sIDLager != null){
			ATOM_LAG_Mengenermittlung_ext oMenge = new ATOM_LAG_Mengenermittlung_ext(m_bKostenberuecksichtigung);
			oMenge.initMengenermittlung(sIDLager, null, sIDSorte, sDatum);
			oMenge.ermittleDurchschnittspreisVonMenge(dMenge, true);
			
			oRet.setPreisMitKosten(oMenge.get_ErmittelterPreis());
			oRet.setPreisNetto(oMenge.get_ermittelterPreisOhneKosten());
		}
		
		return oRet;
	}

	
	
	
	
	
	/**
	 * prüft ob die Lager gleich sind. wenn unterschiedlich, wird das Ziellager ROT hinterlegt
	 * @author manfred
	 * @date   30.03.2012
	 */
	private void pruefeLagerGleichheitUndWarne(){
		
	    String id_quelle = getIDLagerQuelle();
	    String id_ziel = getIDLagerZiel();
		
		// Lager auf Ziellager kopieren, wenn null
		if ( bIsLagerumbuchungErlaubt() ){
			
			if (id_quelle != null && id_ziel.equals("0") && !m_bZiellagerIsDirty ){
				oSelectFieldLagerZiel.set_ActiveValue_OR_FirstValue(id_quelle);
				id_ziel = id_quelle;
			} 
		
			if ( id_quelle != null && id_ziel != null && id_quelle.equals(id_ziel)){
				oSelectFieldLagerZiel.setBackground(Color.WHITE);
			} else {
				oSelectFieldLagerZiel.setBackground(new E2_ColorAlarm()) ;
			}
		} else {
			oSelectFieldLagerZiel.set_ActiveValue_OR_FirstValue(id_quelle);
		}
	}
		
	
	
	
	/**
	 * Gibt die aktuelle ID des Lagers zurück.
	 * Im Fehlerfall "0"
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private String getIDLagerQuelle (){
		String id_quelle = "0";

		try {
			id_quelle = new MyLong(oSelectFieldLagerQuelle.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
		} catch (myException e) {
			id_quelle = "0";
		}
		return id_quelle;
	}
	
	/**
	 * Gibt die aktuelle ID des Lagers zurück.
	 * Im Fehlerfall "0"
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private String getIDLagerZiel () {
		String id_ziel = "0";
		try {
			id_ziel = new MyLong(oSelectFieldLagerZiel.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
		} catch (myException e) {
			id_ziel = "0";
		}
		return id_ziel;
	}
	
	
	
	/**
	 * Gibt true zurück, wenn die Lagerumbuchung erlaubt ist.
	 * Folgende Bedingungen müssen erfüllt sein:
	 *  -* LAGER_UMBUCHUNGEN_ERLAUBEN muss im Mandanten-Zusatz gesetzt sein
	 *  -* Der Benutzer muss Supervisor sein
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private boolean bIsLagerumbuchungErlaubt(){
		
		// nur wenn der Schalter im Mandantenzusatz gesetzt ist
		boolean bErlaubt = __RECORD_MANDANT_ZUSATZ.IS__Value("LAGER_UMBUCHUNGEN_ERLAUBEN", "N", "N");
		
		// nur Supervisor
		bErlaubt = bErlaubt && bibALL.get_bIST_SUPERVISOR();
		
		return bErlaubt;
	}
	
	
	
	private void doSaveBuchung() throws myException{
		if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT)){
			// Umbuchung
			doSave_Umbuchung();
		} else {
			// Korrekturbuchung
			doSave_Korrekturbuchung();
		}
	}
	

	/**
	 * Führt den Speichervorgang für die Korrektur/ -Umbuchung durch
	 * 
	 *
	 * Autor:	 manfred
	 * Erstellt: 26.02.2014
	 * @throws myException 
	 *
	 */
	private void doSave_Umbuchung() throws myException{
		// wenn mal speichern gedrückt wurde, dann ist der Zustand "dirty"
		m_bIsDirty = true;
		
		String sIDLagerQuelle = null;
		String sIDLagerZiel = null;
	
		String sIDSorte = null;
		String sSorteQuelle = null;
		
		String sIdSorteZiel =  null;
		String sSorteZiel = null;
		
		String sBemerkung = null;
		String sDatum = null;
		
		BigDecimal dMengeAbbuchung 				= BigDecimal.ZERO;
		BigDecimal dPreisAbbuchung 				= BigDecimal.ZERO;
		BigDecimal dPreisAbbuchung_Kostenanteil  = BigDecimal.ZERO;
		BigDecimal dPreisAbbuchung_KostenGesamt = BigDecimal.ZERO;
		
		BigDecimal dMenge 				= BigDecimal.ZERO;
		BigDecimal dPreis 				= BigDecimal.ZERO;
		BigDecimal dPreis_Kostenanteil   = BigDecimal.ZERO;
		BigDecimal dPreis_KostenGesamt 	= BigDecimal.ZERO;
		
		bibMSG.add_MESSAGE(vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
		
		// Einheiten
		String sEinheitQuelle = lblSaldo_EinheitMenge.get_oTextObject().toString();
		String sEinheitZiel = lblEinheitMenge.get_oTextObject().toString();
		
		// Menge und Preis lesen
		dPreis 					= bibALL.convertTextToBigDecimal(tfPreisZiel.getText());
		dPreis_Kostenanteil 	= bibALL.convertTextToBigDecimal(tfPreisZielKosten.getText());
		dMenge 					= bibALL.convertTextToBigDecimal(tfMengeZiel.getText());

		
		if (!sEinheitQuelle.equals(sEinheitZiel)){
			if (!cbEinheitenUmbuchung.isSelected() || 
				tfEinheitenumbuchung_FaktorMenge.getText().trim().isEmpty() || 
				tfEinheitenumbuchung_FaktorPreis.getText().trim().isEmpty() || 
				tfEinheitenumbuchung_MengeAbbuchung.getText().trim().isEmpty() ){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur eine Umbuchung stattfinden, wenn die Mengeneinheiten identisch sind, oder eine Einheiten-Umrechnung durchgeführt wird.")));
			}
		}
		
		
		if (dMenge == null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine gültige Menge eingetragen werden.")));
		} else if (dMenge.compareTo(BigDecimal.ZERO) <= 0){
			// und die Menge muss positiv sein
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine positive Menge angegeben werden!")));
		}
		
		// lager lesen und prüfen
		sIDLagerQuelle = getIDLagerQuelle();
		if (sIDLagerQuelle == null || sIDLagerQuelle.equals("0")){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Lager ausgewählt werden!")));
		}
		
		// Sorte lesen und prüfen
		sIDSorte = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		sSorteQuelle = oSearchSorteQuelle.get_oTextForAnzeige().getText();
		if (sIDSorte == null || sIDSorte.equals("0")){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Sorte ausgewählt werden!")));
		}
		
		sIDLagerZiel  = getIDLagerZiel();
		if (sIDLagerZiel == null || sIDLagerZiel.equals("0")){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Ziel-Lager ausgewählt werden!")));
		}
		
		BigDecimal dMengeAusgangskonto = bibALL.convertTextToBigDecimal(tfSaldo_Buchungsdatum.getText());
		if ( dMengeAusgangskonto == null || dMengeAusgangskonto.compareTo(BigDecimal.ZERO) < 0 ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es darf keine Umbuchung stattfinden, wenn das Ausgangslager negativen Bestand hat.")));
		}
		

		
		
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		
		RECORD_ARTIKEL oRec = new RECORD_ARTIKEL(sIDSorte);
		BigDecimal bdDivisor = oRec.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
		
		// Preis und Kostenanteil korrigieren
		if(dMenge!= null && dMenge.compareTo(BigDecimal.ZERO)!=0){
			dPreis_KostenGesamt = dPreis_Kostenanteil != null ? dPreis_Kostenanteil.multiply(dMenge).divide(bdDivisor)  : BigDecimal.ZERO;
			dPreis 				= (dPreis!=null && dPreis_Kostenanteil != null) ? dPreis.subtract(dPreis_Kostenanteil) : dPreis; 
		}		
					
		// keine Umbuchung wenn der Ausgangsbestand negativ ist.
		// nochmal den Saldo neu ermitteln
		readSaldoUndAvgPreis_Ausgangssorte_Absolut();


		
		
		// Preise für die Abbuchung erst mal gleich setzen
		dPreisAbbuchung = dPreis;
		dPreisAbbuchung_Kostenanteil = dPreis_Kostenanteil;
		dPreisAbbuchung_KostenGesamt = dPreis_KostenGesamt;
		
		dMengeAbbuchung = dMenge;
		

		if (cbProzentual.isSelected()){
			// prüfen ob prozentuale Abbuchung
			bdDivisor = oRec.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
			
			dPreisAbbuchung = bibALL.convertTextToBigDecimal(tfPreis_Abbuchung.getText());
			dPreisAbbuchung_Kostenanteil = bibALL.convertTextToBigDecimal(tfKostenanteil_Abbuchung.getText());
			dMengeAbbuchung = bibALL.convertTextToBigDecimal(tfMengeAbbuchung.getText());
			
			// jetzt die Abbuchungskosten anpassen:
			// Preis und Kostenanteil korrigieren
			if(dMengeAbbuchung!= null && dMengeAbbuchung.compareTo(BigDecimal.ZERO)!=0){
				
				dPreisAbbuchung_KostenGesamt 	= dPreisAbbuchung_Kostenanteil != null ? dPreisAbbuchung_Kostenanteil.multiply(dMengeAbbuchung).divide(bdDivisor)  : BigDecimal.ZERO;
				dPreisAbbuchung 				= (dPreisAbbuchung!=null && dPreisAbbuchung_Kostenanteil != null) ? dPreisAbbuchung.subtract(dPreisAbbuchung_Kostenanteil) : dPreisAbbuchung; 
			}	
			
		} else if (cbEinheitenUmbuchung.isSelected()){
			// oder Einheitenumrechnung
			
			bdDivisor = bibALL.convertTextToBigDecimal(tfEinheitenumbuchung_FaktorMenge.getText());
			BigDecimal bdDivisorPreis = bibALL.convertTextToBigDecimal(tfEinheitenumbuchung_FaktorPreis.getText());
			
			dPreisAbbuchung = bibALL.convertTextToBigDecimal(tfSaldo_Buchungsdatum_AvgPreis.getText());
			dPreisAbbuchung_Kostenanteil = bibALL.convertTextToBigDecimal(tfSaldo_Buchungsdatum_Kostenanteil.getText());
			dMengeAbbuchung = bibALL.convertTextToBigDecimal(tfEinheitenumbuchung_MengeAbbuchung.getText());
			
			dMengeAbbuchung = (dMengeAbbuchung == null ? BigDecimal.ZERO : dMengeAbbuchung);
			
			// jetzt die Abbuchungskosten anpassen:
			// Preis und Kostenanteil korrigieren
			if(dMengeAbbuchung!= null && dMengeAbbuchung.compareTo(BigDecimal.ZERO)!=0){
				dPreisAbbuchung_KostenGesamt 	= dPreisAbbuchung_Kostenanteil != null ? dPreisAbbuchung_Kostenanteil.multiply(dMengeAbbuchung).divide(bdDivisor)  : BigDecimal.ZERO;
				dPreisAbbuchung 				= (dPreisAbbuchung!=null && dPreisAbbuchung_Kostenanteil != null) ? dPreisAbbuchung.subtract(dPreisAbbuchung_Kostenanteil) : dPreisAbbuchung; 
			}	
		}
		
		
		// prüfen, ob die Lagermenge des Ausgangskontos - der Umbuchungsmenge < 0 ist
		if ( dMengeAusgangskonto.subtract(dMengeAbbuchung).compareTo(BigDecimal.ZERO) < 0){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es darf keine Umbuchung stattfinden, wenn das Ausgangslager einen negativen Bestand erhalten wird.")));
		}
		
		// Abbruch, wenn Fehler
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		
		// Zeit und Datum lesen
		sDatum = myDateHelper.ChangeNormalString2DBFormatString( tfDatum.get_cString4Database() );

			
		// Zielsorte muss da sein
		sIdSorteZiel =  new MyLong(oSearchSorteZiel.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		sSorteZiel = oSearchSorteZiel.get_oTextForAnzeige().getText();
		
		if (sIdSorteZiel == null || sIdSorteZiel.equals("0") ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Ziel-Sorte ausgewählt werden!")));
		}
		
		// negative Mengen können nicht verarbeitet werden!!
		if (dMengeAbbuchung.compareTo(BigDecimal.ZERO) <= 0){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine positive Menge angegeben werden!")));
		}

		
		sBemerkung = tfBemerkung.get_cText();
		
		if (!bibMSG.get_bHasAlarms()) {
			
			JtBewegung       oBewegung = new JtBewegung();
				
			sSorteZiel = new MyString("Geht nach Zielsorte").CTrans() + " " + sSorteZiel + System.getProperty("line.separator") ;
			sSorteQuelle = new MyString("Kommt von Ausgangssorte").CTrans() + " " + sSorteQuelle + System.getProperty("line.separator");
			
			Long lID = getIdAdresseBesitzer(sIDLagerQuelle);
			String sIDBesitzerQuelle = ((lID != null) ? lID.toString() : null);

			lID = getIdAdresseBesitzer(sIDLagerZiel);
			String sIDBesitzerZiel = ((lID != null) ? lID.toString() : null);
			
			oBewegung =  generiereBewegung_Umbuchung(
							sIDLagerQuelle,
							sIDBesitzerQuelle,
							sIDSorte,
							dMengeAbbuchung,
							dPreisAbbuchung,
							dPreisAbbuchung_Kostenanteil,
							dPreisAbbuchung_KostenGesamt,
							sDatum,
							sSorteZiel + sBemerkung,
							sIDLagerZiel,
							sIDBesitzerZiel,
							sIdSorteZiel,
							dMenge, 
							dPreis,
							dPreis_Kostenanteil,
							dPreis_KostenGesamt,
							sSorteQuelle + sBemerkung );

			
			
			//
			// Beim schreiben des Vektors muss in der Transaktion die Vektorid abgefragt weden, damit das anschliessende Statement korrekt ausgeführt werden kann.
			//
			BL_BEWEGUNG_HANDLER oBewegungHandler = new BL_BEWEGUNG_HANDLER();
			Vector<String> vSqlStatements = oBewegungHandler.getSQLForBewegung(oBewegung);
			
			// Ausführen der Statements
			executeSql(vSqlStatements);
		}
	}
	
	
	/**
	 * Führt den Speichervorgang für die Korrektur/ -Umbuchung durch
	 * 
	 *
	 * Autor:	 manfred
	 * Erstellt: 26.02.2014
	 * @throws myException 
	 *
	 */
	private void doSave_Korrekturbuchung() throws myException{
		// wenn mal speichern gedrückt wurde, dann ist der Zustand "dirty"
		m_bIsDirty = true;
		
		String sIDLagerQuelle = null;
		String sIDSorte = null;
		String sBemerkung = null;
		String sDatum = null;
		
		BigDecimal dMenge 				= BigDecimal.ZERO;
		BigDecimal dPreis 				= BigDecimal.ZERO;
		BigDecimal dPreis_Kostenanteil   = BigDecimal.ZERO;
		BigDecimal dPreis_KostenGesamt 	= BigDecimal.ZERO;

		// prüfen, ob alle Felder korrekt validiert werden
		bibMSG.add_MESSAGE(vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
		
		
		// Menge und Preis lesen
		dPreis 					= bibALL.convertTextToBigDecimal(tfPreisZiel.getText());
		dPreis_Kostenanteil 	= bibALL.convertTextToBigDecimal(tfPreisZielKosten.getText());
		dMenge 					= bibALL.convertTextToBigDecimal(tfMengeZiel.getText());

		// eine einfache Korrekturbuchung darf nur vom Supervisor durchgeführt werden!
		if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH)){
			if (!bibALL.get_bIST_SUPERVISOR()){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine einfache Korrekturbuchung darf nur durch den Supervisor erzeugt werden!")));
			}
		}
		
		if (dMenge == null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine gültige Menge eingetragen werden.")));
		} else if (dMenge.compareTo(BigDecimal.ZERO) <= 0){
			// und die Menge muss positiv sein
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine positive Menge angegeben werden!")));
		}
		
		// lager lesen und prüfen
		sIDLagerQuelle = getIDLagerQuelle();
		if (sIDLagerQuelle == null || sIDLagerQuelle.equals("0")){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Lager ausgewählt werden!")));
		}
		
		// Sorte lesen und prüfen
		sIDSorte = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		if (sIDSorte == null || sIDSorte.equals("0")){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Sorte ausgewählt werden!")));
		}
		
		// es muss zu- oder Abbuchung definiert sein, wenn es eine einfache Korrekturbuchung ist (keine Standardmäßige auswahl)
		if (bIsKorrekturbuchung()){
			if (!bIsBuchungEineAbbuchung() && !bIsBuchungEineZubuchung()){
				// es wurde keine Checkbox gesetzt
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Zu- oder Abbuchung sein. Bitte wählen sie die korrekte Buchungsart aus.")));
			}
		}
		
		
		// Abbruch, wenn Fehler
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		
		RECORD_ARTIKEL oRec = new RECORD_ARTIKEL(sIDSorte);
		BigDecimal bdDivisor = oRec.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
		
		// Preis und Kostenanteil korrigieren
		if(dMenge!= null && dMenge.compareTo(BigDecimal.ZERO)!=0){
			dPreis_KostenGesamt = dPreis_Kostenanteil != null ? dPreis_Kostenanteil.multiply(dMenge).divide(bdDivisor)  : BigDecimal.ZERO;
			dPreis 				= (dPreis!=null && dPreis_Kostenanteil != null) ? dPreis.subtract(dPreis_Kostenanteil) : dPreis; 
		}		
		
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		// Zeit und Datum lesen
		sDatum = myDateHelper.ChangeNormalString2DBFormatString( tfDatum.get_cString4Database() );

		// 0-Mengen können nicht verarbeitet werden!!
		if (dMenge.compareTo(BigDecimal.ZERO) == 0){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Menge angegeben werden!")));
		}
	
		sBemerkung = tfBemerkung.get_cText();
		
		if (!bibMSG.get_bHasAlarms()) {
			
			JtBewegung       oBewegung = new JtBewegung();
			
			
			if (m_enumDisplayOption.equals(EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH)){
				Long lID = getIdAdresseBesitzer(sIDLagerQuelle);
				String sIDBesitzer = ((lID != null) ? lID.toString() : null);
				oBewegung = generiere_Bewegung_Korrekturbuchung(
								sIDLagerQuelle, 
								sIDBesitzer, 
								sIDSorte, 
								dMenge, 
								dPreis, 
								dPreis_Kostenanteil,
								dPreis_KostenGesamt,
								sDatum, 
								bIsBuchungEineZubuchung(), 
								sBemerkung);
				
			} 
			
			BL_BEWEGUNG_HANDLER oBewegungHandler = new BL_BEWEGUNG_HANDLER();
			
			//
			// Beim schreiben des Vektors muss in der Transaktion die Vektorid abgefragt weden, damit das anschliessende Statement korrekt ausgeführt werden kann.
			//
			Vector<String> vSqlStatements = oBewegungHandler.getSQLForBewegung(oBewegung);
			
			// Ausführen der Statements
			executeSql(vSqlStatements);
			
		}
	}

	
	
	private void executeSql(Vector<String> vSqlStatements) throws myException {
		// ausführen der Statements
		boolean bRet = false;
		bRet = bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(vSqlStatements, false);
		if (bRet){
			String sID = bibDB.EinzelAbfrage("SELECT seq_bewegung_vektor.currval FROM DUAL");

			String sSqlUpdate = 
					" UPDATE  " + bibE2.cTO() + "." + RECORD_BEWEGUNG_VEKTOR.TABLENAME + " v SET " +
					" 		 v.KOSTEN_TRANSPORT_WE 	= nvl((select sum(k.kosten_gesamt) from " + bibE2.cTO() + "." + RECORD_BEWEGUNG_ATOM_KOSTEN.TABLENAME + " k WHERE k.ID_BEWEGUNG_VEKTOR = v.ID_BEWEGUNG_VEKTOR and upper(k.IST_WARENEINGANG) = 'Y' ),0) " +
					" 		,v.KOSTEN_TRANSPORT_WA  = nvl((select sum(k.kosten_gesamt) from " + bibE2.cTO() + "." + RECORD_BEWEGUNG_ATOM_KOSTEN.TABLENAME + " k WHERE k.ID_BEWEGUNG_VEKTOR = v.ID_BEWEGUNG_VEKTOR and upper(k.IST_WARENEINGANG) = 'N' ),0)  " +
					" WHERE v.ID_BEWEGUNG_VEKTOR  = " + sID;
;
			bRet &= bibDB.ExecSQL(sSqlUpdate, false);
		}
		
		if (bRet){
			bibDB.Commit();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Buchung wurde ausgeführt.")));
			CLOSE_AND_DESTROY_POPUPWINDOW(true);
		} else {
			bibDB.Rollback();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist ein Fehler bei der Buchung aufgetreten!")));
		}
	}
	
	
	
	
	
	
	
	/**
	 * Generieren einer Bewegung für eine einfache Korrekturbuchung
	 * @author manfred
	 * @date   24.09.2012
	 * @param oRec
	 * @param posnrAtomInVector
	 * @return
	 * @throws myException
	 */
	private JtBewegung generiere_Bewegung_Korrekturbuchung (String sIDLager, 
				String sIDBesitzer,
				String sIDSorte, 
				BigDecimal bdMenge, 
				BigDecimal bdPreis, 
				BigDecimal bdPreis_KostenEinzel,
				BigDecimal bdPreis_KostenGesamt,
				String sBuchungsdatumISO,
				boolean bZubuchung, 
				String sBemerkung
			) throws myException{
		
		JtBewegung_Vektor oVec = new JtBewegung_Vektor();

		// die Bewegung
		JtBewegung oBew = new JtBewegung();
		
		// Bewegung-Satz
		oBew.setIdMandant(Long.parseLong(bibALL.get_ID_MANDANT()));
		oBew.setErzeugtAm(new Date());
		oBew.setErzeugtVon(bibALL.get_KUERZEL());
		oBew.setGeaendertVon(bibALL.get_KUERZEL());
		oBew.setLetzteAenderung(new Date());
		oBew.setIdBewegung("SEQ_BEWEGUNG.NEXTVAL");
		
		
		oBew.getJtBewegungsvektors().add(oVec);
		
		
		String idBuchung = null;
		
		oVec.setJtBewegung(oBew);
		oVec.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		
		oVec.setIdMandant(Long.parseLong(bibALL.get_ID_MANDANT()));
		
		// userinfo
		oVec.setErzeugtAm(new Date());
		oVec.setLetzteAenderung(new Date());
		oVec.setErzeugtVon(bibALL.get_KUERZEL());
		oVec.setGeaendertVon(bibALL.get_KUERZEL());
		
		oVec.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		
		//oVec.setVariante(ATOM_Const.VEKTOR_VARIANTE_KORR_HAND);
		oVec.setVariante(ENUM_VEKTOR_TYP.KORR.name());
		oVec.setPosnr(1L);

		// die 1. Bewegungsvektorposition erzeugen und anhängen 
		JtBewegung_Vektor_Pos oVecPos = new JtBewegung_Vektor_Pos(oVec);
		oVec.getJtBewegungVektorPoss().add(oVecPos);
		oVecPos.setPosnr(1L);
	
		
		JtBewegung_Atom oAtom = new JtBewegung_Atom();
		
		oAtom.setJtBewegung(oBew);
		oAtom.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		
		// id_lager_konto
		oAtom.setIdLagerKonto(null);
		oAtom.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		
		oAtom.setJtBewegungsvektor(oVec);
		oAtom.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");

		oAtom.setJtBewegungsvektorPos(oVecPos);
		oAtom.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
		
		oAtom.setIdMandant(Long.parseLong(bibALL.get_ID_MANDANT()));
		oAtom.setPosnr(new Long(1));
		
		// userinfo
		oAtom.setErzeugtAm(oVec.getErzeugtAm().dValue);
		oAtom.setErzeugtVon(oVec.getErzeugtVon().sValue);
		oAtom.setGeaendertVon(oVec.getGeaendertVon().sValue);
		oAtom.setLetzteAenderung(new Date());
		
		oAtom.setAbgerechnet(null);
		oAtom.setAbrechenbar(null);
		
		// Datum
		oAtom.setLeistungsdatum(bibDate.String2Date(sBuchungsdatumISO) );
		
		// Sorte und die dazugehörige 1. artikelbez lesen
		oAtom.setIdArtikel(Long.parseLong(sIDSorte));
		
		Long lIDArtikelBez = null;
		String sARTBez1 = null;
		lIDArtikelBez = getArtikelBezID_For_ArtikelID(sIDSorte);
		sARTBez1 = getArtbez_For_ArtikelID(sIDSorte);
		
		oAtom.setIdArtikelBez(lIDArtikelBez);
		oAtom.setArtbez1(sARTBez1);
		
		// Mengen
		oAtom.setPlanmenge(bdMenge.abs() );
		oAtom.setMenge(bdMenge.abs());
		
		oAtom.setPruefungMenge("Y");
		oAtom.setPruefungMengeAm(oAtom.getLeistungsdatum().dValue);
		oAtom.setPruefungMengeVon(oAtom.getErzeugtVon().sValue);
		
		// Preis
		oAtom.setEPreis(bdPreis);
		oAtom.setEPreisResultNettoMge(bdPreis);
		oAtom.setAbzugMenge(BigDecimal.ZERO);



		
		// Start-Station
		JtBewegung_Station oStationStart = new JtBewegung_Station(oAtom,-1);

		
		// Station ZIEL
		JtBewegung_Station oStationZiel = new JtBewegung_Station(oAtom,+1);
		
		
		// Zu- oder Abbuchung abhängig vom Buchungstyp
		// PROBLEM: Storno werden als negierten Wert im gleichen Buchungstyp durchgeführt
		Long lIDAdresseBasis = getIdAdresseBasis(sIDLager);
		
		if (bZubuchung){
			// WE
			oStationStart.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_KORREKTUR_HAND()));
			oStationStart.setIDAdresseBasis(lIDAdresseBasis);
			oStationStart.setIDAdresseBesitzer(Long.parseLong(sIDBesitzer));
			oStationStart.setKontrollmenge(bdMenge.abs());
			
			oStationZiel.setIDAdresse(Long.parseLong(sIDLager));
			oStationZiel.setIDAdresseBasis(lIDAdresseBasis);
			oStationZiel.setIDAdresseBesitzer(Long.parseLong(sIDBesitzer));
			oStationZiel.setKontrollmenge(bdMenge.abs());
			
			// Kosten
			if (bdPreis_KostenEinzel != null && bdPreis_KostenEinzel.compareTo(BigDecimal.ZERO)!= 0 ){
				REC_Bewegung_Atom_Kosten oKosten = new REC_Bewegung_Atom_Kosten("SEQ_BEWEGUNG_VEKTOR.CURRVAL","SEQ_BEWEGUNG_ATOM.CURRVAL");
				oKosten.set_Bemerkung("Kosten Hand-Umbuchung WA");
				oKosten.set_IstWareneingang("Y");
				oKosten.set_Pauschal("N");
				oKosten.set_Kostentyp("KH");
				oKosten.set_KostenEinzel(bdPreis_KostenEinzel);
				oKosten.set_KostenGesamt(bdPreis_KostenGesamt);
				oAtom.addRecKosten(oKosten);
			}
			
		} else {
			// WA
			oStationStart.setIDAdresse(Long.parseLong(sIDLager));
			oStationStart.setIDAdresseBasis(lIDAdresseBasis);
			oStationStart.setKontrollmenge(bdMenge.abs());
			oStationStart.setIDAdresseBesitzer(Long.parseLong(sIDBesitzer));

			oStationZiel.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_KORREKTUR_HAND()));
			oStationZiel.setIDAdresseBasis(lIDAdresseBasis);
			oStationZiel.setKontrollmenge(bdMenge.abs());
			oStationZiel.setIDAdresseBesitzer(Long.parseLong(sIDBesitzer));
			
			// Kosten
			if (bdPreis_KostenEinzel != null && bdPreis_KostenEinzel.compareTo(BigDecimal.ZERO)!= 0 ){
				REC_Bewegung_Atom_Kosten oKosten = new REC_Bewegung_Atom_Kosten("SEQ_BEWEGUNG_VEKTOR.CURRVAL","SEQ_BEWEGUNG_ATOM.CURRVAL");
				oKosten.set_Bemerkung("Kosten Hand-Umbuchung WA");
				oKosten.set_IstWareneingang("N");
				oKosten.set_Pauschal("N");
				oKosten.set_Kostentyp("KH");
				oKosten.set_KostenEinzel(bdPreis_KostenEinzel);
				oKosten.set_KostenGesamt(bdPreis_KostenGesamt);
				oAtom.addRecKosten(oKosten);
			}
		}

		
		// Bemerkungsfelder
		oAtom.setBemerkung(sBemerkung);

		// Atom in Vetor einbauen
		oVecPos.getJtBewegungsatoms().add(oAtom);

		return oBew;
	}

	
	
	/**
	 * Generierung einer Bewegung für eine Umbuchung
	 * @author manfred
	 * @date 05.10.2016
	 *
	 * @param sIDLager
	 * @param sIDBesitzer
	 * @param sIDSorte
	 * @param bdMenge
	 * @param bdPreis
	 * @param bdPreisKostenEinzel
	 * @param bdPreisKostenGesamt
	 * @param sBuchungsdatumISO
	 * @param sBemerkung
	 * @param sIDLagerZiel
	 * @param sIDBesitzerZiel
	 * @param sIDSorteZiel
	 * @param bdMengeZiel
	 * @param bdPreisZiel
	 * @param bdPreisZielKostenEinzel
	 * @param bdPreisZielKostenGesamt
	 * @param sBemerkungZiel
	 * @return
	 * @throws myException
	 */
	protected JtBewegung generiereBewegung_Umbuchung(
			String 		sIDLager, 
			String      sIDBesitzer,
			String 		sIDSorte, 
			BigDecimal 	bdMenge, 
			BigDecimal 	bdPreis, 
			BigDecimal  bdPreisKostenEinzel,
			BigDecimal  bdPreisKostenGesamt,
			String 		sBuchungsdatumISO,
			String 		sBemerkung,
			String 		sIDLagerZiel,
			String 		sIDBesitzerZiel,
			String 		sIDSorteZiel,
			BigDecimal 	bdMengeZiel,
			BigDecimal 	bdPreisZiel,
			BigDecimal  bdPreisZielKostenEinzel,
			BigDecimal  bdPreisZielKostenGesamt,
			String		sBemerkungZiel		) throws myException{

		// Eintrittsbedingung
		if (sIDLager == null && sIDLagerZiel == null) return null;
		
		// die Bewegung
		JtBewegung oBew = new JtBewegung();
		
		// Bewegung-Satz
		oBew.setIdMandant(Long.parseLong(bibALL.get_ID_MANDANT()));
		oBew.setErzeugtAm(new Date());
		oBew.setErzeugtVon(bibALL.get_KUERZEL());
		oBew.setGeaendertVon(bibALL.get_KUERZEL());
		oBew.setLetzteAenderung(new Date());
		oBew.setIdBewegung("SEQ_BEWEGUNG.NEXTVAL");
		
		// der Vektor
		JtBewegung_Vektor oVec = new JtBewegung_Vektor();
		oBew.getJtBewegungsvektors().add(oVec);
		
		
		oVec.setIdMandant( Long.parseLong(bibALL.get_ID_MANDANT()) );
		
		// userinfo
		oVec.setErzeugtAm(new Date());
		oVec.setLetzteAenderung(new Date());
		oVec.setErzeugtVon(bibALL.get_KUERZEL());
		oVec.setGeaendertVon(bibALL.get_KUERZEL());
		
		
		oVec.setJtBewegung(oBew);
		oVec.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		
		oVec.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.NEXTVAL");
		oVec.setPosnr(1L);

		oVec.setVariante(ENUM_VEKTOR_TYP.UMB.name());

		
		JtBewegung_Vektor_Pos oVecPos = new JtBewegung_Vektor_Pos(oVec);
		oVec.getJtBewegungVektorPoss().add(oVecPos);
		oVecPos.setPosnr(1L);
		
		
		// 1. Atom Umbuchung-Ausbuchung 
		
		Long atomposnr = (long)1;

		JtBewegung_Atom a1 = new JtBewegung_Atom();
		
		a1.setJtBewegung(oBew);
		a1.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		
		a1.setJtBewegungsvektor(oVec);
		a1.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
		
		a1.setJtBewegungsvektorPos(oVecPos);
		a1.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
		
		a1.setIdMandant(oVec.getIdMandant().lValue);
		
		a1.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		
		a1.setPosnr(atomposnr++);
		
		// ID-Lagerkonto
		a1.setIdLagerKonto(null );
		
		// userinfo
		a1.setErzeugtAm(oVec.getErzeugtAm().dValue);
		a1.setErzeugtVon(oVec.getErzeugtVon().sValue);
		a1.setGeaendertVon(oVec.getGeaendertVon().sValue);
		a1.setLetzteAenderung(oVec.getLetzteAenderung().dValue);
		
		
		// Datum
		a1.setLeistungsdatum(bibDate.String2Date(sBuchungsdatumISO) );

		// Sorte
		Long lIDArtikelBez = null;
		String sARTBez1 = null;

		lIDArtikelBez = getArtikelBezID_For_ArtikelID(sIDSorte);
		sARTBez1 = getArtbez_For_ArtikelID(sIDSorte);
		
		a1.setIdArtikel(Long.parseLong(sIDSorte));
		a1.setIdArtikelBez(lIDArtikelBez);
		a1.setArtbez1(sARTBez1);
		
		// Menge
		a1.setMenge(bdMenge.abs());
		a1.setPlanmenge(bdMenge.abs());
		a1.setAbzugMenge(BigDecimal.ZERO);
		
		a1.setPruefungMenge("Y");
		a1.setPruefungMengeAm(a1.getLeistungsdatum().dValue);
		a1.setPruefungMengeVon(a1.getErzeugtVon().sValue);
		
		// Preis
		a1.setEPreis(bdPreis);
		a1.setEPreisResultNettoMge(bdPreis);
		
		// Kosten
		if (bdPreisKostenEinzel != null && bdPreisKostenEinzel.compareTo(BigDecimal.ZERO)!= 0 ){
			REC_Bewegung_Atom_Kosten oKosten = new REC_Bewegung_Atom_Kosten("SEQ_BEWEGUNG_VEKTOR.CURRVAL","SEQ_BEWEGUNG_ATOM.CURRVAL");
			oKosten.set_Bemerkung("Kosten Hand-Umbuchung WA");
			oKosten.set_IstWareneingang("N");
			oKosten.set_Pauschal("N");
			oKosten.set_Kostentyp("UH");
			oKosten.set_KostenEinzel(bdPreisKostenEinzel);
			oKosten.set_KostenGesamt(bdPreisKostenGesamt);
			
			a1.addRecKosten(oKosten);
		}
		
		// Start-Station
		JtBewegung_Station oStationStart = new JtBewegung_Station(a1,-1);
		
		// Station ZIEL
		JtBewegung_Station oStationZiel = new JtBewegung_Station(a1,+1);
		
		Long lIDAdresseBasis = getIdAdresseBasis(sIDLager);
		
		oStationStart.setKontrollmenge(bdMenge.abs());
		oStationZiel.setKontrollmenge(bdMenge.abs());
		
		// WA
		oStationStart.setIDAdresse(Long.parseLong(sIDLager));
		oStationStart.setIDAdresseBasis(lIDAdresseBasis);
		oStationStart.setIDAdresseBesitzer(Long.parseLong(sIDBesitzer));
		
		oStationZiel.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND()));
		oStationZiel.setIDAdresseBesitzer(Long.parseLong(sIDBesitzer));
		oStationZiel.setIDAdresseBasis(lIDAdresseBasis);
		
		
		// Bemerkungsfelder
		a1.setBemerkung(sBemerkung);
		
		// das 1. Atom in den Vektor eintragen
		oVecPos.getJtBewegungsatoms().add(a1);
		
		
		JtBewegung_Atom a2 = new JtBewegung_Atom();
		
		a2.setJtBewegung(oBew);
		a2.setIdBewegung("SEQ_BEWEGUNG.CURRVAL");
		
		a2.setJtBewegungsvektor(oVec);
		a2.setIdBewegungsvektor("SEQ_BEWEGUNG_VEKTOR.CURRVAL");
		
		a2.setJtBewegungsvektorPos(oVecPos);
		a2.setIdBewegungsvektorPos("SEQ_BEWEGUNG_VEKTOR_POS.CURRVAL");
		
		a2.setIdMandant(oVec.getIdMandant().lValue);
		
		a2.setIdBewegungsatom("SEQ_BEWEGUNG_ATOM.NEXTVAL");
		
		a2.setPosnr(atomposnr++);
		
		// ID-Lagerkonto
		a2.setIdLagerKonto(null);
		
		// userinfo
		a2.setErzeugtAm(oVec.getErzeugtAm().dValue);
		a2.setErzeugtVon(oVec.getErzeugtVon().sValue);
		a2.setGeaendertVon(oVec.getGeaendertVon().sValue);
		a2.setLetzteAenderung(oVec.getLetzteAenderung().dValue);
		
		// Datum
		a2.setLeistungsdatum(bibDate.String2Date(sBuchungsdatumISO) );

		
		// Sorte
		lIDArtikelBez = null;
		sARTBez1 = null;
		lIDArtikelBez = getArtikelBezID_For_ArtikelID(sIDSorteZiel);
		sARTBez1 = getArtbez_For_ArtikelID(sIDSorteZiel);
		
		a2.setIdArtikel(Long.parseLong(sIDSorteZiel));
		a2.setIdArtikelBez(lIDArtikelBez);
		a2.setArtbez1(sARTBez1);
		a2.setArtbez2(null);
		
		// Menge
		a2.setMenge(bdMengeZiel.abs());
		a2.setPlanmenge(bdMengeZiel.abs());
		a2.setAbzugMenge(BigDecimal.ZERO);
		
		a2.setPruefungMenge("Y");
		a2.setPruefungMengeAm(a2.getLeistungsdatum().dValue);
		a2.setPruefungMengeVon(a2.getErzeugtVon().sValue);
		
		// Preis
		a2.setEPreis(bdPreisZiel);
		a2.setEPreisResultNettoMge(bdPreisZiel);
		
		// Kosten
		if (bdPreisZielKostenEinzel != null && bdPreisZielKostenEinzel.compareTo(BigDecimal.ZERO)!= 0 ){
			REC_Bewegung_Atom_Kosten oKosten = new REC_Bewegung_Atom_Kosten("SEQ_BEWEGUNG_VEKTOR.CURRVAL","SEQ_BEWEGUNG_ATOM.CURRVAL");
			oKosten.set_Bemerkung("Kosten Hand-Umbuchung WE");
			oKosten.set_IstWareneingang("Y");
			oKosten.set_Pauschal("N");
			oKosten.set_Kostentyp("UH");
			oKosten.set_KostenEinzel(bdPreisZielKostenEinzel);
			oKosten.set_KostenGesamt(bdPreisZielKostenGesamt);
			
			a2.addRecKosten(oKosten);
		}
		

		
		// Start-Station
		oStationStart = new JtBewegung_Station(a2,-1);
		
		// Station ZIEL
		oStationZiel = new JtBewegung_Station(a2,+1);
		
		
		oStationStart.setKontrollmenge(bdMengeZiel);
		oStationZiel.setKontrollmenge(bdMengeZiel);
		
		// WE
		oStationStart.setIDAdresse(Long.parseLong(bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND() ));
		oStationStart.setIDAdresseBasis(lIDAdresseBasis);
		oStationStart.setIDAdresseBesitzer(Long.parseLong(sIDBesitzerZiel));

		oStationZiel.setIDAdresse(Long.parseLong(sIDLagerZiel));
		oStationZiel.setIDAdresseBasis(lIDAdresseBasis);
		oStationZiel.setIDAdresseBesitzer(Long.parseLong(sIDBesitzerZiel));
		
		// Bemerkungsfelder
		a2.setBemerkung(sBemerkungZiel);
		
		
		
		// das 2. Atom in den Vektor eintragen
		oVecPos.getJtBewegungsatoms().add(a2);
		
		//
		// den Umbuchungsvektor zurückgeben
		//
		return oBew;
		
	}

	
	
	/**
	 * Ermittelt die ID-Adresse des Besitzer eines Lagers
	 * @param sIDAdresseLager
	 * @return
	 */
	protected Long getIdAdresseBesitzer(String sIDAdresseLager ){

		String sSQL = " SELECT DISTINCT NVL(V.ID_ADRESSE_FREMDWARE,V.ID_ADRESSE_BASIS) FROM " + bibE2.cTO() +".V_FIRMENADRESSEN_FLACH V "
					+ " WHERE V.ID_MANDANT = " + bibALL.get_ID_MANDANT()
					+ " AND V.ID_ADRESSE_LIEFER = " + sIDAdresseLager ;
				
		Long lIDRet = null;
		
		String sIDRet = bibDB.EinzelAbfrage(sSQL);
		if ( !bibALL.isEmpty(sIDRet) ){
			try {
				lIDRet = Long.parseLong(sIDRet);
			} catch (Exception e) {
				lIDRet = null;
			}
		}
		return lIDRet;
	}

	/**
	 * gibt die Basisadresse einer Lageradresse zurück
	 * @param sIDAdresseLager
	 * @return
	 *
	 * Autor:	 manfred
	 * Erstellt: 21.05.2014
	 *
	 */
	protected Long getIdAdresseBasis(String sIDAdresseLager ){

		String sSQL = " SELECT DISTINCT V.ID_ADRESSE_BASIS FROM " + bibE2.cTO() +".V_FIRMENADRESSEN_FLACH V "
					+ " WHERE V.ID_MANDANT = " + bibALL.get_ID_MANDANT()
					+ " AND V.ID_ADRESSE = " + sIDAdresseLager ;
				
		Long lIDRet = null;
		
		String sIDRet = bibDB.EinzelAbfrage(sSQL);
		if ( !bibALL.isEmpty(sIDRet) ){
			try {
				lIDRet = Long.parseLong(sIDRet);
			} catch (Exception e) {
				lIDRet = null;
			}
		}
		return lIDRet;
	}
	
	
	/**
	 * ermittelt die 1. Artikelbez-ID aus die zu einem Artikel gefunden wird (die 00-er Bezeichnung) und die Artbez dazu
	 * @author manfred
	 *	   
	 * @param sIDArtikelBez
	 * @return
	 * @throws myException 
	 */
	protected Long getArtikelBezID_For_ArtikelID(String sIDArtikel) {
		Long lIDArtikelBez = null;
		String sArtbez1 = null;
		
		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
		
		RECLIST_ARTIKEL_BEZ oArtBez = null;
		try {
			oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
			RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
			lIDArtikelBez = o.get_ID_ARTIKEL_BEZ_lValue(null);
			sArtbez1 = o.get_ARTBEZ1_cUF();
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return lIDArtikelBez;
	}

	protected String getArtbez_For_ArtikelID(String sIDArtikel) {
		String sArtbez1 = null;
		
		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
		
		RECLIST_ARTIKEL_BEZ oArtBez = null;
		try {
			oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
			RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
			sArtbez1 = o.get_ARTBEZ1_cUF();
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return sArtbez1;
	}
	
	
	/**
	 * Actionagent für die Checkbox Sortenumbuchung
	 * @author manfred
	 *
	 */
	private class actionChangeBuchungsTyp extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			if (oThis.cbSortenumbuchung.isSelected()){
				m_enumDisplayOption = EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT;
			} else {
				m_enumDisplayOption = EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH;
			}
			oThis.setBuchungsDisplayOption();
		}
	}

	
	
	private class actionSetProzentualeBerechnung extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			boolean bPct = oThis.cbProzentual.isSelected();
			
			if (bPct){
				oThis.cbEinheitenUmbuchung.setSelected(false);
				oThis.setUmrechnungStatus();
			}
			
			oThis.setProzentrechnungStatus();
			oThis.doProzentualeUmrechnung();
			oThis.setUmrechnungStatus();
			oThis.doEinheitenUmrechnung();
		}
		
	}
	
	private class actionBerechneProzentualeUmbuchung extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			oThis.doProzentualeUmrechnung();
		}
	}
	
	
	private class actionSetEinheitenUmrechnung extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			boolean bUmrechnung = oThis.cbEinheitenUmbuchung.isSelected();
			
			// die Prozentuale Umrechnung deaktivieren, wenn Einheitenumrechnung 
			if (bUmrechnung){
				oThis.cbProzentual.setSelected(false);
				oThis.setProzentrechnungStatus();
			} 
			
			
			oThis.setUmrechnungStatus();
			oThis.doEinheitenUmrechnung();
		}
		
	}
	
	
	private class actionBerechneEinheitenUmrechnung extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			oThis.doEinheitenUmrechnung();
		}
	}
	
	/**
	 * Eventhandler
	 * muss aufgerufen werden, um den aktuellen Saldo einer Lager/Sorte-Kombination zu ermitteln
	 * @author manfred
	 *
	 */
	private class actionAfterChangeOfSelectionQuellLager extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			
			oThis.initLagerbestandAbsolut();
			oThis.setEinheitMengePreisZiel();
			
			oThis.pruefeLagerGleichheitUndWarne();
			
			oThis.doProzentualeUmrechnung();
			
			oThis.doEinheitenUmrechnung();
			
		}
	}
	
	
	/**
	 * Eventhandler
	 * muss aufgerufen werden, um den aktuellen Saldo einer Lager/Sorte-Kombination zu ermitteln
	 * @author manfred
	 *
	 */
	private class actionAfterChangeOfSelectionZielLager extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;

			oThis.m_bZiellagerIsDirty = true;
			oThis.pruefeLagerGleichheitUndWarne();
			
			oThis.doProzentualeUmrechnung();
			
			oThis.doEinheitenUmrechnung();
		}
	}
	
	


	

	/**
	 * Eventhandler
	 * muss aufgerufen werden, um den aktuellen Saldo einer Lager/Sorte-Kombination zu ermitteln
	 * @author manfred
	 *
	 */	
	private class actionAfterFoundSorteZiel extends XX_MaskActionAfterFoundNonDB{

		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(	String maskVfalsealue, 
																MyE2_MaskSearchField searchField,	
																boolean afterAction) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			oThis.setEinheitMengePreisZiel();
			
		}
	}





	/**
	 * Eventhandler
	 * muss aufgerufen werden, um den aktuellen Saldo einer Lager/Sorte-Kombination zu ermitteln
	 * @author manfred
	 *
	 */	
	private class actionAfterFoundSorte extends XX_MaskActionAfterFoundNonDB{

		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(	String maskVfalsealue, 
																MyE2_MaskSearchField searchField,	
																boolean afterAction) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			
			oThis.readSaldoUndAvgPreis_Ausgangssorte_Zum_Buchungstag();
			oThis.setEinheitMengePreisZiel();
			
			// lesen des aktuellen Saldos
			oThis.initLagerbestandAbsolut();
			oThis.setProzentrechnungStatus();
			oThis.setUmrechnungStatus();
			
			
			oThis.doProzentualeUmrechnung();
			
			oThis.doEinheitenUmrechnung();
		}
		
	}
	
	
	
	
	/**
	 * Eventhandler-Klasse zum manuellen refreshen der Saldo-Stände
	 * @author manfred
	 *
	 */
	private class actionRefreshLagerPreis extends XX_ActionAgent
	{

		/***
		 * Ermitteln des aktuellen Saldos für die gewählte Lager/Sorte-Kombination	
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			
			oThis.readSaldoUndAvgPreis_Ausgangssorte_Zum_Buchungstag();
			oThis.setEinheitMengePreisZiel();
			
			// lesen des aktuellen Saldos
			oThis.initLagerbestandAbsolut();
			
			oThis.setProzentrechnungStatus();
			oThis.doProzentualeUmrechnung();

			oThis.setUmrechnungStatus();
			oThis.doEinheitenUmrechnung();
		}
	}
	
	
	
	
	
	private class actionSaveBuchung extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			doSaveBuchung();		
		}
	}

	
	
	
	
	/**
	 * Eventhandler-Klasse zum manuellen refreshen der Saldo-Stände
	 * @author manfred
	 *
	 */
	private class actionSetzkastenAktualisieren extends XX_ActionAgent
	{

		/***
		 * Aktualisiert den Setzkasten für die aktuelle Sorte	
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			ATOM_LAG_Umbuchung_Erfassung oThis = ATOM_LAG_Umbuchung_Erfassung.this;
			
			
			// lesen des aktuellen Saldos
			oThis.aktualisiereSetzkasten();
			oThis.initLagerbestandAbsolut();
			oThis.setProzentrechnungStatus();
			
			oThis.setUmrechnungStatus();
			
			oThis.setEinheitMengePreisZiel();

			oThis.readSaldoUndAvgPreis_Ausgangssorte_Absolut();
			oThis.readSaldoUndAvgPreis_Ausgangssorte_Zum_Buchungstag();
			
		}
	}



	
	
	/**
	 * Klasse die die Preisinformationen mit Kosten und ohne Kosten kapselt.
	 * 
	 * @author manfred
	 *
	 */
	private class cPreis {
		private BigDecimal bdPreisNetto = BigDecimal.ZERO;
		private BigDecimal bdPreisMitKosten = BigDecimal.ZERO;
		
		/**
		 * Initialisiert beide Preise mit 0
		 */
		public cPreis() {
		}
		
		
		/**
		 * Setzt beide Preise gleich dem übergebenen Wert
		 * @param bdPreis
		 */
		public cPreis(BigDecimal bdPreis) {
			bdPreisNetto = bdPreis;
			bdPreisMitKosten = bdPreis;
		}
		
		
		public BigDecimal getPreisMitKosten() {
			return bdPreisMitKosten;
		}
		public void setPreisMitKosten(BigDecimal bdPreisMitKosten) {
			this.bdPreisMitKosten = bdPreisMitKosten;
		}
		
		public BigDecimal getPreisNetto() {
			return bdPreisNetto;
		}
		public void setPreisNetto(BigDecimal bdPreisNetto) {
			this.bdPreisNetto = bdPreisNetto;
		}
		
		public BigDecimal getKostenanteil(){
			BigDecimal bdRet = BigDecimal.ZERO;
			if (bdPreisMitKosten != null ){
				bdRet = bdPreisMitKosten.subtract(bdPreisNetto!=null? bdPreisNetto: BigDecimal.ZERO);
			}
			return bdRet;
		}
		
	}
	
	
}
