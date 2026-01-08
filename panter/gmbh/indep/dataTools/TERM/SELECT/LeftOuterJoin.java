package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;


public class LeftOuterJoin extends Join {


	public LeftOuterJoin(Object p_table, Object l, String m, Object r ) throws myException {
		super(p_table,l,m,r);
	}

	
	public LeftOuterJoin(Object p_table, Object l, Object r ) throws myException {
		super(p_table,l,r);
	}

	
	public LeftOuterJoin(Term p_table, And bedingung) {
		super(p_table, bedingung);
	}

	/**
	 * 
	 * @author martin
	 * @date 29.10.2020
	 *
	 * @param p_table
	 * @param bedingung
	 */
	public LeftOuterJoin(_TAB p_table, And bedingung) {
		super(new TableTerm(p_table), bedingung);
	}


	
	public LeftOuterJoin(Term p_table, vgl bedingung) {
		super(p_table, bedingung);
	}

	
	
	@Override
	public String get_JoinIntro() throws myException {
		return _TermCONST.D_KEYWORDS.LEFTOUTERJOIN.s();
	}

}
