package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

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
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_Float_Nullable;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panterdt.dataBase.pdProtokollInfos;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_SelectBuchungsgewichte.ENUM_SelektorLagerBuchungsgewichte;
import echopointng.Separator;

/*
 * 
 * 
 */
public class LAG_LagerBuchung_Erfassung extends Project_BasicModuleContainer
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
	private EnumDisplayOptions 				m_enumDisplayOption = EnumDisplayOptions.SHOW_BUCHUNG_EINFACH;
	private boolean							m_bIsDirty			= false;
//	private MyE2_PopUpMenue  				popUpSortenAbholadresse = null;

	private	LAG_UB_SearchFieldSorte			oSearchSorteQuelle 	= null;
//	private Vector<String> 					sAdressIds 			= null;
//	private String 							sWhere 				= null;
	private MyE2_SelectField				oSelectFieldLagerQuelle 	= null;
	
	private UB_TextField 		  			tfMengeZiel 		= null;
	private UB_TextField					tfSaldo				= null;
	private UB_TextField					tfAvgPreisSaldo  	= null;
	private UB_TextField					tfPreisZiel			= null;
	private UB_TextArea						tfBemerkung			= null;
	private LAG_SelectBuchungsgewichte      oSelectSonderbuchung = null;
	
	
	// für die prozentuale Umbuchung mit Preisanpassung
	private UB_TextField					tfMengeAusgang   = null;
	private UB_TextField					tfPreisAusgang   = null;
	private UB_TextField					tfProzent 		 = null;
	// 2021-07-07 Prozentuale Zielmenge als Absolut-Menge eingeben und dann in Proz. umrechnen 
	// damit die Preisanpassung gemacht werden kann.
	private UB_TextField					tfProzentMenge   = null;
	private MyE2_Button  					oButtonRecalcPctFromMenge = null;
	
	
	private MyE2_CheckBox					cbProzentual	 = null;
	private MyE2_CheckBox					cbPreisanpassung = null;
	private MyE2_Button  					oButtonRecalcPct = null;
	
		
	private UB_TextField_With_DatePOPUP_OWN  tfDatum = null;     
	private UB_TextField					 tfZeit = null;
	
	
	// explizite unterscheidung, ob eine Korrekturbuchung eine Zu- oder Abbuchung ist
	// damit alle Gewichtswerte positiv sein können
	private MyE2_CheckBox					cbLagerZubuchung = null;
	private MyE2_CheckBox					cbLagerAbbuchung = null;
	private ActionAgent_RadioFunction_CheckBoxList agentWatchdogZUAB = null;
		
	
	private MyE2_Button  					oButtonSaveBuchungAndCloseDialog 	= null;
	private MyE2_Button  					oButtonSaveBuchungAndRestart 	= null;

	private MyE2_Button						oButtonRefreshSaldo = null; 
	private MyE2_Button						oButtonRefreshLagerPreis = null;
	private VECTOR_UB_FIELDS  				vUnboundFields 		= new VECTOR_UB_FIELDS();
	
	
	// Objekte für die Splitbuchung
	private MyE2_SelectField				oSelectFieldLagerZiel 	= null;	
	private boolean							m_bZiellagerIsDirty 	= false;  // wird true, wenn das Ziellager selektiert wurde
	private	LAG_UB_SearchFieldSorte			oSearchSorteZiel		= null;
	
	// Saldo für die Zielsorte
	private UB_TextField					tfSaldoZiel 				= null;
	private MyE2_Label 						lblEinheitMengeSaldoZiel	= null;
	private MyE2_Button						oButtonRefreshSaldoZiel 	= null;
	
	// Labels für die Einheiten von Mengen und Preisen
	MyE2_Label 								lblEinheitPreis 	= null;
	MyE2_Label 								lblEinheitMenge		= null;
	MyE2_Label 								lblEinheitMengeSaldo= null;
	
	// Labels für die Einheiten von Mengen und Preisen der Quellsorten
	MyE2_Label 								lblEinheitPreisSaldo 		= null;
	MyE2_Label 								lblEinheitMengeQuelle		= null;
	
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
	BigDecimal								m_bdCurrentSaldo = null;
	BigDecimal								m_bdCurrentPreisLager = null;
	
	
	/**
	 * Erfassungsmaske wird initialisiert für die Verwendung mit der Navigationsliste
	 * @param navigationlist
	 * @param displayOption
	 * @throws myException
	 */
	public LAG_LagerBuchung_Erfassung(E2_NavigationList navigationlist, EnumDisplayOptions displayOption) throws myException
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
	public LAG_LagerBuchung_Erfassung(String IdLager, String IdSorte, BigDecimal bdMenge, Date dtBuchung, E2_NavigationList navListToRefresh ) throws myException
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
	public LAG_LagerBuchung_Erfassung(String IdLager, String IdSorte, BigDecimal bdMenge, Date dtBuchung, E2_NavigationList navListToRefresh, boolean bKorrekturbuchung ) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGER_ERFASSUNG);
		
		if (bKorrekturbuchung){
			this.m_enumDisplayOption = EnumDisplayOptions.SHOW_BUCHUNG_EINFACH;
		} else {
			this.m_enumDisplayOption = EnumDisplayOptions.SHOW_BUCHUNG_SPLIT;
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

		Alignment alRightCenter = new Alignment(Alignment.RIGHT,Alignment.CENTER);

		/**
		 * Umbuchung oder Korrekturbuchung 
		 */
		cbSortenumbuchung = new MyE2_CheckBox(new MyString("Sortenumbuchung"), oStyleCheckboxNoBorder);
		if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
			cbSortenumbuchung.setSelected(true);
		}
		cbSortenumbuchung.add_oActionAgent(new actionChangeBuchungsTyp());
		
		
		/**
		 * Ausgangsseite der Verbuchung (bei Korrekturbuchung die einzige Lagerangabe)
		 */
		oSelectFieldLagerQuelle 	= (new LAG_LagerSelectField_Factory()).getSelectField();
		oSelectFieldLagerQuelle.add_oActionAgent(new actionAfterChangeOfSelectionQuellLager());
		
		oSearchSorteQuelle 	= new LAG_UB_SearchFieldSorte("ID_ARTIKEL",true);
		oSearchSorteQuelle.set_oMaskActionAfterMaskValueIsFound(new actionAfterFoundSorte());
		oSearchSorteQuelle.get_oTextForAnzeige().setWidth(new Extent(300));

		tfSaldo				= new UB_TextField("MENGE_SALDO",false,"",120,15);
		tfSaldo.set_bEnabled_For_Edit(false);

		tfAvgPreisSaldo     = new UB_TextField("LAGERPREIS_AVG", false, "", 80,15);
		tfAvgPreisSaldo.set_bEnabled_For_Edit(false);
		
		lblEinheitMengeSaldo = new MyE2_Label("");
		lblEinheitPreisSaldo = new MyE2_Label("");

		oButtonRefreshSaldo = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png")); 
		oButtonRefreshSaldo.add_oActionAgent(new actionRefreshSaldo());
		oToolTipText = new MyE2_String("Lagersaldo aktualisieren. Wenn ein Buchungsdatum angegeben ist, dann wird der Saldo zum angegebenen Buchungsdatum ermittelt. Sonst der Gesamtsaldo.");
		oButtonRefreshSaldo.setToolTipText(oToolTipText.toString());

		
		// initialisieren der einzelnen Objekte
//		popUpSortenAbholadresse = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup.png"),E2_ResourceIcon.get_RI("popup.png"),true);

