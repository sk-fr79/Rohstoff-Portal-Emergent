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

public class RECORD_VPOS_TPA_FUHRE_ORT extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_VPOS_TPA_FUHRE_ORT";
    public static String IDFIELD   = "ID_VPOS_TPA_FUHRE_ORT";
    

	//erzeugen eines RECORDNEW_JT_VPOS_TPA_FUHRE_ORT - felds
	private RECORDNEW_VPOS_TPA_FUHRE_ORT   recNEW = null;

    private _TAB  tab = _TAB.vpos_tpa_fuhre_ort;  



	public RECORD_VPOS_TPA_FUHRE_ORT() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");
	}


	public RECORD_VPOS_TPA_FUHRE_ORT(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");
	}



	public RECORD_VPOS_TPA_FUHRE_ORT(RECORD_VPOS_TPA_FUHRE_ORT recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME);
	}


	//2014-04-03
	public RECORD_VPOS_TPA_FUHRE_ORT(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME);
	}




	public RECORD_VPOS_TPA_FUHRE_ORT(long lID_Unformated) throws myException
	{
		super("JT_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT",""+lID_Unformated);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT", "ID_VPOS_TPA_FUHRE_ORT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME);
	}
	
	

	public RECORD_VPOS_TPA_FUHRE_ORT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE_ORT",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME);

	}


	public RECORD_VPOS_TPA_FUHRE_ORT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_ORT");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT", "ID_VPOS_TPA_FUHRE_ORT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_VPOS_TPA_FUHRE_ORT();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_VPOS_TPA_FUHRE_ORT";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_ORT_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_TPA_FUHRE_ORT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_ORT", bibE2.cTO(), "ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_TPA_FUHRE_ORT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_ORT", bibE2.cTO(), "ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
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
			if (S.isFull(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT", "ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_VPOS_TPA_FUHRE_ORT get_RECORDNEW_VPOS_TPA_FUHRE_ORT() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_VPOS_TPA_FUHRE_ORT();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_VPOS_TPA_FUHRE_ORT(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance() throws myException {
		return new RECORD_VPOS_TPA_FUHRE_ORT();
	}
	
	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_VPOS_TPA_FUHRE_ORT(Conn);
    }

	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance(RECORD_VPOS_TPA_FUHRE_ORT recordOrig) {
		return new RECORD_VPOS_TPA_FUHRE_ORT(recordOrig);
    }

	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance(long lID_Unformated) throws myException {
		return new RECORD_VPOS_TPA_FUHRE_ORT(lID_Unformated);
    }

	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_VPOS_TPA_FUHRE_ORT(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_VPOS_TPA_FUHRE_ORT(lID_Unformated, Conn);
	}

	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_VPOS_TPA_FUHRE_ORT(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_VPOS_TPA_FUHRE_ORT create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_VPOS_TPA_FUHRE_ORT(recordOrig);	    
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
    public RECORD_VPOS_TPA_FUHRE_ORT build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF());
      }
      //return new RECORD_VPOS_TPA_FUHRE_ORT(this.get_cSQL_4_Build());
      RECORD_VPOS_TPA_FUHRE_ORT rec = new RECORD_VPOS_TPA_FUHRE_ORT();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_VPOS_TPA_FUHRE_ORT
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_VPOS_TPA_FUHRE_ORT-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_VPOS_TPA_FUHRE_ORT get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_VPOS_TPA_FUHRE_ORT_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_VPOS_TPA_FUHRE_ORT  recNew = new RECORDNEW_VPOS_TPA_FUHRE_ORT();
		
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
    public RECORD_VPOS_TPA_FUHRE_ORT set_recordNew(RECORDNEW_VPOS_TPA_FUHRE_ORT recnew) throws myException {
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




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lager = null;




		private RECORD_ARTIKEL UP_RECORD_ARTIKEL_id_artikel = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez = null;




		private RECORD_EAK_CODE UP_RECORD_EAK_CODE_id_eak_code = null;




		private RECORD_HANDELSDEF UP_RECORD_HANDELSDEF_id_handelsdef = null;




		private RECORD_TAX UP_RECORD_TAX_id_tax = null;




		private RECORD_VPOS_KON UP_RECORD_VPOS_KON_id_vpos_kon = null;




		private RECORD_VPOS_STD UP_RECORD_VPOS_STD_id_vpos_std = null;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre = null;




		private RECLIST_BAM_IMPORT DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort = null ;




		private RECLIST_FBAM DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre_ort = null ;




		private RECLIST_FUHREN_RECHNUNGEN DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort = null ;




		private RECLIST_PROFORMA_RECHNUNG DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT_RGVL DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort = null ;




		private RECLIST_VPOS_TPA_FUHRE_VP_EXT DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort = null ;




		private RECORD_VPOS_TPA_FUHRE_SONDER UP_RECORD_VPOS_TPA_FUHRE_SONDER_id_vpos_tpa_fuhre_sonder = null;




		private RECORD_WIEGEKARTE UP_RECORD_WIEGEKARTE_id_wiegekarte = null;






	
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
	 * References the Field ID_ADRESSE_LAGER
	 * Falls keine this.get_ID_ADRESSE_LAGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LAGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lager==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lager = new RECORD_ADRESSE(this.get_ID_ADRESSE_LAGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lager;
	}
	





	
	/**
	 * References the Field ID_ARTIKEL
	 * Falls keine this.get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL get_UP_RECORD_ARTIKEL_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_id_artikel==null)
		{
			this.UP_RECORD_ARTIKEL_id_artikel = new RECORD_ARTIKEL(this.get_ID_ARTIKEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_id_artikel;
	}
	





	
	/**
	 * References the Field ID_ARTIKEL_BEZ
	 * Falls keine this.get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_BEZ get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez==null)
		{
			this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez = new RECORD_ARTIKEL_BEZ(this.get_ID_ARTIKEL_BEZ_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez;
	}
	





	
	/**
	 * References the Field ID_EAK_CODE
	 * Falls keine this.get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_EAK_CODE get_UP_RECORD_EAK_CODE_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_EAK_CODE_id_eak_code==null)
		{
			this.UP_RECORD_EAK_CODE_id_eak_code = new RECORD_EAK_CODE(this.get_ID_EAK_CODE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_EAK_CODE_id_eak_code;
	}
	





	
	/**
	 * References the Field ID_HANDELSDEF
	 * Falls keine this.get_ID_HANDELSDEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_HANDELSDEF get_UP_RECORD_HANDELSDEF_id_handelsdef() throws myException
	{
		if (S.isEmpty(this.get_ID_HANDELSDEF_cUF()))
			return null;
	
	
		if (this.UP_RECORD_HANDELSDEF_id_handelsdef==null)
		{
			this.UP_RECORD_HANDELSDEF_id_handelsdef = new RECORD_HANDELSDEF(this.get_ID_HANDELSDEF_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_HANDELSDEF_id_handelsdef;
	}
	





	
	/**
	 * References the Field ID_TAX
	 * Falls keine this.get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX get_UP_RECORD_TAX_id_tax() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_id_tax==null)
		{
			this.UP_RECORD_TAX_id_tax = new RECORD_TAX(this.get_ID_TAX_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_id_tax;
	}
	





	
	/**
	 * References the Field ID_VPOS_KON
	 * Falls keine this.get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_KON get_UP_RECORD_VPOS_KON_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_KON_id_vpos_kon==null)
		{
			this.UP_RECORD_VPOS_KON_id_vpos_kon = new RECORD_VPOS_KON(this.get_ID_VPOS_KON_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_KON_id_vpos_kon;
	}
	





	
	/**
	 * References the Field ID_VPOS_STD
	 * Falls keine this.get_ID_VPOS_STD_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_STD get_UP_RECORD_VPOS_STD_id_vpos_std() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_STD_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_STD_id_vpos_std==null)
		{
			this.UP_RECORD_VPOS_STD_id_vpos_std = new RECORD_VPOS_STD(this.get_ID_VPOS_STD_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_STD_id_vpos_std;
	}
	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre = new RECORD_VPOS_TPA_FUHRE(this.get_ID_VPOS_TPA_FUHRE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre;
	}
	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BAM_IMPORT get_DOWN_RECORD_LIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort = new RECLIST_BAM_IMPORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_BAM_IMPORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BAM_IMPORT get_DOWN_RECORD_LIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort = new RECLIST_BAM_IMPORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre_ort;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre_ort = new RECLIST_FBAM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_FBAM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre_ort = new RECLIST_FBAM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre_ort;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort = new RECLIST_FUHREN_RECHNUNGEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_FUHREN_RECHNUNGEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort = new RECLIST_FUHREN_RECHNUNGEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre_ort;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort = new RECLIST_PROFORMA_RECHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_PROFORMA_RECHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort = new RECLIST_PROFORMA_RECHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre_ort;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT_ZUGEORD 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_TPA_FUHRE_ORT_ZUGEORD="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT_ZUGEORD 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_TPA_FUHRE_ORT_ZUGEORD="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort = new RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT_ABZUG WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT_ABZUG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT_ABZUG WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort = new RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT_RGVL get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort = new RECLIST_VPOS_TPA_FUHRE_ORT_RGVL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT_RGVL WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT_RGVL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT_RGVL get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT_RGVL WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort = new RECLIST_VPOS_TPA_FUHRE_ORT_RGVL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_RGVL_id_vpos_tpa_fuhre_ort;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_VP_EXT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort = new RECLIST_VPOS_TPA_FUHRE_VP_EXT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_VP_EXT WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_VP_EXT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_VP_EXT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_VP_EXT WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort = new RECLIST_VPOS_TPA_FUHRE_VP_EXT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre_ort;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ORT 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_VPOS_TPA_FUHRE_ORT="+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre_ort;
	}


	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE_SONDER
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE_SONDER get_UP_RECORD_VPOS_TPA_FUHRE_SONDER_id_vpos_tpa_fuhre_sonder() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_SONDER_id_vpos_tpa_fuhre_sonder==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_SONDER_id_vpos_tpa_fuhre_sonder = new RECORD_VPOS_TPA_FUHRE_SONDER(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_SONDER_id_vpos_tpa_fuhre_sonder;
	}
	





	
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
	

	public static String FIELD__ABLADEMENGE_RECHNUNG = "ABLADEMENGE_RECHNUNG";
	public static String FIELD__ABZUG_MENGE = "ABZUG_MENGE";
	public static String FIELD__ANR1 = "ANR1";
	public static String FIELD__ANR2 = "ANR2";
	public static String FIELD__ANTEIL_ABLADEMENGE = "ANTEIL_ABLADEMENGE";
	public static String FIELD__ANTEIL_LADEMENGE = "ANTEIL_LADEMENGE";
	public static String FIELD__ANTEIL_PLANMENGE = "ANTEIL_PLANMENGE";
	public static String FIELD__ARTBEZ1 = "ARTBEZ1";
	public static String FIELD__ARTBEZ2 = "ARTBEZ2";
	public static String FIELD__AVV_AUSSTELLUNG_DATUM = "AVV_AUSSTELLUNG_DATUM";
	public static String FIELD__BASEL_CODE = "BASEL_CODE";
	public static String FIELD__BASEL_NOTIZ = "BASEL_NOTIZ";
	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__BESTELLNUMMER = "BESTELLNUMMER";
	public static String FIELD__BUCHUNGSNUMMER_ZUSATZ = "BUCHUNGSNUMMER_ZUSATZ";
	public static String FIELD__DATUM_LADE_ABLADE = "DATUM_LADE_ABLADE";
	public static String FIELD__DEF_QUELLE_ZIEL = "DEF_QUELLE_ZIEL";
	public static String FIELD__DELETED = "DELETED";
	public static String FIELD__DEL_DATE = "DEL_DATE";
	public static String FIELD__DEL_GRUND = "DEL_GRUND";
	public static String FIELD__DEL_KUERZEL = "DEL_KUERZEL";
	public static String FIELD__EINHEIT_MENGEN = "EINHEIT_MENGEN";
	public static String FIELD__EINZELPREIS = "EINZELPREIS";
	public static String FIELD__EPREIS_RESULT_NETTO_MGE = "EPREIS_RESULT_NETTO_MGE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EUCODE = "EUCODE";
	public static String FIELD__EUNOTIZ = "EUNOTIZ";
	public static String FIELD__EU_BLATT_MENGE = "EU_BLATT_MENGE";
	public static String FIELD__EU_BLATT_VOLUMEN = "EU_BLATT_VOLUMEN";
	public static String FIELD__EU_STEUER_VERMERK = "EU_STEUER_VERMERK";
	public static String FIELD__FAX = "FAX";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GELANGENSBESTAETIGUNG_ERHALTEN = "GELANGENSBESTAETIGUNG_ERHALTEN";
	public static String FIELD__HAUSNUMMER = "HAUSNUMMER";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_ADRESSE_LAGER = "ID_ADRESSE_LAGER";
	public static String FIELD__ID_ARTIKEL = "ID_ARTIKEL";
	public static String FIELD__ID_ARTIKEL_BEZ = "ID_ARTIKEL_BEZ";
	public static String FIELD__ID_EAK_CODE = "ID_EAK_CODE";
	public static String FIELD__ID_HANDELSDEF = "ID_HANDELSDEF";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TAX = "ID_TAX";
	public static String FIELD__ID_VPOS_KON = "ID_VPOS_KON";
	public static String FIELD__ID_VPOS_STD = "ID_VPOS_STD";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ORT = "ID_VPOS_TPA_FUHRE_ORT";
	public static String FIELD__ID_VPOS_TPA_FUHRE_SONDER = "ID_VPOS_TPA_FUHRE_SONDER";
	public static String FIELD__ID_WIEGEKARTE = "ID_WIEGEKARTE";
	public static String FIELD__INTRASTAT_MELD = "INTRASTAT_MELD";
	public static String FIELD__KEIN_KONTRAKT_NOETIG = "KEIN_KONTRAKT_NOETIG";
	public static String FIELD__LADEMENGE_GUTSCHRIFT = "LADEMENGE_GUTSCHRIFT";
	public static String FIELD__LAENDERCODE = "LAENDERCODE";
	public static String FIELD__LAENDERCODE_TRANSIT1 = "LAENDERCODE_TRANSIT1";
	public static String FIELD__LAENDERCODE_TRANSIT2 = "LAENDERCODE_TRANSIT2";
	public static String FIELD__LAENDERCODE_TRANSIT3 = "LAENDERCODE_TRANSIT3";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERBED_ALTERNATIV = "LIEFERBED_ALTERNATIV";
	public static String FIELD__MANUELL_PREIS = "MANUELL_PREIS";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__NATIONALER_ABFALL_CODE = "NATIONALER_ABFALL_CODE";
	public static String FIELD__NOTIFIKATION_NR = "NOTIFIKATION_NR";
	public static String FIELD__OEFFNUNGSZEITEN = "OEFFNUNGSZEITEN";
	public static String FIELD__OHNE_ABRECHNUNG = "OHNE_ABRECHNUNG";
	public static String FIELD__OHNE_AVV_VERTRAG_CHECK = "OHNE_AVV_VERTRAG_CHECK";
	public static String FIELD__ORT = "ORT";
	public static String FIELD__ORTZUSATZ = "ORTZUSATZ";
	public static String FIELD__PLZ = "PLZ";
	public static String FIELD__POSTENNUMMER = "POSTENNUMMER";
	public static String FIELD__PRINT_EU_AMTSBLATT = "PRINT_EU_AMTSBLATT";
	public static String FIELD__PRUEFUNG_MENGE = "PRUEFUNG_MENGE";
	public static String FIELD__PRUEFUNG_MENGE_AM = "PRUEFUNG_MENGE_AM";
	public static String FIELD__PRUEFUNG_MENGE_VON = "PRUEFUNG_MENGE_VON";
	public static String FIELD__PRUEFUNG_PREIS = "PRUEFUNG_PREIS";
	public static String FIELD__PRUEFUNG_PREIS_AM = "PRUEFUNG_PREIS_AM";
	public static String FIELD__PRUEFUNG_PREIS_VON = "PRUEFUNG_PREIS_VON";
	public static String FIELD__STEUERSATZ = "STEUERSATZ";
	public static String FIELD__STRASSE = "STRASSE";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TEL = "TEL";
	public static String FIELD__TRANSIT = "TRANSIT";
	public static String FIELD__WIEGEKARTENKENNER = "WIEGEKARTENKENNER";
	public static String FIELD__ZEITSTEMPEL = "ZEITSTEMPEL";
	public static String FIELD__ZOLLTARIFNR = "ZOLLTARIFNR";


	public String get_ABLADEMENGE_RECHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABLADEMENGE_RECHNUNG");
	}

	public String get_ABLADEMENGE_RECHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("ABLADEMENGE_RECHNUNG");	
	}

	public String get_ABLADEMENGE_RECHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABLADEMENGE_RECHNUNG");
	}

	public String get_ABLADEMENGE_RECHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABLADEMENGE_RECHNUNG",cNotNullValue);
	}

	public String get_ABLADEMENGE_RECHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABLADEMENGE_RECHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABLADEMENGE_RECHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABLADEMENGE_RECHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEMENGE_RECHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEMENGE_RECHNUNG", calNewValueFormated);
	}
		public boolean is_ABLADEMENGE_RECHNUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABLADEMENGE_RECHNUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABLADEMENGE_RECHNUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABLADEMENGE_RECHNUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ABZUG_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABZUG_MENGE");
	}

	public String get_ABZUG_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("ABZUG_MENGE");	
	}

	public String get_ABZUG_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABZUG_MENGE");
	}

	public String get_ABZUG_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABZUG_MENGE",cNotNullValue);
	}

	public String get_ABZUG_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABZUG_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABZUG_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABZUG_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABZUG_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABZUG_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_MENGE", calNewValueFormated);
	}
		public Double get_ABZUG_MENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ABZUG_MENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ABZUG_MENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ABZUG_MENGE").getDoubleValue();
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
	public String get_ABZUG_MENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABZUG_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ABZUG_MENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABZUG_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ABZUG_MENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ABZUG_MENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ABZUG_MENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ABZUG_MENGE").getBigDecimalValue();
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
	
	
	public String get_ANR1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1");
	}

	public String get_ANR1_cF() throws myException
	{
		return this.get_FormatedValue("ANR1");	
	}

	public String get_ANR1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1");
	}

	public String get_ANR1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1",cNotNullValue);
	}

	public String get_ANR1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", calNewValueFormated);
	}
		public String get_ANR2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR2");
	}

	public String get_ANR2_cF() throws myException
	{
		return this.get_FormatedValue("ANR2");	
	}

	public String get_ANR2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR2");
	}

	public String get_ANR2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR2",cNotNullValue);
	}

	public String get_ANR2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", calNewValueFormated);
	}
		public String get_ANTEIL_ABLADEMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_ABLADEMENGE");
	}

	public String get_ANTEIL_ABLADEMENGE_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_ABLADEMENGE");	
	}

	public String get_ANTEIL_ABLADEMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_ABLADEMENGE");
	}

	public String get_ANTEIL_ABLADEMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_ABLADEMENGE",cNotNullValue);
	}

	public String get_ANTEIL_ABLADEMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_ABLADEMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_ABLADEMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE", calNewValueFormated);
	}
		public Double get_ANTEIL_ABLADEMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_ABLADEMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_ABLADEMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_ABLADEMENGE").getDoubleValue();
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
	public String get_ANTEIL_ABLADEMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_ABLADEMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_ABLADEMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_ABLADEMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_ABLADEMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_ABLADEMENGE").getBigDecimalValue();
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
	
	
	public String get_ANTEIL_LADEMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_LADEMENGE");
	}

	public String get_ANTEIL_LADEMENGE_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_LADEMENGE");	
	}

	public String get_ANTEIL_LADEMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_LADEMENGE");
	}

	public String get_ANTEIL_LADEMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_LADEMENGE",cNotNullValue);
	}

	public String get_ANTEIL_LADEMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_LADEMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_LADEMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_LADEMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_LADEMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE", calNewValueFormated);
	}
		public Double get_ANTEIL_LADEMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_LADEMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_LADEMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_LADEMENGE").getDoubleValue();
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
	public String get_ANTEIL_LADEMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_LADEMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_LADEMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_LADEMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_LADEMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_LADEMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_LADEMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_LADEMENGE").getBigDecimalValue();
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
	
	
	public String get_ANTEIL_PLANMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_PLANMENGE");
	}

	public String get_ANTEIL_PLANMENGE_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_PLANMENGE");	
	}

	public String get_ANTEIL_PLANMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_PLANMENGE");
	}

	public String get_ANTEIL_PLANMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_PLANMENGE",cNotNullValue);
	}

	public String get_ANTEIL_PLANMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_PLANMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_PLANMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_PLANMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_PLANMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE", calNewValueFormated);
	}
		public Double get_ANTEIL_PLANMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_PLANMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_PLANMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_PLANMENGE").getDoubleValue();
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
	public String get_ANTEIL_PLANMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_PLANMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_PLANMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_PLANMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_PLANMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_PLANMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_PLANMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_PLANMENGE").getBigDecimalValue();
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
	
	
	public String get_ARTBEZ1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1");
	}

	public String get_ARTBEZ1_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ1");	
	}

	public String get_ARTBEZ1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ1");
	}

	public String get_ARTBEZ1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1",cNotNullValue);
	}

	public String get_ARTBEZ1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", calNewValueFormated);
	}
		public String get_ARTBEZ2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2");
	}

	public String get_ARTBEZ2_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ2");	
	}

	public String get_ARTBEZ2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ2");
	}

	public String get_ARTBEZ2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2",cNotNullValue);
	}

	public String get_ARTBEZ2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", calNewValueFormated);
	}
		public String get_AVV_AUSSTELLUNG_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("AVV_AUSSTELLUNG_DATUM");
	}

	public String get_AVV_AUSSTELLUNG_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("AVV_AUSSTELLUNG_DATUM");	
	}

	public String get_AVV_AUSSTELLUNG_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AVV_AUSSTELLUNG_DATUM");
	}

	public String get_AVV_AUSSTELLUNG_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AVV_AUSSTELLUNG_DATUM",cNotNullValue);
	}

	public String get_AVV_AUSSTELLUNG_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AVV_AUSSTELLUNG_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", calNewValueFormated);
	}
		public String get_BASEL_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BASEL_CODE");
	}

	public String get_BASEL_CODE_cF() throws myException
	{
		return this.get_FormatedValue("BASEL_CODE");	
	}

	public String get_BASEL_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BASEL_CODE");
	}

	public String get_BASEL_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BASEL_CODE",cNotNullValue);
	}

	public String get_BASEL_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BASEL_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BASEL_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_CODE", calNewValueFormated);
	}
		public String get_BASEL_NOTIZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("BASEL_NOTIZ");
	}

	public String get_BASEL_NOTIZ_cF() throws myException
	{
		return this.get_FormatedValue("BASEL_NOTIZ");	
	}

	public String get_BASEL_NOTIZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BASEL_NOTIZ");
	}

	public String get_BASEL_NOTIZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BASEL_NOTIZ",cNotNullValue);
	}

	public String get_BASEL_NOTIZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BASEL_NOTIZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", calNewValueFormated);
	}
		public String get_BEMERKUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG");
	}

	public String get_BEMERKUNG_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG");	
	}

	public String get_BEMERKUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG");
	}

	public String get_BEMERKUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG",cNotNullValue);
	}

	public String get_BEMERKUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", calNewValueFormated);
	}
		public String get_BESTELLNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER");
	}

	public String get_BESTELLNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER");	
	}

	public String get_BESTELLNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESTELLNUMMER");
	}

	public String get_BESTELLNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER",cNotNullValue);
	}

	public String get_BESTELLNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER", calNewValueFormated);
	}
		public String get_BUCHUNGSNUMMER_ZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNUMMER_ZUSATZ");
	}

	public String get_BUCHUNGSNUMMER_ZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNUMMER_ZUSATZ");	
	}

	public String get_BUCHUNGSNUMMER_ZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSNUMMER_ZUSATZ");
	}

	public String get_BUCHUNGSNUMMER_ZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNUMMER_ZUSATZ",cNotNullValue);
	}

	public String get_BUCHUNGSNUMMER_ZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNUMMER_ZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_ZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER_ZUSATZ", calNewValueFormated);
	}
		public String get_DATUM_LADE_ABLADE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_LADE_ABLADE");
	}

	public String get_DATUM_LADE_ABLADE_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_LADE_ABLADE");	
	}

	public String get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_LADE_ABLADE");
	}

	public String get_DATUM_LADE_ABLADE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_LADE_ABLADE",cNotNullValue);
	}

	public String get_DATUM_LADE_ABLADE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_LADE_ABLADE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_LADE_ABLADE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_LADE_ABLADE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_LADE_ABLADE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_LADE_ABLADE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_LADE_ABLADE", calNewValueFormated);
	}
		public String get_DEF_QUELLE_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEF_QUELLE_ZIEL");
	}

	public String get_DEF_QUELLE_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("DEF_QUELLE_ZIEL");	
	}

	public String get_DEF_QUELLE_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEF_QUELLE_ZIEL");
	}

	public String get_DEF_QUELLE_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEF_QUELLE_ZIEL",cNotNullValue);
	}

	public String get_DEF_QUELLE_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEF_QUELLE_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEF_QUELLE_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEF_QUELLE_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEF_QUELLE_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_QUELLE_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEF_QUELLE_ZIEL", calNewValueFormated);
	}
		public String get_DELETED_cUF() throws myException
	{
		return this.get_UnFormatedValue("DELETED");
	}

	public String get_DELETED_cF() throws myException
	{
		return this.get_FormatedValue("DELETED");	
	}

	public String get_DELETED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DELETED");
	}

	public String get_DELETED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DELETED",cNotNullValue);
	}

	public String get_DELETED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DELETED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DELETED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DELETED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DELETED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", calNewValueFormated);
	}
		public boolean is_DELETED_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DELETED_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DELETED_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DELETED_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DEL_DATE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_DATE");
	}

	public String get_DEL_DATE_cF() throws myException
	{
		return this.get_FormatedValue("DEL_DATE");	
	}

	public String get_DEL_DATE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_DATE");
	}

	public String get_DEL_DATE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_DATE",cNotNullValue);
	}

	public String get_DEL_DATE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_DATE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_DATE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", calNewValueFormated);
	}
		public String get_DEL_GRUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_GRUND");
	}

	public String get_DEL_GRUND_cF() throws myException
	{
		return this.get_FormatedValue("DEL_GRUND");	
	}

	public String get_DEL_GRUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_GRUND");
	}

	public String get_DEL_GRUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_GRUND",cNotNullValue);
	}

	public String get_DEL_GRUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_GRUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_GRUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_GRUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_GRUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_GRUND", calNewValueFormated);
	}
		public String get_DEL_KUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_KUERZEL");
	}

	public String get_DEL_KUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("DEL_KUERZEL");	
	}

	public String get_DEL_KUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_KUERZEL");
	}

	public String get_DEL_KUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_KUERZEL",cNotNullValue);
	}

	public String get_DEL_KUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_KUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", calNewValueFormated);
	}
		public String get_EINHEIT_MENGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINHEIT_MENGEN");
	}

	public String get_EINHEIT_MENGEN_cF() throws myException
	{
		return this.get_FormatedValue("EINHEIT_MENGEN");	
	}

	public String get_EINHEIT_MENGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINHEIT_MENGEN");
	}

	public String get_EINHEIT_MENGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINHEIT_MENGEN",cNotNullValue);
	}

	public String get_EINHEIT_MENGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINHEIT_MENGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINHEIT_MENGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINHEIT_MENGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINHEIT_MENGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_MENGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT_MENGEN", calNewValueFormated);
	}
		public String get_EINZELPREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS");
	}

	public String get_EINZELPREIS_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS");	
	}

	public String get_EINZELPREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS");
	}

	public String get_EINZELPREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS",cNotNullValue);
	}

	public String get_EINZELPREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS", calNewValueFormated);
	}
		public Double get_EINZELPREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS").getDoubleValue();
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
	public String get_EINZELPREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EINZELPREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EINZELPREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS").getBigDecimalValue();
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
	
	
	public String get_EPREIS_RESULT_NETTO_MGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_RESULT_NETTO_MGE");
	}

	public String get_EPREIS_RESULT_NETTO_MGE_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_RESULT_NETTO_MGE");	
	}

	public String get_EPREIS_RESULT_NETTO_MGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_RESULT_NETTO_MGE");
	}

	public String get_EPREIS_RESULT_NETTO_MGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_RESULT_NETTO_MGE",cNotNullValue);
	}

	public String get_EPREIS_RESULT_NETTO_MGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_RESULT_NETTO_MGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_RESULT_NETTO_MGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE", calNewValueFormated);
	}
		public Double get_EPREIS_RESULT_NETTO_MGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_RESULT_NETTO_MGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_RESULT_NETTO_MGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_RESULT_NETTO_MGE").getDoubleValue();
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
	public String get_EPREIS_RESULT_NETTO_MGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_RESULT_NETTO_MGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EPREIS_RESULT_NETTO_MGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_RESULT_NETTO_MGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EPREIS_RESULT_NETTO_MGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_RESULT_NETTO_MGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_RESULT_NETTO_MGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_RESULT_NETTO_MGE").getBigDecimalValue();
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
		public String get_EUCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EUCODE");
	}

	public String get_EUCODE_cF() throws myException
	{
		return this.get_FormatedValue("EUCODE");	
	}

	public String get_EUCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EUCODE");
	}

	public String get_EUCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EUCODE",cNotNullValue);
	}

	public String get_EUCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EUCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EUCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EUCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EUCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUCODE", calNewValueFormated);
	}
		public String get_EUNOTIZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("EUNOTIZ");
	}

	public String get_EUNOTIZ_cF() throws myException
	{
		return this.get_FormatedValue("EUNOTIZ");	
	}

	public String get_EUNOTIZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EUNOTIZ");
	}

	public String get_EUNOTIZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EUNOTIZ",cNotNullValue);
	}

	public String get_EUNOTIZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EUNOTIZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EUNOTIZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUNOTIZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUNOTIZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUNOTIZ", calNewValueFormated);
	}
		public String get_EU_BLATT_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_MENGE");
	}

	public String get_EU_BLATT_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("EU_BLATT_MENGE");	
	}

	public String get_EU_BLATT_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BLATT_MENGE");
	}

	public String get_EU_BLATT_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_MENGE",cNotNullValue);
	}

	public String get_EU_BLATT_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BLATT_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", calNewValueFormated);
	}
		public Double get_EU_BLATT_MENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EU_BLATT_MENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EU_BLATT_MENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EU_BLATT_MENGE").getDoubleValue();
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
	public String get_EU_BLATT_MENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EU_BLATT_MENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EU_BLATT_MENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_MENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EU_BLATT_MENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_MENGE").getBigDecimalValue();
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
	
	
	public String get_EU_BLATT_VOLUMEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_VOLUMEN");
	}

	public String get_EU_BLATT_VOLUMEN_cF() throws myException
	{
		return this.get_FormatedValue("EU_BLATT_VOLUMEN");	
	}

	public String get_EU_BLATT_VOLUMEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BLATT_VOLUMEN");
	}

	public String get_EU_BLATT_VOLUMEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_VOLUMEN",cNotNullValue);
	}

	public String get_EU_BLATT_VOLUMEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BLATT_VOLUMEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", calNewValueFormated);
	}
		public Double get_EU_BLATT_VOLUMEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EU_BLATT_VOLUMEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EU_BLATT_VOLUMEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EU_BLATT_VOLUMEN").getDoubleValue();
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
	public String get_EU_BLATT_VOLUMEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_VOLUMEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EU_BLATT_VOLUMEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_VOLUMEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EU_BLATT_VOLUMEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_VOLUMEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EU_BLATT_VOLUMEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_VOLUMEN").getBigDecimalValue();
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
	
	
	public String get_EU_STEUER_VERMERK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK");
	}

	public String get_EU_STEUER_VERMERK_cF() throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK");	
	}

	public String get_EU_STEUER_VERMERK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_STEUER_VERMERK");
	}

	public String get_EU_STEUER_VERMERK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK",cNotNullValue);
	}

	public String get_EU_STEUER_VERMERK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_STEUER_VERMERK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK", calNewValueFormated);
	}
		public String get_FAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAX");
	}

	public String get_FAX_cF() throws myException
	{
		return this.get_FormatedValue("FAX");	
	}

	public String get_FAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAX");
	}

	public String get_FAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAX",cNotNullValue);
	}

	public String get_FAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX", calNewValueFormated);
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
		public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN");
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cF() throws myException
	{
		return this.get_FormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN");	
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GELANGENSBESTAETIGUNG_ERHALTEN");
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN",cNotNullValue);
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", calNewValueFormated);
	}
		public boolean is_GELANGENSBESTAETIGUNG_ERHALTEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GELANGENSBESTAETIGUNG_ERHALTEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_HAUSNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("HAUSNUMMER");
	}

	public String get_HAUSNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("HAUSNUMMER");	
	}

	public String get_HAUSNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HAUSNUMMER");
	}

	public String get_HAUSNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HAUSNUMMER",cNotNullValue);
	}

	public String get_HAUSNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HAUSNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAUSNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAUSNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAUSNUMMER", calNewValueFormated);
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
	
	
	public String get_ID_ARTIKEL_BEZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ");
	}

	public String get_ID_ARTIKEL_BEZ_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ");	
	}

	public String get_ID_ARTIKEL_BEZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_BEZ");
	}

	public String get_ID_ARTIKEL_BEZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ",cNotNullValue);
	}

	public String get_ID_ARTIKEL_BEZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_BEZ_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_BEZ").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_BEZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_BEZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ").getDoubleValue();
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
	public String get_ID_ARTIKEL_BEZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ARTIKEL_BEZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ARTIKEL_BEZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_BEZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ").getBigDecimalValue();
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
	
	
	public String get_ID_EAK_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE");
	}

	public String get_ID_EAK_CODE_cF() throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE");	
	}

	public String get_ID_EAK_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EAK_CODE");
	}

	public String get_ID_EAK_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE",cNotNullValue);
	}

	public String get_ID_EAK_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public Long get_ID_EAK_CODE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EAK_CODE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EAK_CODE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EAK_CODE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE").getDoubleValue();
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
	public String get_ID_EAK_CODE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_EAK_CODE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_EAK_CODE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EAK_CODE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX");
	}

	public String get_ID_TAX_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX");	
	}

	public String get_ID_TAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX");
	}

	public String get_ID_TAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX",cNotNullValue);
	}

	public String get_ID_TAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX", calNewValueFormated);
	}
		public Long get_ID_TAX_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX").getDoubleValue();
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
	public String get_ID_TAX_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_TAX_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_TAX_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_KON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON");
	}

	public String get_ID_VPOS_KON_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON");	
	}

	public String get_ID_VPOS_KON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_KON");
	}

	public String get_ID_VPOS_KON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON",cNotNullValue);
	}

	public String get_ID_VPOS_KON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON", calNewValueFormated);
	}
		public Long get_ID_VPOS_KON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_KON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_KON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_KON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON").getDoubleValue();
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
	public String get_ID_VPOS_KON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_KON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_KON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_KON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_STD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_STD");
	}

	public String get_ID_VPOS_STD_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_STD");	
	}

	public String get_ID_VPOS_STD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_STD");
	}

	public String get_ID_VPOS_STD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_STD",cNotNullValue);
	}

	public String get_ID_VPOS_STD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_STD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_STD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD", calNewValueFormated);
	}
		public Long get_ID_VPOS_STD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_STD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_STD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_STD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_STD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_STD").getDoubleValue();
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
	public String get_ID_VPOS_STD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_STD_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_STD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_STD_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_STD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_STD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_STD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_STD").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_SONDER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_SONDER");
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_SONDER");	
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_SONDER");
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_SONDER",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_SONDER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_SONDER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_SONDER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_SONDER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_SONDER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_SONDER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_TPA_FUHRE_SONDER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_SONDER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_TPA_FUHRE_SONDER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_SONDER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getBigDecimalValue();
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
	
	
	public String get_INTRASTAT_MELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD");
	}

	public String get_INTRASTAT_MELD_cF() throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD");	
	}

	public String get_INTRASTAT_MELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTRASTAT_MELD");
	}

	public String get_INTRASTAT_MELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD",cNotNullValue);
	}

	public String get_INTRASTAT_MELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTRASTAT_MELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTRASTAT_MELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD", calNewValueFormated);
	}
		public String get_KEIN_KONTRAKT_NOETIG_cUF() throws myException
	{
		return this.get_UnFormatedValue("KEIN_KONTRAKT_NOETIG");
	}

	public String get_KEIN_KONTRAKT_NOETIG_cF() throws myException
	{
		return this.get_FormatedValue("KEIN_KONTRAKT_NOETIG");	
	}

	public String get_KEIN_KONTRAKT_NOETIG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KEIN_KONTRAKT_NOETIG");
	}

	public String get_KEIN_KONTRAKT_NOETIG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KEIN_KONTRAKT_NOETIG",cNotNullValue);
	}

	public String get_KEIN_KONTRAKT_NOETIG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KEIN_KONTRAKT_NOETIG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", calNewValueFormated);
	}
		public boolean is_KEIN_KONTRAKT_NOETIG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEIN_KONTRAKT_NOETIG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KEIN_KONTRAKT_NOETIG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEIN_KONTRAKT_NOETIG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LADEMENGE_GUTSCHRIFT_cUF() throws myException
	{
		return this.get_UnFormatedValue("LADEMENGE_GUTSCHRIFT");
	}

	public String get_LADEMENGE_GUTSCHRIFT_cF() throws myException
	{
		return this.get_FormatedValue("LADEMENGE_GUTSCHRIFT");	
	}

	public String get_LADEMENGE_GUTSCHRIFT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LADEMENGE_GUTSCHRIFT");
	}

	public String get_LADEMENGE_GUTSCHRIFT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LADEMENGE_GUTSCHRIFT",cNotNullValue);
	}

	public String get_LADEMENGE_GUTSCHRIFT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LADEMENGE_GUTSCHRIFT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LADEMENGE_GUTSCHRIFT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADEMENGE_GUTSCHRIFT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADEMENGE_GUTSCHRIFT", calNewValueFormated);
	}
		public boolean is_LADEMENGE_GUTSCHRIFT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_LADEMENGE_GUTSCHRIFT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_LADEMENGE_GUTSCHRIFT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_LADEMENGE_GUTSCHRIFT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LAENDERCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE");
	}

	public String get_LAENDERCODE_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE");	
	}

	public String get_LAENDERCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE");
	}

	public String get_LAENDERCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE",cNotNullValue);
	}

	public String get_LAENDERCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE", calNewValueFormated);
	}
		public String get_LAENDERCODE_TRANSIT1_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT1");
	}

	public String get_LAENDERCODE_TRANSIT1_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT1");	
	}

	public String get_LAENDERCODE_TRANSIT1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_TRANSIT1");
	}

	public String get_LAENDERCODE_TRANSIT1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT1",cNotNullValue);
	}

	public String get_LAENDERCODE_TRANSIT1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", calNewValueFormated);
	}
		public String get_LAENDERCODE_TRANSIT2_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT2");
	}

	public String get_LAENDERCODE_TRANSIT2_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT2");	
	}

	public String get_LAENDERCODE_TRANSIT2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_TRANSIT2");
	}

	public String get_LAENDERCODE_TRANSIT2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT2",cNotNullValue);
	}

	public String get_LAENDERCODE_TRANSIT2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", calNewValueFormated);
	}
		public String get_LAENDERCODE_TRANSIT3_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT3");
	}

	public String get_LAENDERCODE_TRANSIT3_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT3");	
	}

	public String get_LAENDERCODE_TRANSIT3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_TRANSIT3");
	}

	public String get_LAENDERCODE_TRANSIT3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT3",cNotNullValue);
	}

	public String get_LAENDERCODE_TRANSIT3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", calNewValueFormated);
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
		public String get_LIEFERBED_ALTERNATIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERBED_ALTERNATIV");
	}

	public String get_LIEFERBED_ALTERNATIV_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERBED_ALTERNATIV");	
	}

	public String get_LIEFERBED_ALTERNATIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERBED_ALTERNATIV");
	}

	public String get_LIEFERBED_ALTERNATIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERBED_ALTERNATIV",cNotNullValue);
	}

	public String get_LIEFERBED_ALTERNATIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERBED_ALTERNATIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERBED_ALTERNATIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERBED_ALTERNATIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV", calNewValueFormated);
	}
		public String get_MANUELL_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("MANUELL_PREIS");
	}

	public String get_MANUELL_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("MANUELL_PREIS");	
	}

	public String get_MANUELL_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MANUELL_PREIS");
	}

	public String get_MANUELL_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MANUELL_PREIS",cNotNullValue);
	}

	public String get_MANUELL_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MANUELL_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MANUELL_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MANUELL_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MANUELL_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS", calNewValueFormated);
	}
		public boolean is_MANUELL_PREIS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MANUELL_PREIS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MANUELL_PREIS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MANUELL_PREIS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_NAME1_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME1");
	}

	public String get_NAME1_cF() throws myException
	{
		return this.get_FormatedValue("NAME1");	
	}

	public String get_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME1");
	}

	public String get_NAME1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME1",cNotNullValue);
	}

	public String get_NAME1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public String get_NAME2_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME2");
	}

	public String get_NAME2_cF() throws myException
	{
		return this.get_FormatedValue("NAME2");	
	}

	public String get_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME2");
	}

	public String get_NAME2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME2",cNotNullValue);
	}

	public String get_NAME2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public String get_NAME3_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME3");
	}

	public String get_NAME3_cF() throws myException
	{
		return this.get_FormatedValue("NAME3");	
	}

	public String get_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME3");
	}

	public String get_NAME3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME3",cNotNullValue);
	}

	public String get_NAME3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public String get_NATIONALER_ABFALL_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("NATIONALER_ABFALL_CODE");
	}

	public String get_NATIONALER_ABFALL_CODE_cF() throws myException
	{
		return this.get_FormatedValue("NATIONALER_ABFALL_CODE");	
	}

	public String get_NATIONALER_ABFALL_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NATIONALER_ABFALL_CODE");
	}

	public String get_NATIONALER_ABFALL_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NATIONALER_ABFALL_CODE",cNotNullValue);
	}

	public String get_NATIONALER_ABFALL_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NATIONALER_ABFALL_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NATIONALER_ABFALL_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NATIONALER_ABFALL_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NATIONALER_ABFALL_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NATIONALER_ABFALL_CODE", calNewValueFormated);
	}
		public String get_NOTIFIKATION_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("NOTIFIKATION_NR");
	}

	public String get_NOTIFIKATION_NR_cF() throws myException
	{
		return this.get_FormatedValue("NOTIFIKATION_NR");	
	}

	public String get_NOTIFIKATION_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NOTIFIKATION_NR");
	}

	public String get_NOTIFIKATION_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NOTIFIKATION_NR",cNotNullValue);
	}

	public String get_NOTIFIKATION_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NOTIFIKATION_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NOTIFIKATION_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NOTIFIKATION_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NOTIFIKATION_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR", calNewValueFormated);
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
		public String get_OHNE_ABRECHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("OHNE_ABRECHNUNG");
	}

	public String get_OHNE_ABRECHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("OHNE_ABRECHNUNG");	
	}

	public String get_OHNE_ABRECHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OHNE_ABRECHNUNG");
	}

	public String get_OHNE_ABRECHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OHNE_ABRECHNUNG",cNotNullValue);
	}

	public String get_OHNE_ABRECHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OHNE_ABRECHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", calNewValueFormated);
	}
		public boolean is_OHNE_ABRECHNUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_ABRECHNUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OHNE_ABRECHNUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_ABRECHNUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OHNE_AVV_VERTRAG_CHECK_cUF() throws myException
	{
		return this.get_UnFormatedValue("OHNE_AVV_VERTRAG_CHECK");
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_cF() throws myException
	{
		return this.get_FormatedValue("OHNE_AVV_VERTRAG_CHECK");	
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OHNE_AVV_VERTRAG_CHECK");
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OHNE_AVV_VERTRAG_CHECK",cNotNullValue);
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OHNE_AVV_VERTRAG_CHECK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", calNewValueFormated);
	}
		public boolean is_OHNE_AVV_VERTRAG_CHECK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_AVV_VERTRAG_CHECK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OHNE_AVV_VERTRAG_CHECK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_AVV_VERTRAG_CHECK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORT");
	}

	public String get_ORT_cF() throws myException
	{
		return this.get_FormatedValue("ORT");	
	}

	public String get_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORT");
	}

	public String get_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORT",cNotNullValue);
	}

	public String get_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public String get_ORTZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORTZUSATZ");
	}

	public String get_ORTZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("ORTZUSATZ");	
	}

	public String get_ORTZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORTZUSATZ");
	}

	public String get_ORTZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORTZUSATZ",cNotNullValue);
	}

	public String get_ORTZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORTZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORTZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORTZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORTZUSATZ", calNewValueFormated);
	}
		public String get_PLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("PLZ");
	}

	public String get_PLZ_cF() throws myException
	{
		return this.get_FormatedValue("PLZ");	
	}

	public String get_PLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PLZ");
	}

	public String get_PLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PLZ",cNotNullValue);
	}

	public String get_PLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
	}
		public String get_POSTENNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSTENNUMMER");
	}

	public String get_POSTENNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("POSTENNUMMER");	
	}

	public String get_POSTENNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSTENNUMMER");
	}

	public String get_POSTENNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSTENNUMMER",cNotNullValue);
	}

	public String get_POSTENNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSTENNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSTENNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSTENNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSTENNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSTENNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER", calNewValueFormated);
	}
		public String get_PRINT_EU_AMTSBLATT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRINT_EU_AMTSBLATT");
	}

	public String get_PRINT_EU_AMTSBLATT_cF() throws myException
	{
		return this.get_FormatedValue("PRINT_EU_AMTSBLATT");	
	}

	public String get_PRINT_EU_AMTSBLATT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRINT_EU_AMTSBLATT");
	}

	public String get_PRINT_EU_AMTSBLATT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRINT_EU_AMTSBLATT",cNotNullValue);
	}

	public String get_PRINT_EU_AMTSBLATT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRINT_EU_AMTSBLATT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", calNewValueFormated);
	}
		public boolean is_PRINT_EU_AMTSBLATT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_EU_AMTSBLATT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRINT_EU_AMTSBLATT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_EU_AMTSBLATT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRUEFUNG_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_MENGE");
	}

	public String get_PRUEFUNG_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_MENGE");	
	}

	public String get_PRUEFUNG_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_MENGE");
	}

	public String get_PRUEFUNG_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_MENGE",cNotNullValue);
	}

	public String get_PRUEFUNG_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE", calNewValueFormated);
	}
		public boolean is_PRUEFUNG_MENGE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_MENGE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRUEFUNG_MENGE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_MENGE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRUEFUNG_MENGE_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_MENGE_AM");
	}

	public String get_PRUEFUNG_MENGE_AM_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_MENGE_AM");	
	}

	public String get_PRUEFUNG_MENGE_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_MENGE_AM");
	}

	public String get_PRUEFUNG_MENGE_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_MENGE_AM",cNotNullValue);
	}

	public String get_PRUEFUNG_MENGE_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_MENGE_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_MENGE_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_MENGE_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_AM", calNewValueFormated);
	}
		public String get_PRUEFUNG_MENGE_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_MENGE_VON");
	}

	public String get_PRUEFUNG_MENGE_VON_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_MENGE_VON");	
	}

	public String get_PRUEFUNG_MENGE_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_MENGE_VON");
	}

	public String get_PRUEFUNG_MENGE_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_MENGE_VON",cNotNullValue);
	}

	public String get_PRUEFUNG_MENGE_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_MENGE_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_MENGE_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_MENGE_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_MENGE_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_MENGE_VON", calNewValueFormated);
	}
		public String get_PRUEFUNG_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_PREIS");
	}

	public String get_PRUEFUNG_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_PREIS");	
	}

	public String get_PRUEFUNG_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_PREIS");
	}

	public String get_PRUEFUNG_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_PREIS",cNotNullValue);
	}

	public String get_PRUEFUNG_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS", calNewValueFormated);
	}
		public boolean is_PRUEFUNG_PREIS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_PREIS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRUEFUNG_PREIS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_PREIS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRUEFUNG_PREIS_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_PREIS_AM");
	}

	public String get_PRUEFUNG_PREIS_AM_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_PREIS_AM");	
	}

	public String get_PRUEFUNG_PREIS_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_PREIS_AM");
	}

	public String get_PRUEFUNG_PREIS_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_PREIS_AM",cNotNullValue);
	}

	public String get_PRUEFUNG_PREIS_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_PREIS_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_PREIS_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_PREIS_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_AM", calNewValueFormated);
	}
		public String get_PRUEFUNG_PREIS_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_PREIS_VON");
	}

	public String get_PRUEFUNG_PREIS_VON_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_PREIS_VON");	
	}

	public String get_PRUEFUNG_PREIS_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_PREIS_VON");
	}

	public String get_PRUEFUNG_PREIS_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_PREIS_VON",cNotNullValue);
	}

	public String get_PRUEFUNG_PREIS_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_PREIS_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_PREIS_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_PREIS_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_PREIS_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_PREIS_VON", calNewValueFormated);
	}
		public String get_STEUERSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ");
	}

	public String get_STEUERSATZ_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ");	
	}

	public String get_STEUERSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ");
	}

	public String get_STEUERSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ",cNotNullValue);
	}

	public String get_STEUERSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ", calNewValueFormated);
	}
		public Double get_STEUERSATZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ").getDoubleValue();
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
	public String get_STEUERSATZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_STEUERSATZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_STEUERSATZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ").getBigDecimalValue();
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
	
	
	public String get_STRASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("STRASSE");
	}

	public String get_STRASSE_cF() throws myException
	{
		return this.get_FormatedValue("STRASSE");	
	}

	public String get_STRASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STRASSE");
	}

	public String get_STRASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STRASSE",cNotNullValue);
	}

	public String get_STRASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STRASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
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
	
	
	public String get_TEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEL");
	}

	public String get_TEL_cF() throws myException
	{
		return this.get_FormatedValue("TEL");	
	}

	public String get_TEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEL");
	}

	public String get_TEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEL",cNotNullValue);
	}

	public String get_TEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL", calNewValueFormated);
	}
		public String get_TRANSIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSIT");
	}

	public String get_TRANSIT_cF() throws myException
	{
		return this.get_FormatedValue("TRANSIT");	
	}

	public String get_TRANSIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSIT");
	}

	public String get_TRANSIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSIT",cNotNullValue);
	}

	public String get_TRANSIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT", calNewValueFormated);
	}
		public boolean is_TRANSIT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSIT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_WIEGEKARTENKENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER");
	}

	public String get_WIEGEKARTENKENNER_cF() throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER");	
	}

	public String get_WIEGEKARTENKENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEGEKARTENKENNER");
	}

	public String get_WIEGEKARTENKENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER",cNotNullValue);
	}

	public String get_WIEGEKARTENKENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", calNewValueFormated);
	}
		public String get_ZEITSTEMPEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEITSTEMPEL");
	}

	public String get_ZEITSTEMPEL_cF() throws myException
	{
		return this.get_FormatedValue("ZEITSTEMPEL");	
	}

	public String get_ZEITSTEMPEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEITSTEMPEL");
	}

	public String get_ZEITSTEMPEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEITSTEMPEL",cNotNullValue);
	}

	public String get_ZEITSTEMPEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEITSTEMPEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", calNewValueFormated);
	}
		public String get_ZOLLTARIFNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIFNR");
	}

	public String get_ZOLLTARIFNR_cF() throws myException
	{
		return this.get_FormatedValue("ZOLLTARIFNR");	
	}

	public String get_ZOLLTARIFNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZOLLTARIFNR");
	}

	public String get_ZOLLTARIFNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIFNR",cNotNullValue);
	}

	public String get_ZOLLTARIFNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZOLLTARIFNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABLADEMENGE_RECHNUNG",MyRECORD.DATATYPES.YESNO);
	put("ABZUG_MENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANR1",MyRECORD.DATATYPES.TEXT);
	put("ANR2",MyRECORD.DATATYPES.TEXT);
	put("ANTEIL_ABLADEMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANTEIL_LADEMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANTEIL_PLANMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ARTBEZ1",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ2",MyRECORD.DATATYPES.TEXT);
	put("AVV_AUSSTELLUNG_DATUM",MyRECORD.DATATYPES.DATE);
	put("BASEL_CODE",MyRECORD.DATATYPES.TEXT);
	put("BASEL_NOTIZ",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("BESTELLNUMMER",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSNUMMER_ZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("DATUM_LADE_ABLADE",MyRECORD.DATATYPES.DATE);
	put("DEF_QUELLE_ZIEL",MyRECORD.DATATYPES.TEXT);
	put("DELETED",MyRECORD.DATATYPES.YESNO);
	put("DEL_DATE",MyRECORD.DATATYPES.DATE);
	put("DEL_GRUND",MyRECORD.DATATYPES.TEXT);
	put("DEL_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("EINHEIT_MENGEN",MyRECORD.DATATYPES.TEXT);
	put("EINZELPREIS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EPREIS_RESULT_NETTO_MGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EUCODE",MyRECORD.DATATYPES.TEXT);
	put("EUNOTIZ",MyRECORD.DATATYPES.TEXT);
	put("EU_BLATT_MENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EU_BLATT_VOLUMEN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EU_STEUER_VERMERK",MyRECORD.DATATYPES.TEXT);
	put("FAX",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GELANGENSBESTAETIGUNG_ERHALTEN",MyRECORD.DATATYPES.YESNO);
	put("HAUSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_EAK_CODE",MyRECORD.DATATYPES.NUMBER);
	put("ID_HANDELSDEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_KON",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_STD",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ORT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_SONDER",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE",MyRECORD.DATATYPES.NUMBER);
	put("INTRASTAT_MELD",MyRECORD.DATATYPES.TEXT);
	put("KEIN_KONTRAKT_NOETIG",MyRECORD.DATATYPES.YESNO);
	put("LADEMENGE_GUTSCHRIFT",MyRECORD.DATATYPES.YESNO);
	put("LAENDERCODE",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_TRANSIT1",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_TRANSIT2",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_TRANSIT3",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERBED_ALTERNATIV",MyRECORD.DATATYPES.TEXT);
	put("MANUELL_PREIS",MyRECORD.DATATYPES.YESNO);
	put("NAME1",MyRECORD.DATATYPES.TEXT);
	put("NAME2",MyRECORD.DATATYPES.TEXT);
	put("NAME3",MyRECORD.DATATYPES.TEXT);
	put("NATIONALER_ABFALL_CODE",MyRECORD.DATATYPES.TEXT);
	put("NOTIFIKATION_NR",MyRECORD.DATATYPES.TEXT);
	put("OEFFNUNGSZEITEN",MyRECORD.DATATYPES.TEXT);
	put("OHNE_ABRECHNUNG",MyRECORD.DATATYPES.YESNO);
	put("OHNE_AVV_VERTRAG_CHECK",MyRECORD.DATATYPES.YESNO);
	put("ORT",MyRECORD.DATATYPES.TEXT);
	put("ORTZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("PLZ",MyRECORD.DATATYPES.TEXT);
	put("POSTENNUMMER",MyRECORD.DATATYPES.TEXT);
	put("PRINT_EU_AMTSBLATT",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_MENGE",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_MENGE_AM",MyRECORD.DATATYPES.DATE);
	put("PRUEFUNG_MENGE_VON",MyRECORD.DATATYPES.TEXT);
	put("PRUEFUNG_PREIS",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_PREIS_AM",MyRECORD.DATATYPES.DATE);
	put("PRUEFUNG_PREIS_VON",MyRECORD.DATATYPES.TEXT);
	put("STEUERSATZ",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STRASSE",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TEL",MyRECORD.DATATYPES.TEXT);
	put("TRANSIT",MyRECORD.DATATYPES.YESNO);
	put("WIEGEKARTENKENNER",MyRECORD.DATATYPES.TEXT);
	put("ZEITSTEMPEL",MyRECORD.DATATYPES.TEXT);
	put("ZOLLTARIFNR",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_VPOS_TPA_FUHRE_ORT.HM_FIELDS;	
	}

}
