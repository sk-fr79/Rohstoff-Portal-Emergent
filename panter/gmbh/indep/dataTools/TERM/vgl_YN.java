package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.exceptions.myException;

public class vgl_YN extends vgl {

	/**
	 * 
	 * @param field
	 * @param yes_true__no_false
	 * .s()  gives: NVL([field],'N')=( yes_true__no_false?"'Y','N')
	 * @throws myException
	 */
	public vgl_YN(IF_Field field, boolean yes_true__no_false) throws myException {
		super(new FieldTerm(ATTRIBUTES.NVL_TRUE_FALSE,null, field),COMP.EQ,new TermSimple((yes_true__no_false?"'Y'":"'N'")));
	}


	/**
	 * 
	 * @param field
	 * @param yes_true__no_false
	 * .s()  gives: NVL([field],'N')=( yes_true__no_false?"'Y','N')
	 * @throws myException
	 */
	public vgl_YN(String tablePrefix, IF_Field field, boolean yes_true__no_false) throws myException {
		super(new FieldTerm(ATTRIBUTES.NVL_TRUE_FALSE,tablePrefix, field),COMP.EQ,new TermSimple((yes_true__no_false?"'Y'":"'N'")));
	}

	
	
}
