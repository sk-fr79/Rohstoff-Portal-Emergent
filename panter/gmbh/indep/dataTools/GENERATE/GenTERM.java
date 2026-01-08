package panter.gmbh.indep.dataTools.GENERATE;

import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class GenTERM {
	
	private Vector<TERM>  vAusdruck = new Vector<TERM>();
	
	public GenTERM() {
		super();
	}

	public GenTERM AppendTerm(String left,String op, String right) throws myException {
		if (S.isEmpty(left) || S.isEmpty(op) || S.isEmpty(right)) {
			throw new myException(this,"Error AppendingTerm! Empty Values are not allowed !!!");
		}
		this.vAusdruck.add(new TERM(left, op, right));
		return this;
	}

	public GenTERM AppendTerm(String left,String op, String right, boolean apostroLeft, boolean apostroRight) throws myException {
		if (S.isEmpty(left) || S.isEmpty(op) || S.isEmpty(right)) {
			throw new myException(this,"Error AppendingTerm! Empty Values are not allowed !!!");
		}
		this.vAusdruck.add(new TERM(left, op, right,apostroLeft,apostroRight));
		return this;
	}

	public String get_TERMS_WITH(String cOPERATOR) throws myException {
		Vector<String> vHELP = new Vector<String>();
		for (TERM term: this.vAusdruck) {
			vHELP.add(term.get_TERM());
		}
		return bibALL.Concatenate(vHELP, (" "+cOPERATOR+" "), " FALSE ");
	}
	
	/**
	 * 
	 * @return s get_TERMS_WITH("AND")
	 * @throws myException
	 */
	public String get_TERMS() throws myException {
		return this.get_TERMS_WITH("AND");
	}
	
}
