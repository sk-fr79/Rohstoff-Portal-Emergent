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

public class RECORD_INTRASTAT_MELDUNG extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_INTRASTAT_MELDUNG";
    public static String IDFIELD   = "ID_INTRASTAT_MELDUNG";
    

	//erzeugen eines RECORDNEW_JT_INTRASTAT_MELDUNG - felds
	private RECORDNEW_INTRASTAT_MELDUNG   recNEW = null;

    private _TAB  tab = _TAB.intrastat_meldung;  



	public RECORD_INTRASTAT_MELDUNG() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");
	}


	public RECORD_INTRASTAT_MELDUNG(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");
	}



	public RECORD_INTRASTAT_MELDUNG(RECORD_INTRASTAT_MELDUNG recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_INTRASTAT_MELDUNG.TABLENAME);
	}


	//2014-04-03
	public RECORD_INTRASTAT_MELDUNG(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");
		this.set_Tablename_To_FieldMetaDefs(RECORD_INTRASTAT_MELDUNG.TABLENAME);
	}




	public RECORD_INTRASTAT_MELDUNG(long lID_Unformated) throws myException
	{
		super("JT_INTRASTAT_MELDUNG","ID_INTRASTAT_MELDUNG",""+lID_Unformated);
		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_INTRASTAT_MELDUNG.TABLENAME);
	}

	public RECORD_INTRASTAT_MELDUNG(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_INTRASTAT_MELDUNG", "ID_INTRASTAT_MELDUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_INTRASTAT_MELDUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_INTRASTAT_MELDUNG.TABLENAME);
	}
	
	

	public RECORD_INTRASTAT_MELDUNG(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_INTRASTAT_MELDUNG","ID_INTRASTAT_MELDUNG",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_INTRASTAT_MELDUNG.TABLENAME);

	}


	public RECORD_INTRASTAT_MELDUNG(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_INTRASTAT_MELDUNG");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_INTRASTAT_MELDUNG", "ID_INTRASTAT_MELDUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_INTRASTAT_MELDUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_INTRASTAT_MELDUNG.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_INTRASTAT_MELDUNG();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_INTRASTAT_MELDUNG.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_INTRASTAT_MELDUNG";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_INTRASTAT_MELDUNG_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_INTRASTAT_MELDUNG_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_INTRASTAT_MELDUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_INTRASTAT_MELDUNG", bibE2.cTO(), "ID_INTRASTAT_MELDUNG="+this.get_ID_INTRASTAT_MELDUNG_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_INTRASTAT_MELDUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_INTRASTAT_MELDUNG", bibE2.cTO(), "ID_INTRASTAT_MELDUNG="+this.get_ID_INTRASTAT_MELDUNG_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG WHERE ID_INTRASTAT_MELDUNG="+this.get_ID_INTRASTAT_MELDUNG_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG WHERE ID_INTRASTAT_MELDUNG="+this.get_ID_INTRASTAT_MELDUNG_cUF();
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
			if (S.isFull(this.get_ID_INTRASTAT_MELDUNG_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_INTRASTAT_MELDUNG", "ID_INTRASTAT_MELDUNG="+this.get_ID_INTRASTAT_MELDUNG_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_INTRASTAT_MELDUNG get_RECORDNEW_INTRASTAT_MELDUNG() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_INTRASTAT_MELDUNG();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_INTRASTAT_MELDUNG(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_INTRASTAT_MELDUNG create_Instance() throws myException {
		return new RECORD_INTRASTAT_MELDUNG();
	}
	
	public static RECORD_INTRASTAT_MELDUNG create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_INTRASTAT_MELDUNG(Conn);
    }

	public static RECORD_INTRASTAT_MELDUNG create_Instance(RECORD_INTRASTAT_MELDUNG recordOrig) {
		return new RECORD_INTRASTAT_MELDUNG(recordOrig);
    }

	public static RECORD_INTRASTAT_MELDUNG create_Instance(long lID_Unformated) throws myException {
		return new RECORD_INTRASTAT_MELDUNG(lID_Unformated);
    }

	public static RECORD_INTRASTAT_MELDUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_INTRASTAT_MELDUNG(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_INTRASTAT_MELDUNG create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_INTRASTAT_MELDUNG(lID_Unformated, Conn);
	}

	public static RECORD_INTRASTAT_MELDUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_INTRASTAT_MELDUNG(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_INTRASTAT_MELDUNG create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_INTRASTAT_MELDUNG(recordOrig);	    
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
    public RECORD_INTRASTAT_MELDUNG build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG WHERE ID_INTRASTAT_MELDUNG="+this.get_ID_INTRASTAT_MELDUNG_cUF());
      }
      //return new RECORD_INTRASTAT_MELDUNG(this.get_cSQL_4_Build());
      RECORD_INTRASTAT_MELDUNG rec = new RECORD_INTRASTAT_MELDUNG();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_INTRASTAT_MELDUNG
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_INTRASTAT_MELDUNG-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_INTRASTAT_MELDUNG get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_INTRASTAT_MELDUNG_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_INTRASTAT_MELDUNG  recNew = new RECORDNEW_INTRASTAT_MELDUNG();
		
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
    public RECORD_INTRASTAT_MELDUNG set_recordNew(RECORDNEW_INTRASTAT_MELDUNG recnew) throws myException {
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
	
	

	public static String FIELD__ANMELDEFORM = "ANMELDEFORM";
	public static String FIELD__ANMELDEJAHR = "ANMELDEJAHR";
	public static String FIELD__ANMELDEMONAT = "ANMELDEMONAT";
	public static String FIELD__BESTIMM_LAND = "BESTIMM_LAND";
	public static String FIELD__BESTIMM_REGION = "BESTIMM_REGION";
	public static String FIELD__BEZUGSJAHR = "BEZUGSJAHR";
	public static String FIELD__BEZUGSMONAT = "BEZUGSMONAT";
	public static String FIELD__BUNDESLAND_FA = "BUNDESLAND_FA";
	public static String FIELD__EIGENMASSE = "EIGENMASSE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EXPORTFREIGABE = "EXPORTFREIGABE";
	public static String FIELD__EXPORTIERT_AM = "EXPORTIERT_AM";
	public static String FIELD__EXPORT_NO_PRICE = "EXPORT_NO_PRICE";
	public static String FIELD__FRACHTKOSTEN = "FRACHTKOSTEN";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GESCHAEFTSART = "GESCHAEFTSART";
	public static String FIELD__ID_ARTIKEL = "ID_ARTIKEL";
	public static String FIELD__ID_INTRASTAT_MELDUNG = "ID_INTRASTAT_MELDUNG";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ORT = "ID_VPOS_TPA_FUHRE_ORT";
	public static String FIELD__LAND_URSPRUNG = "LAND_URSPRUNG";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MASSEINHEIT = "MASSEINHEIT";
	public static String FIELD__MELDEART = "MELDEART";
	public static String FIELD__NICHT_MELDEN = "NICHT_MELDEN";
	public static String FIELD__PAGINIERNUMMER = "PAGINIERNUMMER";
	public static String FIELD__PREISTYP = "PREISTYP";
	public static String FIELD__RECHBETRAG = "RECHBETRAG";
	public static String FIELD__STATISTISCHER_WERT = "STATISTISCHER_WERT";
	public static String FIELD__STEUERNR = "STEUERNR";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__UMSATZSTEUERID = "UMSATZSTEUERID";
	public static String FIELD__UMSATZSTEUER_LKZ = "UMSATZSTEUER_LKZ";
	public static String FIELD__UNTERSCHEIDUNGSNR = "UNTERSCHEIDUNGSNR";
	public static String FIELD__VERKEHRSZWEIG = "VERKEHRSZWEIG";
	public static String FIELD__WAEHRUNGSKENNZIFFER = "WAEHRUNGSKENNZIFFER";
	public static String FIELD__WARENNR = "WARENNR";
	public static String FIELD__ZAEHLER_MELDUNG = "ZAEHLER_MELDUNG";


	public String get_ANMELDEFORM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANMELDEFORM");
	}

	public String get_ANMELDEFORM_cF() throws myException
	{
		return this.get_FormatedValue("ANMELDEFORM");	
	}

	public String get_ANMELDEFORM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANMELDEFORM");
	}

	public String get_ANMELDEFORM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANMELDEFORM",cNotNullValue);
	}

	public String get_ANMELDEFORM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANMELDEFORM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANMELDEFORM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANMELDEFORM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANMELDEFORM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANMELDEFORM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEFORM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEFORM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEFORM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEFORM", calNewValueFormated);
	}
		public boolean is_ANMELDEFORM_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ANMELDEFORM_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ANMELDEFORM_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ANMELDEFORM_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ANMELDEJAHR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANMELDEJAHR");
	}

	public String get_ANMELDEJAHR_cF() throws myException
	{
		return this.get_FormatedValue("ANMELDEJAHR");	
	}

	public String get_ANMELDEJAHR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANMELDEJAHR");
	}

	public String get_ANMELDEJAHR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANMELDEJAHR",cNotNullValue);
	}

	public String get_ANMELDEJAHR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANMELDEJAHR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANMELDEJAHR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANMELDEJAHR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANMELDEJAHR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANMELDEJAHR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEJAHR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEJAHR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEJAHR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEJAHR", calNewValueFormated);
	}
		public String get_ANMELDEMONAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANMELDEMONAT");
	}

	public String get_ANMELDEMONAT_cF() throws myException
	{
		return this.get_FormatedValue("ANMELDEMONAT");	
	}

	public String get_ANMELDEMONAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANMELDEMONAT");
	}

	public String get_ANMELDEMONAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANMELDEMONAT",cNotNullValue);
	}

	public String get_ANMELDEMONAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANMELDEMONAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANMELDEMONAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANMELDEMONAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANMELDEMONAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANMELDEMONAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEMONAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEMONAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANMELDEMONAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANMELDEMONAT", calNewValueFormated);
	}
		public String get_BESTIMM_LAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESTIMM_LAND");
	}

	public String get_BESTIMM_LAND_cF() throws myException
	{
		return this.get_FormatedValue("BESTIMM_LAND");	
	}

	public String get_BESTIMM_LAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESTIMM_LAND");
	}

	public String get_BESTIMM_LAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESTIMM_LAND",cNotNullValue);
	}

	public String get_BESTIMM_LAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESTIMM_LAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESTIMM_LAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESTIMM_LAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESTIMM_LAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESTIMM_LAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTIMM_LAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTIMM_LAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_LAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTIMM_LAND", calNewValueFormated);
	}
		public String get_BESTIMM_REGION_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESTIMM_REGION");
	}

	public String get_BESTIMM_REGION_cF() throws myException
	{
		return this.get_FormatedValue("BESTIMM_REGION");	
	}

	public String get_BESTIMM_REGION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESTIMM_REGION");
	}

	public String get_BESTIMM_REGION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESTIMM_REGION",cNotNullValue);
	}

	public String get_BESTIMM_REGION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESTIMM_REGION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESTIMM_REGION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESTIMM_REGION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESTIMM_REGION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESTIMM_REGION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTIMM_REGION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTIMM_REGION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTIMM_REGION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTIMM_REGION", calNewValueFormated);
	}
		public String get_BEZUGSJAHR_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZUGSJAHR");
	}

	public String get_BEZUGSJAHR_cF() throws myException
	{
		return this.get_FormatedValue("BEZUGSJAHR");	
	}

	public String get_BEZUGSJAHR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZUGSJAHR");
	}

	public String get_BEZUGSJAHR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZUGSJAHR",cNotNullValue);
	}

	public String get_BEZUGSJAHR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZUGSJAHR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZUGSJAHR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZUGSJAHR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZUGSJAHR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZUGSJAHR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUGSJAHR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUGSJAHR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSJAHR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUGSJAHR", calNewValueFormated);
	}
		public String get_BEZUGSMONAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZUGSMONAT");
	}

	public String get_BEZUGSMONAT_cF() throws myException
	{
		return this.get_FormatedValue("BEZUGSMONAT");	
	}

	public String get_BEZUGSMONAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZUGSMONAT");
	}

	public String get_BEZUGSMONAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZUGSMONAT",cNotNullValue);
	}

	public String get_BEZUGSMONAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZUGSMONAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZUGSMONAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZUGSMONAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZUGSMONAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZUGSMONAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUGSMONAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUGSMONAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUGSMONAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUGSMONAT", calNewValueFormated);
	}
		public String get_BUNDESLAND_FA_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUNDESLAND_FA");
	}

	public String get_BUNDESLAND_FA_cF() throws myException
	{
		return this.get_FormatedValue("BUNDESLAND_FA");	
	}

	public String get_BUNDESLAND_FA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUNDESLAND_FA");
	}

	public String get_BUNDESLAND_FA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUNDESLAND_FA",cNotNullValue);
	}

	public String get_BUNDESLAND_FA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUNDESLAND_FA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUNDESLAND_FA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUNDESLAND_FA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUNDESLAND_FA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUNDESLAND_FA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUNDESLAND_FA", calNewValueFormated);
	}
		public String get_EIGENMASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EIGENMASSE");
	}

	public String get_EIGENMASSE_cF() throws myException
	{
		return this.get_FormatedValue("EIGENMASSE");	
	}

	public String get_EIGENMASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENMASSE");
	}

	public String get_EIGENMASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EIGENMASSE",cNotNullValue);
	}

	public String get_EIGENMASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EIGENMASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EIGENMASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EIGENMASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EIGENMASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EIGENMASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENMASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENMASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENMASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENMASSE", calNewValueFormated);
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
		public String get_EXPORTFREIGABE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EXPORTFREIGABE");
	}

	public String get_EXPORTFREIGABE_cF() throws myException
	{
		return this.get_FormatedValue("EXPORTFREIGABE");	
	}

	public String get_EXPORTFREIGABE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EXPORTFREIGABE");
	}

	public String get_EXPORTFREIGABE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EXPORTFREIGABE",cNotNullValue);
	}

	public String get_EXPORTFREIGABE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EXPORTFREIGABE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EXPORTFREIGABE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EXPORTFREIGABE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EXPORTFREIGABE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTFREIGABE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTFREIGABE", calNewValueFormated);
	}
		public boolean is_EXPORTFREIGABE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_EXPORTFREIGABE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_EXPORTFREIGABE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_EXPORTFREIGABE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_EXPORTIERT_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("EXPORTIERT_AM");
	}

	public String get_EXPORTIERT_AM_cF() throws myException
	{
		return this.get_FormatedValue("EXPORTIERT_AM");	
	}

	public String get_EXPORTIERT_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EXPORTIERT_AM");
	}

	public String get_EXPORTIERT_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EXPORTIERT_AM",cNotNullValue);
	}

	public String get_EXPORTIERT_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EXPORTIERT_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EXPORTIERT_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EXPORTIERT_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EXPORTIERT_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORTIERT_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORTIERT_AM", calNewValueFormated);
	}
		public String get_EXPORT_NO_PRICE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EXPORT_NO_PRICE");
	}

	public String get_EXPORT_NO_PRICE_cF() throws myException
	{
		return this.get_FormatedValue("EXPORT_NO_PRICE");	
	}

	public String get_EXPORT_NO_PRICE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EXPORT_NO_PRICE");
	}

	public String get_EXPORT_NO_PRICE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EXPORT_NO_PRICE",cNotNullValue);
	}

	public String get_EXPORT_NO_PRICE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EXPORT_NO_PRICE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EXPORT_NO_PRICE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EXPORT_NO_PRICE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EXPORT_NO_PRICE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EXPORT_NO_PRICE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EXPORT_NO_PRICE", calNewValueFormated);
	}
		public boolean is_EXPORT_NO_PRICE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_EXPORT_NO_PRICE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_EXPORT_NO_PRICE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_EXPORT_NO_PRICE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_FRACHTKOSTEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FRACHTKOSTEN");
	}

	public String get_FRACHTKOSTEN_cF() throws myException
	{
		return this.get_FormatedValue("FRACHTKOSTEN");	
	}

	public String get_FRACHTKOSTEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FRACHTKOSTEN");
	}

	public String get_FRACHTKOSTEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FRACHTKOSTEN",cNotNullValue);
	}

	public String get_FRACHTKOSTEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FRACHTKOSTEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FRACHTKOSTEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FRACHTKOSTEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FRACHTKOSTEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRACHTKOSTEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FRACHTKOSTEN", calNewValueFormated);
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
		public String get_GESCHAEFTSART_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESCHAEFTSART");
	}

	public String get_GESCHAEFTSART_cF() throws myException
	{
		return this.get_FormatedValue("GESCHAEFTSART");	
	}

	public String get_GESCHAEFTSART_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHAEFTSART");
	}

	public String get_GESCHAEFTSART_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESCHAEFTSART",cNotNullValue);
	}

	public String get_GESCHAEFTSART_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESCHAEFTSART",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESCHAEFTSART", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESCHAEFTSART(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESCHAEFTSART", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESCHAEFTSART", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSART", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSART", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSART_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSART", calNewValueFormated);
	}
		public String get_ID_ARTIKEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL");
	}

	public String get_ID_ARTIKEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL");	
	}

	public String get_ID_ARTIKEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL");
	}

	public String get_ID_ARTIKEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL",cNotNullValue);
	}

	public String get_ID_ARTIKEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL").getDoubleValue();
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
	public String get_ID_ARTIKEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL").getBigDecimalValue();
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
	
	
	public String get_ID_INTRASTAT_MELDUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_INTRASTAT_MELDUNG");
	}

	public String get_ID_INTRASTAT_MELDUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_INTRASTAT_MELDUNG");	
	}

	public String get_ID_INTRASTAT_MELDUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_INTRASTAT_MELDUNG");
	}

	public String get_ID_INTRASTAT_MELDUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_INTRASTAT_MELDUNG",cNotNullValue);
	}

	public String get_ID_INTRASTAT_MELDUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_INTRASTAT_MELDUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_INTRASTAT_MELDUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_INTRASTAT_MELDUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_INTRASTAT_MELDUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_INTRASTAT_MELDUNG", calNewValueFormated);
	}
		public Long get_ID_INTRASTAT_MELDUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_INTRASTAT_MELDUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_INTRASTAT_MELDUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_INTRASTAT_MELDUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_INTRASTAT_MELDUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_INTRASTAT_MELDUNG").getDoubleValue();
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
	public String get_ID_INTRASTAT_MELDUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_INTRASTAT_MELDUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_INTRASTAT_MELDUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_INTRASTAT_MELDUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_INTRASTAT_MELDUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_INTRASTAT_MELDUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_INTRASTAT_MELDUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_INTRASTAT_MELDUNG").getBigDecimalValue();
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
	
	
	public String get_LAND_URSPRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAND_URSPRUNG");
	}

	public String get_LAND_URSPRUNG_cF() throws myException
	{
		return this.get_FormatedValue("LAND_URSPRUNG");	
	}

	public String get_LAND_URSPRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAND_URSPRUNG");
	}

	public String get_LAND_URSPRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAND_URSPRUNG",cNotNullValue);
	}

	public String get_LAND_URSPRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAND_URSPRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAND_URSPRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAND_URSPRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAND_URSPRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAND_URSPRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAND_URSPRUNG", calNewValueFormated);
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
		public String get_MASSEINHEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MASSEINHEIT");
	}

	public String get_MASSEINHEIT_cF() throws myException
	{
		return this.get_FormatedValue("MASSEINHEIT");	
	}

	public String get_MASSEINHEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MASSEINHEIT");
	}

	public String get_MASSEINHEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MASSEINHEIT",cNotNullValue);
	}

	public String get_MASSEINHEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MASSEINHEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MASSEINHEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MASSEINHEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MASSEINHEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MASSEINHEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MASSEINHEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MASSEINHEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASSEINHEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MASSEINHEIT", calNewValueFormated);
	}
		public String get_MELDEART_cUF() throws myException
	{
		return this.get_UnFormatedValue("MELDEART");
	}

	public String get_MELDEART_cF() throws myException
	{
		return this.get_FormatedValue("MELDEART");	
	}

	public String get_MELDEART_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MELDEART");
	}

	public String get_MELDEART_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MELDEART",cNotNullValue);
	}

	public String get_MELDEART_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MELDEART",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDEART(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MELDEART", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MELDEART(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MELDEART", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDEART_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MELDEART", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDEART_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDEART", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDEART_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDEART", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDEART_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDEART", calNewValueFormated);
	}
		public boolean is_MELDEART_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MELDEART_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MELDEART_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MELDEART_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_NICHT_MELDEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("NICHT_MELDEN");
	}

	public String get_NICHT_MELDEN_cF() throws myException
	{
		return this.get_FormatedValue("NICHT_MELDEN");	
	}

	public String get_NICHT_MELDEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NICHT_MELDEN");
	}

	public String get_NICHT_MELDEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NICHT_MELDEN",cNotNullValue);
	}

	public String get_NICHT_MELDEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NICHT_MELDEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NICHT_MELDEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NICHT_MELDEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NICHT_MELDEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NICHT_MELDEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NICHT_MELDEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NICHT_MELDEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NICHT_MELDEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NICHT_MELDEN", calNewValueFormated);
	}
		public boolean is_NICHT_MELDEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_NICHT_MELDEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_NICHT_MELDEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_NICHT_MELDEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PAGINIERNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("PAGINIERNUMMER");
	}

	public String get_PAGINIERNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("PAGINIERNUMMER");	
	}

	public String get_PAGINIERNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PAGINIERNUMMER");
	}

	public String get_PAGINIERNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PAGINIERNUMMER",cNotNullValue);
	}

	public String get_PAGINIERNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PAGINIERNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PAGINIERNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PAGINIERNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PAGINIERNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PAGINIERNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PAGINIERNUMMER", calNewValueFormated);
	}
		public String get_PREISTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREISTYP");
	}

	public String get_PREISTYP_cF() throws myException
	{
		return this.get_FormatedValue("PREISTYP");	
	}

	public String get_PREISTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREISTYP");
	}

	public String get_PREISTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREISTYP",cNotNullValue);
	}

	public String get_PREISTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREISTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREISTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREISTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREISTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREISTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISTYP", calNewValueFormated);
	}
		public String get_RECHBETRAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECHBETRAG");
	}

	public String get_RECHBETRAG_cF() throws myException
	{
		return this.get_FormatedValue("RECHBETRAG");	
	}

	public String get_RECHBETRAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECHBETRAG");
	}

	public String get_RECHBETRAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECHBETRAG",cNotNullValue);
	}

	public String get_RECHBETRAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECHBETRAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECHBETRAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECHBETRAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECHBETRAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECHBETRAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHBETRAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHBETRAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHBETRAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHBETRAG", calNewValueFormated);
	}
		public String get_STATISTISCHER_WERT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATISTISCHER_WERT");
	}

	public String get_STATISTISCHER_WERT_cF() throws myException
	{
		return this.get_FormatedValue("STATISTISCHER_WERT");	
	}

	public String get_STATISTISCHER_WERT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATISTISCHER_WERT");
	}

	public String get_STATISTISCHER_WERT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATISTISCHER_WERT",cNotNullValue);
	}

	public String get_STATISTISCHER_WERT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATISTISCHER_WERT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATISTISCHER_WERT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATISTISCHER_WERT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATISTISCHER_WERT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTISCHER_WERT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATISTISCHER_WERT", calNewValueFormated);
	}
		public String get_STEUERNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERNR");
	}

	public String get_STEUERNR_cF() throws myException
	{
		return this.get_FormatedValue("STEUERNR");	
	}

	public String get_STEUERNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERNR");
	}

	public String get_STEUERNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERNR",cNotNullValue);
	}

	public String get_STEUERNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNR", calNewValueFormated);
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
	
	
	public String get_UMSATZSTEUERID_cUF() throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERID");
	}

	public String get_UMSATZSTEUERID_cF() throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERID");	
	}

	public String get_UMSATZSTEUERID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UMSATZSTEUERID");
	}

	public String get_UMSATZSTEUERID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERID",cNotNullValue);
	}

	public String get_UMSATZSTEUERID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UMSATZSTEUERID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UMSATZSTEUERID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", calNewValueFormated);
	}
		public String get_UMSATZSTEUER_LKZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUER_LKZ");
	}

	public String get_UMSATZSTEUER_LKZ_cF() throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUER_LKZ");	
	}

	public String get_UMSATZSTEUER_LKZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UMSATZSTEUER_LKZ");
	}

	public String get_UMSATZSTEUER_LKZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUER_LKZ",cNotNullValue);
	}

	public String get_UMSATZSTEUER_LKZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUER_LKZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UMSATZSTEUER_LKZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUER_LKZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UMSATZSTEUER_LKZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUER_LKZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUER_LKZ", calNewValueFormated);
	}
		public String get_UNTERSCHEIDUNGSNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("UNTERSCHEIDUNGSNR");
	}

	public String get_UNTERSCHEIDUNGSNR_cF() throws myException
	{
		return this.get_FormatedValue("UNTERSCHEIDUNGSNR");	
	}

	public String get_UNTERSCHEIDUNGSNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UNTERSCHEIDUNGSNR");
	}

	public String get_UNTERSCHEIDUNGSNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UNTERSCHEIDUNGSNR",cNotNullValue);
	}

	public String get_UNTERSCHEIDUNGSNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UNTERSCHEIDUNGSNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UNTERSCHEIDUNGSNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UNTERSCHEIDUNGSNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UNTERSCHEIDUNGSNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UNTERSCHEIDUNGSNR", calNewValueFormated);
	}
		public String get_VERKEHRSZWEIG_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERKEHRSZWEIG");
	}

	public String get_VERKEHRSZWEIG_cF() throws myException
	{
		return this.get_FormatedValue("VERKEHRSZWEIG");	
	}

	public String get_VERKEHRSZWEIG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERKEHRSZWEIG");
	}

	public String get_VERKEHRSZWEIG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERKEHRSZWEIG",cNotNullValue);
	}

	public String get_VERKEHRSZWEIG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERKEHRSZWEIG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERKEHRSZWEIG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERKEHRSZWEIG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERKEHRSZWEIG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERKEHRSZWEIG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERKEHRSZWEIG", calNewValueFormated);
	}
		public boolean is_VERKEHRSZWEIG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_VERKEHRSZWEIG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_VERKEHRSZWEIG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_VERKEHRSZWEIG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_WAEHRUNGSKENNZIFFER_cUF() throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNGSKENNZIFFER");
	}

	public String get_WAEHRUNGSKENNZIFFER_cF() throws myException
	{
		return this.get_FormatedValue("WAEHRUNGSKENNZIFFER");	
	}

	public String get_WAEHRUNGSKENNZIFFER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WAEHRUNGSKENNZIFFER");
	}

	public String get_WAEHRUNGSKENNZIFFER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNGSKENNZIFFER",cNotNullValue);
	}

	public String get_WAEHRUNGSKENNZIFFER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WAEHRUNGSKENNZIFFER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNGSKENNZIFFER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WAEHRUNGSKENNZIFFER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKENNZIFFER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSKENNZIFFER", calNewValueFormated);
	}
		public boolean is_WAEHRUNGSKENNZIFFER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_WAEHRUNGSKENNZIFFER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_WAEHRUNGSKENNZIFFER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_WAEHRUNGSKENNZIFFER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_WARENNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("WARENNR");
	}

	public String get_WARENNR_cF() throws myException
	{
		return this.get_FormatedValue("WARENNR");	
	}

	public String get_WARENNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WARENNR");
	}

	public String get_WARENNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WARENNR",cNotNullValue);
	}

	public String get_WARENNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WARENNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WARENNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WARENNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WARENNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WARENNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENNR", calNewValueFormated);
	}
		public String get_ZAEHLER_MELDUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAEHLER_MELDUNG");
	}

	public String get_ZAEHLER_MELDUNG_cF() throws myException
	{
		return this.get_FormatedValue("ZAEHLER_MELDUNG");	
	}

	public String get_ZAEHLER_MELDUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAEHLER_MELDUNG");
	}

	public String get_ZAEHLER_MELDUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAEHLER_MELDUNG",cNotNullValue);
	}

	public String get_ZAEHLER_MELDUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAEHLER_MELDUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAEHLER_MELDUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAEHLER_MELDUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAEHLER_MELDUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_MELDUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAEHLER_MELDUNG", calNewValueFormated);
	}
		public Long get_ZAEHLER_MELDUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ZAEHLER_MELDUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ZAEHLER_MELDUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAEHLER_MELDUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAEHLER_MELDUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAEHLER_MELDUNG").getDoubleValue();
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
	public String get_ZAEHLER_MELDUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAEHLER_MELDUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ZAEHLER_MELDUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAEHLER_MELDUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ZAEHLER_MELDUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAEHLER_MELDUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAEHLER_MELDUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAEHLER_MELDUNG").getBigDecimalValue();
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
	put("ANMELDEFORM",MyRECORD.DATATYPES.YESNO);
	put("ANMELDEJAHR",MyRECORD.DATATYPES.TEXT);
	put("ANMELDEMONAT",MyRECORD.DATATYPES.TEXT);
	put("BESTIMM_LAND",MyRECORD.DATATYPES.TEXT);
	put("BESTIMM_REGION",MyRECORD.DATATYPES.TEXT);
	put("BEZUGSJAHR",MyRECORD.DATATYPES.TEXT);
	put("BEZUGSMONAT",MyRECORD.DATATYPES.TEXT);
	put("BUNDESLAND_FA",MyRECORD.DATATYPES.TEXT);
	put("EIGENMASSE",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EXPORTFREIGABE",MyRECORD.DATATYPES.YESNO);
	put("EXPORTIERT_AM",MyRECORD.DATATYPES.DATE);
	put("EXPORT_NO_PRICE",MyRECORD.DATATYPES.YESNO);
	put("FRACHTKOSTEN",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GESCHAEFTSART",MyRECORD.DATATYPES.TEXT);
	put("ID_ARTIKEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_INTRASTAT_MELDUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ORT",MyRECORD.DATATYPES.NUMBER);
	put("LAND_URSPRUNG",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MASSEINHEIT",MyRECORD.DATATYPES.TEXT);
	put("MELDEART",MyRECORD.DATATYPES.YESNO);
	put("NICHT_MELDEN",MyRECORD.DATATYPES.YESNO);
	put("PAGINIERNUMMER",MyRECORD.DATATYPES.TEXT);
	put("PREISTYP",MyRECORD.DATATYPES.TEXT);
	put("RECHBETRAG",MyRECORD.DATATYPES.TEXT);
	put("STATISTISCHER_WERT",MyRECORD.DATATYPES.TEXT);
	put("STEUERNR",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("UMSATZSTEUERID",MyRECORD.DATATYPES.TEXT);
	put("UMSATZSTEUER_LKZ",MyRECORD.DATATYPES.TEXT);
	put("UNTERSCHEIDUNGSNR",MyRECORD.DATATYPES.TEXT);
	put("VERKEHRSZWEIG",MyRECORD.DATATYPES.YESNO);
	put("WAEHRUNGSKENNZIFFER",MyRECORD.DATATYPES.YESNO);
	put("WARENNR",MyRECORD.DATATYPES.TEXT);
	put("ZAEHLER_MELDUNG",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_INTRASTAT_MELDUNG.HM_FIELDS;	
	}

}
