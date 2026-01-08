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


public class RECLIST_HANDELSDEF extends HashMap<String,RECORD_HANDELSDEF>  implements Iterable<RECORD_HANDELSDEF>, IF_RecordList<RECORD_HANDELSDEF>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_HANDELSDEF";
	private String        	cTableName = "JT_HANDELSDEF";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(SELECT select) throws myException
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
	public RECLIST_HANDELSDEF(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_HANDELSDEF() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_HANDELSDEF muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(String QueryString) throws myException
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
	public RECLIST_HANDELSDEF(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF";
		
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
	public RECLIST_HANDELSDEF(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF";
		
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
	 * @param QueryString !!ID_HANDELSDEF muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_HANDELSDEF(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_HANDELSDEF IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_HANDELSDEF IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_HANDELSDEF muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_HANDELSDEF(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_HANDELSDEF muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_HANDELSDEF(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_HANDELSDEF(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_HANDELSDEF  	oRec = 		new RECORD_HANDELSDEF(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_HANDELSDEF: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_HANDELSDEF get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_HANDELSDEF!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_HANDELSDEF get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_HANDELSDEF oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_HANDELSDEF oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_HANDELSDEF.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_HANDELSDEF oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_HANDELSDEF oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_HANDELSDEF: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_HANDELSDEF oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_HANDELSDEF get_SUBLIST(RECLIST_HANDELSDEF.Validation oValidator)  throws myException
	{
		RECLIST_HANDELSDEF oRecListRueck = new RECLIST_HANDELSDEF(this.oConn);
		
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
	public Vector<RECORD_HANDELSDEF>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_HANDELSDEF> vRueck = new Vector<RECORD_HANDELSDEF>();
		
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
     public Vector<RECORD_HANDELSDEF>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_HANDELSDEF>
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

		
		public int compare(RECORD_HANDELSDEF o1, RECORD_HANDELSDEF o2) 
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
    public Iterator<RECORD_HANDELSDEF> iterator() {
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
	
	
	
	
	
	
	


	public HashMap<String, String>  get_AKTIV_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AKTIV_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AKTIV_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AKTIV_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_HANDELSDEF_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_HANDELSDEF_lValue(new Long(0));
			
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

	

	public long get_ID_HANDELSDEF_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_HANDELSDEF_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_HANDELSDEF_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_HANDELSDEF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_HANDELSDEF_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_HANDELSDEF_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_HANDELSDEF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_HANDELSDEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_QUELLE_GEO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_QUELLE_GEO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_QUELLE_GEO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_QUELLE_GEO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_QUELLE_GEO_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_QUELLE_GEO_lValue(new Long(0));
			
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

	

	public long get_ID_LAND_QUELLE_GEO_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_QUELLE_GEO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_QUELLE_GEO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_QUELLE_GEO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_QUELLE_GEO_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_QUELLE_GEO_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_QUELLE_GEO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_QUELLE_GEO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_QUELLE_JUR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_QUELLE_JUR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_QUELLE_JUR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_QUELLE_JUR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_QUELLE_JUR_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_QUELLE_JUR_lValue(new Long(0));
			
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

	

	public long get_ID_LAND_QUELLE_JUR_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_QUELLE_JUR_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_QUELLE_JUR_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_QUELLE_JUR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_QUELLE_JUR_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_QUELLE_JUR_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_QUELLE_JUR_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_QUELLE_JUR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_ZIEL_GEO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_ZIEL_GEO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_ZIEL_GEO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_ZIEL_GEO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_ZIEL_GEO_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_ZIEL_GEO_lValue(new Long(0));
			
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

	

	public long get_ID_LAND_ZIEL_GEO_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_ZIEL_GEO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_ZIEL_GEO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_ZIEL_GEO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_ZIEL_GEO_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_ZIEL_GEO_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_ZIEL_GEO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_ZIEL_GEO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_ZIEL_JUR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_ZIEL_JUR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_ZIEL_JUR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_ZIEL_JUR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_ZIEL_JUR_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_ZIEL_JUR_lValue(new Long(0));
			
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

	

	public long get_ID_LAND_ZIEL_JUR_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_ZIEL_JUR_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_ZIEL_JUR_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_ZIEL_JUR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_ZIEL_JUR_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_ZIEL_JUR_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_ZIEL_JUR_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_ZIEL_JUR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_NEGATIV_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_NEGATIV_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_NEGATIV_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_NEGATIV_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_NEGATIV_QUELLE_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_NEGATIV_QUELLE_lValue(new Long(0));
			
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

	

	public long get_ID_TAX_NEGATIV_QUELLE_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_NEGATIV_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_NEGATIV_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_NEGATIV_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_NEGATIV_QUELLE_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_NEGATIV_QUELLE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_NEGATIV_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_NEGATIV_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_NEGATIV_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_NEGATIV_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_NEGATIV_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_NEGATIV_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_NEGATIV_ZIEL_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_NEGATIV_ZIEL_lValue(new Long(0));
			
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

	

	public long get_ID_TAX_NEGATIV_ZIEL_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_NEGATIV_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_NEGATIV_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_NEGATIV_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_NEGATIV_ZIEL_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_NEGATIV_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_NEGATIV_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_NEGATIV_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_QUELLE_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_QUELLE_lValue(new Long(0));
			
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

	

	public long get_ID_TAX_QUELLE_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_QUELLE_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_QUELLE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_ZIEL_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_ZIEL_lValue(new Long(0));
			
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

	

	public long get_ID_TAX_ZIEL_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_ZIEL_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_INTRASTAT_MELD_IN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_IN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INTRASTAT_MELD_IN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_IN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_INTRASTAT_MELD_OUT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_OUT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INTRASTAT_MELD_OUT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_OUT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MELDUNG_FUER_USER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MELDUNG_FUER_USER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MELDUNG_FUER_USER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MELDUNG_FUER_USER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_QUELLE_IST_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_QUELLE_IST_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_QUELLE_IST_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_QUELLE_IST_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SORTE_DIENSTLEIST_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_DIENSTLEIST_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_DIENSTLEIST_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_DIENSTLEIST_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_DIENSTLEIST_QUELLE_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_DIENSTLEIST_QUELLE_lValue(new Long(0));
			
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

	

	public long get_SORTE_DIENSTLEIST_QUELLE_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_DIENSTLEIST_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_DIENSTLEIST_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_DIENSTLEIST_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_DIENSTLEIST_QUELLE_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_DIENSTLEIST_QUELLE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_DIENSTLEIST_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_DIENSTLEIST_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SORTE_DIENSTLEIST_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_DIENSTLEIST_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_DIENSTLEIST_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_DIENSTLEIST_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_DIENSTLEIST_ZIEL_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_DIENSTLEIST_ZIEL_lValue(new Long(0));
			
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

	

	public long get_SORTE_DIENSTLEIST_ZIEL_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_DIENSTLEIST_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_DIENSTLEIST_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_DIENSTLEIST_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_DIENSTLEIST_ZIEL_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_DIENSTLEIST_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_DIENSTLEIST_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_DIENSTLEIST_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SORTE_EOW_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_EOW_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_EOW_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_EOW_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_EOW_QUELLE_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_EOW_QUELLE_lValue(new Long(0));
			
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

	

	public long get_SORTE_EOW_QUELLE_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_EOW_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_EOW_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_EOW_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_EOW_QUELLE_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_EOW_QUELLE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_EOW_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_EOW_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SORTE_EOW_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_EOW_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_EOW_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_EOW_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_EOW_ZIEL_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_EOW_ZIEL_lValue(new Long(0));
			
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

	

	public long get_SORTE_EOW_ZIEL_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_EOW_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_EOW_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_EOW_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_EOW_ZIEL_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_EOW_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_EOW_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_EOW_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SORTE_PRODUKT_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_PRODUKT_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_PRODUKT_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_PRODUKT_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_PRODUKT_QUELLE_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_PRODUKT_QUELLE_lValue(new Long(0));
			
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

	

	public long get_SORTE_PRODUKT_QUELLE_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_PRODUKT_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_PRODUKT_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_PRODUKT_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_PRODUKT_QUELLE_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_PRODUKT_QUELLE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_PRODUKT_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_PRODUKT_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SORTE_PRODUKT_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_PRODUKT_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_PRODUKT_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_PRODUKT_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_PRODUKT_ZIEL_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_PRODUKT_ZIEL_lValue(new Long(0));
			
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

	

	public long get_SORTE_PRODUKT_ZIEL_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_PRODUKT_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_PRODUKT_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_PRODUKT_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_PRODUKT_ZIEL_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_PRODUKT_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_PRODUKT_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_PRODUKT_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SORTE_RC_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_RC_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_RC_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_RC_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_RC_QUELLE_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_RC_QUELLE_lValue(new Long(0));
			
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

	

	public long get_SORTE_RC_QUELLE_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_RC_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_RC_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_RC_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_RC_QUELLE_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_RC_QUELLE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_RC_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_RC_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SORTE_RC_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_RC_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_RC_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_RC_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTE_RC_ZIEL_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_RC_ZIEL_lValue(new Long(0));
			
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

	

	public long get_SORTE_RC_ZIEL_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTE_RC_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTE_RC_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTE_RC_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTE_RC_ZIEL_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTE_RC_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SORTE_RC_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTE_RC_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TP_VERANTWORTUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TP_VERANTWORTUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TP_VERANTWORTUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TP_VERANTWORTUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSIT_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSIT_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSIT_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSIT_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TYP_MELDUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_MELDUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TYP_MELDUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_MELDUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_UST_TEILNEHMER_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UST_TEILNEHMER_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_UST_TEILNEHMER_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UST_TEILNEHMER_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_UST_TEILNEHMER_QUELLE_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_UST_TEILNEHMER_QUELLE_lValue(new Long(0));
			
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

	

	public long get_UST_TEILNEHMER_QUELLE_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_UST_TEILNEHMER_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_UST_TEILNEHMER_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_UST_TEILNEHMER_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_UST_TEILNEHMER_QUELLE_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_UST_TEILNEHMER_QUELLE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_UST_TEILNEHMER_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_UST_TEILNEHMER_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_UST_TEILNEHMER_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UST_TEILNEHMER_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_UST_TEILNEHMER_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UST_TEILNEHMER_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_UST_TEILNEHMER_ZIEL_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_UST_TEILNEHMER_ZIEL_lValue(new Long(0));
			
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

	

	public long get_UST_TEILNEHMER_ZIEL_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_UST_TEILNEHMER_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_UST_TEILNEHMER_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_UST_TEILNEHMER_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_UST_TEILNEHMER_ZIEL_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_UST_TEILNEHMER_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_UST_TEILNEHMER_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_UST_TEILNEHMER_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_VERSIONSZAEHLER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSIONSZAEHLER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VERSIONSZAEHLER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VERSIONSZAEHLER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_VERSIONSZAEHLER_l_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERSIONSZAEHLER_lValue(new Long(0));
			
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

	

	public long get_VERSIONSZAEHLER_l_Count_NotNull(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VERSIONSZAEHLER_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_VERSIONSZAEHLER_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_VERSIONSZAEHLER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_VERSIONSZAEHLER_d_Summe(RECLIST_HANDELSDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_HANDELSDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_HANDELSDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_VERSIONSZAEHLER_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_VERSIONSZAEHLER_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_VERSIONSZAEHLER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ZIEL_IST_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZIEL_IST_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZIEL_IST_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_HANDELSDEF> oEntry: this.entrySet())
		{
			RECORD_HANDELSDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZIEL_IST_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