//		sAdressIds 			= bibROHSTOFF.get_vEigeneLieferadressen();
//		sWhere 				= " AND ID_ADRESSE IN ("  + bibALL.Concatenate(sAdressIds, ",", "") + ")";

		
//		this.popUpSortenAbholadresse.get_oContainerEx().setWidth(new Extent(400));
		
		
		/**
		 * Zielseite der Verbuchung (nur bei Umbuchung)
		 */
		// Ziellager
		oSelectFieldLagerZiel = (new LAG_LagerSelectField_Factory()).getSelectField();
		oSelectFieldLagerZiel.add_oActionAgent(new actionAfterChangeOfSelectionZielLager());
		if (!bIsLagerumbuchungErlaubt()){
			oSelectFieldLagerZiel.set_bEnabled_For_Edit(false);
		}
		
		
		// Sortenangabe der Zielsorte
		oSearchSorteZiel	= new LAG_UB_SearchFieldSorte("ID_ARTIKEL",true);
		oSearchSorteZiel.set_oMaskActionAfterMaskValueIsFound(new actionAfterFoundSorteZiel());
		oSearchSorteZiel.get_oTextForAnzeige().setWidth(new Extent(300));
		
		// saldo der Zielsorte
		tfSaldoZiel				= new UB_TextField("MENGE_SALDO_ZIEL",false,"",120,15);
		tfSaldoZiel.set_bEnabled_For_Edit(false);
		lblEinheitMengeSaldoZiel = new MyE2_Label("");
		oButtonRefreshSaldoZiel = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png")); 
		oButtonRefreshSaldoZiel.add_oActionAgent(new actionRefreshSaldoZiel());
		oToolTipText = new MyE2_String("Lagersaldo der Zielsorte aktualisieren. Wenn ein Buchungsdatum angegeben ist, dann wird der Saldo zum angegebenen Buchungsdatum ermittelt. Sonst der Gesamtsaldo.");
		oButtonRefreshSaldoZiel.setToolTipText(oToolTipText.toString());


		
		// prozentuale Umrechung
		tfPreisAusgang		= new UB_TextField("PREIS_ALT", true, "",80,15);
		tfMengeAusgang 		= new UB_TextField("MENGE_ALT",true,"",120,15);
		tfProzent			= new UB_TextField("PROZENT",true,"",80,10);
		tfProzentMenge		= new UB_TextField("PROZENT_MENGE",true,"",80,15);
		
		oButtonRecalcPct	= new MyE2_Button(new MyString("Menge/Preis berechnen"), E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png"));
		oButtonRecalcPct.add_oActionAgent(new actionBerechneProzentualeUmbuchung());
		
		cbPreisanpassung    = new MyE2_CheckBox("Preis anpassen", oStyleCheckboxNoBorder, true, false);
		cbProzentual		= new MyE2_CheckBox("Prozentuale Umbuchung", oStyleCheckboxNoBorder, false, false); 
		cbProzentual.add_oActionAgent(new actionSetProzentualeBerechnung());
		
		
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
		
		oSelectSonderbuchung = new LAG_SelectBuchungsgewichte();
		

		tfPreisZiel			= new UB_TextField("PREIS_NEU",false,"",80,15);
		this.tfPreisZiel.add_InputValidator(new VALIDATE_INPUT_Float());
		this.tfPreisZiel.set_bEmptyAllowd(false);
		this.tfPreisZiel.set_StyleForInput(true);
		
		if (bibALL.get_bIST_SUPERVISOR()){
			this.tfPreisZiel.set_bEnabled_For_Edit(true);
		} else {
			this.tfPreisZiel.set_bEnabled_For_Edit(false);
		}

		lblEinheitPreis 	= new MyE2_Label("");
		lblEinheitMenge 	= new MyE2_Label("");
		
		
		tfDatum 			= new UB_TextField_With_DatePOPUP_OWN("BUCHUNGSDATUM", false, null, 80);
		tfDatum.set_bMiniIcon(true);
		tfDatum.add_InputValidator(new VALIDATE_INPUT_DATE());
		tfDatum.set_bEmptyAllowd(false);
		tfDatum.set_bEnabled_For_Edit(true);
		tfDatum.set_StyleForInput(true);
		tfDatum.get_vActionAgentsZusatz().add( new actionRefreshSaldo() );
		tfDatum.get_vActionAgentsZusatz().add( new actionRefreshLagerPreis());
		tfDatum.get_vActionAgentsZusatz().add( new actionRefreshSaldoZiel());
		
		
		tfZeit 				= new UB_TextField("BUCHUNGSZEIT", false, "23:58", 80, 5) ;
		this.tfZeit.set_bEmptyAllowd(false);
		this.tfZeit.set_bEnabled_For_Edit(false);
	
		tfBemerkung			= new UB_TextArea("",300,3,500);
		tfBemerkung.set_iMaxInputSize(500);
				
		oButtonSaveBuchungAndCloseDialog = new MyE2_Button("Buchung ausführen und Beenden");
		oButtonSaveBuchungAndCloseDialog.setFont(new E2_FontBold(1));
		oButtonSaveBuchungAndCloseDialog.add_oActionAgent(new actionSaveBuchung());
		
		
//		oButtonSaveBuchungAndKeepOpen = new 
		oButtonSaveBuchungAndRestart = new MyE2_Button("Buchung ausführen und Neue Buchung starten");
		oButtonSaveBuchungAndRestart.setFont(new E2_FontBold(1));
		oButtonSaveBuchungAndRestart.add_oActionAgent(new actionSaveBuchungAndRestart());
			
		this.oButtonRefreshLagerPreis = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png"));
		this.oButtonRefreshLagerPreis.add_oActionAgent(new actionRefreshLagerPreis());
		oToolTipText = new MyE2_String("Durchschnittlicher Lagerpreis ermitteln.");
		this.oButtonRefreshLagerPreis.setToolTipText(oToolTipText.toString());
		

		MyE2_Row oRowSaldo = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowSaldo.add(tfSaldo,E2_INSETS.I_0_0_10_0);
		oRowSaldo.add(lblEinheitMengeSaldo);
		oRowSaldo.add(new MyE2_Label(new MyE2_String("Lagerpreis"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_20_0_0_0);
		oRowSaldo.add(tfAvgPreisSaldo,E2_INSETS.I_10_0_0_0);
		oRowSaldo.add(lblEinheitPreisSaldo);
		oRowSaldo.add(oButtonRefreshSaldo);
		
		MyE2_Row oRowButtonBuchung = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowButtonBuchung.add(oButtonSaveBuchungAndCloseDialog);
		oRowButtonBuchung.add(oButtonSaveBuchungAndRestart,E2_INSETS.I_10_2_2_2);
		
		//die felder in einen vector zusammenfassen, damit es einfacher geht
		this.vUnboundFields.add(oSearchSorteQuelle);
		this.vUnboundFields.add(tfMengeZiel);
		this.vUnboundFields.add(tfSaldo);
		this.vUnboundFields.add(tfPreisZiel);
		this.vUnboundFields.add(tfZeit);
		this.vUnboundFields.add(tfDatum);
		this.vUnboundFields.add(tfMengeAusgang);
		this.vUnboundFields.add(tfPreisAusgang);
		this.vUnboundFields.add(tfProzent);
		this.vUnboundFields.add(tfProzentMenge);
		
		
		
		
		tfSaldo.setAlignment(alRightCenter);
		tfSaldoZiel.setAlignment(alRightCenter);
		tfAvgPreisSaldo.setAlignment(alRightCenter);
		tfPreisZiel.setAlignment(alRightCenter);
		tfMengeZiel.setAlignment(alRightCenter);

//		tfDatum.setAlignment(al);
		tfZeit.setAlignment(alRightCenter);

		tfPreisAusgang.setAlignment(alRightCenter);
		tfProzent.setAlignment(alRightCenter);
		tfProzentMenge.setAlignment(alRightCenter);
		tfMengeAusgang.setAlignment(alRightCenter);
		
		vComponentsUmbuchung = new Vector<Component>();
		vComponentsKorrekturbuchung = new Vector<Component>();
	
		
		Insets oIn = new Insets(4,1,2,1);
		Insets oInSep = new Insets(4,5,2,1);

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
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Lagerbestand (Saldo)"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(oRowSaldo,4,oIn);

		
		// debug
//		oGridBase.add(new Separator(),1,new Insets(1,0,0,0));		
//		oGridBase.add(new Separator(),1,new Insets(1,0,0,0));		
//		oGridBase.add(new Separator(),1,new Insets(1,0,0,0));		
//		oGridBase.add(new Separator(),1,new Insets(1,0,0,0));		
//		oGridBase.add(new Separator(),1,new Insets(1,0,0,0));		
		// /debug
		
		
		


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
		
		
		
		
		
		// b2018-09-18 Zielsaldo soll eingeblendet werden (ML)
		// Saldo Zielsorte
		MyE2_Label lblSaldoZiel =new MyE2_Label(new MyE2_String("Saldo Zielsorte"),MyE2_Label.STYLE_NORMAL_PLAIN());
		oGridBase.add(lblSaldoZiel ,1,oIn);
		vComponentsUmbuchung.add(lblSaldoZiel);
		
		MyE2_Row oRowSaldoZiel = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowSaldoZiel.add(tfSaldoZiel,E2_INSETS.I_0_0_10_0);
		oRowSaldoZiel.add(lblEinheitMengeSaldoZiel,E2_INSETS.I_0_0_10_0);
		oRowSaldoZiel.add(oButtonRefreshSaldoZiel,E2_INSETS.I_0_0_10_0);
		oGridBase.add(oRowSaldoZiel,4, new Insets(0,1,2,1));
		
		vComponentsUmbuchung.add(tfSaldoZiel);
		vComponentsUmbuchung.add(lblEinheitMengeSaldoZiel);
		vComponentsUmbuchung.add(oButtonRefreshSaldoZiel);

		
		
		
		
		// Umrechnung prozentuale Verbuchung
		oGridZielsorte = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		oGridZielsorte.add(cbProzentual,1,oIn);
		MyE2_Row oRowPct1 = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowPct1.add(cbPreisanpassung, E2_INSETS.I_0_0_0_0);
		oGridZielsorte.add(oRowPct1,4,oIn);
		
		oGridZielsorte.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		MyE2_Row oRowMengePct =  new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowMengePct.add(new MyE2_Label(new MyE2_String("Umbuchungsmenge"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_0_0_0_0);
		oRowMengePct.add(tfMengeAusgang,E2_INSETS.I_10_0_0_0);
		oRowMengePct.add(new MyE2_Label(new MyE2_String("Ausgangspreis"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_20_0_0_0);
		oRowMengePct.add(tfPreisAusgang,E2_INSETS.I_10_0_0_0);
		oGridZielsorte.add(oRowMengePct,4, oIn);
		
		oGridZielsorte.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		MyE2_Row oRowPct =  new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowPct.add(new MyE2_Label(new MyE2_String("Umbuchungssatz in %"),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_0_0_0_0);
		oRowPct.add(tfProzent, E2_INSETS.I_10_0_0_0);
		oRowPct.add(new MyE2_Label(new MyE2_String(" oder Absolutgewicht "),MyE2_Label.STYLE_NORMAL_PLAIN()),E2_INSETS.I_5_0_0_0);
		oRowPct.add(tfProzentMenge, E2_INSETS.I_10_0_0_0);

		oGridZielsorte.add(oRowPct,4, oIn);
		
		oGridZielsorte.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		MyE2_Row oRowPctBerechnen =  new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowPctBerechnen.add(oButtonRecalcPct);
		oGridZielsorte.add(oRowPctBerechnen,4,oIn);
		
		MyE2_Label lblGridProzentabzug = new MyE2_Label("");
		oGridBase.add(lblGridProzentabzug,1,oIn);
		oGridBase.add(oGridZielsorte,4,oIn);
		
		vComponentsUmbuchung.add(lblGridProzentabzug);
		vComponentsUmbuchung.add(oGridZielsorte);
		
		
		//
		// Buchungsdaten
		//
		oGridBase.add(new Separator(),5,oInSep);	
		oGridBase.add(new MyE2_Label(new MyE2_String("Buchungsdaten"),MyE2_Label.STYLE_TITEL_NORMAL()),5, E2_INSETS.I_0_2_2_2);

		
		
		// Buchungsdatum / Zeit
		oGridBase.add(new MyE2_Label(new MyE2_String("Buchungsdatum"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(tfDatum,4, oIn);
//		oGridBase.add(new E2_ComponentGroupHorizontal(1,tfDatum,E2_INSETS.I_0_0_0_0),4, oIn);

		oGridBase.add(new MyE2_Label(new MyE2_String("Buchungszeit"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
		oGridBase.add(tfZeit,4, oIn);
//		oGridBase.add(new E2_ComponentGroupHorizontal(1,tfZeit,E2_INSETS.I_0_0_0_0),4, oIn);

		// Schalter Zu/Abbuchung
		// wird nur angezeigt, wenn Korrekturbuchung, sonst ausgeblendet
		MyE2_Label lblBuchungsart = new MyE2_Label(new MyE2_String("Zu- / Abbuchung"),MyE2_Label.STYLE_NORMAL_PLAIN());
		oGridBase.add(lblBuchungsart,1,oIn);
		MyE2_Row   rowBuchungsart = new MyE2_Row(); 
		rowBuchungsart.add(cbLagerZubuchung, E2_INSETS.I_0_0_10_0);
		rowBuchungsart.add(cbLagerAbbuchung);
		oGridBase.add(rowBuchungsart,4,oIn);
		
		vComponentsKorrekturbuchung.add(lblBuchungsart);
		vComponentsKorrekturbuchung.add(rowBuchungsart);
		vComponentsKorrekturbuchung.add(cbLagerAbbuchung);
		vComponentsKorrekturbuchung.add(cbLagerZubuchung);
		
		
		// Spezialverbuchung (Entweder Lager, Buchhalterisch oder Neutral)
		if (bib_Settigs_Mandant.get_Lager_Allow_Buchung_Buchhalterisch()){
			oGridBase.add(new MyE2_Label(new MyE2_String("Spezielle Verbuchung"),MyE2_Label.STYLE_NORMAL_PLAIN()),1, oIn);
			MyE2_Row oRowSonderbuchung =  new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			oRowSonderbuchung.add(oSelectSonderbuchung,E2_INSETS.I_0_0_10_0);
			oGridBase.add(oRowSonderbuchung,4, oIn);
		}
				
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

		if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_EINFACH)){
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
		return m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_EINFACH);
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
		if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
			lblHeading.setText(new MyString("Erfassung einer Sorten-Umbuchung").CTrans());
			lblHeadingSorte.setText(new MyString("Auswahl der Ausgangssorte (Menge wird abgebucht)").CTrans());
			lblHeadingFunction.setText(new MyString("Auswahl der Zielsorte / Buchungsberechnungen (Menge wird zugebucht)").CTrans());
			oSearchSorteZiel.set_bEnabled_For_Edit(true);
			cbProzentual.set_bEnabled_For_Edit(true);
			
			for (Component c : vComponentsUmbuchung){
				c.setVisible(true);
			}
			for (Component c : vComponentsKorrekturbuchung){
				c.setVisible(false);
			}

		} else {
			lblHeading.setText(new MyString("Erfassung einer Korrekturbuchung").CTrans());
			lblHeadingSorte.setText(new MyString("Auswahl der Sorte").CTrans());
			lblHeadingFunction.setText(new MyString("Neubuchung einer Menge").CTrans());
			
			oSearchSorteZiel.get_oTextFieldForSearchInput().setText("");
			oSearchSorteZiel.get_oTextForAnzeige().setText("");
			oSearchSorteZiel.set_bEnabled_For_Edit(false);
			tfSaldoZiel.setText("");
			
			cbProzentual.setSelected(false);
			cbProzentual.set_bEnabled_For_Edit(false);
			
			for (Component c : vComponentsUmbuchung){
				c.setVisible(false);
			}
			for (Component c : vComponentsKorrekturbuchung){
				c.setVisible(true);
			}
		}
		

		setProzentrechnungStatus();
	}
	
	
	
	/**
	 * Setzt den status der Felder die bei der Umbuchung für die prozentuale Berechnung nötig sind
	 * @throws myException
	 */
	private void setProzentrechnungStatus() throws myException{
		boolean bOn = cbProzentual.isSelected();
		
		this.tfMengeAusgang.set_bEnabled_For_Edit(bOn);
		this.tfMengeAusgang.set_StyleForInput(bOn);
		this.tfMengeAusgang.set_bEmptyAllowd(!bOn);
			
		this.tfPreisAusgang.set_bEnabled_For_Edit(bOn);
		this.tfPreisAusgang.set_StyleForInput(bOn);
		this.tfPreisAusgang.set_bEmptyAllowd(!bOn);
		
		this.tfProzent.set_bEnabled_For_Edit(bOn);
		this.tfProzent.set_StyleForInput(bOn);
		this.tfProzent.set_bEmptyAllowd(!bOn);
		
		this.tfProzentMenge.set_bEnabled_For_Edit(bOn);
		this.tfProzentMenge.set_StyleForInput(bOn);
		this.tfProzentMenge.set_bEmptyAllowd(true);
		

		this.cbPreisanpassung.set_bEnabled_For_Edit(bOn);
		this.oButtonRecalcPct.set_bEnabled_For_Edit(bOn);
		
		this.tfMengeZiel.set_bEnabled_For_Edit(!bOn);
//		this.oSelectSonderbuchung.set_bEnabled_For_Edit(!bOn);
	
		if (!bOn){
			tfMengeAusgang.get_vInputValidator().removeAllElements();
			tfPreisAusgang.get_vInputValidator().removeAllElements();
			tfProzent.get_vInputValidator().removeAllElements();
			tfProzentMenge.get_vInputValidator().removeAllElements();
			tfMengeAusgang.setText(null);
			tfPreisAusgang.setText(null);
			tfProzent.setText(null);
		} else {
			tfMengeAusgang.add_InputValidator(new VALIDATE_INPUT_Float());
			tfPreisAusgang.add_InputValidator(new VALIDATE_INPUT_Float());
			tfProzent.add_InputValidator(new VALIDATE_INPUT_Float());
			
			tfProzentMenge.set_bEmptyAllowd(true);
			tfProzentMenge.add_InputValidator(new VALIDATE_INPUT_Float_Nullable());
			
			
			tfPreisAusgang.setText(tfAvgPreisSaldo.getText());
		}
	}
	
	
	
	/**
	 * Rechnet die Ausgangsmenge prozentual in die Zielmenge um und passt wenn gewünscht den Preis
	 * so an, dass der Wert erhalten bleibt
	 */
	private void doProzentualeUmrechnung(){
		VECTOR_UB_FIELDS  				vUnboundFields 		= new VECTOR_UB_FIELDS();
		vUnboundFields.add(tfMengeAusgang);
		vUnboundFields.add(tfPreisAusgang);
//		vUnboundFields.add(tfProzent);
		vUnboundFields.add(tfProzentMenge);
		
		
		try {
			bibMSG.add_MESSAGE(vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
		} catch (myException e1) {
			//
		}
		
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		
		
		BigDecimal bdMengeAusgang ;
		BigDecimal bdPreisAusgang;

		BigDecimal bdProzent ;
		BigDecimal bdProzentMenge;
		
		BigDecimal bdMengeZiel = BigDecimal.ZERO;
		BigDecimal bdPreisZiel = BigDecimal.ZERO;
		
		bdProzent = bibALL.convertTextToBigDecimal(tfProzent.getText());
		bdProzentMenge = bibALL.convertTextToBigDecimal(tfProzentMenge.getText());
		bdMengeAusgang = bibALL.convertTextToBigDecimal(tfMengeAusgang.getText());
		bdPreisAusgang = bibALL.convertTextToBigDecimal(tfPreisAusgang.getText());

		if (bdProzentMenge != null) {
			bdProzent = bdProzentMenge.divide(bdMengeAusgang,7,RoundingMode.HALF_UP).multiply(new BigDecimal(100));
			tfProzent.setText(bibALL.convertBigDecimalToString(bdProzent, 5));
		}
		
				
		try {
			bibMSG.add_MESSAGE(tfProzent.get_MV_InputOK());
		} catch (myException e) {
			e.printStackTrace();
		}
		
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		bdMengeZiel = bdMengeAusgang.multiply(bdProzent.divide(new BigDecimal(100)));
		
		if (cbPreisanpassung.isSelected()){
			if (bdMengeZiel.compareTo(BigDecimal.ZERO) != 0){
				bdPreisZiel = bdPreisAusgang.multiply( bdMengeAusgang.divide(bdMengeZiel,20,RoundingMode.HALF_UP));
				bdPreisZiel.round(new MathContext(2));
			} else {
				// division / 0
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Zielmenge darf nicht null sein!"));
			}
		} else {
			bdPreisZiel = bibALL.convertTextToBigDecimal(this.tfAvgPreisSaldo.getText());
		}
		
		// zuweisung der Feldinhalte
		tfMengeZiel.setText( bibALL.convertBigDecimalToString(bdMengeZiel, 3));
		tfPreisZiel.setText( bibALL.convertBigDecimalToString(bdPreisZiel, 2));
		
	}
	
	
	
	
	/**
	 * getter für das Quell-Sortensuchfeld
	 * @author manfred
	 * @date   
	 * @return
	 */
	public LAG_UB_SearchFieldSorte get_oSearchSorte()
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
				RECORD_LAGER_KONTO recLagerKonto = new RECORD_LAGER_KONTO(vSelectedIDs.get(0));
				
				oSelectFieldLagerQuelle.set_ActiveValue_OR_FirstValue(recLagerKonto.get_ID_ADRESSE_LAGER_cUF());
				oSelectFieldLagerZiel.set_ActiveValue_OR_FirstValue(recLagerKonto.get_ID_ADRESSE_LAGER_cUF());
				
				oSearchSorteQuelle.get_oTextFieldForSearchInput().setText(recLagerKonto.get_ID_ARTIKEL_SORTE_cUF());
				oSearchSorteQuelle.FillLabelWithDBQuery(recLagerKonto.get_ID_ARTIKEL_SORTE_cUF());
				
				this.initLagerbestand();
				
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
				// abhängig vom Vorzeichen die Checkboxen setzen (unabhängig davon, ob Korrektur- oder Umbuchung
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
				this.initLagerbestand();

				this.initZielBuchungsdaten();
			}
		}
	}
	
	
	
	/**
	 * Initialisiert die Saldowerte des Lagers
	 * @throws myException 
	 */
	private void initLagerbestand() throws myException{
		this.getSaldoForSelectedLagerSorte();
		this.getEinheitMengePreisSaldo();
		this.getLagerPreisSaldo();
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
		// menge, einheiten und preise für das Ausgangslager lesen
		initLagerbestand();

		// zieldaten lesen
		this.getEinheitMengePreis();
		this.getLagerPreisZiel();
	}
	
	
	/**
	 * löscht die Maske nach dem Speichern, dass nur noch die Ausgangssorte und das Ausgangslager bestehen bleiben 
	 * @author manfred
	 * @throws myException 
	 * @date 19.09.2018
	 *
	 */
	private void clearDataToNewEntry() throws myException{
		
		oSearchSorteQuelle.get_oTextFieldForSearchInput().setText("");
		oSearchSorteQuelle.get_oTextForAnzeige().setText("");
		
		oSearchSorteZiel.get_oTextFieldForSearchInput().setText("");
		oSearchSorteZiel.get_oTextForAnzeige().setText("");
		
		cbProzentual.set_bEnabled_For_Edit(true);
		cbProzentual.setSelected(false);
		tfSaldoZiel.setText("");
		
		tfMengeZiel.setText("");
		tfPreisZiel.setText("");
		
		tfSaldo.setText("");
		tfAvgPreisSaldo.setText("");

		tfBemerkung.setText("");
		
		oSelectSonderbuchung.set_ActiveValue_OR_FirstValue(ENUM_SelektorLagerBuchungsgewichte.neutral.name());
		
		setProzentrechnungStatus();
	}
	
	

	/**
	 * Ermitteln des Saldo einer Lager/Sorte-Kombination
	 * Ermittelt auch den Durchschnittslagerpreise
	 * Die Ermittlung erfolgt zu dem im Buchungs-Tag angegebenen Zeitpunkt
	 * Author: manfred
	 * 06.05.2009
	 * 26.03.2012
	 * @return true, wenn lager und sorte nicht null sind.
	 */
	private boolean getSaldoForSelectedLagerSorte(){
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
		String sBuchungsDatum = null;
		try {
			if (!bibALL.isEmpty(tfDatum.get_cText()) ){
				sBuchungsDatum = myDateHelper.ChangeNormalString2DBFormatString( tfDatum.get_cString4Database() );
			}
			
		} catch (myException e2) {
			e2.printStackTrace();
			sBuchungsDatum = null;
		} 
		
		if (sIDSorte != null && sIDLager != null){
			bRet = true;
		}
		
		LAG_LagerSaldoErmittlung oLagerSaldo = new LAG_LagerSaldoErmittlung();
		BigDecimal dSaldo = null;
		
		try {
			// lagersaldo immer auf 23:58 lesen, da sonst evtl. die Inventur-Buchung (0-Durchgang) gelesen wird und der Saldo 0 wird.
			oLagerSaldo.readLagerSaldoDaten(sIDLager, sIDSorte, sBuchungsDatum,"23:58");
			LAG_LagerSaldoDaten oSaldo = oLagerSaldo.getData(sIDLager, sIDSorte);
			dSaldo = oSaldo.get_Saldo();

		} catch (myException e) {
			e.printStackTrace();
		}

		if (dSaldo == null){
			dSaldo = BigDecimal.ZERO;
		}
		
		m_bdCurrentSaldo = dSaldo;
		tfSaldo.setText(bibALL.convertBigDecimalToString(dSaldo,3));
		
		return bRet;
	}

	
	
	/**
	 * Ermitteln des Saldo einer Lager/Sorte-Kombination der Ziel-Sorte
	 * Ermittelt auch den Durchschnittslagerpreise
	 * Die Ermittlung erfolgt zu dem im Buchungs-Tag angegebenen Zeitpunkt
	 * Author: manfred
	 * 06.05.2009
	 * 26.03.2012
	 * @return true, wenn lager und sorte nicht null sind.
	 */
	private boolean getSaldoForSelectedLagerSorteZiel(){
		boolean bRet = false;
		String sIDLager = null;
		String sIDSorte = null;
		
		sIDSorte = new MyLong(oSearchSorteZiel.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();

		try {
			sIDLager = new MyLong(oSelectFieldLagerZiel.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
		} catch (myException e) {
			sIDLager = null;
		}

		// das Buchungsdatum ermitteln wenn vorhanden
		String sBuchungsDatum = null;
		try {
			if (!bibALL.isEmpty(tfDatum.get_cText()) ){
				sBuchungsDatum = myDateHelper.ChangeNormalString2DBFormatString( tfDatum.get_cString4Database() );
			}
			
		} catch (myException e2) {
			e2.printStackTrace();
			sBuchungsDatum = null;
		} 
		
		if (sIDSorte != null && !sIDSorte.equals("0") && sIDLager != null){
			bRet = true;
		
			LAG_LagerSaldoErmittlung oLagerSaldo = new LAG_LagerSaldoErmittlung();
			BigDecimal dSaldo = null;
			
			try {
				// lagersaldo immer auf 23:58 lesen, da sonst evtl. die Inventur-Buchung (0-Durchgang) gelesen wird und der Saldo 0 wird.
				oLagerSaldo.readLagerSaldoDaten(sIDLager, sIDSorte, sBuchungsDatum,"23:58");
				LAG_LagerSaldoDaten oSaldo = oLagerSaldo.getData(sIDLager, sIDSorte);
				dSaldo = oSaldo.get_Saldo();
				
			} catch (myException e) {
				e.printStackTrace();
			}
			
			if (dSaldo == null){
				dSaldo = BigDecimal.ZERO;
			}
			
			tfSaldoZiel.setText(bibALL.convertBigDecimalToString(dSaldo,3));
		} else {
			tfSaldoZiel.setText("");
		}
		
		return bRet;
	}
	
	
	

	
	/**
	 * Ermittelt die Mengen und Preiseinheiten der Quellsorte
	 */
	private void getEinheitMengePreisSaldo(){

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
			
			lblEinheitMengeSaldo.set_Text(sEinheitMenge);
			lblEinheitPreisSaldo.set_Text(" / " + sEinheitPreis);

		} catch (myException e1)
		{
			lblEinheitMengeSaldo.set_Text("?");
			lblEinheitPreisSaldo.set_Text(" / ?");
		}
	}

	/**
	 * Ermittelt die Mengen und Preiseinheiten der Zielsorte
	 */
	private void getEinheitMengeSaldoZiel(){

		String sIDSorteZiel = null;

		sIDSorteZiel = new MyLong(oSearchSorteZiel.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		
		RECORD_EINHEIT oEinheitSaldo = null;
		String 			sEinheitSaldo = "?";
		String 			sEinheitMenge = "?";

		try
		{
			RECORD_ARTIKEL o = new RECORD_ARTIKEL(sIDSorteZiel);
			oEinheitSaldo = o.get_UP_RECORD_EINHEIT_id_einheit();
			sEinheitSaldo = oEinheitSaldo.get_EINHEITKURZ_cUF();
			sEinheitMenge = sEinheitSaldo;
			
			lblEinheitMengeSaldoZiel.set_Text(sEinheitMenge);

		} catch (myException e1)
		{
			lblEinheitMengeSaldoZiel.set_Text("?");
		}
	}
	
	/**
	 * Ermittelt den Durchschnittslagerpreis 
	 * der auf dem Lager liegenden Werte
	 * Wenn kein Datum vorhanden ist, dann den Gesamtdurchschnitt
	 * Wenn ein Datum vorhanden ist, dann den Durchschnittspreis der Historie
	 * Wenn kein Historischer Preis vorhanden ist, dann der Preis der letzten 10 Buchungen zum Zeitpunkt der Buchung
	 * 
	 * @author manfred
	 * @throws myException 
	 * @date 25.06.2009
	 * @date 29.03.2012 
	 */
	private void getLagerPreisSaldo() throws myException{
		
		BigDecimal dPreis = null;

		// nur wenn der aktuelle Lagersaldo > 0 ist, kann es auch einen aktuellen durchschnittspreis geben!
		if (m_bdCurrentSaldo.compareTo(BigDecimal.ZERO) >= 0){
			dPreis = getLagerPreisValue(null);
		} 
		
		m_bdCurrentPreisLager = dPreis;
		
		String     sPreis = "";
		if (dPreis != null){
			sPreis = bibALL.convertBigDecimalToString(dPreis,2);
			this.tfAvgPreisSaldo.setText(sPreis);
		} else {
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Es konnte kein Lagerpreis ermittelt werden, da keine verbuchbare Mengen auf dem Lager vorhanden sind."));
			this.tfAvgPreisSaldo.setText("");
		}
	}

	

	/**
	 * Ermittelt die Einheiten für Menge und Preis
	 * @author manfred
	 * @date  
	 */
	private void getEinheitMengePreis(){

		String sIDSorteQuelle = null;
		String sIDSorteZiel = null;

		sIDSorteQuelle = new MyLong(oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
			sIDSorteZiel = new MyLong(oSearchSorteZiel.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		}
		
		
		RECORD_EINHEIT oEinheitZiel = null;
		RECORD_EINHEIT oEinheitPreis = null;
		String 			sEinheitMenge = "?";
		String 			sEinheitPreis = "?";

		try
		{
			RECORD_ARTIKEL o = new RECORD_ARTIKEL(sIDSorteQuelle);
			
			if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
				
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
	
	
	
	/**
	 * Anzeige des Lagerpreises für die Umbuchung
	 * @author manfred
	 * @date   26.03.2012
	 * @throws myException
	 */
	private void getLagerPreisZiel() throws myException{
		// Menge
		BigDecimal dMenge = bibALL.convertTextToBigDecimal(tfMengeZiel.getText());
		
		// Preis lesen
		BigDecimal dPreis = getLagerPreisValue(dMenge);
		String sPreis = (dPreis != null ? bibALL.convertBigDecimalToString(dPreis,2) : null);
		this.tfPreisZiel.setText(sPreis);
	}
	
	
	/**
	 * Ermittelt den Durchschnittslagerpreis 
	 * Ermittlung des Preises nach dem Setzkastenprinzip wenn die Buchung aktuell ist (nicht älter als 20 Tage)
	 * @return  der Lagerpreis wenn gefunden oder NULL
	 * 
	 * @author manfred
	 * @throws myException 
	 * @throws  
	 * @date 25.06.2009
	 */
	private BigDecimal getLagerPreisValue(BigDecimal dMenge) throws myException{
		String sIDLager 	= null;
		String sIDSorte 	= null;
		String sDatum 		= null;
		
		BigDecimal dPreis	= null;
		String sMessage 	= null;

		
		long days 			= 0;
		
		
		
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
		sDatum = tfDatum.get_oDBFormatedDateFromTextField();
		if (bibALL.isEmpty(sDatum)){
			sDatum = null;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dtDatumPreis = df.parse(sDatum);
				Date dtNow = new Date();
				days = Math.round((double) (dtNow.getTime() - dtDatumPreis.getTime())   / (24. * 60. *60. *1000. ));
			} catch (ParseException e) {
				e.printStackTrace();
				days = 0;
			}
		}		
		
				
		if (sIDSorte != null && sIDLager != null){
			
			if (dMenge != null){
		
				// durschnittspreis der Lagermenge
				if (days > 20){
					// historischen Durchschnittspreis ermitteln, wenn Buchung vor 20 Tagen stattfinden soll (historische Werte)
					dPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(sIDLager, sIDSorte, 1, 10,sDatum);
					sMessage = "Es wurde der Lagerdurchnittspreis zum angegebenen Zeitpunkt ermittelt.";
				} else {
					dPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager(sIDLager, sIDSorte, dMenge,null, true);
					sMessage = "Es wurde der Lagerdurchnittspreis für die angegebene Menge ermittelt.";
					
					// falls der preis so nicht gefunden werden konnte, doch noch mal schauen, ob man der Preis anderst bekommt
					if (dPreis == null){
						dPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(sIDLager, sIDSorte, 1, 10,sDatum);
						if (dPreis != null){
							sMessage = "Es wurde der Lagerdurchnittspreis für die letzten 10 Wareneingänge ermittelt, da keine freien verbuchbaren Mengen vorhanden waren.";
						}
					}
				}
			} else {
				// keine Mengenangabe
				if (days > 20){
					dPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(sIDLager, sIDSorte, 1, 10, sDatum);
					if (dPreis != null){
						sMessage = "Es wurde der Lagerdurchnittspreis für die angegebene Menge zum angegebenen Zeitpunkt ermittelt.";
					}
				}  else {
					// Gesamtdurchschnitt ermitteln
					dPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager(sIDLager, sIDSorte, null,true);
					sMessage = "Es wurde der Lagerdurchnittspreis aller verbuchbarer Mengen ermittelt.";
	
					if (dPreis == null){
						dPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(sIDLager, sIDSorte, 1, 10, sDatum);
						if (dPreis != null){
							sMessage = "Es wurde der Lagerdurchnittspreis für die letzten 10 Wareneingänge ermittelt, da keine freien verbuchbaren Mengen vorhanden waren.";
						}
					} 
				}
			}

			if (dPreis == null){
				sMessage = "Es konnte kein Lagerdurchnittspreis ermittelt werden!";
			}
		}
		
		if (sMessage != null){
			bibMSG.add_MESSAGE( new MyE2_Info_Message(sMessage));
		}
				
		return dPreis;
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
	
	
	
	/**
	 * Actionagent für die Checkbox Sortenumbuchung
	 * @author manfred
	 *
	 */
	private class actionChangeBuchungsTyp extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			if (oThis.cbSortenumbuchung.isSelected()){
				m_enumDisplayOption = EnumDisplayOptions.SHOW_BUCHUNG_SPLIT;
			} else {
				m_enumDisplayOption = EnumDisplayOptions.SHOW_BUCHUNG_EINFACH;
			}
			oThis.setBuchungsDisplayOption();
		}
	}

	
	
	private class actionSetProzentualeBerechnung extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
//			boolean bPct = oThis.cbProzentual.isSelected();
			
			oThis.setProzentrechnungStatus();
	
		}
		
	}
	
	private class actionBerechneProzentualeUmbuchung extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			oThis.doProzentualeUmrechnung();
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
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			
			oThis.initLagerbestand();
			oThis.getEinheitMengePreis();
			
			oThis.pruefeLagerGleichheitUndWarne();
			
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
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;

			oThis.m_bZiellagerIsDirty = true;
			oThis.pruefeLagerGleichheitUndWarne();
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
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			oThis.initLagerbestand();
			oThis.getEinheitMengePreis();
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
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			oThis.getEinheitMengePreis();
			oThis.getSaldoForSelectedLagerSorteZiel();
			oThis.getEinheitMengeSaldoZiel();
		}
	}




	/**
	 * Eventhandler-Klasse zum manuellen refreshen der Saldo-Stände
	 * @author manfred
	 *
	 */
	private class actionRefreshSaldo extends XX_ActionAgent
	{

		/***
		 * Ermitteln des aktuellen Saldos für die gewählte Lager/Sorte-Kombination	
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			oThis.initLagerbestand();
			oThis.getEinheitMengePreis();
			
		}
		
	}

	
	/**
	 * Eventhandler-Klasse zum manuellen refreshen der Saldo-Stände
	 * @author manfred
	 *
	 */
	private class actionRefreshSaldoZiel extends XX_ActionAgent
	{

		/***
		 * Ermitteln des aktuellen Saldos für die gewählte Lager/Sorte-Kombination	
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			oThis.getSaldoForSelectedLagerSorteZiel();
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
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
			oThis.getLagerPreisZiel();
			oThis.getEinheitMengePreis();
			
			// lesen des aktuellen Saldos
//			oThis.getSaldoForSelectedLagerSorte();
			oThis.initLagerbestand();
		}
	}
	
	
//	private class actionSaveBuchung extends XX_ActionAgent
//	{
//		@Override
//		public void executeAgentCode(ExecINFO execInfo) throws myException
//		{
//			// wenn mal speichern gedrückt wurde, dann ist der Zustand "dirty"
//			m_bIsDirty = true;
//			
//			//daten einsammeln und validieren
//			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
//			
//			
//			String sIDLagerQuelle = null;
//			String sIDLagerZiel = null;
//			
//			String sIDSorte = null;
//			String sSorteQuelle = null;
//			
//			String sIdSorteZiel =  null;
//			String sSorteZiel = null;
//
//			String sBemerkung = null;
//			String sDatum = null;
//			String sZeit  = null;
//			
//			BigDecimal dPreisAbbuchung = BigDecimal.ZERO;
//			BigDecimal dMengeAbbuchung = BigDecimal.ZERO;
//			
//			BigDecimal dPreis = BigDecimal.ZERO;
//			BigDecimal dMenge = BigDecimal.ZERO;
//			
//			bibMSG.add_MESSAGE(oThis.vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
//			
//			
//			String sEinheitQuelle = lblEinheitMengeSaldo.get_oTextObject().toString();
//			String sEinheitZiel = lblEinheitMenge.get_oTextObject().toString();
//			
//			
//			if (!sEinheitQuelle.equals(sEinheitZiel)){
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur eine Umbuchung stattfinden, wenn die Mengeneinheiten identisch sind.")));
//			}
//			
//			// Menge und Preis lesen
//			dPreis = bibALL.convertTextToBigDecimal(oThis.tfPreisZiel.getText());
//			dMenge = bibALL.convertTextToBigDecimal(oThis.tfMengeZiel.getText());
//			
//			if (dMenge == null){
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine gültige Menge eingetragen werden.")));
//			} else if (dMenge.compareTo(BigDecimal.ZERO) <= 0){
//				// und die Menge muss positiv sein
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine positive Menge angegeben werden!")));
//			}
//			
//			// lager lesen und prüfen
//			sIDLagerQuelle = getIDLagerQuelle();
//			if (sIDLagerQuelle == null || sIDLagerQuelle.equals("0")){
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Lager ausgewählt werden!")));
//			}
//			
//			
//			// Sorte lesen und prüfen
//			sIDSorte = new MyLong(oThis.oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
//			sSorteQuelle = oThis.oSearchSorteQuelle.get_oTextForAnzeige().getText();
//			if (sIDSorte == null || sIDSorte.equals("0")){
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Sorte ausgewählt werden!")));
//			}
//			
//			
//			if (bibMSG.get_bHasAlarms()){
//				return;
//			}
//			
//			
//			/**
//			 * Saldo-Prüfung, dass keine negativen Mengen auf dem Ausgangslager erzeugt werden können
//			 */
//			if (oThis.m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
//				
//				// keine Umbuchung wenn der Ausgangsbestand negativ ist.
//				// nochmal den Saldo neu ermitteln
//				oThis.getSaldoForSelectedLagerSorte();
//
//				sIDLagerZiel  = getIDLagerZiel();
//				if (sIDLagerZiel == null || sIDLagerZiel.equals("0")){
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Ziel-Lager ausgewählt werden!")));
//				}
//				
//				BigDecimal dMengeAusgangskonto = bibALL.convertTextToBigDecimal(oThis.tfSaldo.getText());
//				if ( dMengeAusgangskonto == null || dMengeAusgangskonto.compareTo(BigDecimal.ZERO) < 0 ){
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es darf keine Umbuchung stattfinden, wenn das Ausgangslager negativen Bestand hat.")));
//				}
//								
//				// prüfen, ob die Lagermenge des Ausgangskontos - der Umbuchungsmenge < 0 ist
//				if ( dMengeAusgangskonto.subtract(dMenge).compareTo(BigDecimal.ZERO) < 0){
//					 bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es darf keine Umbuchung stattfinden, wenn das Ausgangslager einen negativen Bestand erhalten wird.")));
//				}
//				
//			}
//
//			// eine einfache Korrekturbuchung darf nur vom Supervisor durchgeführt werden!
//			if (oThis.m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_EINFACH)){
//				if (!bibALL.get_bIST_SUPERVISOR()){
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine einfache Korrekturbuchung darf nur durch den Supervisor erzeugt werden!")));
//				}
//			}
//			
//			
//			// es muss zu- oder Abbuchung definiert sein, wenn es eine einfache Korrekturbuchung ist (keine Standardmäßige auswahl)
//			if (bIsKorrekturbuchung()){
//				if (!bIsBuchungEineAbbuchung() && !bIsBuchungEineZubuchung()){
//					// es wurde keine Checkbox gesetzt
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Zu- oder Abbuchung sein. Bitte wählen sie die korrekte Buchungsart aus.")));
//				}
//			}
//			
//			// Abbruch, wenn Fehler
//			if (bibMSG.get_bHasAlarms()){
//				return;
//			}
//						
//			
//			dPreisAbbuchung = dPreis;
//			dMengeAbbuchung = dMenge;
//			
//
//			// falls Splitbuchung, dann evtl die prozentuale buchung nehmen
//			if (oThis.m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
//				if (oThis.cbProzentual.isSelected()){
//					dPreisAbbuchung = bibALL.convertTextToBigDecimal(oThis.tfPreisAusgang.getText());
//					dMengeAbbuchung = bibALL.convertTextToBigDecimal(oThis.tfMengeAusgang.getText());
//				} 
//			} 
//			
//			// Zeit und Datum lesen
//			sDatum = myDateHelper.ChangeNormalString2DBFormatString( oThis.tfDatum.get_cString4Database() );
//			sZeit  = oThis.tfZeit.get_cString4Database();
//			
//			
//			if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
//				
//				// Zielsorte muss da sein
//				sIdSorteZiel =  new MyLong(oThis.oSearchSorteZiel.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
//				sSorteZiel = oThis.oSearchSorteZiel.get_oTextForAnzeige().getText();
//				
//				if (sIdSorteZiel == null || sIdSorteZiel.equals("0") ){
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Ziel-Sorte ausgewählt werden!")));
//				}
//				
//				// negative Mengen können nicht verarbeitet werden!!
//				if (dMengeAbbuchung.compareTo(BigDecimal.ZERO) <= 0){
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine positive Menge angegeben werden!")));
//				}
//				
//				
//			} else {
//				// 0-Mengen können nicht verarbeitet werden!!
//				if (dMenge.compareTo(BigDecimal.ZERO) == 0){
////					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Menge angegeben werden!")));
//				}
//			}
//			
//			sBemerkung = tfBemerkung.get_cText();
//			
//			if (!bibMSG.get_bHasAlarms()) {
//				
//				
//				// sonderprüfung, ob buchalterische Gewichte berücksichtigt werden sollen
//				boolean bAllowBuchhalterisch = bib_Settigs_Mandant.get_Lager_Allow_Buchung_Buchhalterisch();
//				
//				
//				LAG_LagerHandler 		oLagerHandler 	= new LAG_LagerHandler();
//				LAG_LagerBuchungsSatz 	oLagerSatz 		= null;
//				
//				if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_EINFACH)){
//					String sBuchungstyp = "";
////					if (dMenge.compareTo( BigDecimal.ZERO) < 0) {
//					
//					if (bIsBuchungEineAbbuchung()){
//						sBuchungstyp = "WA";
//						dMenge = dMenge.negate();
//					}
//					else
//					{
//						sBuchungstyp = "WE";
//					}
//					
//					oLagerSatz = new LAG_LagerBuchungsSatz(sIDLagerQuelle,sIDSorte,dPreis,dMenge,sDatum,sZeit,sBuchungstyp,null,null, sBemerkung,"K",null);
//					
//					if (bAllowBuchhalterisch){
//						behandleSonderbuchung(oLagerSatz);
//					}
//					
//					oLagerHandler.verbucheLagerBuchungsSatz(oLagerSatz);
//				} else {
//					sSorteZiel = new MyString("Geht nach Zielsorte").CTrans() + " " + sSorteZiel + System.getProperty("line.separator") ;
//					
//					oLagerSatz = new LAG_LagerBuchungsSatz(sIDLagerQuelle,sIDSorte,dPreisAbbuchung,dMengeAbbuchung.negate(),sDatum,sZeit,"WA",null,null, sSorteZiel + sBemerkung,"U",null);
//					if (bAllowBuchhalterisch){ 
//						behandleSonderbuchung(oLagerSatz);
//					}
//					oLagerHandler.verbucheLagerBuchungsSatz(oLagerSatz);
//
//					sSorteQuelle = new MyString("Kommt von Ausgangssorte").CTrans() + " " + sSorteQuelle + System.getProperty("line.separator");
//					oLagerSatz = new LAG_LagerBuchungsSatz(sIDLagerZiel,sIdSorteZiel,dPreis,dMenge,sDatum,sZeit,"WE",null,null, sSorteQuelle + sBemerkung,"U",null);
//					if (bAllowBuchhalterisch){
//						behandleSonderbuchung(oLagerSatz);
//					}
//					oLagerHandler.verbucheLagerBuchungsSatz(oLagerSatz);
//				}
//				
//				boolean bRet = oLagerHandler.executeSqlStatements(true);
//				
//				if (bRet){
//					
//					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Buchung wurde ausgeführt.")));
//					CLOSE_AND_DESTROY_POPUPWINDOW(true);
//					
//				} else {
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist ein Fehler bei der Buchung aufgetreten!")));
//				}
//			}
//		}
//
//		
//		/**
//		 * setzt die Gewichte je nach Sonderbuchung Lager / Buchhalterisch
//		 * @param oLagerSatz
//		 * @throws myException
//		 */
//		private void behandleSonderbuchung(LAG_LagerBuchungsSatz oLagerSatz) throws myException{
//			ENUM_SelektorLagerBuchungsgewichte eBuch = null;
//			eBuch = oSelectSonderbuchung.getSelected();
//			if (eBuch != ENUM_SelektorLagerBuchungsgewichte.neutral){
//				BigDecimal bdMenge = oLagerSatz.get_menge();
//				oLagerSatz.set_menge(bdMenge.multiply(new BigDecimal(eBuch.getMultiplikatorLager())));
//				oLagerSatz.set_menge_buchhalterisch(bdMenge.multiply(new BigDecimal(eBuch.getMultiplikatorBuchhalterisch())));
//				
//				// falls die Buchung nur Buchhalterisch ist, muss der Schlater ist_komplett auf Y gesetzt werden
//				if (eBuch == ENUM_SelektorLagerBuchungsgewichte.buchhalterisch){
//					oLagerSatz.set_ist_komplett("Y");
//				}
//				
//			}
//			
//		}
//
//	}

	
	
	private class actionSaveBuchung extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			boolean bRet = saveBuchung();
			if (bRet){
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Buchung wurde ausgeführt.")));
				CLOSE_AND_DESTROY_POPUPWINDOW(true);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist ein Fehler bei der Buchung aufgetreten!")));
			}
		}
	}

	private class actionSaveBuchungAndRestart extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;

			boolean bRet = saveBuchung();
			if (bRet){
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Buchung wurde ausgeführt.")));
					
					// neuaufbau der Maske...
					oThis.clearDataToNewEntry();
					
					if (m_bIsDirty && m_NavListRecordLagerKonto != null){
						m_NavListRecordLagerKonto.RefreshList();
						m_bIsDirty = false;
					}
					
			} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist ein Fehler bei der Buchung aufgetreten!")));
			}
				
		}
	}	
	

	
	/**
	 * setzt die Gewichte je nach Sonderbuchung Lager / Buchhalterisch
	 * @param oLagerSatz
	 * @throws myException
	 */
	private void behandleSonderbuchung(LAG_LagerBuchungsSatz oLagerSatz) throws myException{
		ENUM_SelektorLagerBuchungsgewichte eBuch = null;
		eBuch = oSelectSonderbuchung.getSelected();
		if (eBuch != ENUM_SelektorLagerBuchungsgewichte.neutral){
			BigDecimal bdMenge = oLagerSatz.get_menge();
			oLagerSatz.set_menge(bdMenge.multiply(new BigDecimal(eBuch.getMultiplikatorLager())));
			oLagerSatz.set_menge_buchhalterisch(bdMenge.multiply(new BigDecimal(eBuch.getMultiplikatorBuchhalterisch())));
			
			// falls die Buchung nur Buchhalterisch ist, muss der Schlater ist_komplett auf Y gesetzt werden
			if (eBuch == ENUM_SelektorLagerBuchungsgewichte.buchhalterisch){
				oLagerSatz.set_ist_komplett("Y");
			}
			
		}
		
	}
	
	
	private boolean saveBuchung() throws myException{
		boolean bRet = false;
		
		// wenn mal speichern gedrückt wurde, dann ist der Zustand "dirty"
		m_bIsDirty = true;
		
		//daten einsammeln und validieren
		LAG_LagerBuchung_Erfassung oThis = LAG_LagerBuchung_Erfassung.this;
		
		
		String sIDLagerQuelle = null;
		String sIDLagerZiel = null;
		
		String sIDSorte = null;
		String sSorteQuelle = null;
		
		String sIdSorteZiel =  null;
		String sSorteZiel = null;

		String sBemerkung = null;
		String sDatum = null;
		String sZeit  = null;
		
		BigDecimal dPreisAbbuchung = BigDecimal.ZERO;
		BigDecimal dMengeAbbuchung = BigDecimal.ZERO;
		
		BigDecimal dPreis = BigDecimal.ZERO;
		BigDecimal dMenge = BigDecimal.ZERO;
		
		bibMSG.add_MESSAGE(oThis.vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
		
		
		String sEinheitQuelle = lblEinheitMengeSaldo.get_oTextObject().toString();
		String sEinheitZiel = lblEinheitMenge.get_oTextObject().toString();
		
		
		if (!sEinheitQuelle.equals(sEinheitZiel)){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur eine Umbuchung stattfinden, wenn die Mengeneinheiten identisch sind.")));
		}
		
		// Menge und Preis lesen
		dPreis = bibALL.convertTextToBigDecimal(oThis.tfPreisZiel.getText());
		dMenge = bibALL.convertTextToBigDecimal(oThis.tfMengeZiel.getText());
		
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
		sIDSorte = new MyLong(oThis.oSearchSorteQuelle.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
		sSorteQuelle = oThis.oSearchSorteQuelle.get_oTextForAnzeige().getText();
		if (sIDSorte == null || sIDSorte.equals("0")){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Sorte ausgewählt werden!")));
		}
		
		
		if (bibMSG.get_bHasAlarms()){
			return bRet;
		}
		
		
		/**
		 * Saldo-Prüfung, dass keine negativen Mengen auf dem Ausgangslager erzeugt werden können
		 */
		if (oThis.m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
			
			// keine Umbuchung wenn der Ausgangsbestand negativ ist.
			// nochmal den Saldo neu ermitteln
			oThis.getSaldoForSelectedLagerSorte();

			sIDLagerZiel  = getIDLagerZiel();
			if (sIDLagerZiel == null || sIDLagerZiel.equals("0")){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Ziel-Lager ausgewählt werden!")));
			}
			
			BigDecimal dMengeAusgangskonto = bibALL.convertTextToBigDecimal(oThis.tfSaldo.getText());
			if ( dMengeAusgangskonto == null || dMengeAusgangskonto.compareTo(BigDecimal.ZERO) < 0 ){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es darf keine Umbuchung stattfinden, wenn das Ausgangslager negativen Bestand hat.")));
			}
							
			// prüfen, ob die Lagermenge des Ausgangskontos - der Umbuchungsmenge < 0 ist
			if ( dMengeAusgangskonto.subtract(dMenge).compareTo(BigDecimal.ZERO) < 0){
				 bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es darf keine Umbuchung stattfinden, wenn das Ausgangslager einen negativen Bestand erhalten wird.")));
			}
			
		}

		// eine einfache Korrekturbuchung darf nur vom Supervisor durchgeführt werden!
		if (oThis.m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_EINFACH)){
			if (!bibALL.get_bIST_SUPERVISOR()){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine einfache Korrekturbuchung darf nur durch den Supervisor erzeugt werden!")));
			}
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
			return bRet;
		}
					
		
		dPreisAbbuchung = dPreis;
		dMengeAbbuchung = dMenge;
		

		// falls Splitbuchung, dann evtl die prozentuale buchung nehmen
		if (oThis.m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
			if (oThis.cbProzentual.isSelected()){
				dPreisAbbuchung = bibALL.convertTextToBigDecimal(oThis.tfPreisAusgang.getText());
				dMengeAbbuchung = bibALL.convertTextToBigDecimal(oThis.tfMengeAusgang.getText());
			} 
		} 
		
		// Zeit und Datum lesen
		sDatum = myDateHelper.ChangeNormalString2DBFormatString( oThis.tfDatum.get_cString4Database() );
		sZeit  = oThis.tfZeit.get_cString4Database();
		
		
		if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_SPLIT)){
			
			// Zielsorte muss da sein
			sIdSorteZiel =  new MyLong(oThis.oSearchSorteZiel.get_oTextFieldForSearchInput().getText(), new Long(0),new Long(0)).get_cUF_LongString();
			sSorteZiel = oThis.oSearchSorteZiel.get_oTextForAnzeige().getText();
			
			if (sIdSorteZiel == null || sIdSorteZiel.equals("0") ){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Ziel-Sorte ausgewählt werden!")));
			}
			
			// negative Mengen können nicht verarbeitet werden!!
			if (dMengeAbbuchung.compareTo(BigDecimal.ZERO) <= 0){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine positive Menge angegeben werden!")));
			}
			
			
		} else {
			// 0-Mengen können nicht verarbeitet werden!!
			if (dMenge.compareTo(BigDecimal.ZERO) == 0){
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Menge angegeben werden!")));
			}
		}
		
		sBemerkung = tfBemerkung.get_cText();
		
		if (!bibMSG.get_bHasAlarms()) {
			
			
			// sonderprüfung, ob buchalterische Gewichte berücksichtigt werden sollen
			boolean bAllowBuchhalterisch = bib_Settigs_Mandant.get_Lager_Allow_Buchung_Buchhalterisch();
			
			
			LAG_LagerHandler 		oLagerHandler 	= new LAG_LagerHandler();
			LAG_LagerBuchungsSatz 	oLagerSatz 		= null;
			
			if (m_enumDisplayOption.equals(EnumDisplayOptions.SHOW_BUCHUNG_EINFACH)){
				String sBuchungstyp = "";
//				if (dMenge.compareTo( BigDecimal.ZERO) < 0) {
				
				if (bIsBuchungEineAbbuchung()){
					sBuchungstyp = "WA";
					dMenge = dMenge.negate();
				}
				else
				{
					sBuchungstyp = "WE";
				}
				
				oLagerSatz = new LAG_LagerBuchungsSatz(sIDLagerQuelle,sIDSorte,dPreis,dMenge,sDatum,sZeit,sBuchungstyp,null,null, sBemerkung,"K",null);
				
				if (bAllowBuchhalterisch){
					behandleSonderbuchung(oLagerSatz);
				}
				
				oLagerHandler.verbucheLagerBuchungsSatz(oLagerSatz);
			} else {
				sSorteZiel = new MyString("Geht nach Zielsorte").CTrans() + " " + sSorteZiel + System.getProperty("line.separator") ;
				
				oLagerSatz = new LAG_LagerBuchungsSatz(sIDLagerQuelle,sIDSorte,dPreisAbbuchung,dMengeAbbuchung.negate(),sDatum,sZeit,"WA",null,null, sSorteZiel + sBemerkung,"U",null);
				if (bAllowBuchhalterisch){ 
					behandleSonderbuchung(oLagerSatz);
				}
				oLagerHandler.verbucheLagerBuchungsSatz(oLagerSatz);

				sSorteQuelle = new MyString("Kommt von Ausgangssorte").CTrans() + " " + sSorteQuelle + System.getProperty("line.separator");
				oLagerSatz = new LAG_LagerBuchungsSatz(sIDLagerZiel,sIdSorteZiel,dPreis,dMenge,sDatum,sZeit,"WE",null,null, sSorteQuelle + sBemerkung,"U",null);
				if (bAllowBuchhalterisch){
					behandleSonderbuchung(oLagerSatz);
				}
				oLagerHandler.verbucheLagerBuchungsSatz(oLagerSatz);
			}
			
			bRet = oLagerHandler.executeSqlStatements(true);
			
		}

		return bRet;

	}

	
	
}
