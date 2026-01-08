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


public class RECLIST_VPOS_TPA_FUHRE_ORT extends HashMap<String,RECORD_VPOS_TPA_FUHRE_ORT>  implements Iterable<RECORD_VPOS_TPA_FUHRE_ORT>, IF_RecordList<RECORD_VPOS_TPA_FUHRE_ORT>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_VPOS_TPA_FUHRE_ORT";
	private String        	cTableName = "JT_VPOS_TPA_FUHRE_ORT";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(SELECT select) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE_ORT(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE_ORT() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_VPOS_TPA_FUHRE_ORT muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(String QueryString) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE_ORT(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT";
		
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
	public RECLIST_VPOS_TPA_FUHRE_ORT(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT";
		
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
	 * @param QueryString !!ID_VPOS_TPA_FUHRE_ORT muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE_ORT(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_VPOS_TPA_FUHRE_ORT muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_VPOS_TPA_FUHRE_ORT(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_VPOS_TPA_FUHRE_ORT muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_VPOS_TPA_FUHRE_ORT(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_VPOS_TPA_FUHRE_ORT  	oRec = 		new RECORD_VPOS_TPA_FUHRE_ORT(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_VPOS_TPA_FUHRE_ORT: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_VPOS_TPA_FUHRE_ORT get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_VPOS_TPA_FUHRE_ORT!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_VPOS_TPA_FUHRE_ORT get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_VPOS_TPA_FUHRE_ORT oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_VPOS_TPA_FUHRE_ORT oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_VPOS_TPA_FUHRE_ORT oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_VPOS_TPA_FUHRE_ORT oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_VPOS_TPA_FUHRE_ORT: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_VPOS_TPA_FUHRE_ORT oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_VPOS_TPA_FUHRE_ORT get_SUBLIST(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator)  throws myException
	{
		RECLIST_VPOS_TPA_FUHRE_ORT oRecListRueck = new RECLIST_VPOS_TPA_FUHRE_ORT(this.oConn);
		
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
	public Vector<RECORD_VPOS_TPA_FUHRE_ORT>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_VPOS_TPA_FUHRE_ORT> vRueck = new Vector<RECORD_VPOS_TPA_FUHRE_ORT>();
		
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
     public Vector<RECORD_VPOS_TPA_FUHRE_ORT>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_VPOS_TPA_FUHRE_ORT>
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

		
		public int compare(RECORD_VPOS_TPA_FUHRE_ORT o1, RECORD_VPOS_TPA_FUHRE_ORT o2) 
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
    public Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iterator() {
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
	
	
	
	
	
	
	


	public HashMap<String, String>  get_ABLADEMENGE_RECHNUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEMENGE_RECHNUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABLADEMENGE_RECHNUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABLADEMENGE_RECHNUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ABZUG_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ABZUG_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ABZUG_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ABZUG_MENGE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ABZUG_MENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANR1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANR1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANR2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANR2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANR2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ANTEIL_ABLADEMENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_ABLADEMENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_ABLADEMENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_ABLADEMENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_ABLADEMENGE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_ABLADEMENGE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_ABLADEMENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_ABLADEMENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANTEIL_LADEMENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_LADEMENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_LADEMENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_LADEMENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_LADEMENGE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_LADEMENGE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_LADEMENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_LADEMENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ANTEIL_PLANMENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_PLANMENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ANTEIL_PLANMENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ANTEIL_PLANMENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_ANTEIL_PLANMENGE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ANTEIL_PLANMENGE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ANTEIL_PLANMENGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ANTEIL_PLANMENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ARTBEZ1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ARTBEZ2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ARTBEZ2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ARTBEZ2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_AVV_AUSSTELLUNG_DATUM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AVV_AUSSTELLUNG_DATUM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_AVV_AUSSTELLUNG_DATUM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_AVV_AUSSTELLUNG_DATUM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BASEL_CODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_CODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BASEL_CODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_CODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BASEL_NOTIZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_NOTIZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BASEL_NOTIZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BASEL_NOTIZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BEMERKUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BEMERKUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BESTELLNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESTELLNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BESTELLNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BESTELLNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_BUCHUNGSNUMMER_ZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_ZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_BUCHUNGSNUMMER_ZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_BUCHUNGSNUMMER_ZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DATUM_LADE_ABLADE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_LADE_ABLADE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DATUM_LADE_ABLADE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DATUM_LADE_ABLADE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEF_QUELLE_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEF_QUELLE_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEF_QUELLE_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEF_QUELLE_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DELETED_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DELETED_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DELETED_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_DATE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_DATE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_GRUND_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_GRUND_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_DEL_KUERZEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_DEL_KUERZEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EINHEIT_MENGEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINHEIT_MENGEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EINHEIT_MENGEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINHEIT_MENGEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EINZELPREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINZELPREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EINZELPREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EINZELPREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EINZELPREIS_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EINZELPREIS_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EINZELPREIS_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EINZELPREIS_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EPREIS_RESULT_NETTO_MGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_RESULT_NETTO_MGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EPREIS_RESULT_NETTO_MGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_RESULT_NETTO_MGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EPREIS_RESULT_NETTO_MGE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EPREIS_RESULT_NETTO_MGE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EPREIS_RESULT_NETTO_MGE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EPREIS_RESULT_NETTO_MGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EUCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EUCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EUNOTIZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUNOTIZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EUNOTIZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EUNOTIZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_EU_BLATT_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_BLATT_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EU_BLATT_MENGE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EU_BLATT_MENGE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EU_BLATT_VOLUMEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_VOLUMEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_BLATT_VOLUMEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_BLATT_VOLUMEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EU_BLATT_VOLUMEN_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EU_BLATT_VOLUMEN_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EU_STEUER_VERMERK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EU_STEUER_VERMERK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EU_STEUER_VERMERK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_FAX_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_FAX_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_FAX_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GELANGENSBESTAETIGUNG_ERHALTEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GELANGENSBESTAETIGUNG_ERHALTEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GELANGENSBESTAETIGUNG_ERHALTEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_HAUSNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAUSNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_HAUSNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_HAUSNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_ADRESSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ADRESSE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ADRESSE_LAGER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ADRESSE_LAGER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ADRESSE_LAGER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ADRESSE_LAGER_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ADRESSE_LAGER_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ADRESSE_LAGER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ADRESSE_LAGER_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ADRESSE_LAGER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ARTIKEL_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_ARTIKEL_BEZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_ARTIKEL_BEZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_ARTIKEL_BEZ_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_ARTIKEL_BEZ_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_ARTIKEL_BEZ_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_ARTIKEL_BEZ_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_ARTIKEL_BEZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_EAK_CODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_EAK_CODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_EAK_CODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_EAK_CODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_EAK_CODE_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_EAK_CODE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_EAK_CODE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_EAK_CODE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_EAK_CODE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_HANDELSDEF_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_HANDELSDEF_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_HANDELSDEF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_HANDELSDEF_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_HANDELSDEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_TAX_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_KON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_KON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_KON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_KON_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_KON_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_KON_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_KON_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_KON_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_STD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_STD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_STD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_STD_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_STD_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_STD_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_STD_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_STD_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_TPA_FUHRE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_ORT_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_TPA_FUHRE_ORT_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_ORT_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_SONDER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_SONDER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_SONDER_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_TPA_FUHRE_SONDER_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_SONDER_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_SONDER_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_WIEGEKARTE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_WIEGEKARTE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_WIEGEKARTE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_WIEGEKARTE_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_ID_WIEGEKARTE_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_WIEGEKARTE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_WIEGEKARTE_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_WIEGEKARTE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_INTRASTAT_MELD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INTRASTAT_MELD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_MELD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_KEIN_KONTRAKT_NOETIG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KEIN_KONTRAKT_NOETIG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_KEIN_KONTRAKT_NOETIG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_KEIN_KONTRAKT_NOETIG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LADEMENGE_GUTSCHRIFT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LADEMENGE_GUTSCHRIFT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LADEMENGE_GUTSCHRIFT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LADEMENGE_GUTSCHRIFT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_TRANSIT3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_TRANSIT3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LIEFERBED_ALTERNATIV_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBED_ALTERNATIV_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LIEFERBED_ALTERNATIV_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LIEFERBED_ALTERNATIV_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_MANUELL_PREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_MANUELL_PREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME1_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME1_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME1_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME2_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME2_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME2_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NAME3_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NAME3_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NAME3_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NATIONALER_ABFALL_CODE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NATIONALER_ABFALL_CODE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NATIONALER_ABFALL_CODE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NATIONALER_ABFALL_CODE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_NOTIFIKATION_NR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_NOTIFIKATION_NR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OEFFNUNGSZEITEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OEFFNUNGSZEITEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OHNE_ABRECHNUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_ABRECHNUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OHNE_ABRECHNUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_ABRECHNUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OHNE_AVV_VERTRAG_CHECK_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_AVV_VERTRAG_CHECK_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OHNE_AVV_VERTRAG_CHECK_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OHNE_AVV_VERTRAG_CHECK_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ORTZUSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORTZUSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ORTZUSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ORTZUSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PLZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PLZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PLZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_POSTENNUMMER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_POSTENNUMMER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_POSTENNUMMER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRINT_EU_AMTSBLATT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRINT_EU_AMTSBLATT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRINT_EU_AMTSBLATT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRINT_EU_AMTSBLATT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_MENGE_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_MENGE_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_PRUEFUNG_PREIS_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_PRUEFUNG_PREIS_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERSATZ_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STRASSE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRASSE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STRASSE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STRASSE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_VPOS_TPA_FUHRE_ORT.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_TEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_TRANSIT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_TRANSIT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_TRANSIT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_WIEGEKARTENKENNER_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_WIEGEKARTENKENNER_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_WIEGEKARTENKENNER_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_WIEGEKARTENKENNER_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZEITSTEMPEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITSTEMPEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZEITSTEMPEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZEITSTEMPEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ZOLLTARIFNR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZOLLTARIFNR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ZOLLTARIFNR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: this.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ZOLLTARIFNR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
}
