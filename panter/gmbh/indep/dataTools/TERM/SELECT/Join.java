package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;

public abstract class Join implements Term {

	private And  		bedingung = new And();
	private Term   		table = null;


	public abstract String get_JoinIntro() throws myException;
	
	
	
	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public Join(Object p_table, Object l, String m, Object r ) throws myException {
		super();
		this.interPret(p_table, l, m, r);
	}

	
	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public Join(Object p_table, Object l, Object r ) throws myException {
		super();
		this.interPret(p_table, l, COMP.EQ.s(), r);
	}

	
	public Join(Term p_table, And bedingung) {
		super();
		this.bedingung = bedingung;
		this.table = p_table;
	}

	
	public Join(Term p_table, vgl bedingung) {
		super();
		this.bedingung.add(bedingung);
		this.table = p_table;
	}

	
	
	private void interPret(Object tabObject, Object FieldObLeft, String compString,  Object FieldObRight) throws myException {
		
		if 			(tabObject instanceof TableTerm) {
			this.table = (TableTerm)tabObject;
		} else if 	(tabObject instanceof Term) {
			this.table = (Term)tabObject;
		} else if   (tabObject instanceof String) {
			this.table = new TermSimple((String)tabObject);
		} else if   (tabObject instanceof _TAB) {
			this.table = new TermSimple(((_TAB)tabObject).fullTableName()+" "+((_TAB)tabObject).fullTableName());
		} else {
			throw new myException(this,"tabObject can only be: TableTerm, Term, _TAB or String-Type");
		}
		
		
//		String s_left = this.interPretField(FieldObLeft);
//		String s_right = this.interPretField(FieldObRight);
		
		this.bedingung.and(FieldObLeft, compString, FieldObRight);
	}
	
//	private String interPretField(Object fieldObject) throws myException {
//		
//		if 			(fieldObject instanceof FieldTerm) {
//			return ((FieldTerm)fieldObject).get_termWithout_As_Part().s();
//		} else if 	(fieldObject instanceof Term) {
//			return ((Term)fieldObject).s();
//		} else if   (fieldObject instanceof String) {
//			return  (String)fieldObject;
//		} else if   (fieldObject instanceof IF_Field) {
//			return  ((IF_Field)fieldObject).fieldName();
//		} else {
//			throw new myException(this,"fieldObject can only be: FieldTerm, Term, String or IF_Field -Type");
//		}
//	}

	
	
	
	
	@Override
	public String s() throws myException {
		StringBuffer s = new StringBuffer(this.get_JoinIntro()+" "+this.table.s()); 
		s.append(" "+_TermCONST.D_KEYWORDS.ON.name()+" "+this.bedingung.s());
		
		return s.toString();
	}
	
	
	
	
}
