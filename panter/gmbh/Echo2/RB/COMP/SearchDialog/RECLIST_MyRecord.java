package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import java.sql.ResultSetMetaData;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.IF_RecordList;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB_raw;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * Allgemeine Reclist für beliebige Selektionen
 * @author manfred
 * @date 16.08.2017
 *
 */
public class RECLIST_MyRecord extends HashMap<String,MyRECORD>  implements Iterable<MyRECORD>, IF_RecordList<MyRECORD>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	private int  		    iMaxNumberOfRecords = 0;             //falls groesser als null, wird der schalter overflow = true gesetzt und die recordlist wieder leer gemacht
	private boolean 		bOverFlow =false;


	//2015-03-10 martin: 
	private String        	cKeyField = "";
//	private String        	cTableName = "";
	private SEL   			Select =  null; 
	
	
    //2015-12-01 martin:
    private boolean         allow_over_flow = false;
	


	/**
	 * 2015-03-10:   
	 * @param SEL 
	 * @throws myException
	 */
	public RECLIST_MyRecord(SEL select, String IDField) throws myException
	{
		super();
		this.cQueryString = select.s();
		this.cKeyField = IDField;
		this.Select = select;
		this.REFRESH();
	}

	
	/**
	 * 2015-03-10: martin  
	 * @param SELECT 
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_MyRecord(SEL select, String IDField, MyConnection Conn) throws myException
	{
		super();
		this.cQueryString = select.s();
		this.cKeyField = IDField;
		this.Select = select;
		this.oConn = Conn;
		this.REFRESH();
	}







	/**
	 * @throws myException
	 */
	public RECLIST_MyRecord() throws myException
	{
		super();
	}


	/**
	 * @throws myException
	 */
	public RECLIST_MyRecord(MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
	}



	/**
	 * 
	 * @param QueryString !!ID_USER muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_MyRecord(String QueryString, String IDField) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = IDField;
		this.REFRESH();
	}

	
//	/**
//	 * 
//	 * @param WhereStatment or null
//	 * @param OrderStatment or null
//	 * @throws myException
//	 */
//	public RECLIST_MyRecord(String cWhereblock, String cOrderblock) throws myException
//	{
//		super();
//		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER";
//		
//		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
//		{
//		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
//		}
//
//		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
//		{
//		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
//		}
//		
//		this.REFRESH();
//	}

	

//	/**
//	 * 
//	 * @param WhereStatment or null
//	 * @param OrderStatment or null
//	 * @param Conn
//	 * @throws myException
//	 */
//	public RECLIST_MyRecord(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
//	{
//		super();
//		this.oConn = Conn;
//		
//		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER";
//		
//		if (!(cWhereblock==null || cWhereblock.trim().equals("")))
//		{
//		  this.cQueryString = this.cQueryString+" WHERE "+cWhereblock;
//		}
//
//		if (!(cOrderblock==null || cOrderblock.trim().equals("")))
//		{
//		  this.cQueryString = this.cQueryString+" ORDER BY "+cOrderblock;
//		}
//		
//		this.REFRESH();
//	}


	
	

	/**
	 * 
	 * @param QueryString !!ID muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @throws myException
	 */
	public RECLIST_MyRecord(String QueryString, String IDField, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		this.cQueryString = QueryString;
		this.cKeyField = IDField;

		this.REFRESH();
	}



	//2012-02-10: neue konstruktoren: uebergabe eines vectors aus unformated ids
	
	/**
	 * 
	 * @param Vector<String> !!!
	 * @param Conn
	 * @throws myException
	 */
//	public RECLIST_MyRecord(Vector<String> vIDs) throws myException
//	{
//		super();
//		
//		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
//		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER IN("+cID_Block+")";
//		this.REFRESH();
//	}
	
	
	
