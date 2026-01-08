package rohstoff.businesslogic.bewegung2.lager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.vectorForSegmentation;
import rohstoff.businesslogic.bewegung2.global.saldo.B2_LAG_SALDO_Ermittlung;

public class B2_LAG_LIST_Summary_Block extends E2_ListSelectorContainer
{

	private cFieldList      m_fieldList 			= new cFieldList();
	private cFieldList      m_fieldList_caption   	= new cFieldList();


	// Objekte
	private  RB_lab 	lblMessage = null;


	//
	// gebundene Elemente 
	//
	// Spalte Einheit
	private  CLabel_String 		cfHeadingBewegung =  new CLabel_String(new E2_FontBold(0));
	private  CLabel_String 		cfHeadingEinheit =  new CLabel_String(new E2_FontBold(0));
	private  CLabel_String 		cfDescMengeGesamt = new CLabel_String();

	private  CLabel_String  	cfDescMengeEigenware = new CLabel_String();;
	private  CLabel_String		cfDescMengeEigenwareBewertet = new CLabel_String(new E2_FontItalic(-2));
	private  CLabel_String 		cfDescMengeEigenwareUnbewertet = new CLabel_String(new E2_FontItalic(-2));

	private  CLabel_String  	cfDescMengeFremdware = new CLabel_String();
	private  CLabel_String 		cfDescPreis = new CLabel_String();
	private  CLabel_String 		cfDescSumme = new CLabel_String();


	// Spalte WE
	private  CLabel_String 		cfHeadingWE =  new CLabel_String(new E2_FontBold(0));
	private  CLabel_BigDecimal 	cfMengeWEGesamt = new CLabel_BigDecimal();

