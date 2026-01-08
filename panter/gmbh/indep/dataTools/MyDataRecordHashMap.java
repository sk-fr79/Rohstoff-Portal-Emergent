package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;



/*
 * aufbau einer hashmap aus der abfrage eines datensatzes,
 * Die Objekte in der Map sind String[2], element 0 ist der Rueckgabewert einer spalte unformatiert, 1 formatiert 
 */
public class MyDataRecordHashMap extends HashMap<String, String[]>
{
	private String      				cInnerQuery = null;
	
	
	/*
	 * eine parallele hashmap fuer die metainformationen vom typ myFieldMetaInformation
	 */
	private HashMap<String,MyMetaFieldDEF>    hmMetaInformations = new HashMap<String,MyMetaFieldDEF>();
	
	private MyConnection  	oConn = bibALL.get_oConnectionNormal();

	
	//2012-08-15: variante mit RAW-Datenbankabfrage (ohne Datenbankzusatzfelder und View-ersetzung) 
	private	boolean 		bErsetzungTableView = true; 	

	
	/**
	 * 
	 */
	public MyDataRecordHashMap()
	{
		super();
	}


	/**
	 * 
	 * @param Conn
	 */
	public MyDataRecordHashMap(MyConnection Conn)
	{
		super();
		this.oConn = Conn;
	}

	
	/**
	 * 
	 * @param cSQLCompleteQuery
	 * @throws myException
	 */
	public MyDataRecordHashMap(String cSQLCompleteQuery) throws myException
	{
		super();
		this.INIT_Hash(cSQLCompleteQuery);
	}


	/**
	 * 2012-08-15: raw-variante 
	 * @param cSQLCompleteQuery
	 * @param ErsetzungTableView
	 * @throws myException
	 */
	public MyDataRecordHashMap(String cSQLCompleteQuery, boolean ErsetzungTableView) throws myException
	{
		super();
		this.bErsetzungTableView = ErsetzungTableView;
		
		this.INIT_Hash(cSQLCompleteQuery);
	}


	
	
	/**
	 * 
	 * @param cSQLCompleteQuery
	 * @param Conn
	 * @throws myException
	 */
	public MyDataRecordHashMap(String cSQLCompleteQuery,MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		this.INIT_Hash(cSQLCompleteQuery);
	}

	

