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


public class RECLIST_TESTTABELLE_HANDELDEF extends HashMap<String,RECORD_TESTTABELLE_HANDELDEF>  implements Iterable<RECORD_TESTTABELLE_HANDELDEF>, IF_RecordList<RECORD_TESTTABELLE_HANDELDEF>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "ID_TESTTABELLE_HANDELDEF";
	private String        	cTableName = "JT_TESTTABELLE_HANDELDEF";
	private SELECT   		Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(SELECT select) throws myException
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
	public RECLIST_TESTTABELLE_HANDELDEF(SELECT select, MyConnection Conn) throws myException
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
	public RECLIST_TESTTABELLE_HANDELDEF() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_TESTTABELLE_HANDELDEF muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(String QueryString) throws myException
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
	public RECLIST_TESTTABELLE_HANDELDEF(String cWhereblock, String cOrderblock) throws myException
	{
		super();
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF";
		
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
	public RECLIST_TESTTABELLE_HANDELDEF(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF";
		
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
	 * @param QueryString !!ID_TESTTABELLE_HANDELDEF muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(String QueryString, MyConnection Conn) throws myException
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
	public RECLIST_TESTTABELLE_HANDELDEF(Vector<String> vIDs) throws myException
	{
		super();
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF WHERE ID_TESTTABELLE_HANDELDEF IN("+cID_Block+")";
		this.REFRESH();
	}
	
	
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF WHERE ID_TESTTABELLE_HANDELDEF IN("+cID_Block+")";
		this.REFRESH();
	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_TESTTABELLE_HANDELDEF muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(String QueryString,int MaxNumberOfRecords) throws myException
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
    public RECLIST_TESTTABELLE_HANDELDEF(String QueryString,int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}




	/**
	 * 
	 * @param QueryString !!ID_TESTTABELLE_HANDELDEF muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_TESTTABELLE_HANDELDEF(String QueryString, MyConnection Conn,int MaxNumberOfRecords) throws myException
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
	public RECLIST_TESTTABELLE_HANDELDEF(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String cOwnKeyField) throws myException
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
                      	RECORD_TESTTABELLE_HANDELDEF  	oRec = 		new RECORD_TESTTABELLE_HANDELDEF(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_TESTTABELLE_HANDELDEF: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public RECORD_TESTTABELLE_HANDELDEF get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_TESTTABELLE_HANDELDEF!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public RECORD_TESTTABELLE_HANDELDEF get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(RECORD_TESTTABELLE_HANDELDEF oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, RECORD_TESTTABELLE_HANDELDEF oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_TESTTABELLE_HANDELDEF.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_TESTTABELLE_HANDELDEF oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(RECORD_TESTTABELLE_HANDELDEF oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD_TESTTABELLE_HANDELDEF: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_TESTTABELLE_HANDELDEF oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_TESTTABELLE_HANDELDEF get_SUBLIST(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator)  throws myException
	{
		RECLIST_TESTTABELLE_HANDELDEF oRecListRueck = new RECLIST_TESTTABELLE_HANDELDEF(this.oConn);
		
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
	public Vector<RECORD_TESTTABELLE_HANDELDEF>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<RECORD_TESTTABELLE_HANDELDEF> vRueck = new Vector<RECORD_TESTTABELLE_HANDELDEF>();
		
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
     public Vector<RECORD_TESTTABELLE_HANDELDEF>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<RECORD_TESTTABELLE_HANDELDEF>
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

		
		public int compare(RECORD_TESTTABELLE_HANDELDEF o1, RECORD_TESTTABELLE_HANDELDEF o2) 
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
    public Iterator<RECORD_TESTTABELLE_HANDELDEF> iterator() {
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
	
	
	
	
	
	
	


	public HashMap<String, String>  get_EPREIS_QUELLE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_QUELLE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EPREIS_QUELLE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_QUELLE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EPREIS_QUELLE_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EPREIS_QUELLE_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EPREIS_QUELLE_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EPREIS_QUELLE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_EPREIS_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_EPREIS_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_EPREIS_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_EPREIS_ZIEL_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_EPREIS_ZIEL_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_EPREIS_ZIEL_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_EPREIS_ZIEL_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ERZEUGT_AM_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_AM_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_AM_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ERZEUGT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ERZEUGT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_GEAENDERT_VON_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_GEAENDERT_VON_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_HANDELSDEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_HANDELSDEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_HANDELSDEF_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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

	

	public long get_ID_HANDELSDEF_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_HANDELSDEF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_HANDELSDEF_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_HANDELSDEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_MANDANT_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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

	

	public long get_ID_MANDANT_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_MANDANT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_MANDANT_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_MANDANT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_START_HD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_START_HD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_START_HD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_START_HD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_START_HD_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_START_HD_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_TAX_START_HD_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_START_HD_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_START_HD_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_START_HD_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_START_HD_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_START_HD_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_START_HD_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_START_HD_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TAX_ZIEL_HD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_ZIEL_HD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TAX_ZIEL_HD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TAX_ZIEL_HD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TAX_ZIEL_HD_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_ZIEL_HD_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_TAX_ZIEL_HD_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TAX_ZIEL_HD_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TAX_ZIEL_HD_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TAX_ZIEL_HD_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TAX_ZIEL_HD_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TAX_ZIEL_HD_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_TAX_ZIEL_HD_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TAX_ZIEL_HD_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_TESTTABELLE_HANDELDEF_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TESTTABELLE_HANDELDEF_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_TESTTABELLE_HANDELDEF_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_TESTTABELLE_HANDELDEF_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_TESTTABELLE_HANDELDEF_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TESTTABELLE_HANDELDEF_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_ID_TESTTABELLE_HANDELDEF_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_ID_TESTTABELLE_HANDELDEF_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_ID_TESTTABELLE_HANDELDEF_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_TESTTABELLE_HANDELDEF_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_TESTTABELLE_HANDELDEF_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_ID_TESTTABELLE_HANDELDEF_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_ID_TESTTABELLE_HANDELDEF_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_TESTTABELLE_HANDELDEF_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_TPA_FUHRE_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_ORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_ID_VPOS_TPA_FUHRE_ORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_ID_VPOS_TPA_FUHRE_ORT_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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

	

	public long get_ID_VPOS_TPA_FUHRE_ORT_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_ID_VPOS_TPA_FUHRE_ORT_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_ID_VPOS_TPA_FUHRE_ORT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_INTRASTAT_HD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_HD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_INTRASTAT_HD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_INTRASTAT_HD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_START_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_START_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_START_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_START_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LAENDERCODE_ZIEL_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_ZIEL_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LAENDERCODE_ZIEL_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LAENDERCODE_ZIEL_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_LETZTE_AENDERUNG_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_LETZTE_AENDERUNG_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ID_LAND_QUELLE_GEO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_QUELLE_GEO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ID_LAND_QUELLE_GEO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_QUELLE_GEO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_OM_ID_LAND_QUELLE_GEO_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_QUELLE_GEO_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_OM_ID_LAND_QUELLE_GEO_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_QUELLE_GEO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_OM_ID_LAND_QUELLE_GEO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_OM_ID_LAND_QUELLE_GEO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_OM_ID_LAND_QUELLE_GEO_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_OM_ID_LAND_QUELLE_GEO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_OM_ID_LAND_QUELLE_GEO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_OM_ID_LAND_QUELLE_GEO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_OM_ID_LAND_QUELLE_JUR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_QUELLE_JUR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ID_LAND_QUELLE_JUR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_QUELLE_JUR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_OM_ID_LAND_QUELLE_JUR_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_QUELLE_JUR_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_OM_ID_LAND_QUELLE_JUR_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_QUELLE_JUR_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_OM_ID_LAND_QUELLE_JUR_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_OM_ID_LAND_QUELLE_JUR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_OM_ID_LAND_QUELLE_JUR_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_OM_ID_LAND_QUELLE_JUR_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_OM_ID_LAND_QUELLE_JUR_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_OM_ID_LAND_QUELLE_JUR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_OM_ID_LAND_ZIEL_GEO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_ZIEL_GEO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ID_LAND_ZIEL_GEO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_ZIEL_GEO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_OM_ID_LAND_ZIEL_GEO_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_ZIEL_GEO_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_OM_ID_LAND_ZIEL_GEO_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_ZIEL_GEO_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_OM_ID_LAND_ZIEL_GEO_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_OM_ID_LAND_ZIEL_GEO_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_OM_ID_LAND_ZIEL_GEO_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_OM_ID_LAND_ZIEL_GEO_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_OM_ID_LAND_ZIEL_GEO_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_OM_ID_LAND_ZIEL_GEO_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_OM_ID_LAND_ZIEL_JUR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_ZIEL_JUR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ID_LAND_ZIEL_JUR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ID_LAND_ZIEL_JUR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_OM_ID_LAND_ZIEL_JUR_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_ZIEL_JUR_lValue(new Long(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}

	

	public long get_OM_ID_LAND_ZIEL_JUR_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Long 				oD = oRec.get_OM_ID_LAND_ZIEL_JUR_lValue(new Long(0));
			
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




	
	public HashMap<String, Long>  get_OM_ID_LAND_ZIEL_JUR_hmLong(Long NullValue) throws myException
	{
		HashMap<String, Long> hmRueck = new HashMap<String, Long>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_OM_ID_LAND_ZIEL_JUR_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_OM_ID_LAND_ZIEL_JUR_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_OM_ID_LAND_ZIEL_JUR_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_OM_ID_LAND_ZIEL_JUR_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_OM_ID_LAND_ZIEL_JUR_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_OM_START_DIENSTLST_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_DIENSTLST_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_DIENSTLST_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_DIENSTLST_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_START_EOW_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_EOW_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_EOW_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_EOW_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_START_IST_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_IST_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_IST_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_IST_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_START_LAND_GEO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_LAND_GEO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_LAND_GEO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_LAND_GEO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_START_LAND_JUR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_LAND_JUR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_LAND_JUR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_LAND_JUR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_START_PRODUKT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_PRODUKT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_PRODUKT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_PRODUKT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_START_RC_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_RC_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_RC_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_RC_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_START_UNTERNEHMEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_UNTERNEHMEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_START_UNTERNEHMEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_START_UNTERNEHMEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_TPA_VERANTWORT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_TPA_VERANTWORT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_TPA_VERANTWORT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_TPA_VERANTWORT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_DIENSTLST_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_DIENSTLST_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_DIENSTLST_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_DIENSTLST_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_EOW_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_EOW_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_EOW_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_EOW_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_IST_MANDANT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_IST_MANDANT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_IST_MANDANT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_IST_MANDANT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_LAND_GEO_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_LAND_GEO_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_LAND_GEO_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_LAND_GEO_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_LAND_JUR_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_LAND_JUR_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_LAND_JUR_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_LAND_JUR_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_PRODUKT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_PRODUKT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_PRODUKT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_PRODUKT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_RC_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_RC_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_RC_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_RC_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_OM_ZIEL_UNTERNEHMEN_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_UNTERNEHMEN_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_OM_ZIEL_UNTERNEHMEN_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_OM_ZIEL_UNTERNEHMEN_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERSATZ_START_ALT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_START_ALT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_START_ALT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_START_ALT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_START_ALT_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STEUERSATZ_START_ALT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_STEUERSATZ_START_ALT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_START_ALT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STEUERSATZ_START_HD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_START_HD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_START_HD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_START_HD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_START_HD_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STEUERSATZ_START_HD_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_STEUERSATZ_START_HD_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_START_HD_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STEUERSATZ_ZIEL_ALT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_ZIEL_ALT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_ZIEL_ALT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_ZIEL_ALT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_ZIEL_ALT_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STEUERSATZ_ZIEL_ALT_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_STEUERSATZ_ZIEL_ALT_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_ZIEL_ALT_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STEUERSATZ_ZIEL_HD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_ZIEL_HD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERSATZ_ZIEL_HD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERSATZ_ZIEL_HD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	
	
	public double get_STEUERSATZ_ZIEL_HD_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
			Double 				oD = oRec.get_STEUERSATZ_ZIEL_HD_dValue(new Double(0));
			
			if (oValidator == null || oValidator.isValid(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	

	
	
	public HashMap<String, Double>  get_STEUERSATZ_ZIEL_HD_hmDouble(Double NullValue) throws myException
	{
		HashMap<String, Double> hmRueck = new HashMap<String, Double>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_STEUERSATZ_ZIEL_HD_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	
	public HashMap<String, String>  get_STEUERVERMERK_START_ALT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_START_ALT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERVERMERK_START_ALT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_START_ALT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERVERMERK_START_HD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_START_HD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERVERMERK_START_HD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_START_HD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERVERMERK_ZIEL_ALT_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_ZIEL_ALT_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERVERMERK_ZIEL_ALT_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_ZIEL_ALT_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_STEUERVERMERK_ZIEL_HD_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_ZIEL_HD_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_STEUERVERMERK_ZIEL_HD_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_STEUERVERMERK_ZIEL_HD_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SUCHERGEBNIS_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SUCHERGEBNIS_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SUCHERGEBNIS_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SUCHERGEBNIS_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_TIMESTAMP_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_TIMESTAMP_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_UUID_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_UUID_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_Formated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	
	

	public HashMap<String, String>  get_SYS_TRIGGER_VERSION_hmString_UnFormated(String NullString) throws myException
	{
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			String 				oString =   oRec.get_SYS_TRIGGER_VERSION_cUF_NN(NullString);
			
			hmRueck.put(oEntry.getKey(), oString);
		}
		return hmRueck;
	}
	

	
	
	

	public long get_SYS_TRIGGER_VERSION_l_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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

	

	public long get_SYS_TRIGGER_VERSION_l_Count_NotNull(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		long dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Long 				oL =   oRec.get_SYS_TRIGGER_VERSION_lValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oL);
		}
		return hmRueck;
	}
	
	
		
	public double get_SYS_TRIGGER_VERSION_d_Summe(RECLIST_TESTTABELLE_HANDELDEF.Validation oValidator) throws myException
	{
		Iterator<Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oIter.next().getValue();
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
		
		for (java.util.Map.Entry<String,RECORD_TESTTABELLE_HANDELDEF> oEntry: this.entrySet())
		{
			RECORD_TESTTABELLE_HANDELDEF 	oRec = oEntry.getValue();
			Double 				oD =   oRec.get_SYS_TRIGGER_VERSION_dValue(NullValue);
			
			hmRueck.put(oEntry.getKey(), oD);
		}
		return hmRueck;
	}
	


	}
