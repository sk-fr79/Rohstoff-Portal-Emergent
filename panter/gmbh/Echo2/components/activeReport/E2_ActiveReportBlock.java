package panter.gmbh.Echo2.components.activeReport;

import panter.gmbh.indep.exceptions.myException;


public abstract class E2_ActiveReportBlock 
{
	/*
	 * hat der block keinen extender, dann gilt er immer als extended
	 */
	private boolean 						bHasExtender = false;
	private E2_ActiveReport 				oMotherReport = null;

	private int   							insetColNumber = 0;              //einrueckung

	private E2_ActiveReportExtenter  		oExtender = new E2_ActiveReportExtenter();
	private E2_ActiveReportBlock     		oMotherReportBlock = null;


	public E2_ActiveReportBlock(int InsetCols) 
	{
		super();
		this.insetColNumber =       InsetCols;
	}

	
	public abstract void        built(E2_ActiveReport oActiveReport, E2_ActiveReportBlock oCallingReportBlock) throws myException;
	
	public boolean get_bHasExtender() 
	{
		return bHasExtender;
	}
	
	
	public E2_ActiveReportBlock get_oMotherReportBlock() 
	{
		return oMotherReportBlock;
	}

	public E2_ActiveReport get_oMotherReport() 
	{
		return oMotherReport;
	}
	
	
	public int get_InsetColCount() 
	{
		return insetColNumber;
	}
	
	public E2_ActiveReportExtenter get_oExtender() 
	{
		return oExtender;
	}

	
	public boolean get_bIsExtended()
	{
		return ((!this.bHasExtender) || this.oExtender.isSelected());
	}
	
	public void set_oMotherReport(E2_ActiveReport oMotherReport) 
	{
		this.oMotherReport = oMotherReport;
	}
	
}
