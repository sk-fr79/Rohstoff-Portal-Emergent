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

public class RECORD_FIBU_FORMULAR extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_FIBU_FORMULAR";
    public static String IDFIELD   = "ID_FIBU_FORMULAR";
    

	//erzeugen eines RECORDNEW_JT_FIBU_FORMULAR - felds
	private RECORDNEW_FIBU_FORMULAR   recNEW = null;

    private _TAB  tab = _TAB.fibu_formular;  



	public RECORD_FIBU_FORMULAR() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_FIBU_FORMULAR");
	}


	public RECORD_FIBU_FORMULAR(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FIBU_FORMULAR");
	}



	public RECORD_FIBU_FORMULAR(RECORD_FIBU_FORMULAR recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FIBU_FORMULAR");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU_FORMULAR.TABLENAME);
	}


	//2014-04-03
	public RECORD_FIBU_FORMULAR(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FIBU_FORMULAR");
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU_FORMULAR.TABLENAME);
	}




	public RECORD_FIBU_FORMULAR(long lID_Unformated) throws myException
	{
		super("JT_FIBU_FORMULAR","ID_FIBU_FORMULAR",""+lID_Unformated);
		this.set_TABLE_NAME("JT_FIBU_FORMULAR");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU_FORMULAR.TABLENAME);
	}

	public RECORD_FIBU_FORMULAR(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_FIBU_FORMULAR");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU_FORMULAR", "ID_FIBU_FORMULAR="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU_FORMULAR", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU_FORMULAR.TABLENAME);
	}
	
	

	public RECORD_FIBU_FORMULAR(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_FIBU_FORMULAR","ID_FIBU_FORMULAR",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_FIBU_FORMULAR");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU_FORMULAR.TABLENAME);

	}


	public RECORD_FIBU_FORMULAR(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FIBU_FORMULAR");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU_FORMULAR", "ID_FIBU_FORMULAR="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU_FORMULAR", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU_FORMULAR.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_FIBU_FORMULAR();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_FIBU_FORMULAR.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_FIBU_FORMULAR";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_FIBU_FORMULAR_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_FIBU_FORMULAR_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FIBU_FORMULAR was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FIBU_FORMULAR", bibE2.cTO(), "ID_FIBU_FORMULAR="+this.get_ID_FIBU_FORMULAR_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FIBU_FORMULAR was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FIBU_FORMULAR", bibE2.cTO(), "ID_FIBU_FORMULAR="+this.get_ID_FIBU_FORMULAR_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_FIBU_FORMULAR WHERE ID_FIBU_FORMULAR="+this.get_ID_FIBU_FORMULAR_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_FIBU_FORMULAR WHERE ID_FIBU_FORMULAR="+this.get_ID_FIBU_FORMULAR_cUF();
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
			if (S.isFull(this.get_ID_FIBU_FORMULAR_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU_FORMULAR", "ID_FIBU_FORMULAR="+this.get_ID_FIBU_FORMULAR_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_FIBU_FORMULAR get_RECORDNEW_FIBU_FORMULAR() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_FIBU_FORMULAR();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_FIBU_FORMULAR(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_FIBU_FORMULAR create_Instance() throws myException {
		return new RECORD_FIBU_FORMULAR();
	}
	
	public static RECORD_FIBU_FORMULAR create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_FIBU_FORMULAR(Conn);
    }

	public static RECORD_FIBU_FORMULAR create_Instance(RECORD_FIBU_FORMULAR recordOrig) {
		return new RECORD_FIBU_FORMULAR(recordOrig);
    }

	public static RECORD_FIBU_FORMULAR create_Instance(long lID_Unformated) throws myException {
		return new RECORD_FIBU_FORMULAR(lID_Unformated);
    }

	public static RECORD_FIBU_FORMULAR create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_FIBU_FORMULAR(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_FIBU_FORMULAR create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_FIBU_FORMULAR(lID_Unformated, Conn);
	}

	public static RECORD_FIBU_FORMULAR create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_FIBU_FORMULAR(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_FIBU_FORMULAR create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_FIBU_FORMULAR(recordOrig);	    
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
    public RECORD_FIBU_FORMULAR build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_FIBU_FORMULAR WHERE ID_FIBU_FORMULAR="+this.get_ID_FIBU_FORMULAR_cUF());
      }
      //return new RECORD_FIBU_FORMULAR(this.get_cSQL_4_Build());
      RECORD_FIBU_FORMULAR rec = new RECORD_FIBU_FORMULAR();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_FIBU_FORMULAR
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_FIBU_FORMULAR-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_FIBU_FORMULAR get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_FIBU_FORMULAR_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_FIBU_FORMULAR  recNew = new RECORDNEW_FIBU_FORMULAR();
		
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
    public RECORD_FIBU_FORMULAR set_recordNew(RECORDNEW_FIBU_FORMULAR recnew) throws myException {
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
	
	

	public static String FIELD__DROPDOWNTEXT = "DROPDOWNTEXT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FORMULARNAME = "FORMULARNAME";
	public static String FIELD__FORMULARTYP = "FORMULARTYP";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GUT_NEGATIV = "GUT_NEGATIV";
	public static String FIELD__GUT_NEGATIV_STORNO = "GUT_NEGATIV_STORNO";
	public static String FIELD__GUT_POSITIV = "GUT_POSITIV";
	public static String FIELD__GUT_POSITIV_STORNO = "GUT_POSITIV_STORNO";
	public static String FIELD__ID_FIBU_FORMULAR = "ID_FIBU_FORMULAR";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__RECH_NEGATIV = "RECH_NEGATIV";
	public static String FIELD__RECH_NEGATIV_STORNO = "RECH_NEGATIV_STORNO";
	public static String FIELD__RECH_POSITIV = "RECH_POSITIV";
	public static String FIELD__RECH_POSITIV_STORNO = "RECH_POSITIV_STORNO";
	public static String FIELD__SCHECK = "SCHECK";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__ZAHLUNGSAUSGANG = "ZAHLUNGSAUSGANG";
	public static String FIELD__ZAHLUNGSEINGANG = "ZAHLUNGSEINGANG";


	public String get_DROPDOWNTEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("DROPDOWNTEXT");
	}

	public String get_DROPDOWNTEXT_cF() throws myException
	{
		return this.get_FormatedValue("DROPDOWNTEXT");	
	}

	public String get_DROPDOWNTEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DROPDOWNTEXT");
	}

	public String get_DROPDOWNTEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DROPDOWNTEXT",cNotNullValue);
	}

	public String get_DROPDOWNTEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DROPDOWNTEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DROPDOWNTEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DROPDOWNTEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DROPDOWNTEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWNTEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWNTEXT", calNewValueFormated);
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
		public String get_FORMULARNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("FORMULARNAME");
	}

	public String get_FORMULARNAME_cF() throws myException
	{
		return this.get_FormatedValue("FORMULARNAME");	
	}

	public String get_FORMULARNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FORMULARNAME");
	}

	public String get_FORMULARNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FORMULARNAME",cNotNullValue);
	}

	public String get_FORMULARNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FORMULARNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FORMULARNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FORMULARNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FORMULARNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FORMULARNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARNAME", calNewValueFormated);
	}
		public String get_FORMULARTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FORMULARTYP");
	}

	public String get_FORMULARTYP_cF() throws myException
	{
		return this.get_FormatedValue("FORMULARTYP");	
	}

	public String get_FORMULARTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FORMULARTYP");
	}

	public String get_FORMULARTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FORMULARTYP",cNotNullValue);
	}

	public String get_FORMULARTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FORMULARTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FORMULARTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FORMULARTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FORMULARTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FORMULARTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTYP", calNewValueFormated);
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
		public String get_GUT_NEGATIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUT_NEGATIV");
	}

	public String get_GUT_NEGATIV_cF() throws myException
	{
		return this.get_FormatedValue("GUT_NEGATIV");	
	}

	public String get_GUT_NEGATIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUT_NEGATIV");
	}

	public String get_GUT_NEGATIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUT_NEGATIV",cNotNullValue);
	}

	public String get_GUT_NEGATIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUT_NEGATIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUT_NEGATIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUT_NEGATIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUT_NEGATIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_NEGATIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_NEGATIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_NEGATIV", calNewValueFormated);
	}
		public boolean is_GUT_NEGATIV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_NEGATIV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GUT_NEGATIV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_NEGATIV_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GUT_NEGATIV_STORNO_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUT_NEGATIV_STORNO");
	}

	public String get_GUT_NEGATIV_STORNO_cF() throws myException
	{
		return this.get_FormatedValue("GUT_NEGATIV_STORNO");	
	}

	public String get_GUT_NEGATIV_STORNO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUT_NEGATIV_STORNO");
	}

	public String get_GUT_NEGATIV_STORNO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUT_NEGATIV_STORNO",cNotNullValue);
	}

	public String get_GUT_NEGATIV_STORNO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUT_NEGATIV_STORNO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUT_NEGATIV_STORNO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUT_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUT_NEGATIV_STORNO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_NEGATIV_STORNO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_NEGATIV_STORNO", calNewValueFormated);
	}
		public boolean is_GUT_NEGATIV_STORNO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_NEGATIV_STORNO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GUT_NEGATIV_STORNO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_NEGATIV_STORNO_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GUT_POSITIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUT_POSITIV");
	}

	public String get_GUT_POSITIV_cF() throws myException
	{
		return this.get_FormatedValue("GUT_POSITIV");	
	}

	public String get_GUT_POSITIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUT_POSITIV");
	}

	public String get_GUT_POSITIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUT_POSITIV",cNotNullValue);
	}

	public String get_GUT_POSITIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUT_POSITIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUT_POSITIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUT_POSITIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUT_POSITIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUT_POSITIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_POSITIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_POSITIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_POSITIV", calNewValueFormated);
	}
		public boolean is_GUT_POSITIV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_POSITIV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GUT_POSITIV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_POSITIV_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GUT_POSITIV_STORNO_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUT_POSITIV_STORNO");
	}

	public String get_GUT_POSITIV_STORNO_cF() throws myException
	{
		return this.get_FormatedValue("GUT_POSITIV_STORNO");	
	}

	public String get_GUT_POSITIV_STORNO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUT_POSITIV_STORNO");
	}

	public String get_GUT_POSITIV_STORNO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUT_POSITIV_STORNO",cNotNullValue);
	}

	public String get_GUT_POSITIV_STORNO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUT_POSITIV_STORNO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUT_POSITIV_STORNO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUT_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUT_POSITIV_STORNO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUT_POSITIV_STORNO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUT_POSITIV_STORNO", calNewValueFormated);
	}
		public boolean is_GUT_POSITIV_STORNO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_POSITIV_STORNO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GUT_POSITIV_STORNO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUT_POSITIV_STORNO_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_FIBU_FORMULAR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU_FORMULAR");
	}

	public String get_ID_FIBU_FORMULAR_cF() throws myException
	{
		return this.get_FormatedValue("ID_FIBU_FORMULAR");	
	}

	public String get_ID_FIBU_FORMULAR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_FIBU_FORMULAR");
	}

	public String get_ID_FIBU_FORMULAR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU_FORMULAR",cNotNullValue);
	}

	public String get_ID_FIBU_FORMULAR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_FIBU_FORMULAR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_FIBU_FORMULAR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_FORMULAR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_FIBU_FORMULAR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_FORMULAR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_FORMULAR", calNewValueFormated);
	}
		public Long get_ID_FIBU_FORMULAR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_FIBU_FORMULAR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_FIBU_FORMULAR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_FIBU_FORMULAR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_FIBU_FORMULAR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_FIBU_FORMULAR").getDoubleValue();
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
	public String get_ID_FIBU_FORMULAR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_FORMULAR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_FIBU_FORMULAR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_FORMULAR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_FIBU_FORMULAR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU_FORMULAR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_FIBU_FORMULAR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU_FORMULAR").getBigDecimalValue();
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
		public String get_RECH_NEGATIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECH_NEGATIV");
	}

	public String get_RECH_NEGATIV_cF() throws myException
	{
		return this.get_FormatedValue("RECH_NEGATIV");	
	}

	public String get_RECH_NEGATIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECH_NEGATIV");
	}

	public String get_RECH_NEGATIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECH_NEGATIV",cNotNullValue);
	}

	public String get_RECH_NEGATIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECH_NEGATIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECH_NEGATIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECH_NEGATIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECH_NEGATIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_NEGATIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_NEGATIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_NEGATIV", calNewValueFormated);
	}
		public boolean is_RECH_NEGATIV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_NEGATIV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RECH_NEGATIV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_NEGATIV_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RECH_NEGATIV_STORNO_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECH_NEGATIV_STORNO");
	}

	public String get_RECH_NEGATIV_STORNO_cF() throws myException
	{
		return this.get_FormatedValue("RECH_NEGATIV_STORNO");	
	}

	public String get_RECH_NEGATIV_STORNO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECH_NEGATIV_STORNO");
	}

	public String get_RECH_NEGATIV_STORNO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECH_NEGATIV_STORNO",cNotNullValue);
	}

	public String get_RECH_NEGATIV_STORNO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECH_NEGATIV_STORNO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECH_NEGATIV_STORNO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECH_NEGATIV_STORNO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECH_NEGATIV_STORNO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_NEGATIV_STORNO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_NEGATIV_STORNO", calNewValueFormated);
	}
		public boolean is_RECH_NEGATIV_STORNO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_NEGATIV_STORNO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RECH_NEGATIV_STORNO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_NEGATIV_STORNO_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RECH_POSITIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECH_POSITIV");
	}

	public String get_RECH_POSITIV_cF() throws myException
	{
		return this.get_FormatedValue("RECH_POSITIV");	
	}

	public String get_RECH_POSITIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECH_POSITIV");
	}

	public String get_RECH_POSITIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECH_POSITIV",cNotNullValue);
	}

	public String get_RECH_POSITIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECH_POSITIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECH_POSITIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECH_POSITIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECH_POSITIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECH_POSITIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_POSITIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_POSITIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_POSITIV", calNewValueFormated);
	}
		public boolean is_RECH_POSITIV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_POSITIV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RECH_POSITIV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_POSITIV_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RECH_POSITIV_STORNO_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECH_POSITIV_STORNO");
	}

	public String get_RECH_POSITIV_STORNO_cF() throws myException
	{
		return this.get_FormatedValue("RECH_POSITIV_STORNO");	
	}

	public String get_RECH_POSITIV_STORNO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECH_POSITIV_STORNO");
	}

	public String get_RECH_POSITIV_STORNO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECH_POSITIV_STORNO",cNotNullValue);
	}

	public String get_RECH_POSITIV_STORNO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECH_POSITIV_STORNO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECH_POSITIV_STORNO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECH_POSITIV_STORNO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECH_POSITIV_STORNO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECH_POSITIV_STORNO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECH_POSITIV_STORNO", calNewValueFormated);
	}
		public boolean is_RECH_POSITIV_STORNO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_POSITIV_STORNO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RECH_POSITIV_STORNO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECH_POSITIV_STORNO_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SCHECK_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECK");
	}

	public String get_SCHECK_cF() throws myException
	{
		return this.get_FormatedValue("SCHECK");	
	}

	public String get_SCHECK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECK");
	}

	public String get_SCHECK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECK",cNotNullValue);
	}

	public String get_SCHECK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECK", calNewValueFormated);
	}
		public boolean is_SCHECK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SCHECK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SCHECK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SCHECK_cUF_NN("N").equals("N"))
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
	
	
	public String get_ZAHLUNGSAUSGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSAUSGANG");
	}

	public String get_ZAHLUNGSAUSGANG_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSAUSGANG");	
	}

	public String get_ZAHLUNGSAUSGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSAUSGANG");
	}

	public String get_ZAHLUNGSAUSGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSAUSGANG",cNotNullValue);
	}

	public String get_ZAHLUNGSAUSGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSAUSGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSAUSGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSAUSGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSAUSGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSAUSGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSAUSGANG", calNewValueFormated);
	}
		public boolean is_ZAHLUNGSAUSGANG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZAHLUNGSAUSGANG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZAHLUNGSAUSGANG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZAHLUNGSAUSGANG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZAHLUNGSEINGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSEINGANG");
	}

	public String get_ZAHLUNGSEINGANG_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSEINGANG");	
	}

	public String get_ZAHLUNGSEINGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSEINGANG");
	}

	public String get_ZAHLUNGSEINGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSEINGANG",cNotNullValue);
	}

	public String get_ZAHLUNGSEINGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSEINGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSEINGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSEINGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSEINGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSEINGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSEINGANG", calNewValueFormated);
	}
		public boolean is_ZAHLUNGSEINGANG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZAHLUNGSEINGANG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZAHLUNGSEINGANG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZAHLUNGSEINGANG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("DROPDOWNTEXT",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FORMULARNAME",MyRECORD.DATATYPES.TEXT);
	put("FORMULARTYP",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GUT_NEGATIV",MyRECORD.DATATYPES.YESNO);
	put("GUT_NEGATIV_STORNO",MyRECORD.DATATYPES.YESNO);
	put("GUT_POSITIV",MyRECORD.DATATYPES.YESNO);
	put("GUT_POSITIV_STORNO",MyRECORD.DATATYPES.YESNO);
	put("ID_FIBU_FORMULAR",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("RECH_NEGATIV",MyRECORD.DATATYPES.YESNO);
	put("RECH_NEGATIV_STORNO",MyRECORD.DATATYPES.YESNO);
	put("RECH_POSITIV",MyRECORD.DATATYPES.YESNO);
	put("RECH_POSITIV_STORNO",MyRECORD.DATATYPES.YESNO);
	put("SCHECK",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("ZAHLUNGSAUSGANG",MyRECORD.DATATYPES.YESNO);
	put("ZAHLUNGSEINGANG",MyRECORD.DATATYPES.YESNO);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_FIBU_FORMULAR.HM_FIELDS;	
	}

}
