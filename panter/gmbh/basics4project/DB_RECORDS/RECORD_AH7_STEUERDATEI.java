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

public class RECORD_AH7_STEUERDATEI extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_AH7_STEUERDATEI";
    public static String IDFIELD   = "ID_AH7_STEUERDATEI";
    

	//erzeugen eines RECORDNEW_JT_AH7_STEUERDATEI - felds
	private RECORDNEW_AH7_STEUERDATEI   recNEW = null;

    private _TAB  tab = _TAB.ah7_steuerdatei;  



	public RECORD_AH7_STEUERDATEI() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");
	}


	public RECORD_AH7_STEUERDATEI(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");
	}



	public RECORD_AH7_STEUERDATEI(RECORD_AH7_STEUERDATEI recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_STEUERDATEI.TABLENAME);
	}


	//2014-04-03
	public RECORD_AH7_STEUERDATEI(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_STEUERDATEI.TABLENAME);
	}




	public RECORD_AH7_STEUERDATEI(long lID_Unformated) throws myException
	{
		super("JT_AH7_STEUERDATEI","ID_AH7_STEUERDATEI",""+lID_Unformated);
		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_STEUERDATEI.TABLENAME);
	}

	public RECORD_AH7_STEUERDATEI(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_STEUERDATEI", "ID_AH7_STEUERDATEI="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_STEUERDATEI", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_STEUERDATEI.TABLENAME);
	}
	
	

	public RECORD_AH7_STEUERDATEI(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_AH7_STEUERDATEI","ID_AH7_STEUERDATEI",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_STEUERDATEI.TABLENAME);

	}


	public RECORD_AH7_STEUERDATEI(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_AH7_STEUERDATEI");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_STEUERDATEI", "ID_AH7_STEUERDATEI="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_STEUERDATEI", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_STEUERDATEI.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_AH7_STEUERDATEI();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_AH7_STEUERDATEI.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_AH7_STEUERDATEI";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_AH7_STEUERDATEI_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_AH7_STEUERDATEI_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_AH7_STEUERDATEI was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_AH7_STEUERDATEI", bibE2.cTO(), "ID_AH7_STEUERDATEI="+this.get_ID_AH7_STEUERDATEI_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_AH7_STEUERDATEI was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_AH7_STEUERDATEI", bibE2.cTO(), "ID_AH7_STEUERDATEI="+this.get_ID_AH7_STEUERDATEI_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_AH7_STEUERDATEI="+this.get_ID_AH7_STEUERDATEI_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_AH7_STEUERDATEI="+this.get_ID_AH7_STEUERDATEI_cUF();
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
			if (S.isFull(this.get_ID_AH7_STEUERDATEI_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_STEUERDATEI", "ID_AH7_STEUERDATEI="+this.get_ID_AH7_STEUERDATEI_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_AH7_STEUERDATEI get_RECORDNEW_AH7_STEUERDATEI() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_AH7_STEUERDATEI();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_AH7_STEUERDATEI(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_AH7_STEUERDATEI create_Instance() throws myException {
		return new RECORD_AH7_STEUERDATEI();
	}
	
	public static RECORD_AH7_STEUERDATEI create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_AH7_STEUERDATEI(Conn);
    }

	public static RECORD_AH7_STEUERDATEI create_Instance(RECORD_AH7_STEUERDATEI recordOrig) {
		return new RECORD_AH7_STEUERDATEI(recordOrig);
    }

	public static RECORD_AH7_STEUERDATEI create_Instance(long lID_Unformated) throws myException {
		return new RECORD_AH7_STEUERDATEI(lID_Unformated);
    }

	public static RECORD_AH7_STEUERDATEI create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_AH7_STEUERDATEI(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_AH7_STEUERDATEI create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_AH7_STEUERDATEI(lID_Unformated, Conn);
	}

	public static RECORD_AH7_STEUERDATEI create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_AH7_STEUERDATEI(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_AH7_STEUERDATEI create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_AH7_STEUERDATEI(recordOrig);	    
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
    public RECORD_AH7_STEUERDATEI build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_AH7_STEUERDATEI="+this.get_ID_AH7_STEUERDATEI_cUF());
      }
      //return new RECORD_AH7_STEUERDATEI(this.get_cSQL_4_Build());
      RECORD_AH7_STEUERDATEI rec = new RECORD_AH7_STEUERDATEI();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_AH7_STEUERDATEI
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_AH7_STEUERDATEI-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_AH7_STEUERDATEI get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_AH7_STEUERDATEI_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_AH7_STEUERDATEI  recNew = new RECORDNEW_AH7_STEUERDATEI();
		
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
    public RECORD_AH7_STEUERDATEI set_recordNew(RECORDNEW_AH7_STEUERDATEI recnew) throws myException {
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
	
	



		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_geo_start = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_geo_ziel = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_jur_start = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_jur_ziel = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_1_abfallerzeuger = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_1_import_empfaenger = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_1_verbr_veranlasser = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_1_verwertungsanlage = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_2_abfallerzeuger = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_2_import_empfaenger = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_2_verbr_veranlasser = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_2_verwertungsanlage = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_3_abfallerzeuger = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_3_import_empfaenger = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_3_verbr_veranlasser = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_3_verwertungsanlage = null;




		private RECORD_AH7_PROFIL UP_RECORD_AH7_PROFIL_id_ah7_profil = null;






	
	/**
	 * References the Field ID_ADRESSE_GEO_START
	 * Falls keine this.get_ID_ADRESSE_GEO_START_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_geo_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_GEO_START_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_geo_start==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_geo_start = new RECORD_ADRESSE(this.get_ID_ADRESSE_GEO_START_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_geo_start;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_GEO_ZIEL
	 * Falls keine this.get_ID_ADRESSE_GEO_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_geo_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_GEO_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_geo_ziel==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_geo_ziel = new RECORD_ADRESSE(this.get_ID_ADRESSE_GEO_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_geo_ziel;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_JUR_START
	 * Falls keine this.get_ID_ADRESSE_JUR_START_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_jur_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_JUR_START_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_jur_start==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_jur_start = new RECORD_ADRESSE(this.get_ID_ADRESSE_JUR_START_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_jur_start;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_JUR_ZIEL
	 * Falls keine this.get_ID_ADRESSE_JUR_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_jur_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_JUR_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_jur_ziel==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_jur_ziel = new RECORD_ADRESSE(this.get_ID_ADRESSE_JUR_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_jur_ziel;
	}
	





	
	/**
	 * References the Field ID_1_ABFALLERZEUGER
	 * Falls keine this.get_ID_1_ABFALLERZEUGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_1_abfallerzeuger() throws myException
	{
		if (S.isEmpty(this.get_ID_1_ABFALLERZEUGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_1_abfallerzeuger==null)
		{
			this.UP_RECORD_ADRESSE_id_1_abfallerzeuger = new RECORD_ADRESSE(this.get_ID_1_ABFALLERZEUGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_1_abfallerzeuger;
	}
	





	
	/**
	 * References the Field ID_1_IMPORT_EMPFAENGER
	 * Falls keine this.get_ID_1_IMPORT_EMPFAENGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_1_import_empfaenger() throws myException
	{
		if (S.isEmpty(this.get_ID_1_IMPORT_EMPFAENGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_1_import_empfaenger==null)
		{
			this.UP_RECORD_ADRESSE_id_1_import_empfaenger = new RECORD_ADRESSE(this.get_ID_1_IMPORT_EMPFAENGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_1_import_empfaenger;
	}
	





	
	/**
	 * References the Field ID_1_VERBR_VERANLASSER
	 * Falls keine this.get_ID_1_VERBR_VERANLASSER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_1_verbr_veranlasser() throws myException
	{
		if (S.isEmpty(this.get_ID_1_VERBR_VERANLASSER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_1_verbr_veranlasser==null)
		{
			this.UP_RECORD_ADRESSE_id_1_verbr_veranlasser = new RECORD_ADRESSE(this.get_ID_1_VERBR_VERANLASSER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_1_verbr_veranlasser;
	}
	





	
	/**
	 * References the Field ID_1_VERWERTUNGSANLAGE
	 * Falls keine this.get_ID_1_VERWERTUNGSANLAGE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_1_verwertungsanlage() throws myException
	{
		if (S.isEmpty(this.get_ID_1_VERWERTUNGSANLAGE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_1_verwertungsanlage==null)
		{
			this.UP_RECORD_ADRESSE_id_1_verwertungsanlage = new RECORD_ADRESSE(this.get_ID_1_VERWERTUNGSANLAGE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_1_verwertungsanlage;
	}
	





	
	/**
	 * References the Field ID_2_ABFALLERZEUGER
	 * Falls keine this.get_ID_2_ABFALLERZEUGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_2_abfallerzeuger() throws myException
	{
		if (S.isEmpty(this.get_ID_2_ABFALLERZEUGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_2_abfallerzeuger==null)
		{
			this.UP_RECORD_ADRESSE_id_2_abfallerzeuger = new RECORD_ADRESSE(this.get_ID_2_ABFALLERZEUGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_2_abfallerzeuger;
	}
	





	
	/**
	 * References the Field ID_2_IMPORT_EMPFAENGER
	 * Falls keine this.get_ID_2_IMPORT_EMPFAENGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_2_import_empfaenger() throws myException
	{
		if (S.isEmpty(this.get_ID_2_IMPORT_EMPFAENGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_2_import_empfaenger==null)
		{
			this.UP_RECORD_ADRESSE_id_2_import_empfaenger = new RECORD_ADRESSE(this.get_ID_2_IMPORT_EMPFAENGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_2_import_empfaenger;
	}
	





	
	/**
	 * References the Field ID_2_VERBR_VERANLASSER
	 * Falls keine this.get_ID_2_VERBR_VERANLASSER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_2_verbr_veranlasser() throws myException
	{
		if (S.isEmpty(this.get_ID_2_VERBR_VERANLASSER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_2_verbr_veranlasser==null)
		{
			this.UP_RECORD_ADRESSE_id_2_verbr_veranlasser = new RECORD_ADRESSE(this.get_ID_2_VERBR_VERANLASSER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_2_verbr_veranlasser;
	}
	





	
	/**
	 * References the Field ID_2_VERWERTUNGSANLAGE
	 * Falls keine this.get_ID_2_VERWERTUNGSANLAGE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_2_verwertungsanlage() throws myException
	{
		if (S.isEmpty(this.get_ID_2_VERWERTUNGSANLAGE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_2_verwertungsanlage==null)
		{
			this.UP_RECORD_ADRESSE_id_2_verwertungsanlage = new RECORD_ADRESSE(this.get_ID_2_VERWERTUNGSANLAGE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_2_verwertungsanlage;
	}
	





	
	/**
	 * References the Field ID_3_ABFALLERZEUGER
	 * Falls keine this.get_ID_3_ABFALLERZEUGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_3_abfallerzeuger() throws myException
	{
		if (S.isEmpty(this.get_ID_3_ABFALLERZEUGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_3_abfallerzeuger==null)
		{
			this.UP_RECORD_ADRESSE_id_3_abfallerzeuger = new RECORD_ADRESSE(this.get_ID_3_ABFALLERZEUGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_3_abfallerzeuger;
	}
	





	
	/**
	 * References the Field ID_3_IMPORT_EMPFAENGER
	 * Falls keine this.get_ID_3_IMPORT_EMPFAENGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_3_import_empfaenger() throws myException
	{
		if (S.isEmpty(this.get_ID_3_IMPORT_EMPFAENGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_3_import_empfaenger==null)
		{
			this.UP_RECORD_ADRESSE_id_3_import_empfaenger = new RECORD_ADRESSE(this.get_ID_3_IMPORT_EMPFAENGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_3_import_empfaenger;
	}
	





	
	/**
	 * References the Field ID_3_VERBR_VERANLASSER
	 * Falls keine this.get_ID_3_VERBR_VERANLASSER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_3_verbr_veranlasser() throws myException
	{
		if (S.isEmpty(this.get_ID_3_VERBR_VERANLASSER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_3_verbr_veranlasser==null)
		{
			this.UP_RECORD_ADRESSE_id_3_verbr_veranlasser = new RECORD_ADRESSE(this.get_ID_3_VERBR_VERANLASSER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_3_verbr_veranlasser;
	}
	





	
	/**
	 * References the Field ID_3_VERWERTUNGSANLAGE
	 * Falls keine this.get_ID_3_VERWERTUNGSANLAGE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_3_verwertungsanlage() throws myException
	{
		if (S.isEmpty(this.get_ID_3_VERWERTUNGSANLAGE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_3_verwertungsanlage==null)
		{
			this.UP_RECORD_ADRESSE_id_3_verwertungsanlage = new RECORD_ADRESSE(this.get_ID_3_VERWERTUNGSANLAGE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_3_verwertungsanlage;
	}
	





	
	/**
	 * References the Field ID_AH7_PROFIL
	 * Falls keine this.get_ID_AH7_PROFIL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_AH7_PROFIL get_UP_RECORD_AH7_PROFIL_id_ah7_profil() throws myException
	{
		if (S.isEmpty(this.get_ID_AH7_PROFIL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_AH7_PROFIL_id_ah7_profil==null)
		{
			this.UP_RECORD_AH7_PROFIL_id_ah7_profil = new RECORD_AH7_PROFIL(this.get_ID_AH7_PROFIL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_AH7_PROFIL_id_ah7_profil;
	}
	

	public static String FIELD__DRUCKE_BLATT2 = "DRUCKE_BLATT2";
	public static String FIELD__DRUCKE_BLATT3 = "DRUCKE_BLATT3";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_1_ABFALLERZEUGER = "ID_1_ABFALLERZEUGER";
	public static String FIELD__ID_1_IMPORT_EMPFAENGER = "ID_1_IMPORT_EMPFAENGER";
	public static String FIELD__ID_1_VERBR_VERANLASSER = "ID_1_VERBR_VERANLASSER";
	public static String FIELD__ID_1_VERWERTUNGSANLAGE = "ID_1_VERWERTUNGSANLAGE";
	public static String FIELD__ID_2_ABFALLERZEUGER = "ID_2_ABFALLERZEUGER";
	public static String FIELD__ID_2_IMPORT_EMPFAENGER = "ID_2_IMPORT_EMPFAENGER";
	public static String FIELD__ID_2_VERBR_VERANLASSER = "ID_2_VERBR_VERANLASSER";
	public static String FIELD__ID_2_VERWERTUNGSANLAGE = "ID_2_VERWERTUNGSANLAGE";
	public static String FIELD__ID_3_ABFALLERZEUGER = "ID_3_ABFALLERZEUGER";
	public static String FIELD__ID_3_IMPORT_EMPFAENGER = "ID_3_IMPORT_EMPFAENGER";
	public static String FIELD__ID_3_VERBR_VERANLASSER = "ID_3_VERBR_VERANLASSER";
	public static String FIELD__ID_3_VERWERTUNGSANLAGE = "ID_3_VERWERTUNGSANLAGE";
	public static String FIELD__ID_ADRESSE_GEO_START = "ID_ADRESSE_GEO_START";
	public static String FIELD__ID_ADRESSE_GEO_ZIEL = "ID_ADRESSE_GEO_ZIEL";
	public static String FIELD__ID_ADRESSE_JUR_START = "ID_ADRESSE_JUR_START";
	public static String FIELD__ID_ADRESSE_JUR_ZIEL = "ID_ADRESSE_JUR_ZIEL";
	public static String FIELD__ID_AH7_PROFIL = "ID_AH7_PROFIL";
	public static String FIELD__ID_AH7_STEUERDATEI = "ID_AH7_STEUERDATEI";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LOCKED = "LOCKED";
	public static String FIELD__STATUS_RELATION = "STATUS_RELATION";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_DRUCKE_BLATT2_cUF() throws myException
	{
		return this.get_UnFormatedValue("DRUCKE_BLATT2");
	}

	public String get_DRUCKE_BLATT2_cF() throws myException
	{
		return this.get_FormatedValue("DRUCKE_BLATT2");	
	}

	public String get_DRUCKE_BLATT2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DRUCKE_BLATT2");
	}

	public String get_DRUCKE_BLATT2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DRUCKE_BLATT2",cNotNullValue);
	}

	public String get_DRUCKE_BLATT2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DRUCKE_BLATT2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DRUCKE_BLATT2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DRUCKE_BLATT2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DRUCKE_BLATT2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DRUCKE_BLATT2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKE_BLATT2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKE_BLATT2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKE_BLATT2", calNewValueFormated);
	}
		public boolean is_DRUCKE_BLATT2_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DRUCKE_BLATT2_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DRUCKE_BLATT2_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DRUCKE_BLATT2_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DRUCKE_BLATT3_cUF() throws myException
	{
		return this.get_UnFormatedValue("DRUCKE_BLATT3");
	}

	public String get_DRUCKE_BLATT3_cF() throws myException
	{
		return this.get_FormatedValue("DRUCKE_BLATT3");	
	}

	public String get_DRUCKE_BLATT3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DRUCKE_BLATT3");
	}

	public String get_DRUCKE_BLATT3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DRUCKE_BLATT3",cNotNullValue);
	}

	public String get_DRUCKE_BLATT3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DRUCKE_BLATT3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DRUCKE_BLATT3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DRUCKE_BLATT3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DRUCKE_BLATT3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DRUCKE_BLATT3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKE_BLATT3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKE_BLATT3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKE_BLATT3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKE_BLATT3", calNewValueFormated);
	}
		public boolean is_DRUCKE_BLATT3_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DRUCKE_BLATT3_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DRUCKE_BLATT3_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DRUCKE_BLATT3_cUF_NN("N").equals("N"))
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
		public String get_ID_1_ABFALLERZEUGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_1_ABFALLERZEUGER");
	}

	public String get_ID_1_ABFALLERZEUGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_1_ABFALLERZEUGER");	
	}

	public String get_ID_1_ABFALLERZEUGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_1_ABFALLERZEUGER");
	}

	public String get_ID_1_ABFALLERZEUGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_1_ABFALLERZEUGER",cNotNullValue);
	}

	public String get_ID_1_ABFALLERZEUGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_1_ABFALLERZEUGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_1_ABFALLERZEUGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_1_ABFALLERZEUGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_1_ABFALLERZEUGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_1_ABFALLERZEUGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_ABFALLERZEUGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_1_ABFALLERZEUGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_ABFALLERZEUGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_ABFALLERZEUGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_ABFALLERZEUGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_ABFALLERZEUGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_ABFALLERZEUGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_ABFALLERZEUGER", calNewValueFormated);
	}
		public Long get_ID_1_ABFALLERZEUGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_1_ABFALLERZEUGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_1_ABFALLERZEUGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_1_ABFALLERZEUGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_1_ABFALLERZEUGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_1_ABFALLERZEUGER").getDoubleValue();
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
	public String get_ID_1_ABFALLERZEUGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_ABFALLERZEUGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_1_ABFALLERZEUGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_ABFALLERZEUGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_1_ABFALLERZEUGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_ABFALLERZEUGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_1_ABFALLERZEUGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_ABFALLERZEUGER").getBigDecimalValue();
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
	
	
	public String get_ID_1_IMPORT_EMPFAENGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_1_IMPORT_EMPFAENGER");
	}

	public String get_ID_1_IMPORT_EMPFAENGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_1_IMPORT_EMPFAENGER");	
	}

	public String get_ID_1_IMPORT_EMPFAENGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_1_IMPORT_EMPFAENGER");
	}

	public String get_ID_1_IMPORT_EMPFAENGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_1_IMPORT_EMPFAENGER",cNotNullValue);
	}

	public String get_ID_1_IMPORT_EMPFAENGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_1_IMPORT_EMPFAENGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_1_IMPORT_EMPFAENGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_1_IMPORT_EMPFAENGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_1_IMPORT_EMPFAENGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_1_IMPORT_EMPFAENGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_IMPORT_EMPFAENGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_1_IMPORT_EMPFAENGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_IMPORT_EMPFAENGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_IMPORT_EMPFAENGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_IMPORT_EMPFAENGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_IMPORT_EMPFAENGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_IMPORT_EMPFAENGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_IMPORT_EMPFAENGER", calNewValueFormated);
	}
		public Long get_ID_1_IMPORT_EMPFAENGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_1_IMPORT_EMPFAENGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_1_IMPORT_EMPFAENGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_1_IMPORT_EMPFAENGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_1_IMPORT_EMPFAENGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_1_IMPORT_EMPFAENGER").getDoubleValue();
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
	public String get_ID_1_IMPORT_EMPFAENGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_IMPORT_EMPFAENGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_1_IMPORT_EMPFAENGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_IMPORT_EMPFAENGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_1_IMPORT_EMPFAENGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_IMPORT_EMPFAENGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_1_IMPORT_EMPFAENGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_IMPORT_EMPFAENGER").getBigDecimalValue();
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
	
	
	public String get_ID_1_VERBR_VERANLASSER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_1_VERBR_VERANLASSER");
	}

	public String get_ID_1_VERBR_VERANLASSER_cF() throws myException
	{
		return this.get_FormatedValue("ID_1_VERBR_VERANLASSER");	
	}

	public String get_ID_1_VERBR_VERANLASSER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_1_VERBR_VERANLASSER");
	}

	public String get_ID_1_VERBR_VERANLASSER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_1_VERBR_VERANLASSER",cNotNullValue);
	}

	public String get_ID_1_VERBR_VERANLASSER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_1_VERBR_VERANLASSER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERBR_VERANLASSER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_1_VERBR_VERANLASSER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_1_VERBR_VERANLASSER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_1_VERBR_VERANLASSER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERBR_VERANLASSER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_1_VERBR_VERANLASSER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERBR_VERANLASSER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_VERBR_VERANLASSER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERBR_VERANLASSER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_VERBR_VERANLASSER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERBR_VERANLASSER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_VERBR_VERANLASSER", calNewValueFormated);
	}
		public Long get_ID_1_VERBR_VERANLASSER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_1_VERBR_VERANLASSER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_1_VERBR_VERANLASSER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_1_VERBR_VERANLASSER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_1_VERBR_VERANLASSER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_1_VERBR_VERANLASSER").getDoubleValue();
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
	public String get_ID_1_VERBR_VERANLASSER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_VERBR_VERANLASSER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_1_VERBR_VERANLASSER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_VERBR_VERANLASSER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_1_VERBR_VERANLASSER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_VERBR_VERANLASSER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_1_VERBR_VERANLASSER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_VERBR_VERANLASSER").getBigDecimalValue();
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
	
	
	public String get_ID_1_VERWERTUNGSANLAGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_1_VERWERTUNGSANLAGE");
	}

	public String get_ID_1_VERWERTUNGSANLAGE_cF() throws myException
	{
		return this.get_FormatedValue("ID_1_VERWERTUNGSANLAGE");	
	}

	public String get_ID_1_VERWERTUNGSANLAGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_1_VERWERTUNGSANLAGE");
	}

	public String get_ID_1_VERWERTUNGSANLAGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_1_VERWERTUNGSANLAGE",cNotNullValue);
	}

	public String get_ID_1_VERWERTUNGSANLAGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_1_VERWERTUNGSANLAGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERWERTUNGSANLAGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_1_VERWERTUNGSANLAGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_1_VERWERTUNGSANLAGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_1_VERWERTUNGSANLAGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERWERTUNGSANLAGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_1_VERWERTUNGSANLAGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERWERTUNGSANLAGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_VERWERTUNGSANLAGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERWERTUNGSANLAGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_VERWERTUNGSANLAGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_1_VERWERTUNGSANLAGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_1_VERWERTUNGSANLAGE", calNewValueFormated);
	}
		public Long get_ID_1_VERWERTUNGSANLAGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_1_VERWERTUNGSANLAGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_1_VERWERTUNGSANLAGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_1_VERWERTUNGSANLAGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_1_VERWERTUNGSANLAGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_1_VERWERTUNGSANLAGE").getDoubleValue();
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
	public String get_ID_1_VERWERTUNGSANLAGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_VERWERTUNGSANLAGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_1_VERWERTUNGSANLAGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_1_VERWERTUNGSANLAGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_1_VERWERTUNGSANLAGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_VERWERTUNGSANLAGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_1_VERWERTUNGSANLAGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_1_VERWERTUNGSANLAGE").getBigDecimalValue();
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
	
	
	public String get_ID_2_ABFALLERZEUGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_2_ABFALLERZEUGER");
	}

	public String get_ID_2_ABFALLERZEUGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_2_ABFALLERZEUGER");	
	}

	public String get_ID_2_ABFALLERZEUGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_2_ABFALLERZEUGER");
	}

	public String get_ID_2_ABFALLERZEUGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_2_ABFALLERZEUGER",cNotNullValue);
	}

	public String get_ID_2_ABFALLERZEUGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_2_ABFALLERZEUGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_2_ABFALLERZEUGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_2_ABFALLERZEUGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_2_ABFALLERZEUGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_2_ABFALLERZEUGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_ABFALLERZEUGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_2_ABFALLERZEUGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_ABFALLERZEUGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_ABFALLERZEUGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_ABFALLERZEUGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_ABFALLERZEUGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_ABFALLERZEUGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_ABFALLERZEUGER", calNewValueFormated);
	}
		public Long get_ID_2_ABFALLERZEUGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_2_ABFALLERZEUGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_2_ABFALLERZEUGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_2_ABFALLERZEUGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_2_ABFALLERZEUGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_2_ABFALLERZEUGER").getDoubleValue();
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
	public String get_ID_2_ABFALLERZEUGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_ABFALLERZEUGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_2_ABFALLERZEUGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_ABFALLERZEUGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_2_ABFALLERZEUGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_ABFALLERZEUGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_2_ABFALLERZEUGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_ABFALLERZEUGER").getBigDecimalValue();
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
	
	
	public String get_ID_2_IMPORT_EMPFAENGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_2_IMPORT_EMPFAENGER");
	}

	public String get_ID_2_IMPORT_EMPFAENGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_2_IMPORT_EMPFAENGER");	
	}

	public String get_ID_2_IMPORT_EMPFAENGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_2_IMPORT_EMPFAENGER");
	}

	public String get_ID_2_IMPORT_EMPFAENGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_2_IMPORT_EMPFAENGER",cNotNullValue);
	}

	public String get_ID_2_IMPORT_EMPFAENGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_2_IMPORT_EMPFAENGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_2_IMPORT_EMPFAENGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_2_IMPORT_EMPFAENGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_2_IMPORT_EMPFAENGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_2_IMPORT_EMPFAENGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_IMPORT_EMPFAENGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_2_IMPORT_EMPFAENGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_IMPORT_EMPFAENGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_IMPORT_EMPFAENGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_IMPORT_EMPFAENGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_IMPORT_EMPFAENGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_IMPORT_EMPFAENGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_IMPORT_EMPFAENGER", calNewValueFormated);
	}
		public Long get_ID_2_IMPORT_EMPFAENGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_2_IMPORT_EMPFAENGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_2_IMPORT_EMPFAENGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_2_IMPORT_EMPFAENGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_2_IMPORT_EMPFAENGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_2_IMPORT_EMPFAENGER").getDoubleValue();
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
	public String get_ID_2_IMPORT_EMPFAENGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_IMPORT_EMPFAENGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_2_IMPORT_EMPFAENGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_IMPORT_EMPFAENGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_2_IMPORT_EMPFAENGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_IMPORT_EMPFAENGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_2_IMPORT_EMPFAENGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_IMPORT_EMPFAENGER").getBigDecimalValue();
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
	
	
	public String get_ID_2_VERBR_VERANLASSER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_2_VERBR_VERANLASSER");
	}

	public String get_ID_2_VERBR_VERANLASSER_cF() throws myException
	{
		return this.get_FormatedValue("ID_2_VERBR_VERANLASSER");	
	}

	public String get_ID_2_VERBR_VERANLASSER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_2_VERBR_VERANLASSER");
	}

	public String get_ID_2_VERBR_VERANLASSER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_2_VERBR_VERANLASSER",cNotNullValue);
	}

	public String get_ID_2_VERBR_VERANLASSER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_2_VERBR_VERANLASSER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERBR_VERANLASSER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_2_VERBR_VERANLASSER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_2_VERBR_VERANLASSER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_2_VERBR_VERANLASSER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERBR_VERANLASSER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_2_VERBR_VERANLASSER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERBR_VERANLASSER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_VERBR_VERANLASSER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERBR_VERANLASSER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_VERBR_VERANLASSER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERBR_VERANLASSER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_VERBR_VERANLASSER", calNewValueFormated);
	}
		public Long get_ID_2_VERBR_VERANLASSER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_2_VERBR_VERANLASSER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_2_VERBR_VERANLASSER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_2_VERBR_VERANLASSER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_2_VERBR_VERANLASSER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_2_VERBR_VERANLASSER").getDoubleValue();
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
	public String get_ID_2_VERBR_VERANLASSER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_VERBR_VERANLASSER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_2_VERBR_VERANLASSER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_VERBR_VERANLASSER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_2_VERBR_VERANLASSER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_VERBR_VERANLASSER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_2_VERBR_VERANLASSER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_VERBR_VERANLASSER").getBigDecimalValue();
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
	
	
	public String get_ID_2_VERWERTUNGSANLAGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_2_VERWERTUNGSANLAGE");
	}

	public String get_ID_2_VERWERTUNGSANLAGE_cF() throws myException
	{
		return this.get_FormatedValue("ID_2_VERWERTUNGSANLAGE");	
	}

	public String get_ID_2_VERWERTUNGSANLAGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_2_VERWERTUNGSANLAGE");
	}

	public String get_ID_2_VERWERTUNGSANLAGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_2_VERWERTUNGSANLAGE",cNotNullValue);
	}

	public String get_ID_2_VERWERTUNGSANLAGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_2_VERWERTUNGSANLAGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERWERTUNGSANLAGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_2_VERWERTUNGSANLAGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_2_VERWERTUNGSANLAGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_2_VERWERTUNGSANLAGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERWERTUNGSANLAGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_2_VERWERTUNGSANLAGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERWERTUNGSANLAGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_VERWERTUNGSANLAGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERWERTUNGSANLAGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_VERWERTUNGSANLAGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_2_VERWERTUNGSANLAGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_2_VERWERTUNGSANLAGE", calNewValueFormated);
	}
		public Long get_ID_2_VERWERTUNGSANLAGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_2_VERWERTUNGSANLAGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_2_VERWERTUNGSANLAGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_2_VERWERTUNGSANLAGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_2_VERWERTUNGSANLAGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_2_VERWERTUNGSANLAGE").getDoubleValue();
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
	public String get_ID_2_VERWERTUNGSANLAGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_VERWERTUNGSANLAGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_2_VERWERTUNGSANLAGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_2_VERWERTUNGSANLAGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_2_VERWERTUNGSANLAGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_VERWERTUNGSANLAGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_2_VERWERTUNGSANLAGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_2_VERWERTUNGSANLAGE").getBigDecimalValue();
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
	
	
	public String get_ID_3_ABFALLERZEUGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_3_ABFALLERZEUGER");
	}

	public String get_ID_3_ABFALLERZEUGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_3_ABFALLERZEUGER");	
	}

	public String get_ID_3_ABFALLERZEUGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_3_ABFALLERZEUGER");
	}

	public String get_ID_3_ABFALLERZEUGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_3_ABFALLERZEUGER",cNotNullValue);
	}

	public String get_ID_3_ABFALLERZEUGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_3_ABFALLERZEUGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_3_ABFALLERZEUGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_3_ABFALLERZEUGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_3_ABFALLERZEUGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_3_ABFALLERZEUGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_ABFALLERZEUGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_3_ABFALLERZEUGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_ABFALLERZEUGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_ABFALLERZEUGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_ABFALLERZEUGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_ABFALLERZEUGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_ABFALLERZEUGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_ABFALLERZEUGER", calNewValueFormated);
	}
		public Long get_ID_3_ABFALLERZEUGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_3_ABFALLERZEUGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_3_ABFALLERZEUGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_3_ABFALLERZEUGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_3_ABFALLERZEUGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_3_ABFALLERZEUGER").getDoubleValue();
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
	public String get_ID_3_ABFALLERZEUGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_ABFALLERZEUGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_3_ABFALLERZEUGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_ABFALLERZEUGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_3_ABFALLERZEUGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_ABFALLERZEUGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_3_ABFALLERZEUGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_ABFALLERZEUGER").getBigDecimalValue();
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
	
	
	public String get_ID_3_IMPORT_EMPFAENGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_3_IMPORT_EMPFAENGER");
	}

	public String get_ID_3_IMPORT_EMPFAENGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_3_IMPORT_EMPFAENGER");	
	}

	public String get_ID_3_IMPORT_EMPFAENGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_3_IMPORT_EMPFAENGER");
	}

	public String get_ID_3_IMPORT_EMPFAENGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_3_IMPORT_EMPFAENGER",cNotNullValue);
	}

	public String get_ID_3_IMPORT_EMPFAENGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_3_IMPORT_EMPFAENGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_3_IMPORT_EMPFAENGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_3_IMPORT_EMPFAENGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_3_IMPORT_EMPFAENGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_3_IMPORT_EMPFAENGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_IMPORT_EMPFAENGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_3_IMPORT_EMPFAENGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_IMPORT_EMPFAENGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_IMPORT_EMPFAENGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_IMPORT_EMPFAENGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_IMPORT_EMPFAENGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_IMPORT_EMPFAENGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_IMPORT_EMPFAENGER", calNewValueFormated);
	}
		public Long get_ID_3_IMPORT_EMPFAENGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_3_IMPORT_EMPFAENGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_3_IMPORT_EMPFAENGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_3_IMPORT_EMPFAENGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_3_IMPORT_EMPFAENGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_3_IMPORT_EMPFAENGER").getDoubleValue();
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
	public String get_ID_3_IMPORT_EMPFAENGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_IMPORT_EMPFAENGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_3_IMPORT_EMPFAENGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_IMPORT_EMPFAENGER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_3_IMPORT_EMPFAENGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_IMPORT_EMPFAENGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_3_IMPORT_EMPFAENGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_IMPORT_EMPFAENGER").getBigDecimalValue();
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
	
	
	public String get_ID_3_VERBR_VERANLASSER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_3_VERBR_VERANLASSER");
	}

	public String get_ID_3_VERBR_VERANLASSER_cF() throws myException
	{
		return this.get_FormatedValue("ID_3_VERBR_VERANLASSER");	
	}

	public String get_ID_3_VERBR_VERANLASSER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_3_VERBR_VERANLASSER");
	}

	public String get_ID_3_VERBR_VERANLASSER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_3_VERBR_VERANLASSER",cNotNullValue);
	}

	public String get_ID_3_VERBR_VERANLASSER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_3_VERBR_VERANLASSER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERBR_VERANLASSER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_3_VERBR_VERANLASSER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_3_VERBR_VERANLASSER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_3_VERBR_VERANLASSER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERBR_VERANLASSER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_3_VERBR_VERANLASSER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERBR_VERANLASSER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_VERBR_VERANLASSER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERBR_VERANLASSER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_VERBR_VERANLASSER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERBR_VERANLASSER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_VERBR_VERANLASSER", calNewValueFormated);
	}
		public Long get_ID_3_VERBR_VERANLASSER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_3_VERBR_VERANLASSER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_3_VERBR_VERANLASSER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_3_VERBR_VERANLASSER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_3_VERBR_VERANLASSER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_3_VERBR_VERANLASSER").getDoubleValue();
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
	public String get_ID_3_VERBR_VERANLASSER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_VERBR_VERANLASSER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_3_VERBR_VERANLASSER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_VERBR_VERANLASSER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_3_VERBR_VERANLASSER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_VERBR_VERANLASSER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_3_VERBR_VERANLASSER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_VERBR_VERANLASSER").getBigDecimalValue();
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
	
	
	public String get_ID_3_VERWERTUNGSANLAGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_3_VERWERTUNGSANLAGE");
	}

	public String get_ID_3_VERWERTUNGSANLAGE_cF() throws myException
	{
		return this.get_FormatedValue("ID_3_VERWERTUNGSANLAGE");	
	}

	public String get_ID_3_VERWERTUNGSANLAGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_3_VERWERTUNGSANLAGE");
	}

	public String get_ID_3_VERWERTUNGSANLAGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_3_VERWERTUNGSANLAGE",cNotNullValue);
	}

	public String get_ID_3_VERWERTUNGSANLAGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_3_VERWERTUNGSANLAGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERWERTUNGSANLAGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_3_VERWERTUNGSANLAGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_3_VERWERTUNGSANLAGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_3_VERWERTUNGSANLAGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERWERTUNGSANLAGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_3_VERWERTUNGSANLAGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERWERTUNGSANLAGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_VERWERTUNGSANLAGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERWERTUNGSANLAGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_VERWERTUNGSANLAGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_3_VERWERTUNGSANLAGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_3_VERWERTUNGSANLAGE", calNewValueFormated);
	}
		public Long get_ID_3_VERWERTUNGSANLAGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_3_VERWERTUNGSANLAGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_3_VERWERTUNGSANLAGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_3_VERWERTUNGSANLAGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_3_VERWERTUNGSANLAGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_3_VERWERTUNGSANLAGE").getDoubleValue();
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
	public String get_ID_3_VERWERTUNGSANLAGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_VERWERTUNGSANLAGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_3_VERWERTUNGSANLAGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_3_VERWERTUNGSANLAGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_3_VERWERTUNGSANLAGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_VERWERTUNGSANLAGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_3_VERWERTUNGSANLAGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_3_VERWERTUNGSANLAGE").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_GEO_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_GEO_START");
	}

	public String get_ID_ADRESSE_GEO_START_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_GEO_START");	
	}

	public String get_ID_ADRESSE_GEO_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_GEO_START");
	}

	public String get_ID_ADRESSE_GEO_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_GEO_START",cNotNullValue);
	}

	public String get_ID_ADRESSE_GEO_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_GEO_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_GEO_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_GEO_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_GEO_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_START", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_GEO_START_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_GEO_START").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_GEO_START_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_GEO_START").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_GEO_START_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_GEO_START").getDoubleValue();
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
	public String get_ID_ADRESSE_GEO_START_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_GEO_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_GEO_START_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_GEO_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_GEO_START_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_GEO_START").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_GEO_START_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_GEO_START").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_GEO_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_GEO_ZIEL");
	}

	public String get_ID_ADRESSE_GEO_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_GEO_ZIEL");	
	}

	public String get_ID_ADRESSE_GEO_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_GEO_ZIEL");
	}

	public String get_ID_ADRESSE_GEO_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_GEO_ZIEL",cNotNullValue);
	}

	public String get_ID_ADRESSE_GEO_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_GEO_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_GEO_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_GEO_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_GEO_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_GEO_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_GEO_ZIEL", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_GEO_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_GEO_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_GEO_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_GEO_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_GEO_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_GEO_ZIEL").getDoubleValue();
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
	public String get_ID_ADRESSE_GEO_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_GEO_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_GEO_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_GEO_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_GEO_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_GEO_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_GEO_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_GEO_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_JUR_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_JUR_START");
	}

	public String get_ID_ADRESSE_JUR_START_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_JUR_START");	
	}

	public String get_ID_ADRESSE_JUR_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_JUR_START");
	}

	public String get_ID_ADRESSE_JUR_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_JUR_START",cNotNullValue);
	}

	public String get_ID_ADRESSE_JUR_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_JUR_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_JUR_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_JUR_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_JUR_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_START", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_JUR_START_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_JUR_START").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_JUR_START_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_JUR_START").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_JUR_START_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_JUR_START").getDoubleValue();
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
	public String get_ID_ADRESSE_JUR_START_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_JUR_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_JUR_START_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_JUR_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_JUR_START_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_JUR_START").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_JUR_START_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_JUR_START").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_JUR_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_JUR_ZIEL");
	}

	public String get_ID_ADRESSE_JUR_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_JUR_ZIEL");	
	}

	public String get_ID_ADRESSE_JUR_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_JUR_ZIEL");
	}

	public String get_ID_ADRESSE_JUR_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_JUR_ZIEL",cNotNullValue);
	}

	public String get_ID_ADRESSE_JUR_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_JUR_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_JUR_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_JUR_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_JUR_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_JUR_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_JUR_ZIEL", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_JUR_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_JUR_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_JUR_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_JUR_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_JUR_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_JUR_ZIEL").getDoubleValue();
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
	public String get_ID_ADRESSE_JUR_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_JUR_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_JUR_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_JUR_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_JUR_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_JUR_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_JUR_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_JUR_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_AH7_PROFIL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_AH7_PROFIL");
	}

	public String get_ID_AH7_PROFIL_cF() throws myException
	{
		return this.get_FormatedValue("ID_AH7_PROFIL");	
	}

	public String get_ID_AH7_PROFIL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_AH7_PROFIL");
	}

	public String get_ID_AH7_PROFIL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_AH7_PROFIL",cNotNullValue);
	}

	public String get_ID_AH7_PROFIL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_AH7_PROFIL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_AH7_PROFIL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_AH7_PROFIL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_AH7_PROFIL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", calNewValueFormated);
	}
		public Long get_ID_AH7_PROFIL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_AH7_PROFIL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_AH7_PROFIL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_AH7_PROFIL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_AH7_PROFIL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_AH7_PROFIL").getDoubleValue();
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
	public String get_ID_AH7_PROFIL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_AH7_PROFIL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_AH7_PROFIL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_AH7_PROFIL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_AH7_PROFIL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_AH7_PROFIL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_AH7_PROFIL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_AH7_PROFIL").getBigDecimalValue();
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
	
	
	public String get_ID_AH7_STEUERDATEI_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_AH7_STEUERDATEI");
	}

	public String get_ID_AH7_STEUERDATEI_cF() throws myException
	{
		return this.get_FormatedValue("ID_AH7_STEUERDATEI");	
	}

	public String get_ID_AH7_STEUERDATEI_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_AH7_STEUERDATEI");
	}

	public String get_ID_AH7_STEUERDATEI_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_AH7_STEUERDATEI",cNotNullValue);
	}

	public String get_ID_AH7_STEUERDATEI_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_AH7_STEUERDATEI",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_STEUERDATEI(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_AH7_STEUERDATEI", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_AH7_STEUERDATEI(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_AH7_STEUERDATEI", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_STEUERDATEI_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_AH7_STEUERDATEI", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_STEUERDATEI_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_STEUERDATEI", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_STEUERDATEI_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_STEUERDATEI", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_STEUERDATEI_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_STEUERDATEI", calNewValueFormated);
	}
		public Long get_ID_AH7_STEUERDATEI_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_AH7_STEUERDATEI").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_AH7_STEUERDATEI_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_AH7_STEUERDATEI").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_AH7_STEUERDATEI_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_AH7_STEUERDATEI").getDoubleValue();
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
	public String get_ID_AH7_STEUERDATEI_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_AH7_STEUERDATEI_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_AH7_STEUERDATEI_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_AH7_STEUERDATEI_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_AH7_STEUERDATEI_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_AH7_STEUERDATEI").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_AH7_STEUERDATEI_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_AH7_STEUERDATEI").getBigDecimalValue();
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
		public String get_LOCKED_cUF() throws myException
	{
		return this.get_UnFormatedValue("LOCKED");
	}

	public String get_LOCKED_cF() throws myException
	{
		return this.get_FormatedValue("LOCKED");	
	}

	public String get_LOCKED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LOCKED");
	}

	public String get_LOCKED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LOCKED",cNotNullValue);
	}

	public String get_LOCKED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LOCKED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LOCKED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LOCKED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LOCKED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LOCKED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOCKED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LOCKED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOCKED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOCKED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOCKED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOCKED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOCKED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOCKED", calNewValueFormated);
	}
		public boolean is_LOCKED_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_LOCKED_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_LOCKED_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_LOCKED_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STATUS_RELATION_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATUS_RELATION");
	}

	public String get_STATUS_RELATION_cF() throws myException
	{
		return this.get_FormatedValue("STATUS_RELATION");	
	}

	public String get_STATUS_RELATION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATUS_RELATION");
	}

	public String get_STATUS_RELATION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATUS_RELATION",cNotNullValue);
	}

	public String get_STATUS_RELATION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATUS_RELATION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATUS_RELATION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATUS_RELATION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATUS_RELATION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATUS_RELATION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_RELATION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_RELATION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_RELATION", calNewValueFormated);
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
	put("DRUCKE_BLATT2",MyRECORD.DATATYPES.YESNO);
	put("DRUCKE_BLATT3",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_1_ABFALLERZEUGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_1_IMPORT_EMPFAENGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_1_VERBR_VERANLASSER",MyRECORD.DATATYPES.NUMBER);
	put("ID_1_VERWERTUNGSANLAGE",MyRECORD.DATATYPES.NUMBER);
	put("ID_2_ABFALLERZEUGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_2_IMPORT_EMPFAENGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_2_VERBR_VERANLASSER",MyRECORD.DATATYPES.NUMBER);
	put("ID_2_VERWERTUNGSANLAGE",MyRECORD.DATATYPES.NUMBER);
	put("ID_3_ABFALLERZEUGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_3_IMPORT_EMPFAENGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_3_VERBR_VERANLASSER",MyRECORD.DATATYPES.NUMBER);
	put("ID_3_VERWERTUNGSANLAGE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_GEO_START",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_GEO_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_JUR_START",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_JUR_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_AH7_PROFIL",MyRECORD.DATATYPES.NUMBER);
	put("ID_AH7_STEUERDATEI",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LOCKED",MyRECORD.DATATYPES.YESNO);
	put("STATUS_RELATION",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_AH7_STEUERDATEI.HM_FIELDS;	
	}

}
