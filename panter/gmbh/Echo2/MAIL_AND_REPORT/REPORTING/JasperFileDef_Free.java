package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.Echo2.MyE2_String;

public class JasperFileDef_Free extends JasperFileDef
{

	/**
	 * 
	 * @param ENDING_CSV
	 * @param MIMETYP_CSV
	 * @param UserText_CSV
	 */
	public JasperFileDef_Free(String ENDING_CSV,String MIMETYP_CSV, MyE2_String UserText_CSV)
	{
		super();
		this.Endung = ENDING_CSV;
		this.MimeType = MIMETYP_CSV;
		this.UserText  = UserText_CSV;

	}

}