	/**
	 * 
	 * @param tablename
	 * @param keyfieldname
	 * @param keyvalue_unformated
	 * @throws myException
	 */
	public MyDataRecordHashMap(String tablename, String keyfieldname, String keyvalue_unformated) throws myException
	{
		super();
		
		if (bibALL.isEmpty(keyfieldname) || bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyDataRecordHashMap:Constructor: Empty parameters not allowed!");
		
		this.INIT_Hash("*",bibE2.cTO()+"."+tablename,keyfieldname+"="+keyvalue_unformated);
	}
	

	/**
	 * 
	 * @param tablename
	 * @param keyfieldname
	 * @param keyvalue_unformated
	 * @param Conn
	 * @throws myException
	 */
	public MyDataRecordHashMap(String tablename, String keyfieldname, String keyvalue_unformated, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		if (bibALL.isEmpty(keyfieldname) || bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyDataRecordHashMap:Constructor: Empty parameters not allowed!");
		
		this.INIT_Hash("*",bibE2.cTO()+"."+tablename,keyfieldname+"="+keyvalue_unformated);
	}
	


	/**
	 * 
	 * @param tablename
	 * @param keyvalue_unformated
	 * @throws myException
	 */
	public MyDataRecordHashMap(String tablename, String keyvalue_unformated) throws myException
	{
		super();
		
		if (bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyDataRecordHashMap:Constructor: Empty parameters not allowed!");
		
		this.INIT_Hash("*",bibE2.cTO()+"."+tablename,"ID_"+tablename.substring(3)+"="+keyvalue_unformated);
	}

	
	/**
	 * 
	 * @param tablename
	 * @param keyvalue_unformated
	 * @param Conn
	 * @throws myException
	 */
	public MyDataRecordHashMap(String tablename, String keyvalue_unformated, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		if (bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyDataRecordHashMap:Constructor: Empty parameters not allowed!");
		
		this.INIT_Hash("*",bibE2.cTO()+"."+tablename,"ID_"+tablename.substring(3)+"="+keyvalue_unformated);
	}
	
	
	
	/**
	 * 
	 * @param oQueryBuilder
	 * @throws myException
	 */
	public MyDataRecordHashMap(MyQueryBUILDER oQueryBuilder) throws myException
	{
		super();
		this.INIT_Hash(oQueryBuilder.get_SelectBlock(),oQueryBuilder.get_FromBlock(),oQueryBuilder.get_WhereBlock(null));
	}

	
	
	/**
	 * 
	 * @param oQueryBuilder
	 * @param Conn
	 * @throws myException
	 */
	public MyDataRecordHashMap(MyQueryBUILDER oQueryBuilder, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		this.INIT_Hash(oQueryBuilder.get_SelectBlock(),oQueryBuilder.get_FromBlock(),oQueryBuilder.get_WhereBlock(null));
	}

	

	/**
	 * 
	 * @param cSelect
	 * @param cFrom
	 * @param cWhere
	 * @param bDummy
	 * @throws myException
	 */
	public MyDataRecordHashMap(String cSelect, String cFrom, String cWhere, boolean bDummy) throws myException
	{
		super();
		this.INIT_Hash(cSelect,cFrom,cWhere);
		if (!bDummy)
		{
			@SuppressWarnings("unused")
			boolean bbDummy=bDummy;
		}
	}


	/**
	 * 
	 * @param cSelect
	 * @param cFrom
	 * @param cWhere
	 * @param bDummy
	 * @param Conn
	 * @throws myException
	 */
	public MyDataRecordHashMap(String cSelect, String cFrom, String cWhere, boolean bDummy, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		this.INIT_Hash(cSelect,cFrom,cWhere);
		if (!bDummy)
		{
			@SuppressWarnings("unused")
			boolean bbDummy=bDummy;
		}
	}

	
	/**
	 * @param cSelect
	 * @param cFrom
	 * @param cWhere
	 * @throws myException
	 */
	public void INIT_Hash(String cSelect, String cFrom, String cWhere) throws myException
	{
		String cSQL = "SELECT "+cSelect+" FROM "+cFrom+" WHERE "+cWhere;
		this.INIT_Hash(cSQL);
	}
	
	
	
	

	/**
	 * @param cSQL_Query
	 * @throws myException
	 */
	public void INIT_Hash(String cSQL_Query) throws myException
	{
		this.cInnerQuery = cSQL_Query;
		
		this.Refresh();

	}
	

	
	public void Refresh() throws myException
	{
		this.clear();
		String cSQL = this.cInnerQuery;
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox(this.oConn);
		
		//2012-08-15: neue variante mir RAW moeglich
		if (!this.bErsetzungTableView)
		{
			oDB.set_bErsetzungTableView(false);
		}
		
		MyDBResultSet oRS = oDB.OpenResultSet(cSQL);
		
		if (oRS.RS != null)
		{
            try
            {
                int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

                int iCount = 0;
                if (iAnzahlSpalten > 0)
                {
                    
                    while (oRS.RS.next())
                    {
                        iCount++;
                        if (iCount>1)
                        {
                        	oRS.Close();
                        	bibALL.destroy_myDBToolBox(oDB);
                           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_UNIQUE,"MyDataRecordHashMap:__build_Hash: More than one result-rows cannot be !!");
                        }
                        for (int i = 0; i < iAnzahlSpalten; i++)
                        {
                        	MyResultValue oRSF = new MyResultValue(new MyMetaFieldDEF(oRS.RS,i, null),oRS.RS,false);
                        	
                        	String cROWNAME	= oRS.RS.getMetaData().getColumnLabel(i+1);
                            
                            String[] cWert = new String[2];
                            cWert[0]=oRSF.get_FieldValueUnformated();
                            cWert[1]=oRSF.get_FieldValueFormated();
                            this.put(cROWNAME,cWert);
                            
                            this.hmMetaInformations.put(cROWNAME, new MyMetaFieldDEF(oRS.RS,i,null));
                            
                        }
                    }

                }
                
                if (iCount==0)
                {
                	oRS.Close();
                	bibALL.destroy_myDBToolBox(oDB);
                	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_EMPTY,"MyDataRecordHashMap:__build_Hash: Empty Resultset is not allowed !!");
                }

            }
            catch (myException ex)
            {
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	throw ex;
            }
            catch (Exception e)
            {
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	e.printStackTrace();
            	throw new myException(e.getLocalizedMessage());
            }
 		}
		else
		{
        	DEBUG.System_println("--------------------------------------------------", "");
        	DEBUG.System_println("MyDataRecordHashMap: Error SQL  ---------------------------------------", "");
        	DEBUG.System_println(cSQL, "");
        	DEBUG.System_println("--------------------------------------------------", "");
        	bibALL.destroy_myDBToolBox(oDB);
           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"MyDataRecordHashMap:__build_Hash: Cannnot open resultset !"+cSQL);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
		
	}
	
	
	
	
	public String get_FormatedValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:get_FormatedValue:"+cRowName+" not in MAP!");
		
		String cRueck = null;
		
		String[] cWert = (String[])this.get(cRowName);
		if (cWert != null)
		{
			cRueck = cWert[1];
		}
		
		return cRueck;
	}

	
	public String get_UnFormatedValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_UnFormatedValue:"+cRowName+" not in MAP!");
	
		String cRueck = null;
		String[] cWert = (String[])this.get(cRowName);
		if (cWert != null)
		{
			cRueck = cWert[0];
		}
		
		return cRueck;
	}


	
	
	
	public String get_FormatedValue_LeerWennNull(String cRowName) throws myException
	{
		return bibALL.null2leer(this.get_FormatedValue(cRowName));
	}

	
	public String get_UnFormatedValue_LeerWennNull(String cRowName) throws myException
	{
		return bibALL.null2leer(this.get_UnFormatedValue(cRowName));
	}

	
	
	
	
	
	
	
	
