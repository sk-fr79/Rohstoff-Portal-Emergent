package panter.gmbh.indep.dataTools.TERM;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class SetList implements Term {
	
	private HashMap<Term, Term> pairs = new HashMap<>();
	
	public SetList _addPair(Term t1, Term t2) {
		this.pairs.put(t1, t2);
		return this;
	}
	
	
	public SetList _addPair(Term t1, String s2) {
		this.pairs.put(t1, new TermSimple(s2));
		return this;
	}
	
	
	
	@Override
	public String s() throws myException {
		Vector<String> parts = new Vector<>();
		if (this.pairs.size()>0) {
			for (Term t1: this.pairs.keySet()) {
				parts.add(t1.s()+"="+this.pairs.get(t1).s());
			}

			return bibALL.Concatenate(parts, ",", "");
			
			
		}
		
		return null;
	}

}
