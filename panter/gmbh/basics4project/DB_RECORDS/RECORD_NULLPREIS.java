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

public class RECORD_NULLPREIS extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_NULLPREIS";
    public static String IDFIELD   = "ID_NULLPREIS";
    

	//erzeugen eines RECORDNEW_JT_NULLPREIS - felds
	private RECORDNEW_NULLPREIS   recNEW = null;

    private _TAB  tab = _TAB.nullpreis;  



	public RECORD_NULLPREIS() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_NULLPREIS");
	}


	public RECORD_NULLPREIS(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_NULLPREIS");
	}



	public RECORD_NULLPREIS(RECORD_NULLPREIS recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_NULLPREIS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_NULLPREIS.TABLENAME);
	}


	//2014-04-03
	public RECORD_NULLPREIS(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_NULLPREIS");
		this.set_Tablename_To_FieldMetaDefs(RECORD_NULLPREIS.TABLENAME);
	}




	public RECORD_NULLPREIS(long lID_Unformated) throws myException
	{
		super("JT_NULLPREIS","ID_NULLPREIS",""+lID_Unformated);
		this.set_TABLE_NAME("JT_NULLPREIS");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_NULLPREIS.TABLENAME);
	}

	public RECORD_NULLPREIS(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_NULLPREIS");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_NULLPREIS", "ID_NULLPREIS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_NULLPREIS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_NULLPREIS.TABLENAME);
	}
	
	

	public RECORD_NULLPREIS(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_NULLPREIS","ID_NULLPREIS",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_NULLPREIS");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_NULLPREIS.TABLENAME);

	}


	public RECORD_NULLPREIS(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_NULLPREIS");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_NULLPREIS", "ID_NULLPREIS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_NULLPREIS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_NULLPREIS.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_NULLPREIS();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_NULLPREIS.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_NULLPREIS";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_NULLPREIS_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_NULLPREIS_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_NULLPREIS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_NULLPREIS", bibE2.cTO(), "ID_NULLPREIS="+this.get_ID_NULLPREIS_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_NULLPREIS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_NULLPREIS", bibE2.cTO(), "ID_NULLPREIS="+this.get_ID_NULLPREIS_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_NULLPREIS WHERE ID_NULLPREIS="+this.get_ID_NULLPREIS_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_NULLPREIS WHERE ID_NULLPREIS="+this.get_ID_NULLPREIS_cUF();
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
			if (S.isFull(this.get_ID_NULLPREIS_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_NULLPREIS", "ID_NULLPREIS="+this.get_ID_NULLPREIS_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_NULLPREIS get_RECORDNEW_NULLPREIS() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_NULLPREIS();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_NULLPREIS(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_NULLPREIS create_Instance() throws myException {
		return new RECORD_NULLPREIS();
	}
	
	public static RECORD_NULLPREIS create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_NULLPREIS(Conn);
    }

	public static RECORD_NULLPREIS create_Instance(RECORD_NULLPREIS recordOrig) {
		return new RECORD_NULLPREIS(recordOrig);
    }

	public static RECORD_NULLPREIS create_Instance(long lID_Unformated) throws myException {
		return new RECORD_NULLPREIS(lID_Unformated);
    }

	public static RECORD_NULLPREIS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_NULLPREIS(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_NULLPREIS create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_NULLPREIS(lID_Unformated, Conn);
	}

	public static RECORD_NULLPREIS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_NULLPREIS(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_NULLPREIS create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_NULLPREIS(recordOrig);	    
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
    public RECORD_NULLPREIS build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_NULLPREIS WHERE ID_NULLPREIS="+this.get_ID_NULLPREIS_cUF());
      }
      //return new RECORD_NULLPREIS(this.get_cSQL_4_Build());
      RECORD_NULLPREIS rec = new RECORD_NULLPREIS();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_NULLPREIS
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_NULLPREIS-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_NULLPREIS get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_NULLPREIS_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_NULLPREIS  recNew = new RECORDNEW_NULLPREIS();
		
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
    public RECORD_NULLPREIS set_recordNew(RECORDNEW_NULLPREIS recnew) throws myException {
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
	
	

	public static String FIELD__ERLEDIGT = "ERLEDIGT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERTVON = "GEAENDERTVON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_NULLPREIS = "ID_NULLPREIS";
	public static String FIELD__KONTRAKT_NR = "KONTRAKT_NR";
	public static String FIELD__LETZTEAENDERUNG = "LETZTEAENDERUNG";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__NP_BEZUG = "NP_BEZUG";
	public static String FIELD__NP_BEZUG2 = "NP_BEZUG2";
	public static String FIELD__NP_CA_MENGE = "NP_CA_MENGE";
	public static String FIELD__NP_LFRBED_TEXT = "NP_LFRBED_TEXT";
	public static String FIELD__NP_LIEFERADRESSE = "NP_LIEFERADRESSE";
	public static String FIELD__NP_LIEFERORT = "NP_LIEFERORT";
	public static String FIELD__NP_PREIS = "NP_PREIS";
	public static String FIELD__OLD_BEZUG = "OLD_BEZUG";
	public static String FIELD__OLD_BEZUG2 = "OLD_BEZUG2";
	public static String FIELD__OLD_CA_MENGE = "OLD_CA_MENGE";
	public static String FIELD__OLD_LFRBED_TEXT = "OLD_LFRBED_TEXT";
	public static String FIELD__OLD_LIEFERADRESSE = "OLD_LIEFERADRESSE";
	public static String FIELD__OLD_LIEFERORT = "OLD_LIEFERORT";
	public static String FIELD__OLD_PREIS = "OLD_PREIS";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TYP = "TYP";
	public static String FIELD__ZUSATZINFO1 = "ZUSATZINFO1";
	public static String FIELD__ZUSATZINFO2 = "ZUSATZINFO2";
	public static String FIELD__ZUSATZINFO3 = "ZUSATZINFO3";
	public static String FIELD__ZUSATZINFO4 = "ZUSATZINFO4";
	public static String FIELD__ZUSATZINFO5 = "ZUSATZINFO5";


	public String get_ERLEDIGT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERLEDIGT");
	}

	public String get_ERLEDIGT_cF() throws myException
	{
		return this.get_FormatedValue("ERLEDIGT");	
	}

	public String get_ERLEDIGT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERLEDIGT");
	}

	public String get_ERLEDIGT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERLEDIGT",cNotNullValue);
	}

	public String get_ERLEDIGT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERLEDIGT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERLEDIGT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERLEDIGT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERLEDIGT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERLEDIGT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLEDIGT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLEDIGT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLEDIGT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLEDIGT", calNewValueFormated);
	}
		public boolean is_ERLEDIGT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLEDIGT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ERLEDIGT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLEDIGT_cUF_NN("N").equals("N"))
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
		public String get_GEAENDERTVON_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEAENDERTVON");
	}

	public String get_GEAENDERTVON_cF() throws myException
	{
		return this.get_FormatedValue("GEAENDERTVON");	
	}

	public String get_GEAENDERTVON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEAENDERTVON");
	}

	public String get_GEAENDERTVON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEAENDERTVON",cNotNullValue);
	}

	public String get_GEAENDERTVON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEAENDERTVON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEAENDERTVON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEAENDERTVON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEAENDERTVON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEAENDERTVON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEAENDERTVON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEAENDERTVON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERTVON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEAENDERTVON", calNewValueFormated);
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
	
	
	public String get_ID_NULLPREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_NULLPREIS");
	}

	public String get_ID_NULLPREIS_cF() throws myException
	{
		return this.get_FormatedValue("ID_NULLPREIS");	
	}

	public String get_ID_NULLPREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_NULLPREIS");
	}

	public String get_ID_NULLPREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_NULLPREIS",cNotNullValue);
	}

	public String get_ID_NULLPREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_NULLPREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_NULLPREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_NULLPREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_NULLPREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_NULLPREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_NULLPREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_NULLPREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_NULLPREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_NULLPREIS", calNewValueFormated);
	}
		public Long get_ID_NULLPREIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_NULLPREIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_NULLPREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_NULLPREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_NULLPREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_NULLPREIS").getDoubleValue();
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
	public String get_ID_NULLPREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_NULLPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_NULLPREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_NULLPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_NULLPREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_NULLPREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_NULLPREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_NULLPREIS").getBigDecimalValue();
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
	
	
	public String get_LETZTEAENDERUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("LETZTEAENDERUNG");
	}

	public String get_LETZTEAENDERUNG_cF() throws myException
	{
		return this.get_FormatedValue("LETZTEAENDERUNG");	
	}

	public String get_LETZTEAENDERUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LETZTEAENDERUNG");
	}

	public String get_LETZTEAENDERUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LETZTEAENDERUNG",cNotNullValue);
	}

	public String get_LETZTEAENDERUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LETZTEAENDERUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LETZTEAENDERUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LETZTEAENDERUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LETZTEAENDERUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTEAENDERUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTEAENDERUNG", calNewValueFormated);
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
		public String get_NP_BEZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("NP_BEZUG");
	}

	public String get_NP_BEZUG_cF() throws myException
	{
		return this.get_FormatedValue("NP_BEZUG");	
	}

	public String get_NP_BEZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NP_BEZUG");
	}

	public String get_NP_BEZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NP_BEZUG",cNotNullValue);
	}

	public String get_NP_BEZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NP_BEZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NP_BEZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NP_BEZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NP_BEZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NP_BEZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_BEZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_BEZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_BEZUG", calNewValueFormated);
	}
		public String get_NP_BEZUG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("NP_BEZUG2");
	}

	public String get_NP_BEZUG2_cF() throws myException
	{
		return this.get_FormatedValue("NP_BEZUG2");	
	}

	public String get_NP_BEZUG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NP_BEZUG2");
	}

	public String get_NP_BEZUG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NP_BEZUG2",cNotNullValue);
	}

	public String get_NP_BEZUG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NP_BEZUG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NP_BEZUG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NP_BEZUG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NP_BEZUG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NP_BEZUG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_BEZUG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_BEZUG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_BEZUG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_BEZUG2", calNewValueFormated);
	}
		public String get_NP_CA_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("NP_CA_MENGE");
	}

	public String get_NP_CA_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("NP_CA_MENGE");	
	}

	public String get_NP_CA_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NP_CA_MENGE");
	}

	public String get_NP_CA_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NP_CA_MENGE",cNotNullValue);
	}

	public String get_NP_CA_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NP_CA_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NP_CA_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NP_CA_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NP_CA_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NP_CA_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_CA_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_CA_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_CA_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_CA_MENGE", calNewValueFormated);
	}
		public Long get_NP_CA_MENGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("NP_CA_MENGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_NP_CA_MENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NP_CA_MENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NP_CA_MENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NP_CA_MENGE").getDoubleValue();
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
	public String get_NP_CA_MENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NP_CA_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NP_CA_MENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NP_CA_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NP_CA_MENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NP_CA_MENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NP_CA_MENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NP_CA_MENGE").getBigDecimalValue();
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
	
	
	public String get_NP_LFRBED_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("NP_LFRBED_TEXT");
	}

	public String get_NP_LFRBED_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("NP_LFRBED_TEXT");	
	}

	public String get_NP_LFRBED_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NP_LFRBED_TEXT");
	}

	public String get_NP_LFRBED_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NP_LFRBED_TEXT",cNotNullValue);
	}

	public String get_NP_LFRBED_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NP_LFRBED_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NP_LFRBED_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NP_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NP_LFRBED_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LFRBED_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LFRBED_TEXT", calNewValueFormated);
	}
		public String get_NP_LIEFERADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("NP_LIEFERADRESSE");
	}

	public String get_NP_LIEFERADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("NP_LIEFERADRESSE");	
	}

	public String get_NP_LIEFERADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NP_LIEFERADRESSE");
	}

	public String get_NP_LIEFERADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NP_LIEFERADRESSE",cNotNullValue);
	}

	public String get_NP_LIEFERADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NP_LIEFERADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NP_LIEFERADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NP_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NP_LIEFERADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LIEFERADRESSE", calNewValueFormated);
	}
		public String get_NP_LIEFERORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("NP_LIEFERORT");
	}

	public String get_NP_LIEFERORT_cF() throws myException
	{
		return this.get_FormatedValue("NP_LIEFERORT");	
	}

	public String get_NP_LIEFERORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NP_LIEFERORT");
	}

	public String get_NP_LIEFERORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NP_LIEFERORT",cNotNullValue);
	}

	public String get_NP_LIEFERORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NP_LIEFERORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NP_LIEFERORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NP_LIEFERORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NP_LIEFERORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NP_LIEFERORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LIEFERORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LIEFERORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_LIEFERORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_LIEFERORT", calNewValueFormated);
	}
		public String get_NP_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("NP_PREIS");
	}

	public String get_NP_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("NP_PREIS");	
	}

	public String get_NP_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NP_PREIS");
	}

	public String get_NP_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NP_PREIS",cNotNullValue);
	}

	public String get_NP_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NP_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NP_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NP_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NP_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NP_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NP_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NP_PREIS", calNewValueFormated);
	}
		public Double get_NP_PREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NP_PREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NP_PREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NP_PREIS").getDoubleValue();
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
	public String get_NP_PREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NP_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NP_PREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NP_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NP_PREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NP_PREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NP_PREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NP_PREIS").getBigDecimalValue();
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
	
	
	public String get_OLD_BEZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("OLD_BEZUG");
	}

	public String get_OLD_BEZUG_cF() throws myException
	{
		return this.get_FormatedValue("OLD_BEZUG");	
	}

	public String get_OLD_BEZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OLD_BEZUG");
	}

	public String get_OLD_BEZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OLD_BEZUG",cNotNullValue);
	}

	public String get_OLD_BEZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OLD_BEZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OLD_BEZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OLD_BEZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OLD_BEZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OLD_BEZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_BEZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_BEZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_BEZUG", calNewValueFormated);
	}
		public String get_OLD_BEZUG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("OLD_BEZUG2");
	}

	public String get_OLD_BEZUG2_cF() throws myException
	{
		return this.get_FormatedValue("OLD_BEZUG2");	
	}

	public String get_OLD_BEZUG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OLD_BEZUG2");
	}

	public String get_OLD_BEZUG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OLD_BEZUG2",cNotNullValue);
	}

	public String get_OLD_BEZUG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OLD_BEZUG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OLD_BEZUG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OLD_BEZUG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OLD_BEZUG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OLD_BEZUG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_BEZUG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_BEZUG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_BEZUG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_BEZUG2", calNewValueFormated);
	}
		public String get_OLD_CA_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("OLD_CA_MENGE");
	}

	public String get_OLD_CA_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("OLD_CA_MENGE");	
	}

	public String get_OLD_CA_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OLD_CA_MENGE");
	}

	public String get_OLD_CA_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OLD_CA_MENGE",cNotNullValue);
	}

	public String get_OLD_CA_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OLD_CA_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OLD_CA_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OLD_CA_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OLD_CA_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_CA_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_CA_MENGE", calNewValueFormated);
	}
		public Long get_OLD_CA_MENGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("OLD_CA_MENGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_OLD_CA_MENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("OLD_CA_MENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_OLD_CA_MENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("OLD_CA_MENGE").getDoubleValue();
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
	public String get_OLD_CA_MENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OLD_CA_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_OLD_CA_MENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OLD_CA_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_OLD_CA_MENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("OLD_CA_MENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_OLD_CA_MENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("OLD_CA_MENGE").getBigDecimalValue();
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
	
	
	public String get_OLD_LFRBED_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("OLD_LFRBED_TEXT");
	}

	public String get_OLD_LFRBED_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("OLD_LFRBED_TEXT");	
	}

	public String get_OLD_LFRBED_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OLD_LFRBED_TEXT");
	}

	public String get_OLD_LFRBED_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OLD_LFRBED_TEXT",cNotNullValue);
	}

	public String get_OLD_LFRBED_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OLD_LFRBED_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OLD_LFRBED_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OLD_LFRBED_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OLD_LFRBED_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LFRBED_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LFRBED_TEXT", calNewValueFormated);
	}
		public String get_OLD_LIEFERADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("OLD_LIEFERADRESSE");
	}

	public String get_OLD_LIEFERADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("OLD_LIEFERADRESSE");	
	}

	public String get_OLD_LIEFERADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OLD_LIEFERADRESSE");
	}

	public String get_OLD_LIEFERADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OLD_LIEFERADRESSE",cNotNullValue);
	}

	public String get_OLD_LIEFERADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OLD_LIEFERADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OLD_LIEFERADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OLD_LIEFERADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OLD_LIEFERADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LIEFERADRESSE", calNewValueFormated);
	}
		public String get_OLD_LIEFERORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("OLD_LIEFERORT");
	}

	public String get_OLD_LIEFERORT_cF() throws myException
	{
		return this.get_FormatedValue("OLD_LIEFERORT");	
	}

	public String get_OLD_LIEFERORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OLD_LIEFERORT");
	}

	public String get_OLD_LIEFERORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OLD_LIEFERORT",cNotNullValue);
	}

	public String get_OLD_LIEFERORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OLD_LIEFERORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OLD_LIEFERORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OLD_LIEFERORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OLD_LIEFERORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_LIEFERORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_LIEFERORT", calNewValueFormated);
	}
		public String get_OLD_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("OLD_PREIS");
	}

	public String get_OLD_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("OLD_PREIS");	
	}

	public String get_OLD_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OLD_PREIS");
	}

	public String get_OLD_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OLD_PREIS",cNotNullValue);
	}

	public String get_OLD_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OLD_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OLD_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OLD_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OLD_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OLD_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OLD_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OLD_PREIS", calNewValueFormated);
	}
		public Double get_OLD_PREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("OLD_PREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_OLD_PREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("OLD_PREIS").getDoubleValue();
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
	public String get_OLD_PREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OLD_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_OLD_PREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_OLD_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_OLD_PREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("OLD_PREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_OLD_PREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("OLD_PREIS").getBigDecimalValue();
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
	
	
	public String get_TYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYP");
	}

	public String get_TYP_cF() throws myException
	{
		return this.get_FormatedValue("TYP");	
	}

	public String get_TYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYP");
	}

	public String get_TYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYP",cNotNullValue);
	}

	public String get_TYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP", calNewValueFormated);
	}
		public boolean is_TYP_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TYP_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TYP_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TYP_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZUSATZINFO1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO1");
	}

	public String get_ZUSATZINFO1_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO1");	
	}

	public String get_ZUSATZINFO1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZINFO1");
	}

	public String get_ZUSATZINFO1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO1",cNotNullValue);
	}

	public String get_ZUSATZINFO1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZINFO1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZINFO1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZINFO1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO1", calNewValueFormated);
	}
		public String get_ZUSATZINFO2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO2");
	}

	public String get_ZUSATZINFO2_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO2");	
	}

	public String get_ZUSATZINFO2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZINFO2");
	}

	public String get_ZUSATZINFO2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO2",cNotNullValue);
	}

	public String get_ZUSATZINFO2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZINFO2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZINFO2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZINFO2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO2", calNewValueFormated);
	}
		public String get_ZUSATZINFO3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO3");
	}

	public String get_ZUSATZINFO3_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO3");	
	}

	public String get_ZUSATZINFO3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZINFO3");
	}

	public String get_ZUSATZINFO3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO3",cNotNullValue);
	}

	public String get_ZUSATZINFO3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZINFO3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZINFO3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZINFO3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO3", calNewValueFormated);
	}
		public String get_ZUSATZINFO4_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO4");
	}

	public String get_ZUSATZINFO4_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO4");	
	}

	public String get_ZUSATZINFO4_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZINFO4");
	}

	public String get_ZUSATZINFO4_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO4",cNotNullValue);
	}

	public String get_ZUSATZINFO4_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO4",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZINFO4", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO4(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZINFO4", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZINFO4", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO4", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO4", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO4_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO4", calNewValueFormated);
	}
		public String get_ZUSATZINFO5_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO5");
	}

	public String get_ZUSATZINFO5_cF() throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO5");	
	}

	public String get_ZUSATZINFO5_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZUSATZINFO5");
	}

	public String get_ZUSATZINFO5_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZUSATZINFO5",cNotNullValue);
	}

	public String get_ZUSATZINFO5_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZUSATZINFO5",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZUSATZINFO5", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZUSATZINFO5(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZUSATZINFO5", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZUSATZINFO5", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO5", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO5", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZUSATZINFO5_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZUSATZINFO5", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ERLEDIGT",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERTVON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_NULLPREIS",MyRECORD.DATATYPES.NUMBER);
	put("KONTRAKT_NR",MyRECORD.DATATYPES.NUMBER);
	put("LETZTEAENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("NP_BEZUG",MyRECORD.DATATYPES.TEXT);
	put("NP_BEZUG2",MyRECORD.DATATYPES.TEXT);
	put("NP_CA_MENGE",MyRECORD.DATATYPES.NUMBER);
	put("NP_LFRBED_TEXT",MyRECORD.DATATYPES.TEXT);
	put("NP_LIEFERADRESSE",MyRECORD.DATATYPES.TEXT);
	put("NP_LIEFERORT",MyRECORD.DATATYPES.TEXT);
	put("NP_PREIS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("OLD_BEZUG",MyRECORD.DATATYPES.TEXT);
	put("OLD_BEZUG2",MyRECORD.DATATYPES.TEXT);
	put("OLD_CA_MENGE",MyRECORD.DATATYPES.NUMBER);
	put("OLD_LFRBED_TEXT",MyRECORD.DATATYPES.TEXT);
	put("OLD_LIEFERADRESSE",MyRECORD.DATATYPES.TEXT);
	put("OLD_LIEFERORT",MyRECORD.DATATYPES.TEXT);
	put("OLD_PREIS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TYP",MyRECORD.DATATYPES.YESNO);
	put("ZUSATZINFO1",MyRECORD.DATATYPES.TEXT);
	put("ZUSATZINFO2",MyRECORD.DATATYPES.TEXT);
	put("ZUSATZINFO3",MyRECORD.DATATYPES.TEXT);
	put("ZUSATZINFO4",MyRECORD.DATATYPES.TEXT);
	put("ZUSATZINFO5",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_NULLPREIS.HM_FIELDS;	
	}

}