	private  CLabel_BigDecimal 	cfMengeWEEigenware = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWEEigenwareBewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));
	private  CLabel_BigDecimal 	cfMengeWEEigenwareUnbewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));

	private  CLabel_BigDecimal 	cfMengeWEFremdware = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisWE = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeWE = new CLabel_BigDecimal();;


	// Spalte WE-Netto
	private  CLabel_String 		cfHeadingWENetto = new CLabel_String(new E2_FontBold(0));
	private  CLabel_BigDecimal 	cfMengeWEGesamtNetto = new CLabel_BigDecimal();

	private  CLabel_BigDecimal 	cfMengeWEEigenwareNetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfMengeWEEigenwareBewertetNetto = new CLabel_BigDecimal(new E2_FontItalic(-2));
	private  CLabel_BigDecimal 	cfMengeWEEigenwareUnbewertetNetto = new CLabel_BigDecimal(new E2_FontItalic(-2));

	private  CLabel_BigDecimal 	cfMengeWEFremdwareNetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfPreisWENetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfSummeWENetto = new CLabel_BigDecimal();


	// Spalte WA
	private  CLabel_String 		cfHeadingWA =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeWAGesamt = new CLabel_BigDecimal();;

	private  CLabel_BigDecimal 	cfMengeWAEigenware = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAEigenwareBewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));;
	private  CLabel_BigDecimal 	cfMengeWAEigenwareUnbewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));;

	private  CLabel_BigDecimal 	cfMengeWAFremdware = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisWA = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeWA = new CLabel_BigDecimal();;

	// Spalte WA-Netto
	private  CLabel_String 		cfHeadingWANetto =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeWAGesamtNetto = new CLabel_BigDecimal();;

	private  CLabel_BigDecimal 	cfMengeWAEigenwareNetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAEigenwareBewertetNetto = new CLabel_BigDecimal(new E2_FontItalic(-2));;
	private  CLabel_BigDecimal 	cfMengeWAEigenwareUnbewertetNetto = new CLabel_BigDecimal(new E2_FontItalic(-2));;

	private  CLabel_BigDecimal 	cfMengeWAFremdwareNetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisWANetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeWANetto = new CLabel_BigDecimal();;


	// Spalte DIFFERENZ
	private  CLabel_String 		cfHeadingDiff =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeDiffGesamt = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeDiffEigenware = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeDiffEigenwareBewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));;
	private  CLabel_BigDecimal 	cfMengeDiffEigenwareUnbewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));;

	private  CLabel_BigDecimal 	cfMengeDiffFremdware = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisDiff = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeDiff = new CLabel_BigDecimal();;



	// Bereich SALDO-Ermittlung
	private  RB_lab	lblDescSaldo 				= null;  
	private  RB_lab lblDescSaldoSorte 			= null;
	private  RB_lab lblDescSaldoSorteEigen 		= null;
	private  RB_lab lblDescSaldoSorteFremd 		= null;
	private  RB_lab lblDescSaldoSorteStrecke 	= null;

	private  RB_lab	lblDescSaldoVon1 			= null;
	private  RB_lab	lblDescSaldoVon2 			= null;
	private  RB_lab	lblSaldoVon 				= null;
	private  RB_lab	lblSaldoVonEigen 			= null;
	private  RB_lab	lblSaldoVonFremd 			= null;
	private  RB_lab	lblSaldoVonStrecke 			= null;

	private  RB_lab lblDescSaldoBis1 			= null;
	private  RB_lab	lblDescSaldoBis2 			= null;
	private  RB_lab	lblSaldoBis 				= null;
	private  RB_lab	lblSaldoBisEigen 			= null;
	private  RB_lab	lblSaldoBisFremd 			= null;
	private  RB_lab	lblSaldoBisStrecke 			= null;

	private  RB_lab lblDescSaldoDiff 			= null;
	private  RB_lab lblSaldoDiff 				= null;
	private  RB_lab lblSaldoDiffEigen 			= null;
	private  RB_lab lblSaldoDiffFremd 			= null;
	private  RB_lab lblSaldoDiffStrecke 		= null;


	private  String sRefEinheitMenge 			= "-";
	private  String sRefEinheitPreis 			= "-";
	private  boolean bKennzahlenGueltig 		= false;

	// Variablen zur Berechnung der Preise
	private  BigDecimal		bdMengeWEPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWEPreisBerechnungNetto = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAPreisBerechnungNetto = BigDecimal.ZERO;

	// Werte für die Saldo
	// 2015-05-11: Manfred: Die Saldo werden jetzt unterteilt in Eigenaren-,Fremdwaren- und Streckenbestände 
	private  BigDecimal 	bdSaldoVon = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoBis = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoDiff = BigDecimal.ZERO;

	private  BigDecimal 	bdSaldoVonEigen = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoBisEigen = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoDiffEigen = BigDecimal.ZERO;

	private  BigDecimal 	bdSaldoVonFremd = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoBisFremd = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoDiffFremd = BigDecimal.ZERO;

	private  BigDecimal 	bdSaldoVonStrecke = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoBisStrecke = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoDiffStrecke = BigDecimal.ZERO;



	private E2_NavigationList	oNaviList = null;

	// den Selektor der Lagerbewegung, um die Saldi zu ermitteln
	private B2_LAG_LIST_Selector m_oSelector = null;


	// checkbox zum ein-ausblenden der Kostenanteile
	private MyE2_CheckBox    cbShowPreisangaben;
	private MyE2_CheckBox    cbShowLagerEinzel;
	private E2_MutableStyle  oStyleCheckboxBorderless = new E2_MutableStyle();



	Vector<String> vIDSorte 		= null;
	Vector<String> vIDHauptsorte 	= null;
	Vector<String> vIDLager			= null;


	//	E2_XYPlot plot = new E2_XYPlot();
	//	E2_ChartDisplay chart_display = null;

	public B2_LAG_LIST_Summary_Block(E2_NavigationList oNavigationList, B2_LAG_LIST_Selector oSelector) throws myException
	{
		super(S.ms("Lagerinformationen / Summen / Durchschnittswerte"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		oNaviList = oNavigationList;
		m_oSelector = oSelector;

		// Checkbox Borderless definieren
		oStyleCheckboxBorderless.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));

		lblMessage = new RB_lab("")._b()._col_fore(Color.RED);
		lblMessage.setVisible(false);

		lblDescSaldo 				= new RB_lab(S.ms("Lagerbestände"))._b();
		lblDescSaldoSorte 			= new RB_lab()._t("Alle Lager (EW/FW/S) / Alle Sorten");
		lblDescSaldoBis1 			= new RB_lab()._t(S.ms("Abends zum"))._b();
		lblDescSaldoBis2 			= new RB_lab()._t(S.ms("-"))._b();
		lblDescSaldoDiff 			= new RB_lab()._t(S.ms("Differenz"))._b();
		lblDescSaldoSorteEigen		= new RB_lab()._t("Eigenwarenlager");
		lblDescSaldoSorteFremd		= new RB_lab()._t("Fremdwarenlager");
		lblDescSaldoSorteStrecke	= new RB_lab()._t("Streckenlager");
		lblDescSaldoVon1 			= new RB_lab()._t(S.ms("Morgens zum"))._b();
		lblDescSaldoVon2 			= new RB_lab()._t(S.ms("-"))._b();





		lblSaldoVon 				= new RB_lab()._t("-");
		lblSaldoBis 				= new RB_lab()._t("-");
		lblSaldoDiff 				= new RB_lab()._t("-");

		lblSaldoVonEigen 			= new RB_lab()._t("-")._i()._fsa(-2);
		lblSaldoBisEigen 			= new RB_lab()._t("-")._i()._fsa(-2);
		lblSaldoDiffEigen 			= new RB_lab()._t("-")._i()._fsa(-2);

		lblSaldoVonFremd 			= new RB_lab()._t("-")._i()._fsa(-2);
		lblSaldoBisFremd 			= new RB_lab()._t("-")._i()._fsa(-2);
		lblSaldoDiffFremd 			= new RB_lab()._t("-")._i()._fsa(-2);

		lblSaldoVonStrecke 			= new RB_lab()._t("-")._i()._fsa(-2);
		lblSaldoBisStrecke 			= new RB_lab()._t("-")._i()._fsa(-2);
		lblSaldoDiffStrecke 		= new RB_lab()._t("-")._i()._fsa(-2);


		// Überschriften TAbelle Summenblock
		//		cfHeadingBewegung.setValue("Lagerbewegungen");
		cfHeadingEinheit.setValue("Einheit");
		cfHeadingWE.setValue("WE brutto");
		cfHeadingWENetto.setValue("WE netto");
		cfHeadingWA.setValue("WA brutto");
		cfHeadingWANetto.setValue("WA netto");
		cfHeadingDiff.setValue("Differenz WE(n)/WA(br.)");
		cfHeadingDiff.getObject().setToolTipText(S.ms("Differenz vom Netto WE zum Brutto WA").CTrans());


		m_fieldList_caption._a(cfHeadingBewegung)
		._a(cfHeadingEinheit)._a(cfHeadingWENetto)._a(cfHeadingWE)
		._a(cfHeadingWANetto)._a(cfHeadingWA)._a(cfHeadingDiff)
		;

		// Bindings definieren
		m_fieldList
		._a(cfDescMengeGesamt)._a(cfDescMengeEigenware)._a(cfDescMengeEigenwareBewertet)
		._a(cfDescMengeEigenwareUnbewertet)._a(cfDescMengeFremdware)._a(cfDescPreis)._a(cfDescSumme);

		m_fieldList._a(cfMengeWEGesamtNetto)
		._a(cfMengeWEEigenwareNetto)._a(cfMengeWEEigenwareBewertetNetto)._a(cfMengeWEEigenwareUnbewertetNetto)
		._a(cfMengeWEFremdwareNetto)._a(cfPreisWENetto)._a(cfSummeWENetto);

		m_fieldList._a(cfMengeWAEigenware);
		m_fieldList._a(cfMengeWAGesamtNetto);
		m_fieldList._a(cfMengeWAEigenwareBewertetNetto);
		m_fieldList._a(cfMengeWAEigenwareUnbewertetNetto);
		m_fieldList._a(cfMengeWAFremdwareNetto);
		m_fieldList._a(cfPreisWANetto);
		m_fieldList._a(cfSummeWANetto);

		m_fieldList._a(cfMengeWEGesamt);
		m_fieldList._a(cfMengeWEEigenware);
		m_fieldList._a(cfMengeWEEigenwareBewertet);
		m_fieldList._a(cfMengeWEEigenwareUnbewertet);
		m_fieldList._a(cfMengeWEFremdware);
		m_fieldList._a(cfPreisWE);
		m_fieldList._a(cfSummeWE);

		m_fieldList._a(cfMengeWAGesamt);
		m_fieldList._a(cfMengeWAEigenwareNetto);
		m_fieldList._a(cfMengeWAEigenwareBewertet);
		m_fieldList._a(cfMengeWAEigenwareUnbewertet);
		m_fieldList._a(cfMengeWAFremdware);
		m_fieldList._a(cfPreisWA);
		m_fieldList._a(cfSummeWA);

		m_fieldList._a(cfMengeDiffGesamt);
		m_fieldList._a(cfMengeDiffEigenware);
		m_fieldList._a(cfMengeDiffEigenwareBewertet);
		m_fieldList._a(cfMengeDiffEigenwareUnbewertet);
		m_fieldList._a(cfMengeDiffFremdware);
		m_fieldList._a(cfPreisDiff);
		m_fieldList._a(cfSummeDiff);

		cbShowPreisangaben = new MyE2_CheckBox(new MyString("Mengedetails"), oStyleCheckboxBorderless);
		cbShowPreisangaben.setToolTipText(S.ms("Aus- und Einblenden der Eigen- und Fremdware, die bewertet und unbewertet sind").CTrans());

		cbShowPreisangaben.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				createGrid();				
			}
		});


		cbShowLagerEinzel = new MyE2_CheckBox(new MyString("Lagerdetails"), oStyleCheckboxBorderless);
		cbShowLagerEinzel.setToolTipText(S.ms("Zeigt die Eigen-, Fremd- und Streckenlager-Bestände an").CTrans());
		cbShowLagerEinzel.add_oActionAgent(new XX_ActionAgent() {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (cbShowLagerEinzel.isSelected()){
					readSaldoForSelectedSorte();
					refreshSummaryValues();
				}
				createGrid();
			}
		});


		init(oNaviList);
	}



	protected void init(E2_NavigationList oNavigationList)
			throws myException
	{

		createGrid();

		oNavigationList.add_actionAfterContentVectorIsSet(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (oExecInfo instanceof ExecINFO_OnlyCodeNavigationList){
					readLagerdatenFromSelectorsE();
				}
			}
		});

	}


	/**
	 * Baut das Grid mit den Labeln auf
	 */
	private void createGrid(){
		// alle elemente raus
		this.remove_All_FromInnerComponent();

		MyE2_Column oCol = new MyE2_Column();

		// Meldungszeile oberhalb des Grids anzeigen
		oCol.add(lblMessage);

		// Vollständig verbuchte Einträge ausblenden...
		E2_Grid oGrid = new E2_Grid()._s(5)._bo_no();

		Insets oIN = new Insets(0,2,15,2);

		Insets oIN2 = new Insets(0,2,2,10);

		RB_gld oLayout1ColRight = new RB_gld()._right_mid()._span(1)._ins(oIN);

		// Saldo über alle Infos
		// 1. Zeile
		oGrid
		._a(new RB_lab()._t(S.ms("")),	new RB_gld()._span(1)._ins(oIN))
		._a(new RB_lab()._t(S.ms("")),		new RB_gld()._span(1)._ins(oIN))
		._a(lblDescSaldoVon1,				oLayout1ColRight)
		._a(lblDescSaldoBis1,				oLayout1ColRight)
		._a(new RB_lab()._t(S.ms("")),		new RB_gld()._span(1)._ins(oIN))
		;
		// 2. Zeile
		E2_Grid oGridHeading = new E2_Grid()._bo_no();
		oGridHeading
		._a(lblDescSaldo)
		._a(cbShowLagerEinzel,				new RB_gld()._span(1)._ins(10,0,0,0));

		oGrid._a(oGridHeading,				new RB_gld()._span(1)._ins(oIN))
		._a(new RB_lab()._t(S.ms("")),		new RB_gld()._span(1)._ins(oIN))
		._a(lblDescSaldoVon2,				oLayout1ColRight)		
		._a(lblDescSaldoBis2,				oLayout1ColRight)
		._a(lblDescSaldoDiff,				oLayout1ColRight)

		// Preisoption über die erste Zeile
		._a(lblDescSaldoSorte,				new RB_gld()._span(2)._ins(oIN))
		._a(lblSaldoVon,					oLayout1ColRight)	
		._a(lblSaldoBis,					oLayout1ColRight)	
		._a(lblSaldoDiff,					oLayout1ColRight)
		;



		boolean bShowLagerEinzel = cbShowLagerEinzel.isSelected();
		if (bShowLagerEinzel){
			oGrid
			._a(lblDescSaldoSorteEigen,		new RB_gld()._span(2)._ins(oIN))
			._a(lblSaldoVonEigen,			oLayout1ColRight)	
			._a(lblSaldoBisEigen,			oLayout1ColRight)	
			._a(lblSaldoDiffEigen,			oLayout1ColRight)

			._a(lblDescSaldoSorteFremd,		new RB_gld()._span(2)._ins(oIN))
			._a(lblSaldoVonFremd,			oLayout1ColRight)		
			._a(lblSaldoBisFremd,			oLayout1ColRight)
			._a(lblSaldoDiffFremd,			oLayout1ColRight)

			._a(lblDescSaldoSorteStrecke,	new RB_gld()._span(2)._ins(oIN))
			._a(lblSaldoVonStrecke,			oLayout1ColRight)	
			._a(lblSaldoBisStrecke,			oLayout1ColRight)	
			._a(lblSaldoDiffStrecke,		oLayout1ColRight);
		}



		oGrid._a(new RB_lab()._t(S.ms("")),new RB_gld()._span(5)._ins(oIN2));

		// Überschrift
		//		oGrid._a(new RB_lab()._t(S.ms("Bewegung (alle Selektoren)")),new RB_gld()._span(1)._ins(oIN));
		E2_Grid oGridHeadingBewegung = new E2_Grid()._bo_no()._s(2)
				._a(new RB_lab()._t("Lagerbewegungen")._b())//cfHeadingBewegung.getObject())
				._a(cbShowPreisangaben,					new RB_gld()._ins(10,0,0,0));

		oGrid._a(oGridHeadingBewegung,new RB_gld()._span(1)._ins(oIN));

		cfHeadingEinheit.setValue("Einheit");
		cfHeadingWE.setValue("WE brutto");
		cfHeadingWENetto.setValue("WE netto");
		cfHeadingWA.setValue("WA brutto");
		cfHeadingWANetto.setValue("WA netto");
		cfHeadingDiff.setValue("Differenz WE(n)/WA(br.)");
		cfHeadingDiff.getObject().setToolTipText(S.ms("Differenz vom Netto WE zum Brutto WA").CTrans());

		//		oGrid._a(cfHeadingBewegung.getObject(),new RB_gld()._span(1)._ins(oIN));
		oGrid._a(new RB_lab()._t("Einheit")._b(),					oLayout1ColRight);
		oGrid._a(new RB_lab()._t("WE netto")._b(),					oLayout1ColRight);		
		oGrid._a(new RB_lab()._t("WA brutto")._b(),					oLayout1ColRight);	
		oGrid._a(new RB_lab()._t("Differenz WE(n)/WA(br.)")._b(),	oLayout1ColRight);


		// Zeile Gesamt
		oGrid._a(new RB_lab()._t(S.ms("Menge")),	new RB_gld()._span(1)._ins(oIN));
		oGrid._a(cfDescMengeGesamt.getObject(),		oLayout1ColRight);
		oGrid._a(cfMengeWEGesamtNetto.getObject(),	oLayout1ColRight);
		oGrid._a(cfMengeWAGesamt.getObject(),		oLayout1ColRight);
		oGrid._a(cfMengeDiffGesamt.getObject(),		oLayout1ColRight);


		boolean bShowBewerteteZahlen = cbShowPreisangaben.isSelected();
		if (bShowBewerteteZahlen){
			// Zeile Eigenware
			oGrid._a(new RB_lab()._t(S.ms("Anteil Eigenware")),	new RB_gld()._span(1)._ins(oIN));
			oGrid._a(cfDescMengeEigenware.getObject(), 			oLayout1ColRight);
			oGrid._a(cfMengeWEEigenwareNetto.getObject(),		oLayout1ColRight);
			oGrid._a(cfMengeWAEigenware.getObject(),			oLayout1ColRight);
			oGrid._a(cfMengeDiffEigenware.getObject(),			oLayout1ColRight);

			// Zeile	
			oGrid._a(new RB_lab()._t(S.ms(" - davon mit Preisen")),	new RB_gld()._span(1)._ins(oIN));
			oGrid._a(cfDescMengeEigenwareBewertet.getObject(),		oLayout1ColRight);
			oGrid._a(cfMengeWEEigenwareBewertetNetto.getObject(),	oLayout1ColRight);
			oGrid._a(cfMengeWAEigenwareBewertet.getObject(),		oLayout1ColRight);
			oGrid._a(cfMengeDiffEigenwareBewertet.getObject(),		oLayout1ColRight);
			// /eile
			oGrid._a(new RB_lab()._t(S.ms(" - davon ohne Preise")),	new RB_gld()._span(1)._ins(oIN));
			oGrid._a(cfDescMengeEigenwareUnbewertet.getObject(),	oLayout1ColRight);
			oGrid._a(cfMengeWEEigenwareUnbewertetNetto.getObject(),	oLayout1ColRight);
			oGrid._a(cfMengeWAEigenwareUnbewertet.getObject(),		oLayout1ColRight);
			oGrid._a(cfMengeDiffEigenwareUnbewertet.getObject(),	oLayout1ColRight);

			// Zeile Fremdware
			oGrid._a(new RB_lab()._t(S.ms("Anteil Fremdware")),		new RB_gld()._span(1)._ins(oIN));
			oGrid._a(cfDescMengeFremdware.getObject(),				oLayout1ColRight);
			oGrid._a(cfMengeWEFremdwareNetto.getObject(),			oLayout1ColRight);
			oGrid._a(cfMengeWAFremdware.getObject(),				oLayout1ColRight);
			oGrid._a(cfMengeDiffFremdware.getObject(),				oLayout1ColRight);
		}




		oGrid._a(new RB_lab()._t(S.ms("Durchschnittspreis aller Bewerteten")),new RB_gld()._span(1)._ins(oIN));
		oGrid._a(cfDescPreis.getObject(),		oLayout1ColRight);
		oGrid._a(cfPreisWENetto.getObject(),	oLayout1ColRight );
		oGrid._a(cfPreisWA.getObject(),			oLayout1ColRight);
		oGrid._a(cfPreisDiff.getObject(),		oLayout1ColRight);

		oGrid._a(new RB_lab()._t(S.ms("Gesamtbetrag")),new RB_gld()._span(1)._ins(oIN));
		oGrid._a(cfDescSumme.getObject(),		oLayout1ColRight);
		oGrid._a(cfSummeWENetto.getObject(),	oLayout1ColRight);
		oGrid._a(cfSummeWA.getObject(),			oLayout1ColRight);
		oGrid._a(new RB_lab()._t(S.ms("")),		new RB_gld()._span(1)._ins(oIN));

		oCol.add(oGrid );
		this.add(oCol,new Insets(2,5,2,5));
	}


	/**
	 * liest die Saldo-Stände des gewählten Lager/Sorten-Kombination aus
	 * @throws myException 
	 */
	private void readSaldoForSelectedSorte() throws myException{
		// Saldo-Daten löschen
		clearDataSaldo();

		String sIDLager = null;
		//		Vector<String> vIDLager = null;

		String sHauptsorte = null;
		String sIDSorte = null;
		String sDatumVon = null;
		String sDatumBis = null;
		String sIDEinheit = null;

		// Lager ermitteln
		//		vIDLager = m_oSelector.getMultiLagerSelector().get_SelectedValues();
		if (vIDLager != null && vIDLager.size()== 1){
			sIDLager = vIDLager.get(0);
		}



		vIDSorte 							= m_oSelector.getIDSelectedSorte();
		vIDHauptsorte 						= m_oSelector.getSelectedHauptsorte();

		sIDEinheit 							= m_oSelector.getIDSelectedEinheit();

		// Multiselector für Sortenbereich beachten...
		ArrayList<String[]>  arrIDs 		= m_oSelector.getIdsArray_Sortenbereich();
		VEK<String> vIDs 					= getSortenIDsFromMultiSelector(arrIDs);

		// die Vereinigungsmenge der IDs bilden 
		vIDSorte.addAll(vIDs);

		// Prüfen, ob mindestens eine Vorraussetzung gegeben ist
		if (bibALL.isEmpty(sIDEinheit)	&& (vIDSorte == null || vIDSorte.size() == 0) && (vIDHauptsorte == null || vIDHauptsorte.size() == 0) 	){
			showMessage(S.ms("Eine Ermittlung der Lagerbestände ist nur möglich, wenn eine Hauptsorte, Sorte oder Einheit ausgewählt wurde.").CTrans());
			return;
		}



		if (vIDSorte != null && vIDSorte.size() == 1){
			sIDSorte = vIDSorte.get(0);
		}

		if (vIDHauptsorte != null && vIDHauptsorte.size() == 1){
			sHauptsorte = vIDHauptsorte.get(0);
		}


		sDatumVon = m_oSelector.getDatumVon();
		sDatumBis = m_oSelector.getDatumBis();

		B2_LAG_SALDO_Ermittlung oSaldo = new B2_LAG_SALDO_Ermittlung();
		boolean bShowLagerEinzel = cbShowLagerEinzel.isSelected();
		if (!bShowLagerEinzel) {
			// nur den gesamt-Saldo anzeigen und auch nur Selektieren
			bdSaldoVon = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager, sHauptsorte,vIDHauptsorte, sIDSorte,vIDSorte, sIDEinheit, sDatumVon, true,m_oSelector.getShowEigenwarenlager(),m_oSelector.getShowFremdwarenlager(), m_oSelector.getIncludeStrecke(),false);
			bdSaldoVon = oSaldo.getSumOfAllSaldoValuesFound();

			bdSaldoBis = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager,  sHauptsorte, vIDHauptsorte,sIDSorte,vIDSorte,sIDEinheit, sDatumBis, false, m_oSelector.getShowEigenwarenlager(),m_oSelector.getShowFremdwarenlager(),m_oSelector.getIncludeStrecke(),false);
			bdSaldoBis = oSaldo.getSumOfAllSaldoValuesFound();

		} else {
			// Startsaldo ermitteln

			bdSaldoVonEigen = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager, sHauptsorte,vIDHauptsorte, sIDSorte,vIDSorte, sIDEinheit, sDatumVon, true,m_oSelector.getShowEigenwarenlager(),false, false,false);
			bdSaldoVonEigen = oSaldo.getSumOfAllSaldoValuesFound();

			bdSaldoVonFremd = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager, sHauptsorte,vIDHauptsorte, sIDSorte,vIDSorte, sIDEinheit, sDatumVon, true,false,m_oSelector.getShowFremdwarenlager(),false,false);
			bdSaldoVonFremd = oSaldo.getSumOfAllSaldoValuesFound();

			bdSaldoVonStrecke = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager, sHauptsorte,vIDHauptsorte, sIDSorte,vIDSorte, sIDEinheit, sDatumVon, true,false,false, m_oSelector.getIncludeStrecke(),false);
			bdSaldoVonStrecke = oSaldo.getSumOfAllSaldoValuesFound();

			// Zielsaldo ermitteln

			bdSaldoBisEigen = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager,  sHauptsorte, vIDHauptsorte,sIDSorte,vIDSorte,sIDEinheit, sDatumBis, false, m_oSelector.getShowEigenwarenlager(),false,false,false);
			bdSaldoBisEigen = oSaldo.getSumOfAllSaldoValuesFound();
			bdSaldoDiffEigen = bdSaldoBisEigen.subtract(bdSaldoVonEigen);

			bdSaldoBisFremd = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager,  sHauptsorte, vIDHauptsorte,sIDSorte,vIDSorte,sIDEinheit, sDatumBis, false, false,m_oSelector.getShowFremdwarenlager(),false,false);
			bdSaldoBisFremd = oSaldo.getSumOfAllSaldoValuesFound();
			bdSaldoDiffFremd = bdSaldoBisFremd.subtract(bdSaldoVonFremd);

			bdSaldoBisStrecke = BigDecimal.ZERO;
			oSaldo.readSaldoDaten(sIDLager,vIDLager,  sHauptsorte, vIDHauptsorte,sIDSorte,vIDSorte,sIDEinheit, sDatumBis, false, false,false ,m_oSelector.getIncludeStrecke(),false);
			bdSaldoBisStrecke = oSaldo.getSumOfAllSaldoValuesFound();
			bdSaldoDiffStrecke = bdSaldoBisStrecke.subtract(bdSaldoVonStrecke);

			bdSaldoVon = bdSaldoVonEigen.add(bdSaldoVonFremd).add(bdSaldoVonStrecke);
			bdSaldoBis = bdSaldoBisEigen.add(bdSaldoBisFremd).add(bdSaldoBisStrecke);
		}



		// Gesamtsaldo-Differenz
		bdSaldoDiff = bdSaldoBis.subtract(bdSaldoVon);

	}

	private VEK<String> getSortenIDsFromMultiSelector(ArrayList<String[]> multiSelektorValues) throws myException{
		VEK<String> vRet = new VEK<>();

		if (multiSelektorValues != null && multiSelektorValues.size() > 0){

			for (String[] bereich: multiSelektorValues){
				String idVon = bereich[0];
				String idBis = bereich[1];

				SEL s = new SEL()
						.ADD_Distinct()
						.ADDFIELD(ARTIKEL.id_artikel)
						.FROM(_TAB.artikel)
						.WHERE(new vgl(ARTIKEL.anr1, COMP.GE, idVon))
						.AND (new vgl(ARTIKEL.anr1,COMP.LE,idBis))
						;

				RecList20 rl = new RecList20(_TAB.artikel)._fill(s.s());

				for (Rec20 rec : rl){
					String id = rec.get_field(ARTIKEL.id_artikel).get_database_value_uf();
					vRet.add(id);
				}

			}

		}
		return vRet;
	}


	private void readLagerdatenFromSelectorsE() throws myException {
		clearData();

		refreshSummaryValues();

		vectorForSegmentation vNeu = new vectorForSegmentation();
		vNeu.addAll(oNaviList.get_vectorSegmentation());
		vNeu.set_iSegmentGroesse(100);

		int iSegmente = vNeu.get_iZahlSegmente();

		String sIDEinheitReferenz = null;

		String sIDs = "";

		String sSqlFieldEW = 
				" CASE WHEN S.ID_ADRESSE_BESITZ_LDG = " + bibALL.get_ID_ADRESS_MANDANT() + " "
						+ " 		OR S1.ID_ADRESSE_BESITZ_LDG = " + bibALL.get_ID_ADRESS_MANDANT() 
						+ " 				THEN 'Y' "
						+ "                 ELSE 'N' " 
						+ " END ";


		for (int i = 0; i< iSegmente; i++){

			// Werte für die In-Klausel generieren
			// Die Werte segmentieren, damit das Statement nicht zu groß wird
			//
			Vector<String> vSegment = vNeu.get_vSegment(i);
			try {
				sIDs = bibALL.Concatenate(vSegment, ",", "-1");
			} catch (myException e) {
				continue;
			}

			/**
			 * Einstufung der Fremdwaren/Eigenwaren/Abrechenbar/Nicht Abrechenbar
			 * -------------
			 * |  2  |  1  |
			 * |-----|-----|
			 * |EW=1 |Ab=1 |
			 * |FW=0 |NA=0 |
			 * -------------
			 * EW-Abrechenbar = 11 = 3
			 * EW-Nicht Abrechenbar = 10 = 2
			 * FW-Abrechenbar = 01 = 1  (eigentlich nicht möglich)
			 * FW-Nicht Abrechenbar = 00 = 0
			 */


			String[][] cLagerDaten = new String[0][0];

			String sIDAdresseMandant = bibALL.get_ID_ADRESS_MANDANT();


			String sSql = "";
			sSql = "SELECT " 
					+ "  NVL(ATOM1.MENGE,0) * S.MENGENVORZEICHEN ,"
					+ " NVL(ATOM1.MENGE_NETTO , NVL(ATOM1.MENGE,0)) * S.MENGENVORZEICHEN ,  " 
					+ " ATOM1.E_PREIS_BASISWAEHRUNG , " 
					+ " ATOM1.E_PREIS_RES_NETTO_MGE_BASIS,"
					+ " S.MENGENVORZEICHEN, " 
					+ " ART.ID_EINHEIT, " 
					+ " ART.MENGENDIVISOR ,  " 
					+ " E1.EINHEITKURZ, " 
					+ " E2.EINHEITKURZ, " 
					+ " CASE WHEN (S.ID_ADRESSE_BESITZ_LDG = 5525 OR S1.ID_ADRESSE_BESITZ_LDG = 5525) AND s.ID_ADRESSE_BESITZ_LDG != S1.ID_ADRESSE_BESITZ_LDG THEN  'Y' ELSE 'N' END AS ABRECHENBAR, "
					+ " atom1.E_PREIS_BASISWAEHRUNG, "
					+ " CASE WHEN S.ID_ADRESSE_BESITZ_LDG = " + sIDAdresseMandant + " then "
					+ "             CASE WHEN s.ID_ADRESSE_BESITZ_LDG = s1.ID_ADRESSE_BESITZ_LDG  " 
					+ "                  THEN 2  " 
					+ "                  ELSE 3  " 
					+ "             END  " 
					+ "     WHEN s.ID_ADRESSE_BESITZ_LDG !=  " + sIDAdresseMandant + " then   " 
					+ "             CASE WHEN s1.ID_ADRESSE_BESITZ_LDG = " + sIDAdresseMandant + "   " 
					+ "              	THEN 3  " 
					+ "              	ELSE 0  "
					+ "             END  "
					+ "    ELSE -1  "
					+ " END as status_eigen_fremd_abrechenbar   ,"
					+   sSqlFieldEW
					//				+ " , V.VARIANTE "
					+ " FROM " + bibE2.cTO() + ".JT_BG_AUSWERT S "
					+ " LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_BG_AUSWERT S1 ON S.ID_BG_VEKTOR = S1.ID_BG_VEKTOR AND S.ID_BG_ATOM != S1.ID_BG_ATOM AND ( (S.STATION_KZ = -1 * S1.STATION_KZ AND S.ID_BG_STATION != S1.ID_BG_STATION) OR (S.STATION_KZ = 0 AND S.ID_BG_STATION != S1.ID_BG_STATION)) "
					+ " INNER JOIN " + bibE2.cTO() + ".JT_BG_ATOM ATOM1 on ATOM1.ID_BG_ATOM = S.ID_BG_ATOM "
					+ " INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART on S.ID_ARTIKEL = ART.ID_ARTIKEL"
					+ " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E1 on ART.ID_EINHEIT = E1.ID_EINHEIT "
					+ " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E2 on ART.ID_EINHEIT_PREIS = E2.ID_EINHEIT " 
					+ " WHERE S.ID_BG_ATOM in ( "
					+ sIDs
					+ " ) ";

			cLagerDaten = bibDB.EinzelAbfrageInArray(sSql, (String) null);

			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0) {
				continue ;
			}

			for (int j = 0; j < cLagerDaten.length; j++) {

				String sMenge 			= cLagerDaten[j][0];
				String sMengeNetto 		= cLagerDaten[j][1];
				String sPreis 			= cLagerDaten[j][2];
				String sPreisNetto 		= cLagerDaten[j][3];
				String sTyp 			= cLagerDaten[j][4];

				Integer nTyp 			= Integer.parseInt(sTyp);

				String sIDEinheit 		= cLagerDaten[j][5];
				String sMengenDiv 		= cLagerDaten[j][6];
				String sEinheitMenge 	= cLagerDaten[j][7];
				String sEinheitPreis 	= cLagerDaten[j][8];
//				String sOhneAbrechnung 	= cLagerDaten[j][9];
				String sPreisBrutto 	= cLagerDaten[j][10];

//				String sStatusEF     	= cLagerDaten[j][11];
//				Integer nStatusEF		= Integer.parseInt(sStatusEF); 

				String sEigenware		= cLagerDaten[j][12];
				//				String sVariante		= cLagerDaten[j][13];  //nur wegen MI == mixed


				if (sIDEinheitReferenz == null){
					sIDEinheitReferenz = sIDEinheit;
					sRefEinheitMenge = sEinheitMenge;
					sRefEinheitPreis = sEinheitPreis;

				}

				if (!sIDEinheit.equals(sIDEinheitReferenz)){
					invalidateData();
					sRefEinheitMenge = "-";
					sRefEinheitPreis = "-";

					showMessage(new MyE2_String("Eine Ermittlung der Kennzahlen ist nur möglich, " +
							"wenn alle Buchungen die gleiche Mengeneinheit (z.B. Kg) haben").CTrans());

					refreshSummaryValues();
					return;
				}



				// als Brutto-Preis jetzt den E_PREIS_RESULT_BRUTTO_MGE nehmen, um korrektere Summen zu generieren.
				BigDecimal dPreis = (sPreis != null ? new BigDecimal(sPreis) : null);
				BigDecimal dPreisBrutto = (sPreisBrutto != null ? new BigDecimal(sPreisBrutto) : null);
				if (dPreisBrutto != null){
					dPreis 		= dPreisBrutto;
				}

				BigDecimal dPreisNetto  = (sPreisNetto != null ? new BigDecimal(sPreisNetto) : null);
				BigDecimal dMenge 		= (sMenge != null ? new BigDecimal(sMenge) : null);
				BigDecimal dMengeNetto 	= (sMengeNetto != null ? new BigDecimal(sMengeNetto) : null);
				BigDecimal dDiv 		= (sMengenDiv != null ? new BigDecimal(sMengenDiv) : BigDecimal.ONE);

				BigDecimal bdMengePreis 		= BigDecimal.ZERO;
				BigDecimal bdMengePreisNetto 	= BigDecimal.ZERO;


				// WE
				if (nTyp.intValue() == 1){
					if (dMenge != null){
						cfMengeWEGesamt.add(dMenge);

						if(sEigenware.equals("Y")){
							// EIGENWARE
							cfMengeWEEigenware.add(dMenge);

							// unterscheiden ob bewertet oder nicht
							if (dPreis != null){
								cfMengeWEEigenwareBewertet.add(dMenge);

								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreis = dMenge.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWE.add(bdMengePreis.multiply(dPreis))  ;
								// Menge für die Durchschnittspreise
								bdMengeWEPreisBerechnung = bdMengeWEPreisBerechnung.add(bdMengePreis);

							} else {
								cfMengeWEEigenwareUnbewertet.add(dMenge);
							}
						} else {
							//FREMDWARE
							cfMengeWEFremdware.add(dMenge);
						}
					}

					// WE NETTO-MENGE
					if (dMengeNetto != null){
						cfMengeWEGesamtNetto.add(dMengeNetto);

						if(sEigenware.equals("Y")){
							// EIGENWARE
							cfMengeWEEigenwareNetto.add(dMengeNetto);

							// unterscheiden ob bewertet oder nicht
							if (dPreisNetto != null){
								cfMengeWEEigenwareBewertetNetto.add(dMengeNetto);

								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreisNetto = dMengeNetto.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWENetto.add(bdMengePreisNetto.multiply(dPreisNetto))  ;
								// Menge für die Durchschnittspreise
								bdMengeWEPreisBerechnungNetto = bdMengeWEPreisBerechnungNetto.add(bdMengePreisNetto);

							} else {
								cfMengeWEEigenwareUnbewertetNetto.add(dMengeNetto);
							}
						} else {
							//FREMDWARE
							cfMengeWEFremdwareNetto.add(dMengeNetto);
						}
					}

				} else { // WA

					if (dMenge != null){
						// der Warenausgang wird in der DB negativ geführt. Also für die Berechnungen hier die Menge negieren.
						dMenge = dMenge.multiply(BigDecimal.ONE.negate());

						cfMengeWAGesamt.add(dMenge);

						if(sEigenware.equals("Y")){
							// EIGENWARE
							cfMengeWAEigenware.add(dMenge);

							// unterscheiden ob bewertet oder nicht
							if (dPreis != null){
								cfMengeWAEigenwareBewertet.add(dMenge);

								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreis = dMenge.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWA.add(bdMengePreis.multiply(dPreis))  ;
								// Menge für die Durchschnittspreise
								bdMengeWAPreisBerechnung = bdMengeWAPreisBerechnung.add(bdMengePreis);

							} else {
								cfMengeWAEigenwareUnbewertet.add(dMenge);
							}
						} else {
							//FREMDWARE
							cfMengeWAFremdware.add(dMenge);
						}
					}


					// WA NETTO-MENGE
					if (dMengeNetto != null){
						// der Warenausgang wird in der DB negativ geführt. Also für die Berechnungen hier die Menge negieren.
						dMengeNetto = dMengeNetto.multiply(BigDecimal.ONE.negate());

						cfMengeWAGesamtNetto.add(dMengeNetto);

						if(sEigenware.equals("Y")){
							// EIGENWARE
							cfMengeWAEigenwareNetto.add(dMengeNetto);

							// unterscheiden ob bewertet oder nicht
							if (dPreisNetto != null){
								cfMengeWAEigenwareBewertetNetto.add(dMengeNetto);

								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreisNetto = dMengeNetto.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWANetto.add(bdMengePreisNetto.multiply(dPreisNetto))  ;
								// Menge für die Durchschnittspreise
								bdMengeWAPreisBerechnungNetto = bdMengeWAPreisBerechnungNetto.add(bdMengePreisNetto);

							} else {
								cfMengeWAEigenwareUnbewertetNetto.add(dMengeNetto);
							}
						} else {
							//FREMDWARE
							cfMengeWAFremdwareNetto.add(dMengeNetto);
						}
					}
				} // end WA


			}  // end for
		} // end segmente


		if (bdMengeWEPreisBerechnung != null && bdMengeWEPreisBerechnung.compareTo(BigDecimal.ZERO)!= 0){
			cfPreisWE.setValue( cfSummeWE.getValue().divide(bdMengeWEPreisBerechnung, 2, BigDecimal.ROUND_HALF_UP) ) ;
		}

		if (bdMengeWEPreisBerechnungNetto != null && bdMengeWEPreisBerechnungNetto.compareTo(BigDecimal.ZERO)!= 0){
			cfPreisWENetto.setValue( cfSummeWENetto.getValue().divide(bdMengeWEPreisBerechnungNetto, 2, BigDecimal.ROUND_HALF_UP) );
		}

		if (bdMengeWAPreisBerechnung != null && bdMengeWAPreisBerechnung.compareTo(BigDecimal.ZERO)!= 0){
			cfPreisWA.setValue( cfSummeWA.getValue().divide(bdMengeWAPreisBerechnung, 2, BigDecimal.ROUND_HALF_UP) );
		}

		if (bdMengeWAPreisBerechnungNetto != null && bdMengeWAPreisBerechnungNetto.compareTo(BigDecimal.ZERO)!= 0){
			cfPreisWANetto.setValue( cfSummeWANetto.getValue().divide(bdMengeWAPreisBerechnungNetto, 2, BigDecimal.ROUND_HALF_UP) );
		}


		// danach die Differenzwerte bestimmen
		cfMengeDiffGesamt.setValue( cfMengeWEGesamtNetto.getValue().subtract(cfMengeWAGesamt.getValue()) );

		cfMengeDiffEigenware.setValue( cfMengeWEEigenwareNetto.getValue().subtract(cfMengeWAEigenware.getValue()) );
		cfMengeDiffEigenwareBewertet.setValue( cfMengeWEEigenwareBewertetNetto.getValue().subtract(cfMengeWAEigenwareBewertet.getValue()) );
		cfMengeDiffEigenwareUnbewertet.setValue( cfMengeWEEigenwareUnbewertetNetto.getValue().subtract(cfMengeWAEigenwareUnbewertet.getValue()) );


		cfMengeDiffFremdware.setValue(cfMengeWEFremdwareNetto.getValue().subtract(cfMengeWAFremdware.getValue()) );

		cfPreisDiff.setValue( cfPreisWA.getValue().subtract(cfPreisWENetto.getValue()) );
		cfSummeDiff.setValue( cfSummeWA.getValue().subtract(cfSummeWENetto.getValue()) );

		// wenn Alle Zahlen berechnet sind sind die Kennzahlen auch gültig und können angezeigt werden.
		bKennzahlenGueltig = true;

		//Saldo-Werte ermitteln
		this.readSaldoForSelectedSorte();

		refreshSummaryValues();
	}

	/**
	 * Löscht die Saldo-Daten des Blocks
	 */
	private void clearDataSaldo(){
		bdSaldoBis = BigDecimal.ZERO;
		bdSaldoVon = BigDecimal.ZERO;
		bdSaldoDiff = BigDecimal.ZERO;

		bdSaldoBisEigen = BigDecimal.ZERO;
		bdSaldoVonEigen = BigDecimal.ZERO;
		bdSaldoDiffEigen = BigDecimal.ZERO;
		bdSaldoBisFremd = BigDecimal.ZERO;
		bdSaldoVonFremd = BigDecimal.ZERO;
		bdSaldoDiffFremd = BigDecimal.ZERO;
		bdSaldoBisStrecke = BigDecimal.ZERO;
		bdSaldoVonStrecke = BigDecimal.ZERO;
		bdSaldoDiffStrecke = BigDecimal.ZERO;

		// Vectoren initialisiern oder löschen
		vIDSorte = new Vector<>();
		vIDHauptsorte = new Vector<>();
		vIDLager= new Vector<>();
	}


	/**
	 * Löscht alle Kenndaten des Blocks
	 */
	private void clearData() {

		clearDataSaldo();

		lblMessage.setVisible(false);
		m_fieldList.clearFieldList();

		bdMengeWEPreisBerechnung = BigDecimal.ZERO;
		bdMengeWAPreisBerechnung = BigDecimal.ZERO;
		bdMengeWEPreisBerechnungNetto = BigDecimal.ZERO;
		bdMengeWAPreisBerechnungNetto = BigDecimal.ZERO;

		// keine Kennzahlen gültig
		bKennzahlenGueltig = false;
	}



	private void invalidateData() {
		lblMessage.setVisible(false);
		bdMengeWEPreisBerechnung = BigDecimal.ZERO;

		bdMengeWAPreisBerechnung = BigDecimal.ZERO;

		bdSaldoBis = BigDecimal.ZERO;
		bdSaldoVon = BigDecimal.ZERO;
		bdSaldoDiff = BigDecimal.ZERO;


		bdSaldoBisEigen = BigDecimal.ZERO;
		bdSaldoVonEigen = BigDecimal.ZERO;
		bdSaldoDiffEigen = BigDecimal.ZERO;
		bdSaldoBisFremd = BigDecimal.ZERO;
		bdSaldoVonFremd = BigDecimal.ZERO;
		bdSaldoDiffFremd = BigDecimal.ZERO;
		bdSaldoBisStrecke = BigDecimal.ZERO;
		bdSaldoVonStrecke = BigDecimal.ZERO;
		bdSaldoDiffStrecke = BigDecimal.ZERO;

		m_fieldList.setFieldListValuesToNull();

		bdMengeWEPreisBerechnungNetto = BigDecimal.ZERO;
		bdMengeWAPreisBerechnungNetto = BigDecimal.ZERO;

		// keine Kennzahlen gültig
		bKennzahlenGueltig = false;
	}



	private void showMessage(String sMessage){
		lblMessage.setVisible(true);
		lblMessage.setText(sMessage);
	}



	private void refreshSummaryValues() {
		cfDescMengeGesamt.setValue("(" +  sRefEinheitMenge + ")");

		cfDescMengeEigenware.setValue("(" + sRefEinheitMenge + ")");
		cfDescMengeEigenwareBewertet.setValue("(" + sRefEinheitMenge + ")");
		cfDescMengeEigenwareUnbewertet.setValue( "(" +  sRefEinheitMenge + ")");

		cfDescMengeFremdware.setValue("(" + sRefEinheitMenge + ")");
		cfDescPreis.setValue("(Eur./" + sRefEinheitPreis + ")");
		cfDescSumme.setValue("(Eur.)");

		this.m_fieldList.bindFieldList();
		this.m_fieldList_caption.bindFieldList();


		try {

			//			Vector<String> vIDSorte 		= m_oSelector.getIDSelectedSorte();
			//			Vector<String> vIDHauptsorte 	= m_oSelector.getSelectedHauptsorte();
			//			Vector<String> vLager			= m_oSelector.getSelectedLager();


			String sDescription_Lager = "";
			String sDescription = "";

			if(bKennzahlenGueltig){
				if (vIDLager != null && vIDLager.size() > 0){
					if (vIDLager.size() == 1){
						sDescription_Lager = (m_oSelector.getSelectedLagerDesc()+"                              ").substring(0, 30).trim().replace(".", "") + "..."; 

					} else {
						sDescription_Lager =new MyString("<Lager mehrfach>").CTrans();
					}
				}
				if (bibALL.isEmpty(sDescription_Lager) ){

					sDescription_Lager = new MyString("Alle Lager").CTrans();
					String sLagertypen = "";
					
					if (sLagertypen.length() > 0) {
						sLagertypen = sLagertypen.substring(1);
					} 
					sDescription_Lager += " (" + sLagertypen + ")";

				}

				if (vIDSorte != null && vIDSorte.size() > 0){
					if (vIDSorte.size() == 1){
						sDescription = m_oSelector.getSelectedSorteDesc();  //vIDSorte.get(0);
					} else {
						sDescription =new MyString("<Sorte mehrfach>").CTrans();
					}
				} else if (vIDHauptsorte != null && vIDHauptsorte.size()>0) {
					if (vIDHauptsorte.size() == 1){
						sDescription = new MyString("Hauptsorte").CTrans() + " " + vIDHauptsorte.get(0);
					} else {
						sDescription =new MyString("<Hauptsorte mehrfach>").CTrans();
					}
				} else {
					sDescription =  new MyString("Alle Sorten").CTrans();
				}
				sDescription = " / " + sDescription;

			} else {
				sDescription_Lager = new MyString("Keine Bestandsermittlung möglich.").CTrans();
			}

			lblDescSaldoSorte.setText(sDescription_Lager + sDescription);
			lblDescSaldoSorteEigen.setText(new MyString("Eigenwarenlager").CTrans() + sDescription);
			lblDescSaldoSorteFremd.setText(new MyString("Fremdwarenlager").CTrans() + sDescription);
			lblDescSaldoSorteStrecke.setText(new MyString("Streckenlager").CTrans() + sDescription);


			this.lblDescSaldoVon2.setText( bibALL.FormatDatum(m_oSelector.getDatumVon()) + " (00:00)" );
			this.lblDescSaldoBis2.setText( bibALL.FormatDatum(m_oSelector.getDatumBis()) + " (23:59)" );
		} catch (myException e) {
			e.printStackTrace();
		}

		this.lblSaldoVon.setText(bibALL.makeDez(bdSaldoVon.doubleValue(),3,true));
		this.lblSaldoBis.setText(bibALL.makeDez(bdSaldoBis.doubleValue(),3,true));
		this.lblSaldoDiff.setText(bibALL.makeDez(bdSaldoDiff.doubleValue(),3,true));

		this.lblSaldoVonEigen.setText(bibALL.makeDez(bdSaldoVonEigen.doubleValue(),3,true));
		this.lblSaldoBisEigen.setText(bibALL.makeDez(bdSaldoBisEigen.doubleValue(),3,true));
		this.lblSaldoDiffEigen.setText(bibALL.makeDez(bdSaldoDiffEigen.doubleValue(),3,true));

		this.lblSaldoVonFremd.setText(bibALL.makeDez(bdSaldoVonFremd.doubleValue(),3,true));
		this.lblSaldoBisFremd.setText(bibALL.makeDez(bdSaldoBisFremd.doubleValue(),3,true));
		this.lblSaldoDiffFremd.setText(bibALL.makeDez(bdSaldoDiffFremd.doubleValue(),3,true));

		this.lblSaldoVonStrecke.setText(bibALL.makeDez(bdSaldoVonStrecke.doubleValue(),3,true));
		this.lblSaldoBisStrecke.setText(bibALL.makeDez(bdSaldoBisStrecke.doubleValue(),3,true));
		this.lblSaldoDiffStrecke.setText(bibALL.makeDez(bdSaldoDiffStrecke.doubleValue(),3,true));
	}








	/**
	 * Sammlung der Felder um zu aktualisieren und löschen
	 * @author manfred
	 * @date   10.10.2013
	 */
	private class cFieldList {
		ArrayList<cField> m_fieldList = null;

		public cFieldList() {
			m_fieldList = new ArrayList<B2_LAG_LIST_Summary_Block.cField>();
		}

		public cFieldList _a(cField field){
			m_fieldList.add(field);
			return this;
		}

		public void clearFieldList () {
			for (cField o: m_fieldList){
				o.clear();
			}
		}

		public void setFieldListValuesToNull(){
			for (cField o: m_fieldList){
				o.setValue(null);
			}
		}

		public void bindFieldList () {
			for (cField o: m_fieldList){
				o.bind();
			}
		}
	}




	/***
	 * 
	 * @author manfred
	 * @date   10.10.2013
	 */
	private abstract class cField {

		protected MyE2IF__Component		m_object = null;
		protected Object 				m_value_object = null;
		protected E2_Font				m_font = null;
		protected cFieldDataConverter 	m_dataconverter = null;

		protected cField(E2_Font font){
			m_font = font;
		}


		protected abstract void 	bind();
		protected abstract void 	clear();

		// Get/Set VALUE-OBJECT
		protected abstract Object 	getValue();
		protected abstract void 	setValue(Object value);

		// Get GUI
		protected abstract Object getObject();

	}



	private abstract class cField_Label extends cField {

		protected cField_Label(E2_Font font){
			super(font);
			m_object = new RB_lab()._t("");
			m_object.c().setFont(m_font);
		}


		@Override
		protected RB_lab getObject() {
			return (RB_lab)m_object;
		}


		protected abstract Object 	getValue();

		protected abstract void 	setValue(Object value);

	}



	/***
	 * 
	 * @author manfred
	 * @date   10.10.2013
	 */
	private class CLabel_String extends cField_Label{


		public CLabel_String() {
			this(new E2_FontPlain());
		}


		public CLabel_String(E2_Font font) {
			super(font);
			m_dataconverter = new cFieldDataConverter_String();
			m_dataconverter.setDisplayOnNull("-");
		}

		@Override
		protected String getValue() {
			return (String)m_value_object;
		}


		@Override
		protected void setValue(Object value) {
			if (value == null){
				m_value_object = null;
			} else 	if (value.getClass().equals(String.class)){
				m_value_object = value;
			} else {
				m_value_object = null;
			}
		}

		@Override
		protected void bind() {
			getObject()._t(m_dataconverter.doTransformation(this.getValue()));
		}

		@Override
		protected void clear() {
			setValue("");
		}




	}

	/***
	 * 
	 * @author manfred
	 * @date   10.10.2013
	 */
	private class CLabel_BigDecimal extends cField_Label{

		public CLabel_BigDecimal() {
			this(new E2_FontPlain());
		}


		public CLabel_BigDecimal(E2_Font font) {
			super(font);
			m_value_object = new BigDecimal(0);
			m_dataconverter = new cFieldDataConverter_BigDecimal();
			m_dataconverter.setDisplayOnNull("-");
		}


		@Override
		protected BigDecimal getValue() {
			return (BigDecimal)m_value_object;
		}


		@Override
		protected void setValue(Object value) {
			if (value == null){
				m_value_object = null;
			} else  if (value.getClass().equals(BigDecimal.class)){
				m_value_object = value;
			} else {
				m_value_object = null;
			}
		}

		/**
		 * Addiert den Wert 
		 * @param bdValueToAdd
		 */
		protected void add(BigDecimal bdValueToAdd){
			if (bdValueToAdd != null){
				m_value_object = ((BigDecimal)m_value_object).add(bdValueToAdd);
			}
		}

		@Override
		protected void bind() {
			getObject().setText(m_dataconverter.doTransformation(this.getValue()));
		}


		@Override
		protected void clear() {
			m_value_object = BigDecimal.ZERO;
		}


	}

	private abstract class cFieldDataConverter {

		protected String 	m_null_value = null;

		public cFieldDataConverter() {
			//default Konstruktor
		}
		protected void   setDisplayOnNull(String sDisplayWhenNull){
			m_null_value = sDisplayWhenNull;
		}

		protected abstract String doTransformation(Object data);
	}



	private class cFieldDataConverter_BigDecimal extends cFieldDataConverter_Double{

		public cFieldDataConverter_BigDecimal(){
			super();
		}

		@Override
		protected String doTransformation(Object data) {
			String sRet = "?";
			if (data != null){
				if (data.getClass().equals(BigDecimal.class)){
					sRet = bibALL.makeDez( ((BigDecimal) data).doubleValue(),3,true);
				}
			} else {
				sRet = m_null_value;
			}
			return sRet;
		}

	}


	private class cFieldDataConverter_Double extends cFieldDataConverter{

		public cFieldDataConverter_Double(){
			this(3,true);
		}

		public cFieldDataConverter_Double(int anzahl_dezimalstellen, boolean bZeigeTausenderpunkt){
		}

		@Override
		protected String doTransformation(Object data) {
			String sRet = "?";
			if (data != null){
				if (data.getClass().equals(Double.class)){
					sRet = bibALL.makeDez((Double)data,3,true);
				}
			} else {
				sRet = m_null_value;
			}
			return sRet;
		}
	}


	private class cFieldDataConverter_String extends cFieldDataConverter{

		private boolean 	m_uppercase = false;

		public cFieldDataConverter_String(){
			this(false);
		}

		public cFieldDataConverter_String(boolean bUpperCase){
			m_uppercase = bUpperCase;
		}

		@Override
		protected String doTransformation(Object data) {
			String sRet = "?";

			if (data != null){
				if (data.getClass().equals(String.class)){
					sRet = m_uppercase ? ((String)data).toUpperCase() : (String)data;
				} else {
					sRet = data.toString();
				}
			} else {
				sRet = m_null_value;
			}

			return sRet;
		}
	}
}
