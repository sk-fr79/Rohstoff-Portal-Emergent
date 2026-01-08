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

public class RECORD_TESTTABELLE_HANDELDEF extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_TESTTABELLE_HANDELDEF";
    public static String IDFIELD   = "ID_TESTTABELLE_HANDELDEF";
    

	//erzeugen eines RECORDNEW_JT_TESTTABELLE_HANDELDEF - felds
	private RECORDNEW_TESTTABELLE_HANDELDEF   recNEW = null;

    private _TAB  tab = _TAB.testtabelle_handeldef;  



	public RECORD_TESTTABELLE_HANDELDEF() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");
	}


	public RECORD_TESTTABELLE_HANDELDEF(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");
	}



	public RECORD_TESTTABELLE_HANDELDEF(RECORD_TESTTABELLE_HANDELDEF recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TESTTABELLE_HANDELDEF.TABLENAME);
	}


	//2014-04-03
	public RECORD_TESTTABELLE_HANDELDEF(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");
		this.set_Tablename_To_FieldMetaDefs(RECORD_TESTTABELLE_HANDELDEF.TABLENAME);
	}




	public RECORD_TESTTABELLE_HANDELDEF(long lID_Unformated) throws myException
	{
		super("JT_TESTTABELLE_HANDELDEF","ID_TESTTABELLE_HANDELDEF",""+lID_Unformated);
		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TESTTABELLE_HANDELDEF.TABLENAME);
	}

	public RECORD_TESTTABELLE_HANDELDEF(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF", "ID_TESTTABELLE_HANDELDEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TESTTABELLE_HANDELDEF.TABLENAME);
	}
	
	

	public RECORD_TESTTABELLE_HANDELDEF(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_TESTTABELLE_HANDELDEF","ID_TESTTABELLE_HANDELDEF",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TESTTABELLE_HANDELDEF.TABLENAME);

	}


	public RECORD_TESTTABELLE_HANDELDEF(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TESTTABELLE_HANDELDEF");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF", "ID_TESTTABELLE_HANDELDEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TESTTABELLE_HANDELDEF.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_TESTTABELLE_HANDELDEF();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_TESTTABELLE_HANDELDEF.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_TESTTABELLE_HANDELDEF";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_TESTTABELLE_HANDELDEF_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_TESTTABELLE_HANDELDEF_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TESTTABELLE_HANDELDEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TESTTABELLE_HANDELDEF", bibE2.cTO(), "ID_TESTTABELLE_HANDELDEF="+this.get_ID_TESTTABELLE_HANDELDEF_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TESTTABELLE_HANDELDEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TESTTABELLE_HANDELDEF", bibE2.cTO(), "ID_TESTTABELLE_HANDELDEF="+this.get_ID_TESTTABELLE_HANDELDEF_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF WHERE ID_TESTTABELLE_HANDELDEF="+this.get_ID_TESTTABELLE_HANDELDEF_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF WHERE ID_TESTTABELLE_HANDELDEF="+this.get_ID_TESTTABELLE_HANDELDEF_cUF();
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
			if (S.isFull(this.get_ID_TESTTABELLE_HANDELDEF_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF", "ID_TESTTABELLE_HANDELDEF="+this.get_ID_TESTTABELLE_HANDELDEF_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_TESTTABELLE_HANDELDEF get_RECORDNEW_TESTTABELLE_HANDELDEF() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_TESTTABELLE_HANDELDEF();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_TESTTABELLE_HANDELDEF(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_TESTTABELLE_HANDELDEF create_Instance() throws myException {
		return new RECORD_TESTTABELLE_HANDELDEF();
	}
	
	public static RECORD_TESTTABELLE_HANDELDEF create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_TESTTABELLE_HANDELDEF(Conn);
    }

	public static RECORD_TESTTABELLE_HANDELDEF create_Instance(RECORD_TESTTABELLE_HANDELDEF recordOrig) {
		return new RECORD_TESTTABELLE_HANDELDEF(recordOrig);
    }

	public static RECORD_TESTTABELLE_HANDELDEF create_Instance(long lID_Unformated) throws myException {
		return new RECORD_TESTTABELLE_HANDELDEF(lID_Unformated);
    }

	public static RECORD_TESTTABELLE_HANDELDEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_TESTTABELLE_HANDELDEF(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_TESTTABELLE_HANDELDEF create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_TESTTABELLE_HANDELDEF(lID_Unformated, Conn);
	}

	public static RECORD_TESTTABELLE_HANDELDEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_TESTTABELLE_HANDELDEF(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_TESTTABELLE_HANDELDEF create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_TESTTABELLE_HANDELDEF(recordOrig);	    
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
    public RECORD_TESTTABELLE_HANDELDEF build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_TESTTABELLE_HANDELDEF WHERE ID_TESTTABELLE_HANDELDEF="+this.get_ID_TESTTABELLE_HANDELDEF_cUF());
      }
      //return new RECORD_TESTTABELLE_HANDELDEF(this.get_cSQL_4_Build());
      RECORD_TESTTABELLE_HANDELDEF rec = new RECORD_TESTTABELLE_HANDELDEF();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_TESTTABELLE_HANDELDEF
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_TESTTABELLE_HANDELDEF-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_TESTTABELLE_HANDELDEF get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_TESTTABELLE_HANDELDEF_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_TESTTABELLE_HANDELDEF  recNew = new RECORDNEW_TESTTABELLE_HANDELDEF();
		
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
    public RECORD_TESTTABELLE_HANDELDEF set_recordNew(RECORDNEW_TESTTABELLE_HANDELDEF recnew) throws myException {
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
	
	

	public static String FIELD__EPREIS_QUELLE = "EPREIS_QUELLE";
	public static String FIELD__EPREIS_ZIEL = "EPREIS_ZIEL";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_HANDELSDEF = "ID_HANDELSDEF";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TAX_START_HD = "ID_TAX_START_HD";
	public static String FIELD__ID_TAX_ZIEL_HD = "ID_TAX_ZIEL_HD";
	public static String FIELD__ID_TESTTABELLE_HANDELDEF = "ID_TESTTABELLE_HANDELDEF";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ORT = "ID_VPOS_TPA_FUHRE_ORT";
	public static String FIELD__INTRASTAT_HD = "INTRASTAT_HD";
	public static String FIELD__LAENDERCODE_START = "LAENDERCODE_START";
	public static String FIELD__LAENDERCODE_ZIEL = "LAENDERCODE_ZIEL";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__OM_ID_LAND_QUELLE_GEO = "OM_ID_LAND_QUELLE_GEO";
	public static String FIELD__OM_ID_LAND_QUELLE_JUR = "OM_ID_LAND_QUELLE_JUR";
	public static String FIELD__OM_ID_LAND_ZIEL_GEO = "OM_ID_LAND_ZIEL_GEO";
	public static String FIELD__OM_ID_LAND_ZIEL_JUR = "OM_ID_LAND_ZIEL_JUR";
	public static String FIELD__OM_START_DIENSTLST = "OM_START_DIENSTLST";
	public static String FIELD__OM_START_EOW = "OM_START_EOW";
	public static String FIELD__OM_START_IST_MANDANT = "OM_START_IST_MANDANT";
	public static String FIELD__OM_START_LAND_GEO = "OM_START_LAND_GEO";
	public static String FIELD__OM_START_LAND_JUR = "OM_START_LAND_JUR";
	public static String FIELD__OM_START_PRODUKT = "OM_START_PRODUKT";
	public static String FIELD__OM_START_RC = "OM_START_RC";
	public static String FIELD__OM_START_UNTERNEHMEN = "OM_START_UNTERNEHMEN";
	public static String FIELD__OM_TPA_VERANTWORT = "OM_TPA_VERANTWORT";
	public static String FIELD__OM_ZIEL_DIENSTLST = "OM_ZIEL_DIENSTLST";
	public static String FIELD__OM_ZIEL_EOW = "OM_ZIEL_EOW";
	public static String FIELD__OM_ZIEL_IST_MANDANT = "OM_ZIEL_IST_MANDANT";
	public static String FIELD__OM_ZIEL_LAND_GEO = "OM_ZIEL_LAND_GEO";
	public static String FIELD__OM_ZIEL_LAND_JUR = "OM_ZIEL_LAND_JUR";
	public static String FIELD__OM_ZIEL_PRODUKT = "OM_ZIEL_PRODUKT";
	public static String FIELD__OM_ZIEL_RC = "OM_ZIEL_RC";
	public static String FIELD__OM_ZIEL_UNTERNEHMEN = "OM_ZIEL_UNTERNEHMEN";
	public static String FIELD__STEUERSATZ_START_ALT = "STEUERSATZ_START_ALT";
	public static String FIELD__STEUERSATZ_START_HD = "STEUERSATZ_START_HD";
	public static String FIELD__STEUERSATZ_ZIEL_ALT = "STEUERSATZ_ZIEL_ALT";
	public static String FIELD__STEUERSATZ_ZIEL_HD = "STEUERSATZ_ZIEL_HD";
	public static String FIELD__STEUERVERMERK_START_ALT = "STEUERVERMERK_START_ALT";
	public static String FIELD__STEUERVERMERK_START_HD = "STEUERVERMERK_START_HD";
	public static String FIELD__STEUERVERMERK_ZIEL_ALT = "STEUERVERMERK_ZIEL_ALT";
	public static String FIELD__STEUERVERMERK_ZIEL_HD = "STEUERVERMERK_ZIEL_HD";
	public static String FIELD__SUCHERGEBNIS = "SUCHERGEBNIS";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_EPREIS_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_QUELLE");
	}

	public String get_EPREIS_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_QUELLE");	
	}

	public String get_EPREIS_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_QUELLE");
	}

	public String get_EPREIS_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_QUELLE",cNotNullValue);
	}

	public String get_EPREIS_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_QUELLE", calNewValueFormated);
	}
		public Double get_EPREIS_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_QUELLE").getDoubleValue();
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
	public String get_EPREIS_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EPREIS_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EPREIS_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_QUELLE").getBigDecimalValue();
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
	
	
	public String get_EPREIS_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_ZIEL");
	}

	public String get_EPREIS_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_ZIEL");	
	}

	public String get_EPREIS_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_ZIEL");
	}

	public String get_EPREIS_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_ZIEL",cNotNullValue);
	}

	public String get_EPREIS_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_ZIEL", calNewValueFormated);
	}
		public Double get_EPREIS_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_ZIEL").getDoubleValue();
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
	public String get_EPREIS_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EPREIS_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EPREIS_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_ZIEL").getBigDecimalValue();
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
		public String get_ID_HANDELSDEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_HANDELSDEF");
	}

	public String get_ID_HANDELSDEF_cF() throws myException
	{
		return this.get_FormatedValue("ID_HANDELSDEF");	
	}

	public String get_ID_HANDELSDEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_HANDELSDEF");
	}

	public String get_ID_HANDELSDEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_HANDELSDEF",cNotNullValue);
	}

	public String get_ID_HANDELSDEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_HANDELSDEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", calNewValueFormated);
	}
		public Long get_ID_HANDELSDEF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_HANDELSDEF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_HANDELSDEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_HANDELSDEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_HANDELSDEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_HANDELSDEF").getDoubleValue();
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
	public String get_ID_HANDELSDEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_HANDELSDEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_HANDELSDEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_HANDELSDEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_HANDELSDEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_HANDELSDEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_HANDELSDEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_HANDELSDEF").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_START_HD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_START_HD");
	}

	public String get_ID_TAX_START_HD_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_START_HD");	
	}

	public String get_ID_TAX_START_HD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_START_HD");
	}

	public String get_ID_TAX_START_HD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_START_HD",cNotNullValue);
	}

	public String get_ID_TAX_START_HD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_START_HD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_START_HD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_START_HD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_START_HD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_START_HD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_START_HD", calNewValueFormated);
	}
		public Long get_ID_TAX_START_HD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_START_HD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_START_HD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_START_HD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_START_HD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_START_HD").getDoubleValue();
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
	public String get_ID_TAX_START_HD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_START_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_START_HD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_START_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_START_HD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_START_HD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_START_HD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_START_HD").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_ZIEL_HD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_ZIEL_HD");
	}

	public String get_ID_TAX_ZIEL_HD_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_ZIEL_HD");	
	}

	public String get_ID_TAX_ZIEL_HD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_ZIEL_HD");
	}

	public String get_ID_TAX_ZIEL_HD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_ZIEL_HD",cNotNullValue);
	}

	public String get_ID_TAX_ZIEL_HD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_ZIEL_HD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_ZIEL_HD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_ZIEL_HD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_ZIEL_HD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_HD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL_HD", calNewValueFormated);
	}
		public Long get_ID_TAX_ZIEL_HD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_ZIEL_HD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_ZIEL_HD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_ZIEL_HD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_ZIEL_HD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_ZIEL_HD").getDoubleValue();
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
	public String get_ID_TAX_ZIEL_HD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_ZIEL_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_ZIEL_HD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_ZIEL_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_ZIEL_HD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_ZIEL_HD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_ZIEL_HD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_ZIEL_HD").getBigDecimalValue();
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
	
	
	public String get_ID_TESTTABELLE_HANDELDEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TESTTABELLE_HANDELDEF");
	}

	public String get_ID_TESTTABELLE_HANDELDEF_cF() throws myException
	{
		return this.get_FormatedValue("ID_TESTTABELLE_HANDELDEF");	
	}

	public String get_ID_TESTTABELLE_HANDELDEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TESTTABELLE_HANDELDEF");
	}

	public String get_ID_TESTTABELLE_HANDELDEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TESTTABELLE_HANDELDEF",cNotNullValue);
	}

	public String get_ID_TESTTABELLE_HANDELDEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TESTTABELLE_HANDELDEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TESTTABELLE_HANDELDEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TESTTABELLE_HANDELDEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TESTTABELLE_HANDELDEF", calNewValueFormated);
	}
		public Long get_ID_TESTTABELLE_HANDELDEF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TESTTABELLE_HANDELDEF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TESTTABELLE_HANDELDEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TESTTABELLE_HANDELDEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TESTTABELLE_HANDELDEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TESTTABELLE_HANDELDEF").getDoubleValue();
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
	public String get_ID_TESTTABELLE_HANDELDEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TESTTABELLE_HANDELDEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TESTTABELLE_HANDELDEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TESTTABELLE_HANDELDEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TESTTABELLE_HANDELDEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TESTTABELLE_HANDELDEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TESTTABELLE_HANDELDEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TESTTABELLE_HANDELDEF").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
	}

	public String get_ID_VPOS_TPA_FUHRE_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE");	
	}

	public String get_ID_VPOS_TPA_FUHRE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE");
	}

	public String get_ID_VPOS_TPA_FUHRE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT");
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ORT");	
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_ORT");
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_ORT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_ORT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_ORT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_ORT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_ORT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_ORT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getBigDecimalValue();
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
	
	
	public String get_INTRASTAT_HD_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_HD");
	}

	public String get_INTRASTAT_HD_cF() throws myException
	{
		return this.get_FormatedValue("INTRASTAT_HD");	
	}

	public String get_INTRASTAT_HD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTRASTAT_HD");
	}

	public String get_INTRASTAT_HD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_HD",cNotNullValue);
	}

	public String get_INTRASTAT_HD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTRASTAT_HD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTRASTAT_HD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_HD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTRASTAT_HD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTRASTAT_HD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_HD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_HD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_HD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_HD", calNewValueFormated);
	}
		public boolean is_INTRASTAT_HD_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_HD_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_INTRASTAT_HD_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_HD_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LAENDERCODE_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_START");
	}

	public String get_LAENDERCODE_START_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_START");	
	}

	public String get_LAENDERCODE_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_START");
	}

	public String get_LAENDERCODE_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_START",cNotNullValue);
	}

	public String get_LAENDERCODE_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_START", calNewValueFormated);
	}
		public String get_LAENDERCODE_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_ZIEL");
	}

	public String get_LAENDERCODE_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_ZIEL");	
	}

	public String get_LAENDERCODE_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_ZIEL");
	}

	public String get_LAENDERCODE_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_ZIEL",cNotNullValue);
	}

	public String get_LAENDERCODE_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_ZIEL", calNewValueFormated);
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
		public String get_OM_ID_LAND_QUELLE_GEO_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_QUELLE_GEO");
	}

	public String get_OM_ID_LAND_QUELLE_GEO_cF() throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_QUELLE_GEO");	
	}

	public String get_OM_ID_LAND_QUELLE_GEO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ID_LAND_QUELLE_GEO");
	}

	public String get_OM_ID_LAND_QUELLE_GEO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_QUELLE_GEO",cNotNullValue);
	}

	public String get_OM_ID_LAND_QUELLE_GEO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_QUELLE_GEO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_GEO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_GEO", calNewValueFormated);
	}
		public Long get_OM_ID_LAND_QUELLE_GEO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("OM_ID_LAND_QUELLE_GEO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_OM_ID_LAND_QUELLE_GEO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_QUELLE_GEO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_OM_ID_LAND_QUELLE_GEO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_QUELLE_GEO").getDoubleValue();
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
	public String get_OM_ID_LAND_QUELLE_GEO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_QUELLE_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_OM_ID_LAND_QUELLE_GEO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_QUELLE_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_OM_ID_LAND_QUELLE_GEO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_QUELLE_GEO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_OM_ID_LAND_QUELLE_GEO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_QUELLE_GEO").getBigDecimalValue();
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
	
	
	public String get_OM_ID_LAND_QUELLE_JUR_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_QUELLE_JUR");
	}

	public String get_OM_ID_LAND_QUELLE_JUR_cF() throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_QUELLE_JUR");	
	}

	public String get_OM_ID_LAND_QUELLE_JUR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ID_LAND_QUELLE_JUR");
	}

	public String get_OM_ID_LAND_QUELLE_JUR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_QUELLE_JUR",cNotNullValue);
	}

	public String get_OM_ID_LAND_QUELLE_JUR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_QUELLE_JUR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_QUELLE_JUR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_QUELLE_JUR", calNewValueFormated);
	}
		public Long get_OM_ID_LAND_QUELLE_JUR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("OM_ID_LAND_QUELLE_JUR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_OM_ID_LAND_QUELLE_JUR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_QUELLE_JUR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_OM_ID_LAND_QUELLE_JUR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_QUELLE_JUR").getDoubleValue();
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
	public String get_OM_ID_LAND_QUELLE_JUR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_QUELLE_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_OM_ID_LAND_QUELLE_JUR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_QUELLE_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_OM_ID_LAND_QUELLE_JUR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_QUELLE_JUR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_OM_ID_LAND_QUELLE_JUR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_QUELLE_JUR").getBigDecimalValue();
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
	
	
	public String get_OM_ID_LAND_ZIEL_GEO_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_ZIEL_GEO");
	}

	public String get_OM_ID_LAND_ZIEL_GEO_cF() throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_ZIEL_GEO");	
	}

	public String get_OM_ID_LAND_ZIEL_GEO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ID_LAND_ZIEL_GEO");
	}

	public String get_OM_ID_LAND_ZIEL_GEO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_ZIEL_GEO",cNotNullValue);
	}

	public String get_OM_ID_LAND_ZIEL_GEO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_ZIEL_GEO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_GEO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_GEO", calNewValueFormated);
	}
		public Long get_OM_ID_LAND_ZIEL_GEO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("OM_ID_LAND_ZIEL_GEO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_OM_ID_LAND_ZIEL_GEO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_ZIEL_GEO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_OM_ID_LAND_ZIEL_GEO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_ZIEL_GEO").getDoubleValue();
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
	public String get_OM_ID_LAND_ZIEL_GEO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_ZIEL_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_OM_ID_LAND_ZIEL_GEO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_ZIEL_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_OM_ID_LAND_ZIEL_GEO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_ZIEL_GEO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_OM_ID_LAND_ZIEL_GEO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_ZIEL_GEO").getBigDecimalValue();
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
	
	
	public String get_OM_ID_LAND_ZIEL_JUR_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_ZIEL_JUR");
	}

	public String get_OM_ID_LAND_ZIEL_JUR_cF() throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_ZIEL_JUR");	
	}

	public String get_OM_ID_LAND_ZIEL_JUR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ID_LAND_ZIEL_JUR");
	}

	public String get_OM_ID_LAND_ZIEL_JUR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ID_LAND_ZIEL_JUR",cNotNullValue);
	}

	public String get_OM_ID_LAND_ZIEL_JUR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ID_LAND_ZIEL_JUR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ID_LAND_ZIEL_JUR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ID_LAND_ZIEL_JUR", calNewValueFormated);
	}
		public Long get_OM_ID_LAND_ZIEL_JUR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("OM_ID_LAND_ZIEL_JUR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_OM_ID_LAND_ZIEL_JUR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_ZIEL_JUR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_OM_ID_LAND_ZIEL_JUR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("OM_ID_LAND_ZIEL_JUR").getDoubleValue();
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
	public String get_OM_ID_LAND_ZIEL_JUR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_ZIEL_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_OM_ID_LAND_ZIEL_JUR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OM_ID_LAND_ZIEL_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_OM_ID_LAND_ZIEL_JUR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_ZIEL_JUR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_OM_ID_LAND_ZIEL_JUR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("OM_ID_LAND_ZIEL_JUR").getBigDecimalValue();
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
	
	
	public String get_OM_START_DIENSTLST_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_DIENSTLST");
	}

	public String get_OM_START_DIENSTLST_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_DIENSTLST");	
	}

	public String get_OM_START_DIENSTLST_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_DIENSTLST");
	}

	public String get_OM_START_DIENSTLST_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_DIENSTLST",cNotNullValue);
	}

	public String get_OM_START_DIENSTLST_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_DIENSTLST",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_DIENSTLST", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_DIENSTLST(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_DIENSTLST", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_DIENSTLST_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_DIENSTLST", calNewValueFormated);
	}
		public boolean is_OM_START_DIENSTLST_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_DIENSTLST_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_START_DIENSTLST_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_DIENSTLST_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_START_EOW_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_EOW");
	}

	public String get_OM_START_EOW_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_EOW");	
	}

	public String get_OM_START_EOW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_EOW");
	}

	public String get_OM_START_EOW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_EOW",cNotNullValue);
	}

	public String get_OM_START_EOW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_EOW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_EOW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_EOW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_EOW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_EOW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_EOW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_EOW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_EOW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_EOW", calNewValueFormated);
	}
		public boolean is_OM_START_EOW_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_EOW_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_START_EOW_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_EOW_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_START_IST_MANDANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_IST_MANDANT");
	}

	public String get_OM_START_IST_MANDANT_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_IST_MANDANT");	
	}

	public String get_OM_START_IST_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_IST_MANDANT");
	}

	public String get_OM_START_IST_MANDANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_IST_MANDANT",cNotNullValue);
	}

	public String get_OM_START_IST_MANDANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_IST_MANDANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_IST_MANDANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_IST_MANDANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_IST_MANDANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_IST_MANDANT", calNewValueFormated);
	}
		public boolean is_OM_START_IST_MANDANT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_IST_MANDANT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_START_IST_MANDANT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_IST_MANDANT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_START_LAND_GEO_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_LAND_GEO");
	}

	public String get_OM_START_LAND_GEO_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_LAND_GEO");	
	}

	public String get_OM_START_LAND_GEO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_LAND_GEO");
	}

	public String get_OM_START_LAND_GEO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_LAND_GEO",cNotNullValue);
	}

	public String get_OM_START_LAND_GEO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_LAND_GEO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_LAND_GEO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_LAND_GEO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_LAND_GEO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_GEO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_LAND_GEO", calNewValueFormated);
	}
		public String get_OM_START_LAND_JUR_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_LAND_JUR");
	}

	public String get_OM_START_LAND_JUR_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_LAND_JUR");	
	}

	public String get_OM_START_LAND_JUR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_LAND_JUR");
	}

	public String get_OM_START_LAND_JUR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_LAND_JUR",cNotNullValue);
	}

	public String get_OM_START_LAND_JUR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_LAND_JUR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_LAND_JUR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_LAND_JUR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_LAND_JUR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_LAND_JUR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_LAND_JUR", calNewValueFormated);
	}
		public String get_OM_START_PRODUKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_PRODUKT");
	}

	public String get_OM_START_PRODUKT_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_PRODUKT");	
	}

	public String get_OM_START_PRODUKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_PRODUKT");
	}

	public String get_OM_START_PRODUKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_PRODUKT",cNotNullValue);
	}

	public String get_OM_START_PRODUKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_PRODUKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_PRODUKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_PRODUKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_PRODUKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_PRODUKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_PRODUKT", calNewValueFormated);
	}
		public boolean is_OM_START_PRODUKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_PRODUKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_START_PRODUKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_PRODUKT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_START_RC_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_RC");
	}

	public String get_OM_START_RC_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_RC");	
	}

	public String get_OM_START_RC_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_RC");
	}

	public String get_OM_START_RC_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_RC",cNotNullValue);
	}

	public String get_OM_START_RC_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_RC",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_RC", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_RC(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_RC", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_RC", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_RC", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_RC", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_RC_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_RC", calNewValueFormated);
	}
		public boolean is_OM_START_RC_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_RC_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_START_RC_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_RC_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_START_UNTERNEHMEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_START_UNTERNEHMEN");
	}

	public String get_OM_START_UNTERNEHMEN_cF() throws myException
	{
		return this.get_FormatedValue("OM_START_UNTERNEHMEN");	
	}

	public String get_OM_START_UNTERNEHMEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_START_UNTERNEHMEN");
	}

	public String get_OM_START_UNTERNEHMEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_START_UNTERNEHMEN",cNotNullValue);
	}

	public String get_OM_START_UNTERNEHMEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_START_UNTERNEHMEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_START_UNTERNEHMEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_START_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_START_UNTERNEHMEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_START_UNTERNEHMEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_START_UNTERNEHMEN", calNewValueFormated);
	}
		public boolean is_OM_START_UNTERNEHMEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_UNTERNEHMEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_START_UNTERNEHMEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_START_UNTERNEHMEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_TPA_VERANTWORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_TPA_VERANTWORT");
	}

	public String get_OM_TPA_VERANTWORT_cF() throws myException
	{
		return this.get_FormatedValue("OM_TPA_VERANTWORT");	
	}

	public String get_OM_TPA_VERANTWORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_TPA_VERANTWORT");
	}

	public String get_OM_TPA_VERANTWORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_TPA_VERANTWORT",cNotNullValue);
	}

	public String get_OM_TPA_VERANTWORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_TPA_VERANTWORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_TPA_VERANTWORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_TPA_VERANTWORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_TPA_VERANTWORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_TPA_VERANTWORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_TPA_VERANTWORT", calNewValueFormated);
	}
		public String get_OM_ZIEL_DIENSTLST_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_DIENSTLST");
	}

	public String get_OM_ZIEL_DIENSTLST_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_DIENSTLST");	
	}

	public String get_OM_ZIEL_DIENSTLST_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_DIENSTLST");
	}

	public String get_OM_ZIEL_DIENSTLST_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_DIENSTLST",cNotNullValue);
	}

	public String get_OM_ZIEL_DIENSTLST_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_DIENSTLST",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_DIENSTLST(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_DIENSTLST", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_DIENSTLST_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_DIENSTLST", calNewValueFormated);
	}
		public boolean is_OM_ZIEL_DIENSTLST_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_DIENSTLST_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_ZIEL_DIENSTLST_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_DIENSTLST_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_ZIEL_EOW_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_EOW");
	}

	public String get_OM_ZIEL_EOW_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_EOW");	
	}

	public String get_OM_ZIEL_EOW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_EOW");
	}

	public String get_OM_ZIEL_EOW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_EOW",cNotNullValue);
	}

	public String get_OM_ZIEL_EOW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_EOW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_EOW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_EOW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_EOW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_EOW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_EOW", calNewValueFormated);
	}
		public boolean is_OM_ZIEL_EOW_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_EOW_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_ZIEL_EOW_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_EOW_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_ZIEL_IST_MANDANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_IST_MANDANT");
	}

	public String get_OM_ZIEL_IST_MANDANT_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_IST_MANDANT");	
	}

	public String get_OM_ZIEL_IST_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_IST_MANDANT");
	}

	public String get_OM_ZIEL_IST_MANDANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_IST_MANDANT",cNotNullValue);
	}

	public String get_OM_ZIEL_IST_MANDANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_IST_MANDANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_IST_MANDANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_IST_MANDANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_IST_MANDANT", calNewValueFormated);
	}
		public boolean is_OM_ZIEL_IST_MANDANT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_IST_MANDANT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_ZIEL_IST_MANDANT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_IST_MANDANT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_ZIEL_LAND_GEO_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_LAND_GEO");
	}

	public String get_OM_ZIEL_LAND_GEO_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_LAND_GEO");	
	}

	public String get_OM_ZIEL_LAND_GEO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_LAND_GEO");
	}

	public String get_OM_ZIEL_LAND_GEO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_LAND_GEO",cNotNullValue);
	}

	public String get_OM_ZIEL_LAND_GEO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_LAND_GEO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_LAND_GEO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_LAND_GEO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_GEO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_GEO", calNewValueFormated);
	}
		public String get_OM_ZIEL_LAND_JUR_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_LAND_JUR");
	}

	public String get_OM_ZIEL_LAND_JUR_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_LAND_JUR");	
	}

	public String get_OM_ZIEL_LAND_JUR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_LAND_JUR");
	}

	public String get_OM_ZIEL_LAND_JUR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_LAND_JUR",cNotNullValue);
	}

	public String get_OM_ZIEL_LAND_JUR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_LAND_JUR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_LAND_JUR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_LAND_JUR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_LAND_JUR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_LAND_JUR", calNewValueFormated);
	}
		public String get_OM_ZIEL_PRODUKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_PRODUKT");
	}

	public String get_OM_ZIEL_PRODUKT_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_PRODUKT");	
	}

	public String get_OM_ZIEL_PRODUKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_PRODUKT");
	}

	public String get_OM_ZIEL_PRODUKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_PRODUKT",cNotNullValue);
	}

	public String get_OM_ZIEL_PRODUKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_PRODUKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_PRODUKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_PRODUKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_PRODUKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_PRODUKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_PRODUKT", calNewValueFormated);
	}
		public boolean is_OM_ZIEL_PRODUKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_PRODUKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_ZIEL_PRODUKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_PRODUKT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_ZIEL_RC_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_RC");
	}

	public String get_OM_ZIEL_RC_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_RC");	
	}

	public String get_OM_ZIEL_RC_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_RC");
	}

	public String get_OM_ZIEL_RC_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_RC",cNotNullValue);
	}

	public String get_OM_ZIEL_RC_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_RC",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_RC", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_RC(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_RC", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_RC_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_RC", calNewValueFormated);
	}
		public boolean is_OM_ZIEL_RC_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_RC_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_ZIEL_RC_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_RC_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OM_ZIEL_UNTERNEHMEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_UNTERNEHMEN");
	}

	public String get_OM_ZIEL_UNTERNEHMEN_cF() throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_UNTERNEHMEN");	
	}

	public String get_OM_ZIEL_UNTERNEHMEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OM_ZIEL_UNTERNEHMEN");
	}

	public String get_OM_ZIEL_UNTERNEHMEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OM_ZIEL_UNTERNEHMEN",cNotNullValue);
	}

	public String get_OM_ZIEL_UNTERNEHMEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OM_ZIEL_UNTERNEHMEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OM_ZIEL_UNTERNEHMEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OM_ZIEL_UNTERNEHMEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OM_ZIEL_UNTERNEHMEN", calNewValueFormated);
	}
		public boolean is_OM_ZIEL_UNTERNEHMEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_UNTERNEHMEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OM_ZIEL_UNTERNEHMEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OM_ZIEL_UNTERNEHMEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STEUERSATZ_START_ALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_START_ALT");
	}

	public String get_STEUERSATZ_START_ALT_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_START_ALT");	
	}

	public String get_STEUERSATZ_START_ALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ_START_ALT");
	}

	public String get_STEUERSATZ_START_ALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_START_ALT",cNotNullValue);
	}

	public String get_STEUERSATZ_START_ALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_START_ALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ_START_ALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_START_ALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ_START_ALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_ALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_START_ALT", calNewValueFormated);
	}
		public Double get_STEUERSATZ_START_ALT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_START_ALT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_START_ALT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_START_ALT").getDoubleValue();
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
	public String get_STEUERSATZ_START_ALT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_START_ALT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_STEUERSATZ_START_ALT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_START_ALT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_STEUERSATZ_START_ALT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_START_ALT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_START_ALT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_START_ALT").getBigDecimalValue();
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
	
	
	public String get_STEUERSATZ_START_HD_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_START_HD");
	}

	public String get_STEUERSATZ_START_HD_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_START_HD");	
	}

	public String get_STEUERSATZ_START_HD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ_START_HD");
	}

	public String get_STEUERSATZ_START_HD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_START_HD",cNotNullValue);
	}

	public String get_STEUERSATZ_START_HD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_START_HD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ_START_HD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_START_HD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ_START_HD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_START_HD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_START_HD", calNewValueFormated);
	}
		public Double get_STEUERSATZ_START_HD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_START_HD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_START_HD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_START_HD").getDoubleValue();
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
	public String get_STEUERSATZ_START_HD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_START_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_STEUERSATZ_START_HD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_START_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_STEUERSATZ_START_HD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_START_HD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_START_HD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_START_HD").getBigDecimalValue();
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
	
	
	public String get_STEUERSATZ_ZIEL_ALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_ZIEL_ALT");
	}

	public String get_STEUERSATZ_ZIEL_ALT_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_ZIEL_ALT");	
	}

	public String get_STEUERSATZ_ZIEL_ALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ_ZIEL_ALT");
	}

	public String get_STEUERSATZ_ZIEL_ALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_ZIEL_ALT",cNotNullValue);
	}

	public String get_STEUERSATZ_ZIEL_ALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_ZIEL_ALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_ALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_ALT", calNewValueFormated);
	}
		public Double get_STEUERSATZ_ZIEL_ALT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_ZIEL_ALT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_ZIEL_ALT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_ZIEL_ALT").getDoubleValue();
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
	public String get_STEUERSATZ_ZIEL_ALT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_ZIEL_ALT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_STEUERSATZ_ZIEL_ALT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_ZIEL_ALT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_STEUERSATZ_ZIEL_ALT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_ZIEL_ALT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_ZIEL_ALT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_ZIEL_ALT").getBigDecimalValue();
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
	
	
	public String get_STEUERSATZ_ZIEL_HD_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_ZIEL_HD");
	}

	public String get_STEUERSATZ_ZIEL_HD_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_ZIEL_HD");	
	}

	public String get_STEUERSATZ_ZIEL_HD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ_ZIEL_HD");
	}

	public String get_STEUERSATZ_ZIEL_HD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_ZIEL_HD",cNotNullValue);
	}

	public String get_STEUERSATZ_ZIEL_HD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_ZIEL_HD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_ZIEL_HD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ_ZIEL_HD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_ZIEL_HD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_ZIEL_HD", calNewValueFormated);
	}
		public Double get_STEUERSATZ_ZIEL_HD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_ZIEL_HD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_ZIEL_HD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_ZIEL_HD").getDoubleValue();
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
	public String get_STEUERSATZ_ZIEL_HD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_ZIEL_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_STEUERSATZ_ZIEL_HD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_ZIEL_HD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_STEUERSATZ_ZIEL_HD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_ZIEL_HD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_ZIEL_HD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_ZIEL_HD").getBigDecimalValue();
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
	
	
	public String get_STEUERVERMERK_START_ALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_START_ALT");
	}

	public String get_STEUERVERMERK_START_ALT_cF() throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_START_ALT");	
	}

	public String get_STEUERVERMERK_START_ALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERVERMERK_START_ALT");
	}

	public String get_STEUERVERMERK_START_ALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_START_ALT",cNotNullValue);
	}

	public String get_STEUERVERMERK_START_ALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_START_ALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERVERMERK_START_ALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_START_ALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERVERMERK_START_ALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_ALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_ALT", calNewValueFormated);
	}
		public String get_STEUERVERMERK_START_HD_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_START_HD");
	}

	public String get_STEUERVERMERK_START_HD_cF() throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_START_HD");	
	}

	public String get_STEUERVERMERK_START_HD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERVERMERK_START_HD");
	}

	public String get_STEUERVERMERK_START_HD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_START_HD",cNotNullValue);
	}

	public String get_STEUERVERMERK_START_HD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_START_HD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERVERMERK_START_HD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_START_HD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERVERMERK_START_HD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_START_HD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_START_HD", calNewValueFormated);
	}
		public String get_STEUERVERMERK_ZIEL_ALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_ZIEL_ALT");
	}

	public String get_STEUERVERMERK_ZIEL_ALT_cF() throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_ZIEL_ALT");	
	}

	public String get_STEUERVERMERK_ZIEL_ALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERVERMERK_ZIEL_ALT");
	}

	public String get_STEUERVERMERK_ZIEL_ALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_ZIEL_ALT",cNotNullValue);
	}

	public String get_STEUERVERMERK_ZIEL_ALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_ZIEL_ALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_ZIEL_ALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_ALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_ALT", calNewValueFormated);
	}
		public String get_STEUERVERMERK_ZIEL_HD_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_ZIEL_HD");
	}

	public String get_STEUERVERMERK_ZIEL_HD_cF() throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_ZIEL_HD");	
	}

	public String get_STEUERVERMERK_ZIEL_HD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERVERMERK_ZIEL_HD");
	}

	public String get_STEUERVERMERK_ZIEL_HD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK_ZIEL_HD",cNotNullValue);
	}

	public String get_STEUERVERMERK_ZIEL_HD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK_ZIEL_HD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK_ZIEL_HD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_ZIEL_HD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK_ZIEL_HD", calNewValueFormated);
	}
		public String get_SUCHERGEBNIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("SUCHERGEBNIS");
	}

	public String get_SUCHERGEBNIS_cF() throws myException
	{
		return this.get_FormatedValue("SUCHERGEBNIS");	
	}

	public String get_SUCHERGEBNIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SUCHERGEBNIS");
	}

	public String get_SUCHERGEBNIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SUCHERGEBNIS",cNotNullValue);
	}

	public String get_SUCHERGEBNIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SUCHERGEBNIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SUCHERGEBNIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SUCHERGEBNIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SUCHERGEBNIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SUCHERGEBNIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SUCHERGEBNIS", calNewValueFormated);
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
	put("EPREIS_QUELLE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EPREIS_ZIEL",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_HANDELSDEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_START_HD",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_ZIEL_HD",MyRECORD.DATATYPES.NUMBER);
	put("ID_TESTTABELLE_HANDELDEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ORT",MyRECORD.DATATYPES.NUMBER);
	put("INTRASTAT_HD",MyRECORD.DATATYPES.YESNO);
	put("LAENDERCODE_START",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_ZIEL",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("OM_ID_LAND_QUELLE_GEO",MyRECORD.DATATYPES.NUMBER);
	put("OM_ID_LAND_QUELLE_JUR",MyRECORD.DATATYPES.NUMBER);
	put("OM_ID_LAND_ZIEL_GEO",MyRECORD.DATATYPES.NUMBER);
	put("OM_ID_LAND_ZIEL_JUR",MyRECORD.DATATYPES.NUMBER);
	put("OM_START_DIENSTLST",MyRECORD.DATATYPES.YESNO);
	put("OM_START_EOW",MyRECORD.DATATYPES.YESNO);
	put("OM_START_IST_MANDANT",MyRECORD.DATATYPES.YESNO);
	put("OM_START_LAND_GEO",MyRECORD.DATATYPES.TEXT);
	put("OM_START_LAND_JUR",MyRECORD.DATATYPES.TEXT);
	put("OM_START_PRODUKT",MyRECORD.DATATYPES.YESNO);
	put("OM_START_RC",MyRECORD.DATATYPES.YESNO);
	put("OM_START_UNTERNEHMEN",MyRECORD.DATATYPES.YESNO);
	put("OM_TPA_VERANTWORT",MyRECORD.DATATYPES.TEXT);
	put("OM_ZIEL_DIENSTLST",MyRECORD.DATATYPES.YESNO);
	put("OM_ZIEL_EOW",MyRECORD.DATATYPES.YESNO);
	put("OM_ZIEL_IST_MANDANT",MyRECORD.DATATYPES.YESNO);
	put("OM_ZIEL_LAND_GEO",MyRECORD.DATATYPES.TEXT);
	put("OM_ZIEL_LAND_JUR",MyRECORD.DATATYPES.TEXT);
	put("OM_ZIEL_PRODUKT",MyRECORD.DATATYPES.YESNO);
	put("OM_ZIEL_RC",MyRECORD.DATATYPES.YESNO);
	put("OM_ZIEL_UNTERNEHMEN",MyRECORD.DATATYPES.YESNO);
	put("STEUERSATZ_START_ALT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERSATZ_START_HD",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERSATZ_ZIEL_ALT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERSATZ_ZIEL_HD",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERVERMERK_START_ALT",MyRECORD.DATATYPES.TEXT);
	put("STEUERVERMERK_START_HD",MyRECORD.DATATYPES.TEXT);
	put("STEUERVERMERK_ZIEL_ALT",MyRECORD.DATATYPES.TEXT);
	put("STEUERVERMERK_ZIEL_HD",MyRECORD.DATATYPES.TEXT);
	put("SUCHERGEBNIS",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_TESTTABELLE_HANDELDEF.HM_FIELDS;	
	}

}
