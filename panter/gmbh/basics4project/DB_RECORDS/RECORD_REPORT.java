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

public class RECORD_REPORT extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_REPORT";
    public static String IDFIELD   = "ID_REPORT";
    

	//erzeugen eines RECORDNEW_JT_REPORT - felds
	private RECORDNEW_REPORT   recNEW = null;

    private _TAB  tab = _TAB.report;  



	public RECORD_REPORT() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_REPORT");
	}


	public RECORD_REPORT(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORT");
	}



	public RECORD_REPORT(RECORD_REPORT recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT.TABLENAME);
	}


	//2014-04-03
	public RECORD_REPORT(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORT");
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT.TABLENAME);
	}




	public RECORD_REPORT(long lID_Unformated) throws myException
	{
		super("JT_REPORT","ID_REPORT",""+lID_Unformated);
		this.set_TABLE_NAME("JT_REPORT");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT.TABLENAME);
	}

	public RECORD_REPORT(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_REPORT");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT", "ID_REPORT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT.TABLENAME);
	}
	
	

	public RECORD_REPORT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_REPORT","ID_REPORT",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_REPORT");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT.TABLENAME);

	}


	public RECORD_REPORT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORT");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT", "ID_REPORT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_REPORT();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_REPORT.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_REPORT";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_REPORT_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_REPORT_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORT", bibE2.cTO(), "ID_REPORT="+this.get_ID_REPORT_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORT", bibE2.cTO(), "ID_REPORT="+this.get_ID_REPORT_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_REPORT WHERE ID_REPORT="+this.get_ID_REPORT_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_REPORT WHERE ID_REPORT="+this.get_ID_REPORT_cUF();
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
			if (S.isFull(this.get_ID_REPORT_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT", "ID_REPORT="+this.get_ID_REPORT_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_REPORT get_RECORDNEW_REPORT() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_REPORT();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_REPORT(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_REPORT create_Instance() throws myException {
		return new RECORD_REPORT();
	}
	
	public static RECORD_REPORT create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_REPORT(Conn);
    }

	public static RECORD_REPORT create_Instance(RECORD_REPORT recordOrig) {
		return new RECORD_REPORT(recordOrig);
    }

	public static RECORD_REPORT create_Instance(long lID_Unformated) throws myException {
		return new RECORD_REPORT(lID_Unformated);
    }

	public static RECORD_REPORT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_REPORT(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_REPORT create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_REPORT(lID_Unformated, Conn);
	}

	public static RECORD_REPORT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_REPORT(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_REPORT create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_REPORT(recordOrig);	    
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
    public RECORD_REPORT build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_REPORT WHERE ID_REPORT="+this.get_ID_REPORT_cUF());
      }
      //return new RECORD_REPORT(this.get_cSQL_4_Build());
      RECORD_REPORT rec = new RECORD_REPORT();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_REPORT
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_REPORT-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_REPORT get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_REPORT_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_REPORT  recNew = new RECORDNEW_REPORT();
		
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
    public RECORD_REPORT set_recordNew(RECORDNEW_REPORT recnew) throws myException {
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
	
	



		private RECLIST_REPORT_PARAMETER DOWN_RECLIST_REPORT_PARAMETER_id_report = null ;






	/**
	 * References the Field ID_REPORT 
	 * Falls keine get_ID_REPORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PARAMETER get_DOWN_RECORD_LIST_REPORT_PARAMETER_id_report() throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PARAMETER_id_report==null)
		{
			this.DOWN_RECLIST_REPORT_PARAMETER_id_report = new RECLIST_REPORT_PARAMETER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PARAMETER WHERE ID_REPORT="+this.get_ID_REPORT_cUF()+" ORDER BY ID_REPORT_PARAMETER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PARAMETER_id_report;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_REPORT 
	 * Falls keine get_ID_REPORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PARAMETER get_DOWN_RECORD_LIST_REPORT_PARAMETER_id_report(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PARAMETER_id_report==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PARAMETER WHERE ID_REPORT="+this.get_ID_REPORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REPORT_PARAMETER_id_report = new RECLIST_REPORT_PARAMETER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PARAMETER_id_report;
	}


	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__ALLOW_EMAIL_EMPLOYES = "ALLOW_EMAIL_EMPLOYES";
	public static String FIELD__ALLOW_EMAIL_FREE = "ALLOW_EMAIL_FREE";
	public static String FIELD__ALLOW_EMAIL_SEARCH_CUSTOMER = "ALLOW_EMAIL_SEARCH_CUSTOMER";
	public static String FIELD__ALLOW_HTML = "ALLOW_HTML";
	public static String FIELD__ALLOW_PDF = "ALLOW_PDF";
	public static String FIELD__ALLOW_TXT = "ALLOW_TXT";
	public static String FIELD__ALLOW_XLS = "ALLOW_XLS";
	public static String FIELD__ALLOW_XML = "ALLOW_XML";
	public static String FIELD__BESCHREIBUNG = "BESCHREIBUNG";
	public static String FIELD__BUTTONTEXT = "BUTTONTEXT";
	public static String FIELD__BUTTON_AUTH_KENNER = "BUTTON_AUTH_KENNER";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GESCHAEFTSFUEHRER = "GESCHAEFTSFUEHRER";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_REPORT = "ID_REPORT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MODULE_KENNER = "MODULE_KENNER";
	public static String FIELD__NAME_OF_REPORTFILE = "NAME_OF_REPORTFILE";
	public static String FIELD__PASSWORD = "PASSWORD";
	public static String FIELD__PASS_MULTI_ID = "PASS_MULTI_ID";
	public static String FIELD__PASS_NO_ID = "PASS_NO_ID";
	public static String FIELD__PASS_SINGLE_ID = "PASS_SINGLE_ID";
	public static String FIELD__PREFER_HTML = "PREFER_HTML";
	public static String FIELD__PREFER_PDF = "PREFER_PDF";
	public static String FIELD__PREFER_TXT = "PREFER_TXT";
	public static String FIELD__PREFER_XLS = "PREFER_XLS";
	public static String FIELD__PREFER_XML = "PREFER_XML";
	public static String FIELD__STATIC_MAIL_ADRESSES = "STATIC_MAIL_ADRESSES";
	public static String FIELD__SUPERVISOR = "SUPERVISOR";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TITEL = "TITEL";


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
		public String get_ALLOW_EMAIL_EMPLOYES_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EMAIL_EMPLOYES");
	}

	public String get_ALLOW_EMAIL_EMPLOYES_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_EMAIL_EMPLOYES");	
	}

	public String get_ALLOW_EMAIL_EMPLOYES_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_EMAIL_EMPLOYES");
	}

	public String get_ALLOW_EMAIL_EMPLOYES_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EMAIL_EMPLOYES",cNotNullValue);
	}

	public String get_ALLOW_EMAIL_EMPLOYES_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_EMAIL_EMPLOYES",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EMAIL_EMPLOYES(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_EMPLOYES_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_EMPLOYES", calNewValueFormated);
	}
		public boolean is_ALLOW_EMAIL_EMPLOYES_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EMAIL_EMPLOYES_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_EMAIL_EMPLOYES_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EMAIL_EMPLOYES_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_EMAIL_FREE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EMAIL_FREE");
	}

	public String get_ALLOW_EMAIL_FREE_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_EMAIL_FREE");	
	}

	public String get_ALLOW_EMAIL_FREE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_EMAIL_FREE");
	}

	public String get_ALLOW_EMAIL_FREE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EMAIL_FREE",cNotNullValue);
	}

	public String get_ALLOW_EMAIL_FREE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_EMAIL_FREE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_EMAIL_FREE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EMAIL_FREE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_EMAIL_FREE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_FREE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_FREE", calNewValueFormated);
	}
		public boolean is_ALLOW_EMAIL_FREE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EMAIL_FREE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_EMAIL_FREE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EMAIL_FREE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_EMAIL_SEARCH_CUSTOMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EMAIL_SEARCH_CUSTOMER");
	}

	public String get_ALLOW_EMAIL_SEARCH_CUSTOMER_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_EMAIL_SEARCH_CUSTOMER");	
	}

	public String get_ALLOW_EMAIL_SEARCH_CUSTOMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_EMAIL_SEARCH_CUSTOMER");
	}

	public String get_ALLOW_EMAIL_SEARCH_CUSTOMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_EMAIL_SEARCH_CUSTOMER",cNotNullValue);
	}

	public String get_ALLOW_EMAIL_SEARCH_CUSTOMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_EMAIL_SEARCH_CUSTOMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_EMAIL_SEARCH_CUSTOMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_EMAIL_SEARCH_CUSTOMER", calNewValueFormated);
	}
		public boolean is_ALLOW_EMAIL_SEARCH_CUSTOMER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EMAIL_SEARCH_CUSTOMER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_EMAIL_SEARCH_CUSTOMER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_EMAIL_SEARCH_CUSTOMER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_HTML_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_HTML");
	}

	public String get_ALLOW_HTML_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_HTML");	
	}

	public String get_ALLOW_HTML_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_HTML");
	}

	public String get_ALLOW_HTML_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_HTML",cNotNullValue);
	}

	public String get_ALLOW_HTML_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_HTML",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_HTML", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_HTML(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_HTML", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_HTML", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_HTML", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_HTML", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_HTML_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_HTML", calNewValueFormated);
	}
		public boolean is_ALLOW_HTML_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_HTML_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_HTML_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_HTML_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_PDF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_PDF");
	}

	public String get_ALLOW_PDF_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_PDF");	
	}

	public String get_ALLOW_PDF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_PDF");
	}

	public String get_ALLOW_PDF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_PDF",cNotNullValue);
	}

	public String get_ALLOW_PDF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_PDF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_PDF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_PDF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_PDF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_PDF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_PDF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_PDF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_PDF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_PDF", calNewValueFormated);
	}
		public boolean is_ALLOW_PDF_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_PDF_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_PDF_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_PDF_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_TXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_TXT");
	}

	public String get_ALLOW_TXT_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_TXT");	
	}

	public String get_ALLOW_TXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_TXT");
	}

	public String get_ALLOW_TXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_TXT",cNotNullValue);
	}

	public String get_ALLOW_TXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_TXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_TXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_TXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_TXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_TXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_TXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_TXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_TXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_TXT", calNewValueFormated);
	}
		public boolean is_ALLOW_TXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_TXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_TXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_TXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_XLS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_XLS");
	}

	public String get_ALLOW_XLS_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_XLS");	
	}

	public String get_ALLOW_XLS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_XLS");
	}

	public String get_ALLOW_XLS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_XLS",cNotNullValue);
	}

	public String get_ALLOW_XLS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_XLS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_XLS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_XLS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_XLS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_XLS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_XLS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_XLS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XLS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_XLS", calNewValueFormated);
	}
		public boolean is_ALLOW_XLS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_XLS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_XLS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_XLS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ALLOW_XML_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_XML");
	}

	public String get_ALLOW_XML_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_XML");	
	}

	public String get_ALLOW_XML_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_XML");
	}

	public String get_ALLOW_XML_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_XML",cNotNullValue);
	}

	public String get_ALLOW_XML_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_XML",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_XML", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_XML(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_XML", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_XML", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_XML", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_XML", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_XML_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_XML", calNewValueFormated);
	}
		public boolean is_ALLOW_XML_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_XML_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_XML_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_XML_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_BUTTONTEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUTTONTEXT");
	}

	public String get_BUTTONTEXT_cF() throws myException
	{
		return this.get_FormatedValue("BUTTONTEXT");	
	}

	public String get_BUTTONTEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUTTONTEXT");
	}

	public String get_BUTTONTEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUTTONTEXT",cNotNullValue);
	}

	public String get_BUTTONTEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUTTONTEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUTTONTEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUTTONTEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUTTONTEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUTTONTEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONTEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONTEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONTEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONTEXT", calNewValueFormated);
	}
		public String get_BUTTON_AUTH_KENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUTTON_AUTH_KENNER");
	}

	public String get_BUTTON_AUTH_KENNER_cF() throws myException
	{
		return this.get_FormatedValue("BUTTON_AUTH_KENNER");	
	}

	public String get_BUTTON_AUTH_KENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUTTON_AUTH_KENNER");
	}

	public String get_BUTTON_AUTH_KENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUTTON_AUTH_KENNER",cNotNullValue);
	}

	public String get_BUTTON_AUTH_KENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUTTON_AUTH_KENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUTTON_AUTH_KENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUTTON_AUTH_KENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUTTON_AUTH_KENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTON_AUTH_KENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTON_AUTH_KENNER", calNewValueFormated);
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
		public String get_GESCHAEFTSFUEHRER_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESCHAEFTSFUEHRER");
	}

	public String get_GESCHAEFTSFUEHRER_cF() throws myException
	{
		return this.get_FormatedValue("GESCHAEFTSFUEHRER");	
	}

	public String get_GESCHAEFTSFUEHRER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHAEFTSFUEHRER");
	}

	public String get_GESCHAEFTSFUEHRER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESCHAEFTSFUEHRER",cNotNullValue);
	}

	public String get_GESCHAEFTSFUEHRER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESCHAEFTSFUEHRER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", calNewValueFormated);
	}
		public boolean is_GESCHAEFTSFUEHRER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHAEFTSFUEHRER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GESCHAEFTSFUEHRER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHAEFTSFUEHRER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
	
	
	public String get_ID_REPORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT");
	}

	public String get_ID_REPORT_cF() throws myException
	{
		return this.get_FormatedValue("ID_REPORT");	
	}

	public String get_ID_REPORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_REPORT");
	}

	public String get_ID_REPORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT",cNotNullValue);
	}

	public String get_ID_REPORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_REPORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_REPORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_REPORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_REPORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT", calNewValueFormated);
	}
		public Long get_ID_REPORT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_REPORT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_REPORT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_REPORT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_REPORT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_REPORT").getDoubleValue();
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
	public String get_ID_REPORT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_REPORT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_REPORT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_REPORT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT").getBigDecimalValue();
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
		public String get_MODULE_KENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MODULE_KENNER");
	}

	public String get_MODULE_KENNER_cF() throws myException
	{
		return this.get_FormatedValue("MODULE_KENNER");	
	}

	public String get_MODULE_KENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MODULE_KENNER");
	}

	public String get_MODULE_KENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MODULE_KENNER",cNotNullValue);
	}

	public String get_MODULE_KENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MODULE_KENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULE_KENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULE_KENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULE_KENNER", calNewValueFormated);
	}
		public String get_NAME_OF_REPORTFILE_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME_OF_REPORTFILE");
	}

	public String get_NAME_OF_REPORTFILE_cF() throws myException
	{
		return this.get_FormatedValue("NAME_OF_REPORTFILE");	
	}

	public String get_NAME_OF_REPORTFILE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME_OF_REPORTFILE");
	}

	public String get_NAME_OF_REPORTFILE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME_OF_REPORTFILE",cNotNullValue);
	}

	public String get_NAME_OF_REPORTFILE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME_OF_REPORTFILE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME_OF_REPORTFILE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME_OF_REPORTFILE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME_OF_REPORTFILE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_REPORTFILE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_OF_REPORTFILE", calNewValueFormated);
	}
		public String get_PASSWORD_cUF() throws myException
	{
		return this.get_UnFormatedValue("PASSWORD");
	}

	public String get_PASSWORD_cF() throws myException
	{
		return this.get_FormatedValue("PASSWORD");	
	}

	public String get_PASSWORD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PASSWORD");
	}

	public String get_PASSWORD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PASSWORD",cNotNullValue);
	}

	public String get_PASSWORD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PASSWORD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PASSWORD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PASSWORD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PASSWORD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PASSWORD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PASSWORD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASSWORD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASSWORD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASSWORD", calNewValueFormated);
	}
		public String get_PASS_MULTI_ID_cUF() throws myException
	{
		return this.get_UnFormatedValue("PASS_MULTI_ID");
	}

	public String get_PASS_MULTI_ID_cF() throws myException
	{
		return this.get_FormatedValue("PASS_MULTI_ID");	
	}

	public String get_PASS_MULTI_ID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PASS_MULTI_ID");
	}

	public String get_PASS_MULTI_ID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PASS_MULTI_ID",cNotNullValue);
	}

	public String get_PASS_MULTI_ID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PASS_MULTI_ID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PASS_MULTI_ID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PASS_MULTI_ID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PASS_MULTI_ID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_MULTI_ID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_MULTI_ID", calNewValueFormated);
	}
		public boolean is_PASS_MULTI_ID_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PASS_MULTI_ID_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PASS_MULTI_ID_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PASS_MULTI_ID_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PASS_NO_ID_cUF() throws myException
	{
		return this.get_UnFormatedValue("PASS_NO_ID");
	}

	public String get_PASS_NO_ID_cF() throws myException
	{
		return this.get_FormatedValue("PASS_NO_ID");	
	}

	public String get_PASS_NO_ID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PASS_NO_ID");
	}

	public String get_PASS_NO_ID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PASS_NO_ID",cNotNullValue);
	}

	public String get_PASS_NO_ID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PASS_NO_ID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PASS_NO_ID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PASS_NO_ID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PASS_NO_ID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PASS_NO_ID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_NO_ID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_NO_ID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_NO_ID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_NO_ID", calNewValueFormated);
	}
		public boolean is_PASS_NO_ID_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PASS_NO_ID_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PASS_NO_ID_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PASS_NO_ID_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PASS_SINGLE_ID_cUF() throws myException
	{
		return this.get_UnFormatedValue("PASS_SINGLE_ID");
	}

	public String get_PASS_SINGLE_ID_cF() throws myException
	{
		return this.get_FormatedValue("PASS_SINGLE_ID");	
	}

	public String get_PASS_SINGLE_ID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PASS_SINGLE_ID");
	}

	public String get_PASS_SINGLE_ID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PASS_SINGLE_ID",cNotNullValue);
	}

	public String get_PASS_SINGLE_ID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PASS_SINGLE_ID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PASS_SINGLE_ID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PASS_SINGLE_ID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PASS_SINGLE_ID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASS_SINGLE_ID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASS_SINGLE_ID", calNewValueFormated);
	}
		public boolean is_PASS_SINGLE_ID_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PASS_SINGLE_ID_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PASS_SINGLE_ID_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PASS_SINGLE_ID_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PREFER_HTML_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREFER_HTML");
	}

	public String get_PREFER_HTML_cF() throws myException
	{
		return this.get_FormatedValue("PREFER_HTML");	
	}

	public String get_PREFER_HTML_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREFER_HTML");
	}

	public String get_PREFER_HTML_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREFER_HTML",cNotNullValue);
	}

	public String get_PREFER_HTML_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREFER_HTML",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREFER_HTML", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREFER_HTML(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREFER_HTML", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREFER_HTML", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_HTML", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_HTML", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_HTML_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_HTML", calNewValueFormated);
	}
		public boolean is_PREFER_HTML_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_HTML_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREFER_HTML_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_HTML_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PREFER_PDF_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREFER_PDF");
	}

	public String get_PREFER_PDF_cF() throws myException
	{
		return this.get_FormatedValue("PREFER_PDF");	
	}

	public String get_PREFER_PDF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREFER_PDF");
	}

	public String get_PREFER_PDF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREFER_PDF",cNotNullValue);
	}

	public String get_PREFER_PDF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREFER_PDF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREFER_PDF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREFER_PDF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREFER_PDF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREFER_PDF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_PDF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_PDF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_PDF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_PDF", calNewValueFormated);
	}
		public boolean is_PREFER_PDF_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_PDF_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREFER_PDF_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_PDF_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PREFER_TXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREFER_TXT");
	}

	public String get_PREFER_TXT_cF() throws myException
	{
		return this.get_FormatedValue("PREFER_TXT");	
	}

	public String get_PREFER_TXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREFER_TXT");
	}

	public String get_PREFER_TXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREFER_TXT",cNotNullValue);
	}

	public String get_PREFER_TXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREFER_TXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREFER_TXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREFER_TXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREFER_TXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREFER_TXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_TXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_TXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_TXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_TXT", calNewValueFormated);
	}
		public boolean is_PREFER_TXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_TXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREFER_TXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_TXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PREFER_XLS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREFER_XLS");
	}

	public String get_PREFER_XLS_cF() throws myException
	{
		return this.get_FormatedValue("PREFER_XLS");	
	}

	public String get_PREFER_XLS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREFER_XLS");
	}

	public String get_PREFER_XLS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREFER_XLS",cNotNullValue);
	}

	public String get_PREFER_XLS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREFER_XLS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREFER_XLS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREFER_XLS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREFER_XLS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREFER_XLS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_XLS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_XLS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XLS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_XLS", calNewValueFormated);
	}
		public boolean is_PREFER_XLS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_XLS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREFER_XLS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_XLS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PREFER_XML_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREFER_XML");
	}

	public String get_PREFER_XML_cF() throws myException
	{
		return this.get_FormatedValue("PREFER_XML");	
	}

	public String get_PREFER_XML_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREFER_XML");
	}

	public String get_PREFER_XML_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREFER_XML",cNotNullValue);
	}

	public String get_PREFER_XML_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREFER_XML",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREFER_XML", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREFER_XML(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREFER_XML", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREFER_XML", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_XML", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_XML", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREFER_XML_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREFER_XML", calNewValueFormated);
	}
		public boolean is_PREFER_XML_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_XML_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREFER_XML_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREFER_XML_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STATIC_MAIL_ADRESSES_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATIC_MAIL_ADRESSES");
	}

	public String get_STATIC_MAIL_ADRESSES_cF() throws myException
	{
		return this.get_FormatedValue("STATIC_MAIL_ADRESSES");	
	}

	public String get_STATIC_MAIL_ADRESSES_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATIC_MAIL_ADRESSES");
	}

	public String get_STATIC_MAIL_ADRESSES_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATIC_MAIL_ADRESSES",cNotNullValue);
	}

	public String get_STATIC_MAIL_ADRESSES_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATIC_MAIL_ADRESSES",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATIC_MAIL_ADRESSES(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATIC_MAIL_ADRESSES", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATIC_MAIL_ADRESSES_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATIC_MAIL_ADRESSES", calNewValueFormated);
	}
		public String get_SUPERVISOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("SUPERVISOR");
	}

	public String get_SUPERVISOR_cF() throws myException
	{
		return this.get_FormatedValue("SUPERVISOR");	
	}

	public String get_SUPERVISOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SUPERVISOR");
	}

	public String get_SUPERVISOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SUPERVISOR",cNotNullValue);
	}

	public String get_SUPERVISOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SUPERVISOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SUPERVISOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SUPERVISOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SUPERVISOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SUPERVISOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUPERVISOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUPERVISOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUPERVISOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUPERVISOR", calNewValueFormated);
	}
		public boolean is_SUPERVISOR_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SUPERVISOR_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SUPERVISOR_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SUPERVISOR_cUF_NN("N").equals("N"))
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
	
	
	public String get_TITEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("TITEL");
	}

	public String get_TITEL_cF() throws myException
	{
		return this.get_FormatedValue("TITEL");	
	}

	public String get_TITEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TITEL");
	}

	public String get_TITEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TITEL",cNotNullValue);
	}

	public String get_TITEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TITEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TITEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TITEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TITEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TITEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITEL", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_EMAIL_EMPLOYES",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_EMAIL_FREE",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_EMAIL_SEARCH_CUSTOMER",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_HTML",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_PDF",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_TXT",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_XLS",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_XML",MyRECORD.DATATYPES.YESNO);
	put("BESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("BUTTONTEXT",MyRECORD.DATATYPES.TEXT);
	put("BUTTON_AUTH_KENNER",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GESCHAEFTSFUEHRER",MyRECORD.DATATYPES.YESNO);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_REPORT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MODULE_KENNER",MyRECORD.DATATYPES.TEXT);
	put("NAME_OF_REPORTFILE",MyRECORD.DATATYPES.TEXT);
	put("PASSWORD",MyRECORD.DATATYPES.TEXT);
	put("PASS_MULTI_ID",MyRECORD.DATATYPES.YESNO);
	put("PASS_NO_ID",MyRECORD.DATATYPES.YESNO);
	put("PASS_SINGLE_ID",MyRECORD.DATATYPES.YESNO);
	put("PREFER_HTML",MyRECORD.DATATYPES.YESNO);
	put("PREFER_PDF",MyRECORD.DATATYPES.YESNO);
	put("PREFER_TXT",MyRECORD.DATATYPES.YESNO);
	put("PREFER_XLS",MyRECORD.DATATYPES.YESNO);
	put("PREFER_XML",MyRECORD.DATATYPES.YESNO);
	put("STATIC_MAIL_ADRESSES",MyRECORD.DATATYPES.TEXT);
	put("SUPERVISOR",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TITEL",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_REPORT.HM_FIELDS;	
	}

}
