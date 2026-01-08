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

public class RECORD_REPORT_PARAMETER extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_REPORT_PARAMETER";
    public static String IDFIELD   = "ID_REPORT_PARAMETER";
    

	//erzeugen eines RECORDNEW_JT_REPORT_PARAMETER - felds
	private RECORDNEW_REPORT_PARAMETER   recNEW = null;

    private _TAB  tab = _TAB.report_parameter;  



	public RECORD_REPORT_PARAMETER() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_REPORT_PARAMETER");
	}


	public RECORD_REPORT_PARAMETER(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORT_PARAMETER");
	}



	public RECORD_REPORT_PARAMETER(RECORD_REPORT_PARAMETER recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORT_PARAMETER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PARAMETER.TABLENAME);
	}


	//2014-04-03
	public RECORD_REPORT_PARAMETER(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORT_PARAMETER");
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PARAMETER.TABLENAME);
	}




	public RECORD_REPORT_PARAMETER(long lID_Unformated) throws myException
	{
		super("JT_REPORT_PARAMETER","ID_REPORT_PARAMETER",""+lID_Unformated);
		this.set_TABLE_NAME("JT_REPORT_PARAMETER");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PARAMETER.TABLENAME);
	}

	public RECORD_REPORT_PARAMETER(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_REPORT_PARAMETER");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PARAMETER", "ID_REPORT_PARAMETER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PARAMETER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PARAMETER.TABLENAME);
	}
	
	

	public RECORD_REPORT_PARAMETER(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_REPORT_PARAMETER","ID_REPORT_PARAMETER",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_REPORT_PARAMETER");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PARAMETER.TABLENAME);

	}


	public RECORD_REPORT_PARAMETER(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORT_PARAMETER");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PARAMETER", "ID_REPORT_PARAMETER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PARAMETER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORT_PARAMETER.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_REPORT_PARAMETER();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_REPORT_PARAMETER.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_REPORT_PARAMETER";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_REPORT_PARAMETER_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_REPORT_PARAMETER_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORT_PARAMETER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORT_PARAMETER", bibE2.cTO(), "ID_REPORT_PARAMETER="+this.get_ID_REPORT_PARAMETER_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORT_PARAMETER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORT_PARAMETER", bibE2.cTO(), "ID_REPORT_PARAMETER="+this.get_ID_REPORT_PARAMETER_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_REPORT_PARAMETER WHERE ID_REPORT_PARAMETER="+this.get_ID_REPORT_PARAMETER_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_REPORT_PARAMETER WHERE ID_REPORT_PARAMETER="+this.get_ID_REPORT_PARAMETER_cUF();
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
			if (S.isFull(this.get_ID_REPORT_PARAMETER_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORT_PARAMETER", "ID_REPORT_PARAMETER="+this.get_ID_REPORT_PARAMETER_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_REPORT_PARAMETER get_RECORDNEW_REPORT_PARAMETER() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_REPORT_PARAMETER();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_REPORT_PARAMETER(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_REPORT_PARAMETER create_Instance() throws myException {
		return new RECORD_REPORT_PARAMETER();
	}
	
	public static RECORD_REPORT_PARAMETER create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_REPORT_PARAMETER(Conn);
    }

	public static RECORD_REPORT_PARAMETER create_Instance(RECORD_REPORT_PARAMETER recordOrig) {
		return new RECORD_REPORT_PARAMETER(recordOrig);
    }

	public static RECORD_REPORT_PARAMETER create_Instance(long lID_Unformated) throws myException {
		return new RECORD_REPORT_PARAMETER(lID_Unformated);
    }

	public static RECORD_REPORT_PARAMETER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_REPORT_PARAMETER(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_REPORT_PARAMETER create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_REPORT_PARAMETER(lID_Unformated, Conn);
	}

	public static RECORD_REPORT_PARAMETER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_REPORT_PARAMETER(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_REPORT_PARAMETER create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_REPORT_PARAMETER(recordOrig);	    
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
    public RECORD_REPORT_PARAMETER build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PARAMETER WHERE ID_REPORT_PARAMETER="+this.get_ID_REPORT_PARAMETER_cUF());
      }
      //return new RECORD_REPORT_PARAMETER(this.get_cSQL_4_Build());
      RECORD_REPORT_PARAMETER rec = new RECORD_REPORT_PARAMETER();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_REPORT_PARAMETER
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_REPORT_PARAMETER-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_REPORT_PARAMETER get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_REPORT_PARAMETER_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_REPORT_PARAMETER  recNew = new RECORDNEW_REPORT_PARAMETER();
		
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
    public RECORD_REPORT_PARAMETER set_recordNew(RECORDNEW_REPORT_PARAMETER recnew) throws myException {
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
	
	



		private RECORD_REPORT UP_RECORD_REPORT_id_report = null;






	
	/**
	 * References the Field ID_REPORT
	 * Falls keine this.get_ID_REPORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_REPORT get_UP_RECORD_REPORT_id_report() throws myException
	{
		if (S.isEmpty(this.get_ID_REPORT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_REPORT_id_report==null)
		{
			this.UP_RECORD_REPORT_id_report = new RECORD_REPORT(this.get_ID_REPORT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_REPORT_id_report;
	}
	

	public static String FIELD__BESCHREIBUNGSTEXT = "BESCHREIBUNGSTEXT";
	public static String FIELD__DEFAULTVALUE = "DEFAULTVALUE";
	public static String FIELD__DROPDOWN_DEF = "DROPDOWN_DEF";
	public static String FIELD__ERSATZTEXT_FUER_LEER = "ERSATZTEXT_FUER_LEER";
	public static String FIELD__ERSATZTEXT_FUER_WERT = "ERSATZTEXT_FUER_WERT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_REPORT = "ID_REPORT";
	public static String FIELD__ID_REPORT_PARAMETER = "ID_REPORT_PARAMETER";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__PARAMETERNAME_IN_REPORT = "PARAMETERNAME_IN_REPORT";
	public static String FIELD__PARAMETERTYP = "PARAMETERTYP";
	public static String FIELD__PARAMETER_MUST_BE_SET = "PARAMETER_MUST_BE_SET";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TEXTUSERINTERFACE = "TEXTUSERINTERFACE";


	public String get_BESCHREIBUNGSTEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNGSTEXT");
	}

	public String get_BESCHREIBUNGSTEXT_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNGSTEXT");	
	}

	public String get_BESCHREIBUNGSTEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIBUNGSTEXT");
	}

	public String get_BESCHREIBUNGSTEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNGSTEXT",cNotNullValue);
	}

	public String get_BESCHREIBUNGSTEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNGSTEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIBUNGSTEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNGSTEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIBUNGSTEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNGSTEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNGSTEXT", calNewValueFormated);
	}
		public String get_DEFAULTVALUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULTVALUE");
	}

	public String get_DEFAULTVALUE_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULTVALUE");	
	}

	public String get_DEFAULTVALUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULTVALUE");
	}

	public String get_DEFAULTVALUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULTVALUE",cNotNullValue);
	}

	public String get_DEFAULTVALUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULTVALUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULTVALUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULTVALUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULTVALUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULTVALUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULTVALUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULTVALUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULTVALUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULTVALUE", calNewValueFormated);
	}
		public String get_DROPDOWN_DEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("DROPDOWN_DEF");
	}

	public String get_DROPDOWN_DEF_cF() throws myException
	{
		return this.get_FormatedValue("DROPDOWN_DEF");	
	}

	public String get_DROPDOWN_DEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DROPDOWN_DEF");
	}

	public String get_DROPDOWN_DEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DROPDOWN_DEF",cNotNullValue);
	}

	public String get_DROPDOWN_DEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DROPDOWN_DEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DROPDOWN_DEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DROPDOWN_DEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DROPDOWN_DEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_DEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWN_DEF", calNewValueFormated);
	}
		public String get_ERSATZTEXT_FUER_LEER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERSATZTEXT_FUER_LEER");
	}

	public String get_ERSATZTEXT_FUER_LEER_cF() throws myException
	{
		return this.get_FormatedValue("ERSATZTEXT_FUER_LEER");	
	}

	public String get_ERSATZTEXT_FUER_LEER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERSATZTEXT_FUER_LEER");
	}

	public String get_ERSATZTEXT_FUER_LEER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERSATZTEXT_FUER_LEER",cNotNullValue);
	}

	public String get_ERSATZTEXT_FUER_LEER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERSATZTEXT_FUER_LEER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERSATZTEXT_FUER_LEER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERSATZTEXT_FUER_LEER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_LEER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_LEER", calNewValueFormated);
	}
		public String get_ERSATZTEXT_FUER_WERT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERSATZTEXT_FUER_WERT");
	}

	public String get_ERSATZTEXT_FUER_WERT_cF() throws myException
	{
		return this.get_FormatedValue("ERSATZTEXT_FUER_WERT");	
	}

	public String get_ERSATZTEXT_FUER_WERT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERSATZTEXT_FUER_WERT");
	}

	public String get_ERSATZTEXT_FUER_WERT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERSATZTEXT_FUER_WERT",cNotNullValue);
	}

	public String get_ERSATZTEXT_FUER_WERT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERSATZTEXT_FUER_WERT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERSATZTEXT_FUER_WERT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERSATZTEXT_FUER_WERT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSATZTEXT_FUER_WERT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSATZTEXT_FUER_WERT", calNewValueFormated);
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
	
	
	public String get_ID_REPORT_PARAMETER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT_PARAMETER");
	}

	public String get_ID_REPORT_PARAMETER_cF() throws myException
	{
		return this.get_FormatedValue("ID_REPORT_PARAMETER");	
	}

	public String get_ID_REPORT_PARAMETER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_REPORT_PARAMETER");
	}

	public String get_ID_REPORT_PARAMETER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_REPORT_PARAMETER",cNotNullValue);
	}

	public String get_ID_REPORT_PARAMETER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_REPORT_PARAMETER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_REPORT_PARAMETER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_REPORT_PARAMETER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_REPORT_PARAMETER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORT_PARAMETER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORT_PARAMETER", calNewValueFormated);
	}
		public Long get_ID_REPORT_PARAMETER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_REPORT_PARAMETER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_REPORT_PARAMETER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_REPORT_PARAMETER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_REPORT_PARAMETER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_REPORT_PARAMETER").getDoubleValue();
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
	public String get_ID_REPORT_PARAMETER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_PARAMETER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_REPORT_PARAMETER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORT_PARAMETER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_REPORT_PARAMETER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT_PARAMETER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_REPORT_PARAMETER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORT_PARAMETER").getBigDecimalValue();
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
		public String get_PARAMETERNAME_IN_REPORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETERNAME_IN_REPORT");
	}

	public String get_PARAMETERNAME_IN_REPORT_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETERNAME_IN_REPORT");	
	}

	public String get_PARAMETERNAME_IN_REPORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETERNAME_IN_REPORT");
	}

	public String get_PARAMETERNAME_IN_REPORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETERNAME_IN_REPORT",cNotNullValue);
	}

	public String get_PARAMETERNAME_IN_REPORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETERNAME_IN_REPORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETERNAME_IN_REPORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETERNAME_IN_REPORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERNAME_IN_REPORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETERNAME_IN_REPORT", calNewValueFormated);
	}
		public String get_PARAMETERTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETERTYP");
	}

	public String get_PARAMETERTYP_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETERTYP");	
	}

	public String get_PARAMETERTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETERTYP");
	}

	public String get_PARAMETERTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETERTYP",cNotNullValue);
	}

	public String get_PARAMETERTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETERTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETERTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETERTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETERTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETERTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETERTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETERTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETERTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETERTYP", calNewValueFormated);
	}
		public String get_PARAMETER_MUST_BE_SET_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER_MUST_BE_SET");
	}

	public String get_PARAMETER_MUST_BE_SET_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER_MUST_BE_SET");	
	}

	public String get_PARAMETER_MUST_BE_SET_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER_MUST_BE_SET");
	}

	public String get_PARAMETER_MUST_BE_SET_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER_MUST_BE_SET",cNotNullValue);
	}

	public String get_PARAMETER_MUST_BE_SET_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER_MUST_BE_SET",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER_MUST_BE_SET(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER_MUST_BE_SET", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER_MUST_BE_SET_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER_MUST_BE_SET", calNewValueFormated);
	}
		public boolean is_PARAMETER_MUST_BE_SET_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PARAMETER_MUST_BE_SET_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PARAMETER_MUST_BE_SET_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PARAMETER_MUST_BE_SET_cUF_NN("N").equals("N"))
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
	
	
	public String get_TEXTUSERINTERFACE_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEXTUSERINTERFACE");
	}

	public String get_TEXTUSERINTERFACE_cF() throws myException
	{
		return this.get_FormatedValue("TEXTUSERINTERFACE");	
	}

	public String get_TEXTUSERINTERFACE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEXTUSERINTERFACE");
	}

	public String get_TEXTUSERINTERFACE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEXTUSERINTERFACE",cNotNullValue);
	}

	public String get_TEXTUSERINTERFACE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEXTUSERINTERFACE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEXTUSERINTERFACE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEXTUSERINTERFACE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEXTUSERINTERFACE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTUSERINTERFACE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTUSERINTERFACE", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("BESCHREIBUNGSTEXT",MyRECORD.DATATYPES.TEXT);
	put("DEFAULTVALUE",MyRECORD.DATATYPES.TEXT);
	put("DROPDOWN_DEF",MyRECORD.DATATYPES.TEXT);
	put("ERSATZTEXT_FUER_LEER",MyRECORD.DATATYPES.TEXT);
	put("ERSATZTEXT_FUER_WERT",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_REPORT",MyRECORD.DATATYPES.NUMBER);
	put("ID_REPORT_PARAMETER",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("PARAMETERNAME_IN_REPORT",MyRECORD.DATATYPES.TEXT);
	put("PARAMETERTYP",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER_MUST_BE_SET",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TEXTUSERINTERFACE",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_REPORT_PARAMETER.HM_FIELDS;	
	}

}
