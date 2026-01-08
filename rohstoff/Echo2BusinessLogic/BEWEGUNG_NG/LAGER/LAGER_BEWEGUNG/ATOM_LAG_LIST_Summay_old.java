package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.vectorForSegmentation;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;

public class ATOM_LAG_LIST_Summay_old extends E2_ListSelectorContainer
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
	private  CLabel_String 		cfDescMenge = new CLabel_String();;
	private  CLabel_String  	cfDescMengeAbrechenbar = new CLabel_String();;
	private  CLabel_String		cfDescMengeBewertet = new CLabel_String();;
	private  CLabel_String 		cfDescMengeUnbewertet = new CLabel_String();;
	private  CLabel_String  	cfDescMengeOhneAbrechnung = new CLabel_String();;
	private  CLabel_String 		cfDescPreis = new CLabel_String();;
	private  CLabel_String 		cfDescSumme = new CLabel_String();;
	

	// Spalte WE
	private  CLabel_String 		cfHeadingWE =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeWE = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWEAbrechenbar = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWEBewertet = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWEUnbewertet = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWEOhneAbrechnung = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisWE = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeWE = new CLabel_BigDecimal();;

	
	// Spalte WE-Netto
	private  CLabel_String 		cfHeadingWENetto = new CLabel_String(new E2_FontBold(0));
	private  CLabel_BigDecimal 	cfMengeWENetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfMengeWEAbrechenbarNetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfMengeWEBewertetNetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfMengeWEUnbewertetNetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfMengeWEOhneAbrechnungNetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfPreisWENetto = new CLabel_BigDecimal();
	private  CLabel_BigDecimal 	cfSummeWENetto = new CLabel_BigDecimal();
	
	
	// Spalte WA
	private  CLabel_String 		cfHeadingWA =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeWA = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAAbrechenbar = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWABewertet = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAUnbewertet = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAOhneAbrechnung = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisWA = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeWA = new CLabel_BigDecimal();;

	// Spalte WA-Netto
	private  CLabel_String 		cfHeadingWANetto =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeWANetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAAbrechenbarNetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWABewertetNetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAUnbewertetNetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeWAOhneAbrechnungNetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisWANetto = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeWANetto = new CLabel_BigDecimal();;

	
	// Spalte DIFFERENZ
	private  CLabel_String 		cfHeadingDiff =  new CLabel_String(new E2_FontBold(0));;
	private  CLabel_BigDecimal 	cfMengeDiff = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeDiffAbrechenbar = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeDiffBewertet = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeDiffUnbewertet = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfMengeDiffOhneAbrechnung = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfPreisDiff = new CLabel_BigDecimal();;
	private  CLabel_BigDecimal 	cfSummeDiff = new CLabel_BigDecimal();;
	
	
	
	// Bereich SALDO-Ermittlung
	private  MyE2_Label		lblDescSaldo = null;  
	private  MyE2_Label    lblDescSaldoSorte = null;
	private  MyE2_Label		lblDescSaldoVon1 = null;
	private  MyE2_Label		lblDescSaldoVon2 = null;
	
	private  MyE2_Label		lblSaldoVon = null;
	private  MyE2_Label		lblDescSaldoBis1 = null;
	private  MyE2_Label		lblDescSaldoBis2 = null;
	private  MyE2_Label		lblSaldoBis = null;
	private  MyE2_Label    lblDescSaldoDiff = null;
	private  MyE2_Label    lblSaldoDiff = null;
	
	
	private  String 		sRefEinheitMenge = "-";
	private  String 		sRefEinheitPreis = "-";
	private  boolean      bKennzahlenGueltig = false;
	
	
	
	// Variablen zur Berechnung der Preise
	private  BigDecimal		bdMengeWEPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWEPreisBerechnungNetto = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAPreisBerechnungNetto = BigDecimal.ZERO;
	
	
	// Werte für die Saldi
	private  BigDecimal 	bdSaldoVon = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoBis = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoDiff = BigDecimal.ZERO;
	
	
	
	private E2_NavigationList	oNaviList = null;
	
	// den Selektor der Lagerbewegung, um die Saldi zu ermitteln
	private ATOM_LAG_LIST_Selector m_oSelector = null;
	
	
