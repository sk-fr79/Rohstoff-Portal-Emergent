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

public class RECORD_TEXT_LISTE extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_TEXT_LISTE";
    public static String IDFIELD   = "ID_TEXT_LISTE";
    

	//erzeugen eines RECORDNEW_JT_TEXT_LISTE - felds
	private RECORDNEW_TEXT_LISTE   recNEW = null;

    private _TAB  tab = _TAB.text_liste;  



	public RECORD_TEXT_LISTE() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_TEXT_LISTE");
	}


	public RECORD_TEXT_LISTE(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TEXT_LISTE");
	}



	public RECORD_TEXT_LISTE(RECORD_TEXT_LISTE recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TEXT_LISTE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEXT_LISTE.TABLENAME);
	}


	//2014-04-03
	public RECORD_TEXT_LISTE(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TEXT_LISTE");
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEXT_LISTE.TABLENAME);
	}




	public RECORD_TEXT_LISTE(long lID_Unformated) throws myException
	{
		super("JT_TEXT_LISTE","ID_TEXT_LISTE",""+lID_Unformated);
		this.set_TABLE_NAME("JT_TEXT_LISTE");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEXT_LISTE.TABLENAME);
	}

	public RECORD_TEXT_LISTE(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_TEXT_LISTE");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEXT_LISTE", "ID_TEXT_LISTE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEXT_LISTE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEXT_LISTE.TABLENAME);
	}
	
	

	public RECORD_TEXT_LISTE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_TEXT_LISTE","ID_TEXT_LISTE",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_TEXT_LISTE");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEXT_LISTE.TABLENAME);

	}


	public RECORD_TEXT_LISTE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TEXT_LISTE");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEXT_LISTE", "ID_TEXT_LISTE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEXT_LISTE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TEXT_LISTE.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_TEXT_LISTE();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_TEXT_LISTE.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_TEXT_LISTE";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_TEXT_LISTE_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_TEXT_LISTE_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TEXT_LISTE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TEXT_LISTE", bibE2.cTO(), "ID_TEXT_LISTE="+this.get_ID_TEXT_LISTE_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TEXT_LISTE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TEXT_LISTE", bibE2.cTO(), "ID_TEXT_LISTE="+this.get_ID_TEXT_LISTE_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_TEXT_LISTE WHERE ID_TEXT_LISTE="+this.get_ID_TEXT_LISTE_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_TEXT_LISTE WHERE ID_TEXT_LISTE="+this.get_ID_TEXT_LISTE_cUF();
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
			if (S.isFull(this.get_ID_TEXT_LISTE_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TEXT_LISTE", "ID_TEXT_LISTE="+this.get_ID_TEXT_LISTE_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_TEXT_LISTE get_RECORDNEW_TEXT_LISTE() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_TEXT_LISTE();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_TEXT_LISTE(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_TEXT_LISTE create_Instance() throws myException {
		return new RECORD_TEXT_LISTE();
	}
	
	public static RECORD_TEXT_LISTE create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_TEXT_LISTE(Conn);
    }

	public static RECORD_TEXT_LISTE create_Instance(RECORD_TEXT_LISTE recordOrig) {
		return new RECORD_TEXT_LISTE(recordOrig);
    }

	public static RECORD_TEXT_LISTE create_Instance(long lID_Unformated) throws myException {
		return new RECORD_TEXT_LISTE(lID_Unformated);
    }

	public static RECORD_TEXT_LISTE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_TEXT_LISTE(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_TEXT_LISTE create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_TEXT_LISTE(lID_Unformated, Conn);
	}

	public static RECORD_TEXT_LISTE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_TEXT_LISTE(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_TEXT_LISTE create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_TEXT_LISTE(recordOrig);	    
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
    public RECORD_TEXT_LISTE build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_TEXT_LISTE WHERE ID_TEXT_LISTE="+this.get_ID_TEXT_LISTE_cUF());
      }
      //return new RECORD_TEXT_LISTE(this.get_cSQL_4_Build());
      RECORD_TEXT_LISTE rec = new RECORD_TEXT_LISTE();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_TEXT_LISTE
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_TEXT_LISTE-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_TEXT_LISTE get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_TEXT_LISTE_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_TEXT_LISTE  recNew = new RECORDNEW_TEXT_LISTE();
		
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
    public RECORD_TEXT_LISTE set_recordNew(RECORDNEW_TEXT_LISTE recnew) throws myException {
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
	
	

	public static String FIELD__AUFZAEHL_TEXT = "AUFZAEHL_TEXT";
	public static String FIELD__BOLD_AUFZAEHL_TEXT = "BOLD_AUFZAEHL_TEXT";
	public static String FIELD__BOLD_LANG_TEXT = "BOLD_LANG_TEXT";
	public static String FIELD__BOLD_TITEL_TEXT = "BOLD_TITEL_TEXT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FONTSIZE_AUFZAEHL_TEXT = "FONTSIZE_AUFZAEHL_TEXT";
	public static String FIELD__FONTSIZE_LANG_TEXT = "FONTSIZE_LANG_TEXT";
	public static String FIELD__FONTSIZE_TITEL_TEXT = "FONTSIZE_TITEL_TEXT";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TABLE = "ID_TABLE";
	public static String FIELD__ID_TEXT_LISTE = "ID_TEXT_LISTE";
	public static String FIELD__ITALIC_AUFZAEHL_TEXT = "ITALIC_AUFZAEHL_TEXT";
	public static String FIELD__ITALIC_LANG_TEXT = "ITALIC_LANG_TEXT";
	public static String FIELD__ITALIC_TITEL_TEXT = "ITALIC_TITEL_TEXT";
	public static String FIELD__LANG_TEXT = "LANG_TEXT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__POSITION = "POSITION";
	public static String FIELD__POSITION_ENUM = "POSITION_ENUM";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TABLENAME = "TABLENAME";
	public static String FIELD__TITEL_TEXT = "TITEL_TEXT";
	public static String FIELD__UNDERLINE_AUFZAEHL_TEXT = "UNDERLINE_AUFZAEHL_TEXT";
	public static String FIELD__UNDERLINE_LANG_TEXT = "UNDERLINE_LANG_TEXT";
	public static String FIELD__UNDERLINE_TITEL_TEXT = "UNDERLINE_TITEL_TEXT";


	public String get_AUFZAEHL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUFZAEHL_TEXT");
	}

	public String get_AUFZAEHL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("AUFZAEHL_TEXT");	
	}

	public String get_AUFZAEHL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUFZAEHL_TEXT");
	}

	public String get_AUFZAEHL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUFZAEHL_TEXT",cNotNullValue);
	}

	public String get_AUFZAEHL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUFZAEHL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFZAEHL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFZAEHL_TEXT", calNewValueFormated);
	}
		public String get_BOLD_AUFZAEHL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BOLD_AUFZAEHL_TEXT");
	}

	public String get_BOLD_AUFZAEHL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("BOLD_AUFZAEHL_TEXT");	
	}

	public String get_BOLD_AUFZAEHL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BOLD_AUFZAEHL_TEXT");
	}

	public String get_BOLD_AUFZAEHL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BOLD_AUFZAEHL_TEXT",cNotNullValue);
	}

	public String get_BOLD_AUFZAEHL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BOLD_AUFZAEHL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BOLD_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_AUFZAEHL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public boolean is_BOLD_AUFZAEHL_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BOLD_AUFZAEHL_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BOLD_AUFZAEHL_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BOLD_AUFZAEHL_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BOLD_LANG_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BOLD_LANG_TEXT");
	}

	public String get_BOLD_LANG_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("BOLD_LANG_TEXT");	
	}

	public String get_BOLD_LANG_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BOLD_LANG_TEXT");
	}

	public String get_BOLD_LANG_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BOLD_LANG_TEXT",cNotNullValue);
	}

	public String get_BOLD_LANG_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BOLD_LANG_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BOLD_LANG_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BOLD_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BOLD_LANG_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_LANG_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_LANG_TEXT", calNewValueFormated);
	}
		public boolean is_BOLD_LANG_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BOLD_LANG_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BOLD_LANG_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BOLD_LANG_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BOLD_TITEL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BOLD_TITEL_TEXT");
	}

	public String get_BOLD_TITEL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("BOLD_TITEL_TEXT");	
	}

	public String get_BOLD_TITEL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BOLD_TITEL_TEXT");
	}

	public String get_BOLD_TITEL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BOLD_TITEL_TEXT",cNotNullValue);
	}

	public String get_BOLD_TITEL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BOLD_TITEL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BOLD_TITEL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BOLD_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BOLD_TITEL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOLD_TITEL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOLD_TITEL_TEXT", calNewValueFormated);
	}
		public boolean is_BOLD_TITEL_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BOLD_TITEL_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BOLD_TITEL_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BOLD_TITEL_TEXT_cUF_NN("N").equals("N"))
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
		public String get_FONTSIZE_AUFZAEHL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("FONTSIZE_AUFZAEHL_TEXT");
	}

	public String get_FONTSIZE_AUFZAEHL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("FONTSIZE_AUFZAEHL_TEXT");	
	}

	public String get_FONTSIZE_AUFZAEHL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FONTSIZE_AUFZAEHL_TEXT");
	}

	public String get_FONTSIZE_AUFZAEHL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FONTSIZE_AUFZAEHL_TEXT",cNotNullValue);
	}

	public String get_FONTSIZE_AUFZAEHL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FONTSIZE_AUFZAEHL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_AUFZAEHL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public Long get_FONTSIZE_AUFZAEHL_TEXT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FONTSIZE_AUFZAEHL_TEXT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FONTSIZE_AUFZAEHL_TEXT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FONTSIZE_AUFZAEHL_TEXT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FONTSIZE_AUFZAEHL_TEXT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FONTSIZE_AUFZAEHL_TEXT").getDoubleValue();
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
	public String get_FONTSIZE_AUFZAEHL_TEXT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FONTSIZE_AUFZAEHL_TEXT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_FONTSIZE_AUFZAEHL_TEXT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FONTSIZE_AUFZAEHL_TEXT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_FONTSIZE_AUFZAEHL_TEXT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FONTSIZE_AUFZAEHL_TEXT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FONTSIZE_AUFZAEHL_TEXT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FONTSIZE_AUFZAEHL_TEXT").getBigDecimalValue();
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
	
	
	public String get_FONTSIZE_LANG_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("FONTSIZE_LANG_TEXT");
	}

	public String get_FONTSIZE_LANG_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("FONTSIZE_LANG_TEXT");	
	}

	public String get_FONTSIZE_LANG_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FONTSIZE_LANG_TEXT");
	}

	public String get_FONTSIZE_LANG_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FONTSIZE_LANG_TEXT",cNotNullValue);
	}

	public String get_FONTSIZE_LANG_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FONTSIZE_LANG_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FONTSIZE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FONTSIZE_LANG_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_LANG_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_LANG_TEXT", calNewValueFormated);
	}
		public Long get_FONTSIZE_LANG_TEXT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FONTSIZE_LANG_TEXT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FONTSIZE_LANG_TEXT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FONTSIZE_LANG_TEXT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FONTSIZE_LANG_TEXT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FONTSIZE_LANG_TEXT").getDoubleValue();
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
	public String get_FONTSIZE_LANG_TEXT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FONTSIZE_LANG_TEXT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_FONTSIZE_LANG_TEXT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FONTSIZE_LANG_TEXT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_FONTSIZE_LANG_TEXT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FONTSIZE_LANG_TEXT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FONTSIZE_LANG_TEXT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FONTSIZE_LANG_TEXT").getBigDecimalValue();
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
	
	
	public String get_FONTSIZE_TITEL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("FONTSIZE_TITEL_TEXT");
	}

	public String get_FONTSIZE_TITEL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("FONTSIZE_TITEL_TEXT");	
	}

	public String get_FONTSIZE_TITEL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FONTSIZE_TITEL_TEXT");
	}

	public String get_FONTSIZE_TITEL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FONTSIZE_TITEL_TEXT",cNotNullValue);
	}

	public String get_FONTSIZE_TITEL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FONTSIZE_TITEL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FONTSIZE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FONTSIZE_TITEL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FONTSIZE_TITEL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FONTSIZE_TITEL_TEXT", calNewValueFormated);
	}
		public Long get_FONTSIZE_TITEL_TEXT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FONTSIZE_TITEL_TEXT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FONTSIZE_TITEL_TEXT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FONTSIZE_TITEL_TEXT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FONTSIZE_TITEL_TEXT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FONTSIZE_TITEL_TEXT").getDoubleValue();
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
	public String get_FONTSIZE_TITEL_TEXT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FONTSIZE_TITEL_TEXT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_FONTSIZE_TITEL_TEXT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FONTSIZE_TITEL_TEXT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_FONTSIZE_TITEL_TEXT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FONTSIZE_TITEL_TEXT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FONTSIZE_TITEL_TEXT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FONTSIZE_TITEL_TEXT").getBigDecimalValue();
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
	
	
	public String get_ID_TEXT_LISTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TEXT_LISTE");
	}

	public String get_ID_TEXT_LISTE_cF() throws myException
	{
		return this.get_FormatedValue("ID_TEXT_LISTE");	
	}

	public String get_ID_TEXT_LISTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TEXT_LISTE");
	}

	public String get_ID_TEXT_LISTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TEXT_LISTE",cNotNullValue);
	}

	public String get_ID_TEXT_LISTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TEXT_LISTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TEXT_LISTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TEXT_LISTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TEXT_LISTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TEXT_LISTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TEXT_LISTE", calNewValueFormated);
	}
		public Long get_ID_TEXT_LISTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TEXT_LISTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TEXT_LISTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TEXT_LISTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TEXT_LISTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TEXT_LISTE").getDoubleValue();
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
	public String get_ID_TEXT_LISTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TEXT_LISTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TEXT_LISTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TEXT_LISTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TEXT_LISTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TEXT_LISTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TEXT_LISTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TEXT_LISTE").getBigDecimalValue();
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
	
	
	public String get_ITALIC_AUFZAEHL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ITALIC_AUFZAEHL_TEXT");
	}

	public String get_ITALIC_AUFZAEHL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("ITALIC_AUFZAEHL_TEXT");	
	}

	public String get_ITALIC_AUFZAEHL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ITALIC_AUFZAEHL_TEXT");
	}

	public String get_ITALIC_AUFZAEHL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ITALIC_AUFZAEHL_TEXT",cNotNullValue);
	}

	public String get_ITALIC_AUFZAEHL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ITALIC_AUFZAEHL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ITALIC_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_AUFZAEHL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public boolean is_ITALIC_AUFZAEHL_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ITALIC_AUFZAEHL_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ITALIC_AUFZAEHL_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ITALIC_AUFZAEHL_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ITALIC_LANG_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ITALIC_LANG_TEXT");
	}

	public String get_ITALIC_LANG_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("ITALIC_LANG_TEXT");	
	}

	public String get_ITALIC_LANG_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ITALIC_LANG_TEXT");
	}

	public String get_ITALIC_LANG_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ITALIC_LANG_TEXT",cNotNullValue);
	}

	public String get_ITALIC_LANG_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ITALIC_LANG_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ITALIC_LANG_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ITALIC_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ITALIC_LANG_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_LANG_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_LANG_TEXT", calNewValueFormated);
	}
		public boolean is_ITALIC_LANG_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ITALIC_LANG_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ITALIC_LANG_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ITALIC_LANG_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ITALIC_TITEL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ITALIC_TITEL_TEXT");
	}

	public String get_ITALIC_TITEL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("ITALIC_TITEL_TEXT");	
	}

	public String get_ITALIC_TITEL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ITALIC_TITEL_TEXT");
	}

	public String get_ITALIC_TITEL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ITALIC_TITEL_TEXT",cNotNullValue);
	}

	public String get_ITALIC_TITEL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ITALIC_TITEL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ITALIC_TITEL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ITALIC_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ITALIC_TITEL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ITALIC_TITEL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ITALIC_TITEL_TEXT", calNewValueFormated);
	}
		public boolean is_ITALIC_TITEL_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ITALIC_TITEL_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ITALIC_TITEL_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ITALIC_TITEL_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LANG_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LANG_TEXT");
	}

	public String get_LANG_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("LANG_TEXT");	
	}

	public String get_LANG_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LANG_TEXT");
	}

	public String get_LANG_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LANG_TEXT",cNotNullValue);
	}

	public String get_LANG_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LANG_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LANG_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LANG_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LANG_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LANG_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LANG_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LANG_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LANG_TEXT", calNewValueFormated);
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
		public String get_POSITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSITION");
	}

	public String get_POSITION_cF() throws myException
	{
		return this.get_FormatedValue("POSITION");	
	}

	public String get_POSITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSITION");
	}

	public String get_POSITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSITION",cNotNullValue);
	}

	public String get_POSITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION", calNewValueFormated);
	}
		public Long get_POSITION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("POSITION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_POSITION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("POSITION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_POSITION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("POSITION").getDoubleValue();
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
	public String get_POSITION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSITION_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_POSITION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSITION_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_POSITION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("POSITION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_POSITION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("POSITION").getBigDecimalValue();
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
	
	
	public String get_POSITION_ENUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSITION_ENUM");
	}

	public String get_POSITION_ENUM_cF() throws myException
	{
		return this.get_FormatedValue("POSITION_ENUM");	
	}

	public String get_POSITION_ENUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSITION_ENUM");
	}

	public String get_POSITION_ENUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSITION_ENUM",cNotNullValue);
	}

	public String get_POSITION_ENUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSITION_ENUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSITION_ENUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSITION_ENUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSITION_ENUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSITION_ENUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_ENUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_ENUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ENUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_ENUM", calNewValueFormated);
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
		public String get_TITEL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("TITEL_TEXT");
	}

	public String get_TITEL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("TITEL_TEXT");	
	}

	public String get_TITEL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TITEL_TEXT");
	}

	public String get_TITEL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TITEL_TEXT",cNotNullValue);
	}

	public String get_TITEL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TITEL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TITEL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TITEL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TITEL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITEL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITEL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TITEL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TITEL_TEXT", calNewValueFormated);
	}
		public String get_UNDERLINE_AUFZAEHL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("UNDERLINE_AUFZAEHL_TEXT");
	}

	public String get_UNDERLINE_AUFZAEHL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("UNDERLINE_AUFZAEHL_TEXT");	
	}

	public String get_UNDERLINE_AUFZAEHL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UNDERLINE_AUFZAEHL_TEXT");
	}

	public String get_UNDERLINE_AUFZAEHL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UNDERLINE_AUFZAEHL_TEXT",cNotNullValue);
	}

	public String get_UNDERLINE_AUFZAEHL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UNDERLINE_AUFZAEHL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_AUFZAEHL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_AUFZAEHL_TEXT", calNewValueFormated);
	}
		public boolean is_UNDERLINE_AUFZAEHL_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_UNDERLINE_AUFZAEHL_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_UNDERLINE_AUFZAEHL_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_UNDERLINE_AUFZAEHL_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_UNDERLINE_LANG_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("UNDERLINE_LANG_TEXT");
	}

	public String get_UNDERLINE_LANG_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("UNDERLINE_LANG_TEXT");	
	}

	public String get_UNDERLINE_LANG_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UNDERLINE_LANG_TEXT");
	}

	public String get_UNDERLINE_LANG_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UNDERLINE_LANG_TEXT",cNotNullValue);
	}

	public String get_UNDERLINE_LANG_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UNDERLINE_LANG_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UNDERLINE_LANG_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UNDERLINE_LANG_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_LANG_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_LANG_TEXT", calNewValueFormated);
	}
		public boolean is_UNDERLINE_LANG_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_UNDERLINE_LANG_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_UNDERLINE_LANG_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_UNDERLINE_LANG_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_UNDERLINE_TITEL_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("UNDERLINE_TITEL_TEXT");
	}

	public String get_UNDERLINE_TITEL_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("UNDERLINE_TITEL_TEXT");	
	}

	public String get_UNDERLINE_TITEL_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UNDERLINE_TITEL_TEXT");
	}

	public String get_UNDERLINE_TITEL_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UNDERLINE_TITEL_TEXT",cNotNullValue);
	}

	public String get_UNDERLINE_TITEL_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UNDERLINE_TITEL_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UNDERLINE_TITEL_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UNDERLINE_TITEL_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNDERLINE_TITEL_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNDERLINE_TITEL_TEXT", calNewValueFormated);
	}
		public boolean is_UNDERLINE_TITEL_TEXT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_UNDERLINE_TITEL_TEXT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_UNDERLINE_TITEL_TEXT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_UNDERLINE_TITEL_TEXT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AUFZAEHL_TEXT",MyRECORD.DATATYPES.TEXT);
	put("BOLD_AUFZAEHL_TEXT",MyRECORD.DATATYPES.YESNO);
	put("BOLD_LANG_TEXT",MyRECORD.DATATYPES.YESNO);
	put("BOLD_TITEL_TEXT",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FONTSIZE_AUFZAEHL_TEXT",MyRECORD.DATATYPES.NUMBER);
	put("FONTSIZE_LANG_TEXT",MyRECORD.DATATYPES.NUMBER);
	put("FONTSIZE_TITEL_TEXT",MyRECORD.DATATYPES.NUMBER);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TABLE",MyRECORD.DATATYPES.NUMBER);
	put("ID_TEXT_LISTE",MyRECORD.DATATYPES.NUMBER);
	put("ITALIC_AUFZAEHL_TEXT",MyRECORD.DATATYPES.YESNO);
	put("ITALIC_LANG_TEXT",MyRECORD.DATATYPES.YESNO);
	put("ITALIC_TITEL_TEXT",MyRECORD.DATATYPES.YESNO);
	put("LANG_TEXT",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("POSITION",MyRECORD.DATATYPES.NUMBER);
	put("POSITION_ENUM",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TABLENAME",MyRECORD.DATATYPES.TEXT);
	put("TITEL_TEXT",MyRECORD.DATATYPES.TEXT);
	put("UNDERLINE_AUFZAEHL_TEXT",MyRECORD.DATATYPES.YESNO);
	put("UNDERLINE_LANG_TEXT",MyRECORD.DATATYPES.YESNO);
	put("UNDERLINE_TITEL_TEXT",MyRECORD.DATATYPES.YESNO);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_TEXT_LISTE.HM_FIELDS;	
	}

}
