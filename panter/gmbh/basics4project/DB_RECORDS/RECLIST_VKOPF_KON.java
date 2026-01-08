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


public class RECLIST_VKOPF_KON extends HashMap<String,RECORD_VKOPF_KON>  implements Iterable<RECORD_VKOPF_KON>, IF_RecordList<RECORD_VKOPF_KON>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_VKOPF_KON";
	private String        	cTableName = "JT_VKOPF_KON";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(SELECT select) throws myException
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
	public RECLIST_VKOPF_KON(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_VKOPF_KON() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_VKOPF_KON muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(String QueryString) throws myException
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
	public RECLIST_VKOPF_KON(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON";
		
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
	public RECLIST_VKOPF_KON(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON";
		
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
	 * @param QueryString !!ID_VKOPF_KON muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_VKOPF_KON(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_VKOPF_KON IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_VKOPF_KON IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_VKOPF_KON muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_VKOPF_KON(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_VKOPF_KON muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VKOPF_KON(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_VKOPF_KON(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_VKOPF_KON  	oRec = 		new RECORD_VKOPF_KON(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_VKOPF_KON: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_VKOPF_KON get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_VKOPF_KON!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_VKOPF_KON get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_VKOPF_KON oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_VKOPF_KON oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_VKOPF_KON.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_VKOPF_KON oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_VKOPF_KON oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_VKOPF_KON: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_VKOPF_KON oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_VKOPF_KON get_SUBLIST(RECLIST_VKOPF_KON.Validation oValidator)  throws myException
	{
		RECLIST_VKOPF_KON oRecListRueck = new RECLIST_VKOPF_KON(this.oConn);
		
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
	public Vector<RECORD_VKOPF_KON>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_VKOPF_KON> vRueck = new Vector<RECORD_VKOPF_KON>();
		
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
     public Vector<RECORD_VKOPF_KON>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_VKOPF_KON>
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

		
		public int compare(RECORD_VKOPF_KON o1, RECORD_VKOPF_KON o2) 
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
    public Iterator<RECORD_VKOPF_KON> iterator() {
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
	
	
	
	
	
	
	


	public HashMap<String, String>  get_ABGESCHLOSSEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNGEN_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNGEN_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNGEN_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNGEN_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_FIX1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_FIX1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_FIX1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_FIX1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEZUG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEZUG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEZUG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEZUG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BOERSE_DIFF_ABS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BOERSE_DIFF_ABS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BOERSE_DIFF_ABS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BOERSE_DIFF_ABS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_BOERSE_DIFF_ABS_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_BOERSE_DIFF_ABS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_BOERSE_DIFF_ABS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_BOERSE_DIFF_ABS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_BOERSE_DIFF_PROZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BOERSE_DIFF_PROZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BOERSE_DIFF_PROZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BOERSE_DIFF_PROZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_BOERSE_DIFF_PROZ_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_BOERSE_DIFF_PROZ_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_BOERSE_DIFF_PROZ_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_BOERSE_DIFF_PROZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_BUCHUNGSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DELETED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DELETED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DRUCKDATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKDATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DRUCKDATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKDATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DRUCKZAEHLER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKZAEHLER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DRUCKZAEHLER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKZAEHLER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_DRUCKZAEHLER_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_DRUCKZAEHLER_lValue(new Long(0));
			
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

	

	public long get_DRUCKZAEHLER_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_DRUCKZAEHLER_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_DRUCKZAEHLER_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_DRUCKZAEHLER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_DRUCKZAEHLER_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_DRUCKZAEHLER_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_DRUCKZAEHLER_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_DRUCKZAEHLER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EMAIL_AUF_FORMULAR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EMAIL_AUF_FORMULAR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EMAIL_AUF_FORMULAR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EMAIL_AUF_FORMULAR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERSTELLUNGSDATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERSTELLUNGSDATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERSTELLUNGSDATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERSTELLUNGSDATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAX_ANSPRECH_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_ANSPRECH_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAX_ANSPRECH_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_ANSPRECH_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAX_BEARBEITER_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_BEARBEITER_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAX_BEARBEITER_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_BEARBEITER_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAX_SACHBEARB_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_SACHBEARB_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAX_SACHBEARB_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_SACHBEARB_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIXMONAT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXMONAT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIXMONAT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXMONAT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_FIXMONAT_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIXMONAT_lValue(new Long(0));
			
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

	

	public long get_FIXMONAT_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIXMONAT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_FIXMONAT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_FIXMONAT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_FIXMONAT_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_FIXMONAT_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_FIXMONAT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_FIXMONAT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_FIXNUMMER_EIGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXNUMMER_EIGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIXNUMMER_EIGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXNUMMER_EIGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIXNUMMER_FREMD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXNUMMER_FREMD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIXNUMMER_FREMD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXNUMMER_FREMD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIXTAG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXTAG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIXTAG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIXTAG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_FIXTAG_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIXTAG_lValue(new Long(0));
			
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

	

	public long get_FIXTAG_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIXTAG_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_FIXTAG_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_FIXTAG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_FIXTAG_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_FIXTAG_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_FIXTAG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_FIXTAG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_FIX_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIX_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FIX_ID_ARTBEZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_ID_ARTBEZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIX_ID_ARTBEZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_ID_ARTBEZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_FIX_ID_ARTBEZ_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIX_ID_ARTBEZ_lValue(new Long(0));
			
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

	

	public long get_FIX_ID_ARTBEZ_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIX_ID_ARTBEZ_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_FIX_ID_ARTBEZ_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_FIX_ID_ARTBEZ_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_FIX_ID_ARTBEZ_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_FIX_ID_ARTBEZ_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_FIX_ID_ARTBEZ_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_FIX_ID_ARTBEZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_FIX_ID_ARTIKEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_ID_ARTIKEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIX_ID_ARTIKEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_ID_ARTIKEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_FIX_ID_ARTIKEL_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIX_ID_ARTIKEL_lValue(new Long(0));
			
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

	

	public long get_FIX_ID_ARTIKEL_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIX_ID_ARTIKEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_FIX_ID_ARTIKEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_FIX_ID_ARTIKEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_FIX_ID_ARTIKEL_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_FIX_ID_ARTIKEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_FIX_ID_ARTIKEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_FIX_ID_ARTIKEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_FIX_MENGE_GESAMT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_MENGE_GESAMT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIX_MENGE_GESAMT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_MENGE_GESAMT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_FIX_MENGE_GESAMT_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIX_MENGE_GESAMT_lValue(new Long(0));
			
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

	

	public long get_FIX_MENGE_GESAMT_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FIX_MENGE_GESAMT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_FIX_MENGE_GESAMT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_FIX_MENGE_GESAMT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_FIX_MENGE_GESAMT_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_FIX_MENGE_GESAMT_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_FIX_MENGE_GESAMT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_FIX_MENGE_GESAMT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_FIX_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FIX_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FIX_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FORMULARTEXT_ANFANG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FORMULARTEXT_ANFANG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FORMULARTEXT_ANFANG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FORMULARTEXT_ANFANG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FORMULARTEXT_ENDE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FORMULARTEXT_ENDE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FORMULARTEXT_ENDE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FORMULARTEXT_ENDE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GUELTIG_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GUELTIG_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GUELTIG_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GUELTIG_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GUELTIG_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GUELTIG_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GUELTIG_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GUELTIG_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_HAUSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAUSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_HAUSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAUSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_ADRESSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ADRESSE_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_USER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_USER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_USER_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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

	

	public long get_ID_USER_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_USER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_USER_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_USER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_USER_ANSPRECH_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_ANSPRECH_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_USER_ANSPRECH_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_ANSPRECH_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_USER_ANSPRECH_INTERN_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USER_ANSPRECH_INTERN_lValue(new Long(0));
			
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

	

	public long get_ID_USER_ANSPRECH_INTERN_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USER_ANSPRECH_INTERN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_USER_ANSPRECH_INTERN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_USER_ANSPRECH_INTERN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_USER_ANSPRECH_INTERN_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_USER_ANSPRECH_INTERN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_USER_ANSPRECH_INTERN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_USER_ANSPRECH_INTERN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_USER_SACHBEARB_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_SACHBEARB_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_USER_SACHBEARB_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_USER_SACHBEARB_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_USER_SACHBEARB_INTERN_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USER_SACHBEARB_INTERN_lValue(new Long(0));
			
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

	

	public long get_ID_USER_SACHBEARB_INTERN_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_USER_SACHBEARB_INTERN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_USER_SACHBEARB_INTERN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_USER_SACHBEARB_INTERN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_USER_SACHBEARB_INTERN_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_USER_SACHBEARB_INTERN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_USER_SACHBEARB_INTERN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_USER_SACHBEARB_INTERN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VKOPF_KON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VKOPF_KON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VKOPF_KON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VKOPF_KON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VKOPF_KON_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VKOPF_KON_lValue(new Long(0));
			
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

	

	public long get_ID_VKOPF_KON_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VKOPF_KON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VKOPF_KON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VKOPF_KON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VKOPF_KON_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VKOPF_KON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_VKOPF_KON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VKOPF_KON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WAEHRUNG_FREMD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WAEHRUNG_FREMD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WAEHRUNG_FREMD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WAEHRUNG_FREMD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WAEHRUNG_FREMD_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WAEHRUNG_FREMD_lValue(new Long(0));
			
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

	

	public long get_ID_WAEHRUNG_FREMD_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WAEHRUNG_FREMD_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WAEHRUNG_FREMD_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WAEHRUNG_FREMD_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WAEHRUNG_FREMD_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WAEHRUNG_FREMD_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_WAEHRUNG_FREMD_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WAEHRUNG_FREMD_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ZAHLUNGSBEDINGUNGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ZAHLUNGSBEDINGUNGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ZAHLUNGSBEDINGUNGEN_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ZAHLUNGSBEDINGUNGEN_lValue(new Long(0));
			
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

	

	public long get_ID_ZAHLUNGSBEDINGUNGEN_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ZAHLUNGSBEDINGUNGEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ZAHLUNGSBEDINGUNGEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ZAHLUNGSBEDINGUNGEN_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ZAHLUNGSBEDINGUNGEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ZAHLUNGSBEDINGUNGEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_IST_FIXIERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_FIXIERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_FIXIERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_FIXIERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_IS_POB_ACTIVE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IS_POB_ACTIVE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IS_POB_ACTIVE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IS_POB_ACTIVE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KDNR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KDNR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KDNR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KDNR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KOPIE_BEMERKUNG_AUF_POS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KOPIE_BEMERKUNG_AUF_POS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KOPIE_BEMERKUNG_AUF_POS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KOPIE_BEMERKUNG_AUF_POS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTER_DRUCK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTER_DRUCK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTER_DRUCK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTER_DRUCK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERADRESSE_AKTIV_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERADRESSE_AKTIV_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERADRESSE_AKTIV_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERADRESSE_AKTIV_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERBEDINGUNGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBEDINGUNGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERBEDINGUNGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBEDINGUNGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_HAUSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_HAUSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_HAUSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_HAUSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_LAENDERCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_LAENDERCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_LAENDERCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_LAENDERCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_NAME1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_NAME1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_NAME2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_NAME2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_NAME3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_NAME3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_ORTZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORTZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_ORTZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORTZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_PLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_PLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_PLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_PLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_STRASSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_STRASSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_STRASSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_STRASSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_VORNAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_VORNAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_VORNAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_VORNAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME_ANSPRECHPARTNER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_ANSPRECHPARTNER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME_ANSPRECHPARTNER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_ANSPRECHPARTNER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME_ANSPRECH_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_ANSPRECH_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME_ANSPRECH_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_ANSPRECH_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME_BEARBEITER_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_BEARBEITER_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME_BEARBEITER_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_BEARBEITER_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME_SACHBEARB_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_SACHBEARB_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME_SACHBEARB_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME_SACHBEARB_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ORTZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORTZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORTZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORTZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PLZ_POB_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_POB_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PLZ_POB_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_POB_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_POB_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POB_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_POB_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POB_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SKONTO_PROZENT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SKONTO_PROZENT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SKONTO_PROZENT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SKONTO_PROZENT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_SKONTO_PROZENT_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SKONTO_PROZENT_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_SKONTO_PROZENT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SKONTO_PROZENT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SPRACHE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SPRACHE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SPRACHE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SPRACHE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STRASSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRASSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STRASSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRASSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TEILZAHLUNG_PROZENT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEILZAHLUNG_PROZENT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEILZAHLUNG_PROZENT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEILZAHLUNG_PROZENT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_TEILZAHLUNG_PROZENT_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_TEILZAHLUNG_PROZENT_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_TEILZAHLUNG_PROZENT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_TEILZAHLUNG_PROZENT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TELEFAX_AUF_FORMULAR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFAX_AUF_FORMULAR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TELEFAX_AUF_FORMULAR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFAX_AUF_FORMULAR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TELEFON_AUF_FORMULAR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFON_AUF_FORMULAR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TELEFON_AUF_FORMULAR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TELEFON_AUF_FORMULAR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TEL_ANSPRECH_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_ANSPRECH_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEL_ANSPRECH_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_ANSPRECH_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TEL_BEARBEITER_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_BEARBEITER_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEL_BEARBEITER_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_BEARBEITER_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TEL_SACHBEARB_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_SACHBEARB_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEL_SACHBEARB_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_SACHBEARB_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TYP_25_TO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_25_TO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TYP_25_TO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_25_TO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TYP_LADUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_LADUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TYP_LADUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_LADUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VORGANGSGRUPPE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORGANGSGRUPPE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VORGANGSGRUPPE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORGANGSGRUPPE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_VORGANGSGRUPPE_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VORGANGSGRUPPE_lValue(new Long(0));
			
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

	

	public long get_VORGANGSGRUPPE_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_VORGANGSGRUPPE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_VORGANGSGRUPPE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_VORGANGSGRUPPE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_VORGANGSGRUPPE_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_VORGANGSGRUPPE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_VORGANGSGRUPPE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_VORGANGSGRUPPE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_VORGANG_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORGANG_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VORGANG_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORGANG_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VORGANG_TYP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORGANG_TYP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VORGANG_TYP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORGANG_TYP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_VORNAME_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORNAME_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VORNAME_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VORNAME_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZAEHLER_ENTSPERRUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAEHLER_ENTSPERRUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZAEHLER_ENTSPERRUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAEHLER_ENTSPERRUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ZAEHLER_ENTSPERRUNG_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ZAEHLER_ENTSPERRUNG_lValue(new Long(0));
			
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

	

	public long get_ZAEHLER_ENTSPERRUNG_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ZAEHLER_ENTSPERRUNG_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ZAEHLER_ENTSPERRUNG_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ZAEHLER_ENTSPERRUNG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ZAEHLER_ENTSPERRUNG_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ZAEHLER_ENTSPERRUNG_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ZAEHLER_ENTSPERRUNG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ZAEHLER_ENTSPERRUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ZAHLTAGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHLTAGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZAHLTAGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHLTAGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ZAHLTAGE_l_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ZAHLTAGE_lValue(new Long(0));
			
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

	

	public long get_ZAHLTAGE_l_Count_NotNull(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ZAHLTAGE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ZAHLTAGE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ZAHLTAGE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ZAHLTAGE_d_Summe(RECLIST_VKOPF_KON.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VKOPF_KON>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VKOPF_KON 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ZAHLTAGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ZAHLTAGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ZAHLTAGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ZAHLUNGSBEDINGUNGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHLUNGSBEDINGUNGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZAHLUNGSBEDINGUNGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VKOPF_KON> oEntry: this.entrySet())
		{
			RECORD_VKOPF_KON 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZAHLUNGSBEDINGUNGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
