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

public class RECORD_TABELLENMIGRATION extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JD_TABELLENMIGRATION";
    public static String IDFIELD   = "ID_TABELLENMIGRATION";
    

	//erzeugen eines RECORDNEW_JD_TABELLENMIGRATION - felds
	private RECORDNEW_TABELLENMIGRATION   recNEW = null;

    private _TAB  tab = _TAB.tabellenmigration;  



	public RECORD_TABELLENMIGRATION() throws myException
	{
		super();
		this.set_TABLE_NAME("JD_TABELLENMIGRATION");
	}


	public RECORD_TABELLENMIGRATION(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_TABELLENMIGRATION");
	}



	public RECORD_TABELLENMIGRATION(RECORD_TABELLENMIGRATION recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_TABELLENMIGRATION");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TABELLENMIGRATION.TABLENAME);
	}


	//2014-04-03
	public RECORD_TABELLENMIGRATION(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_TABELLENMIGRATION");
		this.set_Tablename_To_FieldMetaDefs(RECORD_TABELLENMIGRATION.TABLENAME);
	}




	public RECORD_TABELLENMIGRATION(long lID_Unformated) throws myException
	{
		super("JD_TABELLENMIGRATION","ID_TABELLENMIGRATION",""+lID_Unformated);
		this.set_TABLE_NAME("JD_TABELLENMIGRATION");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TABELLENMIGRATION.TABLENAME);
	}

	public RECORD_TABELLENMIGRATION(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JD_TABELLENMIGRATION");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_TABELLENMIGRATION", "ID_TABELLENMIGRATION="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_TABELLENMIGRATION", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TABELLENMIGRATION.TABLENAME);
	}
	
	

	public RECORD_TABELLENMIGRATION(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JD_TABELLENMIGRATION","ID_TABELLENMIGRATION",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JD_TABELLENMIGRATION");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TABELLENMIGRATION.TABLENAME);

	}


	public RECORD_TABELLENMIGRATION(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_TABELLENMIGRATION");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_TABELLENMIGRATION", "ID_TABELLENMIGRATION="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_TABELLENMIGRATION", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TABELLENMIGRATION.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_TABELLENMIGRATION();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_TABELLENMIGRATION.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_TABELLENMIGRATION";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_TABELLENMIGRATION_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_TABELLENMIGRATION_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TABELLENMIGRATION was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_TABELLENMIGRATION", bibE2.cTO(), "ID_TABELLENMIGRATION="+this.get_ID_TABELLENMIGRATION_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TABELLENMIGRATION was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_TABELLENMIGRATION", bibE2.cTO(), "ID_TABELLENMIGRATION="+this.get_ID_TABELLENMIGRATION_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JD_TABELLENMIGRATION WHERE ID_TABELLENMIGRATION="+this.get_ID_TABELLENMIGRATION_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JD_TABELLENMIGRATION WHERE ID_TABELLENMIGRATION="+this.get_ID_TABELLENMIGRATION_cUF();
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
			if (S.isFull(this.get_ID_TABELLENMIGRATION_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_TABELLENMIGRATION", "ID_TABELLENMIGRATION="+this.get_ID_TABELLENMIGRATION_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_TABELLENMIGRATION get_RECORDNEW_TABELLENMIGRATION() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_TABELLENMIGRATION();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_TABELLENMIGRATION(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_TABELLENMIGRATION create_Instance() throws myException {
		return new RECORD_TABELLENMIGRATION();
	}
	
	public static RECORD_TABELLENMIGRATION create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_TABELLENMIGRATION(Conn);
    }

	public static RECORD_TABELLENMIGRATION create_Instance(RECORD_TABELLENMIGRATION recordOrig) {
		return new RECORD_TABELLENMIGRATION(recordOrig);
    }

	public static RECORD_TABELLENMIGRATION create_Instance(long lID_Unformated) throws myException {
		return new RECORD_TABELLENMIGRATION(lID_Unformated);
    }

	public static RECORD_TABELLENMIGRATION create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_TABELLENMIGRATION(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_TABELLENMIGRATION create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_TABELLENMIGRATION(lID_Unformated, Conn);
	}

	public static RECORD_TABELLENMIGRATION create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_TABELLENMIGRATION(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_TABELLENMIGRATION create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_TABELLENMIGRATION(recordOrig);	    
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
    public RECORD_TABELLENMIGRATION build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JD_TABELLENMIGRATION WHERE ID_TABELLENMIGRATION="+this.get_ID_TABELLENMIGRATION_cUF());
      }
      //return new RECORD_TABELLENMIGRATION(this.get_cSQL_4_Build());
      RECORD_TABELLENMIGRATION rec = new RECORD_TABELLENMIGRATION();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_TABELLENMIGRATION
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_TABELLENMIGRATION-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_TABELLENMIGRATION get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_TABELLENMIGRATION_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_TABELLENMIGRATION  recNew = new RECORDNEW_TABELLENMIGRATION();
		
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
    public RECORD_TABELLENMIGRATION set_recordNew(RECORDNEW_TABELLENMIGRATION recnew) throws myException {
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
	
	

	public static String FIELD__ADDIEREN = "ADDIEREN";
	public static String FIELD__BESCHREIBUNG1 = "BESCHREIBUNG1";
	public static String FIELD__BESCHREIBUNG2 = "BESCHREIBUNG2";
	public static String FIELD__DF_DEFAULT = "DF_DEFAULT";
	public static String FIELD__DF_FELDLAENGE = "DF_FELDLAENGE";
	public static String FIELD__DF_FELDNACHKOMMA = "DF_FELDNACHKOMMA";
	public static String FIELD__DF_FELDNAME = "DF_FELDNAME";
	public static String FIELD__DF_FELDTYP = "DF_FELDTYP";
	public static String FIELD__DF_NULLOK = "DF_NULLOK";
	public static String FIELD__DF_TABELLE = "DF_TABELLE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TABELLENMIGRATION = "ID_TABELLENMIGRATION";
	public static String FIELD__JAVA_DEFAULT = "JAVA_DEFAULT";
	public static String FIELD__JAVA_FELDLAENGE = "JAVA_FELDLAENGE";
	public static String FIELD__JAVA_FELDNACHKOMMA = "JAVA_FELDNACHKOMMA";
	public static String FIELD__JAVA_FELDNAME = "JAVA_FELDNAME";
	public static String FIELD__JAVA_FELDTYP = "JAVA_FELDTYP";
	public static String FIELD__JAVA_INDEXFELD = "JAVA_INDEXFELD";
	public static String FIELD__JAVA_NULLOK = "JAVA_NULLOK";
	public static String FIELD__JAVA_TABELLE = "JAVA_TABELLE";
	public static String FIELD__KONSTANTE = "KONSTANTE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_ADDIEREN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ADDIEREN");
	}

	public String get_ADDIEREN_cF() throws myException
	{
		return this.get_FormatedValue("ADDIEREN");	
	}

	public String get_ADDIEREN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ADDIEREN");
	}

	public String get_ADDIEREN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ADDIEREN",cNotNullValue);
	}

	public String get_ADDIEREN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ADDIEREN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ADDIEREN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ADDIEREN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ADDIEREN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ADDIEREN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADDIEREN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADDIEREN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADDIEREN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADDIEREN", calNewValueFormated);
	}
		public String get_BESCHREIBUNG1_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG1");
	}

	public String get_BESCHREIBUNG1_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG1");	
	}

	public String get_BESCHREIBUNG1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIBUNG1");
	}

	public String get_BESCHREIBUNG1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG1",cNotNullValue);
	}

	public String get_BESCHREIBUNG1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIBUNG1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIBUNG1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG1", calNewValueFormated);
	}
		public String get_BESCHREIBUNG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG2");
	}

	public String get_BESCHREIBUNG2_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG2");	
	}

	public String get_BESCHREIBUNG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIBUNG2");
	}

	public String get_BESCHREIBUNG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG2",cNotNullValue);
	}

	public String get_BESCHREIBUNG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIBUNG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIBUNG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG2", calNewValueFormated);
	}
		public String get_DF_DEFAULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("DF_DEFAULT");
	}

	public String get_DF_DEFAULT_cF() throws myException
	{
		return this.get_FormatedValue("DF_DEFAULT");	
	}

	public String get_DF_DEFAULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DF_DEFAULT");
	}

	public String get_DF_DEFAULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DF_DEFAULT",cNotNullValue);
	}

	public String get_DF_DEFAULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DF_DEFAULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DF_DEFAULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DF_DEFAULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DF_DEFAULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DF_DEFAULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_DEFAULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_DEFAULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_DEFAULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_DEFAULT", calNewValueFormated);
	}
		public String get_DF_FELDLAENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DF_FELDLAENGE");
	}

	public String get_DF_FELDLAENGE_cF() throws myException
	{
		return this.get_FormatedValue("DF_FELDLAENGE");	
	}

	public String get_DF_FELDLAENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DF_FELDLAENGE");
	}

	public String get_DF_FELDLAENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DF_FELDLAENGE",cNotNullValue);
	}

	public String get_DF_FELDLAENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DF_FELDLAENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DF_FELDLAENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DF_FELDLAENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DF_FELDLAENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDLAENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDLAENGE", calNewValueFormated);
	}
		public Long get_DF_FELDLAENGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DF_FELDLAENGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DF_FELDLAENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DF_FELDLAENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DF_FELDLAENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DF_FELDLAENGE").getDoubleValue();
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
	public String get_DF_FELDLAENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DF_FELDLAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_DF_FELDLAENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DF_FELDLAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_DF_FELDLAENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DF_FELDLAENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DF_FELDLAENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DF_FELDLAENGE").getBigDecimalValue();
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
	
	
	public String get_DF_FELDNACHKOMMA_cUF() throws myException
	{
		return this.get_UnFormatedValue("DF_FELDNACHKOMMA");
	}

	public String get_DF_FELDNACHKOMMA_cF() throws myException
	{
		return this.get_FormatedValue("DF_FELDNACHKOMMA");	
	}

	public String get_DF_FELDNACHKOMMA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DF_FELDNACHKOMMA");
	}

	public String get_DF_FELDNACHKOMMA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DF_FELDNACHKOMMA",cNotNullValue);
	}

	public String get_DF_FELDNACHKOMMA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DF_FELDNACHKOMMA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DF_FELDNACHKOMMA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DF_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DF_FELDNACHKOMMA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNACHKOMMA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDNACHKOMMA", calNewValueFormated);
	}
		public Long get_DF_FELDNACHKOMMA_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DF_FELDNACHKOMMA").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DF_FELDNACHKOMMA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DF_FELDNACHKOMMA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DF_FELDNACHKOMMA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DF_FELDNACHKOMMA").getDoubleValue();
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
	public String get_DF_FELDNACHKOMMA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DF_FELDNACHKOMMA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_DF_FELDNACHKOMMA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DF_FELDNACHKOMMA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_DF_FELDNACHKOMMA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DF_FELDNACHKOMMA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DF_FELDNACHKOMMA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DF_FELDNACHKOMMA").getBigDecimalValue();
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
	
	
	public String get_DF_FELDNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("DF_FELDNAME");
	}

	public String get_DF_FELDNAME_cF() throws myException
	{
		return this.get_FormatedValue("DF_FELDNAME");	
	}

	public String get_DF_FELDNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DF_FELDNAME");
	}

	public String get_DF_FELDNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DF_FELDNAME",cNotNullValue);
	}

	public String get_DF_FELDNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DF_FELDNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DF_FELDNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DF_FELDNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DF_FELDNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DF_FELDNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDNAME", calNewValueFormated);
	}
		public String get_DF_FELDTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("DF_FELDTYP");
	}

	public String get_DF_FELDTYP_cF() throws myException
	{
		return this.get_FormatedValue("DF_FELDTYP");	
	}

	public String get_DF_FELDTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DF_FELDTYP");
	}

	public String get_DF_FELDTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DF_FELDTYP",cNotNullValue);
	}

	public String get_DF_FELDTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DF_FELDTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DF_FELDTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DF_FELDTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DF_FELDTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DF_FELDTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_FELDTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_FELDTYP", calNewValueFormated);
	}
		public String get_DF_NULLOK_cUF() throws myException
	{
		return this.get_UnFormatedValue("DF_NULLOK");
	}

	public String get_DF_NULLOK_cF() throws myException
	{
		return this.get_FormatedValue("DF_NULLOK");	
	}

	public String get_DF_NULLOK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DF_NULLOK");
	}

	public String get_DF_NULLOK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DF_NULLOK",cNotNullValue);
	}

	public String get_DF_NULLOK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DF_NULLOK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DF_NULLOK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DF_NULLOK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DF_NULLOK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DF_NULLOK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_NULLOK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_NULLOK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_NULLOK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_NULLOK", calNewValueFormated);
	}
		public boolean is_DF_NULLOK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DF_NULLOK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DF_NULLOK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DF_NULLOK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DF_TABELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DF_TABELLE");
	}

	public String get_DF_TABELLE_cF() throws myException
	{
		return this.get_FormatedValue("DF_TABELLE");	
	}

	public String get_DF_TABELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DF_TABELLE");
	}

	public String get_DF_TABELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DF_TABELLE",cNotNullValue);
	}

	public String get_DF_TABELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DF_TABELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DF_TABELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DF_TABELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DF_TABELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DF_TABELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_TABELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_TABELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DF_TABELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DF_TABELLE", calNewValueFormated);
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
	
	
	public String get_ID_TABELLENMIGRATION_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TABELLENMIGRATION");
	}

	public String get_ID_TABELLENMIGRATION_cF() throws myException
	{
		return this.get_FormatedValue("ID_TABELLENMIGRATION");	
	}

	public String get_ID_TABELLENMIGRATION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TABELLENMIGRATION");
	}

	public String get_ID_TABELLENMIGRATION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TABELLENMIGRATION",cNotNullValue);
	}

	public String get_ID_TABELLENMIGRATION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TABELLENMIGRATION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TABELLENMIGRATION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TABELLENMIGRATION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TABELLENMIGRATION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABELLENMIGRATION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABELLENMIGRATION", calNewValueFormated);
	}
		public Long get_ID_TABELLENMIGRATION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TABELLENMIGRATION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TABELLENMIGRATION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TABELLENMIGRATION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TABELLENMIGRATION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TABELLENMIGRATION").getDoubleValue();
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
	public String get_ID_TABELLENMIGRATION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TABELLENMIGRATION_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TABELLENMIGRATION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TABELLENMIGRATION_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TABELLENMIGRATION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TABELLENMIGRATION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TABELLENMIGRATION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TABELLENMIGRATION").getBigDecimalValue();
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
	
	
	public String get_JAVA_DEFAULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_DEFAULT");
	}

	public String get_JAVA_DEFAULT_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_DEFAULT");	
	}

	public String get_JAVA_DEFAULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_DEFAULT");
	}

	public String get_JAVA_DEFAULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_DEFAULT",cNotNullValue);
	}

	public String get_JAVA_DEFAULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_DEFAULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_DEFAULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_DEFAULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_DEFAULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_DEFAULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_DEFAULT", calNewValueFormated);
	}
		public String get_JAVA_FELDLAENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDLAENGE");
	}

	public String get_JAVA_FELDLAENGE_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_FELDLAENGE");	
	}

	public String get_JAVA_FELDLAENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_FELDLAENGE");
	}

	public String get_JAVA_FELDLAENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDLAENGE",cNotNullValue);
	}

	public String get_JAVA_FELDLAENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_FELDLAENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_FELDLAENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDLAENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_FELDLAENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDLAENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDLAENGE", calNewValueFormated);
	}
		public Long get_JAVA_FELDLAENGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("JAVA_FELDLAENGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_JAVA_FELDLAENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("JAVA_FELDLAENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_JAVA_FELDLAENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("JAVA_FELDLAENGE").getDoubleValue();
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
	public String get_JAVA_FELDLAENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_JAVA_FELDLAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_JAVA_FELDLAENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_JAVA_FELDLAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_JAVA_FELDLAENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("JAVA_FELDLAENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_JAVA_FELDLAENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("JAVA_FELDLAENGE").getBigDecimalValue();
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
	
	
	public String get_JAVA_FELDNACHKOMMA_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDNACHKOMMA");
	}

	public String get_JAVA_FELDNACHKOMMA_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_FELDNACHKOMMA");	
	}

	public String get_JAVA_FELDNACHKOMMA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_FELDNACHKOMMA");
	}

	public String get_JAVA_FELDNACHKOMMA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDNACHKOMMA",cNotNullValue);
	}

	public String get_JAVA_FELDNACHKOMMA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_FELDNACHKOMMA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDNACHKOMMA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_FELDNACHKOMMA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNACHKOMMA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDNACHKOMMA", calNewValueFormated);
	}
		public Long get_JAVA_FELDNACHKOMMA_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("JAVA_FELDNACHKOMMA").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_JAVA_FELDNACHKOMMA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("JAVA_FELDNACHKOMMA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_JAVA_FELDNACHKOMMA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("JAVA_FELDNACHKOMMA").getDoubleValue();
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
	public String get_JAVA_FELDNACHKOMMA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_JAVA_FELDNACHKOMMA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_JAVA_FELDNACHKOMMA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_JAVA_FELDNACHKOMMA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_JAVA_FELDNACHKOMMA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("JAVA_FELDNACHKOMMA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_JAVA_FELDNACHKOMMA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("JAVA_FELDNACHKOMMA").getBigDecimalValue();
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
	
	
	public String get_JAVA_FELDNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDNAME");
	}

	public String get_JAVA_FELDNAME_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_FELDNAME");	
	}

	public String get_JAVA_FELDNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_FELDNAME");
	}

	public String get_JAVA_FELDNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDNAME",cNotNullValue);
	}

	public String get_JAVA_FELDNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_FELDNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_FELDNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_FELDNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDNAME", calNewValueFormated);
	}
		public String get_JAVA_FELDTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDTYP");
	}

	public String get_JAVA_FELDTYP_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_FELDTYP");	
	}

	public String get_JAVA_FELDTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_FELDTYP");
	}

	public String get_JAVA_FELDTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_FELDTYP",cNotNullValue);
	}

	public String get_JAVA_FELDTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_FELDTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_FELDTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_FELDTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_FELDTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_FELDTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_FELDTYP", calNewValueFormated);
	}
		public String get_JAVA_INDEXFELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_INDEXFELD");
	}

	public String get_JAVA_INDEXFELD_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_INDEXFELD");	
	}

	public String get_JAVA_INDEXFELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_INDEXFELD");
	}

	public String get_JAVA_INDEXFELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_INDEXFELD",cNotNullValue);
	}

	public String get_JAVA_INDEXFELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_INDEXFELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_INDEXFELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_INDEXFELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_INDEXFELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_INDEXFELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_INDEXFELD", calNewValueFormated);
	}
		public boolean is_JAVA_INDEXFELD_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_JAVA_INDEXFELD_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_JAVA_INDEXFELD_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_JAVA_INDEXFELD_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_JAVA_NULLOK_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_NULLOK");
	}

	public String get_JAVA_NULLOK_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_NULLOK");	
	}

	public String get_JAVA_NULLOK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_NULLOK");
	}

	public String get_JAVA_NULLOK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_NULLOK",cNotNullValue);
	}

	public String get_JAVA_NULLOK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_NULLOK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_NULLOK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_NULLOK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_NULLOK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_NULLOK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_NULLOK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_NULLOK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_NULLOK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_NULLOK", calNewValueFormated);
	}
		public boolean is_JAVA_NULLOK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_JAVA_NULLOK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_JAVA_NULLOK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_JAVA_NULLOK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_JAVA_TABELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAVA_TABELLE");
	}

	public String get_JAVA_TABELLE_cF() throws myException
	{
		return this.get_FormatedValue("JAVA_TABELLE");	
	}

	public String get_JAVA_TABELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAVA_TABELLE");
	}

	public String get_JAVA_TABELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAVA_TABELLE",cNotNullValue);
	}

	public String get_JAVA_TABELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAVA_TABELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAVA_TABELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAVA_TABELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAVA_TABELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAVA_TABELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_TABELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_TABELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAVA_TABELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAVA_TABELLE", calNewValueFormated);
	}
		public String get_KONSTANTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KONSTANTE");
	}

	public String get_KONSTANTE_cF() throws myException
	{
		return this.get_FormatedValue("KONSTANTE");	
	}

	public String get_KONSTANTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KONSTANTE");
	}

	public String get_KONSTANTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KONSTANTE",cNotNullValue);
	}

	public String get_KONSTANTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KONSTANTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KONSTANTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KONSTANTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KONSTANTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KONSTANTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONSTANTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONSTANTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONSTANTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONSTANTE", calNewValueFormated);
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
	
	


	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ADDIEREN",MyRECORD.DATATYPES.TEXT);
	put("BESCHREIBUNG1",MyRECORD.DATATYPES.TEXT);
	put("BESCHREIBUNG2",MyRECORD.DATATYPES.TEXT);
	put("DF_DEFAULT",MyRECORD.DATATYPES.TEXT);
	put("DF_FELDLAENGE",MyRECORD.DATATYPES.NUMBER);
	put("DF_FELDNACHKOMMA",MyRECORD.DATATYPES.NUMBER);
	put("DF_FELDNAME",MyRECORD.DATATYPES.TEXT);
	put("DF_FELDTYP",MyRECORD.DATATYPES.TEXT);
	put("DF_NULLOK",MyRECORD.DATATYPES.YESNO);
	put("DF_TABELLE",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TABELLENMIGRATION",MyRECORD.DATATYPES.NUMBER);
	put("JAVA_DEFAULT",MyRECORD.DATATYPES.TEXT);
	put("JAVA_FELDLAENGE",MyRECORD.DATATYPES.NUMBER);
	put("JAVA_FELDNACHKOMMA",MyRECORD.DATATYPES.NUMBER);
	put("JAVA_FELDNAME",MyRECORD.DATATYPES.TEXT);
	put("JAVA_FELDTYP",MyRECORD.DATATYPES.TEXT);
	put("JAVA_INDEXFELD",MyRECORD.DATATYPES.YESNO);
	put("JAVA_NULLOK",MyRECORD.DATATYPES.YESNO);
	put("JAVA_TABELLE",MyRECORD.DATATYPES.TEXT);
	put("KONSTANTE",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_TABELLENMIGRATION.HM_FIELDS;	
	}

}
