package panter.gmbh.basics4project.DB_RECORDS;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_MASCHINEN extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_MASCHINEN";
    public static String IDFIELD   = "ID_MASCHINEN";
    

	//erzeugen eines RECORDNEW_JT_MASCHINEN - felds
	private RECORDNEW_MASCHINEN   recNEW = null;

    private _TAB  tab = _TAB.maschinen;  



	public RECORD_MASCHINEN() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_MASCHINEN");
	}


	public RECORD_MASCHINEN(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MASCHINEN");
	}



	public RECORD_MASCHINEN(RECORD_MASCHINEN recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MASCHINEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MASCHINEN.TABLENAME);
	}


	//2014-04-03
	public RECORD_MASCHINEN(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MASCHINEN");
		this.set_Tablename_To_FieldMetaDefs(RECORD_MASCHINEN.TABLENAME);
	}




	public RECORD_MASCHINEN(long lID_Unformated) throws myException
	{
		super("JT_MASCHINEN","ID_MASCHINEN",""+lID_Unformated);
		this.set_TABLE_NAME("JT_MASCHINEN");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MASCHINEN.TABLENAME);
	}

	public RECORD_MASCHINEN(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_MASCHINEN");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MASCHINEN", "ID_MASCHINEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MASCHINEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MASCHINEN.TABLENAME);
	}
	
	

	public RECORD_MASCHINEN(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_MASCHINEN","ID_MASCHINEN",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_MASCHINEN");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MASCHINEN.TABLENAME);

	}


	public RECORD_MASCHINEN(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MASCHINEN");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MASCHINEN", "ID_MASCHINEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MASCHINEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MASCHINEN.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_MASCHINEN();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_MASCHINEN.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_MASCHINEN";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_MASCHINEN_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_MASCHINEN_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MASCHINEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MASCHINEN", bibE2.cTO(), "ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MASCHINEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MASCHINEN", bibE2.cTO(), "ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF(), null);
	}
	
	
	/*
	 * 2012-09-18: simples update, nur geaenderte felder 
	 */
	public MyE2_MessageVector UPDATE(boolean bCommit) throws myException
	{
	    //2015-05-06:  geaendert von bibDB.ExecMultiSQLVector() nach this.ExecMultiSQLVector()
		return this.ExecMultiSQLVector(bibALL.get_Vector(this.get_SQL_UPDATE_STD()),bCommit);
	}
	
		
	/*
	 * 2016-09-15: anderer name 
	 */
	public MyE2_MessageVector SAVE(boolean bCommit) throws myException {
		return this.UPDATE(bCommit);
	}
	
	
	
	public MyE2_MessageVector UPDATE(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
	    //2015-05-06:  geaendert von bibDB.ExecMultiSQLVector() nach this.ExecMultiSQLVector()
		return this.ExecMultiSQLVector(bibALL.get_Vector(this.get_SQL_UPDATE_STATEMENT(vFieldsNotInUpdate,bOnlyChangedFields)),true);
	}
	
	public MyE2_MessageVector DELETE() throws myException
	{
        //2015-05-06:  geaendert von bibDB.ExecMultiSQLVector() nach this.ExecMultiSQLVector()
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_MASCHINEN WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_MASCHINEN WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF();
	}
	
	
	
	/**
	 * REBUILD wird ueberschrieben, falls der record-datensatz von einer uebergeordnet recordlist rausgezogen wird
	 * dort liegen keine sql-querys vor. deshalb muessen diese neu definiert werden
	 * 
	 */
	public void REBUILD() throws myException
	{
		if (S.isEmpty(this.get_cSQL_4_Build()))
		{
			if (S.isFull(this.get_ID_MASCHINEN_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MASCHINEN", "ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_MASCHINEN get_RECORDNEW_MASCHINEN() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_MASCHINEN();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_MASCHINEN(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_MASCHINEN create_Instance() throws myException {
		return new RECORD_MASCHINEN();
	}
	
	public static RECORD_MASCHINEN create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_MASCHINEN(Conn);
    }

	public static RECORD_MASCHINEN create_Instance(RECORD_MASCHINEN recordOrig) {
		return new RECORD_MASCHINEN(recordOrig);
    }

	public static RECORD_MASCHINEN create_Instance(long lID_Unformated) throws myException {
		return new RECORD_MASCHINEN(lID_Unformated);
    }

	public static RECORD_MASCHINEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_MASCHINEN(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_MASCHINEN create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_MASCHINEN(lID_Unformated, Conn);
	}

	public static RECORD_MASCHINEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_MASCHINEN(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_MASCHINEN create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_MASCHINEN(recordOrig);	    
	}
	
	
//    /** 2015-02-03
//     * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
//    */
//    public MySqlStatementBuilder get_StatementBuilder(boolean bExcludeAutomaticFields) throws myException   {
//      return this.get_StatementBuilderFilledWithActualValues(bExcludeAutomaticFields);
//    }

//    /** 2015-02-17
//     * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
//    */
//	@Override
//	public boolean get_bHasSomething_to_save() throws myException {
//		return this.get_bAnyFieldIsRealyChanged();
//	}


    /** 2015-02-17
     * hinzugefuegt, um interface MyRECORD_IF_RECORDS zu erfuellen 
     */
    @Override
    public RECORD_MASCHINEN build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_MASCHINEN WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF());
      }
      //return new RECORD_MASCHINEN(this.get_cSQL_4_Build());
      RECORD_MASCHINEN rec = new RECORD_MASCHINEN();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_MASCHINEN
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_MASCHINEN-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_MASCHINEN get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_MASCHINEN_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_MASCHINEN  recNew = new RECORDNEW_MASCHINEN();
		
        Vector<String> vSonder = this.get_vSonderFelder();
		
        for (String Field: this.keySet()) {
           if (bRemoveAutomaticField) {
                //2015-05-06: geaendert von  bibALL.get_vSonderFelder() nach this.get_vSonderFelder()          
                if (vSonder.contains(Field)) {
                    continue;
                }
            }
            msg_sammler.add_MESSAGE(recNew.set_NewValueForDatabase(Field, this.get_FormatedValue(Field,null)));
        }
		
        if (changeIdWithSeq) {
           recNew.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(this.get_PRIMARY_KEY_NAME(), "SEQ_"+this.get_TABLE_NAME().substring(3)+".NEXTVAL");
        }
        return recNew;
     }
	
	
    /*
     *
     */
    public RECORD_MASCHINEN set_recordNew(RECORDNEW_MASCHINEN recnew) throws myException {
        if (this.is_ExistingRecord()) {
            throw new myException(this,"set_recordNew() only allowed, when record is empty");
        }
        this.recNEW=recnew;
        return this;
    }
	
    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }
	
    public boolean is_ExistingRecord() {
        return S.isFull(this.get_cSQL_4_Build());
    }

    public boolean is_NewRecord() {
        return !this.is_ExistingRecord();
    }
	
	



		private RECLIST_BEWEGUNG DOWN_RECLIST_BEWEGUNG_id_maschinen_anh_fp = null ;




		private RECLIST_BEWEGUNG DOWN_RECLIST_BEWEGUNG_id_maschinen_lkw_fp = null ;




		private RECLIST_BG_FAHRPLAN_TO_VEKTOR DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen = null ;




		private RECLIST_CONTAINER_STATION DOWN_RECLIST_CONTAINER_STATION_id_maschinen = null ;




		private RECLIST_FAHRPLANPOS DOWN_RECLIST_FAHRPLANPOS_id_maschinen_anh = null ;




		private RECLIST_FAHRPLANPOS DOWN_RECLIST_FAHRPLANPOS_id_maschinen_lkw = null ;




		private RECLIST_MASCHINEN_AUFGABE DOWN_RECLIST_MASCHINEN_AUFGABE_id_maschinen = null ;




		private RECLIST_MASCHINEN_KOSTEN DOWN_RECLIST_MASCHINEN_KOSTEN_id_maschinen = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp = null ;




		private RECORD_MASCHINENTYP UP_RECORD_MASCHINENTYP_id_maschinentyp = null;






	/**
	 * References the Field ID_MASCHINEN_ANH_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG get_DOWN_RECORD_LIST_BEWEGUNG_id_maschinen_anh_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_id_maschinen_anh_fp==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_id_maschinen_anh_fp = new RECLIST_BEWEGUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_MASCHINEN_ANH_FP="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_BEWEGUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_id_maschinen_anh_fp;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN_ANH_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG get_DOWN_RECORD_LIST_BEWEGUNG_id_maschinen_anh_fp(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_id_maschinen_anh_fp==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_MASCHINEN_ANH_FP="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_id_maschinen_anh_fp = new RECLIST_BEWEGUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_id_maschinen_anh_fp;
	}


	





	/**
	 * References the Field ID_MASCHINEN_LKW_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG get_DOWN_RECORD_LIST_BEWEGUNG_id_maschinen_lkw_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_id_maschinen_lkw_fp==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_id_maschinen_lkw_fp = new RECLIST_BEWEGUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_MASCHINEN_LKW_FP="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_BEWEGUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_id_maschinen_lkw_fp;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN_LKW_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG get_DOWN_RECORD_LIST_BEWEGUNG_id_maschinen_lkw_fp(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_id_maschinen_lkw_fp==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_MASCHINEN_LKW_FP="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_id_maschinen_lkw_fp = new RECLIST_BEWEGUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_id_maschinen_lkw_fp;
	}


	





	/**
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_FAHRPLAN_TO_VEKTOR get_DOWN_RECORD_LIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen==null)
		{
			this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen = new RECLIST_BG_FAHRPLAN_TO_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_FAHRPLAN_TO_VEKTOR WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_BG_FAHRPLAN_TO_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_FAHRPLAN_TO_VEKTOR get_DOWN_RECORD_LIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_FAHRPLAN_TO_VEKTOR WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen = new RECLIST_BG_FAHRPLAN_TO_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_maschinen;
	}


	





	/**
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_maschinen() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_maschinen==null)
		{
			this.DOWN_RECLIST_CONTAINER_STATION_id_maschinen = new RECLIST_CONTAINER_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_CONTAINER_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_maschinen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_maschinen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_maschinen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_STATION_id_maschinen = new RECLIST_CONTAINER_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_maschinen;
	}


	





	/**
	 * References the Field ID_MASCHINEN_ANH 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_maschinen_anh() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_anh==null)
		{
			this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_anh = new RECLIST_FAHRPLANPOS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_MASCHINEN_ANH="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_FAHRPLANPOS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_anh;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN_ANH 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_maschinen_anh(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_anh==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_MASCHINEN_ANH="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_anh = new RECLIST_FAHRPLANPOS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_anh;
	}


	





	/**
	 * References the Field ID_MASCHINEN_LKW 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_maschinen_lkw() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_lkw==null)
		{
			this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_lkw = new RECLIST_FAHRPLANPOS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_MASCHINEN_LKW="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_FAHRPLANPOS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_lkw;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN_LKW 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_maschinen_lkw(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_lkw==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_MASCHINEN_LKW="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_lkw = new RECLIST_FAHRPLANPOS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_maschinen_lkw;
	}


	





	/**
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MASCHINEN_AUFGABE get_DOWN_RECORD_LIST_MASCHINEN_AUFGABE_id_maschinen() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MASCHINEN_AUFGABE_id_maschinen==null)
		{
			this.DOWN_RECLIST_MASCHINEN_AUFGABE_id_maschinen = new RECLIST_MASCHINEN_AUFGABE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MASCHINEN_AUFGABE WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_MASCHINEN_AUFGABE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MASCHINEN_AUFGABE_id_maschinen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MASCHINEN_AUFGABE get_DOWN_RECORD_LIST_MASCHINEN_AUFGABE_id_maschinen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MASCHINEN_AUFGABE_id_maschinen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MASCHINEN_AUFGABE WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MASCHINEN_AUFGABE_id_maschinen = new RECLIST_MASCHINEN_AUFGABE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MASCHINEN_AUFGABE_id_maschinen;
	}


	





	/**
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MASCHINEN_KOSTEN get_DOWN_RECORD_LIST_MASCHINEN_KOSTEN_id_maschinen() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MASCHINEN_KOSTEN_id_maschinen==null)
		{
			this.DOWN_RECLIST_MASCHINEN_KOSTEN_id_maschinen = new RECLIST_MASCHINEN_KOSTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MASCHINEN_KOSTEN WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_MASCHINEN_KOSTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MASCHINEN_KOSTEN_id_maschinen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MASCHINEN_KOSTEN get_DOWN_RECORD_LIST_MASCHINEN_KOSTEN_id_maschinen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MASCHINEN_KOSTEN_id_maschinen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MASCHINEN_KOSTEN WHERE ID_MASCHINEN="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MASCHINEN_KOSTEN_id_maschinen = new RECLIST_MASCHINEN_KOSTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MASCHINEN_KOSTEN_id_maschinen;
	}


	





	/**
	 * References the Field ID_MASCHINEN_ANH_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_MASCHINEN_ANH_FP="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN_ANH_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_MASCHINEN_ANH_FP="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_anh_fp;
	}


	





	/**
	 * References the Field ID_MASCHINEN_LKW_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_MASCHINEN_LKW_FP="+this.get_ID_MASCHINEN_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MASCHINEN_LKW_FP 
	 * Falls keine get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_MASCHINEN_LKW_FP="+this.get_ID_MASCHINEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_maschinen_lkw_fp;
	}


	





	
	/**
	 * References the Field ID_MASCHINENTYP
	 * Falls keine this.get_ID_MASCHINENTYP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MASCHINENTYP get_UP_RECORD_MASCHINENTYP_id_maschinentyp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINENTYP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MASCHINENTYP_id_maschinentyp==null)
		{
			this.UP_RECORD_MASCHINENTYP_id_maschinentyp = new RECORD_MASCHINENTYP(this.get_ID_MASCHINENTYP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MASCHINENTYP_id_maschinentyp;
	}
	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__BESCHREIBUNG = "BESCHREIBUNG";
	public static String FIELD__BRIEFNUMMER = "BRIEFNUMMER";
	public static String FIELD__DATUM_ANSCHAFFUNG = "DATUM_ANSCHAFFUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FAHRGESTELLNUMMER = "FAHRGESTELLNUMMER";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEKAUFT_AB = "GEKAUFT_AB";
	public static String FIELD__GEWAEHRLEISTUNG_BIS = "GEWAEHRLEISTUNG_BIS";
	public static String FIELD__HERSTELLER = "HERSTELLER";
	public static String FIELD__ID_ADRESSE_STANDORT = "ID_ADRESSE_STANDORT";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_MASCHINEN = "ID_MASCHINEN";
	public static String FIELD__ID_MASCHINENTYP = "ID_MASCHINENTYP";
	public static String FIELD__ID_USER_BEDIENER1 = "ID_USER_BEDIENER1";
	public static String FIELD__ID_USER_BEDIENER2 = "ID_USER_BEDIENER2";
	public static String FIELD__KFZKENNZEICHEN = "KFZKENNZEICHEN";
	public static String FIELD__KOSTENSTELLE1 = "KOSTENSTELLE1";
	public static String FIELD__KOSTENSTELLE2 = "KOSTENSTELLE2";
	public static String FIELD__KOSTEN_ANSCHAFFUNG = "KOSTEN_ANSCHAFFUNG";
	public static String FIELD__LEASING_BIS = "LEASING_BIS";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TYPENBEZ = "TYPENBEZ";


	public String get_AKTIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("AKTIV");
	}

	public String get_AKTIV_cF() throws myException
	{
		return this.get_FormatedValue("AKTIV");	
	}

	public String get_AKTIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AKTIV");
	}

	public String get_AKTIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AKTIV",cNotNullValue);
	}

	public String get_AKTIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AKTIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AKTIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AKTIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIV", calNewValueFormated);
	}
		public boolean is_AKTIV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_AKTIV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_AKTIV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_AKTIV_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BEMERKUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG");
	}

	public String get_BEMERKUNG_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG");	
	}

	public String get_BEMERKUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG");
	}

	public String get_BEMERKUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG",cNotNullValue);
	}

	public String get_BEMERKUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", calNewValueFormated);
	}
		public String get_BESCHREIBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG");
	}

	public String get_BESCHREIBUNG_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG");	
	}

	public String get_BESCHREIBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIBUNG");
	}

	public String get_BESCHREIBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG",cNotNullValue);
	}

	public String get_BESCHREIBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", calNewValueFormated);
	}
		public String get_BRIEFNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BRIEFNUMMER");
	}

	public String get_BRIEFNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("BRIEFNUMMER");	
	}

	public String get_BRIEFNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BRIEFNUMMER");
	}

	public String get_BRIEFNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BRIEFNUMMER",cNotNullValue);
	}

	public String get_BRIEFNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BRIEFNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BRIEFNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BRIEFNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BRIEFNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BRIEFNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BRIEFNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BRIEFNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRIEFNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BRIEFNUMMER", calNewValueFormated);
	}
		public String get_DATUM_ANSCHAFFUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ANSCHAFFUNG");
	}

	public String get_DATUM_ANSCHAFFUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ANSCHAFFUNG");	
	}

	public String get_DATUM_ANSCHAFFUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ANSCHAFFUNG");
	}

	public String get_DATUM_ANSCHAFFUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ANSCHAFFUNG",cNotNullValue);
	}

	public String get_DATUM_ANSCHAFFUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ANSCHAFFUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ANSCHAFFUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANSCHAFFUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANSCHAFFUNG", calNewValueFormated);
	}
		public String get_ERZEUGT_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_AM");
	}

	public String get_ERZEUGT_AM_cF() throws myException
	{
		return this.get_FormatedValue("ERZEUGT_AM");	
	}

	public String get_ERZEUGT_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERZEUGT_AM");
	}

	public String get_ERZEUGT_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_AM",cNotNullValue);
	}

	public String get_ERZEUGT_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERZEUGT_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERZEUGT_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERZEUGT_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERZEUGT_AM", calNewValueFormated);
	}
		public String get_ERZEUGT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_VON");
	}

	public String get_ERZEUGT_VON_cF() throws myException
	{
		return this.get_FormatedValue("ERZEUGT_VON");	
	}

	public String get_ERZEUGT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERZEUGT_VON");
	}

	public String get_ERZEUGT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_VON",cNotNullValue);
	}

	public String get_ERZEUGT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERZEUGT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERZEUGT_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERZEUGT_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERZEUGT_VON", calNewValueFormated);
	}
		public String get_FAHRGESTELLNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRGESTELLNUMMER");
	}

	public String get_FAHRGESTELLNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("FAHRGESTELLNUMMER");	
	}

	public String get_FAHRGESTELLNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRGESTELLNUMMER");
	}

	public String get_FAHRGESTELLNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRGESTELLNUMMER",cNotNullValue);
	}

	public String get_FAHRGESTELLNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRGESTELLNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRGESTELLNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRGESTELLNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRGESTELLNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRGESTELLNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRGESTELLNUMMER", calNewValueFormated);
	}
		public String get_GEAENDERT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEAENDERT_VON");
	}

	public String get_GEAENDERT_VON_cF() throws myException
	{
		return this.get_FormatedValue("GEAENDERT_VON");	
	}

	public String get_GEAENDERT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEAENDERT_VON");
	}

	public String get_GEAENDERT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEAENDERT_VON",cNotNullValue);
	}

	public String get_GEAENDERT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEAENDERT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEAENDERT_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEAENDERT_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEAENDERT_VON", calNewValueFormated);
	}
		public String get_GEKAUFT_AB_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEKAUFT_AB");
	}

	public String get_GEKAUFT_AB_cF() throws myException
	{
		return this.get_FormatedValue("GEKAUFT_AB");	
	}

	public String get_GEKAUFT_AB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEKAUFT_AB");
	}

	public String get_GEKAUFT_AB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEKAUFT_AB",cNotNullValue);
	}

	public String get_GEKAUFT_AB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEKAUFT_AB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEKAUFT_AB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEKAUFT_AB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEKAUFT_AB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEKAUFT_AB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEKAUFT_AB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEKAUFT_AB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEKAUFT_AB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEKAUFT_AB", calNewValueFormated);
	}
		public String get_GEWAEHRLEISTUNG_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEWAEHRLEISTUNG_BIS");
	}

	public String get_GEWAEHRLEISTUNG_BIS_cF() throws myException
	{
		return this.get_FormatedValue("GEWAEHRLEISTUNG_BIS");	
	}

	public String get_GEWAEHRLEISTUNG_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEWAEHRLEISTUNG_BIS");
	}

	public String get_GEWAEHRLEISTUNG_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEWAEHRLEISTUNG_BIS",cNotNullValue);
	}

	public String get_GEWAEHRLEISTUNG_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEWAEHRLEISTUNG_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEWAEHRLEISTUNG_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWAEHRLEISTUNG_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWAEHRLEISTUNG_BIS", calNewValueFormated);
	}
		public String get_HERSTELLER_cUF() throws myException
	{
		return this.get_UnFormatedValue("HERSTELLER");
	}

	public String get_HERSTELLER_cF() throws myException
	{
		return this.get_FormatedValue("HERSTELLER");	
	}

	public String get_HERSTELLER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HERSTELLER");
	}

	public String get_HERSTELLER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HERSTELLER",cNotNullValue);
	}

	public String get_HERSTELLER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HERSTELLER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HERSTELLER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HERSTELLER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HERSTELLER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HERSTELLER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HERSTELLER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HERSTELLER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HERSTELLER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HERSTELLER", calNewValueFormated);
	}
		public String get_ID_ADRESSE_STANDORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_STANDORT");
	}

	public String get_ID_ADRESSE_STANDORT_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_STANDORT");	
	}

	public String get_ID_ADRESSE_STANDORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_STANDORT");
	}

	public String get_ID_ADRESSE_STANDORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_STANDORT",cNotNullValue);
	}

	public String get_ID_ADRESSE_STANDORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_STANDORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_STANDORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_STANDORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_STANDORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_STANDORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_STANDORT", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_STANDORT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_STANDORT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_STANDORT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_STANDORT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_STANDORT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_STANDORT").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_ADRESSE_STANDORT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_STANDORT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_ADRESSE_STANDORT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_STANDORT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_STANDORT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_STANDORT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_STANDORT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_STANDORT").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ID_MANDANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MANDANT");
	}

	public String get_ID_MANDANT_cF() throws myException
	{
		return this.get_FormatedValue("ID_MANDANT");	
	}

	public String get_ID_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MANDANT");
	}

	public String get_ID_MANDANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MANDANT",cNotNullValue);
	}

	public String get_ID_MANDANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MANDANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MANDANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MANDANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MANDANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MANDANT", calNewValueFormated);
	}
		public Long get_ID_MANDANT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MANDANT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MANDANT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MANDANT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MANDANT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MANDANT").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MANDANT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MANDANT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_MANDANT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MANDANT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MANDANT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MANDANT").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ID_MASCHINEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN");
	}

	public String get_ID_MASCHINEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN");	
	}

	public String get_ID_MASCHINEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MASCHINEN");
	}

	public String get_ID_MASCHINEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN",cNotNullValue);
	}

	public String get_ID_MASCHINEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MASCHINEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MASCHINEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN", calNewValueFormated);
	}
		public Long get_ID_MASCHINEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MASCHINEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MASCHINEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MASCHINEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MASCHINEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MASCHINEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_MASCHINEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MASCHINEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ID_MASCHINENTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINENTYP");
	}

	public String get_ID_MASCHINENTYP_cF() throws myException
	{
		return this.get_FormatedValue("ID_MASCHINENTYP");	
	}

	public String get_ID_MASCHINENTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MASCHINENTYP");
	}

	public String get_ID_MASCHINENTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINENTYP",cNotNullValue);
	}

	public String get_ID_MASCHINENTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MASCHINENTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MASCHINENTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINENTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MASCHINENTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINENTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINENTYP", calNewValueFormated);
	}
		public Long get_ID_MASCHINENTYP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MASCHINENTYP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MASCHINENTYP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MASCHINENTYP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MASCHINENTYP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MASCHINENTYP").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MASCHINENTYP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINENTYP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MASCHINENTYP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINENTYP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_MASCHINENTYP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINENTYP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MASCHINENTYP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINENTYP").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ID_USER_BEDIENER1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEDIENER1");
	}

	public String get_ID_USER_BEDIENER1_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_BEDIENER1");	
	}

	public String get_ID_USER_BEDIENER1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_BEDIENER1");
	}

	public String get_ID_USER_BEDIENER1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEDIENER1",cNotNullValue);
	}

	public String get_ID_USER_BEDIENER1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_BEDIENER1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_BEDIENER1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEDIENER1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_BEDIENER1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER1", calNewValueFormated);
	}
		public Long get_ID_USER_BEDIENER1_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_BEDIENER1").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_BEDIENER1_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_BEDIENER1").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_BEDIENER1_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_BEDIENER1").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_BEDIENER1_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEDIENER1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_BEDIENER1_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEDIENER1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_BEDIENER1_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEDIENER1").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_BEDIENER1_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEDIENER1").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ID_USER_BEDIENER2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEDIENER2");
	}

	public String get_ID_USER_BEDIENER2_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_BEDIENER2");	
	}

	public String get_ID_USER_BEDIENER2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_BEDIENER2");
	}

	public String get_ID_USER_BEDIENER2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEDIENER2",cNotNullValue);
	}

	public String get_ID_USER_BEDIENER2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_BEDIENER2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_BEDIENER2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEDIENER2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_BEDIENER2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEDIENER2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEDIENER2", calNewValueFormated);
	}
		public Long get_ID_USER_BEDIENER2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_BEDIENER2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_BEDIENER2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_BEDIENER2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_BEDIENER2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_BEDIENER2").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_BEDIENER2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEDIENER2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_BEDIENER2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEDIENER2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_BEDIENER2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEDIENER2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_BEDIENER2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEDIENER2").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_KFZKENNZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("KFZKENNZEICHEN");
	}

	public String get_KFZKENNZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("KFZKENNZEICHEN");	
	}

	public String get_KFZKENNZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KFZKENNZEICHEN");
	}

	public String get_KFZKENNZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KFZKENNZEICHEN",cNotNullValue);
	}

	public String get_KFZKENNZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KFZKENNZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KFZKENNZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KFZKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KFZKENNZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", calNewValueFormated);
	}
		public String get_KOSTENSTELLE1_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTENSTELLE1");
	}

	public String get_KOSTENSTELLE1_cF() throws myException
	{
		return this.get_FormatedValue("KOSTENSTELLE1");	
	}

	public String get_KOSTENSTELLE1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTENSTELLE1");
	}

	public String get_KOSTENSTELLE1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTENSTELLE1",cNotNullValue);
	}

	public String get_KOSTENSTELLE1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTENSTELLE1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTENSTELLE1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTENSTELLE1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTENSTELLE1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTENSTELLE1", calNewValueFormated);
	}
		public String get_KOSTENSTELLE2_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTENSTELLE2");
	}

	public String get_KOSTENSTELLE2_cF() throws myException
	{
		return this.get_FormatedValue("KOSTENSTELLE2");	
	}

	public String get_KOSTENSTELLE2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTENSTELLE2");
	}

	public String get_KOSTENSTELLE2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTENSTELLE2",cNotNullValue);
	}

	public String get_KOSTENSTELLE2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTENSTELLE2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTENSTELLE2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTENSTELLE2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTENSTELLE2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTENSTELLE2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTENSTELLE2", calNewValueFormated);
	}
		public String get_KOSTEN_ANSCHAFFUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_ANSCHAFFUNG");
	}

	public String get_KOSTEN_ANSCHAFFUNG_cF() throws myException
	{
		return this.get_FormatedValue("KOSTEN_ANSCHAFFUNG");	
	}

	public String get_KOSTEN_ANSCHAFFUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTEN_ANSCHAFFUNG");
	}

	public String get_KOSTEN_ANSCHAFFUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_ANSCHAFFUNG",cNotNullValue);
	}

	public String get_KOSTEN_ANSCHAFFUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTEN_ANSCHAFFUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_ANSCHAFFUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_ANSCHAFFUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_ANSCHAFFUNG", calNewValueFormated);
	}
		public Double get_KOSTEN_ANSCHAFFUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KOSTEN_ANSCHAFFUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KOSTEN_ANSCHAFFUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KOSTEN_ANSCHAFFUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_KOSTEN_ANSCHAFFUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_ANSCHAFFUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_KOSTEN_ANSCHAFFUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_ANSCHAFFUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_KOSTEN_ANSCHAFFUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_ANSCHAFFUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KOSTEN_ANSCHAFFUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_ANSCHAFFUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_LEASING_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("LEASING_BIS");
	}

	public String get_LEASING_BIS_cF() throws myException
	{
		return this.get_FormatedValue("LEASING_BIS");	
	}

	public String get_LEASING_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LEASING_BIS");
	}

	public String get_LEASING_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LEASING_BIS",cNotNullValue);
	}

	public String get_LEASING_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LEASING_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LEASING_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LEASING_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LEASING_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LEASING_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEASING_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEASING_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEASING_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEASING_BIS", calNewValueFormated);
	}
		public String get_LETZTE_AENDERUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("LETZTE_AENDERUNG");
	}

	public String get_LETZTE_AENDERUNG_cF() throws myException
	{
		return this.get_FormatedValue("LETZTE_AENDERUNG");	
	}

	public String get_LETZTE_AENDERUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LETZTE_AENDERUNG");
	}

	public String get_LETZTE_AENDERUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LETZTE_AENDERUNG",cNotNullValue);
	}

	public String get_LETZTE_AENDERUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LETZTE_AENDERUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", calNewValueFormated);
	}
		public String get_SYS_TRIGGER_TIMESTAMP_cUF() throws myException
	{
		return this.get_UnFormatedValue("SYS_TRIGGER_TIMESTAMP");
	}

	public String get_SYS_TRIGGER_TIMESTAMP_cF() throws myException
	{
		return this.get_FormatedValue("SYS_TRIGGER_TIMESTAMP");	
	}

	public String get_SYS_TRIGGER_TIMESTAMP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SYS_TRIGGER_TIMESTAMP");
	}

	public String get_SYS_TRIGGER_TIMESTAMP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SYS_TRIGGER_TIMESTAMP",cNotNullValue);
	}

	public String get_SYS_TRIGGER_TIMESTAMP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SYS_TRIGGER_TIMESTAMP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", calNewValueFormated);
	}
		public String get_SYS_TRIGGER_UUID_cUF() throws myException
	{
		return this.get_UnFormatedValue("SYS_TRIGGER_UUID");
	}

	public String get_SYS_TRIGGER_UUID_cF() throws myException
	{
		return this.get_FormatedValue("SYS_TRIGGER_UUID");	
	}

	public String get_SYS_TRIGGER_UUID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SYS_TRIGGER_UUID");
	}

	public String get_SYS_TRIGGER_UUID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SYS_TRIGGER_UUID",cNotNullValue);
	}

	public String get_SYS_TRIGGER_UUID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SYS_TRIGGER_UUID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SYS_TRIGGER_UUID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_UUID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SYS_TRIGGER_UUID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", calNewValueFormated);
	}
		public String get_SYS_TRIGGER_VERSION_cUF() throws myException
	{
		return this.get_UnFormatedValue("SYS_TRIGGER_VERSION");
	}

	public String get_SYS_TRIGGER_VERSION_cF() throws myException
	{
		return this.get_FormatedValue("SYS_TRIGGER_VERSION");	
	}

	public String get_SYS_TRIGGER_VERSION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SYS_TRIGGER_VERSION");
	}

	public String get_SYS_TRIGGER_VERSION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SYS_TRIGGER_VERSION",cNotNullValue);
	}

	public String get_SYS_TRIGGER_VERSION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SYS_TRIGGER_VERSION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SYS_TRIGGER_VERSION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_VERSION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SYS_TRIGGER_VERSION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", calNewValueFormated);
	}
		public Long get_SYS_TRIGGER_VERSION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SYS_TRIGGER_VERSION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SYS_TRIGGER_VERSION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SYS_TRIGGER_VERSION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SYS_TRIGGER_VERSION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SYS_TRIGGER_VERSION").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_SYS_TRIGGER_VERSION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SYS_TRIGGER_VERSION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_SYS_TRIGGER_VERSION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SYS_TRIGGER_VERSION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_SYS_TRIGGER_VERSION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SYS_TRIGGER_VERSION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SYS_TRIGGER_VERSION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SYS_TRIGGER_VERSION").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_TYPENBEZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYPENBEZ");
	}

	public String get_TYPENBEZ_cF() throws myException
	{
		return this.get_FormatedValue("TYPENBEZ");	
	}

	public String get_TYPENBEZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYPENBEZ");
	}

	public String get_TYPENBEZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYPENBEZ",cNotNullValue);
	}

	public String get_TYPENBEZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYPENBEZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYPENBEZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYPENBEZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYPENBEZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYPENBEZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYPENBEZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYPENBEZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYPENBEZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYPENBEZ", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("BESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("BRIEFNUMMER",MyRECORD.DATATYPES.TEXT);
	put("DATUM_ANSCHAFFUNG",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FAHRGESTELLNUMMER",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEKAUFT_AB",MyRECORD.DATATYPES.DATE);
	put("GEWAEHRLEISTUNG_BIS",MyRECORD.DATATYPES.DATE);
	put("HERSTELLER",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE_STANDORT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MASCHINEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_MASCHINENTYP",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_BEDIENER1",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_BEDIENER2",MyRECORD.DATATYPES.NUMBER);
	put("KFZKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("KOSTENSTELLE1",MyRECORD.DATATYPES.TEXT);
	put("KOSTENSTELLE2",MyRECORD.DATATYPES.TEXT);
	put("KOSTEN_ANSCHAFFUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("LEASING_BIS",MyRECORD.DATATYPES.DATE);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TYPENBEZ",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_MASCHINEN.HM_FIELDS;	
	}

}
