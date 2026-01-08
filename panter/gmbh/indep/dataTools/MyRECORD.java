package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.DB_STATICS.AUTOMATC_FIELDS;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;



/*
 * aufbau einer hashmap aus der abfrage eines datensatzes,
 */
public class MyRECORD extends HashMap<String, MyResultValue> implements MyRECORD_IF_FILLABLE
{
	
	private MyConnection  			oConn = bibALL.get_oConnectionNormal();

	/**
	 * 2015-05-06: Toolbox-Generator, um DBToolboxen mit von der norm abweichenden Ausnahmefeldern erzeugen zu koennen
	 */
	private MyDBToolBox_FAB  	DBToolBox_FAB = null;


	private String        cSQL_4_Build = null;
	


	private String        cTABLE_NAME = null;               //dTablename kann auch unbestimmt sein
	
	
//	//2013-07-17: hashmap mit zuweisungsfehler-statuswerten
//	private HashMap<String, Integer> hmLastAssignmentErrors =new HashMap<String, Integer>();
	
	//2013-09-20: hashmap mit field-metadefs gleich mitaufbauen
	private HashMap<String, MyMetaFieldDEF>  hm_FieldMetaDefs = new HashMap<String, MyMetaFieldDEF>();		




	//2012-02-03: eine enum mit den datentypen, die vorkommen
	public static enum    DATATYPES 
							{
								NUMBER,
								DECIMALNUMBER,
								TEXT,
								DATE,
								YESNO
							};
	
	
	//2015-03-05: zusatzpaare feld-wert von aussen fuer die erzeugung der statementbuilders
	private HashMap<String, String>      hm_Field_Value_pairs_from_outside = new HashMap<String, String>();
							
							
							
	/**
	 * 
	 */
	public MyRECORD()
	{
		super();
	}

	
	public MyRECORD(MyRECORD oRecordOrig)
	{
		super();
		this.oConn = oRecordOrig.get_oConn();
		this.putAll(oRecordOrig);
		this.hm_FieldMetaDefs.putAll(oRecordOrig.get_hm_FieldMetaDefs());
		this.cSQL_4_Build = oRecordOrig.get_cSQL_4_Build();
	}
	
	/**
	 * 
	 * @param Conn
	 */
	public MyRECORD(MyConnection Conn)
	{
		super();
		this.oConn = Conn;
	}

	
	/**
	 * 
	 * @param cSQLCompleteQuery
	 * @throws myException
	 */
	public MyRECORD(String cSQLCompleteQuery) throws myException
	{
		super();
		this.BuildRecord(cSQLCompleteQuery);
	}


	/**
	 * 
	 * @param cSQLCompleteQuery
	 * @param Conn
	 * @throws myException
	 */
	public MyRECORD(String cSQLCompleteQuery,MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		this.BuildRecord(cSQLCompleteQuery);
	}

	

	/**
	 * 
	 * @param tablename
	 * @param keyfieldname
	 * @param keyvalue_unformated
	 * @throws myException
	 */
	public MyRECORD(String tablename, String keyfieldname, String keyvalue_unformated) throws myException
	{
		super();
		
		if (bibALL.isEmpty(keyfieldname) || bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyRecord:Constructor: Empty parameters not allowed!");
		
		this.PrepareAndBuild("*",bibE2.cTO()+"."+tablename,keyfieldname+"="+keyvalue_unformated);
	}
	

	/**
	 * 
	 * @param tablename
	 * @param keyfieldname
	 * @param keyvalue_unformated
	 * @param Conn
	 * @throws myException
	 */
	public MyRECORD(String tablename, String keyfieldname, String keyvalue_unformated, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		if (bibALL.isEmpty(keyfieldname) || bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyRecord:Constructor: Empty parameters not allowed!");
		
		this.PrepareAndBuild("*",bibE2.cTO()+"."+tablename,keyfieldname+"="+keyvalue_unformated);
	}
	


	/**
	 * 
	 * @param tablename
	 * @param keyvalue_unformated
	 * @throws myException
	 */
	public MyRECORD(String tablename, String keyvalue_unformated) throws myException
	{
		super();
		
		if (bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyRecord:Constructor: Empty parameters not allowed!");
		
		this.PrepareAndBuild("*",bibE2.cTO()+"."+tablename,"ID_"+tablename.substring(3)+"="+keyvalue_unformated);
	}

	
	/**
	 * 
	 * @param tablename
	 * @param keyvalue_unformated
	 * @param Conn
	 * @throws myException
	 */
	public MyRECORD(String tablename, String keyvalue_unformated, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		if (bibALL.isEmpty(tablename) || bibALL.isEmpty(keyvalue_unformated))
			throw new myException("MyRecord:Constructor: Empty parameters not allowed!");
		
		this.PrepareAndBuild("*",bibE2.cTO()+"."+tablename,"ID_"+tablename.substring(3)+"="+keyvalue_unformated);
	}
	
	
	
	/**
	 * 
	 * @param oQueryBuilder
	 * @throws myException
	 */
	public MyRECORD(MyQueryBUILDER oQueryBuilder) throws myException
	{
		super();
		this.PrepareAndBuild(oQueryBuilder.get_SelectBlock(),oQueryBuilder.get_FromBlock(),oQueryBuilder.get_WhereBlock(null));
	}

	
	
	/**
	 * 
	 * @param oQueryBuilder
	 * @param Conn
	 * @throws myException
	 */
	public MyRECORD(MyQueryBUILDER oQueryBuilder, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		this.PrepareAndBuild(oQueryBuilder.get_SelectBlock(),oQueryBuilder.get_FromBlock(),oQueryBuilder.get_WhereBlock(null));
	}



	
	/**
	 * @param cSelect
	 * @param cFrom
	 * @param cWhere
	 * @throws myException
	 */
	public void PrepareAndBuild(String cSelect, String cFrom, String cWhere) throws myException
	{
		String cSQL = "SELECT "+cSelect+" FROM "+cFrom+" WHERE "+cWhere;
		this.BuildRecord(cSQL);
	}
	
	

	public void REBUILD() throws myException
	{
		this.BuildRecord(this.cSQL_4_Build);
	}

	
	
	
	public void BuildRecord(String cSQL) throws myException
	{
		this.cSQL_4_Build = cSQL;
		
		this.clear();
		
		//2015-05-06: hier geaendert:
		//von
		// MyDBToolBox oDB = bibALL.get_myDBToolBox(this.oConn);
		//nach
		MyDBToolBox oDB = this.generate_DBToolBox(this.oConn);
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
                           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_UNIQUE,"MyRecord:__build_Hash: More than on result-rows cannot be !!");
                        }
                        for (int i = 0; i < iAnzahlSpalten; i++)
                        {
                        	MyResultValue oResult = new MyResultValue(new MyMetaFieldDEF(oRS.RS,i, null),oRS.RS,false);
                            this.ADD(oResult);
                            
                        	//2013-09-20: hashmap mit field-metadefs gleich mitaufbauen
                            this.hm_FieldMetaDefs.put(oResult.get_MetaFieldDef().get_FieldName().toUpperCase(), oResult.get_MetaFieldDef());
                            
                            //DEBUG.MetaDef_print_infos( oResult.get_MetaFieldDef());
                        }
                    }

                }
                
