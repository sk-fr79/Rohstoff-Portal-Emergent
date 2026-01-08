package panter.gmbh.indep.dataTools.TERM;

import java.util.ArrayList;

import panter.gmbh.indep.exceptions.myException;

public abstract class TermList extends ArrayList<Term> implements Term {
	

	public TermList() {
		super();
	}


	public abstract String get_separator() throws myException;


	@Override
	public String s() throws myException {
		StringBuffer  sb = new StringBuffer();
		for (Term t: this) {
			//DEBUG.System_println("TERM <"+t.s()+">");
//			if (S.isFull(t.s().trim())) {
				sb.append(t.s()+this.get_separator());
//			}
		}
		
		String sr = sb.toString();
		if (sr!=null && sr.endsWith(this.get_separator())) {
			sr = sr.substring(0,sr.length()-this.get_separator().length());
		}
		
		return sr;
	}


	public Term get_LastTerm() {
		if (this.size()>0) {
			return this.get(this.size()-1);
		}
		return null;
	}

	public void remove_LastTerm() {
		if (this.size()>0) {
			this.remove(this.size()-1);
		}
	}


}
