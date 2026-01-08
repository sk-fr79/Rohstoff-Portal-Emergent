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

public class RECORD_CONTAINER_STATION extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_CONTAINER_STATION";
    public static String IDFIELD   = "ID_CONTAINER_STATION";
    

	//erzeugen eines RECORDNEW_JT_CONTAINER_STATION - felds
	private RECORDNEW_CONTAINER_STATION   recNEW = null;

    private _TAB  tab = _TAB.container_station;  



	public RECORD_CONTAINER_STATION() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_CONTAINER_STATION");
	}


	public RECORD_CONTAINER_STATION(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_CONTAINER_STATION");
	}



	public RECORD_CONTAINER_STATION(RECORD_CONTAINER_STATION recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_CONTAINER_STATION");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_CONTAINER_STATION.TABLENAME);
	}


	//2014-04-03
	public RECORD_CONTAINER_STATION(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_CONTAINER_STATION");
		this.set_Tablename_To_FieldMetaDefs(RECORD_CONTAINER_STATION.TABLENAME);
	}




	public RECORD_CONTAINER_STATION(long lID_Unformated) throws myException
	{
		super("JT_CONTAINER_STATION","ID_CONTAINER_STATION",""+lID_Unformated);
		this.set_TABLE_NAME("JT_CONTAINER_STATION");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_CONTAINER_STATION.TABLENAME);
	}

	public RECORD_CONTAINER_STATION(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_CONTAINER_STATION");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_CONTAINER_STATION", "ID_CONTAINER_STATION="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_CONTAINER_STATION", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_CONTAINER_STATION.TABLENAME);
	}
	
	

	public RECORD_CONTAINER_STATION(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_CONTAINER_STATION","ID_CONTAINER_STATION",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_CONTAINER_STATION");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_CONTAINER_STATION.TABLENAME);

	}


	public RECORD_CONTAINER_STATION(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_CONTAINER_STATION");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_CONTAINER_STATION", "ID_CONTAINER_STATION="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_CONTAINER_STATION", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_CONTAINER_STATION.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_CONTAINER_STATION();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_CONTAINER_STATION.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_CONTAINER_STATION";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_CONTAINER_STATION_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_CONTAINER_STATION_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_CONTAINER_STATION was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_CONTAINER_STATION", bibE2.cTO(), "ID_CONTAINER_STATION="+this.get_ID_CONTAINER_STATION_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_CONTAINER_STATION was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_CONTAINER_STATION", bibE2.cTO(), "ID_CONTAINER_STATION="+this.get_ID_CONTAINER_STATION_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_CONTAINER_STATION="+this.get_ID_CONTAINER_STATION_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_CONTAINER_STATION="+this.get_ID_CONTAINER_STATION_cUF();
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
			if (S.isFull(this.get_ID_CONTAINER_STATION_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_CONTAINER_STATION", "ID_CONTAINER_STATION="+this.get_ID_CONTAINER_STATION_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_CONTAINER_STATION get_RECORDNEW_CONTAINER_STATION() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_CONTAINER_STATION();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_CONTAINER_STATION(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_CONTAINER_STATION create_Instance() throws myException {
		return new RECORD_CONTAINER_STATION();
	}
	
	public static RECORD_CONTAINER_STATION create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_CONTAINER_STATION(Conn);
    }

	public static RECORD_CONTAINER_STATION create_Instance(RECORD_CONTAINER_STATION recordOrig) {
		return new RECORD_CONTAINER_STATION(recordOrig);
    }

	public static RECORD_CONTAINER_STATION create_Instance(long lID_Unformated) throws myException {
		return new RECORD_CONTAINER_STATION(lID_Unformated);
    }

	public static RECORD_CONTAINER_STATION create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_CONTAINER_STATION(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_CONTAINER_STATION create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_CONTAINER_STATION(lID_Unformated, Conn);
	}

	public static RECORD_CONTAINER_STATION create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_CONTAINER_STATION(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_CONTAINER_STATION create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_CONTAINER_STATION(recordOrig);	    
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
    public RECORD_CONTAINER_STATION build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_STATION WHERE ID_CONTAINER_STATION="+this.get_ID_CONTAINER_STATION_cUF());
      }
      //return new RECORD_CONTAINER_STATION(this.get_cSQL_4_Build());
      RECORD_CONTAINER_STATION rec = new RECORD_CONTAINER_STATION();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_CONTAINER_STATION
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_CONTAINER_STATION-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_CONTAINER_STATION get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_CONTAINER_STATION_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_CONTAINER_STATION  recNew = new RECORDNEW_CONTAINER_STATION();
		
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
    public RECORD_CONTAINER_STATION set_recordNew(RECORDNEW_CONTAINER_STATION recnew) throws myException {
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
	
	



		private RECORD_USER UP_RECORD_USER_id_user_auftraggeber = null;




		private RECORD_USER UP_RECORD_USER_id_user_auftragnehmer = null;




		private RECORD_USER UP_RECORD_USER_id_user_auftragnehmer_plan = null;




		private RECORD_USER UP_RECORD_USER_id_user_buchung = null;




		private RECORD_CONTAINER_ZYKLUS UP_RECORD_CONTAINER_ZYKLUS_id_container_zyklus = null;




		private RECORD_LAGERPLATZ UP_RECORD_LAGERPLATZ_id_lagerplatz = null;




		private RECORD_LAGERPLATZ UP_RECORD_LAGERPLATZ_id_lagerplatz_planung = null;




		private RECORD_MASCHINEN UP_RECORD_MASCHINEN_id_maschinen = null;






	
	/**
	 * References the Field ID_USER_AUFTRAGGEBER
	 * Falls keine this.get_ID_USER_AUFTRAGGEBER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_auftraggeber() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_AUFTRAGGEBER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_auftraggeber==null)
		{
			this.UP_RECORD_USER_id_user_auftraggeber = new RECORD_USER(this.get_ID_USER_AUFTRAGGEBER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_auftraggeber;
	}
	





	
	/**
	 * References the Field ID_USER_AUFTRAGNEHMER
	 * Falls keine this.get_ID_USER_AUFTRAGNEHMER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_auftragnehmer() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_AUFTRAGNEHMER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_auftragnehmer==null)
		{
			this.UP_RECORD_USER_id_user_auftragnehmer = new RECORD_USER(this.get_ID_USER_AUFTRAGNEHMER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_auftragnehmer;
	}
	





	
	/**
	 * References the Field ID_USER_AUFTRAGNEHMER_PLAN
	 * Falls keine this.get_ID_USER_AUFTRAGNEHMER_PLAN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_auftragnehmer_plan() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_AUFTRAGNEHMER_PLAN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_auftragnehmer_plan==null)
		{
			this.UP_RECORD_USER_id_user_auftragnehmer_plan = new RECORD_USER(this.get_ID_USER_AUFTRAGNEHMER_PLAN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_auftragnehmer_plan;
	}
	





	
	/**
	 * References the Field ID_USER_BUCHUNG
	 * Falls keine this.get_ID_USER_BUCHUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_buchung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_BUCHUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_buchung==null)
		{
			this.UP_RECORD_USER_id_user_buchung = new RECORD_USER(this.get_ID_USER_BUCHUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_buchung;
	}
	





	
	/**
	 * References the Field ID_CONTAINER_ZYKLUS
	 * Falls keine this.get_ID_CONTAINER_ZYKLUS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_CONTAINER_ZYKLUS get_UP_RECORD_CONTAINER_ZYKLUS_id_container_zyklus() throws myException
	{
		if (S.isEmpty(this.get_ID_CONTAINER_ZYKLUS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_CONTAINER_ZYKLUS_id_container_zyklus==null)
		{
			this.UP_RECORD_CONTAINER_ZYKLUS_id_container_zyklus = new RECORD_CONTAINER_ZYKLUS(this.get_ID_CONTAINER_ZYKLUS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_CONTAINER_ZYKLUS_id_container_zyklus;
	}
	





	
	/**
	 * References the Field ID_LAGERPLATZ
	 * Falls keine this.get_ID_LAGERPLATZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAGERPLATZ get_UP_RECORD_LAGERPLATZ_id_lagerplatz() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGERPLATZ_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAGERPLATZ_id_lagerplatz==null)
		{
			this.UP_RECORD_LAGERPLATZ_id_lagerplatz = new RECORD_LAGERPLATZ(this.get_ID_LAGERPLATZ_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAGERPLATZ_id_lagerplatz;
	}
	





	
	/**
	 * References the Field ID_LAGERPLATZ_PLANUNG
	 * Falls keine this.get_ID_LAGERPLATZ_PLANUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAGERPLATZ get_UP_RECORD_LAGERPLATZ_id_lagerplatz_planung() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGERPLATZ_PLANUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAGERPLATZ_id_lagerplatz_planung==null)
		{
			this.UP_RECORD_LAGERPLATZ_id_lagerplatz_planung = new RECORD_LAGERPLATZ(this.get_ID_LAGERPLATZ_PLANUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAGERPLATZ_id_lagerplatz_planung;
	}
	





	
	/**
	 * References the Field ID_MASCHINEN
	 * Falls keine this.get_ID_MASCHINEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MASCHINEN get_UP_RECORD_MASCHINEN_id_maschinen() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MASCHINEN_id_maschinen==null)
		{
			this.UP_RECORD_MASCHINEN_id_maschinen = new RECORD_MASCHINEN(this.get_ID_MASCHINEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MASCHINEN_id_maschinen;
	}
	

	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__DATUM_PLANUNG_BIS = "DATUM_PLANUNG_BIS";
	public static String FIELD__DATUM_PLANUNG_VON = "DATUM_PLANUNG_VON";
	public static String FIELD__DATUM_ZEIT_BUCHUNG = "DATUM_ZEIT_BUCHUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_CONTAINER_STATION = "ID_CONTAINER_STATION";
	public static String FIELD__ID_CONTAINER_ZYKLUS = "ID_CONTAINER_ZYKLUS";
	public static String FIELD__ID_LAGERPLATZ = "ID_LAGERPLATZ";
	public static String FIELD__ID_LAGERPLATZ_PLANUNG = "ID_LAGERPLATZ_PLANUNG";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_MASCHINEN = "ID_MASCHINEN";
	public static String FIELD__ID_USER_AUFTRAGGEBER = "ID_USER_AUFTRAGGEBER";
	public static String FIELD__ID_USER_AUFTRAGNEHMER = "ID_USER_AUFTRAGNEHMER";
	public static String FIELD__ID_USER_AUFTRAGNEHMER_PLAN = "ID_USER_AUFTRAGNEHMER_PLAN";
	public static String FIELD__ID_USER_BUCHUNG = "ID_USER_BUCHUNG";
	public static String FIELD__KFZKENNZEICHEN = "KFZKENNZEICHEN";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TAETIGKEIT = "TAETIGKEIT";
	public static String FIELD__TAETIGKEIT_PLANUNG = "TAETIGKEIT_PLANUNG";


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
		public String get_DATUM_PLANUNG_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_PLANUNG_BIS");
	}

	public String get_DATUM_PLANUNG_BIS_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_PLANUNG_BIS");	
	}

	public String get_DATUM_PLANUNG_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_PLANUNG_BIS");
	}

	public String get_DATUM_PLANUNG_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_PLANUNG_BIS",cNotNullValue);
	}

	public String get_DATUM_PLANUNG_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_PLANUNG_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_PLANUNG_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_PLANUNG_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_PLANUNG_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_BIS", calNewValueFormated);
	}
		public String get_DATUM_PLANUNG_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_PLANUNG_VON");
	}

	public String get_DATUM_PLANUNG_VON_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_PLANUNG_VON");	
	}

	public String get_DATUM_PLANUNG_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_PLANUNG_VON");
	}

	public String get_DATUM_PLANUNG_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_PLANUNG_VON",cNotNullValue);
	}

	public String get_DATUM_PLANUNG_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_PLANUNG_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_PLANUNG_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_PLANUNG_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_PLANUNG_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_PLANUNG_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_PLANUNG_VON", calNewValueFormated);
	}
		public String get_DATUM_ZEIT_BUCHUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ZEIT_BUCHUNG");
	}

	public String get_DATUM_ZEIT_BUCHUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ZEIT_BUCHUNG");	
	}

	public String get_DATUM_ZEIT_BUCHUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ZEIT_BUCHUNG");
	}

	public String get_DATUM_ZEIT_BUCHUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ZEIT_BUCHUNG",cNotNullValue);
	}

	public String get_DATUM_ZEIT_BUCHUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ZEIT_BUCHUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ZEIT_BUCHUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZEIT_BUCHUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ZEIT_BUCHUNG", calNewValueFormated);
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
		public String get_ID_CONTAINER_STATION_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINER_STATION");
	}

	public String get_ID_CONTAINER_STATION_cF() throws myException
	{
		return this.get_FormatedValue("ID_CONTAINER_STATION");	
	}

	public String get_ID_CONTAINER_STATION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_CONTAINER_STATION");
	}

	public String get_ID_CONTAINER_STATION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINER_STATION",cNotNullValue);
	}

	public String get_ID_CONTAINER_STATION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_CONTAINER_STATION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_CONTAINER_STATION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER_STATION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_CONTAINER_STATION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_STATION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_STATION", calNewValueFormated);
	}
		public Long get_ID_CONTAINER_STATION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_CONTAINER_STATION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_CONTAINER_STATION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_CONTAINER_STATION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_CONTAINER_STATION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_CONTAINER_STATION").getDoubleValue();
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
	public String get_ID_CONTAINER_STATION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINER_STATION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_CONTAINER_STATION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINER_STATION_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_CONTAINER_STATION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINER_STATION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_CONTAINER_STATION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINER_STATION").getBigDecimalValue();
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
	
	
	public String get_ID_CONTAINER_ZYKLUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINER_ZYKLUS");
	}

	public String get_ID_CONTAINER_ZYKLUS_cF() throws myException
	{
		return this.get_FormatedValue("ID_CONTAINER_ZYKLUS");	
	}

	public String get_ID_CONTAINER_ZYKLUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_CONTAINER_ZYKLUS");
	}

	public String get_ID_CONTAINER_ZYKLUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINER_ZYKLUS",cNotNullValue);
	}

	public String get_ID_CONTAINER_ZYKLUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_CONTAINER_ZYKLUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER_ZYKLUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_CONTAINER_ZYKLUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_ZYKLUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINER_ZYKLUS", calNewValueFormated);
	}
		public Long get_ID_CONTAINER_ZYKLUS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_CONTAINER_ZYKLUS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_CONTAINER_ZYKLUS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_CONTAINER_ZYKLUS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_CONTAINER_ZYKLUS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_CONTAINER_ZYKLUS").getDoubleValue();
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
	public String get_ID_CONTAINER_ZYKLUS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINER_ZYKLUS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_CONTAINER_ZYKLUS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINER_ZYKLUS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_CONTAINER_ZYKLUS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINER_ZYKLUS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_CONTAINER_ZYKLUS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINER_ZYKLUS").getBigDecimalValue();
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
	
	
	public String get_ID_LAGERPLATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ");
	}

	public String get_ID_LAGERPLATZ_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ");	
	}

	public String get_ID_LAGERPLATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGERPLATZ");
	}

	public String get_ID_LAGERPLATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ",cNotNullValue);
	}

	public String get_ID_LAGERPLATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGERPLATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGERPLATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ", calNewValueFormated);
	}
		public Long get_ID_LAGERPLATZ_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGERPLATZ").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGERPLATZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGERPLATZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ").getDoubleValue();
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
	public String get_ID_LAGERPLATZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAGERPLATZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAGERPLATZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGERPLATZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ").getBigDecimalValue();
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
	
	
	public String get_ID_LAGERPLATZ_PLANUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ_PLANUNG");
	}

	public String get_ID_LAGERPLATZ_PLANUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ_PLANUNG");	
	}

	public String get_ID_LAGERPLATZ_PLANUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGERPLATZ_PLANUNG");
	}

	public String get_ID_LAGERPLATZ_PLANUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGERPLATZ_PLANUNG",cNotNullValue);
	}

	public String get_ID_LAGERPLATZ_PLANUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGERPLATZ_PLANUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_PLANUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_PLANUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_PLANUNG", calNewValueFormated);
	}
		public Long get_ID_LAGERPLATZ_PLANUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGERPLATZ_PLANUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGERPLATZ_PLANUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ_PLANUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGERPLATZ_PLANUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGERPLATZ_PLANUNG").getDoubleValue();
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
	public String get_ID_LAGERPLATZ_PLANUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_PLANUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAGERPLATZ_PLANUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGERPLATZ_PLANUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAGERPLATZ_PLANUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ_PLANUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGERPLATZ_PLANUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGERPLATZ_PLANUNG").getBigDecimalValue();
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
	
	
	public String get_ID_MASCHINEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN");
	}

	public String get_ID_MASCHINEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN");	
	}

	public String get_ID_MASCHINEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MASCHINEN");
	}

	public String get_ID_MASCHINEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN",cNotNullValue);
	}

	public String get_ID_MASCHINEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MASCHINEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MASCHINEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN", calNewValueFormated);
	}
		public Long get_ID_MASCHINEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MASCHINEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MASCHINEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MASCHINEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN").getDoubleValue();
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
	public String get_ID_MASCHINEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_MASCHINEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_MASCHINEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MASCHINEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN").getBigDecimalValue();
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
	
	
	public String get_ID_USER_AUFTRAGGEBER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUFTRAGGEBER");
	}

	public String get_ID_USER_AUFTRAGGEBER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_AUFTRAGGEBER");	
	}

	public String get_ID_USER_AUFTRAGGEBER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_AUFTRAGGEBER");
	}

	public String get_ID_USER_AUFTRAGGEBER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUFTRAGGEBER",cNotNullValue);
	}

	public String get_ID_USER_AUFTRAGGEBER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_AUFTRAGGEBER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUFTRAGGEBER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_AUFTRAGGEBER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGGEBER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGGEBER", calNewValueFormated);
	}
		public Long get_ID_USER_AUFTRAGGEBER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_AUFTRAGGEBER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_AUFTRAGGEBER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_AUFTRAGGEBER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_AUFTRAGGEBER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_AUFTRAGGEBER").getDoubleValue();
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
	public String get_ID_USER_AUFTRAGGEBER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUFTRAGGEBER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_AUFTRAGGEBER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUFTRAGGEBER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_AUFTRAGGEBER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUFTRAGGEBER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_AUFTRAGGEBER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUFTRAGGEBER").getBigDecimalValue();
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
	
	
	public String get_ID_USER_AUFTRAGNEHMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUFTRAGNEHMER");
	}

	public String get_ID_USER_AUFTRAGNEHMER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_AUFTRAGNEHMER");	
	}

	public String get_ID_USER_AUFTRAGNEHMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_AUFTRAGNEHMER");
	}

	public String get_ID_USER_AUFTRAGNEHMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUFTRAGNEHMER",cNotNullValue);
	}

	public String get_ID_USER_AUFTRAGNEHMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_AUFTRAGNEHMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUFTRAGNEHMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER", calNewValueFormated);
	}
		public Long get_ID_USER_AUFTRAGNEHMER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_AUFTRAGNEHMER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_AUFTRAGNEHMER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_AUFTRAGNEHMER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_AUFTRAGNEHMER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_AUFTRAGNEHMER").getDoubleValue();
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
	public String get_ID_USER_AUFTRAGNEHMER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUFTRAGNEHMER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_AUFTRAGNEHMER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUFTRAGNEHMER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_AUFTRAGNEHMER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUFTRAGNEHMER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_AUFTRAGNEHMER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUFTRAGNEHMER").getBigDecimalValue();
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
	
	
	public String get_ID_USER_AUFTRAGNEHMER_PLAN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUFTRAGNEHMER_PLAN");
	}

	public String get_ID_USER_AUFTRAGNEHMER_PLAN_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_AUFTRAGNEHMER_PLAN");	
	}

	public String get_ID_USER_AUFTRAGNEHMER_PLAN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_AUFTRAGNEHMER_PLAN");
	}

	public String get_ID_USER_AUFTRAGNEHMER_PLAN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUFTRAGNEHMER_PLAN",cNotNullValue);
	}

	public String get_ID_USER_AUFTRAGNEHMER_PLAN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_AUFTRAGNEHMER_PLAN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUFTRAGNEHMER_PLAN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUFTRAGNEHMER_PLAN", calNewValueFormated);
	}
		public Long get_ID_USER_AUFTRAGNEHMER_PLAN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_AUFTRAGNEHMER_PLAN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_AUFTRAGNEHMER_PLAN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_AUFTRAGNEHMER_PLAN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_AUFTRAGNEHMER_PLAN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_AUFTRAGNEHMER_PLAN").getDoubleValue();
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
	public String get_ID_USER_AUFTRAGNEHMER_PLAN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUFTRAGNEHMER_PLAN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_AUFTRAGNEHMER_PLAN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUFTRAGNEHMER_PLAN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_AUFTRAGNEHMER_PLAN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUFTRAGNEHMER_PLAN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_AUFTRAGNEHMER_PLAN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUFTRAGNEHMER_PLAN").getBigDecimalValue();
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
	
	
	public String get_ID_USER_BUCHUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BUCHUNG");
	}

	public String get_ID_USER_BUCHUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_BUCHUNG");	
	}

	public String get_ID_USER_BUCHUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_BUCHUNG");
	}

	public String get_ID_USER_BUCHUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BUCHUNG",cNotNullValue);
	}

	public String get_ID_USER_BUCHUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_BUCHUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_BUCHUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BUCHUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_BUCHUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BUCHUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BUCHUNG", calNewValueFormated);
	}
		public Long get_ID_USER_BUCHUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_BUCHUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_BUCHUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_BUCHUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_BUCHUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_BUCHUNG").getDoubleValue();
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
	public String get_ID_USER_BUCHUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BUCHUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_BUCHUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BUCHUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_BUCHUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BUCHUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_BUCHUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BUCHUNG").getBigDecimalValue();
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
	
	
	public String get_KFZKENNZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("KFZKENNZEICHEN");
	}

	public String get_KFZKENNZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("KFZKENNZEICHEN");	
	}

	public String get_KFZKENNZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KFZKENNZEICHEN");
	}

	public String get_KFZKENNZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KFZKENNZEICHEN",cNotNullValue);
	}

	public String get_KFZKENNZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KFZKENNZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KFZKENNZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KFZKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KFZKENNZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZKENNZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZKENNZEICHEN", calNewValueFormated);
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
	
	
	public String get_TAETIGKEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT");
	}

	public String get_TAETIGKEIT_cF() throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT");	
	}

	public String get_TAETIGKEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TAETIGKEIT");
	}

	public String get_TAETIGKEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT",cNotNullValue);
	}

	public String get_TAETIGKEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TAETIGKEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TAETIGKEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TAETIGKEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT", calNewValueFormated);
	}
		public String get_TAETIGKEIT_PLANUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT_PLANUNG");
	}

	public String get_TAETIGKEIT_PLANUNG_cF() throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT_PLANUNG");	
	}

	public String get_TAETIGKEIT_PLANUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TAETIGKEIT_PLANUNG");
	}

	public String get_TAETIGKEIT_PLANUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT_PLANUNG",cNotNullValue);
	}

	public String get_TAETIGKEIT_PLANUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT_PLANUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT_PLANUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TAETIGKEIT_PLANUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_PLANUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_PLANUNG", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("DATUM_PLANUNG_BIS",MyRECORD.DATATYPES.DATE);
	put("DATUM_PLANUNG_VON",MyRECORD.DATATYPES.DATE);
	put("DATUM_ZEIT_BUCHUNG",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_CONTAINER_STATION",MyRECORD.DATATYPES.NUMBER);
	put("ID_CONTAINER_ZYKLUS",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGERPLATZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGERPLATZ_PLANUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MASCHINEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_AUFTRAGGEBER",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_AUFTRAGNEHMER",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_AUFTRAGNEHMER_PLAN",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_BUCHUNG",MyRECORD.DATATYPES.NUMBER);
	put("KFZKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TAETIGKEIT",MyRECORD.DATATYPES.TEXT);
	put("TAETIGKEIT_PLANUNG",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_CONTAINER_STATION.HM_FIELDS;	
	}

}
