package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
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
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.vectorForSegmentation;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;

public class ATOM_LAG_LIST_Summay extends E2_ListSelectorContainer
{
	
	private cFieldList      m_fieldList 			= new cFieldList();
	private cFieldList      m_fieldList_caption   	= new cFieldList();
	
	
	
	
	
	// Objekte
	private  MyE2_Label 	lblMessage = null;

	
	//
	// gebundene Elemente 
	//
	// Spalte Einheit
	private  CLabel_String 		cfHeadingBewegung =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_String 		cfHeadingEinheit =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_String 		cfDescMengeGesamt = new CLabel_String();
	
	private  CLabel_String  	cfDescMengeEigenware = new CLabel_String();;
	private  CLabel_String		cfDescMengeEigenwareBewertet = new CLabel_String(new E2_FontItalic(-2));;
	private  CLabel_String 		cfDescMengeEigenwareUnbewertet = new CLabel_String(new E2_FontItalic(-2));;
	
	private  CLabel_String  	cfDescMengeFremdware = new CLabel_String();;
	private  CLabel_String 		cfDescPreis = new CLabel_String();;
	private  CLabel_String 		cfDescSumme = new CLabel_String();;
	

	// Spalte WE
	private  CLabel_String 		cfHeadingWE =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeWEGesamt = new CLabel_BigDecimal();;
	
	private  CLabel_BigDecimal 	cfMengeWEEigenware = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWEEigenwareBewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));;
	private  CLabel_BigDecimal 	cfMengeWEEigenwareUnbewertet = new CLabel_BigDecimal(new E2_FontItalic(-2));;
	
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
	private  MyE2_Label		lblDescSaldo = null;  
	private  MyE2_Label    lblDescSaldoSorte = null;
	private  MyE2_Label    lblDescSaldoSorteEigen = null;
	private  MyE2_Label    lblDescSaldoSorteFremd = null;
	private  MyE2_Label    lblDescSaldoSorteStrecke = null;
	
	private  MyE2_Label		lblDescSaldoVon1 = null;
	private  MyE2_Label		lblDescSaldoVon2 = null;
	private  MyE2_Label		lblSaldoVon = null;
	private  MyE2_Label		lblSaldoVonEigen = null;
	private  MyE2_Label		lblSaldoVonFremd = null;
	private  MyE2_Label		lblSaldoVonStrecke = null;
	
	private  MyE2_Label		lblDescSaldoBis1 = null;
	private  MyE2_Label		lblDescSaldoBis2 = null;
	private  MyE2_Label		lblSaldoBis = null;
	private  MyE2_Label		lblSaldoBisEigen = null;
	private  MyE2_Label		lblSaldoBisFremd = null;
	private  MyE2_Label		lblSaldoBisStrecke = null;
	
	private  MyE2_Label    lblDescSaldoDiff = null;
	private  MyE2_Label    lblSaldoDiff = null;
	private  MyE2_Label    lblSaldoDiffEigen = null;
	private  MyE2_Label    lblSaldoDiffFremd = null;
	private  MyE2_Label    lblSaldoDiffStrecke = null;
	
	
	private  String 		sRefEinheitMenge = "-";
	private  String 		sRefEinheitPreis = "-";
	private  boolean      bKennzahlenGueltig = false;
	
	
	
	// Variablen zur Berechnung der Preise
	private  BigDecimal		bdMengeWEPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWEPreisBerechnungNetto = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAPreisBerechnungNetto = BigDecimal.ZERO;
	
	
	// Werte für die Saldi
	// 2015-05-11: Manfred: Die Saldi werden jetzt unterteilt in Eigenaren-,Fremdwaren- und Streckenbestände 
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
	private ATOM_LAG_LIST_Selector m_oSelector = null;
	
	
	// checkbox zum ein-ausblenden der Kostenanteile
	private MyE2_CheckBox    cbShowPreisangaben;
	private MyE2_CheckBox    cbShowLagerEinzel;
	private E2_MutableStyle  oStyleCheckboxBorderless = new E2_MutableStyle();
	
	
	
	Vector<String> vIDSorte 		= null;
	Vector<String> vIDHauptsorte 	= null;
	Vector<String> vIDLager			= null;
	
	
