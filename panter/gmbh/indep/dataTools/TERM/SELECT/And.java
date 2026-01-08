package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermList;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.exceptions.myException;

/**
 * and ist eine TermList aus Termen, entweder singulare vergleichsterme oder or-terme
 * @author martin
 *
 */
public class And extends TermList {

	private boolean coverInBrackets = true;
	
	public And() {
		super();
	}
	

	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And(Object l, String m, Object r) throws myException {
		this.add(new TermLMR(l, m, r));
	}
	
	
	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			COMP (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And(Object l, _TermCONST.COMP m, Object r) throws myException {
		this.add(new TermLMR(l, m.s(), r));
	}

	
	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And(Object l,Object r) throws myException {
		this.add(new TermLMR(l, _TermCONST.COMP.EQ.s(), r));
	}

	
	public And(Term subTerm) {
		this.add(subTerm);
	}
	
	
	
	
	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And and(Object l, String m, Object r) throws myException {
		this.add(new TermLMR(l, m, r));
		return this;
	}

	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			_TermCONST.COMP m (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And and(Object l, _TermCONST.COMP m, Object r) throws myException {
		this.add(new TermLMR(l, m.s(), r));
		return this;
	}

	
	
	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And and(Object l,Object r) throws myException {
		this.add(new TermLMR(l, _TermCONST.COMP.EQ.s(), r));
		return this;
	}
	

	
	
	public And and(Term subTerm) {
		this.add(subTerm);
		return this;
	}
	
	
	
	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And or(Object l, String m, Object r) throws myException {
		return this.or(new TermLMR(l, m, r));
	}
	
	/**
	*  @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public And or(Object l,Object r) throws myException {
		return this.or(new TermLMR(l,_TermCONST.COMP.EQ.s(),r));	
	}

	
	public And or(Term subTerm) {
		TermList chain_with_or = null;
		if (this.size()==0) {
			chain_with_or = new Or(subTerm);
			this.add(chain_with_or);
		} else {
			//den or-term an den letzten und-ausdruck anhaengen 
			Term lastTerm = this.get_LastTerm();
			if (lastTerm instanceof Or) {
				((Or)lastTerm).OR(subTerm);
			} else {
				Or orLast = new Or(lastTerm);
				
				this.remove_LastTerm();
				this.add(orLast);
				
				orLast.OR(subTerm);
			}
		}
		
		return this;
	}

	
	
	public And or(Or part) {
		this.addAll(part);
		return this;
	}
	
	
	
	
	@Override
	public String get_separator() {
		return (" "+_TermCONST.D_KEYWORDS.AND.name()+" ");
	}


	public String s() throws myException {
		if (this.coverInBrackets) {
			return "("+super.s()+")";
		} else {
			return super.s();
		}
	}

	public boolean is_CoverInBrackets() {
		return coverInBrackets;
	}

	public void set_CoverInBrackets(boolean coverInBrackets) {
		this.coverInBrackets = coverInBrackets;
	}
	
	

	public And _clear() {
		this.clear();
		return this;
	}
	
	
}
