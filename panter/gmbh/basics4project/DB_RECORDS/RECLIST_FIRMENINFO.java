package panter.gmbh.basics4project.DB_RECORDS;

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
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.IF_RecordList;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB_raw;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;


public class RECLIST_FIRMENINFO extends HashMap<String,RECORD_FIRMENINFO>  implements Iterable<RECORD_FIRMENINFO>, IF_RecordList<RECORD_FIRMENINFO>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_FIRMENINFO";
	private String        	cTableName = "JT_FIRMENINFO";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(SELECT select) throws myException
	{
		super();
		this.cQueryString = select.toString();
		this.Select = select;
		this.REFRESH();
	}

	
	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(SELECT select, MyConnection Conn) throws myException
	{
		super();
		this.cQueryString = select.toString();
		this.Select = select;
		this.oConn = Conn;
		this.REFRESH();
	}







	/**
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_FIRMENINFO muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(String QueryString) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.REFRESH();
	}

	
	/**
	 * 
	 * @param WhereStatment or null
	 * @param OrderStatment or null
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_FIRMENINFO";
		
		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
		}

		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
		}
		
		this.REFRESH();
	}

	

	/**
	 * 
	 * @param WhereStatment or null
	 * @param OrderStatment or null
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_FIRMENINFO";
		
		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
		}

		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
		{
		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
		}
		
		this.REFRESH();
	}


	
	

	/**
	 * 
	 * @param QueryString !!ID_FIRMENINFO muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(String QueryString, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;

		this.REFRESH();
	}



	//2012-02-10: neue konstruktoren: uebergabe eines vectors aus unformated ids
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_FIRMENINFO IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_FIRMENINFO IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_FIRMENINFO muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(String QueryString,int MaxNumberOfRecords) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
		
		this.REFRESH();
	}

	

    /**
     * 
     * @param QueryString !!ID_ADRESSE muss im querystring vorhanden sein !!!
     * @param MaxNumberOfRecords
     * @param p_allow_overflow  (dann werden alle records bis zur overflow-grenze zurueckgegeben)
     * @throws myException
     */
    public RECLIST_FIRMENINFO(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_FIRMENINFO muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_FIRMENINFO(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
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
	public RECLIST_FIRMENINFO(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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



	/**
	 * 
	 * @return keyfield-name 
	 */
	public String get_cKeyField()
	{
		return this.cKeyField;
	}


	public void set_oConn(MyConnection conn)
	{
		this.oConn = conn;
	}


	public String get_cQueryString()
	{
		return this.cQueryString;
	}


	public void set_cQueryString(String queryString)
	{
		this.cQueryString = queryString;
	}


	public int get_iMaxNumberOfRecords()
	{
		return this.iMaxNumberOfRecords;
	}


	public void set_iMaxNumberOfRecords(int maxNumberOfRecords)
	{
		this.iMaxNumberOfRecords = maxNumberOfRecords;
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

        if (this.oDB == null) {
            this.oDB = this.generate_DBToolBox(this.oConn);
            //2015-05-07: ersetzt durch neue lokale methode
            //this.oDB = 	bibALL.get_myDBToolBox(this.oConn);
        }
		
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
                      	RECORD_FIRMENINFO  	oRec = 		new RECORD_FIRMENINFO(this.oConn);
                      	
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
                            if (!this.allow_over_flow) {                   //2015-12-01
                        	   this.vKeyValues.removeAllElements();
							   this.clear();
							}
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_FIRMENINFO: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_FIRMENINFO get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_FIRMENINFO!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_FIRMENINFO get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_FIRMENINFO oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_FIRMENINFO oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_FIRMENINFO.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_FIRMENINFO oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_FIRMENINFO oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_FIRMENINFO: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_FIRMENINFO oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_FIRMENINFO get_SUBLIST(RECLIST_FIRMENINFO.Validation oValidator)  throws myException
	{
		RECLIST_FIRMENINFO oRecListRueck = new RECLIST_FIRMENINFO(this.oConn);
		
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
	public Vector<RECORD_FIRMENINFO>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_FIRMENINFO> vRueck = new Vector<RECORD_FIRMENINFO>();
		
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
	
	
	
     /**
     * 2016-06-06: einfachere sortiermethode
     * @author martin
     *
     */
     public Vector<RECORD_FIRMENINFO>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_FIRMENINFO>
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

		
		public int compare(RECORD_FIRMENINFO o1, RECORD_FIRMENINFO o2) 
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
	
	
    //2015-03-10 martin: 
    public String get_cTableName() {
        return this.cTableName;
    }
	
	
	
    /**
     * 2015-03-10: martin  
	 * @return SELECT-object, can be null
	 */
    public SELECT get_Select() {
        return this.Select;
    }


    /**
     * 2015-03-10: martin  
	 * @param select
	 */
    public void set_Select(SELECT select) {
        this.Select = select;
        this.set_cQueryString(this.Select.toString());
    }
	
	
	
    @Override
    public Iterator<RECORD_FIRMENINFO> iterator() {
        return this.values().iterator();
    }
	
	
    /**
     * 2015-05-06: getter/setter hinzugefuegt
     * @return
     */
    public MyDBToolBox get_oDB() {
        return oDB;
    }


    public void set_oDB(MyDBToolBox oDBToolBox) {
        this.oDB = oDBToolBox;
    }
	
	
	
	/**
	 * 2015-05-06: Toolbox-Generator, um DBToolboxen mit von der norm abweichenden Ausnahmefeldern erzeugen zu koennen
	 */
	private MyDBToolBox_FAB  	DBToolBox_FAB = null;

	
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
	
	
	
    public boolean is_allow_over_flow() {
        return this.allow_over_flow;
    }
	
	
     /** 
      * 2016-09-07: martin
      * sorgt dafuer, dass abfragen auf den basisadressen und speichervorgaenge ohne automatische zusatzfelder ausgefuehrt werden
      */
     public void set_to_raw_state() {
        this.set_DBToolBox_FAB(new MyDBToolBox_FAB_raw());
     }
	
	
	
	
	
	
	


	public HashMap<String, String>  get_ABLADEMENGE_FUER_GUTSCHRIFT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEMENGE_FUER_GUTSCHRIFT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABLADEMENGE_FUER_GUTSCHRIFT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEMENGE_FUER_GUTSCHRIFT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_AKONTO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AKONTO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AKONTO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AKONTO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_AQUISITIONSKOSTEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AQUISITIONSKOSTEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AQUISITIONSKOSTEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AQUISITIONSKOSTEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_AQUISITIONSKOSTEN_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_AQUISITIONSKOSTEN_lValue(new Long(0));
			
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

	

	public long get_AQUISITIONSKOSTEN_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_AQUISITIONSKOSTEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_AQUISITIONSKOSTEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_AQUISITIONSKOSTEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_AQUISITIONSKOSTEN_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_AQUISITIONSKOSTEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_AQUISITIONSKOSTEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_AQUISITIONSKOSTEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_BESCHREIBUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESCHREIBUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BESCHREIBUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESCHREIBUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BESUCHSRYTHMUS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESUCHSRYTHMUS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BESUCHSRYTHMUS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESUCHSRYTHMUS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_BESUCHSRYTHMUS_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_BESUCHSRYTHMUS_lValue(new Long(0));
			
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

	

	public long get_BESUCHSRYTHMUS_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_BESUCHSRYTHMUS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_BESUCHSRYTHMUS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_BESUCHSRYTHMUS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_BESUCHSRYTHMUS_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_BESUCHSRYTHMUS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_BESUCHSRYTHMUS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_BESUCHSRYTHMUS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_BETRIEBSNUMMER_SAA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BETRIEBSNUMMER_SAA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BETRIEBSNUMMER_SAA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BETRIEBSNUMMER_SAA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEBITOR_NUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEBITOR_NUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEBITOR_NUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEBITOR_NUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEBITOR_NUMMER_ALT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEBITOR_NUMMER_ALT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEBITOR_NUMMER_ALT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEBITOR_NUMMER_ALT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DOKUMENTKOPIEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DOKUMENTKOPIEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DOKUMENTKOPIEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DOKUMENTKOPIEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_DOKUMENTKOPIEN_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_DOKUMENTKOPIEN_lValue(new Long(0));
			
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

	

	public long get_DOKUMENTKOPIEN_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_DOKUMENTKOPIEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_DOKUMENTKOPIEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_DOKUMENTKOPIEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_DOKUMENTKOPIEN_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_DOKUMENTKOPIEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_DOKUMENTKOPIEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_DOKUMENTKOPIEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FBAM_NUR_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FBAM_NUR_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FBAM_NUR_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FBAM_NUR_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIRMA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIRMA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIRMA_OHNE_USTID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMA_OHNE_USTID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIRMA_OHNE_USTID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIRMA_OHNE_USTID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FORDERUNGSVERRECHNUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FORDERUNGSVERRECHNUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FORDERUNGSVERRECHNUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FORDERUNGSVERRECHNUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GRUENDUNGSDATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GRUENDUNGSDATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GRUENDUNGSDATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GRUENDUNGSDATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_HANDELSREGISTER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HANDELSREGISTER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_HANDELSREGISTER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HANDELSREGISTER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_ADRESSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_lValue(new Long(0));
			
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

	

	public long get_ID_ADRESSE_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BRANCHE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BRANCHE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BRANCHE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BRANCHE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BRANCHE_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BRANCHE_lValue(new Long(0));
			
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

	

	public long get_ID_BRANCHE_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BRANCHE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BRANCHE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BRANCHE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BRANCHE_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BRANCHE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_BRANCHE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BRANCHE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_EAK_BRANCHE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_EAK_BRANCHE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_EAK_BRANCHE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_EAK_BRANCHE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_EAK_BRANCHE_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_EAK_BRANCHE_lValue(new Long(0));
			
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

	

	public long get_ID_EAK_BRANCHE_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_EAK_BRANCHE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_EAK_BRANCHE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_EAK_BRANCHE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_EAK_BRANCHE_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_EAK_BRANCHE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_EAK_BRANCHE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_EAK_BRANCHE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_FIRMENINFO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_FIRMENINFO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_FIRMENINFO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_FIRMENINFO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_FIRMENINFO_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_FIRMENINFO_lValue(new Long(0));
			
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

	

	public long get_ID_FIRMENINFO_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_FIRMENINFO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_FIRMENINFO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_FIRMENINFO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_FIRMENINFO_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_FIRMENINFO_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_FIRMENINFO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_FIRMENINFO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT2_BEDINGUNG1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT2_BEDINGUNG1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT2_BEDINGUNG1_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG1_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT2_BEDINGUNG1_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG1_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT2_BEDINGUNG1_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG1_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT2_BEDINGUNG1_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG1_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT2_BEDINGUNG1_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG1_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT2_BEDINGUNG2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT2_BEDINGUNG2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT2_BEDINGUNG2_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG2_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT2_BEDINGUNG2_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG2_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT2_BEDINGUNG2_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG2_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT2_BEDINGUNG2_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG2_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT2_BEDINGUNG2_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG2_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT2_BEDINGUNG3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT2_BEDINGUNG3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT2_BEDINGUNG3_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG3_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT2_BEDINGUNG3_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG3_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT2_BEDINGUNG3_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG3_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT2_BEDINGUNG3_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT2_BEDINGUNG3_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT2_BEDINGUNG3_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT2_BEDINGUNG3_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT3_BEDINGUNG1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT3_BEDINGUNG1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT3_BEDINGUNG1_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG1_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT3_BEDINGUNG1_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG1_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT3_BEDINGUNG1_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG1_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT3_BEDINGUNG1_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG1_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT3_BEDINGUNG1_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG1_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT3_BEDINGUNG2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT3_BEDINGUNG2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT3_BEDINGUNG2_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG2_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT3_BEDINGUNG2_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG2_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT3_BEDINGUNG2_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG2_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT3_BEDINGUNG2_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG2_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT3_BEDINGUNG2_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG2_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT3_BEDINGUNG3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT3_BEDINGUNG3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT3_BEDINGUNG3_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG3_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT3_BEDINGUNG3_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG3_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT3_BEDINGUNG3_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG3_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT3_BEDINGUNG3_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT3_BEDINGUNG3_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT3_BEDINGUNG3_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT3_BEDINGUNG3_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT_BEDINGUNG1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT_BEDINGUNG1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT_BEDINGUNG1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT_BEDINGUNG1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT_BEDINGUNG1_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG1_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT_BEDINGUNG1_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG1_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT_BEDINGUNG1_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT_BEDINGUNG1_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT_BEDINGUNG1_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG1_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT_BEDINGUNG1_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT_BEDINGUNG1_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT_BEDINGUNG2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT_BEDINGUNG2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT_BEDINGUNG2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT_BEDINGUNG2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT_BEDINGUNG2_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG2_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT_BEDINGUNG2_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG2_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT_BEDINGUNG2_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT_BEDINGUNG2_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT_BEDINGUNG2_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG2_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT_BEDINGUNG2_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT_BEDINGUNG2_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_KREDITLIMIT_BEDINGUNG3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT_BEDINGUNG3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_KREDITLIMIT_BEDINGUNG3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_KREDITLIMIT_BEDINGUNG3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_KREDITLIMIT_BEDINGUNG3_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG3_lValue(new Long(0));
			
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

	

	public long get_ID_KREDITLIMIT_BEDINGUNG3_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG3_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_KREDITLIMIT_BEDINGUNG3_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_KREDITLIMIT_BEDINGUNG3_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_KREDITLIMIT_BEDINGUNG3_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_KREDITLIMIT_BEDINGUNG3_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_KREDITLIMIT_BEDINGUNG3_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_KREDITLIMIT_BEDINGUNG3_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_RECHTSFORM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_RECHTSFORM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_RECHTSFORM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_RECHTSFORM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_RECHTSFORM_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_RECHTSFORM_lValue(new Long(0));
			
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

	

	public long get_ID_RECHTSFORM_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_RECHTSFORM_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_RECHTSFORM_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_RECHTSFORM_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_RECHTSFORM_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_RECHTSFORM_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_RECHTSFORM_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_RECHTSFORM_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ZAHLUNGSMEDIUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ZAHLUNGSMEDIUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ZAHLUNGSMEDIUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ZAHLUNGSMEDIUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ZAHLUNGSMEDIUM_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ZAHLUNGSMEDIUM_lValue(new Long(0));
			
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

	

	public long get_ID_ZAHLUNGSMEDIUM_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ZAHLUNGSMEDIUM_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ZAHLUNGSMEDIUM_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ZAHLUNGSMEDIUM_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ZAHLUNGSMEDIUM_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ZAHLUNGSMEDIUM_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ZAHLUNGSMEDIUM_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ZAHLUNGSMEDIUM_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_INNERGEMEIN_LIEF_EU_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INNERGEMEIN_LIEF_EU_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INNERGEMEIN_LIEF_EU_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INNERGEMEIN_LIEF_EU_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KEINE_MAHNUNGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KEINE_MAHNUNGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KEINE_MAHNUNGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KEINE_MAHNUNGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITBETRAG_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITBETRAG_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITBETRAG_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITBETRAG_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_KREDITBETRAG_INTERN_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_KREDITBETRAG_INTERN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_KREDITBETRAG_INTERN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_KREDITBETRAG_INTERN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KREDITBETRAG_INTERN_VALID_TO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITBETRAG_INTERN_VALID_TO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITBETRAG_INTERN_VALID_TO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITBETRAG_INTERN_VALID_TO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITLIMIT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_KREDITLIMIT_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITLIMIT_lValue(new Long(0));
			
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

	

	public long get_KREDITLIMIT_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITLIMIT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_KREDITLIMIT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_KREDITLIMIT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_KREDITLIMIT_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_KREDITLIMIT_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_KREDITLIMIT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_KREDITLIMIT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KREDITLIMIT2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_KREDITLIMIT2_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITLIMIT2_lValue(new Long(0));
			
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

	

	public long get_KREDITLIMIT2_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITLIMIT2_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_KREDITLIMIT2_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_KREDITLIMIT2_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_KREDITLIMIT2_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_KREDITLIMIT2_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_KREDITLIMIT2_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_KREDITLIMIT2_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KREDITLIMIT2_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT2_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT2_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT2_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITLIMIT2_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT2_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT2_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT2_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITLIMIT3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_KREDITLIMIT3_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITLIMIT3_lValue(new Long(0));
			
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

	

	public long get_KREDITLIMIT3_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITLIMIT3_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_KREDITLIMIT3_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_KREDITLIMIT3_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_KREDITLIMIT3_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_KREDITLIMIT3_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_KREDITLIMIT3_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_KREDITLIMIT3_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KREDITLIMIT3_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT3_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT3_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT3_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITLIMIT3_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT3_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT3_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT3_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITLIMIT_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITLIMIT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITLIMIT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITLIMIT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITOR_NUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITOR_NUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITOR_NUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITOR_NUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITOR_NUMMER_ALT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITOR_NUMMER_ALT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITOR_NUMMER_ALT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITOR_NUMMER_ALT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KREDITSTAND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITSTAND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITSTAND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITSTAND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_KREDITSTAND_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITSTAND_lValue(new Long(0));
			
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

	

	public long get_KREDITSTAND_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_KREDITSTAND_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_KREDITSTAND_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_KREDITSTAND_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_KREDITSTAND_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_KREDITSTAND_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_KREDITSTAND_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_KREDITSTAND_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KREDITVERS_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITVERS_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KREDITVERS_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KREDITVERS_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LADEMENGE_FUER_RECHNUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LADEMENGE_FUER_RECHNUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LADEMENGE_FUER_RECHNUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LADEMENGE_FUER_RECHNUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERMENGE_MAX_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERMENGE_MAX_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERMENGE_MAX_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERMENGE_MAX_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_LIEFERMENGE_MAX_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LIEFERMENGE_MAX_lValue(new Long(0));
			
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

	

	public long get_LIEFERMENGE_MAX_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LIEFERMENGE_MAX_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_LIEFERMENGE_MAX_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_LIEFERMENGE_MAX_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_LIEFERMENGE_MAX_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_LIEFERMENGE_MAX_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_LIEFERMENGE_MAX_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_LIEFERMENGE_MAX_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_LIEFERMENGE_SCHNITT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERMENGE_SCHNITT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERMENGE_SCHNITT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERMENGE_SCHNITT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_LIEFERMENGE_SCHNITT_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LIEFERMENGE_SCHNITT_lValue(new Long(0));
			
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

	

	public long get_LIEFERMENGE_SCHNITT_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_LIEFERMENGE_SCHNITT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_LIEFERMENGE_SCHNITT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_LIEFERMENGE_SCHNITT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_LIEFERMENGE_SCHNITT_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_LIEFERMENGE_SCHNITT_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_LIEFERMENGE_SCHNITT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_LIEFERMENGE_SCHNITT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_OEFFNUNGSZEITEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OHNE_STEUER_WARENAUSGANG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_STEUER_WARENAUSGANG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OHNE_STEUER_WARENAUSGANG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_STEUER_WARENAUSGANG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OHNE_STEUER_WARENEINGANG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_STEUER_WARENEINGANG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OHNE_STEUER_WARENEINGANG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_STEUER_WARENEINGANG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRIVAT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRIVAT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRIVAT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRIVAT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRIVAT_MIT_USTID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRIVAT_MIT_USTID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRIVAT_MIT_USTID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRIVAT_MIT_USTID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_JN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_JN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHECKDRUCK_JN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHECKDRUCK_JN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STAMMKAPITAL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STAMMKAPITAL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STAMMKAPITAL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STAMMKAPITAL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_STAMMKAPITAL_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_STAMMKAPITAL_lValue(new Long(0));
			
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

	

	public long get_STAMMKAPITAL_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_STAMMKAPITAL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_STAMMKAPITAL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_STAMMKAPITAL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_STAMMKAPITAL_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STAMMKAPITAL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_STAMMKAPITAL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STAMMKAPITAL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STEUERNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERNUMMER_STATT_UST_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERNUMMER_STATT_UST_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERNUMMER_STATT_UST_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERNUMMER_STATT_UST_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SYS_TRIGGER_VERSION_lValue(new Long(0));
			
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SYS_TRIGGER_VERSION_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SYS_TRIGGER_VERSION_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SYS_TRIGGER_VERSION_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SYS_TRIGGER_VERSION_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_UMSATZSTEUERID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UMSATZSTEUERID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_UMSATZSTEUERID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UMSATZSTEUERID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_UMSATZSTEUERLKZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UMSATZSTEUERLKZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_UMSATZSTEUERLKZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UMSATZSTEUERLKZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VERLAENGERT_EIGENTUM_VORBEHALT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERLAENGERT_EIGENTUM_VORBEHALT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERLAENGERT_EIGENTUM_VORBEHALT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERLAENGERT_EIGENTUM_VORBEHALT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VERSICHANFR_DAT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSICHANFR_DAT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERSICHANFR_DAT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSICHANFR_DAT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VERSICHANFR_SUMME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSICHANFR_SUMME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERSICHANFR_SUMME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSICHANFR_SUMME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_VERSICHANFR_SUMME_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERSICHANFR_SUMME_lValue(new Long(0));
			
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

	

	public long get_VERSICHANFR_SUMME_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERSICHANFR_SUMME_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_VERSICHANFR_SUMME_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_VERSICHANFR_SUMME_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_VERSICHANFR_SUMME_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_VERSICHANFR_SUMME_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_VERSICHANFR_SUMME_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_VERSICHANFR_SUMME_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_VERSICH_MELDEFRIST_TAGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSICH_MELDEFRIST_TAGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERSICH_MELDEFRIST_TAGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSICH_MELDEFRIST_TAGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_VERSICH_MELDEFRIST_TAGE_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERSICH_MELDEFRIST_TAGE_lValue(new Long(0));
			
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

	

	public long get_VERSICH_MELDEFRIST_TAGE_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERSICH_MELDEFRIST_TAGE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_VERSICH_MELDEFRIST_TAGE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_VERSICH_MELDEFRIST_TAGE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_VERSICH_MELDEFRIST_TAGE_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_VERSICH_MELDEFRIST_TAGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_VERSICH_MELDEFRIST_TAGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_VERSICH_MELDEFRIST_TAGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_VERWERTUNGSVERFAHREN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERWERTUNGSVERFAHREN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERWERTUNGSVERFAHREN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERWERTUNGSVERFAHREN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VORSTEUERABZUG_RC_INLAND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORSTEUERABZUG_RC_INLAND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VORSTEUERABZUG_RC_INLAND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORSTEUERABZUG_RC_INLAND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZAHLANGESTELLTE_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHLANGESTELLTE_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZAHLANGESTELLTE_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHLANGESTELLTE_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZAHL_ANGESTELLTE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHL_ANGESTELLTE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZAHL_ANGESTELLTE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHL_ANGESTELLTE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ZAHL_ANGESTELLTE_l_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ZAHL_ANGESTELLTE_lValue(new Long(0));
			
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

	

	public long get_ZAHL_ANGESTELLTE_l_Count_NotNull(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ZAHL_ANGESTELLTE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ZAHL_ANGESTELLTE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ZAHL_ANGESTELLTE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ZAHL_ANGESTELLTE_d_Summe(RECLIST_FIRMENINFO.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_FIRMENINFO>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_FIRMENINFO 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ZAHL_ANGESTELLTE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ZAHL_ANGESTELLTE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_FIRMENINFO> oEntry: this.entrySet())
		{
			RECORD_FIRMENINFO 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ZAHL_ANGESTELLTE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	}
