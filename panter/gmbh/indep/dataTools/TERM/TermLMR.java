package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.exceptions.myException;

/**
 * term aus 3 teilen, links mitte und rechts
 * @author martin
 *
 */
public class TermLMR implements Term {

	private String L = null;
	private String M = null;
	private String R = null;
	
	
	public TermLMR(String l, String m, String r) {
		super();
		this.L = l;
		this.M = m;
		this.R = r;
	}

	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public TermLMR(Object l, String m, Object r) throws myException {
		super();
		this.M = m;
		
		this.L = this.interPretField(l);
		this.R = this.interPretField(r);
	}

	private String interPretField(Object fieldObject) throws myException {
		
		if 			(fieldObject instanceof FieldTerm) {
			return ((FieldTerm)fieldObject).get_termWithout_As_Part().s();
		} else if 	(fieldObject instanceof Term) {
			return ((Term)fieldObject).s();
		} else if   (fieldObject instanceof String) {
			return  (String)fieldObject;
		} else if   (fieldObject instanceof IF_Field) {
			return  ((IF_Field)fieldObject).fullTableName()+"."+((IF_Field)fieldObject).fieldName();
		} else {
			throw new myException(this,"fieldObject can only be: FieldTerm, Term, String or IF_Field -Type");
		}
	}


	@Override
	public String s() throws myException {
		return this.L+" "+this.M+" "+this.R;
	}

	
}
