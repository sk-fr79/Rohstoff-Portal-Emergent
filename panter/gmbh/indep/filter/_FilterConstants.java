package panter.gmbh.indep.filter;

import java.util.Collection;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.MyString;

/** 
 * Contains all constants and Strings for any filter. 
 * 1. The regular constant is the value used in JT_FILTER (in database)
 * 2. The _PRINT suffixed constants are used upon toString()'ing a filter
 * 3. The _LANG suffixed constants are used in the UI to make the filter readable 
 * @author nils
 *
 */
public class _FilterConstants {
	/** The type of left and right handsides values for comparison */
	public static enum LEFT {
		LITERAL,
		FIELD,
		CONST
	}
	
	public static enum RIGHT {
		CONST, SET, SQL, FIELD, LITERAL
	}
	
	/** This module makes use of {@see panter.gmbh.indep.filter}, so we grab all the stuff from there */
	public static enum OP {
		NOT("NOT", 			"!"), 
		AND("AND", 			"&&"), 
		OR("OR", 			"||");
		
		private String id = 			null;
		private String alternateId = 	null;
		private String readAble = 	null;
	
		OP(String id, String altId) {
			this.id = id;
			this.alternateId = altId;
		}
	
		public String getAltValue() {
			return this.alternateId;
		}
		
		public String getId() {
			return this.id;
		}	
	}
	
	/** This module makes use of {@see panter.gmbh.indep.filter}, so we grab all the stuff from there */
	public static enum COMP {
		EQ("EQ", 					"="),
		GT("GT", 					">"),
		LT("LT", 					"<"),
		GEQ("GEQ", 					">="),
		LEQ("LEQ", 					"<="),
		CONTAINS("CONTAINS",		"CONTAINS"),
		STARTSWITH("STARTSWITH",	"STARTSWITH"),
		ENDSWITH("ENDSWITH",		"ENDSWITH"),
		NULL("NULL",				"NULL"),
		IN("IN",					"IN"),
		EXISTS("EXISTS",			"EXISTS"),
		IS("IS",					"IS"),
		LIKE("LIKE",				"LIKE"),
		;

		
		private String id = 			null;
		private String sqlId = 	null;

		COMP(String id, String sqlId) {
			this.id = id;
			this.sqlId = sqlId;
		}

		public String getSqlId() {
			return this.sqlId;
		}

		public static COMP fromSqlId(String sqlId) {
			for (COMP c : COMP.values()) {
				//korrektur: String-vergleich !!! martin, 2015-03-02
			    //if (c.getSqlId() == sqlId) {
			    if (c.getSqlId().equals(sqlId)) {
			    	return c;
			    }
			}
			return null;
		}
		
		public String getID() {
			return this.id;
		}
	}
}