//	E2_XYPlot plot = new E2_XYPlot();
//	E2_ChartDisplay chart_display = null;
	
	public ATOM_LAG_LIST_Summay_old(E2_NavigationList oNavigationList, ATOM_LAG_LIST_Selector oSelector) throws myException
	{
		super(new MyE2_String("Lagerinformationen / Summen / Durchschnittswerte"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		oNaviList = oNavigationList;
		m_oSelector = oSelector;
		
		lblMessage = new MyE2_Label("");
		lblMessage.setFont(new E2_FontBold(0));
		lblMessage.setForeground(Color.RED);
		lblMessage.setVisible(false);

		
		lblDescSaldo 	= new MyE2_Label(new MyE2_String("Bestand (Sel: Lager, Lag.-Art, Hauptsorte, Sorte)"));
		lblDescSaldo.setFont(new E2_FontBold());
		lblDescSaldoSorte = new MyE2_Label("-");
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

		
		// Überschriften TAbelle Summenblock
		cfHeadingBewegung.setValue("Bewegung (Sel.: Alle) ");
		cfHeadingEinheit.setValue("Einheit");
		cfHeadingWE.setValue("WE brutto");
		cfHeadingWENetto.setValue("WE netto");
		cfHeadingWA.setValue("WA brutto");
		cfHeadingWANetto.setValue("WA netto");
		cfHeadingDiff.setValue("Differenz WE(n)/WA(br.)");
		cfHeadingDiff.getObject().setToolTipText(new MyE2_String("Differenz vom Netto WE zum Brutto WA").CTrans());
		
		
		m_fieldList_caption.add(cfHeadingWENetto);
		m_fieldList_caption.add(cfHeadingWE);
		m_fieldList_caption.add(cfHeadingWANetto);
		m_fieldList_caption.add(cfHeadingWA);
		m_fieldList_caption.add(cfHeadingBewegung);
		m_fieldList_caption.add(cfHeadingEinheit);
		m_fieldList_caption.add(cfHeadingDiff);
		
		
		// Bindings definieren
		m_fieldList.add(cfDescMenge);
		m_fieldList.add(cfDescMengeBewertet);
		m_fieldList.add(cfDescMengeOhneAbrechnung);
		m_fieldList.add(cfDescMengeUnbewertet);
		m_fieldList.add(cfDescPreis);
		m_fieldList.add(cfDescSumme);
		
	
		m_fieldList.add(cfMengeWEBewertetNetto);
		m_fieldList.add(cfMengeWENetto);
		m_fieldList.add(cfMengeWEOhneAbrechnungNetto);
		m_fieldList.add(cfMengeWEUnbewertetNetto);
		m_fieldList.add(cfPreisWENetto);
		m_fieldList.add(cfSummeWENetto);

		m_fieldList.add(cfMengeWABewertetNetto);
		m_fieldList.add(cfMengeWANetto);
		m_fieldList.add(cfMengeWAOhneAbrechnungNetto);
		m_fieldList.add(cfMengeWAUnbewertetNetto);
		m_fieldList.add(cfPreisWANetto);
		m_fieldList.add(cfSummeWANetto);

		
		m_fieldList.add(cfMengeWEBewertet);
		m_fieldList.add(cfMengeWE);
		m_fieldList.add(cfMengeWEOhneAbrechnung);
		m_fieldList.add(cfMengeWEUnbewertet);
		m_fieldList.add(cfPreisWE);
		m_fieldList.add(cfSummeWE);

		m_fieldList.add(cfMengeWABewertet);
		m_fieldList.add(cfMengeWA);
		m_fieldList.add(cfMengeWAOhneAbrechnung);
		m_fieldList.add(cfMengeWAUnbewertet);
		m_fieldList.add(cfPreisWA);
		m_fieldList.add(cfSummeWA);

		m_fieldList.add(cfMengeDiff);
		m_fieldList.add(cfMengeDiffBewertet);
		m_fieldList.add(cfMengeDiffUnbewertet);
		m_fieldList.add(cfMengeDiffOhneAbrechnung);
		m_fieldList.add(cfPreisDiff);
		m_fieldList.add(cfSummeDiff);

		
		m_fieldList.add(cfDescMengeAbrechenbar);
		m_fieldList.add(cfMengeWEAbrechenbar);
		m_fieldList.add(cfMengeWAAbrechenbar);
		m_fieldList.add(cfMengeDiffAbrechenbar);
		m_fieldList.add(cfMengeWEAbrechenbarNetto);
		m_fieldList.add(cfMengeWAAbrechenbarNetto);
		
		
		init(oNaviList);
	}



	protected void init(E2_NavigationList oNavigationList)
			throws myException
	{
		MyE2_Column oCol = new MyE2_Column();
		
		// Meldungszeile oberhalb des Grids anzeigen
		oCol.add(lblMessage);
		
		// Vollständig verbuchte Einträge ausblenden...
		MyE2_Grid oGrid = new MyE2_Grid(5,0);
		
		Insets oIN = new Insets(0,2,15,2);
		Insets oIN2 = new Insets(0,2,2,10);

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
		oGrid.add(lblDescSaldo,1,oIN);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);
		oGrid.add(lblDescSaldoVon2,oLayout1ColRight);		
		oGrid.add(lblDescSaldoBis2,oLayout1ColRight);	
		oGrid.add(lblDescSaldoDiff,oLayout1ColRight);
		
	
		oGrid.add(lblDescSaldoSorte,2,oIN);
		oGrid.add(lblSaldoVon,oLayout1ColRight);		
		oGrid.add(lblSaldoBis,oLayout1ColRight);	
		oGrid.add(lblSaldoDiff,oLayout1ColRight);
		
		oGrid.add(new MyE2_Label(new MyE2_String("")),5,oIN2);
		
		// Überschrift
//		oGrid.add(new MyE2_Label(new MyE2_String("Bewegung (alle Selektoren)")),1,oIN);
		oGrid.add(cfHeadingBewegung.getObject(),1,oIN);
		oGrid.add(cfHeadingEinheit.getObject(),oLayout1ColRight);
		oGrid.add(cfHeadingWENetto.getObject(),oLayout1ColRight);		
		oGrid.add(cfHeadingWA.getObject(),oLayout1ColRight);	
		oGrid.add(cfHeadingDiff.getObject(),oLayout1ColRight);

		
		oGrid.add(new MyE2_Label(new MyE2_String("Menge Gesamt")),1,oIN);
		oGrid.add(cfDescMenge.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWENetto.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWA.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeDiff.getObject(),oLayout1ColRight);
		
		oGrid.add(new MyE2_Label(new MyE2_String("Menge Abrechenbar")),1,oIN);
		oGrid.add(cfDescMengeAbrechenbar.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWEAbrechenbarNetto.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWAAbrechenbar.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeDiffAbrechenbar.getObject(),oLayout1ColRight);
		
		
		oGrid.add(new MyE2_Label(new MyE2_String(" - davon mit Preisen")),1,oIN);
		oGrid.add(cfDescMengeBewertet.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWEBewertetNetto.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWABewertet.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeDiffBewertet.getObject(),oLayout1ColRight);

		oGrid.add(new MyE2_Label(new MyE2_String(" - davon ohne Preise")),1,oIN);
		oGrid.add(cfDescMengeUnbewertet.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWEUnbewertetNetto.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWAUnbewertet.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeDiffUnbewertet.getObject(),oLayout1ColRight);

		oGrid.add(new MyE2_Label(new MyE2_String("Menge nicht Abrechenbar")),1,oIN);
		oGrid.add(cfDescMengeOhneAbrechnung.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWEOhneAbrechnungNetto.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeWAOhneAbrechnung.getObject(),oLayout1ColRight);
		oGrid.add(cfMengeDiffOhneAbrechnung.getObject(),oLayout1ColRight);
		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Durchschnittspreis")),1,oIN);
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
		
		
//		double[][] a2 = { { 0.0, 1.0 }, { 1.0, 0 }, { 2, 2 }, { 2, 3 }, { 4, 2} };
//		plot.set_DataSetValues("mySeries", a2);
//		plot.set_Titel("titel");
//		plot.set_XAchse("Euro");
//		plot.set_YAchse("Kg");
//		
//		chart_display = new E2_ChartDisplay(plot.getChart());
//		chart_display.setHeight(new Extent(100));
//		chart_display.setWidth(new Extent(500));
//		
//		oCol.add(chart_display);
		
		this.add(oCol,new Insets(2,5,2,5));

		
		// das Event an die Navigation-List anhängen, um die Berechnungen zu starten
		oNavigationList.add_actionBeforeListStarted(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (oExecInfo instanceof ExecINFO_OnlyCodeNavigationList){
					ExecINFO_OnlyCodeNavigationList o = (ExecINFO_OnlyCodeNavigationList) oExecInfo;
					
					ReadLagerdatenFromNavigationListEntriesE(o.get_NavigationList());
					
//					Random r = new Random();
//					
//					double[][] a2 = { { r.nextDouble(), 1.0 }, { r.nextDouble(), 0 }, { r.nextDouble(), 2 }, { r.nextDouble(), 3 }, { r.nextDouble(), 2} };
//					plot.set_DataSetValues("mySeries", a2);
//					plot.set_Titel("lslslsl");
////					chart_display.setChart(plot.getChart());
				}
			}
		});
		
	}
	
	
	
	/**
	 * liest die Saldo-Stände des gewählten Lager/Sorten-Kombination aus
	 * @throws myException 
	 */
	private void ReadSaldoForSelectedSorte() throws myException{
		String sIDLager = null;
		Vector<String> vIDLager = null;
		
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
		
		
		Vector<String> vIDSorte 		= m_oSelector.getIDSelectedSorte();
		Vector<String> vIDHauptsorte 	= m_oSelector.getSelectedHauptsorte();
		sIDEinheit = m_oSelector.getIDSelectedEinheit();

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
		
		
//		// Selektionskriterien aufbauen
//		if (vIDSorte != null && vIDSorte.size() > 0){
//			if (vIDSorte.size() == 1){
//				sIDSorte = vIDSorte.get(0);
//				vIDSorte = null;
//			} else {
//				sIDSorte = null;
//			} 
//		} else if (vIDHauptsorte != null && vIDHauptsorte.size() > 0){
//			if (vIDHauptsorte.size() == 1){
//				sHauptsorte = vIDHauptsorte.get(0);
//				vIDHauptsorte = null;
//			} else {
//				sHauptsorte = null;
//			}
//		}

		
		sDatumVon = m_oSelector.getDatumVon();
		sDatumBis = m_oSelector.getDatumBis();
		
		ATOM_LagerSaldoErmittlung oSaldo = new ATOM_LagerSaldoErmittlung();
		
		// Startsaldo ermitteln
		bdSaldoVon = BigDecimal.ZERO;
		oSaldo.readSaldoDaten(sIDLager,vIDLager, sHauptsorte,vIDHauptsorte, sIDSorte,vIDSorte, sIDEinheit, sDatumVon, true,m_oSelector.getShowEigenwarenlager(),m_oSelector.getShowFremdwarenlager(), m_oSelector.getIncludeStrecke());
		bdSaldoVon = oSaldo.getSumOfAllSaldoValuesFound();

		// Zielsaldo ermitteln
		bdSaldoBis = BigDecimal.ZERO;
		oSaldo.readSaldoDaten(sIDLager,vIDLager,  sHauptsorte, vIDHauptsorte,sIDSorte,vIDSorte,sIDEinheit, sDatumBis, false, m_oSelector.getShowEigenwarenlager(),m_oSelector.getShowFremdwarenlager(),m_oSelector.getIncludeStrecke());
		bdSaldoBis = oSaldo.getSumOfAllSaldoValuesFound();
		
		bdSaldoDiff = bdSaldoBis.subtract(bdSaldoVon);

	}
	

	
	/**
	 * Ermitteln der Lagerdaten, die in der Navigation-List vorhandenen Einträge des Lagerkontos
	 * @throws myException 
	 */
	private void ReadLagerdatenFromNavigationListEntriesE(E2_NavigationList oNaviList) throws myException {

		// Wenn das Panel nicht ausgeklappt ist, dann ist die Berechnung nicht nötig
//		if (!this.get_bOpen()){
//			return;
//		}
		
		clearData();

		refreshSummaryValues();
		
		vectorForSegmentation vNeu = new vectorForSegmentation();
		vNeu.addAll(oNaviList.get_vectorSegmentation());
		vNeu.set_iSegmentGroesse(100);
				
		int iSegmente = vNeu.get_iZahlSegmente();

		String sIDEinheitReferenz = null;
		
		String sIDs = "";
		
		
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
			
			String[][] cLagerDaten = new String[0][0];
			String sSql = "";
			sSql = "SELECT " 
				+ " A.MENGE  * S.MENGENVORZEICHEN , "
				+ " A.MENGE_NETTO * S.MENGENVORZEICHEN , " 
				+ " A.E_PREIS , " 
				+ " A.E_PREIS_RESULT_NETTO_MGE, "
				+ " S.MENGENVORZEICHEN, " 
				+ " ART.ID_EINHEIT, " 
				+ " ART.MENGENDIVISOR ,  " 
				+ " E1.EINHEITKURZ, " 
				+ " E2.EINHEITKURZ, " 
				+ " CASE WHEN UPPER(A.ABRECHENBAR) = UPPER('N') then UPPER('Y') ELSE UPPER('N') END as FREMDWARE, "
				+ " A.E_PREIS_RESULT_BRUTTO_MGE "
				+ " FROM " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A ON S.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V ON V.ID_BEWEGUNG_VEKTOR = A.ID_BEWEGUNG_VEKTOR "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART on A.ID_ARTIKEL = ART.ID_ARTIKEL"
			    + " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E1 on ART.ID_EINHEIT = E1.ID_EINHEIT "
			    + " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E2 on ART.ID_EINHEIT_PREIS = E2.ID_EINHEIT " 

			    + " WHERE S.ID_BEWEGUNG_STATION in ( "
				+ sIDs
				+ " ) ";
			
			cLagerDaten = bibDB.EinzelAbfrageInArray(sSql, (String) null);
		
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0) {
				continue ;
			}
			
			for (int j = 0; j < cLagerDaten.length; j++) {
				
				String sMenge = cLagerDaten[j][0];
				String sMengeNetto = cLagerDaten[j][1];
				String sPreis = cLagerDaten[j][2];
				String sPreisNetto = cLagerDaten[j][3];
				String sTyp = cLagerDaten[j][4];
				
				Integer nTyp = Integer.parseInt(sTyp);
				
				String sIDEinheit = cLagerDaten[j][5];
				String sMengenDiv = cLagerDaten[j][6];
				String sEinheitMenge = cLagerDaten[j][7];
				String sEinheitPreis = cLagerDaten[j][8];
				String sOhneAbrechnung = cLagerDaten[j][9];
				String sPreisBrutto = cLagerDaten[j][10];
				
				if (sIDEinheitReferenz == null){
					sIDEinheitReferenz = sIDEinheit;
					sRefEinheitMenge = sEinheitMenge;
					sRefEinheitPreis = sEinheitPreis;
					
				}
				
				if (!sIDEinheit.equals(sIDEinheitReferenz)){
					clearData();
					showMessage(new MyE2_String("Eine Ermittlung der Kennzahlen ist nur möglich, " +
									"wenn alle Buchungen die gleiche Mengeneinheit (z.B. Kg) haben").CTrans());
					return;
				}
				
				
				
				// als Brutto-Preis jetzt den E_PREIS_RESULT_BRUTTO_MGE nehmen, um korrektere Summen zu generieren.
//				BigDecimal dPreis 		= (sPreis != null ? new BigDecimal(sPreis) : null);
				BigDecimal dPreisBrutto = (sPreisBrutto != null ? new BigDecimal(sPreisBrutto) : null);
				BigDecimal dPreis 		= dPreisBrutto;
				
				BigDecimal dPreisNetto  = (sPreisNetto != null ? new BigDecimal(sPreisNetto) : null);
				BigDecimal dMenge 		= (sMenge != null ? new BigDecimal(sMenge) : null);
				BigDecimal dMengeNetto 	= (sMengeNetto != null ? new BigDecimal(sMengeNetto) : null);
				BigDecimal dDiv 		= (sMengenDiv != null ? new BigDecimal(sMengenDiv) : BigDecimal.ONE);
				
				BigDecimal bdMengePreis 		= BigDecimal.ZERO;
				BigDecimal bdMengePreisNetto 	= BigDecimal.ZERO;
				

				// WE
				if (nTyp.intValue() == 1){
					if (dMenge != null){
						cfMengeWE.setValue( cfMengeWE.getValue().add(dMenge));
						if (sOhneAbrechnung.equals("Y")){
							cfMengeWEOhneAbrechnung.setValue( cfMengeWEOhneAbrechnung.getValue().add(dMenge));
						} else {
							if (dPreis == null){
								cfMengeWEUnbewertet.setValue(cfMengeWEUnbewertet.getValue().add(dMenge)); 
							} else {
								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreis = dMenge.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWE.setValue(cfSummeWE.getValue().add(bdMengePreis.multiply(dPreis)) );
								// Menge für die Durchschnittspreise
								bdMengeWEPreisBerechnung = bdMengeWEPreisBerechnung.add(bdMengePreis);
							}
						}
						
					}
					
					if (dMengeNetto != null){
						cfMengeWENetto.setValue( cfMengeWENetto.getValue().add(dMengeNetto) );
						if (sOhneAbrechnung.equals("Y")){
							cfMengeWEOhneAbrechnungNetto.setValue(cfMengeWEOhneAbrechnungNetto.getValue().add(dMengeNetto));
						} else {
							if (dPreisNetto == null){
								cfMengeWEUnbewertetNetto.setValue(cfMengeWEUnbewertetNetto.getValue().add(dMengeNetto) ); 
							} else {
								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreisNetto = dMengeNetto.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWENetto.setValue( cfSummeWENetto.getValue().add(bdMengePreisNetto.multiply(dPreisNetto)) );
								
								// Menge für die Durchschnittspreise
								bdMengeWEPreisBerechnungNetto = bdMengeWEPreisBerechnungNetto.add(bdMengePreis);
							}
						}
						
					}
				} else {
					// WA
					// der Warenausgang wird in der DB negativ geführt. Also für die Berechnungen hier die Menge negieren.
					if (dMenge != null){
						dMenge = dMenge.multiply(BigDecimal.ONE.negate());
						cfMengeWA.setValue( cfMengeWA.getValue().add(dMenge));
						if (sOhneAbrechnung.equals("Y")){
							cfMengeWAOhneAbrechnung.setValue(cfMengeWAOhneAbrechnung.getValue().add(dMenge));
						} else {
							if (dPreis == null){
								cfMengeWAUnbewertet.setValue( cfMengeWAUnbewertet.getValue().add(dMenge)); 
							} else {
								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreis = dMenge.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWA.setValue( cfSummeWA.getValue().add(bdMengePreis.multiply(dPreis)));
								// Menge für die Durchschnittspreise
								bdMengeWAPreisBerechnung = bdMengeWAPreisBerechnung.add(bdMengePreis);
							}
						}
					}
					
					if (dMengeNetto != null){
						dMengeNetto =dMengeNetto.multiply(BigDecimal.ONE.negate());
						cfMengeWANetto.setValue(cfMengeWANetto.getValue().add(dMengeNetto)) ;
						if (sOhneAbrechnung.equals("Y")){
							cfMengeWAOhneAbrechnungNetto.setValue( cfMengeWAOhneAbrechnungNetto.getValue().add(dMengeNetto));
						} else {
							if (dPreisNetto == null){
								cfMengeWAUnbewertetNetto.setValue(cfMengeWAUnbewertetNetto.getValue().add(dMengeNetto)); 
							} else {
								// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
								bdMengePreisNetto = dMengeNetto.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
								// Gesamtsumme 
								cfSummeWANetto.setValue( cfSummeWANetto.getValue().add(bdMengePreisNetto.multiply(dPreisNetto)));
								// Menge für die Durchschnittspreise
								bdMengeWAPreisBerechnungNetto = bdMengeWAPreisBerechnungNetto.add(bdMengePreis);
							}
						}
					}					
					
				}
			}
			
		}
		

		
		// wenn alle Mengen und Preise aufsummiert sind, dann die Durschnittspreise berechnen 
		cfMengeWEBewertet.setValue( cfMengeWE.getValue().subtract(cfMengeWEUnbewertet.getValue()).subtract(cfMengeWEOhneAbrechnung.getValue()) );
		cfMengeWEAbrechenbar.setValue(cfMengeWE.getValue().subtract(cfMengeWEOhneAbrechnung.getValue()));
		
		cfMengeWEBewertetNetto.setValue( cfMengeWENetto.getValue().subtract(cfMengeWEUnbewertetNetto.getValue()).subtract(cfMengeWEOhneAbrechnungNetto.getValue()) );
		cfMengeWEAbrechenbarNetto.setValue( cfMengeWENetto.getValue().subtract(cfMengeWEOhneAbrechnungNetto.getValue()) );
		
		
		cfMengeWABewertet.setValue( cfMengeWA.getValue().subtract(cfMengeWAUnbewertet.getValue()).subtract(cfMengeWAOhneAbrechnung.getValue()) );
		cfMengeWAAbrechenbar.setValue( cfMengeWA.getValue().subtract(cfMengeWAOhneAbrechnung.getValue()) );
		
		cfMengeWABewertetNetto.setValue( cfMengeWANetto.getValue().subtract(cfMengeWAUnbewertetNetto.getValue()).subtract(cfMengeWAOhneAbrechnungNetto.getValue()) );
		cfMengeWAAbrechenbarNetto.setValue( cfMengeWANetto.getValue().subtract(cfMengeWAOhneAbrechnungNetto.getValue()) );
		
		
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
		cfMengeDiff.setValue( cfMengeWENetto.getValue().subtract(cfMengeWA.getValue()) );
		cfMengeDiffAbrechenbar.setValue( cfMengeWEAbrechenbarNetto.getValue().subtract(cfMengeWAAbrechenbar.getValue()) );
		cfMengeDiffBewertet.setValue( cfMengeWEBewertetNetto.getValue().subtract(cfMengeWABewertet.getValue()) );
		cfMengeDiffUnbewertet.setValue( cfMengeWEUnbewertetNetto.getValue().subtract(cfMengeWAUnbewertet.getValue()) );

		cfMengeDiffOhneAbrechnung.setValue(cfMengeWEOhneAbrechnungNetto.getValue().subtract(cfMengeWAOhneAbrechnung.getValue()) );

		cfPreisDiff.setValue( cfPreisWA.getValue().subtract(cfPreisWE.getValue()) );
		cfSummeDiff.setValue( cfSummeWA.getValue().subtract(cfSummeWE.getValue()) );
		
		// wenn Alle Zahlen berechnet sind sind die Kennzahlen auch gültig und können angezeigt werden.
		bKennzahlenGueltig = true;
		
		//Saldo-Werte ermitteln
		this.ReadSaldoForSelectedSorte();
		
		refreshSummaryValues();
		
	}



	private void clearData() {
		lblMessage.setVisible(false);
		bdMengeWEPreisBerechnung = BigDecimal.ZERO;
		
		bdMengeWAPreisBerechnung = BigDecimal.ZERO;
		
		bdSaldoBis = BigDecimal.ZERO;
		bdSaldoVon = BigDecimal.ZERO;
		bdSaldoDiff = BigDecimal.ZERO;
		m_fieldList.clearFieldList();

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
		cfDescMenge.setValue("(" +  sRefEinheitMenge + ")");
		cfDescMengeAbrechenbar.setValue("(" + sRefEinheitMenge + ")");
		cfDescMengeBewertet.setValue("(" + sRefEinheitMenge + ")");
		cfDescMengeUnbewertet.setValue( "(" +  sRefEinheitMenge + ")");
		cfDescMengeOhneAbrechnung.setValue("(" + sRefEinheitMenge + ")");
		cfDescPreis.setValue("(Eur./" + sRefEinheitPreis + ")");
		cfDescSumme.setValue("(Eur.)");
		
		this.m_fieldList.bindFieldList();
		this.m_fieldList_caption.bindFieldList();
		


		
		
		try {
			
			Vector<String> vIDSorte 		= m_oSelector.getIDSelectedSorte();
			Vector<String> vIDHauptsorte 	= m_oSelector.getSelectedHauptsorte();
			Vector<String> vLager			= m_oSelector.getSelectedLager();
			
			
			String sDescription_Lager = "";
			String sDescription = "";
			
			if(bKennzahlenGueltig){
				if (vLager != null && vLager.size() > 0){
					if (vLager.size() == 1){
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
						
			this.lblDescSaldoVon2.setText( bibALL.FormatDatum(m_oSelector.getDatumVon()) + " (00:00)" );
			this.lblDescSaldoBis2.setText( bibALL.FormatDatum(m_oSelector.getDatumBis()) + " (23:59)" );
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.lblSaldoVon.setText(bibALL.makeDez(bdSaldoVon.doubleValue(),3,true));
		this.lblSaldoBis.setText(bibALL.makeDez(bdSaldoBis.doubleValue(),3,true));
		this.lblSaldoDiff.setText(bibALL.makeDez(bdSaldoDiff.doubleValue(),3,true));
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Sammlung der Felder um zu aktualisieren und löschen
	 * @author manfred
	 * @date   10.10.2013
	 */
	private class cFieldList {
		ArrayList<cField> m_fieldList = null;
		
		public cFieldList() {
			m_fieldList = new ArrayList<ATOM_LAG_LIST_Summay_old.cField>();
		}
		
		public void add(cField field){
			m_fieldList.add(field);
		}
		
		public void clearFieldList () {
			for (cField o: m_fieldList){
				o.clear();
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
		
		// Get VALUE-OBJECT
		protected abstract Object getValue();
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
		
		
		protected abstract void 	bind();
		protected abstract void 	clear();
		
		protected abstract Object getValue();
//		protected abstract Object getValue(Object valueWhenNull);
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
		}

		@Override
		protected String getValue() {
			return (String)m_value_object;
		}

//		@Override
//		protected String getValue(Object valueWhenNull) {
//			if (m_value_object == null){
//				return (String)valueWhenNull;
//			} else{
//				return (String)m_value_object;
//			}
//		}
		
		@Override
		protected void setValue(Object value) {
			if (value == null){
				m_value_object = null;
			} else 	if (value.getClass().equals(String.class)){
				m_value_object = value;
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

//		@Override
//		protected BigDecimal getValue(Object valueWhenNull) {
//			if (m_value_object == null){
//				return (BigDecimal)valueWhenNull;
//			} else{
//				return (BigDecimal)m_value_object;
//			}
//		}
		
		
		@Override
		protected void setValue(Object value) {
			if (value == null){
				m_value_object = null;
			} else  if (value.getClass().equals(BigDecimal.class)){
				m_value_object = value;
			}
		}

		@Override
		protected void bind() {
			getObject().setText(m_dataconverter.doTransformation(this.getValue()));
		}

		
		@Override
		protected void clear() {
			m_value_object = BigDecimal.ZERO;
//			m_value_object = null;
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
