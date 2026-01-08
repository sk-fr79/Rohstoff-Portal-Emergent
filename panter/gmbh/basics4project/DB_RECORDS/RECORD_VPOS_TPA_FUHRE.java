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

public class RECORD_VPOS_TPA_FUHRE extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_VPOS_TPA_FUHRE";
    public static String IDFIELD   = "ID_VPOS_TPA_FUHRE";
    

	//erzeugen eines RECORDNEW_JT_VPOS_TPA_FUHRE - felds
	private RECORDNEW_VPOS_TPA_FUHRE   recNEW = null;

    private _TAB  tab = _TAB.vpos_tpa_fuhre;  



	public RECORD_VPOS_TPA_FUHRE() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");
	}


	public RECORD_VPOS_TPA_FUHRE(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");
	}



	public RECORD_VPOS_TPA_FUHRE(RECORD_VPOS_TPA_FUHRE recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE.TABLENAME);
	}


	//2014-04-03
	public RECORD_VPOS_TPA_FUHRE(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE.TABLENAME);
	}




	public RECORD_VPOS_TPA_FUHRE(long lID_Unformated) throws myException
	{
		super("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",""+lID_Unformated);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE.TABLENAME);
	}

	public RECORD_VPOS_TPA_FUHRE(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE", "ID_VPOS_TPA_FUHRE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE.TABLENAME);
	}
	
	

	public RECORD_VPOS_TPA_FUHRE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE.TABLENAME);

	}


	public RECORD_VPOS_TPA_FUHRE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE", "ID_VPOS_TPA_FUHRE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_VPOS_TPA_FUHRE();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_VPOS_TPA_FUHRE.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_VPOS_TPA_FUHRE";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_TPA_FUHRE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE", bibE2.cTO(), "ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_TPA_FUHRE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE", bibE2.cTO(), "ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
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
			if (S.isFull(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE", "ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_VPOS_TPA_FUHRE get_RECORDNEW_VPOS_TPA_FUHRE() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_VPOS_TPA_FUHRE();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_VPOS_TPA_FUHRE(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_VPOS_TPA_FUHRE create_Instance() throws myException {
		return new RECORD_VPOS_TPA_FUHRE();
	}
	
	public static RECORD_VPOS_TPA_FUHRE create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_VPOS_TPA_FUHRE(Conn);
    }

	public static RECORD_VPOS_TPA_FUHRE create_Instance(RECORD_VPOS_TPA_FUHRE recordOrig) {
		return new RECORD_VPOS_TPA_FUHRE(recordOrig);
    }

	public static RECORD_VPOS_TPA_FUHRE create_Instance(long lID_Unformated) throws myException {
		return new RECORD_VPOS_TPA_FUHRE(lID_Unformated);
    }

	public static RECORD_VPOS_TPA_FUHRE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_VPOS_TPA_FUHRE(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_VPOS_TPA_FUHRE create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_VPOS_TPA_FUHRE(lID_Unformated, Conn);
	}

	public static RECORD_VPOS_TPA_FUHRE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_VPOS_TPA_FUHRE(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_VPOS_TPA_FUHRE create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_VPOS_TPA_FUHRE(recordOrig);	    
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
    public RECORD_VPOS_TPA_FUHRE build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF());
      }
      //return new RECORD_VPOS_TPA_FUHRE(this.get_cSQL_4_Build());
      RECORD_VPOS_TPA_FUHRE rec = new RECORD_VPOS_TPA_FUHRE();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_VPOS_TPA_FUHRE
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_VPOS_TPA_FUHRE-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_VPOS_TPA_FUHRE get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_VPOS_TPA_FUHRE_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_VPOS_TPA_FUHRE  recNew = new RECORDNEW_VPOS_TPA_FUHRE();
		
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
    public RECORD_VPOS_TPA_FUHRE set_recordNew(RECORDNEW_VPOS_TPA_FUHRE recnew) throws myException {
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
	
	



		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_fremdauftrag = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lager_start = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lager_ziel = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_spedition = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_start = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_ziel = null;




		private RECORD_ARTIKEL UP_RECORD_ARTIKEL_id_artikel = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk = null;




		private RECORD_CONTAINERTYP UP_RECORD_CONTAINERTYP_id_containertyp_fp = null;




		private RECORD_EAK_CODE UP_RECORD_EAK_CODE_id_eak_code = null;




		private RECORD_FAHRPLAN_ZEITANGABE UP_RECORD_FAHRPLAN_ZEITANGABE_id_fahrplan_zeitangabe = null;




		private RECORD_HANDELSDEF UP_RECORD_HANDELSDEF_id_handelsdef = null;




		private RECORD_MASCHINEN UP_RECORD_MASCHINEN_id_maschinen_anh_fp = null;




		private RECORD_MASCHINEN UP_RECORD_MASCHINEN_id_maschinen_lkw_fp = null;




		private RECORD_TAX UP_RECORD_TAX_id_tax_ek = null;




		private RECORD_TAX UP_RECORD_TAX_id_tax_vk = null;




		private RECORD_UMA_KONTRAKT UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt = null;




		private RECORD_VERARBEITUNG UP_RECORD_VERARBEITUNG_id_verarbeitung = null;




		private RECORD_VERPACKUNGSART UP_RECORD_VERPACKUNGSART_id_verpackungsart = null;




		private RECORD_VPOS_KON UP_RECORD_VPOS_KON_id_vpos_kon_ek = null;




		private RECORD_VPOS_KON UP_RECORD_VPOS_KON_id_vpos_kon_vk = null;




		private RECORD_VPOS_STD UP_RECORD_VPOS_STD_id_vpos_std_ek = null;




		private RECORD_VPOS_STD UP_RECORD_VPOS_STD_id_vpos_std_vk = null;




		private RECORD_VPOS_TPA UP_RECORD_VPOS_TPA_id_vpos_tpa = null;




		private RECLIST_ABRECHBLATT DOWN_RECLIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech = null ;




		private RECLIST_ABRECHBLATT_POS DOWN_RECLIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle = null ;




		private RECLIST_BAM_IMPORT DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre = null ;




		private RECLIST_BEWEGUNG DOWN_RECLIST_BEWEGUNG_id_vpos_tpa_fuhre = null ;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre = null ;




		private RECLIST_DLP_JOIN_WARENBEWG DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre = null ;




		private RECLIST_DLP_JOIN_WARENBEWG DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl = null ;




		private RECLIST_FAHRPLANPOS DOWN_RECLIST_FAHRPLANPOS_id_vpos_tpa_fuhre = null ;




		private RECLIST_FBAM DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre = null ;




		private RECLIST_FP_POS_AKTE DOWN_RECLIST_FP_POS_AKTE_id_vpos_tpa_fuhre = null ;




		private RECLIST_FUHREN_RECHNUNGEN DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein = null ;




		private RECLIST_PROFORMA_RECHNUNG DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord = null ;




		private RECLIST_VPOS_TPA_FUHRE_ABZUG_EK DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre = null ;




		private RECLIST_VPOS_TPA_FUHRE_ABZUG_VK DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre = null ;




		private RECLIST_VPOS_TPA_FUHRE_DRUCK DOWN_RECLIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre = null ;




		private RECLIST_VPOS_TPA_FUHRE_KOSTEN DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre = null ;




		private RECLIST_VPOS_TPA_FUHRE_RGVL DOWN_RECLIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre = null ;




		private RECLIST_VPOS_TPA_FUHRE_VP_EXT DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre = null ;




		private RECORD_VPOS_TPA_FUHRE_SONDER UP_RECORD_VPOS_TPA_FUHRE_SONDER_id_vpos_tpa_fuhre_sonder = null;




		private RECORD_WIEGEKARTE UP_RECORD_WIEGEKARTE_id_wiegekarte_abn = null;




		private RECORD_WIEGEKARTE UP_RECORD_WIEGEKARTE_id_wiegekarte_lief = null;






	
	/**
	 * References the Field ID_ADRESSE_FREMDAUFTRAG
	 * Falls keine this.get_ID_ADRESSE_FREMDAUFTRAG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_fremdauftrag() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_FREMDAUFTRAG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_fremdauftrag==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_fremdauftrag = new RECORD_ADRESSE(this.get_ID_ADRESSE_FREMDAUFTRAG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_fremdauftrag;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_LAGER_START
	 * Falls keine this.get_ID_ADRESSE_LAGER_START_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lager_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LAGER_START_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lager_start==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lager_start = new RECORD_ADRESSE(this.get_ID_ADRESSE_LAGER_START_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lager_start;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_LAGER_ZIEL
	 * Falls keine this.get_ID_ADRESSE_LAGER_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lager_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LAGER_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lager_ziel==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lager_ziel = new RECORD_ADRESSE(this.get_ID_ADRESSE_LAGER_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lager_ziel;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_SPEDITION
	 * Falls keine this.get_ID_ADRESSE_SPEDITION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_spedition() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_SPEDITION_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_spedition==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_spedition = new RECORD_ADRESSE(this.get_ID_ADRESSE_SPEDITION_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_spedition;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_START
	 * Falls keine this.get_ID_ADRESSE_START_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_START_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_start==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_start = new RECORD_ADRESSE(this.get_ID_ADRESSE_START_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_start;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_ZIEL
	 * Falls keine this.get_ID_ADRESSE_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_ziel==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_ziel = new RECORD_ADRESSE(this.get_ID_ADRESSE_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_ziel;
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
	 * References the Field ID_ARTIKEL_BEZ_EK
	 * Falls keine this.get_ID_ARTIKEL_BEZ_EK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_BEZ get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_EK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek==null)
		{
			this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek = new RECORD_ARTIKEL_BEZ(this.get_ID_ARTIKEL_BEZ_EK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek;
	}
	





	
	/**
	 * References the Field ID_ARTIKEL_BEZ_VK
	 * Falls keine this.get_ID_ARTIKEL_BEZ_VK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_BEZ get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_VK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk==null)
		{
			this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk = new RECORD_ARTIKEL_BEZ(this.get_ID_ARTIKEL_BEZ_VK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk;
	}
	





	
	/**
	 * References the Field ID_CONTAINERTYP_FP
	 * Falls keine this.get_ID_CONTAINERTYP_FP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_CONTAINERTYP get_UP_RECORD_CONTAINERTYP_id_containertyp_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_CONTAINERTYP_FP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_CONTAINERTYP_id_containertyp_fp==null)
		{
			this.UP_RECORD_CONTAINERTYP_id_containertyp_fp = new RECORD_CONTAINERTYP(this.get_ID_CONTAINERTYP_FP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_CONTAINERTYP_id_containertyp_fp;
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
	 * References the Field ID_FAHRPLAN_ZEITANGABE
	 * Falls keine this.get_ID_FAHRPLAN_ZEITANGABE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_FAHRPLAN_ZEITANGABE get_UP_RECORD_FAHRPLAN_ZEITANGABE_id_fahrplan_zeitangabe() throws myException
	{
		if (S.isEmpty(this.get_ID_FAHRPLAN_ZEITANGABE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_FAHRPLAN_ZEITANGABE_id_fahrplan_zeitangabe==null)
		{
			this.UP_RECORD_FAHRPLAN_ZEITANGABE_id_fahrplan_zeitangabe = new RECORD_FAHRPLAN_ZEITANGABE(this.get_ID_FAHRPLAN_ZEITANGABE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_FAHRPLAN_ZEITANGABE_id_fahrplan_zeitangabe;
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
	 * References the Field ID_MASCHINEN_ANH_FP
	 * Falls keine this.get_ID_MASCHINEN_ANH_FP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MASCHINEN get_UP_RECORD_MASCHINEN_id_maschinen_anh_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_ANH_FP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MASCHINEN_id_maschinen_anh_fp==null)
		{
			this.UP_RECORD_MASCHINEN_id_maschinen_anh_fp = new RECORD_MASCHINEN(this.get_ID_MASCHINEN_ANH_FP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MASCHINEN_id_maschinen_anh_fp;
	}
	





	
	/**
	 * References the Field ID_MASCHINEN_LKW_FP
	 * Falls keine this.get_ID_MASCHINEN_LKW_FP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MASCHINEN get_UP_RECORD_MASCHINEN_id_maschinen_lkw_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_LKW_FP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MASCHINEN_id_maschinen_lkw_fp==null)
		{
			this.UP_RECORD_MASCHINEN_id_maschinen_lkw_fp = new RECORD_MASCHINEN(this.get_ID_MASCHINEN_LKW_FP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MASCHINEN_id_maschinen_lkw_fp;
	}
	





	
	/**
	 * References the Field ID_TAX_EK
	 * Falls keine this.get_ID_TAX_EK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX get_UP_RECORD_TAX_id_tax_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_EK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_id_tax_ek==null)
		{
			this.UP_RECORD_TAX_id_tax_ek = new RECORD_TAX(this.get_ID_TAX_EK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_id_tax_ek;
	}
	





	
	/**
	 * References the Field ID_TAX_VK
	 * Falls keine this.get_ID_TAX_VK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX get_UP_RECORD_TAX_id_tax_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_VK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_id_tax_vk==null)
		{
			this.UP_RECORD_TAX_id_tax_vk = new RECORD_TAX(this.get_ID_TAX_VK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_id_tax_vk;
	}
	





	
	/**
	 * References the Field ID_UMA_KONTRAKT
	 * Falls keine this.get_ID_UMA_KONTRAKT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_UMA_KONTRAKT get_UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt() throws myException
	{
		if (S.isEmpty(this.get_ID_UMA_KONTRAKT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt==null)
		{
			this.UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt = new RECORD_UMA_KONTRAKT(this.get_ID_UMA_KONTRAKT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt;
	}
	





	
	/**
	 * References the Field ID_VERARBEITUNG
	 * Falls keine this.get_ID_VERARBEITUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VERARBEITUNG get_UP_RECORD_VERARBEITUNG_id_verarbeitung() throws myException
	{
		if (S.isEmpty(this.get_ID_VERARBEITUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VERARBEITUNG_id_verarbeitung==null)
		{
			this.UP_RECORD_VERARBEITUNG_id_verarbeitung = new RECORD_VERARBEITUNG(this.get_ID_VERARBEITUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VERARBEITUNG_id_verarbeitung;
	}
	





	
	/**
	 * References the Field ID_VERPACKUNGSART
	 * Falls keine this.get_ID_VERPACKUNGSART_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VERPACKUNGSART get_UP_RECORD_VERPACKUNGSART_id_verpackungsart() throws myException
	{
		if (S.isEmpty(this.get_ID_VERPACKUNGSART_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VERPACKUNGSART_id_verpackungsart==null)
		{
			this.UP_RECORD_VERPACKUNGSART_id_verpackungsart = new RECORD_VERPACKUNGSART(this.get_ID_VERPACKUNGSART_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VERPACKUNGSART_id_verpackungsart;
	}
	





	
	/**
	 * References the Field ID_VPOS_KON_EK
	 * Falls keine this.get_ID_VPOS_KON_EK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_KON get_UP_RECORD_VPOS_KON_id_vpos_kon_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_EK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_KON_id_vpos_kon_ek==null)
		{
			this.UP_RECORD_VPOS_KON_id_vpos_kon_ek = new RECORD_VPOS_KON(this.get_ID_VPOS_KON_EK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_KON_id_vpos_kon_ek;
	}
	





	
	/**
	 * References the Field ID_VPOS_KON_VK
	 * Falls keine this.get_ID_VPOS_KON_VK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_KON get_UP_RECORD_VPOS_KON_id_vpos_kon_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_VK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_KON_id_vpos_kon_vk==null)
		{
			this.UP_RECORD_VPOS_KON_id_vpos_kon_vk = new RECORD_VPOS_KON(this.get_ID_VPOS_KON_VK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_KON_id_vpos_kon_vk;
	}
	





	
	/**
	 * References the Field ID_VPOS_STD_EK
	 * Falls keine this.get_ID_VPOS_STD_EK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_STD get_UP_RECORD_VPOS_STD_id_vpos_std_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_STD_EK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_STD_id_vpos_std_ek==null)
		{
			this.UP_RECORD_VPOS_STD_id_vpos_std_ek = new RECORD_VPOS_STD(this.get_ID_VPOS_STD_EK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_STD_id_vpos_std_ek;
	}
	





	
	/**
	 * References the Field ID_VPOS_STD_VK
	 * Falls keine this.get_ID_VPOS_STD_VK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_STD get_UP_RECORD_VPOS_STD_id_vpos_std_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_STD_VK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_STD_id_vpos_std_vk==null)
		{
			this.UP_RECORD_VPOS_STD_id_vpos_std_vk = new RECORD_VPOS_STD(this.get_ID_VPOS_STD_VK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_STD_id_vpos_std_vk;
	}
	





	
	/**
	 * References the Field ID_VPOS_TPA
	 * Falls keine this.get_ID_VPOS_TPA_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA get_UP_RECORD_VPOS_TPA_id_vpos_tpa() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_id_vpos_tpa==null)
		{
			this.UP_RECORD_VPOS_TPA_id_vpos_tpa = new RECORD_VPOS_TPA(this.get_ID_VPOS_TPA_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_id_vpos_tpa;
	}
	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ABRECH 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech = new RECLIST_ABRECHBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_VPOS_TPA_FUHRE_ABRECH="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_ABRECHBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ABRECH 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_VPOS_TPA_FUHRE_ABRECH="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech = new RECLIST_ABRECHBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_vpos_tpa_fuhre_abrech;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_QUELLE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_POS get_DOWN_RECORD_LIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle = new RECLIST_ABRECHBLATT_POS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_VPOS_TPA_FUHRE_QUELLE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_ABRECHBLATT_POS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_QUELLE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_POS get_DOWN_RECORD_LIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_VPOS_TPA_FUHRE_QUELLE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle = new RECLIST_ABRECHBLATT_POS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_POS_id_vpos_tpa_fuhre_quelle;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BAM_IMPORT get_DOWN_RECORD_LIST_BAM_IMPORT_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre = new RECLIST_BAM_IMPORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_BAM_IMPORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BAM_IMPORT get_DOWN_RECORD_LIST_BAM_IMPORT_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre = new RECLIST_BAM_IMPORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BAM_IMPORT_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG get_DOWN_RECORD_LIST_BEWEGUNG_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_id_vpos_tpa_fuhre = new RECLIST_BEWEGUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_BEWEGUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG get_DOWN_RECORD_LIST_BEWEGUNG_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_id_vpos_tpa_fuhre = new RECLIST_BEWEGUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre = new RECLIST_DLP_JOIN_WARENBEWG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_DLP_JOIN_WARENBEWG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre = new RECLIST_DLP_JOIN_WARENBEWG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_DL 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl==null)
		{
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl = new RECLIST_DLP_JOIN_WARENBEWG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_VPOS_TPA_FUHRE_DL="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_DLP_JOIN_WARENBEWG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_DL 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_VPOS_TPA_FUHRE_DL="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl = new RECLIST_DLP_JOIN_WARENBEWG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_vpos_tpa_fuhre_dl;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_FAHRPLANPOS_id_vpos_tpa_fuhre = new RECLIST_FAHRPLANPOS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_FAHRPLANPOS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRPLANPOS_id_vpos_tpa_fuhre = new RECLIST_FAHRPLANPOS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre = new RECLIST_FBAM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_FBAM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre = new RECLIST_FBAM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FP_POS_AKTE get_DOWN_RECORD_LIST_FP_POS_AKTE_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FP_POS_AKTE_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_FP_POS_AKTE_id_vpos_tpa_fuhre = new RECLIST_FP_POS_AKTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FP_POS_AKTE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_FP_POS_AKTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FP_POS_AKTE_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FP_POS_AKTE get_DOWN_RECORD_LIST_FP_POS_AKTE_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FP_POS_AKTE_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FP_POS_AKTE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FP_POS_AKTE_id_vpos_tpa_fuhre = new RECLIST_FP_POS_AKTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FP_POS_AKTE_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre = new RECLIST_FUHREN_RECHNUNGEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_FUHREN_RECHNUNGEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre = new RECLIST_FUHREN_RECHNUNGEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_AUS 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_VPOS_TPA_FUHRE_AUS="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_AUS 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_VPOS_TPA_FUHRE_AUS="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_aus;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_EIN 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_VPOS_TPA_FUHRE_EIN="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_EIN 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_VPOS_TPA_FUHRE_EIN="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_vpos_tpa_fuhre_ein;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre = new RECLIST_PROFORMA_RECHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_PROFORMA_RECHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre = new RECLIST_PROFORMA_RECHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ZUGEORD 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_TPA_FUHRE_ZUGEORD="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_ZUGEORD 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_TPA_FUHRE_ZUGEORD="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ABZUG_EK get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_ABZUG_EK (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_EK WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ABZUG_EK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ABZUG_EK get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_EK WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_ABZUG_EK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ABZUG_VK get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_ABZUG_VK (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_VK WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ABZUG_VK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ABZUG_VK get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_VK WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_ABZUG_VK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_DRUCK get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_DRUCK (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_DRUCK WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_DRUCK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_DRUCK get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_DRUCK WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_DRUCK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_DRUCK_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_KOSTEN get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_KOSTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_KOSTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_KOSTEN get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_KOSTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_RGVL get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_RGVL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_RGVL WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_RGVL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_RGVL get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_RGVL WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_RGVL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_RGVL_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_VP_EXT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_VP_EXT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_VP_EXT WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_VP_EXT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_VP_EXT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_VP_EXT WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre = new RECLIST_VPOS_TPA_FUHRE_VP_EXT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_VP_EXT_id_vpos_tpa_fuhre;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_vpos_tpa_fuhre(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_vpos_tpa_fuhre;
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
	 * References the Field ID_WIEGEKARTE_ABN
	 * Falls keine this.get_ID_WIEGEKARTE_ABN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WIEGEKARTE get_UP_RECORD_WIEGEKARTE_id_wiegekarte_abn() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_ABN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WIEGEKARTE_id_wiegekarte_abn==null)
		{
			this.UP_RECORD_WIEGEKARTE_id_wiegekarte_abn = new RECORD_WIEGEKARTE(this.get_ID_WIEGEKARTE_ABN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WIEGEKARTE_id_wiegekarte_abn;
	}
	





	
	/**
	 * References the Field ID_WIEGEKARTE_LIEF
	 * Falls keine this.get_ID_WIEGEKARTE_LIEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WIEGEKARTE get_UP_RECORD_WIEGEKARTE_id_wiegekarte_lief() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_LIEF_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WIEGEKARTE_id_wiegekarte_lief==null)
		{
			this.UP_RECORD_WIEGEKARTE_id_wiegekarte_lief = new RECORD_WIEGEKARTE(this.get_ID_WIEGEKARTE_LIEF_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WIEGEKARTE_id_wiegekarte_lief;
	}
	

	public static String FIELD__ABGESCHLOSSEN = "ABGESCHLOSSEN";
	public static String FIELD__ABLADEMENGE_RECHNUNG = "ABLADEMENGE_RECHNUNG";
	public static String FIELD__ABLADEN_BRUTTO = "ABLADEN_BRUTTO";
	public static String FIELD__ABLADEN_TARA = "ABLADEN_TARA";
	public static String FIELD__ABZUG_ABLADEMENGE_ABN = "ABZUG_ABLADEMENGE_ABN";
	public static String FIELD__ABZUG_LADEMENGE_LIEF = "ABZUG_LADEMENGE_LIEF";
	public static String FIELD__ALTE_LIEFERSCHEIN_NR = "ALTE_LIEFERSCHEIN_NR";
	public static String FIELD__ALT_WIRD_NICHT_GEBUCHT = "ALT_WIRD_NICHT_GEBUCHT";
	public static String FIELD__ANHAENGERKENNZEICHEN = "ANHAENGERKENNZEICHEN";
	public static String FIELD__ANR1_EK = "ANR1_EK";
	public static String FIELD__ANR1_VK = "ANR1_VK";
	public static String FIELD__ANR2_EK = "ANR2_EK";
	public static String FIELD__ANR2_VK = "ANR2_VK";
	public static String FIELD__ANRUFDATUM_FP = "ANRUFDATUM_FP";
	public static String FIELD__ANRUFER_FP = "ANRUFER_FP";
	public static String FIELD__ANTEIL_ABLADEMENGE_ABN = "ANTEIL_ABLADEMENGE_ABN";
	public static String FIELD__ANTEIL_ABLADEMENGE_LIEF = "ANTEIL_ABLADEMENGE_LIEF";
	public static String FIELD__ANTEIL_LADEMENGE_ABN = "ANTEIL_LADEMENGE_ABN";
	public static String FIELD__ANTEIL_LADEMENGE_LIEF = "ANTEIL_LADEMENGE_LIEF";
	public static String FIELD__ANTEIL_PLANMENGE_ABN = "ANTEIL_PLANMENGE_ABN";
	public static String FIELD__ANTEIL_PLANMENGE_LIEF = "ANTEIL_PLANMENGE_LIEF";
	public static String FIELD__ANZAHL_CONTAINER_FP = "ANZAHL_CONTAINER_FP";
	public static String FIELD__ARTBEZ1_EK = "ARTBEZ1_EK";
	public static String FIELD__ARTBEZ1_VK = "ARTBEZ1_VK";
	public static String FIELD__ARTBEZ2_EK = "ARTBEZ2_EK";
	public static String FIELD__ARTBEZ2_VK = "ARTBEZ2_VK";
	public static String FIELD__AUFLADEN_BRUTTO = "AUFLADEN_BRUTTO";
	public static String FIELD__AUFLADEN_TARA = "AUFLADEN_TARA";
	public static String FIELD__AVV_AUSSTELLUNG_DATUM = "AVV_AUSSTELLUNG_DATUM";
	public static String FIELD__A_HAUSNUMMER = "A_HAUSNUMMER";
	public static String FIELD__A_LAENDERCODE = "A_LAENDERCODE";
	public static String FIELD__A_NAME1 = "A_NAME1";
	public static String FIELD__A_NAME2 = "A_NAME2";
	public static String FIELD__A_NAME3 = "A_NAME3";
	public static String FIELD__A_ORT = "A_ORT";
	public static String FIELD__A_ORTZUSATZ = "A_ORTZUSATZ";
	public static String FIELD__A_PLZ = "A_PLZ";
	public static String FIELD__A_STRASSE = "A_STRASSE";
	public static String FIELD__BASEL_CODE = "BASEL_CODE";
	public static String FIELD__BASEL_NOTIZ = "BASEL_NOTIZ";
	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__BEMERKUNG_FUER_KUNDE = "BEMERKUNG_FUER_KUNDE";
	public static String FIELD__BEMERKUNG_SACHBEARBEITER = "BEMERKUNG_SACHBEARBEITER";
	public static String FIELD__BEMERKUNG_START_FP = "BEMERKUNG_START_FP";
	public static String FIELD__BEMERKUNG_ZIEL_FP = "BEMERKUNG_ZIEL_FP";
	public static String FIELD__BESTELLNUMMER_EK = "BESTELLNUMMER_EK";
	public static String FIELD__BESTELLNUMMER_VK = "BESTELLNUMMER_VK";
	public static String FIELD__BUCHUNGSNR_FUHRE = "BUCHUNGSNR_FUHRE";
	public static String FIELD__DATUM_ABHOLUNG = "DATUM_ABHOLUNG";
	public static String FIELD__DATUM_ABHOLUNG_ENDE = "DATUM_ABHOLUNG_ENDE";
	public static String FIELD__DATUM_ABLADEN = "DATUM_ABLADEN";
	public static String FIELD__DATUM_ANLIEFERUNG = "DATUM_ANLIEFERUNG";
	public static String FIELD__DATUM_ANLIEFERUNG_ENDE = "DATUM_ANLIEFERUNG_ENDE";
	public static String FIELD__DATUM_AUFLADEN = "DATUM_AUFLADEN";
	public static String FIELD__DAT_FAHRPLAN_FP = "DAT_FAHRPLAN_FP";
	public static String FIELD__DAT_VORGEMERKT_ENDE_FP = "DAT_VORGEMERKT_ENDE_FP";
	public static String FIELD__DAT_VORGEMERKT_FP = "DAT_VORGEMERKT_FP";
	public static String FIELD__DELETED = "DELETED";
	public static String FIELD__DEL_DATE = "DEL_DATE";
	public static String FIELD__DEL_GRUND = "DEL_GRUND";
	public static String FIELD__DEL_KUERZEL = "DEL_KUERZEL";
	public static String FIELD__EAN_CODE_FP = "EAN_CODE_FP";
	public static String FIELD__EINHEIT_MENGEN = "EINHEIT_MENGEN";
	public static String FIELD__EINZELPREIS_EK = "EINZELPREIS_EK";
	public static String FIELD__EINZELPREIS_VK = "EINZELPREIS_VK";
	public static String FIELD__EK_KONTRAKTNR_ZUSATZ = "EK_KONTRAKTNR_ZUSATZ";
	public static String FIELD__EK_VK_SORTE_LOCK = "EK_VK_SORTE_LOCK";
	public static String FIELD__EK_VK_ZUORD_ZWANG = "EK_VK_ZUORD_ZWANG";
	public static String FIELD__EPREIS_RESULT_NETTO_MGE_EK = "EPREIS_RESULT_NETTO_MGE_EK";
	public static String FIELD__EPREIS_RESULT_NETTO_MGE_VK = "EPREIS_RESULT_NETTO_MGE_VK";
	public static String FIELD__ERFASSER_FP = "ERFASSER_FP";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EUCODE = "EUCODE";
	public static String FIELD__EUNOTIZ = "EUNOTIZ";
	public static String FIELD__EU_BLATT_MENGE = "EU_BLATT_MENGE";
	public static String FIELD__EU_BLATT_VOLUMEN = "EU_BLATT_VOLUMEN";
	public static String FIELD__EU_STEUER_VERMERK_EK = "EU_STEUER_VERMERK_EK";
	public static String FIELD__EU_STEUER_VERMERK_VK = "EU_STEUER_VERMERK_VK";
	public static String FIELD__FAHRER_FP = "FAHRER_FP";
	public static String FIELD__FAHRPLANGRUPPE_FP = "FAHRPLANGRUPPE_FP";
	public static String FIELD__FAHRT_ANFANG_FP = "FAHRT_ANFANG_FP";
	public static String FIELD__FAHRT_ENDE_FP = "FAHRT_ENDE_FP";
	public static String FIELD__FAX_ABNEHMER = "FAX_ABNEHMER";
	public static String FIELD__FAX_LIEFERANT = "FAX_LIEFERANT";
	public static String FIELD__FUHRENGRUPPE = "FUHRENGRUPPE";
	public static String FIELD__FUHRE_AUS_FAHRPLAN = "FUHRE_AUS_FAHRPLAN";
	public static String FIELD__FUHRE_IST_UMGELEITET = "FUHRE_IST_UMGELEITET";
	public static String FIELD__FUHRE_KOMPLETT = "FUHRE_KOMPLETT";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GELANGENSBESTAETIGUNG_ERHALTEN = "GELANGENSBESTAETIGUNG_ERHALTEN";
	public static String FIELD__ID_ADRESSE_FREMDAUFTRAG = "ID_ADRESSE_FREMDAUFTRAG";
	public static String FIELD__ID_ADRESSE_LAGER_START = "ID_ADRESSE_LAGER_START";
	public static String FIELD__ID_ADRESSE_LAGER_ZIEL = "ID_ADRESSE_LAGER_ZIEL";
	public static String FIELD__ID_ADRESSE_SPEDITION = "ID_ADRESSE_SPEDITION";
	public static String FIELD__ID_ADRESSE_START = "ID_ADRESSE_START";
	public static String FIELD__ID_ADRESSE_ZIEL = "ID_ADRESSE_ZIEL";
	public static String FIELD__ID_ARTIKEL = "ID_ARTIKEL";
	public static String FIELD__ID_ARTIKEL_BEZ_EK = "ID_ARTIKEL_BEZ_EK";
	public static String FIELD__ID_ARTIKEL_BEZ_VK = "ID_ARTIKEL_BEZ_VK";
	public static String FIELD__ID_CONTAINERTYP_FP = "ID_CONTAINERTYP_FP";
	public static String FIELD__ID_EAK_CODE = "ID_EAK_CODE";
	public static String FIELD__ID_FAHRPLAN_ZEITANGABE = "ID_FAHRPLAN_ZEITANGABE";
	public static String FIELD__ID_HANDELSDEF = "ID_HANDELSDEF";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_MASCHINEN_ANH_FP = "ID_MASCHINEN_ANH_FP";
	public static String FIELD__ID_MASCHINEN_LKW_FP = "ID_MASCHINEN_LKW_FP";
	public static String FIELD__ID_TAX_EK = "ID_TAX_EK";
	public static String FIELD__ID_TAX_VK = "ID_TAX_VK";
	public static String FIELD__ID_UMA_KONTRAKT = "ID_UMA_KONTRAKT";
	public static String FIELD__ID_VERARBEITUNG = "ID_VERARBEITUNG";
	public static String FIELD__ID_VERPACKUNGSART = "ID_VERPACKUNGSART";
	public static String FIELD__ID_VPOS_KON_EK = "ID_VPOS_KON_EK";
	public static String FIELD__ID_VPOS_KON_VK = "ID_VPOS_KON_VK";
	public static String FIELD__ID_VPOS_STD_EK = "ID_VPOS_STD_EK";
	public static String FIELD__ID_VPOS_STD_VK = "ID_VPOS_STD_VK";
	public static String FIELD__ID_VPOS_TPA = "ID_VPOS_TPA";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_FUHRE_SONDER = "ID_VPOS_TPA_FUHRE_SONDER";
	public static String FIELD__ID_WIEGEKARTE_ABN = "ID_WIEGEKARTE_ABN";
	public static String FIELD__ID_WIEGEKARTE_LIEF = "ID_WIEGEKARTE_LIEF";
	public static String FIELD__INTRASTAT_MELD_IN = "INTRASTAT_MELD_IN";
	public static String FIELD__INTRASTAT_MELD_OUT = "INTRASTAT_MELD_OUT";
	public static String FIELD__IST_GEPLANT_FP = "IST_GEPLANT_FP";
	public static String FIELD__IST_STORNIERT = "IST_STORNIERT";
	public static String FIELD__KEIN_KONTRAKT_NOETIG = "KEIN_KONTRAKT_NOETIG";
	public static String FIELD__KENNER_ALTE_SAETZE_FP = "KENNER_ALTE_SAETZE_FP";
	public static String FIELD__LADEMENGE_GUTSCHRIFT = "LADEMENGE_GUTSCHRIFT";
	public static String FIELD__LAENDERCODE_TRANSIT1 = "LAENDERCODE_TRANSIT1";
	public static String FIELD__LAENDERCODE_TRANSIT2 = "LAENDERCODE_TRANSIT2";
	public static String FIELD__LAENDERCODE_TRANSIT3 = "LAENDERCODE_TRANSIT3";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERBED_ALTERNATIV_EK = "LIEFERBED_ALTERNATIV_EK";
	public static String FIELD__LIEFERBED_ALTERNATIV_VK = "LIEFERBED_ALTERNATIV_VK";
	public static String FIELD__LIEFERINFO_TPA = "LIEFERINFO_TPA";
	public static String FIELD__L_HAUSNUMMER = "L_HAUSNUMMER";
	public static String FIELD__L_LAENDERCODE = "L_LAENDERCODE";
	public static String FIELD__L_NAME1 = "L_NAME1";
	public static String FIELD__L_NAME2 = "L_NAME2";
	public static String FIELD__L_NAME3 = "L_NAME3";
	public static String FIELD__L_ORT = "L_ORT";
	public static String FIELD__L_ORTZUSATZ = "L_ORTZUSATZ";
	public static String FIELD__L_PLZ = "L_PLZ";
	public static String FIELD__L_STRASSE = "L_STRASSE";
	public static String FIELD__MANUELL_PREIS_EK = "MANUELL_PREIS_EK";
	public static String FIELD__MANUELL_PREIS_VK = "MANUELL_PREIS_VK";
	public static String FIELD__MENGE_ABLADEN_KO = "MENGE_ABLADEN_KO";
	public static String FIELD__MENGE_AUFLADEN_KO = "MENGE_AUFLADEN_KO";
	public static String FIELD__MENGE_VORGABE_KO = "MENGE_VORGABE_KO";
	public static String FIELD__NATIONALER_ABFALL_CODE = "NATIONALER_ABFALL_CODE";
	public static String FIELD__NOTIFIKATION_NR = "NOTIFIKATION_NR";
	public static String FIELD__NOTIFIKATION_NR_EK = "NOTIFIKATION_NR_EK";
	public static String FIELD__OEFFNUNGSZEITEN_ABN = "OEFFNUNGSZEITEN_ABN";
	public static String FIELD__OEFFNUNGSZEITEN_LIEF = "OEFFNUNGSZEITEN_LIEF";
	public static String FIELD__OHNE_ABRECHNUNG = "OHNE_ABRECHNUNG";
	public static String FIELD__OHNE_AVV_VERTRAG_CHECK = "OHNE_AVV_VERTRAG_CHECK";
	public static String FIELD__ORDNUNGSNUMMER_CMR = "ORDNUNGSNUMMER_CMR";
	public static String FIELD__POSTENNUMMER_EK = "POSTENNUMMER_EK";
	public static String FIELD__POSTENNUMMER_VK = "POSTENNUMMER_VK";
	public static String FIELD__PRINT_EU_AMTSBLATT = "PRINT_EU_AMTSBLATT";
	public static String FIELD__PRUEFUNG_ABLADEMENGE = "PRUEFUNG_ABLADEMENGE";
	public static String FIELD__PRUEFUNG_ABLADEMENGE_AM = "PRUEFUNG_ABLADEMENGE_AM";
	public static String FIELD__PRUEFUNG_ABLADEMENGE_VON = "PRUEFUNG_ABLADEMENGE_VON";
	public static String FIELD__PRUEFUNG_EK_PREIS = "PRUEFUNG_EK_PREIS";
	public static String FIELD__PRUEFUNG_EK_PREIS_AM = "PRUEFUNG_EK_PREIS_AM";
	public static String FIELD__PRUEFUNG_EK_PREIS_VON = "PRUEFUNG_EK_PREIS_VON";
	public static String FIELD__PRUEFUNG_LADEMENGE = "PRUEFUNG_LADEMENGE";
	public static String FIELD__PRUEFUNG_LADEMENGE_AM = "PRUEFUNG_LADEMENGE_AM";
	public static String FIELD__PRUEFUNG_LADEMENGE_VON = "PRUEFUNG_LADEMENGE_VON";
	public static String FIELD__PRUEFUNG_VK_PREIS = "PRUEFUNG_VK_PREIS";
	public static String FIELD__PRUEFUNG_VK_PREIS_AM = "PRUEFUNG_VK_PREIS_AM";
	public static String FIELD__PRUEFUNG_VK_PREIS_VON = "PRUEFUNG_VK_PREIS_VON";
	public static String FIELD__ROUTING_DISTANCE_KM = "ROUTING_DISTANCE_KM";
	public static String FIELD__ROUTING_TIME_MINUTES = "ROUTING_TIME_MINUTES";
	public static String FIELD__SCHLIESSE_FUHRE = "SCHLIESSE_FUHRE";
	public static String FIELD__SCHLIESSE_FUHRE_AM = "SCHLIESSE_FUHRE_AM";
	public static String FIELD__SCHLIESSE_FUHRE_VON = "SCHLIESSE_FUHRE_VON";
	public static String FIELD__SORTIERUNG_FP = "SORTIERUNG_FP";
	public static String FIELD__SPEICHERN_TROTZ_INKONSISTENZ = "SPEICHERN_TROTZ_INKONSISTENZ";
	public static String FIELD__STATUS_BUCHUNG = "STATUS_BUCHUNG";
	public static String FIELD__STEUERSATZ_EK = "STEUERSATZ_EK";
	public static String FIELD__STEUERSATZ_VK = "STEUERSATZ_VK";
	public static String FIELD__STORNO_GRUND = "STORNO_GRUND";
	public static String FIELD__STORNO_KUERZEL = "STORNO_KUERZEL";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TAETIGKEIT_FP = "TAETIGKEIT_FP";
	public static String FIELD__TEL_ABNEHMER = "TEL_ABNEHMER";
	public static String FIELD__TEL_LIEFERANT = "TEL_LIEFERANT";
	public static String FIELD__TP_VERANTWORTUNG = "TP_VERANTWORTUNG";
	public static String FIELD__TRANSIT_EK = "TRANSIT_EK";
	public static String FIELD__TRANSIT_VK = "TRANSIT_VK";
	public static String FIELD__TRANSPORTKENNZEICHEN = "TRANSPORTKENNZEICHEN";
	public static String FIELD__TRANSPORTMITTEL = "TRANSPORTMITTEL";
	public static String FIELD__TYP_STRECKE_LAGER_MIXED = "TYP_STRECKE_LAGER_MIXED";
	public static String FIELD__VK_KONTRAKTNR_ZUSATZ = "VK_KONTRAKTNR_ZUSATZ";
	public static String FIELD__WIEGEKARTENKENNER_ABLADEN = "WIEGEKARTENKENNER_ABLADEN";
	public static String FIELD__WIEGEKARTENKENNER_LADEN = "WIEGEKARTENKENNER_LADEN";
	public static String FIELD__ZEITANGABE = "ZEITANGABE";
	public static String FIELD__ZEITSTEMPEL = "ZEITSTEMPEL";
	public static String FIELD__ZOLLTARIFNR = "ZOLLTARIFNR";


	public String get_ABGESCHLOSSEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN");	
	}

	public String get_ABGESCHLOSSEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", calNewValueFormated);
	}
		public boolean is_ABGESCHLOSSEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABGESCHLOSSEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
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
		public String get_ABLADEN_BRUTTO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABLADEN_BRUTTO");
	}

	public String get_ABLADEN_BRUTTO_cF() throws myException
	{
		return this.get_FormatedValue("ABLADEN_BRUTTO");	
	}

	public String get_ABLADEN_BRUTTO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABLADEN_BRUTTO");
	}

	public String get_ABLADEN_BRUTTO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABLADEN_BRUTTO",cNotNullValue);
	}

	public String get_ABLADEN_BRUTTO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABLADEN_BRUTTO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABLADEN_BRUTTO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABLADEN_BRUTTO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_BRUTTO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEN_BRUTTO", calNewValueFormated);
	}
		public Double get_ABLADEN_BRUTTO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ABLADEN_BRUTTO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ABLADEN_BRUTTO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ABLADEN_BRUTTO").getDoubleValue();
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
	public String get_ABLADEN_BRUTTO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABLADEN_BRUTTO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ABLADEN_BRUTTO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABLADEN_BRUTTO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ABLADEN_BRUTTO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ABLADEN_BRUTTO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ABLADEN_BRUTTO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ABLADEN_BRUTTO").getBigDecimalValue();
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
	
	
	public String get_ABLADEN_TARA_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABLADEN_TARA");
	}

	public String get_ABLADEN_TARA_cF() throws myException
	{
		return this.get_FormatedValue("ABLADEN_TARA");	
	}

	public String get_ABLADEN_TARA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABLADEN_TARA");
	}

	public String get_ABLADEN_TARA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABLADEN_TARA",cNotNullValue);
	}

	public String get_ABLADEN_TARA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABLADEN_TARA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABLADEN_TARA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABLADEN_TARA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABLADEN_TARA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABLADEN_TARA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEN_TARA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEN_TARA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADEN_TARA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADEN_TARA", calNewValueFormated);
	}
		public Double get_ABLADEN_TARA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ABLADEN_TARA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ABLADEN_TARA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ABLADEN_TARA").getDoubleValue();
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
	public String get_ABLADEN_TARA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABLADEN_TARA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ABLADEN_TARA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABLADEN_TARA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ABLADEN_TARA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ABLADEN_TARA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ABLADEN_TARA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ABLADEN_TARA").getBigDecimalValue();
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
	
	
	public String get_ABZUG_ABLADEMENGE_ABN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABZUG_ABLADEMENGE_ABN");
	}

	public String get_ABZUG_ABLADEMENGE_ABN_cF() throws myException
	{
		return this.get_FormatedValue("ABZUG_ABLADEMENGE_ABN");	
	}

	public String get_ABZUG_ABLADEMENGE_ABN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABZUG_ABLADEMENGE_ABN");
	}

	public String get_ABZUG_ABLADEMENGE_ABN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABZUG_ABLADEMENGE_ABN",cNotNullValue);
	}

	public String get_ABZUG_ABLADEMENGE_ABN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABZUG_ABLADEMENGE_ABN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_ABLADEMENGE_ABN", calNewValueFormated);
	}
		public Double get_ABZUG_ABLADEMENGE_ABN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ABZUG_ABLADEMENGE_ABN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ABZUG_ABLADEMENGE_ABN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ABZUG_ABLADEMENGE_ABN").getDoubleValue();
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
	public String get_ABZUG_ABLADEMENGE_ABN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABZUG_ABLADEMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ABZUG_ABLADEMENGE_ABN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABZUG_ABLADEMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ABZUG_ABLADEMENGE_ABN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ABZUG_ABLADEMENGE_ABN").getBigDecimalValue();
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
	
	
	public String get_ABZUG_LADEMENGE_LIEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABZUG_LADEMENGE_LIEF");
	}

	public String get_ABZUG_LADEMENGE_LIEF_cF() throws myException
	{
		return this.get_FormatedValue("ABZUG_LADEMENGE_LIEF");	
	}

	public String get_ABZUG_LADEMENGE_LIEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABZUG_LADEMENGE_LIEF");
	}

	public String get_ABZUG_LADEMENGE_LIEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABZUG_LADEMENGE_LIEF",cNotNullValue);
	}

	public String get_ABZUG_LADEMENGE_LIEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABZUG_LADEMENGE_LIEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABZUG_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABZUG_LADEMENGE_LIEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABZUG_LADEMENGE_LIEF", calNewValueFormated);
	}
		public Double get_ABZUG_LADEMENGE_LIEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ABZUG_LADEMENGE_LIEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ABZUG_LADEMENGE_LIEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ABZUG_LADEMENGE_LIEF").getDoubleValue();
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
	public String get_ABZUG_LADEMENGE_LIEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABZUG_LADEMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ABZUG_LADEMENGE_LIEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ABZUG_LADEMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ABZUG_LADEMENGE_LIEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ABZUG_LADEMENGE_LIEF").getBigDecimalValue();
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
	
	
	public String get_ALTE_LIEFERSCHEIN_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALTE_LIEFERSCHEIN_NR");
	}

	public String get_ALTE_LIEFERSCHEIN_NR_cF() throws myException
	{
		return this.get_FormatedValue("ALTE_LIEFERSCHEIN_NR");	
	}

	public String get_ALTE_LIEFERSCHEIN_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALTE_LIEFERSCHEIN_NR");
	}

	public String get_ALTE_LIEFERSCHEIN_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALTE_LIEFERSCHEIN_NR",cNotNullValue);
	}

	public String get_ALTE_LIEFERSCHEIN_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALTE_LIEFERSCHEIN_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALTE_LIEFERSCHEIN_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALTE_LIEFERSCHEIN_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALTE_LIEFERSCHEIN_NR", calNewValueFormated);
	}
		public String get_ALT_WIRD_NICHT_GEBUCHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ALT_WIRD_NICHT_GEBUCHT");
	}

	public String get_ALT_WIRD_NICHT_GEBUCHT_cF() throws myException
	{
		return this.get_FormatedValue("ALT_WIRD_NICHT_GEBUCHT");	
	}

	public String get_ALT_WIRD_NICHT_GEBUCHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ALT_WIRD_NICHT_GEBUCHT");
	}

	public String get_ALT_WIRD_NICHT_GEBUCHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ALT_WIRD_NICHT_GEBUCHT",cNotNullValue);
	}

	public String get_ALT_WIRD_NICHT_GEBUCHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ALT_WIRD_NICHT_GEBUCHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ALT_WIRD_NICHT_GEBUCHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ALT_WIRD_NICHT_GEBUCHT", calNewValueFormated);
	}
		public boolean is_ALT_WIRD_NICHT_GEBUCHT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALT_WIRD_NICHT_GEBUCHT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ALT_WIRD_NICHT_GEBUCHT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ALT_WIRD_NICHT_GEBUCHT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ANHAENGERKENNZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANHAENGERKENNZEICHEN");
	}

	public String get_ANHAENGERKENNZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("ANHAENGERKENNZEICHEN");	
	}

	public String get_ANHAENGERKENNZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANHAENGERKENNZEICHEN");
	}

	public String get_ANHAENGERKENNZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANHAENGERKENNZEICHEN",cNotNullValue);
	}

	public String get_ANHAENGERKENNZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANHAENGERKENNZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", calNewValueFormated);
	}
		public String get_ANR1_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1_EK");
	}

	public String get_ANR1_EK_cF() throws myException
	{
		return this.get_FormatedValue("ANR1_EK");	
	}

	public String get_ANR1_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1_EK");
	}

	public String get_ANR1_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1_EK",cNotNullValue);
	}

	public String get_ANR1_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_EK", calNewValueFormated);
	}
		public String get_ANR1_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1_VK");
	}

	public String get_ANR1_VK_cF() throws myException
	{
		return this.get_FormatedValue("ANR1_VK");	
	}

	public String get_ANR1_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1_VK");
	}

	public String get_ANR1_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1_VK",cNotNullValue);
	}

	public String get_ANR1_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1_VK", calNewValueFormated);
	}
		public String get_ANR2_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR2_EK");
	}

	public String get_ANR2_EK_cF() throws myException
	{
		return this.get_FormatedValue("ANR2_EK");	
	}

	public String get_ANR2_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR2_EK");
	}

	public String get_ANR2_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR2_EK",cNotNullValue);
	}

	public String get_ANR2_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR2_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR2_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR2_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR2_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR2_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2_EK", calNewValueFormated);
	}
		public String get_ANR2_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR2_VK");
	}

	public String get_ANR2_VK_cF() throws myException
	{
		return this.get_FormatedValue("ANR2_VK");	
	}

	public String get_ANR2_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR2_VK");
	}

	public String get_ANR2_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR2_VK",cNotNullValue);
	}

	public String get_ANR2_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR2_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR2_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR2_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR2_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR2_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2_VK", calNewValueFormated);
	}
		public String get_ANRUFDATUM_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANRUFDATUM_FP");
	}

	public String get_ANRUFDATUM_FP_cF() throws myException
	{
		return this.get_FormatedValue("ANRUFDATUM_FP");	
	}

	public String get_ANRUFDATUM_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANRUFDATUM_FP");
	}

	public String get_ANRUFDATUM_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANRUFDATUM_FP",cNotNullValue);
	}

	public String get_ANRUFDATUM_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANRUFDATUM_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANRUFDATUM_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANRUFDATUM_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANRUFDATUM_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", calNewValueFormated);
	}
		public String get_ANRUFER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANRUFER_FP");
	}

	public String get_ANRUFER_FP_cF() throws myException
	{
		return this.get_FormatedValue("ANRUFER_FP");	
	}

	public String get_ANRUFER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANRUFER_FP");
	}

	public String get_ANRUFER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANRUFER_FP",cNotNullValue);
	}

	public String get_ANRUFER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANRUFER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANRUFER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANRUFER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANRUFER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANRUFER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFER_FP", calNewValueFormated);
	}
		public String get_ANTEIL_ABLADEMENGE_ABN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_ABLADEMENGE_ABN");
	}

	public String get_ANTEIL_ABLADEMENGE_ABN_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_ABLADEMENGE_ABN");	
	}

	public String get_ANTEIL_ABLADEMENGE_ABN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_ABLADEMENGE_ABN");
	}

	public String get_ANTEIL_ABLADEMENGE_ABN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_ABLADEMENGE_ABN",cNotNullValue);
	}

	public String get_ANTEIL_ABLADEMENGE_ABN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_ABLADEMENGE_ABN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_ABN", calNewValueFormated);
	}
		public Double get_ANTEIL_ABLADEMENGE_ABN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_ABLADEMENGE_ABN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_ABLADEMENGE_ABN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_ABLADEMENGE_ABN").getDoubleValue();
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
	public String get_ANTEIL_ABLADEMENGE_ABN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_ABLADEMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_ABLADEMENGE_ABN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_ABLADEMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_ABLADEMENGE_ABN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_ABLADEMENGE_ABN").getBigDecimalValue();
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
	
	
	public String get_ANTEIL_ABLADEMENGE_LIEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_ABLADEMENGE_LIEF");
	}

	public String get_ANTEIL_ABLADEMENGE_LIEF_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_ABLADEMENGE_LIEF");	
	}

	public String get_ANTEIL_ABLADEMENGE_LIEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_ABLADEMENGE_LIEF");
	}

	public String get_ANTEIL_ABLADEMENGE_LIEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_ABLADEMENGE_LIEF",cNotNullValue);
	}

	public String get_ANTEIL_ABLADEMENGE_LIEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_ABLADEMENGE_LIEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_ABLADEMENGE_LIEF", calNewValueFormated);
	}
		public Double get_ANTEIL_ABLADEMENGE_LIEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_ABLADEMENGE_LIEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_ABLADEMENGE_LIEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_ABLADEMENGE_LIEF").getDoubleValue();
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
	public String get_ANTEIL_ABLADEMENGE_LIEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_ABLADEMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_ABLADEMENGE_LIEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_ABLADEMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_ABLADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_ABLADEMENGE_LIEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_ABLADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_ABLADEMENGE_LIEF").getBigDecimalValue();
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
	
	
	public String get_ANTEIL_LADEMENGE_ABN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_LADEMENGE_ABN");
	}

	public String get_ANTEIL_LADEMENGE_ABN_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_LADEMENGE_ABN");	
	}

	public String get_ANTEIL_LADEMENGE_ABN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_LADEMENGE_ABN");
	}

	public String get_ANTEIL_LADEMENGE_ABN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_LADEMENGE_ABN",cNotNullValue);
	}

	public String get_ANTEIL_LADEMENGE_ABN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_LADEMENGE_ABN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_LADEMENGE_ABN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_ABN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_ABN", calNewValueFormated);
	}
		public Double get_ANTEIL_LADEMENGE_ABN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_LADEMENGE_ABN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_LADEMENGE_ABN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_LADEMENGE_ABN").getDoubleValue();
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
	public String get_ANTEIL_LADEMENGE_ABN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_LADEMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_LADEMENGE_ABN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_LADEMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_LADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_LADEMENGE_ABN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_LADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_LADEMENGE_ABN").getBigDecimalValue();
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
	
	
	public String get_ANTEIL_LADEMENGE_LIEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_LADEMENGE_LIEF");
	}

	public String get_ANTEIL_LADEMENGE_LIEF_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_LADEMENGE_LIEF");	
	}

	public String get_ANTEIL_LADEMENGE_LIEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_LADEMENGE_LIEF");
	}

	public String get_ANTEIL_LADEMENGE_LIEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_LADEMENGE_LIEF",cNotNullValue);
	}

	public String get_ANTEIL_LADEMENGE_LIEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_LADEMENGE_LIEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_LADEMENGE_LIEF", calNewValueFormated);
	}
		public Double get_ANTEIL_LADEMENGE_LIEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_LADEMENGE_LIEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_LADEMENGE_LIEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_LADEMENGE_LIEF").getDoubleValue();
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
	public String get_ANTEIL_LADEMENGE_LIEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_LADEMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_LADEMENGE_LIEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_LADEMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_LADEMENGE_LIEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_LADEMENGE_LIEF").getBigDecimalValue();
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
	
	
	public String get_ANTEIL_PLANMENGE_ABN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_PLANMENGE_ABN");
	}

	public String get_ANTEIL_PLANMENGE_ABN_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_PLANMENGE_ABN");	
	}

	public String get_ANTEIL_PLANMENGE_ABN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_PLANMENGE_ABN");
	}

	public String get_ANTEIL_PLANMENGE_ABN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_PLANMENGE_ABN",cNotNullValue);
	}

	public String get_ANTEIL_PLANMENGE_ABN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_PLANMENGE_ABN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_PLANMENGE_ABN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_ABN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_ABN", calNewValueFormated);
	}
		public Double get_ANTEIL_PLANMENGE_ABN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_PLANMENGE_ABN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_PLANMENGE_ABN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_PLANMENGE_ABN").getDoubleValue();
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
	public String get_ANTEIL_PLANMENGE_ABN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_PLANMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_PLANMENGE_ABN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_PLANMENGE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_PLANMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_PLANMENGE_ABN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_PLANMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_PLANMENGE_ABN").getBigDecimalValue();
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
	
	
	public String get_ANTEIL_PLANMENGE_LIEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_PLANMENGE_LIEF");
	}

	public String get_ANTEIL_PLANMENGE_LIEF_cF() throws myException
	{
		return this.get_FormatedValue("ANTEIL_PLANMENGE_LIEF");	
	}

	public String get_ANTEIL_PLANMENGE_LIEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANTEIL_PLANMENGE_LIEF");
	}

	public String get_ANTEIL_PLANMENGE_LIEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANTEIL_PLANMENGE_LIEF",cNotNullValue);
	}

	public String get_ANTEIL_PLANMENGE_LIEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANTEIL_PLANMENGE_LIEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANTEIL_PLANMENGE_LIEF", calNewValueFormated);
	}
		public Double get_ANTEIL_PLANMENGE_LIEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANTEIL_PLANMENGE_LIEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANTEIL_PLANMENGE_LIEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANTEIL_PLANMENGE_LIEF").getDoubleValue();
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
	public String get_ANTEIL_PLANMENGE_LIEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_PLANMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANTEIL_PLANMENGE_LIEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANTEIL_PLANMENGE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANTEIL_PLANMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_PLANMENGE_LIEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANTEIL_PLANMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANTEIL_PLANMENGE_LIEF").getBigDecimalValue();
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
	
	
	public String get_ANZAHL_CONTAINER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_CONTAINER_FP");
	}

	public String get_ANZAHL_CONTAINER_FP_cF() throws myException
	{
		return this.get_FormatedValue("ANZAHL_CONTAINER_FP");	
	}

	public String get_ANZAHL_CONTAINER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZAHL_CONTAINER_FP");
	}

	public String get_ANZAHL_CONTAINER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_CONTAINER_FP",cNotNullValue);
	}

	public String get_ANZAHL_CONTAINER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZAHL_CONTAINER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZAHL_CONTAINER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZAHL_CONTAINER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", calNewValueFormated);
	}
		public Long get_ANZAHL_CONTAINER_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ANZAHL_CONTAINER_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ANZAHL_CONTAINER_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZAHL_CONTAINER_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZAHL_CONTAINER_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZAHL_CONTAINER_FP").getDoubleValue();
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
	public String get_ANZAHL_CONTAINER_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_CONTAINER_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANZAHL_CONTAINER_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_CONTAINER_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANZAHL_CONTAINER_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_CONTAINER_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZAHL_CONTAINER_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_CONTAINER_FP").getBigDecimalValue();
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
	
	
	public String get_ARTBEZ1_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1_EK");
	}

	public String get_ARTBEZ1_EK_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ1_EK");	
	}

	public String get_ARTBEZ1_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ1_EK");
	}

	public String get_ARTBEZ1_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1_EK",cNotNullValue);
	}

	public String get_ARTBEZ1_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ1_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ1_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ1_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1_EK", calNewValueFormated);
	}
		public String get_ARTBEZ1_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1_VK");
	}

	public String get_ARTBEZ1_VK_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ1_VK");	
	}

	public String get_ARTBEZ1_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ1_VK");
	}

	public String get_ARTBEZ1_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1_VK",cNotNullValue);
	}

	public String get_ARTBEZ1_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ1_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ1_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ1_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1_VK", calNewValueFormated);
	}
		public String get_ARTBEZ2_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2_EK");
	}

	public String get_ARTBEZ2_EK_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ2_EK");	
	}

	public String get_ARTBEZ2_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ2_EK");
	}

	public String get_ARTBEZ2_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2_EK",cNotNullValue);
	}

	public String get_ARTBEZ2_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ2_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ2_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ2_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2_EK", calNewValueFormated);
	}
		public String get_ARTBEZ2_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2_VK");
	}

	public String get_ARTBEZ2_VK_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ2_VK");	
	}

	public String get_ARTBEZ2_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ2_VK");
	}

	public String get_ARTBEZ2_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2_VK",cNotNullValue);
	}

	public String get_ARTBEZ2_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ2_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ2_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ2_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2_VK", calNewValueFormated);
	}
		public String get_AUFLADEN_BRUTTO_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUFLADEN_BRUTTO");
	}

	public String get_AUFLADEN_BRUTTO_cF() throws myException
	{
		return this.get_FormatedValue("AUFLADEN_BRUTTO");	
	}

	public String get_AUFLADEN_BRUTTO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUFLADEN_BRUTTO");
	}

	public String get_AUFLADEN_BRUTTO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUFLADEN_BRUTTO",cNotNullValue);
	}

	public String get_AUFLADEN_BRUTTO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUFLADEN_BRUTTO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUFLADEN_BRUTTO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUFLADEN_BRUTTO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUFLADEN_BRUTTO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_BRUTTO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFLADEN_BRUTTO", calNewValueFormated);
	}
		public Double get_AUFLADEN_BRUTTO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("AUFLADEN_BRUTTO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_AUFLADEN_BRUTTO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("AUFLADEN_BRUTTO").getDoubleValue();
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
	public String get_AUFLADEN_BRUTTO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AUFLADEN_BRUTTO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_AUFLADEN_BRUTTO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AUFLADEN_BRUTTO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_AUFLADEN_BRUTTO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("AUFLADEN_BRUTTO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_AUFLADEN_BRUTTO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("AUFLADEN_BRUTTO").getBigDecimalValue();
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
	
	
	public String get_AUFLADEN_TARA_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUFLADEN_TARA");
	}

	public String get_AUFLADEN_TARA_cF() throws myException
	{
		return this.get_FormatedValue("AUFLADEN_TARA");	
	}

	public String get_AUFLADEN_TARA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUFLADEN_TARA");
	}

	public String get_AUFLADEN_TARA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUFLADEN_TARA",cNotNullValue);
	}

	public String get_AUFLADEN_TARA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUFLADEN_TARA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUFLADEN_TARA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUFLADEN_TARA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUFLADEN_TARA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFLADEN_TARA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFLADEN_TARA", calNewValueFormated);
	}
		public Double get_AUFLADEN_TARA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("AUFLADEN_TARA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_AUFLADEN_TARA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("AUFLADEN_TARA").getDoubleValue();
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
	public String get_AUFLADEN_TARA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AUFLADEN_TARA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_AUFLADEN_TARA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AUFLADEN_TARA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_AUFLADEN_TARA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("AUFLADEN_TARA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_AUFLADEN_TARA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("AUFLADEN_TARA").getBigDecimalValue();
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
		public String get_A_HAUSNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_HAUSNUMMER");
	}

	public String get_A_HAUSNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("A_HAUSNUMMER");	
	}

	public String get_A_HAUSNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_HAUSNUMMER");
	}

	public String get_A_HAUSNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_HAUSNUMMER",cNotNullValue);
	}

	public String get_A_HAUSNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_HAUSNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_HAUSNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_HAUSNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_HAUSNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_HAUSNUMMER", calNewValueFormated);
	}
		public String get_A_LAENDERCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_LAENDERCODE");
	}

	public String get_A_LAENDERCODE_cF() throws myException
	{
		return this.get_FormatedValue("A_LAENDERCODE");	
	}

	public String get_A_LAENDERCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_LAENDERCODE");
	}

	public String get_A_LAENDERCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_LAENDERCODE",cNotNullValue);
	}

	public String get_A_LAENDERCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_LAENDERCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_LAENDERCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_LAENDERCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_LAENDERCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_LAENDERCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_LAENDERCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_LAENDERCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_LAENDERCODE", calNewValueFormated);
	}
		public String get_A_NAME1_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_NAME1");
	}

	public String get_A_NAME1_cF() throws myException
	{
		return this.get_FormatedValue("A_NAME1");	
	}

	public String get_A_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_NAME1");
	}

	public String get_A_NAME1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_NAME1",cNotNullValue);
	}

	public String get_A_NAME1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_NAME1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_NAME1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_NAME1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_NAME1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_NAME1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME1", calNewValueFormated);
	}
		public String get_A_NAME2_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_NAME2");
	}

	public String get_A_NAME2_cF() throws myException
	{
		return this.get_FormatedValue("A_NAME2");	
	}

	public String get_A_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_NAME2");
	}

	public String get_A_NAME2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_NAME2",cNotNullValue);
	}

	public String get_A_NAME2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_NAME2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_NAME2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_NAME2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_NAME2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_NAME2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME2", calNewValueFormated);
	}
		public String get_A_NAME3_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_NAME3");
	}

	public String get_A_NAME3_cF() throws myException
	{
		return this.get_FormatedValue("A_NAME3");	
	}

	public String get_A_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_NAME3");
	}

	public String get_A_NAME3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_NAME3",cNotNullValue);
	}

	public String get_A_NAME3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_NAME3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_NAME3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_NAME3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_NAME3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_NAME3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_NAME3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_NAME3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_NAME3", calNewValueFormated);
	}
		public String get_A_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_ORT");
	}

	public String get_A_ORT_cF() throws myException
	{
		return this.get_FormatedValue("A_ORT");	
	}

	public String get_A_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_ORT");
	}

	public String get_A_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_ORT",cNotNullValue);
	}

	public String get_A_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_ORT", calNewValueFormated);
	}
		public String get_A_ORTZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_ORTZUSATZ");
	}

	public String get_A_ORTZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("A_ORTZUSATZ");	
	}

	public String get_A_ORTZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_ORTZUSATZ");
	}

	public String get_A_ORTZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_ORTZUSATZ",cNotNullValue);
	}

	public String get_A_ORTZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_ORTZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_ORTZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_ORTZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_ORTZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_ORTZUSATZ", calNewValueFormated);
	}
		public String get_A_PLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_PLZ");
	}

	public String get_A_PLZ_cF() throws myException
	{
		return this.get_FormatedValue("A_PLZ");	
	}

	public String get_A_PLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_PLZ");
	}

	public String get_A_PLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_PLZ",cNotNullValue);
	}

	public String get_A_PLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_PLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_PLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_PLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_PLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_PLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_PLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_PLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_PLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_PLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_PLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_PLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_PLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_PLZ", calNewValueFormated);
	}
		public String get_A_STRASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_STRASSE");
	}

	public String get_A_STRASSE_cF() throws myException
	{
		return this.get_FormatedValue("A_STRASSE");	
	}

	public String get_A_STRASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_STRASSE");
	}

	public String get_A_STRASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_STRASSE",cNotNullValue);
	}

	public String get_A_STRASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_STRASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_STRASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_STRASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_STRASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_STRASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_STRASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_STRASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_STRASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_STRASSE", calNewValueFormated);
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
		public String get_BEMERKUNG_FUER_KUNDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_FUER_KUNDE");
	}

	public String get_BEMERKUNG_FUER_KUNDE_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_FUER_KUNDE");	
	}

	public String get_BEMERKUNG_FUER_KUNDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_FUER_KUNDE");
	}

	public String get_BEMERKUNG_FUER_KUNDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_FUER_KUNDE",cNotNullValue);
	}

	public String get_BEMERKUNG_FUER_KUNDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_FUER_KUNDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", calNewValueFormated);
	}
		public String get_BEMERKUNG_SACHBEARBEITER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_SACHBEARBEITER");
	}

	public String get_BEMERKUNG_SACHBEARBEITER_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_SACHBEARBEITER");	
	}

	public String get_BEMERKUNG_SACHBEARBEITER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_SACHBEARBEITER");
	}

	public String get_BEMERKUNG_SACHBEARBEITER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_SACHBEARBEITER",cNotNullValue);
	}

	public String get_BEMERKUNG_SACHBEARBEITER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_SACHBEARBEITER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", calNewValueFormated);
	}
		public String get_BEMERKUNG_START_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_START_FP");
	}

	public String get_BEMERKUNG_START_FP_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_START_FP");	
	}

	public String get_BEMERKUNG_START_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_START_FP");
	}

	public String get_BEMERKUNG_START_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_START_FP",cNotNullValue);
	}

	public String get_BEMERKUNG_START_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_START_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_START_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_START_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_START_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", calNewValueFormated);
	}
		public String get_BEMERKUNG_ZIEL_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ZIEL_FP");
	}

	public String get_BEMERKUNG_ZIEL_FP_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ZIEL_FP");	
	}

	public String get_BEMERKUNG_ZIEL_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_ZIEL_FP");
	}

	public String get_BEMERKUNG_ZIEL_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ZIEL_FP",cNotNullValue);
	}

	public String get_BEMERKUNG_ZIEL_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ZIEL_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_ZIEL_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_ZIEL_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", calNewValueFormated);
	}
		public String get_BESTELLNUMMER_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER_EK");
	}

	public String get_BESTELLNUMMER_EK_cF() throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER_EK");	
	}

	public String get_BESTELLNUMMER_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESTELLNUMMER_EK");
	}

	public String get_BESTELLNUMMER_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER_EK",cNotNullValue);
	}

	public String get_BESTELLNUMMER_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESTELLNUMMER_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESTELLNUMMER_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER_EK", calNewValueFormated);
	}
		public String get_BESTELLNUMMER_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER_VK");
	}

	public String get_BESTELLNUMMER_VK_cF() throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER_VK");	
	}

	public String get_BESTELLNUMMER_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESTELLNUMMER_VK");
	}

	public String get_BESTELLNUMMER_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER_VK",cNotNullValue);
	}

	public String get_BESTELLNUMMER_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESTELLNUMMER_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESTELLNUMMER_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER_VK", calNewValueFormated);
	}
		public String get_BUCHUNGSNR_FUHRE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNR_FUHRE");
	}

	public String get_BUCHUNGSNR_FUHRE_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNR_FUHRE");	
	}

	public String get_BUCHUNGSNR_FUHRE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSNR_FUHRE");
	}

	public String get_BUCHUNGSNR_FUHRE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNR_FUHRE",cNotNullValue);
	}

	public String get_BUCHUNGSNR_FUHRE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNR_FUHRE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNR_FUHRE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSNR_FUHRE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_FUHRE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNR_FUHRE", calNewValueFormated);
	}
		public String get_DATUM_ABHOLUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABHOLUNG");
	}

	public String get_DATUM_ABHOLUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ABHOLUNG");	
	}

	public String get_DATUM_ABHOLUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ABHOLUNG");
	}

	public String get_DATUM_ABHOLUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABHOLUNG",cNotNullValue);
	}

	public String get_DATUM_ABHOLUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ABHOLUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ABHOLUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABHOLUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ABHOLUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG", calNewValueFormated);
	}
		public String get_DATUM_ABHOLUNG_ENDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABHOLUNG_ENDE");
	}

	public String get_DATUM_ABHOLUNG_ENDE_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ABHOLUNG_ENDE");	
	}

	public String get_DATUM_ABHOLUNG_ENDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ABHOLUNG_ENDE");
	}

	public String get_DATUM_ABHOLUNG_ENDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABHOLUNG_ENDE",cNotNullValue);
	}

	public String get_DATUM_ABHOLUNG_ENDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ABHOLUNG_ENDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABHOLUNG_ENDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABHOLUNG_ENDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABHOLUNG_ENDE", calNewValueFormated);
	}
		public String get_DATUM_ABLADEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABLADEN");
	}

	public String get_DATUM_ABLADEN_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ABLADEN");	
	}

	public String get_DATUM_ABLADEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ABLADEN");
	}

	public String get_DATUM_ABLADEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABLADEN",cNotNullValue);
	}

	public String get_DATUM_ABLADEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ABLADEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ABLADEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABLADEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ABLADEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABLADEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABLADEN", calNewValueFormated);
	}
		public String get_DATUM_ANLIEFERUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ANLIEFERUNG");
	}

	public String get_DATUM_ANLIEFERUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ANLIEFERUNG");	
	}

	public String get_DATUM_ANLIEFERUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ANLIEFERUNG");
	}

	public String get_DATUM_ANLIEFERUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ANLIEFERUNG",cNotNullValue);
	}

	public String get_DATUM_ANLIEFERUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ANLIEFERUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ANLIEFERUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ANLIEFERUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ANLIEFERUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG", calNewValueFormated);
	}
		public String get_DATUM_ANLIEFERUNG_ENDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ANLIEFERUNG_ENDE");
	}

	public String get_DATUM_ANLIEFERUNG_ENDE_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ANLIEFERUNG_ENDE");	
	}

	public String get_DATUM_ANLIEFERUNG_ENDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ANLIEFERUNG_ENDE");
	}

	public String get_DATUM_ANLIEFERUNG_ENDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ANLIEFERUNG_ENDE",cNotNullValue);
	}

	public String get_DATUM_ANLIEFERUNG_ENDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ANLIEFERUNG_ENDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ANLIEFERUNG_ENDE", calNewValueFormated);
	}
		public String get_DATUM_AUFLADEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_AUFLADEN");
	}

	public String get_DATUM_AUFLADEN_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_AUFLADEN");	
	}

	public String get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_AUFLADEN");
	}

	public String get_DATUM_AUFLADEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_AUFLADEN",cNotNullValue);
	}

	public String get_DATUM_AUFLADEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_AUFLADEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_AUFLADEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_AUFLADEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_AUFLADEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUFLADEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_AUFLADEN", calNewValueFormated);
	}
		public String get_DAT_FAHRPLAN_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("DAT_FAHRPLAN_FP");
	}

	public String get_DAT_FAHRPLAN_FP_cF() throws myException
	{
		return this.get_FormatedValue("DAT_FAHRPLAN_FP");	
	}

	public String get_DAT_FAHRPLAN_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DAT_FAHRPLAN_FP");
	}

	public String get_DAT_FAHRPLAN_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DAT_FAHRPLAN_FP",cNotNullValue);
	}

	public String get_DAT_FAHRPLAN_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DAT_FAHRPLAN_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DAT_FAHRPLAN_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DAT_FAHRPLAN_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DAT_FAHRPLAN_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", calNewValueFormated);
	}
		public String get_DAT_VORGEMERKT_ENDE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_ENDE_FP");
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_cF() throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_ENDE_FP");	
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DAT_VORGEMERKT_ENDE_FP");
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_ENDE_FP",cNotNullValue);
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_ENDE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", calNewValueFormated);
	}
		public String get_DAT_VORGEMERKT_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_FP");
	}

	public String get_DAT_VORGEMERKT_FP_cF() throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_FP");	
	}

	public String get_DAT_VORGEMERKT_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DAT_VORGEMERKT_FP");
	}

	public String get_DAT_VORGEMERKT_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_FP",cNotNullValue);
	}

	public String get_DAT_VORGEMERKT_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DAT_VORGEMERKT_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DAT_VORGEMERKT_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", calNewValueFormated);
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
		public String get_EAN_CODE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("EAN_CODE_FP");
	}

	public String get_EAN_CODE_FP_cF() throws myException
	{
		return this.get_FormatedValue("EAN_CODE_FP");	
	}

	public String get_EAN_CODE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EAN_CODE_FP");
	}

	public String get_EAN_CODE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EAN_CODE_FP",cNotNullValue);
	}

	public String get_EAN_CODE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EAN_CODE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EAN_CODE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EAN_CODE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EAN_CODE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EAN_CODE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EAN_CODE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EAN_CODE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EAN_CODE_FP", calNewValueFormated);
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
		public String get_EINZELPREIS_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_EK");
	}

	public String get_EINZELPREIS_EK_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_EK");	
	}

	public String get_EINZELPREIS_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS_EK");
	}

	public String get_EINZELPREIS_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_EK",cNotNullValue);
	}

	public String get_EINZELPREIS_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_EK", calNewValueFormated);
	}
		public Double get_EINZELPREIS_EK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_EK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_EK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_EK").getDoubleValue();
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
	public String get_EINZELPREIS_EK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EINZELPREIS_EK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EINZELPREIS_EK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_EK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_EK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_EK").getBigDecimalValue();
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
	
	
	public String get_EINZELPREIS_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_VK");
	}

	public String get_EINZELPREIS_VK_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_VK");	
	}

	public String get_EINZELPREIS_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS_VK");
	}

	public String get_EINZELPREIS_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_VK",cNotNullValue);
	}

	public String get_EINZELPREIS_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_VK", calNewValueFormated);
	}
		public Double get_EINZELPREIS_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_VK").getDoubleValue();
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
	public String get_EINZELPREIS_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EINZELPREIS_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EINZELPREIS_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_VK").getBigDecimalValue();
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
	
	
	public String get_EK_KONTRAKTNR_ZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("EK_KONTRAKTNR_ZUSATZ");
	}

	public String get_EK_KONTRAKTNR_ZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("EK_KONTRAKTNR_ZUSATZ");	
	}

	public String get_EK_KONTRAKTNR_ZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EK_KONTRAKTNR_ZUSATZ");
	}

	public String get_EK_KONTRAKTNR_ZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EK_KONTRAKTNR_ZUSATZ",cNotNullValue);
	}

	public String get_EK_KONTRAKTNR_ZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EK_KONTRAKTNR_ZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_KONTRAKTNR_ZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_KONTRAKTNR_ZUSATZ", calNewValueFormated);
	}
		public String get_EK_VK_SORTE_LOCK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EK_VK_SORTE_LOCK");
	}

	public String get_EK_VK_SORTE_LOCK_cF() throws myException
	{
		return this.get_FormatedValue("EK_VK_SORTE_LOCK");	
	}

	public String get_EK_VK_SORTE_LOCK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EK_VK_SORTE_LOCK");
	}

	public String get_EK_VK_SORTE_LOCK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EK_VK_SORTE_LOCK",cNotNullValue);
	}

	public String get_EK_VK_SORTE_LOCK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EK_VK_SORTE_LOCK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EK_VK_SORTE_LOCK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EK_VK_SORTE_LOCK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EK_VK_SORTE_LOCK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_SORTE_LOCK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_SORTE_LOCK", calNewValueFormated);
	}
		public boolean is_EK_VK_SORTE_LOCK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_EK_VK_SORTE_LOCK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_EK_VK_SORTE_LOCK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_EK_VK_SORTE_LOCK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_EK_VK_ZUORD_ZWANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EK_VK_ZUORD_ZWANG");
	}

	public String get_EK_VK_ZUORD_ZWANG_cF() throws myException
	{
		return this.get_FormatedValue("EK_VK_ZUORD_ZWANG");	
	}

	public String get_EK_VK_ZUORD_ZWANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EK_VK_ZUORD_ZWANG");
	}

	public String get_EK_VK_ZUORD_ZWANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EK_VK_ZUORD_ZWANG",cNotNullValue);
	}

	public String get_EK_VK_ZUORD_ZWANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EK_VK_ZUORD_ZWANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", calNewValueFormated);
	}
		public boolean is_EK_VK_ZUORD_ZWANG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_EK_VK_ZUORD_ZWANG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_EK_VK_ZUORD_ZWANG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_EK_VK_ZUORD_ZWANG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_EPREIS_RESULT_NETTO_MGE_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_RESULT_NETTO_MGE_EK");
	}

	public String get_EPREIS_RESULT_NETTO_MGE_EK_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_RESULT_NETTO_MGE_EK");	
	}

	public String get_EPREIS_RESULT_NETTO_MGE_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_RESULT_NETTO_MGE_EK");
	}

	public String get_EPREIS_RESULT_NETTO_MGE_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_RESULT_NETTO_MGE_EK",cNotNullValue);
	}

	public String get_EPREIS_RESULT_NETTO_MGE_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_RESULT_NETTO_MGE_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_EK", calNewValueFormated);
	}
		public Double get_EPREIS_RESULT_NETTO_MGE_EK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_RESULT_NETTO_MGE_EK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_RESULT_NETTO_MGE_EK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_RESULT_NETTO_MGE_EK").getDoubleValue();
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
	public String get_EPREIS_RESULT_NETTO_MGE_EK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_RESULT_NETTO_MGE_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EPREIS_RESULT_NETTO_MGE_EK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_RESULT_NETTO_MGE_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EPREIS_RESULT_NETTO_MGE_EK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_RESULT_NETTO_MGE_EK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_RESULT_NETTO_MGE_EK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_RESULT_NETTO_MGE_EK").getBigDecimalValue();
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
	
	
	public String get_EPREIS_RESULT_NETTO_MGE_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EPREIS_RESULT_NETTO_MGE_VK");
	}

	public String get_EPREIS_RESULT_NETTO_MGE_VK_cF() throws myException
	{
		return this.get_FormatedValue("EPREIS_RESULT_NETTO_MGE_VK");	
	}

	public String get_EPREIS_RESULT_NETTO_MGE_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EPREIS_RESULT_NETTO_MGE_VK");
	}

	public String get_EPREIS_RESULT_NETTO_MGE_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EPREIS_RESULT_NETTO_MGE_VK",cNotNullValue);
	}

	public String get_EPREIS_RESULT_NETTO_MGE_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EPREIS_RESULT_NETTO_MGE_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EPREIS_RESULT_NETTO_MGE_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EPREIS_RESULT_NETTO_MGE_VK", calNewValueFormated);
	}
		public Double get_EPREIS_RESULT_NETTO_MGE_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EPREIS_RESULT_NETTO_MGE_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EPREIS_RESULT_NETTO_MGE_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EPREIS_RESULT_NETTO_MGE_VK").getDoubleValue();
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
	public String get_EPREIS_RESULT_NETTO_MGE_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_RESULT_NETTO_MGE_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EPREIS_RESULT_NETTO_MGE_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EPREIS_RESULT_NETTO_MGE_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EPREIS_RESULT_NETTO_MGE_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_RESULT_NETTO_MGE_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EPREIS_RESULT_NETTO_MGE_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EPREIS_RESULT_NETTO_MGE_VK").getBigDecimalValue();
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
	
	
	public String get_ERFASSER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERFASSER_FP");
	}

	public String get_ERFASSER_FP_cF() throws myException
	{
		return this.get_FormatedValue("ERFASSER_FP");	
	}

	public String get_ERFASSER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERFASSER_FP");
	}

	public String get_ERFASSER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERFASSER_FP",cNotNullValue);
	}

	public String get_ERFASSER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERFASSER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERFASSER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERFASSER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERFASSER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERFASSER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER_FP", calNewValueFormated);
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
	
	
	public String get_EU_STEUER_VERMERK_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK_EK");
	}

	public String get_EU_STEUER_VERMERK_EK_cF() throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK_EK");	
	}

	public String get_EU_STEUER_VERMERK_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_STEUER_VERMERK_EK");
	}

	public String get_EU_STEUER_VERMERK_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK_EK",cNotNullValue);
	}

	public String get_EU_STEUER_VERMERK_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_STEUER_VERMERK_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_EK", calNewValueFormated);
	}
		public String get_EU_STEUER_VERMERK_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK_VK");
	}

	public String get_EU_STEUER_VERMERK_VK_cF() throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK_VK");	
	}

	public String get_EU_STEUER_VERMERK_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_STEUER_VERMERK_VK");
	}

	public String get_EU_STEUER_VERMERK_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_STEUER_VERMERK_VK",cNotNullValue);
	}

	public String get_EU_STEUER_VERMERK_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_STEUER_VERMERK_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_STEUER_VERMERK_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_STEUER_VERMERK_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_STEUER_VERMERK_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_STEUER_VERMERK_VK", calNewValueFormated);
	}
		public String get_FAHRER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRER_FP");
	}

	public String get_FAHRER_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRER_FP");	
	}

	public String get_FAHRER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRER_FP");
	}

	public String get_FAHRER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRER_FP",cNotNullValue);
	}

	public String get_FAHRER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRER_FP", calNewValueFormated);
	}
		public String get_FAHRPLANGRUPPE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRPLANGRUPPE_FP");
	}

	public String get_FAHRPLANGRUPPE_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRPLANGRUPPE_FP");	
	}

	public String get_FAHRPLANGRUPPE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRPLANGRUPPE_FP");
	}

	public String get_FAHRPLANGRUPPE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRPLANGRUPPE_FP",cNotNullValue);
	}

	public String get_FAHRPLANGRUPPE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRPLANGRUPPE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRPLANGRUPPE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRPLANGRUPPE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", calNewValueFormated);
	}
		public Long get_FAHRPLANGRUPPE_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FAHRPLANGRUPPE_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FAHRPLANGRUPPE_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FAHRPLANGRUPPE_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FAHRPLANGRUPPE_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FAHRPLANGRUPPE_FP").getDoubleValue();
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
	public String get_FAHRPLANGRUPPE_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FAHRPLANGRUPPE_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_FAHRPLANGRUPPE_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FAHRPLANGRUPPE_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_FAHRPLANGRUPPE_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FAHRPLANGRUPPE_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FAHRPLANGRUPPE_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FAHRPLANGRUPPE_FP").getBigDecimalValue();
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
	
	
	public String get_FAHRT_ANFANG_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ANFANG_FP");
	}

	public String get_FAHRT_ANFANG_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRT_ANFANG_FP");	
	}

	public String get_FAHRT_ANFANG_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRT_ANFANG_FP");
	}

	public String get_FAHRT_ANFANG_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ANFANG_FP",cNotNullValue);
	}

	public String get_FAHRT_ANFANG_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRT_ANFANG_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRT_ANFANG_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ANFANG_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRT_ANFANG_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", calNewValueFormated);
	}
		public String get_FAHRT_ENDE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ENDE_FP");
	}

	public String get_FAHRT_ENDE_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRT_ENDE_FP");	
	}

	public String get_FAHRT_ENDE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRT_ENDE_FP");
	}

	public String get_FAHRT_ENDE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ENDE_FP",cNotNullValue);
	}

	public String get_FAHRT_ENDE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRT_ENDE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRT_ENDE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRT_ENDE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", calNewValueFormated);
	}
		public String get_FAX_ABNEHMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAX_ABNEHMER");
	}

	public String get_FAX_ABNEHMER_cF() throws myException
	{
		return this.get_FormatedValue("FAX_ABNEHMER");	
	}

	public String get_FAX_ABNEHMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAX_ABNEHMER");
	}

	public String get_FAX_ABNEHMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAX_ABNEHMER",cNotNullValue);
	}

	public String get_FAX_ABNEHMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAX_ABNEHMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAX_ABNEHMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAX_ABNEHMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAX_ABNEHMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ABNEHMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_ABNEHMER", calNewValueFormated);
	}
		public String get_FAX_LIEFERANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAX_LIEFERANT");
	}

	public String get_FAX_LIEFERANT_cF() throws myException
	{
		return this.get_FormatedValue("FAX_LIEFERANT");	
	}

	public String get_FAX_LIEFERANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAX_LIEFERANT");
	}

	public String get_FAX_LIEFERANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAX_LIEFERANT",cNotNullValue);
	}

	public String get_FAX_LIEFERANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAX_LIEFERANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAX_LIEFERANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAX_LIEFERANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAX_LIEFERANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_LIEFERANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_LIEFERANT", calNewValueFormated);
	}
		public String get_FUHRENGRUPPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("FUHRENGRUPPE");
	}

	public String get_FUHRENGRUPPE_cF() throws myException
	{
		return this.get_FormatedValue("FUHRENGRUPPE");	
	}

	public String get_FUHRENGRUPPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FUHRENGRUPPE");
	}

	public String get_FUHRENGRUPPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FUHRENGRUPPE",cNotNullValue);
	}

	public String get_FUHRENGRUPPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FUHRENGRUPPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FUHRENGRUPPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FUHRENGRUPPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FUHRENGRUPPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRENGRUPPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRENGRUPPE", calNewValueFormated);
	}
		public Long get_FUHRENGRUPPE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FUHRENGRUPPE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FUHRENGRUPPE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FUHRENGRUPPE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FUHRENGRUPPE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FUHRENGRUPPE").getDoubleValue();
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
	public String get_FUHRENGRUPPE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FUHRENGRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_FUHRENGRUPPE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FUHRENGRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_FUHRENGRUPPE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FUHRENGRUPPE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FUHRENGRUPPE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FUHRENGRUPPE").getBigDecimalValue();
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
	
	
	public String get_FUHRE_AUS_FAHRPLAN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FUHRE_AUS_FAHRPLAN");
	}

	public String get_FUHRE_AUS_FAHRPLAN_cF() throws myException
	{
		return this.get_FormatedValue("FUHRE_AUS_FAHRPLAN");	
	}

	public String get_FUHRE_AUS_FAHRPLAN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FUHRE_AUS_FAHRPLAN");
	}

	public String get_FUHRE_AUS_FAHRPLAN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FUHRE_AUS_FAHRPLAN",cNotNullValue);
	}

	public String get_FUHRE_AUS_FAHRPLAN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FUHRE_AUS_FAHRPLAN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FUHRE_AUS_FAHRPLAN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_AUS_FAHRPLAN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_AUS_FAHRPLAN", calNewValueFormated);
	}
		public boolean is_FUHRE_AUS_FAHRPLAN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FUHRE_AUS_FAHRPLAN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FUHRE_AUS_FAHRPLAN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FUHRE_AUS_FAHRPLAN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_FUHRE_IST_UMGELEITET_cUF() throws myException
	{
		return this.get_UnFormatedValue("FUHRE_IST_UMGELEITET");
	}

	public String get_FUHRE_IST_UMGELEITET_cF() throws myException
	{
		return this.get_FormatedValue("FUHRE_IST_UMGELEITET");	
	}

	public String get_FUHRE_IST_UMGELEITET_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FUHRE_IST_UMGELEITET");
	}

	public String get_FUHRE_IST_UMGELEITET_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FUHRE_IST_UMGELEITET",cNotNullValue);
	}

	public String get_FUHRE_IST_UMGELEITET_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FUHRE_IST_UMGELEITET",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FUHRE_IST_UMGELEITET(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FUHRE_IST_UMGELEITET", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_IST_UMGELEITET_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_IST_UMGELEITET", calNewValueFormated);
	}
		public boolean is_FUHRE_IST_UMGELEITET_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FUHRE_IST_UMGELEITET_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FUHRE_IST_UMGELEITET_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FUHRE_IST_UMGELEITET_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_FUHRE_KOMPLETT_cUF() throws myException
	{
		return this.get_UnFormatedValue("FUHRE_KOMPLETT");
	}

	public String get_FUHRE_KOMPLETT_cF() throws myException
	{
		return this.get_FormatedValue("FUHRE_KOMPLETT");	
	}

	public String get_FUHRE_KOMPLETT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FUHRE_KOMPLETT");
	}

	public String get_FUHRE_KOMPLETT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FUHRE_KOMPLETT",cNotNullValue);
	}

	public String get_FUHRE_KOMPLETT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FUHRE_KOMPLETT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FUHRE_KOMPLETT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FUHRE_KOMPLETT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FUHRE_KOMPLETT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FUHRE_KOMPLETT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FUHRE_KOMPLETT", calNewValueFormated);
	}
		public boolean is_FUHRE_KOMPLETT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FUHRE_KOMPLETT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FUHRE_KOMPLETT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FUHRE_KOMPLETT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_ID_ADRESSE_FREMDAUFTRAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_FREMDAUFTRAG");
	}

	public String get_ID_ADRESSE_FREMDAUFTRAG_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_FREMDAUFTRAG");	
	}

	public String get_ID_ADRESSE_FREMDAUFTRAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_FREMDAUFTRAG");
	}

	public String get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_FREMDAUFTRAG",cNotNullValue);
	}

	public String get_ID_ADRESSE_FREMDAUFTRAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_FREMDAUFTRAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDAUFTRAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDAUFTRAG", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_FREMDAUFTRAG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_FREMDAUFTRAG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_FREMDAUFTRAG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_FREMDAUFTRAG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_FREMDAUFTRAG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_FREMDAUFTRAG").getDoubleValue();
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
	public String get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_FREMDAUFTRAG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_FREMDAUFTRAG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_FREMDAUFTRAG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_FREMDAUFTRAG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_FREMDAUFTRAG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_FREMDAUFTRAG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_FREMDAUFTRAG").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LAGER_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_START");
	}

	public String get_ID_ADRESSE_LAGER_START_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_START");	
	}

	public String get_ID_ADRESSE_LAGER_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER_START");
	}

	public String get_ID_ADRESSE_LAGER_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_START",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_START_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER_START").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_START_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_START").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_START_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_START").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_START_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_LAGER_START_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_LAGER_START_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_START").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_START_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_START").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LAGER_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_ZIEL");
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_ZIEL");	
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER_ZIEL");
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_ZIEL",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_LAGER_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_LAGER_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_SPEDITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_SPEDITION");
	}

	public String get_ID_ADRESSE_SPEDITION_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_SPEDITION");	
	}

	public String get_ID_ADRESSE_SPEDITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_SPEDITION");
	}

	public String get_ID_ADRESSE_SPEDITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_SPEDITION",cNotNullValue);
	}

	public String get_ID_ADRESSE_SPEDITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_SPEDITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_SPEDITION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_SPEDITION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_SPEDITION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_SPEDITION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_SPEDITION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_SPEDITION").getDoubleValue();
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
	public String get_ID_ADRESSE_SPEDITION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_SPEDITION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_SPEDITION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_SPEDITION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_SPEDITION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_SPEDITION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_SPEDITION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_SPEDITION").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_START");
	}

	public String get_ID_ADRESSE_START_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_START");	
	}

	public String get_ID_ADRESSE_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_START");
	}

	public String get_ID_ADRESSE_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_START",cNotNullValue);
	}

	public String get_ID_ADRESSE_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_START", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_START_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_START").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_START_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_START").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_START_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_START").getDoubleValue();
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
	public String get_ID_ADRESSE_START_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_START_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_START_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_START").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_START_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_START").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_ZIEL");
	}

	public String get_ID_ADRESSE_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_ZIEL");	
	}

	public String get_ID_ADRESSE_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_ZIEL");
	}

	public String get_ID_ADRESSE_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_ZIEL",cNotNullValue);
	}

	public String get_ID_ADRESSE_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ZIEL", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_ZIEL").getDoubleValue();
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
	public String get_ID_ADRESSE_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_BEZ_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_EK");
	}

	public String get_ID_ARTIKEL_BEZ_EK_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_EK");	
	}

	public String get_ID_ARTIKEL_BEZ_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_BEZ_EK");
	}

	public String get_ID_ARTIKEL_BEZ_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_EK",cNotNullValue);
	}

	public String get_ID_ARTIKEL_BEZ_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_EK", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_BEZ_EK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_BEZ_EK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_BEZ_EK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_EK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_BEZ_EK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_EK").getDoubleValue();
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
	public String get_ID_ARTIKEL_BEZ_EK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ARTIKEL_BEZ_EK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ARTIKEL_BEZ_EK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_EK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_BEZ_EK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_EK").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_BEZ_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_VK");
	}

	public String get_ID_ARTIKEL_BEZ_VK_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_VK");	
	}

	public String get_ID_ARTIKEL_BEZ_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_BEZ_VK");
	}

	public String get_ID_ARTIKEL_BEZ_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_VK",cNotNullValue);
	}

	public String get_ID_ARTIKEL_BEZ_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_VK", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_BEZ_VK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_BEZ_VK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_BEZ_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_BEZ_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_VK").getDoubleValue();
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
	public String get_ID_ARTIKEL_BEZ_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ARTIKEL_BEZ_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ARTIKEL_BEZ_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_BEZ_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_VK").getBigDecimalValue();
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
	
	
	public String get_ID_CONTAINERTYP_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINERTYP_FP");
	}

	public String get_ID_CONTAINERTYP_FP_cF() throws myException
	{
		return this.get_FormatedValue("ID_CONTAINERTYP_FP");	
	}

	public String get_ID_CONTAINERTYP_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_CONTAINERTYP_FP");
	}

	public String get_ID_CONTAINERTYP_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINERTYP_FP",cNotNullValue);
	}

	public String get_ID_CONTAINERTYP_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_CONTAINERTYP_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_CONTAINERTYP_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINERTYP_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_CONTAINERTYP_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", calNewValueFormated);
	}
		public Long get_ID_CONTAINERTYP_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_CONTAINERTYP_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_CONTAINERTYP_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_CONTAINERTYP_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_CONTAINERTYP_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_CONTAINERTYP_FP").getDoubleValue();
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
	public String get_ID_CONTAINERTYP_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINERTYP_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_CONTAINERTYP_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINERTYP_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_CONTAINERTYP_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINERTYP_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_CONTAINERTYP_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINERTYP_FP").getBigDecimalValue();
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
	
	
	public String get_ID_FAHRPLAN_ZEITANGABE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_FAHRPLAN_ZEITANGABE");
	}

	public String get_ID_FAHRPLAN_ZEITANGABE_cF() throws myException
	{
		return this.get_FormatedValue("ID_FAHRPLAN_ZEITANGABE");	
	}

	public String get_ID_FAHRPLAN_ZEITANGABE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_FAHRPLAN_ZEITANGABE");
	}

	public String get_ID_FAHRPLAN_ZEITANGABE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_FAHRPLAN_ZEITANGABE",cNotNullValue);
	}

	public String get_ID_FAHRPLAN_ZEITANGABE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_FAHRPLAN_ZEITANGABE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FAHRPLAN_ZEITANGABE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FAHRPLAN_ZEITANGABE", calNewValueFormated);
	}
		public Long get_ID_FAHRPLAN_ZEITANGABE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_FAHRPLAN_ZEITANGABE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_FAHRPLAN_ZEITANGABE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_FAHRPLAN_ZEITANGABE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_FAHRPLAN_ZEITANGABE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_FAHRPLAN_ZEITANGABE").getDoubleValue();
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
	public String get_ID_FAHRPLAN_ZEITANGABE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FAHRPLAN_ZEITANGABE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_FAHRPLAN_ZEITANGABE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FAHRPLAN_ZEITANGABE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_FAHRPLAN_ZEITANGABE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FAHRPLAN_ZEITANGABE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_FAHRPLAN_ZEITANGABE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FAHRPLAN_ZEITANGABE").getBigDecimalValue();
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
	
	
	public String get_ID_MASCHINEN_ANH_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_ANH_FP");
	}

	public String get_ID_MASCHINEN_ANH_FP_cF() throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_ANH_FP");	
	}

	public String get_ID_MASCHINEN_ANH_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MASCHINEN_ANH_FP");
	}

	public String get_ID_MASCHINEN_ANH_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_ANH_FP",cNotNullValue);
	}

	public String get_ID_MASCHINEN_ANH_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_ANH_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_ANH_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MASCHINEN_ANH_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", calNewValueFormated);
	}
		public Long get_ID_MASCHINEN_ANH_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MASCHINEN_ANH_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MASCHINEN_ANH_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_ANH_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MASCHINEN_ANH_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_ANH_FP").getDoubleValue();
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
	public String get_ID_MASCHINEN_ANH_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_ANH_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_MASCHINEN_ANH_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_ANH_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_MASCHINEN_ANH_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_ANH_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MASCHINEN_ANH_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_ANH_FP").getBigDecimalValue();
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
	
	
	public String get_ID_MASCHINEN_LKW_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_LKW_FP");
	}

	public String get_ID_MASCHINEN_LKW_FP_cF() throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_LKW_FP");	
	}

	public String get_ID_MASCHINEN_LKW_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MASCHINEN_LKW_FP");
	}

	public String get_ID_MASCHINEN_LKW_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_LKW_FP",cNotNullValue);
	}

	public String get_ID_MASCHINEN_LKW_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_LKW_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_LKW_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MASCHINEN_LKW_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", calNewValueFormated);
	}
		public Long get_ID_MASCHINEN_LKW_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MASCHINEN_LKW_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MASCHINEN_LKW_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_LKW_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MASCHINEN_LKW_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_LKW_FP").getDoubleValue();
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
	public String get_ID_MASCHINEN_LKW_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_LKW_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_MASCHINEN_LKW_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_LKW_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_MASCHINEN_LKW_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_LKW_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MASCHINEN_LKW_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_LKW_FP").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_EK");
	}

	public String get_ID_TAX_EK_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_EK");	
	}

	public String get_ID_TAX_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_EK");
	}

	public String get_ID_TAX_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_EK",cNotNullValue);
	}

	public String get_ID_TAX_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_EK", calNewValueFormated);
	}
		public Long get_ID_TAX_EK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_EK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_EK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_EK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_EK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_EK").getDoubleValue();
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
	public String get_ID_TAX_EK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_TAX_EK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_TAX_EK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_EK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_EK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_EK").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_VK");
	}

	public String get_ID_TAX_VK_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_VK");	
	}

	public String get_ID_TAX_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_VK");
	}

	public String get_ID_TAX_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_VK",cNotNullValue);
	}

	public String get_ID_TAX_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_VK", calNewValueFormated);
	}
		public Long get_ID_TAX_VK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_VK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_VK").getDoubleValue();
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
	public String get_ID_TAX_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_TAX_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_TAX_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_VK").getBigDecimalValue();
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
	
	
	public String get_ID_UMA_KONTRAKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_UMA_KONTRAKT");
	}

	public String get_ID_UMA_KONTRAKT_cF() throws myException
	{
		return this.get_FormatedValue("ID_UMA_KONTRAKT");	
	}

	public String get_ID_UMA_KONTRAKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_UMA_KONTRAKT");
	}

	public String get_ID_UMA_KONTRAKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_UMA_KONTRAKT",cNotNullValue);
	}

	public String get_ID_UMA_KONTRAKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_UMA_KONTRAKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", calNewValueFormated);
	}
		public Long get_ID_UMA_KONTRAKT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_UMA_KONTRAKT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_UMA_KONTRAKT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_UMA_KONTRAKT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_UMA_KONTRAKT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_UMA_KONTRAKT").getDoubleValue();
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
	public String get_ID_UMA_KONTRAKT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_UMA_KONTRAKT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_UMA_KONTRAKT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_UMA_KONTRAKT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_UMA_KONTRAKT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_UMA_KONTRAKT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_UMA_KONTRAKT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_UMA_KONTRAKT").getBigDecimalValue();
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
	
	
	public String get_ID_VERARBEITUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VERARBEITUNG");
	}

	public String get_ID_VERARBEITUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_VERARBEITUNG");	
	}

	public String get_ID_VERARBEITUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VERARBEITUNG");
	}

	public String get_ID_VERARBEITUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VERARBEITUNG",cNotNullValue);
	}

	public String get_ID_VERARBEITUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VERARBEITUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VERARBEITUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VERARBEITUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VERARBEITUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERARBEITUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERARBEITUNG", calNewValueFormated);
	}
		public Long get_ID_VERARBEITUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VERARBEITUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VERARBEITUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VERARBEITUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VERARBEITUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VERARBEITUNG").getDoubleValue();
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
	public String get_ID_VERARBEITUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VERARBEITUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VERARBEITUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VERARBEITUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VERARBEITUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VERARBEITUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VERARBEITUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VERARBEITUNG").getBigDecimalValue();
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
	
	
	public String get_ID_VERPACKUNGSART_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VERPACKUNGSART");
	}

	public String get_ID_VERPACKUNGSART_cF() throws myException
	{
		return this.get_FormatedValue("ID_VERPACKUNGSART");	
	}

	public String get_ID_VERPACKUNGSART_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VERPACKUNGSART");
	}

	public String get_ID_VERPACKUNGSART_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VERPACKUNGSART",cNotNullValue);
	}

	public String get_ID_VERPACKUNGSART_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VERPACKUNGSART",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", calNewValueFormated);
	}
		public Long get_ID_VERPACKUNGSART_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VERPACKUNGSART").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VERPACKUNGSART_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VERPACKUNGSART").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VERPACKUNGSART_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VERPACKUNGSART").getDoubleValue();
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
	public String get_ID_VERPACKUNGSART_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VERPACKUNGSART_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VERPACKUNGSART_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VERPACKUNGSART_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VERPACKUNGSART_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VERPACKUNGSART").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VERPACKUNGSART_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VERPACKUNGSART").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_KON_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON_EK");
	}

	public String get_ID_VPOS_KON_EK_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON_EK");	
	}

	public String get_ID_VPOS_KON_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_KON_EK");
	}

	public String get_ID_VPOS_KON_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON_EK",cNotNullValue);
	}

	public String get_ID_VPOS_KON_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_KON_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_KON_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_EK", calNewValueFormated);
	}
		public Long get_ID_VPOS_KON_EK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_KON_EK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_KON_EK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON_EK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_KON_EK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON_EK").getDoubleValue();
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
	public String get_ID_VPOS_KON_EK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_KON_EK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_KON_EK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON_EK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_KON_EK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON_EK").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_KON_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON_VK");
	}

	public String get_ID_VPOS_KON_VK_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON_VK");	
	}

	public String get_ID_VPOS_KON_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_KON_VK");
	}

	public String get_ID_VPOS_KON_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON_VK",cNotNullValue);
	}

	public String get_ID_VPOS_KON_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_KON_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_KON_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_VK", calNewValueFormated);
	}
		public Long get_ID_VPOS_KON_VK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_KON_VK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_KON_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_KON_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON_VK").getDoubleValue();
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
	public String get_ID_VPOS_KON_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_KON_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_KON_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_KON_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON_VK").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_STD_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_STD_EK");
	}

	public String get_ID_VPOS_STD_EK_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_STD_EK");	
	}

	public String get_ID_VPOS_STD_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_STD_EK");
	}

	public String get_ID_VPOS_STD_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_STD_EK",cNotNullValue);
	}

	public String get_ID_VPOS_STD_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_STD_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_STD_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_STD_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD_EK", calNewValueFormated);
	}
		public Long get_ID_VPOS_STD_EK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_STD_EK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_STD_EK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_STD_EK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_STD_EK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_STD_EK").getDoubleValue();
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
	public String get_ID_VPOS_STD_EK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_STD_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_STD_EK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_STD_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_STD_EK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_STD_EK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_STD_EK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_STD_EK").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_STD_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_STD_VK");
	}

	public String get_ID_VPOS_STD_VK_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_STD_VK");	
	}

	public String get_ID_VPOS_STD_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_STD_VK");
	}

	public String get_ID_VPOS_STD_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_STD_VK",cNotNullValue);
	}

	public String get_ID_VPOS_STD_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_STD_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_STD_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_STD_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_STD_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_STD_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_STD_VK", calNewValueFormated);
	}
		public Long get_ID_VPOS_STD_VK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_STD_VK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_STD_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_STD_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_STD_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_STD_VK").getDoubleValue();
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
	public String get_ID_VPOS_STD_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_STD_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_STD_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_STD_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_STD_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_STD_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_STD_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_STD_VK").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA");
	}

	public String get_ID_VPOS_TPA_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA");	
	}

	public String get_ID_VPOS_TPA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA");
	}

	public String get_ID_VPOS_TPA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA").getDoubleValue();
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
	public String get_ID_VPOS_TPA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_TPA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_TPA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA").getBigDecimalValue();
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
	
	
	public String get_ID_WIEGEKARTE_ABN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_ABN");
	}

	public String get_ID_WIEGEKARTE_ABN_cF() throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_ABN");	
	}

	public String get_ID_WIEGEKARTE_ABN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WIEGEKARTE_ABN");
	}

	public String get_ID_WIEGEKARTE_ABN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_ABN",cNotNullValue);
	}

	public String get_ID_WIEGEKARTE_ABN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_ABN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_ABN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WIEGEKARTE_ABN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_ABN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_ABN", calNewValueFormated);
	}
		public Long get_ID_WIEGEKARTE_ABN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WIEGEKARTE_ABN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WIEGEKARTE_ABN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_ABN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WIEGEKARTE_ABN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_ABN").getDoubleValue();
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
	public String get_ID_WIEGEKARTE_ABN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_WIEGEKARTE_ABN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_ABN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_WIEGEKARTE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_ABN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WIEGEKARTE_ABN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_ABN").getBigDecimalValue();
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
	
	
	public String get_ID_WIEGEKARTE_LIEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_LIEF");
	}

	public String get_ID_WIEGEKARTE_LIEF_cF() throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_LIEF");	
	}

	public String get_ID_WIEGEKARTE_LIEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WIEGEKARTE_LIEF");
	}

	public String get_ID_WIEGEKARTE_LIEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_LIEF",cNotNullValue);
	}

	public String get_ID_WIEGEKARTE_LIEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_LIEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_LIEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WIEGEKARTE_LIEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_LIEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_LIEF", calNewValueFormated);
	}
		public Long get_ID_WIEGEKARTE_LIEF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WIEGEKARTE_LIEF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WIEGEKARTE_LIEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_LIEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WIEGEKARTE_LIEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_LIEF").getDoubleValue();
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
	public String get_ID_WIEGEKARTE_LIEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_WIEGEKARTE_LIEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_LIEF_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_WIEGEKARTE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_LIEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WIEGEKARTE_LIEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_LIEF").getBigDecimalValue();
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
	
	
	public String get_INTRASTAT_MELD_IN_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_IN");
	}

	public String get_INTRASTAT_MELD_IN_cF() throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_IN");	
	}

	public String get_INTRASTAT_MELD_IN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTRASTAT_MELD_IN");
	}

	public String get_INTRASTAT_MELD_IN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_IN",cNotNullValue);
	}

	public String get_INTRASTAT_MELD_IN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_IN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTRASTAT_MELD_IN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD_IN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTRASTAT_MELD_IN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", calNewValueFormated);
	}
		public String get_INTRASTAT_MELD_OUT_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_OUT");
	}

	public String get_INTRASTAT_MELD_OUT_cF() throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_OUT");	
	}

	public String get_INTRASTAT_MELD_OUT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTRASTAT_MELD_OUT");
	}

	public String get_INTRASTAT_MELD_OUT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_OUT",cNotNullValue);
	}

	public String get_INTRASTAT_MELD_OUT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_OUT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTRASTAT_MELD_OUT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD_OUT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTRASTAT_MELD_OUT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", calNewValueFormated);
	}
		public String get_IST_GEPLANT_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_GEPLANT_FP");
	}

	public String get_IST_GEPLANT_FP_cF() throws myException
	{
		return this.get_FormatedValue("IST_GEPLANT_FP");	
	}

	public String get_IST_GEPLANT_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_GEPLANT_FP");
	}

	public String get_IST_GEPLANT_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_GEPLANT_FP",cNotNullValue);
	}

	public String get_IST_GEPLANT_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_GEPLANT_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_GEPLANT_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_GEPLANT_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_GEPLANT_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", calNewValueFormated);
	}
		public boolean is_IST_GEPLANT_FP_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEPLANT_FP_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_GEPLANT_FP_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEPLANT_FP_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_STORNIERT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_STORNIERT");
	}

	public String get_IST_STORNIERT_cF() throws myException
	{
		return this.get_FormatedValue("IST_STORNIERT");	
	}

	public String get_IST_STORNIERT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_STORNIERT");
	}

	public String get_IST_STORNIERT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_STORNIERT",cNotNullValue);
	}

	public String get_IST_STORNIERT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_STORNIERT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_STORNIERT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_STORNIERT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_STORNIERT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_STORNIERT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_STORNIERT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_STORNIERT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STORNIERT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_STORNIERT", calNewValueFormated);
	}
		public boolean is_IST_STORNIERT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_STORNIERT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_STORNIERT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_STORNIERT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_KENNER_ALTE_SAETZE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("KENNER_ALTE_SAETZE_FP");
	}

	public String get_KENNER_ALTE_SAETZE_FP_cF() throws myException
	{
		return this.get_FormatedValue("KENNER_ALTE_SAETZE_FP");	
	}

	public String get_KENNER_ALTE_SAETZE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KENNER_ALTE_SAETZE_FP");
	}

	public String get_KENNER_ALTE_SAETZE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KENNER_ALTE_SAETZE_FP",cNotNullValue);
	}

	public String get_KENNER_ALTE_SAETZE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KENNER_ALTE_SAETZE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KENNER_ALTE_SAETZE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNER_ALTE_SAETZE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KENNER_ALTE_SAETZE_FP", calNewValueFormated);
	}
		public boolean is_KENNER_ALTE_SAETZE_FP_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KENNER_ALTE_SAETZE_FP_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KENNER_ALTE_SAETZE_FP_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KENNER_ALTE_SAETZE_FP_cUF_NN("N").equals("N"))
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
		public String get_LIEFERBED_ALTERNATIV_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERBED_ALTERNATIV_EK");
	}

	public String get_LIEFERBED_ALTERNATIV_EK_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERBED_ALTERNATIV_EK");	
	}

	public String get_LIEFERBED_ALTERNATIV_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERBED_ALTERNATIV_EK");
	}

	public String get_LIEFERBED_ALTERNATIV_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERBED_ALTERNATIV_EK",cNotNullValue);
	}

	public String get_LIEFERBED_ALTERNATIV_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERBED_ALTERNATIV_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_EK", calNewValueFormated);
	}
		public String get_LIEFERBED_ALTERNATIV_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERBED_ALTERNATIV_VK");
	}

	public String get_LIEFERBED_ALTERNATIV_VK_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERBED_ALTERNATIV_VK");	
	}

	public String get_LIEFERBED_ALTERNATIV_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERBED_ALTERNATIV_VK");
	}

	public String get_LIEFERBED_ALTERNATIV_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERBED_ALTERNATIV_VK",cNotNullValue);
	}

	public String get_LIEFERBED_ALTERNATIV_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERBED_ALTERNATIV_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBED_ALTERNATIV_VK", calNewValueFormated);
	}
		public String get_LIEFERINFO_TPA_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERINFO_TPA");
	}

	public String get_LIEFERINFO_TPA_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERINFO_TPA");	
	}

	public String get_LIEFERINFO_TPA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERINFO_TPA");
	}

	public String get_LIEFERINFO_TPA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERINFO_TPA",cNotNullValue);
	}

	public String get_LIEFERINFO_TPA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERINFO_TPA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERINFO_TPA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERINFO_TPA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERINFO_TPA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERINFO_TPA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERINFO_TPA", calNewValueFormated);
	}
		public String get_L_HAUSNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_HAUSNUMMER");
	}

	public String get_L_HAUSNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("L_HAUSNUMMER");	
	}

	public String get_L_HAUSNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_HAUSNUMMER");
	}

	public String get_L_HAUSNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_HAUSNUMMER",cNotNullValue);
	}

	public String get_L_HAUSNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_HAUSNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", calNewValueFormated);
	}
		public String get_L_LAENDERCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_LAENDERCODE");
	}

	public String get_L_LAENDERCODE_cF() throws myException
	{
		return this.get_FormatedValue("L_LAENDERCODE");	
	}

	public String get_L_LAENDERCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_LAENDERCODE");
	}

	public String get_L_LAENDERCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_LAENDERCODE",cNotNullValue);
	}

	public String get_L_LAENDERCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_LAENDERCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_LAENDERCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_LAENDERCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_LAENDERCODE", calNewValueFormated);
	}
		public String get_L_NAME1_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_NAME1");
	}

	public String get_L_NAME1_cF() throws myException
	{
		return this.get_FormatedValue("L_NAME1");	
	}

	public String get_L_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_NAME1");
	}

	public String get_L_NAME1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_NAME1",cNotNullValue);
	}

	public String get_L_NAME1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_NAME1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_NAME1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME1", calNewValueFormated);
	}
		public String get_L_NAME2_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_NAME2");
	}

	public String get_L_NAME2_cF() throws myException
	{
		return this.get_FormatedValue("L_NAME2");	
	}

	public String get_L_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_NAME2");
	}

	public String get_L_NAME2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_NAME2",cNotNullValue);
	}

	public String get_L_NAME2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_NAME2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_NAME2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME2", calNewValueFormated);
	}
		public String get_L_NAME3_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_NAME3");
	}

	public String get_L_NAME3_cF() throws myException
	{
		return this.get_FormatedValue("L_NAME3");	
	}

	public String get_L_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_NAME3");
	}

	public String get_L_NAME3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_NAME3",cNotNullValue);
	}

	public String get_L_NAME3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_NAME3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_NAME3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME3", calNewValueFormated);
	}
		public String get_L_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_ORT");
	}

	public String get_L_ORT_cF() throws myException
	{
		return this.get_FormatedValue("L_ORT");	
	}

	public String get_L_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_ORT");
	}

	public String get_L_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_ORT",cNotNullValue);
	}

	public String get_L_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORT", calNewValueFormated);
	}
		public String get_L_ORTZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_ORTZUSATZ");
	}

	public String get_L_ORTZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("L_ORTZUSATZ");	
	}

	public String get_L_ORTZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_ORTZUSATZ");
	}

	public String get_L_ORTZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_ORTZUSATZ",cNotNullValue);
	}

	public String get_L_ORTZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_ORTZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", calNewValueFormated);
	}
		public String get_L_PLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_PLZ");
	}

	public String get_L_PLZ_cF() throws myException
	{
		return this.get_FormatedValue("L_PLZ");	
	}

	public String get_L_PLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_PLZ");
	}

	public String get_L_PLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_PLZ",cNotNullValue);
	}

	public String get_L_PLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_PLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_PLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_PLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_PLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_PLZ", calNewValueFormated);
	}
		public String get_L_STRASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_STRASSE");
	}

	public String get_L_STRASSE_cF() throws myException
	{
		return this.get_FormatedValue("L_STRASSE");	
	}

	public String get_L_STRASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_STRASSE");
	}

	public String get_L_STRASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_STRASSE",cNotNullValue);
	}

	public String get_L_STRASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_STRASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_STRASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_STRASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_STRASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_STRASSE", calNewValueFormated);
	}
		public String get_MANUELL_PREIS_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("MANUELL_PREIS_EK");
	}

	public String get_MANUELL_PREIS_EK_cF() throws myException
	{
		return this.get_FormatedValue("MANUELL_PREIS_EK");	
	}

	public String get_MANUELL_PREIS_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MANUELL_PREIS_EK");
	}

	public String get_MANUELL_PREIS_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MANUELL_PREIS_EK",cNotNullValue);
	}

	public String get_MANUELL_PREIS_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MANUELL_PREIS_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MANUELL_PREIS_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MANUELL_PREIS_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MANUELL_PREIS_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS_EK", calNewValueFormated);
	}
		public boolean is_MANUELL_PREIS_EK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MANUELL_PREIS_EK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MANUELL_PREIS_EK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MANUELL_PREIS_EK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_MANUELL_PREIS_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("MANUELL_PREIS_VK");
	}

	public String get_MANUELL_PREIS_VK_cF() throws myException
	{
		return this.get_FormatedValue("MANUELL_PREIS_VK");	
	}

	public String get_MANUELL_PREIS_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MANUELL_PREIS_VK");
	}

	public String get_MANUELL_PREIS_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MANUELL_PREIS_VK",cNotNullValue);
	}

	public String get_MANUELL_PREIS_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MANUELL_PREIS_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MANUELL_PREIS_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MANUELL_PREIS_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MANUELL_PREIS_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MANUELL_PREIS_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MANUELL_PREIS_VK", calNewValueFormated);
	}
		public boolean is_MANUELL_PREIS_VK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MANUELL_PREIS_VK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MANUELL_PREIS_VK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MANUELL_PREIS_VK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_MENGE_ABLADEN_KO_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_ABLADEN_KO");
	}

	public String get_MENGE_ABLADEN_KO_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_ABLADEN_KO");	
	}

	public String get_MENGE_ABLADEN_KO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_ABLADEN_KO");
	}

	public String get_MENGE_ABLADEN_KO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_ABLADEN_KO",cNotNullValue);
	}

	public String get_MENGE_ABLADEN_KO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_ABLADEN_KO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_ABLADEN_KO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_ABLADEN_KO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_ABLADEN_KO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_ABLADEN_KO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_ABLADEN_KO", calNewValueFormated);
	}
		public Double get_MENGE_ABLADEN_KO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_ABLADEN_KO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_ABLADEN_KO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_ABLADEN_KO").getDoubleValue();
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
	public String get_MENGE_ABLADEN_KO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_ABLADEN_KO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_MENGE_ABLADEN_KO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_ABLADEN_KO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_MENGE_ABLADEN_KO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_ABLADEN_KO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_ABLADEN_KO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_ABLADEN_KO").getBigDecimalValue();
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
	
	
	public String get_MENGE_AUFLADEN_KO_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_AUFLADEN_KO");
	}

	public String get_MENGE_AUFLADEN_KO_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_AUFLADEN_KO");	
	}

	public String get_MENGE_AUFLADEN_KO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_AUFLADEN_KO");
	}

	public String get_MENGE_AUFLADEN_KO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_AUFLADEN_KO",cNotNullValue);
	}

	public String get_MENGE_AUFLADEN_KO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_AUFLADEN_KO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_AUFLADEN_KO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_AUFLADEN_KO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_AUFLADEN_KO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_AUFLADEN_KO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_AUFLADEN_KO", calNewValueFormated);
	}
		public Double get_MENGE_AUFLADEN_KO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_AUFLADEN_KO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_AUFLADEN_KO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_AUFLADEN_KO").getDoubleValue();
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
	public String get_MENGE_AUFLADEN_KO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_AUFLADEN_KO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_MENGE_AUFLADEN_KO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_AUFLADEN_KO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_MENGE_AUFLADEN_KO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_AUFLADEN_KO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_AUFLADEN_KO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_AUFLADEN_KO").getBigDecimalValue();
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
	
	
	public String get_MENGE_VORGABE_KO_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_VORGABE_KO");
	}

	public String get_MENGE_VORGABE_KO_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_VORGABE_KO");	
	}

	public String get_MENGE_VORGABE_KO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_VORGABE_KO");
	}

	public String get_MENGE_VORGABE_KO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_VORGABE_KO",cNotNullValue);
	}

	public String get_MENGE_VORGABE_KO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_VORGABE_KO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_VORGABE_KO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_VORGABE_KO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_VORGABE_KO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_VORGABE_KO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_VORGABE_KO", calNewValueFormated);
	}
		public Double get_MENGE_VORGABE_KO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_VORGABE_KO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_VORGABE_KO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_VORGABE_KO").getDoubleValue();
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
	public String get_MENGE_VORGABE_KO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_VORGABE_KO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_MENGE_VORGABE_KO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_VORGABE_KO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_MENGE_VORGABE_KO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_VORGABE_KO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_VORGABE_KO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_VORGABE_KO").getBigDecimalValue();
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
		public String get_NOTIFIKATION_NR_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("NOTIFIKATION_NR_EK");
	}

	public String get_NOTIFIKATION_NR_EK_cF() throws myException
	{
		return this.get_FormatedValue("NOTIFIKATION_NR_EK");	
	}

	public String get_NOTIFIKATION_NR_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NOTIFIKATION_NR_EK");
	}

	public String get_NOTIFIKATION_NR_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NOTIFIKATION_NR_EK",cNotNullValue);
	}

	public String get_NOTIFIKATION_NR_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NOTIFIKATION_NR_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NOTIFIKATION_NR_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NOTIFIKATION_NR_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NOTIFIKATION_NR_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NOTIFIKATION_NR_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NOTIFIKATION_NR_EK", calNewValueFormated);
	}
		public String get_OEFFNUNGSZEITEN_ABN_cUF() throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN_ABN");
	}

	public String get_OEFFNUNGSZEITEN_ABN_cF() throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN_ABN");	
	}

	public String get_OEFFNUNGSZEITEN_ABN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OEFFNUNGSZEITEN_ABN");
	}

	public String get_OEFFNUNGSZEITEN_ABN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN_ABN",cNotNullValue);
	}

	public String get_OEFFNUNGSZEITEN_ABN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN_ABN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN_ABN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_ABN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_ABN", calNewValueFormated);
	}
		public String get_OEFFNUNGSZEITEN_LIEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN_LIEF");
	}

	public String get_OEFFNUNGSZEITEN_LIEF_cF() throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN_LIEF");	
	}

	public String get_OEFFNUNGSZEITEN_LIEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OEFFNUNGSZEITEN_LIEF");
	}

	public String get_OEFFNUNGSZEITEN_LIEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN_LIEF",cNotNullValue);
	}

	public String get_OEFFNUNGSZEITEN_LIEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN_LIEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN_LIEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_LIEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN_LIEF", calNewValueFormated);
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
		public String get_ORDNUNGSNUMMER_CMR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORDNUNGSNUMMER_CMR");
	}

	public String get_ORDNUNGSNUMMER_CMR_cF() throws myException
	{
		return this.get_FormatedValue("ORDNUNGSNUMMER_CMR");	
	}

	public String get_ORDNUNGSNUMMER_CMR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORDNUNGSNUMMER_CMR");
	}

	public String get_ORDNUNGSNUMMER_CMR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORDNUNGSNUMMER_CMR",cNotNullValue);
	}

	public String get_ORDNUNGSNUMMER_CMR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORDNUNGSNUMMER_CMR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORDNUNGSNUMMER_CMR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORDNUNGSNUMMER_CMR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORDNUNGSNUMMER_CMR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORDNUNGSNUMMER_CMR", calNewValueFormated);
	}
		public String get_POSTENNUMMER_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSTENNUMMER_EK");
	}

	public String get_POSTENNUMMER_EK_cF() throws myException
	{
		return this.get_FormatedValue("POSTENNUMMER_EK");	
	}

	public String get_POSTENNUMMER_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSTENNUMMER_EK");
	}

	public String get_POSTENNUMMER_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSTENNUMMER_EK",cNotNullValue);
	}

	public String get_POSTENNUMMER_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSTENNUMMER_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSTENNUMMER_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSTENNUMMER_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSTENNUMMER_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER_EK", calNewValueFormated);
	}
		public String get_POSTENNUMMER_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSTENNUMMER_VK");
	}

	public String get_POSTENNUMMER_VK_cF() throws myException
	{
		return this.get_FormatedValue("POSTENNUMMER_VK");	
	}

	public String get_POSTENNUMMER_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSTENNUMMER_VK");
	}

	public String get_POSTENNUMMER_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSTENNUMMER_VK",cNotNullValue);
	}

	public String get_POSTENNUMMER_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSTENNUMMER_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSTENNUMMER_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSTENNUMMER_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSTENNUMMER_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSTENNUMMER_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSTENNUMMER_VK", calNewValueFormated);
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
		public String get_PRUEFUNG_ABLADEMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_ABLADEMENGE");
	}

	public String get_PRUEFUNG_ABLADEMENGE_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_ABLADEMENGE");	
	}

	public String get_PRUEFUNG_ABLADEMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_ABLADEMENGE");
	}

	public String get_PRUEFUNG_ABLADEMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_ABLADEMENGE",cNotNullValue);
	}

	public String get_PRUEFUNG_ABLADEMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_ABLADEMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_ABLADEMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE", calNewValueFormated);
	}
		public boolean is_PRUEFUNG_ABLADEMENGE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_ABLADEMENGE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRUEFUNG_ABLADEMENGE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_ABLADEMENGE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRUEFUNG_ABLADEMENGE_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_ABLADEMENGE_AM");
	}

	public String get_PRUEFUNG_ABLADEMENGE_AM_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_ABLADEMENGE_AM");	
	}

	public String get_PRUEFUNG_ABLADEMENGE_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_ABLADEMENGE_AM");
	}

	public String get_PRUEFUNG_ABLADEMENGE_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_ABLADEMENGE_AM",cNotNullValue);
	}

	public String get_PRUEFUNG_ABLADEMENGE_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_ABLADEMENGE_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_AM", calNewValueFormated);
	}
		public String get_PRUEFUNG_ABLADEMENGE_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_ABLADEMENGE_VON");
	}

	public String get_PRUEFUNG_ABLADEMENGE_VON_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_ABLADEMENGE_VON");	
	}

	public String get_PRUEFUNG_ABLADEMENGE_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_ABLADEMENGE_VON");
	}

	public String get_PRUEFUNG_ABLADEMENGE_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_ABLADEMENGE_VON",cNotNullValue);
	}

	public String get_PRUEFUNG_ABLADEMENGE_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_ABLADEMENGE_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_ABLADEMENGE_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_ABLADEMENGE_VON", calNewValueFormated);
	}
		public String get_PRUEFUNG_EK_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_EK_PREIS");
	}

	public String get_PRUEFUNG_EK_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_EK_PREIS");	
	}

	public String get_PRUEFUNG_EK_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_EK_PREIS");
	}

	public String get_PRUEFUNG_EK_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_EK_PREIS",cNotNullValue);
	}

	public String get_PRUEFUNG_EK_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_EK_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_EK_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_EK_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS", calNewValueFormated);
	}
		public boolean is_PRUEFUNG_EK_PREIS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_EK_PREIS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRUEFUNG_EK_PREIS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_EK_PREIS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRUEFUNG_EK_PREIS_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_EK_PREIS_AM");
	}

	public String get_PRUEFUNG_EK_PREIS_AM_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_EK_PREIS_AM");	
	}

	public String get_PRUEFUNG_EK_PREIS_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_EK_PREIS_AM");
	}

	public String get_PRUEFUNG_EK_PREIS_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_EK_PREIS_AM",cNotNullValue);
	}

	public String get_PRUEFUNG_EK_PREIS_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_EK_PREIS_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_EK_PREIS_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_AM", calNewValueFormated);
	}
		public String get_PRUEFUNG_EK_PREIS_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_EK_PREIS_VON");
	}

	public String get_PRUEFUNG_EK_PREIS_VON_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_EK_PREIS_VON");	
	}

	public String get_PRUEFUNG_EK_PREIS_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_EK_PREIS_VON");
	}

	public String get_PRUEFUNG_EK_PREIS_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_EK_PREIS_VON",cNotNullValue);
	}

	public String get_PRUEFUNG_EK_PREIS_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_EK_PREIS_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_EK_PREIS_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_EK_PREIS_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_EK_PREIS_VON", calNewValueFormated);
	}
		public String get_PRUEFUNG_LADEMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_LADEMENGE");
	}

	public String get_PRUEFUNG_LADEMENGE_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_LADEMENGE");	
	}

	public String get_PRUEFUNG_LADEMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_LADEMENGE");
	}

	public String get_PRUEFUNG_LADEMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_LADEMENGE",cNotNullValue);
	}

	public String get_PRUEFUNG_LADEMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_LADEMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_LADEMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_LADEMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE", calNewValueFormated);
	}
		public boolean is_PRUEFUNG_LADEMENGE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_LADEMENGE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRUEFUNG_LADEMENGE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_LADEMENGE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRUEFUNG_LADEMENGE_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_LADEMENGE_AM");
	}

	public String get_PRUEFUNG_LADEMENGE_AM_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_LADEMENGE_AM");	
	}

	public String get_PRUEFUNG_LADEMENGE_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_LADEMENGE_AM");
	}

	public String get_PRUEFUNG_LADEMENGE_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_LADEMENGE_AM",cNotNullValue);
	}

	public String get_PRUEFUNG_LADEMENGE_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_LADEMENGE_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_LADEMENGE_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_AM", calNewValueFormated);
	}
		public String get_PRUEFUNG_LADEMENGE_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_LADEMENGE_VON");
	}

	public String get_PRUEFUNG_LADEMENGE_VON_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_LADEMENGE_VON");	
	}

	public String get_PRUEFUNG_LADEMENGE_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_LADEMENGE_VON");
	}

	public String get_PRUEFUNG_LADEMENGE_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_LADEMENGE_VON",cNotNullValue);
	}

	public String get_PRUEFUNG_LADEMENGE_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_LADEMENGE_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_LADEMENGE_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_LADEMENGE_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_LADEMENGE_VON", calNewValueFormated);
	}
		public String get_PRUEFUNG_VK_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_VK_PREIS");
	}

	public String get_PRUEFUNG_VK_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_VK_PREIS");	
	}

	public String get_PRUEFUNG_VK_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_VK_PREIS");
	}

	public String get_PRUEFUNG_VK_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_VK_PREIS",cNotNullValue);
	}

	public String get_PRUEFUNG_VK_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_VK_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_VK_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_VK_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS", calNewValueFormated);
	}
		public boolean is_PRUEFUNG_VK_PREIS_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_VK_PREIS_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRUEFUNG_VK_PREIS_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRUEFUNG_VK_PREIS_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_PRUEFUNG_VK_PREIS_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_VK_PREIS_AM");
	}

	public String get_PRUEFUNG_VK_PREIS_AM_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_VK_PREIS_AM");	
	}

	public String get_PRUEFUNG_VK_PREIS_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_VK_PREIS_AM");
	}

	public String get_PRUEFUNG_VK_PREIS_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_VK_PREIS_AM",cNotNullValue);
	}

	public String get_PRUEFUNG_VK_PREIS_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_VK_PREIS_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_VK_PREIS_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_AM", calNewValueFormated);
	}
		public String get_PRUEFUNG_VK_PREIS_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_VK_PREIS_VON");
	}

	public String get_PRUEFUNG_VK_PREIS_VON_cF() throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_VK_PREIS_VON");	
	}

	public String get_PRUEFUNG_VK_PREIS_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRUEFUNG_VK_PREIS_VON");
	}

	public String get_PRUEFUNG_VK_PREIS_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRUEFUNG_VK_PREIS_VON",cNotNullValue);
	}

	public String get_PRUEFUNG_VK_PREIS_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRUEFUNG_VK_PREIS_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRUEFUNG_VK_PREIS_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRUEFUNG_VK_PREIS_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRUEFUNG_VK_PREIS_VON", calNewValueFormated);
	}
		public String get_ROUTING_DISTANCE_KM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ROUTING_DISTANCE_KM");
	}

	public String get_ROUTING_DISTANCE_KM_cF() throws myException
	{
		return this.get_FormatedValue("ROUTING_DISTANCE_KM");	
	}

	public String get_ROUTING_DISTANCE_KM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ROUTING_DISTANCE_KM");
	}

	public String get_ROUTING_DISTANCE_KM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ROUTING_DISTANCE_KM",cNotNullValue);
	}

	public String get_ROUTING_DISTANCE_KM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ROUTING_DISTANCE_KM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ROUTING_DISTANCE_KM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ROUTING_DISTANCE_KM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ROUTING_DISTANCE_KM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_DISTANCE_KM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROUTING_DISTANCE_KM", calNewValueFormated);
	}
		public Double get_ROUTING_DISTANCE_KM_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ROUTING_DISTANCE_KM").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ROUTING_DISTANCE_KM_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ROUTING_DISTANCE_KM").getDoubleValue();
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
	public String get_ROUTING_DISTANCE_KM_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ROUTING_DISTANCE_KM_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ROUTING_DISTANCE_KM_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ROUTING_DISTANCE_KM_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ROUTING_DISTANCE_KM_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ROUTING_DISTANCE_KM").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ROUTING_DISTANCE_KM_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ROUTING_DISTANCE_KM").getBigDecimalValue();
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
	
	
	public String get_ROUTING_TIME_MINUTES_cUF() throws myException
	{
		return this.get_UnFormatedValue("ROUTING_TIME_MINUTES");
	}

	public String get_ROUTING_TIME_MINUTES_cF() throws myException
	{
		return this.get_FormatedValue("ROUTING_TIME_MINUTES");	
	}

	public String get_ROUTING_TIME_MINUTES_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ROUTING_TIME_MINUTES");
	}

	public String get_ROUTING_TIME_MINUTES_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ROUTING_TIME_MINUTES",cNotNullValue);
	}

	public String get_ROUTING_TIME_MINUTES_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ROUTING_TIME_MINUTES",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ROUTING_TIME_MINUTES", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ROUTING_TIME_MINUTES(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ROUTING_TIME_MINUTES", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ROUTING_TIME_MINUTES_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ROUTING_TIME_MINUTES", calNewValueFormated);
	}
		public Long get_ROUTING_TIME_MINUTES_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ROUTING_TIME_MINUTES").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ROUTING_TIME_MINUTES_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ROUTING_TIME_MINUTES").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ROUTING_TIME_MINUTES_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ROUTING_TIME_MINUTES").getDoubleValue();
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
	public String get_ROUTING_TIME_MINUTES_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ROUTING_TIME_MINUTES_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ROUTING_TIME_MINUTES_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ROUTING_TIME_MINUTES_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ROUTING_TIME_MINUTES_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ROUTING_TIME_MINUTES").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ROUTING_TIME_MINUTES_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ROUTING_TIME_MINUTES").getBigDecimalValue();
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
	
	
	public String get_SCHLIESSE_FUHRE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHLIESSE_FUHRE");
	}

	public String get_SCHLIESSE_FUHRE_cF() throws myException
	{
		return this.get_FormatedValue("SCHLIESSE_FUHRE");	
	}

	public String get_SCHLIESSE_FUHRE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHLIESSE_FUHRE");
	}

	public String get_SCHLIESSE_FUHRE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHLIESSE_FUHRE",cNotNullValue);
	}

	public String get_SCHLIESSE_FUHRE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHLIESSE_FUHRE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHLIESSE_FUHRE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHLIESSE_FUHRE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHLIESSE_FUHRE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE", calNewValueFormated);
	}
		public boolean is_SCHLIESSE_FUHRE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SCHLIESSE_FUHRE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SCHLIESSE_FUHRE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SCHLIESSE_FUHRE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SCHLIESSE_FUHRE_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHLIESSE_FUHRE_AM");
	}

	public String get_SCHLIESSE_FUHRE_AM_cF() throws myException
	{
		return this.get_FormatedValue("SCHLIESSE_FUHRE_AM");	
	}

	public String get_SCHLIESSE_FUHRE_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHLIESSE_FUHRE_AM");
	}

	public String get_SCHLIESSE_FUHRE_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHLIESSE_FUHRE_AM",cNotNullValue);
	}

	public String get_SCHLIESSE_FUHRE_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHLIESSE_FUHRE_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHLIESSE_FUHRE_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHLIESSE_FUHRE_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_AM", calNewValueFormated);
	}
		public String get_SCHLIESSE_FUHRE_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHLIESSE_FUHRE_VON");
	}

	public String get_SCHLIESSE_FUHRE_VON_cF() throws myException
	{
		return this.get_FormatedValue("SCHLIESSE_FUHRE_VON");	
	}

	public String get_SCHLIESSE_FUHRE_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHLIESSE_FUHRE_VON");
	}

	public String get_SCHLIESSE_FUHRE_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHLIESSE_FUHRE_VON",cNotNullValue);
	}

	public String get_SCHLIESSE_FUHRE_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHLIESSE_FUHRE_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHLIESSE_FUHRE_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHLIESSE_FUHRE_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHLIESSE_FUHRE_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHLIESSE_FUHRE_VON", calNewValueFormated);
	}
		public String get_SORTIERUNG_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTIERUNG_FP");
	}

	public String get_SORTIERUNG_FP_cF() throws myException
	{
		return this.get_FormatedValue("SORTIERUNG_FP");	
	}

	public String get_SORTIERUNG_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTIERUNG_FP");
	}

	public String get_SORTIERUNG_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTIERUNG_FP",cNotNullValue);
	}

	public String get_SORTIERUNG_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTIERUNG_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTIERUNG_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTIERUNG_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTIERUNG_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", calNewValueFormated);
	}
		public Long get_SORTIERUNG_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTIERUNG_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTIERUNG_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTIERUNG_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTIERUNG_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTIERUNG_FP").getDoubleValue();
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
	public String get_SORTIERUNG_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTIERUNG_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_SORTIERUNG_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTIERUNG_FP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_SORTIERUNG_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTIERUNG_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTIERUNG_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTIERUNG_FP").getBigDecimalValue();
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
	
	
	public String get_SPEICHERN_TROTZ_INKONSISTENZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("SPEICHERN_TROTZ_INKONSISTENZ");
	}

	public String get_SPEICHERN_TROTZ_INKONSISTENZ_cF() throws myException
	{
		return this.get_FormatedValue("SPEICHERN_TROTZ_INKONSISTENZ");	
	}

	public String get_SPEICHERN_TROTZ_INKONSISTENZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SPEICHERN_TROTZ_INKONSISTENZ");
	}

	public String get_SPEICHERN_TROTZ_INKONSISTENZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SPEICHERN_TROTZ_INKONSISTENZ",cNotNullValue);
	}

	public String get_SPEICHERN_TROTZ_INKONSISTENZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SPEICHERN_TROTZ_INKONSISTENZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPEICHERN_TROTZ_INKONSISTENZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SPEICHERN_TROTZ_INKONSISTENZ", calNewValueFormated);
	}
		public boolean is_SPEICHERN_TROTZ_INKONSISTENZ_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SPEICHERN_TROTZ_INKONSISTENZ_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SPEICHERN_TROTZ_INKONSISTENZ_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SPEICHERN_TROTZ_INKONSISTENZ_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STATUS_BUCHUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATUS_BUCHUNG");
	}

	public String get_STATUS_BUCHUNG_cF() throws myException
	{
		return this.get_FormatedValue("STATUS_BUCHUNG");	
	}

	public String get_STATUS_BUCHUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATUS_BUCHUNG");
	}

	public String get_STATUS_BUCHUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATUS_BUCHUNG",cNotNullValue);
	}

	public String get_STATUS_BUCHUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATUS_BUCHUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATUS_BUCHUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATUS_BUCHUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATUS_BUCHUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_BUCHUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_BUCHUNG", calNewValueFormated);
	}
		public Long get_STATUS_BUCHUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("STATUS_BUCHUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_STATUS_BUCHUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STATUS_BUCHUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STATUS_BUCHUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STATUS_BUCHUNG").getDoubleValue();
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
	public String get_STATUS_BUCHUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STATUS_BUCHUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_STATUS_BUCHUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STATUS_BUCHUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_STATUS_BUCHUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STATUS_BUCHUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STATUS_BUCHUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STATUS_BUCHUNG").getBigDecimalValue();
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
	
	
	public String get_STEUERSATZ_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_EK");
	}

	public String get_STEUERSATZ_EK_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_EK");	
	}

	public String get_STEUERSATZ_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ_EK");
	}

	public String get_STEUERSATZ_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_EK",cNotNullValue);
	}

	public String get_STEUERSATZ_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_EK", calNewValueFormated);
	}
		public Double get_STEUERSATZ_EK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_EK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_EK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_EK").getDoubleValue();
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
	public String get_STEUERSATZ_EK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_STEUERSATZ_EK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_EK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_STEUERSATZ_EK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_EK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_EK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_EK").getBigDecimalValue();
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
	
	
	public String get_STEUERSATZ_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_VK");
	}

	public String get_STEUERSATZ_VK_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_VK");	
	}

	public String get_STEUERSATZ_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ_VK");
	}

	public String get_STEUERSATZ_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_VK",cNotNullValue);
	}

	public String get_STEUERSATZ_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_VK", calNewValueFormated);
	}
		public Double get_STEUERSATZ_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_VK").getDoubleValue();
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
	public String get_STEUERSATZ_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_STEUERSATZ_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_STEUERSATZ_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_VK").getBigDecimalValue();
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
	
	
	public String get_STORNO_GRUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNO_GRUND");
	}

	public String get_STORNO_GRUND_cF() throws myException
	{
		return this.get_FormatedValue("STORNO_GRUND");	
	}

	public String get_STORNO_GRUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNO_GRUND");
	}

	public String get_STORNO_GRUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNO_GRUND",cNotNullValue);
	}

	public String get_STORNO_GRUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNO_GRUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNO_GRUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNO_GRUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNO_GRUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO_GRUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO_GRUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO_GRUND", calNewValueFormated);
	}
		public String get_STORNO_KUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNO_KUERZEL");
	}

	public String get_STORNO_KUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("STORNO_KUERZEL");	
	}

	public String get_STORNO_KUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNO_KUERZEL");
	}

	public String get_STORNO_KUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNO_KUERZEL",cNotNullValue);
	}

	public String get_STORNO_KUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNO_KUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNO_KUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNO_KUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNO_KUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_KUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO_KUERZEL", calNewValueFormated);
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
	
	
	public String get_TAETIGKEIT_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT_FP");
	}

	public String get_TAETIGKEIT_FP_cF() throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT_FP");	
	}

	public String get_TAETIGKEIT_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TAETIGKEIT_FP");
	}

	public String get_TAETIGKEIT_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT_FP",cNotNullValue);
	}

	public String get_TAETIGKEIT_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TAETIGKEIT_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TAETIGKEIT_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", calNewValueFormated);
	}
		public String get_TEL_ABNEHMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEL_ABNEHMER");
	}

	public String get_TEL_ABNEHMER_cF() throws myException
	{
		return this.get_FormatedValue("TEL_ABNEHMER");	
	}

	public String get_TEL_ABNEHMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEL_ABNEHMER");
	}

	public String get_TEL_ABNEHMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEL_ABNEHMER",cNotNullValue);
	}

	public String get_TEL_ABNEHMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEL_ABNEHMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEL_ABNEHMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEL_ABNEHMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEL_ABNEHMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ABNEHMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_ABNEHMER", calNewValueFormated);
	}
		public String get_TEL_LIEFERANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEL_LIEFERANT");
	}

	public String get_TEL_LIEFERANT_cF() throws myException
	{
		return this.get_FormatedValue("TEL_LIEFERANT");	
	}

	public String get_TEL_LIEFERANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEL_LIEFERANT");
	}

	public String get_TEL_LIEFERANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEL_LIEFERANT",cNotNullValue);
	}

	public String get_TEL_LIEFERANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEL_LIEFERANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEL_LIEFERANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEL_LIEFERANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEL_LIEFERANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_LIEFERANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_LIEFERANT", calNewValueFormated);
	}
		public String get_TP_VERANTWORTUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("TP_VERANTWORTUNG");
	}

	public String get_TP_VERANTWORTUNG_cF() throws myException
	{
		return this.get_FormatedValue("TP_VERANTWORTUNG");	
	}

	public String get_TP_VERANTWORTUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TP_VERANTWORTUNG");
	}

	public String get_TP_VERANTWORTUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TP_VERANTWORTUNG",cNotNullValue);
	}

	public String get_TP_VERANTWORTUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TP_VERANTWORTUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TP_VERANTWORTUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TP_VERANTWORTUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TP_VERANTWORTUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", calNewValueFormated);
	}
		public String get_TRANSIT_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_EK");
	}

	public String get_TRANSIT_EK_cF() throws myException
	{
		return this.get_FormatedValue("TRANSIT_EK");	
	}

	public String get_TRANSIT_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSIT_EK");
	}

	public String get_TRANSIT_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_EK",cNotNullValue);
	}

	public String get_TRANSIT_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSIT_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSIT_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSIT_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSIT_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSIT_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_EK", calNewValueFormated);
	}
		public boolean is_TRANSIT_EK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_EK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSIT_EK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_EK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_TRANSIT_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_VK");
	}

	public String get_TRANSIT_VK_cF() throws myException
	{
		return this.get_FormatedValue("TRANSIT_VK");	
	}

	public String get_TRANSIT_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSIT_VK");
	}

	public String get_TRANSIT_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_VK",cNotNullValue);
	}

	public String get_TRANSIT_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSIT_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSIT_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSIT_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSIT_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSIT_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_VK", calNewValueFormated);
	}
		public boolean is_TRANSIT_VK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_VK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSIT_VK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_VK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_TRANSPORTKENNZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTKENNZEICHEN");
	}

	public String get_TRANSPORTKENNZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("TRANSPORTKENNZEICHEN");	
	}

	public String get_TRANSPORTKENNZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSPORTKENNZEICHEN");
	}

	public String get_TRANSPORTKENNZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTKENNZEICHEN",cNotNullValue);
	}

	public String get_TRANSPORTKENNZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSPORTKENNZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", calNewValueFormated);
	}
		public String get_TRANSPORTMITTEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTMITTEL");
	}

	public String get_TRANSPORTMITTEL_cF() throws myException
	{
		return this.get_FormatedValue("TRANSPORTMITTEL");	
	}

	public String get_TRANSPORTMITTEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSPORTMITTEL");
	}

	public String get_TRANSPORTMITTEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTMITTEL",cNotNullValue);
	}

	public String get_TRANSPORTMITTEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSPORTMITTEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", calNewValueFormated);
	}
		public String get_TYP_STRECKE_LAGER_MIXED_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYP_STRECKE_LAGER_MIXED");
	}

	public String get_TYP_STRECKE_LAGER_MIXED_cF() throws myException
	{
		return this.get_FormatedValue("TYP_STRECKE_LAGER_MIXED");	
	}

	public String get_TYP_STRECKE_LAGER_MIXED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYP_STRECKE_LAGER_MIXED");
	}

	public String get_TYP_STRECKE_LAGER_MIXED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYP_STRECKE_LAGER_MIXED",cNotNullValue);
	}

	public String get_TYP_STRECKE_LAGER_MIXED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYP_STRECKE_LAGER_MIXED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_STRECKE_LAGER_MIXED", calNewValueFormated);
	}
		public Long get_TYP_STRECKE_LAGER_MIXED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("TYP_STRECKE_LAGER_MIXED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_TYP_STRECKE_LAGER_MIXED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("TYP_STRECKE_LAGER_MIXED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_TYP_STRECKE_LAGER_MIXED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("TYP_STRECKE_LAGER_MIXED").getDoubleValue();
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
	public String get_TYP_STRECKE_LAGER_MIXED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TYP_STRECKE_LAGER_MIXED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_TYP_STRECKE_LAGER_MIXED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TYP_STRECKE_LAGER_MIXED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_TYP_STRECKE_LAGER_MIXED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("TYP_STRECKE_LAGER_MIXED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_TYP_STRECKE_LAGER_MIXED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("TYP_STRECKE_LAGER_MIXED").getBigDecimalValue();
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
	
	
	public String get_VK_KONTRAKTNR_ZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("VK_KONTRAKTNR_ZUSATZ");
	}

	public String get_VK_KONTRAKTNR_ZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("VK_KONTRAKTNR_ZUSATZ");	
	}

	public String get_VK_KONTRAKTNR_ZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VK_KONTRAKTNR_ZUSATZ");
	}

	public String get_VK_KONTRAKTNR_ZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VK_KONTRAKTNR_ZUSATZ",cNotNullValue);
	}

	public String get_VK_KONTRAKTNR_ZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VK_KONTRAKTNR_ZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VK_KONTRAKTNR_ZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VK_KONTRAKTNR_ZUSATZ", calNewValueFormated);
	}
		public String get_WIEGEKARTENKENNER_ABLADEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER_ABLADEN");
	}

	public String get_WIEGEKARTENKENNER_ABLADEN_cF() throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER_ABLADEN");	
	}

	public String get_WIEGEKARTENKENNER_ABLADEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEGEKARTENKENNER_ABLADEN");
	}

	public String get_WIEGEKARTENKENNER_ABLADEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER_ABLADEN",cNotNullValue);
	}

	public String get_WIEGEKARTENKENNER_ABLADEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER_ABLADEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_ABLADEN", calNewValueFormated);
	}
		public String get_WIEGEKARTENKENNER_LADEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER_LADEN");
	}

	public String get_WIEGEKARTENKENNER_LADEN_cF() throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER_LADEN");	
	}

	public String get_WIEGEKARTENKENNER_LADEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEGEKARTENKENNER_LADEN");
	}

	public String get_WIEGEKARTENKENNER_LADEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER_LADEN",cNotNullValue);
	}

	public String get_WIEGEKARTENKENNER_LADEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER_LADEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER_LADEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_LADEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER_LADEN", calNewValueFormated);
	}
		public String get_ZEITANGABE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEITANGABE");
	}

	public String get_ZEITANGABE_cF() throws myException
	{
		return this.get_FormatedValue("ZEITANGABE");	
	}

	public String get_ZEITANGABE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEITANGABE");
	}

	public String get_ZEITANGABE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEITANGABE",cNotNullValue);
	}

	public String get_ZEITANGABE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEITANGABE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEITANGABE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEITANGABE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEITANGABE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEITANGABE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITANGABE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITANGABE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITANGABE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITANGABE", calNewValueFormated);
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
	put("ABGESCHLOSSEN",MyRECORD.DATATYPES.YESNO);
	put("ABLADEMENGE_RECHNUNG",MyRECORD.DATATYPES.YESNO);
	put("ABLADEN_BRUTTO",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ABLADEN_TARA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ABZUG_ABLADEMENGE_ABN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ABZUG_LADEMENGE_LIEF",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ALTE_LIEFERSCHEIN_NR",MyRECORD.DATATYPES.TEXT);
	put("ALT_WIRD_NICHT_GEBUCHT",MyRECORD.DATATYPES.YESNO);
	put("ANHAENGERKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("ANR1_EK",MyRECORD.DATATYPES.TEXT);
	put("ANR1_VK",MyRECORD.DATATYPES.TEXT);
	put("ANR2_EK",MyRECORD.DATATYPES.TEXT);
	put("ANR2_VK",MyRECORD.DATATYPES.TEXT);
	put("ANRUFDATUM_FP",MyRECORD.DATATYPES.DATE);
	put("ANRUFER_FP",MyRECORD.DATATYPES.TEXT);
	put("ANTEIL_ABLADEMENGE_ABN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANTEIL_ABLADEMENGE_LIEF",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANTEIL_LADEMENGE_ABN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANTEIL_LADEMENGE_LIEF",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANTEIL_PLANMENGE_ABN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANTEIL_PLANMENGE_LIEF",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANZAHL_CONTAINER_FP",MyRECORD.DATATYPES.NUMBER);
	put("ARTBEZ1_EK",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ1_VK",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ2_EK",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ2_VK",MyRECORD.DATATYPES.TEXT);
	put("AUFLADEN_BRUTTO",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("AUFLADEN_TARA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("AVV_AUSSTELLUNG_DATUM",MyRECORD.DATATYPES.DATE);
	put("A_HAUSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("A_LAENDERCODE",MyRECORD.DATATYPES.TEXT);
	put("A_NAME1",MyRECORD.DATATYPES.TEXT);
	put("A_NAME2",MyRECORD.DATATYPES.TEXT);
	put("A_NAME3",MyRECORD.DATATYPES.TEXT);
	put("A_ORT",MyRECORD.DATATYPES.TEXT);
	put("A_ORTZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("A_PLZ",MyRECORD.DATATYPES.TEXT);
	put("A_STRASSE",MyRECORD.DATATYPES.TEXT);
	put("BASEL_CODE",MyRECORD.DATATYPES.TEXT);
	put("BASEL_NOTIZ",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_FUER_KUNDE",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_SACHBEARBEITER",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_START_FP",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_ZIEL_FP",MyRECORD.DATATYPES.TEXT);
	put("BESTELLNUMMER_EK",MyRECORD.DATATYPES.TEXT);
	put("BESTELLNUMMER_VK",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSNR_FUHRE",MyRECORD.DATATYPES.TEXT);
	put("DATUM_ABHOLUNG",MyRECORD.DATATYPES.DATE);
	put("DATUM_ABHOLUNG_ENDE",MyRECORD.DATATYPES.DATE);
	put("DATUM_ABLADEN",MyRECORD.DATATYPES.DATE);
	put("DATUM_ANLIEFERUNG",MyRECORD.DATATYPES.DATE);
	put("DATUM_ANLIEFERUNG_ENDE",MyRECORD.DATATYPES.DATE);
	put("DATUM_AUFLADEN",MyRECORD.DATATYPES.DATE);
	put("DAT_FAHRPLAN_FP",MyRECORD.DATATYPES.DATE);
	put("DAT_VORGEMERKT_ENDE_FP",MyRECORD.DATATYPES.DATE);
	put("DAT_VORGEMERKT_FP",MyRECORD.DATATYPES.DATE);
	put("DELETED",MyRECORD.DATATYPES.YESNO);
	put("DEL_DATE",MyRECORD.DATATYPES.DATE);
	put("DEL_GRUND",MyRECORD.DATATYPES.TEXT);
	put("DEL_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("EAN_CODE_FP",MyRECORD.DATATYPES.TEXT);
	put("EINHEIT_MENGEN",MyRECORD.DATATYPES.TEXT);
	put("EINZELPREIS_EK",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EINZELPREIS_VK",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EK_KONTRAKTNR_ZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("EK_VK_SORTE_LOCK",MyRECORD.DATATYPES.YESNO);
	put("EK_VK_ZUORD_ZWANG",MyRECORD.DATATYPES.YESNO);
	put("EPREIS_RESULT_NETTO_MGE_EK",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EPREIS_RESULT_NETTO_MGE_VK",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ERFASSER_FP",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EUCODE",MyRECORD.DATATYPES.TEXT);
	put("EUNOTIZ",MyRECORD.DATATYPES.TEXT);
	put("EU_BLATT_MENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EU_BLATT_VOLUMEN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EU_STEUER_VERMERK_EK",MyRECORD.DATATYPES.TEXT);
	put("EU_STEUER_VERMERK_VK",MyRECORD.DATATYPES.TEXT);
	put("FAHRER_FP",MyRECORD.DATATYPES.TEXT);
	put("FAHRPLANGRUPPE_FP",MyRECORD.DATATYPES.NUMBER);
	put("FAHRT_ANFANG_FP",MyRECORD.DATATYPES.TEXT);
	put("FAHRT_ENDE_FP",MyRECORD.DATATYPES.TEXT);
	put("FAX_ABNEHMER",MyRECORD.DATATYPES.TEXT);
	put("FAX_LIEFERANT",MyRECORD.DATATYPES.TEXT);
	put("FUHRENGRUPPE",MyRECORD.DATATYPES.NUMBER);
	put("FUHRE_AUS_FAHRPLAN",MyRECORD.DATATYPES.YESNO);
	put("FUHRE_IST_UMGELEITET",MyRECORD.DATATYPES.YESNO);
	put("FUHRE_KOMPLETT",MyRECORD.DATATYPES.YESNO);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GELANGENSBESTAETIGUNG_ERHALTEN",MyRECORD.DATATYPES.YESNO);
	put("ID_ADRESSE_FREMDAUFTRAG",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER_START",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_SPEDITION",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_START",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ_EK",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ_VK",MyRECORD.DATATYPES.NUMBER);
	put("ID_CONTAINERTYP_FP",MyRECORD.DATATYPES.NUMBER);
	put("ID_EAK_CODE",MyRECORD.DATATYPES.NUMBER);
	put("ID_FAHRPLAN_ZEITANGABE",MyRECORD.DATATYPES.NUMBER);
	put("ID_HANDELSDEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MASCHINEN_ANH_FP",MyRECORD.DATATYPES.NUMBER);
	put("ID_MASCHINEN_LKW_FP",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_EK",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_VK",MyRECORD.DATATYPES.NUMBER);
	put("ID_UMA_KONTRAKT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VERARBEITUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_VERPACKUNGSART",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_KON_EK",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_KON_VK",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_STD_EK",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_STD_VK",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_SONDER",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE_ABN",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE_LIEF",MyRECORD.DATATYPES.NUMBER);
	put("INTRASTAT_MELD_IN",MyRECORD.DATATYPES.TEXT);
	put("INTRASTAT_MELD_OUT",MyRECORD.DATATYPES.TEXT);
	put("IST_GEPLANT_FP",MyRECORD.DATATYPES.YESNO);
	put("IST_STORNIERT",MyRECORD.DATATYPES.YESNO);
	put("KEIN_KONTRAKT_NOETIG",MyRECORD.DATATYPES.YESNO);
	put("KENNER_ALTE_SAETZE_FP",MyRECORD.DATATYPES.YESNO);
	put("LADEMENGE_GUTSCHRIFT",MyRECORD.DATATYPES.YESNO);
	put("LAENDERCODE_TRANSIT1",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_TRANSIT2",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_TRANSIT3",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERBED_ALTERNATIV_EK",MyRECORD.DATATYPES.TEXT);
	put("LIEFERBED_ALTERNATIV_VK",MyRECORD.DATATYPES.TEXT);
	put("LIEFERINFO_TPA",MyRECORD.DATATYPES.TEXT);
	put("L_HAUSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("L_LAENDERCODE",MyRECORD.DATATYPES.TEXT);
	put("L_NAME1",MyRECORD.DATATYPES.TEXT);
	put("L_NAME2",MyRECORD.DATATYPES.TEXT);
	put("L_NAME3",MyRECORD.DATATYPES.TEXT);
	put("L_ORT",MyRECORD.DATATYPES.TEXT);
	put("L_ORTZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("L_PLZ",MyRECORD.DATATYPES.TEXT);
	put("L_STRASSE",MyRECORD.DATATYPES.TEXT);
	put("MANUELL_PREIS_EK",MyRECORD.DATATYPES.YESNO);
	put("MANUELL_PREIS_VK",MyRECORD.DATATYPES.YESNO);
	put("MENGE_ABLADEN_KO",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_AUFLADEN_KO",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_VORGABE_KO",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("NATIONALER_ABFALL_CODE",MyRECORD.DATATYPES.TEXT);
	put("NOTIFIKATION_NR",MyRECORD.DATATYPES.TEXT);
	put("NOTIFIKATION_NR_EK",MyRECORD.DATATYPES.TEXT);
	put("OEFFNUNGSZEITEN_ABN",MyRECORD.DATATYPES.TEXT);
	put("OEFFNUNGSZEITEN_LIEF",MyRECORD.DATATYPES.TEXT);
	put("OHNE_ABRECHNUNG",MyRECORD.DATATYPES.YESNO);
	put("OHNE_AVV_VERTRAG_CHECK",MyRECORD.DATATYPES.YESNO);
	put("ORDNUNGSNUMMER_CMR",MyRECORD.DATATYPES.TEXT);
	put("POSTENNUMMER_EK",MyRECORD.DATATYPES.TEXT);
	put("POSTENNUMMER_VK",MyRECORD.DATATYPES.TEXT);
	put("PRINT_EU_AMTSBLATT",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_ABLADEMENGE",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_ABLADEMENGE_AM",MyRECORD.DATATYPES.DATE);
	put("PRUEFUNG_ABLADEMENGE_VON",MyRECORD.DATATYPES.TEXT);
	put("PRUEFUNG_EK_PREIS",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_EK_PREIS_AM",MyRECORD.DATATYPES.DATE);
	put("PRUEFUNG_EK_PREIS_VON",MyRECORD.DATATYPES.TEXT);
	put("PRUEFUNG_LADEMENGE",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_LADEMENGE_AM",MyRECORD.DATATYPES.DATE);
	put("PRUEFUNG_LADEMENGE_VON",MyRECORD.DATATYPES.TEXT);
	put("PRUEFUNG_VK_PREIS",MyRECORD.DATATYPES.YESNO);
	put("PRUEFUNG_VK_PREIS_AM",MyRECORD.DATATYPES.DATE);
	put("PRUEFUNG_VK_PREIS_VON",MyRECORD.DATATYPES.TEXT);
	put("ROUTING_DISTANCE_KM",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ROUTING_TIME_MINUTES",MyRECORD.DATATYPES.NUMBER);
	put("SCHLIESSE_FUHRE",MyRECORD.DATATYPES.YESNO);
	put("SCHLIESSE_FUHRE_AM",MyRECORD.DATATYPES.DATE);
	put("SCHLIESSE_FUHRE_VON",MyRECORD.DATATYPES.TEXT);
	put("SORTIERUNG_FP",MyRECORD.DATATYPES.NUMBER);
	put("SPEICHERN_TROTZ_INKONSISTENZ",MyRECORD.DATATYPES.YESNO);
	put("STATUS_BUCHUNG",MyRECORD.DATATYPES.NUMBER);
	put("STEUERSATZ_EK",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERSATZ_VK",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STORNO_GRUND",MyRECORD.DATATYPES.TEXT);
	put("STORNO_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TAETIGKEIT_FP",MyRECORD.DATATYPES.TEXT);
	put("TEL_ABNEHMER",MyRECORD.DATATYPES.TEXT);
	put("TEL_LIEFERANT",MyRECORD.DATATYPES.TEXT);
	put("TP_VERANTWORTUNG",MyRECORD.DATATYPES.TEXT);
	put("TRANSIT_EK",MyRECORD.DATATYPES.YESNO);
	put("TRANSIT_VK",MyRECORD.DATATYPES.YESNO);
	put("TRANSPORTKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("TRANSPORTMITTEL",MyRECORD.DATATYPES.TEXT);
	put("TYP_STRECKE_LAGER_MIXED",MyRECORD.DATATYPES.NUMBER);
	put("VK_KONTRAKTNR_ZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("WIEGEKARTENKENNER_ABLADEN",MyRECORD.DATATYPES.TEXT);
	put("WIEGEKARTENKENNER_LADEN",MyRECORD.DATATYPES.TEXT);
	put("ZEITANGABE",MyRECORD.DATATYPES.TEXT);
	put("ZEITSTEMPEL",MyRECORD.DATATYPES.TEXT);
	put("ZOLLTARIFNR",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_VPOS_TPA_FUHRE.HM_FIELDS;	
	}

}