                if (iCount==0)
                {
                	oRS.Close();
                	bibALL.destroy_myDBToolBox(oDB);
                	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_EMPTY,"MyRecord:BuildRecord: Empty Resultset is not allowed: "+this.cSQL_4_Build);
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
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("MyRecord: Error SQL  ---------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println(cSQL,DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	bibALL.destroy_myDBToolBox(oDB);
           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"MyRecord:__build_Hash: Cannnot open resultset !"+cSQL);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
		
	}
	
	
	
	public MyResultValue ADD(MyResultValue oResultValue, boolean allowOverwriting) throws myException
	{
		if (this.containsKey(oResultValue.get_cFieldLABEL()) && !allowOverwriting)
		{
			throw new myException(this,"ADD_PairOfValues: Double Key "+oResultValue.get_cFieldLABEL()+"is not allowed !!");
		}
		
		/*
		 * 2016-11-02: werden records innerhalb einer recordlist gebildet, dann werden die metafielddefs nicht uebertragen,
		 * hiermit gefixed
		 * Martin
		 */
        this.hm_FieldMetaDefs.put(oResultValue.get_MetaFieldDef().get_FieldName().toUpperCase(), oResultValue.get_MetaFieldDef());
		
        if (S.isFull(this.get_TABLENAME())) {
        	this.set_Tablename_To_FieldMetaDefs(this.get_TABLENAME());
        }
//		DEBUG.System_println("SQL-4-build = <"+S.NN(this.cSQL_4_Build)+">");
		
		/*
		 *ende fix 2016-11-02 
		 */
		
		
		return this.put(oResultValue.get_cFieldLABEL(), oResultValue);
	}
	
	
	public MyResultValue ADD(MyResultValue oResultValue) throws myException
	{
		return this.ADD(oResultValue,false);
	}
	

	
	
	public String get_FormatedValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName) || this.get(cRowName)==null)
			throw new myException("MyRecord:get_FormatedValue:"+cRowName+" not in MAP!");
		
		return this.get(cRowName).get_FieldValueFormated();
	}

	
	public String get_UnFormatedValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName) || this.get(cRowName)==null)
			throw new myException("MyRecord:	get_UnFormatedValue:"+cRowName+" not in MAP!");
	
		return this.get(cRowName).get_FieldValueUnformated();
	}


	public String get_FormatedValue(String cRowName, String cValueWhenNull) throws myException
	{
		if (this.get_FormatedValue(cRowName) == null)
		{
			return cValueWhenNull;
		}
		else
		{
			return this.get_FormatedValue(cRowName);
		}
		
		
	}

	
	public String get_UnFormatedValue(String cRowName, String cValueWhenNull) throws myException
	{
		if (this.get_UnFormatedValue(cRowName) == null)
		{
			return cValueWhenNull;
		}
		else
		{
			return this.get_UnFormatedValue(cRowName);
		}
	}

	
	public String get_cVALUE_FOR_SQLSTATEMENT(String cRowName) throws myException
	{
		if (!this.containsKey(cRowName)  || this.get(cRowName)==null)
			throw new myException(this,"get_cVALUE_FOR_SQLSTATEMENT:"+cRowName+" not in MyRecord!");
	
		return this.get(cRowName).get_cVALUE_FOR_SQLSTATEMENT();
	}
	
	
	public Double get_doubleValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName) || this.get(cRowName)==null)
			throw new myException(this,"get_doubleValue:"+cRowName+" not in MyRecord!");
		
		
		return this.get(cRowName).getDoubleValue();
		
	}
	
	
	/**
	 * 2014-06-03
	 * @param cRowName
	 * @param dWhenNull
	 * @return
	 * @throws myException
	 */
	public Double get_doubleValue(String cRowName, Double dWhenNull) throws myException
	{
		
		if (!this.containsKey(cRowName) || this.get(cRowName)==null)
			throw new myException(this,"get_doubleValue:"+cRowName+" not in MyRecord!");
		
		Double dRueck = this.get(cRowName).getDoubleValue();
		
		if (dRueck == null) {
			dRueck = dWhenNull;
		}
		
		return dRueck;
		
	}
	
	
	
	
	
	public Long get_longValue(String cRowName) throws myException
	{
		
		if (!this.containsKey(cRowName) || this.get(cRowName)==null)
			throw new myException(this,"get_longValue:"+cRowName+" not in MyRecord!");
		
		return this.get(cRowName).getLongValue();
		
	}
	
	

	/**
	 * 2014-06-03
	 * @param cRowName
	 * @param lWhenNull
	 * @return
	 * @throws myException
	 */
	public Long get_longValue(String cRowName, Long lWhenNull) throws myException
	{
		
		if (!this.containsKey(cRowName) || this.get(cRowName)==null)
			throw new myException(this,"get_longValue:"+cRowName+" not in MyRecord!");
		
		Long lRueck = this.get(cRowName).getLongValue();
		if (lRueck == null) {
			lRueck = lWhenNull;
		}
		
		return lRueck;
		
	}

	
	
	
	public BigDecimal get_bdValue(String cRowName, BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get(cRowName).getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}

	
	
	
	
	public boolean equals(Object oVGL)
	{
		boolean bRueck = false;
		try
		{
			if (oVGL != null && (oVGL instanceof MyRECORD))
			{
			
				MyRECORD hmVGL = (MyRECORD)oVGL;

				// groessen-vergleich
				if (hmVGL.entrySet().size() != this.entrySet().size())
					return false;

				Iterator<Map.Entry<String, MyResultValue>> itVGL = hmVGL.entrySet().iterator();
								
				bRueck = true;               // wenn ein fehler gefunden wird, raus aus der schleife
				while (itVGL.hasNext()) 
				{
				    Map.Entry<String,MyResultValue> entry = (Map.Entry<String, MyResultValue>)itVGL.next();
				    
				    if (! this.containsKey(entry.getKey()))
				    	return false;

				    MyResultValue oResultSetToCompare = (MyResultValue)entry.getValue();
				    MyResultValue oResultSetOwn   = (MyResultValue)this.get(entry.getKey());
				    
				    
				    if (oResultSetToCompare != null || oResultSetOwn != null)
				    {

				    	if (oResultSetToCompare == null && oResultSetOwn != null)
					    	return false;
					    
					    if (oResultSetToCompare != null && oResultSetOwn == null)
					    	return false;
					    
					    
					    // zum vergleich wird der unformatierte string rangezogen
				    	String ccHmVGLValue = 	oResultSetToCompare.get_FieldValueUnformated();
				    	String ccOwnValue = 	oResultSetOwn.get_FieldValueUnformated();
				    	
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


	
	/**
	 * 2015-02-17: compare mit rueckgabe der unterschiedliche felder
	 * @param oVGL
	 * @return
	 */
	public boolean compareTo(MyRECORD oVGL, HashMap<String, String> hmDifferents)
	{
		if (oVGL != null)
		{
		
			// groessen-vergleich
			if (oVGL.entrySet().size() != this.entrySet().size()) {
				hmDifferents.put(this.get_TABLE_NAME(), "Different Fieldnumbers!");
			}

			Iterator<Map.Entry<String, MyResultValue>> itVGL = oVGL.entrySet().iterator();
							
			while (itVGL.hasNext()) 
			{
			    Map.Entry<String,MyResultValue> entry = (Map.Entry<String, MyResultValue>)itVGL.next();
			    
			    if (! this.containsKey(entry.getKey())) {
			    	hmDifferents.put(this.get_TABLE_NAME()+":"+entry.getKey(), "Field not in original RECORD");
			    } else {

				    MyResultValue oResultSetToCompare = (MyResultValue)entry.getValue();
				    MyResultValue oResultSetOwn   = (MyResultValue)this.get(entry.getKey());
				    
				    if (oResultSetToCompare != null || oResultSetOwn != null)
				    {

				    	if (oResultSetToCompare == null && oResultSetOwn != null) {
					    	hmDifferents.put(this.get_TABLE_NAME()+":"+entry.getKey(), "Field not in other RECORD");
				    	}
					    
					    if (oResultSetToCompare != null && oResultSetOwn == null) {
					    	hmDifferents.put(this.get_TABLE_NAME()+":"+entry.getKey(), "Field not in this RECORD");
					    }
					    
					    // zum vergleich wird der unformatierte string rangezogen
				    	String ccHmVGLValue = 	oResultSetToCompare.get_FieldValueUnformated();
				    	String ccOwnValue = 	oResultSetOwn.get_FieldValueUnformated();
				    	
				    	if (ccHmVGLValue != null || ccOwnValue != null)
				    	{
					    	if (ccHmVGLValue == null && ccOwnValue != null) {
					    		hmDifferents.put(this.get_TABLE_NAME()+":"+entry.getKey(), "other: NULL"+			":   own: "+ccOwnValue);
					    	}
						    
						    if (ccHmVGLValue != null && ccOwnValue == null) {
					    		hmDifferents.put(this.get_TABLE_NAME()+":"+entry.getKey(), "other: "+ccHmVGLValue+	":   own: NULL");
						    }
				    		
						    if (!ccHmVGLValue.equals(ccOwnValue)) {
					    		hmDifferents.put(this.get_TABLE_NAME()+":"+entry.getKey(), "other: "+ccHmVGLValue+	":   own: "+ccOwnValue);
						    }

				    	}
				    }
			    }
			} 			
		}
		return (hmDifferents.size()==0);
	}


	
	
	
	
	/**
	 * Debug-methode
	 */
	public void print_record_to_console()
	{
		
		
		Iterator<Map.Entry<String, MyResultValue>> it = this.entrySet().iterator();
		
		while (it.hasNext()) 
		{
		    Map.Entry<String,MyResultValue> entry = (Map.Entry<String, MyResultValue>)it.next();
		    
		    MyResultValue oRS = (MyResultValue)entry.getValue();
		    
		    try
			{
				DEBUG.System_println(oRS.get_cFieldLABEL()+": "+oRS.get_FieldValueUnformated()+" : "+oRS.get_FieldValueFormated()+" : "+oRS.get_cVALUE_FOR_SQLSTATEMENT(), "");
			} catch (myException e)
			{
				e.printStackTrace();
			}
		    
		    
		}		
	}
		
		
	@Override
	public MyE2_MessageVector  set_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException
	{
	
		if (!this.containsKey(FIELDNAME) || this.get(FIELDNAME)==null)
			throw new myException(this,"set_NewValueForDatabase:"+FIELDNAME+" not in MyRecord!");

		MyE2_MessageVector oMVRueck = this.get(FIELDNAME).set_NewValueForDatabase(cNewValueFormated);
		
//		//2013-07-17: assignment-fehler definieren
//		this.hmLastAssignmentErrors.put(FIELDNAME, this.get(FIELDNAME).get_iLastAssignmentError());
		
		return oMVRueck;
	}
	
	
	/**
	 * 2013-09-18: pruefmethode, um die inhalte zu verifizieren bevor sie in die records geschrieben werden
	 * @param FIELDNAME
	 * @param cNewValueFormated
	 * @return
	 * @throws myException
	 */
	@Override
	public MyE2_MessageVector  check_NewValueForDatabase(String FIELDNAME, String cNewValueFormated) throws myException
	{
	
		if (!this.containsKey(FIELDNAME) || this.get(FIELDNAME)==null)
			throw new myException(this,"set_NewValueForDatabase:"+FIELDNAME+" not in MyRecord!");

		MyE2_MessageVector oMVRueck = this.get(FIELDNAME).check_NewValueForDatabase(cNewValueFormated);
		
		
		//DEBUG.MetaDef_print_infos(this.get(FIELDNAME).get_MetaFieldDef());
		
		
//		//2013-07-17: assignment-fehler definieren
//		this.hmLastAssignmentErrors.put(FIELDNAME, this.get(FIELDNAME).get_iLastAssignmentError());
		
		return oMVRueck;
	}
	

	
	
	
	//2013-07-17:  erweiterung der set_NewValue - mimic und symmetrisierung mit dem MyRECORD_NEW - Typ
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, long lNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(lNewValueFormated, false));		
	}
	
	//2013-07-17:  erweiterung der set_NewValue - mimic und symmetrisierung mit dem MyRECORD_NEW - Typ
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, double dNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(dNewValueFormated, this.get(FIELDNAME).get_MetaFieldDef().get_FieldDecimals(),false));		
	}

	//2013-07-17:  erweiterung der set_NewValue - mimic und symmetrisierung mit dem MyRECORD_NEW - Typ
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, BigDecimal bdNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, MyNumberFormater.formatDez(bdNewValueFormated, this.get(FIELDNAME).get_MetaFieldDef().get_FieldDecimals(),true));		
	}

	
	//2013-07-17:  erweiterung der set_NewValue - mimic und symmetrisierung mit dem MyRECORD_NEW - Typ
	@Override
	public MyE2_MessageVector set_NewValueForDatabase(String FIELDNAME, GregorianCalendar calNewValueFormated) throws myException
	{
		return set_NewValueForDatabase(FIELDNAME, myDateHelper.FormatDateNormal(calNewValueFormated.getTime()));		
	}

	
	
	
	/**
	 * reset aller bestehenden neuen Datenbank-Values
	 * @throws myException
	 */
	public void RESET_ALL_NEWVALUES() throws myException
	{
		for (Map.Entry<String, MyResultValue> oEntry: this.entrySet())
		{
			oEntry.getValue().ResetOriginalDatabaseValue();
		}
	}
	
	

	/**
	 * @return s StatementBuilder um die gleichen Inhalte abzuspeichern, d.h.
	 * 			 einfach andere Werte uebergeben und updateStatement rausziehen, fertig ist das Update-Statement
	 * 			(ohne die Sonderfelder)
	 * @throws myException
	 */
	public MySqlStatementBuilder get_StatementBuilderFilledWithActualValues() throws myException
	{
		return __statementBuilderFilledWithActualValues(true, null);
	}


	/**
	 * 
	 * @param cTableName
	 * @return s StatementBuilder um die gleichen Inhalte abzuspeichern, d.h.
	 * 			 einfach andere Werte uebergeben und updateStatement rausziehen, fertig ist das Update-Statement
	 * 			(ohne die Sonderfelder)
	 * @throws myException
	 */
	public MySqlStatementBuilder get_StatementBuilderFilledWithActualValues(String cTableName) throws myException
	{
		return __statementBuilderFilledWithActualValues(true, cTableName);
	}

	
	
	/**
	 * @return s StatementBuilder um die gleichen Inhalte abzuspeichern, d.h.
	 * 			 einfach andere Werte uebergeben und updateStatement rausziehen, fertig ist das Update-Statement
	 * bExcludeStandard   :  when true, dann werden die mandant usw nicht mit rauskopiert
	 * @throws myException
	 */
	public MySqlStatementBuilder get_StatementBuilderFilledWithActualValues(boolean bExcludeStandard) throws myException
	{
		return __statementBuilderFilledWithActualValues(bExcludeStandard, null);
	}
	
	
