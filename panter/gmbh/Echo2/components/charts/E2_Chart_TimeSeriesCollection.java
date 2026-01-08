package panter.gmbh.Echo2.components.charts;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;


public class E2_Chart_TimeSeriesCollection {

	
	TimeSeriesCollection m_time_series_coll = null;
	
	
	
	public E2_Chart_TimeSeriesCollection() {
		this.m_time_series_coll = new TimeSeriesCollection();
	}
	
	
	public E2_Chart_TimeSeriesCollection(TimeSeries series) {
		this.m_time_series_coll = new TimeSeriesCollection(series);
	}
	
	
	public E2_Chart_TimeSeriesCollection(String series_name, Class timePeriodClass, TimeSeriesDataItem[] dataset){
		this();
		this.setSeriesValues(series_name, timePeriodClass, dataset);
	}
	
	/**
	 * gibt die SeriesCollection zurück, die als Dataset im Chart verwendet werden kann.
	 * @return
	 */
	public TimeSeriesCollection getTimeSeriesCollection(){
		return m_time_series_coll;
	}
	
	
	
	
	/**
	 * fügt eine neue Serie zur Collection hinzu. Falls diese Serie schon besteht, wird sie überschrieben
	 * @param series_name
	 * @param series
	 */
	public void setSeries(String series_name, TimeSeries series) {
		int j = m_time_series_coll.indexOf(series_name);
		if ( j >= 0)	m_time_series_coll.removeSeries(j);
		m_time_series_coll.addSeries(series);
	}


	
	public void setSeriesValues(String series_name , Class timePeriodClass, TimeSeriesDataItem[] dataset){
		
		int j = m_time_series_coll.indexOf(series_name);
		if ( j >= 0)	m_time_series_coll.removeSeries(j);
		
		TimeSeries	time_series = new TimeSeries(series_name,timePeriodClass);
		
		for (int i = 0; i < dataset.length; i++) {
			time_series.add (dataset[i]);
		}

		m_time_series_coll.addSeries( time_series);
	}
	

}