//	/**
//	 * 
//	 * @param Vector<String> !!!
//	 * @param Conn
//	 * @throws myException
//	 */
//	public RECLIST_MyRecord(Vector<String> vIDs, MyConnection Conn) throws myException
//	{
//		super();
//		this.oConn = Conn;
//		
//		String cID_Block = bibALL.Concatenate(vIDs, ",", "-1");
//		this.cQueryString = "SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER IN("+cID_Block+")";
//		this.REFRESH();
//	}

	


	/**
	 * @throws myException
	 */
	public RECLIST_MyRecord(int MaxNumberOfRecords) throws myException
	{
		super();
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}


	/**
	 * @throws myException
	 */
	public RECLIST_MyRecord(MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super();
		
		this.oConn = Conn;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;
	}



	/**
	 * 
	 * @param QueryString !!ID_USER muss im querystring vorhanden sein !!!
	 * @throws myException
	 */
	public RECLIST_MyRecord(String QueryString, String IDField , int MaxNumberOfRecords) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = IDField;
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
    public RECLIST_MyRecord(String QueryString, String IDField, int MaxNumberOfRecords, boolean p_allow_overflow) throws myException
    {
        super();
        this.cQueryString = QueryString;
        this.cKeyField = IDField;
        this.iMaxNumberOfRecords = MaxNumberOfRecords;
        this.allow_over_flow = p_allow_overflow;
		
        this.REFRESH();
	}



