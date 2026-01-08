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

public class RECORD_DATEV_PROFILE extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_DATEV_PROFILE";
    public static String IDFIELD   = "ID_DATEV_PROFILE";
    

	//erzeugen eines RECORDNEW_JT_DATEV_PROFILE - felds
	private RECORDNEW_DATEV_PROFILE   recNEW = null;

    private _TAB  tab = _TAB.datev_profile;  



	public RECORD_DATEV_PROFILE() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_DATEV_PROFILE");
	}


	public RECORD_DATEV_PROFILE(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_DATEV_PROFILE");
	}



	public RECORD_DATEV_PROFILE(RECORD_DATEV_PROFILE recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_DATEV_PROFILE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_DATEV_PROFILE.TABLENAME);
	}


	//2014-04-03
	public RECORD_DATEV_PROFILE(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_DATEV_PROFILE");
		this.set_Tablename_To_FieldMetaDefs(RECORD_DATEV_PROFILE.TABLENAME);
	}




	public RECORD_DATEV_PROFILE(long lID_Unformated) throws myException
	{
		super("JT_DATEV_PROFILE","ID_DATEV_PROFILE",""+lID_Unformated);
		this.set_TABLE_NAME("JT_DATEV_PROFILE");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_DATEV_PROFILE.TABLENAME);
	}

	public RECORD_DATEV_PROFILE(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_DATEV_PROFILE");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_DATEV_PROFILE", "ID_DATEV_PROFILE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_DATEV_PROFILE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_DATEV_PROFILE.TABLENAME);
	}
	
	

	public RECORD_DATEV_PROFILE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_DATEV_PROFILE","ID_DATEV_PROFILE",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_DATEV_PROFILE");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_DATEV_PROFILE.TABLENAME);

	}


	public RECORD_DATEV_PROFILE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_DATEV_PROFILE");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_DATEV_PROFILE", "ID_DATEV_PROFILE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_DATEV_PROFILE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_DATEV_PROFILE.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_DATEV_PROFILE();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_DATEV_PROFILE.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_DATEV_PROFILE";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_DATEV_PROFILE_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_DATEV_PROFILE_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_DATEV_PROFILE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_DATEV_PROFILE", bibE2.cTO(), "ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_DATEV_PROFILE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_DATEV_PROFILE", bibE2.cTO(), "ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE WHERE ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE WHERE ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF();
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
			if (S.isFull(this.get_ID_DATEV_PROFILE_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_DATEV_PROFILE", "ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_DATEV_PROFILE get_RECORDNEW_DATEV_PROFILE() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_DATEV_PROFILE();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_DATEV_PROFILE(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_DATEV_PROFILE create_Instance() throws myException {
		return new RECORD_DATEV_PROFILE();
	}
	
	public static RECORD_DATEV_PROFILE create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_DATEV_PROFILE(Conn);
    }

	public static RECORD_DATEV_PROFILE create_Instance(RECORD_DATEV_PROFILE recordOrig) {
		return new RECORD_DATEV_PROFILE(recordOrig);
    }

	public static RECORD_DATEV_PROFILE create_Instance(long lID_Unformated) throws myException {
		return new RECORD_DATEV_PROFILE(lID_Unformated);
    }

	public static RECORD_DATEV_PROFILE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_DATEV_PROFILE(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_DATEV_PROFILE create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_DATEV_PROFILE(lID_Unformated, Conn);
	}

	public static RECORD_DATEV_PROFILE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_DATEV_PROFILE(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_DATEV_PROFILE create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_DATEV_PROFILE(recordOrig);	    
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
    public RECORD_DATEV_PROFILE build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_DATEV_PROFILE WHERE ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF());
      }
      //return new RECORD_DATEV_PROFILE(this.get_cSQL_4_Build());
      RECORD_DATEV_PROFILE rec = new RECORD_DATEV_PROFILE();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_DATEV_PROFILE
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_DATEV_PROFILE-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_DATEV_PROFILE get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_DATEV_PROFILE_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_DATEV_PROFILE  recNew = new RECORDNEW_DATEV_PROFILE();
		
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
    public RECORD_DATEV_PROFILE set_recordNew(RECORDNEW_DATEV_PROFILE recnew) throws myException {
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
	
	



		private RECLIST_USER DOWN_RECLIST_USER_id_datev_profile = null ;




		private RECORD_DRUCKER UP_RECORD_DRUCKER_id_drucker = null;




		private RECORD_DRUCKER UP_RECORD_DRUCKER_id_drucker_protokolle = null;






	/**
	 * References the Field ID_DATEV_PROFILE 
	 * Falls keine get_ID_DATEV_PROFILE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_USER get_DOWN_RECORD_LIST_USER_id_datev_profile() throws myException
	{
		if (S.isEmpty(this.get_ID_DATEV_PROFILE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_USER_id_datev_profile==null)
		{
			this.DOWN_RECLIST_USER_id_datev_profile = new RECLIST_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF()+" ORDER BY ID_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_USER_id_datev_profile;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_DATEV_PROFILE 
	 * Falls keine get_ID_DATEV_PROFILE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_USER get_DOWN_RECORD_LIST_USER_id_datev_profile(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_DATEV_PROFILE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_USER_id_datev_profile==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_DATEV_PROFILE="+this.get_ID_DATEV_PROFILE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_USER_id_datev_profile = new RECLIST_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_USER_id_datev_profile;
	}


	





	
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
	 * References the Field ID_DRUCKER_PROTOKOLLE
	 * Falls keine this.get_ID_DRUCKER_PROTOKOLLE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_DRUCKER get_UP_RECORD_DRUCKER_id_drucker_protokolle() throws myException
	{
		if (S.isEmpty(this.get_ID_DRUCKER_PROTOKOLLE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_DRUCKER_id_drucker_protokolle==null)
		{
			this.UP_RECORD_DRUCKER_id_drucker_protokolle = new RECORD_DRUCKER(this.get_ID_DRUCKER_PROTOKOLLE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_DRUCKER_id_drucker_protokolle;
	}
	

	public static String FIELD__CORRECT_DATES = "CORRECT_DATES";
	public static String FIELD__DATEV_BERATERNUMMER = "DATEV_BERATERNUMMER";
	public static String FIELD__DATEV_GESCHAEFTSJAHRESBEGINN = "DATEV_GESCHAEFTSJAHRESBEGINN";
	public static String FIELD__DATEV_MANDANTNUMMER = "DATEV_MANDANTNUMMER";
	public static String FIELD__DESCRIPTION = "DESCRIPTION";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EXPORTS_STARTING_DATE = "EXPORTS_STARTING_DATE";
	public static String FIELD__EXPORTS_STARTING_ID = "EXPORTS_STARTING_ID";
	public static String FIELD__EXPORT_DIR = "EXPORT_DIR";
	public static String FIELD__FLUSH_TABLES = "FLUSH_TABLES";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_DATEV_PROFILE = "ID_DATEV_PROFILE";
	public static String FIELD__ID_DRUCKER = "ID_DRUCKER";
	public static String FIELD__ID_DRUCKER_PROTOKOLLE = "ID_DRUCKER_PROTOKOLLE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__PRINT_PROTOCOLS = "PRINT_PROTOCOLS";
	public static String FIELD__STAMP_IMPORTED_WITH_DEBUGFLAGS = "STAMP_IMPORTED_WITH_DEBUGFLAGS";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_CORRECT_DATES_cUF() throws myException
	{
		return this.get_UnFormatedValue("CORRECT_DATES");
	}

	public String get_CORRECT_DATES_cF() throws myException
	{
		return this.get_FormatedValue("CORRECT_DATES");	
	}

	public String get_CORRECT_DATES_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("CORRECT_DATES");
	}

	public String get_CORRECT_DATES_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("CORRECT_DATES",cNotNullValue);
	}

	public String get_CORRECT_DATES_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("CORRECT_DATES",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("CORRECT_DATES", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_CORRECT_DATES(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("CORRECT_DATES", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("CORRECT_DATES", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CORRECT_DATES", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CORRECT_DATES", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CORRECT_DATES_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CORRECT_DATES", calNewValueFormated);
	}
		public boolean is_CORRECT_DATES_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_CORRECT_DATES_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_CORRECT_DATES_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_CORRECT_DATES_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DATEV_BERATERNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATEV_BERATERNUMMER");
	}

	public String get_DATEV_BERATERNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("DATEV_BERATERNUMMER");	
	}

	public String get_DATEV_BERATERNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATEV_BERATERNUMMER");
	}

	public String get_DATEV_BERATERNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATEV_BERATERNUMMER",cNotNullValue);
	}

	public String get_DATEV_BERATERNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATEV_BERATERNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATEV_BERATERNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATEV_BERATERNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATEV_BERATERNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_BERATERNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_BERATERNUMMER", calNewValueFormated);
	}
		public String get_DATEV_GESCHAEFTSJAHRESBEGINN_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATEV_GESCHAEFTSJAHRESBEGINN");
	}

	public String get_DATEV_GESCHAEFTSJAHRESBEGINN_cF() throws myException
	{
		return this.get_FormatedValue("DATEV_GESCHAEFTSJAHRESBEGINN");	
	}

	public String get_DATEV_GESCHAEFTSJAHRESBEGINN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATEV_GESCHAEFTSJAHRESBEGINN");
	}

	public String get_DATEV_GESCHAEFTSJAHRESBEGINN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATEV_GESCHAEFTSJAHRESBEGINN",cNotNullValue);
	}

	public String get_DATEV_GESCHAEFTSJAHRESBEGINN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATEV_GESCHAEFTSJAHRESBEGINN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_GESCHAEFTSJAHRESBEGINN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_GESCHAEFTSJAHRESBEGINN", calNewValueFormated);
	}
		public String get_DATEV_MANDANTNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATEV_MANDANTNUMMER");
	}

	public String get_DATEV_MANDANTNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("DATEV_MANDANTNUMMER");	
	}

	public String get_DATEV_MANDANTNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATEV_MANDANTNUMMER");
	}

	public String get_DATEV_MANDANTNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATEV_MANDANTNUMMER",cNotNullValue);
	}

	public String get_DATEV_MANDANTNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATEV_MANDANTNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATEV_MANDANTNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATEV_MANDANTNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATEV_MANDANTNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEV_MANDANTNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEV_MANDANTNUMMER", calNewValueFormated);
	}
		public String get_DESCRIPTION_cUF() throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION");
	}

	public String get_DESCRIPTION_cF() throws myException
	{
		return this.get_FormatedValue("DESCRIPTION");	
	}

	public String get_DESCRIPTION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DESCRIPTION");
	}

	public String get_DESCRIPTION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION",cNotNullValue);
	}

	public String get_DESCRIPTION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DESCRIPTION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DESCRIPTION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DESCRIPTION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DESCRIPTION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION", calNewValueFormated);
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
		public String get_EXPORTS_STARTING_DATE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EXPORTS_STARTING_DATE");
	}

	public String get_EXPORTS_STARTING_DATE_cF() throws myException
	{
		return this.get_FormatedValue("EXPORTS_STARTING_DATE");	
	}

	public String get_EXPORTS_STARTING_DATE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EXPORTS_STARTING_DATE");
	}

	public String get_EXPORTS_STARTING_DATE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EXPORTS_STARTING_DATE",cNotNullValue);
	}

	public String get_EXPORTS_STARTING_DATE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EXPORTS_STARTING_DATE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EXPORTS_STARTING_DATE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EXPORTS_STARTING_DATE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EXPORTS_STARTING_DATE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_DATE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_DATE", calNewValueFormated);
	}
		public String get_EXPORTS_STARTING_ID_cUF() throws myException
	{
		return this.get_UnFormatedValue("EXPORTS_STARTING_ID");
	}

	public String get_EXPORTS_STARTING_ID_cF() throws myException
	{
		return this.get_FormatedValue("EXPORTS_STARTING_ID");	
	}

	public String get_EXPORTS_STARTING_ID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EXPORTS_STARTING_ID");
	}

	public String get_EXPORTS_STARTING_ID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EXPORTS_STARTING_ID",cNotNullValue);
	}

	public String get_EXPORTS_STARTING_ID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EXPORTS_STARTING_ID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EXPORTS_STARTING_ID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EXPORTS_STARTING_ID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EXPORTS_STARTING_ID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTS_STARTING_ID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTS_STARTING_ID", calNewValueFormated);
	}
		public Long get_EXPORTS_STARTING_ID_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("EXPORTS_STARTING_ID").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_EXPORTS_STARTING_ID_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EXPORTS_STARTING_ID").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EXPORTS_STARTING_ID_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EXPORTS_STARTING_ID").getDoubleValue();
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
	public String get_EXPORTS_STARTING_ID_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EXPORTS_STARTING_ID_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EXPORTS_STARTING_ID_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EXPORTS_STARTING_ID_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EXPORTS_STARTING_ID_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EXPORTS_STARTING_ID").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EXPORTS_STARTING_ID_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EXPORTS_STARTING_ID").getBigDecimalValue();
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
	
	
	public String get_EXPORT_DIR_cUF() throws myException
	{
		return this.get_UnFormatedValue("EXPORT_DIR");
	}

	public String get_EXPORT_DIR_cF() throws myException
	{
		return this.get_FormatedValue("EXPORT_DIR");	
	}

	public String get_EXPORT_DIR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EXPORT_DIR");
	}

	public String get_EXPORT_DIR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EXPORT_DIR",cNotNullValue);
	}

	public String get_EXPORT_DIR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EXPORT_DIR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EXPORT_DIR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EXPORT_DIR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EXPORT_DIR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EXPORT_DIR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORT_DIR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORT_DIR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_DIR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORT_DIR", calNewValueFormated);
	}
		public String get_FLUSH_TABLES_cUF() throws myException
	{
		return this.get_UnFormatedValue("FLUSH_TABLES");
	}

	public String get_FLUSH_TABLES_cF() throws myException
	{
		return this.get_FormatedValue("FLUSH_TABLES");	
	}

	public String get_FLUSH_TABLES_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FLUSH_TABLES");
	}

	public String get_FLUSH_TABLES_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FLUSH_TABLES",cNotNullValue);
	}

	public String get_FLUSH_TABLES_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FLUSH_TABLES",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FLUSH_TABLES", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FLUSH_TABLES(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FLUSH_TABLES", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FLUSH_TABLES", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FLUSH_TABLES", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FLUSH_TABLES", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FLUSH_TABLES_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FLUSH_TABLES", calNewValueFormated);
	}
		public boolean is_FLUSH_TABLES_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FLUSH_TABLES_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FLUSH_TABLES_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FLUSH_TABLES_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_ID_DATEV_PROFILE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_DATEV_PROFILE");
	}

	public String get_ID_DATEV_PROFILE_cF() throws myException
	{
		return this.get_FormatedValue("ID_DATEV_PROFILE");	
	}

	public String get_ID_DATEV_PROFILE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_DATEV_PROFILE");
	}

	public String get_ID_DATEV_PROFILE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_DATEV_PROFILE",cNotNullValue);
	}

	public String get_ID_DATEV_PROFILE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_DATEV_PROFILE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", calNewValueFormated);
	}
		public Long get_ID_DATEV_PROFILE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_DATEV_PROFILE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_DATEV_PROFILE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_DATEV_PROFILE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_DATEV_PROFILE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_DATEV_PROFILE").getDoubleValue();
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
	public String get_ID_DATEV_PROFILE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DATEV_PROFILE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_DATEV_PROFILE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DATEV_PROFILE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_DATEV_PROFILE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DATEV_PROFILE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_DATEV_PROFILE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DATEV_PROFILE").getBigDecimalValue();
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
	
	
	public String get_ID_DRUCKER_PROTOKOLLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_DRUCKER_PROTOKOLLE");
	}

	public String get_ID_DRUCKER_PROTOKOLLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_DRUCKER_PROTOKOLLE");	
	}

	public String get_ID_DRUCKER_PROTOKOLLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_DRUCKER_PROTOKOLLE");
	}

	public String get_ID_DRUCKER_PROTOKOLLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_DRUCKER_PROTOKOLLE",cNotNullValue);
	}

	public String get_ID_DRUCKER_PROTOKOLLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_DRUCKER_PROTOKOLLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_DRUCKER_PROTOKOLLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DRUCKER_PROTOKOLLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DRUCKER_PROTOKOLLE", calNewValueFormated);
	}
		public Long get_ID_DRUCKER_PROTOKOLLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_DRUCKER_PROTOKOLLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_DRUCKER_PROTOKOLLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_DRUCKER_PROTOKOLLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_DRUCKER_PROTOKOLLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_DRUCKER_PROTOKOLLE").getDoubleValue();
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
	public String get_ID_DRUCKER_PROTOKOLLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DRUCKER_PROTOKOLLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_DRUCKER_PROTOKOLLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DRUCKER_PROTOKOLLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_DRUCKER_PROTOKOLLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DRUCKER_PROTOKOLLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_DRUCKER_PROTOKOLLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DRUCKER_PROTOKOLLE").getBigDecimalValue();
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
		public String get_PRINT_PROTOCOLS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRINT_PROTOCOLS");
	}

	public String get_PRINT_PROTOCOLS_cF() throws myException
	{
		return this.get_FormatedValue("PRINT_PROTOCOLS");	
	}

	public String get_PRINT_PROTOCOLS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRINT_PROTOCOLS");
	}

	public String get_PRINT_PROTOCOLS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRINT_PROTOCOLS",cNotNullValue);
	}

	public String get_PRINT_PROTOCOLS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRINT_PROTOCOLS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRINT_PROTOCOLS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRINT_PROTOCOLS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRINT_PROTOCOLS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_PROTOCOLS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_PROTOCOLS", calNewValueFormated);
	}
		public boolean is_PRINT_PROTOCOLS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_PROTOCOLS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRINT_PROTOCOLS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_PROTOCOLS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STAMP_IMPORTED_WITH_DEBUGFLAGS_cUF() throws myException
	{
		return this.get_UnFormatedValue("STAMP_IMPORTED_WITH_DEBUGFLAGS");
	}

	public String get_STAMP_IMPORTED_WITH_DEBUGFLAGS_cF() throws myException
	{
		return this.get_FormatedValue("STAMP_IMPORTED_WITH_DEBUGFLAGS");	
	}

	public String get_STAMP_IMPORTED_WITH_DEBUGFLAGS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STAMP_IMPORTED_WITH_DEBUGFLAGS");
	}

	public String get_STAMP_IMPORTED_WITH_DEBUGFLAGS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STAMP_IMPORTED_WITH_DEBUGFLAGS",cNotNullValue);
	}

	public String get_STAMP_IMPORTED_WITH_DEBUGFLAGS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STAMP_IMPORTED_WITH_DEBUGFLAGS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMP_IMPORTED_WITH_DEBUGFLAGS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STAMP_IMPORTED_WITH_DEBUGFLAGS", calNewValueFormated);
	}
		public boolean is_STAMP_IMPORTED_WITH_DEBUGFLAGS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STAMP_IMPORTED_WITH_DEBUGFLAGS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STAMP_IMPORTED_WITH_DEBUGFLAGS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STAMP_IMPORTED_WITH_DEBUGFLAGS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
	
	


	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("CORRECT_DATES",MyRECORD.DATATYPES.YESNO);
	put("DATEV_BERATERNUMMER",MyRECORD.DATATYPES.TEXT);
	put("DATEV_GESCHAEFTSJAHRESBEGINN",MyRECORD.DATATYPES.DATE);
	put("DATEV_MANDANTNUMMER",MyRECORD.DATATYPES.TEXT);
	put("DESCRIPTION",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EXPORTS_STARTING_DATE",MyRECORD.DATATYPES.DATE);
	put("EXPORTS_STARTING_ID",MyRECORD.DATATYPES.NUMBER);
	put("EXPORT_DIR",MyRECORD.DATATYPES.TEXT);
	put("FLUSH_TABLES",MyRECORD.DATATYPES.YESNO);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_DATEV_PROFILE",MyRECORD.DATATYPES.NUMBER);
	put("ID_DRUCKER",MyRECORD.DATATYPES.NUMBER);
	put("ID_DRUCKER_PROTOKOLLE",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("PRINT_PROTOCOLS",MyRECORD.DATATYPES.YESNO);
	put("STAMP_IMPORTED_WITH_DEBUGFLAGS",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_DATEV_PROFILE.HM_FIELDS;	
	}

}
