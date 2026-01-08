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

public class RECORD_REPORTING_QUERY_FIELD extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_REPORTING_QUERY_FIELD";
    public static String IDFIELD   = "ID_REPORTING_QUERY_FIELD";
    

	//erzeugen eines RECORDNEW_JT_REPORTING_QUERY_FIELD - felds
	private RECORDNEW_REPORTING_QUERY_FIELD   recNEW = null;

    private _TAB  tab = _TAB.reporting_query_field;  



	public RECORD_REPORTING_QUERY_FIELD() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");
	}


	public RECORD_REPORTING_QUERY_FIELD(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");
	}



	public RECORD_REPORTING_QUERY_FIELD(RECORD_REPORTING_QUERY_FIELD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORTING_QUERY_FIELD.TABLENAME);
	}


	//2014-04-03
	public RECORD_REPORTING_QUERY_FIELD(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORTING_QUERY_FIELD.TABLENAME);
	}




	public RECORD_REPORTING_QUERY_FIELD(long lID_Unformated) throws myException
	{
		super("JT_REPORTING_QUERY_FIELD","ID_REPORTING_QUERY_FIELD",""+lID_Unformated);
		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORTING_QUERY_FIELD.TABLENAME);
	}

	public RECORD_REPORTING_QUERY_FIELD(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORTING_QUERY_FIELD", "ID_REPORTING_QUERY_FIELD="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORTING_QUERY_FIELD", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORTING_QUERY_FIELD.TABLENAME);
	}
	
	

	public RECORD_REPORTING_QUERY_FIELD(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_REPORTING_QUERY_FIELD","ID_REPORTING_QUERY_FIELD",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORTING_QUERY_FIELD.TABLENAME);

	}


	public RECORD_REPORTING_QUERY_FIELD(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_REPORTING_QUERY_FIELD");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORTING_QUERY_FIELD", "ID_REPORTING_QUERY_FIELD="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORTING_QUERY_FIELD", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_REPORTING_QUERY_FIELD.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_REPORTING_QUERY_FIELD();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_REPORTING_QUERY_FIELD.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_REPORTING_QUERY_FIELD";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_REPORTING_QUERY_FIELD_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_REPORTING_QUERY_FIELD_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORTING_QUERY_FIELD was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORTING_QUERY_FIELD", bibE2.cTO(), "ID_REPORTING_QUERY_FIELD="+this.get_ID_REPORTING_QUERY_FIELD_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_REPORTING_QUERY_FIELD was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_REPORTING_QUERY_FIELD", bibE2.cTO(), "ID_REPORTING_QUERY_FIELD="+this.get_ID_REPORTING_QUERY_FIELD_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_REPORTING_QUERY_FIELD WHERE ID_REPORTING_QUERY_FIELD="+this.get_ID_REPORTING_QUERY_FIELD_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_REPORTING_QUERY_FIELD WHERE ID_REPORTING_QUERY_FIELD="+this.get_ID_REPORTING_QUERY_FIELD_cUF();
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
			if (S.isFull(this.get_ID_REPORTING_QUERY_FIELD_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_REPORTING_QUERY_FIELD", "ID_REPORTING_QUERY_FIELD="+this.get_ID_REPORTING_QUERY_FIELD_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_REPORTING_QUERY_FIELD get_RECORDNEW_REPORTING_QUERY_FIELD() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_REPORTING_QUERY_FIELD();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_REPORTING_QUERY_FIELD(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_REPORTING_QUERY_FIELD create_Instance() throws myException {
		return new RECORD_REPORTING_QUERY_FIELD();
	}
	
	public static RECORD_REPORTING_QUERY_FIELD create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_REPORTING_QUERY_FIELD(Conn);
    }

	public static RECORD_REPORTING_QUERY_FIELD create_Instance(RECORD_REPORTING_QUERY_FIELD recordOrig) {
		return new RECORD_REPORTING_QUERY_FIELD(recordOrig);
    }

	public static RECORD_REPORTING_QUERY_FIELD create_Instance(long lID_Unformated) throws myException {
		return new RECORD_REPORTING_QUERY_FIELD(lID_Unformated);
    }

	public static RECORD_REPORTING_QUERY_FIELD create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_REPORTING_QUERY_FIELD(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_REPORTING_QUERY_FIELD create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_REPORTING_QUERY_FIELD(lID_Unformated, Conn);
	}

	public static RECORD_REPORTING_QUERY_FIELD create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_REPORTING_QUERY_FIELD(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_REPORTING_QUERY_FIELD create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_REPORTING_QUERY_FIELD(recordOrig);	    
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
    public RECORD_REPORTING_QUERY_FIELD build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_REPORTING_QUERY_FIELD WHERE ID_REPORTING_QUERY_FIELD="+this.get_ID_REPORTING_QUERY_FIELD_cUF());
      }
      //return new RECORD_REPORTING_QUERY_FIELD(this.get_cSQL_4_Build());
      RECORD_REPORTING_QUERY_FIELD rec = new RECORD_REPORTING_QUERY_FIELD();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_REPORTING_QUERY_FIELD
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_REPORTING_QUERY_FIELD-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_REPORTING_QUERY_FIELD get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_REPORTING_QUERY_FIELD_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_REPORTING_QUERY_FIELD  recNew = new RECORDNEW_REPORTING_QUERY_FIELD();
		
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
    public RECORD_REPORTING_QUERY_FIELD set_recordNew(RECORDNEW_REPORTING_QUERY_FIELD recnew) throws myException {
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
	
	



		private RECORD_REPORTING_QUERY UP_RECORD_REPORTING_QUERY_id_reporting_query = null;






	
	/**
	 * References the Field ID_REPORTING_QUERY
	 * Falls keine this.get_ID_REPORTING_QUERY_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_REPORTING_QUERY get_UP_RECORD_REPORTING_QUERY_id_reporting_query() throws myException
	{
		if (S.isEmpty(this.get_ID_REPORTING_QUERY_cUF()))
			return null;
	
	
		if (this.UP_RECORD_REPORTING_QUERY_id_reporting_query==null)
		{
			this.UP_RECORD_REPORTING_QUERY_id_reporting_query = new RECORD_REPORTING_QUERY(this.get_ID_REPORTING_QUERY_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_REPORTING_QUERY_id_reporting_query;
	}
	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__ALIGNMENT = "ALIGNMENT";
	public static String FIELD__BREITE_LISTE_PX = "BREITE_LISTE_PX";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FIELDNAME = "FIELDNAME";
	public static String FIELD__FIELDNAME_4_USER = "FIELDNAME_4_USER";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_REPORTING_QUERY = "ID_REPORTING_QUERY";
	public static String FIELD__ID_REPORTING_QUERY_FIELD = "ID_REPORTING_QUERY_FIELD";
	public static String FIELD__IS_SEARCHFIELD = "IS_SEARCHFIELD";
	public static String FIELD__IS_SELECTORFIELD = "IS_SELECTORFIELD";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SORTIERFOLGE = "SORTIERFOLGE";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


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
		public String get_ALIGNMENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALIGNMENT");
	}

	public String get_ALIGNMENT_cF() throws myException
	{
		return this.get_FormatedValue("ALIGNMENT");	
	}

	public String get_ALIGNMENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALIGNMENT");
	}

	public String get_ALIGNMENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALIGNMENT",cNotNullValue);
	}

	public String get_ALIGNMENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALIGNMENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALIGNMENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALIGNMENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALIGNMENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALIGNMENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALIGNMENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALIGNMENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALIGNMENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALIGNMENT", calNewValueFormated);
	}
		public String get_BREITE_LISTE_PX_cUF() throws myException
	{
		return this.get_UnFormatedValue("BREITE_LISTE_PX");
	}

	public String get_BREITE_LISTE_PX_cF() throws myException
	{
		return this.get_FormatedValue("BREITE_LISTE_PX");	
	}

	public String get_BREITE_LISTE_PX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BREITE_LISTE_PX");
	}

	public String get_BREITE_LISTE_PX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BREITE_LISTE_PX",cNotNullValue);
	}

	public String get_BREITE_LISTE_PX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BREITE_LISTE_PX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BREITE_LISTE_PX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BREITE_LISTE_PX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BREITE_LISTE_PX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BREITE_LISTE_PX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BREITE_LISTE_PX", calNewValueFormated);
	}
		public Long get_BREITE_LISTE_PX_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("BREITE_LISTE_PX").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_BREITE_LISTE_PX_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("BREITE_LISTE_PX").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_BREITE_LISTE_PX_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("BREITE_LISTE_PX").getDoubleValue();
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
	public String get_BREITE_LISTE_PX_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BREITE_LISTE_PX_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_BREITE_LISTE_PX_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BREITE_LISTE_PX_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_BREITE_LISTE_PX_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("BREITE_LISTE_PX").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_BREITE_LISTE_PX_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("BREITE_LISTE_PX").getBigDecimalValue();
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
		public String get_FIELDNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIELDNAME");
	}

	public String get_FIELDNAME_cF() throws myException
	{
		return this.get_FormatedValue("FIELDNAME");	
	}

	public String get_FIELDNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIELDNAME");
	}

	public String get_FIELDNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIELDNAME",cNotNullValue);
	}

	public String get_FIELDNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIELDNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIELDNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME", calNewValueFormated);
	}
		public String get_FIELDNAME_4_USER_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIELDNAME_4_USER");
	}

	public String get_FIELDNAME_4_USER_cF() throws myException
	{
		return this.get_FormatedValue("FIELDNAME_4_USER");	
	}

	public String get_FIELDNAME_4_USER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIELDNAME_4_USER");
	}

	public String get_FIELDNAME_4_USER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIELDNAME_4_USER",cNotNullValue);
	}

	public String get_FIELDNAME_4_USER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIELDNAME_4_USER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIELDNAME_4_USER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME_4_USER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIELDNAME_4_USER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_4_USER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME_4_USER", calNewValueFormated);
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
	
	
	public String get_ID_REPORTING_QUERY_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_REPORTING_QUERY");
	}

	public String get_ID_REPORTING_QUERY_cF() throws myException
	{
		return this.get_FormatedValue("ID_REPORTING_QUERY");	
	}

	public String get_ID_REPORTING_QUERY_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_REPORTING_QUERY");
	}

	public String get_ID_REPORTING_QUERY_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_REPORTING_QUERY",cNotNullValue);
	}

	public String get_ID_REPORTING_QUERY_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_REPORTING_QUERY",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_REPORTING_QUERY", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_REPORTING_QUERY(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_REPORTING_QUERY", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY", calNewValueFormated);
	}
		public Long get_ID_REPORTING_QUERY_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_REPORTING_QUERY").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_REPORTING_QUERY_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_REPORTING_QUERY").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_REPORTING_QUERY_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_REPORTING_QUERY").getDoubleValue();
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
	public String get_ID_REPORTING_QUERY_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORTING_QUERY_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_REPORTING_QUERY_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORTING_QUERY_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_REPORTING_QUERY_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORTING_QUERY").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_REPORTING_QUERY_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORTING_QUERY").getBigDecimalValue();
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
	
	
	public String get_ID_REPORTING_QUERY_FIELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_REPORTING_QUERY_FIELD");
	}

	public String get_ID_REPORTING_QUERY_FIELD_cF() throws myException
	{
		return this.get_FormatedValue("ID_REPORTING_QUERY_FIELD");	
	}

	public String get_ID_REPORTING_QUERY_FIELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_REPORTING_QUERY_FIELD");
	}

	public String get_ID_REPORTING_QUERY_FIELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_REPORTING_QUERY_FIELD",cNotNullValue);
	}

	public String get_ID_REPORTING_QUERY_FIELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_REPORTING_QUERY_FIELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_REPORTING_QUERY_FIELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_REPORTING_QUERY_FIELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_REPORTING_QUERY_FIELD", calNewValueFormated);
	}
		public Long get_ID_REPORTING_QUERY_FIELD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_REPORTING_QUERY_FIELD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_REPORTING_QUERY_FIELD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_REPORTING_QUERY_FIELD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_REPORTING_QUERY_FIELD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_REPORTING_QUERY_FIELD").getDoubleValue();
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
	public String get_ID_REPORTING_QUERY_FIELD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORTING_QUERY_FIELD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_REPORTING_QUERY_FIELD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_REPORTING_QUERY_FIELD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_REPORTING_QUERY_FIELD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORTING_QUERY_FIELD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_REPORTING_QUERY_FIELD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_REPORTING_QUERY_FIELD").getBigDecimalValue();
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
	
	
	public String get_IS_SEARCHFIELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("IS_SEARCHFIELD");
	}

	public String get_IS_SEARCHFIELD_cF() throws myException
	{
		return this.get_FormatedValue("IS_SEARCHFIELD");	
	}

	public String get_IS_SEARCHFIELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IS_SEARCHFIELD");
	}

	public String get_IS_SEARCHFIELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IS_SEARCHFIELD",cNotNullValue);
	}

	public String get_IS_SEARCHFIELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IS_SEARCHFIELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IS_SEARCHFIELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IS_SEARCHFIELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IS_SEARCHFIELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SEARCHFIELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_SEARCHFIELD", calNewValueFormated);
	}
		public boolean is_IS_SEARCHFIELD_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_SEARCHFIELD_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IS_SEARCHFIELD_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_SEARCHFIELD_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IS_SELECTORFIELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("IS_SELECTORFIELD");
	}

	public String get_IS_SELECTORFIELD_cF() throws myException
	{
		return this.get_FormatedValue("IS_SELECTORFIELD");	
	}

	public String get_IS_SELECTORFIELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IS_SELECTORFIELD");
	}

	public String get_IS_SELECTORFIELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IS_SELECTORFIELD",cNotNullValue);
	}

	public String get_IS_SELECTORFIELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IS_SELECTORFIELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IS_SELECTORFIELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IS_SELECTORFIELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IS_SELECTORFIELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_SELECTORFIELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_SELECTORFIELD", calNewValueFormated);
	}
		public boolean is_IS_SELECTORFIELD_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_SELECTORFIELD_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IS_SELECTORFIELD_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_SELECTORFIELD_cUF_NN("N").equals("N"))
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
		public String get_SORTIERFOLGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTIERFOLGE");
	}

	public String get_SORTIERFOLGE_cF() throws myException
	{
		return this.get_FormatedValue("SORTIERFOLGE");	
	}

	public String get_SORTIERFOLGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTIERFOLGE");
	}

	public String get_SORTIERFOLGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTIERFOLGE",cNotNullValue);
	}

	public String get_SORTIERFOLGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTIERFOLGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTIERFOLGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTIERFOLGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTIERFOLGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTIERFOLGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERFOLGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERFOLGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERFOLGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERFOLGE", calNewValueFormated);
	}
		public Long get_SORTIERFOLGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTIERFOLGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTIERFOLGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTIERFOLGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTIERFOLGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTIERFOLGE").getDoubleValue();
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
	public String get_SORTIERFOLGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTIERFOLGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTIERFOLGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTIERFOLGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTIERFOLGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTIERFOLGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTIERFOLGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTIERFOLGE").getBigDecimalValue();
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
	
	


	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("ALIGNMENT",MyRECORD.DATATYPES.TEXT);
	put("BREITE_LISTE_PX",MyRECORD.DATATYPES.NUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FIELDNAME",MyRECORD.DATATYPES.TEXT);
	put("FIELDNAME_4_USER",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_REPORTING_QUERY",MyRECORD.DATATYPES.NUMBER);
	put("ID_REPORTING_QUERY_FIELD",MyRECORD.DATATYPES.NUMBER);
	put("IS_SEARCHFIELD",MyRECORD.DATATYPES.YESNO);
	put("IS_SELECTORFIELD",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SORTIERFOLGE",MyRECORD.DATATYPES.NUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_REPORTING_QUERY_FIELD.HM_FIELDS;	
	}

}
