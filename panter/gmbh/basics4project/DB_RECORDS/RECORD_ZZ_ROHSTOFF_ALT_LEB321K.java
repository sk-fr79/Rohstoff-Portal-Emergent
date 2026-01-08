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

public class RECORD_ZZ_ROHSTOFF_ALT_LEB321K extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ZZ_ROHSTOFF_ALT_LEB321K";
    public static String IDFIELD   = "ID_ZZ_ROHSTOFF_ALT_LEB321K";
    

	//erzeugen eines RECORDNEW_JT_ZZ_ROHSTOFF_ALT_LEB321K - felds
	private RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K   recNEW = null;

    private _TAB  tab = _TAB.zz_rohstoff_alt_leb321k;  



	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");
	}


	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");
	}



	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K(RECORD_ZZ_ROHSTOFF_ALT_LEB321K recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
	}


	//2014-04-03
	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
	}




	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K(long lID_Unformated) throws myException
	{
		super("JT_ZZ_ROHSTOFF_ALT_LEB321K","ID_ZZ_ROHSTOFF_ALT_LEB321K",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
	}

	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K", "ID_ZZ_ROHSTOFF_ALT_LEB321K="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
	}
	
	

	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ZZ_ROHSTOFF_ALT_LEB321K","ID_ZZ_ROHSTOFF_ALT_LEB321K",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);

	}


	public RECORD_ZZ_ROHSTOFF_ALT_LEB321K(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ZZ_ROHSTOFF_ALT_LEB321K");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K", "ID_ZZ_ROHSTOFF_ALT_LEB321K="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ZZ_ROHSTOFF_ALT_LEB321K.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ZZ_ROHSTOFF_ALT_LEB321K";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ZZ_ROHSTOFF_ALT_LEB321K was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ZZ_ROHSTOFF_ALT_LEB321K", bibE2.cTO(), "ID_ZZ_ROHSTOFF_ALT_LEB321K="+this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ZZ_ROHSTOFF_ALT_LEB321K was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ZZ_ROHSTOFF_ALT_LEB321K", bibE2.cTO(), "ID_ZZ_ROHSTOFF_ALT_LEB321K="+this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K WHERE ID_ZZ_ROHSTOFF_ALT_LEB321K="+this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K WHERE ID_ZZ_ROHSTOFF_ALT_LEB321K="+this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF();
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
			if (S.isFull(this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K", "ID_ZZ_ROHSTOFF_ALT_LEB321K="+this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K get_RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance() throws myException {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K();
	}
	
	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(Conn);
    }

	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance(RECORD_ZZ_ROHSTOFF_ALT_LEB321K recordOrig) {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(recordOrig);
    }

	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(lID_Unformated);
    }

	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(lID_Unformated, Conn);
	}

	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ZZ_ROHSTOFF_ALT_LEB321K create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(recordOrig);	    
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
    public RECORD_ZZ_ROHSTOFF_ALT_LEB321K build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ZZ_ROHSTOFF_ALT_LEB321K WHERE ID_ZZ_ROHSTOFF_ALT_LEB321K="+this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF());
      }
      //return new RECORD_ZZ_ROHSTOFF_ALT_LEB321K(this.get_cSQL_4_Build());
      RECORD_ZZ_ROHSTOFF_ALT_LEB321K rec = new RECORD_ZZ_ROHSTOFF_ALT_LEB321K();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K  recNew = new RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K();
		
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
    public RECORD_ZZ_ROHSTOFF_ALT_LEB321K set_recordNew(RECORDNEW_ZZ_ROHSTOFF_ALT_LEB321K recnew) throws myException {
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
	
	

	public static String FIELD__ABGESCHLOSSEN = "ABGESCHLOSSEN";
	public static String FIELD__BEZUG = "BEZUG";
	public static String FIELD__BEZUG2 = "BEZUG2";
	public static String FIELD__DATUM = "DATUM";
	public static String FIELD__DRUCKKENNZ = "DRUCKKENNZ";
	public static String FIELD__ERFASSER = "ERFASSER";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_ZZ_ROHSTOFF_ALT_LEB321K = "ID_ZZ_ROHSTOFF_ALT_LEB321K";
	public static String FIELD__KONTRAKTPARTNER = "KONTRAKTPARTNER";
	public static String FIELD__KONTRAKT_NR = "KONTRAKT_NR";
	public static String FIELD__LAGER = "LAGER";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LFRBED = "LFRBED";
	public static String FIELD__LFRBED_TEXT = "LFRBED_TEXT";
	public static String FIELD__LIEFERADRESSE = "LIEFERADRESSE";
	public static String FIELD__LIEFERORT = "LIEFERORT";
	public static String FIELD__LIEFERZEIT = "LIEFERZEIT";
	public static String FIELD__LIEF_NR = "LIEF_NR";
	public static String FIELD__PREISSTELLUNG = "PREISSTELLUNG";
	public static String FIELD__RECNUM = "RECNUM";
	public static String FIELD__STATUSKENNZ = "STATUSKENNZ";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TEXTBAUSTEINNR = "TEXTBAUSTEINNR";
	public static String FIELD__TRANSPORTMITTEL = "TRANSPORTMITTEL";
	public static String FIELD__VERKAEUFER = "VERKAEUFER";
	public static String FIELD__ZLGBED = "ZLGBED";
	public static String FIELD__ZLGBED_TEXT = "ZLGBED_TEXT";


	public String get_ABGESCHLOSSEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN");	
	}

	public String get_ABGESCHLOSSEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", calNewValueFormated);
	}
		public boolean is_ABGESCHLOSSEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABGESCHLOSSEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BEZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZUG");
	}

	public String get_BEZUG_cF() throws myException
	{
		return this.get_FormatedValue("BEZUG");	
	}

	public String get_BEZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZUG");
	}

	public String get_BEZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZUG",cNotNullValue);
	}

	public String get_BEZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG", calNewValueFormated);
	}
		public String get_BEZUG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZUG2");
	}

	public String get_BEZUG2_cF() throws myException
	{
		return this.get_FormatedValue("BEZUG2");	
	}

	public String get_BEZUG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZUG2");
	}

	public String get_BEZUG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZUG2",cNotNullValue);
	}

	public String get_BEZUG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZUG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZUG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZUG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZUG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZUG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG2", calNewValueFormated);
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
		public String get_DRUCKKENNZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("DRUCKKENNZ");
	}

	public String get_DRUCKKENNZ_cF() throws myException
	{
		return this.get_FormatedValue("DRUCKKENNZ");	
	}

	public String get_DRUCKKENNZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DRUCKKENNZ");
	}

	public String get_DRUCKKENNZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DRUCKKENNZ",cNotNullValue);
	}

	public String get_DRUCKKENNZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DRUCKKENNZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DRUCKKENNZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DRUCKKENNZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DRUCKKENNZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DRUCKKENNZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKKENNZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKKENNZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKKENNZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKKENNZ", calNewValueFormated);
	}
		public Long get_DRUCKKENNZ_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DRUCKKENNZ").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DRUCKKENNZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DRUCKKENNZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DRUCKKENNZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DRUCKKENNZ").getDoubleValue();
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
	public String get_DRUCKKENNZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKKENNZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_DRUCKKENNZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKKENNZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_DRUCKKENNZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKKENNZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DRUCKKENNZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKKENNZ").getBigDecimalValue();
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
	
	
	public String get_ERFASSER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERFASSER");
	}

	public String get_ERFASSER_cF() throws myException
	{
		return this.get_FormatedValue("ERFASSER");	
	}

	public String get_ERFASSER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERFASSER");
	}

	public String get_ERFASSER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERFASSER",cNotNullValue);
	}

	public String get_ERFASSER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERFASSER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERFASSER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERFASSER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERFASSER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERFASSER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER", calNewValueFormated);
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
		public String get_ID_ADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE");
	}

	public String get_ID_ADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE");	
	}

	public String get_ID_ADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE");
	}

	public String get_ID_ADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE",cNotNullValue);
	}

	public String get_ID_ADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE").getDoubleValue();
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
	public String get_ID_ADRESSE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE").getBigDecimalValue();
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
	
	
	public String get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ZZ_ROHSTOFF_ALT_LEB321K");
	}

	public String get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cF() throws myException
	{
		return this.get_FormatedValue("ID_ZZ_ROHSTOFF_ALT_LEB321K");	
	}

	public String get_ID_ZZ_ROHSTOFF_ALT_LEB321K_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ZZ_ROHSTOFF_ALT_LEB321K");
	}

	public String get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ZZ_ROHSTOFF_ALT_LEB321K",cNotNullValue);
	}

	public String get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ZZ_ROHSTOFF_ALT_LEB321K",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_ROHSTOFF_ALT_LEB321K_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZZ_ROHSTOFF_ALT_LEB321K", calNewValueFormated);
	}
		public Long get_ID_ZZ_ROHSTOFF_ALT_LEB321K_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ZZ_ROHSTOFF_ALT_LEB321K").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ZZ_ROHSTOFF_ALT_LEB321K_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ZZ_ROHSTOFF_ALT_LEB321K").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ZZ_ROHSTOFF_ALT_LEB321K_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ZZ_ROHSTOFF_ALT_LEB321K").getDoubleValue();
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
	public String get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ZZ_ROHSTOFF_ALT_LEB321K_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZZ_ROHSTOFF_ALT_LEB321K_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ZZ_ROHSTOFF_ALT_LEB321K_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZZ_ROHSTOFF_ALT_LEB321K").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ZZ_ROHSTOFF_ALT_LEB321K_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZZ_ROHSTOFF_ALT_LEB321K").getBigDecimalValue();
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
	
	
	public String get_KONTRAKTPARTNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("KONTRAKTPARTNER");
	}

	public String get_KONTRAKTPARTNER_cF() throws myException
	{
		return this.get_FormatedValue("KONTRAKTPARTNER");	
	}

	public String get_KONTRAKTPARTNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KONTRAKTPARTNER");
	}

	public String get_KONTRAKTPARTNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KONTRAKTPARTNER",cNotNullValue);
	}

	public String get_KONTRAKTPARTNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KONTRAKTPARTNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KONTRAKTPARTNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KONTRAKTPARTNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KONTRAKTPARTNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKTPARTNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTRAKTPARTNER", calNewValueFormated);
	}
		public String get_KONTRAKT_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("KONTRAKT_NR");
	}

	public String get_KONTRAKT_NR_cF() throws myException
	{
		return this.get_FormatedValue("KONTRAKT_NR");	
	}

	public String get_KONTRAKT_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KONTRAKT_NR");
	}

	public String get_KONTRAKT_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KONTRAKT_NR",cNotNullValue);
	}

	public String get_KONTRAKT_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KONTRAKT_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KONTRAKT_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KONTRAKT_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KONTRAKT_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KONTRAKT_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTRAKT_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTRAKT_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTRAKT_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTRAKT_NR", calNewValueFormated);
	}
		public Long get_KONTRAKT_NR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("KONTRAKT_NR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_KONTRAKT_NR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KONTRAKT_NR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KONTRAKT_NR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KONTRAKT_NR").getDoubleValue();
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
	public String get_KONTRAKT_NR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KONTRAKT_NR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KONTRAKT_NR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KONTRAKT_NR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KONTRAKT_NR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KONTRAKT_NR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KONTRAKT_NR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KONTRAKT_NR").getBigDecimalValue();
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
	
	
	public String get_LAGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAGER");
	}

	public String get_LAGER_cF() throws myException
	{
		return this.get_FormatedValue("LAGER");	
	}

	public String get_LAGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAGER");
	}

	public String get_LAGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAGER",cNotNullValue);
	}

	public String get_LAGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER", calNewValueFormated);
	}
		public Long get_LAGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LAGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LAGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LAGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LAGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LAGER").getDoubleValue();
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
	public String get_LAGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LAGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LAGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LAGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LAGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LAGER").getBigDecimalValue();
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
		public String get_LFRBED_cUF() throws myException
	{
		return this.get_UnFormatedValue("LFRBED");
	}

	public String get_LFRBED_cF() throws myException
	{
		return this.get_FormatedValue("LFRBED");	
	}

	public String get_LFRBED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LFRBED");
	}

	public String get_LFRBED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LFRBED",cNotNullValue);
	}

	public String get_LFRBED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LFRBED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LFRBED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LFRBED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LFRBED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LFRBED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LFRBED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFRBED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFRBED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFRBED", calNewValueFormated);
	}
		public Long get_LFRBED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LFRBED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LFRBED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LFRBED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LFRBED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LFRBED").getDoubleValue();
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
	public String get_LFRBED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LFRBED_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LFRBED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LFRBED_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LFRBED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LFRBED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LFRBED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LFRBED").getBigDecimalValue();
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
	
	
	public String get_LFRBED_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LFRBED_TEXT");
	}

	public String get_LFRBED_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("LFRBED_TEXT");	
	}

	public String get_LFRBED_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LFRBED_TEXT");
	}

	public String get_LFRBED_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LFRBED_TEXT",cNotNullValue);
	}

	public String get_LFRBED_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LFRBED_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LFRBED_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LFRBED_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LFRBED_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFRBED_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFRBED_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFRBED_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFRBED_TEXT", calNewValueFormated);
	}
		public String get_LIEFERADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERADRESSE");
	}

	public String get_LIEFERADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERADRESSE");	
	}

	public String get_LIEFERADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERADRESSE");
	}

	public String get_LIEFERADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERADRESSE",cNotNullValue);
	}

	public String get_LIEFERADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERADRESSE", calNewValueFormated);
	}
		public String get_LIEFERORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERORT");
	}

	public String get_LIEFERORT_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERORT");	
	}

	public String get_LIEFERORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERORT");
	}

	public String get_LIEFERORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERORT",cNotNullValue);
	}

	public String get_LIEFERORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERORT", calNewValueFormated);
	}
		public String get_LIEFERZEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERZEIT");
	}

	public String get_LIEFERZEIT_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERZEIT");	
	}

	public String get_LIEFERZEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERZEIT");
	}

	public String get_LIEFERZEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERZEIT",cNotNullValue);
	}

	public String get_LIEFERZEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERZEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERZEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERZEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERZEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERZEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERZEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERZEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERZEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERZEIT", calNewValueFormated);
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
		public String get_PREISSTELLUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREISSTELLUNG");
	}

	public String get_PREISSTELLUNG_cF() throws myException
	{
		return this.get_FormatedValue("PREISSTELLUNG");	
	}

	public String get_PREISSTELLUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREISSTELLUNG");
	}

	public String get_PREISSTELLUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREISSTELLUNG",cNotNullValue);
	}

	public String get_PREISSTELLUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREISSTELLUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREISSTELLUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREISSTELLUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREISSTELLUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREISSTELLUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISSTELLUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISSTELLUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISSTELLUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISSTELLUNG", calNewValueFormated);
	}
		public String get_RECNUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECNUM");
	}

	public String get_RECNUM_cF() throws myException
	{
		return this.get_FormatedValue("RECNUM");	
	}

	public String get_RECNUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECNUM");
	}

	public String get_RECNUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECNUM",cNotNullValue);
	}

	public String get_RECNUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECNUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECNUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECNUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECNUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECNUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECNUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECNUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECNUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECNUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECNUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECNUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECNUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECNUM", calNewValueFormated);
	}
		public Long get_RECNUM_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("RECNUM").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_RECNUM_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("RECNUM").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_RECNUM_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("RECNUM").getDoubleValue();
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
	public String get_RECNUM_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_RECNUM_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_RECNUM_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_RECNUM_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_RECNUM_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("RECNUM").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_RECNUM_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("RECNUM").getBigDecimalValue();
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
	
	
	public String get_STATUSKENNZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATUSKENNZ");
	}

	public String get_STATUSKENNZ_cF() throws myException
	{
		return this.get_FormatedValue("STATUSKENNZ");	
	}

	public String get_STATUSKENNZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATUSKENNZ");
	}

	public String get_STATUSKENNZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATUSKENNZ",cNotNullValue);
	}

	public String get_STATUSKENNZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATUSKENNZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATUSKENNZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATUSKENNZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATUSKENNZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATUSKENNZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUSKENNZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUSKENNZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUSKENNZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUSKENNZ", calNewValueFormated);
	}
		public boolean is_STATUSKENNZ_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STATUSKENNZ_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STATUSKENNZ_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STATUSKENNZ_cUF_NN("N").equals("N"))
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
	
	
	public String get_TEXTBAUSTEINNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEXTBAUSTEINNR");
	}

	public String get_TEXTBAUSTEINNR_cF() throws myException
	{
		return this.get_FormatedValue("TEXTBAUSTEINNR");	
	}

	public String get_TEXTBAUSTEINNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEXTBAUSTEINNR");
	}

	public String get_TEXTBAUSTEINNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEXTBAUSTEINNR",cNotNullValue);
	}

	public String get_TEXTBAUSTEINNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEXTBAUSTEINNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEXTBAUSTEINNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEXTBAUSTEINNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEXTBAUSTEINNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTBAUSTEINNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTBAUSTEINNR", calNewValueFormated);
	}
		public Long get_TEXTBAUSTEINNR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("TEXTBAUSTEINNR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_TEXTBAUSTEINNR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("TEXTBAUSTEINNR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_TEXTBAUSTEINNR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("TEXTBAUSTEINNR").getDoubleValue();
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
	public String get_TEXTBAUSTEINNR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TEXTBAUSTEINNR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_TEXTBAUSTEINNR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TEXTBAUSTEINNR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_TEXTBAUSTEINNR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("TEXTBAUSTEINNR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_TEXTBAUSTEINNR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("TEXTBAUSTEINNR").getBigDecimalValue();
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
	
	
	public String get_TRANSPORTMITTEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTMITTEL");
	}

	public String get_TRANSPORTMITTEL_cF() throws myException
	{
		return this.get_FormatedValue("TRANSPORTMITTEL");	
	}

	public String get_TRANSPORTMITTEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSPORTMITTEL");
	}

	public String get_TRANSPORTMITTEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTMITTEL",cNotNullValue);
	}

	public String get_TRANSPORTMITTEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSPORTMITTEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", calNewValueFormated);
	}
		public boolean is_TRANSPORTMITTEL_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSPORTMITTEL_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSPORTMITTEL_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSPORTMITTEL_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_VERKAEUFER_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERKAEUFER");
	}

	public String get_VERKAEUFER_cF() throws myException
	{
		return this.get_FormatedValue("VERKAEUFER");	
	}

	public String get_VERKAEUFER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERKAEUFER");
	}

	public String get_VERKAEUFER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERKAEUFER",cNotNullValue);
	}

	public String get_VERKAEUFER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERKAEUFER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERKAEUFER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERKAEUFER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERKAEUFER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERKAEUFER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERKAEUFER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERKAEUFER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKAEUFER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERKAEUFER", calNewValueFormated);
	}
		public String get_ZLGBED_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZLGBED");
	}

	public String get_ZLGBED_cF() throws myException
	{
		return this.get_FormatedValue("ZLGBED");	
	}

	public String get_ZLGBED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZLGBED");
	}

	public String get_ZLGBED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZLGBED",cNotNullValue);
	}

	public String get_ZLGBED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZLGBED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZLGBED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZLGBED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZLGBED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZLGBED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZLGBED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZLGBED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZLGBED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZLGBED", calNewValueFormated);
	}
		public Long get_ZLGBED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ZLGBED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ZLGBED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZLGBED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZLGBED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZLGBED").getDoubleValue();
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
	public String get_ZLGBED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZLGBED_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ZLGBED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZLGBED_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ZLGBED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZLGBED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZLGBED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZLGBED").getBigDecimalValue();
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
	
	
	public String get_ZLGBED_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZLGBED_TEXT");
	}

	public String get_ZLGBED_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("ZLGBED_TEXT");	
	}

	public String get_ZLGBED_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZLGBED_TEXT");
	}

	public String get_ZLGBED_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZLGBED_TEXT",cNotNullValue);
	}

	public String get_ZLGBED_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZLGBED_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZLGBED_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZLGBED_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZLGBED_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZLGBED_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZLGBED_TEXT", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABGESCHLOSSEN",MyRECORD.DATATYPES.YESNO);
	put("BEZUG",MyRECORD.DATATYPES.TEXT);
	put("BEZUG2",MyRECORD.DATATYPES.TEXT);
	put("DATUM",MyRECORD.DATATYPES.DATE);
	put("DRUCKKENNZ",MyRECORD.DATATYPES.NUMBER);
	put("ERFASSER",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZZ_ROHSTOFF_ALT_LEB321K",MyRECORD.DATATYPES.NUMBER);
	put("KONTRAKTPARTNER",MyRECORD.DATATYPES.TEXT);
	put("KONTRAKT_NR",MyRECORD.DATATYPES.NUMBER);
	put("LAGER",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LFRBED",MyRECORD.DATATYPES.NUMBER);
	put("LFRBED_TEXT",MyRECORD.DATATYPES.TEXT);
	put("LIEFERADRESSE",MyRECORD.DATATYPES.TEXT);
	put("LIEFERORT",MyRECORD.DATATYPES.TEXT);
	put("LIEFERZEIT",MyRECORD.DATATYPES.TEXT);
	put("LIEF_NR",MyRECORD.DATATYPES.TEXT);
	put("PREISSTELLUNG",MyRECORD.DATATYPES.TEXT);
	put("RECNUM",MyRECORD.DATATYPES.NUMBER);
	put("STATUSKENNZ",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TEXTBAUSTEINNR",MyRECORD.DATATYPES.NUMBER);
	put("TRANSPORTMITTEL",MyRECORD.DATATYPES.YESNO);
	put("VERKAEUFER",MyRECORD.DATATYPES.TEXT);
	put("ZLGBED",MyRECORD.DATATYPES.NUMBER);
	put("ZLGBED_TEXT",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ZZ_ROHSTOFF_ALT_LEB321K.HM_FIELDS;	
	}

}
