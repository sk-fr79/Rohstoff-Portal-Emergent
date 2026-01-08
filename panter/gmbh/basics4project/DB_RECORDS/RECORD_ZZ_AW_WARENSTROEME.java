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

public class RECORD_ZZ_AW_WARENSTROEME extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ZZ_AW_WARENSTROEME";
    public static String IDFIELD   = "ID_ZZ_AW_WARENSTROEME";
    

	//erzeugen eines RECORDNEW_JT_ZZ_AW_WARENSTROEME - felds
	private RECORDNEW_ZZ_AW_WARENSTROEME   recNEW = null;

    private _TAB  tab = _TAB.zz_aw_warenstroeme;  



	public RECORD_ZZ_AW_WARENSTROEME() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");
	}


	public RECORD_ZZ_AW_WARENSTROEME(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");
	}



	public RECORD_ZZ_AW_WARENSTROEME(RECORD_ZZ_AW_WARENSTROEME recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_AW_WARENSTROEME.TABLENAME);
	}


	//2014-04-03
	public RECORD_ZZ_AW_WARENSTROEME(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_AW_WARENSTROEME.TABLENAME);
	}




	public RECORD_ZZ_AW_WARENSTROEME(long lID_Unformated) throws myException
	{
		super("JT_ZZ_AW_WARENSTROEME","ID_ZZ_AW_WARENSTROEME",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_AW_WARENSTROEME.TABLENAME);
	}

	public RECORD_ZZ_AW_WARENSTROEME(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME", "ID_ZZ_AW_WARENSTROEME="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_AW_WARENSTROEME.TABLENAME);
	}
	
	

	public RECORD_ZZ_AW_WARENSTROEME(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ZZ_AW_WARENSTROEME","ID_ZZ_AW_WARENSTROEME",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_AW_WARENSTROEME.TABLENAME);

	}


	public RECORD_ZZ_AW_WARENSTROEME(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ZZ_AW_WARENSTROEME");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME", "ID_ZZ_AW_WARENSTROEME="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZZ_AW_WARENSTROEME.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ZZ_AW_WARENSTROEME();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ZZ_AW_WARENSTROEME.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ZZ_AW_WARENSTROEME";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ZZ_AW_WARENSTROEME_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ZZ_AW_WARENSTROEME_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ZZ_AW_WARENSTROEME was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ZZ_AW_WARENSTROEME", bibE2.cTO(), "ID_ZZ_AW_WARENSTROEME="+this.get_ID_ZZ_AW_WARENSTROEME_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ZZ_AW_WARENSTROEME was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ZZ_AW_WARENSTROEME", bibE2.cTO(), "ID_ZZ_AW_WARENSTROEME="+this.get_ID_ZZ_AW_WARENSTROEME_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME WHERE ID_ZZ_AW_WARENSTROEME="+this.get_ID_ZZ_AW_WARENSTROEME_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME WHERE ID_ZZ_AW_WARENSTROEME="+this.get_ID_ZZ_AW_WARENSTROEME_cUF();
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
			if (S.isFull(this.get_ID_ZZ_AW_WARENSTROEME_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME", "ID_ZZ_AW_WARENSTROEME="+this.get_ID_ZZ_AW_WARENSTROEME_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ZZ_AW_WARENSTROEME get_RECORDNEW_ZZ_AW_WARENSTROEME() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ZZ_AW_WARENSTROEME();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ZZ_AW_WARENSTROEME(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ZZ_AW_WARENSTROEME create_Instance() throws myException {
		return new RECORD_ZZ_AW_WARENSTROEME();
	}
	
	public static RECORD_ZZ_AW_WARENSTROEME create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ZZ_AW_WARENSTROEME(Conn);
    }

	public static RECORD_ZZ_AW_WARENSTROEME create_Instance(RECORD_ZZ_AW_WARENSTROEME recordOrig) {
		return new RECORD_ZZ_AW_WARENSTROEME(recordOrig);
    }

	public static RECORD_ZZ_AW_WARENSTROEME create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ZZ_AW_WARENSTROEME(lID_Unformated);
    }

	public static RECORD_ZZ_AW_WARENSTROEME create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ZZ_AW_WARENSTROEME(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ZZ_AW_WARENSTROEME create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ZZ_AW_WARENSTROEME(lID_Unformated, Conn);
	}

	public static RECORD_ZZ_AW_WARENSTROEME create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ZZ_AW_WARENSTROEME(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ZZ_AW_WARENSTROEME create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ZZ_AW_WARENSTROEME(recordOrig);	    
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
    public RECORD_ZZ_AW_WARENSTROEME build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ZZ_AW_WARENSTROEME WHERE ID_ZZ_AW_WARENSTROEME="+this.get_ID_ZZ_AW_WARENSTROEME_cUF());
      }
      //return new RECORD_ZZ_AW_WARENSTROEME(this.get_cSQL_4_Build());
      RECORD_ZZ_AW_WARENSTROEME rec = new RECORD_ZZ_AW_WARENSTROEME();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ZZ_AW_WARENSTROEME
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ZZ_AW_WARENSTROEME-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ZZ_AW_WARENSTROEME get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ZZ_AW_WARENSTROEME_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ZZ_AW_WARENSTROEME  recNew = new RECORDNEW_ZZ_AW_WARENSTROEME();
		
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
    public RECORD_ZZ_AW_WARENSTROEME set_recordNew(RECORDNEW_ZZ_AW_WARENSTROEME recnew) throws myException {
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
	
	

	public static String FIELD__BENUTZERKUERZEL = "BENUTZERKUERZEL";
	public static String FIELD__EPREIS_BRUTTO_WA = "EPREIS_BRUTTO_WA";
	public static String FIELD__EPREIS_BRUTTO_WE = "EPREIS_BRUTTO_WE";
	public static String FIELD__EPREIS_NETTO_MARGE = "EPREIS_NETTO_MARGE";
	public static String FIELD__EPREIS_NETTO_WA = "EPREIS_NETTO_WA";
	public static String FIELD__EPREIS_NETTO_WE = "EPREIS_NETTO_WE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GESAMTPREIS_WA = "GESAMTPREIS_WA";
	public static String FIELD__GESAMTPREIS_WE = "GESAMTPREIS_WE";
	public static String FIELD__GPREIS_ABZ_AUF_NETTOMGE_WA = "GPREIS_ABZ_AUF_NETTOMGE_WA";
	public static String FIELD__GPREIS_ABZ_AUF_NETTOMGE_WE = "GPREIS_ABZ_AUF_NETTOMGE_WE";
	public static String FIELD__GPREIS_ABZ_MGE_WA = "GPREIS_ABZ_MGE_WA";
	public static String FIELD__GPREIS_ABZ_MGE_WE = "GPREIS_ABZ_MGE_WE";
	public static String FIELD__GPREIS_NETTO_MARGE = "GPREIS_NETTO_MARGE";
	public static String FIELD__GPREIS_NETTO_WA = "GPREIS_NETTO_WA";
	public static String FIELD__GPREIS_NETTO_WE = "GPREIS_NETTO_WE";
	public static String FIELD__ID_ADRESSE_LAGER = "ID_ADRESSE_LAGER";
	public static String FIELD__ID_ARTIKEL = "ID_ARTIKEL";
	public static String FIELD__ID_EINHEIT = "ID_EINHEIT";
	public static String FIELD__ID_EINHEIT_PREIS = "ID_EINHEIT_PREIS";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_ZZ_AW_WARENSTROEME = "ID_ZZ_AW_WARENSTROEME";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MENGENDIVISOR = "MENGENDIVISOR";
	public static String FIELD__MENGE_ABZUG_LAGERAUSGANG = "MENGE_ABZUG_LAGERAUSGANG";
	public static String FIELD__MENGE_ABZUG_LAGEREINGANG = "MENGE_ABZUG_LAGEREINGANG";
	public static String FIELD__MENGE_FUHRE_ABZUG_WA = "MENGE_FUHRE_ABZUG_WA";
	public static String FIELD__MENGE_FUHRE_ABZUG_WE = "MENGE_FUHRE_ABZUG_WE";
	public static String FIELD__MENGE_FUHRE_WA = "MENGE_FUHRE_WA";
	public static String FIELD__MENGE_FUHRE_WE = "MENGE_FUHRE_WE";
	public static String FIELD__MENGE_LAGERAUSGANG = "MENGE_LAGERAUSGANG";
	public static String FIELD__MENGE_LAGEREINGANG = "MENGE_LAGEREINGANG";
	public static String FIELD__MENGE_RG_POS_ABZUG_LAG_REL_WA = "MENGE_RG_POS_ABZUG_LAG_REL_WA";
	public static String FIELD__MENGE_RG_POS_ABZUG_LAG_REL_WE = "MENGE_RG_POS_ABZUG_LAG_REL_WE";
	public static String FIELD__MENGE_RG_POS_ABZUG_WA = "MENGE_RG_POS_ABZUG_WA";
	public static String FIELD__MENGE_RG_POS_ABZUG_WE = "MENGE_RG_POS_ABZUG_WE";
	public static String FIELD__MENGE_RG_POS_NETTO_WA = "MENGE_RG_POS_NETTO_WA";
	public static String FIELD__MENGE_RG_POS_NETTO_WE = "MENGE_RG_POS_NETTO_WE";
	public static String FIELD__MENGE_RG_POS_WA = "MENGE_RG_POS_WA";
	public static String FIELD__MENGE_RG_POS_WE = "MENGE_RG_POS_WE";
	public static String FIELD__REPORTNUMMER = "REPORTNUMMER";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_BENUTZERKUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("BENUTZERKUERZEL");
	}

	public String get_BENUTZERKUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("BENUTZERKUERZEL");	
	}

	public String get_BENUTZERKUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BENUTZERKUERZEL");
	}

	public String get_BENUTZERKUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BENUTZERKUERZEL",cNotNullValue);
	}

	public String get_BENUTZERKUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BENUTZERKUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BENUTZERKUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BENUTZERKUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BENUTZERKUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BENUTZERKUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BENUTZERKUERZEL", calNewValueFormated);
	}
		public String get_EPREIS_BRUTTO_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_BRUTTO_WA");
	}

	public String get_EPREIS_BRUTTO_WA_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_BRUTTO_WA");	
	}

	public String get_EPREIS_BRUTTO_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_BRUTTO_WA");
	}

	public String get_EPREIS_BRUTTO_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_BRUTTO_WA",cNotNullValue);
	}

	public String get_EPREIS_BRUTTO_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_BRUTTO_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_BRUTTO_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_BRUTTO_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_BRUTTO_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WA", calNewValueFormated);
	}
		public Double get_EPREIS_BRUTTO_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_BRUTTO_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_BRUTTO_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_BRUTTO_WA").getDoubleValue();
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
	public String get_EPREIS_BRUTTO_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_BRUTTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EPREIS_BRUTTO_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_BRUTTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EPREIS_BRUTTO_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_BRUTTO_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_BRUTTO_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_BRUTTO_WA").getBigDecimalValue();
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
	
	
	public String get_EPREIS_BRUTTO_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_BRUTTO_WE");
	}

	public String get_EPREIS_BRUTTO_WE_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_BRUTTO_WE");	
	}

	public String get_EPREIS_BRUTTO_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_BRUTTO_WE");
	}

	public String get_EPREIS_BRUTTO_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_BRUTTO_WE",cNotNullValue);
	}

	public String get_EPREIS_BRUTTO_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_BRUTTO_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_BRUTTO_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_BRUTTO_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_BRUTTO_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_BRUTTO_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_BRUTTO_WE", calNewValueFormated);
	}
		public Double get_EPREIS_BRUTTO_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_BRUTTO_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_BRUTTO_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_BRUTTO_WE").getDoubleValue();
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
	public String get_EPREIS_BRUTTO_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_BRUTTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EPREIS_BRUTTO_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_BRUTTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EPREIS_BRUTTO_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_BRUTTO_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_BRUTTO_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_BRUTTO_WE").getBigDecimalValue();
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
	
	
	public String get_EPREIS_NETTO_MARGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_NETTO_MARGE");
	}

	public String get_EPREIS_NETTO_MARGE_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_NETTO_MARGE");	
	}

	public String get_EPREIS_NETTO_MARGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_NETTO_MARGE");
	}

	public String get_EPREIS_NETTO_MARGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_NETTO_MARGE",cNotNullValue);
	}

	public String get_EPREIS_NETTO_MARGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_NETTO_MARGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_NETTO_MARGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_NETTO_MARGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_MARGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_MARGE", calNewValueFormated);
	}
		public Double get_EPREIS_NETTO_MARGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_NETTO_MARGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_NETTO_MARGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_NETTO_MARGE").getDoubleValue();
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
	public String get_EPREIS_NETTO_MARGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_NETTO_MARGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EPREIS_NETTO_MARGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_NETTO_MARGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EPREIS_NETTO_MARGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_NETTO_MARGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_NETTO_MARGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_NETTO_MARGE").getBigDecimalValue();
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
	
	
	public String get_EPREIS_NETTO_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_NETTO_WA");
	}

	public String get_EPREIS_NETTO_WA_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_NETTO_WA");	
	}

	public String get_EPREIS_NETTO_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_NETTO_WA");
	}

	public String get_EPREIS_NETTO_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_NETTO_WA",cNotNullValue);
	}

	public String get_EPREIS_NETTO_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_NETTO_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_NETTO_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_NETTO_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WA", calNewValueFormated);
	}
		public Double get_EPREIS_NETTO_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_NETTO_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_NETTO_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_NETTO_WA").getDoubleValue();
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
	public String get_EPREIS_NETTO_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_NETTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EPREIS_NETTO_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_NETTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EPREIS_NETTO_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_NETTO_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_NETTO_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_NETTO_WA").getBigDecimalValue();
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
	
	
	public String get_EPREIS_NETTO_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_NETTO_WE");
	}

	public String get_EPREIS_NETTO_WE_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_NETTO_WE");	
	}

	public String get_EPREIS_NETTO_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_NETTO_WE");
	}

	public String get_EPREIS_NETTO_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_NETTO_WE",cNotNullValue);
	}

	public String get_EPREIS_NETTO_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_NETTO_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_NETTO_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_NETTO_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_NETTO_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_NETTO_WE", calNewValueFormated);
	}
		public Double get_EPREIS_NETTO_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_NETTO_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_NETTO_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_NETTO_WE").getDoubleValue();
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
	public String get_EPREIS_NETTO_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_NETTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EPREIS_NETTO_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_NETTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EPREIS_NETTO_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_NETTO_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_NETTO_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_NETTO_WE").getBigDecimalValue();
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
		public String get_GESAMTPREIS_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_WA");
	}

	public String get_GESAMTPREIS_WA_cF() throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_WA");	
	}

	public String get_GESAMTPREIS_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESAMTPREIS_WA");
	}

	public String get_GESAMTPREIS_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_WA",cNotNullValue);
	}

	public String get_GESAMTPREIS_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESAMTPREIS_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESAMTPREIS_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_WA", calNewValueFormated);
	}
		public Double get_GESAMTPREIS_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GESAMTPREIS_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_WA").getDoubleValue();
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
	public String get_GESAMTPREIS_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GESAMTPREIS_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GESAMTPREIS_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GESAMTPREIS_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_WA").getBigDecimalValue();
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
	
	
	public String get_GESAMTPREIS_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_WE");
	}

	public String get_GESAMTPREIS_WE_cF() throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_WE");	
	}

	public String get_GESAMTPREIS_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESAMTPREIS_WE");
	}

	public String get_GESAMTPREIS_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_WE",cNotNullValue);
	}

	public String get_GESAMTPREIS_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESAMTPREIS_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESAMTPREIS_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_WE", calNewValueFormated);
	}
		public Double get_GESAMTPREIS_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GESAMTPREIS_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_WE").getDoubleValue();
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
	public String get_GESAMTPREIS_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GESAMTPREIS_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GESAMTPREIS_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GESAMTPREIS_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_WE").getBigDecimalValue();
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
	
	
	public String get_GPREIS_ABZ_AUF_NETTOMGE_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WA");
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WA_cF() throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WA");	
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GPREIS_ABZ_AUF_NETTOMGE_WA");
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WA",cNotNullValue);
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WA", calNewValueFormated);
	}
		public Double get_GPREIS_ABZ_AUF_NETTOMGE_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GPREIS_ABZ_AUF_NETTOMGE_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WA").getDoubleValue();
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
	public String get_GPREIS_ABZ_AUF_NETTOMGE_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_AUF_NETTOMGE_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GPREIS_ABZ_AUF_NETTOMGE_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_AUF_NETTOMGE_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GPREIS_ABZ_AUF_NETTOMGE_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GPREIS_ABZ_AUF_NETTOMGE_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WA").getBigDecimalValue();
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
	
	
	public String get_GPREIS_ABZ_AUF_NETTOMGE_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WE");
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WE_cF() throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WE");	
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GPREIS_ABZ_AUF_NETTOMGE_WE");
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WE",cNotNullValue);
	}

	public String get_GPREIS_ABZ_AUF_NETTOMGE_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_AUF_NETTOMGE_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_AUF_NETTOMGE_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_AUF_NETTOMGE_WE", calNewValueFormated);
	}
		public Double get_GPREIS_ABZ_AUF_NETTOMGE_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GPREIS_ABZ_AUF_NETTOMGE_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WE").getDoubleValue();
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
	public String get_GPREIS_ABZ_AUF_NETTOMGE_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_AUF_NETTOMGE_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GPREIS_ABZ_AUF_NETTOMGE_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_AUF_NETTOMGE_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GPREIS_ABZ_AUF_NETTOMGE_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GPREIS_ABZ_AUF_NETTOMGE_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_AUF_NETTOMGE_WE").getBigDecimalValue();
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
	
	
	public String get_GPREIS_ABZ_MGE_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_MGE_WA");
	}

	public String get_GPREIS_ABZ_MGE_WA_cF() throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_MGE_WA");	
	}

	public String get_GPREIS_ABZ_MGE_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GPREIS_ABZ_MGE_WA");
	}

	public String get_GPREIS_ABZ_MGE_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_MGE_WA",cNotNullValue);
	}

	public String get_GPREIS_ABZ_MGE_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_MGE_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_MGE_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GPREIS_ABZ_MGE_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WA", calNewValueFormated);
	}
		public Double get_GPREIS_ABZ_MGE_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_MGE_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GPREIS_ABZ_MGE_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_MGE_WA").getDoubleValue();
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
	public String get_GPREIS_ABZ_MGE_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_MGE_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GPREIS_ABZ_MGE_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_MGE_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GPREIS_ABZ_MGE_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_MGE_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GPREIS_ABZ_MGE_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_MGE_WA").getBigDecimalValue();
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
	
	
	public String get_GPREIS_ABZ_MGE_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_MGE_WE");
	}

	public String get_GPREIS_ABZ_MGE_WE_cF() throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_MGE_WE");	
	}

	public String get_GPREIS_ABZ_MGE_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GPREIS_ABZ_MGE_WE");
	}

	public String get_GPREIS_ABZ_MGE_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GPREIS_ABZ_MGE_WE",cNotNullValue);
	}

	public String get_GPREIS_ABZ_MGE_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GPREIS_ABZ_MGE_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GPREIS_ABZ_MGE_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GPREIS_ABZ_MGE_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_ABZ_MGE_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_ABZ_MGE_WE", calNewValueFormated);
	}
		public Double get_GPREIS_ABZ_MGE_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_MGE_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GPREIS_ABZ_MGE_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GPREIS_ABZ_MGE_WE").getDoubleValue();
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
	public String get_GPREIS_ABZ_MGE_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_MGE_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GPREIS_ABZ_MGE_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_ABZ_MGE_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GPREIS_ABZ_MGE_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_MGE_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GPREIS_ABZ_MGE_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_ABZ_MGE_WE").getBigDecimalValue();
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
	
	
	public String get_GPREIS_NETTO_MARGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GPREIS_NETTO_MARGE");
	}

	public String get_GPREIS_NETTO_MARGE_cF() throws myException
	{
		return this.get_FormatedValue("GPREIS_NETTO_MARGE");	
	}

	public String get_GPREIS_NETTO_MARGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GPREIS_NETTO_MARGE");
	}

	public String get_GPREIS_NETTO_MARGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GPREIS_NETTO_MARGE",cNotNullValue);
	}

	public String get_GPREIS_NETTO_MARGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GPREIS_NETTO_MARGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GPREIS_NETTO_MARGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GPREIS_NETTO_MARGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GPREIS_NETTO_MARGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_MARGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_MARGE", calNewValueFormated);
	}
		public Double get_GPREIS_NETTO_MARGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GPREIS_NETTO_MARGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GPREIS_NETTO_MARGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GPREIS_NETTO_MARGE").getDoubleValue();
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
	public String get_GPREIS_NETTO_MARGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_NETTO_MARGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GPREIS_NETTO_MARGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_NETTO_MARGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GPREIS_NETTO_MARGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_NETTO_MARGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GPREIS_NETTO_MARGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_NETTO_MARGE").getBigDecimalValue();
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
	
	
	public String get_GPREIS_NETTO_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("GPREIS_NETTO_WA");
	}

	public String get_GPREIS_NETTO_WA_cF() throws myException
	{
		return this.get_FormatedValue("GPREIS_NETTO_WA");	
	}

	public String get_GPREIS_NETTO_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GPREIS_NETTO_WA");
	}

	public String get_GPREIS_NETTO_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GPREIS_NETTO_WA",cNotNullValue);
	}

	public String get_GPREIS_NETTO_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GPREIS_NETTO_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GPREIS_NETTO_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GPREIS_NETTO_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GPREIS_NETTO_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WA", calNewValueFormated);
	}
		public Double get_GPREIS_NETTO_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GPREIS_NETTO_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GPREIS_NETTO_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GPREIS_NETTO_WA").getDoubleValue();
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
	public String get_GPREIS_NETTO_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_NETTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GPREIS_NETTO_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_NETTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GPREIS_NETTO_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_NETTO_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GPREIS_NETTO_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_NETTO_WA").getBigDecimalValue();
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
	
	
	public String get_GPREIS_NETTO_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GPREIS_NETTO_WE");
	}

	public String get_GPREIS_NETTO_WE_cF() throws myException
	{
		return this.get_FormatedValue("GPREIS_NETTO_WE");	
	}

	public String get_GPREIS_NETTO_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GPREIS_NETTO_WE");
	}

	public String get_GPREIS_NETTO_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GPREIS_NETTO_WE",cNotNullValue);
	}

	public String get_GPREIS_NETTO_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GPREIS_NETTO_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GPREIS_NETTO_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GPREIS_NETTO_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GPREIS_NETTO_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GPREIS_NETTO_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GPREIS_NETTO_WE", calNewValueFormated);
	}
		public Double get_GPREIS_NETTO_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GPREIS_NETTO_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GPREIS_NETTO_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GPREIS_NETTO_WE").getDoubleValue();
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
	public String get_GPREIS_NETTO_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_NETTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GPREIS_NETTO_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GPREIS_NETTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GPREIS_NETTO_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_NETTO_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GPREIS_NETTO_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GPREIS_NETTO_WE").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LAGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER");
	}

	public String get_ID_ADRESSE_LAGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER");	
	}

	public String get_ID_ADRESSE_LAGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER");
	}

	public String get_ID_ADRESSE_LAGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_LAGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_LAGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER").getBigDecimalValue();
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
	
	
	public String get_ID_EINHEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT");
	}

	public String get_ID_EINHEIT_cF() throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT");	
	}

	public String get_ID_EINHEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EINHEIT");
	}

	public String get_ID_EINHEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT",cNotNullValue);
	}

	public String get_ID_EINHEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT", calNewValueFormated);
	}
		public Long get_ID_EINHEIT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EINHEIT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EINHEIT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EINHEIT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT").getDoubleValue();
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
	public String get_ID_EINHEIT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EINHEIT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EINHEIT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EINHEIT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT").getBigDecimalValue();
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
	
	
	public String get_ID_EINHEIT_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT_PREIS");
	}

	public String get_ID_EINHEIT_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT_PREIS");	
	}

	public String get_ID_EINHEIT_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EINHEIT_PREIS");
	}

	public String get_ID_EINHEIT_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT_PREIS",cNotNullValue);
	}

	public String get_ID_EINHEIT_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", calNewValueFormated);
	}
		public Long get_ID_EINHEIT_PREIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EINHEIT_PREIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EINHEIT_PREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT_PREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EINHEIT_PREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT_PREIS").getDoubleValue();
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
	public String get_ID_EINHEIT_PREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EINHEIT_PREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EINHEIT_PREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT_PREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EINHEIT_PREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT_PREIS").getBigDecimalValue();
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
	
	
	public String get_ID_ZZ_AW_WARENSTROEME_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ZZ_AW_WARENSTROEME");
	}

	public String get_ID_ZZ_AW_WARENSTROEME_cF() throws myException
	{
		return this.get_FormatedValue("ID_ZZ_AW_WARENSTROEME");	
	}

	public String get_ID_ZZ_AW_WARENSTROEME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ZZ_AW_WARENSTROEME");
	}

	public String get_ID_ZZ_AW_WARENSTROEME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ZZ_AW_WARENSTROEME",cNotNullValue);
	}

	public String get_ID_ZZ_AW_WARENSTROEME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ZZ_AW_WARENSTROEME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ZZ_AW_WARENSTROEME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZZ_AW_WARENSTROEME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZZ_AW_WARENSTROEME", calNewValueFormated);
	}
		public Long get_ID_ZZ_AW_WARENSTROEME_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ZZ_AW_WARENSTROEME").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ZZ_AW_WARENSTROEME_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ZZ_AW_WARENSTROEME").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ZZ_AW_WARENSTROEME_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ZZ_AW_WARENSTROEME").getDoubleValue();
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
	public String get_ID_ZZ_AW_WARENSTROEME_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZZ_AW_WARENSTROEME_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ZZ_AW_WARENSTROEME_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZZ_AW_WARENSTROEME_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ZZ_AW_WARENSTROEME_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZZ_AW_WARENSTROEME").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ZZ_AW_WARENSTROEME_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZZ_AW_WARENSTROEME").getBigDecimalValue();
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
		public String get_MENGENDIVISOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGENDIVISOR");
	}

	public String get_MENGENDIVISOR_cF() throws myException
	{
		return this.get_FormatedValue("MENGENDIVISOR");	
	}

	public String get_MENGENDIVISOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGENDIVISOR");
	}

	public String get_MENGENDIVISOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGENDIVISOR",cNotNullValue);
	}

	public String get_MENGENDIVISOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGENDIVISOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENDIVISOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENDIVISOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENDIVISOR", calNewValueFormated);
	}
		public Long get_MENGENDIVISOR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MENGENDIVISOR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MENGENDIVISOR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGENDIVISOR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGENDIVISOR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGENDIVISOR").getDoubleValue();
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
	public String get_MENGENDIVISOR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGENDIVISOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGENDIVISOR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGENDIVISOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGENDIVISOR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGENDIVISOR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGENDIVISOR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGENDIVISOR").getBigDecimalValue();
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
	
	
	public String get_MENGE_ABZUG_LAGERAUSGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_ABZUG_LAGERAUSGANG");
	}

	public String get_MENGE_ABZUG_LAGERAUSGANG_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_ABZUG_LAGERAUSGANG");	
	}

	public String get_MENGE_ABZUG_LAGERAUSGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_ABZUG_LAGERAUSGANG");
	}

	public String get_MENGE_ABZUG_LAGERAUSGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_ABZUG_LAGERAUSGANG",cNotNullValue);
	}

	public String get_MENGE_ABZUG_LAGERAUSGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_ABZUG_LAGERAUSGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGERAUSGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGERAUSGANG", calNewValueFormated);
	}
		public Double get_MENGE_ABZUG_LAGERAUSGANG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_ABZUG_LAGERAUSGANG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_ABZUG_LAGERAUSGANG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_ABZUG_LAGERAUSGANG").getDoubleValue();
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
	public String get_MENGE_ABZUG_LAGERAUSGANG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_ABZUG_LAGERAUSGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_ABZUG_LAGERAUSGANG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_ABZUG_LAGERAUSGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_ABZUG_LAGERAUSGANG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_ABZUG_LAGERAUSGANG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_ABZUG_LAGERAUSGANG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_ABZUG_LAGERAUSGANG").getBigDecimalValue();
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
	
	
	public String get_MENGE_ABZUG_LAGEREINGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_ABZUG_LAGEREINGANG");
	}

	public String get_MENGE_ABZUG_LAGEREINGANG_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_ABZUG_LAGEREINGANG");	
	}

	public String get_MENGE_ABZUG_LAGEREINGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_ABZUG_LAGEREINGANG");
	}

	public String get_MENGE_ABZUG_LAGEREINGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_ABZUG_LAGEREINGANG",cNotNullValue);
	}

	public String get_MENGE_ABZUG_LAGEREINGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_ABZUG_LAGEREINGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABZUG_LAGEREINGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABZUG_LAGEREINGANG", calNewValueFormated);
	}
		public Double get_MENGE_ABZUG_LAGEREINGANG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_ABZUG_LAGEREINGANG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_ABZUG_LAGEREINGANG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_ABZUG_LAGEREINGANG").getDoubleValue();
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
	public String get_MENGE_ABZUG_LAGEREINGANG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_ABZUG_LAGEREINGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_ABZUG_LAGEREINGANG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_ABZUG_LAGEREINGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_ABZUG_LAGEREINGANG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_ABZUG_LAGEREINGANG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_ABZUG_LAGEREINGANG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_ABZUG_LAGEREINGANG").getBigDecimalValue();
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
	
	
	public String get_MENGE_FUHRE_ABZUG_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_ABZUG_WA");
	}

	public String get_MENGE_FUHRE_ABZUG_WA_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_ABZUG_WA");	
	}

	public String get_MENGE_FUHRE_ABZUG_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_FUHRE_ABZUG_WA");
	}

	public String get_MENGE_FUHRE_ABZUG_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_ABZUG_WA",cNotNullValue);
	}

	public String get_MENGE_FUHRE_ABZUG_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_ABZUG_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_ABZUG_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WA", calNewValueFormated);
	}
		public Double get_MENGE_FUHRE_ABZUG_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_ABZUG_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_FUHRE_ABZUG_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_ABZUG_WA").getDoubleValue();
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
	public String get_MENGE_FUHRE_ABZUG_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_ABZUG_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_FUHRE_ABZUG_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_ABZUG_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_FUHRE_ABZUG_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_ABZUG_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_FUHRE_ABZUG_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_ABZUG_WA").getBigDecimalValue();
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
	
	
	public String get_MENGE_FUHRE_ABZUG_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_ABZUG_WE");
	}

	public String get_MENGE_FUHRE_ABZUG_WE_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_ABZUG_WE");	
	}

	public String get_MENGE_FUHRE_ABZUG_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_FUHRE_ABZUG_WE");
	}

	public String get_MENGE_FUHRE_ABZUG_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_ABZUG_WE",cNotNullValue);
	}

	public String get_MENGE_FUHRE_ABZUG_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_ABZUG_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_ABZUG_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_ABZUG_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_ABZUG_WE", calNewValueFormated);
	}
		public Double get_MENGE_FUHRE_ABZUG_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_ABZUG_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_FUHRE_ABZUG_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_ABZUG_WE").getDoubleValue();
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
	public String get_MENGE_FUHRE_ABZUG_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_ABZUG_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_FUHRE_ABZUG_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_ABZUG_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_FUHRE_ABZUG_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_ABZUG_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_FUHRE_ABZUG_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_ABZUG_WE").getBigDecimalValue();
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
	
	
	public String get_MENGE_FUHRE_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_WA");
	}

	public String get_MENGE_FUHRE_WA_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_WA");	
	}

	public String get_MENGE_FUHRE_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_FUHRE_WA");
	}

	public String get_MENGE_FUHRE_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_WA",cNotNullValue);
	}

	public String get_MENGE_FUHRE_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_FUHRE_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_FUHRE_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WA", calNewValueFormated);
	}
		public Double get_MENGE_FUHRE_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_FUHRE_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_WA").getDoubleValue();
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
	public String get_MENGE_FUHRE_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_FUHRE_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_FUHRE_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_FUHRE_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_WA").getBigDecimalValue();
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
	
	
	public String get_MENGE_FUHRE_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_WE");
	}

	public String get_MENGE_FUHRE_WE_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_WE");	
	}

	public String get_MENGE_FUHRE_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_FUHRE_WE");
	}

	public String get_MENGE_FUHRE_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_FUHRE_WE",cNotNullValue);
	}

	public String get_MENGE_FUHRE_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_FUHRE_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_FUHRE_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_FUHRE_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_FUHRE_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_FUHRE_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_FUHRE_WE", calNewValueFormated);
	}
		public Double get_MENGE_FUHRE_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_FUHRE_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_FUHRE_WE").getDoubleValue();
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
	public String get_MENGE_FUHRE_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_FUHRE_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_FUHRE_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_FUHRE_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_FUHRE_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_FUHRE_WE").getBigDecimalValue();
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
	
	
	public String get_MENGE_LAGERAUSGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_LAGERAUSGANG");
	}

	public String get_MENGE_LAGERAUSGANG_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_LAGERAUSGANG");	
	}

	public String get_MENGE_LAGERAUSGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_LAGERAUSGANG");
	}

	public String get_MENGE_LAGERAUSGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_LAGERAUSGANG",cNotNullValue);
	}

	public String get_MENGE_LAGERAUSGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_LAGERAUSGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_LAGERAUSGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_LAGERAUSGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_LAGERAUSGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGERAUSGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_LAGERAUSGANG", calNewValueFormated);
	}
		public Double get_MENGE_LAGERAUSGANG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_LAGERAUSGANG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_LAGERAUSGANG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_LAGERAUSGANG").getDoubleValue();
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
	public String get_MENGE_LAGERAUSGANG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_LAGERAUSGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_LAGERAUSGANG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_LAGERAUSGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_LAGERAUSGANG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_LAGERAUSGANG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_LAGERAUSGANG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_LAGERAUSGANG").getBigDecimalValue();
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
	
	
	public String get_MENGE_LAGEREINGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_LAGEREINGANG");
	}

	public String get_MENGE_LAGEREINGANG_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_LAGEREINGANG");	
	}

	public String get_MENGE_LAGEREINGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_LAGEREINGANG");
	}

	public String get_MENGE_LAGEREINGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_LAGEREINGANG",cNotNullValue);
	}

	public String get_MENGE_LAGEREINGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_LAGEREINGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_LAGEREINGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_LAGEREINGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_LAGEREINGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_LAGEREINGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_LAGEREINGANG", calNewValueFormated);
	}
		public Double get_MENGE_LAGEREINGANG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_LAGEREINGANG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_LAGEREINGANG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_LAGEREINGANG").getDoubleValue();
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
	public String get_MENGE_LAGEREINGANG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_LAGEREINGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_LAGEREINGANG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_LAGEREINGANG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_LAGEREINGANG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_LAGEREINGANG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_LAGEREINGANG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_LAGEREINGANG").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WA");
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WA_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WA");	
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_ABZUG_LAG_REL_WA");
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WA",cNotNullValue);
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WA", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_ABZUG_LAG_REL_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_ABZUG_LAG_REL_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WA").getDoubleValue();
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
	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_LAG_REL_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_LAG_REL_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_ABZUG_LAG_REL_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_ABZUG_LAG_REL_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WA").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WE");
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WE_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WE");	
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_ABZUG_LAG_REL_WE");
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WE",cNotNullValue);
	}

	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_LAG_REL_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_LAG_REL_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_LAG_REL_WE", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_ABZUG_LAG_REL_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_ABZUG_LAG_REL_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WE").getDoubleValue();
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
	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_LAG_REL_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_ABZUG_LAG_REL_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_LAG_REL_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_ABZUG_LAG_REL_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_ABZUG_LAG_REL_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_LAG_REL_WE").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_ABZUG_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_WA");
	}

	public String get_MENGE_RG_POS_ABZUG_WA_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_WA");	
	}

	public String get_MENGE_RG_POS_ABZUG_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_ABZUG_WA");
	}

	public String get_MENGE_RG_POS_ABZUG_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_WA",cNotNullValue);
	}

	public String get_MENGE_RG_POS_ABZUG_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WA", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_ABZUG_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_ABZUG_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_WA").getDoubleValue();
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
	public String get_MENGE_RG_POS_ABZUG_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_ABZUG_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_ABZUG_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_ABZUG_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_WA").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_ABZUG_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_WE");
	}

	public String get_MENGE_RG_POS_ABZUG_WE_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_WE");	
	}

	public String get_MENGE_RG_POS_ABZUG_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_ABZUG_WE");
	}

	public String get_MENGE_RG_POS_ABZUG_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_ABZUG_WE",cNotNullValue);
	}

	public String get_MENGE_RG_POS_ABZUG_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_ABZUG_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_ABZUG_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_ABZUG_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_ABZUG_WE", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_ABZUG_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_ABZUG_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_ABZUG_WE").getDoubleValue();
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
	public String get_MENGE_RG_POS_ABZUG_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_ABZUG_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_ABZUG_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_ABZUG_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_ABZUG_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_ABZUG_WE").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_NETTO_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_NETTO_WA");
	}

	public String get_MENGE_RG_POS_NETTO_WA_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_NETTO_WA");	
	}

	public String get_MENGE_RG_POS_NETTO_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_NETTO_WA");
	}

	public String get_MENGE_RG_POS_NETTO_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_NETTO_WA",cNotNullValue);
	}

	public String get_MENGE_RG_POS_NETTO_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_NETTO_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_NETTO_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WA", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_NETTO_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_NETTO_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_NETTO_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_NETTO_WA").getDoubleValue();
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
	public String get_MENGE_RG_POS_NETTO_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_NETTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_NETTO_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_NETTO_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_NETTO_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_NETTO_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_NETTO_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_NETTO_WA").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_NETTO_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_NETTO_WE");
	}

	public String get_MENGE_RG_POS_NETTO_WE_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_NETTO_WE");	
	}

	public String get_MENGE_RG_POS_NETTO_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_NETTO_WE");
	}

	public String get_MENGE_RG_POS_NETTO_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_NETTO_WE",cNotNullValue);
	}

	public String get_MENGE_RG_POS_NETTO_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_NETTO_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_NETTO_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_NETTO_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_NETTO_WE", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_NETTO_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_NETTO_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_NETTO_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_NETTO_WE").getDoubleValue();
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
	public String get_MENGE_RG_POS_NETTO_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_NETTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_NETTO_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_NETTO_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_NETTO_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_NETTO_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_NETTO_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_NETTO_WE").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_WA");
	}

	public String get_MENGE_RG_POS_WA_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_WA");	
	}

	public String get_MENGE_RG_POS_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_WA");
	}

	public String get_MENGE_RG_POS_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_WA",cNotNullValue);
	}

	public String get_MENGE_RG_POS_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WA", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_WA").getDoubleValue();
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
	public String get_MENGE_RG_POS_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_WA").getBigDecimalValue();
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
	
	
	public String get_MENGE_RG_POS_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_WE");
	}

	public String get_MENGE_RG_POS_WE_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_WE");	
	}

	public String get_MENGE_RG_POS_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_RG_POS_WE");
	}

	public String get_MENGE_RG_POS_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_RG_POS_WE",cNotNullValue);
	}

	public String get_MENGE_RG_POS_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_RG_POS_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_RG_POS_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_RG_POS_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_RG_POS_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_RG_POS_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_RG_POS_WE", calNewValueFormated);
	}
		public Double get_MENGE_RG_POS_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_RG_POS_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_RG_POS_WE").getDoubleValue();
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
	public String get_MENGE_RG_POS_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_RG_POS_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_RG_POS_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_RG_POS_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_RG_POS_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_RG_POS_WE").getBigDecimalValue();
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
	
	
	public String get_REPORTNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("REPORTNUMMER");
	}

	public String get_REPORTNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("REPORTNUMMER");	
	}

	public String get_REPORTNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("REPORTNUMMER");
	}

	public String get_REPORTNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("REPORTNUMMER",cNotNullValue);
	}

	public String get_REPORTNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("REPORTNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("REPORTNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_REPORTNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("REPORTNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("REPORTNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTNUMMER", calNewValueFormated);
	}
		public Long get_REPORTNUMMER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("REPORTNUMMER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_REPORTNUMMER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("REPORTNUMMER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_REPORTNUMMER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("REPORTNUMMER").getDoubleValue();
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
	public String get_REPORTNUMMER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_REPORTNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_REPORTNUMMER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_REPORTNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_REPORTNUMMER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("REPORTNUMMER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_REPORTNUMMER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("REPORTNUMMER").getBigDecimalValue();
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
	put("BENUTZERKUERZEL",MyRECORD.DATATYPES.TEXT);
	put("EPREIS_BRUTTO_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EPREIS_BRUTTO_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EPREIS_NETTO_MARGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EPREIS_NETTO_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EPREIS_NETTO_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GESAMTPREIS_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GESAMTPREIS_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GPREIS_ABZ_AUF_NETTOMGE_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GPREIS_ABZ_AUF_NETTOMGE_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GPREIS_ABZ_MGE_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GPREIS_ABZ_MGE_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GPREIS_NETTO_MARGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GPREIS_NETTO_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GPREIS_NETTO_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ID_ADRESSE_LAGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_EINHEIT",MyRECORD.DATATYPES.NUMBER);
	put("ID_EINHEIT_PREIS",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZZ_AW_WARENSTROEME",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MENGENDIVISOR",MyRECORD.DATATYPES.NUMBER);
	put("MENGE_ABZUG_LAGERAUSGANG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_ABZUG_LAGEREINGANG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_FUHRE_ABZUG_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_FUHRE_ABZUG_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_FUHRE_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_FUHRE_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_LAGERAUSGANG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_LAGEREINGANG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_ABZUG_LAG_REL_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_ABZUG_LAG_REL_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_ABZUG_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_ABZUG_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_NETTO_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_NETTO_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_RG_POS_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("REPORTNUMMER",MyRECORD.DATATYPES.NUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ZZ_AW_WARENSTROEME.HM_FIELDS;	
	}

}
