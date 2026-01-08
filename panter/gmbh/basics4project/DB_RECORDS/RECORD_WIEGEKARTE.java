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

public class RECORD_WIEGEKARTE extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_WIEGEKARTE";
    public static String IDFIELD   = "ID_WIEGEKARTE";
    

	//erzeugen eines RECORDNEW_JT_WIEGEKARTE - felds
	private RECORDNEW_WIEGEKARTE   recNEW = null;

    private _TAB  tab = _TAB.wiegekarte;  



	public RECORD_WIEGEKARTE() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_WIEGEKARTE");
	}


	public RECORD_WIEGEKARTE(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_WIEGEKARTE");
	}



	public RECORD_WIEGEKARTE(RECORD_WIEGEKARTE recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_WIEGEKARTE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WIEGEKARTE.TABLENAME);
	}


	//2014-04-03
	public RECORD_WIEGEKARTE(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_WIEGEKARTE");
		this.set_Tablename_To_FieldMetaDefs(RECORD_WIEGEKARTE.TABLENAME);
	}




	public RECORD_WIEGEKARTE(long lID_Unformated) throws myException
	{
		super("JT_WIEGEKARTE","ID_WIEGEKARTE",""+lID_Unformated);
		this.set_TABLE_NAME("JT_WIEGEKARTE");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WIEGEKARTE.TABLENAME);
	}

	public RECORD_WIEGEKARTE(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_WIEGEKARTE");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_WIEGEKARTE", "ID_WIEGEKARTE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_WIEGEKARTE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WIEGEKARTE.TABLENAME);
	}
	
	

	public RECORD_WIEGEKARTE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_WIEGEKARTE","ID_WIEGEKARTE",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_WIEGEKARTE");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WIEGEKARTE.TABLENAME);

	}


	public RECORD_WIEGEKARTE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_WIEGEKARTE");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_WIEGEKARTE", "ID_WIEGEKARTE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_WIEGEKARTE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WIEGEKARTE.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_WIEGEKARTE();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_WIEGEKARTE.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_WIEGEKARTE";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_WIEGEKARTE_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_WIEGEKARTE_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_WIEGEKARTE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_WIEGEKARTE", bibE2.cTO(), "ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_WIEGEKARTE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_WIEGEKARTE", bibE2.cTO(), "ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
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
			if (S.isFull(this.get_ID_WIEGEKARTE_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_WIEGEKARTE", "ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_WIEGEKARTE get_RECORDNEW_WIEGEKARTE() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_WIEGEKARTE();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_WIEGEKARTE(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_WIEGEKARTE create_Instance() throws myException {
		return new RECORD_WIEGEKARTE();
	}
	
	public static RECORD_WIEGEKARTE create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_WIEGEKARTE(Conn);
    }

	public static RECORD_WIEGEKARTE create_Instance(RECORD_WIEGEKARTE recordOrig) {
		return new RECORD_WIEGEKARTE(recordOrig);
    }

	public static RECORD_WIEGEKARTE create_Instance(long lID_Unformated) throws myException {
		return new RECORD_WIEGEKARTE(lID_Unformated);
    }

	public static RECORD_WIEGEKARTE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_WIEGEKARTE(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_WIEGEKARTE create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_WIEGEKARTE(lID_Unformated, Conn);
	}

	public static RECORD_WIEGEKARTE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_WIEGEKARTE(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_WIEGEKARTE create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_WIEGEKARTE(recordOrig);	    
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
    public RECORD_WIEGEKARTE build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF());
      }
      //return new RECORD_WIEGEKARTE(this.get_cSQL_4_Build());
      RECORD_WIEGEKARTE rec = new RECORD_WIEGEKARTE();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_WIEGEKARTE
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_WIEGEKARTE-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_WIEGEKARTE get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_WIEGEKARTE_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_WIEGEKARTE  recNew = new RECORDNEW_WIEGEKARTE();
		
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
    public RECORD_WIEGEKARTE set_recordNew(RECORDNEW_WIEGEKARTE recnew) throws myException {
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
	
	



		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_abn_strecke = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lager = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lieferant = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_spedition = null;




		private RECORD_ARTIKEL UP_RECORD_ARTIKEL_id_artikel_sorte = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez = null;




		private RECORD_CONTAINER UP_RECORD_CONTAINER_id_container_eigen = null;




		private RECORD_LAGERPLATZ UP_RECORD_LAGERPLATZ_id_lagerplatz_absetz = null;




		private RECORD_LAGERPLATZ UP_RECORD_LAGERPLATZ_id_lagerplatz_schuett = null;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre = null;




		private RECORD_VPOS_TPA_FUHRE_ORT UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort = null;




		private RECORD_WAAGE_STANDORT UP_RECORD_WAAGE_STANDORT_id_waage_standort = null;




		private RECLIST_BAM_IMPORT DOWN_RECLIST_BAM_IMPORT_id_wiegekarte = null ;




		private RECLIST_BEWEGUNG_STATION DOWN_RECLIST_BEWEGUNG_STATION_id_wiegekarte = null ;




		private RECLIST_BG_STATION DOWN_RECLIST_BG_STATION_id_wiegekarte = null ;




		private RECLIST_CONTAINER_ZYKLUS DOWN_RECLIST_CONTAINER_ZYKLUS_id_wiegekarte = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_abn = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_lief = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte = null ;




		private RECLIST_WAEGUNG DOWN_RECLIST_WAEGUNG_id_wiegekarte = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_parent = null ;




		private RECORD_WIEGEKARTE UP_RECORD_WIEGEKARTE_id_wiegekarte_parent = null;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_storno = null ;




		private RECORD_WIEGEKARTE UP_RECORD_WIEGEKARTE_id_wiegekarte_storno = null;




		private RECLIST_WIEGEKARTE_ABZUG_GEB DOWN_RECLIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte = null ;




		private RECLIST_WIEGEKARTE_BEFUND DOWN_RECLIST_WIEGEKARTE_BEFUND_id_wiegekarte = null ;




		private RECLIST_WIEGEKARTE_CONTAINER DOWN_RECLIST_WIEGEKARTE_CONTAINER_id_wiegekarte = null ;




		private RECLIST_WIEGEKARTE_MGE_ABZ DOWN_RECLIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte = null ;






	
	/**
	 * References the Field ID_ADRESSE_ABN_STRECKE
	 * Falls keine this.get_ID_ADRESSE_ABN_STRECKE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_abn_strecke() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_ABN_STRECKE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_abn_strecke==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_abn_strecke = new RECORD_ADRESSE(this.get_ID_ADRESSE_ABN_STRECKE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_abn_strecke;
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
	 * References the Field ID_ADRESSE_LIEFERANT
	 * Falls keine this.get_ID_ADRESSE_LIEFERANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lieferant() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LIEFERANT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lieferant==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lieferant = new RECORD_ADRESSE(this.get_ID_ADRESSE_LIEFERANT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lieferant;
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
	 * References the Field ID_ARTIKEL_SORTE
	 * Falls keine this.get_ID_ARTIKEL_SORTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL get_UP_RECORD_ARTIKEL_id_artikel_sorte() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_SORTE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_id_artikel_sorte==null)
		{
			this.UP_RECORD_ARTIKEL_id_artikel_sorte = new RECORD_ARTIKEL(this.get_ID_ARTIKEL_SORTE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_id_artikel_sorte;
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
	 * References the Field ID_CONTAINER_EIGEN
	 * Falls keine this.get_ID_CONTAINER_EIGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_CONTAINER get_UP_RECORD_CONTAINER_id_container_eigen() throws myException
	{
		if (S.isEmpty(this.get_ID_CONTAINER_EIGEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_CONTAINER_id_container_eigen==null)
		{
			this.UP_RECORD_CONTAINER_id_container_eigen = new RECORD_CONTAINER(this.get_ID_CONTAINER_EIGEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_CONTAINER_id_container_eigen;
	}
	





	
	/**
	 * References the Field ID_LAGERPLATZ_ABSETZ
	 * Falls keine this.get_ID_LAGERPLATZ_ABSETZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAGERPLATZ get_UP_RECORD_LAGERPLATZ_id_lagerplatz_absetz() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGERPLATZ_ABSETZ_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAGERPLATZ_id_lagerplatz_absetz==null)
		{
			this.UP_RECORD_LAGERPLATZ_id_lagerplatz_absetz = new RECORD_LAGERPLATZ(this.get_ID_LAGERPLATZ_ABSETZ_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAGERPLATZ_id_lagerplatz_absetz;
	}
	





	
	/**
	 * References the Field ID_LAGERPLATZ_SCHUETT
	 * Falls keine this.get_ID_LAGERPLATZ_SCHUETT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAGERPLATZ get_UP_RECORD_LAGERPLATZ_id_lagerplatz_schuett() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGERPLATZ_SCHUETT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAGERPLATZ_id_lagerplatz_schuett==null)
		{
			this.UP_RECORD_LAGERPLATZ_id_lagerplatz_schuett = new RECORD_LAGERPLATZ(this.get_ID_LAGERPLATZ_SCHUETT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAGERPLATZ_id_lagerplatz_schuett;
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
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE_ORT get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort = new RECORD_VPOS_TPA_FUHRE_ORT(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort;
	}
	





	
	/**
	 * References the Field ID_WAAGE_STANDORT
	 * Falls keine this.get_ID_WAAGE_STANDORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WAAGE_STANDORT get_UP_RECORD_WAAGE_STANDORT_id_waage_standort() throws myException
	{
		if (S.isEmpty(this.get_ID_WAAGE_STANDORT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WAAGE_STANDORT_id_waage_standort==null)
		{
			this.UP_RECORD_WAAGE_STANDORT_id_waage_standort = new RECORD_WAAGE_STANDORT(this.get_ID_WAAGE_STANDORT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WAAGE_STANDORT_id_waage_standort;
	}
	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BAM_IMPORT get_DOWN_RECORD_LIST_BAM_IMPORT_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BAM_IMPORT_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_BAM_IMPORT_id_wiegekarte = new RECLIST_BAM_IMPORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_BAM_IMPORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BAM_IMPORT_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BAM_IMPORT get_DOWN_RECORD_LIST_BAM_IMPORT_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BAM_IMPORT_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BAM_IMPORT_id_wiegekarte = new RECLIST_BAM_IMPORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BAM_IMPORT_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_STATION get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_STATION_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_STATION_id_wiegekarte = new RECLIST_BEWEGUNG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_BEWEGUNG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_STATION_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_STATION get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_STATION_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_STATION_id_wiegekarte = new RECLIST_BEWEGUNG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_STATION_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_BG_STATION_id_wiegekarte = new RECLIST_BG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_BG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_STATION_id_wiegekarte = new RECLIST_BG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_wiegekarte = new RECLIST_CONTAINER_ZYKLUS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_CONTAINER_ZYKLUS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_wiegekarte = new RECLIST_CONTAINER_ZYKLUS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE_ABN 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_wiegekarte_abn() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_abn==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_abn = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_WIEGEKARTE_ABN="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_abn;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE_ABN 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_wiegekarte_abn(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_abn==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_WIEGEKARTE_ABN="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_abn = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_abn;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE_LIEF 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_wiegekarte_lief() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_lief==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_lief = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_WIEGEKARTE_LIEF="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_lief;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE_LIEF 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_wiegekarte_lief(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_lief==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_WIEGEKARTE_LIEF="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_lief = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_wiegekarte_lief;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WAEGUNG get_DOWN_RECORD_LIST_WAEGUNG_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WAEGUNG_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_WAEGUNG_id_wiegekarte = new RECLIST_WAEGUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WAEGUNG WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_WAEGUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WAEGUNG_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WAEGUNG get_DOWN_RECORD_LIST_WAEGUNG_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WAEGUNG_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WAEGUNG WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WAEGUNG_id_wiegekarte = new RECLIST_WAEGUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WAEGUNG_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE_PARENT 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_wiegekarte_parent() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_parent==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_parent = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE_PARENT="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_parent;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE_PARENT 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_wiegekarte_parent(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_parent==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE_PARENT="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_parent = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_parent;
	}


	





	
	/**
	 * References the Field ID_WIEGEKARTE_PARENT
	 * Falls keine this.get_ID_WIEGEKARTE_PARENT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WIEGEKARTE get_UP_RECORD_WIEGEKARTE_id_wiegekarte_parent() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_PARENT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WIEGEKARTE_id_wiegekarte_parent==null)
		{
			this.UP_RECORD_WIEGEKARTE_id_wiegekarte_parent = new RECORD_WIEGEKARTE(this.get_ID_WIEGEKARTE_PARENT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WIEGEKARTE_id_wiegekarte_parent;
	}
	





	/**
	 * References the Field ID_WIEGEKARTE_STORNO 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_wiegekarte_storno() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_storno==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_storno = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE_STORNO="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_storno;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE_STORNO 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_wiegekarte_storno(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_storno==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_WIEGEKARTE_STORNO="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_storno = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_wiegekarte_storno;
	}


	





	
	/**
	 * References the Field ID_WIEGEKARTE_STORNO
	 * Falls keine this.get_ID_WIEGEKARTE_STORNO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WIEGEKARTE get_UP_RECORD_WIEGEKARTE_id_wiegekarte_storno() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_STORNO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WIEGEKARTE_id_wiegekarte_storno==null)
		{
			this.UP_RECORD_WIEGEKARTE_id_wiegekarte_storno = new RECORD_WIEGEKARTE(this.get_ID_WIEGEKARTE_STORNO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WIEGEKARTE_id_wiegekarte_storno;
	}
	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_ABZUG_GEB get_DOWN_RECORD_LIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte = new RECLIST_WIEGEKARTE_ABZUG_GEB (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_ABZUG_GEB WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_WIEGEKARTE_ABZUG_GEB",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_ABZUG_GEB get_DOWN_RECORD_LIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_ABZUG_GEB WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte = new RECLIST_WIEGEKARTE_ABZUG_GEB (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_ABZUG_GEB_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_BEFUND get_DOWN_RECORD_LIST_WIEGEKARTE_BEFUND_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_BEFUND_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_BEFUND_id_wiegekarte = new RECLIST_WIEGEKARTE_BEFUND (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_BEFUND WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_WIEGEKARTE_BEFUND",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_BEFUND_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_BEFUND get_DOWN_RECORD_LIST_WIEGEKARTE_BEFUND_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_BEFUND_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_BEFUND WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_BEFUND_id_wiegekarte = new RECLIST_WIEGEKARTE_BEFUND (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_BEFUND_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_CONTAINER get_DOWN_RECORD_LIST_WIEGEKARTE_CONTAINER_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_CONTAINER_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_CONTAINER_id_wiegekarte = new RECLIST_WIEGEKARTE_CONTAINER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_CONTAINER WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_WIEGEKARTE_CONTAINER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_CONTAINER_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_CONTAINER get_DOWN_RECORD_LIST_WIEGEKARTE_CONTAINER_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_CONTAINER_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_CONTAINER WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_CONTAINER_id_wiegekarte = new RECLIST_WIEGEKARTE_CONTAINER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_CONTAINER_id_wiegekarte;
	}


	





	/**
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_MGE_ABZ get_DOWN_RECORD_LIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte = new RECLIST_WIEGEKARTE_MGE_ABZ (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_MGE_ABZ WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF()+" ORDER BY ID_WIEGEKARTE_MGE_ABZ",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WIEGEKARTE 
	 * Falls keine get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_MGE_ABZ get_DOWN_RECORD_LIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_MGE_ABZ WHERE ID_WIEGEKARTE="+this.get_ID_WIEGEKARTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte = new RECLIST_WIEGEKARTE_MGE_ABZ (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_MGE_ABZ_id_wiegekarte;
	}


	

	public static String FIELD__ADRESSE_LIEFERANT = "ADRESSE_LIEFERANT";
	public static String FIELD__ADRESSE_SPEDITION = "ADRESSE_SPEDITION";
	public static String FIELD__ANZ_ALLG = "ANZ_ALLG";
	public static String FIELD__ANZ_BEHAELTER = "ANZ_BEHAELTER";
	public static String FIELD__ANZ_BIGBAGS = "ANZ_BIGBAGS";
	public static String FIELD__ANZ_GITTERBOXEN = "ANZ_GITTERBOXEN";
	public static String FIELD__ANZ_PALETTEN = "ANZ_PALETTEN";
	public static String FIELD__BEFUND = "BEFUND";
	public static String FIELD__BEMERKUNG1 = "BEMERKUNG1";
	public static String FIELD__BEMERKUNG2 = "BEMERKUNG2";
	public static String FIELD__BEMERKUNG_INTERN = "BEMERKUNG_INTERN";
	public static String FIELD__BEZ_ALLG = "BEZ_ALLG";
	public static String FIELD__CONTAINER_ABSETZ_GRUND = "CONTAINER_ABSETZ_GRUND";
	public static String FIELD__CONTAINER_NR = "CONTAINER_NR";
	public static String FIELD__CONTAINER_TARA = "CONTAINER_TARA";
	public static String FIELD__DRUCKZAEHLER = "DRUCKZAEHLER";
	public static String FIELD__DRUCKZAEHLER_EINGANGSSCHEIN = "DRUCKZAEHLER_EINGANGSSCHEIN";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__ES_NR = "ES_NR";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEDRUCKT_AM = "GEDRUCKT_AM";
	public static String FIELD__GEWICHT_ABZUG = "GEWICHT_ABZUG";
	public static String FIELD__GEWICHT_ABZUG_FUHRE = "GEWICHT_ABZUG_FUHRE";
	public static String FIELD__GEWICHT_NACH_ABZUG = "GEWICHT_NACH_ABZUG";
	public static String FIELD__GEWICHT_NACH_ABZUG_FUHRE = "GEWICHT_NACH_ABZUG_FUHRE";
	public static String FIELD__GRUND_ABZUG = "GRUND_ABZUG";
	public static String FIELD__GUETERKATEGORIE = "GUETERKATEGORIE";
	public static String FIELD__ID_ADRESSE_ABN_STRECKE = "ID_ADRESSE_ABN_STRECKE";
	public static String FIELD__ID_ADRESSE_LAGER = "ID_ADRESSE_LAGER";
	public static String FIELD__ID_ADRESSE_LIEFERANT = "ID_ADRESSE_LIEFERANT";
	public static String FIELD__ID_ADRESSE_SPEDITION = "ID_ADRESSE_SPEDITION";
	public static String FIELD__ID_ARTIKEL_BEZ = "ID_ARTIKEL_BEZ";
	public static String FIELD__ID_ARTIKEL_SORTE = "ID_ARTIKEL_SORTE";
	public static String FIELD__ID_CONTAINER_EIGEN = "ID_CONTAINER_EIGEN";
	public static String FIELD__ID_LAGERPLATZ_ABSETZ = "ID_LAGERPLATZ_ABSETZ";
	public static String FIELD__ID_LAGERPLATZ_SCHUETT = "ID_LAGERPLATZ_SCHUETT";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ORT = "ID_VPOS_TPA_FUHRE_ORT";
	public static String FIELD__ID_WAAGE_STANDORT = "ID_WAAGE_STANDORT";
	public static String FIELD__ID_WIEGEKARTE = "ID_WIEGEKARTE";
	public static String FIELD__ID_WIEGEKARTE_PARENT = "ID_WIEGEKARTE_PARENT";
	public static String FIELD__ID_WIEGEKARTE_STORNO = "ID_WIEGEKARTE_STORNO";
	public static String FIELD__IST_GESAMTVERWIEGUNG = "IST_GESAMTVERWIEGUNG";
	public static String FIELD__IST_LIEFERANT = "IST_LIEFERANT";
	public static String FIELD__KENNZEICHEN = "KENNZEICHEN";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LFD_NR = "LFD_NR";
	public static String FIELD__NETTOGEWICHT = "NETTOGEWICHT";
	public static String FIELD__SIEGEL_NR = "SIEGEL_NR";
	public static String FIELD__SORTE_HAND = "SORTE_HAND";
	public static String FIELD__STORNIERT_AM = "STORNIERT_AM";
	public static String FIELD__STORNIERT_VON = "STORNIERT_VON";
	public static String FIELD__STORNO = "STORNO";
	public static String FIELD__STORNO_GRUND = "STORNO_GRUND";
	public static String FIELD__STRAHLUNG_GEPRUEFT = "STRAHLUNG_GEPRUEFT";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TARA_KORR_CONTAINER = "TARA_KORR_CONTAINER";
	public static String FIELD__TRAILER = "TRAILER";
	public static String FIELD__TYP_WIEGEKARTE = "TYP_WIEGEKARTE";
	public static String FIELD__UVV_DATUM_CONTAINER = "UVV_DATUM_CONTAINER";


	public String get_ADRESSE_LIEFERANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ADRESSE_LIEFERANT");
	}

	public String get_ADRESSE_LIEFERANT_cF() throws myException
	{
		return this.get_FormatedValue("ADRESSE_LIEFERANT");	
	}

	public String get_ADRESSE_LIEFERANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ADRESSE_LIEFERANT");
	}

	public String get_ADRESSE_LIEFERANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ADRESSE_LIEFERANT",cNotNullValue);
	}

	public String get_ADRESSE_LIEFERANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ADRESSE_LIEFERANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ADRESSE_LIEFERANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ADRESSE_LIEFERANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", calNewValueFormated);
	}
		public String get_ADRESSE_SPEDITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("ADRESSE_SPEDITION");
	}

	public String get_ADRESSE_SPEDITION_cF() throws myException
	{
		return this.get_FormatedValue("ADRESSE_SPEDITION");	
	}

	public String get_ADRESSE_SPEDITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ADRESSE_SPEDITION");
	}

	public String get_ADRESSE_SPEDITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ADRESSE_SPEDITION",cNotNullValue);
	}

	public String get_ADRESSE_SPEDITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ADRESSE_SPEDITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ADRESSE_SPEDITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ADRESSE_SPEDITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", calNewValueFormated);
	}
		public String get_ANZ_ALLG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZ_ALLG");
	}

	public String get_ANZ_ALLG_cF() throws myException
	{
		return this.get_FormatedValue("ANZ_ALLG");	
	}

	public String get_ANZ_ALLG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZ_ALLG");
	}

	public String get_ANZ_ALLG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZ_ALLG",cNotNullValue);
	}

	public String get_ANZ_ALLG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZ_ALLG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZ_ALLG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZ_ALLG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZ_ALLG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZ_ALLG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_ALLG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_ALLG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_ALLG", calNewValueFormated);
	}
		public Double get_ANZ_ALLG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZ_ALLG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZ_ALLG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZ_ALLG").getDoubleValue();
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
	public String get_ANZ_ALLG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_ALLG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANZ_ALLG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_ALLG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANZ_ALLG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_ALLG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZ_ALLG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_ALLG").getBigDecimalValue();
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
	
	
	public String get_ANZ_BEHAELTER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZ_BEHAELTER");
	}

	public String get_ANZ_BEHAELTER_cF() throws myException
	{
		return this.get_FormatedValue("ANZ_BEHAELTER");	
	}

	public String get_ANZ_BEHAELTER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZ_BEHAELTER");
	}

	public String get_ANZ_BEHAELTER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZ_BEHAELTER",cNotNullValue);
	}

	public String get_ANZ_BEHAELTER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZ_BEHAELTER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZ_BEHAELTER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZ_BEHAELTER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZ_BEHAELTER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", calNewValueFormated);
	}
		public Double get_ANZ_BEHAELTER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZ_BEHAELTER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZ_BEHAELTER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZ_BEHAELTER").getDoubleValue();
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
	public String get_ANZ_BEHAELTER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_BEHAELTER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANZ_BEHAELTER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_BEHAELTER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANZ_BEHAELTER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_BEHAELTER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZ_BEHAELTER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_BEHAELTER").getBigDecimalValue();
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
	
	
	public String get_ANZ_BIGBAGS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZ_BIGBAGS");
	}

	public String get_ANZ_BIGBAGS_cF() throws myException
	{
		return this.get_FormatedValue("ANZ_BIGBAGS");	
	}

	public String get_ANZ_BIGBAGS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZ_BIGBAGS");
	}

	public String get_ANZ_BIGBAGS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZ_BIGBAGS",cNotNullValue);
	}

	public String get_ANZ_BIGBAGS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZ_BIGBAGS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZ_BIGBAGS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZ_BIGBAGS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZ_BIGBAGS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", calNewValueFormated);
	}
		public Double get_ANZ_BIGBAGS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZ_BIGBAGS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZ_BIGBAGS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZ_BIGBAGS").getDoubleValue();
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
	public String get_ANZ_BIGBAGS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_BIGBAGS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANZ_BIGBAGS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_BIGBAGS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANZ_BIGBAGS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_BIGBAGS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZ_BIGBAGS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_BIGBAGS").getBigDecimalValue();
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
	
	
	public String get_ANZ_GITTERBOXEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZ_GITTERBOXEN");
	}

	public String get_ANZ_GITTERBOXEN_cF() throws myException
	{
		return this.get_FormatedValue("ANZ_GITTERBOXEN");	
	}

	public String get_ANZ_GITTERBOXEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZ_GITTERBOXEN");
	}

	public String get_ANZ_GITTERBOXEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZ_GITTERBOXEN",cNotNullValue);
	}

	public String get_ANZ_GITTERBOXEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZ_GITTERBOXEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZ_GITTERBOXEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZ_GITTERBOXEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZ_GITTERBOXEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", calNewValueFormated);
	}
		public Double get_ANZ_GITTERBOXEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZ_GITTERBOXEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZ_GITTERBOXEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZ_GITTERBOXEN").getDoubleValue();
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
	public String get_ANZ_GITTERBOXEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_GITTERBOXEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANZ_GITTERBOXEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_GITTERBOXEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANZ_GITTERBOXEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_GITTERBOXEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZ_GITTERBOXEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_GITTERBOXEN").getBigDecimalValue();
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
	
	
	public String get_ANZ_PALETTEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZ_PALETTEN");
	}

	public String get_ANZ_PALETTEN_cF() throws myException
	{
		return this.get_FormatedValue("ANZ_PALETTEN");	
	}

	public String get_ANZ_PALETTEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZ_PALETTEN");
	}

	public String get_ANZ_PALETTEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZ_PALETTEN",cNotNullValue);
	}

	public String get_ANZ_PALETTEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZ_PALETTEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZ_PALETTEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZ_PALETTEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZ_PALETTEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", calNewValueFormated);
	}
		public Double get_ANZ_PALETTEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZ_PALETTEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZ_PALETTEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZ_PALETTEN").getDoubleValue();
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
	public String get_ANZ_PALETTEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_PALETTEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ANZ_PALETTEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZ_PALETTEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANZ_PALETTEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_PALETTEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZ_PALETTEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZ_PALETTEN").getBigDecimalValue();
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
	
	
	public String get_BEFUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEFUND");
	}

	public String get_BEFUND_cF() throws myException
	{
		return this.get_FormatedValue("BEFUND");	
	}

	public String get_BEFUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEFUND");
	}

	public String get_BEFUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEFUND",cNotNullValue);
	}

	public String get_BEFUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEFUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEFUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEFUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEFUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEFUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEFUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEFUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEFUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEFUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEFUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEFUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEFUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEFUND", calNewValueFormated);
	}
		public String get_BEMERKUNG1_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG1");
	}

	public String get_BEMERKUNG1_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG1");	
	}

	public String get_BEMERKUNG1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG1");
	}

	public String get_BEMERKUNG1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG1",cNotNullValue);
	}

	public String get_BEMERKUNG1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG1", calNewValueFormated);
	}
		public String get_BEMERKUNG2_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG2");
	}

	public String get_BEMERKUNG2_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG2");	
	}

	public String get_BEMERKUNG2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG2");
	}

	public String get_BEMERKUNG2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG2",cNotNullValue);
	}

	public String get_BEMERKUNG2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG2", calNewValueFormated);
	}
		public String get_BEMERKUNG_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_INTERN");
	}

	public String get_BEMERKUNG_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_INTERN");	
	}

	public String get_BEMERKUNG_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_INTERN");
	}

	public String get_BEMERKUNG_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_INTERN",cNotNullValue);
	}

	public String get_BEMERKUNG_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", calNewValueFormated);
	}
		public String get_BEZ_ALLG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZ_ALLG");
	}

	public String get_BEZ_ALLG_cF() throws myException
	{
		return this.get_FormatedValue("BEZ_ALLG");	
	}

	public String get_BEZ_ALLG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZ_ALLG");
	}

	public String get_BEZ_ALLG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZ_ALLG",cNotNullValue);
	}

	public String get_BEZ_ALLG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZ_ALLG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZ_ALLG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZ_ALLG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZ_ALLG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZ_ALLG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZ_ALLG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZ_ALLG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZ_ALLG", calNewValueFormated);
	}
		public String get_CONTAINER_ABSETZ_GRUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("CONTAINER_ABSETZ_GRUND");
	}

	public String get_CONTAINER_ABSETZ_GRUND_cF() throws myException
	{
		return this.get_FormatedValue("CONTAINER_ABSETZ_GRUND");	
	}

	public String get_CONTAINER_ABSETZ_GRUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("CONTAINER_ABSETZ_GRUND");
	}

	public String get_CONTAINER_ABSETZ_GRUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("CONTAINER_ABSETZ_GRUND",cNotNullValue);
	}

	public String get_CONTAINER_ABSETZ_GRUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("CONTAINER_ABSETZ_GRUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_CONTAINER_ABSETZ_GRUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", calNewValueFormated);
	}
		public String get_CONTAINER_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("CONTAINER_NR");
	}

	public String get_CONTAINER_NR_cF() throws myException
	{
		return this.get_FormatedValue("CONTAINER_NR");	
	}

	public String get_CONTAINER_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("CONTAINER_NR");
	}

	public String get_CONTAINER_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("CONTAINER_NR",cNotNullValue);
	}

	public String get_CONTAINER_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("CONTAINER_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("CONTAINER_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_CONTAINER_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("CONTAINER_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_NR", calNewValueFormated);
	}
		public String get_CONTAINER_TARA_cUF() throws myException
	{
		return this.get_UnFormatedValue("CONTAINER_TARA");
	}

	public String get_CONTAINER_TARA_cF() throws myException
	{
		return this.get_FormatedValue("CONTAINER_TARA");	
	}

	public String get_CONTAINER_TARA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("CONTAINER_TARA");
	}

	public String get_CONTAINER_TARA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("CONTAINER_TARA",cNotNullValue);
	}

	public String get_CONTAINER_TARA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("CONTAINER_TARA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("CONTAINER_TARA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_CONTAINER_TARA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("CONTAINER_TARA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("CONTAINER_TARA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_TARA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_TARA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CONTAINER_TARA", calNewValueFormated);
	}
		public Double get_CONTAINER_TARA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("CONTAINER_TARA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_CONTAINER_TARA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("CONTAINER_TARA").getDoubleValue();
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
	public String get_CONTAINER_TARA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_CONTAINER_TARA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_CONTAINER_TARA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_CONTAINER_TARA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_CONTAINER_TARA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("CONTAINER_TARA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_CONTAINER_TARA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("CONTAINER_TARA").getBigDecimalValue();
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
	
	
	public String get_DRUCKZAEHLER_cUF() throws myException
	{
		return this.get_UnFormatedValue("DRUCKZAEHLER");
	}

	public String get_DRUCKZAEHLER_cF() throws myException
	{
		return this.get_FormatedValue("DRUCKZAEHLER");	
	}

	public String get_DRUCKZAEHLER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DRUCKZAEHLER");
	}

	public String get_DRUCKZAEHLER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DRUCKZAEHLER",cNotNullValue);
	}

	public String get_DRUCKZAEHLER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DRUCKZAEHLER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", calNewValueFormated);
	}
		public Long get_DRUCKZAEHLER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DRUCKZAEHLER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DRUCKZAEHLER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DRUCKZAEHLER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DRUCKZAEHLER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DRUCKZAEHLER").getDoubleValue();
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
	public String get_DRUCKZAEHLER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKZAEHLER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_DRUCKZAEHLER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKZAEHLER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_DRUCKZAEHLER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKZAEHLER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DRUCKZAEHLER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKZAEHLER").getBigDecimalValue();
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
	
	
	public String get_DRUCKZAEHLER_EINGANGSSCHEIN_cUF() throws myException
	{
		return this.get_UnFormatedValue("DRUCKZAEHLER_EINGANGSSCHEIN");
	}

	public String get_DRUCKZAEHLER_EINGANGSSCHEIN_cF() throws myException
	{
		return this.get_FormatedValue("DRUCKZAEHLER_EINGANGSSCHEIN");	
	}

	public String get_DRUCKZAEHLER_EINGANGSSCHEIN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DRUCKZAEHLER_EINGANGSSCHEIN");
	}

	public String get_DRUCKZAEHLER_EINGANGSSCHEIN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DRUCKZAEHLER_EINGANGSSCHEIN",cNotNullValue);
	}

	public String get_DRUCKZAEHLER_EINGANGSSCHEIN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DRUCKZAEHLER_EINGANGSSCHEIN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", calNewValueFormated);
	}
		public Long get_DRUCKZAEHLER_EINGANGSSCHEIN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DRUCKZAEHLER_EINGANGSSCHEIN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DRUCKZAEHLER_EINGANGSSCHEIN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DRUCKZAEHLER_EINGANGSSCHEIN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DRUCKZAEHLER_EINGANGSSCHEIN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DRUCKZAEHLER_EINGANGSSCHEIN").getDoubleValue();
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
	public String get_DRUCKZAEHLER_EINGANGSSCHEIN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKZAEHLER_EINGANGSSCHEIN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_DRUCKZAEHLER_EINGANGSSCHEIN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKZAEHLER_EINGANGSSCHEIN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_DRUCKZAEHLER_EINGANGSSCHEIN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKZAEHLER_EINGANGSSCHEIN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DRUCKZAEHLER_EINGANGSSCHEIN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKZAEHLER_EINGANGSSCHEIN").getBigDecimalValue();
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
		public String get_ES_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ES_NR");
	}

	public String get_ES_NR_cF() throws myException
	{
		return this.get_FormatedValue("ES_NR");	
	}

	public String get_ES_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ES_NR");
	}

	public String get_ES_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ES_NR",cNotNullValue);
	}

	public String get_ES_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ES_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ES_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ES_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ES_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ES_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ES_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ES_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ES_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ES_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ES_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ES_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ES_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ES_NR", calNewValueFormated);
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
		public String get_GEDRUCKT_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEDRUCKT_AM");
	}

	public String get_GEDRUCKT_AM_cF() throws myException
	{
		return this.get_FormatedValue("GEDRUCKT_AM");	
	}

	public String get_GEDRUCKT_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEDRUCKT_AM");
	}

	public String get_GEDRUCKT_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEDRUCKT_AM",cNotNullValue);
	}

	public String get_GEDRUCKT_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEDRUCKT_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEDRUCKT_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEDRUCKT_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEDRUCKT_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", calNewValueFormated);
	}
		public String get_GEWICHT_ABZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_ABZUG");
	}

	public String get_GEWICHT_ABZUG_cF() throws myException
	{
		return this.get_FormatedValue("GEWICHT_ABZUG");	
	}

	public String get_GEWICHT_ABZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEWICHT_ABZUG");
	}

	public String get_GEWICHT_ABZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_ABZUG",cNotNullValue);
	}

	public String get_GEWICHT_ABZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEWICHT_ABZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEWICHT_ABZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_ABZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEWICHT_ABZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", calNewValueFormated);
	}
		public Double get_GEWICHT_ABZUG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GEWICHT_ABZUG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GEWICHT_ABZUG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GEWICHT_ABZUG").getDoubleValue();
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
	public String get_GEWICHT_ABZUG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_GEWICHT_ABZUG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_GEWICHT_ABZUG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_ABZUG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GEWICHT_ABZUG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_ABZUG").getBigDecimalValue();
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
	
	
	public String get_GEWICHT_ABZUG_FUHRE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_ABZUG_FUHRE");
	}

	public String get_GEWICHT_ABZUG_FUHRE_cF() throws myException
	{
		return this.get_FormatedValue("GEWICHT_ABZUG_FUHRE");	
	}

	public String get_GEWICHT_ABZUG_FUHRE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEWICHT_ABZUG_FUHRE");
	}

	public String get_GEWICHT_ABZUG_FUHRE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_ABZUG_FUHRE",cNotNullValue);
	}

	public String get_GEWICHT_ABZUG_FUHRE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEWICHT_ABZUG_FUHRE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", calNewValueFormated);
	}
		public Double get_GEWICHT_ABZUG_FUHRE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GEWICHT_ABZUG_FUHRE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GEWICHT_ABZUG_FUHRE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GEWICHT_ABZUG_FUHRE").getDoubleValue();
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
	public String get_GEWICHT_ABZUG_FUHRE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_ABZUG_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_GEWICHT_ABZUG_FUHRE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_ABZUG_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_GEWICHT_ABZUG_FUHRE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_ABZUG_FUHRE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GEWICHT_ABZUG_FUHRE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_ABZUG_FUHRE").getBigDecimalValue();
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
	
	
	public String get_GEWICHT_NACH_ABZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_NACH_ABZUG");
	}

	public String get_GEWICHT_NACH_ABZUG_cF() throws myException
	{
		return this.get_FormatedValue("GEWICHT_NACH_ABZUG");	
	}

	public String get_GEWICHT_NACH_ABZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEWICHT_NACH_ABZUG");
	}

	public String get_GEWICHT_NACH_ABZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_NACH_ABZUG",cNotNullValue);
	}

	public String get_GEWICHT_NACH_ABZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEWICHT_NACH_ABZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEWICHT_NACH_ABZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", calNewValueFormated);
	}
		public Double get_GEWICHT_NACH_ABZUG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GEWICHT_NACH_ABZUG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GEWICHT_NACH_ABZUG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GEWICHT_NACH_ABZUG").getDoubleValue();
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
	public String get_GEWICHT_NACH_ABZUG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_NACH_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_GEWICHT_NACH_ABZUG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_NACH_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_GEWICHT_NACH_ABZUG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_NACH_ABZUG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GEWICHT_NACH_ABZUG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_NACH_ABZUG").getBigDecimalValue();
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
	
	
	public String get_GEWICHT_NACH_ABZUG_FUHRE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_NACH_ABZUG_FUHRE");
	}

	public String get_GEWICHT_NACH_ABZUG_FUHRE_cF() throws myException
	{
		return this.get_FormatedValue("GEWICHT_NACH_ABZUG_FUHRE");	
	}

	public String get_GEWICHT_NACH_ABZUG_FUHRE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEWICHT_NACH_ABZUG_FUHRE");
	}

	public String get_GEWICHT_NACH_ABZUG_FUHRE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEWICHT_NACH_ABZUG_FUHRE",cNotNullValue);
	}

	public String get_GEWICHT_NACH_ABZUG_FUHRE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEWICHT_NACH_ABZUG_FUHRE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", calNewValueFormated);
	}
		public Double get_GEWICHT_NACH_ABZUG_FUHRE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GEWICHT_NACH_ABZUG_FUHRE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GEWICHT_NACH_ABZUG_FUHRE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GEWICHT_NACH_ABZUG_FUHRE").getDoubleValue();
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
	public String get_GEWICHT_NACH_ABZUG_FUHRE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_NACH_ABZUG_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_GEWICHT_NACH_ABZUG_FUHRE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEWICHT_NACH_ABZUG_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_GEWICHT_NACH_ABZUG_FUHRE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_NACH_ABZUG_FUHRE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GEWICHT_NACH_ABZUG_FUHRE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GEWICHT_NACH_ABZUG_FUHRE").getBigDecimalValue();
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
	
	
	public String get_GRUND_ABZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("GRUND_ABZUG");
	}

	public String get_GRUND_ABZUG_cF() throws myException
	{
		return this.get_FormatedValue("GRUND_ABZUG");	
	}

	public String get_GRUND_ABZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GRUND_ABZUG");
	}

	public String get_GRUND_ABZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GRUND_ABZUG",cNotNullValue);
	}

	public String get_GRUND_ABZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GRUND_ABZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GRUND_ABZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GRUND_ABZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GRUND_ABZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GRUND_ABZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUND_ABZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUND_ABZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GRUND_ABZUG", calNewValueFormated);
	}
		public String get_GUETERKATEGORIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUETERKATEGORIE");
	}

	public String get_GUETERKATEGORIE_cF() throws myException
	{
		return this.get_FormatedValue("GUETERKATEGORIE");	
	}

	public String get_GUETERKATEGORIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUETERKATEGORIE");
	}

	public String get_GUETERKATEGORIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUETERKATEGORIE",cNotNullValue);
	}

	public String get_GUETERKATEGORIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUETERKATEGORIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUETERKATEGORIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUETERKATEGORIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUETERKATEGORIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", calNewValueFormated);
	}
		public String get_ID_ADRESSE_ABN_STRECKE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_ABN_STRECKE");
	}

	public String get_ID_ADRESSE_ABN_STRECKE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_ABN_STRECKE");	
	}

	public String get_ID_ADRESSE_ABN_STRECKE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_ABN_STRECKE");
	}

	public String get_ID_ADRESSE_ABN_STRECKE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_ABN_STRECKE",cNotNullValue);
	}

	public String get_ID_ADRESSE_ABN_STRECKE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_ABN_STRECKE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_ABN_STRECKE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_ABN_STRECKE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_ABN_STRECKE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_ABN_STRECKE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_ABN_STRECKE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_ABN_STRECKE").getDoubleValue();
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
	public String get_ID_ADRESSE_ABN_STRECKE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_ABN_STRECKE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_ABN_STRECKE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_ABN_STRECKE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_ABN_STRECKE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_ABN_STRECKE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_ABN_STRECKE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_ABN_STRECKE").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LIEFERANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LIEFERANT");
	}

	public String get_ID_ADRESSE_LIEFERANT_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LIEFERANT");	
	}

	public String get_ID_ADRESSE_LIEFERANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LIEFERANT");
	}

	public String get_ID_ADRESSE_LIEFERANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LIEFERANT",cNotNullValue);
	}

	public String get_ID_ADRESSE_LIEFERANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LIEFERANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LIEFERANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LIEFERANT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LIEFERANT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LIEFERANT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LIEFERANT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LIEFERANT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LIEFERANT").getDoubleValue();
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
	public String get_ID_ADRESSE_LIEFERANT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LIEFERANT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_LIEFERANT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LIEFERANT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_LIEFERANT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LIEFERANT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LIEFERANT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LIEFERANT").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_SORTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_SORTE");
	}

	public String get_ID_ARTIKEL_SORTE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_SORTE");	
	}

	public String get_ID_ARTIKEL_SORTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_SORTE");
	}

	public String get_ID_ARTIKEL_SORTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_SORTE",cNotNullValue);
	}

	public String get_ID_ARTIKEL_SORTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_SORTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_SORTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_SORTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_SORTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_SORTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_SORTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_SORTE").getDoubleValue();
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
	public String get_ID_ARTIKEL_SORTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_SORTE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ARTIKEL_SORTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_SORTE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ARTIKEL_SORTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_SORTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_SORTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_SORTE").getBigDecimalValue();
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
	
	
	public String get_ID_CONTAINER_EIGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINER_EIGEN");
	}

	public String get_ID_CONTAINER_EIGEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_CONTAINER_EIGEN");	
	}

	public String get_ID_CONTAINER_EIGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_CONTAINER_EIGEN");
	}

	public String get_ID_CONTAINER_EIGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINER_EIGEN",cNotNullValue);
	}

	public String get_ID_CONTAINER_EIGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_CONTAINER_EIGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_CONTAINER_EIGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER_EIGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_CONTAINER_EIGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", calNewValueFormated);
	}
		public Long get_ID_CONTAINER_EIGEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_CONTAINER_EIGEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_CONTAINER_EIGEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_CONTAINER_EIGEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_CONTAINER_EIGEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_CONTAINER_EIGEN").getDoubleValue();
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
	public String get_ID_CONTAINER_EIGEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINER_EIGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_CONTAINER_EIGEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINER_EIGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_CONTAINER_EIGEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINER_EIGEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_CONTAINER_EIGEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINER_EIGEN").getBigDecimalValue();
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
	
	
	public String get_ID_LAGERPLATZ_ABSETZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ_ABSETZ");
	}

	public String get_ID_LAGERPLATZ_ABSETZ_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ_ABSETZ");	
	}

	public String get_ID_LAGERPLATZ_ABSETZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGERPLATZ_ABSETZ");
	}

	public String get_ID_LAGERPLATZ_ABSETZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ_ABSETZ",cNotNullValue);
	}

	public String get_ID_LAGERPLATZ_ABSETZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ_ABSETZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", calNewValueFormated);
	}
		public Long get_ID_LAGERPLATZ_ABSETZ_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGERPLATZ_ABSETZ").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGERPLATZ_ABSETZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ_ABSETZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGERPLATZ_ABSETZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ_ABSETZ").getDoubleValue();
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
	public String get_ID_LAGERPLATZ_ABSETZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_ABSETZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAGERPLATZ_ABSETZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_ABSETZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAGERPLATZ_ABSETZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ_ABSETZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGERPLATZ_ABSETZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ_ABSETZ").getBigDecimalValue();
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
	
	
	public String get_ID_LAGERPLATZ_SCHUETT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ_SCHUETT");
	}

	public String get_ID_LAGERPLATZ_SCHUETT_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ_SCHUETT");	
	}

	public String get_ID_LAGERPLATZ_SCHUETT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGERPLATZ_SCHUETT");
	}

	public String get_ID_LAGERPLATZ_SCHUETT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ_SCHUETT",cNotNullValue);
	}

	public String get_ID_LAGERPLATZ_SCHUETT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ_SCHUETT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", calNewValueFormated);
	}
		public Long get_ID_LAGERPLATZ_SCHUETT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGERPLATZ_SCHUETT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGERPLATZ_SCHUETT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ_SCHUETT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGERPLATZ_SCHUETT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ_SCHUETT").getDoubleValue();
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
	public String get_ID_LAGERPLATZ_SCHUETT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_SCHUETT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAGERPLATZ_SCHUETT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_SCHUETT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAGERPLATZ_SCHUETT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ_SCHUETT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGERPLATZ_SCHUETT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ_SCHUETT").getBigDecimalValue();
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
	
	
	public String get_ID_WAAGE_STANDORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAAGE_STANDORT");
	}

	public String get_ID_WAAGE_STANDORT_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAAGE_STANDORT");	
	}

	public String get_ID_WAAGE_STANDORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAAGE_STANDORT");
	}

	public String get_ID_WAAGE_STANDORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAAGE_STANDORT",cNotNullValue);
	}

	public String get_ID_WAAGE_STANDORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAAGE_STANDORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAAGE_STANDORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAAGE_STANDORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAAGE_STANDORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", calNewValueFormated);
	}
		public Long get_ID_WAAGE_STANDORT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAAGE_STANDORT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAAGE_STANDORT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAAGE_STANDORT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAAGE_STANDORT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAAGE_STANDORT").getDoubleValue();
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
	public String get_ID_WAAGE_STANDORT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAAGE_STANDORT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_WAAGE_STANDORT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAAGE_STANDORT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_WAAGE_STANDORT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAAGE_STANDORT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAAGE_STANDORT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAAGE_STANDORT").getBigDecimalValue();
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
	
	
	public String get_ID_WIEGEKARTE_PARENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_PARENT");
	}

	public String get_ID_WIEGEKARTE_PARENT_cF() throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_PARENT");	
	}

	public String get_ID_WIEGEKARTE_PARENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WIEGEKARTE_PARENT");
	}

	public String get_ID_WIEGEKARTE_PARENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_PARENT",cNotNullValue);
	}

	public String get_ID_WIEGEKARTE_PARENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_PARENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_PARENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WIEGEKARTE_PARENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", calNewValueFormated);
	}
		public Long get_ID_WIEGEKARTE_PARENT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WIEGEKARTE_PARENT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WIEGEKARTE_PARENT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_PARENT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WIEGEKARTE_PARENT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_PARENT").getDoubleValue();
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
	public String get_ID_WIEGEKARTE_PARENT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_PARENT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_WIEGEKARTE_PARENT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_PARENT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_WIEGEKARTE_PARENT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_PARENT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WIEGEKARTE_PARENT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_PARENT").getBigDecimalValue();
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
	
	
	public String get_ID_WIEGEKARTE_STORNO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_STORNO");
	}

	public String get_ID_WIEGEKARTE_STORNO_cF() throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_STORNO");	
	}

	public String get_ID_WIEGEKARTE_STORNO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WIEGEKARTE_STORNO");
	}

	public String get_ID_WIEGEKARTE_STORNO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE_STORNO",cNotNullValue);
	}

	public String get_ID_WIEGEKARTE_STORNO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE_STORNO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_STORNO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WIEGEKARTE_STORNO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", calNewValueFormated);
	}
		public Long get_ID_WIEGEKARTE_STORNO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WIEGEKARTE_STORNO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WIEGEKARTE_STORNO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_STORNO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WIEGEKARTE_STORNO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE_STORNO").getDoubleValue();
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
	public String get_ID_WIEGEKARTE_STORNO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_STORNO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_WIEGEKARTE_STORNO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_STORNO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_WIEGEKARTE_STORNO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_STORNO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WIEGEKARTE_STORNO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE_STORNO").getBigDecimalValue();
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
	
	
	public String get_IST_GESAMTVERWIEGUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_GESAMTVERWIEGUNG");
	}

	public String get_IST_GESAMTVERWIEGUNG_cF() throws myException
	{
		return this.get_FormatedValue("IST_GESAMTVERWIEGUNG");	
	}

	public String get_IST_GESAMTVERWIEGUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_GESAMTVERWIEGUNG");
	}

	public String get_IST_GESAMTVERWIEGUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_GESAMTVERWIEGUNG",cNotNullValue);
	}

	public String get_IST_GESAMTVERWIEGUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_GESAMTVERWIEGUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_GESAMTVERWIEGUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_GESAMTVERWIEGUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", calNewValueFormated);
	}
		public boolean is_IST_GESAMTVERWIEGUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GESAMTVERWIEGUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_GESAMTVERWIEGUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GESAMTVERWIEGUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_LIEFERANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_LIEFERANT");
	}

	public String get_IST_LIEFERANT_cF() throws myException
	{
		return this.get_FormatedValue("IST_LIEFERANT");	
	}

	public String get_IST_LIEFERANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_LIEFERANT");
	}

	public String get_IST_LIEFERANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_LIEFERANT",cNotNullValue);
	}

	public String get_IST_LIEFERANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_LIEFERANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_LIEFERANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_LIEFERANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_LIEFERANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LIEFERANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LIEFERANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LIEFERANT", calNewValueFormated);
	}
		public boolean is_IST_LIEFERANT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LIEFERANT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_LIEFERANT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LIEFERANT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KENNZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("KENNZEICHEN");
	}

	public String get_KENNZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("KENNZEICHEN");	
	}

	public String get_KENNZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KENNZEICHEN");
	}

	public String get_KENNZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KENNZEICHEN",cNotNullValue);
	}

	public String get_KENNZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KENNZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KENNZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KENNZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KENNZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KENNZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KENNZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KENNZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KENNZEICHEN", calNewValueFormated);
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
		public String get_LFD_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("LFD_NR");
	}

	public String get_LFD_NR_cF() throws myException
	{
		return this.get_FormatedValue("LFD_NR");	
	}

	public String get_LFD_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LFD_NR");
	}

	public String get_LFD_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LFD_NR",cNotNullValue);
	}

	public String get_LFD_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LFD_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LFD_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFD_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFD_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFD_NR", calNewValueFormated);
	}
		public String get_NETTOGEWICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("NETTOGEWICHT");
	}

	public String get_NETTOGEWICHT_cF() throws myException
	{
		return this.get_FormatedValue("NETTOGEWICHT");	
	}

	public String get_NETTOGEWICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NETTOGEWICHT");
	}

	public String get_NETTOGEWICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NETTOGEWICHT",cNotNullValue);
	}

	public String get_NETTOGEWICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NETTOGEWICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NETTOGEWICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NETTOGEWICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NETTOGEWICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOGEWICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOGEWICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOGEWICHT", calNewValueFormated);
	}
		public Double get_NETTOGEWICHT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NETTOGEWICHT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NETTOGEWICHT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NETTOGEWICHT").getDoubleValue();
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
	public String get_NETTOGEWICHT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOGEWICHT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_NETTOGEWICHT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOGEWICHT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_NETTOGEWICHT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOGEWICHT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NETTOGEWICHT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOGEWICHT").getBigDecimalValue();
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
	
	
	public String get_SIEGEL_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("SIEGEL_NR");
	}

	public String get_SIEGEL_NR_cF() throws myException
	{
		return this.get_FormatedValue("SIEGEL_NR");	
	}

	public String get_SIEGEL_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SIEGEL_NR");
	}

	public String get_SIEGEL_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SIEGEL_NR",cNotNullValue);
	}

	public String get_SIEGEL_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SIEGEL_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SIEGEL_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SIEGEL_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SIEGEL_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SIEGEL_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SIEGEL_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SIEGEL_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SIEGEL_NR", calNewValueFormated);
	}
		public String get_SORTE_HAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_HAND");
	}

	public String get_SORTE_HAND_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_HAND");	
	}

	public String get_SORTE_HAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_HAND");
	}

	public String get_SORTE_HAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_HAND",cNotNullValue);
	}

	public String get_SORTE_HAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_HAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_HAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_HAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_HAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_HAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_HAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_HAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_HAND", calNewValueFormated);
	}
		public boolean is_SORTE_HAND_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SORTE_HAND_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SORTE_HAND_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SORTE_HAND_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STORNIERT_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_AM");
	}

	public String get_STORNIERT_AM_cF() throws myException
	{
		return this.get_FormatedValue("STORNIERT_AM");	
	}

	public String get_STORNIERT_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNIERT_AM");
	}

	public String get_STORNIERT_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_AM",cNotNullValue);
	}

	public String get_STORNIERT_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNIERT_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_AM", calNewValueFormated);
	}
		public String get_STORNIERT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_VON");
	}

	public String get_STORNIERT_VON_cF() throws myException
	{
		return this.get_FormatedValue("STORNIERT_VON");	
	}

	public String get_STORNIERT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNIERT_VON");
	}

	public String get_STORNIERT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_VON",cNotNullValue);
	}

	public String get_STORNIERT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNIERT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_VON", calNewValueFormated);
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
		public String get_STRAHLUNG_GEPRUEFT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STRAHLUNG_GEPRUEFT");
	}

	public String get_STRAHLUNG_GEPRUEFT_cF() throws myException
	{
		return this.get_FormatedValue("STRAHLUNG_GEPRUEFT");	
	}

	public String get_STRAHLUNG_GEPRUEFT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STRAHLUNG_GEPRUEFT");
	}

	public String get_STRAHLUNG_GEPRUEFT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STRAHLUNG_GEPRUEFT",cNotNullValue);
	}

	public String get_STRAHLUNG_GEPRUEFT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STRAHLUNG_GEPRUEFT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STRAHLUNG_GEPRUEFT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STRAHLUNG_GEPRUEFT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", calNewValueFormated);
	}
		public boolean is_STRAHLUNG_GEPRUEFT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STRAHLUNG_GEPRUEFT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STRAHLUNG_GEPRUEFT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STRAHLUNG_GEPRUEFT_cUF_NN("N").equals("N"))
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
	
	
	public String get_TARA_KORR_CONTAINER_cUF() throws myException
	{
		return this.get_UnFormatedValue("TARA_KORR_CONTAINER");
	}

	public String get_TARA_KORR_CONTAINER_cF() throws myException
	{
		return this.get_FormatedValue("TARA_KORR_CONTAINER");	
	}

	public String get_TARA_KORR_CONTAINER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TARA_KORR_CONTAINER");
	}

	public String get_TARA_KORR_CONTAINER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TARA_KORR_CONTAINER",cNotNullValue);
	}

	public String get_TARA_KORR_CONTAINER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TARA_KORR_CONTAINER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TARA_KORR_CONTAINER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TARA_KORR_CONTAINER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TARA_KORR_CONTAINER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", calNewValueFormated);
	}
		public Double get_TARA_KORR_CONTAINER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("TARA_KORR_CONTAINER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_TARA_KORR_CONTAINER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("TARA_KORR_CONTAINER").getDoubleValue();
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
	public String get_TARA_KORR_CONTAINER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TARA_KORR_CONTAINER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_TARA_KORR_CONTAINER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TARA_KORR_CONTAINER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_TARA_KORR_CONTAINER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("TARA_KORR_CONTAINER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_TARA_KORR_CONTAINER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("TARA_KORR_CONTAINER").getBigDecimalValue();
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
	
	
	public String get_TRAILER_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRAILER");
	}

	public String get_TRAILER_cF() throws myException
	{
		return this.get_FormatedValue("TRAILER");	
	}

	public String get_TRAILER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRAILER");
	}

	public String get_TRAILER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRAILER",cNotNullValue);
	}

	public String get_TRAILER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRAILER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRAILER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRAILER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRAILER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRAILER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRAILER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRAILER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRAILER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRAILER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRAILER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRAILER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRAILER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRAILER", calNewValueFormated);
	}
		public String get_TYP_WIEGEKARTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYP_WIEGEKARTE");
	}

	public String get_TYP_WIEGEKARTE_cF() throws myException
	{
		return this.get_FormatedValue("TYP_WIEGEKARTE");	
	}

	public String get_TYP_WIEGEKARTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYP_WIEGEKARTE");
	}

	public String get_TYP_WIEGEKARTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYP_WIEGEKARTE",cNotNullValue);
	}

	public String get_TYP_WIEGEKARTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYP_WIEGEKARTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYP_WIEGEKARTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYP_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYP_WIEGEKARTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", calNewValueFormated);
	}
		public String get_UVV_DATUM_CONTAINER_cUF() throws myException
	{
		return this.get_UnFormatedValue("UVV_DATUM_CONTAINER");
	}

	public String get_UVV_DATUM_CONTAINER_cF() throws myException
	{
		return this.get_FormatedValue("UVV_DATUM_CONTAINER");	
	}

	public String get_UVV_DATUM_CONTAINER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UVV_DATUM_CONTAINER");
	}

	public String get_UVV_DATUM_CONTAINER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UVV_DATUM_CONTAINER",cNotNullValue);
	}

	public String get_UVV_DATUM_CONTAINER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UVV_DATUM_CONTAINER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UVV_DATUM_CONTAINER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UVV_DATUM_CONTAINER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UVV_DATUM_CONTAINER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ADRESSE_LIEFERANT",MyRECORD.DATATYPES.TEXT);
	put("ADRESSE_SPEDITION",MyRECORD.DATATYPES.TEXT);
	put("ANZ_ALLG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANZ_BEHAELTER",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANZ_BIGBAGS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANZ_GITTERBOXEN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANZ_PALETTEN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("BEFUND",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG1",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG2",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_INTERN",MyRECORD.DATATYPES.TEXT);
	put("BEZ_ALLG",MyRECORD.DATATYPES.TEXT);
	put("CONTAINER_ABSETZ_GRUND",MyRECORD.DATATYPES.TEXT);
	put("CONTAINER_NR",MyRECORD.DATATYPES.TEXT);
	put("CONTAINER_TARA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("DRUCKZAEHLER",MyRECORD.DATATYPES.NUMBER);
	put("DRUCKZAEHLER_EINGANGSSCHEIN",MyRECORD.DATATYPES.NUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("ES_NR",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEDRUCKT_AM",MyRECORD.DATATYPES.DATE);
	put("GEWICHT_ABZUG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GEWICHT_ABZUG_FUHRE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GEWICHT_NACH_ABZUG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GEWICHT_NACH_ABZUG_FUHRE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GRUND_ABZUG",MyRECORD.DATATYPES.TEXT);
	put("GUETERKATEGORIE",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE_ABN_STRECKE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LIEFERANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_SPEDITION",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_SORTE",MyRECORD.DATATYPES.NUMBER);
	put("ID_CONTAINER_EIGEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGERPLATZ_ABSETZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGERPLATZ_SCHUETT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ORT",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAAGE_STANDORT",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE_PARENT",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE_STORNO",MyRECORD.DATATYPES.NUMBER);
	put("IST_GESAMTVERWIEGUNG",MyRECORD.DATATYPES.YESNO);
	put("IST_LIEFERANT",MyRECORD.DATATYPES.YESNO);
	put("KENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LFD_NR",MyRECORD.DATATYPES.TEXT);
	put("NETTOGEWICHT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SIEGEL_NR",MyRECORD.DATATYPES.TEXT);
	put("SORTE_HAND",MyRECORD.DATATYPES.YESNO);
	put("STORNIERT_AM",MyRECORD.DATATYPES.DATE);
	put("STORNIERT_VON",MyRECORD.DATATYPES.TEXT);
	put("STORNO",MyRECORD.DATATYPES.YESNO);
	put("STORNO_GRUND",MyRECORD.DATATYPES.TEXT);
	put("STRAHLUNG_GEPRUEFT",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TARA_KORR_CONTAINER",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("TRAILER",MyRECORD.DATATYPES.TEXT);
	put("TYP_WIEGEKARTE",MyRECORD.DATATYPES.TEXT);
	put("UVV_DATUM_CONTAINER",MyRECORD.DATATYPES.DATE);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_WIEGEKARTE.HM_FIELDS;	
	}

}
