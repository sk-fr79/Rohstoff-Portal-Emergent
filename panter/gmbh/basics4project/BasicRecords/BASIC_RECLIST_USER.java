package panter.gmbh.basics4project.BasicRecords;

import java.sql.ResultSetMetaData;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.exceptions.myException;


public class BASIC_RECLIST_USER extends HashMap<String,BASIC_RECORD_USER>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private String        	cKeyField = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_USER() throws myException
	{
		super();
		this.cKeyField = "ID_USER";
	}


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(MyConnection Conn) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.cKeyField = "ID_USER";
	}



	/**
	 * 
	 * @param QueryString !!ID_USER muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(String QueryString) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = "ID_USER";
		
		this.REFRESH();
	}

	
	/**
	 * 
	 * @param WhereStatment or null
	 * @param OrderStatment or null
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER";
		
		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
		}

		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
		}
		
		this.cKeyField = "ID_USER";
		
		this.REFRESH();
	}

	

	/**
	 * 
	 * @param WhereStatment or null
	 * @param OrderStatment or null
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER";
		
		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
		}

		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
		}
		
		this.cKeyField = "ID_USER";
		
		this.REFRESH();
	}


	
	

	/**
	 * 
	 * @param QueryString !!ID_USER muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(String QueryString, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField =  "ID_USER";

		this.REFRESH();
	}



	//2012-02-10: neue konstruktoren: uebergabe eines vectors aus unformated ids
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER IN("+cID_Block+")";
		this.cKeyField =  "ID_USER";

		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER IN("+cID_Block+")";
		this.cKeyField =  "ID_USER";

		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(int MaxNumberOfRecords) throws myException
	{
		super();
		this.cKeyField = "ID_USER";
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.cKeyField = "ID_USER";
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_USER muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(String QueryString,int MaxNumberOfRecords) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = "ID_USER";
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
		
		this.REFRESH();
	}

	

	/**
	 * 
	 * @param QueryString !!ID_USER muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField =  "ID_USER";
		this.iMaxNumberOfRecords = MaxNumberOfRecords;

		this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!cOwnKeyField muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @param MaxNumberOfRecords    (0=unbegrenzt)
	 * @param cOwnKeyField          (damit kann ein zusammengesetzter key z.b. benutzt werden)
	 * @throws myException
	 */
	public BASIC_RECLIST_USER(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField =  cOwnKeyField;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;

		this.REFRESH();
	}


	/**
	 * * @param cKeyFieldName   (kann benutzt werden, wenn einer leeren Recordlist record einer manuell aufgebauten Reclist uebergeben werden sollen)
	 */
	public void set_KEYFIELD(String cKeyFieldName)
	{
		this.cKeyField = cKeyFieldName;
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
		
		// Hashmap zum zwischenspeichern der Metainformationen
		HashMap<Integer, MyMetaFieldDEF> oMetaFieldDef = new HashMap<Integer, MyMetaFieldDEF>();
		
		
		if (oRS.RS != null)
		{
            try
            {
                int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

				// die metadaten nur einmal erzeugen
                for (int i = 0; i < iAnzahlSpalten; i++){
                  		ResultSetMetaData o = (ResultSetMetaData) oRS.RS.getMetaData();
                  		oMetaFieldDef.put( Integer.valueOf(i), new MyMetaFieldDEF(o,i, (String)null));
                }         
				

                int iCount = 0;
                if (iAnzahlSpalten > 0)
                {
                    while (oRS.RS.next())
                    {
                    	String 		cHASHKEY = 	null;     //wird waehrend des lesens ermittelt
                      	BASIC_RECORD_USER  	oRec = 		new BASIC_RECORD_USER(this.oConn);
                      	
                      	for (int i = 0; i < iAnzahlSpalten; i++)
                        {
                        	MyResultValue oRSV = new MyResultValue(oMetaFieldDef.get(Integer.valueOf(i)) ,oRS.RS,false);
                        	
                        	if (oRSV.get_cFieldLABEL().equals(this.cKeyField))
                        	{
                        		cHASHKEY = oRSV.get_FieldValueUnformated();    //oRSV ist in der Regel ein INDEXFELD
                        	}
                        	
                        	oRec.ADD(oRSV,false);
                        }
                        iCount++;
                        
                        if (this.iMaxNumberOfRecords>0 && iCount>this.iMaxNumberOfRecords)
                        {
                        	this.bOverFlow = true;
                        	this.vKeyValues.removeAllElements();
							this.clear();
                        	break;
                        }

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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_USER: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
	public BASIC_RECORD_USER get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_USER!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
	public BASIC_RECORD_USER get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(BASIC_RECORD_USER oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, BASIC_RECORD_USER oRECORD) throws myException;
	}



	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(BASIC_RECLIST_USER.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(BASIC_RECLIST_USER oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
				this.put(cIDaddon, oAddonList.get(cIDaddon));
			}
		}
	}
	
	
	public void ADD(BASIC_RECORD_USER oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding BASIC_RECORD_USER: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(BASIC_RECLIST_USER oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public BASIC_RECLIST_USER get_SUBLIST(BASIC_RECLIST_USER.Validation oValidator)  throws myException
	{
		BASIC_RECLIST_USER oRecListRueck = new BASIC_RECLIST_USER(this.oConn);
		
		for (int i=0;i<this.vKeyValues.size();i++)
		{
			if (oValidator.isValid(this.get(i)))
			{
				oRecListRueck.ADD(this.get(i), false);
			}
		}
		
		return oRecListRueck;
	}




	public  int             get_MaxNumberOfRecords()
	{
		return this.iMaxNumberOfRecords;
	}

	/**
	 * @returns true, if recordlist was too big
	 *
	 */
	public  boolean   		get_Overflow()
	{ 
		return this.bOverFlow;
	}	


	
	
	/**
	 * @returns sorted vector of records
	 *
	 */
	public Vector<BASIC_RECORD_USER>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<BASIC_RECORD_USER> vRueck = new Vector<BASIC_RECORD_USER>();
		
		for (int i=0;i<this.get_vKeyValues().size();i++)
		{
			vRueck.add(this.get(i));
		}
		
		Sorter  	oSorter = new Sorter(vSortFields,bUPDown_true_is_up);
		
		Collections.sort(vRueck,oSorter);
		
		if (oSorter.bOK)
		{
			return vRueck;
		}
		else
		{
			throw new myException(this,"GET_SORTED_VECTOR: Fields not in RECORD!!");
		}
	}
	
	
	private class Sorter implements Comparator<BASIC_RECORD_USER>
	{
		private Vector<String>  vFields = null;
		
		public boolean bOK = true;
		public boolean UP_DOWN = false;
		
		public Sorter(Vector<String> vFields, boolean bUP_DOWN) 
		{
			super();
			this.vFields = vFields;
			this.UP_DOWN = bUP_DOWN; 
		}

		
		public int compare(BASIC_RECORD_USER o1, BASIC_RECORD_USER o2) 
		{
			String cSortString1 = "";
			String cSortString2 = "";
			
			this.bOK = true;
			
			for (int i=0;i<this.vFields.size();i++)
			{
				
				try 
				{
					cSortString1 += o1.get_SORTSTRING(this.vFields.get(i));
					cSortString2 += o2.get_SORTSTRING(this.vFields.get(i));
				} 
				catch (myException e) 
				{
					this.bOK=false;
					e.printStackTrace();
				}
				
				if (!bOK)
				{
					break;
				}
			}
			
			if (this.UP_DOWN)
			{
				return cSortString1.compareTo(cSortString2);
			}
			else
			{
				return cSortString2.compareTo(cSortString1);
			}
		}

		
	}
	
	
	


	public HashMap<String, String>  get_AKTIV_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AKTIV_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AKTIV_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AKTIV_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANREDE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANREDE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANREDE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANREDE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_AUTCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUTCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AUTCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUTCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EIGENDEF_BREITEAENDERBAR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENDEF_BREITEAENDERBAR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EIGENDEF_BREITEAENDERBAR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENDEF_BREITEAENDERBAR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EIGENDEF_MENUEBREITE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENDEF_MENUEBREITE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EIGENDEF_MENUEBREITE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENDEF_MENUEBREITE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_EIGENDEF_MENUEBREITE_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_EIGENDEF_MENUEBREITE_lValue(new Long(0));
			
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

	

	public long get_EIGENDEF_MENUEBREITE_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_EIGENDEF_MENUEBREITE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_EIGENDEF_MENUEBREITE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_EIGENDEF_MENUEBREITE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_EIGENDEF_MENUEBREITE_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EIGENDEF_MENUEBREITE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_EIGENDEF_MENUEBREITE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EIGENDEF_MENUEBREITE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EIGENDEF_SCHRIFTGROESSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENDEF_SCHRIFTGROESSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EIGENDEF_SCHRIFTGROESSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENDEF_SCHRIFTGROESSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_EIGENDEF_SCHRIFTGROESSE_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_EIGENDEF_SCHRIFTGROESSE_lValue(new Long(0));
			
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

	

	public long get_EIGENDEF_SCHRIFTGROESSE_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_EIGENDEF_SCHRIFTGROESSE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_EIGENDEF_SCHRIFTGROESSE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_EIGENDEF_SCHRIFTGROESSE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_EIGENDEF_SCHRIFTGROESSE_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EIGENDEF_SCHRIFTGROESSE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_EIGENDEF_SCHRIFTGROESSE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EIGENDEF_SCHRIFTGROESSE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EMAIL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EMAIL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EMAIL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EMAIL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FENSTER_MIT_SCHATTEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FENSTER_MIT_SCHATTEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FENSTER_MIT_SCHATTEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FENSTER_MIT_SCHATTEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GESCHAEFTSFUEHRER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GESCHAEFTSFUEHRER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GESCHAEFTSFUEHRER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GESCHAEFTSFUEHRER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_HAT_FAHRPLAN_BUTTON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAT_FAHRPLAN_BUTTON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_HAT_FAHRPLAN_BUTTON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAT_FAHRPLAN_BUTTON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_SPRACHE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_SPRACHE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_SPRACHE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_SPRACHE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_SPRACHE_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_SPRACHE_lValue(new Long(0));
			
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

	

	public long get_ID_SPRACHE_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_SPRACHE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_SPRACHE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_SPRACHE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_SPRACHE_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_SPRACHE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_SPRACHE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_SPRACHE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_USER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_USER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_USER_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
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

	

	public long get_ID_USER_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_USER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_USER_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_USER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_USERGROUP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USERGROUP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_USERGROUP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USERGROUP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_USERGROUP_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USERGROUP_lValue(new Long(0));
			
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

	

	public long get_ID_USERGROUP_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USERGROUP_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_USERGROUP_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_USERGROUP_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_USERGROUP_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_USERGROUP_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_USERGROUP_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_USERGROUP_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_IST_FAHRER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_FAHRER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_FAHRER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_FAHRER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_IST_SUPERVISOR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_SUPERVISOR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_SUPERVISOR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_SUPERVISOR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_IST_VERWALTUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_VERWALTUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_VERWALTUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_VERWALTUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KUERZEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KUERZEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KUERZEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KUERZEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAUFZEIT_SESSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAUFZEIT_SESSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAUFZEIT_SESSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAUFZEIT_SESSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_LAUFZEIT_SESSION_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LAUFZEIT_SESSION_lValue(new Long(0));
			
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

	

	public long get_LAUFZEIT_SESSION_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LAUFZEIT_SESSION_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_LAUFZEIT_SESSION_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_LAUFZEIT_SESSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_LAUFZEIT_SESSION_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_LAUFZEIT_SESSION_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_LAUFZEIT_SESSION_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_LAUFZEIT_SESSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LISTEGESAMTLAENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LISTEGESAMTLAENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LISTEGESAMTLAENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LISTEGESAMTLAENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_LISTEGESAMTLAENGE_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LISTEGESAMTLAENGE_lValue(new Long(0));
			
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

	

	public long get_LISTEGESAMTLAENGE_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LISTEGESAMTLAENGE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_LISTEGESAMTLAENGE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_LISTEGESAMTLAENGE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_LISTEGESAMTLAENGE_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_LISTEGESAMTLAENGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_LISTEGESAMTLAENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_LISTEGESAMTLAENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_LISTESEITELAENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LISTESEITELAENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LISTESEITELAENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LISTESEITELAENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_LISTESEITELAENGE_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LISTESEITELAENGE_lValue(new Long(0));
			
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

	

	public long get_LISTESEITELAENGE_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LISTESEITELAENGE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_LISTESEITELAENGE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_LISTESEITELAENGE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_LISTESEITELAENGE_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_LISTESEITELAENGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_LISTESEITELAENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_LISTESEITELAENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_MAIL_SIGNATUR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAIL_SIGNATUR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MAIL_SIGNATUR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAIL_SIGNATUR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MENGENABSCHLUSS_FUHRE_OK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGENABSCHLUSS_FUHRE_OK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MENGENABSCHLUSS_FUHRE_OK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGENABSCHLUSS_FUHRE_OK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PASSWORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PASSWORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PASSWORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PASSWORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PREISABSCHLUSS_FUHRE_OK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PREISABSCHLUSS_FUHRE_OK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PREISABSCHLUSS_FUHRE_OK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PREISABSCHLUSS_FUHRE_OK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SONDERRECH_ZEIGE_OPLISTE_SALDO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SONDERRECH_ZEIGE_OPLISTE_SALDO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TELEFAX_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFAX_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TELEFAX_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFAX_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TELEFON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TELEFON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TEXTCOLLECTOR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEXTCOLLECTOR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEXTCOLLECTOR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEXTCOLLECTOR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TODO_SUPERVISOR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TODO_SUPERVISOR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TODO_SUPERVISOR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TODO_SUPERVISOR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VERTICAL_OFFSET_MASKTABS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERTICAL_OFFSET_MASKTABS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERTICAL_OFFSET_MASKTABS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERTICAL_OFFSET_MASKTABS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_VERTICAL_OFFSET_MASKTABS_l_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERTICAL_OFFSET_MASKTABS_lValue(new Long(0));
			
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

	

	public long get_VERTICAL_OFFSET_MASKTABS_l_Count_NotNull(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERTICAL_OFFSET_MASKTABS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_VERTICAL_OFFSET_MASKTABS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_VERTICAL_OFFSET_MASKTABS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_VERTICAL_OFFSET_MASKTABS_d_Summe(BASIC_RECLIST_USER.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_USER>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_USER 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_VERTICAL_OFFSET_MASKTABS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_VERTICAL_OFFSET_MASKTABS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_VERTICAL_OFFSET_MASKTABS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_VORNAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORNAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VORNAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_USER> oEntry: this.entrySet())
		{
			BASIC_RECORD_USER 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORNAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
