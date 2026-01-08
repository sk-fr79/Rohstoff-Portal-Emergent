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

public class RECORD_MAIL_AUS_MASK_JASPER extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_MAIL_AUS_MASK_JASPER";
    public static String IDFIELD   = "ID_MAIL_AUS_MASK_JASPER";
    

	//erzeugen eines RECORDNEW_JT_MAIL_AUS_MASK_JASPER - felds
	private RECORDNEW_MAIL_AUS_MASK_JASPER   recNEW = null;

    private _TAB  tab = _TAB.mail_aus_mask_jasper;  



	public RECORD_MAIL_AUS_MASK_JASPER() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");
	}


	public RECORD_MAIL_AUS_MASK_JASPER(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");
	}



	public RECORD_MAIL_AUS_MASK_JASPER(RECORD_MAIL_AUS_MASK_JASPER recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK_JASPER.TABLENAME);
	}


	//2014-04-03
	public RECORD_MAIL_AUS_MASK_JASPER(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK_JASPER.TABLENAME);
	}




	public RECORD_MAIL_AUS_MASK_JASPER(long lID_Unformated) throws myException
	{
		super("JT_MAIL_AUS_MASK_JASPER","ID_MAIL_AUS_MASK_JASPER",""+lID_Unformated);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK_JASPER.TABLENAME);
	}

	public RECORD_MAIL_AUS_MASK_JASPER(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER", "ID_MAIL_AUS_MASK_JASPER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK_JASPER.TABLENAME);
	}
	
	

	public RECORD_MAIL_AUS_MASK_JASPER(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_MAIL_AUS_MASK_JASPER","ID_MAIL_AUS_MASK_JASPER",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK_JASPER.TABLENAME);

	}


	public RECORD_MAIL_AUS_MASK_JASPER(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK_JASPER");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER", "ID_MAIL_AUS_MASK_JASPER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK_JASPER.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_MAIL_AUS_MASK_JASPER();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_MAIL_AUS_MASK_JASPER.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_MAIL_AUS_MASK_JASPER";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_MAIL_AUS_MASK_JASPER_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_MAIL_AUS_MASK_JASPER_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MAIL_AUS_MASK_JASPER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MAIL_AUS_MASK_JASPER", bibE2.cTO(), "ID_MAIL_AUS_MASK_JASPER="+this.get_ID_MAIL_AUS_MASK_JASPER_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MAIL_AUS_MASK_JASPER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MAIL_AUS_MASK_JASPER", bibE2.cTO(), "ID_MAIL_AUS_MASK_JASPER="+this.get_ID_MAIL_AUS_MASK_JASPER_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER WHERE ID_MAIL_AUS_MASK_JASPER="+this.get_ID_MAIL_AUS_MASK_JASPER_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER WHERE ID_MAIL_AUS_MASK_JASPER="+this.get_ID_MAIL_AUS_MASK_JASPER_cUF();
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
			if (S.isFull(this.get_ID_MAIL_AUS_MASK_JASPER_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER", "ID_MAIL_AUS_MASK_JASPER="+this.get_ID_MAIL_AUS_MASK_JASPER_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_MAIL_AUS_MASK_JASPER get_RECORDNEW_MAIL_AUS_MASK_JASPER() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_MAIL_AUS_MASK_JASPER();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_MAIL_AUS_MASK_JASPER(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance() throws myException {
		return new RECORD_MAIL_AUS_MASK_JASPER();
	}
	
	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_MAIL_AUS_MASK_JASPER(Conn);
    }

	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance(RECORD_MAIL_AUS_MASK_JASPER recordOrig) {
		return new RECORD_MAIL_AUS_MASK_JASPER(recordOrig);
    }

	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance(long lID_Unformated) throws myException {
		return new RECORD_MAIL_AUS_MASK_JASPER(lID_Unformated);
    }

	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_MAIL_AUS_MASK_JASPER(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_MAIL_AUS_MASK_JASPER(lID_Unformated, Conn);
	}

	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_MAIL_AUS_MASK_JASPER(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_MAIL_AUS_MASK_JASPER create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_MAIL_AUS_MASK_JASPER(recordOrig);	    
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
    public RECORD_MAIL_AUS_MASK_JASPER build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER WHERE ID_MAIL_AUS_MASK_JASPER="+this.get_ID_MAIL_AUS_MASK_JASPER_cUF());
      }
      //return new RECORD_MAIL_AUS_MASK_JASPER(this.get_cSQL_4_Build());
      RECORD_MAIL_AUS_MASK_JASPER rec = new RECORD_MAIL_AUS_MASK_JASPER();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_MAIL_AUS_MASK_JASPER
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_MAIL_AUS_MASK_JASPER-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_MAIL_AUS_MASK_JASPER get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_MAIL_AUS_MASK_JASPER_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_MAIL_AUS_MASK_JASPER  recNew = new RECORDNEW_MAIL_AUS_MASK_JASPER();
		
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
    public RECORD_MAIL_AUS_MASK_JASPER set_recordNew(RECORDNEW_MAIL_AUS_MASK_JASPER recnew) throws myException {
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
	
	



		private RECORD_MAIL_AUS_MASK UP_RECORD_MAIL_AUS_MASK_id_mail_aus_mask = null;






	
	/**
	 * References the Field ID_MAIL_AUS_MASK
	 * Falls keine this.get_ID_MAIL_AUS_MASK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MAIL_AUS_MASK get_UP_RECORD_MAIL_AUS_MASK_id_mail_aus_mask() throws myException
	{
		if (S.isEmpty(this.get_ID_MAIL_AUS_MASK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MAIL_AUS_MASK_id_mail_aus_mask==null)
		{
			this.UP_RECORD_MAIL_AUS_MASK_id_mail_aus_mask = new RECORD_MAIL_AUS_MASK(this.get_ID_MAIL_AUS_MASK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MAIL_AUS_MASK_id_mail_aus_mask;
	}
	

	public static String FIELD__DOWNLOADNAME = "DOWNLOADNAME";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MAIL_AUS_MASK = "ID_MAIL_AUS_MASK";
	public static String FIELD__ID_MAIL_AUS_MASK_JASPER = "ID_MAIL_AUS_MASK_JASPER";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__PARAMETER01 = "PARAMETER01";
	public static String FIELD__PARAMETER02 = "PARAMETER02";
	public static String FIELD__PARAMETER03 = "PARAMETER03";
	public static String FIELD__PARAMETER04 = "PARAMETER04";
	public static String FIELD__PARAMETER05 = "PARAMETER05";
	public static String FIELD__PARAMETER06 = "PARAMETER06";
	public static String FIELD__PARAMETER07 = "PARAMETER07";
	public static String FIELD__PARAMETER08 = "PARAMETER08";
	public static String FIELD__PARAMETER09 = "PARAMETER09";
	public static String FIELD__PARAMETER10 = "PARAMETER10";
	public static String FIELD__PARAMETER11 = "PARAMETER11";
	public static String FIELD__PARAMETER12 = "PARAMETER12";
	public static String FIELD__PARAMETER13 = "PARAMETER13";
	public static String FIELD__PARAMETER14 = "PARAMETER14";
	public static String FIELD__PARAMETER15 = "PARAMETER15";
	public static String FIELD__PARAMETER16 = "PARAMETER16";
	public static String FIELD__PARAMETER17 = "PARAMETER17";
	public static String FIELD__PARAMETER18 = "PARAMETER18";
	public static String FIELD__PARAMETER19 = "PARAMETER19";
	public static String FIELD__PARAMETER20 = "PARAMETER20";
	public static String FIELD__REPORTNAME = "REPORTNAME";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__WERT01 = "WERT01";
	public static String FIELD__WERT02 = "WERT02";
	public static String FIELD__WERT03 = "WERT03";
	public static String FIELD__WERT04 = "WERT04";
	public static String FIELD__WERT05 = "WERT05";
	public static String FIELD__WERT06 = "WERT06";
	public static String FIELD__WERT07 = "WERT07";
	public static String FIELD__WERT08 = "WERT08";
	public static String FIELD__WERT09 = "WERT09";
	public static String FIELD__WERT10 = "WERT10";
	public static String FIELD__WERT11 = "WERT11";
	public static String FIELD__WERT12 = "WERT12";
	public static String FIELD__WERT13 = "WERT13";
	public static String FIELD__WERT14 = "WERT14";
	public static String FIELD__WERT15 = "WERT15";
	public static String FIELD__WERT16 = "WERT16";
	public static String FIELD__WERT17 = "WERT17";
	public static String FIELD__WERT18 = "WERT18";
	public static String FIELD__WERT19 = "WERT19";
	public static String FIELD__WERT20 = "WERT20";


	public String get_DOWNLOADNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("DOWNLOADNAME");
	}

	public String get_DOWNLOADNAME_cF() throws myException
	{
		return this.get_FormatedValue("DOWNLOADNAME");	
	}

	public String get_DOWNLOADNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DOWNLOADNAME");
	}

	public String get_DOWNLOADNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DOWNLOADNAME",cNotNullValue);
	}

	public String get_DOWNLOADNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DOWNLOADNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOADNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOADNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOADNAME", calNewValueFormated);
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
		public String get_ID_MAIL_AUS_MASK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MAIL_AUS_MASK");
	}

	public String get_ID_MAIL_AUS_MASK_cF() throws myException
	{
		return this.get_FormatedValue("ID_MAIL_AUS_MASK");	
	}

	public String get_ID_MAIL_AUS_MASK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MAIL_AUS_MASK");
	}

	public String get_ID_MAIL_AUS_MASK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MAIL_AUS_MASK",cNotNullValue);
	}

	public String get_ID_MAIL_AUS_MASK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MAIL_AUS_MASK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", calNewValueFormated);
	}
		public Long get_ID_MAIL_AUS_MASK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MAIL_AUS_MASK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MAIL_AUS_MASK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MAIL_AUS_MASK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MAIL_AUS_MASK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MAIL_AUS_MASK").getDoubleValue();
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
	public String get_ID_MAIL_AUS_MASK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAIL_AUS_MASK_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MAIL_AUS_MASK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAIL_AUS_MASK_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MAIL_AUS_MASK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAIL_AUS_MASK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MAIL_AUS_MASK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAIL_AUS_MASK").getBigDecimalValue();
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
	
	
	public String get_ID_MAIL_AUS_MASK_JASPER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MAIL_AUS_MASK_JASPER");
	}

	public String get_ID_MAIL_AUS_MASK_JASPER_cF() throws myException
	{
		return this.get_FormatedValue("ID_MAIL_AUS_MASK_JASPER");	
	}

	public String get_ID_MAIL_AUS_MASK_JASPER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MAIL_AUS_MASK_JASPER");
	}

	public String get_ID_MAIL_AUS_MASK_JASPER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MAIL_AUS_MASK_JASPER",cNotNullValue);
	}

	public String get_ID_MAIL_AUS_MASK_JASPER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MAIL_AUS_MASK_JASPER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_JASPER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK_JASPER", calNewValueFormated);
	}
		public Long get_ID_MAIL_AUS_MASK_JASPER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MAIL_AUS_MASK_JASPER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MAIL_AUS_MASK_JASPER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MAIL_AUS_MASK_JASPER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MAIL_AUS_MASK_JASPER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MAIL_AUS_MASK_JASPER").getDoubleValue();
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
	public String get_ID_MAIL_AUS_MASK_JASPER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAIL_AUS_MASK_JASPER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MAIL_AUS_MASK_JASPER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAIL_AUS_MASK_JASPER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MAIL_AUS_MASK_JASPER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAIL_AUS_MASK_JASPER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MAIL_AUS_MASK_JASPER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAIL_AUS_MASK_JASPER").getBigDecimalValue();
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
		public String get_PARAMETER01_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER01");
	}

	public String get_PARAMETER01_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER01");	
	}

	public String get_PARAMETER01_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER01");
	}

	public String get_PARAMETER01_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER01",cNotNullValue);
	}

	public String get_PARAMETER01_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER01",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER01", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER01(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER01", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER01", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER01", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER01", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER01_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER01", calNewValueFormated);
	}
		public String get_PARAMETER02_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER02");
	}

	public String get_PARAMETER02_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER02");	
	}

	public String get_PARAMETER02_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER02");
	}

	public String get_PARAMETER02_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER02",cNotNullValue);
	}

	public String get_PARAMETER02_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER02",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER02", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER02(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER02", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER02", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER02", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER02", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER02_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER02", calNewValueFormated);
	}
		public String get_PARAMETER03_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER03");
	}

	public String get_PARAMETER03_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER03");	
	}

	public String get_PARAMETER03_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER03");
	}

	public String get_PARAMETER03_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER03",cNotNullValue);
	}

	public String get_PARAMETER03_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER03",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER03", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER03(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER03", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER03", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER03", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER03", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER03_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER03", calNewValueFormated);
	}
		public String get_PARAMETER04_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER04");
	}

	public String get_PARAMETER04_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER04");	
	}

	public String get_PARAMETER04_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER04");
	}

	public String get_PARAMETER04_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER04",cNotNullValue);
	}

	public String get_PARAMETER04_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER04",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER04", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER04(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER04", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER04", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER04", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER04", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER04_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER04", calNewValueFormated);
	}
		public String get_PARAMETER05_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER05");
	}

	public String get_PARAMETER05_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER05");	
	}

	public String get_PARAMETER05_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER05");
	}

	public String get_PARAMETER05_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER05",cNotNullValue);
	}

	public String get_PARAMETER05_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER05",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER05", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER05(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER05", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER05", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER05", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER05", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER05_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER05", calNewValueFormated);
	}
		public String get_PARAMETER06_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER06");
	}

	public String get_PARAMETER06_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER06");	
	}

	public String get_PARAMETER06_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER06");
	}

	public String get_PARAMETER06_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER06",cNotNullValue);
	}

	public String get_PARAMETER06_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER06",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER06", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER06(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER06", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER06", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER06", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER06", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER06_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER06", calNewValueFormated);
	}
		public String get_PARAMETER07_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER07");
	}

	public String get_PARAMETER07_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER07");	
	}

	public String get_PARAMETER07_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER07");
	}

	public String get_PARAMETER07_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER07",cNotNullValue);
	}

	public String get_PARAMETER07_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER07",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER07", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER07(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER07", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER07", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER07", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER07", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER07_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER07", calNewValueFormated);
	}
		public String get_PARAMETER08_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER08");
	}

	public String get_PARAMETER08_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER08");	
	}

	public String get_PARAMETER08_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER08");
	}

	public String get_PARAMETER08_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER08",cNotNullValue);
	}

	public String get_PARAMETER08_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER08",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER08", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER08(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER08", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER08", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER08", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER08", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER08_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER08", calNewValueFormated);
	}
		public String get_PARAMETER09_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER09");
	}

	public String get_PARAMETER09_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER09");	
	}

	public String get_PARAMETER09_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER09");
	}

	public String get_PARAMETER09_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER09",cNotNullValue);
	}

	public String get_PARAMETER09_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER09",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER09", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER09(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER09", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER09", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER09", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER09", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER09_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER09", calNewValueFormated);
	}
		public String get_PARAMETER10_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER10");
	}

	public String get_PARAMETER10_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER10");	
	}

	public String get_PARAMETER10_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER10");
	}

	public String get_PARAMETER10_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER10",cNotNullValue);
	}

	public String get_PARAMETER10_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER10",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER10", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER10(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER10", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER10", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER10", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER10", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER10_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER10", calNewValueFormated);
	}
		public String get_PARAMETER11_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER11");
	}

	public String get_PARAMETER11_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER11");	
	}

	public String get_PARAMETER11_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER11");
	}

	public String get_PARAMETER11_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER11",cNotNullValue);
	}

	public String get_PARAMETER11_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER11",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER11", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER11(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER11", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER11", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER11", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER11", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER11_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER11", calNewValueFormated);
	}
		public String get_PARAMETER12_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER12");
	}

	public String get_PARAMETER12_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER12");	
	}

	public String get_PARAMETER12_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER12");
	}

	public String get_PARAMETER12_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER12",cNotNullValue);
	}

	public String get_PARAMETER12_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER12",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER12", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER12(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER12", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER12", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER12", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER12", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER12_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER12", calNewValueFormated);
	}
		public String get_PARAMETER13_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER13");
	}

	public String get_PARAMETER13_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER13");	
	}

	public String get_PARAMETER13_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER13");
	}

	public String get_PARAMETER13_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER13",cNotNullValue);
	}

	public String get_PARAMETER13_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER13",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER13", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER13(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER13", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER13", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER13", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER13", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER13_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER13", calNewValueFormated);
	}
		public String get_PARAMETER14_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER14");
	}

	public String get_PARAMETER14_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER14");	
	}

	public String get_PARAMETER14_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER14");
	}

	public String get_PARAMETER14_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER14",cNotNullValue);
	}

	public String get_PARAMETER14_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER14",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER14", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER14(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER14", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER14", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER14", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER14", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER14_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER14", calNewValueFormated);
	}
		public String get_PARAMETER15_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER15");
	}

	public String get_PARAMETER15_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER15");	
	}

	public String get_PARAMETER15_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER15");
	}

	public String get_PARAMETER15_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER15",cNotNullValue);
	}

	public String get_PARAMETER15_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER15",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER15", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER15(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER15", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER15", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER15", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER15", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER15_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER15", calNewValueFormated);
	}
		public String get_PARAMETER16_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER16");
	}

	public String get_PARAMETER16_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER16");	
	}

	public String get_PARAMETER16_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER16");
	}

	public String get_PARAMETER16_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER16",cNotNullValue);
	}

	public String get_PARAMETER16_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER16",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER16", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER16(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER16", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER16", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER16", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER16", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER16_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER16", calNewValueFormated);
	}
		public String get_PARAMETER17_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER17");
	}

	public String get_PARAMETER17_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER17");	
	}

	public String get_PARAMETER17_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER17");
	}

	public String get_PARAMETER17_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER17",cNotNullValue);
	}

	public String get_PARAMETER17_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER17",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER17", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER17(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER17", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER17", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER17", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER17", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER17_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER17", calNewValueFormated);
	}
		public String get_PARAMETER18_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER18");
	}

	public String get_PARAMETER18_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER18");	
	}

	public String get_PARAMETER18_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER18");
	}

	public String get_PARAMETER18_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER18",cNotNullValue);
	}

	public String get_PARAMETER18_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER18",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER18", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER18(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER18", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER18", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER18", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER18", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER18_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER18", calNewValueFormated);
	}
		public String get_PARAMETER19_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER19");
	}

	public String get_PARAMETER19_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER19");	
	}

	public String get_PARAMETER19_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER19");
	}

	public String get_PARAMETER19_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER19",cNotNullValue);
	}

	public String get_PARAMETER19_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER19",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER19", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER19(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER19", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER19", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER19", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER19", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER19_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER19", calNewValueFormated);
	}
		public String get_PARAMETER20_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMETER20");
	}

	public String get_PARAMETER20_cF() throws myException
	{
		return this.get_FormatedValue("PARAMETER20");	
	}

	public String get_PARAMETER20_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMETER20");
	}

	public String get_PARAMETER20_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMETER20",cNotNullValue);
	}

	public String get_PARAMETER20_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMETER20",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMETER20", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMETER20(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMETER20", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMETER20", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER20", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER20", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMETER20_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMETER20", calNewValueFormated);
	}
		public String get_REPORTNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("REPORTNAME");
	}

	public String get_REPORTNAME_cF() throws myException
	{
		return this.get_FormatedValue("REPORTNAME");	
	}

	public String get_REPORTNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("REPORTNAME");
	}

	public String get_REPORTNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("REPORTNAME",cNotNullValue);
	}

	public String get_REPORTNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("REPORTNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("REPORTNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_REPORTNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("REPORTNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("REPORTNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_REPORTNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("REPORTNAME", calNewValueFormated);
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
	
	
	public String get_WERT01_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT01");
	}

	public String get_WERT01_cF() throws myException
	{
		return this.get_FormatedValue("WERT01");	
	}

	public String get_WERT01_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT01");
	}

	public String get_WERT01_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT01",cNotNullValue);
	}

	public String get_WERT01_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT01",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT01(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT01", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT01(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT01", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT01_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT01", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT01_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT01", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT01_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT01", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT01_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT01", calNewValueFormated);
	}
		public String get_WERT02_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT02");
	}

	public String get_WERT02_cF() throws myException
	{
		return this.get_FormatedValue("WERT02");	
	}

	public String get_WERT02_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT02");
	}

	public String get_WERT02_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT02",cNotNullValue);
	}

	public String get_WERT02_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT02",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT02(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT02", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT02(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT02", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT02_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT02", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT02_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT02", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT02_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT02", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT02_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT02", calNewValueFormated);
	}
		public String get_WERT03_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT03");
	}

	public String get_WERT03_cF() throws myException
	{
		return this.get_FormatedValue("WERT03");	
	}

	public String get_WERT03_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT03");
	}

	public String get_WERT03_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT03",cNotNullValue);
	}

	public String get_WERT03_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT03",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT03(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT03", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT03(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT03", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT03_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT03", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT03_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT03", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT03_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT03", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT03_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT03", calNewValueFormated);
	}
		public String get_WERT04_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT04");
	}

	public String get_WERT04_cF() throws myException
	{
		return this.get_FormatedValue("WERT04");	
	}

	public String get_WERT04_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT04");
	}

	public String get_WERT04_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT04",cNotNullValue);
	}

	public String get_WERT04_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT04",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT04(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT04", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT04(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT04", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT04_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT04", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT04_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT04", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT04_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT04", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT04_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT04", calNewValueFormated);
	}
		public String get_WERT05_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT05");
	}

	public String get_WERT05_cF() throws myException
	{
		return this.get_FormatedValue("WERT05");	
	}

	public String get_WERT05_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT05");
	}

	public String get_WERT05_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT05",cNotNullValue);
	}

	public String get_WERT05_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT05",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT05(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT05", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT05(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT05", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT05_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT05", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT05_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT05", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT05_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT05", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT05_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT05", calNewValueFormated);
	}
		public String get_WERT06_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT06");
	}

	public String get_WERT06_cF() throws myException
	{
		return this.get_FormatedValue("WERT06");	
	}

	public String get_WERT06_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT06");
	}

	public String get_WERT06_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT06",cNotNullValue);
	}

	public String get_WERT06_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT06",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT06(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT06", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT06(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT06", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT06_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT06", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT06_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT06", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT06_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT06", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT06_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT06", calNewValueFormated);
	}
		public String get_WERT07_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT07");
	}

	public String get_WERT07_cF() throws myException
	{
		return this.get_FormatedValue("WERT07");	
	}

	public String get_WERT07_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT07");
	}

	public String get_WERT07_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT07",cNotNullValue);
	}

	public String get_WERT07_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT07",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT07(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT07", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT07(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT07", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT07_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT07", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT07_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT07", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT07_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT07", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT07_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT07", calNewValueFormated);
	}
		public String get_WERT08_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT08");
	}

	public String get_WERT08_cF() throws myException
	{
		return this.get_FormatedValue("WERT08");	
	}

	public String get_WERT08_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT08");
	}

	public String get_WERT08_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT08",cNotNullValue);
	}

	public String get_WERT08_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT08",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT08(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT08", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT08(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT08", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT08_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT08", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT08_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT08", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT08_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT08", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT08_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT08", calNewValueFormated);
	}
		public String get_WERT09_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT09");
	}

	public String get_WERT09_cF() throws myException
	{
		return this.get_FormatedValue("WERT09");	
	}

	public String get_WERT09_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT09");
	}

	public String get_WERT09_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT09",cNotNullValue);
	}

	public String get_WERT09_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT09",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT09(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT09", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT09(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT09", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT09_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT09", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT09_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT09", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT09_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT09", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT09_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT09", calNewValueFormated);
	}
		public String get_WERT10_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT10");
	}

	public String get_WERT10_cF() throws myException
	{
		return this.get_FormatedValue("WERT10");	
	}

	public String get_WERT10_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT10");
	}

	public String get_WERT10_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT10",cNotNullValue);
	}

	public String get_WERT10_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT10",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT10(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT10", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT10(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT10", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT10_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT10", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT10_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT10", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT10_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT10", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT10_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT10", calNewValueFormated);
	}
		public String get_WERT11_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT11");
	}

	public String get_WERT11_cF() throws myException
	{
		return this.get_FormatedValue("WERT11");	
	}

	public String get_WERT11_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT11");
	}

	public String get_WERT11_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT11",cNotNullValue);
	}

	public String get_WERT11_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT11",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT11(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT11", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT11(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT11", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT11_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT11", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT11_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT11", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT11_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT11", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT11_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT11", calNewValueFormated);
	}
		public String get_WERT12_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT12");
	}

	public String get_WERT12_cF() throws myException
	{
		return this.get_FormatedValue("WERT12");	
	}

	public String get_WERT12_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT12");
	}

	public String get_WERT12_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT12",cNotNullValue);
	}

	public String get_WERT12_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT12",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT12(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT12", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT12(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT12", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT12_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT12", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT12_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT12", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT12_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT12", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT12_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT12", calNewValueFormated);
	}
		public String get_WERT13_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT13");
	}

	public String get_WERT13_cF() throws myException
	{
		return this.get_FormatedValue("WERT13");	
	}

	public String get_WERT13_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT13");
	}

	public String get_WERT13_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT13",cNotNullValue);
	}

	public String get_WERT13_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT13",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT13(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT13", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT13(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT13", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT13_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT13", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT13_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT13", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT13_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT13", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT13_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT13", calNewValueFormated);
	}
		public String get_WERT14_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT14");
	}

	public String get_WERT14_cF() throws myException
	{
		return this.get_FormatedValue("WERT14");	
	}

	public String get_WERT14_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT14");
	}

	public String get_WERT14_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT14",cNotNullValue);
	}

	public String get_WERT14_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT14",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT14(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT14", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT14(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT14", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT14_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT14", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT14_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT14", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT14_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT14", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT14_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT14", calNewValueFormated);
	}
		public String get_WERT15_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT15");
	}

	public String get_WERT15_cF() throws myException
	{
		return this.get_FormatedValue("WERT15");	
	}

	public String get_WERT15_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT15");
	}

	public String get_WERT15_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT15",cNotNullValue);
	}

	public String get_WERT15_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT15",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT15(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT15", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT15(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT15", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT15_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT15", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT15_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT15", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT15_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT15", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT15_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT15", calNewValueFormated);
	}
		public String get_WERT16_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT16");
	}

	public String get_WERT16_cF() throws myException
	{
		return this.get_FormatedValue("WERT16");	
	}

	public String get_WERT16_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT16");
	}

	public String get_WERT16_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT16",cNotNullValue);
	}

	public String get_WERT16_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT16",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT16(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT16", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT16(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT16", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT16_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT16", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT16_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT16", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT16_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT16", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT16_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT16", calNewValueFormated);
	}
		public String get_WERT17_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT17");
	}

	public String get_WERT17_cF() throws myException
	{
		return this.get_FormatedValue("WERT17");	
	}

	public String get_WERT17_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT17");
	}

	public String get_WERT17_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT17",cNotNullValue);
	}

	public String get_WERT17_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT17",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT17(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT17", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT17(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT17", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT17_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT17", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT17_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT17", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT17_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT17", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT17_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT17", calNewValueFormated);
	}
		public String get_WERT18_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT18");
	}

	public String get_WERT18_cF() throws myException
	{
		return this.get_FormatedValue("WERT18");	
	}

	public String get_WERT18_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT18");
	}

	public String get_WERT18_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT18",cNotNullValue);
	}

	public String get_WERT18_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT18",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT18(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT18", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT18(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT18", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT18_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT18", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT18_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT18", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT18_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT18", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT18_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT18", calNewValueFormated);
	}
		public String get_WERT19_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT19");
	}

	public String get_WERT19_cF() throws myException
	{
		return this.get_FormatedValue("WERT19");	
	}

	public String get_WERT19_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT19");
	}

	public String get_WERT19_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT19",cNotNullValue);
	}

	public String get_WERT19_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT19",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT19(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT19", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT19(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT19", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT19_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT19", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT19_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT19", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT19_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT19", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT19_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT19", calNewValueFormated);
	}
		public String get_WERT20_cUF() throws myException
	{
		return this.get_UnFormatedValue("WERT20");
	}

	public String get_WERT20_cF() throws myException
	{
		return this.get_FormatedValue("WERT20");	
	}

	public String get_WERT20_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WERT20");
	}

	public String get_WERT20_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WERT20",cNotNullValue);
	}

	public String get_WERT20_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WERT20",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WERT20(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WERT20", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WERT20(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WERT20", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT20_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WERT20", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT20_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT20", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT20_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT20", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WERT20_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WERT20", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("DOWNLOADNAME",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MAIL_AUS_MASK",MyRECORD.DATATYPES.NUMBER);
	put("ID_MAIL_AUS_MASK_JASPER",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("PARAMETER01",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER02",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER03",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER04",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER05",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER06",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER07",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER08",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER09",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER10",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER11",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER12",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER13",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER14",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER15",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER16",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER17",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER18",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER19",MyRECORD.DATATYPES.TEXT);
	put("PARAMETER20",MyRECORD.DATATYPES.TEXT);
	put("REPORTNAME",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("WERT01",MyRECORD.DATATYPES.TEXT);
	put("WERT02",MyRECORD.DATATYPES.TEXT);
	put("WERT03",MyRECORD.DATATYPES.TEXT);
	put("WERT04",MyRECORD.DATATYPES.TEXT);
	put("WERT05",MyRECORD.DATATYPES.TEXT);
	put("WERT06",MyRECORD.DATATYPES.TEXT);
	put("WERT07",MyRECORD.DATATYPES.TEXT);
	put("WERT08",MyRECORD.DATATYPES.TEXT);
	put("WERT09",MyRECORD.DATATYPES.TEXT);
	put("WERT10",MyRECORD.DATATYPES.TEXT);
	put("WERT11",MyRECORD.DATATYPES.TEXT);
	put("WERT12",MyRECORD.DATATYPES.TEXT);
	put("WERT13",MyRECORD.DATATYPES.TEXT);
	put("WERT14",MyRECORD.DATATYPES.TEXT);
	put("WERT15",MyRECORD.DATATYPES.TEXT);
	put("WERT16",MyRECORD.DATATYPES.TEXT);
	put("WERT17",MyRECORD.DATATYPES.TEXT);
	put("WERT18",MyRECORD.DATATYPES.TEXT);
	put("WERT19",MyRECORD.DATATYPES.TEXT);
	put("WERT20",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_MAIL_AUS_MASK_JASPER.HM_FIELDS;	
	}

}
