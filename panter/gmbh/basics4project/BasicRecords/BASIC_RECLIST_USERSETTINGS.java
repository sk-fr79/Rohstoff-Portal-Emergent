package panter.gmbh.basics4project.BasicRecords;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.exceptions.myException;

public class BASIC_RECLIST_USERSETTINGS extends HashMap<String,BASIC_RECORD_USERSETTINGS>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private String        	cKeyField = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_USERSETTINGS() throws myException
	{
		super();
		this.cKeyField = "ID_USERSETTINGS";
	}


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_USERSETTINGS(MyConnection Conn) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.cKeyField = "ID_USERSETTINGS";
	}



	/**
	 * 
	 * @param QueryString !!ID_USERSETTINGS muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public BASIC_RECLIST_USERSETTINGS(String QueryString) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = "ID_USERSETTINGS";
		
		this.REFRESH();
	}

	

	/**
	 * 
	 * @param QueryString !!ID_USERSETTINGS muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_USERSETTINGS(String QueryString, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField =  "ID_USERSETTINGS";

		this.REFRESH();
	}


	
	protected void finalize()
	{
		if (this.oDB!=null)
			bibALL.destroy_myDBToolBox(oDB);
	}

	public MyConnection get_oConn()
	{
		return oConn;
	}
	
	
	
	public void REFRESH() throws myException
	{
		if (S.isEmpty(this.cQueryString))
			throw new myException(this,"Refresh only allowed with querystring !!");
	
		this.clear();
		this.vKeyValues.removeAllElements();

		if (this.oDB == null)
			this.oDB = 	bibALL.get_myDBToolBox(this.oConn);
		
		MyDBResultSet oRS = oDB.OpenResultSet(this.cQueryString);
		
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
                    	String 		cHASHKEY = 	null;     //wird waehrend des lesens ermittelt
                      	BASIC_RECORD_USERSETTINGS  	oRec = 		new BASIC_RECORD_USERSETTINGS();
                      	
                      	for (int i = 0; i < iAnzahlSpalten; i++)
                        {
                        	MyResultValue oRSV = new MyResultValue(new MyMetaFieldDEF(oRS.RS,i, null),oRS.RS,false);
                        	
                        	if (oRSV.get_cFieldLABEL().equals(this.cKeyField))
                        	{
                        		cHASHKEY = oRSV.get_FieldValueUnformated();    //oRSV ist in der Regel ein INDEXFELD
                        	}
                        	
                        	oRec.ADD(oRSV,false);
                        }
                        iCount++;

                        if (S.isEmpty(cHASHKEY))
                        {
                        	throw new myException(this,"Field "+ this.cKeyField+" is not in the ResultSet or contains no Value. This is not allowed !!");
                        }
                        else if (this.containsKey(cHASHKEY)) 
                        {
                        	throw new myException(this,"Field "+ this.cKeyField+" has the Value "+cHASHKEY+" more than one time in the resultSet !!");
                        }
                        else
                        {
                        	this.vKeyValues.add(cHASHKEY);
                        	this.put(cHASHKEY, oRec);
                        }
                    }
                }
            }
            catch (myException ex)
            {
            	ex.printStackTrace();
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	throw ex;
            }
            catch (Exception e)
            {
            	e.printStackTrace();
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	e.printStackTrace();
            	throw new myException(e.getLocalizedMessage());
            }
 		}
		else
		{
        	bibALL.destroy_myDBToolBox(oDB);
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_USERSETTINGS: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	public BASIC_RECORD_USERSETTINGS get_(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_USERSETTINGS!");			
		}
		
		return this.get(cIndexKeyOfRecord);
	}
	
	
	
	public BASIC_RECORD_USERSETTINGS get_(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return this.get_(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(BASIC_RECORD_USERSETTINGS oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, BASIC_RECORD_USERSETTINGS oRECORD) throws myException;
	}



	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(BASIC_RECLIST_USERSETTINGS.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(BASIC_RECLIST_USERSETTINGS oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
	{
		for (String cIDaddon: oAddonList.get_vKeyValues())
		{
			if (this.vKeyValues.contains(cIDaddon))
			{
				if (!bAllowAndIgnoreDoubleIDs)
				{
					throw new myException(this,": The addOn-HashMap has a Key witch is allready in the list !");
				}
					
			}
			else
			{
				this.vKeyValues.add(cIDaddon);
				this.put(cIDaddon, oAddonList.get_(cIDaddon));
			}
		}
	}
	
	
	public void ADD(BASIC_RECORD_USERSETTINGS oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_USERSETTINGS: Double Key not allowed !");
		}
		this.vKeyValues.add(cAddedKey);
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(BASIC_RECLIST_USERSETTINGS oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get_(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public BASIC_RECLIST_USERSETTINGS get_SUBLIST(BASIC_RECLIST_USERSETTINGS.Validation oValidator)  throws myException
	{
		BASIC_RECLIST_USERSETTINGS oRecListRueck = new BASIC_RECLIST_USERSETTINGS(this.oConn);
		
		for (int i=0;i<this.vKeyValues.size();i++)
		{
			if (oValidator.isValid(this.get_(i)))
			{
				oRecListRueck.ADD(this.get_(i), false);
			}
		}
		
		return oRecListRueck;
	}




	
	


	public HashMap<String, String>  get_ID_USERSETTINGS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USERSETTINGS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_USERSETTINGS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USERSETTINGS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_USERSETTINGS_l_Summe(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USERSETTINGS_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_USERSETTINGS_l_Count_NotNull(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USERSETTINGS_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck ++;
				}
			}
		}
		
		return dRueck;
	}




	
	public HashMap<String, Long>  get_ID_USERSETTINGS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_USERSETTINGS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_USERSETTINGS_d_Summe(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_USERSETTINGS_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_USERSETTINGS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_USERSETTINGS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_USER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_USER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_USER_l_Summe(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USER_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_USER_l_Count_NotNull(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USER_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck ++;
				}
			}
		}
		
		return dRueck;
	}




	
	public HashMap<String, Long>  get_ID_USER_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_USER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_USER_d_Summe(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_USER_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_USER_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_USER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_HASHVALUE_SESSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HASHVALUE_SESSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_HASHVALUE_SESSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HASHVALUE_SESSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MODUL_KENNER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MODUL_KENNER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MODUL_KENNER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MODUL_KENNER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MODULSETTINGS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MODULSETTINGS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MODULSETTINGS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MODULSETTINGS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_MANDANT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_MANDANT_l_Count_NotNull(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_MANDANT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck ++;
				}
			}
		}
		
		return dRueck;
	}




	
	public HashMap<String, Long>  get_ID_MANDANT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(BASIC_RECLIST_USERSETTINGS.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USERSETTINGS>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_MANDANT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_MANDANT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USERSETTINGS> oEntry: this.entrySet())
		{
			BASIC_RECORD_USERSETTINGS 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
