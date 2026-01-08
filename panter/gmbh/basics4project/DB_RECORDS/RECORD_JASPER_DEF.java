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

public class RECORD_JASPER_DEF extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_JASPER_DEF";
    public static String IDFIELD   = "ID_JASPER_DEF";
    

	//erzeugen eines RECORDNEW_JT_JASPER_DEF - felds
	private RECORDNEW_JASPER_DEF   recNEW = null;

    private _TAB  tab = _TAB.jasper_def;  



	public RECORD_JASPER_DEF() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_JASPER_DEF");
	}


	public RECORD_JASPER_DEF(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_JASPER_DEF");
	}



	public RECORD_JASPER_DEF(RECORD_JASPER_DEF recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_JASPER_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_JASPER_DEF.TABLENAME);
	}


	//2014-04-03
	public RECORD_JASPER_DEF(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_JASPER_DEF");
		this.set_Tablename_To_FieldMetaDefs(RECORD_JASPER_DEF.TABLENAME);
	}




	public RECORD_JASPER_DEF(long lID_Unformated) throws myException
	{
		super("JT_JASPER_DEF","ID_JASPER_DEF",""+lID_Unformated);
		this.set_TABLE_NAME("JT_JASPER_DEF");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_JASPER_DEF.TABLENAME);
	}

	public RECORD_JASPER_DEF(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_JASPER_DEF");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_JASPER_DEF", "ID_JASPER_DEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_JASPER_DEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_JASPER_DEF.TABLENAME);
	}
	
	

	public RECORD_JASPER_DEF(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_JASPER_DEF","ID_JASPER_DEF",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_JASPER_DEF");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_JASPER_DEF.TABLENAME);

	}


	public RECORD_JASPER_DEF(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_JASPER_DEF");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_JASPER_DEF", "ID_JASPER_DEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_JASPER_DEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_JASPER_DEF.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_JASPER_DEF();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_JASPER_DEF.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_JASPER_DEF";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_JASPER_DEF_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_JASPER_DEF_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_JASPER_DEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_JASPER_DEF", bibE2.cTO(), "ID_JASPER_DEF="+this.get_ID_JASPER_DEF_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_JASPER_DEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_JASPER_DEF", bibE2.cTO(), "ID_JASPER_DEF="+this.get_ID_JASPER_DEF_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_JASPER_DEF WHERE ID_JASPER_DEF="+this.get_ID_JASPER_DEF_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_JASPER_DEF WHERE ID_JASPER_DEF="+this.get_ID_JASPER_DEF_cUF();
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
			if (S.isFull(this.get_ID_JASPER_DEF_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_JASPER_DEF", "ID_JASPER_DEF="+this.get_ID_JASPER_DEF_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_JASPER_DEF get_RECORDNEW_JASPER_DEF() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_JASPER_DEF();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_JASPER_DEF(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_JASPER_DEF create_Instance() throws myException {
		return new RECORD_JASPER_DEF();
	}
	
	public static RECORD_JASPER_DEF create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_JASPER_DEF(Conn);
    }

	public static RECORD_JASPER_DEF create_Instance(RECORD_JASPER_DEF recordOrig) {
		return new RECORD_JASPER_DEF(recordOrig);
    }

	public static RECORD_JASPER_DEF create_Instance(long lID_Unformated) throws myException {
		return new RECORD_JASPER_DEF(lID_Unformated);
    }

	public static RECORD_JASPER_DEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_JASPER_DEF(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_JASPER_DEF create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_JASPER_DEF(lID_Unformated, Conn);
	}

	public static RECORD_JASPER_DEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_JASPER_DEF(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_JASPER_DEF create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_JASPER_DEF(recordOrig);	    
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
    public RECORD_JASPER_DEF build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_JASPER_DEF WHERE ID_JASPER_DEF="+this.get_ID_JASPER_DEF_cUF());
      }
      //return new RECORD_JASPER_DEF(this.get_cSQL_4_Build());
      RECORD_JASPER_DEF rec = new RECORD_JASPER_DEF();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_JASPER_DEF
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_JASPER_DEF-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_JASPER_DEF get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_JASPER_DEF_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_JASPER_DEF  recNew = new RECORDNEW_JASPER_DEF();
		
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
    public RECORD_JASPER_DEF set_recordNew(RECORDNEW_JASPER_DEF recnew) throws myException {
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
	
	

	public static String FIELD__ALLOW_CSV = "ALLOW_CSV";
	public static String FIELD__ALLOW_HTML = "ALLOW_HTML";
	public static String FIELD__ALLOW_PDF = "ALLOW_PDF";
	public static String FIELD__ALLOW_XLS = "ALLOW_XLS";
	public static String FIELD__ALLOW_XML = "ALLOW_XML";
	public static String FIELD__DESCRIPTION1 = "DESCRIPTION1";
	public static String FIELD__DESCRIPTION2 = "DESCRIPTION2";
	public static String FIELD__DESCRIPTION3 = "DESCRIPTION3";
	public static String FIELD__DESCRIPTION4 = "DESCRIPTION4";
	public static String FIELD__DESCRIPTION5 = "DESCRIPTION5";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_JASPER_DEF = "ID_JASPER_DEF";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__NAME_OF_JASPERFILE = "NAME_OF_JASPERFILE";
	public static String FIELD__PARAM1_DEFAULT = "PARAM1_DEFAULT";
	public static String FIELD__PARAM1_DEFINITION = "PARAM1_DEFINITION";
	public static String FIELD__PARAM1_DESCRIPTION = "PARAM1_DESCRIPTION";
	public static String FIELD__PARAM1_NAME = "PARAM1_NAME";
	public static String FIELD__PARAM1_TYPE = "PARAM1_TYPE";
	public static String FIELD__PARAM2_DEFAULT = "PARAM2_DEFAULT";
	public static String FIELD__PARAM2_DEFINITION = "PARAM2_DEFINITION";
	public static String FIELD__PARAM2_DESCRIPTION = "PARAM2_DESCRIPTION";
	public static String FIELD__PARAM2_NAME = "PARAM2_NAME";
	public static String FIELD__PARAM2_TYPE = "PARAM2_TYPE";
	public static String FIELD__PARAM3_DEFAULT = "PARAM3_DEFAULT";
	public static String FIELD__PARAM3_DEFINITION = "PARAM3_DEFINITION";
	public static String FIELD__PARAM3_DESCRIPTION = "PARAM3_DESCRIPTION";
	public static String FIELD__PARAM3_NAME = "PARAM3_NAME";
	public static String FIELD__PARAM3_TYPE = "PARAM3_TYPE";
	public static String FIELD__PARAM4_DEFAULT = "PARAM4_DEFAULT";
	public static String FIELD__PARAM4_DEFINITION = "PARAM4_DEFINITION";
	public static String FIELD__PARAM4_DESCRIPTION = "PARAM4_DESCRIPTION";
	public static String FIELD__PARAM4_NAME = "PARAM4_NAME";
	public static String FIELD__PARAM4_TYPE = "PARAM4_TYPE";
	public static String FIELD__PARAM5_DEFAULT = "PARAM5_DEFAULT";
	public static String FIELD__PARAM5_DEFINITION = "PARAM5_DEFINITION";
	public static String FIELD__PARAM5_DESCRIPTION = "PARAM5_DESCRIPTION";
	public static String FIELD__PARAM5_NAME = "PARAM5_NAME";
	public static String FIELD__PARAM5_TYPE = "PARAM5_TYPE";
	public static String FIELD__REPORT_NAME = "REPORT_NAME";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_ALLOW_CSV_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALLOW_CSV");
	}

	public String get_ALLOW_CSV_cF() throws myException
	{
		return this.get_FormatedValue("ALLOW_CSV");	
	}

	public String get_ALLOW_CSV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALLOW_CSV");
	}

	public String get_ALLOW_CSV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALLOW_CSV",cNotNullValue);
	}

	public String get_ALLOW_CSV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALLOW_CSV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALLOW_CSV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALLOW_CSV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALLOW_CSV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALLOW_CSV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_CSV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_CSV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALLOW_CSV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALLOW_CSV", calNewValueFormated);
	}
		public boolean is_ALLOW_CSV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_CSV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALLOW_CSV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALLOW_CSV_cUF_NN("N").equals("N"))
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
		public String get_DESCRIPTION1_cUF() throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION1");
	}

	public String get_DESCRIPTION1_cF() throws myException
	{
		return this.get_FormatedValue("DESCRIPTION1");	
	}

	public String get_DESCRIPTION1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DESCRIPTION1");
	}

	public String get_DESCRIPTION1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION1",cNotNullValue);
	}

	public String get_DESCRIPTION1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DESCRIPTION1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DESCRIPTION1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DESCRIPTION1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DESCRIPTION1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION1", calNewValueFormated);
	}
		public String get_DESCRIPTION2_cUF() throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION2");
	}

	public String get_DESCRIPTION2_cF() throws myException
	{
		return this.get_FormatedValue("DESCRIPTION2");	
	}

	public String get_DESCRIPTION2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DESCRIPTION2");
	}

	public String get_DESCRIPTION2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION2",cNotNullValue);
	}

	public String get_DESCRIPTION2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DESCRIPTION2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DESCRIPTION2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DESCRIPTION2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DESCRIPTION2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION2", calNewValueFormated);
	}
		public String get_DESCRIPTION3_cUF() throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION3");
	}

	public String get_DESCRIPTION3_cF() throws myException
	{
		return this.get_FormatedValue("DESCRIPTION3");	
	}

	public String get_DESCRIPTION3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DESCRIPTION3");
	}

	public String get_DESCRIPTION3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION3",cNotNullValue);
	}

	public String get_DESCRIPTION3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DESCRIPTION3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DESCRIPTION3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DESCRIPTION3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DESCRIPTION3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION3", calNewValueFormated);
	}
		public String get_DESCRIPTION4_cUF() throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION4");
	}

	public String get_DESCRIPTION4_cF() throws myException
	{
		return this.get_FormatedValue("DESCRIPTION4");	
	}

	public String get_DESCRIPTION4_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DESCRIPTION4");
	}

	public String get_DESCRIPTION4_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION4",cNotNullValue);
	}

	public String get_DESCRIPTION4_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DESCRIPTION4",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DESCRIPTION4", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION4(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DESCRIPTION4", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DESCRIPTION4", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION4", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION4", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION4_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION4", calNewValueFormated);
	}
		public String get_DESCRIPTION5_cUF() throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION5");
	}

	public String get_DESCRIPTION5_cF() throws myException
	{
		return this.get_FormatedValue("DESCRIPTION5");	
	}

	public String get_DESCRIPTION5_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DESCRIPTION5");
	}

	public String get_DESCRIPTION5_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DESCRIPTION5",cNotNullValue);
	}

	public String get_DESCRIPTION5_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DESCRIPTION5",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DESCRIPTION5", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DESCRIPTION5(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DESCRIPTION5", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DESCRIPTION5", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION5", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION5", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DESCRIPTION5_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DESCRIPTION5", calNewValueFormated);
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
		public String get_ID_JASPER_DEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_JASPER_DEF");
	}

	public String get_ID_JASPER_DEF_cF() throws myException
	{
		return this.get_FormatedValue("ID_JASPER_DEF");	
	}

	public String get_ID_JASPER_DEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_JASPER_DEF");
	}

	public String get_ID_JASPER_DEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_JASPER_DEF",cNotNullValue);
	}

	public String get_ID_JASPER_DEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_JASPER_DEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_JASPER_DEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_JASPER_DEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_JASPER_DEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_JASPER_DEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_JASPER_DEF", calNewValueFormated);
	}
		public Long get_ID_JASPER_DEF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_JASPER_DEF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_JASPER_DEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_JASPER_DEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_JASPER_DEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_JASPER_DEF").getDoubleValue();
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
	public String get_ID_JASPER_DEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_JASPER_DEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_JASPER_DEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_JASPER_DEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_JASPER_DEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_JASPER_DEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_JASPER_DEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_JASPER_DEF").getBigDecimalValue();
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
		public String get_NAME_OF_JASPERFILE_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME_OF_JASPERFILE");
	}

	public String get_NAME_OF_JASPERFILE_cF() throws myException
	{
		return this.get_FormatedValue("NAME_OF_JASPERFILE");	
	}

	public String get_NAME_OF_JASPERFILE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME_OF_JASPERFILE");
	}

	public String get_NAME_OF_JASPERFILE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME_OF_JASPERFILE",cNotNullValue);
	}

	public String get_NAME_OF_JASPERFILE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME_OF_JASPERFILE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME_OF_JASPERFILE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME_OF_JASPERFILE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME_OF_JASPERFILE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_OF_JASPERFILE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_OF_JASPERFILE", calNewValueFormated);
	}
		public String get_PARAM1_DEFAULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM1_DEFAULT");
	}

	public String get_PARAM1_DEFAULT_cF() throws myException
	{
		return this.get_FormatedValue("PARAM1_DEFAULT");	
	}

	public String get_PARAM1_DEFAULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM1_DEFAULT");
	}

	public String get_PARAM1_DEFAULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM1_DEFAULT",cNotNullValue);
	}

	public String get_PARAM1_DEFAULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM1_DEFAULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM1_DEFAULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM1_DEFAULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM1_DEFAULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFAULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DEFAULT", calNewValueFormated);
	}
		public String get_PARAM1_DEFINITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM1_DEFINITION");
	}

	public String get_PARAM1_DEFINITION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM1_DEFINITION");	
	}

	public String get_PARAM1_DEFINITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM1_DEFINITION");
	}

	public String get_PARAM1_DEFINITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM1_DEFINITION",cNotNullValue);
	}

	public String get_PARAM1_DEFINITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM1_DEFINITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM1_DEFINITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM1_DEFINITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM1_DEFINITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DEFINITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DEFINITION", calNewValueFormated);
	}
		public String get_PARAM1_DESCRIPTION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM1_DESCRIPTION");
	}

	public String get_PARAM1_DESCRIPTION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM1_DESCRIPTION");	
	}

	public String get_PARAM1_DESCRIPTION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM1_DESCRIPTION");
	}

	public String get_PARAM1_DESCRIPTION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM1_DESCRIPTION",cNotNullValue);
	}

	public String get_PARAM1_DESCRIPTION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM1_DESCRIPTION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM1_DESCRIPTION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM1_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM1_DESCRIPTION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_DESCRIPTION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_DESCRIPTION", calNewValueFormated);
	}
		public String get_PARAM1_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM1_NAME");
	}

	public String get_PARAM1_NAME_cF() throws myException
	{
		return this.get_FormatedValue("PARAM1_NAME");	
	}

	public String get_PARAM1_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM1_NAME");
	}

	public String get_PARAM1_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM1_NAME",cNotNullValue);
	}

	public String get_PARAM1_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM1_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM1_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM1_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM1_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM1_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_NAME", calNewValueFormated);
	}
		public String get_PARAM1_TYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM1_TYPE");
	}

	public String get_PARAM1_TYPE_cF() throws myException
	{
		return this.get_FormatedValue("PARAM1_TYPE");	
	}

	public String get_PARAM1_TYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM1_TYPE");
	}

	public String get_PARAM1_TYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM1_TYPE",cNotNullValue);
	}

	public String get_PARAM1_TYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM1_TYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM1_TYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM1_TYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM1_TYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM1_TYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_TYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_TYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM1_TYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM1_TYPE", calNewValueFormated);
	}
		public String get_PARAM2_DEFAULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM2_DEFAULT");
	}

	public String get_PARAM2_DEFAULT_cF() throws myException
	{
		return this.get_FormatedValue("PARAM2_DEFAULT");	
	}

	public String get_PARAM2_DEFAULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM2_DEFAULT");
	}

	public String get_PARAM2_DEFAULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM2_DEFAULT",cNotNullValue);
	}

	public String get_PARAM2_DEFAULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM2_DEFAULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM2_DEFAULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM2_DEFAULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM2_DEFAULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFAULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DEFAULT", calNewValueFormated);
	}
		public String get_PARAM2_DEFINITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM2_DEFINITION");
	}

	public String get_PARAM2_DEFINITION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM2_DEFINITION");	
	}

	public String get_PARAM2_DEFINITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM2_DEFINITION");
	}

	public String get_PARAM2_DEFINITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM2_DEFINITION",cNotNullValue);
	}

	public String get_PARAM2_DEFINITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM2_DEFINITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM2_DEFINITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM2_DEFINITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM2_DEFINITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DEFINITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DEFINITION", calNewValueFormated);
	}
		public String get_PARAM2_DESCRIPTION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM2_DESCRIPTION");
	}

	public String get_PARAM2_DESCRIPTION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM2_DESCRIPTION");	
	}

	public String get_PARAM2_DESCRIPTION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM2_DESCRIPTION");
	}

	public String get_PARAM2_DESCRIPTION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM2_DESCRIPTION",cNotNullValue);
	}

	public String get_PARAM2_DESCRIPTION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM2_DESCRIPTION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM2_DESCRIPTION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM2_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM2_DESCRIPTION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_DESCRIPTION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_DESCRIPTION", calNewValueFormated);
	}
		public String get_PARAM2_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM2_NAME");
	}

	public String get_PARAM2_NAME_cF() throws myException
	{
		return this.get_FormatedValue("PARAM2_NAME");	
	}

	public String get_PARAM2_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM2_NAME");
	}

	public String get_PARAM2_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM2_NAME",cNotNullValue);
	}

	public String get_PARAM2_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM2_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM2_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM2_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM2_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM2_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_NAME", calNewValueFormated);
	}
		public String get_PARAM2_TYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM2_TYPE");
	}

	public String get_PARAM2_TYPE_cF() throws myException
	{
		return this.get_FormatedValue("PARAM2_TYPE");	
	}

	public String get_PARAM2_TYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM2_TYPE");
	}

	public String get_PARAM2_TYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM2_TYPE",cNotNullValue);
	}

	public String get_PARAM2_TYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM2_TYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM2_TYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM2_TYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM2_TYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM2_TYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_TYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_TYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM2_TYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM2_TYPE", calNewValueFormated);
	}
		public String get_PARAM3_DEFAULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM3_DEFAULT");
	}

	public String get_PARAM3_DEFAULT_cF() throws myException
	{
		return this.get_FormatedValue("PARAM3_DEFAULT");	
	}

	public String get_PARAM3_DEFAULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM3_DEFAULT");
	}

	public String get_PARAM3_DEFAULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM3_DEFAULT",cNotNullValue);
	}

	public String get_PARAM3_DEFAULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM3_DEFAULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM3_DEFAULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM3_DEFAULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM3_DEFAULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFAULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DEFAULT", calNewValueFormated);
	}
		public String get_PARAM3_DEFINITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM3_DEFINITION");
	}

	public String get_PARAM3_DEFINITION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM3_DEFINITION");	
	}

	public String get_PARAM3_DEFINITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM3_DEFINITION");
	}

	public String get_PARAM3_DEFINITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM3_DEFINITION",cNotNullValue);
	}

	public String get_PARAM3_DEFINITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM3_DEFINITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM3_DEFINITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM3_DEFINITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM3_DEFINITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DEFINITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DEFINITION", calNewValueFormated);
	}
		public String get_PARAM3_DESCRIPTION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM3_DESCRIPTION");
	}

	public String get_PARAM3_DESCRIPTION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM3_DESCRIPTION");	
	}

	public String get_PARAM3_DESCRIPTION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM3_DESCRIPTION");
	}

	public String get_PARAM3_DESCRIPTION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM3_DESCRIPTION",cNotNullValue);
	}

	public String get_PARAM3_DESCRIPTION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM3_DESCRIPTION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM3_DESCRIPTION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM3_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM3_DESCRIPTION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_DESCRIPTION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_DESCRIPTION", calNewValueFormated);
	}
		public String get_PARAM3_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM3_NAME");
	}

	public String get_PARAM3_NAME_cF() throws myException
	{
		return this.get_FormatedValue("PARAM3_NAME");	
	}

	public String get_PARAM3_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM3_NAME");
	}

	public String get_PARAM3_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM3_NAME",cNotNullValue);
	}

	public String get_PARAM3_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM3_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM3_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM3_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM3_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM3_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_NAME", calNewValueFormated);
	}
		public String get_PARAM3_TYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM3_TYPE");
	}

	public String get_PARAM3_TYPE_cF() throws myException
	{
		return this.get_FormatedValue("PARAM3_TYPE");	
	}

	public String get_PARAM3_TYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM3_TYPE");
	}

	public String get_PARAM3_TYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM3_TYPE",cNotNullValue);
	}

	public String get_PARAM3_TYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM3_TYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM3_TYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM3_TYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM3_TYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM3_TYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_TYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_TYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM3_TYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM3_TYPE", calNewValueFormated);
	}
		public String get_PARAM4_DEFAULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM4_DEFAULT");
	}

	public String get_PARAM4_DEFAULT_cF() throws myException
	{
		return this.get_FormatedValue("PARAM4_DEFAULT");	
	}

	public String get_PARAM4_DEFAULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM4_DEFAULT");
	}

	public String get_PARAM4_DEFAULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM4_DEFAULT",cNotNullValue);
	}

	public String get_PARAM4_DEFAULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM4_DEFAULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM4_DEFAULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM4_DEFAULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM4_DEFAULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFAULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DEFAULT", calNewValueFormated);
	}
		public String get_PARAM4_DEFINITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM4_DEFINITION");
	}

	public String get_PARAM4_DEFINITION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM4_DEFINITION");	
	}

	public String get_PARAM4_DEFINITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM4_DEFINITION");
	}

	public String get_PARAM4_DEFINITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM4_DEFINITION",cNotNullValue);
	}

	public String get_PARAM4_DEFINITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM4_DEFINITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM4_DEFINITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM4_DEFINITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM4_DEFINITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DEFINITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DEFINITION", calNewValueFormated);
	}
		public String get_PARAM4_DESCRIPTION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM4_DESCRIPTION");
	}

	public String get_PARAM4_DESCRIPTION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM4_DESCRIPTION");	
	}

	public String get_PARAM4_DESCRIPTION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM4_DESCRIPTION");
	}

	public String get_PARAM4_DESCRIPTION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM4_DESCRIPTION",cNotNullValue);
	}

	public String get_PARAM4_DESCRIPTION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM4_DESCRIPTION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM4_DESCRIPTION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM4_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM4_DESCRIPTION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_DESCRIPTION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_DESCRIPTION", calNewValueFormated);
	}
		public String get_PARAM4_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM4_NAME");
	}

	public String get_PARAM4_NAME_cF() throws myException
	{
		return this.get_FormatedValue("PARAM4_NAME");	
	}

	public String get_PARAM4_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM4_NAME");
	}

	public String get_PARAM4_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM4_NAME",cNotNullValue);
	}

	public String get_PARAM4_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM4_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM4_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM4_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM4_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM4_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_NAME", calNewValueFormated);
	}
		public String get_PARAM4_TYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM4_TYPE");
	}

	public String get_PARAM4_TYPE_cF() throws myException
	{
		return this.get_FormatedValue("PARAM4_TYPE");	
	}

	public String get_PARAM4_TYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM4_TYPE");
	}

	public String get_PARAM4_TYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM4_TYPE",cNotNullValue);
	}

	public String get_PARAM4_TYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM4_TYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM4_TYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM4_TYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM4_TYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM4_TYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_TYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_TYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM4_TYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM4_TYPE", calNewValueFormated);
	}
		public String get_PARAM5_DEFAULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM5_DEFAULT");
	}

	public String get_PARAM5_DEFAULT_cF() throws myException
	{
		return this.get_FormatedValue("PARAM5_DEFAULT");	
	}

	public String get_PARAM5_DEFAULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM5_DEFAULT");
	}

	public String get_PARAM5_DEFAULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM5_DEFAULT",cNotNullValue);
	}

	public String get_PARAM5_DEFAULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM5_DEFAULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM5_DEFAULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM5_DEFAULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM5_DEFAULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFAULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DEFAULT", calNewValueFormated);
	}
		public String get_PARAM5_DEFINITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM5_DEFINITION");
	}

	public String get_PARAM5_DEFINITION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM5_DEFINITION");	
	}

	public String get_PARAM5_DEFINITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM5_DEFINITION");
	}

	public String get_PARAM5_DEFINITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM5_DEFINITION",cNotNullValue);
	}

	public String get_PARAM5_DEFINITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM5_DEFINITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM5_DEFINITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM5_DEFINITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM5_DEFINITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DEFINITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DEFINITION", calNewValueFormated);
	}
		public String get_PARAM5_DESCRIPTION_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM5_DESCRIPTION");
	}

	public String get_PARAM5_DESCRIPTION_cF() throws myException
	{
		return this.get_FormatedValue("PARAM5_DESCRIPTION");	
	}

	public String get_PARAM5_DESCRIPTION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM5_DESCRIPTION");
	}

	public String get_PARAM5_DESCRIPTION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM5_DESCRIPTION",cNotNullValue);
	}

	public String get_PARAM5_DESCRIPTION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM5_DESCRIPTION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM5_DESCRIPTION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM5_DESCRIPTION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM5_DESCRIPTION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_DESCRIPTION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_DESCRIPTION", calNewValueFormated);
	}
		public String get_PARAM5_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM5_NAME");
	}

	public String get_PARAM5_NAME_cF() throws myException
	{
		return this.get_FormatedValue("PARAM5_NAME");	
	}

	public String get_PARAM5_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM5_NAME");
	}

	public String get_PARAM5_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM5_NAME",cNotNullValue);
	}

	public String get_PARAM5_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM5_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM5_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM5_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM5_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM5_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_NAME", calNewValueFormated);
	}
		public String get_PARAM5_TYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM5_TYPE");
	}

	public String get_PARAM5_TYPE_cF() throws myException
	{
		return this.get_FormatedValue("PARAM5_TYPE");	
	}

	public String get_PARAM5_TYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM5_TYPE");
	}

	public String get_PARAM5_TYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM5_TYPE",cNotNullValue);
	}

	public String get_PARAM5_TYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM5_TYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM5_TYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM5_TYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM5_TYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM5_TYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_TYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_TYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM5_TYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM5_TYPE", calNewValueFormated);
	}
		public String get_REPORT_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("REPORT_NAME");
	}

	public String get_REPORT_NAME_cF() throws myException
	{
		return this.get_FormatedValue("REPORT_NAME");	
	}

	public String get_REPORT_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("REPORT_NAME");
	}

	public String get_REPORT_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("REPORT_NAME",cNotNullValue);
	}

	public String get_REPORT_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("REPORT_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("REPORT_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_REPORT_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("REPORT_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("REPORT_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORT_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORT_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORT_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORT_NAME", calNewValueFormated);
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
	put("ALLOW_CSV",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_HTML",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_PDF",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_XLS",MyRECORD.DATATYPES.YESNO);
	put("ALLOW_XML",MyRECORD.DATATYPES.YESNO);
	put("DESCRIPTION1",MyRECORD.DATATYPES.TEXT);
	put("DESCRIPTION2",MyRECORD.DATATYPES.TEXT);
	put("DESCRIPTION3",MyRECORD.DATATYPES.TEXT);
	put("DESCRIPTION4",MyRECORD.DATATYPES.TEXT);
	put("DESCRIPTION5",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_JASPER_DEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("NAME_OF_JASPERFILE",MyRECORD.DATATYPES.TEXT);
	put("PARAM1_DEFAULT",MyRECORD.DATATYPES.TEXT);
	put("PARAM1_DEFINITION",MyRECORD.DATATYPES.TEXT);
	put("PARAM1_DESCRIPTION",MyRECORD.DATATYPES.TEXT);
	put("PARAM1_NAME",MyRECORD.DATATYPES.TEXT);
	put("PARAM1_TYPE",MyRECORD.DATATYPES.TEXT);
	put("PARAM2_DEFAULT",MyRECORD.DATATYPES.TEXT);
	put("PARAM2_DEFINITION",MyRECORD.DATATYPES.TEXT);
	put("PARAM2_DESCRIPTION",MyRECORD.DATATYPES.TEXT);
	put("PARAM2_NAME",MyRECORD.DATATYPES.TEXT);
	put("PARAM2_TYPE",MyRECORD.DATATYPES.TEXT);
	put("PARAM3_DEFAULT",MyRECORD.DATATYPES.TEXT);
	put("PARAM3_DEFINITION",MyRECORD.DATATYPES.TEXT);
	put("PARAM3_DESCRIPTION",MyRECORD.DATATYPES.TEXT);
	put("PARAM3_NAME",MyRECORD.DATATYPES.TEXT);
	put("PARAM3_TYPE",MyRECORD.DATATYPES.TEXT);
	put("PARAM4_DEFAULT",MyRECORD.DATATYPES.TEXT);
	put("PARAM4_DEFINITION",MyRECORD.DATATYPES.TEXT);
	put("PARAM4_DESCRIPTION",MyRECORD.DATATYPES.TEXT);
	put("PARAM4_NAME",MyRECORD.DATATYPES.TEXT);
	put("PARAM4_TYPE",MyRECORD.DATATYPES.TEXT);
	put("PARAM5_DEFAULT",MyRECORD.DATATYPES.TEXT);
	put("PARAM5_DEFINITION",MyRECORD.DATATYPES.TEXT);
	put("PARAM5_DESCRIPTION",MyRECORD.DATATYPES.TEXT);
	put("PARAM5_NAME",MyRECORD.DATATYPES.TEXT);
	put("PARAM5_TYPE",MyRECORD.DATATYPES.TEXT);
	put("REPORT_NAME",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_JASPER_DEF.HM_FIELDS;	
	}

}