//	E2_XYPlot plot = new E2_XYPlot();
//	E2_ChartDisplay chart_display = null;
	
	public ATOM_LAG_LIST_Summay(E2_NavigationList oNavigationList, ATOM_LAG_LIST_Selector oSelector) throws myException
	{
		super(new MyE2_String("Lagerinformationen / Summen / Durchschnittswerte"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		oNaviList = oNavigationList;
		m_oSelector = oSelector;

		// Checkbox Borderless definieren
		oStyleCheckboxBorderless.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		
		lblMessage = new MyE2_Label("");
		lblMessage.setFont(new E2_FontBold(0));
		lblMessage.setForeground(Color.RED);
		lblMessage.setVisible(false);

		
		lblDescSaldo 	= new MyE2_Label(new MyE2_String("Lagerbestände"));
		lblDescSaldo.setFont(new E2_FontBold());
		lblDescSaldoSorte = new MyE2_Label("-");
		
		lblDescSaldoSorteEigen= new MyE2_Label("-");
		lblDescSaldoSorteFremd= new MyE2_Label("-");
		lblDescSaldoSorteStrecke= new MyE2_Label("-");
		
		lblDescSaldoVon1 = new MyE2_Label(new MyE2_String("Morgens zum"));
		lblDescSaldoVon1.setFont(new E2_FontBold());
		lblDescSaldoVon2 = new MyE2_Label(new MyE2_String(""));
		lblDescSaldoVon2.setFont(new E2_FontBold());
		
		
		lblDescSaldoBis1 = new MyE2_Label(new MyE2_String("Abends zum"));
		lblDescSaldoBis1.setFont(new E2_FontBold());
		lblDescSaldoBis2 = new MyE2_Label(new MyE2_String(""));
		lblDescSaldoBis2.setFont(new E2_FontBold());
		lblDescSaldoDiff = new MyE2_Label(new MyE2_String("Differenz"));
		lblDescSaldoDiff.setFont(new E2_FontBold());
		
		lblSaldoVon = new MyE2_Label("-");
		lblSaldoBis = new MyE2_Label("-");
		lblSaldoDiff = new MyE2_Label("-");
		
		lblSaldoVonEigen = new MyE2_Label("-",new E2_FontItalic(-2));
		lblSaldoBisEigen = new MyE2_Label("-",new E2_FontItalic(-2));
		lblSaldoDiffEigen = new MyE2_Label("-",new E2_FontItalic(-2));
		
		lblSaldoVonFremd = new MyE2_Label("-",new E2_FontItalic(-2));
		lblSaldoBisFremd = new MyE2_Label("-",new E2_FontItalic(-2));
		lblSaldoDiffFremd = new MyE2_Label("-",new E2_FontItalic(-2));
		
		lblSaldoVonStrecke = new MyE2_Label("-",new E2_FontItalic(-2));
		lblSaldoBisStrecke = new MyE2_Label("-",new E2_FontItalic(-2));
		lblSaldoDiffStrecke = new MyE2_Label("-",new E2_FontItalic(-2));

		
		// Überschriften TAbelle Summenblock
		cfHeadingBewegung.setValue("Lagerbewegungen");
		cfHeadingEinheit.setValue("Einheit");
		cfHeadingWE.setValue("WE brutto");
		cfHeadingWENetto.setValue("WE netto");
		cfHeadingWA.setValue("WA brutto");
		cfHeadingWANetto.setValue("WA netto");
		cfHeadingDiff.setValue("Differenz WE(n)/WA(br.)");
		cfHeadingDiff.getObject().setToolTipText(new MyE2_String("Differenz vom Netto WE zum Brutto WA").CTrans());
		
		
		m_fieldList_caption.add(cfHeadingBewegung);
		m_fieldList_caption.add(cfHeadingEinheit);
		m_fieldList_caption.add(cfHeadingWENetto);
		m_fieldList_caption.add(cfHeadingWE);
		m_fieldList_caption.add(cfHeadingWANetto);
		m_fieldList_caption.add(cfHeadingWA);
		m_fieldList_caption.add(cfHeadingDiff);
		
		
		// Bindings definieren
		m_fieldList.add(cfDescMengeGesamt);
		m_fieldList.add(cfDescMengeEigenware);
		m_fieldList.add(cfDescMengeEigenwareBewertet);
		m_fieldList.add(cfDescMengeEigenwareUnbewertet);
		m_fieldList.add(cfDescMengeFremdware);
		m_fieldList.add(cfDescPreis);
		m_fieldList.add(cfDescSumme);
		
		m_fieldList.add(cfMengeWEGesamtNetto);
		m_fieldList.add(cfMengeWEEigenwareNetto);
		m_fieldList.add(cfMengeWEEigenwareBewertetNetto);
		m_fieldList.add(cfMengeWEEigenwareUnbewertetNetto);
		m_fieldList.add(cfMengeWEFremdwareNetto);
		m_fieldList.add(cfPreisWENetto);
		m_fieldList.add(cfSummeWENetto);

		m_fieldList.add(cfMengeWAEigenware);
		m_fieldList.add(cfMengeWAGesamtNetto);
		m_fieldList.add(cfMengeWAEigenwareBewertetNetto);
		m_fieldList.add(cfMengeWAEigenwareUnbewertetNetto);
		m_fieldList.add(cfMengeWAFremdwareNetto);
		m_fieldList.add(cfPreisWANetto);
		m_fieldList.add(cfSummeWANetto);

		m_fieldList.add(cfMengeWEGesamt);
		m_fieldList.add(cfMengeWEEigenware);
		m_fieldList.add(cfMengeWEEigenwareBewertet);
		m_fieldList.add(cfMengeWEEigenwareUnbewertet);
		m_fieldList.add(cfMengeWEFremdware);
		m_fieldList.add(cfPreisWE);
		m_fieldList.add(cfSummeWE);

		m_fieldList.add(cfMengeWAGesamt);
		m_fieldList.add(cfMengeWAEigenwareNetto);
		m_fieldList.add(cfMengeWAEigenwareBewertet);
		m_fieldList.add(cfMengeWAEigenwareUnbewertet);
		m_fieldList.add(cfMengeWAFremdware);
		m_fieldList.add(cfPreisWA);
		m_fieldList.add(cfSummeWA);

		m_fieldList.add(cfMengeDiffGesamt);
		m_fieldList.add(cfMengeDiffEigenware);
		m_fieldList.add(cfMengeDiffEigenwareBewertet);
		m_fieldList.add(cfMengeDiffEigenwareUnbewertet);
		m_fieldList.add(cfMengeDiffFremdware);
		m_fieldList.add(cfPreisDiff);
		m_fieldList.add(cfSummeDiff);
		
		cbShowPreisangaben = new MyE2_CheckBox(new MyString("Mengedetails"), oStyleCheckboxBorderless);
		cbShowPreisangaben.setToolTipText(new MyE2_String("Aus- und Einblenden der Eigen- und Fremdware, die bewertet und unbewertet sind").CTrans());
		
		cbShowPreisangaben.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				createGrid();				
			}
		});
		
		
		cbShowLagerEinzel = new MyE2_CheckBox(new MyString("Lagerdetails"), oStyleCheckboxBorderless);
		cbShowLagerEinzel.setToolTipText(new MyE2_String("Zeigt die Eigen-, Fremd- und Streckenlager-Bestände an").CTrans());
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
					ExecINFO_OnlyCodeNavigationList o = (ExecINFO_OnlyCodeNavigationList) oExecInfo;
					readLagerdatenFromNavigationListEntriesE(o.get_NavigationList());
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
		MyE2_Grid oGrid = new MyE2_Grid(5,0);
		
		Insets oIN = new Insets(0,2,15,2);
		Insets oIN2 = new Insets(0,2,2,10);
		Insets oINEinzug = new Insets(10,2,15,2);

		GridLayoutData oLayout1ColRight = new GridLayoutData();
		oLayout1ColRight.setAlignment(Alignment.ALIGN_RIGHT);
		oLayout1ColRight.setColumnSpan(1);
		oLayout1ColRight.setInsets(oIN);
		
		
		// Saldo über alle Infos
		// 1. Zeile
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);
		oGrid.add(lblDescSaldoVon1,oLayout1ColRight);		
		oGrid.add(lblDescSaldoBis1,oLayout1ColRight);	
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);
		
		// 2. Zeile
		MyE2_Grid oGridHeading = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridHeading.add(lblDescSaldo);
		oGridHeading.add(cbShowLagerEinzel,E2_INSETS.I_10_0_0_0);
		oGrid.add(oGridHeading,1,oIN);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);
		oGrid.add(lblDescSaldoVon2,oLayout1ColRight);		
		oGrid.add(lblDescSaldoBis2,oLayout1ColRight);	
		oGrid.add(lblDescSaldoDiff,oLayout1ColRight);
		
		// Preisoption über die erste Zeile
		oGrid.add(lblDescSaldoSorte,2,oIN);
		oGrid.add(lblSaldoVon,oLayout1ColRight);		
		oGrid.add(lblSaldoBis,oLayout1ColRight);	
		oGrid.add(lblSaldoDiff,oLayout1ColRight);
		
		
				
				
		boolean bShowLagerEinzel = cbShowLagerEinzel.isSelected();
		if (bShowLagerEinzel){
			oGrid.add(lblDescSaldoSorteEigen,2,oIN);
			oGrid.add(lblSaldoVonEigen,oLayout1ColRight);		
			oGrid.add(lblSaldoBisEigen,oLayout1ColRight);	
			oGrid.add(lblSaldoDiffEigen,oLayout1ColRight);
			
			oGrid.add(lblDescSaldoSorteFremd,2,oIN);
			oGrid.add(lblSaldoVonFremd,oLayout1ColRight);		
			oGrid.add(lblSaldoBisFremd,oLayout1ColRight);	
			oGrid.add(lblSaldoDiffFremd,oLayout1ColRight);
			
			oGrid.add(lblDescSaldoSorteStrecke,2,oIN);
			oGrid.add(lblSaldoVonStrecke,oLayout1ColRight);		
			oGrid.add(lblSaldoBisStrecke,oLayout1ColRight);	
			oGrid.add(lblSaldoDiffStrecke,oLayout1ColRight);
		}
		

		
		oGrid.add(new MyE2_Label(new MyE2_String("")),5,oIN2);
		
		
		
		
		// Überschrift
