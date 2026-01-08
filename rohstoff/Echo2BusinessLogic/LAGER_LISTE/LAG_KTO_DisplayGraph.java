package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.charts.E2_ChartDisplay;
import panter.gmbh.Echo2.components.charts.E2_ScatterPlot;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.vectorForSegmentation;

/**
 * Fenster zum Darstellen eines Plots der aktuell gezeigten Lagerdaten 
 * @author manfred
 *
 */
public class LAG_KTO_DisplayGraph extends E2_BasicModuleContainer {
	
	//E2_XYPlot plot = new E2_XYPlot();
	E2_ScatterPlot plot = new E2_ScatterPlot();
	E2_ChartDisplay chart_display = null;
	
	E2_NavigationList m_naviList = null;
	
	public LAG_KTO_DisplayGraph(E2_NavigationList onavigationList) throws myException {
		super();
		this.m_naviList = onavigationList;
		initDialog();
		updatePlot();
	}
	
	
	private void initDialog() throws myException{
		
//		double[][] a2 = { { 0.0, 1.0 }, { 1.0, 0 }, { 2, 2 }, { 2, 3 }, { 4, 2} };
//		plot.set_DataSetValues("mySeries", a2);
//		plot.set_Titel("titel");
//		plot.set_XAchse("Euro");
//		plot.set_YAchse("Kg");
		
		plot.set_Titel("Wareneingang/Warenausgang im gewählten Zeitraum");
		plot.set_XAchse("Preis/Euro");
		plot.set_YAchse("Menge/Kg");
		
		chart_display = new E2_ChartDisplay(plot.getChart());
		
		this.get_oExtHeight();
		chart_display.setHeight(new Extent(500));
		chart_display.setWidth(new Extent(800));
		
		this.add(chart_display);
		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{

			}
		});

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Übersicht der Lagerpreise WE / WA"));
	}
	
	
	private void updatePlot(){
//		Random r = new Random();
//		
//		double[][] a2 = { { r.nextDouble(), 1.0 }, { r.nextDouble(), 0 }, { r.nextDouble(), 2 }, { r.nextDouble(), 3 }, { r.nextDouble(), 2} };
//		plot.set_DataSetValues("mySeries", a2);
//		plot.set_Titel("lslslsl");
		
		vectorForSegmentation vNeu = new vectorForSegmentation();
		vNeu.addAll(m_naviList.get_vectorSegmentation());
		vNeu.set_iSegmentGroesse(100);
		
		int iSegmente = vNeu.get_iZahlSegmente();

		String sIDEinheitReferenz = null;
		
		String sIDs = "";
		
		// zum Werte Sammeln und aggregieren, da wir ja evtl. nicht alle Einträge auf ein mal lesen
		TreeMap<Double, Double> slWE = new TreeMap<Double, Double>();
		TreeMap<Double, Double> slWA = new TreeMap<Double, Double>();
		
		ArrayList<double[]> listWE = new ArrayList<double[]>();
		ArrayList<double[]> listWA = new ArrayList<double[]>();
		
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
			sSql = "SELECT sum(ABS(JT_LAGER_KONTO.MENGE )),   " +
				   " nvl(JT_LAGER_KONTO.PREIS,0) ,  " +
				   " JT_LAGER_KONTO.BUCHUNGSTYP " 
				+ " FROM " + bibE2.cTO() + ".JT_LAGER_KONTO  " 
				+ " WHERE ID_LAGER_KONTO in ( "
				+ sIDs
				+ " ) " 
				+ " GROUP BY BUCHUNGSTYP,PREIS " 
				+ " ORDER BY BUCHUNGSTYP,PREIS";
			
			cLagerDaten = bibDB.EinzelAbfrageInArray(sSql, (String) null);
		
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0) {
				continue ;
			}
			
			for (int j = 0; j < cLagerDaten.length; j++) {
				
				String sMenge = cLagerDaten[j][0];
				String sPreis = cLagerDaten[j][1];
				String sTyp = cLagerDaten[j][2];
			
				Double dPreis = (sPreis != null ? new Double(sPreis) : 0.0);
				Double dMenge = (sMenge != null ? new Double(sMenge) : 0.0);
				
				double[] row = new double[2];
				row[0] = dPreis;
				row[1] = dMenge;
				if (sTyp.equalsIgnoreCase("WE")){
					// wenn es den key schon gibt, dann die Menge nochmal summieren
					if (slWE.containsKey(dPreis)){
						Double dM = slWE.get(dPreis);
						dM += dMenge;
						slWE.put(dPreis,dM);
					}else {
						// sonst den Preis einfach dazupacken
						slWE.put(dPreis, dMenge);
					}
				} else{
					// wenn es den key schon gibt, dann die Menge nochmal summieren
					if (slWA.containsKey(dPreis)){
						Double dM = slWA.get(dPreis);
						dM += dMenge;
						slWA.put(dPreis,dM);
					}else {
						// sonst den Preis einfach dazupacken
						slWA.put(dPreis, dMenge);
					}
				}
			}
		}
	
		// jetzt die sortierte List in ein Array von double umwandeln
		int sizeWA = slWA.size();
		int iWA = 0;
		double[][] laWA = new double[sizeWA][2];
		for (Map.Entry<Double, Double> e : slWA.entrySet()){
			laWA[iWA][0] = e.getKey().doubleValue();
			laWA[iWA][1] = e.getValue().doubleValue();
			iWA++;
		}

		int sizeWE = slWE.size();
		int iWE = 0;
		double[][] laWE = new double[sizeWE][2];
		for (Map.Entry<Double, Double> e : slWE.entrySet()){
			laWE[iWE][0] = e.getKey().doubleValue();
			laWE[iWE][1] = e.getValue().doubleValue();
			iWE++;
		}
		
		
		// wenn alle Mengen und Preise aufsummiert sind, dann die Durschnittspreise berechnen

		
		plot.set_DataSetValues("Warenausgang", laWA);
		plot.set_DataSetValues("Wareneingang", laWE);
		
		plot.set_Titel("Warenausgang/Wareneingang");
	
	}
	

}
