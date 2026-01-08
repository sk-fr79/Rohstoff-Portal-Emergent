package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.indep.exceptions.myException;

public class JasperFileDef_FACT extends JasperFileDef
{
	
	public static JasperFileDef get_JasperFileDef(String cMimeType)  throws myException 
	{
		if (cMimeType.equals(JasperFileDef.MIMETYP_PDF))
		{
			return new JasperFileDef_PDF();
		}
		else if (cMimeType.equals(JasperFileDef.MIMETYP_XLS))
		{
			return new JasperFileDef_XLS();
		}
		else if (cMimeType.equals(JasperFileDef.MIMETYP_CSV))
		{
			return new JasperFileDef_CSV();
		}
		else if (cMimeType.equals(JasperFileDef.MIMETYP_HTML))
		{
			return new JasperFileDef_HTML();
		}
		else if (cMimeType.equals(JasperFileDef.MIMETYP_XML))
		{
			return new JasperFileDef_XML();
		}
		else
		{
			throw new myException("JasperFileDef_FACT:Undefined Filetype for report");
		}
	}

}
