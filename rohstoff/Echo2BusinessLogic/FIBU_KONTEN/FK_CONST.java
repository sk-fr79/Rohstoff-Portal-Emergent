package rohstoff.Echo2BusinessLogic.FIBU_KONTEN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.query.ID;
import panter.gmbh.indep.dataTools.query.Query;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.dataTools.query.U;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter._FilterConstants;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;


public class FK_CONST
{
	/** 
	 * This module makes use of {@see panter.gmbh.indep.filter._FilterConstants}, 
	 * so we grab all the stuff from there 
	 */
	public static enum CompareValues {
		EQ(_FilterConstants.COMP.EQ,	 				new MyE2_String("gleich")),
		GT(_FilterConstants.COMP.GT,	 				new MyE2_String("größer")),
		LT(_FilterConstants.COMP.LT,	 				new MyE2_String("kleiner")),
		GEQ(_FilterConstants.COMP.GEQ,	 				new MyE2_String("größergleich")),
		LEQ(_FilterConstants.COMP.LEQ,	 				new MyE2_String("kleinergleich")),
		CONTAINS(_FilterConstants.COMP.CONTAINS,	 	new MyE2_String("enthält")),
		STARTSWITH(_FilterConstants.COMP.STARTSWITH,	new MyE2_String("beginnt mit")),
		ENDSWITH(_FilterConstants.COMP.ENDSWITH,	 	new MyE2_String("endet auf")),
		NULL(_FilterConstants.COMP.NULL,	 			new MyE2_String("null")),
		IN(_FilterConstants.COMP.IN, 					new MyE2_String("enthalten in"));
		
		private String id = 			null;
		private String alternateId = 	null;
		private MyString readAble = 	null;

		CompareValues(_FilterConstants.COMP cmp, MyString readable) {
			this.id = cmp.toString();
			this.alternateId = cmp.getSqlId();
			this.readAble = readable;
		}

		public String get_AltValue() {
			return this.alternateId;
		}
		
		public String get_ReadableTranslated() {
			return this.readAble.CTrans();
		}

		public String get_DBValue() {
			return this.id;
		}
		
		
	}
	
	public static String[][] get_CompareValuesDrowDownArray() throws myException {
		String[][] cRueck = new String[FK_CONST.CompareValues.values().length][2];
		
		int i=0;
		for (FK_CONST.CompareValues oVal: FK_CONST.CompareValues.values()) {
			cRueck[i][0]=oVal.get_ReadableTranslated();
			cRueck[i][1]=oVal.get_DBValue();
			i++;
		}
		return bibARR.add_emtpy_db_value_inFront(cRueck);
	}
	
	public static HashMap<String, String> get_hmCompareValues() {
		HashMap<String, String> hm = new HashMap<String, String>();
		for (FK_CONST.CompareValues oVal: FK_CONST.CompareValues.values()) {
			hm.put(oVal.get_DBValue(), oVal.get_ReadableTranslated());
		}
		return hm;
	}

	// ---------------------------------------------------------------------------------	

	
	
	
	public static enum ConditionTypesLeft {
		FIELD(_FilterConstants.LEFT.FIELD, 	new MyE2_String("Datenbank-Feld")),
		CONST(_FilterConstants.LEFT.CONST, 	new MyE2_String("Konstante"));
		
		private String 		id = 			null;
		private MyString 	readAble = 	null;

		ConditionTypesLeft(Enum id, MyString readable) {
			this.id = id.toString();
			this.readAble = readable;
		}

		
		public String get_ReadableTranslated() {
			return this.readAble.CTrans();
		}

		public String get_DBValue() {
			return this.id;
		}
	}

	
	public static String[][] get_ConditionTypesLeftDrowDownArray() throws myException {
		String[][] cRueck = new String[FK_CONST.ConditionTypesLeft.values().length][2];
		
		int i=0;
		for (FK_CONST.ConditionTypesLeft oVal: FK_CONST.ConditionTypesLeft.values()) {
			cRueck[i][0]=oVal.get_ReadableTranslated();
			cRueck[i][1]=oVal.get_DBValue();
			i++;
		}
		
		return bibARR.add_emtpy_db_value_inFront(cRueck);
		
	}

