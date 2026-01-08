package panter.gmbh.indep.dataTools.TERM.SELECT;

import java.util.ArrayList;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermList;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public class SEL implements Term {

	//fieldLists kann sowohl einzelne fields als auch fieldlists als term aufnehmen
	private FieldTermList		 	fieldLists = new FieldTermList();
	private TableTerm   			from = null;
	private And 					and = new And();
	private joinList 				join = new joinList();
	private orderList   			order = new orderList();
	
	/*
	 * wird alias gefuellt, dann liefert der select-term folgendes (xxxxxxx) as <alias> um subselects einfacher zu formulieren
	 */
	private String   				alias = null;
	
	private boolean   				selectIsDistinct = false;
	
	
	private VEK<ParamDataObject>    implicitParamDataObjects = new VEK<>();
	
	/**
	 * 
	 * @param subSel, wenn null oder leer, dann ist die fieldlist *
	 */
	public SEL(SEL subSel) {
		super();
		this.fieldLists.add(subSel);
	}
	
	/**
	 * 
	 * @param IF_Field, wenn null oder leer, dann ist die fieldlist *
	 */
	public SEL(IF_Field... field) {
		super();
		for (IF_Field f: field) {
			this.fieldLists.add(f.t());
		}
	}
	
	
	/**
	 * 
	 * @param FieldTerm, wenn null oder leer, dann ist die fieldlist *
	 */
	public SEL(FieldTerm... field) {
		super();
		for (FieldTerm f: field) {
			this.fieldLists.add(f);
		}
	}
	

	
	/**
	 * 
	 * @param p_al_parts, wenn null oder leer, dann ist die fieldlist *
	 */
	public SEL(ArrayList<String> p_al_parts) {
		super();
		this.fieldLists.add(new FieldTermList(p_al_parts));
	}

	/**
	 * 
	 * @param s.. liste
	 */
	public SEL(String... s) {
		super();
		this.fieldLists.add(new FieldTermList(s));
	}
	
	
	public SEL() {
		super();
	}

	
	/**
	 * 
	 * @param tab
	 * @throws myException
	 * adds all fields of this tab
	 * 
	 */
	public SEL(_TAB... tab) throws myException {
		for (_TAB table: tab) {
			for (IF_Field f: table.get_fields()) {
				this.ADDFIELD(new FieldTerm(f));
			}
		}
		
	}
	
	
	/**
	 * abkuerzung fuer new SEL(_tab).FROM(_tab)
	 * @author martin
	 * @date 27.03.2020
	 *
	 * @param table
	 * @param autoFrom
	 * @throws myException
	 */
	public SEL(_TAB table,boolean autoFrom) throws myException {
		for (IF_Field f: table.get_fields()) {
			this.ADDFIELD(new FieldTerm(f));
		}
		if (autoFrom) {
			this.FROM(table);
		}
	}
	
	
	public SEL FROM(String table) {
		this.from = new TableTerm(table);
		return this;
	}

	
	
	public SEL FROM(String table, String alias) {
		this.from = new TableTerm(table,alias);
		return this;
	}

	
	public SEL FROM(TableTerm tabTerm) {
		this.from = tabTerm;
		return this;
	}
	
	
	public SEL FROM(_TAB tab) {
		this.from = new TableTerm(tab.fullTableName());
		return this;
	}
	
	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param tab
	 * @param alias
	 * @return
	 */
	public SEL FROM(_TAB tab, String alias) {
		this.from = new TableTerm(tab.fullTableName(),alias);
		return this;
	}
	
	/**
	 * 
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL WHERE(Object l, String m, Object r) throws myException {
		this.and = this.and.and(l,m,r);
		return this;
	}

	
	/**
	 * 
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL WHERE(Object l, Object r) throws myException {
		this.and = this.and.and(l,r);
		return this;
	}
	


	
	
	public SEL WHERE(Term term) {
		this.and = this.and.and(term);
		return this;
	}
	
	/**
	 * 
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL AND(Object l, String m, Object r) throws myException {
		this.and = this.and.and(l,m,r);
		return this;
	}

	
	/**
	 * 
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL AND(Object l, Object r) throws myException {
		this.and = this.and.and(l,r);
		return this;
	}
	
	
	
	public SEL AND(Term term) {
		this.and = this.and.and(term);
		return this;
	}

	
	/**
	 * 
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL OR(Object l, String m, Object r) throws myException {
		this.and=this.and.or(l, m, r);
		return this;
	}
	
	
	/**
	 * 
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL OR(Object l, Object r) throws myException {
		this.and=this.and.or(l, r);
		return this;
	}
	
	public SEL OR(Term term) {
		this.and=this.and.or(term);
		return this;
	}

	
	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL LEFTOUTER(Object p_table, Object l, String m, Object r) throws myException {
		this.join.add(new LeftOuterJoin(p_table, l,m,r));
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 29.10.2020
	 *
	 * @param leftOuter
	 * @return
	 * @throws myException
	 */
	public SEL LEFTOUTER(LeftOuterJoin leftOuter) throws myException {
		this.join.add(leftOuter);
		return this;
	}
	
	
	
	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL RIGHTOUTER(Object p_table, Object l, String m, Object r) throws myException {
		this.join.add(new RightOuterJoin(p_table, l,m,r));
		return this;
	}
	

	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL INNERJOIN(Object p_table, Object l, String m, Object r) throws myException {
		this.join.add(new InnerJoin(p_table, l,m,r));
		return this;
	}
	
	
	
	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL LEFTOUTER(Object p_table, Object l, Object r) throws myException {
		this.join.add(new LeftOuterJoin(p_table, l,_TermCONST.COMP.EQ.s(),r));
		return this;
	}
	
	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL RIGHTOUTER(Object p_table, Object l, Object r) throws myException {
		this.join.add(new RightOuterJoin(p_table, l,_TermCONST.COMP.EQ.s(),r));
		return this;
	}
	

	/**
	 * 
	 * @param p_table: 	can be: TableTerm, Term, _TAB or String-Type
	 * @param l:		FieldTerm, Term, String or IF_Field
	 * @param m			String (compare-operator)
	 * @param r: 		FieldTerm, Term, String or IF_Field
	 * @throws myException
	 */
	public SEL INNERJOIN(Object p_table, Object l, Object r) throws myException {
		this.join.add(new InnerJoin(p_table, l,_TermCONST.COMP.EQ.s(),r));
		return this;
	}
	

	
	public SEL JOIN(Join p_join) {
		this.join.add(p_join);
		return this;
	}

	
	
	public SEL ADDFIELD(String field) {
		this.fieldLists.add(new TermSimple(field));
		return this;
	}

	public SEL ADDFIELD(IF_Field field) {
		this.fieldLists.add(new TermSimple(field.tnfn()));
		return this;
	}

	
	public SEL ADDFIELD(IF_Field ... fields) {
		for (IF_Field f: fields) {
			this.fieldLists.add(new TermSimple(f.tnfn()));
		}
		return this;
	}

	
	
	public SEL ADDFIELD(String field, String alias) throws myException {
		this.fieldLists.add(new TermLMR(field,_TermCONST.D_KEYWORDS.AS.s(),alias));
		return this;
	}
	
	public SEL ADDFIELD(Term... term) {
		for (Term t: term) {
			this.fieldLists.add(t);
		}
		return this;
	}

	
	
	/**
	 * definiert eine query as distinct
	 * @return
	 */
	public SEL ADD_Distinct() {
		this.selectIsDistinct = true;
		return this;
	}
	
	
	
	public SEL ORDERUP(String field) throws myException {
		this.order.add(new TermSimple(field+" "+_TermCONST.D_KEYWORDS.ASC.s()));
		return this;
	}
	
	
	public SEL ORDERDOWN(String field) throws myException {
		this.order.add(new TermSimple(field+" "+_TermCONST.D_KEYWORDS.DESC.s()));
		return this;
	}
	
	public SEL ORDERUP(Term field) throws myException {
		this.order.add(new TermSimple(field.s()+" "+_TermCONST.D_KEYWORDS.ASC.s()));
		return this;
	}
	
	
	public SEL ORDERDOWN(Term field) throws myException {
		this.order.add(new TermSimple(field.s()+" "+_TermCONST.D_KEYWORDS.DESC.s()));
		return this;
	}

	
	
	
	public SEL ORDERUP(IF_Field field) throws myException {
		this.order.add(new TermSimple(field.t().s()+" "+_TermCONST.D_KEYWORDS.ASC.s()));
		return this;
	}
	
	
	public SEL ORDERDOWN(IF_Field field) throws myException {
		this.order.add(new TermSimple(field.t().s()+" "+_TermCONST.D_KEYWORDS.DESC.s()));
		return this;
	}
	
	
	/**
	 * kompletter orderstring mit eigenem ASC- und DESC dazufuegen
	 * @author martin
	 * @date 23.11.2018
	 *
	 * @param order
	 * @return
	 * @throws myException
	 */
	public SEL ORDER(String order) throws myException {
		if (S.isFull(order)) {
			this.order.add(new TermSimple(order));
		}
		return this;
	}
	

	@Override
	public String s() throws myException {
		String s = _TermCONST.D_KEYWORDS.SELECT.s()+" ";
		if (this.selectIsDistinct) {
			s = s + _TermCONST.D_KEYWORDS.DISTINCT.s()+" "+ this.fieldLists.s();
		} else {
			s= s+ this.fieldLists.s();
		}

		//DEBUG.System_println("this.fieldLists.s(): "+this.fieldLists.s());
		
		s = s+" "+_TermCONST.D_KEYWORDS.FROM.s()+" "+this.from.s();
		
		if (this.join.size()>0) {
			s = s+" "+this.join.s();
		}
		
		if (this.and.size()>0) {
			s= s+" "+_TermCONST.D_KEYWORDS.WHERE.s()+" "+this.and.s();
		}
		
		if (this.order.size()>0) {
			s= s+" "+_TermCONST.D_KEYWORDS.ORDERBY.s()+" "+this.order.s();
		}
		
		if (S.isFull(this.alias)) {
			s = "("+s+") AS "+this.alias;
		}
		
		DEBUG.System_println("Mein SEL-Object: Ergebnis: "+s,DEBUG.DEBUG_FLAGS.FUNCTION_NEW_SELECT.name());
		
		return s;
	}
	
	
	public SEL SET_ALIAS(String p_alias) {
		this.alias = p_alias;
		return this;
	}

	public And getAnd() {
		return and;
	}
	

	
	private class joinList extends TermList {
		@Override
		public String get_separator() {
			return " ";
		}
	}

	private class orderList extends TermList {
		@Override
		public String get_separator() {
			return ", ";
		}
	}
	
	
	public SEL _addParam(ParamDataObject p) {
		this.implicitParamDataObjects._a(p);
		return this;
	}
	
	
	public VEK<ParamDataObject> getImplicitParamDataObjects() {
		return implicitParamDataObjects;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 16.01.2020
	 *
	 * @param params
	 * @return sqlStringExtended of this selection
	 * @throws myException
	 */
	public SqlStringExtended getSqlExt(VEK<ParamDataObject> params) throws myException {
		VEK<ParamDataObject> v = new VEK<ParamDataObject>()._a(this.implicitParamDataObjects)._a(params);
		return new SqlStringExtended(this.s())._addParameters(v);
	}

	/**
	 * 
	 * @author martin
	 * @date 27.03.2020
	 *
	 * @return
	 * @throws myException
	 */
	public SqlStringExtended getSqlExt() throws myException {
		return new SqlStringExtended(this.s())._addParameters(this.implicitParamDataObjects);
	}

}
