package rohstoff.Echo2BusinessLogic.LAGER_LISTE;


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
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.vectorForSegmentation;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.LAGER_LISTE.LAG_KTO_LIST_Selector.SelectorSortenVonBis_Multi;

public class LAG_KTO_LIST_Summary extends E2_ListSelectorContainer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4604214009253404066L;
	
	// Objekte
	private  MyE2_Label 	lblMessage = null;
	
	private  MyE2_Label 	lblHeadingEinheit = null;
	private  MyE2_Label 	lblHeadingWE = null;
	private  MyE2_Label 	lblHeadingWA = null;
	private  MyE2_Label 	lblHeadingDiff = null;

	
	private  MyE2_Label 	lblMengeWE = null;
	private  MyE2_Label 	lblMengeWEUnbewertet = null;
	private  MyE2_Label 	lblMengeWEBewertet = null;
	private  MyE2_Label 	lblPreisWE = null;
	private  MyE2_Label 	lblSummeWE = null;

	private  MyE2_Label 	lblMengeWA = null;
	private  MyE2_Label 	lblMengeWAUnbewertet = null;
	private  MyE2_Label 	lblMengeWABewertet = null;
	private  MyE2_Label 	lblPreisWA = null;
	private  MyE2_Label 	lblSummeWA = null;

	private  MyE2_Label 	lblMengeDiff = null;
	private  MyE2_Label 	lblMengeDiffUnbewertet = null;
	private  MyE2_Label 	lblMengeDiffBewertet = null;
	private  MyE2_Label 	lblPreisDiff = null;
	private  MyE2_Label 	lblSummeDiff = null;

	
	private  MyE2_Label 	lblDescMenge = null;
	private  MyE2_Label 	lblDescMengeUnbewertet = null;
	private  MyE2_Label		lblDescMengeBewertet = null;
	private  MyE2_Label 	lblDescPreis = null;
	private  MyE2_Label 	lblDescSumme = null;
	
	private  MyE2_Label		lblDescSaldo = null;  
	private  MyE2_Label     lblDescSaldoSorte = null;
	private  MyE2_Label		lblDescSaldoVon1 = null;
	private  MyE2_Label		lblDescSaldoVon2 = null;
	
	private  MyE2_Label		lblSaldoVon = null;
	private  MyE2_Label		lblDescSaldoBis1 = null;
	private  MyE2_Label		lblDescSaldoBis2 = null;
	private  MyE2_Label		lblSaldoBis = null;
	private  MyE2_Label     lblDescSaldoDiff = null;
	private  MyE2_Label     lblSaldoDiff = null;
	
	// Fuhren "Ohne Abrechnung" gesondert bewerten 
	private  MyE2_Label     lblDescMengeOhneAbrechnung = null;
	
	private  MyE2_Label 	lblMengeWEOhneAbrechnung = null;
	private  MyE2_Label 	lblMengeWAOhneAbrechnung = null;
	private  MyE2_Label 	lblMengeDiffOhneAbrechnung = null;
	
	
	
	private  String 		sRefEinheitMenge = "-";
	private  String 		sRefEinheitPreis = "-";

	
	// Variablen
	private  BigDecimal		bdMengeWEGesamt = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWEUnbewertet = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWEBewertet = BigDecimal.ZERO;
	private  BigDecimal		bdPreisWE = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWEPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdSummeWE = BigDecimal.ZERO;

	private  BigDecimal		bdMengeWAGesamt = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAUnbewertet = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWABewertet = BigDecimal.ZERO;
	private  BigDecimal		bdPreisWA = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdSummeWA = BigDecimal.ZERO;
	
	private  BigDecimal		bdMengeDiffGesamt = BigDecimal.ZERO;
	private  BigDecimal		bdMengeDiffUnbewertet = BigDecimal.ZERO;
	private  BigDecimal		bdMengeDiffBewertet = BigDecimal.ZERO;
	private  BigDecimal		bdPreisDiff = BigDecimal.ZERO;
