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

public class RECORD_ADRESSE extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ADRESSE";
    public static String IDFIELD   = "ID_ADRESSE";
    

	//erzeugen eines RECORDNEW_JT_ADRESSE - felds
	private RECORDNEW_ADRESSE   recNEW = null;

    private _TAB  tab = _TAB.adresse;  



	public RECORD_ADRESSE() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ADRESSE");
	}


	public RECORD_ADRESSE(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ADRESSE");
	}



	public RECORD_ADRESSE(RECORD_ADRESSE recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ADRESSE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ADRESSE.TABLENAME);
	}


	//2014-04-03
	public RECORD_ADRESSE(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ADRESSE");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ADRESSE.TABLENAME);
	}




	public RECORD_ADRESSE(long lID_Unformated) throws myException
	{
		super("JT_ADRESSE","ID_ADRESSE",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ADRESSE");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ADRESSE.TABLENAME);
	}

	public RECORD_ADRESSE(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ADRESSE");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ADRESSE", "ID_ADRESSE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ADRESSE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ADRESSE.TABLENAME);
	}
	
	

	public RECORD_ADRESSE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ADRESSE","ID_ADRESSE",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ADRESSE");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ADRESSE.TABLENAME);

	}


	public RECORD_ADRESSE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ADRESSE");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ADRESSE", "ID_ADRESSE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ADRESSE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ADRESSE.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ADRESSE();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ADRESSE.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ADRESSE";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ADRESSE_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ADRESSE_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ADRESSE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ADRESSE", bibE2.cTO(), "ID_ADRESSE="+this.get_ID_ADRESSE_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ADRESSE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ADRESSE", bibE2.cTO(), "ID_ADRESSE="+this.get_ID_ADRESSE_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
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
			if (S.isFull(this.get_ID_ADRESSE_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ADRESSE", "ID_ADRESSE="+this.get_ID_ADRESSE_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ADRESSE get_RECORDNEW_ADRESSE() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ADRESSE();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ADRESSE(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ADRESSE create_Instance() throws myException {
		return new RECORD_ADRESSE();
	}
	
	public static RECORD_ADRESSE create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ADRESSE(Conn);
    }

	public static RECORD_ADRESSE create_Instance(RECORD_ADRESSE recordOrig) {
		return new RECORD_ADRESSE(recordOrig);
    }

	public static RECORD_ADRESSE create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ADRESSE(lID_Unformated);
    }

	public static RECORD_ADRESSE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ADRESSE(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ADRESSE create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ADRESSE(lID_Unformated, Conn);
	}

	public static RECORD_ADRESSE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ADRESSE(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ADRESSE create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ADRESSE(recordOrig);	    
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
    public RECORD_ADRESSE build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF());
      }
      //return new RECORD_ADRESSE(this.get_cSQL_4_Build());
      RECORD_ADRESSE rec = new RECORD_ADRESSE();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ADRESSE
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ADRESSE-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ADRESSE get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ADRESSE_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ADRESSE  recNew = new RECORDNEW_ADRESSE();
		
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
    public RECORD_ADRESSE set_recordNew(RECORDNEW_ADRESSE recnew) throws myException {
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
	
	



		private RECORD_LAND UP_RECORD_LAND_id_land = null;




		private RECORD_SPRACHE UP_RECORD_SPRACHE_id_sprache = null;




		private RECORD_USER UP_RECORD_USER_id_user = null;




		private RECORD_USER UP_RECORD_USER_id_user_ersatz = null;




		private RECORD_USER UP_RECORD_USER_id_user_lager_haendler = null;




		private RECORD_USER UP_RECORD_USER_id_user_lager_sachbearb = null;




		private RECORD_USER UP_RECORD_USER_id_user_sachbearbeiter = null;




		private RECORD_WAEHRUNG UP_RECORD_WAEHRUNG_id_waehrung = null;




		private RECLIST_ABRECHBLATT DOWN_RECLIST_ABRECHBLATT_id_adresse_kunde = null ;




		private RECLIST_ABRECHBLATT DOWN_RECLIST_ABRECHBLATT_id_adresse_lager_eigen = null ;




		private RECLIST_ABRECHBLATT DOWN_RECLIST_ABRECHBLATT_id_adresse_lieferant = null ;




		private RECLIST_ADR_CONTAINERTYP DOWN_RECLIST_ADR_CONTAINERTYP_id_adresse = null ;




		private RECLIST_ADRESSAUSSTATT DOWN_RECLIST_ADRESSAUSSTATT_id_adresse = null ;




		private RECLIST_ADRESSE_ARTIKEL DOWN_RECLIST_ADRESSE_ARTIKEL_id_adresse = null ;




		private RECLIST_ADRESSE_EAK_CODE DOWN_RECLIST_ADRESSE_EAK_CODE_id_adresse = null ;




		private RECLIST_ADRESSE_FAHRZEUGE DOWN_RECLIST_ADRESSE_FAHRZEUGE_id_adresse = null ;




		private RECLIST_ADRESSE_GESCHENK DOWN_RECLIST_ADRESSE_GESCHENK_id_adresse = null ;




		private RECLIST_ADRESSE_INFO DOWN_RECLIST_ADRESSE_INFO_id_adresse = null ;




		private RECLIST_ADRESSE_UST_ID DOWN_RECLIST_ADRESSE_UST_ID_id_adresse = null ;




		private RECLIST_ADRESSE_WAEHRUNG DOWN_RECLIST_ADRESSE_WAEHRUNG_id_adresse = null ;




		private RECLIST_ADRESSE_ZUSATZBRANCHE DOWN_RECLIST_ADRESSE_ZUSATZBRANCHE_id_adresse = null ;




		private RECLIST_ADRESSKLASSE DOWN_RECLIST_ADRESSKLASSE_id_adresse = null ;




		private RECLIST_ADRESS_ZUSATZWERTE DOWN_RECLIST_ADRESS_ZUSATZWERTE_id_adresse = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_start = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_ziel = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_start = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_ziel = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_1_abfallerzeuger = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_1_import_empfaenger = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_1_verbr_veranlasser = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_1_verwertungsanlage = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_2_abfallerzeuger = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_2_import_empfaenger = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_2_verbr_veranlasser = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_2_verwertungsanlage = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_3_abfallerzeuger = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_3_import_empfaenger = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_3_verbr_veranlasser = null ;




		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_3_verwertungsanlage = null ;




		private RECLIST_ARTIKELBEZ_LIEF DOWN_RECLIST_ARTIKELBEZ_LIEF_id_adresse = null ;




		private RECLIST_BANKENSTAMM DOWN_RECLIST_BANKENSTAMM_id_adresse = null ;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_start = null ;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_ziel = null ;




		private RECLIST_BEWEGUNG_STATION DOWN_RECLIST_BEWEGUNG_STATION_id_adresse = null ;




		private RECLIST_BEWEGUNG_STATION DOWN_RECLIST_BEWEGUNG_STATION_id_adresse_besitzer = null ;




		private RECLIST_BEWEGUNG_VEKTOR DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_fremdware = null ;




		private RECLIST_BEWEGUNG_VEKTOR DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_spedition = null ;




		private RECLIST_BEWEGUNG_VEKTOR DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik = null ;




		private RECLIST_BEWEGUNG_VEKTOR_PN DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start = null ;




		private RECLIST_BEWEGUNG_VEKTOR_PN DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel = null ;




		private RECLIST_BEWEGUNG_VEKTOR_PN DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_start = null ;




		private RECLIST_BEWEGUNG_VEKTOR_PN DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel = null ;




		private RECLIST_BEZIEHUNG DOWN_RECLIST_BEZIEHUNG_id_adresse_1 = null ;




		private RECLIST_BEZIEHUNG DOWN_RECLIST_BEZIEHUNG_id_adresse_2 = null ;




		private RECLIST_BG_STATION DOWN_RECLIST_BG_STATION_id_adresse = null ;




		private RECLIST_BG_STATION DOWN_RECLIST_BG_STATION_id_adresse_basis = null ;




		private RECLIST_BG_STATION DOWN_RECLIST_BG_STATION_id_adresse_besitz_ldg = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_adresse_fremdware = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_start = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_ziel = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_adresse_spedition = null ;




		private RECLIST_CONTAINER_ZYKLUS DOWN_RECLIST_CONTAINER_ZYKLUS_id_adresse = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_adresse_buchungslager = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_adresse_fremdware = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_adresse_rechnung = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_adresse_start = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_adresse_ziel = null ;




		private RECLIST_EMAIL DOWN_RECLIST_EMAIL_id_adresse = null ;




		private RECLIST_FAHRPLANPOS DOWN_RECLIST_FAHRPLANPOS_id_adresse_start = null ;




		private RECLIST_FAHRPLANPOS DOWN_RECLIST_FAHRPLANPOS_id_adresse_ziel = null ;




		private RECLIST_FAHRTENVARIANTEN DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_start = null ;




		private RECLIST_FAHRTENVARIANTEN DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_ziel = null ;




		private RECLIST_FIBU DOWN_RECLIST_FIBU_id_adresse = null ;




		private RECLIST_FIBU_KONTENREGEL DOWN_RECLIST_FIBU_KONTENREGEL_id_adresse = null ;




		private RECLIST_FIRMENINFO DOWN_RECLIST_FIRMENINFO_id_adresse = null ;




		private RECLIST_INTERNET DOWN_RECLIST_INTERNET_id_adresse = null ;




		private RECLIST_KOMMUNIKATION DOWN_RECLIST_KOMMUNIKATION_id_adresse = null ;




		private RECLIST_KONTO DOWN_RECLIST_KONTO_id_adresse = null ;




		private RECLIST_KOSTEN_LIEFERBED_ADR DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse = null ;




		private RECLIST_KOSTEN_LIEFERBED_ADR DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis = null ;




		private RECLIST_KOSTEN_LIEFERBED_ADR DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel = null ;




		private RECLIST_KREDITVERS_ADRESSE DOWN_RECLIST_KREDITVERS_ADRESSE_id_adresse = null ;




		private RECLIST_KUNDE_MWST DOWN_RECLIST_KUNDE_MWST_id_adresse = null ;




		private RECLIST_LAGER_KONTO DOWN_RECLIST_LAGER_KONTO_id_adresse_lager = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_id_adresse_ausbuch_hand = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_id_adresse_einbuch_hand = null ;




		private RECLIST_LIEFERADRESSE DOWN_RECLIST_LIEFERADRESSE_id_adresse_basis = null ;




		private RECLIST_LIEFERADRESSE DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware = null ;




		private RECLIST_LIEFERADRESSE DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager = null ;




		private RECLIST_LIEFERADRESSE DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitz_lager = null ;




		private RECLIST_LIEFERADRESSE DOWN_RECLIST_LIEFERADRESSE_id_adresse_fremdware = null ;




		private RECLIST_LIEFERADRESSE DOWN_RECLIST_LIEFERADRESSE_id_adresse_liefer = null ;




		private RECLIST_MAT_SPEZ DOWN_RECLIST_MAT_SPEZ_id_adresse = null ;




		private RECLIST_MITARBEITER DOWN_RECLIST_MITARBEITER_id_adresse_basis = null ;




		private RECLIST_MITARBEITER DOWN_RECLIST_MITARBEITER_id_adresse_mitarbeiter = null ;




		private RECLIST_PRO_ADRESSEN DOWN_RECLIST_PRO_ADRESSEN_id_adresse = null ;




		private RECLIST_PROFORMA_RECHNUNG DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_kaeufer = null ;




		private RECLIST_PROFORMA_RECHNUNG DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_vertreter = null ;




		private RECLIST_SANKTION_PRUEFUNG DOWN_RECLIST_SANKTION_PRUEFUNG_id_adresse = null ;




		private RECLIST_SONDERZEITEN DOWN_RECLIST_SONDERZEITEN_id_adresse = null ;




		private RECLIST_UMA_KONTRAKT DOWN_RECLIST_UMA_KONTRAKT_id_adresse = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_id_adresse = null ;




		private RECLIST_VKOPF_RG DOWN_RECLIST_VKOPF_RG_id_adresse = null ;




		private RECLIST_VKOPF_STD DOWN_RECLIST_VKOPF_STD_id_adresse = null ;




		private RECLIST_VKOPF_TPA DOWN_RECLIST_VKOPF_TPA_id_adresse = null ;




		private RECLIST_VPOS_KON DOWN_RECLIST_VPOS_KON_id_adresse = null ;




		private RECLIST_VPOS_KON DOWN_RECLIST_VPOS_KON_id_adresse_lager = null ;




		private RECLIST_VPOS_KON_LAGER DOWN_RECLIST_VPOS_KON_LAGER_id_adresse_lager = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_adresse = null ;




		private RECLIST_VPOS_STD DOWN_RECLIST_VPOS_STD_id_adresse = null ;




		private RECLIST_VPOS_STD DOWN_RECLIST_VPOS_STD_id_adresse_lager = null ;




		private RECLIST_VPOS_TPA DOWN_RECLIST_VPOS_TPA_id_adresse = null ;




		private RECLIST_VPOS_TPA DOWN_RECLIST_VPOS_TPA_id_adresse_lager = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_start = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_spedition = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_start = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_ziel = null ;




		private RECLIST_VPOS_TPA_FUHRE_KOSTEN DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_adresse_abn_strecke = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_adresse_lager = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_adresse_lieferant = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_adresse_spedition = null ;




		private RECORD_ADRESSE_EU_VERTR_FORM UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form = null;




		private RECORD_ADRESSE_MERKMAL1 UP_RECORD_ADRESSE_MERKMAL1_id_adresse_merkmal1 = null;




		private RECORD_ADRESSE_MERKMAL2 UP_RECORD_ADRESSE_MERKMAL2_id_adresse_merkmal2 = null;




		private RECORD_ADRESSE_MERKMAL3 UP_RECORD_ADRESSE_MERKMAL3_id_adresse_merkmal3 = null;




		private RECORD_ADRESSE_MERKMAL4 UP_RECORD_ADRESSE_MERKMAL4_id_adresse_merkmal4 = null;




		private RECORD_ADRESSE_MERKMAL5 UP_RECORD_ADRESSE_MERKMAL5_id_adresse_merkmal5 = null;




		private RECORD_ADRESSE_POTENTIAL UP_RECORD_ADRESSE_POTENTIAL_id_adresse_potential = null;




		private RECORD_ANREDE UP_RECORD_ANREDE_id_anrede = null;




		private RECORD_LIEFERBEDINGUNGEN UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen = null;




		private RECORD_LIEFERBEDINGUNGEN UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk = null;




		private RECORD_ZAHLUNGSBEDINGUNGEN UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen = null;




		private RECORD_ZAHLUNGSBEDINGUNGEN UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk = null;






	
	/**
	 * References the Field ID_LAND
	 * Falls keine this.get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land==null)
		{
			this.UP_RECORD_LAND_id_land = new RECORD_LAND(this.get_ID_LAND_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land;
	}
	





	
	/**
	 * References the Field ID_SPRACHE
	 * Falls keine this.get_ID_SPRACHE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_SPRACHE get_UP_RECORD_SPRACHE_id_sprache() throws myException
	{
		if (S.isEmpty(this.get_ID_SPRACHE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_SPRACHE_id_sprache==null)
		{
			this.UP_RECORD_SPRACHE_id_sprache = new RECORD_SPRACHE(this.get_ID_SPRACHE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_SPRACHE_id_sprache;
	}
	





	
	/**
	 * References the Field ID_USER
	 * Falls keine this.get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user==null)
		{
			this.UP_RECORD_USER_id_user = new RECORD_USER(this.get_ID_USER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user;
	}
	





	
	/**
	 * References the Field ID_USER_ERSATZ
	 * Falls keine this.get_ID_USER_ERSATZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_ersatz() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_ERSATZ_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_ersatz==null)
		{
			this.UP_RECORD_USER_id_user_ersatz = new RECORD_USER(this.get_ID_USER_ERSATZ_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_ersatz;
	}
	





	
	/**
	 * References the Field ID_USER_LAGER_HAENDLER
	 * Falls keine this.get_ID_USER_LAGER_HAENDLER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_lager_haendler() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_LAGER_HAENDLER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_lager_haendler==null)
		{
			this.UP_RECORD_USER_id_user_lager_haendler = new RECORD_USER(this.get_ID_USER_LAGER_HAENDLER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_lager_haendler;
	}
	





	
	/**
	 * References the Field ID_USER_LAGER_SACHBEARB
	 * Falls keine this.get_ID_USER_LAGER_SACHBEARB_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_lager_sachbearb() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_LAGER_SACHBEARB_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_lager_sachbearb==null)
		{
			this.UP_RECORD_USER_id_user_lager_sachbearb = new RECORD_USER(this.get_ID_USER_LAGER_SACHBEARB_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_lager_sachbearb;
	}
	





	
	/**
	 * References the Field ID_USER_SACHBEARBEITER
	 * Falls keine this.get_ID_USER_SACHBEARBEITER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_sachbearbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_SACHBEARBEITER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_sachbearbeiter==null)
		{
			this.UP_RECORD_USER_id_user_sachbearbeiter = new RECORD_USER(this.get_ID_USER_SACHBEARBEITER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_sachbearbeiter;
	}
	





	
	/**
	 * References the Field ID_WAEHRUNG
	 * Falls keine this.get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WAEHRUNG get_UP_RECORD_WAEHRUNG_id_waehrung() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WAEHRUNG_id_waehrung==null)
		{
			this.UP_RECORD_WAEHRUNG_id_waehrung = new RECORD_WAEHRUNG(this.get_ID_WAEHRUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WAEHRUNG_id_waehrung;
	}
	





	/**
	 * References the Field ID_ADRESSE_KUNDE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_adresse_kunde() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_adresse_kunde==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_id_adresse_kunde = new RECLIST_ABRECHBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ADRESSE_KUNDE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ABRECHBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_adresse_kunde;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_KUNDE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_adresse_kunde(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_adresse_kunde==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ADRESSE_KUNDE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_id_adresse_kunde = new RECLIST_ABRECHBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_adresse_kunde;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER_EIGEN 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_adresse_lager_eigen() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lager_eigen==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lager_eigen = new RECLIST_ABRECHBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ADRESSE_LAGER_EIGEN="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ABRECHBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lager_eigen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER_EIGEN 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_adresse_lager_eigen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lager_eigen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ADRESSE_LAGER_EIGEN="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lager_eigen = new RECLIST_ABRECHBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lager_eigen;
	}


	





	/**
	 * References the Field ID_ADRESSE_LIEFERANT 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_adresse_lieferant() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lieferant==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lieferant = new RECLIST_ABRECHBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ADRESSE_LIEFERANT="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ABRECHBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lieferant;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LIEFERANT 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_adresse_lieferant(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lieferant==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ADRESSE_LIEFERANT="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lieferant = new RECLIST_ABRECHBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_adresse_lieferant;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADR_CONTAINERTYP get_DOWN_RECORD_LIST_ADR_CONTAINERTYP_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADR_CONTAINERTYP_id_adresse==null)
		{
			this.DOWN_RECLIST_ADR_CONTAINERTYP_id_adresse = new RECLIST_ADR_CONTAINERTYP (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADR_CONTAINERTYP WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADR_CONTAINERTYP",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADR_CONTAINERTYP_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADR_CONTAINERTYP get_DOWN_RECORD_LIST_ADR_CONTAINERTYP_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADR_CONTAINERTYP_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADR_CONTAINERTYP WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADR_CONTAINERTYP_id_adresse = new RECLIST_ADR_CONTAINERTYP (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADR_CONTAINERTYP_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSAUSSTATT get_DOWN_RECORD_LIST_ADRESSAUSSTATT_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSAUSSTATT_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSAUSSTATT_id_adresse = new RECLIST_ADRESSAUSSTATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSAUSSTATT WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSAUSSTATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSAUSSTATT_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSAUSSTATT get_DOWN_RECORD_LIST_ADRESSAUSSTATT_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSAUSSTATT_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSAUSSTATT WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSAUSSTATT_id_adresse = new RECLIST_ADRESSAUSSTATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSAUSSTATT_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_ARTIKEL get_DOWN_RECORD_LIST_ADRESSE_ARTIKEL_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_adresse = new RECLIST_ADRESSE_ARTIKEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_ARTIKEL WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_ARTIKEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_ARTIKEL get_DOWN_RECORD_LIST_ADRESSE_ARTIKEL_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_ARTIKEL WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_adresse = new RECLIST_ADRESSE_ARTIKEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_EAK_CODE get_DOWN_RECORD_LIST_ADRESSE_EAK_CODE_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_adresse = new RECLIST_ADRESSE_EAK_CODE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_EAK_CODE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_EAK_CODE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_EAK_CODE get_DOWN_RECORD_LIST_ADRESSE_EAK_CODE_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_EAK_CODE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_adresse = new RECLIST_ADRESSE_EAK_CODE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_FAHRZEUGE get_DOWN_RECORD_LIST_ADRESSE_FAHRZEUGE_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_FAHRZEUGE_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_FAHRZEUGE_id_adresse = new RECLIST_ADRESSE_FAHRZEUGE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_FAHRZEUGE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_FAHRZEUGE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_FAHRZEUGE_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_FAHRZEUGE get_DOWN_RECORD_LIST_ADRESSE_FAHRZEUGE_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_FAHRZEUGE_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_FAHRZEUGE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_FAHRZEUGE_id_adresse = new RECLIST_ADRESSE_FAHRZEUGE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_FAHRZEUGE_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_GESCHENK get_DOWN_RECORD_LIST_ADRESSE_GESCHENK_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_GESCHENK_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_GESCHENK_id_adresse = new RECLIST_ADRESSE_GESCHENK (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_GESCHENK WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_GESCHENK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_GESCHENK_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_GESCHENK get_DOWN_RECORD_LIST_ADRESSE_GESCHENK_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_GESCHENK_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_GESCHENK WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_GESCHENK_id_adresse = new RECLIST_ADRESSE_GESCHENK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_GESCHENK_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_INFO_id_adresse = new RECLIST_ADRESSE_INFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_INFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_INFO_id_adresse = new RECLIST_ADRESSE_INFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_UST_ID get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_UST_ID_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_UST_ID_id_adresse = new RECLIST_ADRESSE_UST_ID (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_UST_ID WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_UST_ID",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_UST_ID_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_UST_ID get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_UST_ID_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_UST_ID WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_UST_ID_id_adresse = new RECLIST_ADRESSE_UST_ID (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_UST_ID_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_WAEHRUNG get_DOWN_RECORD_LIST_ADRESSE_WAEHRUNG_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_adresse = new RECLIST_ADRESSE_WAEHRUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_WAEHRUNG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_WAEHRUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_WAEHRUNG get_DOWN_RECORD_LIST_ADRESSE_WAEHRUNG_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_WAEHRUNG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_adresse = new RECLIST_ADRESSE_WAEHRUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_ZUSATZBRANCHE get_DOWN_RECORD_LIST_ADRESSE_ZUSATZBRANCHE_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_ZUSATZBRANCHE_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSE_ZUSATZBRANCHE_id_adresse = new RECLIST_ADRESSE_ZUSATZBRANCHE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_ZUSATZBRANCHE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSE_ZUSATZBRANCHE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_ZUSATZBRANCHE_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_ZUSATZBRANCHE get_DOWN_RECORD_LIST_ADRESSE_ZUSATZBRANCHE_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_ZUSATZBRANCHE_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_ZUSATZBRANCHE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_ZUSATZBRANCHE_id_adresse = new RECLIST_ADRESSE_ZUSATZBRANCHE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_ZUSATZBRANCHE_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSKLASSE get_DOWN_RECORD_LIST_ADRESSKLASSE_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSKLASSE_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESSKLASSE_id_adresse = new RECLIST_ADRESSKLASSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSKLASSE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESSKLASSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSKLASSE_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSKLASSE get_DOWN_RECORD_LIST_ADRESSKLASSE_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSKLASSE_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSKLASSE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSKLASSE_id_adresse = new RECLIST_ADRESSKLASSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSKLASSE_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESS_ZUSATZWERTE get_DOWN_RECORD_LIST_ADRESS_ZUSATZWERTE_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESS_ZUSATZWERTE_id_adresse==null)
		{
			this.DOWN_RECLIST_ADRESS_ZUSATZWERTE_id_adresse = new RECLIST_ADRESS_ZUSATZWERTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESS_ZUSATZWERTE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ADRESS_ZUSATZWERTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESS_ZUSATZWERTE_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESS_ZUSATZWERTE get_DOWN_RECORD_LIST_ADRESS_ZUSATZWERTE_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESS_ZUSATZWERTE_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESS_ZUSATZWERTE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESS_ZUSATZWERTE_id_adresse = new RECLIST_ADRESS_ZUSATZWERTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESS_ZUSATZWERTE_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_GEO_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_geo_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_start==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_start = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_GEO_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_GEO_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_geo_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_GEO_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_start = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_GEO_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_geo_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_ziel==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_ziel = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_GEO_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_GEO_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_geo_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_GEO_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_ziel = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_geo_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE_JUR_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_jur_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_start==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_start = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_JUR_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_JUR_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_jur_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_JUR_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_start = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_JUR_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_jur_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_ziel==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_ziel = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_JUR_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_JUR_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_adresse_jur_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_ADRESSE_JUR_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_ziel = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_adresse_jur_ziel;
	}


	





	/**
	 * References the Field ID_1_ABFALLERZEUGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_abfallerzeuger() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_abfallerzeuger==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_abfallerzeuger = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_ABFALLERZEUGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_abfallerzeuger;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_1_ABFALLERZEUGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_abfallerzeuger(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_abfallerzeuger==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_ABFALLERZEUGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_abfallerzeuger = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_abfallerzeuger;
	}


	





	/**
	 * References the Field ID_1_IMPORT_EMPFAENGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_import_empfaenger() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_import_empfaenger==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_import_empfaenger = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_IMPORT_EMPFAENGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_import_empfaenger;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_1_IMPORT_EMPFAENGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_import_empfaenger(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_import_empfaenger==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_IMPORT_EMPFAENGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_import_empfaenger = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_import_empfaenger;
	}


	





	/**
	 * References the Field ID_1_VERBR_VERANLASSER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_verbr_veranlasser() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verbr_veranlasser==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verbr_veranlasser = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_VERBR_VERANLASSER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verbr_veranlasser;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_1_VERBR_VERANLASSER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_verbr_veranlasser(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verbr_veranlasser==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_VERBR_VERANLASSER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verbr_veranlasser = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verbr_veranlasser;
	}


	





	/**
	 * References the Field ID_1_VERWERTUNGSANLAGE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_verwertungsanlage() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verwertungsanlage==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verwertungsanlage = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_VERWERTUNGSANLAGE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verwertungsanlage;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_1_VERWERTUNGSANLAGE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_1_verwertungsanlage(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verwertungsanlage==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_1_VERWERTUNGSANLAGE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verwertungsanlage = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_1_verwertungsanlage;
	}


	





	/**
	 * References the Field ID_2_ABFALLERZEUGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_abfallerzeuger() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_abfallerzeuger==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_abfallerzeuger = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_ABFALLERZEUGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_abfallerzeuger;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_2_ABFALLERZEUGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_abfallerzeuger(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_abfallerzeuger==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_ABFALLERZEUGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_abfallerzeuger = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_abfallerzeuger;
	}


	





	/**
	 * References the Field ID_2_IMPORT_EMPFAENGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_import_empfaenger() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_import_empfaenger==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_import_empfaenger = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_IMPORT_EMPFAENGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_import_empfaenger;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_2_IMPORT_EMPFAENGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_import_empfaenger(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_import_empfaenger==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_IMPORT_EMPFAENGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_import_empfaenger = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_import_empfaenger;
	}


	





	/**
	 * References the Field ID_2_VERBR_VERANLASSER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_verbr_veranlasser() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verbr_veranlasser==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verbr_veranlasser = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_VERBR_VERANLASSER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verbr_veranlasser;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_2_VERBR_VERANLASSER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_verbr_veranlasser(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verbr_veranlasser==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_VERBR_VERANLASSER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verbr_veranlasser = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verbr_veranlasser;
	}


	





	/**
	 * References the Field ID_2_VERWERTUNGSANLAGE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_verwertungsanlage() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verwertungsanlage==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verwertungsanlage = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_VERWERTUNGSANLAGE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verwertungsanlage;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_2_VERWERTUNGSANLAGE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_2_verwertungsanlage(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verwertungsanlage==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_2_VERWERTUNGSANLAGE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verwertungsanlage = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_2_verwertungsanlage;
	}


	





	/**
	 * References the Field ID_3_ABFALLERZEUGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_abfallerzeuger() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_abfallerzeuger==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_abfallerzeuger = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_ABFALLERZEUGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_abfallerzeuger;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_3_ABFALLERZEUGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_abfallerzeuger(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_abfallerzeuger==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_ABFALLERZEUGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_abfallerzeuger = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_abfallerzeuger;
	}


	





	/**
	 * References the Field ID_3_IMPORT_EMPFAENGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_import_empfaenger() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_import_empfaenger==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_import_empfaenger = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_IMPORT_EMPFAENGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_import_empfaenger;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_3_IMPORT_EMPFAENGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_import_empfaenger(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_import_empfaenger==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_IMPORT_EMPFAENGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_import_empfaenger = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_import_empfaenger;
	}


	





	/**
	 * References the Field ID_3_VERBR_VERANLASSER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_verbr_veranlasser() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verbr_veranlasser==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verbr_veranlasser = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_VERBR_VERANLASSER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verbr_veranlasser;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_3_VERBR_VERANLASSER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_verbr_veranlasser(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verbr_veranlasser==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_VERBR_VERANLASSER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verbr_veranlasser = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verbr_veranlasser;
	}


	





	/**
	 * References the Field ID_3_VERWERTUNGSANLAGE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_verwertungsanlage() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verwertungsanlage==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verwertungsanlage = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_VERWERTUNGSANLAGE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verwertungsanlage;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_3_VERWERTUNGSANLAGE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_3_verwertungsanlage(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verwertungsanlage==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_3_VERWERTUNGSANLAGE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verwertungsanlage = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_3_verwertungsanlage;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_adresse==null)
		{
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_adresse = new RECLIST_ARTIKELBEZ_LIEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_ARTIKELBEZ_LIEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_adresse = new RECLIST_ARTIKELBEZ_LIEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BANKENSTAMM get_DOWN_RECORD_LIST_BANKENSTAMM_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BANKENSTAMM_id_adresse==null)
		{
			this.DOWN_RECLIST_BANKENSTAMM_id_adresse = new RECLIST_BANKENSTAMM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BANKENSTAMM WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BANKENSTAMM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BANKENSTAMM_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BANKENSTAMM get_DOWN_RECORD_LIST_BANKENSTAMM_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BANKENSTAMM_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BANKENSTAMM WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BANKENSTAMM_id_adresse = new RECLIST_BANKENSTAMM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BANKENSTAMM_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_WB_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_adresse_wb_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_start==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_start = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ADRESSE_WB_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_WB_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_adresse_wb_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ADRESSE_WB_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_start = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_WB_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_adresse_wb_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_ziel==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_ziel = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ADRESSE_WB_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_WB_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_adresse_wb_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ADRESSE_WB_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_ziel = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_adresse_wb_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_STATION get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse = new RECLIST_BEWEGUNG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_STATION get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse = new RECLIST_BEWEGUNG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_BESITZER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_STATION get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_adresse_besitzer() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse_besitzer==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse_besitzer = new RECLIST_BEWEGUNG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE ID_ADRESSE_BESITZER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse_besitzer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BESITZER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_STATION get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_adresse_besitzer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse_besitzer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE ID_ADRESSE_BESITZER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse_besitzer = new RECLIST_BEWEGUNG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_STATION_id_adresse_besitzer;
	}


	





	/**
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_adresse_fremdware() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_fremdware==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_fremdware = new RECLIST_BEWEGUNG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_fremdware;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_adresse_fremdware(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_fremdware==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_fremdware = new RECLIST_BEWEGUNG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_fremdware;
	}


	





	/**
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_adresse_spedition() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_spedition==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_spedition = new RECLIST_BEWEGUNG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_spedition;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_adresse_spedition(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_spedition==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_spedition = new RECLIST_BEWEGUNG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_spedition;
	}


	





	/**
	 * References the Field ID_ADRESSE_START_LOGISTIK 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik = new RECLIST_BEWEGUNG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_ADRESSE_START_LOGISTIK="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_START_LOGISTIK 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_ADRESSE_START_LOGISTIK="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik = new RECLIST_BEWEGUNG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_adresse_start_logistik;
	}


	





	/**
	 * References the Field ID_ADRESSE_BESITZER_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start = new RECLIST_BEWEGUNG_VEKTOR_PN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_BESITZER_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_PN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BESITZER_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_BESITZER_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start = new RECLIST_BEWEGUNG_VEKTOR_PN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_BESITZER_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel = new RECLIST_BEWEGUNG_VEKTOR_PN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_BESITZER_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_PN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BESITZER_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_BESITZER_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel = new RECLIST_BEWEGUNG_VEKTOR_PN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_besitzer_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_start==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_start = new RECLIST_BEWEGUNG_VEKTOR_PN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_PN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_start = new RECLIST_BEWEGUNG_VEKTOR_PN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel = new RECLIST_BEWEGUNG_VEKTOR_PN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_PN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel = new RECLIST_BEWEGUNG_VEKTOR_PN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_adresse_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE_1 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEZIEHUNG get_DOWN_RECORD_LIST_BEZIEHUNG_id_adresse_1() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEZIEHUNG_id_adresse_1==null)
		{
			this.DOWN_RECLIST_BEZIEHUNG_id_adresse_1 = new RECLIST_BEZIEHUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEZIEHUNG WHERE ID_ADRESSE_1="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEZIEHUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEZIEHUNG_id_adresse_1;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_1 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEZIEHUNG get_DOWN_RECORD_LIST_BEZIEHUNG_id_adresse_1(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEZIEHUNG_id_adresse_1==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEZIEHUNG WHERE ID_ADRESSE_1="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEZIEHUNG_id_adresse_1 = new RECLIST_BEZIEHUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEZIEHUNG_id_adresse_1;
	}


	





	/**
	 * References the Field ID_ADRESSE_2 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEZIEHUNG get_DOWN_RECORD_LIST_BEZIEHUNG_id_adresse_2() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEZIEHUNG_id_adresse_2==null)
		{
			this.DOWN_RECLIST_BEZIEHUNG_id_adresse_2 = new RECLIST_BEZIEHUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEZIEHUNG WHERE ID_ADRESSE_2="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BEZIEHUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEZIEHUNG_id_adresse_2;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_2 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEZIEHUNG get_DOWN_RECORD_LIST_BEZIEHUNG_id_adresse_2(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEZIEHUNG_id_adresse_2==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEZIEHUNG WHERE ID_ADRESSE_2="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEZIEHUNG_id_adresse_2 = new RECLIST_BEZIEHUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEZIEHUNG_id_adresse_2;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_adresse==null)
		{
			this.DOWN_RECLIST_BG_STATION_id_adresse = new RECLIST_BG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_STATION_id_adresse = new RECLIST_BG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_adresse_basis() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_adresse_basis==null)
		{
			this.DOWN_RECLIST_BG_STATION_id_adresse_basis = new RECLIST_BG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_adresse_basis;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_adresse_basis(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_adresse_basis==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_STATION_id_adresse_basis = new RECLIST_BG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_adresse_basis;
	}


	





	/**
	 * References the Field ID_ADRESSE_BESITZ_LDG 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_adresse_besitz_ldg() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_adresse_besitz_ldg==null)
		{
			this.DOWN_RECLIST_BG_STATION_id_adresse_besitz_ldg = new RECLIST_BG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_ADRESSE_BESITZ_LDG="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_adresse_besitz_ldg;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BESITZ_LDG 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_adresse_besitz_ldg(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_adresse_besitz_ldg==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_ADRESSE_BESITZ_LDG="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_STATION_id_adresse_besitz_ldg = new RECLIST_BG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_adresse_besitz_ldg;
	}


	





	/**
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_fremdware() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_fremdware==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_fremdware = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_fremdware;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_fremdware(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_fremdware==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_fremdware = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_fremdware;
	}


	





	/**
	 * References the Field ID_ADRESSE_LOGI_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_logi_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_start==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_start = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_LOGI_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LOGI_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_logi_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_LOGI_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_start = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_LOGI_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_logi_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_ziel==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_ziel = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_LOGI_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LOGI_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_logi_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_LOGI_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_ziel = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_logi_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_spedition() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_spedition==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_spedition = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_spedition;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_adresse_spedition(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_adresse_spedition==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_adresse_spedition = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_adresse_spedition;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_adresse==null)
		{
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_adresse = new RECLIST_CONTAINER_ZYKLUS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_CONTAINER_ZYKLUS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_adresse = new RECLIST_CONTAINER_ZYKLUS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_BUCHUNGSLAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_buchungslager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_buchungslager==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_buchungslager = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_BUCHUNGSLAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_buchungslager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BUCHUNGSLAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_buchungslager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_buchungslager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_BUCHUNGSLAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_buchungslager = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_buchungslager;
	}


	





	/**
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_fremdware() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_fremdware==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_fremdware = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_fremdware;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_fremdware(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_fremdware==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_fremdware = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_fremdware;
	}


	





	/**
	 * References the Field ID_ADRESSE_RECHNUNG 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_rechnung() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_rechnung==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_rechnung = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_RECHNUNG="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_rechnung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_RECHNUNG 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_rechnung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_rechnung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_RECHNUNG="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_rechnung = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_rechnung;
	}


	





	/**
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_start==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_start = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_start = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_ziel==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_ziel = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_adresse_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_adresse_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_adresse_ziel = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_adresse_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL get_DOWN_RECORD_LIST_EMAIL_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_id_adresse==null)
		{
			this.DOWN_RECLIST_EMAIL_id_adresse = new RECLIST_EMAIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_EMAIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL get_DOWN_RECORD_LIST_EMAIL_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EMAIL_id_adresse = new RECLIST_EMAIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_adresse_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_start==null)
		{
			this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_start = new RECLIST_FAHRPLANPOS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_FAHRPLANPOS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_adresse_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_start = new RECLIST_FAHRPLANPOS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_adresse_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_ziel==null)
		{
			this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_ziel = new RECLIST_FAHRPLANPOS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_FAHRPLANPOS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_adresse_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_ziel = new RECLIST_FAHRPLANPOS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_adresse_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRTENVARIANTEN get_DOWN_RECORD_LIST_FAHRTENVARIANTEN_id_adresse_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_start==null)
		{
			this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_start = new RECLIST_FAHRTENVARIANTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRTENVARIANTEN WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_FAHRTENVARIANTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRTENVARIANTEN get_DOWN_RECORD_LIST_FAHRTENVARIANTEN_id_adresse_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRTENVARIANTEN WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_start = new RECLIST_FAHRTENVARIANTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRTENVARIANTEN get_DOWN_RECORD_LIST_FAHRTENVARIANTEN_id_adresse_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_ziel==null)
		{
			this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_ziel = new RECLIST_FAHRTENVARIANTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRTENVARIANTEN WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_FAHRTENVARIANTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRTENVARIANTEN get_DOWN_RECORD_LIST_FAHRTENVARIANTEN_id_adresse_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRTENVARIANTEN WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_ziel = new RECLIST_FAHRTENVARIANTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRTENVARIANTEN_id_adresse_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU get_DOWN_RECORD_LIST_FIBU_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_id_adresse==null)
		{
			this.DOWN_RECLIST_FIBU_id_adresse = new RECLIST_FIBU (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_FIBU",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU get_DOWN_RECORD_LIST_FIBU_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_id_adresse = new RECLIST_FIBU (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_id_adresse==null)
		{
			this.DOWN_RECLIST_FIBU_KONTENREGEL_id_adresse = new RECLIST_FIBU_KONTENREGEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_FIBU_KONTENREGEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_KONTENREGEL_id_adresse = new RECLIST_FIBU_KONTENREGEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIRMENINFO get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIRMENINFO_id_adresse==null)
		{
			this.DOWN_RECLIST_FIRMENINFO_id_adresse = new RECLIST_FIRMENINFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_FIRMENINFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIRMENINFO_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIRMENINFO get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIRMENINFO_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIRMENINFO_id_adresse = new RECLIST_FIRMENINFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIRMENINFO_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INTERNET get_DOWN_RECORD_LIST_INTERNET_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INTERNET_id_adresse==null)
		{
			this.DOWN_RECLIST_INTERNET_id_adresse = new RECLIST_INTERNET (
			       "SELECT * FROM "+bibE2.cTO()+".JT_INTERNET WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_INTERNET",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_INTERNET_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INTERNET get_DOWN_RECORD_LIST_INTERNET_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INTERNET_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_INTERNET WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_INTERNET_id_adresse = new RECLIST_INTERNET (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_INTERNET_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOMMUNIKATION get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOMMUNIKATION_id_adresse==null)
		{
			this.DOWN_RECLIST_KOMMUNIKATION_id_adresse = new RECLIST_KOMMUNIKATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KOMMUNIKATION WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_KOMMUNIKATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KOMMUNIKATION_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOMMUNIKATION get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOMMUNIKATION_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KOMMUNIKATION WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KOMMUNIKATION_id_adresse = new RECLIST_KOMMUNIKATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KOMMUNIKATION_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KONTO get_DOWN_RECORD_LIST_KONTO_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KONTO_id_adresse==null)
		{
			this.DOWN_RECLIST_KONTO_id_adresse = new RECLIST_KONTO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KONTO WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_KONTO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KONTO_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KONTO get_DOWN_RECORD_LIST_KONTO_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KONTO_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KONTO WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KONTO_id_adresse = new RECLIST_KONTO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KONTO_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOSTEN_LIEFERBED_ADR get_DOWN_RECORD_LIST_KOSTEN_LIEFERBED_ADR_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse==null)
		{
			this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse = new RECLIST_KOSTEN_LIEFERBED_ADR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_KOSTEN_LIEFERBED_ADR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOSTEN_LIEFERBED_ADR get_DOWN_RECORD_LIST_KOSTEN_LIEFERBED_ADR_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse = new RECLIST_KOSTEN_LIEFERBED_ADR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOSTEN_LIEFERBED_ADR get_DOWN_RECORD_LIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis==null)
		{
			this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis = new RECLIST_KOSTEN_LIEFERBED_ADR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_KOSTEN_LIEFERBED_ADR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOSTEN_LIEFERBED_ADR get_DOWN_RECORD_LIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis = new RECLIST_KOSTEN_LIEFERBED_ADR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_basis;
	}


	





	/**
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOSTEN_LIEFERBED_ADR get_DOWN_RECORD_LIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel==null)
		{
			this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel = new RECLIST_KOSTEN_LIEFERBED_ADR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_KOSTEN_LIEFERBED_ADR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KOSTEN_LIEFERBED_ADR get_DOWN_RECORD_LIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel = new RECLIST_KOSTEN_LIEFERBED_ADR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KOSTEN_LIEFERBED_ADR_id_adresse_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KREDITVERS_ADRESSE get_DOWN_RECORD_LIST_KREDITVERS_ADRESSE_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KREDITVERS_ADRESSE_id_adresse==null)
		{
			this.DOWN_RECLIST_KREDITVERS_ADRESSE_id_adresse = new RECLIST_KREDITVERS_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KREDITVERS_ADRESSE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_KREDITVERS_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KREDITVERS_ADRESSE_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KREDITVERS_ADRESSE get_DOWN_RECORD_LIST_KREDITVERS_ADRESSE_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KREDITVERS_ADRESSE_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KREDITVERS_ADRESSE WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KREDITVERS_ADRESSE_id_adresse = new RECLIST_KREDITVERS_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KREDITVERS_ADRESSE_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KUNDE_MWST get_DOWN_RECORD_LIST_KUNDE_MWST_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KUNDE_MWST_id_adresse==null)
		{
			this.DOWN_RECLIST_KUNDE_MWST_id_adresse = new RECLIST_KUNDE_MWST (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KUNDE_MWST WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_KUNDE_MWST",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KUNDE_MWST_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KUNDE_MWST get_DOWN_RECORD_LIST_KUNDE_MWST_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KUNDE_MWST_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KUNDE_MWST WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KUNDE_MWST_id_adresse = new RECLIST_KUNDE_MWST (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KUNDE_MWST_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_KONTO get_DOWN_RECORD_LIST_LAGER_KONTO_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_KONTO_id_adresse_lager==null)
		{
			this.DOWN_RECLIST_LAGER_KONTO_id_adresse_lager = new RECLIST_LAGER_KONTO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LAGER_KONTO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_KONTO_id_adresse_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_KONTO get_DOWN_RECORD_LIST_LAGER_KONTO_id_adresse_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_KONTO_id_adresse_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_KONTO_id_adresse_lager = new RECLIST_LAGER_KONTO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_KONTO_id_adresse_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE_AUSBUCH_HAND 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_adresse_ausbuch_hand() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_ausbuch_hand==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_ausbuch_hand = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_ADRESSE_AUSBUCH_HAND="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_ausbuch_hand;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_AUSBUCH_HAND 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_adresse_ausbuch_hand(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_ausbuch_hand==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_ADRESSE_AUSBUCH_HAND="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_ausbuch_hand = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_ausbuch_hand;
	}


	





	/**
	 * References the Field ID_ADRESSE_EINBUCH_HAND 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_adresse_einbuch_hand() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_einbuch_hand==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_einbuch_hand = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_ADRESSE_EINBUCH_HAND="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_einbuch_hand;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_EINBUCH_HAND 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_adresse_einbuch_hand(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_einbuch_hand==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_ADRESSE_EINBUCH_HAND="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_einbuch_hand = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_adresse_einbuch_hand;
	}


	





	/**
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_basis==null)
		{
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_basis = new RECLIST_LIEFERADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LIEFERADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_basis;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_basis==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_basis = new RECLIST_LIEFERADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_basis;
	}


	





	/**
	 * References the Field ID_ADRESSE_BESITZER_WARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_besitzer_ware() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware==null)
		{
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware = new RECLIST_LIEFERADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BESITZER_WARE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LIEFERADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BESITZER_WARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_besitzer_ware(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BESITZER_WARE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware = new RECLIST_LIEFERADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware;
	}


	





	/**
	 * References the Field ID_ADRESSE_BESITZER_WARE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager==null)
		{
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager = new RECLIST_LIEFERADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BESITZER_WARE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LIEFERADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BESITZER_WARE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BESITZER_WARE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager = new RECLIST_LIEFERADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitzer_ware_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE_BESITZ_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_besitz_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitz_lager==null)
		{
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitz_lager = new RECLIST_LIEFERADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BESITZ_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LIEFERADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitz_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BESITZ_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_besitz_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitz_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BESITZ_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitz_lager = new RECLIST_LIEFERADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_besitz_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_fremdware() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_fremdware==null)
		{
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_fremdware = new RECLIST_LIEFERADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LIEFERADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_fremdware;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_FREMDWARE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_fremdware(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_fremdware==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_FREMDWARE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_fremdware = new RECLIST_LIEFERADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_fremdware;
	}


	





	/**
	 * References the Field ID_ADRESSE_LIEFER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_liefer==null)
		{
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_liefer = new RECLIST_LIEFERADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_LIEFER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_LIEFERADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_liefer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LIEFER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LIEFERADRESSE get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_liefer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_LIEFER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_liefer = new RECLIST_LIEFERADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LIEFERADRESSE_id_adresse_liefer;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAT_SPEZ get_DOWN_RECORD_LIST_MAT_SPEZ_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAT_SPEZ_id_adresse==null)
		{
			this.DOWN_RECLIST_MAT_SPEZ_id_adresse = new RECLIST_MAT_SPEZ (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAT_SPEZ WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_MAT_SPEZ",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAT_SPEZ_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAT_SPEZ get_DOWN_RECORD_LIST_MAT_SPEZ_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAT_SPEZ_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAT_SPEZ WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAT_SPEZ_id_adresse = new RECLIST_MAT_SPEZ (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAT_SPEZ_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MITARBEITER get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MITARBEITER_id_adresse_basis==null)
		{
			this.DOWN_RECLIST_MITARBEITER_id_adresse_basis = new RECLIST_MITARBEITER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_MITARBEITER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MITARBEITER_id_adresse_basis;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_BASIS 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MITARBEITER get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MITARBEITER_id_adresse_basis==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MITARBEITER_id_adresse_basis = new RECLIST_MITARBEITER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MITARBEITER_id_adresse_basis;
	}


	





	/**
	 * References the Field ID_ADRESSE_MITARBEITER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MITARBEITER get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_mitarbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MITARBEITER_id_adresse_mitarbeiter==null)
		{
			this.DOWN_RECLIST_MITARBEITER_id_adresse_mitarbeiter = new RECLIST_MITARBEITER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_MITARBEITER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_MITARBEITER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MITARBEITER_id_adresse_mitarbeiter;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_MITARBEITER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MITARBEITER get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_mitarbeiter(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MITARBEITER_id_adresse_mitarbeiter==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_MITARBEITER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MITARBEITER_id_adresse_mitarbeiter = new RECLIST_MITARBEITER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MITARBEITER_id_adresse_mitarbeiter;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_ADRESSEN get_DOWN_RECORD_LIST_PRO_ADRESSEN_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_ADRESSEN_id_adresse==null)
		{
			this.DOWN_RECLIST_PRO_ADRESSEN_id_adresse = new RECLIST_PRO_ADRESSEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PRO_ADRESSEN WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_PRO_ADRESSEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_ADRESSEN_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_ADRESSEN get_DOWN_RECORD_LIST_PRO_ADRESSEN_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_ADRESSEN_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PRO_ADRESSEN WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PRO_ADRESSEN_id_adresse = new RECLIST_PRO_ADRESSEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_ADRESSEN_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_KAEUFER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_adresse_kaeufer() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_kaeufer==null)
		{
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_kaeufer = new RECLIST_PROFORMA_RECHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_ADRESSE_KAEUFER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_PROFORMA_RECHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_kaeufer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_KAEUFER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_adresse_kaeufer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_kaeufer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_ADRESSE_KAEUFER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_kaeufer = new RECLIST_PROFORMA_RECHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_kaeufer;
	}


	





	/**
	 * References the Field ID_ADRESSE_VERTRETER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_adresse_vertreter() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_vertreter==null)
		{
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_vertreter = new RECLIST_PROFORMA_RECHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_ADRESSE_VERTRETER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_PROFORMA_RECHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_vertreter;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_VERTRETER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROFORMA_RECHNUNG get_DOWN_RECORD_LIST_PROFORMA_RECHNUNG_id_adresse_vertreter(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_vertreter==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PROFORMA_RECHNUNG WHERE ID_ADRESSE_VERTRETER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_vertreter = new RECLIST_PROFORMA_RECHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PROFORMA_RECHNUNG_id_adresse_vertreter;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SANKTION_PRUEFUNG get_DOWN_RECORD_LIST_SANKTION_PRUEFUNG_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SANKTION_PRUEFUNG_id_adresse==null)
		{
			this.DOWN_RECLIST_SANKTION_PRUEFUNG_id_adresse = new RECLIST_SANKTION_PRUEFUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_SANKTION_PRUEFUNG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_SANKTION_PRUEFUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_SANKTION_PRUEFUNG_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SANKTION_PRUEFUNG get_DOWN_RECORD_LIST_SANKTION_PRUEFUNG_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SANKTION_PRUEFUNG_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_SANKTION_PRUEFUNG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_SANKTION_PRUEFUNG_id_adresse = new RECLIST_SANKTION_PRUEFUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_SANKTION_PRUEFUNG_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SONDERZEITEN get_DOWN_RECORD_LIST_SONDERZEITEN_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SONDERZEITEN_id_adresse==null)
		{
			this.DOWN_RECLIST_SONDERZEITEN_id_adresse = new RECLIST_SONDERZEITEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_SONDERZEITEN WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_SONDERZEITEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_SONDERZEITEN_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SONDERZEITEN get_DOWN_RECORD_LIST_SONDERZEITEN_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SONDERZEITEN_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_SONDERZEITEN WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_SONDERZEITEN_id_adresse = new RECLIST_SONDERZEITEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_SONDERZEITEN_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_adresse==null)
		{
			this.DOWN_RECLIST_UMA_KONTRAKT_id_adresse = new RECLIST_UMA_KONTRAKT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_UMA_KONTRAKT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_UMA_KONTRAKT_id_adresse = new RECLIST_UMA_KONTRAKT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_adresse==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_id_adresse = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_id_adresse = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_adresse==null)
		{
			this.DOWN_RECLIST_VKOPF_RG_id_adresse = new RECLIST_VKOPF_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VKOPF_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_RG_id_adresse = new RECLIST_VKOPF_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_adresse==null)
		{
			this.DOWN_RECLIST_VKOPF_STD_id_adresse = new RECLIST_VKOPF_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VKOPF_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_STD_id_adresse = new RECLIST_VKOPF_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_adresse==null)
		{
			this.DOWN_RECLIST_VKOPF_TPA_id_adresse = new RECLIST_VKOPF_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VKOPF_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_TPA_id_adresse = new RECLIST_VKOPF_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_adresse==null)
		{
			this.DOWN_RECLIST_VPOS_KON_id_adresse = new RECLIST_VPOS_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_id_adresse = new RECLIST_VPOS_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_adresse_lager==null)
		{
			this.DOWN_RECLIST_VPOS_KON_id_adresse_lager = new RECLIST_VPOS_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_adresse_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_adresse_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_adresse_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_id_adresse_lager = new RECLIST_VPOS_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_adresse_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_LAGER get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_LAGER_id_adresse_lager==null)
		{
			this.DOWN_RECLIST_VPOS_KON_LAGER_id_adresse_lager = new RECLIST_VPOS_KON_LAGER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_KON_LAGER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_LAGER_id_adresse_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_LAGER get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_adresse_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_LAGER_id_adresse_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_LAGER_id_adresse_lager = new RECLIST_VPOS_KON_LAGER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_LAGER_id_adresse_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_adresse==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_adresse = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_adresse = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_adresse==null)
		{
			this.DOWN_RECLIST_VPOS_STD_id_adresse = new RECLIST_VPOS_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_STD_id_adresse = new RECLIST_VPOS_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_adresse_lager==null)
		{
			this.DOWN_RECLIST_VPOS_STD_id_adresse_lager = new RECLIST_VPOS_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_adresse_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_adresse_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_adresse_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_STD_id_adresse_lager = new RECLIST_VPOS_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_adresse_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_adresse==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_id_adresse = new RECLIST_VPOS_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_id_adresse = new RECLIST_VPOS_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_adresse_lager==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_id_adresse_lager = new RECLIST_VPOS_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_adresse_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_adresse_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_adresse_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_id_adresse_lager = new RECLIST_VPOS_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_adresse_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE_FREMDAUFTRAG 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_FREMDAUFTRAG="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_FREMDAUFTRAG 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_FREMDAUFTRAG="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_lager_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_start==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_start = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_LAGER_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_lager_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_LAGER_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_start = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_LAGER_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_LAGER_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_lager_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_spedition() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_spedition==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_spedition = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_spedition;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_spedition(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_spedition==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_spedition = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_spedition;
	}


	





	/**
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_start==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_start = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_start;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_START 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_start(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_start==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_START="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_start = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_start;
	}


	





	/**
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_ziel==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_ziel = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_ZIEL 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ADRESSE_ZIEL="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_ziel = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_adresse_ziel;
	}


	





	/**
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_KOSTEN get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition = new RECLIST_VPOS_TPA_FUHRE_KOSTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_KOSTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_KOSTEN get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition = new RECLIST_VPOS_TPA_FUHRE_KOSTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_KOSTEN_id_adresse_spedition;
	}


	





	/**
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_adresse() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_adresse(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ADRESSE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_adresse_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE_ABN_STRECKE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_abn_strecke() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_abn_strecke==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_abn_strecke = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_ABN_STRECKE="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_abn_strecke;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_ABN_STRECKE 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_abn_strecke(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_abn_strecke==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_ABN_STRECKE="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_abn_strecke = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_abn_strecke;
	}


	





	/**
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lager==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lager = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lager;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LAGER 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_lager(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lager==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lager = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lager;
	}


	





	/**
	 * References the Field ID_ADRESSE_LIEFERANT 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_lieferant() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lieferant==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lieferant = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_LIEFERANT="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lieferant;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_LIEFERANT 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_lieferant(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lieferant==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_LIEFERANT="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lieferant = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_lieferant;
	}


	





	/**
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_spedition() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_spedition==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_spedition = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_spedition;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ADRESSE_SPEDITION 
	 * Falls keine get_ID_ADRESSE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_adresse_spedition(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_adresse_spedition==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ADRESSE_SPEDITION="+this.get_ID_ADRESSE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_adresse_spedition = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_adresse_spedition;
	}


	





	
	/**
	 * References the Field ID_ADRESSE_EU_VERTR_FORM
	 * Falls keine this.get_ID_ADRESSE_EU_VERTR_FORM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE_EU_VERTR_FORM get_UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_EU_VERTR_FORM_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form==null)
		{
			this.UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form = new RECORD_ADRESSE_EU_VERTR_FORM(this.get_ID_ADRESSE_EU_VERTR_FORM_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_MERKMAL1
	 * Falls keine this.get_ID_ADRESSE_MERKMAL1_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE_MERKMAL1 get_UP_RECORD_ADRESSE_MERKMAL1_id_adresse_merkmal1() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_MERKMAL1_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_MERKMAL1_id_adresse_merkmal1==null)
		{
			this.UP_RECORD_ADRESSE_MERKMAL1_id_adresse_merkmal1 = new RECORD_ADRESSE_MERKMAL1(this.get_ID_ADRESSE_MERKMAL1_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_MERKMAL1_id_adresse_merkmal1;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_MERKMAL2
	 * Falls keine this.get_ID_ADRESSE_MERKMAL2_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE_MERKMAL2 get_UP_RECORD_ADRESSE_MERKMAL2_id_adresse_merkmal2() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_MERKMAL2_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_MERKMAL2_id_adresse_merkmal2==null)
		{
			this.UP_RECORD_ADRESSE_MERKMAL2_id_adresse_merkmal2 = new RECORD_ADRESSE_MERKMAL2(this.get_ID_ADRESSE_MERKMAL2_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_MERKMAL2_id_adresse_merkmal2;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_MERKMAL3
	 * Falls keine this.get_ID_ADRESSE_MERKMAL3_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE_MERKMAL3 get_UP_RECORD_ADRESSE_MERKMAL3_id_adresse_merkmal3() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_MERKMAL3_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_MERKMAL3_id_adresse_merkmal3==null)
		{
			this.UP_RECORD_ADRESSE_MERKMAL3_id_adresse_merkmal3 = new RECORD_ADRESSE_MERKMAL3(this.get_ID_ADRESSE_MERKMAL3_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_MERKMAL3_id_adresse_merkmal3;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_MERKMAL4
	 * Falls keine this.get_ID_ADRESSE_MERKMAL4_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE_MERKMAL4 get_UP_RECORD_ADRESSE_MERKMAL4_id_adresse_merkmal4() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_MERKMAL4_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_MERKMAL4_id_adresse_merkmal4==null)
		{
			this.UP_RECORD_ADRESSE_MERKMAL4_id_adresse_merkmal4 = new RECORD_ADRESSE_MERKMAL4(this.get_ID_ADRESSE_MERKMAL4_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_MERKMAL4_id_adresse_merkmal4;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_MERKMAL5
	 * Falls keine this.get_ID_ADRESSE_MERKMAL5_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE_MERKMAL5 get_UP_RECORD_ADRESSE_MERKMAL5_id_adresse_merkmal5() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_MERKMAL5_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_MERKMAL5_id_adresse_merkmal5==null)
		{
			this.UP_RECORD_ADRESSE_MERKMAL5_id_adresse_merkmal5 = new RECORD_ADRESSE_MERKMAL5(this.get_ID_ADRESSE_MERKMAL5_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_MERKMAL5_id_adresse_merkmal5;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_POTENTIAL
	 * Falls keine this.get_ID_ADRESSE_POTENTIAL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE_POTENTIAL get_UP_RECORD_ADRESSE_POTENTIAL_id_adresse_potential() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_POTENTIAL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_POTENTIAL_id_adresse_potential==null)
		{
			this.UP_RECORD_ADRESSE_POTENTIAL_id_adresse_potential = new RECORD_ADRESSE_POTENTIAL(this.get_ID_ADRESSE_POTENTIAL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_POTENTIAL_id_adresse_potential;
	}
	





	
	/**
	 * References the Field ID_ANREDE
	 * Falls keine this.get_ID_ANREDE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ANREDE get_UP_RECORD_ANREDE_id_anrede() throws myException
	{
		if (S.isEmpty(this.get_ID_ANREDE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ANREDE_id_anrede==null)
		{
			this.UP_RECORD_ANREDE_id_anrede = new RECORD_ANREDE(this.get_ID_ANREDE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ANREDE_id_anrede;
	}
	





	
	/**
	 * References the Field ID_LIEFERBEDINGUNGEN
	 * Falls keine this.get_ID_LIEFERBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LIEFERBEDINGUNGEN get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_LIEFERBEDINGUNGEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen==null)
		{
			this.UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen = new RECORD_LIEFERBEDINGUNGEN(this.get_ID_LIEFERBEDINGUNGEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen;
	}
	





	
	/**
	 * References the Field ID_LIEFERBEDINGUNGEN_VK
	 * Falls keine this.get_ID_LIEFERBEDINGUNGEN_VK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LIEFERBEDINGUNGEN get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_LIEFERBEDINGUNGEN_VK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk==null)
		{
			this.UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk = new RECORD_LIEFERBEDINGUNGEN(this.get_ID_LIEFERBEDINGUNGEN_VK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk;
	}
	





	
	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN
	 * Falls keine this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ZAHLUNGSBEDINGUNGEN get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen==null)
		{
			this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen = new RECORD_ZAHLUNGSBEDINGUNGEN(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen;
	}
	





	
	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN_VK
	 * Falls keine this.get_ID_ZAHLUNGSBEDINGUNGEN_VK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ZAHLUNGSBEDINGUNGEN get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_VK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk==null)
		{
			this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk = new RECORD_ZAHLUNGSBEDINGUNGEN(this.get_ID_ZAHLUNGSBEDINGUNGEN_VK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk;
	}
	

	public static String FIELD__ABN_NR = "ABN_NR";
	public static String FIELD__ADRESSTYP = "ADRESSTYP";
	public static String FIELD__AH7_QUELLE_SICHER = "AH7_QUELLE_SICHER";
	public static String FIELD__AH7_ZIEL_SICHER = "AH7_ZIEL_SICHER";
	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__AUSWEIS_ABLAUF_DATUM = "AUSWEIS_ABLAUF_DATUM";
	public static String FIELD__AUSWEIS_NUMMER = "AUSWEIS_NUMMER";
	public static String FIELD__BARKUNDE = "BARKUNDE";
	public static String FIELD__BEMERKUNGEN = "BEMERKUNGEN";
	public static String FIELD__BEMERKUNG_FAHRPLAN = "BEMERKUNG_FAHRPLAN";
	public static String FIELD__ERSTKONTAKT = "ERSTKONTAKT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EU_BEIBLATT_ANSPRECH = "EU_BEIBLATT_ANSPRECH";
	public static String FIELD__EU_BEIBLATT_EK_VERTRAG = "EU_BEIBLATT_EK_VERTRAG";
	public static String FIELD__EU_BEIBLATT_EMAIL = "EU_BEIBLATT_EMAIL";
	public static String FIELD__EU_BEIBLATT_FAX = "EU_BEIBLATT_FAX";
	public static String FIELD__EU_BEIBLATT_INFOFELD = "EU_BEIBLATT_INFOFELD";
	public static String FIELD__EU_BEIBLATT_TEL = "EU_BEIBLATT_TEL";
	public static String FIELD__EU_BEIBLATT_VK_VERTRAG = "EU_BEIBLATT_VK_VERTRAG";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEBURTSDATUM = "GEBURTSDATUM";
	public static String FIELD__GUTSCHRIFTEN_SPERREN = "GUTSCHRIFTEN_SPERREN";
	public static String FIELD__HAUSNUMMER = "HAUSNUMMER";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_ADRESSE_EU_VERTR_FORM = "ID_ADRESSE_EU_VERTR_FORM";
	public static String FIELD__ID_ADRESSE_MERKMAL1 = "ID_ADRESSE_MERKMAL1";
	public static String FIELD__ID_ADRESSE_MERKMAL2 = "ID_ADRESSE_MERKMAL2";
	public static String FIELD__ID_ADRESSE_MERKMAL3 = "ID_ADRESSE_MERKMAL3";
	public static String FIELD__ID_ADRESSE_MERKMAL4 = "ID_ADRESSE_MERKMAL4";
	public static String FIELD__ID_ADRESSE_MERKMAL5 = "ID_ADRESSE_MERKMAL5";
	public static String FIELD__ID_ADRESSE_POTENTIAL = "ID_ADRESSE_POTENTIAL";
	public static String FIELD__ID_ANREDE = "ID_ANREDE";
	public static String FIELD__ID_LAND = "ID_LAND";
	public static String FIELD__ID_LIEFERBEDINGUNGEN = "ID_LIEFERBEDINGUNGEN";
	public static String FIELD__ID_LIEFERBEDINGUNGEN_VK = "ID_LIEFERBEDINGUNGEN_VK";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_SPRACHE = "ID_SPRACHE";
	public static String FIELD__ID_USER = "ID_USER";
	public static String FIELD__ID_USER_ERSATZ = "ID_USER_ERSATZ";
	public static String FIELD__ID_USER_LAGER_HAENDLER = "ID_USER_LAGER_HAENDLER";
	public static String FIELD__ID_USER_LAGER_SACHBEARB = "ID_USER_LAGER_SACHBEARB";
	public static String FIELD__ID_USER_SACHBEARBEITER = "ID_USER_SACHBEARBEITER";
	public static String FIELD__ID_WAEHRUNG = "ID_WAEHRUNG";
	public static String FIELD__ID_ZAHLUNGSBEDINGUNGEN = "ID_ZAHLUNGSBEDINGUNGEN";
	public static String FIELD__ID_ZAHLUNGSBEDINGUNGEN_VK = "ID_ZAHLUNGSBEDINGUNGEN_VK";
	public static String FIELD__IS_POB_ACTIVE = "IS_POB_ACTIVE";
	public static String FIELD__KDNR = "KDNR";
	public static String FIELD__LAGER_KONTROLLINFO = "LAGER_KONTROLLINFO";
	public static String FIELD__LAGER_ZUSTAENDIG_EXTERN = "LAGER_ZUSTAENDIG_EXTERN";
	public static String FIELD__LATITUDE = "LATITUDE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERINFO_TPA = "LIEFERINFO_TPA";
	public static String FIELD__LIEF_NR = "LIEF_NR";
	public static String FIELD__LONGITUDE = "LONGITUDE";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__ORT = "ORT";
	public static String FIELD__ORTZUSATZ = "ORTZUSATZ";
	public static String FIELD__PLZ = "PLZ";
	public static String FIELD__PLZ_POB = "PLZ_POB";
	public static String FIELD__POB = "POB";
	public static String FIELD__RECHNUNGEN_SPERREN = "RECHNUNGEN_SPERREN";
	public static String FIELD__RECHNUNG_PER_FAX = "RECHNUNG_PER_FAX";
	public static String FIELD__SONDERLAGER = "SONDERLAGER";
	public static String FIELD__STRASSE = "STRASSE";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TRANSFER = "TRANSFER";
	public static String FIELD__VORNAME = "VORNAME";
	public static String FIELD__WARENAUSGANG_SPERREN = "WARENAUSGANG_SPERREN";
	public static String FIELD__WARENEINGANG_SPERREN = "WARENEINGANG_SPERREN";


	public String get_ABN_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABN_NR");
	}

	public String get_ABN_NR_cF() throws myException
	{
		return this.get_FormatedValue("ABN_NR");	
	}

	public String get_ABN_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABN_NR");
	}

	public String get_ABN_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABN_NR",cNotNullValue);
	}

	public String get_ABN_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABN_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABN_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABN_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABN_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABN_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABN_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABN_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABN_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABN_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABN_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABN_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABN_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABN_NR", calNewValueFormated);
	}
		public String get_ADRESSTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ADRESSTYP");
	}

	public String get_ADRESSTYP_cF() throws myException
	{
		return this.get_FormatedValue("ADRESSTYP");	
	}

	public String get_ADRESSTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ADRESSTYP");
	}

	public String get_ADRESSTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ADRESSTYP",cNotNullValue);
	}

	public String get_ADRESSTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ADRESSTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ADRESSTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ADRESSTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ADRESSTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ADRESSTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ADRESSTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ADRESSTYP", calNewValueFormated);
	}
		public Long get_ADRESSTYP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ADRESSTYP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ADRESSTYP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ADRESSTYP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ADRESSTYP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ADRESSTYP").getDoubleValue();
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
	public String get_ADRESSTYP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ADRESSTYP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ADRESSTYP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ADRESSTYP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ADRESSTYP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ADRESSTYP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ADRESSTYP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ADRESSTYP").getBigDecimalValue();
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
	
	
	public String get_AH7_QUELLE_SICHER_cUF() throws myException
	{
		return this.get_UnFormatedValue("AH7_QUELLE_SICHER");
	}

	public String get_AH7_QUELLE_SICHER_cF() throws myException
	{
		return this.get_FormatedValue("AH7_QUELLE_SICHER");	
	}

	public String get_AH7_QUELLE_SICHER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AH7_QUELLE_SICHER");
	}

	public String get_AH7_QUELLE_SICHER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AH7_QUELLE_SICHER",cNotNullValue);
	}

	public String get_AH7_QUELLE_SICHER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AH7_QUELLE_SICHER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AH7_QUELLE_SICHER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AH7_QUELLE_SICHER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AH7_QUELLE_SICHER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_QUELLE_SICHER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_QUELLE_SICHER", calNewValueFormated);
	}
		public boolean is_AH7_QUELLE_SICHER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_AH7_QUELLE_SICHER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_AH7_QUELLE_SICHER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_AH7_QUELLE_SICHER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_AH7_ZIEL_SICHER_cUF() throws myException
	{
		return this.get_UnFormatedValue("AH7_ZIEL_SICHER");
	}

	public String get_AH7_ZIEL_SICHER_cF() throws myException
	{
		return this.get_FormatedValue("AH7_ZIEL_SICHER");	
	}

	public String get_AH7_ZIEL_SICHER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AH7_ZIEL_SICHER");
	}

	public String get_AH7_ZIEL_SICHER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AH7_ZIEL_SICHER",cNotNullValue);
	}

	public String get_AH7_ZIEL_SICHER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AH7_ZIEL_SICHER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AH7_ZIEL_SICHER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AH7_ZIEL_SICHER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AH7_ZIEL_SICHER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_ZIEL_SICHER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_ZIEL_SICHER", calNewValueFormated);
	}
		public boolean is_AH7_ZIEL_SICHER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_AH7_ZIEL_SICHER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_AH7_ZIEL_SICHER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_AH7_ZIEL_SICHER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_AKTIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("AKTIV");
	}

	public String get_AKTIV_cF() throws myException
	{
		return this.get_FormatedValue("AKTIV");	
	}

	public String get_AKTIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AKTIV");
	}

	public String get_AKTIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AKTIV",cNotNullValue);
	}

	public String get_AKTIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AKTIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AKTIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AKTIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIV", calNewValueFormated);
	}
		public boolean is_AKTIV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_AKTIV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_AKTIV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_AKTIV_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_AUSWEIS_ABLAUF_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUSWEIS_ABLAUF_DATUM");
	}

	public String get_AUSWEIS_ABLAUF_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("AUSWEIS_ABLAUF_DATUM");	
	}

	public String get_AUSWEIS_ABLAUF_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUSWEIS_ABLAUF_DATUM");
	}

	public String get_AUSWEIS_ABLAUF_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUSWEIS_ABLAUF_DATUM",cNotNullValue);
	}

	public String get_AUSWEIS_ABLAUF_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUSWEIS_ABLAUF_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUSWEIS_ABLAUF_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_ABLAUF_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWEIS_ABLAUF_DATUM", calNewValueFormated);
	}
		public String get_AUSWEIS_NUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUSWEIS_NUMMER");
	}

	public String get_AUSWEIS_NUMMER_cF() throws myException
	{
		return this.get_FormatedValue("AUSWEIS_NUMMER");	
	}

	public String get_AUSWEIS_NUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUSWEIS_NUMMER");
	}

	public String get_AUSWEIS_NUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUSWEIS_NUMMER",cNotNullValue);
	}

	public String get_AUSWEIS_NUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUSWEIS_NUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUSWEIS_NUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUSWEIS_NUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUSWEIS_NUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWEIS_NUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWEIS_NUMMER", calNewValueFormated);
	}
		public String get_BARKUNDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BARKUNDE");
	}

	public String get_BARKUNDE_cF() throws myException
	{
		return this.get_FormatedValue("BARKUNDE");	
	}

	public String get_BARKUNDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BARKUNDE");
	}

	public String get_BARKUNDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BARKUNDE",cNotNullValue);
	}

	public String get_BARKUNDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BARKUNDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BARKUNDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BARKUNDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BARKUNDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BARKUNDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BARKUNDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BARKUNDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BARKUNDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BARKUNDE", calNewValueFormated);
	}
		public boolean is_BARKUNDE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BARKUNDE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BARKUNDE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BARKUNDE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BEMERKUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNGEN");
	}

	public String get_BEMERKUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNGEN");	
	}

	public String get_BEMERKUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNGEN");
	}

	public String get_BEMERKUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNGEN",cNotNullValue);
	}

	public String get_BEMERKUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN", calNewValueFormated);
	}
		public String get_BEMERKUNG_FAHRPLAN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_FAHRPLAN");
	}

	public String get_BEMERKUNG_FAHRPLAN_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_FAHRPLAN");	
	}

	public String get_BEMERKUNG_FAHRPLAN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_FAHRPLAN");
	}

	public String get_BEMERKUNG_FAHRPLAN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_FAHRPLAN",cNotNullValue);
	}

	public String get_BEMERKUNG_FAHRPLAN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_FAHRPLAN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FAHRPLAN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_FAHRPLAN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FAHRPLAN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FAHRPLAN", calNewValueFormated);
	}
		public String get_ERSTKONTAKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERSTKONTAKT");
	}

	public String get_ERSTKONTAKT_cF() throws myException
	{
		return this.get_FormatedValue("ERSTKONTAKT");	
	}

	public String get_ERSTKONTAKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERSTKONTAKT");
	}

	public String get_ERSTKONTAKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERSTKONTAKT",cNotNullValue);
	}

	public String get_ERSTKONTAKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERSTKONTAKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERSTKONTAKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERSTKONTAKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERSTKONTAKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERSTKONTAKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTKONTAKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTKONTAKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTKONTAKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTKONTAKT", calNewValueFormated);
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
		public String get_EU_BEIBLATT_ANSPRECH_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_ANSPRECH");
	}

	public String get_EU_BEIBLATT_ANSPRECH_cF() throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_ANSPRECH");	
	}

	public String get_EU_BEIBLATT_ANSPRECH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BEIBLATT_ANSPRECH");
	}

	public String get_EU_BEIBLATT_ANSPRECH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_ANSPRECH",cNotNullValue);
	}

	public String get_EU_BEIBLATT_ANSPRECH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_ANSPRECH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_ANSPRECH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_ANSPRECH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_ANSPRECH", calNewValueFormated);
	}
		public String get_EU_BEIBLATT_EK_VERTRAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_EK_VERTRAG");
	}

	public String get_EU_BEIBLATT_EK_VERTRAG_cF() throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_EK_VERTRAG");	
	}

	public String get_EU_BEIBLATT_EK_VERTRAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BEIBLATT_EK_VERTRAG");
	}

	public String get_EU_BEIBLATT_EK_VERTRAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_EK_VERTRAG",cNotNullValue);
	}

	public String get_EU_BEIBLATT_EK_VERTRAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_EK_VERTRAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EK_VERTRAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EK_VERTRAG", calNewValueFormated);
	}
		public String get_EU_BEIBLATT_EMAIL_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_EMAIL");
	}

	public String get_EU_BEIBLATT_EMAIL_cF() throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_EMAIL");	
	}

	public String get_EU_BEIBLATT_EMAIL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BEIBLATT_EMAIL");
	}

	public String get_EU_BEIBLATT_EMAIL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_EMAIL",cNotNullValue);
	}

	public String get_EU_BEIBLATT_EMAIL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_EMAIL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_EMAIL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BEIBLATT_EMAIL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_EMAIL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_EMAIL", calNewValueFormated);
	}
		public String get_EU_BEIBLATT_FAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_FAX");
	}

	public String get_EU_BEIBLATT_FAX_cF() throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_FAX");	
	}

	public String get_EU_BEIBLATT_FAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BEIBLATT_FAX");
	}

	public String get_EU_BEIBLATT_FAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_FAX",cNotNullValue);
	}

	public String get_EU_BEIBLATT_FAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_FAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BEIBLATT_FAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_FAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BEIBLATT_FAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_FAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_FAX", calNewValueFormated);
	}
		public String get_EU_BEIBLATT_INFOFELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_INFOFELD");
	}

	public String get_EU_BEIBLATT_INFOFELD_cF() throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_INFOFELD");	
	}

	public String get_EU_BEIBLATT_INFOFELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BEIBLATT_INFOFELD");
	}

	public String get_EU_BEIBLATT_INFOFELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_INFOFELD",cNotNullValue);
	}

	public String get_EU_BEIBLATT_INFOFELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_INFOFELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_INFOFELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BEIBLATT_INFOFELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_INFOFELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_INFOFELD", calNewValueFormated);
	}
		public String get_EU_BEIBLATT_TEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_TEL");
	}

	public String get_EU_BEIBLATT_TEL_cF() throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_TEL");	
	}

	public String get_EU_BEIBLATT_TEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BEIBLATT_TEL");
	}

	public String get_EU_BEIBLATT_TEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_TEL",cNotNullValue);
	}

	public String get_EU_BEIBLATT_TEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_TEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BEIBLATT_TEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_TEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BEIBLATT_TEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_TEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_TEL", calNewValueFormated);
	}
		public String get_EU_BEIBLATT_VK_VERTRAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_VK_VERTRAG");
	}

	public String get_EU_BEIBLATT_VK_VERTRAG_cF() throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_VK_VERTRAG");	
	}

	public String get_EU_BEIBLATT_VK_VERTRAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BEIBLATT_VK_VERTRAG");
	}

	public String get_EU_BEIBLATT_VK_VERTRAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BEIBLATT_VK_VERTRAG",cNotNullValue);
	}

	public String get_EU_BEIBLATT_VK_VERTRAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BEIBLATT_VK_VERTRAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BEIBLATT_VK_VERTRAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BEIBLATT_VK_VERTRAG", calNewValueFormated);
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
		public String get_GEBURTSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEBURTSDATUM");
	}

	public String get_GEBURTSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("GEBURTSDATUM");	
	}

	public String get_GEBURTSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEBURTSDATUM");
	}

	public String get_GEBURTSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEBURTSDATUM",cNotNullValue);
	}

	public String get_GEBURTSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEBURTSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEBURTSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEBURTSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEBURTSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEBURTSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEBURTSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEBURTSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBURTSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEBURTSDATUM", calNewValueFormated);
	}
		public String get_GUTSCHRIFTEN_SPERREN_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUTSCHRIFTEN_SPERREN");
	}

	public String get_GUTSCHRIFTEN_SPERREN_cF() throws myException
	{
		return this.get_FormatedValue("GUTSCHRIFTEN_SPERREN");	
	}

	public String get_GUTSCHRIFTEN_SPERREN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUTSCHRIFTEN_SPERREN");
	}

	public String get_GUTSCHRIFTEN_SPERREN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUTSCHRIFTEN_SPERREN",cNotNullValue);
	}

	public String get_GUTSCHRIFTEN_SPERREN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUTSCHRIFTEN_SPERREN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUTSCHRIFTEN_SPERREN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUTSCHRIFTEN_SPERREN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUTSCHRIFTEN_SPERREN", calNewValueFormated);
	}
		public boolean is_GUTSCHRIFTEN_SPERREN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUTSCHRIFTEN_SPERREN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GUTSCHRIFTEN_SPERREN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GUTSCHRIFTEN_SPERREN_cUF_NN("N").equals("N"))
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
	
	
	public String get_ID_ADRESSE_EU_VERTR_FORM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_EU_VERTR_FORM");
	}

	public String get_ID_ADRESSE_EU_VERTR_FORM_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_EU_VERTR_FORM");	
	}

	public String get_ID_ADRESSE_EU_VERTR_FORM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_EU_VERTR_FORM");
	}

	public String get_ID_ADRESSE_EU_VERTR_FORM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_EU_VERTR_FORM",cNotNullValue);
	}

	public String get_ID_ADRESSE_EU_VERTR_FORM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_EU_VERTR_FORM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EU_VERTR_FORM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_EU_VERTR_FORM", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_EU_VERTR_FORM_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_EU_VERTR_FORM").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_EU_VERTR_FORM_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_EU_VERTR_FORM").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_EU_VERTR_FORM_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_EU_VERTR_FORM").getDoubleValue();
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
	public String get_ID_ADRESSE_EU_VERTR_FORM_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_EU_VERTR_FORM_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_EU_VERTR_FORM_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_EU_VERTR_FORM_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_EU_VERTR_FORM_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_EU_VERTR_FORM").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_EU_VERTR_FORM_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_EU_VERTR_FORM").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_MERKMAL1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL1");
	}

	public String get_ID_ADRESSE_MERKMAL1_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL1");	
	}

	public String get_ID_ADRESSE_MERKMAL1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_MERKMAL1");
	}

	public String get_ID_ADRESSE_MERKMAL1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL1",cNotNullValue);
	}

	public String get_ID_ADRESSE_MERKMAL1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL1", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_MERKMAL1_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_MERKMAL1").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_MERKMAL1_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL1").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_MERKMAL1_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL1").getDoubleValue();
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
	public String get_ID_ADRESSE_MERKMAL1_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_MERKMAL1_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_MERKMAL1_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL1").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_MERKMAL1_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL1").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_MERKMAL2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL2");
	}

	public String get_ID_ADRESSE_MERKMAL2_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL2");	
	}

	public String get_ID_ADRESSE_MERKMAL2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_MERKMAL2");
	}

	public String get_ID_ADRESSE_MERKMAL2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL2",cNotNullValue);
	}

	public String get_ID_ADRESSE_MERKMAL2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL2", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_MERKMAL2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_MERKMAL2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_MERKMAL2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_MERKMAL2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL2").getDoubleValue();
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
	public String get_ID_ADRESSE_MERKMAL2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_MERKMAL2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_MERKMAL2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_MERKMAL2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL2").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_MERKMAL3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL3");
	}

	public String get_ID_ADRESSE_MERKMAL3_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL3");	
	}

	public String get_ID_ADRESSE_MERKMAL3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_MERKMAL3");
	}

	public String get_ID_ADRESSE_MERKMAL3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL3",cNotNullValue);
	}

	public String get_ID_ADRESSE_MERKMAL3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL3", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_MERKMAL3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_MERKMAL3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_MERKMAL3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_MERKMAL3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL3").getDoubleValue();
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
	public String get_ID_ADRESSE_MERKMAL3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL3_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_MERKMAL3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL3_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_MERKMAL3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_MERKMAL3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL3").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_MERKMAL4_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL4");
	}

	public String get_ID_ADRESSE_MERKMAL4_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL4");	
	}

	public String get_ID_ADRESSE_MERKMAL4_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_MERKMAL4");
	}

	public String get_ID_ADRESSE_MERKMAL4_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL4",cNotNullValue);
	}

	public String get_ID_ADRESSE_MERKMAL4_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL4",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL4(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL4", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL4_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL4", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_MERKMAL4_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_MERKMAL4").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_MERKMAL4_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL4").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_MERKMAL4_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL4").getDoubleValue();
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
	public String get_ID_ADRESSE_MERKMAL4_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL4_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_MERKMAL4_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL4_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_MERKMAL4_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL4").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_MERKMAL4_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL4").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_MERKMAL5_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL5");
	}

	public String get_ID_ADRESSE_MERKMAL5_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL5");	
	}

	public String get_ID_ADRESSE_MERKMAL5_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_MERKMAL5");
	}

	public String get_ID_ADRESSE_MERKMAL5_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_MERKMAL5",cNotNullValue);
	}

	public String get_ID_ADRESSE_MERKMAL5_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_MERKMAL5",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_MERKMAL5(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_MERKMAL5", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_MERKMAL5_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_MERKMAL5", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_MERKMAL5_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_MERKMAL5").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_MERKMAL5_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL5").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_MERKMAL5_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_MERKMAL5").getDoubleValue();
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
	public String get_ID_ADRESSE_MERKMAL5_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL5_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_MERKMAL5_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_MERKMAL5_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_MERKMAL5_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL5").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_MERKMAL5_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_MERKMAL5").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_POTENTIAL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_POTENTIAL");
	}

	public String get_ID_ADRESSE_POTENTIAL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_POTENTIAL");	
	}

	public String get_ID_ADRESSE_POTENTIAL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_POTENTIAL");
	}

	public String get_ID_ADRESSE_POTENTIAL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_POTENTIAL",cNotNullValue);
	}

	public String get_ID_ADRESSE_POTENTIAL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_POTENTIAL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_POTENTIAL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_POTENTIAL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_POTENTIAL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_POTENTIAL", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_POTENTIAL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_POTENTIAL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_POTENTIAL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_POTENTIAL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_POTENTIAL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_POTENTIAL").getDoubleValue();
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
	public String get_ID_ADRESSE_POTENTIAL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_POTENTIAL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_POTENTIAL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_POTENTIAL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_POTENTIAL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_POTENTIAL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_POTENTIAL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_POTENTIAL").getBigDecimalValue();
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
	
	
	public String get_ID_ANREDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ANREDE");
	}

	public String get_ID_ANREDE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ANREDE");	
	}

	public String get_ID_ANREDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ANREDE");
	}

	public String get_ID_ANREDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ANREDE",cNotNullValue);
	}

	public String get_ID_ANREDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ANREDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ANREDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ANREDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ANREDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ANREDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ANREDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ANREDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ANREDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ANREDE", calNewValueFormated);
	}
		public Long get_ID_ANREDE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ANREDE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ANREDE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ANREDE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ANREDE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ANREDE").getDoubleValue();
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
	public String get_ID_ANREDE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ANREDE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ANREDE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ANREDE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ANREDE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ANREDE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ANREDE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ANREDE").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND");
	}

	public String get_ID_LAND_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND");	
	}

	public String get_ID_LAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND");
	}

	public String get_ID_LAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND",cNotNullValue);
	}

	public String get_ID_LAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", calNewValueFormated);
	}
		public Long get_ID_LAND_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND").getDoubleValue();
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
	public String get_ID_LAND_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAND_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAND_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND").getBigDecimalValue();
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
	
	
	public String get_ID_LIEFERBEDINGUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LIEFERBEDINGUNGEN");
	}

	public String get_ID_LIEFERBEDINGUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_LIEFERBEDINGUNGEN");	
	}

	public String get_ID_LIEFERBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LIEFERBEDINGUNGEN");
	}

	public String get_ID_LIEFERBEDINGUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LIEFERBEDINGUNGEN",cNotNullValue);
	}

	public String get_ID_LIEFERBEDINGUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LIEFERBEDINGUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN", calNewValueFormated);
	}
		public Long get_ID_LIEFERBEDINGUNGEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LIEFERBEDINGUNGEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LIEFERBEDINGUNGEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LIEFERBEDINGUNGEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LIEFERBEDINGUNGEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LIEFERBEDINGUNGEN").getDoubleValue();
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
	public String get_ID_LIEFERBEDINGUNGEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LIEFERBEDINGUNGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LIEFERBEDINGUNGEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LIEFERBEDINGUNGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LIEFERBEDINGUNGEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LIEFERBEDINGUNGEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LIEFERBEDINGUNGEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LIEFERBEDINGUNGEN").getBigDecimalValue();
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
	
	
	public String get_ID_LIEFERBEDINGUNGEN_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LIEFERBEDINGUNGEN_VK");
	}

	public String get_ID_LIEFERBEDINGUNGEN_VK_cF() throws myException
	{
		return this.get_FormatedValue("ID_LIEFERBEDINGUNGEN_VK");	
	}

	public String get_ID_LIEFERBEDINGUNGEN_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LIEFERBEDINGUNGEN_VK");
	}

	public String get_ID_LIEFERBEDINGUNGEN_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LIEFERBEDINGUNGEN_VK",cNotNullValue);
	}

	public String get_ID_LIEFERBEDINGUNGEN_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LIEFERBEDINGUNGEN_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LIEFERBEDINGUNGEN_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LIEFERBEDINGUNGEN_VK", calNewValueFormated);
	}
		public Long get_ID_LIEFERBEDINGUNGEN_VK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LIEFERBEDINGUNGEN_VK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LIEFERBEDINGUNGEN_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LIEFERBEDINGUNGEN_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LIEFERBEDINGUNGEN_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LIEFERBEDINGUNGEN_VK").getDoubleValue();
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
	public String get_ID_LIEFERBEDINGUNGEN_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LIEFERBEDINGUNGEN_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LIEFERBEDINGUNGEN_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LIEFERBEDINGUNGEN_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LIEFERBEDINGUNGEN_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LIEFERBEDINGUNGEN_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LIEFERBEDINGUNGEN_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LIEFERBEDINGUNGEN_VK").getBigDecimalValue();
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
	
	
	public String get_ID_SPRACHE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_SPRACHE");
	}

	public String get_ID_SPRACHE_cF() throws myException
	{
		return this.get_FormatedValue("ID_SPRACHE");	
	}

	public String get_ID_SPRACHE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_SPRACHE");
	}

	public String get_ID_SPRACHE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_SPRACHE",cNotNullValue);
	}

	public String get_ID_SPRACHE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_SPRACHE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_SPRACHE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_SPRACHE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_SPRACHE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_SPRACHE", calNewValueFormated);
	}
		public Long get_ID_SPRACHE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_SPRACHE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_SPRACHE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_SPRACHE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_SPRACHE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_SPRACHE").getDoubleValue();
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
	public String get_ID_SPRACHE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_SPRACHE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_SPRACHE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_SPRACHE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_SPRACHE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_SPRACHE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_SPRACHE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_SPRACHE").getBigDecimalValue();
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
	
	
	public String get_ID_USER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER");
	}

	public String get_ID_USER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER");	
	}

	public String get_ID_USER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER");
	}

	public String get_ID_USER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER",cNotNullValue);
	}

	public String get_ID_USER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER", calNewValueFormated);
	}
		public Long get_ID_USER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER").getDoubleValue();
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
	public String get_ID_USER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER").getBigDecimalValue();
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
	
	
	public String get_ID_USER_ERSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_ERSATZ");
	}

	public String get_ID_USER_ERSATZ_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_ERSATZ");	
	}

	public String get_ID_USER_ERSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_ERSATZ");
	}

	public String get_ID_USER_ERSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_ERSATZ",cNotNullValue);
	}

	public String get_ID_USER_ERSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_ERSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_ERSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ERSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_ERSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ERSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ERSATZ", calNewValueFormated);
	}
		public Long get_ID_USER_ERSATZ_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_ERSATZ").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_ERSATZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_ERSATZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_ERSATZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_ERSATZ").getDoubleValue();
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
	public String get_ID_USER_ERSATZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_ERSATZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_ERSATZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_ERSATZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_ERSATZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_ERSATZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_ERSATZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_ERSATZ").getBigDecimalValue();
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
	
	
	public String get_ID_USER_LAGER_HAENDLER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_LAGER_HAENDLER");
	}

	public String get_ID_USER_LAGER_HAENDLER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_LAGER_HAENDLER");	
	}

	public String get_ID_USER_LAGER_HAENDLER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_LAGER_HAENDLER");
	}

	public String get_ID_USER_LAGER_HAENDLER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_LAGER_HAENDLER",cNotNullValue);
	}

	public String get_ID_USER_LAGER_HAENDLER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_LAGER_HAENDLER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_LAGER_HAENDLER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_LAGER_HAENDLER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_HAENDLER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_LAGER_HAENDLER", calNewValueFormated);
	}
		public Long get_ID_USER_LAGER_HAENDLER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_LAGER_HAENDLER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_LAGER_HAENDLER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_LAGER_HAENDLER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_LAGER_HAENDLER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_LAGER_HAENDLER").getDoubleValue();
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
	public String get_ID_USER_LAGER_HAENDLER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_LAGER_HAENDLER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_LAGER_HAENDLER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_LAGER_HAENDLER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_LAGER_HAENDLER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_LAGER_HAENDLER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_LAGER_HAENDLER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_LAGER_HAENDLER").getBigDecimalValue();
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
	
	
	public String get_ID_USER_LAGER_SACHBEARB_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_LAGER_SACHBEARB");
	}

	public String get_ID_USER_LAGER_SACHBEARB_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_LAGER_SACHBEARB");	
	}

	public String get_ID_USER_LAGER_SACHBEARB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_LAGER_SACHBEARB");
	}

	public String get_ID_USER_LAGER_SACHBEARB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_LAGER_SACHBEARB",cNotNullValue);
	}

	public String get_ID_USER_LAGER_SACHBEARB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_LAGER_SACHBEARB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_LAGER_SACHBEARB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_LAGER_SACHBEARB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_LAGER_SACHBEARB", calNewValueFormated);
	}
		public Long get_ID_USER_LAGER_SACHBEARB_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_LAGER_SACHBEARB").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_LAGER_SACHBEARB_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_LAGER_SACHBEARB").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_LAGER_SACHBEARB_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_LAGER_SACHBEARB").getDoubleValue();
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
	public String get_ID_USER_LAGER_SACHBEARB_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_LAGER_SACHBEARB_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_LAGER_SACHBEARB_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_LAGER_SACHBEARB_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_LAGER_SACHBEARB_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_LAGER_SACHBEARB").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_LAGER_SACHBEARB_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_LAGER_SACHBEARB").getBigDecimalValue();
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
	
	
	public String get_ID_USER_SACHBEARBEITER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER");
	}

	public String get_ID_USER_SACHBEARBEITER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER");	
	}

	public String get_ID_USER_SACHBEARBEITER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_SACHBEARBEITER");
	}

	public String get_ID_USER_SACHBEARBEITER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER",cNotNullValue);
	}

	public String get_ID_USER_SACHBEARBEITER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER", calNewValueFormated);
	}
		public Long get_ID_USER_SACHBEARBEITER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_SACHBEARBEITER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_SACHBEARBEITER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_SACHBEARBEITER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER").getDoubleValue();
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
	public String get_ID_USER_SACHBEARBEITER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_SACHBEARBEITER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_SACHBEARBEITER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_SACHBEARBEITER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER").getBigDecimalValue();
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
	
	
	public String get_ID_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG");
	}

	public String get_ID_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG");	
	}

	public String get_ID_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAEHRUNG");
	}

	public String get_ID_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG",cNotNullValue);
	}

	public String get_ID_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", calNewValueFormated);
	}
		public Long get_ID_WAEHRUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAEHRUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG").getDoubleValue();
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
	public String get_ID_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG").getBigDecimalValue();
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
	
	
	public String get_ID_ZAHLUNGSBEDINGUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSBEDINGUNGEN");	
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN",cNotNullValue);
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSBEDINGUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public Long get_ID_ZAHLUNGSBEDINGUNGEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ZAHLUNGSBEDINGUNGEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ZAHLUNGSBEDINGUNGEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getDoubleValue();
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
	public String get_ID_ZAHLUNGSBEDINGUNGEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSBEDINGUNGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSBEDINGUNGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ZAHLUNGSBEDINGUNGEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ZAHLUNGSBEDINGUNGEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getBigDecimalValue();
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
	
	
	public String get_ID_ZAHLUNGSBEDINGUNGEN_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN_VK");
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_VK_cF() throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSBEDINGUNGEN_VK");	
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ZAHLUNGSBEDINGUNGEN_VK");
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN_VK",cNotNullValue);
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSBEDINGUNGEN_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN_VK", calNewValueFormated);
	}
		public Long get_ID_ZAHLUNGSBEDINGUNGEN_VK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN_VK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ZAHLUNGSBEDINGUNGEN_VK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN_VK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ZAHLUNGSBEDINGUNGEN_VK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN_VK").getDoubleValue();
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
	public String get_ID_ZAHLUNGSBEDINGUNGEN_VK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSBEDINGUNGEN_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ZAHLUNGSBEDINGUNGEN_VK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSBEDINGUNGEN_VK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ZAHLUNGSBEDINGUNGEN_VK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN_VK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ZAHLUNGSBEDINGUNGEN_VK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN_VK").getBigDecimalValue();
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
	
	
	public String get_IS_POB_ACTIVE_cUF() throws myException
	{
		return this.get_UnFormatedValue("IS_POB_ACTIVE");
	}

	public String get_IS_POB_ACTIVE_cF() throws myException
	{
		return this.get_FormatedValue("IS_POB_ACTIVE");	
	}

	public String get_IS_POB_ACTIVE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IS_POB_ACTIVE");
	}

	public String get_IS_POB_ACTIVE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IS_POB_ACTIVE",cNotNullValue);
	}

	public String get_IS_POB_ACTIVE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IS_POB_ACTIVE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IS_POB_ACTIVE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IS_POB_ACTIVE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IS_POB_ACTIVE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", calNewValueFormated);
	}
		public boolean is_IS_POB_ACTIVE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_POB_ACTIVE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IS_POB_ACTIVE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_POB_ACTIVE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KDNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("KDNR");
	}

	public String get_KDNR_cF() throws myException
	{
		return this.get_FormatedValue("KDNR");	
	}

	public String get_KDNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KDNR");
	}

	public String get_KDNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KDNR",cNotNullValue);
	}

	public String get_KDNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KDNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KDNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KDNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KDNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KDNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KDNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KDNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KDNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KDNR", calNewValueFormated);
	}
		public String get_LAGER_KONTROLLINFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAGER_KONTROLLINFO");
	}

	public String get_LAGER_KONTROLLINFO_cF() throws myException
	{
		return this.get_FormatedValue("LAGER_KONTROLLINFO");	
	}

	public String get_LAGER_KONTROLLINFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAGER_KONTROLLINFO");
	}

	public String get_LAGER_KONTROLLINFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAGER_KONTROLLINFO",cNotNullValue);
	}

	public String get_LAGER_KONTROLLINFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAGER_KONTROLLINFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAGER_KONTROLLINFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAGER_KONTROLLINFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAGER_KONTROLLINFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_KONTROLLINFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_KONTROLLINFO", calNewValueFormated);
	}
		public String get_LAGER_ZUSTAENDIG_EXTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAGER_ZUSTAENDIG_EXTERN");
	}

	public String get_LAGER_ZUSTAENDIG_EXTERN_cF() throws myException
	{
		return this.get_FormatedValue("LAGER_ZUSTAENDIG_EXTERN");	
	}

	public String get_LAGER_ZUSTAENDIG_EXTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAGER_ZUSTAENDIG_EXTERN");
	}

	public String get_LAGER_ZUSTAENDIG_EXTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAGER_ZUSTAENDIG_EXTERN",cNotNullValue);
	}

	public String get_LAGER_ZUSTAENDIG_EXTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAGER_ZUSTAENDIG_EXTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_ZUSTAENDIG_EXTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_ZUSTAENDIG_EXTERN", calNewValueFormated);
	}
		public String get_LATITUDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LATITUDE");
	}

	public String get_LATITUDE_cF() throws myException
	{
		return this.get_FormatedValue("LATITUDE");	
	}

	public String get_LATITUDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LATITUDE");
	}

	public String get_LATITUDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LATITUDE",cNotNullValue);
	}

	public String get_LATITUDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LATITUDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LATITUDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LATITUDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LATITUDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LATITUDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LATITUDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LATITUDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LATITUDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LATITUDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LATITUDE", calNewValueFormated);
	}
		public Double get_LATITUDE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LATITUDE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LATITUDE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LATITUDE").getDoubleValue();
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
	public String get_LATITUDE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LATITUDE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_LATITUDE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LATITUDE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_LATITUDE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LATITUDE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LATITUDE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LATITUDE").getBigDecimalValue();
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
		public String get_LIEF_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEF_NR");
	}

	public String get_LIEF_NR_cF() throws myException
	{
		return this.get_FormatedValue("LIEF_NR");	
	}

	public String get_LIEF_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEF_NR");
	}

	public String get_LIEF_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEF_NR",cNotNullValue);
	}

	public String get_LIEF_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEF_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEF_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEF_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEF_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEF_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEF_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEF_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEF_NR", calNewValueFormated);
	}
		public String get_LONGITUDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LONGITUDE");
	}

	public String get_LONGITUDE_cF() throws myException
	{
		return this.get_FormatedValue("LONGITUDE");	
	}

	public String get_LONGITUDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LONGITUDE");
	}

	public String get_LONGITUDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LONGITUDE",cNotNullValue);
	}

	public String get_LONGITUDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LONGITUDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LONGITUDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LONGITUDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LONGITUDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LONGITUDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LONGITUDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LONGITUDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LONGITUDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LONGITUDE", calNewValueFormated);
	}
		public Double get_LONGITUDE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LONGITUDE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LONGITUDE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LONGITUDE").getDoubleValue();
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
	public String get_LONGITUDE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LONGITUDE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_LONGITUDE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LONGITUDE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_LONGITUDE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LONGITUDE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LONGITUDE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LONGITUDE").getBigDecimalValue();
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
		public String get_PLZ_POB_cUF() throws myException
	{
		return this.get_UnFormatedValue("PLZ_POB");
	}

	public String get_PLZ_POB_cF() throws myException
	{
		return this.get_FormatedValue("PLZ_POB");	
	}

	public String get_PLZ_POB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PLZ_POB");
	}

	public String get_PLZ_POB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PLZ_POB",cNotNullValue);
	}

	public String get_PLZ_POB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PLZ_POB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PLZ_POB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PLZ_POB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PLZ_POB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PLZ_POB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ_POB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ_POB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ_POB", calNewValueFormated);
	}
		public String get_POB_cUF() throws myException
	{
		return this.get_UnFormatedValue("POB");
	}

	public String get_POB_cF() throws myException
	{
		return this.get_FormatedValue("POB");	
	}

	public String get_POB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POB");
	}

	public String get_POB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POB",cNotNullValue);
	}

	public String get_POB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POB", calNewValueFormated);
	}
		public String get_RECHNUNGEN_SPERREN_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECHNUNGEN_SPERREN");
	}

	public String get_RECHNUNGEN_SPERREN_cF() throws myException
	{
		return this.get_FormatedValue("RECHNUNGEN_SPERREN");	
	}

	public String get_RECHNUNGEN_SPERREN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECHNUNGEN_SPERREN");
	}

	public String get_RECHNUNGEN_SPERREN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECHNUNGEN_SPERREN",cNotNullValue);
	}

	public String get_RECHNUNGEN_SPERREN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECHNUNGEN_SPERREN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECHNUNGEN_SPERREN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECHNUNGEN_SPERREN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECHNUNGEN_SPERREN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNGEN_SPERREN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHNUNGEN_SPERREN", calNewValueFormated);
	}
		public boolean is_RECHNUNGEN_SPERREN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECHNUNGEN_SPERREN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_RECHNUNGEN_SPERREN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_RECHNUNGEN_SPERREN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_RECHNUNG_PER_FAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("RECHNUNG_PER_FAX");
	}

	public String get_RECHNUNG_PER_FAX_cF() throws myException
	{
		return this.get_FormatedValue("RECHNUNG_PER_FAX");	
	}

	public String get_RECHNUNG_PER_FAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RECHNUNG_PER_FAX");
	}

	public String get_RECHNUNG_PER_FAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RECHNUNG_PER_FAX",cNotNullValue);
	}

	public String get_RECHNUNG_PER_FAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RECHNUNG_PER_FAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RECHNUNG_PER_FAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RECHNUNG_PER_FAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RECHNUNG_PER_FAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RECHNUNG_PER_FAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RECHNUNG_PER_FAX", calNewValueFormated);
	}
		public String get_SONDERLAGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("SONDERLAGER");
	}

	public String get_SONDERLAGER_cF() throws myException
	{
		return this.get_FormatedValue("SONDERLAGER");	
	}

	public String get_SONDERLAGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SONDERLAGER");
	}

	public String get_SONDERLAGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SONDERLAGER",cNotNullValue);
	}

	public String get_SONDERLAGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SONDERLAGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SONDERLAGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SONDERLAGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SONDERLAGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SONDERLAGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERLAGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERLAGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERLAGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERLAGER", calNewValueFormated);
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
	
	
	public String get_TRANSFER_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSFER");
	}

	public String get_TRANSFER_cF() throws myException
	{
		return this.get_FormatedValue("TRANSFER");	
	}

	public String get_TRANSFER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSFER");
	}

	public String get_TRANSFER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSFER",cNotNullValue);
	}

	public String get_TRANSFER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSFER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSFER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSFER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSFER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSFER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSFER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSFER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSFER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSFER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSFER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSFER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSFER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSFER", calNewValueFormated);
	}
		public boolean is_TRANSFER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSFER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSFER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSFER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_VORNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORNAME");
	}

	public String get_VORNAME_cF() throws myException
	{
		return this.get_FormatedValue("VORNAME");	
	}

	public String get_VORNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORNAME");
	}

	public String get_VORNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORNAME",cNotNullValue);
	}

	public String get_VORNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", calNewValueFormated);
	}
		public String get_WARENAUSGANG_SPERREN_cUF() throws myException
	{
		return this.get_UnFormatedValue("WARENAUSGANG_SPERREN");
	}

	public String get_WARENAUSGANG_SPERREN_cF() throws myException
	{
		return this.get_FormatedValue("WARENAUSGANG_SPERREN");	
	}

	public String get_WARENAUSGANG_SPERREN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WARENAUSGANG_SPERREN");
	}

	public String get_WARENAUSGANG_SPERREN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WARENAUSGANG_SPERREN",cNotNullValue);
	}

	public String get_WARENAUSGANG_SPERREN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WARENAUSGANG_SPERREN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WARENAUSGANG_SPERREN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WARENAUSGANG_SPERREN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WARENAUSGANG_SPERREN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENAUSGANG_SPERREN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENAUSGANG_SPERREN", calNewValueFormated);
	}
		public boolean is_WARENAUSGANG_SPERREN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_WARENAUSGANG_SPERREN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_WARENAUSGANG_SPERREN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_WARENAUSGANG_SPERREN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_WARENEINGANG_SPERREN_cUF() throws myException
	{
		return this.get_UnFormatedValue("WARENEINGANG_SPERREN");
	}

	public String get_WARENEINGANG_SPERREN_cF() throws myException
	{
		return this.get_FormatedValue("WARENEINGANG_SPERREN");	
	}

	public String get_WARENEINGANG_SPERREN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WARENEINGANG_SPERREN");
	}

	public String get_WARENEINGANG_SPERREN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WARENEINGANG_SPERREN",cNotNullValue);
	}

	public String get_WARENEINGANG_SPERREN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WARENEINGANG_SPERREN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WARENEINGANG_SPERREN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WARENEINGANG_SPERREN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WARENEINGANG_SPERREN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENEINGANG_SPERREN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENEINGANG_SPERREN", calNewValueFormated);
	}
		public boolean is_WARENEINGANG_SPERREN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_WARENEINGANG_SPERREN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_WARENEINGANG_SPERREN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_WARENEINGANG_SPERREN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABN_NR",MyRECORD.DATATYPES.TEXT);
	put("ADRESSTYP",MyRECORD.DATATYPES.NUMBER);
	put("AH7_QUELLE_SICHER",MyRECORD.DATATYPES.YESNO);
	put("AH7_ZIEL_SICHER",MyRECORD.DATATYPES.YESNO);
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("AUSWEIS_ABLAUF_DATUM",MyRECORD.DATATYPES.DATE);
	put("AUSWEIS_NUMMER",MyRECORD.DATATYPES.TEXT);
	put("BARKUNDE",MyRECORD.DATATYPES.YESNO);
	put("BEMERKUNGEN",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_FAHRPLAN",MyRECORD.DATATYPES.TEXT);
	put("ERSTKONTAKT",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EU_BEIBLATT_ANSPRECH",MyRECORD.DATATYPES.TEXT);
	put("EU_BEIBLATT_EK_VERTRAG",MyRECORD.DATATYPES.DATE);
	put("EU_BEIBLATT_EMAIL",MyRECORD.DATATYPES.TEXT);
	put("EU_BEIBLATT_FAX",MyRECORD.DATATYPES.TEXT);
	put("EU_BEIBLATT_INFOFELD",MyRECORD.DATATYPES.TEXT);
	put("EU_BEIBLATT_TEL",MyRECORD.DATATYPES.TEXT);
	put("EU_BEIBLATT_VK_VERTRAG",MyRECORD.DATATYPES.DATE);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEBURTSDATUM",MyRECORD.DATATYPES.DATE);
	put("GUTSCHRIFTEN_SPERREN",MyRECORD.DATATYPES.YESNO);
	put("HAUSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_EU_VERTR_FORM",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_MERKMAL1",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_MERKMAL2",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_MERKMAL3",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_MERKMAL4",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_MERKMAL5",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_POTENTIAL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ANREDE",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND",MyRECORD.DATATYPES.NUMBER);
	put("ID_LIEFERBEDINGUNGEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_LIEFERBEDINGUNGEN_VK",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_SPRACHE",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_ERSATZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_LAGER_HAENDLER",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_LAGER_SACHBEARB",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_SACHBEARBEITER",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAEHRUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZAHLUNGSBEDINGUNGEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZAHLUNGSBEDINGUNGEN_VK",MyRECORD.DATATYPES.NUMBER);
	put("IS_POB_ACTIVE",MyRECORD.DATATYPES.YESNO);
	put("KDNR",MyRECORD.DATATYPES.TEXT);
	put("LAGER_KONTROLLINFO",MyRECORD.DATATYPES.TEXT);
	put("LAGER_ZUSTAENDIG_EXTERN",MyRECORD.DATATYPES.TEXT);
	put("LATITUDE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERINFO_TPA",MyRECORD.DATATYPES.TEXT);
	put("LIEF_NR",MyRECORD.DATATYPES.TEXT);
	put("LONGITUDE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("NAME1",MyRECORD.DATATYPES.TEXT);
	put("NAME2",MyRECORD.DATATYPES.TEXT);
	put("NAME3",MyRECORD.DATATYPES.TEXT);
	put("ORT",MyRECORD.DATATYPES.TEXT);
	put("ORTZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("PLZ",MyRECORD.DATATYPES.TEXT);
	put("PLZ_POB",MyRECORD.DATATYPES.TEXT);
	put("POB",MyRECORD.DATATYPES.TEXT);
	put("RECHNUNGEN_SPERREN",MyRECORD.DATATYPES.YESNO);
	put("RECHNUNG_PER_FAX",MyRECORD.DATATYPES.TEXT);
	put("SONDERLAGER",MyRECORD.DATATYPES.TEXT);
	put("STRASSE",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TRANSFER",MyRECORD.DATATYPES.YESNO);
	put("VORNAME",MyRECORD.DATATYPES.TEXT);
	put("WARENAUSGANG_SPERREN",MyRECORD.DATATYPES.YESNO);
	put("WARENEINGANG_SPERREN",MyRECORD.DATATYPES.YESNO);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ADRESSE.HM_FIELDS;	
	}

}
