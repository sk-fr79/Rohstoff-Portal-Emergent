package panter.gmbh.Echo2.components.charts;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class E2_Chart_XYSeriesCollection {

	
	XYSeriesCollection m_xy_series_coll = null;

	public E2_Chart_XYSeriesCollection() {
		this.m_xy_series_coll = new XYSeriesCollection();
	}
	
	public E2_Chart_XYSeriesCollection(XYSeries series) {
		this.m_xy_series_coll = new XYSeriesCollection(series);
	}
	
	
	public E2_Chart_XYSeriesCollection(String series_name, double[][] xydataset){
		super();
		this.setSeriesValues(series_name, xydataset);
	}
	
	/**
	 * gibt die SeriesCollection zurück, die als Dataset im Chart verwendet werden kann.
	 * @return
	 */
	public XYSeriesCollection getXYSeriesCollection(){
		return m_xy_series_coll;
	}
	
	
	
	
	/**
	 * fügt eine neue Serie zur Collection hinzu. Falls diese Serie schon besteht, wird sie überschrieben
	 * @param series_name
	 * @param series
	 */
	public void setSeries(String series_name, XYSeries series) {
		int j = m_xy_series_coll.indexOf(series_name);
		if ( j >= 0)	m_xy_series_coll.removeSeries(j);
		m_xy_series_coll.addSeries( series);
	}


	
	public void setSeriesValues(String series_name , double[][] xydataset){
		
		int j = m_xy_series_coll.indexOf(series_name);
		if ( j >= 0)	m_xy_series_coll.removeSeries(j);
		
		XYSeries		xy_series = new XYSeries(series_name);
		
		for (int i = 0; i < xydataset.length; i++) {
			xy_series.add(xydataset[i][0],xydataset[i][1]);
		}

		m_xy_series_coll.addSeries( xy_series);
		
		
		
	}
	

}
