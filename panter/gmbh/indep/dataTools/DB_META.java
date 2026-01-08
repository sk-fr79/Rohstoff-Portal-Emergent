package panter.gmbh.indep.dataTools;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;



/*
 * infos zu querys:
 * select * from SYS.USER_IND_COLUMNS    alle indizierten spalten
 */


/*
 * hilfsklasse, das die unterschiedlichen schreibweisen von SAP-DB und Oracle (und anderen) beruecksichtigt
 */
public class DB_META 
{
	public static final String DB_ORA = "ORACLE";
	public static final String DB_SAP = "SAPDB";
	
	public static String BASIC_TYPE_TEXT = "BASIC_TYPE_TEXT";
	public static String BASIC_TYPE_DATUM = "BASIC_TYPE_DATUM";
	public static String BASIC_TYPE_NUMMER = "BASIC_TYPE_NUMMER";

	/*
	 * hinweis: long in oracle = MEMO (wird beim abfrage von feld-default in der datadefinition benutzt)
	 */
	
	public static Vector<String> vDB_NUMBER_TYPES = bibALL.get_Vector("FLOAT", "DOUBLE", "FIXED", "INTEGER","SMALLINT", "NUMBER",null, null);
	public static Vector<String> vDB_TEXT_TYPES = bibALL.get_Vector("VARCHAR", "VARCHAR2", "CHAR", "NVARCHAR","NVARCHAR2", "NCHAR","ROWID", "LONG");
	public static Vector<String> vDB_DATE_TYPES = bibALL.get_Vector("DATE", "DATETIME", "TIMESTAMP", null);
	
	
	/*
	 * abfrage-teilstring fuer oracle zum ermitteln eine zeitstempel-strings bis in millisekundenbereich
	 */
	public static String  ORA_TIMESTAMP_MILLISECS = "TO_CHAR(SYSTIMESTAMP,'YYYYMMDDHH24MISSFF4')";
	
	public static String  ORA_SESSION_QUERY = "SYS_CONTEXT('USERENV','SESSIONID')";
	public static String  ORA_SESSION_QUERY_COMPLETE = "SELECT SYS_CONTEXT('USERENV','SESSIONID') FROM DUAL";
	
	
	public static String get_cColumnTYPE_BASIC(String cTyp) throws myException
	{
		if (DB_META.vDB_NUMBER_TYPES.contains(cTyp.toUpperCase()))
        {
			return DB_META.BASIC_TYPE_NUMMER;
        }
		else if (DB_META.vDB_DATE_TYPES.contains(cTyp.toUpperCase()))
	    {
			return DB_META.BASIC_TYPE_DATUM;
	    }
		else if (DB_META.vDB_TEXT_TYPES.contains(cTyp.toUpperCase()))
	    {
			return DB_META.BASIC_TYPE_TEXT;
	    }
		else
			throw new myException(DB_META.class.getName()+":get_cColumnTYPE_BASIC:Error define Fieldtyp !");
	}


	
	
