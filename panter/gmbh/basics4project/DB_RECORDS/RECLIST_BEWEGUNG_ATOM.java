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


public class RECLIST_BEWEGUNG_ATOM extends HashMap<String,RECORD_BEWEGUNG_ATOM>  implements Iterable<RECORD_BEWEGUNG_ATOM>, IF_RecordList<RECORD_BEWEGUNG_ATOM>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_BEWEGUNG_ATOM";
	private String        	cTableName = "JT_BEWEGUNG_ATOM";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(SELECT select) throws myException
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
	public RECLIST_BEWEGUNG_ATOM(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_BEWEGUNG_ATOM() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_BEWEGUNG_ATOM muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(String QueryString) throws myException
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
	public RECLIST_BEWEGUNG_ATOM(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM";
		
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
	public RECLIST_BEWEGUNG_ATOM(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM";
		
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
	 * @param QueryString !!ID_BEWEGUNG_ATOM muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_BEWEGUNG_ATOM(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_BEWEGUNG_ATOM IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_BEWEGUNG_ATOM IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_BEWEGUNG_ATOM muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_BEWEGUNG_ATOM(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_BEWEGUNG_ATOM muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_ATOM(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_BEWEGUNG_ATOM(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_BEWEGUNG_ATOM  	oRec = 		new RECORD_BEWEGUNG_ATOM(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_BEWEGUNG_ATOM: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_BEWEGUNG_ATOM get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_BEWEGUNG_ATOM!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_BEWEGUNG_ATOM get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_BEWEGUNG_ATOM oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_BEWEGUNG_ATOM oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_BEWEGUNG_ATOM.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_BEWEGUNG_ATOM oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_BEWEGUNG_ATOM oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_BEWEGUNG_ATOM: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_BEWEGUNG_ATOM oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_BEWEGUNG_ATOM get_SUBLIST(RECLIST_BEWEGUNG_ATOM.Validation oValidator)  throws myException
	{
		RECLIST_BEWEGUNG_ATOM oRecListRueck = new RECLIST_BEWEGUNG_ATOM(this.oConn);
		
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
	public Vector<RECORD_BEWEGUNG_ATOM>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_BEWEGUNG_ATOM> vRueck = new Vector<RECORD_BEWEGUNG_ATOM>();
		
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
     public Vector<RECORD_BEWEGUNG_ATOM>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_BEWEGUNG_ATOM>
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

		
		public int compare(RECORD_BEWEGUNG_ATOM o1, RECORD_BEWEGUNG_ATOM o2) 
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
    public Iterator<RECORD_BEWEGUNG_ATOM> iterator() {
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
	
	
	
	
	
	
	


	public HashMap<String, String>  get_ABGERECHNET_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGERECHNET_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABGERECHNET_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGERECHNET_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABRECHENBAR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABRECHENBAR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABRECHENBAR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABRECHENBAR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABZUG_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABZUG_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ABZUG_MENGE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ABZUG_MENGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ABZUG_MENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ABZUG_MENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ARTBEZ1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ARTBEZ2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_SACHBEARBEITER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_SACHBEARBEITER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_SACHBEARBEITER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_SACHBEARBEITER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DELETED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DELETED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_E_PREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_E_PREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_E_PREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_E_PREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_E_PREIS_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_E_PREIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_E_PREIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_E_PREIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_E_PREIS_RESULT_BRUTTO_MGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_E_PREIS_RESULT_BRUTTO_MGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_E_PREIS_RESULT_BRUTTO_MGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_E_PREIS_RESULT_BRUTTO_MGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_E_PREIS_RESULT_BRUTTO_MGE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_E_PREIS_RESULT_BRUTTO_MGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_E_PREIS_RESULT_BRUTTO_MGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_E_PREIS_RESULT_BRUTTO_MGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_E_PREIS_RESULT_NETTO_MGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_E_PREIS_RESULT_NETTO_MGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_E_PREIS_RESULT_NETTO_MGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_E_PREIS_RESULT_NETTO_MGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_E_PREIS_RESULT_NETTO_MGE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_E_PREIS_RESULT_NETTO_MGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_E_PREIS_RESULT_NETTO_MGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_E_PREIS_RESULT_NETTO_MGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GESAMTPREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GESAMTPREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GESAMTPREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GESAMTPREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GESAMTPREIS_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GESAMTPREIS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_GESAMTPREIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GESAMTPREIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GPREIS_ABZ_AUF_NETTOMGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GPREIS_ABZ_AUF_NETTOMGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GPREIS_ABZ_AUF_NETTOMGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GPREIS_ABZ_AUF_NETTOMGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GPREIS_ABZ_AUF_NETTOMGE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GPREIS_ABZ_AUF_NETTOMGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_GPREIS_ABZ_AUF_NETTOMGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GPREIS_ABZ_AUF_NETTOMGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GPREIS_ABZ_MGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GPREIS_ABZ_MGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GPREIS_ABZ_MGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GPREIS_ABZ_MGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GPREIS_ABZ_MGE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GPREIS_ABZ_MGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_GPREIS_ABZ_MGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GPREIS_ABZ_MGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GPREIS_ABZ_VORAUSZAHLUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GPREIS_ABZ_VORAUSZAHLUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GPREIS_ABZ_VORAUSZAHLUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GPREIS_ABZ_VORAUSZAHLUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GPREIS_ABZ_VORAUSZAHLUNG_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GPREIS_ABZ_VORAUSZAHLUNG_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_GPREIS_ABZ_VORAUSZAHLUNG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GPREIS_ABZ_VORAUSZAHLUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_WB_START_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_WB_START_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_WB_START_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_WB_START_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_WB_START_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_WB_START_lValue(new Long(0));
			
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

	

	public long get_ID_ADRESSE_WB_START_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_WB_START_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_WB_START_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_WB_START_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_WB_START_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_WB_START_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_WB_START_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_WB_START_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_WB_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_WB_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_WB_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_WB_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_WB_ZIEL_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_WB_ZIEL_lValue(new Long(0));
			
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

	

	public long get_ID_ADRESSE_WB_ZIEL_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_WB_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_WB_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_WB_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_WB_ZIEL_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_WB_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_WB_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_WB_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_lValue(new Long(0));
			
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

	

	public long get_ID_ARTIKEL_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ARTIKEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ARTIKEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ARTIKEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_BEZ_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_lValue(new Long(0));
			
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

	

	public long get_ID_ARTIKEL_BEZ_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ARTIKEL_BEZ_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_BEZ_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_BEZ_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ARTIKEL_BEZ_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_ARTIKEL_BEZ_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_BEZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BEWEGUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BEWEGUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BEWEGUNG_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_lValue(new Long(0));
			
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

	

	public long get_ID_BEWEGUNG_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BEWEGUNG_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BEWEGUNG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BEWEGUNG_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BEWEGUNG_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_BEWEGUNG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BEWEGUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BEWEGUNGSTATION_LOGI_START_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNGSTATION_LOGI_START_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BEWEGUNGSTATION_LOGI_START_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNGSTATION_LOGI_START_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BEWEGUNGSTATION_LOGI_START_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNGSTATION_LOGI_START_lValue(new Long(0));
			
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

	

	public long get_ID_BEWEGUNGSTATION_LOGI_START_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNGSTATION_LOGI_START_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BEWEGUNGSTATION_LOGI_START_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BEWEGUNGSTATION_LOGI_START_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BEWEGUNGSTATION_LOGI_START_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BEWEGUNGSTATION_LOGI_START_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_BEWEGUNGSTATION_LOGI_START_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BEWEGUNGSTATION_LOGI_START_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BEWEGUNGSTATION_LOGI_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNGSTATION_LOGI_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BEWEGUNGSTATION_LOGI_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNGSTATION_LOGI_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BEWEGUNGSTATION_LOGI_ZIEL_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNGSTATION_LOGI_ZIEL_lValue(new Long(0));
			
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

	

	public long get_ID_BEWEGUNGSTATION_LOGI_ZIEL_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNGSTATION_LOGI_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BEWEGUNGSTATION_LOGI_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BEWEGUNGSTATION_LOGI_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BEWEGUNGSTATION_LOGI_ZIEL_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BEWEGUNGSTATION_LOGI_ZIEL_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_BEWEGUNGSTATION_LOGI_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BEWEGUNGSTATION_LOGI_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BEWEGUNG_ATOM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_ATOM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BEWEGUNG_ATOM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_ATOM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BEWEGUNG_ATOM_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_ATOM_lValue(new Long(0));
			
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

	

	public long get_ID_BEWEGUNG_ATOM_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_ATOM_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BEWEGUNG_ATOM_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BEWEGUNG_ATOM_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BEWEGUNG_ATOM_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BEWEGUNG_ATOM_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_BEWEGUNG_ATOM_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BEWEGUNG_ATOM_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BEWEGUNG_VEKTOR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_VEKTOR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BEWEGUNG_VEKTOR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_VEKTOR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BEWEGUNG_VEKTOR_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_VEKTOR_lValue(new Long(0));
			
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

	

	public long get_ID_BEWEGUNG_VEKTOR_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_VEKTOR_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BEWEGUNG_VEKTOR_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BEWEGUNG_VEKTOR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BEWEGUNG_VEKTOR_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BEWEGUNG_VEKTOR_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_BEWEGUNG_VEKTOR_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BEWEGUNG_VEKTOR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BEWEGUNG_VEKTOR_POS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_VEKTOR_POS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BEWEGUNG_VEKTOR_POS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BEWEGUNG_VEKTOR_POS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BEWEGUNG_VEKTOR_POS_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_VEKTOR_POS_lValue(new Long(0));
			
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

	

	public long get_ID_BEWEGUNG_VEKTOR_POS_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BEWEGUNG_VEKTOR_POS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BEWEGUNG_VEKTOR_POS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BEWEGUNG_VEKTOR_POS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BEWEGUNG_VEKTOR_POS_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BEWEGUNG_VEKTOR_POS_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_BEWEGUNG_VEKTOR_POS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BEWEGUNG_VEKTOR_POS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAGER_KONTO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAGER_KONTO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAGER_KONTO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAGER_KONTO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAGER_KONTO_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAGER_KONTO_lValue(new Long(0));
			
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

	

	public long get_ID_LAGER_KONTO_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAGER_KONTO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAGER_KONTO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAGER_KONTO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAGER_KONTO_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAGER_KONTO_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_LAGER_KONTO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAGER_KONTO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LIEFERBEDINGUNGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LIEFERBEDINGUNGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LIEFERBEDINGUNGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LIEFERBEDINGUNGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LIEFERBEDINGUNGEN_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LIEFERBEDINGUNGEN_lValue(new Long(0));
			
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

	

	public long get_ID_LIEFERBEDINGUNGEN_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LIEFERBEDINGUNGEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LIEFERBEDINGUNGEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LIEFERBEDINGUNGEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LIEFERBEDINGUNGEN_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LIEFERBEDINGUNGEN_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_LIEFERBEDINGUNGEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LIEFERBEDINGUNGEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_KON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_KON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_KON_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_KON_lValue(new Long(0));
			
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

	

	public long get_ID_VPOS_KON_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_KON_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_KON_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_KON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_KON_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_KON_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_KON_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_KON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_STD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_STD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_STD_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_STD_lValue(new Long(0));
			
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

	

	public long get_ID_VPOS_STD_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_STD_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_STD_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_STD_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_STD_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_STD_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_STD_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_STD_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_FUHRE_lValue(new Long(0));
			
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

	

	public long get_ID_VPOS_TPA_FUHRE_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_FUHRE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_TPA_FUHRE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_TPA_FUHRE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_TPA_FUHRE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ZAHLUNGSBEDINGUNGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ZAHLUNGSBEDINGUNGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ZAHLUNGSBEDINGUNGEN_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ZAHLUNGSBEDINGUNGEN_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ZAHLUNGSBEDINGUNGEN_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ZAHLUNGSBEDINGUNGEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KONTRAKTZWANG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KONTRAKTZWANG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KONTRAKTZWANG_AUS_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_AUS_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KONTRAKTZWANG_AUS_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_AUS_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KONTRAKTZWANG_AUS_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_AUS_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KONTRAKTZWANG_AUS_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_AUS_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KONTRAKTZWANG_AUS_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_AUS_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KONTRAKTZWANG_AUS_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KONTRAKTZWANG_AUS_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LEISTUNGSDATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LEISTUNGSDATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LEISTUNGSDATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LEISTUNGSDATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERBEDINGUNGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBEDINGUNGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERBEDINGUNGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBEDINGUNGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERINFO_TPA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERINFO_TPA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERINFO_TPA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERINFO_TPA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_MENGE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MENGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_MENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_MENGE_NETTO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_NETTO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MENGE_NETTO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_NETTO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_MENGE_NETTO_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MENGE_NETTO_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_MENGE_NETTO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MENGE_NETTO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_MENGE_VERTEILUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_VERTEILUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MENGE_VERTEILUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_VERTEILUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_MENGE_VERTEILUNG_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MENGE_VERTEILUNG_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_MENGE_VERTEILUNG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MENGE_VERTEILUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NATIONALER_ABFALL_CODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NATIONALER_ABFALL_CODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NATIONALER_ABFALL_CODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NATIONALER_ABFALL_CODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ORDNUNGSNUMMER_CMR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORDNUNGSNUMMER_CMR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORDNUNGSNUMMER_CMR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORDNUNGSNUMMER_CMR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PLANMENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLANMENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PLANMENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLANMENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_PLANMENGE_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_PLANMENGE_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_PLANMENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_PLANMENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_POSNR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSNR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_POSNR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSNR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_POSNR_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_POSNR_lValue(new Long(0));
			
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

	

	public long get_POSNR_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_POSNR_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_POSNR_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_POSNR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_POSNR_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_POSNR_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_POSNR_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_POSNR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_POSTENNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_POSTENNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PREISERMITTLUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PREISERMITTLUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PREISERMITTLUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PREISERMITTLUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SETZKASTEN_KOMPLETT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SETZKASTEN_KOMPLETT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SETZKASTEN_KOMPLETT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SETZKASTEN_KOMPLETT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STEUERSATZ_dValue(new Double(0));
			
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
	

	

	
	
	public HashMap<String, Double>  get_STEUERSATZ_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STORNIERT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNIERT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNIERT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNIERT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNIERT_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNIERT_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNIERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNIERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_BEWEGUNG_ATOM.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BEWEGUNG_ATOM>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ZEITSTEMPEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITSTEMPEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEITSTEMPEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BEWEGUNG_ATOM> oEntry: this.entrySet())
		{
			RECORD_BEWEGUNG_ATOM 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITSTEMPEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
