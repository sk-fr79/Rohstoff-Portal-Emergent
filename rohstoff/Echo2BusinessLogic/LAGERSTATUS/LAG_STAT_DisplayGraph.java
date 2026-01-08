package rohstoff.Echo2BusinessLogic.LAGERSTATUS;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import nextapp.echo2.app.Extent;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesDataItem;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.charts.E2_ChartDisplay;
import panter.gmbh.Echo2.components.charts.E2_TimeSeriesPlot;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

/**
 * Fenster zum Darstellen eines Plots der aktuell gezeigten Lagerdaten 
 * @author manfred
 *
 */
public class LAG_STAT_DisplayGraph extends E2_BasicModuleContainer {
	
	//E2_XYPlot plot = new E2_XYPlot();
	private E2_TimeSeriesPlot 	plot = new E2_TimeSeriesPlot(Day.class);
	private E2_ChartDisplay 	chart_display = null;
	
	private E2_NavigationList 	m_naviList = null;
	
	private MyDBToolBox			oDB = bibALL.get_myDBToolBox();
	private E2_ComponentMAP		oComponentMAP = null;
	
	
	public LAG_STAT_DisplayGraph(E2_NavigationList onavigationList) throws myException {
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
		
		plot.set_Titel("Lagerstatus ");
		plot.set_XAchse("Datum");
		plot.set_YAchse("Menge/Kg");
		
		plot.set_YAchse2("Euro");
		
		
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

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Übersicht des Lagerbestands "));
	}
	
	
	
	
	
	
	private void updatePlot() throws myException{
		
		
		this.oComponentMAP = (E2_ComponentMAP)this.m_naviList.get_oComponentMAP__REF().get_Copy(null);

		// anzahl der aktuell selektierten Zeilen
		int 		iNumberDataRows = this.m_naviList.get_vectorSegmentation().size();
		
		// wieviele segmentvectoren gibt es
		int 		iNumberOfSegments = this.m_naviList.get_vectorSegmentation().get_iZahlSegmente();
		
		
		int 		iAbsoluteRowNumber = 0;
		
		
		TimeSeriesDataItem[] seriesMengeGesamt = new TimeSeriesDataItem[iNumberDataRows];
		TimeSeriesDataItem[] seriesMengePreiseNichtNull = new TimeSeriesDataItem[iNumberDataRows];
		TimeSeriesDataItem[] seriesMengePreiseNull = new TimeSeriesDataItem[iNumberDataRows];
		TimeSeriesDataItem[] seriesMengePreiseLeer = new TimeSeriesDataItem[iNumberDataRows];
		TimeSeriesDataItem[] seriesWertAlleDataItems = new TimeSeriesDataItem[iNumberDataRows];
		TimeSeriesDataItem[] seriesAvgWertGesamt = new TimeSeriesDataItem[iNumberDataRows];
		TimeSeriesDataItem[] seriesAvgWertMitNull = new TimeSeriesDataItem[iNumberDataRows];
		TimeSeriesDataItem[] seriesAvgWertNichtNull = new TimeSeriesDataItem[iNumberDataRows];
		
		
		TimeSeriesDataItem  oItem = null;
		String 				sBuchungsdatum = null;
		Double 				oDbl = null;
		
		for (int i=0;i<iNumberOfSegments;i++)
		{
			Vector<String> vIDs = this.m_naviList.get_vectorSegmentation().get_vSegment(i);
			Vector<E2_ComponentMAP> vComponents;
			try {
				vComponents = this.BUILD_ComponentMAP_Vector_from_ActualSegment(vIDs);
			} catch (SQLException e) {
				// leeren Vector erzeugen
				vComponents = new Vector<E2_ComponentMAP>();
			}
			
			
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			
			for (int l=0;l<vComponents.size();l++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)vComponents.get(l);
			
				sBuchungsdatum = oMap.get_oInternalSQLResultMAP().get_FormatedValue("BUCHUNGSDATUM");

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("MENGE_GESAMT",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesMengeGesamt[iAbsoluteRowNumber] = oItem; 

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("MENGE_PREISE_NICHT_NULL",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesMengePreiseNichtNull[iAbsoluteRowNumber] = oItem; 

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("MENGE_PREISE_NULL",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesMengePreiseNull[iAbsoluteRowNumber] = oItem; 

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("MENGE_PREISE_LEER",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesMengePreiseLeer[iAbsoluteRowNumber] = oItem; 

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("SUM_RESTWERT",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesWertAlleDataItems[iAbsoluteRowNumber] = oItem; 

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("AVG_WERT_GESAMT",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesAvgWertGesamt[iAbsoluteRowNumber] = oItem; 

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("AVG_WERT_NICHT_NULL",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesAvgWertNichtNull[iAbsoluteRowNumber] = oItem; 

				oDbl = oMap.get_oInternalSQLResultMAP().get_DActualDBValue("AVG_WERT_MIT_NULL",true);
				oItem = new TimeSeriesDataItem(getDayObject(sBuchungsdatum, df), oDbl.doubleValue());
				seriesAvgWertMitNull[iAbsoluteRowNumber] = oItem;
				
				iAbsoluteRowNumber ++;
			}
		}
		
		plot.set_DataSetValues("Menge Gesamt", seriesMengeGesamt);
		plot.set_DataSetValues("Menge Preise nicht 0",seriesMengePreiseNichtNull );
		plot.set_DataSetValues("Menge Preise 0",seriesMengePreiseNull);
		plot.set_DataSetValues("Menge Preise Leer",seriesMengePreiseLeer);
		
		// auf 2. Achse
		plot.set_DataSetValues("Avg Gesamt", seriesAvgWertGesamt,2);
		plot.set_DataSetValues("Avg Preis > 0", seriesAvgWertNichtNull,2);
		plot.set_DataSetValues("Avg bepreist", seriesAvgWertMitNull,2);
		
		
		plot.set_Titel("Historie Lagerbestand Lagerbesta");
	
	}


	private Day getDayObject(String sBuchungsdatum, SimpleDateFormat df) {
		Day day = null;
		
		try {
			day = new Day(df.parse(sBuchungsdatum));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return day;
	}
	
	
	

	
	/**
	 * baut die liste auf mit dem actuell vorhandenen segment neu auf
	 */
	private Vector<E2_ComponentMAP> BUILD_ComponentMAP_Vector_from_ActualSegment(Vector<String> vIDs) throws myException, SQLException
	{
		String cQuerySegment = this.oComponentMAP.get_oSQLFieldMAP().get_CompleteSQLQueryFor_SEGMENT(vIDs);
		Vector<E2_ComponentMAP> vRueck = new Vector<E2_ComponentMAP>();

		MyDBResultSet oRS = this.oDB.OpenResultSet(cQuerySegment);
		
		if (oRS.RS != null)
		{
			/*
			 * alle E2_ComponentMAP - objekte bauen und fuellen
			 */
			try 
			{
				while (oRS.RS.next())
				{
					E2_ComponentMAP oMap = (E2_ComponentMAP)this.oComponentMAP.get_Copy(null);
					oMap.fill_ComponentMapFromDB(oRS.RS,null, E2_ComponentMAP.STATUS_VIEW,false,null);
					vRueck.add(oMap);
				}
			} 
			catch (SQLException e) 
			{
				oRS.Close();
				throw e;
			}
			oRS.Close();
			
		}
		return vRueck;

	}


}
