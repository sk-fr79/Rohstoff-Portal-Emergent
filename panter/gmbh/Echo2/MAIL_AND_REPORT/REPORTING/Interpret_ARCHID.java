package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class Interpret_ARCHID extends Interpret_SCRIPT 
{
	public Interpret_ARCHID(String Script, 	E2_JasperHASH Jasper_Hash) throws myException 
	{
		super(Interpret_SCRIPT.vSTARTER_ARCHID, Script, Jasper_Hash);
	}

	
	
	/**
	 * 2014-09-22: umstellung von singulaerer auf mehrfache Archids (Bsp. Mehrfachlieferschein-dokument wird unter mehreren 
	 *             Fuhren als Archivbeleg hinterlegt
	 * @return
	 */
	public Vector<Integer> get_vID_for_Arch()
	{
//		Integer iRueck = null;
//
//		if (bibALL.isLong(this.get_cRueckgabe()))
//		{
//			iRueck = new Integer(this.get_cRueckgabe());
//		}
//		
//		return iRueck;
		
		
		Vector<Integer> vRueck = new Vector<Integer>();

		if (S.isFull(this.get_cRueckgabe())) {
			
			Vector<String>  vIDStrings = bibALL.TrenneZeile(this.get_cRueckgabe(), Interpreter_JASPERHASH.SEPARATOR_MULTISTRING_WHEN_VECTOR);
			
			for (String cINTEGER: vIDStrings) {
				if (bibALL.isInteger(cINTEGER)) {
					vRueck.add(new Integer(cINTEGER));
				}
			}
		}
		return vRueck;
	}
	
	
}
