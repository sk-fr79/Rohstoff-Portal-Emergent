package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.exceptions.myException;

public class Plus  implements Term {

	private Term[] terms_to_add = null;
	private boolean in_brackets = false;
	
	/**
	 * term zum addieren in sql-ausdruecken
	 */
	public Plus(Term... terms) {
		super();
		this.terms_to_add=terms;
	}


	public Plus in_brackets() {
		this.in_brackets=true;
		return this;
	}

	@Override
	public String s() throws myException {
		StringBuffer s = new StringBuffer();
		
		if (this.terms_to_add!=null && this.terms_to_add.length>0)  {
			for (Term t: this.terms_to_add) {
				s.append(t.s());
				s.append("+");
			}
			
			String c = s.toString();
			c=c.substring(0,c.length()-1);
			
			if (this.in_brackets) {
				return "("+c+")";
			}else {
				return c;
			}
			
		} else {
			return null;
		}
		
	}


}