	public static HashMap<String, String> get_hmTypesLeft() {
		HashMap<String, String> hm = new HashMap<String, String>();
		for (FK_CONST.ConditionTypesLeft oVal: FK_CONST.ConditionTypesLeft.values()) {
			hm.put(oVal.get_DBValue(), oVal.get_ReadableTranslated());
		}
		return hm;
	}

	
	
	
	
// ---------------------------------------------------------------------------------	
	
	
	
	public static enum ConditionTypesRight {
		FIELD(_FilterConstants.LEFT.FIELD, 	new MyE2_String("Datenbank-Feld")),
		CONST(_FilterConstants.LEFT.CONST, 	new MyE2_String("Konstante")),
		SET("SET", 	new MyE2_String("Konstanten-Menge")),
		SQL("SQL", 		new MyE2_String("SQL-Query"));
		
		private String 		id = 			null;
		private MyString 	readAble = 	null;

		ConditionTypesRight(Enum id, MyString readable) {
			this.id = id.toString();
			this.readAble = readable;
		}

		ConditionTypesRight(String id, MyString readable) {
			this.id = id.toString();
			this.readAble = readable;
		}

		
		public String get_ReadableTranslated() {
			return this.readAble.CTrans();
		}

		public String get_DBValue() {
			return this.id;
		}
		
		
	}
	
	
	public static String[][] get_ConditionTypesRightDrowDownArray() throws myException {
		String[][] cRueck = new String[FK_CONST.ConditionTypesRight.values().length][2];
		
		int i=0;
		for (FK_CONST.ConditionTypesRight oVal: FK_CONST.ConditionTypesRight.values()) {
			cRueck[i][0]=oVal.get_ReadableTranslated();
			cRueck[i][1]=oVal.get_DBValue();
			i++;
		}
		return bibARR.add_emtpy_db_value_inFront(cRueck);
	}

	
	public static HashMap<String, String> get_hmTypesRight() {
		HashMap<String, String> hm = new HashMap<String, String>();
		for (FK_CONST.ConditionTypesRight oVal: FK_CONST.ConditionTypesRight.values()) {
			hm.put(oVal.get_DBValue(), oVal.get_ReadableTranslated());
		}
		return hm;
	}
	
	/** Returns an array of database columns for comparison */
	public static String[][] get_DatabaseComparisonColumnsArray() throws myException {
		SELECT sel = DBUtil.returnEntriesRawSQL(null, 0, true);
		String sql = sel.toString();
		HashMap<String, MyMetaFieldDEF> ret; 
		try {
			ret = bibDB.getNativeFieldnames(sql, 0, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = new HashMap<String, MyMetaFieldDEF>(); 
		}
		
		List<String> columns = new ArrayList<String>();
		Iterator<String> it = ret.keySet().iterator();
		int i = 0;
		//Collections.sort(Collections.ret.keySet());
		while(it.hasNext()) {
			//answer.add(it.next());
			String key = it.next();
			
			String fieldName = ret.get(key).get_FieldName().toLowerCase();
//TODO: Die folgende Zeile funktioniert nicht wegen einer Beschränkung von JDBC (2015-09-25, nilsandre) 
//			String fieldName = ret.get(key).get_TableName()+"."+ ret.get(key).get_FieldName().toLowerCase();
			if (!columns.contains(fieldName)) {
				i++;
//				System.out.println("key is ="+key+", "+ret.get(key).get_FieldLabel()+","+ret.get(key).get_FieldName());
				columns.add(fieldName);
			}
			//System.out.println("key is ="+key+" => '"+fieldName+"'");
			
		}
		Collections.sort(columns);
		String[][] answer = new String[i][2];
		i = 0;
		for (String col : columns) {
			answer[i][0] = col; 
			answer[i][1] = col;
			i++;
		}
		return bibARR.add_emtpy_db_value_inFront(answer);
	}
	
}
