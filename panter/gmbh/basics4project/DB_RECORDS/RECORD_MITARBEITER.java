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

public class RECORD_MITARBEITER extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_MITARBEITER";
    public static String IDFIELD   = "ID_MITARBEITER";
    

	//erzeugen eines RECORDNEW_JT_MITARBEITER - felds
	private RECORDNEW_MITARBEITER   recNEW = null;

    private _TAB  tab = _TAB.mitarbeiter;  



	public RECORD_MITARBEITER() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_MITARBEITER");
	}


	public RECORD_MITARBEITER(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MITARBEITER");
	}



	public RECORD_MITARBEITER(RECORD_MITARBEITER recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MITARBEITER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MITARBEITER.TABLENAME);
	}


	//2014-04-03
	public RECORD_MITARBEITER(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MITARBEITER");
		this.set_Tablename_To_FieldMetaDefs(RECORD_MITARBEITER.TABLENAME);
	}




	public RECORD_MITARBEITER(long lID_Unformated) throws myException
	{
		super("JT_MITARBEITER","ID_MITARBEITER",""+lID_Unformated);
		this.set_TABLE_NAME("JT_MITARBEITER");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MITARBEITER.TABLENAME);
	}

	public RECORD_MITARBEITER(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_MITARBEITER");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MITARBEITER", "ID_MITARBEITER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MITARBEITER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MITARBEITER.TABLENAME);
	}
	
	

	public RECORD_MITARBEITER(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_MITARBEITER","ID_MITARBEITER",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_MITARBEITER");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MITARBEITER.TABLENAME);

	}


	public RECORD_MITARBEITER(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MITARBEITER");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MITARBEITER", "ID_MITARBEITER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MITARBEITER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MITARBEITER.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_MITARBEITER();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_MITARBEITER.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_MITARBEITER";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_MITARBEITER_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_MITARBEITER_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MITARBEITER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MITARBEITER", bibE2.cTO(), "ID_MITARBEITER="+this.get_ID_MITARBEITER_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MITARBEITER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MITARBEITER", bibE2.cTO(), "ID_MITARBEITER="+this.get_ID_MITARBEITER_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_MITARBEITER="+this.get_ID_MITARBEITER_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_MITARBEITER="+this.get_ID_MITARBEITER_cUF();
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
			if (S.isFull(this.get_ID_MITARBEITER_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MITARBEITER", "ID_MITARBEITER="+this.get_ID_MITARBEITER_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_MITARBEITER get_RECORDNEW_MITARBEITER() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_MITARBEITER();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_MITARBEITER(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_MITARBEITER create_Instance() throws myException {
		return new RECORD_MITARBEITER();
	}
	
	public static RECORD_MITARBEITER create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_MITARBEITER(Conn);
    }

	public static RECORD_MITARBEITER create_Instance(RECORD_MITARBEITER recordOrig) {
		return new RECORD_MITARBEITER(recordOrig);
    }

	public static RECORD_MITARBEITER create_Instance(long lID_Unformated) throws myException {
		return new RECORD_MITARBEITER(lID_Unformated);
    }

	public static RECORD_MITARBEITER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_MITARBEITER(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_MITARBEITER create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_MITARBEITER(lID_Unformated, Conn);
	}

	public static RECORD_MITARBEITER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_MITARBEITER(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_MITARBEITER create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_MITARBEITER(recordOrig);	    
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
    public RECORD_MITARBEITER build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_MITARBEITER="+this.get_ID_MITARBEITER_cUF());
      }
      //return new RECORD_MITARBEITER(this.get_cSQL_4_Build());
      RECORD_MITARBEITER rec = new RECORD_MITARBEITER();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_MITARBEITER
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_MITARBEITER-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_MITARBEITER get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_MITARBEITER_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_MITARBEITER  recNew = new RECORDNEW_MITARBEITER();
		
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
    public RECORD_MITARBEITER set_recordNew(RECORDNEW_MITARBEITER recnew) throws myException {
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
	
	



		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_basis = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_mitarbeiter = null;




		private RECORD_MITARBEITERTYP UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp = null;




		private RECORD_MITARBEITERTYP UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp2 = null;




		private RECORD_MITARBEITERTYP UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp3 = null;




		private RECORD_MITARBEITERTYP UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp4 = null;






	
	/**
	 * References the Field ID_ADRESSE_BASIS
	 * Falls keine this.get_ID_ADRESSE_BASIS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_basis() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_BASIS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_basis==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_basis = new RECORD_ADRESSE(this.get_ID_ADRESSE_BASIS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_basis;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_MITARBEITER
	 * Falls keine this.get_ID_ADRESSE_MITARBEITER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_MITARBEITER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_mitarbeiter==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_mitarbeiter = new RECORD_ADRESSE(this.get_ID_ADRESSE_MITARBEITER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_mitarbeiter;
	}
	





	
	/**
	 * References the Field ID_MITARBEITERTYP
	 * Falls keine this.get_ID_MITARBEITERTYP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MITARBEITERTYP get_UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp() throws myException
	{
		if (S.isEmpty(this.get_ID_MITARBEITERTYP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp==null)
		{
			this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp = new RECORD_MITARBEITERTYP(this.get_ID_MITARBEITERTYP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp;
	}
	





	
	/**
	 * References the Field ID_MITARBEITERTYP2
	 * Falls keine this.get_ID_MITARBEITERTYP2_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MITARBEITERTYP get_UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp2() throws myException
	{
		if (S.isEmpty(this.get_ID_MITARBEITERTYP2_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp2==null)
		{
			this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp2 = new RECORD_MITARBEITERTYP(this.get_ID_MITARBEITERTYP2_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp2;
	}
	





	
	/**
	 * References the Field ID_MITARBEITERTYP3
	 * Falls keine this.get_ID_MITARBEITERTYP3_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MITARBEITERTYP get_UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp3() throws myException
	{
		if (S.isEmpty(this.get_ID_MITARBEITERTYP3_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp3==null)
		{
			this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp3 = new RECORD_MITARBEITERTYP(this.get_ID_MITARBEITERTYP3_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp3;
	}
	





	
	/**
	 * References the Field ID_MITARBEITERTYP4
	 * Falls keine this.get_ID_MITARBEITERTYP4_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MITARBEITERTYP get_UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp4() throws myException
	{
		if (S.isEmpty(this.get_ID_MITARBEITERTYP4_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp4==null)
		{
			this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp4 = new RECORD_MITARBEITERTYP(this.get_ID_MITARBEITERTYP4_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MITARBEITERTYP_id_mitarbeitertyp4;
	}
	

	public static String FIELD__ASP_ABNAHMEANGEBOT = "ASP_ABNAHMEANGEBOT";
	public static String FIELD__ASP_ANGEBOT = "ASP_ANGEBOT";
	public static String FIELD__ASP_EK_KONTRAKT = "ASP_EK_KONTRAKT";
	public static String FIELD__ASP_FIBU = "ASP_FIBU";
	public static String FIELD__ASP_GUTSCHRIFT = "ASP_GUTSCHRIFT";
	public static String FIELD__ASP_RECHNUNG = "ASP_RECHNUNG";
	public static String FIELD__ASP_TRANSPORT = "ASP_TRANSPORT";
	public static String FIELD__ASP_VK_KONTRAKT = "ASP_VK_KONTRAKT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GESCHENK_KALENDER = "GESCHENK_KALENDER";
	public static String FIELD__GESCHENK_SEKT = "GESCHENK_SEKT";
	public static String FIELD__GESCHENK_SPARGEL = "GESCHENK_SPARGEL";
	public static String FIELD__GESCHENK_WEIN = "GESCHENK_WEIN";
	public static String FIELD__ID_ADRESSE_BASIS = "ID_ADRESSE_BASIS";
	public static String FIELD__ID_ADRESSE_MITARBEITER = "ID_ADRESSE_MITARBEITER";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_MITARBEITER = "ID_MITARBEITER";
	public static String FIELD__ID_MITARBEITERTYP = "ID_MITARBEITERTYP";
	public static String FIELD__ID_MITARBEITERTYP2 = "ID_MITARBEITERTYP2";
	public static String FIELD__ID_MITARBEITERTYP3 = "ID_MITARBEITERTYP3";
	public static String FIELD__ID_MITARBEITERTYP4 = "ID_MITARBEITERTYP4";
	public static String FIELD__IST_ANSPRECHPARTNER = "IST_ANSPRECHPARTNER";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__ROHSTOFF_GESCHENK_INFO = "ROHSTOFF_GESCHENK_INFO";
	public static String FIELD__SOMMERGESCHENK = "SOMMERGESCHENK";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__WEIHNACHTSGESCHENK = "WEIHNACHTSGESCHENK";


	public String get_ASP_ABNAHMEANGEBOT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_ABNAHMEANGEBOT");
	}

	public String get_ASP_ABNAHMEANGEBOT_cF() throws myException
	{
		return this.get_FormatedValue("ASP_ABNAHMEANGEBOT");	
	}

	public String get_ASP_ABNAHMEANGEBOT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_ABNAHMEANGEBOT");
	}

	public String get_ASP_ABNAHMEANGEBOT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_ABNAHMEANGEBOT",cNotNullValue);
	}

	public String get_ASP_ABNAHMEANGEBOT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_ABNAHMEANGEBOT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_ABNAHMEANGEBOT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_ABNAHMEANGEBOT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ABNAHMEANGEBOT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_ABNAHMEANGEBOT", calNewValueFormated);
	}
		public boolean is_ASP_ABNAHMEANGEBOT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_ABNAHMEANGEBOT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_ABNAHMEANGEBOT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_ABNAHMEANGEBOT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ASP_ANGEBOT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_ANGEBOT");
	}

	public String get_ASP_ANGEBOT_cF() throws myException
	{
		return this.get_FormatedValue("ASP_ANGEBOT");	
	}

	public String get_ASP_ANGEBOT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_ANGEBOT");
	}

	public String get_ASP_ANGEBOT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_ANGEBOT",cNotNullValue);
	}

	public String get_ASP_ANGEBOT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_ANGEBOT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_ANGEBOT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_ANGEBOT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_ANGEBOT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_ANGEBOT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_ANGEBOT", calNewValueFormated);
	}
		public boolean is_ASP_ANGEBOT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_ANGEBOT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_ANGEBOT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_ANGEBOT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ASP_EK_KONTRAKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_EK_KONTRAKT");
	}

	public String get_ASP_EK_KONTRAKT_cF() throws myException
	{
		return this.get_FormatedValue("ASP_EK_KONTRAKT");	
	}

	public String get_ASP_EK_KONTRAKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_EK_KONTRAKT");
	}

	public String get_ASP_EK_KONTRAKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_EK_KONTRAKT",cNotNullValue);
	}

	public String get_ASP_EK_KONTRAKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_EK_KONTRAKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_EK_KONTRAKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_EK_KONTRAKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_EK_KONTRAKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_EK_KONTRAKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_EK_KONTRAKT", calNewValueFormated);
	}
		public boolean is_ASP_EK_KONTRAKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_EK_KONTRAKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_EK_KONTRAKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_EK_KONTRAKT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ASP_FIBU_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_FIBU");
	}

	public String get_ASP_FIBU_cF() throws myException
	{
		return this.get_FormatedValue("ASP_FIBU");	
	}

	public String get_ASP_FIBU_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_FIBU");
	}

	public String get_ASP_FIBU_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_FIBU",cNotNullValue);
	}

	public String get_ASP_FIBU_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_FIBU",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_FIBU", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_FIBU(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_FIBU", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_FIBU", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_FIBU", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_FIBU", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_FIBU_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_FIBU", calNewValueFormated);
	}
		public boolean is_ASP_FIBU_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_FIBU_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_FIBU_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_FIBU_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ASP_GUTSCHRIFT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_GUTSCHRIFT");
	}

	public String get_ASP_GUTSCHRIFT_cF() throws myException
	{
		return this.get_FormatedValue("ASP_GUTSCHRIFT");	
	}

	public String get_ASP_GUTSCHRIFT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_GUTSCHRIFT");
	}

	public String get_ASP_GUTSCHRIFT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_GUTSCHRIFT",cNotNullValue);
	}

	public String get_ASP_GUTSCHRIFT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_GUTSCHRIFT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_GUTSCHRIFT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_GUTSCHRIFT", calNewValueFormated);
	}
		public boolean is_ASP_GUTSCHRIFT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_GUTSCHRIFT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_GUTSCHRIFT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_GUTSCHRIFT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ASP_RECHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_RECHNUNG");
	}

	public String get_ASP_RECHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("ASP_RECHNUNG");	
	}

	public String get_ASP_RECHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_RECHNUNG");
	}

	public String get_ASP_RECHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_RECHNUNG",cNotNullValue);
	}

	public String get_ASP_RECHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_RECHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_RECHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_RECHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_RECHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_RECHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_RECHNUNG", calNewValueFormated);
	}
		public boolean is_ASP_RECHNUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_RECHNUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_RECHNUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_RECHNUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ASP_TRANSPORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_TRANSPORT");
	}

	public String get_ASP_TRANSPORT_cF() throws myException
	{
		return this.get_FormatedValue("ASP_TRANSPORT");	
	}

	public String get_ASP_TRANSPORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_TRANSPORT");
	}

	public String get_ASP_TRANSPORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_TRANSPORT",cNotNullValue);
	}

	public String get_ASP_TRANSPORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_TRANSPORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_TRANSPORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_TRANSPORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_TRANSPORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_TRANSPORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_TRANSPORT", calNewValueFormated);
	}
		public boolean is_ASP_TRANSPORT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_TRANSPORT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_TRANSPORT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_TRANSPORT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ASP_VK_KONTRAKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ASP_VK_KONTRAKT");
	}

	public String get_ASP_VK_KONTRAKT_cF() throws myException
	{
		return this.get_FormatedValue("ASP_VK_KONTRAKT");	
	}

	public String get_ASP_VK_KONTRAKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ASP_VK_KONTRAKT");
	}

	public String get_ASP_VK_KONTRAKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ASP_VK_KONTRAKT",cNotNullValue);
	}

	public String get_ASP_VK_KONTRAKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ASP_VK_KONTRAKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ASP_VK_KONTRAKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ASP_VK_KONTRAKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ASP_VK_KONTRAKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ASP_VK_KONTRAKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ASP_VK_KONTRAKT", calNewValueFormated);
	}
		public boolean is_ASP_VK_KONTRAKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_VK_KONTRAKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ASP_VK_KONTRAKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ASP_VK_KONTRAKT_cUF_NN("N").equals("N"))
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
		public String get_GESCHENK_KALENDER_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_KALENDER");
	}

	public String get_GESCHENK_KALENDER_cF() throws myException
	{
		return this.get_FormatedValue("GESCHENK_KALENDER");	
	}

	public String get_GESCHENK_KALENDER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHENK_KALENDER");
	}

	public String get_GESCHENK_KALENDER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_KALENDER",cNotNullValue);
	}

	public String get_GESCHENK_KALENDER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESCHENK_KALENDER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESCHENK_KALENDER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_KALENDER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESCHENK_KALENDER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_KALENDER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_KALENDER", calNewValueFormated);
	}
		public boolean is_GESCHENK_KALENDER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_KALENDER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GESCHENK_KALENDER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_KALENDER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GESCHENK_SEKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_SEKT");
	}

	public String get_GESCHENK_SEKT_cF() throws myException
	{
		return this.get_FormatedValue("GESCHENK_SEKT");	
	}

	public String get_GESCHENK_SEKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHENK_SEKT");
	}

	public String get_GESCHENK_SEKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_SEKT",cNotNullValue);
	}

	public String get_GESCHENK_SEKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESCHENK_SEKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESCHENK_SEKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_SEKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESCHENK_SEKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SEKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_SEKT", calNewValueFormated);
	}
		public boolean is_GESCHENK_SEKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_SEKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GESCHENK_SEKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_SEKT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GESCHENK_SPARGEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_SPARGEL");
	}

	public String get_GESCHENK_SPARGEL_cF() throws myException
	{
		return this.get_FormatedValue("GESCHENK_SPARGEL");	
	}

	public String get_GESCHENK_SPARGEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHENK_SPARGEL");
	}

	public String get_GESCHENK_SPARGEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_SPARGEL",cNotNullValue);
	}

	public String get_GESCHENK_SPARGEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESCHENK_SPARGEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESCHENK_SPARGEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_SPARGEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESCHENK_SPARGEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_SPARGEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_SPARGEL", calNewValueFormated);
	}
		public boolean is_GESCHENK_SPARGEL_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_SPARGEL_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GESCHENK_SPARGEL_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_SPARGEL_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GESCHENK_WEIN_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_WEIN");
	}

	public String get_GESCHENK_WEIN_cF() throws myException
	{
		return this.get_FormatedValue("GESCHENK_WEIN");	
	}

	public String get_GESCHENK_WEIN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHENK_WEIN");
	}

	public String get_GESCHENK_WEIN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESCHENK_WEIN",cNotNullValue);
	}

	public String get_GESCHENK_WEIN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESCHENK_WEIN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESCHENK_WEIN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESCHENK_WEIN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESCHENK_WEIN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHENK_WEIN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHENK_WEIN", calNewValueFormated);
	}
		public boolean is_GESCHENK_WEIN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_WEIN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GESCHENK_WEIN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHENK_WEIN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_ADRESSE_BASIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_BASIS");
	}

	public String get_ID_ADRESSE_BASIS_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_BASIS");	
	}

	public String get_ID_ADRESSE_BASIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_BASIS");
	}

	public String get_ID_ADRESSE_BASIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_BASIS",cNotNullValue);
	}

	public String get_ID_ADRESSE_BASIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_BASIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_BASIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_BASIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_BASIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_BASIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_BASIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_BASIS").getDoubleValue();
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
	public String get_ID_ADRESSE_BASIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_BASIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_BASIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_BASIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_BASIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_BASIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_BASIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_BASIS").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_MITARBEITER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MITARBEITER");
	}

	public String get_ID_ADRESSE_MITARBEITER_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MITARBEITER");	
	}

	public String get_ID_ADRESSE_MITARBEITER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_MITARBEITER");
	}

	public String get_ID_ADRESSE_MITARBEITER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MITARBEITER",cNotNullValue);
	}

	public String get_ID_ADRESSE_MITARBEITER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MITARBEITER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MITARBEITER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_MITARBEITER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MITARBEITER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MITARBEITER", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_MITARBEITER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_MITARBEITER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_MITARBEITER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MITARBEITER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_MITARBEITER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MITARBEITER").getDoubleValue();
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
	public String get_ID_ADRESSE_MITARBEITER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MITARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_MITARBEITER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MITARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_MITARBEITER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MITARBEITER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_MITARBEITER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MITARBEITER").getBigDecimalValue();
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
	
	
	public String get_ID_MITARBEITER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITER");
	}

	public String get_ID_MITARBEITER_cF() throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITER");	
	}

	public String get_ID_MITARBEITER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MITARBEITER");
	}

	public String get_ID_MITARBEITER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITER",cNotNullValue);
	}

	public String get_ID_MITARBEITER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MITARBEITER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MITARBEITER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MITARBEITER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITER", calNewValueFormated);
	}
		public Long get_ID_MITARBEITER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MITARBEITER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MITARBEITER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MITARBEITER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITER").getDoubleValue();
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
	public String get_ID_MITARBEITER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MITARBEITER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MITARBEITER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MITARBEITER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITER").getBigDecimalValue();
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
	
	
	public String get_ID_MITARBEITERTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP");
	}

	public String get_ID_MITARBEITERTYP_cF() throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP");	
	}

	public String get_ID_MITARBEITERTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MITARBEITERTYP");
	}

	public String get_ID_MITARBEITERTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP",cNotNullValue);
	}

	public String get_ID_MITARBEITERTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MITARBEITERTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MITARBEITERTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP", calNewValueFormated);
	}
		public Long get_ID_MITARBEITERTYP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MITARBEITERTYP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MITARBEITERTYP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MITARBEITERTYP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP").getDoubleValue();
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
	public String get_ID_MITARBEITERTYP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MITARBEITERTYP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MITARBEITERTYP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MITARBEITERTYP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP").getBigDecimalValue();
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
	
	
	public String get_ID_MITARBEITERTYP2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP2");
	}

	public String get_ID_MITARBEITERTYP2_cF() throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP2");	
	}

	public String get_ID_MITARBEITERTYP2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MITARBEITERTYP2");
	}

	public String get_ID_MITARBEITERTYP2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP2",cNotNullValue);
	}

	public String get_ID_MITARBEITERTYP2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MITARBEITERTYP2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MITARBEITERTYP2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP2", calNewValueFormated);
	}
		public Long get_ID_MITARBEITERTYP2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MITARBEITERTYP2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MITARBEITERTYP2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MITARBEITERTYP2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP2").getDoubleValue();
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
	public String get_ID_MITARBEITERTYP2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP2_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MITARBEITERTYP2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP2_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MITARBEITERTYP2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MITARBEITERTYP2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP2").getBigDecimalValue();
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
	
	
	public String get_ID_MITARBEITERTYP3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP3");
	}

	public String get_ID_MITARBEITERTYP3_cF() throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP3");	
	}

	public String get_ID_MITARBEITERTYP3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MITARBEITERTYP3");
	}

	public String get_ID_MITARBEITERTYP3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP3",cNotNullValue);
	}

	public String get_ID_MITARBEITERTYP3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MITARBEITERTYP3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MITARBEITERTYP3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP3", calNewValueFormated);
	}
		public Long get_ID_MITARBEITERTYP3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MITARBEITERTYP3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MITARBEITERTYP3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MITARBEITERTYP3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP3").getDoubleValue();
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
	public String get_ID_MITARBEITERTYP3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP3_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MITARBEITERTYP3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP3_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MITARBEITERTYP3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MITARBEITERTYP3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP3").getBigDecimalValue();
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
	
	
	public String get_ID_MITARBEITERTYP4_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP4");
	}

	public String get_ID_MITARBEITERTYP4_cF() throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP4");	
	}

	public String get_ID_MITARBEITERTYP4_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MITARBEITERTYP4");
	}

	public String get_ID_MITARBEITERTYP4_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MITARBEITERTYP4",cNotNullValue);
	}

	public String get_ID_MITARBEITERTYP4_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MITARBEITERTYP4",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MITARBEITERTYP4", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MITARBEITERTYP4(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MITARBEITERTYP4", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MITARBEITERTYP4_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MITARBEITERTYP4", calNewValueFormated);
	}
		public Long get_ID_MITARBEITERTYP4_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MITARBEITERTYP4").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MITARBEITERTYP4_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP4").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MITARBEITERTYP4_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MITARBEITERTYP4").getDoubleValue();
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
	public String get_ID_MITARBEITERTYP4_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP4_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MITARBEITERTYP4_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MITARBEITERTYP4_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MITARBEITERTYP4_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP4").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MITARBEITERTYP4_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MITARBEITERTYP4").getBigDecimalValue();
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
	
	
	public String get_IST_ANSPRECHPARTNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_ANSPRECHPARTNER");
	}

	public String get_IST_ANSPRECHPARTNER_cF() throws myException
	{
		return this.get_FormatedValue("IST_ANSPRECHPARTNER");	
	}

	public String get_IST_ANSPRECHPARTNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_ANSPRECHPARTNER");
	}

	public String get_IST_ANSPRECHPARTNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_ANSPRECHPARTNER",cNotNullValue);
	}

	public String get_IST_ANSPRECHPARTNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_ANSPRECHPARTNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_ANSPRECHPARTNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_ANSPRECHPARTNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ANSPRECHPARTNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_ANSPRECHPARTNER", calNewValueFormated);
	}
		public boolean is_IST_ANSPRECHPARTNER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_ANSPRECHPARTNER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_ANSPRECHPARTNER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_ANSPRECHPARTNER_cUF_NN("N").equals("N"))
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
		public String get_ROHSTOFF_GESCHENK_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ROHSTOFF_GESCHENK_INFO");
	}

	public String get_ROHSTOFF_GESCHENK_INFO_cF() throws myException
	{
		return this.get_FormatedValue("ROHSTOFF_GESCHENK_INFO");	
	}

	public String get_ROHSTOFF_GESCHENK_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ROHSTOFF_GESCHENK_INFO");
	}

	public String get_ROHSTOFF_GESCHENK_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ROHSTOFF_GESCHENK_INFO",cNotNullValue);
	}

	public String get_ROHSTOFF_GESCHENK_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ROHSTOFF_GESCHENK_INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ROHSTOFF_GESCHENK_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROHSTOFF_GESCHENK_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROHSTOFF_GESCHENK_INFO", calNewValueFormated);
	}
		public String get_SOMMERGESCHENK_cUF() throws myException
	{
		return this.get_UnFormatedValue("SOMMERGESCHENK");
	}

	public String get_SOMMERGESCHENK_cF() throws myException
	{
		return this.get_FormatedValue("SOMMERGESCHENK");	
	}

	public String get_SOMMERGESCHENK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SOMMERGESCHENK");
	}

	public String get_SOMMERGESCHENK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SOMMERGESCHENK",cNotNullValue);
	}

	public String get_SOMMERGESCHENK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SOMMERGESCHENK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SOMMERGESCHENK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SOMMERGESCHENK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SOMMERGESCHENK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SOMMERGESCHENK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SOMMERGESCHENK", calNewValueFormated);
	}
		public boolean is_SOMMERGESCHENK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SOMMERGESCHENK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SOMMERGESCHENK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SOMMERGESCHENK_cUF_NN("N").equals("N"))
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
	
	
	public String get_WEIHNACHTSGESCHENK_cUF() throws myException
	{
		return this.get_UnFormatedValue("WEIHNACHTSGESCHENK");
	}

	public String get_WEIHNACHTSGESCHENK_cF() throws myException
	{
		return this.get_FormatedValue("WEIHNACHTSGESCHENK");	
	}

	public String get_WEIHNACHTSGESCHENK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WEIHNACHTSGESCHENK");
	}

	public String get_WEIHNACHTSGESCHENK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WEIHNACHTSGESCHENK",cNotNullValue);
	}

	public String get_WEIHNACHTSGESCHENK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WEIHNACHTSGESCHENK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WEIHNACHTSGESCHENK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WEIHNACHTSGESCHENK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WEIHNACHTSGESCHENK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WEIHNACHTSGESCHENK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WEIHNACHTSGESCHENK", calNewValueFormated);
	}
		public boolean is_WEIHNACHTSGESCHENK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_WEIHNACHTSGESCHENK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_WEIHNACHTSGESCHENK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_WEIHNACHTSGESCHENK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ASP_ABNAHMEANGEBOT",MyRECORD.DATATYPES.YESNO);
	put("ASP_ANGEBOT",MyRECORD.DATATYPES.YESNO);
	put("ASP_EK_KONTRAKT",MyRECORD.DATATYPES.YESNO);
	put("ASP_FIBU",MyRECORD.DATATYPES.YESNO);
	put("ASP_GUTSCHRIFT",MyRECORD.DATATYPES.YESNO);
	put("ASP_RECHNUNG",MyRECORD.DATATYPES.YESNO);
	put("ASP_TRANSPORT",MyRECORD.DATATYPES.YESNO);
	put("ASP_VK_KONTRAKT",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GESCHENK_KALENDER",MyRECORD.DATATYPES.YESNO);
	put("GESCHENK_SEKT",MyRECORD.DATATYPES.YESNO);
	put("GESCHENK_SPARGEL",MyRECORD.DATATYPES.YESNO);
	put("GESCHENK_WEIN",MyRECORD.DATATYPES.YESNO);
	put("ID_ADRESSE_BASIS",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_MITARBEITER",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MITARBEITER",MyRECORD.DATATYPES.NUMBER);
	put("ID_MITARBEITERTYP",MyRECORD.DATATYPES.NUMBER);
	put("ID_MITARBEITERTYP2",MyRECORD.DATATYPES.NUMBER);
	put("ID_MITARBEITERTYP3",MyRECORD.DATATYPES.NUMBER);
	put("ID_MITARBEITERTYP4",MyRECORD.DATATYPES.NUMBER);
	put("IST_ANSPRECHPARTNER",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("ROHSTOFF_GESCHENK_INFO",MyRECORD.DATATYPES.TEXT);
	put("SOMMERGESCHENK",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("WEIHNACHTSGESCHENK",MyRECORD.DATATYPES.YESNO);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_MITARBEITER.HM_FIELDS;	
	}

}