	public static String get_SessionQuery(String cDB_TYPE,String cOWNER) throws myException
	{
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			// cRueck = "SELECT MAX(SESSION) from domain.connectedusers";  >=7.6
			cRueck = "SELECT MAX(SESSION) from domain.show_user_connected";   // 7.3
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			cRueck = "SELECT MAX(SID) FROM  v$session where username='"+cOWNER.toUpperCase()+"' and status = 'ACTIVE'";
		}
		else
		{
			throw new myException(DB_META.class.getName()+":get_SessionQuery:Error querying Session !");
		}
		return cRueck;
	}
	

	

	
	/**
	 * 
	 * @param cDB_TYPE
	 * @param cOWNER
	 * @param With_JD_Tables
	 * @param bDoubleRows
	 * @return
	 * @throws myException
	 */
	public static String get_TablesQuery(String cDB_TYPE,String cOWNER, boolean With_JD_Tables, boolean bDoubleRows) throws myException
	{
		
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			
			if (With_JD_Tables)
			{
				cRueck= "SELECT TABLENAME "+(bDoubleRows?",TABLENAME":"")+" FROM TABLES WHERE " +
							" OWNER='" + cOWNER + "' AND " +
							" TYPE='TABLE' ORDER BY TABLENAME";
				
			}
			else
			{
				cRueck= "SELECT TABLENAME "+(bDoubleRows?",TABLENAME":"")+" FROM TABLES WHERE " +
							" OWNER='" + cOWNER + "' AND " +
							" TYPE='TABLE' AND " +
							" (TABLENAME LIKE 'JT%' OR TABLENAME LIKE 'jt%') ORDER BY TABLENAME";
			}
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			if (With_JD_Tables)
			{
				cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES " +
							" ORDER BY TABLE_NAME DESC";
			}
			else
			{
				cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE " +
							" (TABLE_NAME LIKE 'JT%' OR TABLE_NAME LIKE 'jt%') ORDER BY TABLE_NAME DESC";
			}
		}
		else
		{
			throw new myException(DB_META.class.getName()+":get_SessionQuery:Error querying Tablenames !");
		}
		
		return cRueck;

	}
	
	
	//2018-07-20: neue variante fuer oracle, nur jt oder jd
	/**
	 * 
	 * @param cDB_TYPE
	 * @param cOWNER
	 * @param With_JD_Tables
	 * @param bDoubleRows
	 * @return
	 * @throws myException
	 */
	public static String get_TablesQuery_JT_and_JD(String cDB_TYPE,String cOWNER, boolean With_JD_Tables, boolean bDoubleRows) throws myException	{
		
		String cRueck = "";
		
        if (cDB_TYPE.equals(DB_META.DB_ORA)){
			if (With_JD_Tables)	{
				cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE (UPPER(TABLE_NAME) LIKE 'JD%' OR  UPPER(TABLE_NAME) LIKE 'JT%') "+
							" ORDER BY TABLE_NAME DESC";
			} else {
				cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE (UPPER(TABLE_NAME) LIKE 'JT%') "+
							" ORDER BY TABLE_NAME DESC";
			}
		} else {
			throw new myException(DB_META.class.getName()+":get_SessionQuery:Error querying Tablenames !");
		}
		
		return cRueck;
	}

	
	
	/**
	 * 
	 * @param cDB_TYPE
	 * @param With_JD_Tables
	 * @param bDoubleRows
	 * @return
	 * @throws myException
	 */
	public static String get_TablesQuery_NG(String cDB_TYPE, boolean With_JD_Tables, boolean bDoubleRows) throws myException
	{
		
		String cRueck = "";
		
		if (With_JD_Tables)
		{
			cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE " +
					" (TABLE_NAME LIKE 'JT%' OR TABLE_NAME LIKE 'jt%' OR TABLE_NAME LIKE 'JD%' OR TABLE_NAME LIKE 'jd%') ORDER BY TABLE_NAME ASC";
		}
		else
		{
			cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE " +
						" (TABLE_NAME LIKE 'JT%' OR TABLE_NAME LIKE 'jt%') ORDER BY TABLE_NAME ASC";
		}
		
		return cRueck;

	}

	
	public static Vector<String>  get_ORA_FIELDS(String cTabName)
	{
		return bibVECTOR.get_VectorFromArray(
				bibDB.EinzelAbfrageInArray("SELECT COLUMN_NAME  FROM USER_TAB_COLUMNS  WHERE UPPER(TABLE_NAME) =UPPER("+bibALL.MakeSql(cTabName)+") ORDER BY COLUMN_NAME"));
	}
	
	

	
	public static String get_TablesQuerySort_A_to_Z(String cDB_TYPE,String cOWNER, boolean With_JD_Tables, boolean bDoubleRows) throws myException
	{
		
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			
			if (With_JD_Tables)
			{
				cRueck= "SELECT TABLENAME "+(bDoubleRows?",TABLENAME":"")+" FROM TABLES WHERE " +
							" OWNER='" + cOWNER + "' AND " +
							" TYPE='TABLE' ORDER BY TABLENAME";
				
			}
			else
			{
				cRueck= "SELECT TABLENAME "+(bDoubleRows?",TABLENAME":"")+" FROM TABLES WHERE " +
							" OWNER='" + cOWNER + "' AND " +
							" TYPE='TABLE' AND " +
							" (TABLENAME LIKE 'JT%' OR TABLENAME LIKE 'jt%') ORDER BY TABLENAME";
			}
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			if (With_JD_Tables)
			{
				cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES " +
							" ORDER BY TABLE_NAME ASC";
			}
			else
			{
				cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE " +
							" (TABLE_NAME LIKE 'JT%' OR TABLE_NAME LIKE 'jt%') ORDER BY TABLE_NAME ASC";
			}
		}
		else
		{
			throw new myException(DB_META.class.getName()+":get_SessionQuery:Error querying Tablenames !");
		}
		
		return cRueck;

	}

	
	
	/**
	 * 
	 * @param cOWNER
	 * @param bDoubleRows
	 * @return
	 * @throws myException
	 */
	public static String get_TablesQuerySort_A_to_Z_NUR_JT_TABLES(String cOWNER,  boolean bDoubleRows) throws myException
	{
		
		String cRueck = "";
		
		cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE UPPER(TABLE_NAME) LIKE 'JT%'" +
						" ORDER BY TABLE_NAME ASC";
		
		return cRueck;

	}

	
	/**
	 * 
	 * @param cOWNER
	 * @param bDoubleRows
	 * @return
	 * @throws myException
	 */
	public static String get_TablesQuerySort_A_to_Z_NUR_JD_TABLES(String cOWNER,  boolean bDoubleRows) throws myException
	{
		
		String cRueck = "";
		
		cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE UPPER(TABLE_NAME) LIKE 'JD_%'" +
						" ORDER BY TABLE_NAME ASC";
		
		return cRueck;

	}

	
	/**
	 * 
	 * @param cOWNER
	 * @param bDoubleRows
	 * @return
	 * @throws myException
	 */
	public static String get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(String cOWNER,  boolean bDoubleRows) throws myException
	{
		
		String cRueck = "";
		
		cRueck = "SELECT TABLE_NAME "+(bDoubleRows?",TABLE_NAME AS TAB2":"")+" FROM SYS.USER_TABLES WHERE (UPPER(TABLE_NAME) LIKE 'JD%' OR UPPER(TABLE_NAME) LIKE 'JT%')" +
						" ORDER BY TABLE_NAME ASC";
		
		
		;
		
		return cRueck;

	}

	
	
	/**
	 * 
	 * @param cOWNER
	 * @param With_JD_Tables
	 * @param bDoubleRows
	 * @param bWertSpalteOhnePrefix (dann wird ADRESSE statt JT_ADRESSE in der zweiten Spalte zurueckgegeben)
	 * @return
	 * @throws myException
	 */
	public static String get_TablesQuerySort_A_to_Z(String cOWNER, boolean With_JD_Tables, boolean bDoubleRows, boolean bWertSpalteOhnePrefix) throws myException
	{
		
		String cRueck = "";
		
		if (With_JD_Tables)
		{
			cRueck = "SELECT TABLE_NAME "+(bDoubleRows?","+(bWertSpalteOhnePrefix?"SUBSTR(TABLE_NAME,4)":"TABLE_NAME")+" AS TAB2":"")+
						" FROM SYS.USER_TABLES WHERE " +
						" UPPER(TABLE_NAME) like 'JT%' OR UPPER(TABLE_NAME) like 'JD%'" +
						" ORDER BY TABLE_NAME ASC";
		}
		else
		{
			cRueck = "SELECT TABLE_NAME "+(bDoubleRows?","+(bWertSpalteOhnePrefix?"SUBSTR(TABLE_NAME,4)":"TABLE_NAME")+" AS TAB2":"")+
						" FROM SYS.USER_TABLES WHERE " +
						" UPPER(TABLE_NAME) LIKE 'JT%'  ORDER BY TABLE_NAME ASC";
		}
		
		return cRueck;

	}

	
	
	
	
	public static Vector<String> get_vTableNamesOfSchema(String WhereBlockOhneWhere, boolean bOrderByName)
	{
		String cQuery = "SELECT TABLE_NAME  FROM SYS.USER_TABLES ";
		
		if (S.isFull(WhereBlockOhneWhere))
		{
			cQuery = cQuery + " WHERE "+WhereBlockOhneWhere;
		}
				
		if (bOrderByName)
		{
			cQuery = cQuery + "  ORDER BY TABLE_NAME ASC";
		}

		Vector<String> vRueck = bibALL.get_VectorAusArrayColumn(bibDB.EinzelAbfrageInArray(cQuery,false), 0);
		
		return vRueck;
	}
	
	
	
	
	/**
	 * Gibt die Spalten-Namen der angegebenen Tabelle sortiert zurück
	 * @param cDB_TYPE
	 * @param cOWNER
	 * @param Tablename
	 * @param bDoubleRows, Es wird der Spaltenname in 2 Spalten zurückgebgeben
	 * @return
	 * @throws myException
	 */
	public static String get_ColumnsQuerySort_A_to_Z(String cDB_TYPE,String cOWNER, String cTablename, boolean bDoubleRows) throws myException
	{
		
		String cRueck = "";
		
		
		if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			{
				cRueck = "SELECT COLUMN_NAME "+(bDoubleRows?",COLUMN_NAME AS TAB2":"")+" FROM SYS.USER_TAB_COLUMNS WHERE " +
							" TABLE_NAME = "+bibALL.MakeSql(cTablename) + " ORDER BY COLUMN_NAME ASC";
			}
		}
		else
		{
			throw new myException(DB_META.class.getName()+":get_SessionQuery:Error querying Columnnames !");
		}
		
		return cRueck;

	}

	

	/**
	 * 
	 * @author martin
	 * @date 06.11.2018
	 *
	 * @param cTablename
	 * @param bDoubleRows
	 * @return s sqlQuery for tableColumns of an Oracle-Table
	 * @throws myException
	 */
	public static String getColumnsOraTableQuery(String cTablename, boolean bDoubleRows) throws myException {
		return "SELECT COLUMN_NAME "+(bDoubleRows?",COLUMN_NAME AS TAB2":"")+" FROM SYS.USER_TAB_COLUMNS WHERE " +
				" TABLE_NAME = "+bibALL.MakeSql(cTablename) + " ORDER BY COLUMN_NAME ASC";
	}

	public static String[][] getColumnsOra(String tabName) throws myException {
		return bibDB.EinzelAbfrageInArray(DB_META.getColumnsOraTableQuery(tabName, true));
	}
	
	
	public static String[] getColumnsOraSingle(String tabName) throws myException {
		String[][] s =  bibDB.EinzelAbfrageInArray(DB_META.getColumnsOraTableQuery(tabName, false));
		
		String[] ret = new String[s.length];
		
		for (int i=0;i<s.length;i++) {
			ret[i]=s[i][0];
		}
		return ret;
	}
	
	
	public static Vector<String>  get_vFields(String cTableName, String OWNER) throws myException
	{
		return bibALL.get_VectorAusArrayColumn(bibDB.EinzelAbfrageInArray(DB_META.get_ColumnsQuerySort_A_to_Z(DB_META.DB_ORA,OWNER,cTableName,false)), 0);
	}
	
	
	
	/**
	 * 
	 * @param cTablename
	 * DATA_PRECISION,DATA_SCALE,DATA_DEFAULT koennen null werden
	 * @return 
	 */
	public static String get_FieldsQueryOracle(String cTablename)
	{
		return "SELECT TABLE_NAME,COLUMN_NAME,DATA_TYPE,CHAR_LENGTH,NULLABLE,DATA_PRECISION,DATA_SCALE,DATA_DEFAULT" +
				" FROM SYS.USER_TAB_COLUMNS WHERE TABLE_NAME="+bibALL.MakeSql(cTablename);
	}
	
	

	/**
	 * Gibt den SELECT-String zum ermitteln der COLUMN_ID der Spalte zurück
	 * @param cTablename
	 * @param cColumname
	 * @return
	 */
	public static String get_FieldID(String cTablename, String cColumname, boolean TableNameHasPrefix)
	{
		return "SELECT COLUMN_ID FROM SYS.USER_TAB_COLUMNS WHERE "+ (TableNameHasPrefix?"TABLE_NAME":"SUBSTR(TABLE_NAME,4)")+"="+bibALL.MakeSql(cTablename) + " AND COLUMN_NAME = " + bibALL.MakeSql(cColumname);
	}

	
	/**
	 * Gibt den SELECT-String zum ermitteln der COLUMN_ID der Spalte zurück
	 * @param cTablename
	 * @param cColumname
	 * @return
	 */
	public static String get_TableID(String cTablename, boolean TableNameHasPrefix)
	{
		String cSQL = null;
		
		if (TableNameHasPrefix)
		{
			cSQL= "SELECT OBJECT_ID FROM SYS.DBA_OBJECTS WHERE UPPER(OBJECT_NAME)=UPPER("+bibALL.MakeSql(cTablename) + ") AND UPPER(OWNER) = UPPER(" + bibALL.MakeSql(bibE2.cTO())+")";
		}
		else
		{
			cSQL= "SELECT OBJECT_ID FROM SYS.DBA_OBJECTS WHERE (UPPER(OBJECT_NAME)=UPPER("+bibALL.MakeSql("JT_"+cTablename) + ") OR UPPER(OBJECT_NAME)=UPPER("+bibALL.MakeSql("JD_"+cTablename) + ")) AND UPPER(OWNER) = UPPER(" + bibALL.MakeSql(bibE2.cTO())+")";
		}
		
		return cSQL;
	}

	
	
	/**
	 * Gibt den SELECT-String zum ermitteln des DATA_TYPE der Spalte zurück
	 * @param cTablename
	 * @param cColumname
	 * @return
	 */
	public static String get_FieldType(String cTablename, String cColumname)
	{
		return "SELECT DATA_TYPE FROM SYS.USER_TAB_COLUMNS WHERE TABLE_NAME="+bibALL.MakeSql(cTablename) + " AND COLUMN_NAME = " + bibALL.MakeSql(cColumname);
	}

	
	public static String get_tStampString(String cDB_TYPE)
	{
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			cRueck= "TIMESTAMP";
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			cRueck = "SYSDATE";
		}
		else
		{
			cRueck = "SYSDATE";
		}
		
		return cRueck;
	}
	
	
	public static String get_MonthDayStamp(String cDB_TYPE)  throws myException
	{
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			throw new myException(cDB_TYPE+" is not supported !!");
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			cRueck = "SELECT TO_CHAR(SYSDATE,'YYYYMM') FROM DUAL";
		}
		else
		{
			throw new myException(cDB_TYPE+" is not supported !!");
		}
		
		return cRueck;
	}
	
	
	
	/**
	 * 
	 * @param cDB_TYPE
	 * @return s Tages-Stamp wie z.B.  20101231
	 * @throws myException
	 */
	public static String get_DayStamp(String cDB_TYPE)  throws myException
	{
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			throw new myException(cDB_TYPE+" is not supported !!");
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			cRueck = "SELECT TO_CHAR(SYSDATE,'YYYYMMDD') FROM DUAL";
		}
		else
		{
			throw new myException(cDB_TYPE+" is not supported !!");
		}
		
		return cRueck;
	}
	

	
	public static String get_EmptyResultQuery(String cDB_TYPE, String cTableOwner, String cTableName) throws myException
	{
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			cRueck= "SELECT * FROM "+cTableOwner+"."+cTableName+" WHERE FALSE";
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			// falls als Tabelle ein Subselect angegeben wurde, hier die Abfrage aendern 
			if(cTableName.toUpperCase().contains("SELECT"))
			{
				cRueck = "SELECT * FROM " +cTableName+ " WHERE 1=2";
			} else {
				cRueck = "SELECT * FROM "+cTableOwner+"."+cTableName+" WHERE ID_"+cTableName.substring(3)+"=-1";
			}
		}
		else
		{
			throw new myException(DB_META.class.getName()+":get_EmptyResultQuery:Error querying Resultset "+cTableName);
		}
		
		return cRueck;
	}

		
	
	public static String get_SequenceBuilder(String cDB_TYPE, String cTableOwner, String cSeqCompleteName, String cStartValue) throws myException
	{
		String cRueck = "";
		
		if (cDB_TYPE.equals(DB_META.DB_SAP))
		{
			cRueck= "CREATE SEQUENCE "+cTableOwner+"."+cSeqCompleteName+"  START WITH  "+cStartValue+"  INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999   NOCYCLE CACHE 1"; 
		}
		else if (cDB_TYPE.equals(DB_META.DB_ORA))
		{
			cRueck= "CREATE SEQUENCE "+cTableOwner+"."+cSeqCompleteName+"  START WITH  "+cStartValue+"  INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999 NOCACHE"; 
		}
		else
		{
			throw new myException(DB_META.class.getName()+":get_EmptySequenceBuilder:Error querying Resultset "+cSeqCompleteName);
		}
		
		return cRueck;
	}

		

	
	public static String get_SQL_CountLocks_Oracle_Session()
	{
		return "SELECT COUNT(*) FROM DBA_DML_LOCKS WHERE SESSION_ID="+bibALL.getSessionValue("DBSESSION");
	}
	
	public static String get_SQL_CountLocks_Oracle_ALL_Sessions()
	{
		return "SELECT COUNT(*) FROM DBA_DML_LOCKS";
	}
	
	
	
	/**
	 * 
	 * @param cTableName
	 * @return s true wenn tabelle vorhanden, false wenn nicht, sonst exception 
	 * @throws myException
	 */
	public static boolean Check_Table_exists(String cTableName) throws myException{

		boolean bRueck = false;
		
		String cQuery = "SELECT COUNT(TABLE_NAME) FROM SYS.USER_TABLES WHERE UPPER(TABLE_NAME)='"+cTableName.trim().toUpperCase()+"'";
		
		String cValue = bibDB.EinzelAbfrage(cQuery);
		
		if (cValue.trim().equals("0")) {
			bRueck = false;
		} else if (cValue.trim().equals("1")) {
			bRueck = true;
		} else {
			throw new myException("DB_META:Check_Table_exists:Error checking tablename ..."+cTableName +" !!");
		}
		return bRueck;
	}
	

	/**
	 * 
	 * @param cTableName
	 * @return s true wenn tabelle temporaer, false wenn nicht, sonst exception 
	 * @throws myException
	 */
	public static boolean Check_Table_is_TEMP_TABLE(String cTableName) throws myException{

		if (DB_META.Check_Table_exists(cTableName)) {
			
			boolean bRueck = false;
			
			String cQuery = "SELECT COUNT(TABLE_NAME) FROM SYS.USER_TABLES WHERE UPPER(TABLE_NAME)='"+cTableName.trim().toUpperCase()+"' AND UPPER(NVL(DURATION,'-'))='SYS$SESSION'";
			
			String cValue = bibDB.EinzelAbfrage(cQuery);
			
			if (cValue.trim().equals("0")) {
				bRueck = false;
			} else if (cValue.trim().equals("1")) {
				bRueck = true;
			} else {
				throw new myException("DB_META:Check_Table_is_TEMP_TABLE:Error checking tablename ..."+cTableName +" !!");
			}
			return bRueck;
		} else {
			throw new myException("DB_META:Check_Table_is_TEMP_TABLE:Table does not exist ..."+cTableName +" !!");
		}
	}
	
	

	
}
