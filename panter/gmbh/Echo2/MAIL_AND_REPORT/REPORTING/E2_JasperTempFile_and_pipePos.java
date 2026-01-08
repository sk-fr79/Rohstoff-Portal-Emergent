package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_PIPELINE_POS;

public class E2_JasperTempFile_and_pipePos 
{

	private E2_JasperTempFile  				oJasperTempFile = null;
	private RECORD_REPORT_PIPELINE_POS    	oRecordPipelinePOS = null;
	
	/**
	 * 
	 * @param JasperTempFile
	 * @param RecordPipelinePOS (can be null in Standard-processing)
	 */
	public E2_JasperTempFile_and_pipePos(E2_JasperTempFile JasperTempFile,RECORD_REPORT_PIPELINE_POS RecordPipelinePOS) 
	{
		super();
		this.oJasperTempFile = JasperTempFile;
		this.oRecordPipelinePOS = RecordPipelinePOS;
	}

	
	
	public E2_JasperTempFile get_oJasperTempFile() 
	{
		return oJasperTempFile;
	}
	
	public RECORD_REPORT_PIPELINE_POS get_oRecordPipelinePOS() 
	{
		return oRecordPipelinePOS;
	}

	
}
