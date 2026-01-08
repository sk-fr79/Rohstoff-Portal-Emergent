package panter.gmbh.indep.dataTools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class bibDB {

	public static String EinzelAbfrage(String cQuery) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String cRueck = oDB.EinzelAbfrage(cQuery);
		bibALL.destroy_myDBToolBox(oDB);

		return cRueck;
	}

	public static String EinzelAbfrage(String cQuery, String cNullValue,
			String cErrorNotFoundValue, String cErrorOpenValue) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String cRueck = oDB.EinzelAbfrage(cQuery, cNullValue,
				cErrorNotFoundValue, cErrorOpenValue);
		bibALL.destroy_myDBToolBox(oDB);

		return cRueck;
	}

	public static String EinzelAbfrage(String cQuery, String cNullValue,
			String cErrorNotFoundValue, String cErrorOpenValue,
			boolean bZusatzFelder, boolean bErsetzeTableView) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox(bZusatzFelder,
				bErsetzeTableView);
		String cRueck = oDB.EinzelAbfrage(cQuery, cNullValue,
				cErrorNotFoundValue, cErrorOpenValue);
		bibALL.destroy_myDBToolBox(oDB);

		return cRueck;
	}

	public static String EinzelAbfrage(String cQuery, boolean bZusatzFelder,
			boolean bErsetzeTableView) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox(bZusatzFelder,
				bErsetzeTableView);
		String cRueck = oDB.EinzelAbfrage(cQuery);
		bibALL.destroy_myDBToolBox(oDB);

		return cRueck;
	}

	public static String[][] EinzelAbfrageInArray(String cQuery) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArray(cQuery);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	/**
	 * 20180409: abfrage mit prepared-statement
	 * @param cQuery
	 * @return
	 */
	public static String[][] EinzelAbfrageInArray(SqlStringExtended cQuery) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArray(cQuery);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	/**
	 * Prepared Statement mit null-Wert definition
	 * @author manfred
	 * @date 08.05.2018
	 *
	 * @param cQuery
	 * @param cNullWert
	 * @return
	 */
	public static String[][] EinzelAbfrageInArray(SqlStringExtended cQuery, String cNullWert){
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArray(cQuery,cNullWert);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}
	
	
	public static String[][] EinzelAbfrageInArray(String cQuery, 	MyConnection oConn) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox(oConn);
		String[][] cRueck = oDB.EinzelAbfrageInArray(cQuery);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	public static String[][] EinzelAbfrageInArray(String cQuery,	boolean bErsetzeTableWithViews) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArray(cQuery, bErsetzeTableWithViews);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	public static String[][] EinzelAbfrageInArray(String cQuery, String cNullWert) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArray(cQuery, cNullWert);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	public static String[][] EinzelAbfrageInArray(String cQuery,	String cNullWert, int iMaxResults) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArray(cQuery, cNullWert,
				iMaxResults);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	public static String[][] EinzelAbfrageInArrayFormatiert(String cQuery,
			String cNullWert) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArrayFormatiert(cQuery,
				cNullWert);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	public static String[][] EinzelAbfrageInArrayFormatiert(String cQuery) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArrayFormatiert(cQuery);
		bibALL.destroy_myDBToolBox(oDB);
		return cRueck;
	}

	/**
	 * @param vSQLStack
	 * @param bCommit
	 * @return
	 */
	public static MyE2_MessageVector ExecMultiSQLVector(Vector<String> vSQLStack, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		MyE2_MessageVector VRueck = oDB.ExecMultiSQLVector(vSQLStack, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return VRueck;
	}

	
	/**
	 * @param vSQLStack
	 * @param bCommit
	 * @param v_transporter  (leerer Vector<Integer> fuer die rueckgabe der zahl der aenderungen)
	 * @return
	 */
	public static MyE2_MessageVector ExecMultiSQLVector(Vector<String> vSQLStack, Vector<Integer> v_transporter, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		MyE2_MessageVector VRueck = oDB.ExecMultiSQLVector(vSQLStack, bCommit);
		v_transporter.add(oDB.get_count_last_updates_executed());
		bibALL.destroy_myDBToolBox(oDB);
		return VRueck;
	}
	
	
	/**
	 * @param vSQLStack
	 * @param bCommit
	 * @return
	 */
	public static MyE2_MessageVector ExecMultiSQLVector(Vector<String> vSQLStack, boolean bCommit,String[][] arrayZusatzFelder) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		oDB.setZusatzFelder(arrayZusatzFelder);
		MyE2_MessageVector VRueck = oDB.ExecMultiSQLVector(vSQLStack, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return VRueck;
	}

	/**
	 * @param cSQL
	 * @param bCommit
	 * @return
	 */
	public static boolean ExecSQL(String cSQL, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.ExecSQL(cSQL, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}

	/**
	 * @param cSQL
	 *            !!!! komplett an der infrastruktur vorbei !!! 2011-09-06:
	 *            neuer raw-aufruf VORSICHT !!!!!
	 * @param bCommit
	 * @return
	 */
	public static boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(
			String cSQL, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(
				cSQL, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}
	
	
	/**
	 * @param v_sql
	 *            !!!! komplett an der infrastruktur vorbei !!! 2015-11-18:
	 *            neuer raw-aufruf VORSICHT !!!!!
	 * @param bCommit
	 * @return MyE2_MessageVector
	 */
	public static MyE2_MessageVector  ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(Vector<String> v_sql, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		MyE2_MessageVector mv = oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(v_sql, bCommit, new Vector<>(), new Vector<>());
		bibALL.destroy_myDBToolBox(oDB);
		return mv;
	}
	

	/**
	 * @param v_sql
	 * @param bCommit
	 * @param v_intCounter
	 * @param v_tabTriggersToDisable
	 *            !!!! komplett an der infrastruktur vorbei !!! 2015-11-18:, neue parameter Vector<Integer> v_intCounter, Vector<_TAB> v_tabTriggersToDisable ab 2016-07-20
	 *            neuer raw-aufruf VORSICHT !!!!!
	 * @param bCommit
	 * @return MyE2_MessageVector
	 */
	public static MyE2_MessageVector  ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(Vector<String> v_sql, boolean bCommit, Vector<Integer> v_intCounter, Vector<_TAB> v_tabTriggersToDisable) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		MyE2_MessageVector mv = oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(v_sql, bCommit,  v_intCounter, v_tabTriggersToDisable);
		bibALL.destroy_myDBToolBox(oDB);
		return mv;
	}

	
	
	
	
	/**
	 * @param cSQL
	 *            !!!! komplett an der infrastruktur vorbei !!! 2014-04-14:
	 *            neuer raw-aufruf VORSICHT !!!!!
	 * @param bCommit
     * @param vUpdateCount ist Vector aus Integers, der an Stelle 0 die summe der aenderungen mitfuehrt
	 * @return
	 */
	public static boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(String cSQL, boolean bCommit, Vector<Integer> vUpdateCount) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL, bCommit, vUpdateCount);
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}

	
	
	/**
	 * @param Vector<String> vSQL
	 *            !!!! komplett an der infrastruktur vorbei !!! 2014-03-25:
	 *            neuer raw-aufruf VORSICHT !!!!!
	 * @param bCommit
	 * @return
	 */
	public static boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(Vector<String> vSQL, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		
		boolean bRueck = oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(vSQL, bCommit);
		
		bibALL.destroy_myDBToolBox(oDB);
		
		return bRueck;
	}
	
	
	
	
	
	

	/**
	 * @param vSQL
	 * @param bCommit
	 * @return
	 */
	public static boolean ExecSQL(Vector<String> vSQL, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.ExecSQL(vSQL, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}
	
	

	/**
	 * @param sqlExt
	 * @param bCommit
	 * @return
	 */
	public static boolean ExecSQL(SqlStringExtended sqlExt, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sqlExt, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}
	
	
	
	/**
	 * führt die Statements im Batch-Mode aus. ( vgl: ExecSQL(Vector,boolean) ->führt die Statements einzeln durch) 
	 * @param vSQL
	 * @param bCommit
	 * @return
	 */
	public static boolean ExecSQL_in_Batch(Vector<String> vSQL, boolean bCommit) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.ExecSQL_in_Batch(vSQL, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}
	
	

	public static boolean Commit() {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.Commit();
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}

	public static boolean Rollback() {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		boolean bRueck = oDB.Rollback();
		bibALL.destroy_myDBToolBox(oDB);
		return bRueck;
	}

	public static String get_NextSequenceValueOfTable(String cTAB_NAME) 	throws myException {
		Vector<String> vValues = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_"
				+ cTAB_NAME.substring(3), 1);

		return vValues.get(0);

	}

	public static String get_NextSequenceValueOfTable(_TAB tab) 	throws myException {
		Vector<String> vValues = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_"+ tab.baseTableName(), 1);
		return vValues.get(0);
	}

	
	
	
	public static String get_ORACLE_SESSION_USED_IN_JD_DB_LOG() {
		return bibDB.EinzelAbfrage(DB_META.ORA_SESSION_QUERY_COMPLETE);
	}

	// 2012-02-06: gibt eine hashmap anteilig zurueck (nur die felder, die nicht
	// automatisch generiert werden
	public static HashMap<String, MyRECORD.DATATYPES> get_HM_RelevantFields(
			HashMap<String, MyRECORD.DATATYPES> hmOriginal) {
		HashMap<String, MyRECORD.DATATYPES> hmRueck = new HashMap<String, MyRECORD.DATATYPES>();

		Iterator<String> oIter = hmOriginal.keySet().iterator();

		Vector<String> vNot = bibVECTOR.get_Vector("LETZTE_AENDERUNG",
				"GEAENDERT_VON", "ID_MANDANT", "ERZEUGT_VON", "ERZEUGT_AM");

		while (oIter.hasNext()) {
			String cKey = oIter.next();

			if (vNot.contains(cKey)) {
				continue;
			}

			hmRueck.put(cKey, hmOriginal.get(cKey));
		}

		return hmRueck;
	}

	// 2012-02-06: gibt eine hashmap anteilig zurueck (nur die felder, die
	// uebergeben werden
	public static HashMap<String, MyRECORD.DATATYPES> get_HM_FieldsWithName(
			HashMap<String, MyRECORD.DATATYPES> hmOriginal,
			Vector<String> vNames) {
		HashMap<String, MyRECORD.DATATYPES> hmRueck = new HashMap<String, MyRECORD.DATATYPES>();

		Iterator<String> oIter = hmOriginal.keySet().iterator();

		while (oIter.hasNext()) {
			String cKey = oIter.next();

			if (vNames.contains(cKey)) {
				hmRueck.put(cKey, hmOriginal.get(cKey));
			}
		}

		return hmRueck;
	}

	/**
	 * Liest eine evtl. vorhandene feldbeschreibung aus der datenbank
	 * 
	 * @param cTablename
	 * @param cFieldname
	 * @return
	 */
	public static String QueryFieldDescription(String cTablename,
			String cFieldname) {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck = oDB.EinzelAbfrageInArray(
				"SELECT COMMENTS FROM USER_COL_COMMENTS WHERE UPPER(TABLE_NAME)="
						+ bibALL.MakeSql(cTablename.toUpperCase()) + " AND "
						+ "UPPER(COLUMN_NAME)="
						+ bibALL.MakeSql(cFieldname.toUpperCase()), false);
		bibALL.destroy_myDBToolBox(oDB);

		if (cRueck != null && cRueck.length == 1 && cRueck[0].length == 1) {
			return cRueck[0][0];
		}

		return null;
	}

	/**
	 * 
	 * @param cText
	 * @return String fuer SQL-Query wie folgt: uebergabe = "name" , return = "CSCONVERT('name','NCHAR_CS')" 
	 */
	public static String get_Constant4SQL(String cText) {
		return "CSCONVERT('" + cText + "','NCHAR_CS')";
	}

	
	
	/**
	 * 2014-10-24: neue Methode fuer native rueckgabe
	 * @param cSqlString
	 * @param iMaxRowNumbers
	 * @param bErsetzeTableWithViews
	 * @return
	 * @throws SQLException
	 * @throws myException
	 */
	public static ArrayList<HashMap<String, Object>> get_Native_DataObjects(	String 	cSqlString, 
																		int 	iMaxRowNumbers,
																		boolean bErsetzeTableWithViews) throws SQLException, myException {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		ArrayList<HashMap<String, Object>> al_Rueck  = oDB.get_Native_DataObjects(cSqlString, iMaxRowNumbers, bErsetzeTableWithViews);
		bibALL.destroy_myDBToolBox(oDB);
				
		return al_Rueck;
	}
	
	/**
	 * 2015-01-26: neue Methode fuer native rueckgabe der Feldnamen (nilsandre)
	 * @param cSqlString
	 * @param iMaxRowNumbers
	 * @param bErsetzeTableWithViews
	 * @return
	 * @throws SQLException
	 * @throws myException
	 */
	public static HashMap<String, MyMetaFieldDEF> getNativeFieldnames(	String 	cSqlString, 
																		int 	iMaxRowNumbers,
																		boolean bErsetzeTableWithViews) throws SQLException, myException {
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		@SuppressWarnings("unused")
		ArrayList<HashMap<String, Object>> al_Rueck  = oDB.get_Native_DataObjects(cSqlString, iMaxRowNumbers, bErsetzeTableWithViews);
		
		HashMap<String, MyMetaFieldDEF> hm = oDB.get_oMetaColumnsOfResultSet();
		
		bibALL.destroy_myDBToolBox(oDB);
		return hm;
	}
	
	
	
	
	   /**
     * 2015-06-30: erzeugt das komplette statement inclusive zusatzfelder/values paaren (fuer externe nutzung)
     * @param p_sql
     * @return
     */
    public static String generate_plain_sql_Statement(String p_sql) throws SQLException, myException {
    	MyDBToolBox oDB = bibALL.get_myDBToolBox();
        String c_sql_plain = oDB.generate_plain_sql_Statement(p_sql);
        bibALL.destroy_myDBToolBox(oDB);
        return c_sql_plain;
    }
    
 
	
	/**
	 * execution of multisave-records in one transaction
	 * @author martin
	 * @date 12.02.2019
	 *
	 * @param recs
	 * @param commit
	 * @return messagevektor
	 */
    public static <T extends Rec20> MyE2_MessageVector execMultiRecSave(Collection<T> recs, boolean commit) {
    	MyDBToolBox oDB = bibALL.get_myDBToolBox();
    	MyE2_MessageVector mv = oDB.execMultiRecSave(recs,commit);
    	bibALL.destroy_myDBToolBox(oDB);
    	return mv;
    }
 


    

	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param oSqlExtended
	 * @param bErsetzeTableWithViews
	 * @return s VEK<Object[]> where Object[] represents one dataset, empty VEK on no results, null onm error
	 */
    public static VEK<Object[]> getResultLines(SqlStringExtended oSqlExtended,	boolean bErsetzeTableWithViews) {
    	MyDBToolBox oDB = bibALL.get_myDBToolBox();
    	VEK<Object[]> v = oDB.getResultLines(oSqlExtended, bErsetzeTableWithViews);
    	bibALL.destroy_myDBToolBox(oDB);
    	return v;
	}
	
    
    /**
     * 
     * @author martin
     * @date 29.10.2020
     *
     * @param oSqlExtended
     * @param bErsetzeTableWithViews
     * @return
     */
    public static VEK<DBMap> getResultMaps(SqlStringExtended oSqlExtended,	boolean bErsetzeTableWithViews) {
    	MyDBToolBox oDB = bibALL.get_myDBToolBox();
    	VEK<DBMap> v = oDB.getResultMaps(oSqlExtended, bErsetzeTableWithViews);
    	bibALL.destroy_myDBToolBox(oDB);
    	return v;
	}
	
    
    
    public static <T extends Rec20> boolean deleteRecords(Collection<T> recs, boolean commit, MyE2_MessageVector mv) {
    	boolean ret = false;
    	
    	if (recs!=null) {
	    	//zuerst feststellen, ob alle Recs in der gleichen connection liegen (und zwar im standard-umfeld
	    	VEK<Connection> connections = new VEK<Connection>()
	    			._a(bibALL.get_oConnectionNormal().get_oConnection());
	    	
	    	for (Rec20 r: recs) {
	    		if (r.get_oDB()==null) {
	    			connections._addIfNotIN(bibALL.get_oConnectionNormal().get_oConnection());
	    		} else {
	    			connections._addIfNotIN(r.get_oDB().get_MyConnection().get_oConnection());
	    		}
	    	}
	    	
	    	if (connections.size()>1) {
				ret = false;
				if (mv!=null) {
					mv._addAlarm(S.ms("Fehler: Records aus verschiedenen connections: <a65922ac-e42f-11e9-a359-2a2ae2dbcce4>"));
				}
	    	} else {
	    		
	    		try {
	    			MyE2_MessageVector mv_diag = bibMSG.getNewMV();
					for (Rec20 r: recs) {
						mv_diag._add(r.DELETE(false));
						if (mv_diag.get_bHasAlarms()) {
							break;
						}
					}
					if (mv_diag.hasAlarms()) {
						ret = false;
						mv._addAlarm(S.ms("Fehler beim Delete: <5b01a07a-e431-11e9-81b4-2a2ae2dbcce4>"));
						mv._add(mv_diag);
						if (commit) {
							bibDB.Rollback();
						}
					} else {
						if (commit) {
							if (bibDB.Commit()) {
								ret = true;
							} else {
								bibDB.Rollback();
								ret = false;
								if (mv!=null) {
									mv._addAlarm(S.ms("Fehler beim Commit: <8ff948de-e42f-11e9-81b4-2a2ae2dbcce4>"));
								}
							}
						} else {
							ret = true;
						}
					}
				} catch (myException e) {
					e.printStackTrace();
					if (commit) {
						bibDB.Rollback();
					}
					ret = false;
					if (mv!=null) {
						mv._addAlarm(S.ms("Fehler beim Commit: <7f2e43e6-e430-11e9-81b4-2a2ae2dbcce4>"));
					}
				}
	    	}
    	}
    	return ret;
    }
    
    
    /**
     * speichern aller Recs im vektor und falls commit, dann nach speichern des ganzen stacks, committen
     * @author martin
     * @date 27.03.2020
     *
     * @param recs
     * @param commit
     * @param mv
     * @return
     */
    public static boolean saveRecords(Vector<? extends Rec20> recs, boolean commit, MyE2_MessageVector mv) {
    	boolean ret = false;
    	
    	//debugging
//    	for (Rec20 r: recs) {
//			try {
//				DEBUG._print("sql: "+r.get_sql_4_save(true, mv)+"03c93690-e44c-11e9-81b4-2a2ae2dbcce4");
//			} catch (myException e) {
//				e.printStackTrace();
//			}
//    	}
    	//debugging

    	
    	if (recs!=null) {
	    	//zuerst feststellen, ob alle Recs in der gleichen connection liegen (und zwar im standard-umfeld
	    	VEK<Connection> connections = new VEK<Connection>()
	    			._a(bibALL.get_oConnectionNormal().get_oConnection());
	    	
	    	for (Rec20 r: recs) {
	    		if (r.get_oDB()==null) {
	    			connections._addIfNotIN(bibALL.get_oConnectionNormal().get_oConnection());
	    		} else {
	    			connections._addIfNotIN(r.get_oDB().get_MyConnection().get_oConnection());
	    		}
	    	}
	    	
	    	if (connections.size()>1) {
				ret = false;
				if (mv!=null) {
					mv._addAlarm(S.ms("Fehler: Records aus verschiedenen connections: <a65922ac-e42f-11e9-a359-ebd8d6ae>"));
				}
	    	} else {
	    		
	    		try {
	    			MyE2_MessageVector mv_diag = bibMSG.getNewMV();
					for (Rec20 r: recs) {
						r._SAVE(false,mv_diag);
						
						if (mv_diag.get_bHasAlarms()) {
							break;
						}
					}
					if (mv_diag.hasAlarms()) {
						ret = false;
						mv._addAlarm(S.ms("Fehler beim SAVE: <5b01a07a-e431-11e9-81b4-ebd8d6ae>"));
						mv._add(mv_diag);
						if (commit) {
							bibDB.Rollback();
						}
					} else {
						if (commit) {
							if (bibDB.Commit()) {
								ret = true;
							} else {
								bibDB.Rollback();
								ret = false;
								if (mv!=null) {
									mv._addAlarm(S.ms("Fehler beim Commit: <8ff948de-e42f-11e9-81b4-ebd8d6ae>"));
								}
							}
						} else {
							ret = true;
						}
					}
				} catch (myException e) {
					e.printStackTrace();
					if (commit) {
						bibDB.Rollback();
					}
					ret = false;
					if (mv!=null) {
						mv._addAlarm(S.ms("Fehler beim Commit: <7f2e43e6-e430-11e9-81b4-ebd8d6ae>"));
					}
				}
	    	}
    	}
    	return ret;    
    }
    
    
    
    public static String getLockingStatement() {
    	return "UPDATE "+bibE2.cTO()+".JT_LOCK SET KENNSTRING=KENNSTRING WHERE ID_MANDANT="+bibALL.get_ID_MANDANT();
    }
    
    /**
     * speichern aller Recs im vektor und falls commit, dann nach speichern des ganzen stacks, committen
     * Falls Fehlerzustand, dann erfolgt rollback, unabhaengig, ob commit = true uebergeben wurde
     * @author martin
     * @date 27.03.2020
     *
     * @param recs
     * @param withGlobalLock
     * @param commit
     * @param mv
     * @return
     */
    public static boolean saveRecords(Vector<? extends Rec20> recs, boolean withGlobalLock, boolean commit, MyE2_MessageVector mv) {
    	boolean ret = false;
    	
    	MyE2_MessageVector mvLocal = new MyE2_MessageVector();
    	
    	if (recs!=null) {
    		
	    	//zuerst feststellen, ob alle Recs in der gleichen connection liegen (und zwar im standard-umfeld
	    	VEK<Connection> connections = new VEK<Connection>()._a(bibALL.get_oConnectionNormal().get_oConnection());
	    	
	    	for (Rec20 r: recs) {
	    		if (r.get_oDB()==null) {
	    			connections._addIfNotIN(bibALL.get_oConnectionNormal().get_oConnection());
	    		} else {
	    			connections._addIfNotIN(r.get_oDB().get_MyConnection().get_oConnection());
	    		}
	    	}
	    	
	    	if (connections.size()>1) {
				ret = false;
				mvLocal._addAlarm(S.ms("Fehler: Records aus verschiedenen connections: <a65922ac-e42f-11e9-a359-ebd8d6ae>"));
	    	} else {
	    		
	    		try {
	    			
	        		if (withGlobalLock) {
	        			mvLocal._add(bibDB.ExecMultiSQLVector(new VEK<String>()._a(bibDB.getLockingStatement()), false));
	        		}
	    			if (mvLocal.isOK()) {
						for (Rec20 r: recs) {
							r._SAVE(false,mvLocal);
							
							if (mvLocal.hasAlarms()) {
								break;
							}
						}
	    			}
					if (mvLocal.hasAlarms()) {
						ret = false;
						mvLocal._addAlarm(S.ms("Fehler beim SAVE: <5b01a07a-e431-11e9-81b4-ebd8d6ae>"));
						bibDB.Rollback();
					} else {
						if (commit) {
							if (bibDB.Commit()) {
								ret = true;
							} else {
								bibDB.Rollback();
								ret = false;
								mvLocal._addAlarm(S.ms("Fehler beim Commit: <8ff948de-e42f-11e9-81b4-ebd8d6ae>"));
							}
						} else {
							ret = true;
						}
					}
				} catch (myException e) {
					e.printStackTrace();
					bibDB.Rollback();
					ret = false;
					mvLocal._addAlarm(S.ms("Fehler beim Speichern: <7f2e43e6-e430-11e9-81b4-ebd8d6ae>"));
				}
	    	}
    	}
    	
    	if (mv!=null) {
    		mv._add(mvLocal);
    	} else {
    		bibMSG.MV()._add(mvLocal);
    	}
    	return ret;    
    }
    
    
    
    
    
    /**
     * simple raw-sql-methode
     * @author martin
     * @date 29.10.2019
     *
     * @param vSql
     * @param bMitCommit
     * @return
     * @throws myException 
     */
    public static boolean execRawStatements(VEK<String> vSql, boolean bMitCommit) throws myException {
    	boolean ret = false;
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		ret = oDB.execRawStatements(vSql, bMitCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return ret;
    }
    
    
    /**
     * 
     * @author martin
     * @date 07.11.2019
     *
     * @param statements
     * @param bMitCommit
     * @return number of executed statements
     * @throws myException
     */
    public static int executeRaw(VEK<SqlStringExtended> statements, boolean commit) throws myException   {
    	int ret = 0;
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		ret += oDB.executeRaw(statements,commit);
		bibALL.destroy_myDBToolBox(oDB);
		return ret;
    }
    
    

    /**
     * 
     * @author martin
     * @date 16.10.2020
     *
     * @param statements
     * @param commit
     * @return
     * @throws myException
     * @throws SQLException
     */
    public static int executeStatements(VEK<SqlStringExtended> statements, boolean commit) throws myException, SQLException   {
    	int ret = 0;
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		ret += oDB.executeStatements(statements,commit);
		bibALL.destroy_myDBToolBox(oDB);
		return ret;
    }
     
    
}