//	private  BigDecimal		bdMengeDiffPreisBerechnung = BigDecimal.ZERO;
	private  BigDecimal		bdSummeDiff = BigDecimal.ZERO;

	// Mengen ohne Abrechnung
	private  BigDecimal		bdMengeWEOhneAbrechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeWAOhneAbrechnung = BigDecimal.ZERO;
	private  BigDecimal		bdMengeDiffOhneAbrechnung = BigDecimal.ZERO;
	
	
	// Werte für die Saldi
	private  BigDecimal 	bdSaldoVon = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoBis = BigDecimal.ZERO;
	private  BigDecimal		bdSaldoDiff = BigDecimal.ZERO;
	
	
	
	private E2_NavigationList	oNaviList = null;
	
	// den Selektor der Lagerbewegung, um die Saldi zu ermitteln
	private LAG_KTO_LIST_Selector m_oSelector = null;
	
	
//	E2_XYPlot plot = new E2_XYPlot();
//	E2_ChartDisplay chart_display = null;
	
	public LAG_KTO_LIST_Summary(E2_NavigationList oNavigationList, LAG_KTO_LIST_Selector oSelector) throws myException
	{
		super(new MyE2_String("Lagerinformationen / Summen / Durchschnittswerte"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		oNaviList = oNavigationList;
		m_oSelector = oSelector;
		
		lblMessage = new MyE2_Label("");
		lblMessage.setFont(new E2_FontBold(0));
		lblMessage.setForeground(Color.RED);
		lblMessage.setVisible(false);
		
		lblHeadingEinheit = new MyE2_Label(new MyE2_String("Einheit"));
		lblHeadingEinheit.setFont(new E2_FontBold(0));
		lblHeadingWA = new MyE2_Label(new MyE2_String("WA"));
		lblHeadingWA.setFont(new E2_FontBold(0));
		lblHeadingWE = new MyE2_Label(new MyE2_String("WE"));
		lblHeadingWE.setFont(new E2_FontBold(0));
		lblHeadingDiff = new MyE2_Label(new MyE2_String("Differenz"));
		lblHeadingDiff.setFont(new E2_FontBold(0));
		
		lblDescSaldo 	= new MyE2_Label(new MyE2_String("Saldo"));
		lblDescSaldo.setFont(new E2_FontBold());
		lblDescSaldoSorte = new MyE2_Label("-");
		lblDescSaldoVon1 = new MyE2_Label(new MyE2_String("Bestand Morgens zum"));
		lblDescSaldoVon1.setFont(new E2_FontBold());
		lblDescSaldoVon2 = new MyE2_Label(new MyE2_String(""));
		lblDescSaldoVon2.setFont(new E2_FontBold());
		
		lblDescSaldoBis1 = new MyE2_Label(new MyE2_String("Bestand Abends zum"));
		lblDescSaldoBis1.setFont(new E2_FontBold());
		lblDescSaldoBis2 = new MyE2_Label(new MyE2_String(""));
		lblDescSaldoBis2.setFont(new E2_FontBold());
		lblDescSaldoDiff = new MyE2_Label(new MyE2_String("Differenz"));
		lblDescSaldoDiff.setFont(new E2_FontBold());
		
		lblSaldoVon = new MyE2_Label("-");
		lblSaldoBis = new MyE2_Label("-");
		lblSaldoDiff = new MyE2_Label("-");
		
		
		// Objekte
		lblMengeWE = new MyE2_Label("-");
		lblMengeWEBewertet = new MyE2_Label("-");
		lblMengeWEOhneAbrechnung = new MyE2_Label("-"); 
		lblMengeWEUnbewertet = new MyE2_Label("-");
		lblPreisWE = new MyE2_Label("-");
		lblSummeWE = new MyE2_Label("-");

		lblMengeWA = new MyE2_Label("-");
		lblMengeWABewertet = new MyE2_Label("-");
		lblMengeWAOhneAbrechnung = new MyE2_Label("-");
		lblMengeWAUnbewertet = new MyE2_Label("-");
		lblPreisWA = new MyE2_Label("-");
		lblSummeWA = new MyE2_Label("-");		
		
		lblMengeDiff = new MyE2_Label("-");
		lblMengeDiffBewertet = new MyE2_Label("-");
		lblMengeDiffOhneAbrechnung = new MyE2_Label("-");
		lblMengeDiffUnbewertet = new MyE2_Label("-");
		lblPreisDiff = new MyE2_Label("-");
		lblSummeDiff = new MyE2_Label("-");		
		
		lblDescMenge = new MyE2_Label("-");
		lblDescMengeUnbewertet = new MyE2_Label("-");
		lblDescMengeBewertet = new MyE2_Label("-");
		lblDescMengeOhneAbrechnung = new MyE2_Label("-");
		
		lblDescPreis = new MyE2_Label("-");
		lblDescSumme = new MyE2_Label("-");
		
		
		
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
	
//		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);
		oGrid.add(lblDescSaldoSorte,2,oIN);
		oGrid.add(lblSaldoVon,oLayout1ColRight);		
		oGrid.add(lblSaldoBis,oLayout1ColRight);	
		oGrid.add(lblSaldoDiff,oLayout1ColRight);
	
		
		oGrid.add(new MyE2_Label(new MyE2_String("")),5,oIN2);
		
		// Überschrift
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,oIN);
		oGrid.add(lblHeadingEinheit,oLayout1ColRight);
		oGrid.add(lblHeadingWE,oLayout1ColRight);		
		oGrid.add(lblHeadingWA,oLayout1ColRight);	
		oGrid.add(lblHeadingDiff,oLayout1ColRight);

		
		oGrid.add(new MyE2_Label(new MyE2_String("Menge")),1,oIN);
		oGrid.add(lblDescMenge,oLayout1ColRight);
		oGrid.add(lblMengeWE,oLayout1ColRight);
		oGrid.add(lblMengeWA,oLayout1ColRight);
		oGrid.add(lblMengeDiff,oLayout1ColRight);
		
		oGrid.add(new MyE2_Label(new MyE2_String("Menge mit Preisen")),1,oIN);
		oGrid.add(lblDescMengeBewertet,oLayout1ColRight);
		oGrid.add(lblMengeWEBewertet,oLayout1ColRight);
		oGrid.add(lblMengeWABewertet,oLayout1ColRight);
		oGrid.add(lblMengeDiffBewertet,oLayout1ColRight);

		oGrid.add(new MyE2_Label(new MyE2_String("Menge ohne Abrechnung")),1,oIN);
		oGrid.add(lblDescMengeOhneAbrechnung,oLayout1ColRight);
		oGrid.add(lblMengeWEOhneAbrechnung,oLayout1ColRight);
		oGrid.add(lblMengeWAOhneAbrechnung,oLayout1ColRight);
		oGrid.add(lblMengeDiffOhneAbrechnung,oLayout1ColRight);
		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Menge ohne Preise")),1,oIN);
		oGrid.add(lblDescMengeUnbewertet,oLayout1ColRight);
		oGrid.add(lblMengeWEUnbewertet,oLayout1ColRight);
		oGrid.add(lblMengeWAUnbewertet,oLayout1ColRight);
		oGrid.add(lblMengeDiffUnbewertet,oLayout1ColRight);
	
		
		oGrid.add(new MyE2_Label(new MyE2_String("Durchschnittspreis")),1,oIN);
		oGrid.add(lblDescPreis,oLayout1ColRight);
		oGrid.add(lblPreisWE,oLayout1ColRight );
		oGrid.add(lblPreisWA,oLayout1ColRight);
		oGrid.add(lblPreisDiff,oLayout1ColRight);

		oGrid.add(new MyE2_Label(new MyE2_String("Gesamtbetrag")),1,oIN);
		oGrid.add(lblDescSumme,oLayout1ColRight);
		oGrid.add(lblSummeWE,oLayout1ColRight);
		oGrid.add(lblSummeWA,oLayout1ColRight);
		
		// 2012-07-16
		// Die Berechnung der Summendifferenz soll raus, da "ohne Sinn und Verstand" berechnet
		// Telefonat mit RL
		//oGrid.add(lblSummeDiff,oLayout1ColRight);
		oGrid.add(new MyE2_Label(new MyE2_String("")),oLayout1ColRight);

		
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
		
		this.add(new LAG_KTO_LIST_BT_RECALCULATE_PRICE(oNavigationList, this), new Insets(30,2,20,2)) ;
		
		// das Event an die Navigation-List anhängen, um die Berechnungen zu starten
		
//		oNavigationList.add_actionBeforeListStarted(new XX_ActionAgent() {
		oNavigationList.add_actionAfterContentVectorIsSet(new XX_ActionAgent() {
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
		String sHauptsorte = null;
		String sIDSorte = null;
		String sDatumVon = null;
		String sDatumBis = null;
		String sIDEinheit = null;
		
		sIDLager = m_oSelector.getIDSelectedLager();
		sIDSorte = m_oSelector.getIDSelectedSorte();
		sHauptsorte = m_oSelector.getSelectedHauptsorte();
		sIDEinheit = m_oSelector.getIDSelectedEinheit();
		
		sDatumVon = m_oSelector.getDatumVon();
		sDatumBis = m_oSelector.getDatumBis();
		
		// Bereichs-Selektor Sorten
		 SelectorSortenVonBis_Multi oBlock = m_oSelector.getSortenblockSelektor();
		 ArrayList<String[]> selected_values = oBlock.get_ArrayOfSelectedValues();
		
		
		if (bibALL.isEmpty(sIDSorte) &&  bibALL.isEmpty(sHauptsorte) && bibALL.isEmpty(sIDEinheit)){
			showMessage(new MyE2_String("Eine Ermittlung der Lagerbestände ist nur möglich, " +
			"wenn eine Hauptsorte, Sorte oder eine Mengeneinheit ausgewählt wurde.").CTrans());
			return;
		}
		
		LAG_LagerSaldoErmittlung oSaldo = new LAG_LagerSaldoErmittlung();
		
		// Startsaldo ermitteln
		oSaldo.readLagerSaldoDaten(sIDLager, sHauptsorte, sIDSorte,sIDEinheit, sDatumVon, "00:00", selected_values);
		bdSaldoVon = oSaldo.getSumOfAllSaldoValuesFound();
		
		// Zielsaldo ermitteln
		oSaldo.readLagerSaldoDaten(sIDLager, sHauptsorte, sIDSorte, sIDEinheit, sDatumBis, "23:59",selected_values);
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
			sSql = "SELECT JT_LAGER_KONTO.MENGE , JT_LAGER_KONTO.PREIS , JT_LAGER_KONTO.BUCHUNGSTYP, " 
				+	"JT_ARTIKEL.ID_EINHEIT, JT_ARTIKEL.MENGENDIVISOR ,  E1.EINHEITKURZ, E2.EINHEITKURZ, NVL(F.OHNE_ABRECHNUNG,'N') "
				+ " FROM " + bibE2.cTO() + ".JT_LAGER_KONTO  INNER JOIN  " 
				+ bibE2.cTO() + ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL"
			    + " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E1 on JT_ARTIKEL.ID_EINHEIT = E1.ID_EINHEIT "
			    + " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT E2 on JT_ARTIKEL.ID_EINHEIT_PREIS = E2.ID_EINHEIT " 
			    + " LEFT OUTER JOIN JT_VPOS_TPA_FUHRE F ON F.ID_VPOS_TPA_FUHRE = JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE "

			    + " WHERE JT_LAGER_KONTO.ID_LAGER_KONTO in ( "
				+ sIDs
				+ " ) ";
			
			cLagerDaten = bibDB.EinzelAbfrageInArray(sSql, (String) null);
		
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0) {
				continue ;
			}
			
			for (int j = 0; j < cLagerDaten.length; j++) {
				
				String sMenge = cLagerDaten[j][0];
				String sPreis = cLagerDaten[j][1];
				String sTyp = cLagerDaten[j][2];
				String sIDEinheit = cLagerDaten[j][3];
				String sMengenDiv = cLagerDaten[j][4];
				String sEinheitMenge = cLagerDaten[j][5];
				String sEinheitPreis = cLagerDaten[j][6];
				String sOhneAbrechnung = cLagerDaten[j][7];
				
				if (sIDEinheitReferenz == null){
					sIDEinheitReferenz = sIDEinheit;
					sRefEinheitMenge = sEinheitMenge;
					sRefEinheitPreis = sEinheitPreis;
					
				}
				
				if (!sIDEinheit.equals(sIDEinheitReferenz)){
					clearData();
					showMessage(new MyE2_String("Eine Ermittlung der Kennzahlen ist nur möglich, " +
									"wenn alle Buchungen die gleiche Mengeneinheit (z.B. t, Kg ) haben").CTrans());
					return;
				}
				
				BigDecimal dPreis = (sPreis != null ? new BigDecimal(sPreis) : null);
				BigDecimal dMenge = (sMenge != null ? new BigDecimal(sMenge) : null);
				BigDecimal dDiv = (sMengenDiv != null ? new BigDecimal(sMengenDiv) : BigDecimal.ONE);
				BigDecimal bdMengePreis = BigDecimal.ZERO;
				
				if (sTyp.equalsIgnoreCase("WE")){
					bdMengeWEGesamt = bdMengeWEGesamt.add(dMenge);
					
					if (sOhneAbrechnung.equals("Y")){
						bdMengeWEOhneAbrechnung = bdMengeWEOhneAbrechnung.add(dMenge);
					} else {
						if (dPreis == null){
							bdMengeWEUnbewertet = bdMengeWEUnbewertet.add(dMenge); 
						} else {
							// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
							 bdMengePreis = dMenge.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
							// Gesamtsumme 
							bdSummeWE = bdSummeWE.add(bdMengePreis.multiply(dPreis));
							// Menge für die Durchschnittspreise
							bdMengeWEPreisBerechnung = bdMengeWEPreisBerechnung.add(bdMengePreis);
						}
					}
				} else {
					// der Warenausgang wird in der DB negativ geführt. Also für die Berechnungen hier die Menge negieren.
					dMenge = dMenge.multiply(BigDecimal.ONE.negate());
					
					bdMengeWAGesamt = bdMengeWAGesamt.add(dMenge);
					
					if (sOhneAbrechnung.equals("Y")){
						bdMengeWAOhneAbrechnung = bdMengeWAOhneAbrechnung.add(dMenge);
					} else {
					
						if (dPreis == null){
							bdMengeWAUnbewertet = bdMengeWAUnbewertet.add(dMenge); 
						} else {
							// Menge für die Preisberechnung hängt ab vom Mengendivisor des Artikels
							bdMengePreis = dMenge.divide(dDiv, 3, BigDecimal.ROUND_HALF_UP);
							// Gesamtsumme 
							bdSummeWA = bdSummeWA.add(bdMengePreis.multiply(dPreis));
							// Menge für die Durchschnittspreise
							bdMengeWAPreisBerechnung = bdMengeWAPreisBerechnung.add(bdMengePreis);
						}
					}
				}
			}
		}
		
		// wenn alle Mengen und Preise aufsummiert sind, dann die Durschnittspreise berechnen 
		bdMengeWEBewertet = bdMengeWEGesamt.subtract(bdMengeWEUnbewertet).subtract(bdMengeWEOhneAbrechnung);
		bdMengeWABewertet = bdMengeWAGesamt.subtract(bdMengeWAUnbewertet).subtract(bdMengeWAOhneAbrechnung);
		
		
		if (bdMengeWEPreisBerechnung != null && bdMengeWEPreisBerechnung.compareTo(BigDecimal.ZERO)!= 0){
			bdPreisWE = bdSummeWE.divide(bdMengeWEPreisBerechnung, 2, BigDecimal.ROUND_HALF_UP);
		}
		if (bdMengeWAPreisBerechnung != null && bdMengeWAPreisBerechnung.compareTo(BigDecimal.ZERO)!= 0){
			bdPreisWA = bdSummeWA.divide(bdMengeWAPreisBerechnung, 2, BigDecimal.ROUND_HALF_UP);
		}
		
		
		// danach die Differenzwerte bestimmen
		bdMengeDiffGesamt = bdMengeWEGesamt.subtract(bdMengeWAGesamt);
		bdMengeDiffBewertet = bdMengeWEBewertet.subtract(bdMengeWABewertet);
		bdMengeDiffOhneAbrechnung = bdMengeWEOhneAbrechnung.subtract(bdMengeWAOhneAbrechnung);
		bdMengeDiffUnbewertet = bdMengeWEUnbewertet.subtract(bdMengeWAUnbewertet);

		bdPreisDiff = bdPreisWA.subtract(bdPreisWE);
		bdSummeDiff = bdSummeWA.subtract(bdSummeWE);
		
		
		//Saldo-Werte ermitteln
		this.ReadSaldoForSelectedSorte();
		
		refreshSummaryValues();
		
	}



	private void clearData() {
		lblMessage.setVisible(false);
		
		bdMengeWEGesamt = BigDecimal.ZERO;
		bdMengeWEBewertet = BigDecimal.ZERO;
		bdMengeWEOhneAbrechnung = BigDecimal.ZERO;
		bdMengeWEUnbewertet = BigDecimal.ZERO;
		bdPreisWE = BigDecimal.ZERO;
		bdSummeWE = BigDecimal.ZERO;
		bdMengeWEPreisBerechnung = BigDecimal.ZERO;
		
		bdMengeWAGesamt = BigDecimal.ZERO;
		bdMengeWABewertet = BigDecimal.ZERO;
		bdMengeWAOhneAbrechnung = BigDecimal.ZERO;
		bdMengeWAUnbewertet = BigDecimal.ZERO;
		bdPreisWA = BigDecimal.ZERO;
		bdSummeWA = BigDecimal.ZERO;
		bdMengeWAPreisBerechnung = BigDecimal.ZERO;
		
		bdMengeDiffGesamt = BigDecimal.ZERO;
		bdMengeDiffBewertet = BigDecimal.ZERO;
		bdMengeDiffOhneAbrechnung = BigDecimal.ZERO;
		bdMengeDiffUnbewertet = BigDecimal.ZERO;
		bdPreisDiff = BigDecimal.ZERO;
		bdSummeDiff = BigDecimal.ZERO;
		
		bdSaldoBis = BigDecimal.ZERO;
		bdSaldoVon = BigDecimal.ZERO;
		bdSaldoDiff = BigDecimal.ZERO;
		
	}


	private void showMessage(String sMessage){
		lblMessage.setVisible(true);
		lblMessage.setText(sMessage);
	}
	
	private void refreshSummaryValues() {
		this.lblDescMenge.set_Text("(" +  sRefEinheitMenge + ")");
		this.lblDescMengeBewertet.set_Text("(" + sRefEinheitMenge + ")");
		this.lblDescMengeOhneAbrechnung.set_Text("(" + sRefEinheitMenge + ")");
		this.lblDescMengeUnbewertet.set_Text( "(" +  sRefEinheitMenge + ")");
		this.lblDescPreis.set_Text("(Eur./" + sRefEinheitPreis + ")");
		this.lblDescSumme.set_Text("(Eur.)");
		
		this.lblMengeWE.setText(bibALL.makeDez(bdMengeWEGesamt.doubleValue(), 3, true));
		this.lblMengeWEBewertet.setText(bibALL.makeDez(bdMengeWEBewertet.doubleValue(), 3, true));
		this.lblMengeWEOhneAbrechnung.setText(bibALL.makeDez(bdMengeWEOhneAbrechnung.doubleValue(), 3, true));
		this.lblMengeWEUnbewertet.setText(bibALL.makeDez(bdMengeWEUnbewertet.doubleValue(), 3, true));
		this.lblSummeWE.setText( bibALL.makeDez(bdSummeWE.doubleValue(),2,true));
		this.lblPreisWE.setText(bibALL.makeDez(bdPreisWE.doubleValue(),2,true));
		
		this.lblMengeWA.setText(bibALL.makeDez(bdMengeWAGesamt.doubleValue(), 3, true));
		this.lblMengeWABewertet.setText(bibALL.makeDez(bdMengeWABewertet.doubleValue(), 3, true));
		this.lblMengeWAOhneAbrechnung.setText(bibALL.makeDez(bdMengeWAOhneAbrechnung.doubleValue(), 3, true));
		this.lblMengeWAUnbewertet.setText(bibALL.makeDez(bdMengeWAUnbewertet.doubleValue(), 3, true));
		this.lblSummeWA.setText( bibALL.makeDez(bdSummeWA.doubleValue(),2,true));
		this.lblPreisWA.setText(bibALL.makeDez(bdPreisWA.doubleValue(),2,true));
		
		this.lblMengeDiff.setText(bibALL.makeDez(bdMengeDiffGesamt.doubleValue(), 3, true));
		this.lblMengeDiffBewertet.setText(bibALL.makeDez(bdMengeDiffBewertet.doubleValue(), 3, true));
		this.lblMengeDiffOhneAbrechnung.setText(bibALL.makeDez(bdMengeDiffOhneAbrechnung.doubleValue(), 3, true));
		this.lblMengeDiffUnbewertet.setText(bibALL.makeDez(bdMengeDiffUnbewertet.doubleValue(), 3, true));
		this.lblSummeDiff.setText( bibALL.makeDez(bdSummeDiff.doubleValue(),2,true));
		this.lblPreisDiff.setText(bibALL.makeDez(bdPreisDiff.doubleValue(),2,true));
		
		try {
			String sIDSorte = m_oSelector.getIDSelectedSorte();
			String sHauptsorte = m_oSelector.getSelectedHauptsorte();
			ArrayList<String[]> SorteVonBis = m_oSelector.getSortenblockSelektor().get_ArrayOfSelectedValues();
			
			// Einzelsorte
			if (!bibALL.isEmpty(sIDSorte)){
				this.lblDescSaldoSorte.setText(m_oSelector.getSelectedSorteDesc());
			} 
			// Sortenbereich/e
			else if (SorteVonBis != null && SorteVonBis.size() > 0) {
				String sInfo = "";
				String sInfoFull = "";
				for (String[] bereich : SorteVonBis) {
					sInfo += " " + bereich[0] + "-" + bereich[1] + ",";
				}
				sInfoFull = sInfo;
				if (sInfo.length() > 30) {
					sInfo = sInfo.substring(0,27) + "...";
				} else {
					sInfo = sInfo.substring(0,sInfo.length()-1);
				}
				this.lblDescSaldoSorte.setText("Sortenbereich:" + sInfo);
				this.lblDescSaldoSorte.setToolTipText(sInfoFull);
			}
			// Hauptsorte
			else if (!bibALL.isEmpty(sHauptsorte)){
				this.lblDescSaldoSorte.setText(new MyString("Hauptsorte ").CTrans() + sHauptsorte);
			} else {
				// keine sorte gewählt
				this.lblDescSaldoSorte.setText("-");
			}
			
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

}
