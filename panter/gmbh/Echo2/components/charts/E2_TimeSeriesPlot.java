package panter.gmbh.Echo2.components.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesDataItem;

public class E2_TimeSeriesPlot extends XYPlot {

	// Haupt-Y-Achse (standard)
	E2_Chart_TimeSeriesCollection m_xy_series = null;
	
	// falls man eine zweite Y-Achse braucht mit einer anderen Range, muss man eine 2. Collection aufmachen.
	E2_Chart_TimeSeriesCollection m_xy_series_2 = null;
	
	
	private String 	m_sTitel = null;
	private String 	m_sXAchse = null;
	private String 	m_sYAchse = null;
	private String 	m_sYAchse2 = null;

	Class			m_classTimeSeries = null;
	
	
	public E2_TimeSeriesPlot(Class timePeriodClass) {
		super();
		this.m_xy_series = new E2_Chart_TimeSeriesCollection();
		this.m_xy_series_2 = new E2_Chart_TimeSeriesCollection();
		
		this.m_classTimeSeries = timePeriodClass;
	}

	
	public String get_Titel() {
		return m_sTitel;
	}
	
	public void set_Titel(String mSTitel) {
		m_sTitel = mSTitel;
	}


	public String get_XAchse() {
		return m_sXAchse;
	}
	public void set_XAchse(String mSXAchse) {
		m_sXAchse = mSXAchse;
	}


	public String get_YAchse() {
		return m_sYAchse;
	}
	public void set_YAchse(String mSYAchse) {
		m_sYAchse = mSYAchse;
	}


	public void set_YAchse2(String m_sYAchse2) {
		this.m_sYAchse2 = m_sYAchse2;
	}
	public String get_YAchse2() {
		return m_sYAchse2;
	}

	

	public void set_DataSetValues(String series_name, TimeSeriesDataItem[] dataset){
		this.set_DataSetValues(series_name, dataset,1);
	}
 
	public void set_DataSetValues(String series_name, TimeSeriesDataItem[] dataset,int YAxis){

		if (YAxis == 2){
			this.m_xy_series_2.setSeriesValues(series_name, m_classTimeSeries, dataset);
		} else {
			this.m_xy_series.setSeriesValues(series_name, m_classTimeSeries, dataset);
		}
	}
	
	public JFreeChart getChart(){
		this.setDomainGridlinesVisible(true);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(m_sTitel, m_sXAchse, m_sYAchse, m_xy_series.getTimeSeriesCollection(), true, true, false);
		
		XYPlot plot = chart.getXYPlot();
		

		
		// falls eine
		if (m_sYAchse2 != null){
			
			NumberAxis axis2 = new NumberAxis(m_sYAchse2);
			axis2.setAutoRangeIncludesZero(false);
			//axis2.setAutoRange(true);

			plot.setRangeAxis(1, axis2);
			plot.setDataset(1, m_xy_series_2.getTimeSeriesCollection());
			plot.mapDatasetToRangeAxis(1, 1);
			
			
			XYItemRenderer renderer = plot.getRenderer();
			if (renderer instanceof XYLineAndShapeRenderer) {
				XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
	            rr.setBaseShapesFilled(true);
	            rr.setBaseShapesVisible(true);
	            //plot.setRenderer(0, renderer);
	        }
			
			
			XYLineAndShapeRenderer rr2 = new XYLineAndShapeRenderer();
	        //rr2.setSeriesPaint(0, Color.black);
			rr2.setSeriesStroke(0, DEFAULT_GRIDLINE_STROKE);
	        rr2.setBaseShapesFilled(false);
            rr2.setBaseShapesVisible(true);
	    
	        plot.setRenderer(1, rr2);


			
			
		}
		
		return chart;
		
	}
	
	
	
	
}
