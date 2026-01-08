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

public class RECORD_REPORT_PIPELINE_POS extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_REPORT_PIPELINE_POS";
    public static String IDFIELD   = "ID_REPORT_PIPELINE_POS";
    

	//erzeugen eines RECORDNEW_JT_REPORT_PIPELINE_POS - felds
	private RECORDNEW_REPORT_PIPELINE_POS   recNEW = null;

    private _TAB  tab = _TAB.report_pipeline_pos;  



	public RECORD_REPORT_PIPELINE_POS() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");
	}


	public RECORD_REPORT_PIPELINE_POS(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");
	}



	public RECORD_REPORT_PIPELINE_POS(RECORD_REPORT_PIPELINE_POS recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PIPELINE_POS.TABLENAME);
	}


	//2014-04-03
	public RECORD_REPORT_PIPELINE_POS(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PIPELINE_POS.TABLENAME);
	}




	public RECORD_REPORT_PIPELINE_POS(long lID_Unformated) throws myException
	{
		super("JT_REPORT_PIPELINE_POS","ID_REPORT_PIPELINE_POS",""+lID_Unformated);
		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PIPELINE_POS.TABLENAME);
	}

	public RECORD_REPORT_PIPELINE_POS(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PIPELINE_POS", "ID_REPORT_PIPELINE_POS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PIPELINE_POS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PIPELINE_POS.TABLENAME);
	}
	
	

	public RECORD_REPORT_PIPELINE_POS(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_REPORT_PIPELINE_POS","ID_REPORT_PIPELINE_POS",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PIPELINE_POS.TABLENAME);

	}


	public RECORD_REPORT_PIPELINE_POS(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORT_PIPELINE_POS");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PIPELINE_POS", "ID_REPORT_PIPELINE_POS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PIPELINE_POS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PIPELINE_POS.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_REPORT_PIPELINE_POS();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_REPORT_PIPELINE_POS.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_REPORT_PIPELINE_POS";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_REPORT_PIPELINE_POS_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_REPORT_PIPELINE_POS_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORT_PIPELINE_POS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORT_PIPELINE_POS", bibE2.cTO(), "ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORT_PIPELINE_POS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORT_PIPELINE_POS", bibE2.cTO(), "ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_REPORT_PIPELINE_POS WHERE ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_REPORT_PIPELINE_POS WHERE ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF();
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
			if (S.isFull(this.get_ID_REPORT_PIPELINE_POS_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PIPELINE_POS", "ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_REPORT_PIPELINE_POS get_RECORDNEW_REPORT_PIPELINE_POS() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_REPORT_PIPELINE_POS();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_REPORT_PIPELINE_POS(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_REPORT_PIPELINE_POS create_Instance() throws myException {
		return new RECORD_REPORT_PIPELINE_POS();
	}
	
	public static RECORD_REPORT_PIPELINE_POS create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_REPORT_PIPELINE_POS(Conn);
    }

	public static RECORD_REPORT_PIPELINE_POS create_Instance(RECORD_REPORT_PIPELINE_POS recordOrig) {
		return new RECORD_REPORT_PIPELINE_POS(recordOrig);
    }

	public static RECORD_REPORT_PIPELINE_POS create_Instance(long lID_Unformated) throws myException {
		return new RECORD_REPORT_PIPELINE_POS(lID_Unformated);
    }

	public static RECORD_REPORT_PIPELINE_POS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_REPORT_PIPELINE_POS(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_REPORT_PIPELINE_POS create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_REPORT_PIPELINE_POS(lID_Unformated, Conn);
	}

	public static RECORD_REPORT_PIPELINE_POS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_REPORT_PIPELINE_POS(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_REPORT_PIPELINE_POS create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_REPORT_PIPELINE_POS(recordOrig);	    
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
    public RECORD_REPORT_PIPELINE_POS build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PIPELINE_POS WHERE ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF());
      }
      //return new RECORD_REPORT_PIPELINE_POS(this.get_cSQL_4_Build());
      RECORD_REPORT_PIPELINE_POS rec = new RECORD_REPORT_PIPELINE_POS();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_REPORT_PIPELINE_POS
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_REPORT_PIPELINE_POS-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_REPORT_PIPELINE_POS get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_REPORT_PIPELINE_POS_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_REPORT_PIPELINE_POS  recNew = new RECORDNEW_REPORT_PIPELINE_POS();
		
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
    public RECORD_REPORT_PIPELINE_POS set_recordNew(RECORDNEW_REPORT_PIPELINE_POS recnew) throws myException {
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
	
	



		private RECORD_DRUCKER UP_RECORD_DRUCKER_id_drucker = null;




		private RECORD_REPORT_PIPELINE UP_RECORD_REPORT_PIPELINE_id_report_pipeline = null;




		private RECLIST_REPORT_PP_POS_USER_EXCL DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos = null ;




		private RECLIST_REPORT_PP_POS_USER_INCL DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos = null ;






	
	/**
	 * References the Field ID_DRUCKER
	 * Falls keine this.get_ID_DRUCKER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_DRUCKER get_UP_RECORD_DRUCKER_id_drucker() throws myException
	{
		if (S.isEmpty(this.get_ID_DRUCKER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_DRUCKER_id_drucker==null)
		{
			this.UP_RECORD_DRUCKER_id_drucker = new RECORD_DRUCKER(this.get_ID_DRUCKER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_DRUCKER_id_drucker;
	}
	





	
	/**
	 * References the Field ID_REPORT_PIPELINE
	 * Falls keine this.get_ID_REPORT_PIPELINE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_REPORT_PIPELINE get_UP_RECORD_REPORT_PIPELINE_id_report_pipeline() throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_PIPELINE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_REPORT_PIPELINE_id_report_pipeline==null)
		{
			this.UP_RECORD_REPORT_PIPELINE_id_report_pipeline = new RECORD_REPORT_PIPELINE(this.get_ID_REPORT_PIPELINE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_REPORT_PIPELINE_id_report_pipeline;
	}
	





	/**
	 * References the Field ID_REPORT_PIPELINE_POS 
	 * Falls keine get_ID_REPORT_PIPELINE_POS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_EXCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos() throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_PIPELINE_POS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos==null)
		{
			this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos = new RECLIST_REPORT_PP_POS_USER_EXCL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_EXCL WHERE ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF()+" ORDER BY ID_REPORT_PP_POS_USER_EXCL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_REPORT_PIPELINE_POS 
	 * Falls keine get_ID_REPORT_PIPELINE_POS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_EXCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_PIPELINE_POS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_EXCL WHERE ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos = new RECLIST_REPORT_PP_POS_USER_EXCL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos;
	}


	





	/**
	 * References the Field ID_REPORT_PIPELINE_POS 
	 * Falls keine get_ID_REPORT_PIPELINE_POS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_INCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos() throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_PIPELINE_POS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos==null)
		{
			this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos = new RECLIST_REPORT_PP_POS_USER_INCL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_INCL WHERE ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF()+" ORDER BY ID_REPORT_PP_POS_USER_INCL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_REPORT_PIPELINE_POS 
	 * Falls keine get_ID_REPORT_PIPELINE_POS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_INCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_PIPELINE_POS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_INCL WHERE ID_REPORT_PIPELINE_POS="+this.get_ID_REPORT_PIPELINE_POS_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos = new RECLIST_REPORT_PP_POS_USER_INCL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos;
	}


	

	public static String FIELD__ARCHIV_TABLENAME = "ARCHIV_TABLENAME";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_DRUCKER = "ID_DRUCKER";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_REPORT_PIPELINE = "ID_REPORT_PIPELINE";
	public static String FIELD__ID_REPORT_PIPELINE_POS = "ID_REPORT_PIPELINE_POS";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MAIL_TARGETS_KOMMASEPARATED = "MAIL_TARGETS_KOMMASEPARATED";
	public static String FIELD__ORIGINAL_DOKUMENT = "ORIGINAL_DOKUMENT";
	public static String FIELD__RELEVANT_4_MAIL = "RELEVANT_4_MAIL";
	public static String FIELD__RELEVANT_4_PREVIEW = "RELEVANT_4_PREVIEW";
	public static String FIELD__RELEVANT_4_PRINT = "RELEVANT_4_PRINT";
	public static String FIELD__REPORTFILEBASENAME = "REPORTFILEBASENAME";
	public static String FIELD__SQL_ARCHIVFILENAME = "SQL_ARCHIVFILENAME";
	public static String FIELD__SQL_ARCHIV_ID = "SQL_ARCHIV_ID";
	public static String FIELD__SQL_EXEC_TRUE_FALSE = "SQL_EXEC_TRUE_FALSE";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TYP_VERARBEITUNG = "TYP_VERARBEITUNG";
	public static String FIELD__ZUSATZSTRING4JASPERHASH1 = "ZUSATZSTRING4JASPERHASH1";
	public static String FIELD__ZUSATZSTRING4JASPERHASH2 = "ZUSATZSTRING4JASPERHASH2";
	public static String FIELD__ZUSATZSTRING4JASPERHASH3 = "ZUSATZSTRING4JASPERHASH3";


	public String get_ARCHIV_TABLENAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARCHIV_TABLENAME");
	}

	public String get_ARCHIV_TABLENAME_cF() throws myException
	{
		return this.get_FormatedValue("ARCHIV_TABLENAME");	
	}

	public String get_ARCHIV_TABLENAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARCHIV_TABLENAME");
	}

	public String get_ARCHIV_TABLENAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARCHIV_TABLENAME",cNotNullValue);
	}

	public String get_ARCHIV_TABLENAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARCHIV_TABLENAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARCHIV_TABLENAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARCHIV_TABLENAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARCHIV_TABLENAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARCHIV_TABLENAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARCHIV_TABLENAME", calNewValueFormated);
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
		public String get_ID_DRUCKER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_DRUCKER");
	}

	public String get_ID_DRUCKER_cF() throws myException
	{
		return this.get_FormatedValue("ID_DRUCKER");	
	}

	public String get_ID_DRUCKER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_DRUCKER");
	}

	public String get_ID_DRUCKER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_DRUCKER",cNotNullValue);
	}

	public String get_ID_DRUCKER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_DRUCKER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_DRUCKER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_DRUCKER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_DRUCKER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_DRUCKER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DRUCKER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DRUCKER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DRUCKER", calNewValueFormated);
	}
		public Long get_ID_DRUCKER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_DRUCKER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_DRUCKER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_DRUCKER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_DRUCKER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_DRUCKER").getDoubleValue();
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
	public String get_ID_DRUCKER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DRUCKER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_DRUCKER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DRUCKER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_DRUCKER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DRUCKER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_DRUCKER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DRUCKER").getBigDecimalValue();
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
	
	
	public String get_ID_REPORT_PIPELINE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT_PIPELINE");
	}

	public String get_ID_REPORT_PIPELINE_cF() throws myException
	{
		return this.get_FormatedValue("ID_REPORT_PIPELINE");	
	}

	public String get_ID_REPORT_PIPELINE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_REPORT_PIPELINE");
	}

	public String get_ID_REPORT_PIPELINE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT_PIPELINE",cNotNullValue);
	}

	public String get_ID_REPORT_PIPELINE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_REPORT_PIPELINE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_REPORT_PIPELINE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT_PIPELINE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_REPORT_PIPELINE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE", calNewValueFormated);
	}
		public Long get_ID_REPORT_PIPELINE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_REPORT_PIPELINE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_REPORT_PIPELINE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_REPORT_PIPELINE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_REPORT_PIPELINE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_REPORT_PIPELINE").getDoubleValue();
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
	public String get_ID_REPORT_PIPELINE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_PIPELINE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_REPORT_PIPELINE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_PIPELINE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_REPORT_PIPELINE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT_PIPELINE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_REPORT_PIPELINE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT_PIPELINE").getBigDecimalValue();
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
	
	
	public String get_ID_REPORT_PIPELINE_POS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT_PIPELINE_POS");
	}

	public String get_ID_REPORT_PIPELINE_POS_cF() throws myException
	{
		return this.get_FormatedValue("ID_REPORT_PIPELINE_POS");	
	}

	public String get_ID_REPORT_PIPELINE_POS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_REPORT_PIPELINE_POS");
	}

	public String get_ID_REPORT_PIPELINE_POS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT_PIPELINE_POS",cNotNullValue);
	}

	public String get_ID_REPORT_PIPELINE_POS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_REPORT_PIPELINE_POS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT_PIPELINE_POS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_REPORT_PIPELINE_POS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PIPELINE_POS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PIPELINE_POS", calNewValueFormated);
	}
		public Long get_ID_REPORT_PIPELINE_POS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_REPORT_PIPELINE_POS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_REPORT_PIPELINE_POS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_REPORT_PIPELINE_POS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_REPORT_PIPELINE_POS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_REPORT_PIPELINE_POS").getDoubleValue();
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
	public String get_ID_REPORT_PIPELINE_POS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_PIPELINE_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_REPORT_PIPELINE_POS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_PIPELINE_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_REPORT_PIPELINE_POS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT_PIPELINE_POS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_REPORT_PIPELINE_POS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT_PIPELINE_POS").getBigDecimalValue();
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
		public String get_MAIL_TARGETS_KOMMASEPARATED_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_TARGETS_KOMMASEPARATED");
	}

	public String get_MAIL_TARGETS_KOMMASEPARATED_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_TARGETS_KOMMASEPARATED");	
	}

	public String get_MAIL_TARGETS_KOMMASEPARATED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_TARGETS_KOMMASEPARATED");
	}

	public String get_MAIL_TARGETS_KOMMASEPARATED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_TARGETS_KOMMASEPARATED",cNotNullValue);
	}

	public String get_MAIL_TARGETS_KOMMASEPARATED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_TARGETS_KOMMASEPARATED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TARGETS_KOMMASEPARATED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_TARGETS_KOMMASEPARATED", calNewValueFormated);
	}
		public String get_ORIGINAL_DOKUMENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORIGINAL_DOKUMENT");
	}

	public String get_ORIGINAL_DOKUMENT_cF() throws myException
	{
		return this.get_FormatedValue("ORIGINAL_DOKUMENT");	
	}

	public String get_ORIGINAL_DOKUMENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORIGINAL_DOKUMENT");
	}

	public String get_ORIGINAL_DOKUMENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORIGINAL_DOKUMENT",cNotNullValue);
	}

	public String get_ORIGINAL_DOKUMENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORIGINAL_DOKUMENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORIGINAL_DOKUMENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORIGINAL_DOKUMENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORIGINAL_DOKUMENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORIGINAL_DOKUMENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORIGINAL_DOKUMENT", calNewValueFormated);
	}
		public boolean is_ORIGINAL_DOKUMENT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ORIGINAL_DOKUMENT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ORIGINAL_DOKUMENT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ORIGINAL_DOKUMENT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RELEVANT_4_MAIL_cUF() throws myException
	{
		return this.get_UnFormatedValue("RELEVANT_4_MAIL");
	}

	public String get_RELEVANT_4_MAIL_cF() throws myException
	{
		return this.get_FormatedValue("RELEVANT_4_MAIL");	
	}

	public String get_RELEVANT_4_MAIL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RELEVANT_4_MAIL");
	}

	public String get_RELEVANT_4_MAIL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RELEVANT_4_MAIL",cNotNullValue);
	}

	public String get_RELEVANT_4_MAIL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RELEVANT_4_MAIL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RELEVANT_4_MAIL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RELEVANT_4_MAIL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RELEVANT_4_MAIL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_MAIL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_MAIL", calNewValueFormated);
	}
		public boolean is_RELEVANT_4_MAIL_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RELEVANT_4_MAIL_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RELEVANT_4_MAIL_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RELEVANT_4_MAIL_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RELEVANT_4_PREVIEW_cUF() throws myException
	{
		return this.get_UnFormatedValue("RELEVANT_4_PREVIEW");
	}

	public String get_RELEVANT_4_PREVIEW_cF() throws myException
	{
		return this.get_FormatedValue("RELEVANT_4_PREVIEW");	
	}

	public String get_RELEVANT_4_PREVIEW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RELEVANT_4_PREVIEW");
	}

	public String get_RELEVANT_4_PREVIEW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RELEVANT_4_PREVIEW",cNotNullValue);
	}

	public String get_RELEVANT_4_PREVIEW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RELEVANT_4_PREVIEW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RELEVANT_4_PREVIEW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RELEVANT_4_PREVIEW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RELEVANT_4_PREVIEW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PREVIEW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_PREVIEW", calNewValueFormated);
	}
		public boolean is_RELEVANT_4_PREVIEW_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RELEVANT_4_PREVIEW_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RELEVANT_4_PREVIEW_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RELEVANT_4_PREVIEW_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RELEVANT_4_PRINT_cUF() throws myException
	{
		return this.get_UnFormatedValue("RELEVANT_4_PRINT");
	}

	public String get_RELEVANT_4_PRINT_cF() throws myException
	{
		return this.get_FormatedValue("RELEVANT_4_PRINT");	
	}

	public String get_RELEVANT_4_PRINT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RELEVANT_4_PRINT");
	}

	public String get_RELEVANT_4_PRINT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RELEVANT_4_PRINT",cNotNullValue);
	}

	public String get_RELEVANT_4_PRINT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RELEVANT_4_PRINT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RELEVANT_4_PRINT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RELEVANT_4_PRINT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RELEVANT_4_PRINT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELEVANT_4_PRINT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELEVANT_4_PRINT", calNewValueFormated);
	}
		public boolean is_RELEVANT_4_PRINT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RELEVANT_4_PRINT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RELEVANT_4_PRINT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RELEVANT_4_PRINT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_REPORTFILEBASENAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("REPORTFILEBASENAME");
	}

	public String get_REPORTFILEBASENAME_cF() throws myException
	{
		return this.get_FormatedValue("REPORTFILEBASENAME");	
	}

	public String get_REPORTFILEBASENAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("REPORTFILEBASENAME");
	}

	public String get_REPORTFILEBASENAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("REPORTFILEBASENAME",cNotNullValue);
	}

	public String get_REPORTFILEBASENAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("REPORTFILEBASENAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("REPORTFILEBASENAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_REPORTFILEBASENAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("REPORTFILEBASENAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTFILEBASENAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTFILEBASENAME", calNewValueFormated);
	}
		public String get_SQL_ARCHIVFILENAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQL_ARCHIVFILENAME");
	}

	public String get_SQL_ARCHIVFILENAME_cF() throws myException
	{
		return this.get_FormatedValue("SQL_ARCHIVFILENAME");	
	}

	public String get_SQL_ARCHIVFILENAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQL_ARCHIVFILENAME");
	}

	public String get_SQL_ARCHIVFILENAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQL_ARCHIVFILENAME",cNotNullValue);
	}

	public String get_SQL_ARCHIVFILENAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQL_ARCHIVFILENAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQL_ARCHIVFILENAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQL_ARCHIVFILENAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQL_ARCHIVFILENAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIVFILENAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_ARCHIVFILENAME", calNewValueFormated);
	}
		public String get_SQL_ARCHIV_ID_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQL_ARCHIV_ID");
	}

	public String get_SQL_ARCHIV_ID_cF() throws myException
	{
		return this.get_FormatedValue("SQL_ARCHIV_ID");	
	}

	public String get_SQL_ARCHIV_ID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQL_ARCHIV_ID");
	}

	public String get_SQL_ARCHIV_ID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQL_ARCHIV_ID",cNotNullValue);
	}

	public String get_SQL_ARCHIV_ID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQL_ARCHIV_ID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQL_ARCHIV_ID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQL_ARCHIV_ID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQL_ARCHIV_ID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_ARCHIV_ID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_ARCHIV_ID", calNewValueFormated);
	}
		public String get_SQL_EXEC_TRUE_FALSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQL_EXEC_TRUE_FALSE");
	}

	public String get_SQL_EXEC_TRUE_FALSE_cF() throws myException
	{
		return this.get_FormatedValue("SQL_EXEC_TRUE_FALSE");	
	}

	public String get_SQL_EXEC_TRUE_FALSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQL_EXEC_TRUE_FALSE");
	}

	public String get_SQL_EXEC_TRUE_FALSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQL_EXEC_TRUE_FALSE",cNotNullValue);
	}

	public String get_SQL_EXEC_TRUE_FALSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQL_EXEC_TRUE_FALSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQL_EXEC_TRUE_FALSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQL_EXEC_TRUE_FALSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQL_EXEC_TRUE_FALSE", calNewValueFormated);
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
	
	
	public String get_TYP_VERARBEITUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYP_VERARBEITUNG");
	}

	public String get_TYP_VERARBEITUNG_cF() throws myException
	{
		return this.get_FormatedValue("TYP_VERARBEITUNG");	
	}

	public String get_TYP_VERARBEITUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYP_VERARBEITUNG");
	}

	public String get_TYP_VERARBEITUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYP_VERARBEITUNG",cNotNullValue);
	}

	public String get_TYP_VERARBEITUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYP_VERARBEITUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYP_VERARBEITUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYP_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYP_VERARBEITUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_VERARBEITUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_VERARBEITUNG", calNewValueFormated);
	}
		public String get_ZUSATZSTRING4JASPERHASH1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZSTRING4JASPERHASH1");
	}

	public String get_ZUSATZSTRING4JASPERHASH1_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZSTRING4JASPERHASH1");	
	}

	public String get_ZUSATZSTRING4JASPERHASH1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZSTRING4JASPERHASH1");
	}

	public String get_ZUSATZSTRING4JASPERHASH1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZSTRING4JASPERHASH1",cNotNullValue);
	}

	public String get_ZUSATZSTRING4JASPERHASH1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZSTRING4JASPERHASH1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZSTRING4JASPERHASH1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH1", calNewValueFormated);
	}
		public String get_ZUSATZSTRING4JASPERHASH2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZSTRING4JASPERHASH2");
	}

	public String get_ZUSATZSTRING4JASPERHASH2_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZSTRING4JASPERHASH2");	
	}

	public String get_ZUSATZSTRING4JASPERHASH2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZSTRING4JASPERHASH2");
	}

	public String get_ZUSATZSTRING4JASPERHASH2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZSTRING4JASPERHASH2",cNotNullValue);
	}

	public String get_ZUSATZSTRING4JASPERHASH2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZSTRING4JASPERHASH2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZSTRING4JASPERHASH2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH2", calNewValueFormated);
	}
		public String get_ZUSATZSTRING4JASPERHASH3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZSTRING4JASPERHASH3");
	}

	public String get_ZUSATZSTRING4JASPERHASH3_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZSTRING4JASPERHASH3");	
	}

	public String get_ZUSATZSTRING4JASPERHASH3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZSTRING4JASPERHASH3");
	}

	public String get_ZUSATZSTRING4JASPERHASH3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZSTRING4JASPERHASH3",cNotNullValue);
	}

	public String get_ZUSATZSTRING4JASPERHASH3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZSTRING4JASPERHASH3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZSTRING4JASPERHASH3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZSTRING4JASPERHASH3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZSTRING4JASPERHASH3", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ARCHIV_TABLENAME",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_DRUCKER",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_REPORT_PIPELINE",MyRECORD.DATATYPES.NUMBER);
	put("ID_REPORT_PIPELINE_POS",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MAIL_TARGETS_KOMMASEPARATED",MyRECORD.DATATYPES.TEXT);
	put("ORIGINAL_DOKUMENT",MyRECORD.DATATYPES.YESNO);
	put("RELEVANT_4_MAIL",MyRECORD.DATATYPES.YESNO);
	put("RELEVANT_4_PREVIEW",MyRECORD.DATATYPES.YESNO);
	put("RELEVANT_4_PRINT",MyRECORD.DATATYPES.YESNO);
	put("REPORTFILEBASENAME",MyRECORD.DATATYPES.TEXT);
	put("SQL_ARCHIVFILENAME",MyRECORD.DATATYPES.TEXT);
	put("SQL_ARCHIV_ID",MyRECORD.DATATYPES.TEXT);
	put("SQL_EXEC_TRUE_FALSE",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TYP_VERARBEITUNG",MyRECORD.DATATYPES.TEXT);
	put("ZUSATZSTRING4JASPERHASH1",MyRECORD.DATATYPES.TEXT);
	put("ZUSATZSTRING4JASPERHASH2",MyRECORD.DATATYPES.TEXT);
	put("ZUSATZSTRING4JASPERHASH3",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_REPORT_PIPELINE_POS.HM_FIELDS;	
	}

}
