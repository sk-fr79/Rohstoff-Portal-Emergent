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


public class RECLIST_BG_VEKTOR extends HashMap<String,RECORD_BG_VEKTOR>  implements Iterable<RECORD_BG_VEKTOR>, IF_RecordList<RECORD_BG_VEKTOR>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_BG_VEKTOR";
	private String        	cTableName = "JT_BG_VEKTOR";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(SELECT select) throws myException
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
	public RECLIST_BG_VEKTOR(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_BG_VEKTOR() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_BG_VEKTOR muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(String QueryString) throws myException
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
	public RECLIST_BG_VEKTOR(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR";
		
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
	public RECLIST_BG_VEKTOR(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR";
		
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
	 * @param QueryString !!ID_BG_VEKTOR muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_BG_VEKTOR(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_BG_VEKTOR muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_BG_VEKTOR(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_BG_VEKTOR muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_BG_VEKTOR(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_BG_VEKTOR(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_BG_VEKTOR  	oRec = 		new RECORD_BG_VEKTOR(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_BG_VEKTOR: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_BG_VEKTOR get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_BG_VEKTOR!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_BG_VEKTOR get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_BG_VEKTOR oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_BG_VEKTOR oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_BG_VEKTOR.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_BG_VEKTOR oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_BG_VEKTOR oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_BG_VEKTOR: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_BG_VEKTOR oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_BG_VEKTOR get_SUBLIST(RECLIST_BG_VEKTOR.Validation oValidator)  throws myException
	{
		RECLIST_BG_VEKTOR oRecListRueck = new RECLIST_BG_VEKTOR(this.oConn);
		
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
	public Vector<RECORD_BG_VEKTOR>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_BG_VEKTOR> vRueck = new Vector<RECORD_BG_VEKTOR>();
		
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
     public Vector<RECORD_BG_VEKTOR>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_BG_VEKTOR>
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

		
		public int compare(RECORD_BG_VEKTOR o1, RECORD_BG_VEKTOR o2) 
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
    public Iterator<RECORD_BG_VEKTOR> iterator() {
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
	
	
	
	
	
	
	


	public HashMap<String, String>  get_AH7_AUSSTELLUNG_DATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AH7_AUSSTELLUNG_DATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AH7_AUSSTELLUNG_DATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AH7_AUSSTELLUNG_DATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_AH7_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AH7_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AH7_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AH7_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_AH7_MENGE_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_AH7_MENGE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_AH7_MENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_AH7_MENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_AH7_VOLUMEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AH7_VOLUMEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AH7_VOLUMEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AH7_VOLUMEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_AH7_VOLUMEN_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_AH7_VOLUMEN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_AH7_VOLUMEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_AH7_VOLUMEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANHAENGERKENNZEICHEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANHAENGERKENNZEICHEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANHAENGERKENNZEICHEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANHAENGERKENNZEICHEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_FUER_KUNDE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_FUER_KUNDE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_FUER_KUNDE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_FUER_KUNDE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_SACHBEARBEITER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_SACHBEARBEITER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_SACHBEARBEITER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_SACHBEARBEITER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_PLANUNG_BIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_PLANUNG_BIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_PLANUNG_BIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_PLANUNG_BIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_PLANUNG_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_PLANUNG_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_PLANUNG_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_PLANUNG_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EK_VK_SORTE_LOCK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_SORTE_LOCK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EK_VK_SORTE_LOCK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_SORTE_LOCK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EK_VK_ZUORD_ZWANG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_ZUORD_ZWANG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EK_VK_ZUORD_ZWANG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_ZUORD_ZWANG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EN_TRANSPORT_TYP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EN_TRANSPORT_TYP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EN_TRANSPORT_TYP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EN_TRANSPORT_TYP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EN_VEKTOR_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EN_VEKTOR_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EN_VEKTOR_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EN_VEKTOR_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EN_VEKTOR_STATUS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EN_VEKTOR_STATUS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EN_VEKTOR_STATUS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EN_VEKTOR_STATUS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_ADRESSE_FREMDWARE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_FREMDWARE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_FREMDWARE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_FREMDWARE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_FREMDWARE_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_FREMDWARE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_FREMDWARE_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_FREMDWARE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_FREMDWARE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_FREMDWARE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_FREMDWARE_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_FREMDWARE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_FREMDWARE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_FREMDWARE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_LOGI_START_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LOGI_START_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_LOGI_START_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LOGI_START_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_LOGI_START_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LOGI_START_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_LOGI_START_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LOGI_START_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_LOGI_START_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_LOGI_START_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_LOGI_START_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_LOGI_START_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_LOGI_START_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_LOGI_START_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_LOGI_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LOGI_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_LOGI_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LOGI_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_LOGI_ZIEL_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LOGI_ZIEL_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_LOGI_ZIEL_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LOGI_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_LOGI_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_LOGI_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_LOGI_ZIEL_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_LOGI_ZIEL_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_LOGI_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_LOGI_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_SPEDITION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_SPEDITION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_SPEDITION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_SPEDITION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_SPEDITION_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_SPEDITION_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_SPEDITION_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_SPEDITION_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_SPEDITION_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_SPEDITION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_SPEDITION_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_SPEDITION_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_SPEDITION_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_SPEDITION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_ATOM_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_ATOM_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_ATOM_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_ATOM_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_ATOM_QUELLE_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_ATOM_QUELLE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_ATOM_QUELLE_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_ATOM_QUELLE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_ATOM_QUELLE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_ATOM_QUELLE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_ATOM_QUELLE_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_ATOM_QUELLE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_ATOM_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_ATOM_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_ATOM_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_ATOM_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_ATOM_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_ATOM_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_ATOM_ZIEL_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_ATOM_ZIEL_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_ATOM_ZIEL_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_ATOM_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_ATOM_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_ATOM_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_ATOM_ZIEL_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_ATOM_ZIEL_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_ATOM_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_ATOM_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_DEL_INFO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_DEL_INFO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_DEL_INFO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_DEL_INFO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_DEL_INFO_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_DEL_INFO_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_DEL_INFO_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_DEL_INFO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_DEL_INFO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_DEL_INFO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_DEL_INFO_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_DEL_INFO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_DEL_INFO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_DEL_INFO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_PRUEFPROT_ABSCHLUSS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_PRUEFPROT_ABSCHLUSS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_PRUEFPROT_ABSCHLUSS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_PRUEFPROT_ABSCHLUSS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_PRUEFPROT_ABSCHLUSS_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_PRUEFPROT_ABSCHLUSS_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_PRUEFPROT_ABSCHLUSS_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_PRUEFPROT_ABSCHLUSS_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_PRUEFPROT_ABSCHLUSS_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_PRUEFPROT_ABSCHLUSS_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_PRUEFPROT_ABSCHLUSS_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_PRUEFPROT_ABSCHLUSS_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_PRUEFPROT_ABSCHLUSS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_PRUEFPROT_ABSCHLUSS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_STORNO_INFO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_STORNO_INFO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_STORNO_INFO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_STORNO_INFO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_STORNO_INFO_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_STORNO_INFO_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_STORNO_INFO_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_STORNO_INFO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_STORNO_INFO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_STORNO_INFO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_STORNO_INFO_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_STORNO_INFO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_STORNO_INFO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_STORNO_INFO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_VEKTOR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_VEKTOR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_VEKTOR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_VEKTOR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_VEKTOR_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_VEKTOR_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_VEKTOR_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_VEKTOR_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_VEKTOR_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_VEKTOR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_VEKTOR_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_VEKTOR_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_VEKTOR_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_VEKTOR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_VEKTOR_BASE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_VEKTOR_BASE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_VEKTOR_BASE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_VEKTOR_BASE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_VEKTOR_BASE_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_VEKTOR_BASE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_VEKTOR_BASE_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_VEKTOR_BASE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_VEKTOR_BASE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_VEKTOR_BASE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_VEKTOR_BASE_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_VEKTOR_BASE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_VEKTOR_BASE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_VEKTOR_BASE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_BG_VEKTOR_SUB_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_VEKTOR_SUB_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_BG_VEKTOR_SUB_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_BG_VEKTOR_SUB_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_BG_VEKTOR_SUB_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_VEKTOR_SUB_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_BG_VEKTOR_SUB_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_BG_VEKTOR_SUB_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_BG_VEKTOR_SUB_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_BG_VEKTOR_SUB_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_BG_VEKTOR_SUB_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_BG_VEKTOR_SUB_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_BG_VEKTOR_SUB_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_BG_VEKTOR_SUB_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_HANDELSDEF_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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

	

	public long get_ID_HANDELSDEF_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_HANDELSDEF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_HANDELSDEF_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_HANDELSDEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_TRANSIT1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_TRANSIT1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_TRANSIT1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_TRANSIT1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_TRANSIT1_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_TRANSIT1_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_LAND_TRANSIT1_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_TRANSIT1_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_TRANSIT1_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_TRANSIT1_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_TRANSIT1_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_TRANSIT1_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_TRANSIT1_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_TRANSIT1_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_TRANSIT2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_TRANSIT2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_TRANSIT2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_TRANSIT2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_TRANSIT2_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_TRANSIT2_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_LAND_TRANSIT2_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_TRANSIT2_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_TRANSIT2_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_TRANSIT2_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_TRANSIT2_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_TRANSIT2_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_TRANSIT2_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_TRANSIT2_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAND_TRANSIT3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_TRANSIT3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAND_TRANSIT3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAND_TRANSIT3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAND_TRANSIT3_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_TRANSIT3_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_LAND_TRANSIT3_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAND_TRANSIT3_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAND_TRANSIT3_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAND_TRANSIT3_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAND_TRANSIT3_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAND_TRANSIT3_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_LAND_TRANSIT3_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAND_TRANSIT3_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TRANSPORTMITTEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TRANSPORTMITTEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TRANSPORTMITTEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TRANSPORTMITTEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TRANSPORTMITTEL_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TRANSPORTMITTEL_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_TRANSPORTMITTEL_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TRANSPORTMITTEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TRANSPORTMITTEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TRANSPORTMITTEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TRANSPORTMITTEL_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TRANSPORTMITTEL_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_TRANSPORTMITTEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TRANSPORTMITTEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_UMA_KONTRAKT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_UMA_KONTRAKT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_UMA_KONTRAKT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_UMA_KONTRAKT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_UMA_KONTRAKT_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_UMA_KONTRAKT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_UMA_KONTRAKT_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_UMA_KONTRAKT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_UMA_KONTRAKT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_UMA_KONTRAKT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_UMA_KONTRAKT_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_UMA_KONTRAKT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_UMA_KONTRAKT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_UMA_KONTRAKT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VERARBEITUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERARBEITUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VERARBEITUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERARBEITUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VERARBEITUNG_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VERARBEITUNG_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VERARBEITUNG_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VERARBEITUNG_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VERARBEITUNG_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VERARBEITUNG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VERARBEITUNG_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VERARBEITUNG_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VERARBEITUNG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VERARBEITUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VERPACKUNGSART_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERPACKUNGSART_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VERPACKUNGSART_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERPACKUNGSART_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VERPACKUNGSART_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VERPACKUNGSART_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VERPACKUNGSART_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VERPACKUNGSART_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VERPACKUNGSART_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VERPACKUNGSART_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VERPACKUNGSART_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VERPACKUNGSART_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VERPACKUNGSART_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VERPACKUNGSART_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_KOSTEN_TRANSPORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KOSTEN_TRANSPORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KOSTEN_TRANSPORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KOSTEN_TRANSPORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_KOSTEN_TRANSPORT_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_KOSTEN_TRANSPORT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_KOSTEN_TRANSPORT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_KOSTEN_TRANSPORT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERINFO_TPA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERINFO_TPA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERINFO_TPA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERINFO_TPA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ORDNUNGSNUMMER_CMR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORDNUNGSNUMMER_CMR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORDNUNGSNUMMER_CMR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORDNUNGSNUMMER_CMR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PLANMENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLANMENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PLANMENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLANMENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_PLANMENGE_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_PLANMENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_POSNR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSNR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_POSNR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSNR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_POSNR_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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

	

	public long get_POSNR_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_POSNR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_POSNR_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_POSNR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_PRINT_ANHANG7_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRINT_ANHANG7_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRINT_ANHANG7_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRINT_ANHANG7_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_BG_VEKTOR.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_BG_VEKTOR>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_BG_VEKTOR 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TIMESTAMP_IN_MASK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TIMESTAMP_IN_MASK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TIMESTAMP_IN_MASK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TIMESTAMP_IN_MASK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSPORTKENNZEICHEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTKENNZEICHEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSPORTKENNZEICHEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTKENNZEICHEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSPORTMITTEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTMITTEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSPORTMITTEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTMITTEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSPORTVERANTWORTUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTVERANTWORTUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSPORTVERANTWORTUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_BG_VEKTOR> oEntry: this.entrySet())
		{
			RECORD_BG_VEKTOR 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTVERANTWORTUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
