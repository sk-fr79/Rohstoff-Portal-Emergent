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


public class RECLIST_VPOS_TPA_FUHRE extends HashMap<String,RECORD_VPOS_TPA_FUHRE>  implements Iterable<RECORD_VPOS_TPA_FUHRE>, IF_RecordList<RECORD_VPOS_TPA_FUHRE>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_VPOS_TPA_FUHRE";
	private String        	cTableName = "JT_VPOS_TPA_FUHRE";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(SELECT select) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_VPOS_TPA_FUHRE muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(String QueryString) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE";
		
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
	public RECLIST_VPOS_TPA_FUHRE(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE";
		
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
	 * @param QueryString !!ID_VPOS_TPA_FUHRE muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_VPOS_TPA_FUHRE muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_VPOS_TPA_FUHRE(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_VPOS_TPA_FUHRE muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_VPOS_TPA_FUHRE  	oRec = 		new RECORD_VPOS_TPA_FUHRE(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_VPOS_TPA_FUHRE: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_VPOS_TPA_FUHRE get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_VPOS_TPA_FUHRE!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_VPOS_TPA_FUHRE get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_VPOS_TPA_FUHRE oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_VPOS_TPA_FUHRE oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_VPOS_TPA_FUHRE.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_VPOS_TPA_FUHRE oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_VPOS_TPA_FUHRE oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_VPOS_TPA_FUHRE: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_VPOS_TPA_FUHRE oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_VPOS_TPA_FUHRE get_SUBLIST(RECLIST_VPOS_TPA_FUHRE.Validation oValidator)  throws myException
	{
		RECLIST_VPOS_TPA_FUHRE oRecListRueck = new RECLIST_VPOS_TPA_FUHRE(this.oConn);
		
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
	public Vector<RECORD_VPOS_TPA_FUHRE>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_VPOS_TPA_FUHRE> vRueck = new Vector<RECORD_VPOS_TPA_FUHRE>();
		
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
     public Vector<RECORD_VPOS_TPA_FUHRE>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_VPOS_TPA_FUHRE>
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

		
		public int compare(RECORD_VPOS_TPA_FUHRE o1, RECORD_VPOS_TPA_FUHRE o2) 
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
    public Iterator<RECORD_VPOS_TPA_FUHRE> iterator() {
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABGESCHLOSSEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABGESCHLOSSEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABLADEMENGE_RECHNUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEMENGE_RECHNUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABLADEMENGE_RECHNUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEMENGE_RECHNUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABLADEN_BRUTTO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEN_BRUTTO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABLADEN_BRUTTO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEN_BRUTTO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ABLADEN_BRUTTO_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ABLADEN_BRUTTO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ABLADEN_BRUTTO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ABLADEN_BRUTTO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ABLADEN_TARA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEN_TARA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABLADEN_TARA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEN_TARA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ABLADEN_TARA_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ABLADEN_TARA_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ABLADEN_TARA_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ABLADEN_TARA_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ABZUG_ABLADEMENGE_ABN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_ABLADEMENGE_ABN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABZUG_ABLADEMENGE_ABN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_ABLADEMENGE_ABN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ABZUG_ABLADEMENGE_ABN_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ABZUG_ABLADEMENGE_ABN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ABZUG_ABLADEMENGE_ABN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ABZUG_ABLADEMENGE_ABN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ABZUG_LADEMENGE_LIEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_LADEMENGE_LIEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABZUG_LADEMENGE_LIEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_LADEMENGE_LIEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ABZUG_LADEMENGE_LIEF_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ABZUG_LADEMENGE_LIEF_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ABZUG_LADEMENGE_LIEF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ABZUG_LADEMENGE_LIEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ALTE_LIEFERSCHEIN_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALTE_LIEFERSCHEIN_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ALTE_LIEFERSCHEIN_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALTE_LIEFERSCHEIN_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ALT_WIRD_NICHT_GEBUCHT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALT_WIRD_NICHT_GEBUCHT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ALT_WIRD_NICHT_GEBUCHT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ALT_WIRD_NICHT_GEBUCHT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANHAENGERKENNZEICHEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANHAENGERKENNZEICHEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANHAENGERKENNZEICHEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANHAENGERKENNZEICHEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANR1_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANR1_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANR1_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANR1_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANR2_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR2_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANR2_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR2_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANR2_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR2_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANR2_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR2_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANRUFDATUM_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANRUFDATUM_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANRUFDATUM_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANRUFDATUM_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANRUFER_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANRUFER_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANRUFER_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANRUFER_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANTEIL_ABLADEMENGE_ABN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_ABLADEMENGE_ABN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_ABLADEMENGE_ABN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_ABLADEMENGE_ABN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_ABLADEMENGE_ABN_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_ABLADEMENGE_ABN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_ABLADEMENGE_ABN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_ABLADEMENGE_ABN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANTEIL_ABLADEMENGE_LIEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_ABLADEMENGE_LIEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_ABLADEMENGE_LIEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_ABLADEMENGE_LIEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_ABLADEMENGE_LIEF_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_ABLADEMENGE_LIEF_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_ABLADEMENGE_LIEF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_ABLADEMENGE_LIEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANTEIL_LADEMENGE_ABN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_LADEMENGE_ABN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_LADEMENGE_ABN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_LADEMENGE_ABN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_LADEMENGE_ABN_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_LADEMENGE_ABN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_LADEMENGE_ABN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_LADEMENGE_ABN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANTEIL_LADEMENGE_LIEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_LADEMENGE_LIEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_LADEMENGE_LIEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_LADEMENGE_LIEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_LADEMENGE_LIEF_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_LADEMENGE_LIEF_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_LADEMENGE_LIEF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_LADEMENGE_LIEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANTEIL_PLANMENGE_ABN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_PLANMENGE_ABN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_PLANMENGE_ABN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_PLANMENGE_ABN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_PLANMENGE_ABN_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_PLANMENGE_ABN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_PLANMENGE_ABN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_PLANMENGE_ABN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANTEIL_PLANMENGE_LIEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_PLANMENGE_LIEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_PLANMENGE_LIEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_PLANMENGE_LIEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_PLANMENGE_LIEF_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_PLANMENGE_LIEF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_PLANMENGE_LIEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANZAHL_CONTAINER_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZAHL_CONTAINER_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANZAHL_CONTAINER_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANZAHL_CONTAINER_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ANZAHL_CONTAINER_FP_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ANZAHL_CONTAINER_FP_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ANZAHL_CONTAINER_FP_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ANZAHL_CONTAINER_FP_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ANZAHL_CONTAINER_FP_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ANZAHL_CONTAINER_FP_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ANZAHL_CONTAINER_FP_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANZAHL_CONTAINER_FP_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANZAHL_CONTAINER_FP_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANZAHL_CONTAINER_FP_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ARTBEZ1_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ1_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ARTBEZ1_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ1_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ARTBEZ2_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ2_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ARTBEZ2_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ2_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_AUFLADEN_BRUTTO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUFLADEN_BRUTTO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AUFLADEN_BRUTTO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUFLADEN_BRUTTO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_AUFLADEN_BRUTTO_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_AUFLADEN_BRUTTO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_AUFLADEN_BRUTTO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_AUFLADEN_BRUTTO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_AUFLADEN_TARA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUFLADEN_TARA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AUFLADEN_TARA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AUFLADEN_TARA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_AUFLADEN_TARA_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_AUFLADEN_TARA_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_AUFLADEN_TARA_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_AUFLADEN_TARA_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_AVV_AUSSTELLUNG_DATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AVV_AUSSTELLUNG_DATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AVV_AUSSTELLUNG_DATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AVV_AUSSTELLUNG_DATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_HAUSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_HAUSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_HAUSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_HAUSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_LAENDERCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_LAENDERCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_LAENDERCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_LAENDERCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_NAME1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_NAME1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_NAME1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_NAME1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_NAME2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_NAME2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_NAME2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_NAME2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_NAME3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_NAME3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_NAME3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_NAME3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_ORTZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_ORTZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_ORTZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_ORTZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_PLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_PLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_PLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_PLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_A_STRASSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_STRASSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_A_STRASSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_A_STRASSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BASEL_CODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_CODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BASEL_CODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_CODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BASEL_NOTIZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_NOTIZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BASEL_NOTIZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_NOTIZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_FUER_KUNDE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_FUER_KUNDE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_FUER_KUNDE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_FUER_KUNDE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_SACHBEARBEITER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_SACHBEARBEITER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_SACHBEARBEITER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_SACHBEARBEITER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_START_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_START_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_START_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_START_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_ZIEL_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_ZIEL_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_ZIEL_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_ZIEL_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BESTELLNUMMER_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESTELLNUMMER_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BESTELLNUMMER_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESTELLNUMMER_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BESTELLNUMMER_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESTELLNUMMER_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BESTELLNUMMER_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESTELLNUMMER_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSNR_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNR_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSNR_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNR_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_ABHOLUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ABHOLUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_ABHOLUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ABHOLUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_ABHOLUNG_ENDE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ABHOLUNG_ENDE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_ABHOLUNG_ENDE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ABHOLUNG_ENDE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_ABLADEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ABLADEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_ABLADEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ABLADEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_ANLIEFERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ANLIEFERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_ANLIEFERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ANLIEFERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_ANLIEFERUNG_ENDE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ANLIEFERUNG_ENDE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_ANLIEFERUNG_ENDE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_ANLIEFERUNG_ENDE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_AUFLADEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_AUFLADEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_AUFLADEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_AUFLADEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DAT_FAHRPLAN_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DAT_FAHRPLAN_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DAT_FAHRPLAN_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DAT_FAHRPLAN_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DAT_VORGEMERKT_ENDE_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DAT_VORGEMERKT_ENDE_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DAT_VORGEMERKT_ENDE_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DAT_VORGEMERKT_ENDE_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DAT_VORGEMERKT_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DAT_VORGEMERKT_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DAT_VORGEMERKT_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DAT_VORGEMERKT_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DELETED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DELETED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EAN_CODE_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EAN_CODE_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EAN_CODE_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EAN_CODE_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EINHEIT_MENGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINHEIT_MENGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EINHEIT_MENGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINHEIT_MENGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EINZELPREIS_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINZELPREIS_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EINZELPREIS_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINZELPREIS_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EINZELPREIS_EK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EINZELPREIS_EK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EINZELPREIS_EK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EINZELPREIS_EK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EINZELPREIS_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINZELPREIS_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EINZELPREIS_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINZELPREIS_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EINZELPREIS_VK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EINZELPREIS_VK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EINZELPREIS_VK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EINZELPREIS_VK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EK_KONTRAKTNR_ZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_KONTRAKTNR_ZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EK_KONTRAKTNR_ZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_KONTRAKTNR_ZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EK_VK_SORTE_LOCK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_SORTE_LOCK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EK_VK_SORTE_LOCK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_SORTE_LOCK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EK_VK_ZUORD_ZWANG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_ZUORD_ZWANG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EK_VK_ZUORD_ZWANG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EK_VK_ZUORD_ZWANG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EPREIS_RESULT_NETTO_MGE_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_RESULT_NETTO_MGE_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EPREIS_RESULT_NETTO_MGE_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_RESULT_NETTO_MGE_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EPREIS_RESULT_NETTO_MGE_EK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EPREIS_RESULT_NETTO_MGE_EK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EPREIS_RESULT_NETTO_MGE_EK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EPREIS_RESULT_NETTO_MGE_EK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EPREIS_RESULT_NETTO_MGE_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_RESULT_NETTO_MGE_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EPREIS_RESULT_NETTO_MGE_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_RESULT_NETTO_MGE_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EPREIS_RESULT_NETTO_MGE_VK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EPREIS_RESULT_NETTO_MGE_VK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EPREIS_RESULT_NETTO_MGE_VK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EPREIS_RESULT_NETTO_MGE_VK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ERFASSER_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERFASSER_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERFASSER_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERFASSER_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EUCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EUCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EUNOTIZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUNOTIZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EUNOTIZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUNOTIZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EU_BLATT_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_BLATT_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EU_BLATT_MENGE_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EU_BLATT_MENGE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EU_BLATT_MENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EU_BLATT_MENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EU_BLATT_VOLUMEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_VOLUMEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_BLATT_VOLUMEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_VOLUMEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EU_BLATT_VOLUMEN_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EU_BLATT_VOLUMEN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EU_BLATT_VOLUMEN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EU_BLATT_VOLUMEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EU_STEUER_VERMERK_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAHRER_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRER_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAHRER_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRER_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAHRPLANGRUPPE_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRPLANGRUPPE_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAHRPLANGRUPPE_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRPLANGRUPPE_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_FAHRPLANGRUPPE_FP_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FAHRPLANGRUPPE_FP_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_FAHRPLANGRUPPE_FP_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FAHRPLANGRUPPE_FP_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_FAHRPLANGRUPPE_FP_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_FAHRPLANGRUPPE_FP_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_FAHRPLANGRUPPE_FP_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_FAHRPLANGRUPPE_FP_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_FAHRPLANGRUPPE_FP_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_FAHRPLANGRUPPE_FP_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_FAHRT_ANFANG_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRT_ANFANG_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAHRT_ANFANG_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRT_ANFANG_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAHRT_ENDE_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRT_ENDE_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAHRT_ENDE_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAHRT_ENDE_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAX_ABNEHMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_ABNEHMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAX_ABNEHMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_ABNEHMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAX_LIEFERANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_LIEFERANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAX_LIEFERANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_LIEFERANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FUHRENGRUPPE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRENGRUPPE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FUHRENGRUPPE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRENGRUPPE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_FUHRENGRUPPE_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FUHRENGRUPPE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_FUHRENGRUPPE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_FUHRENGRUPPE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_FUHRENGRUPPE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_FUHRENGRUPPE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_FUHRENGRUPPE_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_FUHRENGRUPPE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_FUHRENGRUPPE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_FUHRENGRUPPE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_FUHRE_AUS_FAHRPLAN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRE_AUS_FAHRPLAN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FUHRE_AUS_FAHRPLAN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRE_AUS_FAHRPLAN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FUHRE_IST_UMGELEITET_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRE_IST_UMGELEITET_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FUHRE_IST_UMGELEITET_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRE_IST_UMGELEITET_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FUHRE_KOMPLETT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRE_KOMPLETT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FUHRE_KOMPLETT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FUHRE_KOMPLETT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GELANGENSBESTAETIGUNG_ERHALTEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GELANGENSBESTAETIGUNG_ERHALTEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GELANGENSBESTAETIGUNG_ERHALTEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_ADRESSE_FREMDAUFTRAG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_FREMDAUFTRAG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_FREMDAUFTRAG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_FREMDAUFTRAG_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_FREMDAUFTRAG_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_FREMDAUFTRAG_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_FREMDAUFTRAG_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_FREMDAUFTRAG_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_FREMDAUFTRAG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_FREMDAUFTRAG_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_FREMDAUFTRAG_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_FREMDAUFTRAG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_FREMDAUFTRAG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_LAGER_START_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_START_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_LAGER_START_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_START_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_LAGER_START_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LAGER_START_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_LAGER_START_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LAGER_START_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_LAGER_START_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_LAGER_START_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_LAGER_START_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_LAGER_START_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_LAGER_START_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_LAGER_START_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_LAGER_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_LAGER_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_LAGER_ZIEL_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LAGER_ZIEL_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_LAGER_ZIEL_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_LAGER_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_LAGER_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_LAGER_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_LAGER_ZIEL_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_LAGER_ZIEL_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_LAGER_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_LAGER_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_SPEDITION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_SPEDITION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_SPEDITION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_SPEDITION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_SPEDITION_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ADRESSE_SPEDITION_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_SPEDITION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_SPEDITION_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_SPEDITION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_START_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_START_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_START_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_START_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_START_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_START_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_START_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_START_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_START_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_START_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_START_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_START_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_START_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_START_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_ZIEL_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_ZIEL_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ADRESSE_ZIEL_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ADRESSE_ZIEL_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ADRESSE_ZIEL_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_ZIEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_ZIEL_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ADRESSE_ZIEL_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ADRESSE_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ARTIKEL_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_BEZ_EK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_EK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ARTIKEL_BEZ_EK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_EK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ARTIKEL_BEZ_EK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_BEZ_EK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_BEZ_EK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ARTIKEL_BEZ_EK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ARTIKEL_BEZ_EK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_BEZ_EK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_BEZ_VK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_VK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_ARTIKEL_BEZ_VK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_ARTIKEL_BEZ_VK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_ARTIKEL_BEZ_VK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_BEZ_VK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_BEZ_VK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_ARTIKEL_BEZ_VK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_ARTIKEL_BEZ_VK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_BEZ_VK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_CONTAINERTYP_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_CONTAINERTYP_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_CONTAINERTYP_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_CONTAINERTYP_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_CONTAINERTYP_FP_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_CONTAINERTYP_FP_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_CONTAINERTYP_FP_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_CONTAINERTYP_FP_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_CONTAINERTYP_FP_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_CONTAINERTYP_FP_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_CONTAINERTYP_FP_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_CONTAINERTYP_FP_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_CONTAINERTYP_FP_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_CONTAINERTYP_FP_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_EAK_CODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_EAK_CODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_EAK_CODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_EAK_CODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_EAK_CODE_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_EAK_CODE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_EAK_CODE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_EAK_CODE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_EAK_CODE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_EAK_CODE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_EAK_CODE_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_EAK_CODE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_EAK_CODE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_EAK_CODE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_FAHRPLAN_ZEITANGABE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_FAHRPLAN_ZEITANGABE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_FAHRPLAN_ZEITANGABE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_FAHRPLAN_ZEITANGABE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_FAHRPLAN_ZEITANGABE_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_FAHRPLAN_ZEITANGABE_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_FAHRPLAN_ZEITANGABE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_FAHRPLAN_ZEITANGABE_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_FAHRPLAN_ZEITANGABE_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_FAHRPLAN_ZEITANGABE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_FAHRPLAN_ZEITANGABE_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_FAHRPLAN_ZEITANGABE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_FAHRPLAN_ZEITANGABE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_FAHRPLAN_ZEITANGABE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_HANDELSDEF_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_HANDELSDEF_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_HANDELSDEF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_HANDELSDEF_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_HANDELSDEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MASCHINEN_ANH_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MASCHINEN_ANH_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MASCHINEN_ANH_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MASCHINEN_ANH_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MASCHINEN_ANH_FP_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_MASCHINEN_ANH_FP_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_MASCHINEN_ANH_FP_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_MASCHINEN_ANH_FP_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_MASCHINEN_ANH_FP_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MASCHINEN_ANH_FP_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MASCHINEN_ANH_FP_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_MASCHINEN_ANH_FP_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_MASCHINEN_ANH_FP_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MASCHINEN_ANH_FP_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MASCHINEN_LKW_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MASCHINEN_LKW_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MASCHINEN_LKW_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MASCHINEN_LKW_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MASCHINEN_LKW_FP_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_MASCHINEN_LKW_FP_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_MASCHINEN_LKW_FP_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_MASCHINEN_LKW_FP_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_MASCHINEN_LKW_FP_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MASCHINEN_LKW_FP_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MASCHINEN_LKW_FP_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_MASCHINEN_LKW_FP_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_MASCHINEN_LKW_FP_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MASCHINEN_LKW_FP_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_EK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_EK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_TAX_EK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_EK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_EK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_EK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_EK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_EK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_EK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_EK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_VK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_VK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_TAX_VK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_VK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_VK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_VK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_VK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_VK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_VK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_VK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_UMA_KONTRAKT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_UMA_KONTRAKT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_UMA_KONTRAKT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_UMA_KONTRAKT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_UMA_KONTRAKT_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_UMA_KONTRAKT_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_UMA_KONTRAKT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_UMA_KONTRAKT_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_UMA_KONTRAKT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VERARBEITUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERARBEITUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VERARBEITUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERARBEITUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VERARBEITUNG_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VERARBEITUNG_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VERARBEITUNG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VERARBEITUNG_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VERARBEITUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VERPACKUNGSART_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERPACKUNGSART_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VERPACKUNGSART_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VERPACKUNGSART_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VERPACKUNGSART_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VERPACKUNGSART_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VERPACKUNGSART_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VERPACKUNGSART_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VERPACKUNGSART_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_KON_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_KON_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_KON_EK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_KON_EK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VPOS_KON_EK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_KON_EK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_KON_EK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_KON_EK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_KON_EK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_KON_EK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_KON_EK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_KON_EK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_KON_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_KON_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_KON_VK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_KON_VK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VPOS_KON_VK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_KON_VK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_KON_VK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_KON_VK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_KON_VK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_KON_VK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_KON_VK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_KON_VK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_STD_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_STD_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_STD_EK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_STD_EK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VPOS_STD_EK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_STD_EK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_STD_EK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_STD_EK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_STD_EK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_STD_EK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_STD_EK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_STD_EK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_STD_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_STD_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_STD_VK_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_STD_VK_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VPOS_STD_VK_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_STD_VK_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_STD_VK_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_STD_VK_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_STD_VK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_STD_VK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_STD_VK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_STD_VK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VPOS_TPA_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_TPA_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_TPA_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_TPA_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_TPA_FUHRE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_SONDER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_SONDER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_SONDER_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_FUHRE_SONDER_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_VPOS_TPA_FUHRE_SONDER_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_VPOS_TPA_FUHRE_SONDER_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_VPOS_TPA_FUHRE_SONDER_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_SONDER_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_VPOS_TPA_FUHRE_SONDER_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_VPOS_TPA_FUHRE_SONDER_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WIEGEKARTE_ABN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_ABN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WIEGEKARTE_ABN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_ABN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WIEGEKARTE_ABN_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_ABN_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_WIEGEKARTE_ABN_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_ABN_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WIEGEKARTE_ABN_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WIEGEKARTE_ABN_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WIEGEKARTE_ABN_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WIEGEKARTE_ABN_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_WIEGEKARTE_ABN_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WIEGEKARTE_ABN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WIEGEKARTE_LIEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_LIEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WIEGEKARTE_LIEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_LIEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WIEGEKARTE_LIEF_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_LIEF_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_WIEGEKARTE_LIEF_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_WIEGEKARTE_LIEF_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_WIEGEKARTE_LIEF_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WIEGEKARTE_LIEF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WIEGEKARTE_LIEF_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_WIEGEKARTE_LIEF_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_WIEGEKARTE_LIEF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WIEGEKARTE_LIEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_INTRASTAT_MELD_IN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_IN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INTRASTAT_MELD_IN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_IN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_INTRASTAT_MELD_OUT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_OUT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INTRASTAT_MELD_OUT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_OUT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_IST_GEPLANT_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_GEPLANT_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_GEPLANT_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_GEPLANT_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_IST_STORNIERT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_STORNIERT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_IST_STORNIERT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_IST_STORNIERT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KEIN_KONTRAKT_NOETIG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KEIN_KONTRAKT_NOETIG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KEIN_KONTRAKT_NOETIG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KEIN_KONTRAKT_NOETIG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KENNER_ALTE_SAETZE_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KENNER_ALTE_SAETZE_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KENNER_ALTE_SAETZE_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KENNER_ALTE_SAETZE_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LADEMENGE_GUTSCHRIFT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LADEMENGE_GUTSCHRIFT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LADEMENGE_GUTSCHRIFT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LADEMENGE_GUTSCHRIFT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERBED_ALTERNATIV_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBED_ALTERNATIV_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERBED_ALTERNATIV_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBED_ALTERNATIV_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERBED_ALTERNATIV_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBED_ALTERNATIV_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERBED_ALTERNATIV_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBED_ALTERNATIV_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERINFO_TPA_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERINFO_TPA_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERINFO_TPA_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERINFO_TPA_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_HAUSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_HAUSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_HAUSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_HAUSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_LAENDERCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_LAENDERCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_LAENDERCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_LAENDERCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_NAME1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_NAME1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_NAME2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_NAME2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_NAME3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_NAME3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_NAME3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_ORTZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORTZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_ORTZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_ORTZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_PLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_PLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_PLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_PLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_L_STRASSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_STRASSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_L_STRASSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_L_STRASSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MENGE_ABLADEN_KO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_ABLADEN_KO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MENGE_ABLADEN_KO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_ABLADEN_KO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_MENGE_ABLADEN_KO_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MENGE_ABLADEN_KO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_MENGE_ABLADEN_KO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MENGE_ABLADEN_KO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_MENGE_AUFLADEN_KO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_AUFLADEN_KO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MENGE_AUFLADEN_KO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_AUFLADEN_KO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_MENGE_AUFLADEN_KO_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MENGE_AUFLADEN_KO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_MENGE_AUFLADEN_KO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MENGE_AUFLADEN_KO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_MENGE_VORGABE_KO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_VORGABE_KO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MENGE_VORGABE_KO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MENGE_VORGABE_KO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_MENGE_VORGABE_KO_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_MENGE_VORGABE_KO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_MENGE_VORGABE_KO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_MENGE_VORGABE_KO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_NATIONALER_ABFALL_CODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NATIONALER_ABFALL_CODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NATIONALER_ABFALL_CODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NATIONALER_ABFALL_CODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_ABN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_ABN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_ABN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_ABN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_LIEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_LIEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_LIEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_LIEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OHNE_ABRECHNUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_ABRECHNUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OHNE_ABRECHNUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_ABRECHNUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OHNE_AVV_VERTRAG_CHECK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_AVV_VERTRAG_CHECK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OHNE_AVV_VERTRAG_CHECK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_AVV_VERTRAG_CHECK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ORDNUNGSNUMMER_CMR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORDNUNGSNUMMER_CMR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORDNUNGSNUMMER_CMR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORDNUNGSNUMMER_CMR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_POSTENNUMMER_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_POSTENNUMMER_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_POSTENNUMMER_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_POSTENNUMMER_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRINT_EU_AMTSBLATT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRINT_EU_AMTSBLATT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRINT_EU_AMTSBLATT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRINT_EU_AMTSBLATT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_ABLADEMENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_ABLADEMENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_ABLADEMENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_ABLADEMENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_ABLADEMENGE_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_ABLADEMENGE_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_ABLADEMENGE_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_ABLADEMENGE_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_ABLADEMENGE_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_ABLADEMENGE_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_ABLADEMENGE_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_ABLADEMENGE_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_EK_PREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_EK_PREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_EK_PREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_EK_PREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_EK_PREIS_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_EK_PREIS_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_EK_PREIS_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_EK_PREIS_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_EK_PREIS_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_EK_PREIS_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_EK_PREIS_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_EK_PREIS_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_LADEMENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_LADEMENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_LADEMENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_LADEMENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_LADEMENGE_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_LADEMENGE_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_LADEMENGE_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_LADEMENGE_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_LADEMENGE_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_LADEMENGE_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_LADEMENGE_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_LADEMENGE_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_VK_PREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_VK_PREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_VK_PREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_VK_PREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_VK_PREIS_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_VK_PREIS_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_VK_PREIS_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_VK_PREIS_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_VK_PREIS_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_VK_PREIS_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_VK_PREIS_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_VK_PREIS_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ROUTING_DISTANCE_KM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ROUTING_DISTANCE_KM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ROUTING_DISTANCE_KM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ROUTING_DISTANCE_KM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ROUTING_DISTANCE_KM_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ROUTING_DISTANCE_KM_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ROUTING_DISTANCE_KM_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ROUTING_DISTANCE_KM_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ROUTING_TIME_MINUTES_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ROUTING_TIME_MINUTES_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ROUTING_TIME_MINUTES_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ROUTING_TIME_MINUTES_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ROUTING_TIME_MINUTES_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ROUTING_TIME_MINUTES_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ROUTING_TIME_MINUTES_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ROUTING_TIME_MINUTES_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ROUTING_TIME_MINUTES_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ROUTING_TIME_MINUTES_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ROUTING_TIME_MINUTES_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ROUTING_TIME_MINUTES_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ROUTING_TIME_MINUTES_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ROUTING_TIME_MINUTES_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SCHLIESSE_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHLIESSE_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHLIESSE_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHLIESSE_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SCHLIESSE_FUHRE_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHLIESSE_FUHRE_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHLIESSE_FUHRE_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHLIESSE_FUHRE_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SCHLIESSE_FUHRE_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHLIESSE_FUHRE_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SCHLIESSE_FUHRE_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SCHLIESSE_FUHRE_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SORTIERUNG_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTIERUNG_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SORTIERUNG_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SORTIERUNG_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SORTIERUNG_FP_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTIERUNG_FP_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_SORTIERUNG_FP_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_SORTIERUNG_FP_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_SORTIERUNG_FP_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SORTIERUNG_FP_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SORTIERUNG_FP_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_SORTIERUNG_FP_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_SORTIERUNG_FP_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SORTIERUNG_FP_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_SPEICHERN_TROTZ_INKONSISTENZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SPEICHERN_TROTZ_INKONSISTENZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SPEICHERN_TROTZ_INKONSISTENZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SPEICHERN_TROTZ_INKONSISTENZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STATUS_BUCHUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STATUS_BUCHUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STATUS_BUCHUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STATUS_BUCHUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_STATUS_BUCHUNG_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_STATUS_BUCHUNG_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_STATUS_BUCHUNG_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_STATUS_BUCHUNG_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_STATUS_BUCHUNG_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_STATUS_BUCHUNG_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_STATUS_BUCHUNG_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STATUS_BUCHUNG_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_STATUS_BUCHUNG_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STATUS_BUCHUNG_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STEUERSATZ_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_EK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STEUERSATZ_EK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_STEUERSATZ_EK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_EK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STEUERSATZ_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_VK_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STEUERSATZ_VK_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_STEUERSATZ_VK_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_VK_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STORNO_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNO_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STORNO_KUERZEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_KUERZEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STORNO_KUERZEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STORNO_KUERZEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TAETIGKEIT_FP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TAETIGKEIT_FP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TAETIGKEIT_FP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TAETIGKEIT_FP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TEL_ABNEHMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_ABNEHMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEL_ABNEHMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_ABNEHMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TEL_LIEFERANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_LIEFERANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEL_LIEFERANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_LIEFERANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TP_VERANTWORTUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TP_VERANTWORTUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TP_VERANTWORTUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TP_VERANTWORTUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSIT_EK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_EK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSIT_EK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_EK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSIT_VK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_VK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSIT_VK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_VK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSPORTKENNZEICHEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTKENNZEICHEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSPORTKENNZEICHEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTKENNZEICHEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSPORTMITTEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTMITTEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSPORTMITTEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSPORTMITTEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TYP_STRECKE_LAGER_MIXED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_STRECKE_LAGER_MIXED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TYP_STRECKE_LAGER_MIXED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TYP_STRECKE_LAGER_MIXED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_TYP_STRECKE_LAGER_MIXED_l_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_TYP_STRECKE_LAGER_MIXED_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_TYP_STRECKE_LAGER_MIXED_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_TYP_STRECKE_LAGER_MIXED_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_TYP_STRECKE_LAGER_MIXED_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_TYP_STRECKE_LAGER_MIXED_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_TYP_STRECKE_LAGER_MIXED_d_Summe(RECLIST_VPOS_TPA_FUHRE.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_TYP_STRECKE_LAGER_MIXED_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_TYP_STRECKE_LAGER_MIXED_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_TYP_STRECKE_LAGER_MIXED_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_VK_KONTRAKTNR_ZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VK_KONTRAKTNR_ZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_VK_KONTRAKTNR_ZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_VK_KONTRAKTNR_ZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_WIEGEKARTENKENNER_ABLADEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_WIEGEKARTENKENNER_ABLADEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_WIEGEKARTENKENNER_ABLADEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_WIEGEKARTENKENNER_ABLADEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_WIEGEKARTENKENNER_LADEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_WIEGEKARTENKENNER_LADEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_WIEGEKARTENKENNER_LADEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_WIEGEKARTENKENNER_LADEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEITANGABE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITANGABE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEITANGABE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITANGABE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEITSTEMPEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITSTEMPEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEITSTEMPEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITSTEMPEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZOLLTARIFNR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZOLLTARIFNR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZOLLTARIFNR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZOLLTARIFNR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
