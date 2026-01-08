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

public class RECORD_COLUMNS_TO_CALC extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_COLUMNS_TO_CALC";
    public static String IDFIELD   = "ID_COLUMNS_TO_CALC";
    

	//erzeugen eines RECORDNEW_JT_COLUMNS_TO_CALC - felds
	private RECORDNEW_COLUMNS_TO_CALC   recNEW = null;

    private _TAB  tab = _TAB.columns_to_calc;  



	public RECORD_COLUMNS_TO_CALC() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");
	}


	public RECORD_COLUMNS_TO_CALC(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");
	}



	public RECORD_COLUMNS_TO_CALC(RECORD_COLUMNS_TO_CALC recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_COLUMNS_TO_CALC.TABLENAME);
	}


	//2014-04-03
	public RECORD_COLUMNS_TO_CALC(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");
		this.set_Tablename_To_FieldMetaDefs(RECORD_COLUMNS_TO_CALC.TABLENAME);
	}




	public RECORD_COLUMNS_TO_CALC(long lID_Unformated) throws myException
	{
		super("JT_COLUMNS_TO_CALC","ID_COLUMNS_TO_CALC",""+lID_Unformated);
		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_COLUMNS_TO_CALC.TABLENAME);
	}

	public RECORD_COLUMNS_TO_CALC(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_COLUMNS_TO_CALC", "ID_COLUMNS_TO_CALC="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_COLUMNS_TO_CALC", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_COLUMNS_TO_CALC.TABLENAME);
	}
	
	

	public RECORD_COLUMNS_TO_CALC(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_COLUMNS_TO_CALC","ID_COLUMNS_TO_CALC",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_COLUMNS_TO_CALC.TABLENAME);

	}


	public RECORD_COLUMNS_TO_CALC(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_COLUMNS_TO_CALC");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_COLUMNS_TO_CALC", "ID_COLUMNS_TO_CALC="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_COLUMNS_TO_CALC", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_COLUMNS_TO_CALC.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_COLUMNS_TO_CALC();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_COLUMNS_TO_CALC.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_COLUMNS_TO_CALC";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_COLUMNS_TO_CALC_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_COLUMNS_TO_CALC_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_COLUMNS_TO_CALC was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_COLUMNS_TO_CALC", bibE2.cTO(), "ID_COLUMNS_TO_CALC="+this.get_ID_COLUMNS_TO_CALC_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_COLUMNS_TO_CALC was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_COLUMNS_TO_CALC", bibE2.cTO(), "ID_COLUMNS_TO_CALC="+this.get_ID_COLUMNS_TO_CALC_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC WHERE ID_COLUMNS_TO_CALC="+this.get_ID_COLUMNS_TO_CALC_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC WHERE ID_COLUMNS_TO_CALC="+this.get_ID_COLUMNS_TO_CALC_cUF();
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
			if (S.isFull(this.get_ID_COLUMNS_TO_CALC_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_COLUMNS_TO_CALC", "ID_COLUMNS_TO_CALC="+this.get_ID_COLUMNS_TO_CALC_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_COLUMNS_TO_CALC get_RECORDNEW_COLUMNS_TO_CALC() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_COLUMNS_TO_CALC();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_COLUMNS_TO_CALC(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_COLUMNS_TO_CALC create_Instance() throws myException {
		return new RECORD_COLUMNS_TO_CALC();
	}
	
	public static RECORD_COLUMNS_TO_CALC create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_COLUMNS_TO_CALC(Conn);
    }

	public static RECORD_COLUMNS_TO_CALC create_Instance(RECORD_COLUMNS_TO_CALC recordOrig) {
		return new RECORD_COLUMNS_TO_CALC(recordOrig);
    }

	public static RECORD_COLUMNS_TO_CALC create_Instance(long lID_Unformated) throws myException {
		return new RECORD_COLUMNS_TO_CALC(lID_Unformated);
    }

	public static RECORD_COLUMNS_TO_CALC create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_COLUMNS_TO_CALC(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_COLUMNS_TO_CALC create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_COLUMNS_TO_CALC(lID_Unformated, Conn);
	}

	public static RECORD_COLUMNS_TO_CALC create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_COLUMNS_TO_CALC(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_COLUMNS_TO_CALC create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_COLUMNS_TO_CALC(recordOrig);	    
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
    public RECORD_COLUMNS_TO_CALC build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC WHERE ID_COLUMNS_TO_CALC="+this.get_ID_COLUMNS_TO_CALC_cUF());
      }
      //return new RECORD_COLUMNS_TO_CALC(this.get_cSQL_4_Build());
      RECORD_COLUMNS_TO_CALC rec = new RECORD_COLUMNS_TO_CALC();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_COLUMNS_TO_CALC
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_COLUMNS_TO_CALC-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_COLUMNS_TO_CALC get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_COLUMNS_TO_CALC_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_COLUMNS_TO_CALC  recNew = new RECORDNEW_COLUMNS_TO_CALC();
		
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
    public RECORD_COLUMNS_TO_CALC set_recordNew(RECORDNEW_COLUMNS_TO_CALC recnew) throws myException {
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
	
	

	public static String FIELD__ACTIVE = "ACTIVE";
	public static String FIELD__COLUMN_LABEL = "COLUMN_LABEL";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_COLUMNS_TO_CALC = "ID_COLUMNS_TO_CALC";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MODULNAME_LISTE = "MODULNAME_LISTE";
	public static String FIELD__NUMBER_DECIMALS = "NUMBER_DECIMALS";
	public static String FIELD__SHOW_LINE_IN_LISTHEADER = "SHOW_LINE_IN_LISTHEADER";
	public static String FIELD__SUMMATION_VIA_QUERY = "SUMMATION_VIA_QUERY";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TEXT4SUMMATION = "TEXT4SUMMATION";
	public static String FIELD__TEXT4TITLE_IN_WINDOW = "TEXT4TITLE_IN_WINDOW";
	public static String FIELD__TEXT4WINDOWTITLE = "TEXT4WINDOWTITLE";
	public static String FIELD__TOOLTIPS = "TOOLTIPS";
	public static String FIELD__VALIDATION_TAG = "VALIDATION_TAG";


	public String get_ACTIVE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ACTIVE");
	}

	public String get_ACTIVE_cF() throws myException
	{
		return this.get_FormatedValue("ACTIVE");	
	}

	public String get_ACTIVE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ACTIVE");
	}

	public String get_ACTIVE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ACTIVE",cNotNullValue);
	}

	public String get_ACTIVE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ACTIVE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ACTIVE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ACTIVE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ACTIVE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ACTIVE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ACTIVE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ACTIVE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ACTIVE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ACTIVE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ACTIVE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ACTIVE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ACTIVE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ACTIVE", calNewValueFormated);
	}
		public boolean is_ACTIVE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ACTIVE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ACTIVE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ACTIVE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_COLUMN_LABEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLUMN_LABEL");
	}

	public String get_COLUMN_LABEL_cF() throws myException
	{
		return this.get_FormatedValue("COLUMN_LABEL");	
	}

	public String get_COLUMN_LABEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLUMN_LABEL");
	}

	public String get_COLUMN_LABEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLUMN_LABEL",cNotNullValue);
	}

	public String get_COLUMN_LABEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLUMN_LABEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLUMN_LABEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLUMN_LABEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLUMN_LABEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLUMN_LABEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLUMN_LABEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLUMN_LABEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLUMN_LABEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLUMN_LABEL", calNewValueFormated);
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
		public String get_ID_COLUMNS_TO_CALC_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_COLUMNS_TO_CALC");
	}

	public String get_ID_COLUMNS_TO_CALC_cF() throws myException
	{
		return this.get_FormatedValue("ID_COLUMNS_TO_CALC");	
	}

	public String get_ID_COLUMNS_TO_CALC_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_COLUMNS_TO_CALC");
	}

	public String get_ID_COLUMNS_TO_CALC_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_COLUMNS_TO_CALC",cNotNullValue);
	}

	public String get_ID_COLUMNS_TO_CALC_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_COLUMNS_TO_CALC",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_COLUMNS_TO_CALC(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_COLUMNS_TO_CALC", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_COLUMNS_TO_CALC_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_COLUMNS_TO_CALC", calNewValueFormated);
	}
		public Long get_ID_COLUMNS_TO_CALC_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_COLUMNS_TO_CALC").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_COLUMNS_TO_CALC_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_COLUMNS_TO_CALC").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_COLUMNS_TO_CALC_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_COLUMNS_TO_CALC").getDoubleValue();
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
	public String get_ID_COLUMNS_TO_CALC_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_COLUMNS_TO_CALC_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_COLUMNS_TO_CALC_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_COLUMNS_TO_CALC_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_COLUMNS_TO_CALC_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_COLUMNS_TO_CALC").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_COLUMNS_TO_CALC_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_COLUMNS_TO_CALC").getBigDecimalValue();
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
		public String get_MODULNAME_LISTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MODULNAME_LISTE");
	}

	public String get_MODULNAME_LISTE_cF() throws myException
	{
		return this.get_FormatedValue("MODULNAME_LISTE");	
	}

	public String get_MODULNAME_LISTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MODULNAME_LISTE");
	}

	public String get_MODULNAME_LISTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MODULNAME_LISTE",cNotNullValue);
	}

	public String get_MODULNAME_LISTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MODULNAME_LISTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MODULNAME_LISTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MODULNAME_LISTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MODULNAME_LISTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULNAME_LISTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULNAME_LISTE", calNewValueFormated);
	}
		public String get_NUMBER_DECIMALS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NUMBER_DECIMALS");
	}

	public String get_NUMBER_DECIMALS_cF() throws myException
	{
		return this.get_FormatedValue("NUMBER_DECIMALS");	
	}

	public String get_NUMBER_DECIMALS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NUMBER_DECIMALS");
	}

	public String get_NUMBER_DECIMALS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NUMBER_DECIMALS",cNotNullValue);
	}

	public String get_NUMBER_DECIMALS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NUMBER_DECIMALS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NUMBER_DECIMALS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NUMBER_DECIMALS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NUMBER_DECIMALS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NUMBER_DECIMALS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NUMBER_DECIMALS", calNewValueFormated);
	}
		public Long get_NUMBER_DECIMALS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NUMBER_DECIMALS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NUMBER_DECIMALS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NUMBER_DECIMALS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NUMBER_DECIMALS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NUMBER_DECIMALS").getDoubleValue();
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
	public String get_NUMBER_DECIMALS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMBER_DECIMALS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NUMBER_DECIMALS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NUMBER_DECIMALS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NUMBER_DECIMALS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NUMBER_DECIMALS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NUMBER_DECIMALS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NUMBER_DECIMALS").getBigDecimalValue();
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
	
	
	public String get_SHOW_LINE_IN_LISTHEADER_cUF() throws myException
	{
		return this.get_UnFormatedValue("SHOW_LINE_IN_LISTHEADER");
	}

	public String get_SHOW_LINE_IN_LISTHEADER_cF() throws myException
	{
		return this.get_FormatedValue("SHOW_LINE_IN_LISTHEADER");	
	}

	public String get_SHOW_LINE_IN_LISTHEADER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SHOW_LINE_IN_LISTHEADER");
	}

	public String get_SHOW_LINE_IN_LISTHEADER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SHOW_LINE_IN_LISTHEADER",cNotNullValue);
	}

	public String get_SHOW_LINE_IN_LISTHEADER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SHOW_LINE_IN_LISTHEADER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SHOW_LINE_IN_LISTHEADER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SHOW_LINE_IN_LISTHEADER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SHOW_LINE_IN_LISTHEADER", calNewValueFormated);
	}
		public boolean is_SHOW_LINE_IN_LISTHEADER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SHOW_LINE_IN_LISTHEADER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SHOW_LINE_IN_LISTHEADER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SHOW_LINE_IN_LISTHEADER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SUMMATION_VIA_QUERY_cUF() throws myException
	{
		return this.get_UnFormatedValue("SUMMATION_VIA_QUERY");
	}

	public String get_SUMMATION_VIA_QUERY_cF() throws myException
	{
		return this.get_FormatedValue("SUMMATION_VIA_QUERY");	
	}

	public String get_SUMMATION_VIA_QUERY_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SUMMATION_VIA_QUERY");
	}

	public String get_SUMMATION_VIA_QUERY_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SUMMATION_VIA_QUERY",cNotNullValue);
	}

	public String get_SUMMATION_VIA_QUERY_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SUMMATION_VIA_QUERY",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SUMMATION_VIA_QUERY", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SUMMATION_VIA_QUERY(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SUMMATION_VIA_QUERY", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUMMATION_VIA_QUERY_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUMMATION_VIA_QUERY", calNewValueFormated);
	}
		public boolean is_SUMMATION_VIA_QUERY_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SUMMATION_VIA_QUERY_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SUMMATION_VIA_QUERY_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SUMMATION_VIA_QUERY_cUF_NN("N").equals("N"))
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
	
	
	public String get_TEXT4SUMMATION_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEXT4SUMMATION");
	}

	public String get_TEXT4SUMMATION_cF() throws myException
	{
		return this.get_FormatedValue("TEXT4SUMMATION");	
	}

	public String get_TEXT4SUMMATION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEXT4SUMMATION");
	}

	public String get_TEXT4SUMMATION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEXT4SUMMATION",cNotNullValue);
	}

	public String get_TEXT4SUMMATION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEXT4SUMMATION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEXT4SUMMATION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEXT4SUMMATION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEXT4SUMMATION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4SUMMATION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4SUMMATION", calNewValueFormated);
	}
		public String get_TEXT4TITLE_IN_WINDOW_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEXT4TITLE_IN_WINDOW");
	}

	public String get_TEXT4TITLE_IN_WINDOW_cF() throws myException
	{
		return this.get_FormatedValue("TEXT4TITLE_IN_WINDOW");	
	}

	public String get_TEXT4TITLE_IN_WINDOW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEXT4TITLE_IN_WINDOW");
	}

	public String get_TEXT4TITLE_IN_WINDOW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEXT4TITLE_IN_WINDOW",cNotNullValue);
	}

	public String get_TEXT4TITLE_IN_WINDOW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEXT4TITLE_IN_WINDOW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEXT4TITLE_IN_WINDOW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4TITLE_IN_WINDOW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4TITLE_IN_WINDOW", calNewValueFormated);
	}
		public String get_TEXT4WINDOWTITLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEXT4WINDOWTITLE");
	}

	public String get_TEXT4WINDOWTITLE_cF() throws myException
	{
		return this.get_FormatedValue("TEXT4WINDOWTITLE");	
	}

	public String get_TEXT4WINDOWTITLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEXT4WINDOWTITLE");
	}

	public String get_TEXT4WINDOWTITLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEXT4WINDOWTITLE",cNotNullValue);
	}

	public String get_TEXT4WINDOWTITLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEXT4WINDOWTITLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEXT4WINDOWTITLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEXT4WINDOWTITLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEXT4WINDOWTITLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXT4WINDOWTITLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXT4WINDOWTITLE", calNewValueFormated);
	}
		public String get_TOOLTIPS_cUF() throws myException
	{
		return this.get_UnFormatedValue("TOOLTIPS");
	}

	public String get_TOOLTIPS_cF() throws myException
	{
		return this.get_FormatedValue("TOOLTIPS");	
	}

	public String get_TOOLTIPS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TOOLTIPS");
	}

	public String get_TOOLTIPS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TOOLTIPS",cNotNullValue);
	}

	public String get_TOOLTIPS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TOOLTIPS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TOOLTIPS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TOOLTIPS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TOOLTIPS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TOOLTIPS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TOOLTIPS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TOOLTIPS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TOOLTIPS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TOOLTIPS", calNewValueFormated);
	}
		public String get_VALIDATION_TAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("VALIDATION_TAG");
	}

	public String get_VALIDATION_TAG_cF() throws myException
	{
		return this.get_FormatedValue("VALIDATION_TAG");	
	}

	public String get_VALIDATION_TAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VALIDATION_TAG");
	}

	public String get_VALIDATION_TAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VALIDATION_TAG",cNotNullValue);
	}

	public String get_VALIDATION_TAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VALIDATION_TAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VALIDATION_TAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VALIDATION_TAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VALIDATION_TAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VALIDATION_TAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VALIDATION_TAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VALIDATION_TAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VALIDATION_TAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VALIDATION_TAG", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ACTIVE",MyRECORD.DATATYPES.YESNO);
	put("COLUMN_LABEL",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_COLUMNS_TO_CALC",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MODULNAME_LISTE",MyRECORD.DATATYPES.TEXT);
	put("NUMBER_DECIMALS",MyRECORD.DATATYPES.NUMBER);
	put("SHOW_LINE_IN_LISTHEADER",MyRECORD.DATATYPES.YESNO);
	put("SUMMATION_VIA_QUERY",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TEXT4SUMMATION",MyRECORD.DATATYPES.TEXT);
	put("TEXT4TITLE_IN_WINDOW",MyRECORD.DATATYPES.TEXT);
	put("TEXT4WINDOWTITLE",MyRECORD.DATATYPES.TEXT);
	put("TOOLTIPS",MyRECORD.DATATYPES.TEXT);
	put("VALIDATION_TAG",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_COLUMNS_TO_CALC.HM_FIELDS;	
	}

}