//	/**
//	 * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
//	 */
//	public MySqlStatementBuilder get_StatementBuilder(boolean bExcludeAutomaticFields) throws myException
//	{
//		return this.get_StatementBuilderFilledWithActualValues(bExcludeAutomaticFields);
//	}
	

	private MySqlStatementBuilder __statementBuilderFilledWithActualValues(boolean bExcludeStandard, String cTableName) throws myException
	{
		MySqlStatementBuilder oStateBuilder = new MySqlStatementBuilder();
		
		//2011-07-29: optional kann der tablename in den statementbuilder uebergeben werden
		if (S.isFull(cTableName))
		{
			oStateBuilder.set_cTableName(cTableName);
		}
		
		Vector<String> oFieldsNotInMap = new Vector<String>();
		if (bExcludeStandard)
		{
			//2015-05-06: geaendert
			//oFieldsNotInMap.addAll(bibALL.get_vSonderFelder());
			oFieldsNotInMap.addAll(this.get_vSonderFelder());
		}
		
		//jetzt mit den Werten der map fuellen
		for (java.util.Map.Entry<String,MyResultValue> oEntry: this.entrySet())
		{
			if (!oFieldsNotInMap.contains(oEntry.getKey()))
			{
				oStateBuilder.addSQL_Paar(oEntry.getKey(),this.get_cVALUE_FOR_SQLSTATEMENT(oEntry.getKey()), false);
			}
		}
		
		//2015-03-05: von aussen zugefuegte statements hier mit einbauen
		for (String field: this.hm_Field_Value_pairs_from_outside.keySet()) {
			oStateBuilder.addSQL_Paar(field, this.hm_Field_Value_pairs_from_outside.get(field), false);
		}
		
		
		return oStateBuilder;
	}

	

	
	/**
	 * @return s StatementBuilder um die gleichen Inhalte abzuspeichern, d.h.
	 * 			 einfach andere Werte uebergeben und updateStatement rausziehen, fertig ist das Update-Statement
	 * 			(ohne die Sonderfelder)
	 * @throws myException
	 */
	public MySqlStatementBuilder get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS() throws myException
	{
		return __StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS(true, null);
	}

	
	
	/**
	 * @return s StatementBuilder um die gleichen Inhalte abzuspeichern, d.h.
	 * 			 einfach andere Werte uebergeben und updateStatement rausziehen, fertig ist das Update-Statement
	 * bExcludeStandard   :  when true, dann werden die mandant usw nicht mit rauskopiert
	 * @throws myException
	 */
	public MySqlStatementBuilder get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS(boolean bExcludeStandard) throws myException
	{
		return __StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS(bExcludeStandard, null);
	}


	 
	private MySqlStatementBuilder __StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS(boolean bExcludeStandard, String cTableName) throws myException
	{
		MySqlStatementBuilder oStateBuilder = new MySqlStatementBuilder();
		
		//2011-07-29: optional kann der tablenam in den statementbuilder uebergeben werden
		if (S.isFull(cTableName))
		{
			oStateBuilder.set_cTableName(cTableName);
		}
		
		Vector<String> oFieldsNotInMap = new Vector<String>();
		
		if (bExcludeStandard)
		{
			oFieldsNotInMap.addAll(this.get_vSonderFelder());
		}
		
		//jetzt mit den Werten der map fuellen
		for (java.util.Map.Entry<String,MyResultValue> oEntry: this.entrySet())
		{
			if (!oFieldsNotInMap.contains(oEntry.getKey()))
			{
				if (oEntry.getValue().get_bDatabaseValue_Was_Set_from_outside())
				{
					oStateBuilder.addSQL_Paar(oEntry.getKey(),this.get_cVALUE_FOR_SQLSTATEMENT(oEntry.getKey()), false);
				}
			}
		}
		
		//2015-03-05: von aussen zugefuegte statements hier mit einbauen
		for (String field: this.hm_Field_Value_pairs_from_outside.keySet()) {
			oStateBuilder.addSQL_Paar(field, this.hm_Field_Value_pairs_from_outside.get(field), false);
		}

		
		return oStateBuilder;
	}


	
	

	public MyConnection get_oConn()
	{
		return oConn;
	}


	public String get_cSQL_4_Build()
	{
		return cSQL_4_Build;
	}

	
	/**
	 * 2015-06-17
	 * @param String... cfield
	 * @return
	 * @throws myException
	 */
	public String get___KETTE(String... fieldnames) throws myException	{
		return this.get___KETTE(bibVECTOR.get_VectorFromArray(fieldnames), "", "", "", " ");
	}
	

	/**
	 * 2016-08-08
	 * @param String... cfield
	 * @return
	 * @throws myException
	 */
	public String get___KETTE(IF_Field... fieldnames) throws myException	{
		Vector<String>  v_name = new Vector<>();
		for (IF_Field f: fieldnames) {
			v_name.add(f.fn());
		}
		
		return this.get___KETTE(v_name, "", "", "", " ");
	}

	
	
	/**
	 * 
	 * @param vFieldNamesToChain
	 * @return
	 * @throws myException
	 */
	public String get___KETTE(Vector<String> vFieldNamesToChain) throws myException
	{
		return this.get___KETTE(vFieldNamesToChain, "", "", "", " ");
	}
	
	
	/**
	 * 
	 * @param vFieldNamesToChain   Vector mit Feldnamen (Falls ein Feldname nicht in der HASH ist, erfolgt myException
	 *                             Hat ein feld ein @ im String, dann ist der hintere teil der Feldname, der vordere 
	 *                             wird als Feldumrandung benutzt (Zeichen 1 links, Zeichen 2 rechts)
	 *                             
	 *                             Beginnt ein Feld mit # dann ist es Beschriftung (uebersetzt)
	 *                             Beginnt ein Feld mit & dann ist es Beschriftung (nicht uebersetzt)
	 *                             
	 *                             
	 * @param cValue4EmptyField    Wert fuer leere Felder
	 * @param cLeftFieldAddon      linke begrenzung fuer ein Feld (z.B. "<")
	 * @param cRightFieldAddon     rechte begrenzung fuer ein Feld (z.B. "<")
	 * @param cSeparator	       trenner zwischen den Feldern
	 * @return
	 * @throws myException
	 */
	public String get___KETTE(Vector<String> vFieldNamesToChain, String cValue4EmptyField, String cLeftFieldAddon, String cRightFieldAddon, String cSeparator) throws myException
	{
		String cRueck = "";
		
		Vector<String> vConcat = new Vector<String>();
		
		for (int i=0;i<vFieldNamesToChain.size();i++)
		{
			
			String cFieldName = vFieldNamesToChain.get(i);

			//zuerst die beschriftungsfaelle
			if (cFieldName.startsWith("#"))
			{
				vConcat.add(new MyE2_String(cFieldName.substring(1)).CTrans());
				continue;
			}
			if (cFieldName.startsWith("&"))
			{
				vConcat.add(cFieldName.substring(1));
				continue;
			}

			
			String cFeldVorne = "";
			String cFeldHinten = "";
			if (cFieldName.indexOf("@")>0)
			{
				String cLeft = cFieldName.substring(0,cFieldName.indexOf("@"));
				if (cLeft.length()>=2)
				{
					cFeldVorne = cLeft.substring(0,1);
					cFeldHinten = cLeft.substring(1,2);
				}
				cFieldName = cFieldName.substring(cFieldName.indexOf("@")+1);
			}
			
			
			if (this.containsKey(cFieldName))
			{
				String cHelp = this.get_FormatedValue(cFieldName, cValue4EmptyField);
				if (! (cHelp==null || cHelp.trim().equals("")) )
				{
					vConcat.add(cLeftFieldAddon+cFeldVorne+cHelp+cFeldHinten+cRightFieldAddon);
				}
			}
			else
			{
				throw new myException(this,"Key "+vFieldNamesToChain.get(i)+" is no Key in HashMap !!");
			}
		}

		for (int i=0;i<vConcat.size();i++)
		{
			cRueck = cRueck + cSeparator+vConcat.get(i);
		}
		
		if (cRueck.length()>0)
			cRueck = cRueck.substring(cSeparator.length());               // erster trenner weg
		
		return cRueck;
	}


	public String get_TABLE_NAME()
	{
		return this.cTABLE_NAME;
	}


	public void set_TABLE_NAME(String ctable_name)
	{
		this.cTABLE_NAME = ctable_name;
	}

	
	/*
	 * 2011-03-17: hilfsmethode fuer die sortierung der reclists
	 */
	public String get_SORTSTRING(String cFieldName) throws myException
	{
		String cRueck = "";
		
		if (this.containsKey(cFieldName))
		{
			MyResultValue  result = this.get(cFieldName);
			
		    MyMetaFieldDEF   meta =result.get_MetaFieldDef();
		    
		    if (meta.get_bIsTextType())
		    {
		    	cRueck = S.NN(result.get_FieldValueUnformated()).trim();
		    	//cRueck = S.makeStringLonger(cRueck," ",meta.get_FieldTextLENGTH(),true);
		    	//2011-09-09: Fehler bei der sortierung: der sortierstring muss rechtss verlaengert werden
		    	cRueck = S.makeStringLonger(cRueck," ",meta.get_FieldTextLENGTH(),false);
		    	
		    }
		    else if (meta.get_bIsNumberTypeWithOutDecimals())
		    {
		    	int iLen = meta.get_FieldNumberLENGTH();
		    	cRueck = S.NN(result.get_FieldValueUnformated());
		    	
		    	cRueck = S.makeStringLonger(cRueck, "0", iLen, true);
		    }
		    else if (meta.get_bIsNumberTypeWithDecimals())
		    {
		    	int iLen = 		meta.get_FieldNumberLENGTH();
		    	int iScale = 	meta.get_FieldDecimals();
		    	cRueck = S.NN(result.get_FieldValueUnformated());
		    	
		    	if (S.isEmpty(cRueck))
		    	{
		    		cRueck = ".";
		    	}
		    	String cVorKomma = cRueck.substring(0,cRueck.indexOf("."));
		    	String cNachKomma = cRueck.substring(cRueck.indexOf(".")+1);
		    	
		    	cRueck = S.makeStringLonger(cVorKomma, "0", iLen, true)+"."+S.makeStringLonger(cNachKomma, "0", iScale, false);
		    }
		    else if (meta.get_bIsDateType())
		    {
		    	cRueck = S.NN(result.get_cVALUE_FOR_SQLSTATEMENT()).replace('\'', ' ').trim();
		    	if (cRueck.equals("NULL"))
		    	{
		    		cRueck = "    -  -  ";
		    	}
		    }
		}
		else
		{
			throw new myException(this,"Field <"+cFieldName+"> not in Hashmap !");
		}
		
		return cRueck;
	}
	
	
	//2013-01-07: datevalue
	public Date  get_DateValue(String cRowName, Date dateValueWhenNULL) throws myException
	{
		if (this.get(cRowName).get_MetaFieldDef().get_bIsDateType())
		{
			if (this.get(cRowName).getDateValue()==null)
			{
				return dateValueWhenNULL;
			}
			else
			{
				return this.get(cRowName).getDateValue();
			}
		}
		else
		{
			throw new myException(this, "Error: Type is no date-Type !!");
		}
		
	}
	
	
	//2013-01-08: komplettes datum
	public Date  get_TimeStampValue(String cRowName, Date dateValueWhenNULL) throws myException
	{
		if (this.get(cRowName).get_MetaFieldDef().get_bIsDateType())
		{
			if (this.get(cRowName).getTimeStampValue()==null)
			{
				return dateValueWhenNULL;
			}
			else
			{
				return this.get(cRowName).getTimeStampValue();
			}
		}
		else
		{
			throw new myException(this, "Error: Type is no date-Type !!");
		}
		
	}

	
	//2013-09-20: hashmap mit field-metadefs gleich mitaufbauen
	public HashMap<String, MyMetaFieldDEF> get_hm_FieldMetaDefs() {
		return hm_FieldMetaDefs;
	}

	
	//2013-09-20: im normalfall ist in den MyFieldMetaDefs keine Tablename-Angabe vorhande
	//in den speziell erzeugten record-objketen der Tabellen steht der Tablename aber fest.
	//mit dieser methode kann dieser Tablename an die MyFieldMetaDefs uebergeben werden
	public void set_Tablename_To_FieldMetaDefs(String tablename) {
		for (MyMetaFieldDEF oMeta: this.hm_FieldMetaDefs.values()) {
			oMeta.set_Tablename(tablename.toUpperCase(), false);
		}
	}
	
	

	//2014-04-02
	public void set_cSQL_4_Build(String sql_4_Build) {
		this.cSQL_4_Build = sql_4_Build;
	}


	//2014-04-02
	public void set_oConn(MyConnection conn) {
		this.oConn = conn;
	}


	
	//2015-02-17: nachsehen, ob es einen resultvalue-wert gibt, der veraendert wurde
	public boolean get_bAnyFieldIsRealyChanged() throws myException {
		for (MyResultValue result: this.values()) {
			if (result.get_bDatabaseValue_Was_Set_from_outside_And_Is_Different()) {
				return true;
			}
		}
		return false;
	}


	/** 2015-02-26
	 * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
	 */
	@Override
    public MySqlStatementBuilder get_StatementBuilder(boolean bExcludeAutomaticFields) throws myException   {
    	return this.get_StatementBuilderFilledWithActualValues(bExcludeAutomaticFields);
    }

	/** 2015-02-26
	 * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
	 */
	@Override
	public String get_TABLENAME() throws myException {
		return this.cTABLE_NAME;
	}

	/** 2015-02-26
	 * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
	 */
	@Override
	public String get_PRIMARY_KEY_NAME() throws myException {
		return "ID_"+this.cTABLE_NAME.substring(3);
	}

	/** 2015-02-26
	 * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
	 */
	@Override
	public boolean get_bHasSomething_to_save() throws myException {
		return this.get_bAnyFieldIsRealyChanged();
	}


	@Override
	public HashMap<String, String> get_hm_InputValuesFormated() throws myException {
		HashMap<String, String>  hmRueck = new HashMap<String, String>();
		
		for (String field: this.keySet()) {
			hmRueck.put(field, this.get(field).get_actual_formated_string_for_save());
		}
		return hmRueck;
	}
	
	//2015-03-05: zusatzpaare feld-wert von aussen fuer die erzeugung der statementbuilders
	@Override
	public HashMap<String, String> get_hm_Field_Value_pairs_from_outside() {
		return hm_Field_Value_pairs_from_outside;
	}

	/**
	 * 
	 * martin: 2015-03-26
	 * @param msg_sammler
	 * @param changeIdWithSeq
	 * @param bRemoveAutomaticField
	 * @return MyRecordNEW-object filled with values of actual record (to generate copy)
	 * @throws myException
	 */
	public MyRECORD_NEW get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
		if (S.isEmpty(this.get_TABLENAME())) {
			throw new myException(this, "generate_FilledRecord_NEW(): no Tablename is present !" );
		}
		
		MyRECORD_NEW  recNew = new MyRECORD_NEW(this.get_TABLENAME());
		
		Vector<String> vSonderFelder = this.get_vSonderFelder();
		
		for (String Field: this.keySet()) {
			if (bRemoveAutomaticField) {
				if (vSonderFelder.contains(Field)) {
					continue;
				}
			}
			msg_sammler.add_MESSAGE(recNew.set_NewValueForDatabase(Field, this.get_FormatedValue(Field,null)));
		}
		
		if (changeIdWithSeq) {
			recNew.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(this.get_PRIMARY_KEY_NAME(), "SEQ_"+this.get_TABLE_NAME().substring(3)+".NEXTVAL");
		}
		return recNew;
	}
	
	
	
	
	
	
	/**
	 * 2015-05-06: ExecMultiSQLVector ausgelagert in eine eigene Methode, damit die eigene DBToolBox verwendet wird
	 * @param vSQLStack
	 * @param bCommit
	 * @return
	 * @throws myException 
	 */
	public MyE2_MessageVector ExecMultiSQLVector(Vector<String> vSQLStack, boolean bCommit) throws myException {
		MyDBToolBox oDB = this.generate_DBToolBox(this.oConn);
		MyE2_MessageVector VRueck = oDB.ExecMultiSQLVector(vSQLStack, bCommit);
		bibALL.destroy_myDBToolBox(oDB);
		return VRueck;
	}
	
	
	/**
	 * 2015-05-06: das holen der DBToolBox hier ausgelagert in eine eigene Methode, die ueberschrieben werden kann
	 * @return
	 * @throws myException
	 */
	public MyDBToolBox  generate_DBToolBox(MyConnection conn) throws myException {
		if (this.DBToolBox_FAB!=null) {
			return this.DBToolBox_FAB.generate_INDIVIDUELL_DBToolBox(conn);
		} else {
			return MyDBToolBox_FAB.generate_STANDARD_DBToolBox(conn);	
		}
	}

	
	
	/**
	 * 2015-05-06: die sonderfelder werden jetzt aus der (evtl. eigenen) MyDBToolBox gelesen, nicht mehr global
	 * @return
	 */
	public Vector<String> get_vSonderFelder() throws myException {
		Vector<String> vRueck = new Vector<String>();
		
		MyDBToolBox tb_temp = this.generate_DBToolBox(this.oConn);
		vRueck.addAll(tb_temp.get_AutomaticWrittenFields());
		bibALL.destroy_myDBToolBox(tb_temp);

		return vRueck;
	}


	public MyDBToolBox_FAB get_DBToolBox_FAB() {
		return this.DBToolBox_FAB;
	}


	public void set_DBToolBox_FAB(MyDBToolBox_FAB dBToolBox_FAB) {
		this.DBToolBox_FAB = dBToolBox_FAB;
	}

	
	
	
	//2016-01-29: neue getter-methoden mit IF_Fields als parameter
	
	/**
	 * 
	 * @param field
	 * @return formated String
	 * @throws myException
	 */
	public String fs(IF_Field field) throws myException {
		if (field == null) {
			throw new myException("MyRecord:fs:"+" field MUST not be null!");
		}
		
		if (!this.containsKey(field.fn()) || this.get(field.fn())==null)
			throw new myException("MyRecord:get_FormatedValue:"+field.fn()+" not in MAP!");
		
		return this.get(field.fn()).get_FieldValueFormated();
	}

	/**
	 * 
	 * @param field
	 * @return unformated String
	 * @throws myException
	 */
	public String ufs(IF_Field field) throws myException {
		if (field == null) {
			throw new myException("MyRecord:fs:"+" field MUST not be null!");
		}
		
		if (!this.containsKey(field.fn()) || this.get(field.fn())==null)
			throw new myException("MyRecord:get_FormatedValue:"+field.fn()+" not in MAP!");
		
		return this.get(field.fn()).get_FieldValueUnformated();
	}


	/**
	 * 
	 * @param field
	 * @param cValueWhenNull
	 * @return Formated String
	 * @throws myException
	 */
	public String fs(IF_Field field, String cValueWhenNull) throws myException {
		if (this.fs(field) == null) 		{
			return cValueWhenNull;
		} else {
			return this.fs(field);
		}
	}

	/**
	 * 
	 * @param field
	 * @param cValueWhenNull
	 * @return unformated String
	 * @throws myException
	 */
	public String ufs(IF_Field field, String cValueWhenNull) throws myException {
		if (this.ufs(field) == null) 		{
			return cValueWhenNull;
		} else {
			return this.ufs(field);
		}
	}

	
	/**
	 * 
	 * @param name
	 * @param cValueWhenNull
	 * @return unformated String
	 * @throws myException
	 */
	public String ufs(String name, String cValueWhenNull) throws myException {
		if (this.get(name) == null) {
			throw new myException(this,"ufs():"+name+" not in MyRecord!");
		}
		return this.get_UnFormatedValue(name, cValueWhenNull);
	}

	/**
	 * 
	 * @param name
	 * @return unformated String,  null when empty
	 * @throws myException
	 */
	public String ufs(String name) throws myException {
		return this.get_UnFormatedValue(name, null);
	}

	
	/**
	 * 
	 * @param name
	 * @param cValueWhenNull
	 * @return formated String
	 * @throws myException
	 */
	public String fs(String name, String cValueWhenNull) throws myException {
		if (this.get(name) == null) {
			//debug-code
			Vector<String> v_fields = new Vector<>(this.keySet());
			DEBUG.System_print(v_fields, true);
			//debug-code -ende
			
			throw new myException(this,"fs():"+name+" not in MyRecord!");
		}
		return this.get_FormatedValue(name, cValueWhenNull);
	}

	/**
	 * 
	 * @param name
	 * @return formated String,  null when empty
	 * @throws myException
	 */
	public String fs(String name) throws myException {
		return this.get_FormatedValue(name, null);
	}

	
	
	
	/**
	 * 
	 * @param field
	 * @return String for SQL-statement
	 * @throws myException
	 */
	public String sql(IF_Field field) throws myException {
		if (!this.containsKey(field.fn())  || this.get(field.fn())==null) {
			throw new myException(this,"sql():"+field.fn()+" not in MyRecord!");
		}
		return this.get(field.fn()).get_cVALUE_FOR_SQLSTATEMENT();
	}
	

	/**
	 * 
	 * @param field
	 * @return Double
	 * @throws myException
	 */
	public Double d(IF_Field field) throws myException {
		if (!this.containsKey(field.fn())  || this.get(field.fn())==null) {
			throw new myException(this,"d():"+field.fn()+" not in MyRecord!");
		}
		return this.get(field.fn()).getDoubleValue();
	}
	
	
	/**
	 * 
	 * @param field
	 * @param dWhenNull
	 * @return Double
	 * @throws myException
	 */
	public Double d(IF_Field field, Double dWhenNull) throws myException {
		if (this.d(field) == null) 		{
			return dWhenNull;
		} else {
			return this.d(field);
		}
	}
	
	
	/**
	 * 
	 * @param field
	 * @return Long 
	 * @throws myException
	 */
	public Long l(IF_Field field) throws myException {
		if (!this.containsKey(field.fn()) || this.get(field.fn())==null)
			throw new myException(this,"l:"+field.fn()+" not in MyRecord!");
		
		return this.get(field.fn()).getLongValue();
	}
	
	/**
	 * 
	 * @param field
	 * @param l_when_null
	 * @return Long 
	 * @throws myException
	 */
	public Long l(IF_Field field, Long l_when_null) throws myException {
		if (this.l(field) == null) 		{
			return l_when_null;
		} else {
			return this.l(field);
		}
	}
	

	
	
	/**
	 * 
	 * @param field
	 * @param bdValueWhenNULL
	 * @param iNachkommaRunden
	 * @return BigDecimal
	 * @throws myException
	 */
	public BigDecimal bd(IF_Field field, BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException 	{
		
		if (!this.containsKey(field.fn())  || this.get(field.fn())==null) {
			throw new myException(this,"bd():"+field.fn()+" not in MyRecord!");
		}

		BigDecimal bdRueck = this.get(field.fn()).getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) { 
			return null; 
		} else	{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}

	

	
	public MyRECORD _add_sequencer() throws myException {
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}
		
		String baseName = this.cTABLE_NAME.substring(3);
		this.hm_Field_Value_pairs_from_outside.put("ID_"+baseName, "SEQ_"+baseName+".NEXTVAL");
		return this;
	}
	
	public MyRECORD _add_id_mandant() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(AUTOMATC_FIELDS.ID_MANDANT.toString(), bibALL.get_ID_MANDANT());
		return this;
	}
	
	public MyRECORD _add_timestamp() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(AUTOMATC_FIELDS.LETZTE_AENDERUNG.toString(), "SYSDATE");
		return this;
	}
	
	public MyRECORD _add_user() throws myException {
		this.hm_Field_Value_pairs_from_outside.put(AUTOMATC_FIELDS.GEAENDERT_VON.toString(), bibALL.MakeSql(bibALL.get_KUERZEL()));
		return this;
	}
	
	/**
	 * speichert den record ohne jegliche automatismen, keine id, keine mandenten- user oder timestamps
	 * @param commit
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public MyRECORD SAVE_RAW(boolean commit, MyE2_MessageVector mv) throws myException {
			
		
		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "do_WRITE_NEW_RECORD(): no Tablename is present !" );
		}
		
		_TAB 		tab = 		_TAB.find_Table(this.cTABLE_NAME);
		IF_Field  	id_field = 	_TAB.find_field(tab, tab.keyFieldName());
		String 		id_val = 	this.ufs(id_field);
		
		Vector<String> vSQL = new Vector<String>();
		vSQL.add(this.get_StatementBuilder(false).get_CompleteUPDATEString(this.cTABLE_NAME, bibE2.cTO(), new vgl(id_field,id_val).s(), null));

		MyDBToolBox oDB = MyDBToolBox_FAB.get_myDBToolBox(false, false, this.oConn);
		mv.add_MESSAGE(oDB.ExecMultiSQLVector(vSQL, commit));
		bibALL.destroy_myDBToolBox(oDB);
		
		return this;
	}

	
	/**
	 * gibt das kompette sql-insert-statement zurueck, ohne irgendein automatisches feld zu entfernen
	 * @return
	 * @throws myException
	 */
	public String get_RAW_SQL_4_SAVE() throws myException {

		if (S.isEmpty(this.cTABLE_NAME)) {
			throw new myException(this, "get_RAW_SQL_4_SAVE(): no Tablename is present !" );
		}

		_TAB 		tab = 		_TAB.find_Table(this.cTABLE_NAME);
		IF_Field  	id_field = 	_TAB.find_field(tab, tab.keyFieldName());
		String 		id_val = 	this.ufs(id_field);

		
		return this.get_StatementBuilder(false).get_CompleteUPDATEString(this.cTABLE_NAME, bibE2.cTO(), new vgl(id_field,id_val).s(), null);
	}

	
	/** 
	 * 2016-09-07: martin
	 * sorgt dafuer, dass abfragen auf den basisadressen und speichervorgaenge ohne automatische zusatzfelder ausgefuehrt werden
	 */
	public void set_to_raw_state() {
		this.set_DBToolBox_FAB(new MyDBToolBox_FAB_raw());
	}
	
	
}
