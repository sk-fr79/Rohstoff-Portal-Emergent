package panter.gmbh.Echo2.components.charts;

import nextapp.echo2.app.Extent;
import nextapp.echo2.chart.app.ChartDisplay;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;


public class E2_ChartDisplay extends ChartDisplay {
	
	private JFreeChart m_chart = null;
	
	private org.jfree.chart.plot.Plot m_plot = null;
	
	
	public E2_ChartDisplay() {
		super();
	}

	public E2_ChartDisplay(JFreeChart chart) {
		super(chart);
		this.m_chart = chart;
	}

	public E2_ChartDisplay(Plot plot) {
		super(plot);
		m_plot = plot;
	}

	@Override
	public JFreeChart getChart() {
		return super.getChart();
	}

	@Override
	public Extent getHeight() {
		return super.getHeight();
	}

	@Override
	public Extent getWidth() {
		return super.getWidth();
	}

	@Override
	public void setChart(JFreeChart newValue) {
		synchronized (this) {
			super.setChart(newValue);
		}
	}

	@Override
	public void setHeight(Extent newValue) {
		synchronized (this) {
			super.setHeight(newValue);
		}
	}

	@Override
	public void setWidth(Extent newValue) {
		synchronized (this) {
			super.setWidth(newValue);
		}
	}
	
	
	
	
}
