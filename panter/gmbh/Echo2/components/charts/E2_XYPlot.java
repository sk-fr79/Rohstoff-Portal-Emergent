package panter.gmbh.Echo2.components.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;

public class E2_XYPlot extends XYPlot {

	E2_Chart_XYSeriesCollection m_xy_series = null;
	//XYSeriesCollection m_xy_series_coll = null;
	
	String 			m_sTitel = null;
	String 			m_sXAchse = null;
	String 			m_sYAchse = null;

	
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




	public E2_XYPlot() {
		super();
		//this.m_xy_series_coll = new XYSeriesCollection();
		this.m_xy_series = new E2_Chart_XYSeriesCollection();
	}

	
	public void set_DataSetValues(String series_name , double[][] xydataset){
		this.m_xy_series.setSeriesValues(series_name, xydataset);
	}
	
	
	public JFreeChart getChart (){
		this.setDomainGridlinesVisible(true);
		
		return  ChartFactory.createXYLineChart(m_sTitel, m_sXAchse, m_sYAchse, m_xy_series.getXYSeriesCollection(), PlotOrientation.VERTICAL, true, true, false);
	}
	
}