//		oGrid.add(new MyE2_Label(new MyE2_String("Bewegung (alle Selektoren)")),1,oIN);
		MyE2_Grid oGridHeadingBewegung = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridHeadingBewegung.add(cfHeadingBewegung.getObject());
		oGridHeadingBewegung.add(cbShowPreisangaben,E2_INSETS.I_10_0_0_0);
		oGrid.add(oGridHeadingBewegung,1,oIN);
		
//		oGrid.add(cfHeadingBewegung.getObject(),1,oIN);
		oGrid.add(cfHeadingEinheit.getObject(),oLayout1ColRight);
		oGrid.add(cfHeadingWENetto.getObject(),oLayout1ColRight);		
		oGrid.add(cfHeadingWA.getObject(),oLayout1ColRight);	
		oGrid.add(cfHeadingDiff.getObject(),oLayout1ColRight);
		

		// Zeile Gesamt
		oGrid.add(new MyE2_Label(new MyE2_String("Menge")),1,oIN);
		oGrid.add(cfDescMengeGesamt.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWEGesamtNetto.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWAGesamt.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeDiffGesamt.getObject(),oLayout1ColRight);
		
		
		boolean bShowBewerteteZahlen = cbShowPreisangaben.isSelected();
		if (bShowBewerteteZahlen){
			// Zeile Eigenware
			oGrid.add(new MyE2_Label(new MyE2_String("Anteil Eigenware")),1,oIN);
			oGrid.add(cfDescMengeEigenware.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWEEigenwareNetto.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWAEigenware.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeDiffEigenware.getObject(),oLayout1ColRight);

			// Zeile	
			oGrid.add(new MyE2_Label(new MyE2_String(" - davon mit Preisen")),1,oINEinzug);
			oGrid.add(cfDescMengeEigenwareBewertet.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWEEigenwareBewertetNetto.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWAEigenwareBewertet.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeDiffEigenwareBewertet.getObject(),oLayout1ColRight);
			// /eile
			oGrid.add(new MyE2_Label(new MyE2_String(" - davon ohne Preise")),1,oINEinzug);
			oGrid.add(cfDescMengeEigenwareUnbewertet.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWEEigenwareUnbewertetNetto.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWAEigenwareUnbewertet.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeDiffEigenwareUnbewertet.getObject(),oLayout1ColRight);
			
			// Zeile Fremdware
			oGrid.add(new MyE2_Label(new MyE2_String("Anteil Fremdware")),1,oIN);
			oGrid.add(cfDescMengeFremdware.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWEFremdwareNetto.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeWAFremdware.getObject(),oLayout1ColRight);
			oGrid.add(cfMengeDiffFremdware.getObject(),oLayout1ColRight);
		}


		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Durchschnittspreis aller Bewerteten")),1,oIN);
		oGrid.add(cfDescPreis.getObject(),oLayout1ColRight);
		oGrid.add(cfPreisWENetto.getObject(),oLayout1ColRight );
		oGrid.add(cfPreisWA.getObject(),oLayout1ColRight);
		oGrid.add(cfPreisDiff.getObject(),oLayout1ColRight);
		
		oGrid.add(new MyE2_Label(new MyE2_String("Gesamtbetrag")),1,oIN);
		oGrid.add(cfDescSumme.getObject(),oLayout1ColRight);
		oGrid.add(cfSummeWENetto.getObject(),oLayout1ColRight);
		oGrid.add(cfSummeWA.getObject(),oLayout1ColRight);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);

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
		vIDLager = m_oSelector.get_oSelMulti_Lager().get_SelectedValues();
		if (vIDLager != null && vIDLager.size()== 1){
			sIDLager = vIDLager.get(0);
		}
		

		
		vIDSorte 		= m_oSelector.getIDSelectedSorte();
		vIDHauptsorte 	= m_oSelector.getSelectedHauptsorte();
		
		sIDEinheit = m_oSelector.getIDSelectedEinheit();
		
		// Multiselector für Sortenbereich beachten...
		String         sWhereMultiSortenbereich  = m_oSelector.getWHERESortenbereich();
		ArrayList<String[]>  arrIDs = m_oSelector.getArrayOfIds_Sortenbereich();
		Vector<String> vIDs = getSortenIDsFromMultiSelector(arrIDs);
		
		// die Vereinigungsmenge der IDs bilden 
		vIDSorte.addAll(vIDs);

		// Prüfen, ob mindestens eine Vorraussetzung gegeben ist
		if (bibALL.isEmpty(sIDEinheit)	&& (vIDSorte == null || vIDSorte.size() == 0) && (vIDHauptsorte == null || vIDHauptsorte.size() == 0) 	){
			showMessage(new MyE2_String("Eine Ermittlung der Lagerbestände ist nur möglich, " +
					"wenn eine Hauptsorte, Sorte oder Einheit ausgewählt wurde.").CTrans());
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
		
		ATOM_LagerSaldoErmittlung oSaldo = new ATOM_LagerSaldoErmittlung();
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
	
	private Vector<String> getSortenIDsFromMultiSelector(ArrayList<String[]> multiSelektorValues) throws myException{
		Vector<String> vRet = new Vector<>();

		if (multiSelektorValues != null && multiSelektorValues.size() > 0){
			vRet = new Vector<>();
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

	
	/**
	 * Ermitteln der Lagerdaten, die in der Navigation-List vorhandenen Einträge des Lagerkontos
	 * @throws myException 
	 */
	private void readLagerdatenFromNavigationListEntriesE(E2_NavigationList oNaviList) throws myException {
		
		clearData();

		refreshSummaryValues();
		
		vectorForSegmentation vNeu = new vectorForSegmentation();
		vNeu.addAll(oNaviList.get_vectorSegmentation());
		vNeu.set_iSegmentGroesse(100);
				
		int iSegmente = vNeu.get_iZahlSegmente();

		String sIDEinheitReferenz = null;
		
		String sIDs = "";
		
		String sSqlFieldEW = 
				  " CASE WHEN S.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() + " "
				+ " 					OR S1.ID_ADRESSE_BESITZER = " + bibALL.get_ID_ADRESS_MANDANT() 
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
				+ " NVL(A.MENGE,0)  * S.MENGENVORZEICHEN , "
				+ " nvl(A.MENGE_NETTO,nvl(A.MENGE,0)) * S.MENGENVORZEICHEN , " 
				+ " A.E_PREIS , " 
				+ " A.E_PREIS_RESULT_NETTO_MGE, "
				+ " S.MENGENVORZEICHEN, " 
				+ " ART.ID_EINHEIT, " 
				+ " ART.MENGENDIVISOR ,  " 
				+ " E1.EINHEITKURZ, " 
				+ " E2.EINHEITKURZ, " 
				+ " CASE WHEN UPPER(A.ABRECHENBAR) = UPPER('N') then UPPER('Y') ELSE UPPER('N') END as OHNE_ABRECHNUNG, "
				+ " A.E_PREIS_RESULT_BRUTTO_MGE ,"
				+ " CASE WHEN S.ID_ADRESSE_BESITZER = " + sIDAdresseMandant + " then "
				+ "             CASE WHEN s.ID_ADRESSE_BESITZER = s1.ID_ADRESSE_BESITZER  " 
				+ "                  THEN 2  " 
				+ "                  ELSE 3  " 
				+ "             END  " 
				+ "     WHEN s.ID_ADRESSE_BESITZER !=  " + sIDAdresseMandant + " then   " 
				+ "             CASE WHEN s1.ID_ADRESSE_BESITZER = " + sIDAdresseMandant + "   " 
				+ "              	THEN 3  " 
				+ "              	ELSE 0  "
				+ "             END  "
				+ "    ELSE -1  "
				+ " END as status_eigen_fremd_abrechenbar   ,"
				+   sSqlFieldEW
				+ " , V.VARIANTE "
				+ " FROM " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A ON S.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V ON V.ID_BEWEGUNG_VEKTOR = A.ID_BEWEGUNG_VEKTOR "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART on A.ID_ARTIKEL = ART.ID_ARTIKEL"
			    + " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E1 on ART.ID_EINHEIT = E1.ID_EINHEIT "
			    + " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E2 on ART.ID_EINHEIT_PREIS = E2.ID_EINHEIT " 
			    + " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S1 ON S.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM AND S1.MENGENVORZEICHEN = (S.MENGENVORZEICHEN * -1) "
			    + " WHERE S.ID_BEWEGUNG_STATION in ( "
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
				String sOhneAbrechnung 	= cLagerDaten[j][9];
				String sPreisBrutto 	= cLagerDaten[j][10];
				
				String sStatusEF     	= cLagerDaten[j][11];
				Integer nStatusEF		= Integer.parseInt(sStatusEF); 
				
				String sEigenware		= cLagerDaten[j][12];
				String sVariante		= cLagerDaten[j][13];  //nur wegen MI == mixed
								
				
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
					
					sLagertypen = sLagertypen  + ( m_oSelector.getShowEigenwarenlager() ? "/EW" : "");
					sLagertypen = sLagertypen  + ( m_oSelector.getShowFremdwarenlager() ? "/FW" : "");
					sLagertypen = sLagertypen  + ( m_oSelector.getIncludeStrecke() ? "/S" : "");
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
			// TODO Auto-generated catch block
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
			m_fieldList = new ArrayList<ATOM_LAG_LIST_Summay.cField>();
		}
		
		public void add(cField field){
			m_fieldList.add(field);
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
		
		protected MyE2IF__Component 	m_object = null;
		protected Object 				m_value_object = null;
		protected E2_Font				m_font = null;
		protected cFieldDataConverter 	m_dataconverter = null;

		
		protected cField(){
			this(new E2_FontPlain());
		}
		
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
		
		protected cField_Label(){
			this(new E2_FontPlain());
		}
		
		
		protected cField_Label(E2_Font font){
			super(font);
			m_object = new MyE2_Label("", m_font);
		}
		
		
		@Override
		protected MyE2_Label getObject() {
			return (MyE2_Label)m_object;
		}
		
		
//		protected abstract void 	bind();
//		protected abstract void 	clear();
//		protected abstract void     clear(String sClearText);
		
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
			getObject().set_Text(m_dataconverter.doTransformation(this.getValue()));
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

		private int 		m_anzahl_dezimalstellen = 3;
		private boolean 	m_zeige_tausender = true;
		
		public cFieldDataConverter_BigDecimal(){
			super();
		}
		
		public cFieldDataConverter_BigDecimal(int anzahl_dezimalstellen, boolean bZeigeTausenderpunkt){
			super(anzahl_dezimalstellen,bZeigeTausenderpunkt);
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

		private int 		m_anzahl_dezimalstellen = 3;
		private boolean 	m_zeige_tausender = true;
		
		
		public cFieldDataConverter_Double(){
			this(3,true);
		}
		
		public cFieldDataConverter_Double(int anzahl_dezimalstellen, boolean bZeigeTausenderpunkt){
			m_anzahl_dezimalstellen = anzahl_dezimalstellen;
			m_zeige_tausender 		= bZeigeTausenderpunkt;
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
