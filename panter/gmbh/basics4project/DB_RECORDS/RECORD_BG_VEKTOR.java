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

public class RECORD_BG_VEKTOR extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_BG_VEKTOR";
    public static String IDFIELD   = "ID_BG_VEKTOR";
    

	//erzeugen eines RECORDNEW_JT_BG_VEKTOR - felds
	private RECORDNEW_BG_VEKTOR   recNEW = null;

    private _TAB  tab = _TAB.bg_vektor;  



	public RECORD_BG_VEKTOR() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_BG_VEKTOR");
	}


	public RECORD_BG_VEKTOR(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BG_VEKTOR");
	}



	public RECORD_BG_VEKTOR(RECORD_BG_VEKTOR recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BG_VEKTOR");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_VEKTOR.TABLENAME);
	}


	//2014-04-03
	public RECORD_BG_VEKTOR(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BG_VEKTOR");
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_VEKTOR.TABLENAME);
	}




	public RECORD_BG_VEKTOR(long lID_Unformated) throws myException
	{
		super("JT_BG_VEKTOR","ID_BG_VEKTOR",""+lID_Unformated);
		this.set_TABLE_NAME("JT_BG_VEKTOR");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_VEKTOR.TABLENAME);
	}

	public RECORD_BG_VEKTOR(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_BG_VEKTOR");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_VEKTOR", "ID_BG_VEKTOR="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_VEKTOR", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_VEKTOR.TABLENAME);
	}
	
	

	public RECORD_BG_VEKTOR(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_BG_VEKTOR","ID_BG_VEKTOR",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_BG_VEKTOR");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_VEKTOR.TABLENAME);

	}


	public RECORD_BG_VEKTOR(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BG_VEKTOR");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_VEKTOR", "ID_BG_VEKTOR="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_VEKTOR", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_VEKTOR.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_BG_VEKTOR();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_BG_VEKTOR.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_BG_VEKTOR";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_BG_VEKTOR_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_BG_VEKTOR_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BG_VEKTOR was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BG_VEKTOR", bibE2.cTO(), "ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BG_VEKTOR was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BG_VEKTOR", bibE2.cTO(), "ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
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
			if (S.isFull(this.get_ID_BG_VEKTOR_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_VEKTOR", "ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_BG_VEKTOR get_RECORDNEW_BG_VEKTOR() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_BG_VEKTOR();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_BG_VEKTOR(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_BG_VEKTOR create_Instance() throws myException {
		return new RECORD_BG_VEKTOR();
	}
	
	public static RECORD_BG_VEKTOR create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_BG_VEKTOR(Conn);
    }

	public static RECORD_BG_VEKTOR create_Instance(RECORD_BG_VEKTOR recordOrig) {
		return new RECORD_BG_VEKTOR(recordOrig);
    }

	public static RECORD_BG_VEKTOR create_Instance(long lID_Unformated) throws myException {
		return new RECORD_BG_VEKTOR(lID_Unformated);
    }

	public static RECORD_BG_VEKTOR create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_BG_VEKTOR(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_BG_VEKTOR create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_BG_VEKTOR(lID_Unformated, Conn);
	}

	public static RECORD_BG_VEKTOR create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_BG_VEKTOR(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_BG_VEKTOR create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_BG_VEKTOR(recordOrig);	    
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
    public RECORD_BG_VEKTOR build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF());
      }
      //return new RECORD_BG_VEKTOR(this.get_cSQL_4_Build());
      RECORD_BG_VEKTOR rec = new RECORD_BG_VEKTOR();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_BG_VEKTOR
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_BG_VEKTOR-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_BG_VEKTOR get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_BG_VEKTOR_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_BG_VEKTOR  recNew = new RECORDNEW_BG_VEKTOR();
		
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
    public RECORD_BG_VEKTOR set_recordNew(RECORDNEW_BG_VEKTOR recnew) throws myException {
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
	
	



		private RECORD_LAND UP_RECORD_LAND_id_land_transit1 = null;




		private RECORD_LAND UP_RECORD_LAND_id_land_transit2 = null;




		private RECORD_LAND UP_RECORD_LAND_id_land_transit3 = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_fremdware = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_logi_start = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_logi_ziel = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_spedition = null;




		private RECORD_BG_ATOM UP_RECORD_BG_ATOM_id_bg_atom_quelle = null;




		private RECORD_BG_ATOM UP_RECORD_BG_ATOM_id_bg_atom_ziel = null;




		private RECORD_BG_DEL_INFO UP_RECORD_BG_DEL_INFO_id_bg_del_info = null;




		private RECORD_BG_PRUEFPROT UP_RECORD_BG_PRUEFPROT_id_bg_pruefprot_abschluss = null;




		private RECORD_BG_STORNO_INFO UP_RECORD_BG_STORNO_INFO_id_bg_storno_info = null;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_bg_vektor = null ;




		private RECLIST_BG_BEWEG_TO_VEKTOR DOWN_RECLIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor = null ;




		private RECLIST_BG_FAHRPLAN_TO_VEKTOR DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor = null ;




		private RECLIST_BG_STATION DOWN_RECLIST_BG_STATION_id_bg_vektor = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_base = null ;




		private RECORD_BG_VEKTOR UP_RECORD_BG_VEKTOR_id_bg_vektor_base = null;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_sub = null ;




		private RECORD_BG_VEKTOR UP_RECORD_BG_VEKTOR_id_bg_vektor_sub = null;




		private RECLIST_BG_VEKTOR_KONVERT DOWN_RECLIST_BG_VEKTOR_KONVERT_id_bg_vektor = null ;




		private RECLIST_BG_VEKTOR_KOSTEN DOWN_RECLIST_BG_VEKTOR_KOSTEN_id_bg_vektor = null ;




		private RECLIST_DLP_JOIN_WARENBEWG DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor = null ;




		private RECLIST_DLP_JOIN_WARENBEWG DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl = null ;




		private RECORD_HANDELSDEF UP_RECORD_HANDELSDEF_id_handelsdef = null;




		private RECORD_TRANSPORTMITTEL UP_RECORD_TRANSPORTMITTEL_id_transportmittel = null;




		private RECORD_UMA_KONTRAKT UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt = null;




		private RECORD_VERARBEITUNG UP_RECORD_VERARBEITUNG_id_verarbeitung = null;




		private RECORD_VERPACKUNGSART UP_RECORD_VERPACKUNGSART_id_verpackungsart = null;






	
	/**
	 * References the Field ID_LAND_TRANSIT1
	 * Falls keine this.get_ID_LAND_TRANSIT1_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land_transit1() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_TRANSIT1_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land_transit1==null)
		{
			this.UP_RECORD_LAND_id_land_transit1 = new RECORD_LAND(this.get_ID_LAND_TRANSIT1_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land_transit1;
	}
	





	
	/**
	 * References the Field ID_LAND_TRANSIT2
	 * Falls keine this.get_ID_LAND_TRANSIT2_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land_transit2() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_TRANSIT2_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land_transit2==null)
		{
			this.UP_RECORD_LAND_id_land_transit2 = new RECORD_LAND(this.get_ID_LAND_TRANSIT2_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land_transit2;
	}
	





	
	/**
	 * References the Field ID_LAND_TRANSIT3
	 * Falls keine this.get_ID_LAND_TRANSIT3_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land_transit3() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_TRANSIT3_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land_transit3==null)
		{
			this.UP_RECORD_LAND_id_land_transit3 = new RECORD_LAND(this.get_ID_LAND_TRANSIT3_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land_transit3;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_FREMDWARE
	 * Falls keine this.get_ID_ADRESSE_FREMDWARE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_fremdware() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_FREMDWARE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_fremdware==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_fremdware = new RECORD_ADRESSE(this.get_ID_ADRESSE_FREMDWARE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_fremdware;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_LOGI_START
	 * Falls keine this.get_ID_ADRESSE_LOGI_START_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_logi_start() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LOGI_START_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_logi_start==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_logi_start = new RECORD_ADRESSE(this.get_ID_ADRESSE_LOGI_START_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_logi_start;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_LOGI_ZIEL
	 * Falls keine this.get_ID_ADRESSE_LOGI_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_logi_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LOGI_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_logi_ziel==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_logi_ziel = new RECORD_ADRESSE(this.get_ID_ADRESSE_LOGI_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_logi_ziel;
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
	 * References the Field ID_BG_ATOM_QUELLE
	 * Falls keine this.get_ID_BG_ATOM_QUELLE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_ATOM get_UP_RECORD_BG_ATOM_id_bg_atom_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_ATOM_QUELLE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_ATOM_id_bg_atom_quelle==null)
		{
			this.UP_RECORD_BG_ATOM_id_bg_atom_quelle = new RECORD_BG_ATOM(this.get_ID_BG_ATOM_QUELLE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_ATOM_id_bg_atom_quelle;
	}
	





	
	/**
	 * References the Field ID_BG_ATOM_ZIEL
	 * Falls keine this.get_ID_BG_ATOM_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_ATOM get_UP_RECORD_BG_ATOM_id_bg_atom_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_ATOM_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_ATOM_id_bg_atom_ziel==null)
		{
			this.UP_RECORD_BG_ATOM_id_bg_atom_ziel = new RECORD_BG_ATOM(this.get_ID_BG_ATOM_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_ATOM_id_bg_atom_ziel;
	}
	





	
	/**
	 * References the Field ID_BG_DEL_INFO
	 * Falls keine this.get_ID_BG_DEL_INFO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_DEL_INFO get_UP_RECORD_BG_DEL_INFO_id_bg_del_info() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_DEL_INFO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_DEL_INFO_id_bg_del_info==null)
		{
			this.UP_RECORD_BG_DEL_INFO_id_bg_del_info = new RECORD_BG_DEL_INFO(this.get_ID_BG_DEL_INFO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_DEL_INFO_id_bg_del_info;
	}
	





	
	/**
	 * References the Field ID_BG_PRUEFPROT_ABSCHLUSS
	 * Falls keine this.get_ID_BG_PRUEFPROT_ABSCHLUSS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_PRUEFPROT get_UP_RECORD_BG_PRUEFPROT_id_bg_pruefprot_abschluss() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_PRUEFPROT_ABSCHLUSS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_PRUEFPROT_id_bg_pruefprot_abschluss==null)
		{
			this.UP_RECORD_BG_PRUEFPROT_id_bg_pruefprot_abschluss = new RECORD_BG_PRUEFPROT(this.get_ID_BG_PRUEFPROT_ABSCHLUSS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_PRUEFPROT_id_bg_pruefprot_abschluss;
	}
	





	
	/**
	 * References the Field ID_BG_STORNO_INFO
	 * Falls keine this.get_ID_BG_STORNO_INFO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_STORNO_INFO get_UP_RECORD_BG_STORNO_INFO_id_bg_storno_info() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STORNO_INFO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_STORNO_INFO_id_bg_storno_info==null)
		{
			this.UP_RECORD_BG_STORNO_INFO_id_bg_storno_info = new RECORD_BG_STORNO_INFO(this.get_ID_BG_STORNO_INFO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_STORNO_INFO_id_bg_storno_info;
	}
	





	/**
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_bg_vektor==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_bg_vektor = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_bg_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_bg_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_bg_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_bg_vektor = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_bg_vektor;
	}


	





	/**
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_BEWEG_TO_VEKTOR get_DOWN_RECORD_LIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor==null)
		{
			this.DOWN_RECLIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor = new RECLIST_BG_BEWEG_TO_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_BEWEG_TO_VEKTOR WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_BEWEG_TO_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_BEWEG_TO_VEKTOR get_DOWN_RECORD_LIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_BEWEG_TO_VEKTOR WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor = new RECLIST_BG_BEWEG_TO_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_BEWEG_TO_VEKTOR_id_bg_vektor;
	}


	





	/**
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_FAHRPLAN_TO_VEKTOR get_DOWN_RECORD_LIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor==null)
		{
			this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor = new RECLIST_BG_FAHRPLAN_TO_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_FAHRPLAN_TO_VEKTOR WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_FAHRPLAN_TO_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_FAHRPLAN_TO_VEKTOR get_DOWN_RECORD_LIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_FAHRPLAN_TO_VEKTOR WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor = new RECLIST_BG_FAHRPLAN_TO_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_FAHRPLAN_TO_VEKTOR_id_bg_vektor;
	}


	





	/**
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_bg_vektor==null)
		{
			this.DOWN_RECLIST_BG_STATION_id_bg_vektor = new RECLIST_BG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_bg_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_bg_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_bg_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_STATION_id_bg_vektor = new RECLIST_BG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_bg_vektor;
	}


	





	/**
	 * References the Field ID_BG_VEKTOR_BASE 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_bg_vektor_base() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_base==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_base = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR_BASE="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_base;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR_BASE 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_bg_vektor_base(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_base==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR_BASE="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_base = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_base;
	}


	





	
	/**
	 * References the Field ID_BG_VEKTOR_BASE
	 * Falls keine this.get_ID_BG_VEKTOR_BASE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_VEKTOR get_UP_RECORD_BG_VEKTOR_id_bg_vektor_base() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_BASE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_VEKTOR_id_bg_vektor_base==null)
		{
			this.UP_RECORD_BG_VEKTOR_id_bg_vektor_base = new RECORD_BG_VEKTOR(this.get_ID_BG_VEKTOR_BASE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_VEKTOR_id_bg_vektor_base;
	}
	





	/**
	 * References the Field ID_BG_VEKTOR_SUB 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_bg_vektor_sub() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_sub==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_sub = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR_SUB="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_sub;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR_SUB 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_bg_vektor_sub(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_sub==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_BG_VEKTOR_SUB="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_sub = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_bg_vektor_sub;
	}


	





	
	/**
	 * References the Field ID_BG_VEKTOR_SUB
	 * Falls keine this.get_ID_BG_VEKTOR_SUB_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_VEKTOR get_UP_RECORD_BG_VEKTOR_id_bg_vektor_sub() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_SUB_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_VEKTOR_id_bg_vektor_sub==null)
		{
			this.UP_RECORD_BG_VEKTOR_id_bg_vektor_sub = new RECORD_BG_VEKTOR(this.get_ID_BG_VEKTOR_SUB_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_VEKTOR_id_bg_vektor_sub;
	}
	





	/**
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR_KONVERT get_DOWN_RECORD_LIST_BG_VEKTOR_KONVERT_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_KONVERT_id_bg_vektor==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_KONVERT_id_bg_vektor = new RECLIST_BG_VEKTOR_KONVERT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR_KONVERT WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_VEKTOR_KONVERT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_KONVERT_id_bg_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR_KONVERT get_DOWN_RECORD_LIST_BG_VEKTOR_KONVERT_id_bg_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_KONVERT_id_bg_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR_KONVERT WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_KONVERT_id_bg_vektor = new RECLIST_BG_VEKTOR_KONVERT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_KONVERT_id_bg_vektor;
	}


	





	/**
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR_KOSTEN get_DOWN_RECORD_LIST_BG_VEKTOR_KOSTEN_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_KOSTEN_id_bg_vektor==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_KOSTEN_id_bg_vektor = new RECLIST_BG_VEKTOR_KOSTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR_KOSTEN WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_BG_VEKTOR_KOSTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_KOSTEN_id_bg_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR_KOSTEN get_DOWN_RECORD_LIST_BG_VEKTOR_KOSTEN_id_bg_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_KOSTEN_id_bg_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR_KOSTEN WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_KOSTEN_id_bg_vektor = new RECLIST_BG_VEKTOR_KOSTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_KOSTEN_id_bg_vektor;
	}


	





	/**
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor==null)
		{
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor = new RECLIST_DLP_JOIN_WARENBEWG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_DLP_JOIN_WARENBEWG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_bg_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_BG_VEKTOR="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor = new RECLIST_DLP_JOIN_WARENBEWG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor;
	}


	





	/**
	 * References the Field ID_BG_VEKTOR_DL 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl==null)
		{
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl = new RECLIST_DLP_JOIN_WARENBEWG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_BG_VEKTOR_DL="+this.get_ID_BG_VEKTOR_cUF()+" ORDER BY ID_DLP_JOIN_WARENBEWG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_VEKTOR_DL 
	 * Falls keine get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_JOIN_WARENBEWG get_DOWN_RECORD_LIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_JOIN_WARENBEWG WHERE ID_BG_VEKTOR_DL="+this.get_ID_BG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl = new RECLIST_DLP_JOIN_WARENBEWG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_JOIN_WARENBEWG_id_bg_vektor_dl;
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
	 * References the Field ID_TRANSPORTMITTEL
	 * Falls keine this.get_ID_TRANSPORTMITTEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TRANSPORTMITTEL get_UP_RECORD_TRANSPORTMITTEL_id_transportmittel() throws myException
	{
		if (S.isEmpty(this.get_ID_TRANSPORTMITTEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TRANSPORTMITTEL_id_transportmittel==null)
		{
			this.UP_RECORD_TRANSPORTMITTEL_id_transportmittel = new RECORD_TRANSPORTMITTEL(this.get_ID_TRANSPORTMITTEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TRANSPORTMITTEL_id_transportmittel;
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
	

	public static String FIELD__AH7_AUSSTELLUNG_DATUM = "AH7_AUSSTELLUNG_DATUM";
	public static String FIELD__AH7_MENGE = "AH7_MENGE";
	public static String FIELD__AH7_VOLUMEN = "AH7_VOLUMEN";
	public static String FIELD__ANHAENGERKENNZEICHEN = "ANHAENGERKENNZEICHEN";
	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__BEMERKUNG_FUER_KUNDE = "BEMERKUNG_FUER_KUNDE";
	public static String FIELD__BEMERKUNG_SACHBEARBEITER = "BEMERKUNG_SACHBEARBEITER";
	public static String FIELD__BUCHUNGSNUMMER = "BUCHUNGSNUMMER";
	public static String FIELD__DATUM_PLANUNG_BIS = "DATUM_PLANUNG_BIS";
	public static String FIELD__DATUM_PLANUNG_VON = "DATUM_PLANUNG_VON";
	public static String FIELD__EK_VK_SORTE_LOCK = "EK_VK_SORTE_LOCK";
	public static String FIELD__EK_VK_ZUORD_ZWANG = "EK_VK_ZUORD_ZWANG";
	public static String FIELD__EN_TRANSPORT_TYP = "EN_TRANSPORT_TYP";
	public static String FIELD__EN_VEKTOR_QUELLE = "EN_VEKTOR_QUELLE";
	public static String FIELD__EN_VEKTOR_STATUS = "EN_VEKTOR_STATUS";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ADRESSE_FREMDWARE = "ID_ADRESSE_FREMDWARE";
	public static String FIELD__ID_ADRESSE_LOGI_START = "ID_ADRESSE_LOGI_START";
	public static String FIELD__ID_ADRESSE_LOGI_ZIEL = "ID_ADRESSE_LOGI_ZIEL";
	public static String FIELD__ID_ADRESSE_SPEDITION = "ID_ADRESSE_SPEDITION";
	public static String FIELD__ID_BG_ATOM_QUELLE = "ID_BG_ATOM_QUELLE";
	public static String FIELD__ID_BG_ATOM_ZIEL = "ID_BG_ATOM_ZIEL";
	public static String FIELD__ID_BG_DEL_INFO = "ID_BG_DEL_INFO";
	public static String FIELD__ID_BG_PRUEFPROT_ABSCHLUSS = "ID_BG_PRUEFPROT_ABSCHLUSS";
	public static String FIELD__ID_BG_STORNO_INFO = "ID_BG_STORNO_INFO";
	public static String FIELD__ID_BG_VEKTOR = "ID_BG_VEKTOR";
	public static String FIELD__ID_BG_VEKTOR_BASE = "ID_BG_VEKTOR_BASE";
	public static String FIELD__ID_BG_VEKTOR_SUB = "ID_BG_VEKTOR_SUB";
	public static String FIELD__ID_HANDELSDEF = "ID_HANDELSDEF";
	public static String FIELD__ID_LAND_TRANSIT1 = "ID_LAND_TRANSIT1";
	public static String FIELD__ID_LAND_TRANSIT2 = "ID_LAND_TRANSIT2";
	public static String FIELD__ID_LAND_TRANSIT3 = "ID_LAND_TRANSIT3";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TRANSPORTMITTEL = "ID_TRANSPORTMITTEL";
	public static String FIELD__ID_UMA_KONTRAKT = "ID_UMA_KONTRAKT";
	public static String FIELD__ID_VERARBEITUNG = "ID_VERARBEITUNG";
	public static String FIELD__ID_VERPACKUNGSART = "ID_VERPACKUNGSART";
	public static String FIELD__KOSTEN_TRANSPORT = "KOSTEN_TRANSPORT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERINFO_TPA = "LIEFERINFO_TPA";
	public static String FIELD__ORDNUNGSNUMMER_CMR = "ORDNUNGSNUMMER_CMR";
	public static String FIELD__PLANMENGE = "PLANMENGE";
	public static String FIELD__POSNR = "POSNR";
	public static String FIELD__PRINT_ANHANG7 = "PRINT_ANHANG7";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TIMESTAMP_IN_MASK = "TIMESTAMP_IN_MASK";
	public static String FIELD__TRANSPORTKENNZEICHEN = "TRANSPORTKENNZEICHEN";
	public static String FIELD__TRANSPORTMITTEL = "TRANSPORTMITTEL";
	public static String FIELD__TRANSPORTVERANTWORTUNG = "TRANSPORTVERANTWORTUNG";


	public String get_AH7_AUSSTELLUNG_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("AH7_AUSSTELLUNG_DATUM");
	}

	public String get_AH7_AUSSTELLUNG_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("AH7_AUSSTELLUNG_DATUM");	
	}

	public String get_AH7_AUSSTELLUNG_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AH7_AUSSTELLUNG_DATUM");
	}

	public String get_AH7_AUSSTELLUNG_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AH7_AUSSTELLUNG_DATUM",cNotNullValue);
	}

	public String get_AH7_AUSSTELLUNG_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AH7_AUSSTELLUNG_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AH7_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_AUSSTELLUNG_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_AUSSTELLUNG_DATUM", calNewValueFormated);
	}
		public String get_AH7_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("AH7_MENGE");
	}

	public String get_AH7_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("AH7_MENGE");	
	}

	public String get_AH7_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AH7_MENGE");
	}

	public String get_AH7_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AH7_MENGE",cNotNullValue);
	}

	public String get_AH7_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AH7_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AH7_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AH7_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AH7_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AH7_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_MENGE", calNewValueFormated);
	}
		public Double get_AH7_MENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("AH7_MENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_AH7_MENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("AH7_MENGE").getDoubleValue();
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
	public String get_AH7_MENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AH7_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_AH7_MENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AH7_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_AH7_MENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("AH7_MENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_AH7_MENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("AH7_MENGE").getBigDecimalValue();
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
	
	
	public String get_AH7_VOLUMEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("AH7_VOLUMEN");
	}

	public String get_AH7_VOLUMEN_cF() throws myException
	{
		return this.get_FormatedValue("AH7_VOLUMEN");	
	}

	public String get_AH7_VOLUMEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AH7_VOLUMEN");
	}

	public String get_AH7_VOLUMEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AH7_VOLUMEN",cNotNullValue);
	}

	public String get_AH7_VOLUMEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AH7_VOLUMEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AH7_VOLUMEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AH7_VOLUMEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AH7_VOLUMEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AH7_VOLUMEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AH7_VOLUMEN", calNewValueFormated);
	}
		public Double get_AH7_VOLUMEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("AH7_VOLUMEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_AH7_VOLUMEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("AH7_VOLUMEN").getDoubleValue();
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
	public String get_AH7_VOLUMEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AH7_VOLUMEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_AH7_VOLUMEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_AH7_VOLUMEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_AH7_VOLUMEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("AH7_VOLUMEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_AH7_VOLUMEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("AH7_VOLUMEN").getBigDecimalValue();
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
		public String get_BUCHUNGSNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNUMMER");
	}

	public String get_BUCHUNGSNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNUMMER");	
	}

	public String get_BUCHUNGSNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSNUMMER");
	}

	public String get_BUCHUNGSNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNUMMER",cNotNullValue);
	}

	public String get_BUCHUNGSNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNUMMER", calNewValueFormated);
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
		public String get_EN_TRANSPORT_TYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("EN_TRANSPORT_TYP");
	}

	public String get_EN_TRANSPORT_TYP_cF() throws myException
	{
		return this.get_FormatedValue("EN_TRANSPORT_TYP");	
	}

	public String get_EN_TRANSPORT_TYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EN_TRANSPORT_TYP");
	}

	public String get_EN_TRANSPORT_TYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EN_TRANSPORT_TYP",cNotNullValue);
	}

	public String get_EN_TRANSPORT_TYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EN_TRANSPORT_TYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EN_TRANSPORT_TYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EN_TRANSPORT_TYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EN_TRANSPORT_TYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_TRANSPORT_TYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_TRANSPORT_TYP", calNewValueFormated);
	}
		public String get_EN_VEKTOR_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EN_VEKTOR_QUELLE");
	}

	public String get_EN_VEKTOR_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("EN_VEKTOR_QUELLE");	
	}

	public String get_EN_VEKTOR_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EN_VEKTOR_QUELLE");
	}

	public String get_EN_VEKTOR_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EN_VEKTOR_QUELLE",cNotNullValue);
	}

	public String get_EN_VEKTOR_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EN_VEKTOR_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EN_VEKTOR_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EN_VEKTOR_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EN_VEKTOR_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_VEKTOR_QUELLE", calNewValueFormated);
	}
		public String get_EN_VEKTOR_STATUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("EN_VEKTOR_STATUS");
	}

	public String get_EN_VEKTOR_STATUS_cF() throws myException
	{
		return this.get_FormatedValue("EN_VEKTOR_STATUS");	
	}

	public String get_EN_VEKTOR_STATUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EN_VEKTOR_STATUS");
	}

	public String get_EN_VEKTOR_STATUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EN_VEKTOR_STATUS",cNotNullValue);
	}

	public String get_EN_VEKTOR_STATUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EN_VEKTOR_STATUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EN_VEKTOR_STATUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EN_VEKTOR_STATUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EN_VEKTOR_STATUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EN_VEKTOR_STATUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EN_VEKTOR_STATUS", calNewValueFormated);
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
		public String get_ID_ADRESSE_FREMDWARE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_FREMDWARE");
	}

	public String get_ID_ADRESSE_FREMDWARE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_FREMDWARE");	
	}

	public String get_ID_ADRESSE_FREMDWARE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_FREMDWARE");
	}

	public String get_ID_ADRESSE_FREMDWARE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_FREMDWARE",cNotNullValue);
	}

	public String get_ID_ADRESSE_FREMDWARE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_FREMDWARE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_FREMDWARE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_FREMDWARE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_FREMDWARE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_FREMDWARE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_FREMDWARE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_FREMDWARE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_FREMDWARE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_FREMDWARE").getDoubleValue();
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
	public String get_ID_ADRESSE_FREMDWARE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_FREMDWARE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_FREMDWARE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_FREMDWARE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_FREMDWARE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_FREMDWARE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_FREMDWARE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_FREMDWARE").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LOGI_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LOGI_START");
	}

	public String get_ID_ADRESSE_LOGI_START_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LOGI_START");	
	}

	public String get_ID_ADRESSE_LOGI_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LOGI_START");
	}

	public String get_ID_ADRESSE_LOGI_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LOGI_START",cNotNullValue);
	}

	public String get_ID_ADRESSE_LOGI_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LOGI_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LOGI_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LOGI_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_START", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LOGI_START_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LOGI_START").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LOGI_START_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LOGI_START").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LOGI_START_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LOGI_START").getDoubleValue();
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
	public String get_ID_ADRESSE_LOGI_START_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LOGI_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_LOGI_START_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LOGI_START_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_LOGI_START_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LOGI_START").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LOGI_START_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LOGI_START").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LOGI_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LOGI_ZIEL");
	}

	public String get_ID_ADRESSE_LOGI_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LOGI_ZIEL");	
	}

	public String get_ID_ADRESSE_LOGI_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LOGI_ZIEL");
	}

	public String get_ID_ADRESSE_LOGI_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LOGI_ZIEL",cNotNullValue);
	}

	public String get_ID_ADRESSE_LOGI_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LOGI_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LOGI_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LOGI_ZIEL", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LOGI_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LOGI_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LOGI_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LOGI_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LOGI_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LOGI_ZIEL").getDoubleValue();
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
	public String get_ID_ADRESSE_LOGI_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LOGI_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ADRESSE_LOGI_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LOGI_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ADRESSE_LOGI_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LOGI_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LOGI_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LOGI_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_BG_ATOM_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_ATOM_QUELLE");
	}

	public String get_ID_BG_ATOM_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_ATOM_QUELLE");	
	}

	public String get_ID_BG_ATOM_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_ATOM_QUELLE");
	}

	public String get_ID_BG_ATOM_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_ATOM_QUELLE",cNotNullValue);
	}

	public String get_ID_BG_ATOM_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_ATOM_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_ATOM_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_ATOM_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_ATOM_QUELLE", calNewValueFormated);
	}
		public Long get_ID_BG_ATOM_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_ATOM_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_ATOM_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_ATOM_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_ATOM_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_ATOM_QUELLE").getDoubleValue();
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
	public String get_ID_BG_ATOM_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_ATOM_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_ATOM_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_ATOM_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_ATOM_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_ATOM_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_ATOM_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_ATOM_QUELLE").getBigDecimalValue();
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
	
	
	public String get_ID_BG_ATOM_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_ATOM_ZIEL");
	}

	public String get_ID_BG_ATOM_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_ATOM_ZIEL");	
	}

	public String get_ID_BG_ATOM_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_ATOM_ZIEL");
	}

	public String get_ID_BG_ATOM_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_ATOM_ZIEL",cNotNullValue);
	}

	public String get_ID_BG_ATOM_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_ATOM_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_ATOM_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_ATOM_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_ATOM_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_ATOM_ZIEL", calNewValueFormated);
	}
		public Long get_ID_BG_ATOM_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_ATOM_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_ATOM_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_ATOM_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_ATOM_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_ATOM_ZIEL").getDoubleValue();
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
	public String get_ID_BG_ATOM_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_ATOM_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_ATOM_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_ATOM_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_ATOM_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_ATOM_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_ATOM_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_ATOM_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_BG_DEL_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_DEL_INFO");
	}

	public String get_ID_BG_DEL_INFO_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_DEL_INFO");	
	}

	public String get_ID_BG_DEL_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_DEL_INFO");
	}

	public String get_ID_BG_DEL_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_DEL_INFO",cNotNullValue);
	}

	public String get_ID_BG_DEL_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_DEL_INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", calNewValueFormated);
	}
		public Long get_ID_BG_DEL_INFO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_DEL_INFO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_DEL_INFO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_DEL_INFO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_DEL_INFO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_DEL_INFO").getDoubleValue();
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
	public String get_ID_BG_DEL_INFO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_DEL_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_DEL_INFO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_DEL_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_DEL_INFO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_DEL_INFO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_DEL_INFO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_DEL_INFO").getBigDecimalValue();
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
	
	
	public String get_ID_BG_PRUEFPROT_ABSCHLUSS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_PRUEFPROT_ABSCHLUSS");
	}

	public String get_ID_BG_PRUEFPROT_ABSCHLUSS_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_PRUEFPROT_ABSCHLUSS");	
	}

	public String get_ID_BG_PRUEFPROT_ABSCHLUSS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_PRUEFPROT_ABSCHLUSS");
	}

	public String get_ID_BG_PRUEFPROT_ABSCHLUSS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_PRUEFPROT_ABSCHLUSS",cNotNullValue);
	}

	public String get_ID_BG_PRUEFPROT_ABSCHLUSS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_PRUEFPROT_ABSCHLUSS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_PRUEFPROT_ABSCHLUSS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_PRUEFPROT_ABSCHLUSS", calNewValueFormated);
	}
		public Long get_ID_BG_PRUEFPROT_ABSCHLUSS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_PRUEFPROT_ABSCHLUSS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_PRUEFPROT_ABSCHLUSS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_PRUEFPROT_ABSCHLUSS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_PRUEFPROT_ABSCHLUSS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_PRUEFPROT_ABSCHLUSS").getDoubleValue();
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
	public String get_ID_BG_PRUEFPROT_ABSCHLUSS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_PRUEFPROT_ABSCHLUSS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_PRUEFPROT_ABSCHLUSS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_PRUEFPROT_ABSCHLUSS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_PRUEFPROT_ABSCHLUSS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_PRUEFPROT_ABSCHLUSS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_PRUEFPROT_ABSCHLUSS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_PRUEFPROT_ABSCHLUSS").getBigDecimalValue();
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
	
	
	public String get_ID_BG_STORNO_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_STORNO_INFO");
	}

	public String get_ID_BG_STORNO_INFO_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_STORNO_INFO");	
	}

	public String get_ID_BG_STORNO_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_STORNO_INFO");
	}

	public String get_ID_BG_STORNO_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_STORNO_INFO",cNotNullValue);
	}

	public String get_ID_BG_STORNO_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_STORNO_INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", calNewValueFormated);
	}
		public Long get_ID_BG_STORNO_INFO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_STORNO_INFO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_STORNO_INFO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_STORNO_INFO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_STORNO_INFO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_STORNO_INFO").getDoubleValue();
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
	public String get_ID_BG_STORNO_INFO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_STORNO_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_STORNO_INFO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_STORNO_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_STORNO_INFO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_STORNO_INFO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_STORNO_INFO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_STORNO_INFO").getBigDecimalValue();
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
	
	
	public String get_ID_BG_VEKTOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR");
	}

	public String get_ID_BG_VEKTOR_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR");	
	}

	public String get_ID_BG_VEKTOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_VEKTOR");
	}

	public String get_ID_BG_VEKTOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR",cNotNullValue);
	}

	public String get_ID_BG_VEKTOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", calNewValueFormated);
	}
		public Long get_ID_BG_VEKTOR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_VEKTOR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_VEKTOR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_VEKTOR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR").getDoubleValue();
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
	public String get_ID_BG_VEKTOR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_VEKTOR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_VEKTOR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_VEKTOR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR").getBigDecimalValue();
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
	
	
	public String get_ID_BG_VEKTOR_BASE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR_BASE");
	}

	public String get_ID_BG_VEKTOR_BASE_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR_BASE");	
	}

	public String get_ID_BG_VEKTOR_BASE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_VEKTOR_BASE");
	}

	public String get_ID_BG_VEKTOR_BASE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR_BASE",cNotNullValue);
	}

	public String get_ID_BG_VEKTOR_BASE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR_BASE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR_BASE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_VEKTOR_BASE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_BASE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_BASE", calNewValueFormated);
	}
		public Long get_ID_BG_VEKTOR_BASE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_VEKTOR_BASE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_VEKTOR_BASE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR_BASE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_VEKTOR_BASE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR_BASE").getDoubleValue();
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
	public String get_ID_BG_VEKTOR_BASE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_BASE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_VEKTOR_BASE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_BASE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_VEKTOR_BASE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR_BASE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_VEKTOR_BASE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR_BASE").getBigDecimalValue();
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
	
	
	public String get_ID_BG_VEKTOR_SUB_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR_SUB");
	}

	public String get_ID_BG_VEKTOR_SUB_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR_SUB");	
	}

	public String get_ID_BG_VEKTOR_SUB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_VEKTOR_SUB");
	}

	public String get_ID_BG_VEKTOR_SUB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR_SUB",cNotNullValue);
	}

	public String get_ID_BG_VEKTOR_SUB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR_SUB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR_SUB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_VEKTOR_SUB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_SUB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR_SUB", calNewValueFormated);
	}
		public Long get_ID_BG_VEKTOR_SUB_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_VEKTOR_SUB").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_VEKTOR_SUB_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR_SUB").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_VEKTOR_SUB_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR_SUB").getDoubleValue();
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
	public String get_ID_BG_VEKTOR_SUB_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_SUB_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_BG_VEKTOR_SUB_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_SUB_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_BG_VEKTOR_SUB_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR_SUB").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_VEKTOR_SUB_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR_SUB").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_TRANSIT1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_TRANSIT1");
	}

	public String get_ID_LAND_TRANSIT1_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND_TRANSIT1");	
	}

	public String get_ID_LAND_TRANSIT1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND_TRANSIT1");
	}

	public String get_ID_LAND_TRANSIT1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_TRANSIT1",cNotNullValue);
	}

	public String get_ID_LAND_TRANSIT1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND_TRANSIT1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND_TRANSIT1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_TRANSIT1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND_TRANSIT1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT1", calNewValueFormated);
	}
		public Long get_ID_LAND_TRANSIT1_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND_TRANSIT1").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_TRANSIT1_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND_TRANSIT1").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_TRANSIT1_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND_TRANSIT1").getDoubleValue();
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
	public String get_ID_LAND_TRANSIT1_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_TRANSIT1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAND_TRANSIT1_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_TRANSIT1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAND_TRANSIT1_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_TRANSIT1").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_TRANSIT1_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_TRANSIT1").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_TRANSIT2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_TRANSIT2");
	}

	public String get_ID_LAND_TRANSIT2_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND_TRANSIT2");	
	}

	public String get_ID_LAND_TRANSIT2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND_TRANSIT2");
	}

	public String get_ID_LAND_TRANSIT2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_TRANSIT2",cNotNullValue);
	}

	public String get_ID_LAND_TRANSIT2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND_TRANSIT2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND_TRANSIT2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_TRANSIT2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND_TRANSIT2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT2", calNewValueFormated);
	}
		public Long get_ID_LAND_TRANSIT2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND_TRANSIT2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_TRANSIT2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND_TRANSIT2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_TRANSIT2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND_TRANSIT2").getDoubleValue();
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
	public String get_ID_LAND_TRANSIT2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_TRANSIT2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAND_TRANSIT2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_TRANSIT2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAND_TRANSIT2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_TRANSIT2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_TRANSIT2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_TRANSIT2").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_TRANSIT3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_TRANSIT3");
	}

	public String get_ID_LAND_TRANSIT3_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND_TRANSIT3");	
	}

	public String get_ID_LAND_TRANSIT3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND_TRANSIT3");
	}

	public String get_ID_LAND_TRANSIT3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_TRANSIT3",cNotNullValue);
	}

	public String get_ID_LAND_TRANSIT3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND_TRANSIT3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND_TRANSIT3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_TRANSIT3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND_TRANSIT3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_TRANSIT3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_TRANSIT3", calNewValueFormated);
	}
		public Long get_ID_LAND_TRANSIT3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND_TRANSIT3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_TRANSIT3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND_TRANSIT3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_TRANSIT3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND_TRANSIT3").getDoubleValue();
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
	public String get_ID_LAND_TRANSIT3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_TRANSIT3_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAND_TRANSIT3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_TRANSIT3_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAND_TRANSIT3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_TRANSIT3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_TRANSIT3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_TRANSIT3").getBigDecimalValue();
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
	
	
	public String get_ID_TRANSPORTMITTEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TRANSPORTMITTEL");
	}

	public String get_ID_TRANSPORTMITTEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_TRANSPORTMITTEL");	
	}

	public String get_ID_TRANSPORTMITTEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TRANSPORTMITTEL");
	}

	public String get_ID_TRANSPORTMITTEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TRANSPORTMITTEL",cNotNullValue);
	}

	public String get_ID_TRANSPORTMITTEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TRANSPORTMITTEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TRANSPORTMITTEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TRANSPORTMITTEL", calNewValueFormated);
	}
		public Long get_ID_TRANSPORTMITTEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TRANSPORTMITTEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TRANSPORTMITTEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TRANSPORTMITTEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TRANSPORTMITTEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TRANSPORTMITTEL").getDoubleValue();
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
	public String get_ID_TRANSPORTMITTEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TRANSPORTMITTEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_TRANSPORTMITTEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TRANSPORTMITTEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_TRANSPORTMITTEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TRANSPORTMITTEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TRANSPORTMITTEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TRANSPORTMITTEL").getBigDecimalValue();
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
	
	
	public String get_KOSTEN_TRANSPORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_TRANSPORT");
	}

	public String get_KOSTEN_TRANSPORT_cF() throws myException
	{
		return this.get_FormatedValue("KOSTEN_TRANSPORT");	
	}

	public String get_KOSTEN_TRANSPORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTEN_TRANSPORT");
	}

	public String get_KOSTEN_TRANSPORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_TRANSPORT",cNotNullValue);
	}

	public String get_KOSTEN_TRANSPORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTEN_TRANSPORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTEN_TRANSPORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_TRANSPORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTEN_TRANSPORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT", calNewValueFormated);
	}
		public Double get_KOSTEN_TRANSPORT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KOSTEN_TRANSPORT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KOSTEN_TRANSPORT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KOSTEN_TRANSPORT").getDoubleValue();
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
	public String get_KOSTEN_TRANSPORT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_TRANSPORT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_KOSTEN_TRANSPORT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_TRANSPORT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_KOSTEN_TRANSPORT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_TRANSPORT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KOSTEN_TRANSPORT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_TRANSPORT").getBigDecimalValue();
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
		public String get_PLANMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PLANMENGE");
	}

	public String get_PLANMENGE_cF() throws myException
	{
		return this.get_FormatedValue("PLANMENGE");	
	}

	public String get_PLANMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PLANMENGE");
	}

	public String get_PLANMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PLANMENGE",cNotNullValue);
	}

	public String get_PLANMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PLANMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PLANMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PLANMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PLANMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PLANMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLANMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLANMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLANMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLANMENGE", calNewValueFormated);
	}
		public Double get_PLANMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("PLANMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_PLANMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("PLANMENGE").getDoubleValue();
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
	public String get_PLANMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PLANMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_PLANMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PLANMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_PLANMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("PLANMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_PLANMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("PLANMENGE").getBigDecimalValue();
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
	
	
	public String get_POSNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSNR");
	}

	public String get_POSNR_cF() throws myException
	{
		return this.get_FormatedValue("POSNR");	
	}

	public String get_POSNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSNR");
	}

	public String get_POSNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSNR",cNotNullValue);
	}

	public String get_POSNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSNR", calNewValueFormated);
	}
		public Long get_POSNR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("POSNR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_POSNR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("POSNR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_POSNR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("POSNR").getDoubleValue();
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
	public String get_POSNR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSNR_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_POSNR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSNR_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_POSNR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("POSNR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_POSNR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("POSNR").getBigDecimalValue();
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
	
	
	public String get_PRINT_ANHANG7_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRINT_ANHANG7");
	}

	public String get_PRINT_ANHANG7_cF() throws myException
	{
		return this.get_FormatedValue("PRINT_ANHANG7");	
	}

	public String get_PRINT_ANHANG7_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRINT_ANHANG7");
	}

	public String get_PRINT_ANHANG7_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRINT_ANHANG7",cNotNullValue);
	}

	public String get_PRINT_ANHANG7_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRINT_ANHANG7",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRINT_ANHANG7", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRINT_ANHANG7(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRINT_ANHANG7", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_ANHANG7_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_ANHANG7", calNewValueFormated);
	}
		public boolean is_PRINT_ANHANG7_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_ANHANG7_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRINT_ANHANG7_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_ANHANG7_cUF_NN("N").equals("N"))
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
	
	
	public String get_TIMESTAMP_IN_MASK_cUF() throws myException
	{
		return this.get_UnFormatedValue("TIMESTAMP_IN_MASK");
	}

	public String get_TIMESTAMP_IN_MASK_cF() throws myException
	{
		return this.get_FormatedValue("TIMESTAMP_IN_MASK");	
	}

	public String get_TIMESTAMP_IN_MASK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TIMESTAMP_IN_MASK");
	}

	public String get_TIMESTAMP_IN_MASK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TIMESTAMP_IN_MASK",cNotNullValue);
	}

	public String get_TIMESTAMP_IN_MASK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TIMESTAMP_IN_MASK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", calNewValueFormated);
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
		public String get_TRANSPORTVERANTWORTUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTVERANTWORTUNG");
	}

	public String get_TRANSPORTVERANTWORTUNG_cF() throws myException
	{
		return this.get_FormatedValue("TRANSPORTVERANTWORTUNG");	
	}

	public String get_TRANSPORTVERANTWORTUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSPORTVERANTWORTUNG");
	}

	public String get_TRANSPORTVERANTWORTUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTVERANTWORTUNG",cNotNullValue);
	}

	public String get_TRANSPORTVERANTWORTUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSPORTVERANTWORTUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTVERANTWORTUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSPORTVERANTWORTUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTVERANTWORTUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTVERANTWORTUNG", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AH7_AUSSTELLUNG_DATUM",MyRECORD.DATATYPES.DATE);
	put("AH7_MENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("AH7_VOLUMEN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANHAENGERKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_FUER_KUNDE",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_SACHBEARBEITER",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("DATUM_PLANUNG_BIS",MyRECORD.DATATYPES.DATE);
	put("DATUM_PLANUNG_VON",MyRECORD.DATATYPES.DATE);
	put("EK_VK_SORTE_LOCK",MyRECORD.DATATYPES.YESNO);
	put("EK_VK_ZUORD_ZWANG",MyRECORD.DATATYPES.YESNO);
	put("EN_TRANSPORT_TYP",MyRECORD.DATATYPES.TEXT);
	put("EN_VEKTOR_QUELLE",MyRECORD.DATATYPES.TEXT);
	put("EN_VEKTOR_STATUS",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE_FREMDWARE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LOGI_START",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LOGI_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_SPEDITION",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_ATOM_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_ATOM_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_DEL_INFO",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_PRUEFPROT_ABSCHLUSS",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_STORNO_INFO",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_VEKTOR",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_VEKTOR_BASE",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_VEKTOR_SUB",MyRECORD.DATATYPES.NUMBER);
	put("ID_HANDELSDEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND_TRANSIT1",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND_TRANSIT2",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND_TRANSIT3",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TRANSPORTMITTEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_UMA_KONTRAKT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VERARBEITUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_VERPACKUNGSART",MyRECORD.DATATYPES.NUMBER);
	put("KOSTEN_TRANSPORT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERINFO_TPA",MyRECORD.DATATYPES.TEXT);
	put("ORDNUNGSNUMMER_CMR",MyRECORD.DATATYPES.TEXT);
	put("PLANMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("POSNR",MyRECORD.DATATYPES.NUMBER);
	put("PRINT_ANHANG7",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TIMESTAMP_IN_MASK",MyRECORD.DATATYPES.TEXT);
	put("TRANSPORTKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("TRANSPORTMITTEL",MyRECORD.DATATYPES.TEXT);
	put("TRANSPORTVERANTWORTUNG",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_BG_VEKTOR.HM_FIELDS;	
	}

}
