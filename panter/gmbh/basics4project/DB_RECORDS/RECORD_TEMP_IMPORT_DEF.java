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

public class RECORD_TEMP_IMPORT_DEF extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_TEMP_IMPORT_DEF";
    public static String IDFIELD   = "ID_TEMP_IMPORT_DEF";
    

	//erzeugen eines RECORDNEW_JT_TEMP_IMPORT_DEF - felds
	private RECORDNEW_TEMP_IMPORT_DEF   recNEW = null;

    private _TAB  tab = _TAB.temp_import_def;  



	public RECORD_TEMP_IMPORT_DEF() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");
	}


	public RECORD_TEMP_IMPORT_DEF(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");
	}



	public RECORD_TEMP_IMPORT_DEF(RECORD_TEMP_IMPORT_DEF recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEMP_IMPORT_DEF.TABLENAME);
	}


	//2014-04-03
	public RECORD_TEMP_IMPORT_DEF(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEMP_IMPORT_DEF.TABLENAME);
	}




	public RECORD_TEMP_IMPORT_DEF(long lID_Unformated) throws myException
	{
		super("JT_TEMP_IMPORT_DEF","ID_TEMP_IMPORT_DEF",""+lID_Unformated);
		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEMP_IMPORT_DEF.TABLENAME);
	}

	public RECORD_TEMP_IMPORT_DEF(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEMP_IMPORT_DEF", "ID_TEMP_IMPORT_DEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEMP_IMPORT_DEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEMP_IMPORT_DEF.TABLENAME);
	}
	
	

	public RECORD_TEMP_IMPORT_DEF(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_TEMP_IMPORT_DEF","ID_TEMP_IMPORT_DEF",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEMP_IMPORT_DEF.TABLENAME);

	}


	public RECORD_TEMP_IMPORT_DEF(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TEMP_IMPORT_DEF");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEMP_IMPORT_DEF", "ID_TEMP_IMPORT_DEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEMP_IMPORT_DEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEMP_IMPORT_DEF.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_TEMP_IMPORT_DEF();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_TEMP_IMPORT_DEF.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_TEMP_IMPORT_DEF";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_TEMP_IMPORT_DEF_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_TEMP_IMPORT_DEF_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TEMP_IMPORT_DEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TEMP_IMPORT_DEF", bibE2.cTO(), "ID_TEMP_IMPORT_DEF="+this.get_ID_TEMP_IMPORT_DEF_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TEMP_IMPORT_DEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TEMP_IMPORT_DEF", bibE2.cTO(), "ID_TEMP_IMPORT_DEF="+this.get_ID_TEMP_IMPORT_DEF_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_TEMP_IMPORT_DEF WHERE ID_TEMP_IMPORT_DEF="+this.get_ID_TEMP_IMPORT_DEF_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_TEMP_IMPORT_DEF WHERE ID_TEMP_IMPORT_DEF="+this.get_ID_TEMP_IMPORT_DEF_cUF();
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
			if (S.isFull(this.get_ID_TEMP_IMPORT_DEF_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEMP_IMPORT_DEF", "ID_TEMP_IMPORT_DEF="+this.get_ID_TEMP_IMPORT_DEF_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_TEMP_IMPORT_DEF get_RECORDNEW_TEMP_IMPORT_DEF() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_TEMP_IMPORT_DEF();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_TEMP_IMPORT_DEF(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_TEMP_IMPORT_DEF create_Instance() throws myException {
		return new RECORD_TEMP_IMPORT_DEF();
	}
	
	public static RECORD_TEMP_IMPORT_DEF create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_TEMP_IMPORT_DEF(Conn);
    }

	public static RECORD_TEMP_IMPORT_DEF create_Instance(RECORD_TEMP_IMPORT_DEF recordOrig) {
		return new RECORD_TEMP_IMPORT_DEF(recordOrig);
    }

	public static RECORD_TEMP_IMPORT_DEF create_Instance(long lID_Unformated) throws myException {
		return new RECORD_TEMP_IMPORT_DEF(lID_Unformated);
    }

	public static RECORD_TEMP_IMPORT_DEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_TEMP_IMPORT_DEF(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_TEMP_IMPORT_DEF create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_TEMP_IMPORT_DEF(lID_Unformated, Conn);
	}

	public static RECORD_TEMP_IMPORT_DEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_TEMP_IMPORT_DEF(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_TEMP_IMPORT_DEF create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_TEMP_IMPORT_DEF(recordOrig);	    
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
    public RECORD_TEMP_IMPORT_DEF build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_TEMP_IMPORT_DEF WHERE ID_TEMP_IMPORT_DEF="+this.get_ID_TEMP_IMPORT_DEF_cUF());
      }
      //return new RECORD_TEMP_IMPORT_DEF(this.get_cSQL_4_Build());
      RECORD_TEMP_IMPORT_DEF rec = new RECORD_TEMP_IMPORT_DEF();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_TEMP_IMPORT_DEF
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_TEMP_IMPORT_DEF-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_TEMP_IMPORT_DEF get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_TEMP_IMPORT_DEF_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_TEMP_IMPORT_DEF  recNew = new RECORDNEW_TEMP_IMPORT_DEF();
		
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
    public RECORD_TEMP_IMPORT_DEF set_recordNew(RECORDNEW_TEMP_IMPORT_DEF recnew) throws myException {
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
	
	

	public static String FIELD__ANR1_1 = "ANR1_1";
	public static String FIELD__ANR1_2 = "ANR1_2";
	public static String FIELD__ANR2 = "ANR2";
	public static String FIELD__ARTBEZ1 = "ARTBEZ1";
	public static String FIELD__ARTBEZ2 = "ARTBEZ2";
	public static String FIELD__AVV_IN = "AVV_IN";
	public static String FIELD__AVV_OUT = "AVV_OUT";
	public static String FIELD__BASEL = "BASEL";
	public static String FIELD__BEREICHDEF = "BEREICHDEF";
	public static String FIELD__EINHEIT1 = "EINHEIT1";
	public static String FIELD__EINHEIT2 = "EINHEIT2";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GRUPPENDEF = "GRUPPENDEF";
	public static String FIELD__GRUPPE_END = "GRUPPE_END";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TEMP_IMPORT_DEF = "ID_TEMP_IMPORT_DEF";
	public static String FIELD__IST_DIENSTLEISTUNG = "IST_DIENSTLEISTUNG";
	public static String FIELD__IST_GEFAEHRLICH = "IST_GEFAEHRLICH";
	public static String FIELD__IST_PRODUKT = "IST_PRODUKT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SORTFIELD = "SORTFIELD";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__ZOLLTARIF = "ZOLLTARIF";


	public String get_ANR1_1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1_1");
	}

	public String get_ANR1_1_cF() throws myException
	{
		return this.get_FormatedValue("ANR1_1");	
	}

	public String get_ANR1_1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1_1");
	}

	public String get_ANR1_1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1_1",cNotNullValue);
	}

	public String get_ANR1_1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1_1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1_1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1_1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1_1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1_1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_1", calNewValueFormated);
	}
		public String get_ANR1_2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1_2");
	}

	public String get_ANR1_2_cF() throws myException
	{
		return this.get_FormatedValue("ANR1_2");	
	}

	public String get_ANR1_2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1_2");
	}

	public String get_ANR1_2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1_2",cNotNullValue);
	}

	public String get_ANR1_2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1_2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1_2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1_2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1_2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1_2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_2", calNewValueFormated);
	}
		public String get_ANR2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR2");
	}

	public String get_ANR2_cF() throws myException
	{
		return this.get_FormatedValue("ANR2");	
	}

	public String get_ANR2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR2");
	}

	public String get_ANR2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR2",cNotNullValue);
	}

	public String get_ANR2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", calNewValueFormated);
	}
		public String get_ARTBEZ1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1");
	}

	public String get_ARTBEZ1_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ1");	
	}

	public String get_ARTBEZ1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ1");
	}

	public String get_ARTBEZ1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1",cNotNullValue);
	}

	public String get_ARTBEZ1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", calNewValueFormated);
	}
		public String get_ARTBEZ2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2");
	}

	public String get_ARTBEZ2_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ2");	
	}

	public String get_ARTBEZ2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ2");
	}

	public String get_ARTBEZ2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2",cNotNullValue);
	}

	public String get_ARTBEZ2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", calNewValueFormated);
	}
		public String get_AVV_IN_cUF() throws myException
	{
		return this.get_UnFormatedValue("AVV_IN");
	}

	public String get_AVV_IN_cF() throws myException
	{
		return this.get_FormatedValue("AVV_IN");	
	}

	public String get_AVV_IN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AVV_IN");
	}

	public String get_AVV_IN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AVV_IN",cNotNullValue);
	}

	public String get_AVV_IN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AVV_IN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_IN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AVV_IN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AVV_IN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AVV_IN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_IN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AVV_IN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_IN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_IN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_IN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_IN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_IN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_IN", calNewValueFormated);
	}
		public String get_AVV_OUT_cUF() throws myException
	{
		return this.get_UnFormatedValue("AVV_OUT");
	}

	public String get_AVV_OUT_cF() throws myException
	{
		return this.get_FormatedValue("AVV_OUT");	
	}

	public String get_AVV_OUT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AVV_OUT");
	}

	public String get_AVV_OUT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AVV_OUT",cNotNullValue);
	}

	public String get_AVV_OUT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AVV_OUT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AVV_OUT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AVV_OUT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AVV_OUT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AVV_OUT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_OUT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_OUT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_OUT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_OUT", calNewValueFormated);
	}
		public String get_BASEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("BASEL");
	}

	public String get_BASEL_cF() throws myException
	{
		return this.get_FormatedValue("BASEL");	
	}

	public String get_BASEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BASEL");
	}

	public String get_BASEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BASEL",cNotNullValue);
	}

	public String get_BASEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BASEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BASEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BASEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BASEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BASEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL", calNewValueFormated);
	}
		public String get_BEREICHDEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEREICHDEF");
	}

	public String get_BEREICHDEF_cF() throws myException
	{
		return this.get_FormatedValue("BEREICHDEF");	
	}

	public String get_BEREICHDEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEREICHDEF");
	}

	public String get_BEREICHDEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEREICHDEF",cNotNullValue);
	}

	public String get_BEREICHDEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEREICHDEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEREICHDEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEREICHDEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEREICHDEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEREICHDEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEREICHDEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEREICHDEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEREICHDEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEREICHDEF", calNewValueFormated);
	}
		public boolean is_BEREICHDEF_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BEREICHDEF_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BEREICHDEF_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BEREICHDEF_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_EINHEIT1_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINHEIT1");
	}

	public String get_EINHEIT1_cF() throws myException
	{
		return this.get_FormatedValue("EINHEIT1");	
	}

	public String get_EINHEIT1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINHEIT1");
	}

	public String get_EINHEIT1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINHEIT1",cNotNullValue);
	}

	public String get_EINHEIT1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINHEIT1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINHEIT1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINHEIT1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINHEIT1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINHEIT1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT1", calNewValueFormated);
	}
		public String get_EINHEIT2_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINHEIT2");
	}

	public String get_EINHEIT2_cF() throws myException
	{
		return this.get_FormatedValue("EINHEIT2");	
	}

	public String get_EINHEIT2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINHEIT2");
	}

	public String get_EINHEIT2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINHEIT2",cNotNullValue);
	}

	public String get_EINHEIT2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINHEIT2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINHEIT2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINHEIT2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINHEIT2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINHEIT2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT2", calNewValueFormated);
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
		public String get_GRUPPENDEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("GRUPPENDEF");
	}

	public String get_GRUPPENDEF_cF() throws myException
	{
		return this.get_FormatedValue("GRUPPENDEF");	
	}

	public String get_GRUPPENDEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GRUPPENDEF");
	}

	public String get_GRUPPENDEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GRUPPENDEF",cNotNullValue);
	}

	public String get_GRUPPENDEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GRUPPENDEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GRUPPENDEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GRUPPENDEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GRUPPENDEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GRUPPENDEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUPPENDEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUPPENDEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPENDEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUPPENDEF", calNewValueFormated);
	}
		public boolean is_GRUPPENDEF_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GRUPPENDEF_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GRUPPENDEF_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GRUPPENDEF_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GRUPPE_END_cUF() throws myException
	{
		return this.get_UnFormatedValue("GRUPPE_END");
	}

	public String get_GRUPPE_END_cF() throws myException
	{
		return this.get_FormatedValue("GRUPPE_END");	
	}

	public String get_GRUPPE_END_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GRUPPE_END");
	}

	public String get_GRUPPE_END_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GRUPPE_END",cNotNullValue);
	}

	public String get_GRUPPE_END_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GRUPPE_END",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GRUPPE_END", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GRUPPE_END(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GRUPPE_END", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GRUPPE_END", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUPPE_END", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUPPE_END", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUPPE_END_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUPPE_END", calNewValueFormated);
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
	
	
	public String get_ID_TEMP_IMPORT_DEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TEMP_IMPORT_DEF");
	}

	public String get_ID_TEMP_IMPORT_DEF_cF() throws myException
	{
		return this.get_FormatedValue("ID_TEMP_IMPORT_DEF");	
	}

	public String get_ID_TEMP_IMPORT_DEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TEMP_IMPORT_DEF");
	}

	public String get_ID_TEMP_IMPORT_DEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TEMP_IMPORT_DEF",cNotNullValue);
	}

	public String get_ID_TEMP_IMPORT_DEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TEMP_IMPORT_DEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TEMP_IMPORT_DEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TEMP_IMPORT_DEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEMP_IMPORT_DEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TEMP_IMPORT_DEF", calNewValueFormated);
	}
		public Long get_ID_TEMP_IMPORT_DEF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TEMP_IMPORT_DEF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TEMP_IMPORT_DEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TEMP_IMPORT_DEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TEMP_IMPORT_DEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TEMP_IMPORT_DEF").getDoubleValue();
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
	public String get_ID_TEMP_IMPORT_DEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TEMP_IMPORT_DEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TEMP_IMPORT_DEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TEMP_IMPORT_DEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TEMP_IMPORT_DEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TEMP_IMPORT_DEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TEMP_IMPORT_DEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TEMP_IMPORT_DEF").getBigDecimalValue();
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
	
	
	public String get_IST_DIENSTLEISTUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_DIENSTLEISTUNG");
	}

	public String get_IST_DIENSTLEISTUNG_cF() throws myException
	{
		return this.get_FormatedValue("IST_DIENSTLEISTUNG");	
	}

	public String get_IST_DIENSTLEISTUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_DIENSTLEISTUNG");
	}

	public String get_IST_DIENSTLEISTUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_DIENSTLEISTUNG",cNotNullValue);
	}

	public String get_IST_DIENSTLEISTUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_DIENSTLEISTUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_DIENSTLEISTUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_DIENSTLEISTUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_DIENSTLEISTUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_DIENSTLEISTUNG", calNewValueFormated);
	}
		public boolean is_IST_DIENSTLEISTUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_DIENSTLEISTUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_DIENSTLEISTUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_DIENSTLEISTUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_GEFAEHRLICH_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_GEFAEHRLICH");
	}

	public String get_IST_GEFAEHRLICH_cF() throws myException
	{
		return this.get_FormatedValue("IST_GEFAEHRLICH");	
	}

	public String get_IST_GEFAEHRLICH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_GEFAEHRLICH");
	}

	public String get_IST_GEFAEHRLICH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_GEFAEHRLICH",cNotNullValue);
	}

	public String get_IST_GEFAEHRLICH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_GEFAEHRLICH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_GEFAEHRLICH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_GEFAEHRLICH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEFAEHRLICH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEFAEHRLICH", calNewValueFormated);
	}
		public boolean is_IST_GEFAEHRLICH_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEFAEHRLICH_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_GEFAEHRLICH_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEFAEHRLICH_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_PRODUKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_PRODUKT");
	}

	public String get_IST_PRODUKT_cF() throws myException
	{
		return this.get_FormatedValue("IST_PRODUKT");	
	}

	public String get_IST_PRODUKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_PRODUKT");
	}

	public String get_IST_PRODUKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_PRODUKT",cNotNullValue);
	}

	public String get_IST_PRODUKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_PRODUKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PRODUKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PRODUKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PRODUKT", calNewValueFormated);
	}
		public boolean is_IST_PRODUKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_PRODUKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_PRODUKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_PRODUKT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_SORTFIELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTFIELD");
	}

	public String get_SORTFIELD_cF() throws myException
	{
		return this.get_FormatedValue("SORTFIELD");	
	}

	public String get_SORTFIELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTFIELD");
	}

	public String get_SORTFIELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTFIELD",cNotNullValue);
	}

	public String get_SORTFIELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTFIELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTFIELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTFIELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTFIELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTFIELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTFIELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTFIELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTFIELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTFIELD", calNewValueFormated);
	}
		public Long get_SORTFIELD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTFIELD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTFIELD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTFIELD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTFIELD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTFIELD").getDoubleValue();
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
	public String get_SORTFIELD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTFIELD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTFIELD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTFIELD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTFIELD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTFIELD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTFIELD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTFIELD").getBigDecimalValue();
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
	
	
	public String get_ZOLLTARIF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIF");
	}

	public String get_ZOLLTARIF_cF() throws myException
	{
		return this.get_FormatedValue("ZOLLTARIF");	
	}

	public String get_ZOLLTARIF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZOLLTARIF");
	}

	public String get_ZOLLTARIF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIF",cNotNullValue);
	}

	public String get_ZOLLTARIF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZOLLTARIF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZOLLTARIF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZOLLTARIF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZOLLTARIF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIF", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ANR1_1",MyRECORD.DATATYPES.TEXT);
	put("ANR1_2",MyRECORD.DATATYPES.TEXT);
	put("ANR2",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ1",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ2",MyRECORD.DATATYPES.TEXT);
	put("AVV_IN",MyRECORD.DATATYPES.TEXT);
	put("AVV_OUT",MyRECORD.DATATYPES.TEXT);
	put("BASEL",MyRECORD.DATATYPES.TEXT);
	put("BEREICHDEF",MyRECORD.DATATYPES.YESNO);
	put("EINHEIT1",MyRECORD.DATATYPES.TEXT);
	put("EINHEIT2",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GRUPPENDEF",MyRECORD.DATATYPES.YESNO);
	put("GRUPPE_END",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TEMP_IMPORT_DEF",MyRECORD.DATATYPES.NUMBER);
	put("IST_DIENSTLEISTUNG",MyRECORD.DATATYPES.YESNO);
	put("IST_GEFAEHRLICH",MyRECORD.DATATYPES.YESNO);
	put("IST_PRODUKT",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SORTFIELD",MyRECORD.DATATYPES.NUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("ZOLLTARIF",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_TEMP_IMPORT_DEF.HM_FIELDS;	
	}

}