//
//	/**
//	 * 
//	 * @param QueryString !!ID_USER muss im querystring vorhanden sein !!!
//	 * @param Conn
//	 * @throws myException
//	 */
//	public RECLIST_MyRecord(String QueryString, String IDField, MyConnection Conn,int MaxNumberOfRecords) throws myException
//	{
//		super();
//		this.oConn = Conn;
//		this.cQueryString = QueryString;
//		this.cKeyField = IDField;
//		this.iMaxNumberOfRecords = MaxNumberOfRecords;
//
//		this.REFRESH();
//	}
//



	/**
	 * 
	 * @param QueryString !!cOwnKeyField muss im querystring vorhanden sein !!!
	 * @param Conn
	 * @param MaxNumberOfRecords    (0=unbegrenzt)
	 * @param cOwnKeyField          (damit kann ein zusammengesetzter key z.b. benutzt werden)
	 * @throws myException
	 */
	public RECLIST_MyRecord(String QueryString, MyConnection Conn,int MaxNumberOfRecords, String IDField) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField = IDField;
		this.iMaxNumberOfRecords = MaxNumberOfRecords;

		this.REFRESH();
	}


	/**
	 * * @param cKeyFieldName   (kann benutzt werden, wenn einer leeren Recordlist record einer manuell aufgebauten Reclist uebergeben werden sollen)
	 */
	public RECLIST_MyRecord set_KEYFIELD(String IDField)
	{
		this.cKeyField = IDField;
		return this;
	}



	/**
	 * 
	 * @return keyfield-name 
	 */
	public String get_cKeyField()
	{
		return this.cKeyField;
	}


	public RECLIST_MyRecord set_oConn(MyConnection conn)
	{
		this.oConn = conn;
		return this;
		
	}


	public String get_cQueryString()
	{
		return this.cQueryString;
	}


	public RECLIST_MyRecord set_cQueryString(String queryString)
	{
		this.cQueryString = queryString;
		return this;
	}


	public int get_iMaxNumberOfRecords()
	{
		return this.iMaxNumberOfRecords;
	}


	public RECLIST_MyRecord set_iMaxNumberOfRecords(int maxNumberOfRecords)
	{
		this.iMaxNumberOfRecords = maxNumberOfRecords;
		return this;
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
                      	MyRECORD  	oRec = 		new MyRECORD(this.oConn);
                      	
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
           	throw new myException(this,myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT+" :: RECORD_LIST_USER: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	
    @Override
	public MyRECORD get(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this RECORD_LIST_USER!");			
		}
		
		return super.get(cIndexKeyOfRecord);
	}
	
	
	
    @Override
    public MyRECORD get(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return super.get(this.vKeyValues.get(iNumberInVector));
	}
	
	

	
	
	public static abstract class Validation
	{
   		public abstract boolean isValid(MyRECORD oRECORD) throws myException;
	}
	
	public static abstract class DoAnyThingWithAll
	{
   		public abstract void doAnyThingWith(String cHashKey, MyRECORD oRECORD) throws myException;
	}


    @Override
	public Vector<String> get_vKeyValues()
	{
		return this.vKeyValues;
	}

	
	
	public void DoAnyThing(RECLIST_MyRecord.DoAnyThingWithAll doingObject) throws myException
	{
		for (java.util.Map.Entry<String,MyRECORD> oEntry: this.entrySet())
		{
			doingObject.doAnyThingWith(oEntry.getKey(),oEntry.getValue());
		}
	}
	
	
	
	public void putAll(RECLIST_MyRecord oAddonList, boolean bAllowAndIgnoreDoubleIDs) throws myException 
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
	
	
	public void ADD(MyRECORD oRecord, boolean bAllowOverWriteDoubleKey) throws myException
	{
		//beim ersten add-Record wird ein refresh unmoeglich, durch loeschen der query-information
		this.cQueryString = null;
		
		String cAddedKey = oRecord.get_UnFormatedValue(this.cKeyField);
		if (this.vKeyValues.contains(cAddedKey) && !bAllowOverWriteDoubleKey)
		{
			throw new myException(this,"Error adding RECORD: Double Key not allowed !");
		}
		//falls ein eintrag ueberschrieben wird, darf der Wert natuerlich im keyVector nur einmal stehen
		if (!this.vKeyValues.contains(cAddedKey))
		{ 
			this.vKeyValues.add(cAddedKey);
		}
		this.put(cAddedKey, oRecord);
	}
	
	
	public void ADD(RECLIST_MyRecord oRecordList, boolean bAllowOverWriteDoubleKey) throws myException
	{
		for (int i=0; i<oRecordList.get_vKeyValues().size();i++)
		{
			this.ADD(oRecordList.get(i), bAllowOverWriteDoubleKey);
		}
	}
	

	public RECLIST_MyRecord get_SUBLIST(RECLIST_MyRecord.Validation oValidator)  throws myException
	{
		RECLIST_MyRecord oRecListRueck = new RECLIST_MyRecord(this.oConn);
		
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
	public Vector<MyRECORD>  GET_SORTED_VECTOR(Vector<String> vSortFields, boolean bUPDown_true_is_up) throws myException
	{
		Vector<MyRECORD> vRueck = new Vector<MyRECORD>();
		
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
     public Vector<MyRECORD>  sort(boolean bUpDown_up_is_true, IF_Field... field) throws myException 	{
        Vector<String>  v_sort = new Vector<>();
        for (IF_Field f: field) {
            v_sort.add(f.fn());
        }
        return this.GET_SORTED_VECTOR(v_sort,bUpDown_up_is_true);
     }
	
	
	
	
	
	private class Sorter implements Comparator<MyRECORD>
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

		
		public int compare(MyRECORD o1, MyRECORD o2) 
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
	
	
//    //2015-03-10 martin: 
//    public String get_cTableName() {
//        return this.cTableName;
//    }
	
	
	
    /**
     * 2015-03-10: martin  
	 * @return SELECT-object, can be null
	 */
    public SEL get_Select() {
        return this.Select;
    }


    /**
     * 2015-03-10: martin  
	 * @param select
     * @throws myException 
	 */
    public RECLIST_MyRecord set_Select(SEL select) throws myException {
        this.Select = select;
        this.set_cQueryString(this.Select.s());
        return this;
    }
	
	
	
    @Override
    public Iterator<MyRECORD> iterator() {
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
	
     
     
     
     
 	public HashMap<String, String>  get_hmString_Formated(String Fieldname,String NullString) throws myException
 	{
 		HashMap<String, String> hmRueck = new HashMap<String, String>();
 		
 		for (java.util.Map.Entry<String,MyRECORD> oEntry: this.entrySet())
 		{
 			MyRECORD 	oRec = oEntry.getValue();
 			String 		oString =   (oRec.get(Fieldname).get_FieldValueFormated() != null ? oRec.get(Fieldname).get_FieldValueFormated() : NullString);
 			
 			hmRueck.put(oEntry.getKey(), oString);
 		}
 		return hmRueck;
 	}

     
 	
 	
	
 	public HashMap<String, String>  get_hmString_Unformated(String Fieldname,String NullString) throws myException
 	{
 		HashMap<String, String> hmRueck = new HashMap<String, String>();
 		
 		for (java.util.Map.Entry<String,MyRECORD> oEntry: this.entrySet())
 		{
 			MyRECORD 	oRec = oEntry.getValue();
 			String 		oString =   (oRec.get(Fieldname).get_FieldValueUnformated() != null ? oRec.get(Fieldname).get_FieldValueUnformated() : NullString);
 			
 			hmRueck.put(oEntry.getKey(), oString);
 		}
 		return hmRueck;
 	}
	
	
}
