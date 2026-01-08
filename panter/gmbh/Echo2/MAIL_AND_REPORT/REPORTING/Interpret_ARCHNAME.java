package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Interpret_ARCHNAME extends Interpret_SCRIPT 
{

	public Interpret_ARCHNAME(String Script,E2_JasperHASH oJasperHash) throws myException 
	{
		super(Interpret_SCRIPT.vSTARTER_ARCHNAME, Script, oJasperHash);
	}

	
	public String get_cArchName()
	{
		if (S.isEmpty(this.get_cRueckgabe()))
		{
			return this.get_oJasperHash().get_cReportBaseName();			
		}
		else
		{
			return this.get_cRueckgabe();
		}
	}
	
}
