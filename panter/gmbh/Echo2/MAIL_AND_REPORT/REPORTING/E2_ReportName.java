package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

public class E2_ReportName 
{
	public String 	cReportNameWithPath = "";
	public int    	iNumberInReport = 0;
	public boolean  bIsMainReport = true;
	
	public E2_ReportName(String reportNameWithPath, int numberInReport, boolean bMainReport)
	{
		super();
		this.cReportNameWithPath = reportNameWithPath;
		this.iNumberInReport = numberInReport;
		this.bIsMainReport = bMainReport;
	}

}
