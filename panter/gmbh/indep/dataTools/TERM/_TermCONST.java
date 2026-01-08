package panter.gmbh.indep.dataTools.TERM;

import java.util.ArrayList;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class _TermCONST {

	public enum D_KEYWORDS implements Term{
		
		SELECT()
		,INSERT()
		,UPDATE()
		,FROM()
		,WHERE()
		,AND()
		,OR()
		,TRUE()
		,FALSE()
		,ON()
		,LEFTOUTERJOIN(" LEFT OUTER JOIN ")
		,RIGHTOUTERJOIN(" RIGHT OUTER JOIN ")
		,INNERJOIN(" INNER JOIN ")
		,AS()
		,ORDERBY("ORDER BY")
		,ASC()
		,DESC()
		,DISTINCT()
		;
		
		private String ersatz = null;
		private D_KEYWORDS(String p_ersatz) {
			this.ersatz = p_ersatz;
		}
		private D_KEYWORDS() {
		}
		
		@Override
		public String s() throws myException {
			if (S.isEmpty(this.ersatz)) {
				return this.name();
			} else {
				return this.ersatz;
			}
		}
		
		
	}
	
	/**
	 *  GE: >= <br>
	 *  GT: > <br>
	 *  EQ: = <br>
	 *  LT: < <br>
	 *  LE: <= <br>
	 */
	public enum COMP {
		
		GE(">=", ">=")
		,GT(">",">")
		,EQ("=","=")
		,LT("<","<")
		,LE("<=","<=")
		,LIKE(" like ","entspricht")
		,IN(" in ", "enthalten in")                		//2015-12-17 - neuer compare-operator "in"
		,NOT_IN(" not in ", "NICHT enthalten in")         	//2016-02-02 - neuer compare-operator "in"
		,ISNULL(" is NULL ","ist leer")					//2016-01-04 - neuer is null-comparator
		,ISNOTNULL(" IS NOT NULL ","ist nicht leer")	//2016-03-16 - neuer is null-comparator
		,NOT_EQ("<>","ungleich")				    	//2016-02-01 - neuer ungleich-operator
		;
		
		private String ersatz = null;
		private String dd_text = null;
		private COMP(String p_ersatz, String p_dd_text) {
			this.ersatz = p_ersatz;
			this.dd_text = p_dd_text;
		}
		private COMP() {
		}
		
		public String s() {
			if (S.isEmpty(this.ersatz)) {
				return this.name();
			} else {
				return this.ersatz;
			}
		}
		
		/**
		 * GE liefert >=
		 * @return
		 */
		public String ausdruck() {
			return this.ersatz;
		}
		
		public String get_dd_text() {
			return new MyE2_String(this.dd_text).CTrans();
		}
		
	}

	
	
	
	
	public static ArrayList<String> genAL(String... als) {
		ArrayList<String> al_rueck = new ArrayList<String>();
		for (String s: als) {
			al_rueck.add(s);
		}
		return al_rueck;
	}
	
	
	
	
	public enum ATTRIBUTES {
		 UPPER("UPPER(",")")
		,TRIM("TRIM(",")")
		,TO_CHAR("TO_CHAR(",")")
		,NVL_TRUE_FALSE("NVL(",",'N')")
		,TO_CHAR_DATE_YYYY_MM_DD("TO_CHAR(",",'YYYY-MM-DD')")
		,MIN("MIN(",")")
		,MAX("MAX(",")")
		,
		;
		
		private String s_before_field = null;
		private String s_after_field = null;
		
		private ATTRIBUTES(String p_before_field, String p_after_field) {
			this.s_before_field = p_before_field;
			this.s_after_field  = p_after_field;
		}

		public String get_before_field() {
			return s_before_field;
		}

		public String get_after_field() {
			return s_after_field;
		}
		
		public String embedd(String statementPart) {
			return this.s_before_field+statementPart+this.s_after_field;
		}
	}
	

	
	

	public enum JOINTYPES implements Term{
		LEFTOUTERJOIN(" LEFT OUTER JOIN ", 		SQLFieldMAP.LEFT_OUTER)
		,RIGHTOUTERJOIN(" RIGHT OUTER JOIN ",	SQLFieldMAP.RIGHT_OUTER)
		,INNERJOIN(" INNER JOIN ",				SQLFieldMAP.INNER)
		;
		
		private String sqlCode = null;
		private int    iRefOld;                //int-Wert, der in der sql-fieldmap fuer diese join-art benutzt wurde
		
		private JOINTYPES(String p_sqlCode, int iRef) {
			this.sqlCode = p_sqlCode;
			this.iRefOld = iRef;
		}

		@Override
		public String s() throws myException {
			if (S.isEmpty(this.sqlCode)) {
				throw new myException(this,"not defined ...");
			} else {
				return this.sqlCode;
			}
		}

		public int getRefOld() {
			return iRefOld;
		}
		
		
	}

	
}
