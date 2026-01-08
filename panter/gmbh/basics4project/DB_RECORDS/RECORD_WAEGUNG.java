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

public class RECORD_WAEGUNG extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_WAEGUNG";
    public static String IDFIELD   = "ID_WAEGUNG";
    

	//erzeugen eines RECORDNEW_JT_WAEGUNG - felds
	private RECORDNEW_WAEGUNG   recNEW = null;

    private _TAB  tab = _TAB.waegung;  



	public RECORD_WAEGUNG() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_WAEGUNG");
	}


	public RECORD_WAEGUNG(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_WAEGUNG");
	}



	public RECORD_WAEGUNG(RECORD_WAEGUNG recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_WAEGUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEGUNG.TABLENAME);
	}


	//2014-04-03
	public RECORD_WAEGUNG(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_WAEGUNG");
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEGUNG.TABLENAME);
	}




	public RECORD_WAEGUNG(long lID_Unformated) throws myException
	{
		super("JT_WAEGUNG","ID_WAEGUNG",""+lID_Unformated);
		this.set_TABLE_NAME("JT_WAEGUNG");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEGUNG.TABLENAME);
	}

	public RECORD_WAEGUNG(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_WAEGUNG");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_WAEGUNG", "ID_WAEGUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_WAEGUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEGUNG.TABLENAME);
	}
	
	

	public RECORD_WAEGUNG(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_WAEGUNG","ID_WAEGUNG",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_WAEGUNG");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEGUNG.TABLENAME);

	}


	public RECORD_WAEGUNG(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_WAEGUNG");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_WAEGUNG", "ID_WAEGUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_WAEGUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEGUNG.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_WAEGUNG();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_WAEGUNG.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_WAEGUNG";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_WAEGUNG_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_WAEGUNG_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_WAEGUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_WAEGUNG", bibE2.cTO(), "ID_WAEGUNG="+this.get_ID_WAEGUNG_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_WAEGUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_WAEGUNG", bibE2.cTO(), "ID_WAEGUNG="+this.get_ID_WAEGUNG_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_WAEGUNG WHERE ID_WAEGUNG="+this.get_ID_WAEGUNG_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_WAEGUNG WHERE ID_WAEGUNG="+this.get_ID_WAEGUNG_cUF();
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
			if (S.isFull(this.get_ID_WAEGUNG_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_WAEGUNG", "ID_WAEGUNG="+this.get_ID_WAEGUNG_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_WAEGUNG get_RECORDNEW_WAEGUNG() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_WAEGUNG();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_WAEGUNG(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_WAEGUNG create_Instance() throws myException {
		return new RECORD_WAEGUNG();
	}
	
	public static RECORD_WAEGUNG create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_WAEGUNG(Conn);
    }

	public static RECORD_WAEGUNG create_Instance(RECORD_WAEGUNG recordOrig) {
		return new RECORD_WAEGUNG(recordOrig);
    }

	public static RECORD_WAEGUNG create_Instance(long lID_Unformated) throws myException {
		return new RECORD_WAEGUNG(lID_Unformated);
    }

	public static RECORD_WAEGUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_WAEGUNG(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_WAEGUNG create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_WAEGUNG(lID_Unformated, Conn);
	}

	public static RECORD_WAEGUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_WAEGUNG(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_WAEGUNG create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_WAEGUNG(recordOrig);	    
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
    public RECORD_WAEGUNG build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_WAEGUNG WHERE ID_WAEGUNG="+this.get_ID_WAEGUNG_cUF());
      }
      //return new RECORD_WAEGUNG(this.get_cSQL_4_Build());
      RECORD_WAEGUNG rec = new RECORD_WAEGUNG();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_WAEGUNG
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_WAEGUNG-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_WAEGUNG get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_WAEGUNG_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_WAEGUNG  recNew = new RECORDNEW_WAEGUNG();
		
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
    public RECORD_WAEGUNG set_recordNew(RECORDNEW_WAEGUNG recnew) throws myException {
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
	
	



		private RECORD_WIEGEKARTE UP_RECORD_WIEGEKARTE_id_wiegekarte = null;






	
	/**
	 * References the Field ID_WIEGEKARTE
	 * Falls keine this.get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WIEGEKARTE get_UP_RECORD_WIEGEKARTE_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WIEGEKARTE_id_wiegekarte==null)
		{
			this.UP_RECORD_WIEGEKARTE_id_wiegekarte = new RECORD_WIEGEKARTE(this.get_ID_WIEGEKARTE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WIEGEKARTE_id_wiegekarte;
	}
	

	public static String FIELD__DATUM = "DATUM";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEWICHT = "GEWICHT";
	public static String FIELD__HANDEINGABE = "HANDEINGABE";
	public static String FIELD__HANDEINGABE_BEM = "HANDEINGABE_BEM";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_USER_WAEGUNG = "ID_USER_WAEGUNG";
	public static String FIELD__ID_WAAGE_SETTINGS = "ID_WAAGE_SETTINGS";
	public static String FIELD__ID_WAEGUNG = "ID_WAEGUNG";
	public static String FIELD__ID_WIEGEKARTE = "ID_WIEGEKARTE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__STORNO = "STORNO";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__WAAGE_DS_ORI = "WAAGE_DS_ORI";
	public static String FIELD__WAEGUNG_POS = "WAEGUNG_POS";
	public static String FIELD__W_BRUTTO_GEWICHT = "W_BRUTTO_GEWICHT";
	public static String FIELD__W_DATUM = "W_DATUM";
	public static String FIELD__W_EINHEIT = "W_EINHEIT";
	public static String FIELD__W_FEHLERCODE = "W_FEHLERCODE";
	public static String FIELD__W_IDENT_NR = "W_IDENT_NR";
	public static String FIELD__W_NETTO_GEWICHT = "W_NETTO_GEWICHT";
	public static String FIELD__W_PRUEFZIFFER = "W_PRUEFZIFFER";
	public static String FIELD__W_STATUS = "W_STATUS";
	public static String FIELD__W_TARACODE = "W_TARACODE";
	public static String FIELD__W_TARAGEWICHT = "W_TARAGEWICHT";
	public static String FIELD__W_TERMINAL = "W_TERMINAL";
	public static String FIELD__W_WAAGEN_NR = "W_WAAGEN_NR";
	public static String FIELD__W_WAEGEBEREICH = "W_WAEGEBEREICH";
	public static String FIELD__W_ZEIT = "W_ZEIT";
	public static String FIELD__ZEIT = "ZEIT";


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
		public String get_GEWICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEWICHT");
	}

	public String get_GEWICHT_cF() throws myException
	{
		return this.get_FormatedValue("GEWICHT");	
	}

	public String get_GEWICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEWICHT");
	}

	public String get_GEWICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEWICHT",cNotNullValue);
	}

	public String get_GEWICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEWICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEWICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEWICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEWICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEWICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT", calNewValueFormated);
	}
		public Double get_GEWICHT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GEWICHT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GEWICHT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GEWICHT").getDoubleValue();
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
	public String get_GEWICHT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GEWICHT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GEWICHT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GEWICHT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT").getBigDecimalValue();
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
	
	
	public String get_HANDEINGABE_cUF() throws myException
	{
		return this.get_UnFormatedValue("HANDEINGABE");
	}

	public String get_HANDEINGABE_cF() throws myException
	{
		return this.get_FormatedValue("HANDEINGABE");	
	}

	public String get_HANDEINGABE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HANDEINGABE");
	}

	public String get_HANDEINGABE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HANDEINGABE",cNotNullValue);
	}

	public String get_HANDEINGABE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HANDEINGABE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HANDEINGABE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HANDEINGABE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HANDEINGABE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HANDEINGABE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDEINGABE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDEINGABE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDEINGABE", calNewValueFormated);
	}
		public boolean is_HANDEINGABE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_HANDEINGABE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_HANDEINGABE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_HANDEINGABE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_HANDEINGABE_BEM_cUF() throws myException
	{
		return this.get_UnFormatedValue("HANDEINGABE_BEM");
	}

	public String get_HANDEINGABE_BEM_cF() throws myException
	{
		return this.get_FormatedValue("HANDEINGABE_BEM");	
	}

	public String get_HANDEINGABE_BEM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HANDEINGABE_BEM");
	}

	public String get_HANDEINGABE_BEM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HANDEINGABE_BEM",cNotNullValue);
	}

	public String get_HANDEINGABE_BEM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HANDEINGABE_BEM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HANDEINGABE_BEM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HANDEINGABE_BEM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HANDEINGABE_BEM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDEINGABE_BEM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDEINGABE_BEM", calNewValueFormated);
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
	
	
	public String get_ID_USER_WAEGUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_WAEGUNG");
	}

	public String get_ID_USER_WAEGUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_WAEGUNG");	
	}

	public String get_ID_USER_WAEGUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_WAEGUNG");
	}

	public String get_ID_USER_WAEGUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_WAEGUNG",cNotNullValue);
	}

	public String get_ID_USER_WAEGUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_WAEGUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_WAEGUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_WAEGUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_WAEGUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_WAEGUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_WAEGUNG", calNewValueFormated);
	}
		public Long get_ID_USER_WAEGUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_WAEGUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_WAEGUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_WAEGUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_WAEGUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_WAEGUNG").getDoubleValue();
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
	public String get_ID_USER_WAEGUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_WAEGUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_WAEGUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_WAEGUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_WAEGUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_WAEGUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_WAEGUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_WAEGUNG").getBigDecimalValue();
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
	
	
	public String get_ID_WAAGE_SETTINGS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAAGE_SETTINGS");
	}

	public String get_ID_WAAGE_SETTINGS_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAAGE_SETTINGS");	
	}

	public String get_ID_WAAGE_SETTINGS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAAGE_SETTINGS");
	}

	public String get_ID_WAAGE_SETTINGS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAAGE_SETTINGS",cNotNullValue);
	}

	public String get_ID_WAAGE_SETTINGS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAAGE_SETTINGS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAAGE_SETTINGS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAAGE_SETTINGS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAAGE_SETTINGS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_SETTINGS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAAGE_SETTINGS", calNewValueFormated);
	}
		public Long get_ID_WAAGE_SETTINGS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAAGE_SETTINGS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAAGE_SETTINGS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAAGE_SETTINGS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAAGE_SETTINGS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAAGE_SETTINGS").getDoubleValue();
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
	public String get_ID_WAAGE_SETTINGS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAAGE_SETTINGS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WAAGE_SETTINGS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAAGE_SETTINGS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WAAGE_SETTINGS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAAGE_SETTINGS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAAGE_SETTINGS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAAGE_SETTINGS").getBigDecimalValue();
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
	
	
	public String get_ID_WAEGUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAEGUNG");
	}

	public String get_ID_WAEGUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAEGUNG");	
	}

	public String get_ID_WAEGUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAEGUNG");
	}

	public String get_ID_WAEGUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAEGUNG",cNotNullValue);
	}

	public String get_ID_WAEGUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAEGUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAEGUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAEGUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAEGUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAEGUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEGUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEGUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEGUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEGUNG", calNewValueFormated);
	}
		public Long get_ID_WAEGUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAEGUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAEGUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAEGUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAEGUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAEGUNG").getDoubleValue();
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
	public String get_ID_WAEGUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEGUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WAEGUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEGUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WAEGUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEGUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAEGUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEGUNG").getBigDecimalValue();
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
	
	
	public String get_ID_WIEGEKARTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE");
	}

	public String get_ID_WIEGEKARTE_cF() throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE");	
	}

	public String get_ID_WIEGEKARTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WIEGEKARTE");
	}

	public String get_ID_WIEGEKARTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE",cNotNullValue);
	}

	public String get_ID_WIEGEKARTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
	}
		public Long get_ID_WIEGEKARTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WIEGEKARTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WIEGEKARTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WIEGEKARTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE").getDoubleValue();
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
	public String get_ID_WIEGEKARTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WIEGEKARTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WIEGEKARTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WIEGEKARTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE").getBigDecimalValue();
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
		public String get_STORNO_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNO");
	}

	public String get_STORNO_cF() throws myException
	{
		return this.get_FormatedValue("STORNO");	
	}

	public String get_STORNO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNO");
	}

	public String get_STORNO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNO",cNotNullValue);
	}

	public String get_STORNO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO", calNewValueFormated);
	}
		public boolean is_STORNO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STORNO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNO_cUF_NN("N").equals("N"))
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
	
	
	public String get_WAAGE_DS_ORI_cUF() throws myException
	{
		return this.get_UnFormatedValue("WAAGE_DS_ORI");
	}

	public String get_WAAGE_DS_ORI_cF() throws myException
	{
		return this.get_FormatedValue("WAAGE_DS_ORI");	
	}

	public String get_WAAGE_DS_ORI_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WAAGE_DS_ORI");
	}

	public String get_WAAGE_DS_ORI_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WAAGE_DS_ORI",cNotNullValue);
	}

	public String get_WAAGE_DS_ORI_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WAAGE_DS_ORI",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WAAGE_DS_ORI", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WAAGE_DS_ORI(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WAAGE_DS_ORI", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAAGE_DS_ORI_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAAGE_DS_ORI", calNewValueFormated);
	}
		public String get_WAEGUNG_POS_cUF() throws myException
	{
		return this.get_UnFormatedValue("WAEGUNG_POS");
	}

	public String get_WAEGUNG_POS_cF() throws myException
	{
		return this.get_FormatedValue("WAEGUNG_POS");	
	}

	public String get_WAEGUNG_POS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WAEGUNG_POS");
	}

	public String get_WAEGUNG_POS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WAEGUNG_POS",cNotNullValue);
	}

	public String get_WAEGUNG_POS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WAEGUNG_POS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WAEGUNG_POS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WAEGUNG_POS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WAEGUNG_POS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WAEGUNG_POS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEGUNG_POS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEGUNG_POS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEGUNG_POS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEGUNG_POS", calNewValueFormated);
	}
		public Long get_WAEGUNG_POS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("WAEGUNG_POS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_WAEGUNG_POS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WAEGUNG_POS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WAEGUNG_POS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WAEGUNG_POS").getDoubleValue();
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
	public String get_WAEGUNG_POS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WAEGUNG_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_WAEGUNG_POS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WAEGUNG_POS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_WAEGUNG_POS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WAEGUNG_POS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WAEGUNG_POS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WAEGUNG_POS").getBigDecimalValue();
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
	
	
	public String get_W_BRUTTO_GEWICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_BRUTTO_GEWICHT");
	}

	public String get_W_BRUTTO_GEWICHT_cF() throws myException
	{
		return this.get_FormatedValue("W_BRUTTO_GEWICHT");	
	}

	public String get_W_BRUTTO_GEWICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_BRUTTO_GEWICHT");
	}

	public String get_W_BRUTTO_GEWICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_BRUTTO_GEWICHT",cNotNullValue);
	}

	public String get_W_BRUTTO_GEWICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_BRUTTO_GEWICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_BRUTTO_GEWICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_BRUTTO_GEWICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_BRUTTO_GEWICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_BRUTTO_GEWICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_BRUTTO_GEWICHT", calNewValueFormated);
	}
		public String get_W_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_DATUM");
	}

	public String get_W_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("W_DATUM");	
	}

	public String get_W_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_DATUM");
	}

	public String get_W_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_DATUM",cNotNullValue);
	}

	public String get_W_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_DATUM", calNewValueFormated);
	}
		public String get_W_EINHEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_EINHEIT");
	}

	public String get_W_EINHEIT_cF() throws myException
	{
		return this.get_FormatedValue("W_EINHEIT");	
	}

	public String get_W_EINHEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_EINHEIT");
	}

	public String get_W_EINHEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_EINHEIT",cNotNullValue);
	}

	public String get_W_EINHEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_EINHEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_EINHEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_EINHEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_EINHEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_EINHEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_EINHEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_EINHEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_EINHEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_EINHEIT", calNewValueFormated);
	}
		public String get_W_FEHLERCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_FEHLERCODE");
	}

	public String get_W_FEHLERCODE_cF() throws myException
	{
		return this.get_FormatedValue("W_FEHLERCODE");	
	}

	public String get_W_FEHLERCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_FEHLERCODE");
	}

	public String get_W_FEHLERCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_FEHLERCODE",cNotNullValue);
	}

	public String get_W_FEHLERCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_FEHLERCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_FEHLERCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_FEHLERCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_FEHLERCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_FEHLERCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_FEHLERCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_FEHLERCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_FEHLERCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_FEHLERCODE", calNewValueFormated);
	}
		public String get_W_IDENT_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_IDENT_NR");
	}

	public String get_W_IDENT_NR_cF() throws myException
	{
		return this.get_FormatedValue("W_IDENT_NR");	
	}

	public String get_W_IDENT_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_IDENT_NR");
	}

	public String get_W_IDENT_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_IDENT_NR",cNotNullValue);
	}

	public String get_W_IDENT_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_IDENT_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_IDENT_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_IDENT_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_IDENT_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_IDENT_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_IDENT_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_IDENT_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_IDENT_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_IDENT_NR", calNewValueFormated);
	}
		public String get_W_NETTO_GEWICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_NETTO_GEWICHT");
	}

	public String get_W_NETTO_GEWICHT_cF() throws myException
	{
		return this.get_FormatedValue("W_NETTO_GEWICHT");	
	}

	public String get_W_NETTO_GEWICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_NETTO_GEWICHT");
	}

	public String get_W_NETTO_GEWICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_NETTO_GEWICHT",cNotNullValue);
	}

	public String get_W_NETTO_GEWICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_NETTO_GEWICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_NETTO_GEWICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_NETTO_GEWICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_NETTO_GEWICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_NETTO_GEWICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_NETTO_GEWICHT", calNewValueFormated);
	}
		public String get_W_PRUEFZIFFER_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_PRUEFZIFFER");
	}

	public String get_W_PRUEFZIFFER_cF() throws myException
	{
		return this.get_FormatedValue("W_PRUEFZIFFER");	
	}

	public String get_W_PRUEFZIFFER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_PRUEFZIFFER");
	}

	public String get_W_PRUEFZIFFER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_PRUEFZIFFER",cNotNullValue);
	}

	public String get_W_PRUEFZIFFER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_PRUEFZIFFER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_PRUEFZIFFER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_PRUEFZIFFER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_PRUEFZIFFER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_PRUEFZIFFER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_PRUEFZIFFER", calNewValueFormated);
	}
		public String get_W_STATUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_STATUS");
	}

	public String get_W_STATUS_cF() throws myException
	{
		return this.get_FormatedValue("W_STATUS");	
	}

	public String get_W_STATUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_STATUS");
	}

	public String get_W_STATUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_STATUS",cNotNullValue);
	}

	public String get_W_STATUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_STATUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_STATUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_STATUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_STATUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_STATUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_STATUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_STATUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_STATUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_STATUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_STATUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_STATUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_STATUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_STATUS", calNewValueFormated);
	}
		public String get_W_TARACODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_TARACODE");
	}

	public String get_W_TARACODE_cF() throws myException
	{
		return this.get_FormatedValue("W_TARACODE");	
	}

	public String get_W_TARACODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_TARACODE");
	}

	public String get_W_TARACODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_TARACODE",cNotNullValue);
	}

	public String get_W_TARACODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_TARACODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_TARACODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_TARACODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_TARACODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_TARACODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TARACODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TARACODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARACODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TARACODE", calNewValueFormated);
	}
		public String get_W_TARAGEWICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_TARAGEWICHT");
	}

	public String get_W_TARAGEWICHT_cF() throws myException
	{
		return this.get_FormatedValue("W_TARAGEWICHT");	
	}

	public String get_W_TARAGEWICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_TARAGEWICHT");
	}

	public String get_W_TARAGEWICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_TARAGEWICHT",cNotNullValue);
	}

	public String get_W_TARAGEWICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_TARAGEWICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_TARAGEWICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_TARAGEWICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_TARAGEWICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TARAGEWICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TARAGEWICHT", calNewValueFormated);
	}
		public String get_W_TERMINAL_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_TERMINAL");
	}

	public String get_W_TERMINAL_cF() throws myException
	{
		return this.get_FormatedValue("W_TERMINAL");	
	}

	public String get_W_TERMINAL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_TERMINAL");
	}

	public String get_W_TERMINAL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_TERMINAL",cNotNullValue);
	}

	public String get_W_TERMINAL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_TERMINAL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_TERMINAL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_TERMINAL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_TERMINAL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_TERMINAL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TERMINAL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TERMINAL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_TERMINAL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_TERMINAL", calNewValueFormated);
	}
		public String get_W_WAAGEN_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_WAAGEN_NR");
	}

	public String get_W_WAAGEN_NR_cF() throws myException
	{
		return this.get_FormatedValue("W_WAAGEN_NR");	
	}

	public String get_W_WAAGEN_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_WAAGEN_NR");
	}

	public String get_W_WAAGEN_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_WAAGEN_NR",cNotNullValue);
	}

	public String get_W_WAAGEN_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_WAAGEN_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_WAAGEN_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_WAAGEN_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_WAAGEN_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAAGEN_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_WAAGEN_NR", calNewValueFormated);
	}
		public boolean is_W_WAAGEN_NR_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_W_WAAGEN_NR_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_W_WAAGEN_NR_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_W_WAAGEN_NR_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_W_WAEGEBEREICH_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_WAEGEBEREICH");
	}

	public String get_W_WAEGEBEREICH_cF() throws myException
	{
		return this.get_FormatedValue("W_WAEGEBEREICH");	
	}

	public String get_W_WAEGEBEREICH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_WAEGEBEREICH");
	}

	public String get_W_WAEGEBEREICH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_WAEGEBEREICH",cNotNullValue);
	}

	public String get_W_WAEGEBEREICH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_WAEGEBEREICH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_WAEGEBEREICH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_WAEGEBEREICH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_WAEGEBEREICH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_WAEGEBEREICH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_WAEGEBEREICH", calNewValueFormated);
	}
		public boolean is_W_WAEGEBEREICH_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_W_WAEGEBEREICH_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_W_WAEGEBEREICH_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_W_WAEGEBEREICH_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_W_ZEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("W_ZEIT");
	}

	public String get_W_ZEIT_cF() throws myException
	{
		return this.get_FormatedValue("W_ZEIT");	
	}

	public String get_W_ZEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("W_ZEIT");
	}

	public String get_W_ZEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("W_ZEIT",cNotNullValue);
	}

	public String get_W_ZEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("W_ZEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("W_ZEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_W_ZEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("W_ZEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("W_ZEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_ZEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_ZEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_W_ZEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("W_ZEIT", calNewValueFormated);
	}
		public String get_ZEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEIT");
	}

	public String get_ZEIT_cF() throws myException
	{
		return this.get_FormatedValue("ZEIT");	
	}

	public String get_ZEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEIT");
	}

	public String get_ZEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEIT",cNotNullValue);
	}

	public String get_ZEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEIT", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("DATUM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEWICHT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("HANDEINGABE",MyRECORD.DATATYPES.YESNO);
	put("HANDEINGABE_BEM",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_WAEGUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAAGE_SETTINGS",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAEGUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("STORNO",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("WAAGE_DS_ORI",MyRECORD.DATATYPES.TEXT);
	put("WAEGUNG_POS",MyRECORD.DATATYPES.NUMBER);
	put("W_BRUTTO_GEWICHT",MyRECORD.DATATYPES.TEXT);
	put("W_DATUM",MyRECORD.DATATYPES.TEXT);
	put("W_EINHEIT",MyRECORD.DATATYPES.TEXT);
	put("W_FEHLERCODE",MyRECORD.DATATYPES.TEXT);
	put("W_IDENT_NR",MyRECORD.DATATYPES.TEXT);
	put("W_NETTO_GEWICHT",MyRECORD.DATATYPES.TEXT);
	put("W_PRUEFZIFFER",MyRECORD.DATATYPES.TEXT);
	put("W_STATUS",MyRECORD.DATATYPES.TEXT);
	put("W_TARACODE",MyRECORD.DATATYPES.TEXT);
	put("W_TARAGEWICHT",MyRECORD.DATATYPES.TEXT);
	put("W_TERMINAL",MyRECORD.DATATYPES.TEXT);
	put("W_WAAGEN_NR",MyRECORD.DATATYPES.YESNO);
	put("W_WAEGEBEREICH",MyRECORD.DATATYPES.YESNO);
	put("W_ZEIT",MyRECORD.DATATYPES.TEXT);
	put("ZEIT",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_WAEGUNG.HM_FIELDS;	
	}

}
