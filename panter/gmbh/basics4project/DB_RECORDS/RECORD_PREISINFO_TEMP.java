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

public class RECORD_PREISINFO_TEMP extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_PREISINFO_TEMP";
    public static String IDFIELD   = "ID_PREISINFO_TEMP";
    

	//erzeugen eines RECORDNEW_JT_PREISINFO_TEMP - felds
	private RECORDNEW_PREISINFO_TEMP   recNEW = null;

    private _TAB  tab = _TAB.preisinfo_temp;  



	public RECORD_PREISINFO_TEMP() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_PREISINFO_TEMP");
	}


	public RECORD_PREISINFO_TEMP(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_PREISINFO_TEMP");
	}



	public RECORD_PREISINFO_TEMP(RECORD_PREISINFO_TEMP recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_PREISINFO_TEMP");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PREISINFO_TEMP.TABLENAME);
	}


	//2014-04-03
	public RECORD_PREISINFO_TEMP(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_PREISINFO_TEMP");
		this.set_Tablename_To_FieldMetaDefs(RECORD_PREISINFO_TEMP.TABLENAME);
	}




	public RECORD_PREISINFO_TEMP(long lID_Unformated) throws myException
	{
		super("JT_PREISINFO_TEMP","ID_PREISINFO_TEMP",""+lID_Unformated);
		this.set_TABLE_NAME("JT_PREISINFO_TEMP");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PREISINFO_TEMP.TABLENAME);
	}

	public RECORD_PREISINFO_TEMP(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_PREISINFO_TEMP");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_PREISINFO_TEMP", "ID_PREISINFO_TEMP="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_PREISINFO_TEMP", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PREISINFO_TEMP.TABLENAME);
	}
	
	

	public RECORD_PREISINFO_TEMP(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_PREISINFO_TEMP","ID_PREISINFO_TEMP",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_PREISINFO_TEMP");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PREISINFO_TEMP.TABLENAME);

	}


	public RECORD_PREISINFO_TEMP(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_PREISINFO_TEMP");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_PREISINFO_TEMP", "ID_PREISINFO_TEMP="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_PREISINFO_TEMP", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PREISINFO_TEMP.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_PREISINFO_TEMP();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_PREISINFO_TEMP.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_PREISINFO_TEMP";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_PREISINFO_TEMP_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_PREISINFO_TEMP_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_PREISINFO_TEMP was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_PREISINFO_TEMP", bibE2.cTO(), "ID_PREISINFO_TEMP="+this.get_ID_PREISINFO_TEMP_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_PREISINFO_TEMP was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_PREISINFO_TEMP", bibE2.cTO(), "ID_PREISINFO_TEMP="+this.get_ID_PREISINFO_TEMP_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_PREISINFO_TEMP WHERE ID_PREISINFO_TEMP="+this.get_ID_PREISINFO_TEMP_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_PREISINFO_TEMP WHERE ID_PREISINFO_TEMP="+this.get_ID_PREISINFO_TEMP_cUF();
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
			if (S.isFull(this.get_ID_PREISINFO_TEMP_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_PREISINFO_TEMP", "ID_PREISINFO_TEMP="+this.get_ID_PREISINFO_TEMP_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_PREISINFO_TEMP get_RECORDNEW_PREISINFO_TEMP() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_PREISINFO_TEMP();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_PREISINFO_TEMP(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_PREISINFO_TEMP create_Instance() throws myException {
		return new RECORD_PREISINFO_TEMP();
	}
	
	public static RECORD_PREISINFO_TEMP create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_PREISINFO_TEMP(Conn);
    }

	public static RECORD_PREISINFO_TEMP create_Instance(RECORD_PREISINFO_TEMP recordOrig) {
		return new RECORD_PREISINFO_TEMP(recordOrig);
    }

	public static RECORD_PREISINFO_TEMP create_Instance(long lID_Unformated) throws myException {
		return new RECORD_PREISINFO_TEMP(lID_Unformated);
    }

	public static RECORD_PREISINFO_TEMP create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_PREISINFO_TEMP(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_PREISINFO_TEMP create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_PREISINFO_TEMP(lID_Unformated, Conn);
	}

	public static RECORD_PREISINFO_TEMP create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_PREISINFO_TEMP(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_PREISINFO_TEMP create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_PREISINFO_TEMP(recordOrig);	    
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
    public RECORD_PREISINFO_TEMP build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_PREISINFO_TEMP WHERE ID_PREISINFO_TEMP="+this.get_ID_PREISINFO_TEMP_cUF());
      }
      //return new RECORD_PREISINFO_TEMP(this.get_cSQL_4_Build());
      RECORD_PREISINFO_TEMP rec = new RECORD_PREISINFO_TEMP();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_PREISINFO_TEMP
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_PREISINFO_TEMP-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_PREISINFO_TEMP get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_PREISINFO_TEMP_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_PREISINFO_TEMP  recNew = new RECORDNEW_PREISINFO_TEMP();
		
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
    public RECORD_PREISINFO_TEMP set_recordNew(RECORDNEW_PREISINFO_TEMP recnew) throws myException {
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
	
	

	public static String FIELD__ANR1 = "ANR1";
	public static String FIELD__ANR2 = "ANR2";
	public static String FIELD__ANSPRECHPARTNER = "ANSPRECHPARTNER";
	public static String FIELD__ARTBEZ = "ARTBEZ";
	public static String FIELD__BEZEICHNUNG = "BEZEICHNUNG";
	public static String FIELD__EMAIL = "EMAIL";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_PREISINFO_TEMP = "ID_PREISINFO_TEMP";
	public static String FIELD__JAHR = "JAHR";
	public static String FIELD__KOPIERKENNZ = "KOPIERKENNZ";
	public static String FIELD__LAND = "LAND";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEF_NR = "LIEF_NR";
	public static String FIELD__MONAT = "MONAT";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__ORT = "ORT";
	public static String FIELD__PLZ = "PLZ";
	public static String FIELD__PREIS = "PREIS";
	public static String FIELD__STRASSE = "STRASSE";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_ANR1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1");
	}

	public String get_ANR1_cF() throws myException
	{
		return this.get_FormatedValue("ANR1");	
	}

	public String get_ANR1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1");
	}

	public String get_ANR1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1",cNotNullValue);
	}

	public String get_ANR1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", calNewValueFormated);
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
		public String get_ANSPRECHPARTNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANSPRECHPARTNER");
	}

	public String get_ANSPRECHPARTNER_cF() throws myException
	{
		return this.get_FormatedValue("ANSPRECHPARTNER");	
	}

	public String get_ANSPRECHPARTNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANSPRECHPARTNER");
	}

	public String get_ANSPRECHPARTNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANSPRECHPARTNER",cNotNullValue);
	}

	public String get_ANSPRECHPARTNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANSPRECHPARTNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANSPRECHPARTNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANSPRECHPARTNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANSPRECHPARTNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANSPRECHPARTNER", calNewValueFormated);
	}
		public String get_ARTBEZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ");
	}

	public String get_ARTBEZ_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ");	
	}

	public String get_ARTBEZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ");
	}

	public String get_ARTBEZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ",cNotNullValue);
	}

	public String get_ARTBEZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ", calNewValueFormated);
	}
		public String get_BEZEICHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZEICHNUNG");
	}

	public String get_BEZEICHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("BEZEICHNUNG");	
	}

	public String get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZEICHNUNG");
	}

	public String get_BEZEICHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZEICHNUNG",cNotNullValue);
	}

	public String get_BEZEICHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZEICHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", calNewValueFormated);
	}
		public String get_EMAIL_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL");
	}

	public String get_EMAIL_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL");	
	}

	public String get_EMAIL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL");
	}

	public String get_EMAIL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL",cNotNullValue);
	}

	public String get_EMAIL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL", calNewValueFormated);
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
	
	
	public String get_ID_PREISINFO_TEMP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_PREISINFO_TEMP");
	}

	public String get_ID_PREISINFO_TEMP_cF() throws myException
	{
		return this.get_FormatedValue("ID_PREISINFO_TEMP");	
	}

	public String get_ID_PREISINFO_TEMP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_PREISINFO_TEMP");
	}

	public String get_ID_PREISINFO_TEMP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_PREISINFO_TEMP",cNotNullValue);
	}

	public String get_ID_PREISINFO_TEMP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_PREISINFO_TEMP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_PREISINFO_TEMP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_PREISINFO_TEMP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_PREISINFO_TEMP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PREISINFO_TEMP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PREISINFO_TEMP", calNewValueFormated);
	}
		public Long get_ID_PREISINFO_TEMP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_PREISINFO_TEMP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_PREISINFO_TEMP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_PREISINFO_TEMP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_PREISINFO_TEMP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_PREISINFO_TEMP").getDoubleValue();
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
	public String get_ID_PREISINFO_TEMP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PREISINFO_TEMP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_PREISINFO_TEMP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PREISINFO_TEMP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_PREISINFO_TEMP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PREISINFO_TEMP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_PREISINFO_TEMP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PREISINFO_TEMP").getBigDecimalValue();
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
	
	
	public String get_JAHR_cUF() throws myException
	{
		return this.get_UnFormatedValue("JAHR");
	}

	public String get_JAHR_cF() throws myException
	{
		return this.get_FormatedValue("JAHR");	
	}

	public String get_JAHR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("JAHR");
	}

	public String get_JAHR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("JAHR",cNotNullValue);
	}

	public String get_JAHR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("JAHR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_JAHR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("JAHR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_JAHR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("JAHR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAHR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("JAHR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAHR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAHR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAHR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAHR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_JAHR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("JAHR", calNewValueFormated);
	}
		public Long get_JAHR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("JAHR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_JAHR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("JAHR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_JAHR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("JAHR").getDoubleValue();
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
	public String get_JAHR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_JAHR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_JAHR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_JAHR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_JAHR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("JAHR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_JAHR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("JAHR").getBigDecimalValue();
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
	
	
	public String get_KOPIERKENNZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOPIERKENNZ");
	}

	public String get_KOPIERKENNZ_cF() throws myException
	{
		return this.get_FormatedValue("KOPIERKENNZ");	
	}

	public String get_KOPIERKENNZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOPIERKENNZ");
	}

	public String get_KOPIERKENNZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOPIERKENNZ",cNotNullValue);
	}

	public String get_KOPIERKENNZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOPIERKENNZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOPIERKENNZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOPIERKENNZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOPIERKENNZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOPIERKENNZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOPIERKENNZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOPIERKENNZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOPIERKENNZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOPIERKENNZ", calNewValueFormated);
	}
		public boolean is_KOPIERKENNZ_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KOPIERKENNZ_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KOPIERKENNZ_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KOPIERKENNZ_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAND");
	}

	public String get_LAND_cF() throws myException
	{
		return this.get_FormatedValue("LAND");	
	}

	public String get_LAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAND");
	}

	public String get_LAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAND",cNotNullValue);
	}

	public String get_LAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAND", calNewValueFormated);
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
		public String get_LIEF_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEF_NR");
	}

	public String get_LIEF_NR_cF() throws myException
	{
		return this.get_FormatedValue("LIEF_NR");	
	}

	public String get_LIEF_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEF_NR");
	}

	public String get_LIEF_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEF_NR",cNotNullValue);
	}

	public String get_LIEF_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEF_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEF_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEF_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEF_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEF_NR", calNewValueFormated);
	}
		public String get_MONAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MONAT");
	}

	public String get_MONAT_cF() throws myException
	{
		return this.get_FormatedValue("MONAT");	
	}

	public String get_MONAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MONAT");
	}

	public String get_MONAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MONAT",cNotNullValue);
	}

	public String get_MONAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MONAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MONAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MONAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MONAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MONAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MONAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MONAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MONAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MONAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MONAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MONAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MONAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MONAT", calNewValueFormated);
	}
		public Long get_MONAT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MONAT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MONAT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MONAT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MONAT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MONAT").getDoubleValue();
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
	public String get_MONAT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MONAT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MONAT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MONAT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MONAT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MONAT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MONAT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MONAT").getBigDecimalValue();
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
	
	
	public String get_NAME1_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME1");
	}

	public String get_NAME1_cF() throws myException
	{
		return this.get_FormatedValue("NAME1");	
	}

	public String get_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME1");
	}

	public String get_NAME1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME1",cNotNullValue);
	}

	public String get_NAME1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public String get_NAME2_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME2");
	}

	public String get_NAME2_cF() throws myException
	{
		return this.get_FormatedValue("NAME2");	
	}

	public String get_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME2");
	}

	public String get_NAME2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME2",cNotNullValue);
	}

	public String get_NAME2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public String get_NAME3_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME3");
	}

	public String get_NAME3_cF() throws myException
	{
		return this.get_FormatedValue("NAME3");	
	}

	public String get_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME3");
	}

	public String get_NAME3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME3",cNotNullValue);
	}

	public String get_NAME3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public String get_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORT");
	}

	public String get_ORT_cF() throws myException
	{
		return this.get_FormatedValue("ORT");	
	}

	public String get_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORT");
	}

	public String get_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORT",cNotNullValue);
	}

	public String get_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public String get_PLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("PLZ");
	}

	public String get_PLZ_cF() throws myException
	{
		return this.get_FormatedValue("PLZ");	
	}

	public String get_PLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PLZ");
	}

	public String get_PLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PLZ",cNotNullValue);
	}

	public String get_PLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
	}
		public String get_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREIS");
	}

	public String get_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("PREIS");	
	}

	public String get_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREIS");
	}

	public String get_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREIS",cNotNullValue);
	}

	public String get_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS", calNewValueFormated);
	}
		public Double get_PREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("PREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_PREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("PREIS").getDoubleValue();
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
	public String get_PREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_PREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_PREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("PREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_PREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("PREIS").getBigDecimalValue();
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
	
	
	public String get_STRASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("STRASSE");
	}

	public String get_STRASSE_cF() throws myException
	{
		return this.get_FormatedValue("STRASSE");	
	}

	public String get_STRASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STRASSE");
	}

	public String get_STRASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STRASSE",cNotNullValue);
	}

	public String get_STRASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STRASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
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
	put("ANR1",MyRECORD.DATATYPES.TEXT);
	put("ANR2",MyRECORD.DATATYPES.TEXT);
	put("ANSPRECHPARTNER",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ",MyRECORD.DATATYPES.TEXT);
	put("BEZEICHNUNG",MyRECORD.DATATYPES.TEXT);
	put("EMAIL",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_PREISINFO_TEMP",MyRECORD.DATATYPES.NUMBER);
	put("JAHR",MyRECORD.DATATYPES.NUMBER);
	put("KOPIERKENNZ",MyRECORD.DATATYPES.YESNO);
	put("LAND",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEF_NR",MyRECORD.DATATYPES.TEXT);
	put("MONAT",MyRECORD.DATATYPES.NUMBER);
	put("NAME1",MyRECORD.DATATYPES.TEXT);
	put("NAME2",MyRECORD.DATATYPES.TEXT);
	put("NAME3",MyRECORD.DATATYPES.TEXT);
	put("ORT",MyRECORD.DATATYPES.TEXT);
	put("PLZ",MyRECORD.DATATYPES.TEXT);
	put("PREIS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STRASSE",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_PREISINFO_TEMP.HM_FIELDS;	
	}

}
