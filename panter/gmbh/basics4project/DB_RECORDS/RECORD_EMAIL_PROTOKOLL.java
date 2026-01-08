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

public class RECORD_EMAIL_PROTOKOLL extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_EMAIL_PROTOKOLL";
    public static String IDFIELD   = "ID_EMAIL_PROTOKOLL";
    

	//erzeugen eines RECORDNEW_JT_EMAIL_PROTOKOLL - felds
	private RECORDNEW_EMAIL_PROTOKOLL   recNEW = null;

    private _TAB  tab = _TAB.email_protokoll;  



	public RECORD_EMAIL_PROTOKOLL() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");
	}


	public RECORD_EMAIL_PROTOKOLL(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");
	}



	public RECORD_EMAIL_PROTOKOLL(RECORD_EMAIL_PROTOKOLL recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_PROTOKOLL.TABLENAME);
	}


	//2014-04-03
	public RECORD_EMAIL_PROTOKOLL(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_PROTOKOLL.TABLENAME);
	}




	public RECORD_EMAIL_PROTOKOLL(long lID_Unformated) throws myException
	{
		super("JT_EMAIL_PROTOKOLL","ID_EMAIL_PROTOKOLL",""+lID_Unformated);
		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_PROTOKOLL.TABLENAME);
	}

	public RECORD_EMAIL_PROTOKOLL(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_PROTOKOLL", "ID_EMAIL_PROTOKOLL="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_PROTOKOLL", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_PROTOKOLL.TABLENAME);
	}
	
	

	public RECORD_EMAIL_PROTOKOLL(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_EMAIL_PROTOKOLL","ID_EMAIL_PROTOKOLL",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_PROTOKOLL.TABLENAME);

	}


	public RECORD_EMAIL_PROTOKOLL(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EMAIL_PROTOKOLL");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_PROTOKOLL", "ID_EMAIL_PROTOKOLL="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_PROTOKOLL", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EMAIL_PROTOKOLL.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_EMAIL_PROTOKOLL();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_EMAIL_PROTOKOLL.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_EMAIL_PROTOKOLL";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_EMAIL_PROTOKOLL_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_EMAIL_PROTOKOLL_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EMAIL_PROTOKOLL was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EMAIL_PROTOKOLL", bibE2.cTO(), "ID_EMAIL_PROTOKOLL="+this.get_ID_EMAIL_PROTOKOLL_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EMAIL_PROTOKOLL was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EMAIL_PROTOKOLL", bibE2.cTO(), "ID_EMAIL_PROTOKOLL="+this.get_ID_EMAIL_PROTOKOLL_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_EMAIL_PROTOKOLL WHERE ID_EMAIL_PROTOKOLL="+this.get_ID_EMAIL_PROTOKOLL_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_EMAIL_PROTOKOLL WHERE ID_EMAIL_PROTOKOLL="+this.get_ID_EMAIL_PROTOKOLL_cUF();
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
			if (S.isFull(this.get_ID_EMAIL_PROTOKOLL_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EMAIL_PROTOKOLL", "ID_EMAIL_PROTOKOLL="+this.get_ID_EMAIL_PROTOKOLL_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_EMAIL_PROTOKOLL get_RECORDNEW_EMAIL_PROTOKOLL() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_EMAIL_PROTOKOLL();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_EMAIL_PROTOKOLL(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_EMAIL_PROTOKOLL create_Instance() throws myException {
		return new RECORD_EMAIL_PROTOKOLL();
	}
	
	public static RECORD_EMAIL_PROTOKOLL create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_EMAIL_PROTOKOLL(Conn);
    }

	public static RECORD_EMAIL_PROTOKOLL create_Instance(RECORD_EMAIL_PROTOKOLL recordOrig) {
		return new RECORD_EMAIL_PROTOKOLL(recordOrig);
    }

	public static RECORD_EMAIL_PROTOKOLL create_Instance(long lID_Unformated) throws myException {
		return new RECORD_EMAIL_PROTOKOLL(lID_Unformated);
    }

	public static RECORD_EMAIL_PROTOKOLL create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_EMAIL_PROTOKOLL(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_EMAIL_PROTOKOLL create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_EMAIL_PROTOKOLL(lID_Unformated, Conn);
	}

	public static RECORD_EMAIL_PROTOKOLL create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_EMAIL_PROTOKOLL(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_EMAIL_PROTOKOLL create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_EMAIL_PROTOKOLL(recordOrig);	    
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
    public RECORD_EMAIL_PROTOKOLL build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_PROTOKOLL WHERE ID_EMAIL_PROTOKOLL="+this.get_ID_EMAIL_PROTOKOLL_cUF());
      }
      //return new RECORD_EMAIL_PROTOKOLL(this.get_cSQL_4_Build());
      RECORD_EMAIL_PROTOKOLL rec = new RECORD_EMAIL_PROTOKOLL();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_EMAIL_PROTOKOLL
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_EMAIL_PROTOKOLL-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_EMAIL_PROTOKOLL get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_EMAIL_PROTOKOLL_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_EMAIL_PROTOKOLL  recNew = new RECORDNEW_EMAIL_PROTOKOLL();
		
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
    public RECORD_EMAIL_PROTOKOLL set_recordNew(RECORDNEW_EMAIL_PROTOKOLL recnew) throws myException {
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
	
	

	public static String FIELD__ABSENDER_ADRESSE = "ABSENDER_ADRESSE";
	public static String FIELD__ANHANG_ARCHIV_NAME = "ANHANG_ARCHIV_NAME";
	public static String FIELD__DATUM = "DATUM";
	public static String FIELD__EMAIL_BETREFF = "EMAIL_BETREFF";
	public static String FIELD__EMAIL_TEXT = "EMAIL_TEXT";
	public static String FIELD__EMPFAENGER_ADRESSE = "EMPFAENGER_ADRESSE";
	public static String FIELD__ERFOLGREICH_UEBERGEBEN = "ERFOLGREICH_UEBERGEBEN";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_EMAIL_PROTOKOLL = "ID_EMAIL_PROTOKOLL";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TABLE = "ID_TABLE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MIME_TYPE = "MIME_TYPE";
	public static String FIELD__PROGRAMM_MODUL = "PROGRAMM_MODUL";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TABLENAME = "TABLENAME";
	public static String FIELD__USER_KUERZEL = "USER_KUERZEL";
	public static String FIELD__VERWALTUNGSINFO = "VERWALTUNGSINFO";


	public String get_ABSENDER_ADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABSENDER_ADRESSE");
	}

	public String get_ABSENDER_ADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("ABSENDER_ADRESSE");	
	}

	public String get_ABSENDER_ADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABSENDER_ADRESSE");
	}

	public String get_ABSENDER_ADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABSENDER_ADRESSE",cNotNullValue);
	}

	public String get_ABSENDER_ADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABSENDER_ADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABSENDER_ADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABSENDER_ADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABSENDER_ADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABSENDER_ADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABSENDER_ADRESSE", calNewValueFormated);
	}
		public String get_ANHANG_ARCHIV_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANHANG_ARCHIV_NAME");
	}

	public String get_ANHANG_ARCHIV_NAME_cF() throws myException
	{
		return this.get_FormatedValue("ANHANG_ARCHIV_NAME");	
	}

	public String get_ANHANG_ARCHIV_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANHANG_ARCHIV_NAME");
	}

	public String get_ANHANG_ARCHIV_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANHANG_ARCHIV_NAME",cNotNullValue);
	}

	public String get_ANHANG_ARCHIV_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANHANG_ARCHIV_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANHANG_ARCHIV_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANHANG_ARCHIV_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG_ARCHIV_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG_ARCHIV_NAME", calNewValueFormated);
	}
		public String get_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM");
	}

	public String get_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("DATUM");	
	}

	public String get_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM");
	}

	public String get_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM",cNotNullValue);
	}

	public String get_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM", calNewValueFormated);
	}
		public String get_EMAIL_BETREFF_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL_BETREFF");
	}

	public String get_EMAIL_BETREFF_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL_BETREFF");	
	}

	public String get_EMAIL_BETREFF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL_BETREFF");
	}

	public String get_EMAIL_BETREFF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL_BETREFF",cNotNullValue);
	}

	public String get_EMAIL_BETREFF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL_BETREFF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL_BETREFF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL_BETREFF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL_BETREFF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_BETREFF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_BETREFF", calNewValueFormated);
	}
		public String get_EMAIL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL_TEXT");
	}

	public String get_EMAIL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL_TEXT");	
	}

	public String get_EMAIL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL_TEXT");
	}

	public String get_EMAIL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL_TEXT",cNotNullValue);
	}

	public String get_EMAIL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_TEXT", calNewValueFormated);
	}
		public String get_EMPFAENGER_ADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMPFAENGER_ADRESSE");
	}

	public String get_EMPFAENGER_ADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("EMPFAENGER_ADRESSE");	
	}

	public String get_EMPFAENGER_ADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMPFAENGER_ADRESSE");
	}

	public String get_EMPFAENGER_ADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMPFAENGER_ADRESSE",cNotNullValue);
	}

	public String get_EMPFAENGER_ADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMPFAENGER_ADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMPFAENGER_ADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMPFAENGER_ADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMPFAENGER_ADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMPFAENGER_ADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMPFAENGER_ADRESSE", calNewValueFormated);
	}
		public String get_ERFOLGREICH_UEBERGEBEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERFOLGREICH_UEBERGEBEN");
	}

	public String get_ERFOLGREICH_UEBERGEBEN_cF() throws myException
	{
		return this.get_FormatedValue("ERFOLGREICH_UEBERGEBEN");	
	}

	public String get_ERFOLGREICH_UEBERGEBEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERFOLGREICH_UEBERGEBEN");
	}

	public String get_ERFOLGREICH_UEBERGEBEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERFOLGREICH_UEBERGEBEN",cNotNullValue);
	}

	public String get_ERFOLGREICH_UEBERGEBEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERFOLGREICH_UEBERGEBEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERFOLGREICH_UEBERGEBEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFOLGREICH_UEBERGEBEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFOLGREICH_UEBERGEBEN", calNewValueFormated);
	}
		public boolean is_ERFOLGREICH_UEBERGEBEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERFOLGREICH_UEBERGEBEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ERFOLGREICH_UEBERGEBEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERFOLGREICH_UEBERGEBEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_ID_EMAIL_PROTOKOLL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EMAIL_PROTOKOLL");
	}

	public String get_ID_EMAIL_PROTOKOLL_cF() throws myException
	{
		return this.get_FormatedValue("ID_EMAIL_PROTOKOLL");	
	}

	public String get_ID_EMAIL_PROTOKOLL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EMAIL_PROTOKOLL");
	}

	public String get_ID_EMAIL_PROTOKOLL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EMAIL_PROTOKOLL",cNotNullValue);
	}

	public String get_ID_EMAIL_PROTOKOLL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EMAIL_PROTOKOLL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EMAIL_PROTOKOLL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EMAIL_PROTOKOLL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EMAIL_PROTOKOLL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EMAIL_PROTOKOLL", calNewValueFormated);
	}
		public Long get_ID_EMAIL_PROTOKOLL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EMAIL_PROTOKOLL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EMAIL_PROTOKOLL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EMAIL_PROTOKOLL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EMAIL_PROTOKOLL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EMAIL_PROTOKOLL").getDoubleValue();
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
	public String get_ID_EMAIL_PROTOKOLL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EMAIL_PROTOKOLL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EMAIL_PROTOKOLL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EMAIL_PROTOKOLL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EMAIL_PROTOKOLL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EMAIL_PROTOKOLL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EMAIL_PROTOKOLL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EMAIL_PROTOKOLL").getBigDecimalValue();
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
	
	
	public String get_ID_TABLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TABLE");
	}

	public String get_ID_TABLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_TABLE");	
	}

	public String get_ID_TABLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TABLE");
	}

	public String get_ID_TABLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TABLE",cNotNullValue);
	}

	public String get_ID_TABLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TABLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TABLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABLE", calNewValueFormated);
	}
		public Long get_ID_TABLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TABLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TABLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TABLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TABLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TABLE").getDoubleValue();
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
	public String get_ID_TABLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TABLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TABLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TABLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TABLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TABLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TABLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TABLE").getBigDecimalValue();
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
		public String get_MIME_TYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MIME_TYPE");
	}

	public String get_MIME_TYPE_cF() throws myException
	{
		return this.get_FormatedValue("MIME_TYPE");	
	}

	public String get_MIME_TYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MIME_TYPE");
	}

	public String get_MIME_TYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MIME_TYPE",cNotNullValue);
	}

	public String get_MIME_TYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MIME_TYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MIME_TYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MIME_TYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MIME_TYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MIME_TYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MIME_TYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MIME_TYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MIME_TYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MIME_TYPE", calNewValueFormated);
	}
		public String get_PROGRAMM_MODUL_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROGRAMM_MODUL");
	}

	public String get_PROGRAMM_MODUL_cF() throws myException
	{
		return this.get_FormatedValue("PROGRAMM_MODUL");	
	}

	public String get_PROGRAMM_MODUL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROGRAMM_MODUL");
	}

	public String get_PROGRAMM_MODUL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROGRAMM_MODUL",cNotNullValue);
	}

	public String get_PROGRAMM_MODUL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROGRAMM_MODUL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROGRAMM_MODUL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROGRAMM_MODUL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROGRAMM_MODUL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_MODUL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_MODUL", calNewValueFormated);
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
	
	
	public String get_TABLENAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("TABLENAME");
	}

	public String get_TABLENAME_cF() throws myException
	{
		return this.get_FormatedValue("TABLENAME");	
	}

	public String get_TABLENAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TABLENAME");
	}

	public String get_TABLENAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TABLENAME",cNotNullValue);
	}

	public String get_TABLENAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TABLENAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TABLENAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TABLENAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TABLENAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TABLENAME", calNewValueFormated);
	}
		public String get_USER_KUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("USER_KUERZEL");
	}

	public String get_USER_KUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("USER_KUERZEL");	
	}

	public String get_USER_KUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("USER_KUERZEL");
	}

	public String get_USER_KUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("USER_KUERZEL",cNotNullValue);
	}

	public String get_USER_KUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("USER_KUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("USER_KUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_USER_KUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("USER_KUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("USER_KUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("USER_KUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("USER_KUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_USER_KUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("USER_KUERZEL", calNewValueFormated);
	}
		public String get_VERWALTUNGSINFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERWALTUNGSINFO");
	}

	public String get_VERWALTUNGSINFO_cF() throws myException
	{
		return this.get_FormatedValue("VERWALTUNGSINFO");	
	}

	public String get_VERWALTUNGSINFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERWALTUNGSINFO");
	}

	public String get_VERWALTUNGSINFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERWALTUNGSINFO",cNotNullValue);
	}

	public String get_VERWALTUNGSINFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERWALTUNGSINFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERWALTUNGSINFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERWALTUNGSINFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERWALTUNGSINFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWALTUNGSINFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWALTUNGSINFO", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABSENDER_ADRESSE",MyRECORD.DATATYPES.TEXT);
	put("ANHANG_ARCHIV_NAME",MyRECORD.DATATYPES.TEXT);
	put("DATUM",MyRECORD.DATATYPES.DATE);
	put("EMAIL_BETREFF",MyRECORD.DATATYPES.TEXT);
	put("EMAIL_TEXT",MyRECORD.DATATYPES.TEXT);
	put("EMPFAENGER_ADRESSE",MyRECORD.DATATYPES.TEXT);
	put("ERFOLGREICH_UEBERGEBEN",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_EMAIL_PROTOKOLL",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TABLE",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MIME_TYPE",MyRECORD.DATATYPES.TEXT);
	put("PROGRAMM_MODUL",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TABLENAME",MyRECORD.DATATYPES.TEXT);
	put("USER_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("VERWALTUNGSINFO",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_EMAIL_PROTOKOLL.HM_FIELDS;	
	}

}
