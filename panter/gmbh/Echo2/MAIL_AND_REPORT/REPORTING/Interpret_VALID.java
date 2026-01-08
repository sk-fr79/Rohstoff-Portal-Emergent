package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Interpret_VALID extends Interpret_SCRIPT 
{

	public Interpret_VALID(String Script, E2_JasperHASH oJasperHash) throws myException 
	{
		super(Interpret_SCRIPT.vSTARTER_VALIDATION, Script, oJasperHash);
	}
	
	boolean get_bIsValid()
	{
		return S.NN(this.get_cRueckgabe()).equals("Y");
	}

}
