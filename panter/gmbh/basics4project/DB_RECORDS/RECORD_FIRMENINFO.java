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

public class RECORD_FIRMENINFO extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_FIRMENINFO";
    public static String IDFIELD   = "ID_FIRMENINFO";
    

	//erzeugen eines RECORDNEW_JT_FIRMENINFO - felds
	private RECORDNEW_FIRMENINFO   recNEW = null;

    private _TAB  tab = _TAB.firmeninfo;  



	public RECORD_FIRMENINFO() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_FIRMENINFO");
	}


	public RECORD_FIRMENINFO(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FIRMENINFO");
	}



	public RECORD_FIRMENINFO(RECORD_FIRMENINFO recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FIRMENINFO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIRMENINFO.TABLENAME);
	}


	//2014-04-03
	public RECORD_FIRMENINFO(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FIRMENINFO");
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIRMENINFO.TABLENAME);
	}




	public RECORD_FIRMENINFO(long lID_Unformated) throws myException
	{
		super("JT_FIRMENINFO","ID_FIRMENINFO",""+lID_Unformated);
		this.set_TABLE_NAME("JT_FIRMENINFO");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIRMENINFO.TABLENAME);
	}

	public RECORD_FIRMENINFO(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_FIRMENINFO");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIRMENINFO", "ID_FIRMENINFO="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIRMENINFO", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIRMENINFO.TABLENAME);
	}
	
	

	public RECORD_FIRMENINFO(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_FIRMENINFO","ID_FIRMENINFO",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_FIRMENINFO");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIRMENINFO.TABLENAME);

	}


	public RECORD_FIRMENINFO(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FIRMENINFO");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIRMENINFO", "ID_FIRMENINFO="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIRMENINFO", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIRMENINFO.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_FIRMENINFO();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_FIRMENINFO.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_FIRMENINFO";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_FIRMENINFO_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_FIRMENINFO_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FIRMENINFO was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FIRMENINFO", bibE2.cTO(), "ID_FIRMENINFO="+this.get_ID_FIRMENINFO_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FIRMENINFO was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FIRMENINFO", bibE2.cTO(), "ID_FIRMENINFO="+this.get_ID_FIRMENINFO_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_FIRMENINFO="+this.get_ID_FIRMENINFO_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_FIRMENINFO="+this.get_ID_FIRMENINFO_cUF();
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
			if (S.isFull(this.get_ID_FIRMENINFO_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIRMENINFO", "ID_FIRMENINFO="+this.get_ID_FIRMENINFO_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_FIRMENINFO get_RECORDNEW_FIRMENINFO() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_FIRMENINFO();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_FIRMENINFO(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_FIRMENINFO create_Instance() throws myException {
		return new RECORD_FIRMENINFO();
	}
	
	public static RECORD_FIRMENINFO create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_FIRMENINFO(Conn);
    }

	public static RECORD_FIRMENINFO create_Instance(RECORD_FIRMENINFO recordOrig) {
		return new RECORD_FIRMENINFO(recordOrig);
    }

	public static RECORD_FIRMENINFO create_Instance(long lID_Unformated) throws myException {
		return new RECORD_FIRMENINFO(lID_Unformated);
    }

	public static RECORD_FIRMENINFO create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_FIRMENINFO(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_FIRMENINFO create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_FIRMENINFO(lID_Unformated, Conn);
	}

	public static RECORD_FIRMENINFO create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_FIRMENINFO(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_FIRMENINFO create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_FIRMENINFO(recordOrig);	    
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
    public RECORD_FIRMENINFO build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_FIRMENINFO="+this.get_ID_FIRMENINFO_cUF());
      }
      //return new RECORD_FIRMENINFO(this.get_cSQL_4_Build());
      RECORD_FIRMENINFO rec = new RECORD_FIRMENINFO();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_FIRMENINFO
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_FIRMENINFO-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_FIRMENINFO get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_FIRMENINFO_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_FIRMENINFO  recNew = new RECORDNEW_FIRMENINFO();
		
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
    public RECORD_FIRMENINFO set_recordNew(RECORDNEW_FIRMENINFO recnew) throws myException {
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
	
	



		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse = null;




		private RECORD_BRANCHE UP_RECORD_BRANCHE_id_branche = null;




		private RECORD_EAK_BRANCHE UP_RECORD_EAK_BRANCHE_id_eak_branche = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung1 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung2 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung3 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung1 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung2 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung3 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung1 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung2 = null;




		private RECORD_KREDITLIMIT_BEDINGUNG UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung3 = null;




		private RECORD_RECHTSFORM UP_RECORD_RECHTSFORM_id_rechtsform = null;




		private RECORD_ZAHLUNGSMEDIUM UP_RECORD_ZAHLUNGSMEDIUM_id_zahlungsmedium = null;






	
	/**
	 * References the Field ID_ADRESSE
	 * Falls keine this.get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse = new RECORD_ADRESSE(this.get_ID_ADRESSE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse;
	}
	





	
	/**
	 * References the Field ID_BRANCHE
	 * Falls keine this.get_ID_BRANCHE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BRANCHE get_UP_RECORD_BRANCHE_id_branche() throws myException
	{
		if (S.isEmpty(this.get_ID_BRANCHE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BRANCHE_id_branche==null)
		{
			this.UP_RECORD_BRANCHE_id_branche = new RECORD_BRANCHE(this.get_ID_BRANCHE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BRANCHE_id_branche;
	}
	





	
	/**
	 * References the Field ID_EAK_BRANCHE
	 * Falls keine this.get_ID_EAK_BRANCHE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_EAK_BRANCHE get_UP_RECORD_EAK_BRANCHE_id_eak_branche() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_BRANCHE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_EAK_BRANCHE_id_eak_branche==null)
		{
			this.UP_RECORD_EAK_BRANCHE_id_eak_branche = new RECORD_EAK_BRANCHE(this.get_ID_EAK_BRANCHE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_EAK_BRANCHE_id_eak_branche;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT_BEDINGUNG1
	 * Falls keine this.get_ID_KREDITLIMIT_BEDINGUNG1_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung1() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT_BEDINGUNG1_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung1==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung1 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT_BEDINGUNG1_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung1;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT_BEDINGUNG2
	 * Falls keine this.get_ID_KREDITLIMIT_BEDINGUNG2_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung2() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT_BEDINGUNG2_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung2==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung2 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT_BEDINGUNG2_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung2;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT_BEDINGUNG3
	 * Falls keine this.get_ID_KREDITLIMIT_BEDINGUNG3_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung3() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT_BEDINGUNG3_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung3==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung3 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT_BEDINGUNG3_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit_bedingung3;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT2_BEDINGUNG1
	 * Falls keine this.get_ID_KREDITLIMIT2_BEDINGUNG1_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung1() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT2_BEDINGUNG1_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung1==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung1 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT2_BEDINGUNG1_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung1;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT2_BEDINGUNG2
	 * Falls keine this.get_ID_KREDITLIMIT2_BEDINGUNG2_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung2() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT2_BEDINGUNG2_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung2==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung2 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT2_BEDINGUNG2_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung2;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT2_BEDINGUNG3
	 * Falls keine this.get_ID_KREDITLIMIT2_BEDINGUNG3_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung3() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT2_BEDINGUNG3_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung3==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung3 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT2_BEDINGUNG3_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit2_bedingung3;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT3_BEDINGUNG1
	 * Falls keine this.get_ID_KREDITLIMIT3_BEDINGUNG1_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung1() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT3_BEDINGUNG1_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung1==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung1 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT3_BEDINGUNG1_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung1;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT3_BEDINGUNG2
	 * Falls keine this.get_ID_KREDITLIMIT3_BEDINGUNG2_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung2() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT3_BEDINGUNG2_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung2==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung2 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT3_BEDINGUNG2_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung2;
	}
	





	
	/**
	 * References the Field ID_KREDITLIMIT3_BEDINGUNG3
	 * Falls keine this.get_ID_KREDITLIMIT3_BEDINGUNG3_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_KREDITLIMIT_BEDINGUNG get_UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung3() throws myException
	{
		if (S.isEmpty(this.get_ID_KREDITLIMIT3_BEDINGUNG3_cUF()))
			return null;
	
	
		if (this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung3==null)
		{
			this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung3 = new RECORD_KREDITLIMIT_BEDINGUNG(this.get_ID_KREDITLIMIT3_BEDINGUNG3_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_KREDITLIMIT_BEDINGUNG_id_kreditlimit3_bedingung3;
	}
	





	
	/**
	 * References the Field ID_RECHTSFORM
	 * Falls keine this.get_ID_RECHTSFORM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_RECHTSFORM get_UP_RECORD_RECHTSFORM_id_rechtsform() throws myException
	{
		if (S.isEmpty(this.get_ID_RECHTSFORM_cUF()))
			return null;
	
	
		if (this.UP_RECORD_RECHTSFORM_id_rechtsform==null)
		{
			this.UP_RECORD_RECHTSFORM_id_rechtsform = new RECORD_RECHTSFORM(this.get_ID_RECHTSFORM_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_RECHTSFORM_id_rechtsform;
	}
	





	
	/**
	 * References the Field ID_ZAHLUNGSMEDIUM
	 * Falls keine this.get_ID_ZAHLUNGSMEDIUM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ZAHLUNGSMEDIUM get_UP_RECORD_ZAHLUNGSMEDIUM_id_zahlungsmedium() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSMEDIUM_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ZAHLUNGSMEDIUM_id_zahlungsmedium==null)
		{
			this.UP_RECORD_ZAHLUNGSMEDIUM_id_zahlungsmedium = new RECORD_ZAHLUNGSMEDIUM(this.get_ID_ZAHLUNGSMEDIUM_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ZAHLUNGSMEDIUM_id_zahlungsmedium;
	}
	

	public static String FIELD__ABLADEMENGE_FUER_GUTSCHRIFT = "ABLADEMENGE_FUER_GUTSCHRIFT";
	public static String FIELD__AKONTO = "AKONTO";
	public static String FIELD__AQUISITIONSKOSTEN = "AQUISITIONSKOSTEN";
	public static String FIELD__BESCHREIBUNG = "BESCHREIBUNG";
	public static String FIELD__BESUCHSRYTHMUS = "BESUCHSRYTHMUS";
	public static String FIELD__BETRIEBSNUMMER_SAA = "BETRIEBSNUMMER_SAA";
	public static String FIELD__DEBITOR_NUMMER = "DEBITOR_NUMMER";
	public static String FIELD__DEBITOR_NUMMER_ALT = "DEBITOR_NUMMER_ALT";
	public static String FIELD__DOKUMENTKOPIEN = "DOKUMENTKOPIEN";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FBAM_NUR_INTERN = "FBAM_NUR_INTERN";
	public static String FIELD__FIRMA = "FIRMA";
	public static String FIELD__FIRMA_OHNE_USTID = "FIRMA_OHNE_USTID";
	public static String FIELD__FORDERUNGSVERRECHNUNG = "FORDERUNGSVERRECHNUNG";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GRUENDUNGSDATUM = "GRUENDUNGSDATUM";
	public static String FIELD__HANDELSREGISTER = "HANDELSREGISTER";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_BRANCHE = "ID_BRANCHE";
	public static String FIELD__ID_EAK_BRANCHE = "ID_EAK_BRANCHE";
	public static String FIELD__ID_FIRMENINFO = "ID_FIRMENINFO";
	public static String FIELD__ID_KREDITLIMIT2_BEDINGUNG1 = "ID_KREDITLIMIT2_BEDINGUNG1";
	public static String FIELD__ID_KREDITLIMIT2_BEDINGUNG2 = "ID_KREDITLIMIT2_BEDINGUNG2";
	public static String FIELD__ID_KREDITLIMIT2_BEDINGUNG3 = "ID_KREDITLIMIT2_BEDINGUNG3";
	public static String FIELD__ID_KREDITLIMIT3_BEDINGUNG1 = "ID_KREDITLIMIT3_BEDINGUNG1";
	public static String FIELD__ID_KREDITLIMIT3_BEDINGUNG2 = "ID_KREDITLIMIT3_BEDINGUNG2";
	public static String FIELD__ID_KREDITLIMIT3_BEDINGUNG3 = "ID_KREDITLIMIT3_BEDINGUNG3";
	public static String FIELD__ID_KREDITLIMIT_BEDINGUNG1 = "ID_KREDITLIMIT_BEDINGUNG1";
	public static String FIELD__ID_KREDITLIMIT_BEDINGUNG2 = "ID_KREDITLIMIT_BEDINGUNG2";
	public static String FIELD__ID_KREDITLIMIT_BEDINGUNG3 = "ID_KREDITLIMIT_BEDINGUNG3";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_RECHTSFORM = "ID_RECHTSFORM";
	public static String FIELD__ID_ZAHLUNGSMEDIUM = "ID_ZAHLUNGSMEDIUM";
	public static String FIELD__INNERGEMEIN_LIEF_EU = "INNERGEMEIN_LIEF_EU";
	public static String FIELD__KEINE_MAHNUNGEN = "KEINE_MAHNUNGEN";
	public static String FIELD__KREDITBETRAG_INTERN = "KREDITBETRAG_INTERN";
	public static String FIELD__KREDITBETRAG_INTERN_VALID_TO = "KREDITBETRAG_INTERN_VALID_TO";
	public static String FIELD__KREDITLIMIT = "KREDITLIMIT";
	public static String FIELD__KREDITLIMIT2 = "KREDITLIMIT2";
	public static String FIELD__KREDITLIMIT2_BIS = "KREDITLIMIT2_BIS";
	public static String FIELD__KREDITLIMIT2_VON = "KREDITLIMIT2_VON";
	public static String FIELD__KREDITLIMIT3 = "KREDITLIMIT3";
	public static String FIELD__KREDITLIMIT3_BIS = "KREDITLIMIT3_BIS";
	public static String FIELD__KREDITLIMIT3_VON = "KREDITLIMIT3_VON";
	public static String FIELD__KREDITLIMIT_BIS = "KREDITLIMIT_BIS";
	public static String FIELD__KREDITLIMIT_VON = "KREDITLIMIT_VON";
	public static String FIELD__KREDITOR_NUMMER = "KREDITOR_NUMMER";
	public static String FIELD__KREDITOR_NUMMER_ALT = "KREDITOR_NUMMER_ALT";
	public static String FIELD__KREDITSTAND = "KREDITSTAND";
	public static String FIELD__KREDITVERS_NR = "KREDITVERS_NR";
	public static String FIELD__LADEMENGE_FUER_RECHNUNG = "LADEMENGE_FUER_RECHNUNG";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERMENGE_MAX = "LIEFERMENGE_MAX";
	public static String FIELD__LIEFERMENGE_SCHNITT = "LIEFERMENGE_SCHNITT";
	public static String FIELD__OEFFNUNGSZEITEN = "OEFFNUNGSZEITEN";
	public static String FIELD__OHNE_STEUER_WARENAUSGANG = "OHNE_STEUER_WARENAUSGANG";
	public static String FIELD__OHNE_STEUER_WARENEINGANG = "OHNE_STEUER_WARENEINGANG";
	public static String FIELD__PRIVAT = "PRIVAT";
	public static String FIELD__PRIVAT_MIT_USTID = "PRIVAT_MIT_USTID";
	public static String FIELD__SCHECKDRUCK_JN = "SCHECKDRUCK_JN";
	public static String FIELD__STAMMKAPITAL = "STAMMKAPITAL";
	public static String FIELD__STEUERNUMMER = "STEUERNUMMER";
	public static String FIELD__STEUERNUMMER_STATT_UST = "STEUERNUMMER_STATT_UST";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__UMSATZSTEUERID = "UMSATZSTEUERID";
	public static String FIELD__UMSATZSTEUERLKZ = "UMSATZSTEUERLKZ";
	public static String FIELD__VERLAENGERT_EIGENTUM_VORBEHALT = "VERLAENGERT_EIGENTUM_VORBEHALT";
	public static String FIELD__VERSICHANFR_DAT = "VERSICHANFR_DAT";
	public static String FIELD__VERSICHANFR_SUMME = "VERSICHANFR_SUMME";
	public static String FIELD__VERSICH_MELDEFRIST_TAGE = "VERSICH_MELDEFRIST_TAGE";
	public static String FIELD__VERWERTUNGSVERFAHREN = "VERWERTUNGSVERFAHREN";
	public static String FIELD__VORSTEUERABZUG_RC_INLAND = "VORSTEUERABZUG_RC_INLAND";
	public static String FIELD__ZAHLANGESTELLTE_AM = "ZAHLANGESTELLTE_AM";
	public static String FIELD__ZAHL_ANGESTELLTE = "ZAHL_ANGESTELLTE";


	public String get_ABLADEMENGE_FUER_GUTSCHRIFT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABLADEMENGE_FUER_GUTSCHRIFT");
	}

	public String get_ABLADEMENGE_FUER_GUTSCHRIFT_cF() throws myException
	{
		return this.get_FormatedValue("ABLADEMENGE_FUER_GUTSCHRIFT");	
	}

	public String get_ABLADEMENGE_FUER_GUTSCHRIFT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABLADEMENGE_FUER_GUTSCHRIFT");
	}

	public String get_ABLADEMENGE_FUER_GUTSCHRIFT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABLADEMENGE_FUER_GUTSCHRIFT",cNotNullValue);
	}

	public String get_ABLADEMENGE_FUER_GUTSCHRIFT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABLADEMENGE_FUER_GUTSCHRIFT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_FUER_GUTSCHRIFT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEMENGE_FUER_GUTSCHRIFT", calNewValueFormated);
	}
		public boolean is_ABLADEMENGE_FUER_GUTSCHRIFT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABLADEMENGE_FUER_GUTSCHRIFT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABLADEMENGE_FUER_GUTSCHRIFT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABLADEMENGE_FUER_GUTSCHRIFT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_AKONTO_cUF() throws myException
	{
		return this.get_UnFormatedValue("AKONTO");
	}

	public String get_AKONTO_cF() throws myException
	{
		return this.get_FormatedValue("AKONTO");	
	}

	public String get_AKONTO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AKONTO");
	}

	public String get_AKONTO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AKONTO",cNotNullValue);
	}

	public String get_AKONTO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AKONTO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AKONTO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AKONTO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AKONTO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AKONTO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKONTO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AKONTO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKONTO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKONTO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKONTO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKONTO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKONTO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKONTO", calNewValueFormated);
	}
		public boolean is_AKONTO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_AKONTO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_AKONTO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_AKONTO_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_AQUISITIONSKOSTEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("AQUISITIONSKOSTEN");
	}

	public String get_AQUISITIONSKOSTEN_cF() throws myException
	{
		return this.get_FormatedValue("AQUISITIONSKOSTEN");	
	}

	public String get_AQUISITIONSKOSTEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AQUISITIONSKOSTEN");
	}

	public String get_AQUISITIONSKOSTEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AQUISITIONSKOSTEN",cNotNullValue);
	}

	public String get_AQUISITIONSKOSTEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AQUISITIONSKOSTEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AQUISITIONSKOSTEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AQUISITIONSKOSTEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AQUISITIONSKOSTEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AQUISITIONSKOSTEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AQUISITIONSKOSTEN", calNewValueFormated);
	}
		public Long get_AQUISITIONSKOSTEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("AQUISITIONSKOSTEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_AQUISITIONSKOSTEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("AQUISITIONSKOSTEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_AQUISITIONSKOSTEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("AQUISITIONSKOSTEN").getDoubleValue();
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
	public String get_AQUISITIONSKOSTEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AQUISITIONSKOSTEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_AQUISITIONSKOSTEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AQUISITIONSKOSTEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_AQUISITIONSKOSTEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("AQUISITIONSKOSTEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_AQUISITIONSKOSTEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("AQUISITIONSKOSTEN").getBigDecimalValue();
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
	
	
	public String get_BESCHREIBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG");
	}

	public String get_BESCHREIBUNG_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG");	
	}

	public String get_BESCHREIBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIBUNG");
	}

	public String get_BESCHREIBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG",cNotNullValue);
	}

	public String get_BESCHREIBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", calNewValueFormated);
	}
		public String get_BESUCHSRYTHMUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESUCHSRYTHMUS");
	}

	public String get_BESUCHSRYTHMUS_cF() throws myException
	{
		return this.get_FormatedValue("BESUCHSRYTHMUS");	
	}

	public String get_BESUCHSRYTHMUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESUCHSRYTHMUS");
	}

	public String get_BESUCHSRYTHMUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESUCHSRYTHMUS",cNotNullValue);
	}

	public String get_BESUCHSRYTHMUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESUCHSRYTHMUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESUCHSRYTHMUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESUCHSRYTHMUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESUCHSRYTHMUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESUCHSRYTHMUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESUCHSRYTHMUS", calNewValueFormated);
	}
		public Long get_BESUCHSRYTHMUS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("BESUCHSRYTHMUS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_BESUCHSRYTHMUS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("BESUCHSRYTHMUS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_BESUCHSRYTHMUS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("BESUCHSRYTHMUS").getDoubleValue();
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
	public String get_BESUCHSRYTHMUS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BESUCHSRYTHMUS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_BESUCHSRYTHMUS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BESUCHSRYTHMUS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_BESUCHSRYTHMUS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("BESUCHSRYTHMUS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_BESUCHSRYTHMUS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("BESUCHSRYTHMUS").getBigDecimalValue();
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
	
	
	public String get_BETRIEBSNUMMER_SAA_cUF() throws myException
	{
		return this.get_UnFormatedValue("BETRIEBSNUMMER_SAA");
	}

	public String get_BETRIEBSNUMMER_SAA_cF() throws myException
	{
		return this.get_FormatedValue("BETRIEBSNUMMER_SAA");	
	}

	public String get_BETRIEBSNUMMER_SAA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BETRIEBSNUMMER_SAA");
	}

	public String get_BETRIEBSNUMMER_SAA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BETRIEBSNUMMER_SAA",cNotNullValue);
	}

	public String get_BETRIEBSNUMMER_SAA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BETRIEBSNUMMER_SAA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BETRIEBSNUMMER_SAA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BETRIEBSNUMMER_SAA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETRIEBSNUMMER_SAA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETRIEBSNUMMER_SAA", calNewValueFormated);
	}
		public String get_DEBITOR_NUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEBITOR_NUMMER");
	}

	public String get_DEBITOR_NUMMER_cF() throws myException
	{
		return this.get_FormatedValue("DEBITOR_NUMMER");	
	}

	public String get_DEBITOR_NUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEBITOR_NUMMER");
	}

	public String get_DEBITOR_NUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEBITOR_NUMMER",cNotNullValue);
	}

	public String get_DEBITOR_NUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEBITOR_NUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEBITOR_NUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEBITOR_NUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEBITOR_NUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER", calNewValueFormated);
	}
		public String get_DEBITOR_NUMMER_ALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEBITOR_NUMMER_ALT");
	}

	public String get_DEBITOR_NUMMER_ALT_cF() throws myException
	{
		return this.get_FormatedValue("DEBITOR_NUMMER_ALT");	
	}

	public String get_DEBITOR_NUMMER_ALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEBITOR_NUMMER_ALT");
	}

	public String get_DEBITOR_NUMMER_ALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEBITOR_NUMMER_ALT",cNotNullValue);
	}

	public String get_DEBITOR_NUMMER_ALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEBITOR_NUMMER_ALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEBITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEBITOR_NUMMER_ALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEBITOR_NUMMER_ALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEBITOR_NUMMER_ALT", calNewValueFormated);
	}
		public String get_DOKUMENTKOPIEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("DOKUMENTKOPIEN");
	}

	public String get_DOKUMENTKOPIEN_cF() throws myException
	{
		return this.get_FormatedValue("DOKUMENTKOPIEN");	
	}

	public String get_DOKUMENTKOPIEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DOKUMENTKOPIEN");
	}

	public String get_DOKUMENTKOPIEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DOKUMENTKOPIEN",cNotNullValue);
	}

	public String get_DOKUMENTKOPIEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DOKUMENTKOPIEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DOKUMENTKOPIEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DOKUMENTKOPIEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DOKUMENTKOPIEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOKUMENTKOPIEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOKUMENTKOPIEN", calNewValueFormated);
	}
		public Long get_DOKUMENTKOPIEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DOKUMENTKOPIEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DOKUMENTKOPIEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DOKUMENTKOPIEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DOKUMENTKOPIEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DOKUMENTKOPIEN").getDoubleValue();
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
	public String get_DOKUMENTKOPIEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DOKUMENTKOPIEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_DOKUMENTKOPIEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DOKUMENTKOPIEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_DOKUMENTKOPIEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DOKUMENTKOPIEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DOKUMENTKOPIEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DOKUMENTKOPIEN").getBigDecimalValue();
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
		public String get_FBAM_NUR_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FBAM_NUR_INTERN");
	}

	public String get_FBAM_NUR_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("FBAM_NUR_INTERN");	
	}

	public String get_FBAM_NUR_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FBAM_NUR_INTERN");
	}

	public String get_FBAM_NUR_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FBAM_NUR_INTERN",cNotNullValue);
	}

	public String get_FBAM_NUR_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FBAM_NUR_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FBAM_NUR_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FBAM_NUR_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FBAM_NUR_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FBAM_NUR_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FBAM_NUR_INTERN", calNewValueFormated);
	}
		public boolean is_FBAM_NUR_INTERN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FBAM_NUR_INTERN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FBAM_NUR_INTERN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FBAM_NUR_INTERN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_FIRMA_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIRMA");
	}

	public String get_FIRMA_cF() throws myException
	{
		return this.get_FormatedValue("FIRMA");	
	}

	public String get_FIRMA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIRMA");
	}

	public String get_FIRMA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIRMA",cNotNullValue);
	}

	public String get_FIRMA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIRMA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIRMA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIRMA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIRMA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIRMA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMA", calNewValueFormated);
	}
		public boolean is_FIRMA_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FIRMA_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FIRMA_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FIRMA_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_FIRMA_OHNE_USTID_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIRMA_OHNE_USTID");
	}

	public String get_FIRMA_OHNE_USTID_cF() throws myException
	{
		return this.get_FormatedValue("FIRMA_OHNE_USTID");	
	}

	public String get_FIRMA_OHNE_USTID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIRMA_OHNE_USTID");
	}

	public String get_FIRMA_OHNE_USTID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIRMA_OHNE_USTID",cNotNullValue);
	}

	public String get_FIRMA_OHNE_USTID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIRMA_OHNE_USTID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIRMA_OHNE_USTID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIRMA_OHNE_USTID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIRMA_OHNE_USTID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIRMA_OHNE_USTID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIRMA_OHNE_USTID", calNewValueFormated);
	}
		public boolean is_FIRMA_OHNE_USTID_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FIRMA_OHNE_USTID_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FIRMA_OHNE_USTID_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FIRMA_OHNE_USTID_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_FORDERUNGSVERRECHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("FORDERUNGSVERRECHNUNG");
	}

	public String get_FORDERUNGSVERRECHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("FORDERUNGSVERRECHNUNG");	
	}

	public String get_FORDERUNGSVERRECHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FORDERUNGSVERRECHNUNG");
	}

	public String get_FORDERUNGSVERRECHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FORDERUNGSVERRECHNUNG",cNotNullValue);
	}

	public String get_FORDERUNGSVERRECHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FORDERUNGSVERRECHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FORDERUNGSVERRECHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FORDERUNGSVERRECHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORDERUNGSVERRECHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORDERUNGSVERRECHNUNG", calNewValueFormated);
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
		public String get_GRUENDUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("GRUENDUNGSDATUM");
	}

	public String get_GRUENDUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("GRUENDUNGSDATUM");	
	}

	public String get_GRUENDUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GRUENDUNGSDATUM");
	}

	public String get_GRUENDUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GRUENDUNGSDATUM",cNotNullValue);
	}

	public String get_GRUENDUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GRUENDUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GRUENDUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GRUENDUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GRUENDUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUENDUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUENDUNGSDATUM", calNewValueFormated);
	}
		public String get_HANDELSREGISTER_cUF() throws myException
	{
		return this.get_UnFormatedValue("HANDELSREGISTER");
	}

	public String get_HANDELSREGISTER_cF() throws myException
	{
		return this.get_FormatedValue("HANDELSREGISTER");	
	}

	public String get_HANDELSREGISTER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HANDELSREGISTER");
	}

	public String get_HANDELSREGISTER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HANDELSREGISTER",cNotNullValue);
	}

	public String get_HANDELSREGISTER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HANDELSREGISTER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HANDELSREGISTER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HANDELSREGISTER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HANDELSREGISTER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HANDELSREGISTER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDELSREGISTER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDELSREGISTER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HANDELSREGISTER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HANDELSREGISTER", calNewValueFormated);
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
	
	
	public String get_ID_BRANCHE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BRANCHE");
	}

	public String get_ID_BRANCHE_cF() throws myException
	{
		return this.get_FormatedValue("ID_BRANCHE");	
	}

	public String get_ID_BRANCHE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BRANCHE");
	}

	public String get_ID_BRANCHE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BRANCHE",cNotNullValue);
	}

	public String get_ID_BRANCHE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BRANCHE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BRANCHE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BRANCHE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BRANCHE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BRANCHE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BRANCHE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BRANCHE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BRANCHE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BRANCHE", calNewValueFormated);
	}
		public Long get_ID_BRANCHE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BRANCHE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BRANCHE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BRANCHE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BRANCHE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BRANCHE").getDoubleValue();
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
	public String get_ID_BRANCHE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BRANCHE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BRANCHE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BRANCHE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BRANCHE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BRANCHE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BRANCHE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BRANCHE").getBigDecimalValue();
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
	
	
	public String get_ID_EAK_BRANCHE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_BRANCHE");
	}

	public String get_ID_EAK_BRANCHE_cF() throws myException
	{
		return this.get_FormatedValue("ID_EAK_BRANCHE");	
	}

	public String get_ID_EAK_BRANCHE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EAK_BRANCHE");
	}

	public String get_ID_EAK_BRANCHE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_BRANCHE",cNotNullValue);
	}

	public String get_ID_EAK_BRANCHE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EAK_BRANCHE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EAK_BRANCHE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_BRANCHE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EAK_BRANCHE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_BRANCHE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_BRANCHE", calNewValueFormated);
	}
		public Long get_ID_EAK_BRANCHE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EAK_BRANCHE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EAK_BRANCHE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EAK_BRANCHE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EAK_BRANCHE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EAK_BRANCHE").getDoubleValue();
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
	public String get_ID_EAK_BRANCHE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_BRANCHE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EAK_BRANCHE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_BRANCHE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EAK_BRANCHE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_BRANCHE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EAK_BRANCHE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_BRANCHE").getBigDecimalValue();
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
	
	
	public String get_ID_FIRMENINFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_FIRMENINFO");
	}

	public String get_ID_FIRMENINFO_cF() throws myException
	{
		return this.get_FormatedValue("ID_FIRMENINFO");	
	}

	public String get_ID_FIRMENINFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_FIRMENINFO");
	}

	public String get_ID_FIRMENINFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_FIRMENINFO",cNotNullValue);
	}

	public String get_ID_FIRMENINFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_FIRMENINFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_FIRMENINFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_FIRMENINFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_FIRMENINFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIRMENINFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIRMENINFO", calNewValueFormated);
	}
		public Long get_ID_FIRMENINFO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_FIRMENINFO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_FIRMENINFO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_FIRMENINFO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_FIRMENINFO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_FIRMENINFO").getDoubleValue();
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
	public String get_ID_FIRMENINFO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIRMENINFO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_FIRMENINFO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIRMENINFO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_FIRMENINFO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIRMENINFO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_FIRMENINFO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIRMENINFO").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT2_BEDINGUNG1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT2_BEDINGUNG1");
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG1_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT2_BEDINGUNG1");	
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT2_BEDINGUNG1");
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT2_BEDINGUNG1",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT2_BEDINGUNG1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG1", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT2_BEDINGUNG1_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG1").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT2_BEDINGUNG1_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG1").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT2_BEDINGUNG1_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG1").getDoubleValue();
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
	public String get_ID_KREDITLIMIT2_BEDINGUNG1_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT2_BEDINGUNG1_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT2_BEDINGUNG1_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT2_BEDINGUNG1_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT2_BEDINGUNG1_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG1").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT2_BEDINGUNG1_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG1").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT2_BEDINGUNG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT2_BEDINGUNG2");
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG2_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT2_BEDINGUNG2");	
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT2_BEDINGUNG2");
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT2_BEDINGUNG2",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT2_BEDINGUNG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG2", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT2_BEDINGUNG2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT2_BEDINGUNG2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT2_BEDINGUNG2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG2").getDoubleValue();
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
	public String get_ID_KREDITLIMIT2_BEDINGUNG2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT2_BEDINGUNG2_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT2_BEDINGUNG2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT2_BEDINGUNG2_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT2_BEDINGUNG2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT2_BEDINGUNG2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG2").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT2_BEDINGUNG3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT2_BEDINGUNG3");
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG3_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT2_BEDINGUNG3");	
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT2_BEDINGUNG3");
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT2_BEDINGUNG3",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT2_BEDINGUNG3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT2_BEDINGUNG3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT2_BEDINGUNG3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT2_BEDINGUNG3", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT2_BEDINGUNG3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT2_BEDINGUNG3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT2_BEDINGUNG3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG3").getDoubleValue();
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
	public String get_ID_KREDITLIMIT2_BEDINGUNG3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT2_BEDINGUNG3_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT2_BEDINGUNG3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT2_BEDINGUNG3_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT2_BEDINGUNG3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT2_BEDINGUNG3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT2_BEDINGUNG3").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT3_BEDINGUNG1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT3_BEDINGUNG1");
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG1_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT3_BEDINGUNG1");	
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT3_BEDINGUNG1");
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT3_BEDINGUNG1",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT3_BEDINGUNG1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG1", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT3_BEDINGUNG1_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG1").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT3_BEDINGUNG1_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG1").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT3_BEDINGUNG1_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG1").getDoubleValue();
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
	public String get_ID_KREDITLIMIT3_BEDINGUNG1_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT3_BEDINGUNG1_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT3_BEDINGUNG1_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT3_BEDINGUNG1_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT3_BEDINGUNG1_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG1").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT3_BEDINGUNG1_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG1").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT3_BEDINGUNG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT3_BEDINGUNG2");
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG2_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT3_BEDINGUNG2");	
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT3_BEDINGUNG2");
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT3_BEDINGUNG2",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT3_BEDINGUNG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG2", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT3_BEDINGUNG2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT3_BEDINGUNG2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT3_BEDINGUNG2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG2").getDoubleValue();
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
	public String get_ID_KREDITLIMIT3_BEDINGUNG2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT3_BEDINGUNG2_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT3_BEDINGUNG2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT3_BEDINGUNG2_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT3_BEDINGUNG2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT3_BEDINGUNG2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG2").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT3_BEDINGUNG3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT3_BEDINGUNG3");
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG3_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT3_BEDINGUNG3");	
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT3_BEDINGUNG3");
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT3_BEDINGUNG3",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT3_BEDINGUNG3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT3_BEDINGUNG3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT3_BEDINGUNG3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT3_BEDINGUNG3", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT3_BEDINGUNG3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT3_BEDINGUNG3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT3_BEDINGUNG3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG3").getDoubleValue();
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
	public String get_ID_KREDITLIMIT3_BEDINGUNG3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT3_BEDINGUNG3_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT3_BEDINGUNG3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT3_BEDINGUNG3_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT3_BEDINGUNG3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT3_BEDINGUNG3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT3_BEDINGUNG3").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT_BEDINGUNG1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT_BEDINGUNG1");
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG1_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT_BEDINGUNG1");	
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT_BEDINGUNG1");
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT_BEDINGUNG1",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT_BEDINGUNG1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG1", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT_BEDINGUNG1_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT_BEDINGUNG1").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT_BEDINGUNG1_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT_BEDINGUNG1").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT_BEDINGUNG1_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT_BEDINGUNG1").getDoubleValue();
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
	public String get_ID_KREDITLIMIT_BEDINGUNG1_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT_BEDINGUNG1_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT_BEDINGUNG1_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT_BEDINGUNG1_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT_BEDINGUNG1_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT_BEDINGUNG1").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT_BEDINGUNG1_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT_BEDINGUNG1").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT_BEDINGUNG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT_BEDINGUNG2");
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG2_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT_BEDINGUNG2");	
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT_BEDINGUNG2");
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT_BEDINGUNG2",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT_BEDINGUNG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG2", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT_BEDINGUNG2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT_BEDINGUNG2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT_BEDINGUNG2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT_BEDINGUNG2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT_BEDINGUNG2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT_BEDINGUNG2").getDoubleValue();
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
	public String get_ID_KREDITLIMIT_BEDINGUNG2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT_BEDINGUNG2_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT_BEDINGUNG2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT_BEDINGUNG2_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT_BEDINGUNG2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT_BEDINGUNG2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT_BEDINGUNG2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT_BEDINGUNG2").getBigDecimalValue();
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
	
	
	public String get_ID_KREDITLIMIT_BEDINGUNG3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT_BEDINGUNG3");
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG3_cF() throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT_BEDINGUNG3");	
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_KREDITLIMIT_BEDINGUNG3");
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_KREDITLIMIT_BEDINGUNG3",cNotNullValue);
	}

	public String get_ID_KREDITLIMIT_BEDINGUNG3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_KREDITLIMIT_BEDINGUNG3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_KREDITLIMIT_BEDINGUNG3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_KREDITLIMIT_BEDINGUNG3", calNewValueFormated);
	}
		public Long get_ID_KREDITLIMIT_BEDINGUNG3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_KREDITLIMIT_BEDINGUNG3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_KREDITLIMIT_BEDINGUNG3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT_BEDINGUNG3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_KREDITLIMIT_BEDINGUNG3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_KREDITLIMIT_BEDINGUNG3").getDoubleValue();
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
	public String get_ID_KREDITLIMIT_BEDINGUNG3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT_BEDINGUNG3_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_KREDITLIMIT_BEDINGUNG3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_KREDITLIMIT_BEDINGUNG3_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_KREDITLIMIT_BEDINGUNG3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT_BEDINGUNG3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_KREDITLIMIT_BEDINGUNG3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_KREDITLIMIT_BEDINGUNG3").getBigDecimalValue();
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
	
	
	public String get_ID_RECHTSFORM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_RECHTSFORM");
	}

	public String get_ID_RECHTSFORM_cF() throws myException
	{
		return this.get_FormatedValue("ID_RECHTSFORM");	
	}

	public String get_ID_RECHTSFORM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_RECHTSFORM");
	}

	public String get_ID_RECHTSFORM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_RECHTSFORM",cNotNullValue);
	}

	public String get_ID_RECHTSFORM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_RECHTSFORM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_RECHTSFORM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_RECHTSFORM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_RECHTSFORM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_RECHTSFORM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_RECHTSFORM", calNewValueFormated);
	}
		public Long get_ID_RECHTSFORM_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_RECHTSFORM").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_RECHTSFORM_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_RECHTSFORM").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_RECHTSFORM_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_RECHTSFORM").getDoubleValue();
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
	public String get_ID_RECHTSFORM_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_RECHTSFORM_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_RECHTSFORM_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_RECHTSFORM_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_RECHTSFORM_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_RECHTSFORM").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_RECHTSFORM_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_RECHTSFORM").getBigDecimalValue();
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
	
	
	public String get_ID_ZAHLUNGSMEDIUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSMEDIUM");
	}

	public String get_ID_ZAHLUNGSMEDIUM_cF() throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSMEDIUM");	
	}

	public String get_ID_ZAHLUNGSMEDIUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ZAHLUNGSMEDIUM");
	}

	public String get_ID_ZAHLUNGSMEDIUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSMEDIUM",cNotNullValue);
	}

	public String get_ID_ZAHLUNGSMEDIUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSMEDIUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSMEDIUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSMEDIUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSMEDIUM", calNewValueFormated);
	}
		public Long get_ID_ZAHLUNGSMEDIUM_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ZAHLUNGSMEDIUM").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ZAHLUNGSMEDIUM_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSMEDIUM").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ZAHLUNGSMEDIUM_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSMEDIUM").getDoubleValue();
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
	public String get_ID_ZAHLUNGSMEDIUM_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSMEDIUM_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ZAHLUNGSMEDIUM_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSMEDIUM_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ZAHLUNGSMEDIUM_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSMEDIUM").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ZAHLUNGSMEDIUM_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSMEDIUM").getBigDecimalValue();
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
	
	
	public String get_INNERGEMEIN_LIEF_EU_cUF() throws myException
	{
		return this.get_UnFormatedValue("INNERGEMEIN_LIEF_EU");
	}

	public String get_INNERGEMEIN_LIEF_EU_cF() throws myException
	{
		return this.get_FormatedValue("INNERGEMEIN_LIEF_EU");	
	}

	public String get_INNERGEMEIN_LIEF_EU_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INNERGEMEIN_LIEF_EU");
	}

	public String get_INNERGEMEIN_LIEF_EU_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INNERGEMEIN_LIEF_EU",cNotNullValue);
	}

	public String get_INNERGEMEIN_LIEF_EU_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INNERGEMEIN_LIEF_EU",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INNERGEMEIN_LIEF_EU(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INNERGEMEIN_LIEF_EU", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INNERGEMEIN_LIEF_EU_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INNERGEMEIN_LIEF_EU", calNewValueFormated);
	}
		public boolean is_INNERGEMEIN_LIEF_EU_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_INNERGEMEIN_LIEF_EU_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_INNERGEMEIN_LIEF_EU_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_INNERGEMEIN_LIEF_EU_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KEINE_MAHNUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("KEINE_MAHNUNGEN");
	}

	public String get_KEINE_MAHNUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("KEINE_MAHNUNGEN");	
	}

	public String get_KEINE_MAHNUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KEINE_MAHNUNGEN");
	}

	public String get_KEINE_MAHNUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KEINE_MAHNUNGEN",cNotNullValue);
	}

	public String get_KEINE_MAHNUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KEINE_MAHNUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KEINE_MAHNUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KEINE_MAHNUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KEINE_MAHNUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEINE_MAHNUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEINE_MAHNUNGEN", calNewValueFormated);
	}
		public boolean is_KEINE_MAHNUNGEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEINE_MAHNUNGEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KEINE_MAHNUNGEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEINE_MAHNUNGEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KREDITBETRAG_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITBETRAG_INTERN");
	}

	public String get_KREDITBETRAG_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("KREDITBETRAG_INTERN");	
	}

	public String get_KREDITBETRAG_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITBETRAG_INTERN");
	}

	public String get_KREDITBETRAG_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITBETRAG_INTERN",cNotNullValue);
	}

	public String get_KREDITBETRAG_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITBETRAG_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITBETRAG_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITBETRAG_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITBETRAG_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN", calNewValueFormated);
	}
		public Double get_KREDITBETRAG_INTERN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KREDITBETRAG_INTERN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KREDITBETRAG_INTERN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KREDITBETRAG_INTERN").getDoubleValue();
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
	public String get_KREDITBETRAG_INTERN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITBETRAG_INTERN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KREDITBETRAG_INTERN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITBETRAG_INTERN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KREDITBETRAG_INTERN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITBETRAG_INTERN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KREDITBETRAG_INTERN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITBETRAG_INTERN").getBigDecimalValue();
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
	
	
	public String get_KREDITBETRAG_INTERN_VALID_TO_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITBETRAG_INTERN_VALID_TO");
	}

	public String get_KREDITBETRAG_INTERN_VALID_TO_cF() throws myException
	{
		return this.get_FormatedValue("KREDITBETRAG_INTERN_VALID_TO");	
	}

	public String get_KREDITBETRAG_INTERN_VALID_TO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITBETRAG_INTERN_VALID_TO");
	}

	public String get_KREDITBETRAG_INTERN_VALID_TO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITBETRAG_INTERN_VALID_TO",cNotNullValue);
	}

	public String get_KREDITBETRAG_INTERN_VALID_TO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITBETRAG_INTERN_VALID_TO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITBETRAG_INTERN_VALID_TO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITBETRAG_INTERN_VALID_TO", calNewValueFormated);
	}
		public String get_KREDITLIMIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT");
	}

	public String get_KREDITLIMIT_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT");	
	}

	public String get_KREDITLIMIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT");
	}

	public String get_KREDITLIMIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT",cNotNullValue);
	}

	public String get_KREDITLIMIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT", calNewValueFormated);
	}
		public Long get_KREDITLIMIT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("KREDITLIMIT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_KREDITLIMIT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KREDITLIMIT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KREDITLIMIT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KREDITLIMIT").getDoubleValue();
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
	public String get_KREDITLIMIT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITLIMIT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KREDITLIMIT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITLIMIT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KREDITLIMIT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITLIMIT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KREDITLIMIT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITLIMIT").getBigDecimalValue();
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
	
	
	public String get_KREDITLIMIT2_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT2");
	}

	public String get_KREDITLIMIT2_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT2");	
	}

	public String get_KREDITLIMIT2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT2");
	}

	public String get_KREDITLIMIT2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT2",cNotNullValue);
	}

	public String get_KREDITLIMIT2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2", calNewValueFormated);
	}
		public Long get_KREDITLIMIT2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("KREDITLIMIT2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_KREDITLIMIT2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KREDITLIMIT2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KREDITLIMIT2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KREDITLIMIT2").getDoubleValue();
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
	public String get_KREDITLIMIT2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITLIMIT2_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KREDITLIMIT2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITLIMIT2_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KREDITLIMIT2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITLIMIT2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KREDITLIMIT2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITLIMIT2").getBigDecimalValue();
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
	
	
	public String get_KREDITLIMIT2_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT2_BIS");
	}

	public String get_KREDITLIMIT2_BIS_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT2_BIS");	
	}

	public String get_KREDITLIMIT2_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT2_BIS");
	}

	public String get_KREDITLIMIT2_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT2_BIS",cNotNullValue);
	}

	public String get_KREDITLIMIT2_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT2_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT2_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT2_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT2_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2_BIS", calNewValueFormated);
	}
		public String get_KREDITLIMIT2_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT2_VON");
	}

	public String get_KREDITLIMIT2_VON_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT2_VON");	
	}

	public String get_KREDITLIMIT2_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT2_VON");
	}

	public String get_KREDITLIMIT2_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT2_VON",cNotNullValue);
	}

	public String get_KREDITLIMIT2_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT2_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT2_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT2_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT2_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT2_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT2_VON", calNewValueFormated);
	}
		public String get_KREDITLIMIT3_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT3");
	}

	public String get_KREDITLIMIT3_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT3");	
	}

	public String get_KREDITLIMIT3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT3");
	}

	public String get_KREDITLIMIT3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT3",cNotNullValue);
	}

	public String get_KREDITLIMIT3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3", calNewValueFormated);
	}
		public Long get_KREDITLIMIT3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("KREDITLIMIT3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_KREDITLIMIT3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KREDITLIMIT3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KREDITLIMIT3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KREDITLIMIT3").getDoubleValue();
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
	public String get_KREDITLIMIT3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITLIMIT3_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KREDITLIMIT3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITLIMIT3_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KREDITLIMIT3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITLIMIT3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KREDITLIMIT3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITLIMIT3").getBigDecimalValue();
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
	
	
	public String get_KREDITLIMIT3_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT3_BIS");
	}

	public String get_KREDITLIMIT3_BIS_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT3_BIS");	
	}

	public String get_KREDITLIMIT3_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT3_BIS");
	}

	public String get_KREDITLIMIT3_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT3_BIS",cNotNullValue);
	}

	public String get_KREDITLIMIT3_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT3_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT3_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT3_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT3_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3_BIS", calNewValueFormated);
	}
		public String get_KREDITLIMIT3_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT3_VON");
	}

	public String get_KREDITLIMIT3_VON_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT3_VON");	
	}

	public String get_KREDITLIMIT3_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT3_VON");
	}

	public String get_KREDITLIMIT3_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT3_VON",cNotNullValue);
	}

	public String get_KREDITLIMIT3_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT3_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT3_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT3_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT3_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT3_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT3_VON", calNewValueFormated);
	}
		public String get_KREDITLIMIT_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT_BIS");
	}

	public String get_KREDITLIMIT_BIS_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT_BIS");	
	}

	public String get_KREDITLIMIT_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT_BIS");
	}

	public String get_KREDITLIMIT_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT_BIS",cNotNullValue);
	}

	public String get_KREDITLIMIT_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT_BIS", calNewValueFormated);
	}
		public String get_KREDITLIMIT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT_VON");
	}

	public String get_KREDITLIMIT_VON_cF() throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT_VON");	
	}

	public String get_KREDITLIMIT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITLIMIT_VON");
	}

	public String get_KREDITLIMIT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITLIMIT_VON",cNotNullValue);
	}

	public String get_KREDITLIMIT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITLIMIT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITLIMIT_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITLIMIT_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITLIMIT_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITLIMIT_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITLIMIT_VON", calNewValueFormated);
	}
		public String get_KREDITOR_NUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITOR_NUMMER");
	}

	public String get_KREDITOR_NUMMER_cF() throws myException
	{
		return this.get_FormatedValue("KREDITOR_NUMMER");	
	}

	public String get_KREDITOR_NUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITOR_NUMMER");
	}

	public String get_KREDITOR_NUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITOR_NUMMER",cNotNullValue);
	}

	public String get_KREDITOR_NUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITOR_NUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITOR_NUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITOR_NUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITOR_NUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER", calNewValueFormated);
	}
		public String get_KREDITOR_NUMMER_ALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITOR_NUMMER_ALT");
	}

	public String get_KREDITOR_NUMMER_ALT_cF() throws myException
	{
		return this.get_FormatedValue("KREDITOR_NUMMER_ALT");	
	}

	public String get_KREDITOR_NUMMER_ALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITOR_NUMMER_ALT");
	}

	public String get_KREDITOR_NUMMER_ALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITOR_NUMMER_ALT",cNotNullValue);
	}

	public String get_KREDITOR_NUMMER_ALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITOR_NUMMER_ALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITOR_NUMMER_ALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITOR_NUMMER_ALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITOR_NUMMER_ALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITOR_NUMMER_ALT", calNewValueFormated);
	}
		public String get_KREDITSTAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITSTAND");
	}

	public String get_KREDITSTAND_cF() throws myException
	{
		return this.get_FormatedValue("KREDITSTAND");	
	}

	public String get_KREDITSTAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITSTAND");
	}

	public String get_KREDITSTAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITSTAND",cNotNullValue);
	}

	public String get_KREDITSTAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITSTAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITSTAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITSTAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITSTAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITSTAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITSTAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITSTAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITSTAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITSTAND", calNewValueFormated);
	}
		public Long get_KREDITSTAND_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("KREDITSTAND").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_KREDITSTAND_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KREDITSTAND").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KREDITSTAND_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KREDITSTAND").getDoubleValue();
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
	public String get_KREDITSTAND_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITSTAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KREDITSTAND_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KREDITSTAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KREDITSTAND_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITSTAND").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KREDITSTAND_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KREDITSTAND").getBigDecimalValue();
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
	
	
	public String get_KREDITVERS_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("KREDITVERS_NR");
	}

	public String get_KREDITVERS_NR_cF() throws myException
	{
		return this.get_FormatedValue("KREDITVERS_NR");	
	}

	public String get_KREDITVERS_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KREDITVERS_NR");
	}

	public String get_KREDITVERS_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KREDITVERS_NR",cNotNullValue);
	}

	public String get_KREDITVERS_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KREDITVERS_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KREDITVERS_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KREDITVERS_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KREDITVERS_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KREDITVERS_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITVERS_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITVERS_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KREDITVERS_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KREDITVERS_NR", calNewValueFormated);
	}
		public String get_LADEMENGE_FUER_RECHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("LADEMENGE_FUER_RECHNUNG");
	}

	public String get_LADEMENGE_FUER_RECHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("LADEMENGE_FUER_RECHNUNG");	
	}

	public String get_LADEMENGE_FUER_RECHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LADEMENGE_FUER_RECHNUNG");
	}

	public String get_LADEMENGE_FUER_RECHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LADEMENGE_FUER_RECHNUNG",cNotNullValue);
	}

	public String get_LADEMENGE_FUER_RECHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LADEMENGE_FUER_RECHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LADEMENGE_FUER_RECHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_FUER_RECHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADEMENGE_FUER_RECHNUNG", calNewValueFormated);
	}
		public boolean is_LADEMENGE_FUER_RECHNUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_LADEMENGE_FUER_RECHNUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_LADEMENGE_FUER_RECHNUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_LADEMENGE_FUER_RECHNUNG_cUF_NN("N").equals("N"))
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
		public String get_LIEFERMENGE_MAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERMENGE_MAX");
	}

	public String get_LIEFERMENGE_MAX_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERMENGE_MAX");	
	}

	public String get_LIEFERMENGE_MAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERMENGE_MAX");
	}

	public String get_LIEFERMENGE_MAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERMENGE_MAX",cNotNullValue);
	}

	public String get_LIEFERMENGE_MAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERMENGE_MAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERMENGE_MAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERMENGE_MAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERMENGE_MAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_MAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERMENGE_MAX", calNewValueFormated);
	}
		public Long get_LIEFERMENGE_MAX_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LIEFERMENGE_MAX").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LIEFERMENGE_MAX_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LIEFERMENGE_MAX").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LIEFERMENGE_MAX_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LIEFERMENGE_MAX").getDoubleValue();
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
	public String get_LIEFERMENGE_MAX_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LIEFERMENGE_MAX_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LIEFERMENGE_MAX_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LIEFERMENGE_MAX_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LIEFERMENGE_MAX_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LIEFERMENGE_MAX").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LIEFERMENGE_MAX_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LIEFERMENGE_MAX").getBigDecimalValue();
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
	
	
	public String get_LIEFERMENGE_SCHNITT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERMENGE_SCHNITT");
	}

	public String get_LIEFERMENGE_SCHNITT_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERMENGE_SCHNITT");	
	}

	public String get_LIEFERMENGE_SCHNITT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERMENGE_SCHNITT");
	}

	public String get_LIEFERMENGE_SCHNITT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERMENGE_SCHNITT",cNotNullValue);
	}

	public String get_LIEFERMENGE_SCHNITT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERMENGE_SCHNITT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERMENGE_SCHNITT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERMENGE_SCHNITT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERMENGE_SCHNITT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERMENGE_SCHNITT", calNewValueFormated);
	}
		public Long get_LIEFERMENGE_SCHNITT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LIEFERMENGE_SCHNITT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LIEFERMENGE_SCHNITT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LIEFERMENGE_SCHNITT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LIEFERMENGE_SCHNITT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LIEFERMENGE_SCHNITT").getDoubleValue();
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
	public String get_LIEFERMENGE_SCHNITT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LIEFERMENGE_SCHNITT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LIEFERMENGE_SCHNITT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LIEFERMENGE_SCHNITT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LIEFERMENGE_SCHNITT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LIEFERMENGE_SCHNITT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LIEFERMENGE_SCHNITT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LIEFERMENGE_SCHNITT").getBigDecimalValue();
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
	
	
	public String get_OEFFNUNGSZEITEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN");
	}

	public String get_OEFFNUNGSZEITEN_cF() throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN");	
	}

	public String get_OEFFNUNGSZEITEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OEFFNUNGSZEITEN");
	}

	public String get_OEFFNUNGSZEITEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN",cNotNullValue);
	}

	public String get_OEFFNUNGSZEITEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", calNewValueFormated);
	}
		public String get_OHNE_STEUER_WARENAUSGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("OHNE_STEUER_WARENAUSGANG");
	}

	public String get_OHNE_STEUER_WARENAUSGANG_cF() throws myException
	{
		return this.get_FormatedValue("OHNE_STEUER_WARENAUSGANG");	
	}

	public String get_OHNE_STEUER_WARENAUSGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OHNE_STEUER_WARENAUSGANG");
	}

	public String get_OHNE_STEUER_WARENAUSGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OHNE_STEUER_WARENAUSGANG",cNotNullValue);
	}

	public String get_OHNE_STEUER_WARENAUSGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OHNE_STEUER_WARENAUSGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OHNE_STEUER_WARENAUSGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENAUSGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENAUSGANG", calNewValueFormated);
	}
		public boolean is_OHNE_STEUER_WARENAUSGANG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_STEUER_WARENAUSGANG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OHNE_STEUER_WARENAUSGANG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_STEUER_WARENAUSGANG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OHNE_STEUER_WARENEINGANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("OHNE_STEUER_WARENEINGANG");
	}

	public String get_OHNE_STEUER_WARENEINGANG_cF() throws myException
	{
		return this.get_FormatedValue("OHNE_STEUER_WARENEINGANG");	
	}

	public String get_OHNE_STEUER_WARENEINGANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OHNE_STEUER_WARENEINGANG");
	}

	public String get_OHNE_STEUER_WARENEINGANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OHNE_STEUER_WARENEINGANG",cNotNullValue);
	}

	public String get_OHNE_STEUER_WARENEINGANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OHNE_STEUER_WARENEINGANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OHNE_STEUER_WARENEINGANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_STEUER_WARENEINGANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_STEUER_WARENEINGANG", calNewValueFormated);
	}
		public boolean is_OHNE_STEUER_WARENEINGANG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_STEUER_WARENEINGANG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OHNE_STEUER_WARENEINGANG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_STEUER_WARENEINGANG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRIVAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRIVAT");
	}

	public String get_PRIVAT_cF() throws myException
	{
		return this.get_FormatedValue("PRIVAT");	
	}

	public String get_PRIVAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRIVAT");
	}

	public String get_PRIVAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRIVAT",cNotNullValue);
	}

	public String get_PRIVAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRIVAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRIVAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT", calNewValueFormated);
	}
		public boolean is_PRIVAT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRIVAT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRIVAT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRIVAT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRIVAT_MIT_USTID_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRIVAT_MIT_USTID");
	}

	public String get_PRIVAT_MIT_USTID_cF() throws myException
	{
		return this.get_FormatedValue("PRIVAT_MIT_USTID");	
	}

	public String get_PRIVAT_MIT_USTID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRIVAT_MIT_USTID");
	}

	public String get_PRIVAT_MIT_USTID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRIVAT_MIT_USTID",cNotNullValue);
	}

	public String get_PRIVAT_MIT_USTID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRIVAT_MIT_USTID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRIVAT_MIT_USTID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRIVAT_MIT_USTID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRIVAT_MIT_USTID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_MIT_USTID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT_MIT_USTID", calNewValueFormated);
	}
		public boolean is_PRIVAT_MIT_USTID_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRIVAT_MIT_USTID_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRIVAT_MIT_USTID_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRIVAT_MIT_USTID_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SCHECKDRUCK_JN_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_JN");
	}

	public String get_SCHECKDRUCK_JN_cF() throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_JN");	
	}

	public String get_SCHECKDRUCK_JN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECKDRUCK_JN");
	}

	public String get_SCHECKDRUCK_JN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECKDRUCK_JN",cNotNullValue);
	}

	public String get_SCHECKDRUCK_JN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECKDRUCK_JN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECKDRUCK_JN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECKDRUCK_JN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECKDRUCK_JN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKDRUCK_JN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKDRUCK_JN", calNewValueFormated);
	}
		public boolean is_SCHECKDRUCK_JN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SCHECKDRUCK_JN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SCHECKDRUCK_JN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SCHECKDRUCK_JN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STAMMKAPITAL_cUF() throws myException
	{
		return this.get_UnFormatedValue("STAMMKAPITAL");
	}

	public String get_STAMMKAPITAL_cF() throws myException
	{
		return this.get_FormatedValue("STAMMKAPITAL");	
	}

	public String get_STAMMKAPITAL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STAMMKAPITAL");
	}

	public String get_STAMMKAPITAL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STAMMKAPITAL",cNotNullValue);
	}

	public String get_STAMMKAPITAL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STAMMKAPITAL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STAMMKAPITAL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STAMMKAPITAL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STAMMKAPITAL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STAMMKAPITAL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STAMMKAPITAL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STAMMKAPITAL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STAMMKAPITAL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STAMMKAPITAL", calNewValueFormated);
	}
		public Long get_STAMMKAPITAL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("STAMMKAPITAL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_STAMMKAPITAL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STAMMKAPITAL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STAMMKAPITAL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STAMMKAPITAL").getDoubleValue();
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
	public String get_STAMMKAPITAL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STAMMKAPITAL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_STAMMKAPITAL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STAMMKAPITAL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_STAMMKAPITAL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STAMMKAPITAL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STAMMKAPITAL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STAMMKAPITAL").getBigDecimalValue();
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
	
	
	public String get_STEUERNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERNUMMER");
	}

	public String get_STEUERNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("STEUERNUMMER");	
	}

	public String get_STEUERNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERNUMMER");
	}

	public String get_STEUERNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERNUMMER",cNotNullValue);
	}

	public String get_STEUERNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNUMMER", calNewValueFormated);
	}
		public String get_STEUERNUMMER_STATT_UST_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERNUMMER_STATT_UST");
	}

	public String get_STEUERNUMMER_STATT_UST_cF() throws myException
	{
		return this.get_FormatedValue("STEUERNUMMER_STATT_UST");	
	}

	public String get_STEUERNUMMER_STATT_UST_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERNUMMER_STATT_UST");
	}

	public String get_STEUERNUMMER_STATT_UST_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERNUMMER_STATT_UST",cNotNullValue);
	}

	public String get_STEUERNUMMER_STATT_UST_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERNUMMER_STATT_UST",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERNUMMER_STATT_UST(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERNUMMER_STATT_UST", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERNUMMER_STATT_UST_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERNUMMER_STATT_UST", calNewValueFormated);
	}
		public boolean is_STEUERNUMMER_STATT_UST_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STEUERNUMMER_STATT_UST_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STEUERNUMMER_STATT_UST_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STEUERNUMMER_STATT_UST_cUF_NN("N").equals("N"))
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
		public String get_UMSATZSTEUERLKZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERLKZ");
	}

	public String get_UMSATZSTEUERLKZ_cF() throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERLKZ");	
	}

	public String get_UMSATZSTEUERLKZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UMSATZSTEUERLKZ");
	}

	public String get_UMSATZSTEUERLKZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERLKZ",cNotNullValue);
	}

	public String get_UMSATZSTEUERLKZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERLKZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UMSATZSTEUERLKZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERLKZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UMSATZSTEUERLKZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", calNewValueFormated);
	}
		public String get_VERLAENGERT_EIGENTUM_VORBEHALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERLAENGERT_EIGENTUM_VORBEHALT");
	}

	public String get_VERLAENGERT_EIGENTUM_VORBEHALT_cF() throws myException
	{
		return this.get_FormatedValue("VERLAENGERT_EIGENTUM_VORBEHALT");	
	}

	public String get_VERLAENGERT_EIGENTUM_VORBEHALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERLAENGERT_EIGENTUM_VORBEHALT");
	}

	public String get_VERLAENGERT_EIGENTUM_VORBEHALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERLAENGERT_EIGENTUM_VORBEHALT",cNotNullValue);
	}

	public String get_VERLAENGERT_EIGENTUM_VORBEHALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERLAENGERT_EIGENTUM_VORBEHALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERLAENGERT_EIGENTUM_VORBEHALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERLAENGERT_EIGENTUM_VORBEHALT", calNewValueFormated);
	}
		public String get_VERSICHANFR_DAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERSICHANFR_DAT");
	}

	public String get_VERSICHANFR_DAT_cF() throws myException
	{
		return this.get_FormatedValue("VERSICHANFR_DAT");	
	}

	public String get_VERSICHANFR_DAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERSICHANFR_DAT");
	}

	public String get_VERSICHANFR_DAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERSICHANFR_DAT",cNotNullValue);
	}

	public String get_VERSICHANFR_DAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERSICHANFR_DAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERSICHANFR_DAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERSICHANFR_DAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERSICHANFR_DAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_DAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICHANFR_DAT", calNewValueFormated);
	}
		public String get_VERSICHANFR_SUMME_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERSICHANFR_SUMME");
	}

	public String get_VERSICHANFR_SUMME_cF() throws myException
	{
		return this.get_FormatedValue("VERSICHANFR_SUMME");	
	}

	public String get_VERSICHANFR_SUMME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERSICHANFR_SUMME");
	}

	public String get_VERSICHANFR_SUMME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERSICHANFR_SUMME",cNotNullValue);
	}

	public String get_VERSICHANFR_SUMME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERSICHANFR_SUMME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERSICHANFR_SUMME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERSICHANFR_SUMME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERSICHANFR_SUMME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICHANFR_SUMME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICHANFR_SUMME", calNewValueFormated);
	}
		public Long get_VERSICHANFR_SUMME_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("VERSICHANFR_SUMME").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_VERSICHANFR_SUMME_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("VERSICHANFR_SUMME").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_VERSICHANFR_SUMME_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("VERSICHANFR_SUMME").getDoubleValue();
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
	public String get_VERSICHANFR_SUMME_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERSICHANFR_SUMME_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_VERSICHANFR_SUMME_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERSICHANFR_SUMME_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_VERSICHANFR_SUMME_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("VERSICHANFR_SUMME").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_VERSICHANFR_SUMME_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("VERSICHANFR_SUMME").getBigDecimalValue();
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
	
	
	public String get_VERSICH_MELDEFRIST_TAGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERSICH_MELDEFRIST_TAGE");
	}

	public String get_VERSICH_MELDEFRIST_TAGE_cF() throws myException
	{
		return this.get_FormatedValue("VERSICH_MELDEFRIST_TAGE");	
	}

	public String get_VERSICH_MELDEFRIST_TAGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERSICH_MELDEFRIST_TAGE");
	}

	public String get_VERSICH_MELDEFRIST_TAGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERSICH_MELDEFRIST_TAGE",cNotNullValue);
	}

	public String get_VERSICH_MELDEFRIST_TAGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERSICH_MELDEFRIST_TAGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERSICH_MELDEFRIST_TAGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSICH_MELDEFRIST_TAGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSICH_MELDEFRIST_TAGE", calNewValueFormated);
	}
		public Long get_VERSICH_MELDEFRIST_TAGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("VERSICH_MELDEFRIST_TAGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_VERSICH_MELDEFRIST_TAGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("VERSICH_MELDEFRIST_TAGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_VERSICH_MELDEFRIST_TAGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("VERSICH_MELDEFRIST_TAGE").getDoubleValue();
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
	public String get_VERSICH_MELDEFRIST_TAGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERSICH_MELDEFRIST_TAGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_VERSICH_MELDEFRIST_TAGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERSICH_MELDEFRIST_TAGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_VERSICH_MELDEFRIST_TAGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("VERSICH_MELDEFRIST_TAGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_VERSICH_MELDEFRIST_TAGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("VERSICH_MELDEFRIST_TAGE").getBigDecimalValue();
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
	
	
	public String get_VERWERTUNGSVERFAHREN_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSVERFAHREN");
	}

	public String get_VERWERTUNGSVERFAHREN_cF() throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSVERFAHREN");	
	}

	public String get_VERWERTUNGSVERFAHREN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERWERTUNGSVERFAHREN");
	}

	public String get_VERWERTUNGSVERFAHREN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSVERFAHREN",cNotNullValue);
	}

	public String get_VERWERTUNGSVERFAHREN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSVERFAHREN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSVERFAHREN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERWERTUNGSVERFAHREN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSVERFAHREN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSVERFAHREN", calNewValueFormated);
	}
		public String get_VORSTEUERABZUG_RC_INLAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORSTEUERABZUG_RC_INLAND");
	}

	public String get_VORSTEUERABZUG_RC_INLAND_cF() throws myException
	{
		return this.get_FormatedValue("VORSTEUERABZUG_RC_INLAND");	
	}

	public String get_VORSTEUERABZUG_RC_INLAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORSTEUERABZUG_RC_INLAND");
	}

	public String get_VORSTEUERABZUG_RC_INLAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORSTEUERABZUG_RC_INLAND",cNotNullValue);
	}

	public String get_VORSTEUERABZUG_RC_INLAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORSTEUERABZUG_RC_INLAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORSTEUERABZUG_RC_INLAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORSTEUERABZUG_RC_INLAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORSTEUERABZUG_RC_INLAND", calNewValueFormated);
	}
		public boolean is_VORSTEUERABZUG_RC_INLAND_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_VORSTEUERABZUG_RC_INLAND_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_VORSTEUERABZUG_RC_INLAND_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_VORSTEUERABZUG_RC_INLAND_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZAHLANGESTELLTE_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLANGESTELLTE_AM");
	}

	public String get_ZAHLANGESTELLTE_AM_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLANGESTELLTE_AM");	
	}

	public String get_ZAHLANGESTELLTE_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLANGESTELLTE_AM");
	}

	public String get_ZAHLANGESTELLTE_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLANGESTELLTE_AM",cNotNullValue);
	}

	public String get_ZAHLANGESTELLTE_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLANGESTELLTE_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLANGESTELLTE_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLANGESTELLTE_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLANGESTELLTE_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLANGESTELLTE_AM", calNewValueFormated);
	}
		public String get_ZAHL_ANGESTELLTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHL_ANGESTELLTE");
	}

	public String get_ZAHL_ANGESTELLTE_cF() throws myException
	{
		return this.get_FormatedValue("ZAHL_ANGESTELLTE");	
	}

	public String get_ZAHL_ANGESTELLTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHL_ANGESTELLTE");
	}

	public String get_ZAHL_ANGESTELLTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHL_ANGESTELLTE",cNotNullValue);
	}

	public String get_ZAHL_ANGESTELLTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHL_ANGESTELLTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHL_ANGESTELLTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHL_ANGESTELLTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHL_ANGESTELLTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_ANGESTELLTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_ANGESTELLTE", calNewValueFormated);
	}
		public Long get_ZAHL_ANGESTELLTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ZAHL_ANGESTELLTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ZAHL_ANGESTELLTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAHL_ANGESTELLTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAHL_ANGESTELLTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAHL_ANGESTELLTE").getDoubleValue();
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
	public String get_ZAHL_ANGESTELLTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHL_ANGESTELLTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ZAHL_ANGESTELLTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHL_ANGESTELLTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ZAHL_ANGESTELLTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHL_ANGESTELLTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAHL_ANGESTELLTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHL_ANGESTELLTE").getBigDecimalValue();
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
	put("ABLADEMENGE_FUER_GUTSCHRIFT",MyRECORD.DATATYPES.YESNO);
	put("AKONTO",MyRECORD.DATATYPES.YESNO);
	put("AQUISITIONSKOSTEN",MyRECORD.DATATYPES.NUMBER);
	put("BESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("BESUCHSRYTHMUS",MyRECORD.DATATYPES.NUMBER);
	put("BETRIEBSNUMMER_SAA",MyRECORD.DATATYPES.TEXT);
	put("DEBITOR_NUMMER",MyRECORD.DATATYPES.TEXT);
	put("DEBITOR_NUMMER_ALT",MyRECORD.DATATYPES.TEXT);
	put("DOKUMENTKOPIEN",MyRECORD.DATATYPES.NUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FBAM_NUR_INTERN",MyRECORD.DATATYPES.YESNO);
	put("FIRMA",MyRECORD.DATATYPES.YESNO);
	put("FIRMA_OHNE_USTID",MyRECORD.DATATYPES.YESNO);
	put("FORDERUNGSVERRECHNUNG",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GRUENDUNGSDATUM",MyRECORD.DATATYPES.DATE);
	put("HANDELSREGISTER",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_BRANCHE",MyRECORD.DATATYPES.NUMBER);
	put("ID_EAK_BRANCHE",MyRECORD.DATATYPES.NUMBER);
	put("ID_FIRMENINFO",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT2_BEDINGUNG1",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT2_BEDINGUNG2",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT2_BEDINGUNG3",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT3_BEDINGUNG1",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT3_BEDINGUNG2",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT3_BEDINGUNG3",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT_BEDINGUNG1",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT_BEDINGUNG2",MyRECORD.DATATYPES.NUMBER);
	put("ID_KREDITLIMIT_BEDINGUNG3",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_RECHTSFORM",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZAHLUNGSMEDIUM",MyRECORD.DATATYPES.NUMBER);
	put("INNERGEMEIN_LIEF_EU",MyRECORD.DATATYPES.YESNO);
	put("KEINE_MAHNUNGEN",MyRECORD.DATATYPES.YESNO);
	put("KREDITBETRAG_INTERN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("KREDITBETRAG_INTERN_VALID_TO",MyRECORD.DATATYPES.DATE);
	put("KREDITLIMIT",MyRECORD.DATATYPES.NUMBER);
	put("KREDITLIMIT2",MyRECORD.DATATYPES.NUMBER);
	put("KREDITLIMIT2_BIS",MyRECORD.DATATYPES.DATE);
	put("KREDITLIMIT2_VON",MyRECORD.DATATYPES.DATE);
	put("KREDITLIMIT3",MyRECORD.DATATYPES.NUMBER);
	put("KREDITLIMIT3_BIS",MyRECORD.DATATYPES.DATE);
	put("KREDITLIMIT3_VON",MyRECORD.DATATYPES.DATE);
	put("KREDITLIMIT_BIS",MyRECORD.DATATYPES.DATE);
	put("KREDITLIMIT_VON",MyRECORD.DATATYPES.DATE);
	put("KREDITOR_NUMMER",MyRECORD.DATATYPES.TEXT);
	put("KREDITOR_NUMMER_ALT",MyRECORD.DATATYPES.TEXT);
	put("KREDITSTAND",MyRECORD.DATATYPES.NUMBER);
	put("KREDITVERS_NR",MyRECORD.DATATYPES.TEXT);
	put("LADEMENGE_FUER_RECHNUNG",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERMENGE_MAX",MyRECORD.DATATYPES.NUMBER);
	put("LIEFERMENGE_SCHNITT",MyRECORD.DATATYPES.NUMBER);
	put("OEFFNUNGSZEITEN",MyRECORD.DATATYPES.TEXT);
	put("OHNE_STEUER_WARENAUSGANG",MyRECORD.DATATYPES.YESNO);
	put("OHNE_STEUER_WARENEINGANG",MyRECORD.DATATYPES.YESNO);
	put("PRIVAT",MyRECORD.DATATYPES.YESNO);
	put("PRIVAT_MIT_USTID",MyRECORD.DATATYPES.YESNO);
	put("SCHECKDRUCK_JN",MyRECORD.DATATYPES.YESNO);
	put("STAMMKAPITAL",MyRECORD.DATATYPES.NUMBER);
	put("STEUERNUMMER",MyRECORD.DATATYPES.TEXT);
	put("STEUERNUMMER_STATT_UST",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("UMSATZSTEUERID",MyRECORD.DATATYPES.TEXT);
	put("UMSATZSTEUERLKZ",MyRECORD.DATATYPES.TEXT);
	put("VERLAENGERT_EIGENTUM_VORBEHALT",MyRECORD.DATATYPES.TEXT);
	put("VERSICHANFR_DAT",MyRECORD.DATATYPES.DATE);
	put("VERSICHANFR_SUMME",MyRECORD.DATATYPES.NUMBER);
	put("VERSICH_MELDEFRIST_TAGE",MyRECORD.DATATYPES.NUMBER);
	put("VERWERTUNGSVERFAHREN",MyRECORD.DATATYPES.TEXT);
	put("VORSTEUERABZUG_RC_INLAND",MyRECORD.DATATYPES.YESNO);
	put("ZAHLANGESTELLTE_AM",MyRECORD.DATATYPES.DATE);
	put("ZAHL_ANGESTELLTE",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_FIRMENINFO.HM_FIELDS;	
	}

}
