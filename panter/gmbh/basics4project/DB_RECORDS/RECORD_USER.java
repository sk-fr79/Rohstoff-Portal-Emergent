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

public class RECORD_USER extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JD_USER";
    public static String IDFIELD   = "ID_USER";
    

	//erzeugen eines RECORDNEW_JD_USER - felds
	private RECORDNEW_USER   recNEW = null;

    private _TAB  tab = _TAB.user;  



	public RECORD_USER() throws myException
	{
		super();
		this.set_TABLE_NAME("JD_USER");
	}


	public RECORD_USER(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_USER");
	}



	public RECORD_USER(RECORD_USER recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_USER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_USER.TABLENAME);
	}


	//2014-04-03
	public RECORD_USER(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_USER");
		this.set_Tablename_To_FieldMetaDefs(RECORD_USER.TABLENAME);
	}




	public RECORD_USER(long lID_Unformated) throws myException
	{
		super("JD_USER","ID_USER",""+lID_Unformated);
		this.set_TABLE_NAME("JD_USER");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_USER.TABLENAME);
	}

	public RECORD_USER(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JD_USER");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_USER", "ID_USER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_USER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_USER.TABLENAME);
	}
	
	

	public RECORD_USER(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JD_USER","ID_USER",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JD_USER");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_USER.TABLENAME);

	}


	public RECORD_USER(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_USER");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_USER", "ID_USER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_USER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_USER.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_USER();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_USER.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_USER";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_USER_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_USER_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_USER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_USER", bibE2.cTO(), "ID_USER="+this.get_ID_USER_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_USER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_USER", bibE2.cTO(), "ID_USER="+this.get_ID_USER_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER="+this.get_ID_USER_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER="+this.get_ID_USER_cUF();
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
			if (S.isFull(this.get_ID_USER_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_USER", "ID_USER="+this.get_ID_USER_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_USER get_RECORDNEW_USER() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_USER();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_USER(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_USER create_Instance() throws myException {
		return new RECORD_USER();
	}
	
	public static RECORD_USER create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_USER(Conn);
    }

	public static RECORD_USER create_Instance(RECORD_USER recordOrig) {
		return new RECORD_USER(recordOrig);
    }

	public static RECORD_USER create_Instance(long lID_Unformated) throws myException {
		return new RECORD_USER(lID_Unformated);
    }

	public static RECORD_USER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_USER(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_USER create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_USER(lID_Unformated, Conn);
	}

	public static RECORD_USER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_USER(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_USER create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_USER(recordOrig);	    
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
    public RECORD_USER build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER="+this.get_ID_USER_cUF());
      }
      //return new RECORD_USER(this.get_cSQL_4_Build());
      RECORD_USER rec = new RECORD_USER();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_USER
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_USER-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_USER get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_USER_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_USER  recNew = new RECORDNEW_USER();
		
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
    public RECORD_USER set_recordNew(RECORDNEW_USER recnew) throws myException {
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
	
	



		private RECORD_SPRACHE UP_RECORD_SPRACHE_id_sprache = null;




		private RECLIST_BUTTON_USER DOWN_RECLIST_BUTTON_USER_id_user = null ;




		private RECLIST_USER_APPLET DOWN_RECLIST_USER_APPLET_id_user = null ;




		private RECLIST_USERSETTINGS DOWN_RECLIST_USERSETTINGS_id_user = null ;




		private RECLIST_ZUGRIFF DOWN_RECLIST_ZUGRIFF_id_user = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_user = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_user_ersatz = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_user_lager_haendler = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_user_lager_sachbearb = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_user_sachbearbeiter = null ;




		private RECLIST_ADRESSE_AQUISE DOWN_RECLIST_ADRESSE_AQUISE_id_user = null ;




		private RECLIST_ADRESSE_INFO DOWN_RECLIST_ADRESSE_INFO_id_user = null ;




		private RECLIST_ADRESSE_INFO DOWN_RECLIST_ADRESSE_INFO_id_user_ersatz = null ;




		private RECLIST_ADRESSE_INFO DOWN_RECLIST_ADRESSE_INFO_id_user_sachbearbeiter = null ;




		private RECLIST_ARTIKEL_DATENBLATT DOWN_RECLIST_ARTIKEL_DATENBLATT_id_user_kontakt = null ;




		private RECLIST_BATCH_USER DOWN_RECLIST_BATCH_USER_id_user = null ;




		private RECLIST_BEWEGUNG_MENGE DOWN_RECLIST_BEWEGUNG_MENGE_id_user = null ;




		private RECLIST_BEWEGUNG_VEKTOR_PN DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_user = null ;




		private RECLIST_BG_DEL_INFO DOWN_RECLIST_BG_DEL_INFO_id_user = null ;




		private RECLIST_BG_PRUEFPROT DOWN_RECLIST_BG_PRUEFPROT_id_user = null ;




		private RECLIST_BG_STORNO_INFO DOWN_RECLIST_BG_STORNO_INFO_id_user = null ;




		private RECLIST_BORDERCROSSING_USERINFO DOWN_RECLIST_BORDERCROSSING_USERINFO_id_user = null ;




		private RECLIST_CHANGELOG DOWN_RECLIST_CHANGELOG_id_user = null ;




		private RECLIST_CONTAINER_STATION DOWN_RECLIST_CONTAINER_STATION_id_user_auftraggeber = null ;




		private RECLIST_CONTAINER_STATION DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer = null ;




		private RECLIST_CONTAINER_STATION DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer_plan = null ;




		private RECLIST_CONTAINER_STATION DOWN_RECLIST_CONTAINER_STATION_id_user_buchung = null ;




		private RECLIST_CONTAINER_ZYKLUS DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_haendler = null ;




		private RECLIST_CONTAINER_ZYKLUS DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung = null ;




		private RECLIST_EMAIL_INBOX DOWN_RECLIST_EMAIL_INBOX_id_user_gelesen = null ;




		private RECLIST_EMAIL_SEND_TARGETS DOWN_RECLIST_EMAIL_SEND_TARGETS_id_user_send = null ;




		private RECLIST_FBAM DOWN_RECLIST_FBAM_id_user_ausstellung = null ;




		private RECLIST_FBAM DOWN_RECLIST_FBAM_id_user_behebung = null ;




		private RECLIST_FBAM DOWN_RECLIST_FBAM_id_user_kontrolle = null ;




		private RECLIST_FBAM_USER DOWN_RECLIST_FBAM_USER_id_user = null ;




		private RECLIST_FIBU_SACHBEARBEITER DOWN_RECLIST_FIBU_SACHBEARBEITER_id_user = null ;




		private RECLIST_FIELD_RULE DOWN_RECLIST_FIELD_RULE_id_user = null ;




		private RECLIST_FP_POS_AKTE DOWN_RECLIST_FP_POS_AKTE_id_user = null ;




		private RECLIST_HILFETEXT DOWN_RECLIST_HILFETEXT_id_user_bearbeiter = null ;




		private RECLIST_HILFETEXT DOWN_RECLIST_HILFETEXT_id_user_ursprung = null ;




		private RECLIST_INFO_CARD DOWN_RECLIST_INFO_CARD_id_user_besitzer = null ;




		private RECLIST_INFO_CARD_X_USER DOWN_RECLIST_INFO_CARD_X_USER_id_user = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_endkontrolle_von = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_gepresst_von = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_palettiert_von = null ;




		private RECLIST_LAGER_PALETTE_USER DOWN_RECLIST_LAGER_PALETTE_USER_id_user = null ;




		private RECLIST_LAUFZETTEL DOWN_RECLIST_LAUFZETTEL_id_user_besitzer = null ;




		private RECLIST_LAUFZETTEL DOWN_RECLIST_LAUFZETTEL_id_user_supervisor = null ;




		private RECLIST_LAUFZETTEL_EINTRAG DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von = null ;




		private RECLIST_LAUFZETTEL_EINTRAG DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter = null ;




		private RECLIST_LAUFZETTEL_EINTRAG DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_besitzer = null ;




		private RECLIST_LAUFZETTEL_TEILNEHMER DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_user = null ;




		private RECLIST_MAHNUNG DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_1 = null ;




		private RECLIST_MAHNUNG DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_2 = null ;




		private RECLIST_MAHNUNG DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_3 = null ;




		private RECLIST_NACHRICHT DOWN_RECLIST_NACHRICHT_id_user = null ;




		private RECLIST_PROJEKT DOWN_RECLIST_PROJEKT_id_user = null ;




		private RECLIST_PROJEKTINFO DOWN_RECLIST_PROJEKTINFO_id_user = null ;




		private RECLIST_PRO_MITARB DOWN_RECLIST_PRO_MITARB_id_user = null ;




		private RECLIST_QUERY_TEILNEHMER DOWN_RECLIST_QUERY_TEILNEHMER_id_user = null ;




		private RECLIST_REMINDER_DEF DOWN_RECLIST_REMINDER_DEF_id_user_abgeschlossen = null ;




		private RECLIST_REMINDER_DEF DOWN_RECLIST_REMINDER_DEF_id_user_angelegt = null ;




		private RECLIST_REMINDER_LOG DOWN_RECLIST_REMINDER_LOG_id_user = null ;




		private RECLIST_REMINDER_USER DOWN_RECLIST_REMINDER_USER_id_user = null ;




		private RECLIST_REPORT_PP_POS_USER_EXCL DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_user = null ;




		private RECLIST_REPORT_PP_POS_USER_INCL DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_user = null ;




		private RECLIST_SCANNER_USER DOWN_RECLIST_SCANNER_USER_id_user = null ;




		private RECLIST_TERMIN_USER DOWN_RECLIST_TERMIN_USER_id_user = null ;




		private RECLIST_TODO DOWN_RECLIST_TODO_id_user = null ;




		private RECLIST_TODO_TEILNEHMER DOWN_RECLIST_TODO_TEILNEHMER_id_user = null ;




		private RECLIST_UMA_KONTRAKT DOWN_RECLIST_UMA_KONTRAKT_id_user_betreuer = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_id_user = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_id_user_ansprech_intern = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_id_user_sachbearb_intern = null ;




		private RECLIST_VKOPF_RG DOWN_RECLIST_VKOPF_RG_id_user = null ;




		private RECLIST_VKOPF_RG DOWN_RECLIST_VKOPF_RG_id_user_ansprech_intern = null ;




		private RECLIST_VKOPF_RG DOWN_RECLIST_VKOPF_RG_id_user_sachbearb_intern = null ;




		private RECLIST_VKOPF_STD DOWN_RECLIST_VKOPF_STD_id_user = null ;




		private RECLIST_VKOPF_STD DOWN_RECLIST_VKOPF_STD_id_user_ansprech_intern = null ;




		private RECLIST_VKOPF_STD DOWN_RECLIST_VKOPF_STD_id_user_sachbearb_intern = null ;




		private RECLIST_VKOPF_TPA DOWN_RECLIST_VKOPF_TPA_id_user = null ;




		private RECLIST_VKOPF_TPA DOWN_RECLIST_VKOPF_TPA_id_user_ansprech_intern = null ;




		private RECLIST_VKOPF_TPA DOWN_RECLIST_VKOPF_TPA_id_user_sachbearb_intern = null ;




		private RECLIST_WIEGEKARTE_USER_BEFUND DOWN_RECLIST_WIEGEKARTE_USER_BEFUND_id_user = null ;




		private RECORD_USERGROUP UP_RECORD_USERGROUP_id_usergroup = null;




		private RECORD_DATEV_PROFILE UP_RECORD_DATEV_PROFILE_id_datev_profile = null;






	
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
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BUTTON_USER get_DOWN_RECORD_LIST_BUTTON_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BUTTON_USER_id_user==null)
		{
			this.DOWN_RECLIST_BUTTON_USER_id_user = new RECLIST_BUTTON_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JD_BUTTON_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BUTTON_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BUTTON_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BUTTON_USER get_DOWN_RECORD_LIST_BUTTON_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BUTTON_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_BUTTON_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BUTTON_USER_id_user = new RECLIST_BUTTON_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BUTTON_USER_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_USER_APPLET get_DOWN_RECORD_LIST_USER_APPLET_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_USER_APPLET_id_user==null)
		{
			this.DOWN_RECLIST_USER_APPLET_id_user = new RECLIST_USER_APPLET (
			       "SELECT * FROM "+bibE2.cTO()+".JD_USER_APPLET WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_USER_APPLET",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_USER_APPLET_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_USER_APPLET get_DOWN_RECORD_LIST_USER_APPLET_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_USER_APPLET_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_USER_APPLET WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_USER_APPLET_id_user = new RECLIST_USER_APPLET (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_USER_APPLET_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_USERSETTINGS get_DOWN_RECORD_LIST_USERSETTINGS_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_USERSETTINGS_id_user==null)
		{
			this.DOWN_RECLIST_USERSETTINGS_id_user = new RECLIST_USERSETTINGS (
			       "SELECT * FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_USERSETTINGS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_USERSETTINGS_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_USERSETTINGS get_DOWN_RECORD_LIST_USERSETTINGS_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_USERSETTINGS_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_USERSETTINGS_id_user = new RECLIST_USERSETTINGS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_USERSETTINGS_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ZUGRIFF get_DOWN_RECORD_LIST_ZUGRIFF_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ZUGRIFF_id_user==null)
		{
			this.DOWN_RECLIST_ZUGRIFF_id_user = new RECLIST_ZUGRIFF (
			       "SELECT * FROM "+bibE2.cTO()+".JD_ZUGRIFF WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_ZUGRIFF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ZUGRIFF_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ZUGRIFF get_DOWN_RECORD_LIST_ZUGRIFF_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ZUGRIFF_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_ZUGRIFF WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ZUGRIFF_id_user = new RECLIST_ZUGRIFF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ZUGRIFF_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_user = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_user = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user;
	}


	





	/**
	 * References the Field ID_USER_ERSATZ 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_ersatz() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_ersatz==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_user_ersatz = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_ERSATZ="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_ersatz;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ERSATZ 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_ersatz(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_ersatz==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_ERSATZ="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_user_ersatz = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_ersatz;
	}


	





	/**
	 * References the Field ID_USER_LAGER_HAENDLER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_lager_haendler() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_lager_haendler==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_user_lager_haendler = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_LAGER_HAENDLER="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_lager_haendler;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_LAGER_HAENDLER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_lager_haendler(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_lager_haendler==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_LAGER_HAENDLER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_user_lager_haendler = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_lager_haendler;
	}


	





	/**
	 * References the Field ID_USER_LAGER_SACHBEARB 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_lager_sachbearb() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_lager_sachbearb==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_user_lager_sachbearb = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_LAGER_SACHBEARB="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_lager_sachbearb;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_LAGER_SACHBEARB 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_lager_sachbearb(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_lager_sachbearb==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_LAGER_SACHBEARB="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_user_lager_sachbearb = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_lager_sachbearb;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_sachbearbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_sachbearbeiter==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_user_sachbearbeiter = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_SACHBEARBEITER="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_sachbearbeiter;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_user_sachbearbeiter(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_user_sachbearbeiter==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_USER_SACHBEARBEITER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_user_sachbearbeiter = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_user_sachbearbeiter;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_AQUISE get_DOWN_RECORD_LIST_ADRESSE_AQUISE_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_AQUISE_id_user==null)
		{
			this.DOWN_RECLIST_ADRESSE_AQUISE_id_user = new RECLIST_ADRESSE_AQUISE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_AQUISE WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE_AQUISE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_AQUISE_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_AQUISE get_DOWN_RECORD_LIST_ADRESSE_AQUISE_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_AQUISE_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_AQUISE WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_AQUISE_id_user = new RECLIST_ADRESSE_AQUISE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_AQUISE_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_user==null)
		{
			this.DOWN_RECLIST_ADRESSE_INFO_id_user = new RECLIST_ADRESSE_INFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE_INFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_INFO_id_user = new RECLIST_ADRESSE_INFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_user;
	}


	





	/**
	 * References the Field ID_USER_ERSATZ 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_user_ersatz() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_user_ersatz==null)
		{
			this.DOWN_RECLIST_ADRESSE_INFO_id_user_ersatz = new RECLIST_ADRESSE_INFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_USER_ERSATZ="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE_INFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_user_ersatz;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ERSATZ 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_user_ersatz(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_user_ersatz==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_USER_ERSATZ="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_INFO_id_user_ersatz = new RECLIST_ADRESSE_INFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_user_ersatz;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_user_sachbearbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_user_sachbearbeiter==null)
		{
			this.DOWN_RECLIST_ADRESSE_INFO_id_user_sachbearbeiter = new RECLIST_ADRESSE_INFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_USER_SACHBEARBEITER="+this.get_ID_USER_cUF()+" ORDER BY ID_ADRESSE_INFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_user_sachbearbeiter;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_INFO get_DOWN_RECORD_LIST_ADRESSE_INFO_id_user_sachbearbeiter(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_INFO_id_user_sachbearbeiter==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_USER_SACHBEARBEITER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_INFO_id_user_sachbearbeiter = new RECLIST_ADRESSE_INFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_INFO_id_user_sachbearbeiter;
	}


	





	/**
	 * References the Field ID_USER_KONTAKT 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_user_kontakt() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_user_kontakt==null)
		{
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_user_kontakt = new RECLIST_ARTIKEL_DATENBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_USER_KONTAKT="+this.get_ID_USER_cUF()+" ORDER BY ID_ARTIKEL_DATENBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_user_kontakt;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_KONTAKT 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_user_kontakt(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_user_kontakt==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_USER_KONTAKT="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_user_kontakt = new RECLIST_ARTIKEL_DATENBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_user_kontakt;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BATCH_USER get_DOWN_RECORD_LIST_BATCH_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BATCH_USER_id_user==null)
		{
			this.DOWN_RECLIST_BATCH_USER_id_user = new RECLIST_BATCH_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BATCH_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BATCH_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BATCH_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BATCH_USER get_DOWN_RECORD_LIST_BATCH_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BATCH_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BATCH_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BATCH_USER_id_user = new RECLIST_BATCH_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BATCH_USER_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_MENGE get_DOWN_RECORD_LIST_BEWEGUNG_MENGE_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_MENGE_id_user==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_MENGE_id_user = new RECLIST_BEWEGUNG_MENGE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_MENGE WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BEWEGUNG_MENGE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_MENGE_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_MENGE get_DOWN_RECORD_LIST_BEWEGUNG_MENGE_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_MENGE_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_MENGE WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_MENGE_id_user = new RECLIST_BEWEGUNG_MENGE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_MENGE_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_user==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_user = new RECLIST_BEWEGUNG_VEKTOR_PN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_PN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_user = new RECLIST_BEWEGUNG_VEKTOR_PN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_DEL_INFO get_DOWN_RECORD_LIST_BG_DEL_INFO_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_DEL_INFO_id_user==null)
		{
			this.DOWN_RECLIST_BG_DEL_INFO_id_user = new RECLIST_BG_DEL_INFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_DEL_INFO WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BG_DEL_INFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_DEL_INFO_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_DEL_INFO get_DOWN_RECORD_LIST_BG_DEL_INFO_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_DEL_INFO_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_DEL_INFO WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_DEL_INFO_id_user = new RECLIST_BG_DEL_INFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_DEL_INFO_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_PRUEFPROT get_DOWN_RECORD_LIST_BG_PRUEFPROT_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_PRUEFPROT_id_user==null)
		{
			this.DOWN_RECLIST_BG_PRUEFPROT_id_user = new RECLIST_BG_PRUEFPROT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_PRUEFPROT WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BG_PRUEFPROT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_PRUEFPROT_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_PRUEFPROT get_DOWN_RECORD_LIST_BG_PRUEFPROT_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_PRUEFPROT_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_PRUEFPROT WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_PRUEFPROT_id_user = new RECLIST_BG_PRUEFPROT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_PRUEFPROT_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STORNO_INFO get_DOWN_RECORD_LIST_BG_STORNO_INFO_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STORNO_INFO_id_user==null)
		{
			this.DOWN_RECLIST_BG_STORNO_INFO_id_user = new RECLIST_BG_STORNO_INFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_STORNO_INFO WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BG_STORNO_INFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STORNO_INFO_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STORNO_INFO get_DOWN_RECORD_LIST_BG_STORNO_INFO_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STORNO_INFO_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_STORNO_INFO WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_STORNO_INFO_id_user = new RECLIST_BG_STORNO_INFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STORNO_INFO_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING_USERINFO get_DOWN_RECORD_LIST_BORDERCROSSING_USERINFO_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_USERINFO_id_user==null)
		{
			this.DOWN_RECLIST_BORDERCROSSING_USERINFO_id_user = new RECLIST_BORDERCROSSING_USERINFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING_USERINFO WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_BORDERCROSSING_USERINFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_USERINFO_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING_USERINFO get_DOWN_RECORD_LIST_BORDERCROSSING_USERINFO_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_USERINFO_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING_USERINFO WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BORDERCROSSING_USERINFO_id_user = new RECLIST_BORDERCROSSING_USERINFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_USERINFO_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CHANGELOG get_DOWN_RECORD_LIST_CHANGELOG_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CHANGELOG_id_user==null)
		{
			this.DOWN_RECLIST_CHANGELOG_id_user = new RECLIST_CHANGELOG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CHANGELOG WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_CHANGELOG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CHANGELOG_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CHANGELOG get_DOWN_RECORD_LIST_CHANGELOG_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CHANGELOG_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CHANGELOG WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CHANGELOG_id_user = new RECLIST_CHANGELOG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CHANGELOG_id_user;
	}


	





	/**
	 * References the Field ID_USER_AUFTRAGGEBER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_auftraggeber() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftraggeber==null)
		{
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftraggeber = new RECLIST_CONTAINER_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_AUFTRAGGEBER="+this.get_ID_USER_cUF()+" ORDER BY ID_CONTAINER_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftraggeber;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_AUFTRAGGEBER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_auftraggeber(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftraggeber==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_AUFTRAGGEBER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftraggeber = new RECLIST_CONTAINER_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftraggeber;
	}


	





	/**
	 * References the Field ID_USER_AUFTRAGNEHMER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_auftragnehmer() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer==null)
		{
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer = new RECLIST_CONTAINER_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_AUFTRAGNEHMER="+this.get_ID_USER_cUF()+" ORDER BY ID_CONTAINER_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_AUFTRAGNEHMER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_auftragnehmer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_AUFTRAGNEHMER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer = new RECLIST_CONTAINER_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer;
	}


	





	/**
	 * References the Field ID_USER_AUFTRAGNEHMER_PLAN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_auftragnehmer_plan() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer_plan==null)
		{
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer_plan = new RECLIST_CONTAINER_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_AUFTRAGNEHMER_PLAN="+this.get_ID_USER_cUF()+" ORDER BY ID_CONTAINER_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer_plan;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_AUFTRAGNEHMER_PLAN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_auftragnehmer_plan(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer_plan==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_AUFTRAGNEHMER_PLAN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer_plan = new RECLIST_CONTAINER_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_auftragnehmer_plan;
	}


	





	/**
	 * References the Field ID_USER_BUCHUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_buchung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_buchung==null)
		{
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_buchung = new RECLIST_CONTAINER_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_BUCHUNG="+this.get_ID_USER_cUF()+" ORDER BY ID_CONTAINER_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_buchung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BUCHUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_STATION get_DOWN_RECORD_LIST_CONTAINER_STATION_id_user_buchung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_STATION_id_user_buchung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_USER_BUCHUNG="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_STATION_id_user_buchung = new RECLIST_CONTAINER_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_STATION_id_user_buchung;
	}


	





	/**
	 * References the Field ID_USER_HAENDLER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_user_haendler() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_haendler==null)
		{
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_haendler = new RECLIST_CONTAINER_ZYKLUS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_USER_HAENDLER="+this.get_ID_USER_cUF()+" ORDER BY ID_CONTAINER_ZYKLUS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_haendler;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_HAENDLER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_user_haendler(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_haendler==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_USER_HAENDLER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_haendler = new RECLIST_CONTAINER_ZYKLUS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_haendler;
	}


	





	/**
	 * References the Field ID_USER_LETZTE_POS_BUCHUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung==null)
		{
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung = new RECLIST_CONTAINER_ZYKLUS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_USER_LETZTE_POS_BUCHUNG="+this.get_ID_USER_cUF()+" ORDER BY ID_CONTAINER_ZYKLUS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_LETZTE_POS_BUCHUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_USER_LETZTE_POS_BUCHUNG="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung = new RECLIST_CONTAINER_ZYKLUS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_user_letzte_pos_buchung;
	}


	





	/**
	 * References the Field ID_USER_GELESEN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL_INBOX get_DOWN_RECORD_LIST_EMAIL_INBOX_id_user_gelesen() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_INBOX_id_user_gelesen==null)
		{
			this.DOWN_RECLIST_EMAIL_INBOX_id_user_gelesen = new RECLIST_EMAIL_INBOX (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_INBOX WHERE ID_USER_GELESEN="+this.get_ID_USER_cUF()+" ORDER BY ID_EMAIL_INBOX",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_INBOX_id_user_gelesen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_GELESEN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL_INBOX get_DOWN_RECORD_LIST_EMAIL_INBOX_id_user_gelesen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_INBOX_id_user_gelesen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_INBOX WHERE ID_USER_GELESEN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EMAIL_INBOX_id_user_gelesen = new RECLIST_EMAIL_INBOX (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_INBOX_id_user_gelesen;
	}


	





	/**
	 * References the Field ID_USER_SEND 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL_SEND_TARGETS get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_user_send() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_SEND_TARGETS_id_user_send==null)
		{
			this.DOWN_RECLIST_EMAIL_SEND_TARGETS_id_user_send = new RECLIST_EMAIL_SEND_TARGETS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_SEND_TARGETS WHERE ID_USER_SEND="+this.get_ID_USER_cUF()+" ORDER BY ID_EMAIL_SEND_TARGETS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_SEND_TARGETS_id_user_send;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SEND 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL_SEND_TARGETS get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_user_send(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_SEND_TARGETS_id_user_send==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_SEND_TARGETS WHERE ID_USER_SEND="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EMAIL_SEND_TARGETS_id_user_send = new RECLIST_EMAIL_SEND_TARGETS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_SEND_TARGETS_id_user_send;
	}


	





	/**
	 * References the Field ID_USER_AUSSTELLUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_user_ausstellung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_user_ausstellung==null)
		{
			this.DOWN_RECLIST_FBAM_id_user_ausstellung = new RECLIST_FBAM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_USER_AUSSTELLUNG="+this.get_ID_USER_cUF()+" ORDER BY ID_FBAM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_user_ausstellung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_AUSSTELLUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_user_ausstellung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_user_ausstellung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_USER_AUSSTELLUNG="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_id_user_ausstellung = new RECLIST_FBAM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_user_ausstellung;
	}


	





	/**
	 * References the Field ID_USER_BEHEBUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_user_behebung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_user_behebung==null)
		{
			this.DOWN_RECLIST_FBAM_id_user_behebung = new RECLIST_FBAM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_USER_BEHEBUNG="+this.get_ID_USER_cUF()+" ORDER BY ID_FBAM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_user_behebung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BEHEBUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_user_behebung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_user_behebung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_USER_BEHEBUNG="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_id_user_behebung = new RECLIST_FBAM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_user_behebung;
	}


	





	/**
	 * References the Field ID_USER_KONTROLLE 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_user_kontrolle() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_user_kontrolle==null)
		{
			this.DOWN_RECLIST_FBAM_id_user_kontrolle = new RECLIST_FBAM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_USER_KONTROLLE="+this.get_ID_USER_cUF()+" ORDER BY ID_FBAM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_user_kontrolle;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_KONTROLLE 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM get_DOWN_RECORD_LIST_FBAM_id_user_kontrolle(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_id_user_kontrolle==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_USER_KONTROLLE="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_id_user_kontrolle = new RECLIST_FBAM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_id_user_kontrolle;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_USER get_DOWN_RECORD_LIST_FBAM_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_USER_id_user==null)
		{
			this.DOWN_RECLIST_FBAM_USER_id_user = new RECLIST_FBAM_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_FBAM_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_USER get_DOWN_RECORD_LIST_FBAM_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_USER_id_user = new RECLIST_FBAM_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_USER_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_SACHBEARBEITER get_DOWN_RECORD_LIST_FIBU_SACHBEARBEITER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_SACHBEARBEITER_id_user==null)
		{
			this.DOWN_RECLIST_FIBU_SACHBEARBEITER_id_user = new RECLIST_FIBU_SACHBEARBEITER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_SACHBEARBEITER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_FIBU_SACHBEARBEITER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_SACHBEARBEITER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_SACHBEARBEITER get_DOWN_RECORD_LIST_FIBU_SACHBEARBEITER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_SACHBEARBEITER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_SACHBEARBEITER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_SACHBEARBEITER_id_user = new RECLIST_FIBU_SACHBEARBEITER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_SACHBEARBEITER_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIELD_RULE get_DOWN_RECORD_LIST_FIELD_RULE_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIELD_RULE_id_user==null)
		{
			this.DOWN_RECLIST_FIELD_RULE_id_user = new RECLIST_FIELD_RULE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIELD_RULE WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_FIELD_RULE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIELD_RULE_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIELD_RULE get_DOWN_RECORD_LIST_FIELD_RULE_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIELD_RULE_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIELD_RULE WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIELD_RULE_id_user = new RECLIST_FIELD_RULE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIELD_RULE_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FP_POS_AKTE get_DOWN_RECORD_LIST_FP_POS_AKTE_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FP_POS_AKTE_id_user==null)
		{
			this.DOWN_RECLIST_FP_POS_AKTE_id_user = new RECLIST_FP_POS_AKTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FP_POS_AKTE WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_FP_POS_AKTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FP_POS_AKTE_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FP_POS_AKTE get_DOWN_RECORD_LIST_FP_POS_AKTE_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FP_POS_AKTE_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FP_POS_AKTE WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FP_POS_AKTE_id_user = new RECLIST_FP_POS_AKTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FP_POS_AKTE_id_user;
	}


	





	/**
	 * References the Field ID_USER_BEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HILFETEXT get_DOWN_RECORD_LIST_HILFETEXT_id_user_bearbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HILFETEXT_id_user_bearbeiter==null)
		{
			this.DOWN_RECLIST_HILFETEXT_id_user_bearbeiter = new RECLIST_HILFETEXT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HILFETEXT WHERE ID_USER_BEARBEITER="+this.get_ID_USER_cUF()+" ORDER BY ID_HILFETEXT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HILFETEXT_id_user_bearbeiter;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HILFETEXT get_DOWN_RECORD_LIST_HILFETEXT_id_user_bearbeiter(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HILFETEXT_id_user_bearbeiter==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HILFETEXT WHERE ID_USER_BEARBEITER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HILFETEXT_id_user_bearbeiter = new RECLIST_HILFETEXT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HILFETEXT_id_user_bearbeiter;
	}


	





	/**
	 * References the Field ID_USER_URSPRUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HILFETEXT get_DOWN_RECORD_LIST_HILFETEXT_id_user_ursprung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HILFETEXT_id_user_ursprung==null)
		{
			this.DOWN_RECLIST_HILFETEXT_id_user_ursprung = new RECLIST_HILFETEXT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HILFETEXT WHERE ID_USER_URSPRUNG="+this.get_ID_USER_cUF()+" ORDER BY ID_HILFETEXT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HILFETEXT_id_user_ursprung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_URSPRUNG 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HILFETEXT get_DOWN_RECORD_LIST_HILFETEXT_id_user_ursprung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HILFETEXT_id_user_ursprung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HILFETEXT WHERE ID_USER_URSPRUNG="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HILFETEXT_id_user_ursprung = new RECLIST_HILFETEXT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HILFETEXT_id_user_ursprung;
	}


	





	/**
	 * References the Field ID_USER_BESITZER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INFO_CARD get_DOWN_RECORD_LIST_INFO_CARD_id_user_besitzer() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INFO_CARD_id_user_besitzer==null)
		{
			this.DOWN_RECLIST_INFO_CARD_id_user_besitzer = new RECLIST_INFO_CARD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_INFO_CARD WHERE ID_USER_BESITZER="+this.get_ID_USER_cUF()+" ORDER BY ID_INFO_CARD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_INFO_CARD_id_user_besitzer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BESITZER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INFO_CARD get_DOWN_RECORD_LIST_INFO_CARD_id_user_besitzer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INFO_CARD_id_user_besitzer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_INFO_CARD WHERE ID_USER_BESITZER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_INFO_CARD_id_user_besitzer = new RECLIST_INFO_CARD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_INFO_CARD_id_user_besitzer;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INFO_CARD_X_USER get_DOWN_RECORD_LIST_INFO_CARD_X_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INFO_CARD_X_USER_id_user==null)
		{
			this.DOWN_RECLIST_INFO_CARD_X_USER_id_user = new RECLIST_INFO_CARD_X_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_INFO_CARD_X_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_INFO_CARD_X_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_INFO_CARD_X_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INFO_CARD_X_USER get_DOWN_RECORD_LIST_INFO_CARD_X_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INFO_CARD_X_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_INFO_CARD_X_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_INFO_CARD_X_USER_id_user = new RECLIST_INFO_CARD_X_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_INFO_CARD_X_USER_id_user;
	}


	





	/**
	 * References the Field ENDKONTROLLE_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_endkontrolle_von() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_endkontrolle_von==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_endkontrolle_von = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ENDKONTROLLE_VON="+this.get_ID_USER_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_endkontrolle_von;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ENDKONTROLLE_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_endkontrolle_von(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_endkontrolle_von==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ENDKONTROLLE_VON="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_endkontrolle_von = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_endkontrolle_von;
	}


	





	/**
	 * References the Field GEPRESST_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_gepresst_von() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_gepresst_von==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_gepresst_von = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE GEPRESST_VON="+this.get_ID_USER_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_gepresst_von;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field GEPRESST_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_gepresst_von(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_gepresst_von==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE GEPRESST_VON="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_gepresst_von = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_gepresst_von;
	}


	





	/**
	 * References the Field PALETTIERT_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_palettiert_von() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_palettiert_von==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_palettiert_von = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE PALETTIERT_VON="+this.get_ID_USER_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_palettiert_von;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field PALETTIERT_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_palettiert_von(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_palettiert_von==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE PALETTIERT_VON="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_palettiert_von = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_palettiert_von;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE_USER get_DOWN_RECORD_LIST_LAGER_PALETTE_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_USER_id_user==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_USER_id_user = new RECLIST_LAGER_PALETTE_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_LAGER_PALETTE_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE_USER get_DOWN_RECORD_LIST_LAGER_PALETTE_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_USER_id_user = new RECLIST_LAGER_PALETTE_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_USER_id_user;
	}


	





	/**
	 * References the Field ID_USER_BESITZER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL get_DOWN_RECORD_LIST_LAUFZETTEL_id_user_besitzer() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_id_user_besitzer==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_id_user_besitzer = new RECLIST_LAUFZETTEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL WHERE ID_USER_BESITZER="+this.get_ID_USER_cUF()+" ORDER BY ID_LAUFZETTEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_id_user_besitzer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BESITZER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL get_DOWN_RECORD_LIST_LAUFZETTEL_id_user_besitzer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_id_user_besitzer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL WHERE ID_USER_BESITZER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_id_user_besitzer = new RECLIST_LAUFZETTEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_id_user_besitzer;
	}


	





	/**
	 * References the Field ID_USER_SUPERVISOR 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL get_DOWN_RECORD_LIST_LAUFZETTEL_id_user_supervisor() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_id_user_supervisor==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_id_user_supervisor = new RECLIST_LAUFZETTEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL WHERE ID_USER_SUPERVISOR="+this.get_ID_USER_cUF()+" ORDER BY ID_LAUFZETTEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_id_user_supervisor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SUPERVISOR 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL get_DOWN_RECORD_LIST_LAUFZETTEL_id_user_supervisor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_id_user_supervisor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL WHERE ID_USER_SUPERVISOR="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_id_user_supervisor = new RECLIST_LAUFZETTEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_id_user_supervisor;
	}


	





	/**
	 * References the Field ID_USER_ABGESCHLOSSEN_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von = new RECLIST_LAUFZETTEL_EINTRAG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_USER_ABGESCHLOSSEN_VON="+this.get_ID_USER_cUF()+" ORDER BY ID_LAUFZETTEL_EINTRAG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ABGESCHLOSSEN_VON 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_USER_ABGESCHLOSSEN_VON="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von = new RECLIST_LAUFZETTEL_EINTRAG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_abgeschlossen_von;
	}


	





	/**
	 * References the Field ID_USER_BEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter = new RECLIST_LAUFZETTEL_EINTRAG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_USER_BEARBEITER="+this.get_ID_USER_cUF()+" ORDER BY ID_LAUFZETTEL_EINTRAG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BEARBEITER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_USER_BEARBEITER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter = new RECLIST_LAUFZETTEL_EINTRAG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_bearbeiter;
	}


	





	/**
	 * References the Field ID_USER_BESITZER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_user_besitzer() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_besitzer==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_besitzer = new RECLIST_LAUFZETTEL_EINTRAG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_USER_BESITZER="+this.get_ID_USER_cUF()+" ORDER BY ID_LAUFZETTEL_EINTRAG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_besitzer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BESITZER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_user_besitzer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_besitzer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_USER_BESITZER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_besitzer = new RECLIST_LAUFZETTEL_EINTRAG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_user_besitzer;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_TEILNEHMER get_DOWN_RECORD_LIST_LAUFZETTEL_TEILNEHMER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_user==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_user = new RECLIST_LAUFZETTEL_TEILNEHMER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_TEILNEHMER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_LAUFZETTEL_TEILNEHMER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_TEILNEHMER get_DOWN_RECORD_LIST_LAUFZETTEL_TEILNEHMER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_TEILNEHMER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_user = new RECLIST_LAUFZETTEL_TEILNEHMER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_user;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARBEITER_1 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG get_DOWN_RECORD_LIST_MAHNUNG_id_user_sachbearbeiter_1() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_1==null)
		{
			this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_1 = new RECLIST_MAHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_USER_SACHBEARBEITER_1="+this.get_ID_USER_cUF()+" ORDER BY ID_MAHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_1;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARBEITER_1 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG get_DOWN_RECORD_LIST_MAHNUNG_id_user_sachbearbeiter_1(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_1==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_USER_SACHBEARBEITER_1="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_1 = new RECLIST_MAHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_1;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARBEITER_2 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG get_DOWN_RECORD_LIST_MAHNUNG_id_user_sachbearbeiter_2() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_2==null)
		{
			this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_2 = new RECLIST_MAHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_USER_SACHBEARBEITER_2="+this.get_ID_USER_cUF()+" ORDER BY ID_MAHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_2;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARBEITER_2 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG get_DOWN_RECORD_LIST_MAHNUNG_id_user_sachbearbeiter_2(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_2==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_USER_SACHBEARBEITER_2="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_2 = new RECLIST_MAHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_2;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARBEITER_3 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG get_DOWN_RECORD_LIST_MAHNUNG_id_user_sachbearbeiter_3() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_3==null)
		{
			this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_3 = new RECLIST_MAHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_USER_SACHBEARBEITER_3="+this.get_ID_USER_cUF()+" ORDER BY ID_MAHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_3;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARBEITER_3 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG get_DOWN_RECORD_LIST_MAHNUNG_id_user_sachbearbeiter_3(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_3==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_USER_SACHBEARBEITER_3="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_3 = new RECLIST_MAHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_id_user_sachbearbeiter_3;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_NACHRICHT get_DOWN_RECORD_LIST_NACHRICHT_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_NACHRICHT_id_user==null)
		{
			this.DOWN_RECLIST_NACHRICHT_id_user = new RECLIST_NACHRICHT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_NACHRICHT WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_NACHRICHT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_NACHRICHT_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_NACHRICHT get_DOWN_RECORD_LIST_NACHRICHT_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_NACHRICHT_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_NACHRICHT WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_NACHRICHT_id_user = new RECLIST_NACHRICHT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_NACHRICHT_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROJEKT get_DOWN_RECORD_LIST_PROJEKT_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROJEKT_id_user==null)
		{
			this.DOWN_RECLIST_PROJEKT_id_user = new RECLIST_PROJEKT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PROJEKT WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_PROJEKT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PROJEKT_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROJEKT get_DOWN_RECORD_LIST_PROJEKT_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROJEKT_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PROJEKT WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PROJEKT_id_user = new RECLIST_PROJEKT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PROJEKT_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROJEKTINFO get_DOWN_RECORD_LIST_PROJEKTINFO_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROJEKTINFO_id_user==null)
		{
			this.DOWN_RECLIST_PROJEKTINFO_id_user = new RECLIST_PROJEKTINFO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PROJEKTINFO WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_PROJEKTINFO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PROJEKTINFO_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PROJEKTINFO get_DOWN_RECORD_LIST_PROJEKTINFO_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PROJEKTINFO_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PROJEKTINFO WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PROJEKTINFO_id_user = new RECLIST_PROJEKTINFO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PROJEKTINFO_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_MITARB get_DOWN_RECORD_LIST_PRO_MITARB_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_MITARB_id_user==null)
		{
			this.DOWN_RECLIST_PRO_MITARB_id_user = new RECLIST_PRO_MITARB (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PRO_MITARB WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_PRO_MITARB",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_MITARB_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_MITARB get_DOWN_RECORD_LIST_PRO_MITARB_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_MITARB_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PRO_MITARB WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PRO_MITARB_id_user = new RECLIST_PRO_MITARB (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_MITARB_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_QUERY_TEILNEHMER get_DOWN_RECORD_LIST_QUERY_TEILNEHMER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_QUERY_TEILNEHMER_id_user==null)
		{
			this.DOWN_RECLIST_QUERY_TEILNEHMER_id_user = new RECLIST_QUERY_TEILNEHMER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_QUERY_TEILNEHMER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_QUERY_TEILNEHMER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_QUERY_TEILNEHMER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_QUERY_TEILNEHMER get_DOWN_RECORD_LIST_QUERY_TEILNEHMER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_QUERY_TEILNEHMER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_QUERY_TEILNEHMER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_QUERY_TEILNEHMER_id_user = new RECLIST_QUERY_TEILNEHMER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_QUERY_TEILNEHMER_id_user;
	}


	





	/**
	 * References the Field ID_USER_ABGESCHLOSSEN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_DEF get_DOWN_RECORD_LIST_REMINDER_DEF_id_user_abgeschlossen() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_DEF_id_user_abgeschlossen==null)
		{
			this.DOWN_RECLIST_REMINDER_DEF_id_user_abgeschlossen = new RECLIST_REMINDER_DEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_DEF WHERE ID_USER_ABGESCHLOSSEN="+this.get_ID_USER_cUF()+" ORDER BY ID_REMINDER_DEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_DEF_id_user_abgeschlossen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ABGESCHLOSSEN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_DEF get_DOWN_RECORD_LIST_REMINDER_DEF_id_user_abgeschlossen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_DEF_id_user_abgeschlossen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_DEF WHERE ID_USER_ABGESCHLOSSEN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REMINDER_DEF_id_user_abgeschlossen = new RECLIST_REMINDER_DEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_DEF_id_user_abgeschlossen;
	}


	





	/**
	 * References the Field ID_USER_ANGELEGT 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_DEF get_DOWN_RECORD_LIST_REMINDER_DEF_id_user_angelegt() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_DEF_id_user_angelegt==null)
		{
			this.DOWN_RECLIST_REMINDER_DEF_id_user_angelegt = new RECLIST_REMINDER_DEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_DEF WHERE ID_USER_ANGELEGT="+this.get_ID_USER_cUF()+" ORDER BY ID_REMINDER_DEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_DEF_id_user_angelegt;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ANGELEGT 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_DEF get_DOWN_RECORD_LIST_REMINDER_DEF_id_user_angelegt(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_DEF_id_user_angelegt==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_DEF WHERE ID_USER_ANGELEGT="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REMINDER_DEF_id_user_angelegt = new RECLIST_REMINDER_DEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_DEF_id_user_angelegt;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_LOG get_DOWN_RECORD_LIST_REMINDER_LOG_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_LOG_id_user==null)
		{
			this.DOWN_RECLIST_REMINDER_LOG_id_user = new RECLIST_REMINDER_LOG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_LOG WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_REMINDER_LOG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_LOG_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_LOG get_DOWN_RECORD_LIST_REMINDER_LOG_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_LOG_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_LOG WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REMINDER_LOG_id_user = new RECLIST_REMINDER_LOG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_LOG_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_USER get_DOWN_RECORD_LIST_REMINDER_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_USER_id_user==null)
		{
			this.DOWN_RECLIST_REMINDER_USER_id_user = new RECLIST_REMINDER_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_REMINDER_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REMINDER_USER get_DOWN_RECORD_LIST_REMINDER_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REMINDER_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REMINDER_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REMINDER_USER_id_user = new RECLIST_REMINDER_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REMINDER_USER_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_EXCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_EXCL_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_user==null)
		{
			this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_user = new RECLIST_REPORT_PP_POS_USER_EXCL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_EXCL WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_REPORT_PP_POS_USER_EXCL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_EXCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_EXCL_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_EXCL WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_user = new RECLIST_REPORT_PP_POS_USER_EXCL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_EXCL_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_INCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_INCL_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_user==null)
		{
			this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_user = new RECLIST_REPORT_PP_POS_USER_INCL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_INCL WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_REPORT_PP_POS_USER_INCL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_REPORT_PP_POS_USER_INCL get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_INCL_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PP_POS_USER_INCL WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_user = new RECLIST_REPORT_PP_POS_USER_INCL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_REPORT_PP_POS_USER_INCL_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SCANNER_USER get_DOWN_RECORD_LIST_SCANNER_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SCANNER_USER_id_user==null)
		{
			this.DOWN_RECLIST_SCANNER_USER_id_user = new RECLIST_SCANNER_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_SCANNER_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_SCANNER_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_SCANNER_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SCANNER_USER get_DOWN_RECORD_LIST_SCANNER_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SCANNER_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_SCANNER_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_SCANNER_USER_id_user = new RECLIST_SCANNER_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_SCANNER_USER_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TERMIN_USER get_DOWN_RECORD_LIST_TERMIN_USER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TERMIN_USER_id_user==null)
		{
			this.DOWN_RECLIST_TERMIN_USER_id_user = new RECLIST_TERMIN_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_TERMIN_USER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_TERMIN_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_TERMIN_USER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TERMIN_USER get_DOWN_RECORD_LIST_TERMIN_USER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TERMIN_USER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_TERMIN_USER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_TERMIN_USER_id_user = new RECLIST_TERMIN_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_TERMIN_USER_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TODO get_DOWN_RECORD_LIST_TODO_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TODO_id_user==null)
		{
			this.DOWN_RECLIST_TODO_id_user = new RECLIST_TODO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_TODO WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_TODO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_TODO_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TODO get_DOWN_RECORD_LIST_TODO_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TODO_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_TODO WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_TODO_id_user = new RECLIST_TODO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_TODO_id_user;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TODO_TEILNEHMER get_DOWN_RECORD_LIST_TODO_TEILNEHMER_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TODO_TEILNEHMER_id_user==null)
		{
			this.DOWN_RECLIST_TODO_TEILNEHMER_id_user = new RECLIST_TODO_TEILNEHMER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_TODO_TEILNEHMER WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_TODO_TEILNEHMER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_TODO_TEILNEHMER_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TODO_TEILNEHMER get_DOWN_RECORD_LIST_TODO_TEILNEHMER_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TODO_TEILNEHMER_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_TODO_TEILNEHMER WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_TODO_TEILNEHMER_id_user = new RECLIST_TODO_TEILNEHMER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_TODO_TEILNEHMER_id_user;
	}


	





	/**
	 * References the Field ID_USER_BETREUER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_user_betreuer() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_user_betreuer==null)
		{
			this.DOWN_RECLIST_UMA_KONTRAKT_id_user_betreuer = new RECLIST_UMA_KONTRAKT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_USER_BETREUER="+this.get_ID_USER_cUF()+" ORDER BY ID_UMA_KONTRAKT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_user_betreuer;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_BETREUER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_user_betreuer(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_user_betreuer==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_USER_BETREUER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_UMA_KONTRAKT_id_user_betreuer = new RECLIST_UMA_KONTRAKT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_user_betreuer;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_user==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_id_user = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_id_user = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_user;
	}


	





	/**
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_user_ansprech_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_user_ansprech_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_id_user_ansprech_intern = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_user_ansprech_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_user_ansprech_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_user_ansprech_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_id_user_ansprech_intern = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_user_ansprech_intern;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_user_sachbearb_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_user_sachbearb_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_id_user_sachbearb_intern = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_user_sachbearb_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_user_sachbearb_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_user_sachbearb_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_id_user_sachbearb_intern = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_user_sachbearb_intern;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_user==null)
		{
			this.DOWN_RECLIST_VKOPF_RG_id_user = new RECLIST_VKOPF_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_RG_id_user = new RECLIST_VKOPF_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_user;
	}


	





	/**
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_user_ansprech_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_user_ansprech_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_RG_id_user_ansprech_intern = new RECLIST_VKOPF_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_user_ansprech_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_user_ansprech_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_user_ansprech_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_RG_id_user_ansprech_intern = new RECLIST_VKOPF_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_user_ansprech_intern;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_user_sachbearb_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_user_sachbearb_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_RG_id_user_sachbearb_intern = new RECLIST_VKOPF_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_user_sachbearb_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_user_sachbearb_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_user_sachbearb_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_RG_id_user_sachbearb_intern = new RECLIST_VKOPF_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_user_sachbearb_intern;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_user==null)
		{
			this.DOWN_RECLIST_VKOPF_STD_id_user = new RECLIST_VKOPF_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_STD_id_user = new RECLIST_VKOPF_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_user;
	}


	





	/**
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_user_ansprech_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_user_ansprech_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_STD_id_user_ansprech_intern = new RECLIST_VKOPF_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_user_ansprech_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_user_ansprech_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_user_ansprech_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_STD_id_user_ansprech_intern = new RECLIST_VKOPF_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_user_ansprech_intern;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_user_sachbearb_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_user_sachbearb_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_STD_id_user_sachbearb_intern = new RECLIST_VKOPF_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_user_sachbearb_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_user_sachbearb_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_user_sachbearb_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_STD_id_user_sachbearb_intern = new RECLIST_VKOPF_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_user_sachbearb_intern;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_user==null)
		{
			this.DOWN_RECLIST_VKOPF_TPA_id_user = new RECLIST_VKOPF_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_TPA_id_user = new RECLIST_VKOPF_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_user;
	}


	





	/**
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_user_ansprech_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_user_ansprech_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_TPA_id_user_ansprech_intern = new RECLIST_VKOPF_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_user_ansprech_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_ANSPRECH_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_user_ansprech_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_user_ansprech_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_USER_ANSPRECH_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_TPA_id_user_ansprech_intern = new RECLIST_VKOPF_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_user_ansprech_intern;
	}


	





	/**
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_user_sachbearb_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_user_sachbearb_intern==null)
		{
			this.DOWN_RECLIST_VKOPF_TPA_id_user_sachbearb_intern = new RECLIST_VKOPF_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF()+" ORDER BY ID_VKOPF_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_user_sachbearb_intern;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER_SACHBEARB_INTERN 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_user_sachbearb_intern(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_user_sachbearb_intern==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_USER_SACHBEARB_INTERN="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_TPA_id_user_sachbearb_intern = new RECLIST_VKOPF_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_user_sachbearb_intern;
	}


	





	/**
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_USER_BEFUND get_DOWN_RECORD_LIST_WIEGEKARTE_USER_BEFUND_id_user() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_USER_BEFUND_id_user==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_USER_BEFUND_id_user = new RECLIST_WIEGEKARTE_USER_BEFUND (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_USER_BEFUND WHERE ID_USER="+this.get_ID_USER_cUF()+" ORDER BY ID_WIEGEKARTE_USER_BEFUND",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_USER_BEFUND_id_user;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_USER 
	 * Falls keine get_ID_USER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE_USER_BEFUND get_DOWN_RECORD_LIST_WIEGEKARTE_USER_BEFUND_id_user(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_USER_BEFUND_id_user==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE_USER_BEFUND WHERE ID_USER="+this.get_ID_USER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_USER_BEFUND_id_user = new RECLIST_WIEGEKARTE_USER_BEFUND (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_USER_BEFUND_id_user;
	}


	





	
	/**
	 * References the Field ID_USERGROUP
	 * Falls keine this.get_ID_USERGROUP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USERGROUP get_UP_RECORD_USERGROUP_id_usergroup() throws myException
	{
		if (S.isEmpty(this.get_ID_USERGROUP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USERGROUP_id_usergroup==null)
		{
			this.UP_RECORD_USERGROUP_id_usergroup = new RECORD_USERGROUP(this.get_ID_USERGROUP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USERGROUP_id_usergroup;
	}
	





	
	/**
	 * References the Field ID_DATEV_PROFILE
	 * Falls keine this.get_ID_DATEV_PROFILE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_DATEV_PROFILE get_UP_RECORD_DATEV_PROFILE_id_datev_profile() throws myException
	{
		if (S.isEmpty(this.get_ID_DATEV_PROFILE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_DATEV_PROFILE_id_datev_profile==null)
		{
			this.UP_RECORD_DATEV_PROFILE_id_datev_profile = new RECORD_DATEV_PROFILE(this.get_ID_DATEV_PROFILE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_DATEV_PROFILE_id_datev_profile;
	}
	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__ANREDE = "ANREDE";
	public static String FIELD__AUTCODE = "AUTCODE";
	public static String FIELD__COLOR_MASK_ERROR_BLUE = "COLOR_MASK_ERROR_BLUE";
	public static String FIELD__COLOR_MASK_ERROR_GREEN = "COLOR_MASK_ERROR_GREEN";
	public static String FIELD__COLOR_MASK_ERROR_RED = "COLOR_MASK_ERROR_RED";
	public static String FIELD__COLOR_MASK_OK_BLUE = "COLOR_MASK_OK_BLUE";
	public static String FIELD__COLOR_MASK_OK_GREEN = "COLOR_MASK_OK_GREEN";
	public static String FIELD__COLOR_MASK_OK_RED = "COLOR_MASK_OK_RED";
	public static String FIELD__COLOR_MASK_WARN_BLUE = "COLOR_MASK_WARN_BLUE";
	public static String FIELD__COLOR_MASK_WARN_GREEN = "COLOR_MASK_WARN_GREEN";
	public static String FIELD__COLOR_MASK_WARN_RED = "COLOR_MASK_WARN_RED";
	public static String FIELD__DEVELOPER = "DEVELOPER";
	public static String FIELD__EIGENDEF_BREITEAENDERBAR = "EIGENDEF_BREITEAENDERBAR";
	public static String FIELD__EIGENDEF_MENUEBREITE = "EIGENDEF_MENUEBREITE";
	public static String FIELD__EIGENDEF_SCHRIFTGROESSE = "EIGENDEF_SCHRIFTGROESSE";
	public static String FIELD__EMAIL = "EMAIL";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FENSTER_MIT_SCHATTEN = "FENSTER_MIT_SCHATTEN";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GESCHAEFTSFUEHRER = "GESCHAEFTSFUEHRER";
	public static String FIELD__HAT_FAHRPLAN_BUTTON = "HAT_FAHRPLAN_BUTTON";
	public static String FIELD__ID_DATEV_PROFILE = "ID_DATEV_PROFILE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_SPRACHE = "ID_SPRACHE";
	public static String FIELD__ID_USER = "ID_USER";
	public static String FIELD__ID_USERGROUP = "ID_USERGROUP";
	public static String FIELD__IST_FAHRER = "IST_FAHRER";
	public static String FIELD__IST_SUPERVISOR = "IST_SUPERVISOR";
	public static String FIELD__IST_VERWALTUNG = "IST_VERWALTUNG";
	public static String FIELD__KUERZEL = "KUERZEL";
	public static String FIELD__LAUFZEIT_SESSION = "LAUFZEIT_SESSION";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LISTEGESAMTLAENGE = "LISTEGESAMTLAENGE";
	public static String FIELD__LISTESEITELAENGE = "LISTESEITELAENGE";
	public static String FIELD__MAIL_SIGNATUR = "MAIL_SIGNATUR";
	public static String FIELD__MELDUNG_KREDITVERS_ABLAUF = "MELDUNG_KREDITVERS_ABLAUF";
	public static String FIELD__MELDUNG_KREDITVERS_BETRAG = "MELDUNG_KREDITVERS_BETRAG";
	public static String FIELD__MENGENABSCHLUSS_FUHRE_OK = "MENGENABSCHLUSS_FUHRE_OK";
	public static String FIELD__NAME = "NAME";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__PASSWORT = "PASSWORT";
	public static String FIELD__PREISABSCHLUSS_FUHRE_OK = "PREISABSCHLUSS_FUHRE_OK";
	public static String FIELD__SONDERRECH_ZEIGE_OPLISTE_SALDO = "SONDERRECH_ZEIGE_OPLISTE_SALDO";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TELEFAX = "TELEFAX";
	public static String FIELD__TELEFON = "TELEFON";
	public static String FIELD__TEXTCOLLECTOR = "TEXTCOLLECTOR";
	public static String FIELD__TODO_SUPERVISOR = "TODO_SUPERVISOR";
	public static String FIELD__VERTICAL_OFFSET_MASKTABS = "VERTICAL_OFFSET_MASKTABS";
	public static String FIELD__VORNAME = "VORNAME";


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
		public String get_ANREDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANREDE");
	}

	public String get_ANREDE_cF() throws myException
	{
		return this.get_FormatedValue("ANREDE");	
	}

	public String get_ANREDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANREDE");
	}

	public String get_ANREDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANREDE",cNotNullValue);
	}

	public String get_ANREDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANREDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANREDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANREDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANREDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANREDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANREDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANREDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANREDE", calNewValueFormated);
	}
		public String get_AUTCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUTCODE");
	}

	public String get_AUTCODE_cF() throws myException
	{
		return this.get_FormatedValue("AUTCODE");	
	}

	public String get_AUTCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUTCODE");
	}

	public String get_AUTCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUTCODE",cNotNullValue);
	}

	public String get_AUTCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUTCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUTCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUTCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUTCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUTCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUTCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUTCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUTCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUTCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUTCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUTCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUTCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUTCODE", calNewValueFormated);
	}
		public String get_COLOR_MASK_ERROR_BLUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_ERROR_BLUE");
	}

	public String get_COLOR_MASK_ERROR_BLUE_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_ERROR_BLUE");	
	}

	public String get_COLOR_MASK_ERROR_BLUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_ERROR_BLUE");
	}

	public String get_COLOR_MASK_ERROR_BLUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_ERROR_BLUE",cNotNullValue);
	}

	public String get_COLOR_MASK_ERROR_BLUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_ERROR_BLUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_ERROR_BLUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_BLUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_BLUE", calNewValueFormated);
	}
		public Long get_COLOR_MASK_ERROR_BLUE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_ERROR_BLUE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_ERROR_BLUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_ERROR_BLUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_ERROR_BLUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_ERROR_BLUE").getDoubleValue();
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
	public String get_COLOR_MASK_ERROR_BLUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_ERROR_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_ERROR_BLUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_ERROR_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_ERROR_BLUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_ERROR_BLUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_ERROR_BLUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_ERROR_BLUE").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_ERROR_GREEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_ERROR_GREEN");
	}

	public String get_COLOR_MASK_ERROR_GREEN_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_ERROR_GREEN");	
	}

	public String get_COLOR_MASK_ERROR_GREEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_ERROR_GREEN");
	}

	public String get_COLOR_MASK_ERROR_GREEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_ERROR_GREEN",cNotNullValue);
	}

	public String get_COLOR_MASK_ERROR_GREEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_ERROR_GREEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_ERROR_GREEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_GREEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_GREEN", calNewValueFormated);
	}
		public Long get_COLOR_MASK_ERROR_GREEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_ERROR_GREEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_ERROR_GREEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_ERROR_GREEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_ERROR_GREEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_ERROR_GREEN").getDoubleValue();
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
	public String get_COLOR_MASK_ERROR_GREEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_ERROR_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_ERROR_GREEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_ERROR_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_ERROR_GREEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_ERROR_GREEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_ERROR_GREEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_ERROR_GREEN").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_ERROR_RED_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_ERROR_RED");
	}

	public String get_COLOR_MASK_ERROR_RED_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_ERROR_RED");	
	}

	public String get_COLOR_MASK_ERROR_RED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_ERROR_RED");
	}

	public String get_COLOR_MASK_ERROR_RED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_ERROR_RED",cNotNullValue);
	}

	public String get_COLOR_MASK_ERROR_RED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_ERROR_RED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_ERROR_RED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_ERROR_RED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_ERROR_RED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_ERROR_RED", calNewValueFormated);
	}
		public Long get_COLOR_MASK_ERROR_RED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_ERROR_RED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_ERROR_RED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_ERROR_RED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_ERROR_RED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_ERROR_RED").getDoubleValue();
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
	public String get_COLOR_MASK_ERROR_RED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_ERROR_RED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_ERROR_RED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_ERROR_RED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_ERROR_RED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_ERROR_RED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_ERROR_RED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_ERROR_RED").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_OK_BLUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_OK_BLUE");
	}

	public String get_COLOR_MASK_OK_BLUE_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_OK_BLUE");	
	}

	public String get_COLOR_MASK_OK_BLUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_OK_BLUE");
	}

	public String get_COLOR_MASK_OK_BLUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_OK_BLUE",cNotNullValue);
	}

	public String get_COLOR_MASK_OK_BLUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_OK_BLUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_OK_BLUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_OK_BLUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_BLUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_BLUE", calNewValueFormated);
	}
		public Long get_COLOR_MASK_OK_BLUE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_OK_BLUE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_OK_BLUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_OK_BLUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_OK_BLUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_OK_BLUE").getDoubleValue();
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
	public String get_COLOR_MASK_OK_BLUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_OK_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_OK_BLUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_OK_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_OK_BLUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_OK_BLUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_OK_BLUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_OK_BLUE").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_OK_GREEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_OK_GREEN");
	}

	public String get_COLOR_MASK_OK_GREEN_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_OK_GREEN");	
	}

	public String get_COLOR_MASK_OK_GREEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_OK_GREEN");
	}

	public String get_COLOR_MASK_OK_GREEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_OK_GREEN",cNotNullValue);
	}

	public String get_COLOR_MASK_OK_GREEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_OK_GREEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_OK_GREEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_OK_GREEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_GREEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_GREEN", calNewValueFormated);
	}
		public Long get_COLOR_MASK_OK_GREEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_OK_GREEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_OK_GREEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_OK_GREEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_OK_GREEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_OK_GREEN").getDoubleValue();
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
	public String get_COLOR_MASK_OK_GREEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_OK_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_OK_GREEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_OK_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_OK_GREEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_OK_GREEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_OK_GREEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_OK_GREEN").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_OK_RED_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_OK_RED");
	}

	public String get_COLOR_MASK_OK_RED_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_OK_RED");	
	}

	public String get_COLOR_MASK_OK_RED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_OK_RED");
	}

	public String get_COLOR_MASK_OK_RED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_OK_RED",cNotNullValue);
	}

	public String get_COLOR_MASK_OK_RED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_OK_RED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_OK_RED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_OK_RED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_OK_RED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_OK_RED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_OK_RED", calNewValueFormated);
	}
		public Long get_COLOR_MASK_OK_RED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_OK_RED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_OK_RED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_OK_RED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_OK_RED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_OK_RED").getDoubleValue();
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
	public String get_COLOR_MASK_OK_RED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_OK_RED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_OK_RED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_OK_RED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_OK_RED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_OK_RED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_OK_RED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_OK_RED").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_WARN_BLUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_WARN_BLUE");
	}

	public String get_COLOR_MASK_WARN_BLUE_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_WARN_BLUE");	
	}

	public String get_COLOR_MASK_WARN_BLUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_WARN_BLUE");
	}

	public String get_COLOR_MASK_WARN_BLUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_WARN_BLUE",cNotNullValue);
	}

	public String get_COLOR_MASK_WARN_BLUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_WARN_BLUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_WARN_BLUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_WARN_BLUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_BLUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_BLUE", calNewValueFormated);
	}
		public Long get_COLOR_MASK_WARN_BLUE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_WARN_BLUE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_WARN_BLUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_WARN_BLUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_WARN_BLUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_WARN_BLUE").getDoubleValue();
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
	public String get_COLOR_MASK_WARN_BLUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_WARN_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_WARN_BLUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_WARN_BLUE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_WARN_BLUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_WARN_BLUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_WARN_BLUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_WARN_BLUE").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_WARN_GREEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_WARN_GREEN");
	}

	public String get_COLOR_MASK_WARN_GREEN_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_WARN_GREEN");	
	}

	public String get_COLOR_MASK_WARN_GREEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_WARN_GREEN");
	}

	public String get_COLOR_MASK_WARN_GREEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_WARN_GREEN",cNotNullValue);
	}

	public String get_COLOR_MASK_WARN_GREEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_WARN_GREEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_WARN_GREEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_WARN_GREEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_GREEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_GREEN", calNewValueFormated);
	}
		public Long get_COLOR_MASK_WARN_GREEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_WARN_GREEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_WARN_GREEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_WARN_GREEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_WARN_GREEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_WARN_GREEN").getDoubleValue();
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
	public String get_COLOR_MASK_WARN_GREEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_WARN_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_WARN_GREEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_WARN_GREEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_WARN_GREEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_WARN_GREEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_WARN_GREEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_WARN_GREEN").getBigDecimalValue();
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
	
	
	public String get_COLOR_MASK_WARN_RED_cUF() throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_WARN_RED");
	}

	public String get_COLOR_MASK_WARN_RED_cF() throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_WARN_RED");	
	}

	public String get_COLOR_MASK_WARN_RED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("COLOR_MASK_WARN_RED");
	}

	public String get_COLOR_MASK_WARN_RED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("COLOR_MASK_WARN_RED",cNotNullValue);
	}

	public String get_COLOR_MASK_WARN_RED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("COLOR_MASK_WARN_RED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("COLOR_MASK_WARN_RED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_COLOR_MASK_WARN_RED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("COLOR_MASK_WARN_RED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_COLOR_MASK_WARN_RED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("COLOR_MASK_WARN_RED", calNewValueFormated);
	}
		public Long get_COLOR_MASK_WARN_RED_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("COLOR_MASK_WARN_RED").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_COLOR_MASK_WARN_RED_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_WARN_RED").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_COLOR_MASK_WARN_RED_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("COLOR_MASK_WARN_RED").getDoubleValue();
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
	public String get_COLOR_MASK_WARN_RED_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_WARN_RED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_COLOR_MASK_WARN_RED_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_COLOR_MASK_WARN_RED_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_COLOR_MASK_WARN_RED_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_WARN_RED").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_COLOR_MASK_WARN_RED_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("COLOR_MASK_WARN_RED").getBigDecimalValue();
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
	
	
	public String get_DEVELOPER_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEVELOPER");
	}

	public String get_DEVELOPER_cF() throws myException
	{
		return this.get_FormatedValue("DEVELOPER");	
	}

	public String get_DEVELOPER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEVELOPER");
	}

	public String get_DEVELOPER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEVELOPER",cNotNullValue);
	}

	public String get_DEVELOPER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEVELOPER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEVELOPER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEVELOPER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEVELOPER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEVELOPER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEVELOPER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEVELOPER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEVELOPER", calNewValueFormated);
	}
		public boolean is_DEVELOPER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DEVELOPER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DEVELOPER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DEVELOPER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_EIGENDEF_BREITEAENDERBAR_cUF() throws myException
	{
		return this.get_UnFormatedValue("EIGENDEF_BREITEAENDERBAR");
	}

	public String get_EIGENDEF_BREITEAENDERBAR_cF() throws myException
	{
		return this.get_FormatedValue("EIGENDEF_BREITEAENDERBAR");	
	}

	public String get_EIGENDEF_BREITEAENDERBAR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENDEF_BREITEAENDERBAR");
	}

	public String get_EIGENDEF_BREITEAENDERBAR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EIGENDEF_BREITEAENDERBAR",cNotNullValue);
	}

	public String get_EIGENDEF_BREITEAENDERBAR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EIGENDEF_BREITEAENDERBAR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", calNewValueFormated);
	}
		public boolean is_EIGENDEF_BREITEAENDERBAR_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_EIGENDEF_BREITEAENDERBAR_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_EIGENDEF_BREITEAENDERBAR_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_EIGENDEF_BREITEAENDERBAR_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_EIGENDEF_MENUEBREITE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EIGENDEF_MENUEBREITE");
	}

	public String get_EIGENDEF_MENUEBREITE_cF() throws myException
	{
		return this.get_FormatedValue("EIGENDEF_MENUEBREITE");	
	}

	public String get_EIGENDEF_MENUEBREITE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENDEF_MENUEBREITE");
	}

	public String get_EIGENDEF_MENUEBREITE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EIGENDEF_MENUEBREITE",cNotNullValue);
	}

	public String get_EIGENDEF_MENUEBREITE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EIGENDEF_MENUEBREITE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EIGENDEF_MENUEBREITE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EIGENDEF_MENUEBREITE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", calNewValueFormated);
	}
		public Long get_EIGENDEF_MENUEBREITE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("EIGENDEF_MENUEBREITE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_EIGENDEF_MENUEBREITE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EIGENDEF_MENUEBREITE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EIGENDEF_MENUEBREITE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EIGENDEF_MENUEBREITE").getDoubleValue();
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
	public String get_EIGENDEF_MENUEBREITE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_MENUEBREITE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EIGENDEF_MENUEBREITE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_MENUEBREITE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EIGENDEF_MENUEBREITE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EIGENDEF_MENUEBREITE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EIGENDEF_MENUEBREITE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EIGENDEF_MENUEBREITE").getBigDecimalValue();
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
	
	
	public String get_EIGENDEF_SCHRIFTGROESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EIGENDEF_SCHRIFTGROESSE");
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_cF() throws myException
	{
		return this.get_FormatedValue("EIGENDEF_SCHRIFTGROESSE");	
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENDEF_SCHRIFTGROESSE");
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EIGENDEF_SCHRIFTGROESSE",cNotNullValue);
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EIGENDEF_SCHRIFTGROESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", calNewValueFormated);
	}
		public Long get_EIGENDEF_SCHRIFTGROESSE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_EIGENDEF_SCHRIFTGROESSE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EIGENDEF_SCHRIFTGROESSE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getDoubleValue();
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
	public String get_EIGENDEF_SCHRIFTGROESSE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_SCHRIFTGROESSE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_EIGENDEF_SCHRIFTGROESSE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_SCHRIFTGROESSE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_EIGENDEF_SCHRIFTGROESSE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EIGENDEF_SCHRIFTGROESSE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getBigDecimalValue();
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
	
	
	public String get_EMAIL_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL");
	}

	public String get_EMAIL_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL");	
	}

	public String get_EMAIL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL");
	}

	public String get_EMAIL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL",cNotNullValue);
	}

	public String get_EMAIL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL", calNewValueFormated);
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
		public String get_FENSTER_MIT_SCHATTEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FENSTER_MIT_SCHATTEN");
	}

	public String get_FENSTER_MIT_SCHATTEN_cF() throws myException
	{
		return this.get_FormatedValue("FENSTER_MIT_SCHATTEN");	
	}

	public String get_FENSTER_MIT_SCHATTEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FENSTER_MIT_SCHATTEN");
	}

	public String get_FENSTER_MIT_SCHATTEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FENSTER_MIT_SCHATTEN",cNotNullValue);
	}

	public String get_FENSTER_MIT_SCHATTEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FENSTER_MIT_SCHATTEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FENSTER_MIT_SCHATTEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FENSTER_MIT_SCHATTEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", calNewValueFormated);
	}
		public boolean is_FENSTER_MIT_SCHATTEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FENSTER_MIT_SCHATTEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FENSTER_MIT_SCHATTEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FENSTER_MIT_SCHATTEN_cUF_NN("N").equals("N"))
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
		public String get_GESCHAEFTSFUEHRER_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESCHAEFTSFUEHRER");
	}

	public String get_GESCHAEFTSFUEHRER_cF() throws myException
	{
		return this.get_FormatedValue("GESCHAEFTSFUEHRER");	
	}

	public String get_GESCHAEFTSFUEHRER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHAEFTSFUEHRER");
	}

	public String get_GESCHAEFTSFUEHRER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESCHAEFTSFUEHRER",cNotNullValue);
	}

	public String get_GESCHAEFTSFUEHRER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESCHAEFTSFUEHRER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESCHAEFTSFUEHRER", calNewValueFormated);
	}
		public boolean is_GESCHAEFTSFUEHRER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHAEFTSFUEHRER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GESCHAEFTSFUEHRER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GESCHAEFTSFUEHRER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_HAT_FAHRPLAN_BUTTON_cUF() throws myException
	{
		return this.get_UnFormatedValue("HAT_FAHRPLAN_BUTTON");
	}

	public String get_HAT_FAHRPLAN_BUTTON_cF() throws myException
	{
		return this.get_FormatedValue("HAT_FAHRPLAN_BUTTON");	
	}

	public String get_HAT_FAHRPLAN_BUTTON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HAT_FAHRPLAN_BUTTON");
	}

	public String get_HAT_FAHRPLAN_BUTTON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HAT_FAHRPLAN_BUTTON",cNotNullValue);
	}

	public String get_HAT_FAHRPLAN_BUTTON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HAT_FAHRPLAN_BUTTON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HAT_FAHRPLAN_BUTTON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", calNewValueFormated);
	}
		public boolean is_HAT_FAHRPLAN_BUTTON_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_HAT_FAHRPLAN_BUTTON_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_HAT_FAHRPLAN_BUTTON_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_HAT_FAHRPLAN_BUTTON_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_DATEV_PROFILE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_DATEV_PROFILE");
	}

	public String get_ID_DATEV_PROFILE_cF() throws myException
	{
		return this.get_FormatedValue("ID_DATEV_PROFILE");	
	}

	public String get_ID_DATEV_PROFILE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_DATEV_PROFILE");
	}

	public String get_ID_DATEV_PROFILE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_DATEV_PROFILE",cNotNullValue);
	}

	public String get_ID_DATEV_PROFILE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_DATEV_PROFILE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_DATEV_PROFILE", calNewValueFormated);
	}
		public Long get_ID_DATEV_PROFILE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_DATEV_PROFILE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_DATEV_PROFILE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_DATEV_PROFILE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_DATEV_PROFILE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_DATEV_PROFILE").getDoubleValue();
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
	public String get_ID_DATEV_PROFILE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DATEV_PROFILE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_DATEV_PROFILE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DATEV_PROFILE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_DATEV_PROFILE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DATEV_PROFILE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_DATEV_PROFILE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_DATEV_PROFILE").getBigDecimalValue();
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
	
	
	public String get_ID_USERGROUP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USERGROUP");
	}

	public String get_ID_USERGROUP_cF() throws myException
	{
		return this.get_FormatedValue("ID_USERGROUP");	
	}

	public String get_ID_USERGROUP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USERGROUP");
	}

	public String get_ID_USERGROUP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USERGROUP",cNotNullValue);
	}

	public String get_ID_USERGROUP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USERGROUP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USERGROUP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USERGROUP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USERGROUP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USERGROUP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USERGROUP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USERGROUP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USERGROUP", calNewValueFormated);
	}
		public Long get_ID_USERGROUP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USERGROUP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USERGROUP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USERGROUP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USERGROUP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USERGROUP").getDoubleValue();
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
	public String get_ID_USERGROUP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USERGROUP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USERGROUP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USERGROUP_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USERGROUP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USERGROUP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USERGROUP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USERGROUP").getBigDecimalValue();
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
	
	
	public String get_IST_FAHRER_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_FAHRER");
	}

	public String get_IST_FAHRER_cF() throws myException
	{
		return this.get_FormatedValue("IST_FAHRER");	
	}

	public String get_IST_FAHRER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_FAHRER");
	}

	public String get_IST_FAHRER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_FAHRER",cNotNullValue);
	}

	public String get_IST_FAHRER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_FAHRER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_FAHRER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_FAHRER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_FAHRER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_FAHRER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_FAHRER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_FAHRER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_FAHRER", calNewValueFormated);
	}
		public boolean is_IST_FAHRER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_FAHRER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_FAHRER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_FAHRER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_SUPERVISOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_SUPERVISOR");
	}

	public String get_IST_SUPERVISOR_cF() throws myException
	{
		return this.get_FormatedValue("IST_SUPERVISOR");	
	}

	public String get_IST_SUPERVISOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_SUPERVISOR");
	}

	public String get_IST_SUPERVISOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_SUPERVISOR",cNotNullValue);
	}

	public String get_IST_SUPERVISOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_SUPERVISOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_SUPERVISOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_SUPERVISOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_SUPERVISOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_SUPERVISOR", calNewValueFormated);
	}
		public boolean is_IST_SUPERVISOR_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_SUPERVISOR_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_SUPERVISOR_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_SUPERVISOR_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_VERWALTUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_VERWALTUNG");
	}

	public String get_IST_VERWALTUNG_cF() throws myException
	{
		return this.get_FormatedValue("IST_VERWALTUNG");	
	}

	public String get_IST_VERWALTUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_VERWALTUNG");
	}

	public String get_IST_VERWALTUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_VERWALTUNG",cNotNullValue);
	}

	public String get_IST_VERWALTUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_VERWALTUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_VERWALTUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_VERWALTUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_VERWALTUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_VERWALTUNG", calNewValueFormated);
	}
		public boolean is_IST_VERWALTUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_VERWALTUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_VERWALTUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_VERWALTUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("KUERZEL");
	}

	public String get_KUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("KUERZEL");	
	}

	public String get_KUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KUERZEL");
	}

	public String get_KUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KUERZEL",cNotNullValue);
	}

	public String get_KUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KUERZEL", calNewValueFormated);
	}
		public String get_LAUFZEIT_SESSION_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAUFZEIT_SESSION");
	}

	public String get_LAUFZEIT_SESSION_cF() throws myException
	{
		return this.get_FormatedValue("LAUFZEIT_SESSION");	
	}

	public String get_LAUFZEIT_SESSION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAUFZEIT_SESSION");
	}

	public String get_LAUFZEIT_SESSION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAUFZEIT_SESSION",cNotNullValue);
	}

	public String get_LAUFZEIT_SESSION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAUFZEIT_SESSION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAUFZEIT_SESSION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAUFZEIT_SESSION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAUFZEIT_SESSION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAUFZEIT_SESSION", calNewValueFormated);
	}
		public Long get_LAUFZEIT_SESSION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LAUFZEIT_SESSION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LAUFZEIT_SESSION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LAUFZEIT_SESSION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LAUFZEIT_SESSION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LAUFZEIT_SESSION").getDoubleValue();
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
	public String get_LAUFZEIT_SESSION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAUFZEIT_SESSION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_LAUFZEIT_SESSION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAUFZEIT_SESSION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_LAUFZEIT_SESSION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LAUFZEIT_SESSION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LAUFZEIT_SESSION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LAUFZEIT_SESSION").getBigDecimalValue();
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
		public String get_LISTEGESAMTLAENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LISTEGESAMTLAENGE");
	}

	public String get_LISTEGESAMTLAENGE_cF() throws myException
	{
		return this.get_FormatedValue("LISTEGESAMTLAENGE");	
	}

	public String get_LISTEGESAMTLAENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LISTEGESAMTLAENGE");
	}

	public String get_LISTEGESAMTLAENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LISTEGESAMTLAENGE",cNotNullValue);
	}

	public String get_LISTEGESAMTLAENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LISTEGESAMTLAENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LISTEGESAMTLAENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LISTEGESAMTLAENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LISTEGESAMTLAENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTEGESAMTLAENGE", calNewValueFormated);
	}
		public Long get_LISTEGESAMTLAENGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LISTEGESAMTLAENGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LISTEGESAMTLAENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LISTEGESAMTLAENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LISTEGESAMTLAENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LISTEGESAMTLAENGE").getDoubleValue();
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
	public String get_LISTEGESAMTLAENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTEGESAMTLAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_LISTEGESAMTLAENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTEGESAMTLAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_LISTEGESAMTLAENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LISTEGESAMTLAENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LISTEGESAMTLAENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LISTEGESAMTLAENGE").getBigDecimalValue();
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
	
	
	public String get_LISTESEITELAENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LISTESEITELAENGE");
	}

	public String get_LISTESEITELAENGE_cF() throws myException
	{
		return this.get_FormatedValue("LISTESEITELAENGE");	
	}

	public String get_LISTESEITELAENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LISTESEITELAENGE");
	}

	public String get_LISTESEITELAENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LISTESEITELAENGE",cNotNullValue);
	}

	public String get_LISTESEITELAENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LISTESEITELAENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LISTESEITELAENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LISTESEITELAENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LISTESEITELAENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTESEITELAENGE", calNewValueFormated);
	}
		public Long get_LISTESEITELAENGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LISTESEITELAENGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LISTESEITELAENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LISTESEITELAENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LISTESEITELAENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LISTESEITELAENGE").getDoubleValue();
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
	public String get_LISTESEITELAENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTESEITELAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_LISTESEITELAENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTESEITELAENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_LISTESEITELAENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LISTESEITELAENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LISTESEITELAENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LISTESEITELAENGE").getBigDecimalValue();
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
	
	
	public String get_MAIL_SIGNATUR_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAIL_SIGNATUR");
	}

	public String get_MAIL_SIGNATUR_cF() throws myException
	{
		return this.get_FormatedValue("MAIL_SIGNATUR");	
	}

	public String get_MAIL_SIGNATUR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_SIGNATUR");
	}

	public String get_MAIL_SIGNATUR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAIL_SIGNATUR",cNotNullValue);
	}

	public String get_MAIL_SIGNATUR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAIL_SIGNATUR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAIL_SIGNATUR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAIL_SIGNATUR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAIL_SIGNATUR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAIL_SIGNATUR", calNewValueFormated);
	}
		public String get_MELDUNG_KREDITVERS_ABLAUF_cUF() throws myException
	{
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_ABLAUF");
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_cF() throws myException
	{
		return this.get_FormatedValue("MELDUNG_KREDITVERS_ABLAUF");	
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MELDUNG_KREDITVERS_ABLAUF");
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_ABLAUF",cNotNullValue);
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MELDUNG_KREDITVERS_ABLAUF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", calNewValueFormated);
	}
		public boolean is_MELDUNG_KREDITVERS_ABLAUF_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MELDUNG_KREDITVERS_ABLAUF_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MELDUNG_KREDITVERS_ABLAUF_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MELDUNG_KREDITVERS_ABLAUF_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_MELDUNG_KREDITVERS_BETRAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_BETRAG");
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_cF() throws myException
	{
		return this.get_FormatedValue("MELDUNG_KREDITVERS_BETRAG");	
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MELDUNG_KREDITVERS_BETRAG");
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_BETRAG",cNotNullValue);
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MELDUNG_KREDITVERS_BETRAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", calNewValueFormated);
	}
		public boolean is_MELDUNG_KREDITVERS_BETRAG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MELDUNG_KREDITVERS_BETRAG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MELDUNG_KREDITVERS_BETRAG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MELDUNG_KREDITVERS_BETRAG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_MENGENABSCHLUSS_FUHRE_OK_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGENABSCHLUSS_FUHRE_OK");
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_cF() throws myException
	{
		return this.get_FormatedValue("MENGENABSCHLUSS_FUHRE_OK");	
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGENABSCHLUSS_FUHRE_OK");
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGENABSCHLUSS_FUHRE_OK",cNotNullValue);
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGENABSCHLUSS_FUHRE_OK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", calNewValueFormated);
	}
		public boolean is_MENGENABSCHLUSS_FUHRE_OK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MENGENABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MENGENABSCHLUSS_FUHRE_OK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MENGENABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME");
	}

	public String get_NAME_cF() throws myException
	{
		return this.get_FormatedValue("NAME");	
	}

	public String get_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME");
	}

	public String get_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME",cNotNullValue);
	}

	public String get_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME", calNewValueFormated);
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
		public String get_PASSWORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PASSWORT");
	}

	public String get_PASSWORT_cF() throws myException
	{
		return this.get_FormatedValue("PASSWORT");	
	}

	public String get_PASSWORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PASSWORT");
	}

	public String get_PASSWORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PASSWORT",cNotNullValue);
	}

	public String get_PASSWORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PASSWORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PASSWORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PASSWORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PASSWORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PASSWORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PASSWORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASSWORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASSWORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PASSWORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PASSWORT", calNewValueFormated);
	}
		public String get_PREISABSCHLUSS_FUHRE_OK_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREISABSCHLUSS_FUHRE_OK");
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_cF() throws myException
	{
		return this.get_FormatedValue("PREISABSCHLUSS_FUHRE_OK");	
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREISABSCHLUSS_FUHRE_OK");
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREISABSCHLUSS_FUHRE_OK",cNotNullValue);
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREISABSCHLUSS_FUHRE_OK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", calNewValueFormated);
	}
		public boolean is_PREISABSCHLUSS_FUHRE_OK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREISABSCHLUSS_FUHRE_OK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREISABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF() throws myException
	{
		return this.get_UnFormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO");
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cF() throws myException
	{
		return this.get_FormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO");	
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SONDERRECH_ZEIGE_OPLISTE_SALDO");
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO",cNotNullValue);
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", calNewValueFormated);
	}
		public boolean is_SONDERRECH_ZEIGE_OPLISTE_SALDO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SONDERRECH_ZEIGE_OPLISTE_SALDO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF_NN("N").equals("N"))
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
	
	
	public String get_TELEFAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("TELEFAX");
	}

	public String get_TELEFAX_cF() throws myException
	{
		return this.get_FormatedValue("TELEFAX");	
	}

	public String get_TELEFAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TELEFAX");
	}

	public String get_TELEFAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TELEFAX",cNotNullValue);
	}

	public String get_TELEFAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TELEFAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TELEFAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TELEFAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TELEFAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TELEFAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFAX", calNewValueFormated);
	}
		public String get_TELEFON_cUF() throws myException
	{
		return this.get_UnFormatedValue("TELEFON");
	}

	public String get_TELEFON_cF() throws myException
	{
		return this.get_FormatedValue("TELEFON");	
	}

	public String get_TELEFON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TELEFON");
	}

	public String get_TELEFON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TELEFON",cNotNullValue);
	}

	public String get_TELEFON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TELEFON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TELEFON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TELEFON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TELEFON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON", calNewValueFormated);
	}
		public String get_TEXTCOLLECTOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEXTCOLLECTOR");
	}

	public String get_TEXTCOLLECTOR_cF() throws myException
	{
		return this.get_FormatedValue("TEXTCOLLECTOR");	
	}

	public String get_TEXTCOLLECTOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEXTCOLLECTOR");
	}

	public String get_TEXTCOLLECTOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEXTCOLLECTOR",cNotNullValue);
	}

	public String get_TEXTCOLLECTOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEXTCOLLECTOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEXTCOLLECTOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEXTCOLLECTOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEXTCOLLECTOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEXTCOLLECTOR", calNewValueFormated);
	}
		public boolean is_TEXTCOLLECTOR_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TEXTCOLLECTOR_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TEXTCOLLECTOR_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TEXTCOLLECTOR_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_TODO_SUPERVISOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("TODO_SUPERVISOR");
	}

	public String get_TODO_SUPERVISOR_cF() throws myException
	{
		return this.get_FormatedValue("TODO_SUPERVISOR");	
	}

	public String get_TODO_SUPERVISOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TODO_SUPERVISOR");
	}

	public String get_TODO_SUPERVISOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TODO_SUPERVISOR",cNotNullValue);
	}

	public String get_TODO_SUPERVISOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TODO_SUPERVISOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TODO_SUPERVISOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TODO_SUPERVISOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TODO_SUPERVISOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TODO_SUPERVISOR", calNewValueFormated);
	}
		public boolean is_TODO_SUPERVISOR_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TODO_SUPERVISOR_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TODO_SUPERVISOR_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TODO_SUPERVISOR_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_VERTICAL_OFFSET_MASKTABS_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERTICAL_OFFSET_MASKTABS");
	}

	public String get_VERTICAL_OFFSET_MASKTABS_cF() throws myException
	{
		return this.get_FormatedValue("VERTICAL_OFFSET_MASKTABS");	
	}

	public String get_VERTICAL_OFFSET_MASKTABS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERTICAL_OFFSET_MASKTABS");
	}

	public String get_VERTICAL_OFFSET_MASKTABS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERTICAL_OFFSET_MASKTABS",cNotNullValue);
	}

	public String get_VERTICAL_OFFSET_MASKTABS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERTICAL_OFFSET_MASKTABS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", calNewValueFormated);
	}
		public Long get_VERTICAL_OFFSET_MASKTABS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("VERTICAL_OFFSET_MASKTABS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_VERTICAL_OFFSET_MASKTABS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("VERTICAL_OFFSET_MASKTABS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_VERTICAL_OFFSET_MASKTABS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("VERTICAL_OFFSET_MASKTABS").getDoubleValue();
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
	public String get_VERTICAL_OFFSET_MASKTABS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERTICAL_OFFSET_MASKTABS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_VERTICAL_OFFSET_MASKTABS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERTICAL_OFFSET_MASKTABS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_VERTICAL_OFFSET_MASKTABS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("VERTICAL_OFFSET_MASKTABS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_VERTICAL_OFFSET_MASKTABS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("VERTICAL_OFFSET_MASKTABS").getBigDecimalValue();
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
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("ANREDE",MyRECORD.DATATYPES.TEXT);
	put("AUTCODE",MyRECORD.DATATYPES.TEXT);
	put("COLOR_MASK_ERROR_BLUE",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_ERROR_GREEN",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_ERROR_RED",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_OK_BLUE",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_OK_GREEN",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_OK_RED",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_WARN_BLUE",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_WARN_GREEN",MyRECORD.DATATYPES.NUMBER);
	put("COLOR_MASK_WARN_RED",MyRECORD.DATATYPES.NUMBER);
	put("DEVELOPER",MyRECORD.DATATYPES.YESNO);
	put("EIGENDEF_BREITEAENDERBAR",MyRECORD.DATATYPES.YESNO);
	put("EIGENDEF_MENUEBREITE",MyRECORD.DATATYPES.NUMBER);
	put("EIGENDEF_SCHRIFTGROESSE",MyRECORD.DATATYPES.NUMBER);
	put("EMAIL",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FENSTER_MIT_SCHATTEN",MyRECORD.DATATYPES.YESNO);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GESCHAEFTSFUEHRER",MyRECORD.DATATYPES.YESNO);
	put("HAT_FAHRPLAN_BUTTON",MyRECORD.DATATYPES.YESNO);
	put("ID_DATEV_PROFILE",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_SPRACHE",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER",MyRECORD.DATATYPES.NUMBER);
	put("ID_USERGROUP",MyRECORD.DATATYPES.NUMBER);
	put("IST_FAHRER",MyRECORD.DATATYPES.YESNO);
	put("IST_SUPERVISOR",MyRECORD.DATATYPES.YESNO);
	put("IST_VERWALTUNG",MyRECORD.DATATYPES.YESNO);
	put("KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("LAUFZEIT_SESSION",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LISTEGESAMTLAENGE",MyRECORD.DATATYPES.NUMBER);
	put("LISTESEITELAENGE",MyRECORD.DATATYPES.NUMBER);
	put("MAIL_SIGNATUR",MyRECORD.DATATYPES.TEXT);
	put("MELDUNG_KREDITVERS_ABLAUF",MyRECORD.DATATYPES.YESNO);
	put("MELDUNG_KREDITVERS_BETRAG",MyRECORD.DATATYPES.YESNO);
	put("MENGENABSCHLUSS_FUHRE_OK",MyRECORD.DATATYPES.YESNO);
	put("NAME",MyRECORD.DATATYPES.TEXT);
	put("NAME1",MyRECORD.DATATYPES.TEXT);
	put("NAME2",MyRECORD.DATATYPES.TEXT);
	put("NAME3",MyRECORD.DATATYPES.TEXT);
	put("PASSWORT",MyRECORD.DATATYPES.TEXT);
	put("PREISABSCHLUSS_FUHRE_OK",MyRECORD.DATATYPES.YESNO);
	put("SONDERRECH_ZEIGE_OPLISTE_SALDO",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TELEFAX",MyRECORD.DATATYPES.TEXT);
	put("TELEFON",MyRECORD.DATATYPES.TEXT);
	put("TEXTCOLLECTOR",MyRECORD.DATATYPES.YESNO);
	put("TODO_SUPERVISOR",MyRECORD.DATATYPES.YESNO);
	put("VERTICAL_OFFSET_MASKTABS",MyRECORD.DATATYPES.NUMBER);
	put("VORNAME",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_USER.HM_FIELDS;	
	}

}
