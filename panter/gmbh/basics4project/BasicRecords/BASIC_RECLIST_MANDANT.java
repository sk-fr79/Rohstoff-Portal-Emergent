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


public class BASIC_RECLIST_MANDANT extends HashMap<String,BASIC_RECORD_MANDANT>
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
	public BASIC_RECLIST_MANDANT() throws myException
	{
		super();
		this.cKeyField = "ID_MANDANT";
	}


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(MyConnection Conn) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.cKeyField = "ID_MANDANT";
	}



	/**
	 * 
	 * @param QueryString !!ID_MANDANT muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(String QueryString) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = "ID_MANDANT";
		
		this.REFRESH();
	}

	
	/**
	 * 
	 * @param WhereStatment or null
	 * @param OrderStatment or null
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT";
		
		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
		}

		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
		}
		
		this.cKeyField = "ID_MANDANT";
		
		this.REFRESH();
	}

	

	/**
	 * 
	 * @param WhereStatment or null
	 * @param OrderStatment or null
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT";
		
		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
		}

		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
		}
		
		this.cKeyField = "ID_MANDANT";
		
		this.REFRESH();
	}


	
	

	/**
	 * 
	 * @param QueryString !!ID_MANDANT muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(String QueryString, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField =  "ID_MANDANT";

		this.REFRESH();
	}



	//2012-02-10: neue konstruktoren: uebergabe eines vectors aus unformated ids
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_MANDANT IN("+cID_Block+")";
		this.cKeyField =  "ID_MANDANT";

		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_MANDANT IN("+cID_Block+")";
		this.cKeyField =  "ID_MANDANT";

		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(int MaxNumberOfRecords) throws myException
	{
		super();
		this.cKeyField = "ID_MANDANT";
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.cKeyField = "ID_MANDANT";
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_MANDANT muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(String QueryString,int MaxNumberOfRecords) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = "ID_MANDANT";
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
		
		this.REFRESH();
	}

	

	/**
	 * 
	 * @param QueryString !!ID_MANDANT muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public BASIC_RECLIST_MANDANT(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField =  "ID_MANDANT";
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
	public BASIC_RECLIST_MANDANT(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	BASIC_RECORD_MANDANT  	oRec = 		new BASIC_RECORD_MANDANT(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_MANDANT: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
	public BASIC_RECORD_MANDANT get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_MANDANT!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
	public BASIC_RECORD_MANDANT get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(BASIC_RECORD_MANDANT oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, BASIC_RECORD_MANDANT oRECORD) throws myException;
	}



	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(BASIC_RECLIST_MANDANT.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(BASIC_RECLIST_MANDANT oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(BASIC_RECORD_MANDANT oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding BASIC_RECORD_MANDANT: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(BASIC_RECLIST_MANDANT oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public BASIC_RECLIST_MANDANT get_SUBLIST(BASIC_RECLIST_MANDANT.Validation oValidator)  throws myException
	{
		BASIC_RECLIST_MANDANT oRecListRueck = new BASIC_RECLIST_MANDANT(this.oConn);
		
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
	public Vector<BASIC_RECORD_MANDANT>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<BASIC_RECORD_MANDANT> vRueck = new Vector<BASIC_RECORD_MANDANT>();
		
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
	
	
	private class Sorter implements Comparator<BASIC_RECORD_MANDANT>
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

		
		public int compare(BASIC_RECORD_MANDANT o1, BASIC_RECORD_MANDANT o2) 
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
	
	
	


	public HashMap<String, String>  get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALLOW_EDIT_PRICE_IN_FUHREN_RG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANR1_GLEICHHEIT_FUHRE_STELLEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANR1_GLEICHHEIT_FUHRE_STELLEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ANR1_GLEICHHEIT_FUHRE_STELLEN_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_lValue(new Long(0));
			
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

	

	public long get_ANR1_GLEICHHEIT_FUHRE_STELLEN_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ANR1_GLEICHHEIT_FUHRE_STELLEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ANR1_GLEICHHEIT_FUHRE_STELLEN_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ANR1_GLEICHHEIT_FUHRE_STELLEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANR1_GLEICHHEIT_FUHRE_STELLEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANREDE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANREDE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANREDE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANREDE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_AUSSEN_STEUER_VERMERK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUSSEN_STEUER_VERMERK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AUSSEN_STEUER_VERMERK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUSSEN_STEUER_VERMERK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_BANK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_BANK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_BANK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_BANK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_BLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_BLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_BLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_BLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_EMAIL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_EMAIL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_EMAIL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_EMAIL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_HANDELSREG_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_HANDELSREG_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_HANDELSREG_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_HANDELSREG_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_KONTO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_KONTO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_KONTO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_KONTO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_REGISTERGERICHT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_REGISTERGERICHT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_REGISTERGERICHT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_REGISTERGERICHT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_STEUERNR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_STEUERNR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_STEUERNR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_STEUERNR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_TELEFAX_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_TELEFAX_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_TELEFAX_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_TELEFAX_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_TELEFON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_TELEFON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_TELEFON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_TELEFON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_USTID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_USTID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_USTID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_USTID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BELEGDRUCK_WWW_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_WWW_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BELEGDRUCK_WWW_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BELEGDRUCK_WWW_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_ABANGEB_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_ABANGEB_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_ABANGEB_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_ABANGEB_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_EKK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_EKK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_EKK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_EKK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_GUT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_GUT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_GUT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_GUT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_LIEFANGEB_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_LIEFANGEB_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_LIEFANGEB_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_LIEFANGEB_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_RECH_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_RECH_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_RECH_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_RECH_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_TPA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_TPA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_TPA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_TPA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_VKK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_VKK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSPREFIX_VKK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSPREFIX_VKK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_COLOR_BLUE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_BLUE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_BLUE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_BLUE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_BLUE_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_BLUE_lValue(new Long(0));
			
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

	

	public long get_COLOR_BLUE_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_BLUE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_BLUE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_BLUE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_BLUE_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_BLUE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_BLUE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_BLUE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_DIFF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_DIFF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_DIFF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_DIFF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_DIFF_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_DIFF_lValue(new Long(0));
			
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

	

	public long get_COLOR_DIFF_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_DIFF_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_DIFF_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_DIFF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_DIFF_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_DIFF_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_DIFF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_DIFF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_GREEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_GREEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_GREEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_GREEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_GREEN_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_GREEN_lValue(new Long(0));
			
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

	

	public long get_COLOR_GREEN_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_GREEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_GREEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_GREEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_GREEN_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_GREEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_GREEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_GREEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_MASK_HIGHLIGHT_BLUE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_MASK_HIGHLIGHT_BLUE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_MASK_HIGHLIGHT_BLUE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_MASK_HIGHLIGHT_BLUE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_MASK_HIGHLIGHT_BLUE_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_BLUE_lValue(new Long(0));
			
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

	

	public long get_COLOR_MASK_HIGHLIGHT_BLUE_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_BLUE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_MASK_HIGHLIGHT_BLUE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_MASK_HIGHLIGHT_BLUE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_MASK_HIGHLIGHT_BLUE_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_BLUE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_MASK_HIGHLIGHT_BLUE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_MASK_HIGHLIGHT_BLUE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_MASK_HIGHLIGHT_GREEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_MASK_HIGHLIGHT_GREEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_MASK_HIGHLIGHT_GREEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_MASK_HIGHLIGHT_GREEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_MASK_HIGHLIGHT_GREEN_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_GREEN_lValue(new Long(0));
			
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

	

	public long get_COLOR_MASK_HIGHLIGHT_GREEN_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_GREEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_MASK_HIGHLIGHT_GREEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_MASK_HIGHLIGHT_GREEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_MASK_HIGHLIGHT_GREEN_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_GREEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_MASK_HIGHLIGHT_GREEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_MASK_HIGHLIGHT_GREEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_MASK_HIGHLIGHT_RED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_MASK_HIGHLIGHT_RED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_MASK_HIGHLIGHT_RED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_MASK_HIGHLIGHT_RED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_MASK_HIGHLIGHT_RED_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_RED_lValue(new Long(0));
			
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

	

	public long get_COLOR_MASK_HIGHLIGHT_RED_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_RED_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_MASK_HIGHLIGHT_RED_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_MASK_HIGHLIGHT_RED_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_MASK_HIGHLIGHT_RED_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_MASK_HIGHLIGHT_RED_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_MASK_HIGHLIGHT_RED_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_MASK_HIGHLIGHT_RED_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_POPUP_TITEL_BLUE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_POPUP_TITEL_BLUE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_POPUP_TITEL_BLUE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_POPUP_TITEL_BLUE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_POPUP_TITEL_BLUE_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_POPUP_TITEL_BLUE_lValue(new Long(0));
			
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

	

	public long get_COLOR_POPUP_TITEL_BLUE_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_POPUP_TITEL_BLUE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_POPUP_TITEL_BLUE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_POPUP_TITEL_BLUE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_POPUP_TITEL_BLUE_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_POPUP_TITEL_BLUE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_POPUP_TITEL_BLUE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_POPUP_TITEL_BLUE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_POPUP_TITEL_GREEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_POPUP_TITEL_GREEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_POPUP_TITEL_GREEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_POPUP_TITEL_GREEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_POPUP_TITEL_GREEN_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_POPUP_TITEL_GREEN_lValue(new Long(0));
			
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

	

	public long get_COLOR_POPUP_TITEL_GREEN_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_POPUP_TITEL_GREEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_POPUP_TITEL_GREEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_POPUP_TITEL_GREEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_POPUP_TITEL_GREEN_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_POPUP_TITEL_GREEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_POPUP_TITEL_GREEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_POPUP_TITEL_GREEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_POPUP_TITEL_RED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_POPUP_TITEL_RED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_POPUP_TITEL_RED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_POPUP_TITEL_RED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_POPUP_TITEL_RED_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_POPUP_TITEL_RED_lValue(new Long(0));
			
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

	

	public long get_COLOR_POPUP_TITEL_RED_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_POPUP_TITEL_RED_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_POPUP_TITEL_RED_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_POPUP_TITEL_RED_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_POPUP_TITEL_RED_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_POPUP_TITEL_RED_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_POPUP_TITEL_RED_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_POPUP_TITEL_RED_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_COLOR_RED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_RED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_COLOR_RED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_COLOR_RED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_COLOR_RED_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_RED_lValue(new Long(0));
			
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

	

	public long get_COLOR_RED_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_COLOR_RED_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_COLOR_RED_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_COLOR_RED_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_COLOR_RED_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_COLOR_RED_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_COLOR_RED_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_COLOR_RED_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EIGENE_ADRESS_ID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENE_ADRESS_ID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EIGENE_ADRESS_ID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EIGENE_ADRESS_ID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_EIGENE_ADRESS_ID_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_EIGENE_ADRESS_ID_lValue(new Long(0));
			
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

	

	public long get_EIGENE_ADRESS_ID_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_EIGENE_ADRESS_ID_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_EIGENE_ADRESS_ID_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_EIGENE_ADRESS_ID_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_EIGENE_ADRESS_ID_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EIGENE_ADRESS_ID_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_EIGENE_ADRESS_ID_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EIGENE_ADRESS_ID_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FILENAME_INTRASTAT_IN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FILENAME_INTRASTAT_IN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FILENAME_INTRASTAT_IN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FILENAME_INTRASTAT_IN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FILENAME_INTRASTAT_OUT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FILENAME_INTRASTAT_OUT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FILENAME_INTRASTAT_OUT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FILENAME_INTRASTAT_OUT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIRMENBLOCKRECHTSOBEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMENBLOCKRECHTSOBEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIRMENBLOCKRECHTSOBEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMENBLOCKRECHTSOBEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIRMENBLOCKSEITENFUSS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMENBLOCKSEITENFUSS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIRMENBLOCKSEITENFUSS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMENBLOCKSEITENFUSS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GRENZE_MENG_DIFF_ABRECH_PROZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GRENZE_MENG_DIFF_ABRECH_PROZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GRENZE_MENG_DIFF_ABRECH_PROZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GRENZE_MENG_DIFF_ABRECH_PROZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GRENZE_MENG_DIFF_ABRECH_PROZ_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GRENZE_MENG_DIFF_ABRECH_PROZ_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_GRENZE_MENG_DIFF_ABRECH_PROZ_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GRENZE_MENG_DIFF_ABRECH_PROZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_HAT_ABZUEGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAT_ABZUEGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_HAT_ABZUEGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAT_ABZUEGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_DUMMY_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_DUMMY_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_DUMMY_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_DUMMY_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_BEZ_DUMMY_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_DUMMY_lValue(new Long(0));
			
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

	

	public long get_ID_ARTIKEL_BEZ_DUMMY_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_DUMMY_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ARTIKEL_BEZ_DUMMY_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_BEZ_DUMMY_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_BEZ_DUMMY_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ARTIKEL_BEZ_DUMMY_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ARTIKEL_BEZ_DUMMY_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_BEZ_DUMMY_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_lValue(new Long(0));
			
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

	

	public long get_ID_LAND_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WAEHRUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WAEHRUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WAEHRUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WAEHRUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WAEHRUNG_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WAEHRUNG_lValue(new Long(0));
			
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

	

	public long get_ID_WAEHRUNG_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WAEHRUNG_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WAEHRUNG_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WAEHRUNG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WAEHRUNG_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WAEHRUNG_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_WAEHRUNG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WAEHRUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_INFO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INFO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INFO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INFO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KARENZ_ZAHLFRIST_AB_HEUTE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KARENZ_ZAHLFRIST_AB_HEUTE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KARENZ_ZAHLFRIST_AB_HEUTE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KARENZ_ZAHLFRIST_AB_HEUTE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_KARENZ_ZAHLFRIST_AB_HEUTE_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KARENZ_ZAHLFRIST_AB_HEUTE_lValue(new Long(0));
			
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

	

	public long get_KARENZ_ZAHLFRIST_AB_HEUTE_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KARENZ_ZAHLFRIST_AB_HEUTE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_KARENZ_ZAHLFRIST_AB_HEUTE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_KARENZ_ZAHLFRIST_AB_HEUTE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_KARENZ_ZAHLFRIST_AB_HEUTE_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_KARENZ_ZAHLFRIST_AB_HEUTE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_KARENZ_ZAHLFRIST_AB_HEUTE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_KARENZ_ZAHLFRIST_AB_HEUTE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KURZNAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KURZNAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KURZNAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KURZNAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LANDKURZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LANDKURZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LANDKURZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LANDKURZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LOGOGROESSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGOGROESSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LOGOGROESSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGOGROESSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_LOGOGROESSE_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LOGOGROESSE_lValue(new Long(0));
			
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

	

	public long get_LOGOGROESSE_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LOGOGROESSE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_LOGOGROESSE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_LOGOGROESSE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_LOGOGROESSE_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_LOGOGROESSE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_LOGOGROESSE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_LOGOGROESSE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_LOGONAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGONAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LOGONAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGONAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LOGOSCHRIFT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGOSCHRIFT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LOGOSCHRIFT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGOSCHRIFT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LOGOTEXT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGOTEXT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LOGOTEXT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LOGOTEXT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MAILACCOUNT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILACCOUNT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MAILACCOUNT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILACCOUNT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MAILPASSWORD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILPASSWORD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MAILPASSWORD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILPASSWORD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MAILSERVER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILSERVER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MAILSERVER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILSERVER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MAILUSERNAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILUSERNAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MAILUSERNAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MAILUSERNAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MG_TOLERANZ_EK_KONTRAKT_POS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MG_TOLERANZ_EK_KONTRAKT_POS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MG_TOLERANZ_EK_KONTRAKT_POS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MG_TOLERANZ_EK_KONTRAKT_POS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_MG_TOLERANZ_EK_KONTRAKT_POS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_MG_TOLERANZ_EK_KONTRAKT_POS_lValue(new Long(0));
			
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

	

	public long get_MG_TOLERANZ_EK_KONTRAKT_POS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_MG_TOLERANZ_EK_KONTRAKT_POS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_MG_TOLERANZ_EK_KONTRAKT_POS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_MG_TOLERANZ_EK_KONTRAKT_POS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_MG_TOLERANZ_EK_KONTRAKT_POS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MG_TOLERANZ_EK_KONTRAKT_POS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_MG_TOLERANZ_EK_KONTRAKT_POS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MG_TOLERANZ_EK_KONTRAKT_POS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_MG_TOLERANZ_VK_KONTRAKT_POS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MG_TOLERANZ_VK_KONTRAKT_POS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MG_TOLERANZ_VK_KONTRAKT_POS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MG_TOLERANZ_VK_KONTRAKT_POS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_MG_TOLERANZ_VK_KONTRAKT_POS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_MG_TOLERANZ_VK_KONTRAKT_POS_lValue(new Long(0));
			
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

	

	public long get_MG_TOLERANZ_VK_KONTRAKT_POS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_MG_TOLERANZ_VK_KONTRAKT_POS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_MG_TOLERANZ_VK_KONTRAKT_POS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_MG_TOLERANZ_VK_KONTRAKT_POS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_MG_TOLERANZ_VK_KONTRAKT_POS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MG_TOLERANZ_VK_KONTRAKT_POS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_MG_TOLERANZ_VK_KONTRAKT_POS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MG_TOLERANZ_VK_KONTRAKT_POS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NAME1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NUMKREIS_DEBITOR_EU_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_EU_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_DEBITOR_EU_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_EU_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_DEBITOR_EU_BIS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_EU_BIS_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_DEBITOR_EU_BIS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_EU_BIS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_DEBITOR_EU_BIS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_DEBITOR_EU_BIS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_DEBITOR_EU_BIS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_DEBITOR_EU_BIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_DEBITOR_EU_BIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_DEBITOR_EU_BIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_DEBITOR_EU_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_EU_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_DEBITOR_EU_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_EU_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_DEBITOR_EU_VON_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_EU_VON_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_DEBITOR_EU_VON_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_EU_VON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_DEBITOR_EU_VON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_DEBITOR_EU_VON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_DEBITOR_EU_VON_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_DEBITOR_EU_VON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_DEBITOR_EU_VON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_DEBITOR_EU_VON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_DEBITOR_INLAND_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_INLAND_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_DEBITOR_INLAND_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_INLAND_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_DEBITOR_INLAND_BIS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_INLAND_BIS_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_DEBITOR_INLAND_BIS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_INLAND_BIS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_DEBITOR_INLAND_BIS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_DEBITOR_INLAND_BIS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_DEBITOR_INLAND_BIS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_DEBITOR_INLAND_BIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_DEBITOR_INLAND_BIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_DEBITOR_INLAND_BIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_DEBITOR_INLAND_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_INLAND_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_DEBITOR_INLAND_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_INLAND_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_DEBITOR_INLAND_VON_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_INLAND_VON_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_DEBITOR_INLAND_VON_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_INLAND_VON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_DEBITOR_INLAND_VON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_DEBITOR_INLAND_VON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_DEBITOR_INLAND_VON_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_DEBITOR_INLAND_VON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_DEBITOR_INLAND_VON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_DEBITOR_INLAND_VON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_DEBITOR_NICHT_EU_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_DEBITOR_NICHT_EU_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_DEBITOR_NICHT_EU_BIS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_DEBITOR_NICHT_EU_BIS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_DEBITOR_NICHT_EU_BIS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_DEBITOR_NICHT_EU_BIS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_DEBITOR_NICHT_EU_BIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_BIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_DEBITOR_NICHT_EU_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_DEBITOR_NICHT_EU_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_DEBITOR_NICHT_EU_VON_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_NICHT_EU_VON_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_DEBITOR_NICHT_EU_VON_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_DEBITOR_NICHT_EU_VON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_DEBITOR_NICHT_EU_VON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_VON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_DEBITOR_NICHT_EU_VON_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_DEBITOR_NICHT_EU_VON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_DEBITOR_NICHT_EU_VON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_DEBITOR_NICHT_EU_VON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_KREDITOR_EU_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_EU_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_KREDITOR_EU_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_EU_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_KREDITOR_EU_BIS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_EU_BIS_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_KREDITOR_EU_BIS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_EU_BIS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_KREDITOR_EU_BIS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_KREDITOR_EU_BIS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_KREDITOR_EU_BIS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_KREDITOR_EU_BIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_KREDITOR_EU_BIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_KREDITOR_EU_BIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_KREDITOR_EU_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_EU_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_KREDITOR_EU_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_EU_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_KREDITOR_EU_VON_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_EU_VON_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_KREDITOR_EU_VON_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_EU_VON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_KREDITOR_EU_VON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_KREDITOR_EU_VON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_KREDITOR_EU_VON_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_KREDITOR_EU_VON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_KREDITOR_EU_VON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_KREDITOR_EU_VON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_KREDITOR_INLAND_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_INLAND_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_KREDITOR_INLAND_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_INLAND_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_KREDITOR_INLAND_BIS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_INLAND_BIS_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_KREDITOR_INLAND_BIS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_INLAND_BIS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_KREDITOR_INLAND_BIS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_KREDITOR_INLAND_BIS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_KREDITOR_INLAND_BIS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_KREDITOR_INLAND_BIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_KREDITOR_INLAND_BIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_KREDITOR_INLAND_BIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_KREDITOR_INLAND_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_INLAND_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_KREDITOR_INLAND_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_INLAND_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_KREDITOR_INLAND_VON_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_INLAND_VON_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_KREDITOR_INLAND_VON_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_INLAND_VON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_KREDITOR_INLAND_VON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_KREDITOR_INLAND_VON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_KREDITOR_INLAND_VON_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_KREDITOR_INLAND_VON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_KREDITOR_INLAND_VON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_KREDITOR_INLAND_VON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_KREDITOR_NICHT_EU_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_KREDITOR_NICHT_EU_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_KREDITOR_NICHT_EU_BIS_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_KREDITOR_NICHT_EU_BIS_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_KREDITOR_NICHT_EU_BIS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_KREDITOR_NICHT_EU_BIS_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_KREDITOR_NICHT_EU_BIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_BIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NUMKREIS_KREDITOR_NICHT_EU_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NUMKREIS_KREDITOR_NICHT_EU_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_NUMKREIS_KREDITOR_NICHT_EU_VON_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_NICHT_EU_VON_lValue(new Long(0));
			
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

	

	public long get_NUMKREIS_KREDITOR_NICHT_EU_VON_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_NUMKREIS_KREDITOR_NICHT_EU_VON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_NUMKREIS_KREDITOR_NICHT_EU_VON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_VON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_NUMKREIS_KREDITOR_NICHT_EU_VON_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NUMKREIS_KREDITOR_NICHT_EU_VON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_NUMKREIS_KREDITOR_NICHT_EU_VON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NUMKREIS_KREDITOR_NICHT_EU_VON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PREISFREIGABE_AUTO_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PREISFREIGABE_AUTO_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PREISFREIGABE_AUTO_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PREISFREIGABE_AUTO_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_RECHDAT_IST_LEISTUNGSDATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_RECHDAT_IST_LEISTUNGSDATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_RECHDAT_IST_LEISTUNGSDATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_RECHDAT_IST_LEISTUNGSDATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_RUNDEN_ABZUGS_MENGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_RUNDEN_ABZUGS_MENGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_RUNDEN_ABZUGS_MENGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_RUNDEN_ABZUGS_MENGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_BANKNAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_BANKNAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_BANKNAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_BANKNAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_BLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_BLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_BLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_BLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_KONTO_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_KONTO_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_KONTO_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_KONTO_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SCHECKVERMERK_AUF_GUTSCHRIFT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKVERMERK_AUF_GUTSCHRIFT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHECKVERMERK_AUF_GUTSCHRIFT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKVERMERK_AUF_GUTSCHRIFT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERFINDUNG_OHNE_EIGENORTE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERFINDUNG_OHNE_EIGENORTE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERFINDUNG_OHNE_EIGENORTE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERFINDUNG_OHNE_EIGENORTE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STICHTAG_START_FIBU_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STICHTAG_START_FIBU_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STICHTAG_START_FIBU_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STICHTAG_START_FIBU_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STRASSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRASSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STRASSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRASSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TITELUEBERANSCHRIFT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TITELUEBERANSCHRIFT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TITELUEBERANSCHRIFT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TITELUEBERANSCHRIFT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_UNTERSCHEIDUNGSNR_INTRASTAT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UNTERSCHEIDUNGSNR_INTRASTAT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_UNTERSCHEIDUNGSNR_INTRASTAT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UNTERSCHEIDUNGSNR_INTRASTAT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_UNTERSCHEIDUNGSNR_INTRASTAT_l_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_UNTERSCHEIDUNGSNR_INTRASTAT_lValue(new Long(0));
			
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

	

	public long get_UNTERSCHEIDUNGSNR_INTRASTAT_l_Count_NotNull(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_UNTERSCHEIDUNGSNR_INTRASTAT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_UNTERSCHEIDUNGSNR_INTRASTAT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_UNTERSCHEIDUNGSNR_INTRASTAT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_UNTERSCHEIDUNGSNR_INTRASTAT_d_Summe(BASIC_RECLIST_MANDANT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,BASIC_RECORD_MANDANT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			BASIC_RECORD_MANDANT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_UNTERSCHEIDUNGSNR_INTRASTAT_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_UNTERSCHEIDUNGSNR_INTRASTAT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_UNTERSCHEIDUNGSNR_INTRASTAT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_VERMERK_STEUERFR_DIENSTLEIST_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERMERK_STEUERFR_DIENSTLEIST_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERMERK_STEUERFR_DIENSTLEIST_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERMERK_STEUERFR_DIENSTLEIST_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VORNAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORNAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VORNAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORNAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_FAHRPLANERFASSUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_FAHRPLANERFASSUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_FAHRPLANERFASSUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_GLOBAL_REPORTS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_GLOBAL_REPORTS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_GLOBAL_REPORTS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_GLOBAL_REPORTS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_KALENDER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_KALENDER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_KALENDER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_KALENDER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_MESSAGES_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_MESSAGES_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_MESSAGES_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_MESSAGES_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_TODOS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_TODOS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_TODOS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_TODOS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_WORKFLOW_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_WORKFLOW_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEIGE_MODUL_WORKFLOW_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,BASIC_RECORD_MANDANT> oEntry: this.entrySet())
		{
			BASIC_RECORD_MANDANT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEIGE_MODUL_WORKFLOW_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
