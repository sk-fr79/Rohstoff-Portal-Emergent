package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermList;
import panter.gmbh.indep.exceptions.myException;

public class Or extends TermList {
	
	public Or(String l, String m, String r) {
		this(new TermLMR(l, m, r));
	}

	public Or(String l, String r) {
		this(new TermLMR(l, "=", r));
	}

	public Or(Term lmr) {
		super();
		this.add(lmr);
	}
	
	public Or OR(Term term) {
		this.add(term);
		return this;
	}

	public Or OR(String l, String m, String r) {
		return this.OR(new TermLMR(l, m, r));
	}

	public Or OR(String l, String r) {
		return this.OR(new TermLMR(l, "=", r));
	}

	

	@Override
	public String s() throws myException {
		return " ("+super.s()+") ";
	}

	@Override
	public String get_separator() throws myException {
		return " "+_TermCONST.D_KEYWORDS.OR.s()+" ";
	}
	
}
