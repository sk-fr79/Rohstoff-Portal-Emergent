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


public class RECLIST_WIEGEKARTE extends HashMap<String,RECORD_WIEGEKARTE>  implements Iterable<RECORD_WIEGEKARTE>, IF_RecordList<RECORD_WIEGEKARTE>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_WIEGEKARTE";
	private String        	cTableName = "JT_WIEGEKARTE";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(SELECT select) throws myException
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
	public RECLIST_WIEGEKARTE(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_WIEGEKARTE() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_WIEGEKARTE muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(String QueryString) throws myException
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
	public RECLIST_WIEGEKARTE(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE";
		
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
	public RECLIST_WIEGEKARTE(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE";
		
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
	 * @param QueryString !!ID_WIEGEKARTE muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_WIEGEKARTE(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_WIEGEKARTE muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_WIEGEKARTE(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_WIEGEKARTE muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_WIEGEKARTE(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_WIEGEKARTE(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_WIEGEKARTE  	oRec = 		new RECORD_WIEGEKARTE(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_WIEGEKARTE: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_WIEGEKARTE get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_WIEGEKARTE!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_WIEGEKARTE get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_WIEGEKARTE oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_WIEGEKARTE oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_WIEGEKARTE.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_WIEGEKARTE oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_WIEGEKARTE oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_WIEGEKARTE: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_WIEGEKARTE oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_WIEGEKARTE get_SUBLIST(RECLIST_WIEGEKARTE.Validation oValidator)  throws myException
	{
		RECLIST_WIEGEKARTE oRecListRueck = new RECLIST_WIEGEKARTE(this.oConn);
		
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
	public Vector<RECORD_WIEGEKARTE>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_WIEGEKARTE> vRueck = new Vector<RECORD_WIEGEKARTE>();
		
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
     public Vector<RECORD_WIEGEKARTE>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_WIEGEKARTE>
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

		
		public int compare(RECORD_WIEGEKARTE o1, RECORD_WIEGEKARTE o2) 
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
    public Iterator<RECORD_WIEGEKARTE> iterator() {
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
	
	
	
	
	
	
	


	public HashMap<String, String>  get_ADRESSE_LIEFERANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ADRESSE_LIEFERANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ADRESSE_LIEFERANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ADRESSE_LIEFERANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ADRESSE_SPEDITION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ADRESSE_SPEDITION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ADRESSE_SPEDITION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ADRESSE_SPEDITION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANZ_ALLG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_ALLG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANZ_ALLG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_ALLG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANZ_ALLG_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANZ_ALLG_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANZ_ALLG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANZ_ALLG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANZ_BEHAELTER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_BEHAELTER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANZ_BEHAELTER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_BEHAELTER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANZ_BEHAELTER_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANZ_BEHAELTER_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANZ_BEHAELTER_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANZ_BEHAELTER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANZ_BIGBAGS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_BIGBAGS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANZ_BIGBAGS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_BIGBAGS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANZ_BIGBAGS_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANZ_BIGBAGS_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANZ_BIGBAGS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANZ_BIGBAGS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANZ_GITTERBOXEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_GITTERBOXEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANZ_GITTERBOXEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_GITTERBOXEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANZ_GITTERBOXEN_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANZ_GITTERBOXEN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANZ_GITTERBOXEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANZ_GITTERBOXEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANZ_PALETTEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_PALETTEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANZ_PALETTEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZ_PALETTEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANZ_PALETTEN_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANZ_PALETTEN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANZ_PALETTEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANZ_PALETTEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_BEFUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEFUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEFUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEFUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_INTERN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_INTERN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_INTERN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_INTERN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEZ_ALLG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEZ_ALLG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEZ_ALLG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEZ_ALLG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_CONTAINER_ABSETZ_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_CONTAINER_ABSETZ_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_CONTAINER_ABSETZ_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_CONTAINER_ABSETZ_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_CONTAINER_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_CONTAINER_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_CONTAINER_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_CONTAINER_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_CONTAINER_TARA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_CONTAINER_TARA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_CONTAINER_TARA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_CONTAINER_TARA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_CONTAINER_TARA_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_CONTAINER_TARA_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_CONTAINER_TARA_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_CONTAINER_TARA_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_DRUCKZAEHLER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKZAEHLER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DRUCKZAEHLER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKZAEHLER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_DRUCKZAEHLER_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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

	

	public long get_DRUCKZAEHLER_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_DRUCKZAEHLER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_DRUCKZAEHLER_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_DRUCKZAEHLER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_DRUCKZAEHLER_EINGANGSSCHEIN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKZAEHLER_EINGANGSSCHEIN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DRUCKZAEHLER_EINGANGSSCHEIN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DRUCKZAEHLER_EINGANGSSCHEIN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_DRUCKZAEHLER_EINGANGSSCHEIN_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_DRUCKZAEHLER_EINGANGSSCHEIN_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_DRUCKZAEHLER_EINGANGSSCHEIN_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_DRUCKZAEHLER_EINGANGSSCHEIN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_DRUCKZAEHLER_EINGANGSSCHEIN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_DRUCKZAEHLER_EINGANGSSCHEIN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_DRUCKZAEHLER_EINGANGSSCHEIN_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_DRUCKZAEHLER_EINGANGSSCHEIN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_DRUCKZAEHLER_EINGANGSSCHEIN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_DRUCKZAEHLER_EINGANGSSCHEIN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ES_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ES_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ES_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ES_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEDRUCKT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEDRUCKT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEDRUCKT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEDRUCKT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEWICHT_ABZUG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_ABZUG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEWICHT_ABZUG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_ABZUG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GEWICHT_ABZUG_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GEWICHT_ABZUG_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_GEWICHT_ABZUG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GEWICHT_ABZUG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GEWICHT_ABZUG_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_ABZUG_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEWICHT_ABZUG_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_ABZUG_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GEWICHT_ABZUG_FUHRE_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GEWICHT_ABZUG_FUHRE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_GEWICHT_ABZUG_FUHRE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GEWICHT_ABZUG_FUHRE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GEWICHT_NACH_ABZUG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_NACH_ABZUG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEWICHT_NACH_ABZUG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_NACH_ABZUG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GEWICHT_NACH_ABZUG_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GEWICHT_NACH_ABZUG_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_GEWICHT_NACH_ABZUG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GEWICHT_NACH_ABZUG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GEWICHT_NACH_ABZUG_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_NACH_ABZUG_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEWICHT_NACH_ABZUG_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEWICHT_NACH_ABZUG_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_GEWICHT_NACH_ABZUG_FUHRE_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_GEWICHT_NACH_ABZUG_FUHRE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_GEWICHT_NACH_ABZUG_FUHRE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_GEWICHT_NACH_ABZUG_FUHRE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_GRUND_ABZUG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GRUND_ABZUG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GRUND_ABZUG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GRUND_ABZUG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GUETERKATEGORIE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GUETERKATEGORIE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GUETERKATEGORIE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GUETERKATEGORIE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_ADRESSE_ABN_STRECKE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_ABN_STRECKE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_ABN_STRECKE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_ABN_STRECKE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_ABN_STRECKE_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_ABN_STRECKE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_ABN_STRECKE_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_ABN_STRECKE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_ABN_STRECKE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_ABN_STRECKE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_ABN_STRECKE_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_ABN_STRECKE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_ABN_STRECKE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_ABN_STRECKE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_LAGER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_LAGER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_LAGER_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LAGER_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_LAGER_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LAGER_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_LAGER_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_LAGER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_LAGER_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_LAGER_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_LAGER_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_LAGER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_LIEFERANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LIEFERANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_LIEFERANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LIEFERANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_LIEFERANT_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LIEFERANT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_LIEFERANT_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LIEFERANT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_LIEFERANT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_LIEFERANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_LIEFERANT_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_LIEFERANT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_LIEFERANT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_LIEFERANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_SPEDITION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_SPEDITION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_SPEDITION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_SPEDITION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_SPEDITION_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ADRESSE_SPEDITION_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_SPEDITION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_SPEDITION_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_SPEDITION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_BEZ_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ARTIKEL_BEZ_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_BEZ_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_BEZ_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_BEZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_SORTE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_SORTE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_SORTE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_SORTE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_SORTE_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_SORTE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ARTIKEL_SORTE_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_SORTE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ARTIKEL_SORTE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_SORTE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_SORTE_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ARTIKEL_SORTE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ARTIKEL_SORTE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_SORTE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_CONTAINER_EIGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_CONTAINER_EIGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_CONTAINER_EIGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_CONTAINER_EIGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_CONTAINER_EIGEN_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_CONTAINER_EIGEN_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_CONTAINER_EIGEN_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_CONTAINER_EIGEN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_CONTAINER_EIGEN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_CONTAINER_EIGEN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_CONTAINER_EIGEN_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_CONTAINER_EIGEN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_CONTAINER_EIGEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_CONTAINER_EIGEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAGERPLATZ_ABSETZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAGERPLATZ_ABSETZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAGERPLATZ_ABSETZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAGERPLATZ_ABSETZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAGERPLATZ_ABSETZ_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAGERPLATZ_ABSETZ_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_LAGERPLATZ_ABSETZ_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAGERPLATZ_ABSETZ_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAGERPLATZ_ABSETZ_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAGERPLATZ_ABSETZ_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAGERPLATZ_ABSETZ_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAGERPLATZ_ABSETZ_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_LAGERPLATZ_ABSETZ_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAGERPLATZ_ABSETZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_LAGERPLATZ_SCHUETT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAGERPLATZ_SCHUETT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_LAGERPLATZ_SCHUETT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_LAGERPLATZ_SCHUETT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_LAGERPLATZ_SCHUETT_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAGERPLATZ_SCHUETT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_LAGERPLATZ_SCHUETT_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_LAGERPLATZ_SCHUETT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_LAGERPLATZ_SCHUETT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_LAGERPLATZ_SCHUETT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_LAGERPLATZ_SCHUETT_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_LAGERPLATZ_SCHUETT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_LAGERPLATZ_SCHUETT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_LAGERPLATZ_SCHUETT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_TPA_FUHRE_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_ORT_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_FUHRE_ORT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VPOS_TPA_FUHRE_ORT_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_FUHRE_ORT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_TPA_FUHRE_ORT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_ORT_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_TPA_FUHRE_ORT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_TPA_FUHRE_ORT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WAAGE_STANDORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WAAGE_STANDORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WAAGE_STANDORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WAAGE_STANDORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WAAGE_STANDORT_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WAAGE_STANDORT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_WAAGE_STANDORT_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WAAGE_STANDORT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WAAGE_STANDORT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WAAGE_STANDORT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WAAGE_STANDORT_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WAAGE_STANDORT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_WAAGE_STANDORT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WAAGE_STANDORT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WIEGEKARTE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WIEGEKARTE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WIEGEKARTE_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_WIEGEKARTE_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WIEGEKARTE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WIEGEKARTE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WIEGEKARTE_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WIEGEKARTE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_WIEGEKARTE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WIEGEKARTE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WIEGEKARTE_PARENT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_PARENT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WIEGEKARTE_PARENT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_PARENT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WIEGEKARTE_PARENT_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_PARENT_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_WIEGEKARTE_PARENT_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_PARENT_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WIEGEKARTE_PARENT_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WIEGEKARTE_PARENT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WIEGEKARTE_PARENT_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WIEGEKARTE_PARENT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_WIEGEKARTE_PARENT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WIEGEKARTE_PARENT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WIEGEKARTE_STORNO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_STORNO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WIEGEKARTE_STORNO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_STORNO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WIEGEKARTE_STORNO_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_STORNO_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_WIEGEKARTE_STORNO_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_STORNO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WIEGEKARTE_STORNO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WIEGEKARTE_STORNO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WIEGEKARTE_STORNO_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WIEGEKARTE_STORNO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_WIEGEKARTE_STORNO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WIEGEKARTE_STORNO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_IST_GESAMTVERWIEGUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_GESAMTVERWIEGUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_GESAMTVERWIEGUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_GESAMTVERWIEGUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_IST_LIEFERANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_LIEFERANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_LIEFERANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_LIEFERANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KENNZEICHEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KENNZEICHEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KENNZEICHEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KENNZEICHEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LFD_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LFD_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LFD_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LFD_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NETTOGEWICHT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NETTOGEWICHT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NETTOGEWICHT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NETTOGEWICHT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_NETTOGEWICHT_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_NETTOGEWICHT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_NETTOGEWICHT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_NETTOGEWICHT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SIEGEL_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SIEGEL_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SIEGEL_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SIEGEL_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SORTE_HAND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_HAND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTE_HAND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTE_HAND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNIERT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNIERT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNIERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNIERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNIERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNO_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNO_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STRAHLUNG_GEPRUEFT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRAHLUNG_GEPRUEFT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STRAHLUNG_GEPRUEFT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRAHLUNG_GEPRUEFT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TARA_KORR_CONTAINER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TARA_KORR_CONTAINER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TARA_KORR_CONTAINER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TARA_KORR_CONTAINER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_TARA_KORR_CONTAINER_d_Summe(RECLIST_WIEGEKARTE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_WIEGEKARTE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_WIEGEKARTE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_TARA_KORR_CONTAINER_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_TARA_KORR_CONTAINER_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_TARA_KORR_CONTAINER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TRAILER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRAILER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRAILER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRAILER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TYP_WIEGEKARTE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_WIEGEKARTE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TYP_WIEGEKARTE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_WIEGEKARTE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_UVV_DATUM_CONTAINER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UVV_DATUM_CONTAINER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_UVV_DATUM_CONTAINER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_WIEGEKARTE> oEntry: this.entrySet())
		{
			RECORD_WIEGEKARTE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_UVV_DATUM_CONTAINER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
