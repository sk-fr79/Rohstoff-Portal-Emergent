package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;

public class InnerJoin extends Join {

	public InnerJoin(Object p_table, Object l, String m, Object r ) throws myException {
		super(p_table,l,m,r);
	}

	
	public InnerJoin(Object p_table, Object l, Object r ) throws myException {
		super(p_table,l,r);
	}

	
	public InnerJoin(Term p_table, And bedingung) {
		super(p_table, bedingung);
	}

	public InnerJoin(Term p_table, vgl bedingung) {
		super(p_table, bedingung);
	}


	@Override
	public String get_JoinIntro() throws myException {
		return _TermCONST.D_KEYWORDS.INNERJOIN.s();
	}


}
