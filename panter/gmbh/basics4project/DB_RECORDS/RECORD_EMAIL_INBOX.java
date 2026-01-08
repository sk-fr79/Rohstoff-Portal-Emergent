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

public class RECORD_EMAIL_INBOX extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_EMAIL_INBOX";
    public static String IDFIELD   = "ID_EMAIL_INBOX";
    

	//erzeugen eines RECORDNEW_JT_EMAIL_INBOX - felds
	private RECORDNEW_EMAIL_INBOX   recNEW = null;

    private _TAB  tab = _TAB.email_inbox;  



	public RECORD_EMAIL_INBOX() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_EMAIL_INBOX");
	}


	public RECORD_EMAIL_INBOX(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EMAIL_INBOX");
	}



	public RECORD_EMAIL_INBOX(RECORD_EMAIL_INBOX recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EMAIL_INBOX");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_INBOX.TABLENAME);
	}


	//2014-04-03
	public RECORD_EMAIL_INBOX(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EMAIL_INBOX");
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_INBOX.TABLENAME);
	}




	public RECORD_EMAIL_INBOX(long lID_Unformated) throws myException
	{
		super("JT_EMAIL_INBOX","ID_EMAIL_INBOX",""+lID_Unformated);
		this.set_TABLE_NAME("JT_EMAIL_INBOX");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_INBOX.TABLENAME);
	}

	public RECORD_EMAIL_INBOX(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_EMAIL_INBOX");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_INBOX", "ID_EMAIL_INBOX="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_INBOX", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_INBOX.TABLENAME);
	}
	
	

	public RECORD_EMAIL_INBOX(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_EMAIL_INBOX","ID_EMAIL_INBOX",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_EMAIL_INBOX");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_INBOX.TABLENAME);

	}


	public RECORD_EMAIL_INBOX(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EMAIL_INBOX");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_INBOX", "ID_EMAIL_INBOX="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_INBOX", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_INBOX.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_EMAIL_INBOX();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_EMAIL_INBOX.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_EMAIL_INBOX";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_EMAIL_INBOX_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_EMAIL_INBOX_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EMAIL_INBOX was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EMAIL_INBOX", bibE2.cTO(), "ID_EMAIL_INBOX="+this.get_ID_EMAIL_INBOX_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EMAIL_INBOX was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EMAIL_INBOX", bibE2.cTO(), "ID_EMAIL_INBOX="+this.get_ID_EMAIL_INBOX_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_EMAIL_INBOX WHERE ID_EMAIL_INBOX="+this.get_ID_EMAIL_INBOX_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_EMAIL_INBOX WHERE ID_EMAIL_INBOX="+this.get_ID_EMAIL_INBOX_cUF();
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
			if (S.isFull(this.get_ID_EMAIL_INBOX_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_INBOX", "ID_EMAIL_INBOX="+this.get_ID_EMAIL_INBOX_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_EMAIL_INBOX get_RECORDNEW_EMAIL_INBOX() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_EMAIL_INBOX();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_EMAIL_INBOX(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_EMAIL_INBOX create_Instance() throws myException {
		return new RECORD_EMAIL_INBOX();
	}
	
	public static RECORD_EMAIL_INBOX create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_EMAIL_INBOX(Conn);
    }

	public static RECORD_EMAIL_INBOX create_Instance(RECORD_EMAIL_INBOX recordOrig) {
		return new RECORD_EMAIL_INBOX(recordOrig);
    }

	public static RECORD_EMAIL_INBOX create_Instance(long lID_Unformated) throws myException {
		return new RECORD_EMAIL_INBOX(lID_Unformated);
    }

	public static RECORD_EMAIL_INBOX create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_EMAIL_INBOX(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_EMAIL_INBOX create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_EMAIL_INBOX(lID_Unformated, Conn);
	}

	public static RECORD_EMAIL_INBOX create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_EMAIL_INBOX(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_EMAIL_INBOX create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_EMAIL_INBOX(recordOrig);	    
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
    public RECORD_EMAIL_INBOX build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_INBOX WHERE ID_EMAIL_INBOX="+this.get_ID_EMAIL_INBOX_cUF());
      }
      //return new RECORD_EMAIL_INBOX(this.get_cSQL_4_Build());
      RECORD_EMAIL_INBOX rec = new RECORD_EMAIL_INBOX();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_EMAIL_INBOX
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_EMAIL_INBOX-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_EMAIL_INBOX get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_EMAIL_INBOX_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_EMAIL_INBOX  recNew = new RECORDNEW_EMAIL_INBOX();
		
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
    public RECORD_EMAIL_INBOX set_recordNew(RECORDNEW_EMAIL_INBOX recnew) throws myException {
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
	
	



		private RECORD_USER UP_RECORD_USER_id_user_gelesen = null;






	
	/**
	 * References the Field ID_USER_GELESEN
	 * Falls keine this.get_ID_USER_GELESEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_gelesen() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_GELESEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_gelesen==null)
		{
			this.UP_RECORD_USER_id_user_gelesen = new RECORD_USER(this.get_ID_USER_GELESEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_gelesen;
	}
	

	public static String FIELD__DATE_RECEIVED = "DATE_RECEIVED";
	public static String FIELD__DATE_SEND = "DATE_SEND";
	public static String FIELD__DELETED = "DELETED";
	public static String FIELD__DEL_DATE = "DEL_DATE";
	public static String FIELD__DEL_KUERZEL = "DEL_KUERZEL";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GELESEN_AM = "GELESEN_AM";
	public static String FIELD__ID_ADRESSE_ZUGEORDNET = "ID_ADRESSE_ZUGEORDNET";
	public static String FIELD__ID_EMAIL_INBOX = "ID_EMAIL_INBOX";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_USER_GELESEN = "ID_USER_GELESEN";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MAIL_ACCOUNT = "MAIL_ACCOUNT";
	public static String FIELD__MAIL_CC = "MAIL_CC";
	public static String FIELD__MAIL_FOLDER = "MAIL_FOLDER";
	public static String FIELD__MAIL_FROM = "MAIL_FROM";
	public static String FIELD__MAIL_HOST = "MAIL_HOST";
	public static String FIELD__MAIL_TO = "MAIL_TO";
	public static String FIELD__MAIL_UID = "MAIL_UID";
	public static String FIELD__MESSAGE_ID = "MESSAGE_ID";
	public static String FIELD__MESSAGE_TEXT = "MESSAGE_TEXT";
	public static String FIELD__SUBJECT = "SUBJECT";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_DATE_RECEIVED_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATE_RECEIVED");
	}

	public String get_DATE_RECEIVED_cF() throws myException
	{
		return this.get_FormatedValue("DATE_RECEIVED");	
	}

	public String get_DATE_RECEIVED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATE_RECEIVED");
	}

	public String get_DATE_RECEIVED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATE_RECEIVED",cNotNullValue);
	}

	public String get_DATE_RECEIVED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATE_RECEIVED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATE_RECEIVED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATE_RECEIVED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATE_RECEIVED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATE_RECEIVED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATE_RECEIVED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATE_RECEIVED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_RECEIVED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATE_RECEIVED", calNewValueFormated);
	}
		public String get_DATE_SEND_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATE_SEND");
	}

	public String get_DATE_SEND_cF() throws myException
	{
		return this.get_FormatedValue("DATE_SEND");	
	}

	public String get_DATE_SEND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATE_SEND");
	}

	public String get_DATE_SEND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATE_SEND",cNotNullValue);
	}

	public String get_DATE_SEND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATE_SEND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATE_SEND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATE_SEND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATE_SEND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATE_SEND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATE_SEND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATE_SEND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATE_SEND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATE_SEND", calNewValueFormated);
	}
		public String get_DELETED_cUF() throws myException
	{
		return this.get_UnFormatedValue("DELETED");
	}

	public String get_DELETED_cF() throws myException
	{
		return this.get_FormatedValue("DELETED");	
	}

	public String get_DELETED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DELETED");
	}

	public String get_DELETED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DELETED",cNotNullValue);
	}

	public String get_DELETED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DELETED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DELETED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DELETED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DELETED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", calNewValueFormated);
	}
		public boolean is_DELETED_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DELETED_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DELETED_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DELETED_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DEL_DATE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_DATE");
	}

	public String get_DEL_DATE_cF() throws myException
	{
		return this.get_FormatedValue("DEL_DATE");	
	}

	public String get_DEL_DATE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_DATE");
	}

	public String get_DEL_DATE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_DATE",cNotNullValue);
	}

	public String get_DEL_DATE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_DATE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_DATE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", calNewValueFormated);
	}
		public String get_DEL_KUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_KUERZEL");
	}

	public String get_DEL_KUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("DEL_KUERZEL");	
	}

	public String get_DEL_KUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_KUERZEL");
	}

	public String get_DEL_KUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_KUERZEL",cNotNullValue);
	}

	public String get_DEL_KUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_KUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", calNewValueFormated);
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
		public String get_GELESEN_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("GELESEN_AM");
	}

	public String get_GELESEN_AM_cF() throws myException
	{
		return this.get_FormatedValue("GELESEN_AM");	
	}

	public String get_GELESEN_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GELESEN_AM");
	}

	public String get_GELESEN_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GELESEN_AM",cNotNullValue);
	}

	public String get_GELESEN_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GELESEN_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GELESEN_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GELESEN_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GELESEN_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GELESEN_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELESEN_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELESEN_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELESEN_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELESEN_AM", calNewValueFormated);
	}
		public String get_ID_ADRESSE_ZUGEORDNET_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_ZUGEORDNET");
	}

	public String get_ID_ADRESSE_ZUGEORDNET_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_ZUGEORDNET");	
	}

	public String get_ID_ADRESSE_ZUGEORDNET_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_ZUGEORDNET");
	}

	public String get_ID_ADRESSE_ZUGEORDNET_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_ZUGEORDNET",cNotNullValue);
	}

	public String get_ID_ADRESSE_ZUGEORDNET_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_ZUGEORDNET",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZUGEORDNET", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_ZUGEORDNET_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_ZUGEORDNET").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_ZUGEORDNET_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_ZUGEORDNET").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_ZUGEORDNET_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_ZUGEORDNET").getDoubleValue();
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
	public String get_ID_ADRESSE_ZUGEORDNET_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_ZUGEORDNET_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_ZUGEORDNET_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_ZUGEORDNET_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_ZUGEORDNET_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_ZUGEORDNET").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_ZUGEORDNET_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_ZUGEORDNET").getBigDecimalValue();
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
	
	
	public String get_ID_EMAIL_INBOX_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EMAIL_INBOX");
	}

	public String get_ID_EMAIL_INBOX_cF() throws myException
	{
		return this.get_FormatedValue("ID_EMAIL_INBOX");	
	}

	public String get_ID_EMAIL_INBOX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EMAIL_INBOX");
	}

	public String get_ID_EMAIL_INBOX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EMAIL_INBOX",cNotNullValue);
	}

	public String get_ID_EMAIL_INBOX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EMAIL_INBOX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EMAIL_INBOX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EMAIL_INBOX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EMAIL_INBOX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_INBOX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EMAIL_INBOX", calNewValueFormated);
	}
		public Long get_ID_EMAIL_INBOX_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EMAIL_INBOX").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EMAIL_INBOX_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EMAIL_INBOX").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EMAIL_INBOX_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EMAIL_INBOX").getDoubleValue();
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
	public String get_ID_EMAIL_INBOX_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EMAIL_INBOX_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EMAIL_INBOX_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EMAIL_INBOX_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EMAIL_INBOX_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EMAIL_INBOX").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EMAIL_INBOX_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EMAIL_INBOX").getBigDecimalValue();
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
	
	
	public String get_ID_USER_GELESEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_GELESEN");
	}

	public String get_ID_USER_GELESEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_GELESEN");	
	}

	public String get_ID_USER_GELESEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_GELESEN");
	}

	public String get_ID_USER_GELESEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_GELESEN",cNotNullValue);
	}

	public String get_ID_USER_GELESEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_GELESEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_GELESEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_GELESEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_GELESEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_GELESEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_GELESEN", calNewValueFormated);
	}
		public Long get_ID_USER_GELESEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_GELESEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_GELESEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_GELESEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_GELESEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_GELESEN").getDoubleValue();
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
	public String get_ID_USER_GELESEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_GELESEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_GELESEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_GELESEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_GELESEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_GELESEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_GELESEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_GELESEN").getBigDecimalValue();
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
		public String get_MAIL_ACCOUNT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_ACCOUNT");
	}

	public String get_MAIL_ACCOUNT_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_ACCOUNT");	
	}

	public String get_MAIL_ACCOUNT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_ACCOUNT");
	}

	public String get_MAIL_ACCOUNT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_ACCOUNT",cNotNullValue);
	}

	public String get_MAIL_ACCOUNT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_ACCOUNT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_ACCOUNT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_ACCOUNT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_ACCOUNT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_ACCOUNT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_ACCOUNT", calNewValueFormated);
	}
		public String get_MAIL_CC_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_CC");
	}

	public String get_MAIL_CC_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_CC");	
	}

	public String get_MAIL_CC_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_CC");
	}

	public String get_MAIL_CC_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_CC",cNotNullValue);
	}

	public String get_MAIL_CC_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_CC",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_CC", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_CC(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_CC", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_CC", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_CC", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_CC", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_CC_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_CC", calNewValueFormated);
	}
		public String get_MAIL_FOLDER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_FOLDER");
	}

	public String get_MAIL_FOLDER_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_FOLDER");	
	}

	public String get_MAIL_FOLDER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_FOLDER");
	}

	public String get_MAIL_FOLDER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_FOLDER",cNotNullValue);
	}

	public String get_MAIL_FOLDER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_FOLDER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_FOLDER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_FOLDER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_FOLDER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_FOLDER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_FOLDER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_FOLDER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FOLDER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_FOLDER", calNewValueFormated);
	}
		public String get_MAIL_FROM_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_FROM");
	}

	public String get_MAIL_FROM_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_FROM");	
	}

	public String get_MAIL_FROM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_FROM");
	}

	public String get_MAIL_FROM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_FROM",cNotNullValue);
	}

	public String get_MAIL_FROM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_FROM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_FROM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_FROM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_FROM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_FROM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_FROM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_FROM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_FROM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_FROM", calNewValueFormated);
	}
		public String get_MAIL_HOST_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_HOST");
	}

	public String get_MAIL_HOST_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_HOST");	
	}

	public String get_MAIL_HOST_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_HOST");
	}

	public String get_MAIL_HOST_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_HOST",cNotNullValue);
	}

	public String get_MAIL_HOST_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_HOST",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_HOST", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_HOST(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_HOST", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_HOST", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_HOST", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_HOST", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_HOST_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_HOST", calNewValueFormated);
	}
		public String get_MAIL_TO_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_TO");
	}

	public String get_MAIL_TO_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_TO");	
	}

	public String get_MAIL_TO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_TO");
	}

	public String get_MAIL_TO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_TO",cNotNullValue);
	}

	public String get_MAIL_TO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_TO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_TO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_TO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_TO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_TO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_TO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_TO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_TO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_TO", calNewValueFormated);
	}
		public String get_MAIL_UID_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_UID");
	}

	public String get_MAIL_UID_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_UID");	
	}

	public String get_MAIL_UID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_UID");
	}

	public String get_MAIL_UID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_UID",cNotNullValue);
	}

	public String get_MAIL_UID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_UID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_UID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_UID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_UID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_UID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_UID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_UID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_UID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_UID", calNewValueFormated);
	}
		public Long get_MAIL_UID_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MAIL_UID").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MAIL_UID_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MAIL_UID").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MAIL_UID_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MAIL_UID").getDoubleValue();
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
	public String get_MAIL_UID_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAIL_UID_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MAIL_UID_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAIL_UID_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MAIL_UID_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MAIL_UID").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MAIL_UID_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MAIL_UID").getBigDecimalValue();
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
	
	
	public String get_MESSAGE_ID_cUF() throws myException
	{
		return this.get_UnFormatedValue("MESSAGE_ID");
	}

	public String get_MESSAGE_ID_cF() throws myException
	{
		return this.get_FormatedValue("MESSAGE_ID");	
	}

	public String get_MESSAGE_ID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MESSAGE_ID");
	}

	public String get_MESSAGE_ID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MESSAGE_ID",cNotNullValue);
	}

	public String get_MESSAGE_ID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MESSAGE_ID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MESSAGE_ID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MESSAGE_ID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MESSAGE_ID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MESSAGE_ID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MESSAGE_ID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MESSAGE_ID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_ID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MESSAGE_ID", calNewValueFormated);
	}
		public String get_MESSAGE_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MESSAGE_TEXT");
	}

	public String get_MESSAGE_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("MESSAGE_TEXT");	
	}

	public String get_MESSAGE_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MESSAGE_TEXT");
	}

	public String get_MESSAGE_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MESSAGE_TEXT",cNotNullValue);
	}

	public String get_MESSAGE_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MESSAGE_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MESSAGE_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MESSAGE_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MESSAGE_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MESSAGE_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MESSAGE_TEXT", calNewValueFormated);
	}
		public String get_SUBJECT_cUF() throws myException
	{
		return this.get_UnFormatedValue("SUBJECT");
	}

	public String get_SUBJECT_cF() throws myException
	{
		return this.get_FormatedValue("SUBJECT");	
	}

	public String get_SUBJECT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SUBJECT");
	}

	public String get_SUBJECT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SUBJECT",cNotNullValue);
	}

	public String get_SUBJECT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SUBJECT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SUBJECT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SUBJECT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SUBJECT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SUBJECT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUBJECT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SUBJECT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUBJECT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUBJECT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUBJECT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUBJECT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUBJECT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUBJECT", calNewValueFormated);
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
	put("DATE_RECEIVED",MyRECORD.DATATYPES.DATE);
	put("DATE_SEND",MyRECORD.DATATYPES.DATE);
	put("DELETED",MyRECORD.DATATYPES.YESNO);
	put("DEL_DATE",MyRECORD.DATATYPES.DATE);
	put("DEL_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GELESEN_AM",MyRECORD.DATATYPES.DATE);
	put("ID_ADRESSE_ZUGEORDNET",MyRECORD.DATATYPES.NUMBER);
	put("ID_EMAIL_INBOX",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_GELESEN",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MAIL_ACCOUNT",MyRECORD.DATATYPES.TEXT);
	put("MAIL_CC",MyRECORD.DATATYPES.TEXT);
	put("MAIL_FOLDER",MyRECORD.DATATYPES.TEXT);
	put("MAIL_FROM",MyRECORD.DATATYPES.TEXT);
	put("MAIL_HOST",MyRECORD.DATATYPES.TEXT);
	put("MAIL_TO",MyRECORD.DATATYPES.TEXT);
	put("MAIL_UID",MyRECORD.DATATYPES.NUMBER);
	put("MESSAGE_ID",MyRECORD.DATATYPES.TEXT);
	put("MESSAGE_TEXT",MyRECORD.DATATYPES.TEXT);
	put("SUBJECT",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_EMAIL_INBOX.HM_FIELDS;	
	}

}