	public Double get_doubleValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_doubleValue:"+cRowName+" not in MAP!");
		
		Double dRueck = null;
		
		String[] cWert = (String[])this.get(cRowName);
		if (cWert != null && cWert[0]!=null)
		{
			try
			{
				dRueck = new Double(cWert[0]);
			}
			catch (Exception ex)
			{
				throw new myException("MyDataRecordHashMap:get_doubleValue: Cannnot create double-Value "+cRowName);
			}
		}
		
		return dRueck;
		
	}
	
	
	
	public Integer get_intValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_intValue:"+cRowName+" not in MAP!");
		
		Integer iRueck = null;
		
		String[] cWert = (String[])this.get(cRowName);
		if (cWert != null && cWert[0]!=null)
		{
			try
			{
				iRueck = new Integer(cWert[0]);
			}
			catch (Exception ex)
			{
				throw new myException("MyDataRecordHashMap:get_intValue: Cannnot create integer-Value "+cRowName);
			}
		}
		
		return iRueck;
		
	}
	
	
	
	
	public Long get_longValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_longValue:"+cRowName+" not in MAP!");
		
		Long lRueck = null;
		
		String[] cWert = (String[])this.get(cRowName);
		if (cWert != null && cWert[0]!=null)
		{
			try
			{
				lRueck = new Long(cWert[0]);
			}
			catch (Exception ex)
			{
				throw new myException("MyDataRecordHashMap:get_longValue: Cannnot create Long-Value "+cRowName);
			}
		}
		
		return lRueck;
		
	}
	
	

	
	
	/**
	 * @param cFIELDNAME
	 * @return s Wert, der direkt in eine SQL-anweisung eingefuegt werden kann
	 * @throws myException
	 */
	public String get_ValueStringForSQLStatement(String cFIELDNAME) throws myException
	{
		if (!this.containsKey(cFIELDNAME))
			throw new myException("MyDataRecordHashMap:	get_ValueStringForSQLStatement:"+cFIELDNAME+" not in MAP!");
		
		
		String cRueck = null;
		
		if (!this.containsKey(cFIELDNAME))
			throw new myException("MyDataRecordHashMap:get_ValueStringForSQLStatement:"+cFIELDNAME+" is not in the list!");

		if (this.get(cFIELDNAME)==null || this.get_FormatedValue(cFIELDNAME)==null || this.get_FormatedValue(cFIELDNAME).equals(""))
			return "NULL";
		
		
		MyMetaFieldDEF oMF = (MyMetaFieldDEF)this.hmMetaInformations.get(cFIELDNAME);
		String cTyp = oMF.get_FieldType();
		
		if (DB_META.vDB_NUMBER_TYPES.contains(cTyp))
		{
			cRueck = this.get_UnFormatedValue(cFIELDNAME);
	    }
		else if (DB_META.vDB_DATE_TYPES.contains(cTyp))
		{
			TestingDate oTD = new TestingDate(this.get_FormatedValue(cFIELDNAME));
			if (!oTD.testing())
				throw new myException("MyDataRecordHashMap:get_ValueStringForSQLStatement:"+cFIELDNAME+" is bad Dateformat!");
			
			cRueck = oTD.get_ISO_DateFormat(true);
		}
		else if (DB_META.vDB_TEXT_TYPES.contains(cTyp))
		{
			cRueck = bibALL.MakeSql(this.get_UnFormatedValue(cFIELDNAME));
		}
		
		return cRueck;
	}

	
	
	
	
	
	
	public boolean equals(Object oVGL)
	{
		boolean bRueck = false;
		try
		{
			if (oVGL != null && (oVGL instanceof MyDataRecordHashMap))
			{
			
				MyDataRecordHashMap hmVGL = (MyDataRecordHashMap)oVGL;

				// groessen-vergleich
				if (hmVGL.entrySet().size() != this.entrySet().size())
					return false;

				Iterator<Map.Entry<String, String[]>> itVGL = hmVGL.entrySet().iterator(); 
								
				bRueck = true;               // wenn ein fehler gefunden wird, raus aus der schleife
				while (itVGL.hasNext()) 
				{
				    Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>)itVGL.next();
				    
				    if (! this.containsKey(entry.getKey()))
				    	return false;

				    String[] cHmVGLValue = (String[])entry.getValue();
				    String[] cOwnValue   = (String[])this.get(entry.getKey());
				    
				    
				    if (cHmVGLValue != null || cOwnValue != null)
				    {

				    	if (cHmVGLValue == null && cOwnValue != null)
					    	return false;
					    
					    if (cHmVGLValue != null && cOwnValue == null)
					    	return false;
					    
					    
					    // zum vergleich wird der unformatierte string rangezogen
				    	String ccHmVGLValue = 	cHmVGLValue[0];
				    	String ccOwnValue = 	cOwnValue[0];
				    	
				    	if (ccHmVGLValue != null || ccOwnValue != null)
				    	{
				    		
					    	if (ccHmVGLValue == null && ccOwnValue != null)
						    	return false;
						    
						    if (ccHmVGLValue != null && ccOwnValue == null)
						    	return false;
				    		
						    if (!ccHmVGLValue.equals(ccOwnValue))
						    	return false;

				    	}
				    }
				} 			
			}
			
		}
		catch (Exception ex)
		{
		}
		return bRueck;
	}



	public HashMap<String, MyMetaFieldDEF> get_hmMetaInformations()
	{
		return hmMetaInformations;
	}
	
	
	/**
	 * @return s StatementBuilder um die gleichen Inhalte abzuspeichern, d.h.
	 * 			 einfach andere Werte uebergeben und updateStatement rausziehen, fertig ist das Update-Statement
	 * 			(ohne die Sonderfelder)
	 * @throws myException
	 */
	public MySqlStatementBuilder get_StatementBuilderFilledWithActualValues() throws myException
	{
		MySqlStatementBuilder oStateBuilder = new MySqlStatementBuilder();
		
		Vector<String> oFieldsNotInMap = new Vector<String>();
		oFieldsNotInMap.addAll(DB_STATICS.get_AutomaticWrittenFields_STD());
		
		
		//jetzt mit den Werten der map fuellen
		for (java.util.Map.Entry<String,String[]> oEntry: this.entrySet())
		{
			if (!oFieldsNotInMap.contains(oEntry.getKey()))
			{
				oStateBuilder.addSQL_Paar(oEntry.getKey(), this.get_ValueStringForSQLStatement(oEntry.getKey()), false);
			}
		}
		
		return oStateBuilder;
	}
	

	public boolean get_bErsetzungTableView()
	{
		return bErsetzungTableView;
	}


	public void set_bErsetzungTableView(boolean bErsetzungTableView)
	{
		this.bErsetzungTableView = bErsetzungTableView;
	}

	
	
	
}
