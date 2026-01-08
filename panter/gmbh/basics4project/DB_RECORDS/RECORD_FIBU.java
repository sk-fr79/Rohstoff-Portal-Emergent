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

public class RECORD_FIBU extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_FIBU";
    public static String IDFIELD   = "ID_FIBU";
    

	//erzeugen eines RECORDNEW_JT_FIBU - felds
	private RECORDNEW_FIBU   recNEW = null;

    private _TAB  tab = _TAB.fibu;  



	public RECORD_FIBU() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_FIBU");
	}


	public RECORD_FIBU(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FIBU");
	}



	public RECORD_FIBU(RECORD_FIBU recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FIBU");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU.TABLENAME);
	}


	//2014-04-03
	public RECORD_FIBU(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FIBU");
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU.TABLENAME);
	}




	public RECORD_FIBU(long lID_Unformated) throws myException
	{
		super("JT_FIBU","ID_FIBU",""+lID_Unformated);
		this.set_TABLE_NAME("JT_FIBU");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU.TABLENAME);
	}

	public RECORD_FIBU(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_FIBU");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU", "ID_FIBU="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU.TABLENAME);
	}
	
	

	public RECORD_FIBU(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_FIBU","ID_FIBU",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_FIBU");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU.TABLENAME);

	}


	public RECORD_FIBU(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FIBU");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU", "ID_FIBU="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FIBU.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_FIBU();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_FIBU.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_FIBU";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_FIBU_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_FIBU_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FIBU was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FIBU", bibE2.cTO(), "ID_FIBU="+this.get_ID_FIBU_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FIBU was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FIBU", bibE2.cTO(), "ID_FIBU="+this.get_ID_FIBU_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_FIBU="+this.get_ID_FIBU_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_FIBU="+this.get_ID_FIBU_cUF();
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
			if (S.isFull(this.get_ID_FIBU_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FIBU", "ID_FIBU="+this.get_ID_FIBU_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_FIBU get_RECORDNEW_FIBU() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_FIBU();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_FIBU(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_FIBU create_Instance() throws myException {
		return new RECORD_FIBU();
	}
	
	public static RECORD_FIBU create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_FIBU(Conn);
    }

	public static RECORD_FIBU create_Instance(RECORD_FIBU recordOrig) {
		return new RECORD_FIBU(recordOrig);
    }

	public static RECORD_FIBU create_Instance(long lID_Unformated) throws myException {
		return new RECORD_FIBU(lID_Unformated);
    }

	public static RECORD_FIBU create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_FIBU(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_FIBU create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_FIBU(lID_Unformated, Conn);
	}

	public static RECORD_FIBU create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_FIBU(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_FIBU create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_FIBU(recordOrig);	    
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
    public RECORD_FIBU build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_FIBU="+this.get_ID_FIBU_cUF());
      }
      //return new RECORD_FIBU(this.get_cSQL_4_Build());
      RECORD_FIBU rec = new RECORD_FIBU();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_FIBU
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_FIBU-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_FIBU get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_FIBU_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_FIBU  recNew = new RECORDNEW_FIBU();
		
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
    public RECORD_FIBU set_recordNew(RECORDNEW_FIBU recnew) throws myException {
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




		private RECLIST_FIBU_MAHNUNG DOWN_RECLIST_FIBU_MAHNUNG_id_fibu = null ;




		private RECLIST_MAHNUNG_POS DOWN_RECLIST_MAHNUNG_POS_id_fibu = null ;




		private RECORD_VKOPF_RG UP_RECORD_VKOPF_RG_id_vkopf_rg = null;






	
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
	 * References the Field ID_FIBU 
	 * Falls keine get_ID_FIBU_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_MAHNUNG get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_fibu() throws myException
	{
		if (S.isEmpty(this.get_ID_FIBU_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_MAHNUNG_id_fibu==null)
		{
			this.DOWN_RECLIST_FIBU_MAHNUNG_id_fibu = new RECLIST_FIBU_MAHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_MAHNUNG WHERE ID_FIBU="+this.get_ID_FIBU_cUF()+" ORDER BY ID_FIBU_MAHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_MAHNUNG_id_fibu;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_FIBU 
	 * Falls keine get_ID_FIBU_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_MAHNUNG get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_fibu(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_FIBU_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_MAHNUNG_id_fibu==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_MAHNUNG WHERE ID_FIBU="+this.get_ID_FIBU_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_MAHNUNG_id_fibu = new RECLIST_FIBU_MAHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_MAHNUNG_id_fibu;
	}


	





	/**
	 * References the Field ID_FIBU 
	 * Falls keine get_ID_FIBU_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG_POS get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu() throws myException
	{
		if (S.isEmpty(this.get_ID_FIBU_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_POS_id_fibu==null)
		{
			this.DOWN_RECLIST_MAHNUNG_POS_id_fibu = new RECLIST_MAHNUNG_POS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG_POS WHERE ID_FIBU="+this.get_ID_FIBU_cUF()+" ORDER BY ID_MAHNUNG_POS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_POS_id_fibu;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_FIBU 
	 * Falls keine get_ID_FIBU_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG_POS get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_FIBU_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_POS_id_fibu==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG_POS WHERE ID_FIBU="+this.get_ID_FIBU_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAHNUNG_POS_id_fibu = new RECLIST_MAHNUNG_POS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_POS_id_fibu;
	}


	





	
	/**
	 * References the Field ID_VKOPF_RG
	 * Falls keine this.get_ID_VKOPF_RG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VKOPF_RG get_UP_RECORD_VKOPF_RG_id_vkopf_rg() throws myException
	{
		if (S.isEmpty(this.get_ID_VKOPF_RG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VKOPF_RG_id_vkopf_rg==null)
		{
			this.UP_RECORD_VKOPF_RG_id_vkopf_rg = new RECORD_VKOPF_RG(this.get_ID_VKOPF_RG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VKOPF_RG_id_vkopf_rg;
	}
	

	public static String FIELD__ANZAHL_SCHECK_DRUCK = "ANZAHL_SCHECK_DRUCK";
	public static String FIELD__BEARBEITERKUERZEL = "BEARBEITERKUERZEL";
	public static String FIELD__BUCHUNGSBLOCK_NR = "BUCHUNGSBLOCK_NR";
	public static String FIELD__BUCHUNGSDATUM = "BUCHUNGSDATUM";
	public static String FIELD__BUCHUNGSINFO = "BUCHUNGSINFO";
	public static String FIELD__BUCHUNGSTYP = "BUCHUNGSTYP";
	public static String FIELD__BUCHUNG_GESCHLOSSEN = "BUCHUNG_GESCHLOSSEN";
	public static String FIELD__DATUMVERAENDERUNG = "DATUMVERAENDERUNG";
	public static String FIELD__ENDBETRAG_BASIS_WAEHRUNG = "ENDBETRAG_BASIS_WAEHRUNG";
	public static String FIELD__ENDBETRAG_FREMD_WAEHRUNG = "ENDBETRAG_FREMD_WAEHRUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FAKTOR_BUCHUNG_PLUS_MINUS = "FAKTOR_BUCHUNG_PLUS_MINUS";
	public static String FIELD__FREMDBELEGNUMMER = "FREMDBELEGNUMMER";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_FIBU = "ID_FIBU";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VKOPF_RG = "ID_VKOPF_RG";
	public static String FIELD__INTERN_INFO = "INTERN_INFO";
	public static String FIELD__KORREKTURBUCHUNG = "KORREKTURBUCHUNG";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__NETTOSUMME_BASIS_WAEHRUNG = "NETTOSUMME_BASIS_WAEHRUNG";
	public static String FIELD__NETTOSUMME_FREMD_WAEHRUNG = "NETTOSUMME_FREMD_WAEHRUNG";
	public static String FIELD__RESTBETRAG_FREMD_WAEHRUNG = "RESTBETRAG_FREMD_WAEHRUNG";
	public static String FIELD__SCHECKNUMMER = "SCHECKNUMMER";
	public static String FIELD__SCHECK_VERWENDUNGSZWECK = "SCHECK_VERWENDUNGSZWECK";
	public static String FIELD__SKONTOBETRAG_BASIS_WAEHRUNG = "SKONTOBETRAG_BASIS_WAEHRUNG";
	public static String FIELD__SKONTOBETRAG_FREMD_WAEHRUNG = "SKONTOBETRAG_FREMD_WAEHRUNG";
	public static String FIELD__SKONTO_DATUM_UEBERSCHRITTEN = "SKONTO_DATUM_UEBERSCHRITTEN";
	public static String FIELD__STEUERSUMME_BASIS_WAEHRUNG = "STEUERSUMME_BASIS_WAEHRUNG";
	public static String FIELD__STEUERSUMME_FREMD_WAEHRUNG = "STEUERSUMME_FREMD_WAEHRUNG";
	public static String FIELD__STORNIERT = "STORNIERT";
	public static String FIELD__STORNIERT_AM = "STORNIERT_AM";
	public static String FIELD__STORNOGRUND = "STORNOGRUND";
	public static String FIELD__STORNOKUERZEL = "STORNOKUERZEL";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__VORLAEUFIG = "VORLAEUFIG";
	public static String FIELD__WAEHRUNG_FREMD = "WAEHRUNG_FREMD";
	public static String FIELD__ZAHLUNGSBETRAG_BASIS_WAEHRUNG = "ZAHLUNGSBETRAG_BASIS_WAEHRUNG";
	public static String FIELD__ZAHLUNGSBETRAG_FREMD_WAEHRUNG = "ZAHLUNGSBETRAG_FREMD_WAEHRUNG";
	public static String FIELD__ZAHLUNGSZIEL = "ZAHLUNGSZIEL";


	public String get_ANZAHL_SCHECK_DRUCK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_SCHECK_DRUCK");
	}

	public String get_ANZAHL_SCHECK_DRUCK_cF() throws myException
	{
		return this.get_FormatedValue("ANZAHL_SCHECK_DRUCK");	
	}

	public String get_ANZAHL_SCHECK_DRUCK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZAHL_SCHECK_DRUCK");
	}

	public String get_ANZAHL_SCHECK_DRUCK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_SCHECK_DRUCK",cNotNullValue);
	}

	public String get_ANZAHL_SCHECK_DRUCK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZAHL_SCHECK_DRUCK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZAHL_SCHECK_DRUCK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_SCHECK_DRUCK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_SCHECK_DRUCK", calNewValueFormated);
	}
		public Long get_ANZAHL_SCHECK_DRUCK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ANZAHL_SCHECK_DRUCK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ANZAHL_SCHECK_DRUCK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZAHL_SCHECK_DRUCK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZAHL_SCHECK_DRUCK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZAHL_SCHECK_DRUCK").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ANZAHL_SCHECK_DRUCK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_SCHECK_DRUCK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ANZAHL_SCHECK_DRUCK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_SCHECK_DRUCK_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ANZAHL_SCHECK_DRUCK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_SCHECK_DRUCK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZAHL_SCHECK_DRUCK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_SCHECK_DRUCK").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_BEARBEITERKUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEARBEITERKUERZEL");
	}

	public String get_BEARBEITERKUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("BEARBEITERKUERZEL");	
	}

	public String get_BEARBEITERKUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEARBEITERKUERZEL");
	}

	public String get_BEARBEITERKUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEARBEITERKUERZEL",cNotNullValue);
	}

	public String get_BEARBEITERKUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEARBEITERKUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEARBEITERKUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEARBEITERKUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEARBEITERKUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITERKUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITERKUERZEL", calNewValueFormated);
	}
		public String get_BUCHUNGSBLOCK_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSBLOCK_NR");
	}

	public String get_BUCHUNGSBLOCK_NR_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSBLOCK_NR");	
	}

	public String get_BUCHUNGSBLOCK_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSBLOCK_NR");
	}

	public String get_BUCHUNGSBLOCK_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSBLOCK_NR",cNotNullValue);
	}

	public String get_BUCHUNGSBLOCK_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSBLOCK_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSBLOCK_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSBLOCK_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSBLOCK_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSBLOCK_NR", calNewValueFormated);
	}
		public Long get_BUCHUNGSBLOCK_NR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("BUCHUNGSBLOCK_NR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_BUCHUNGSBLOCK_NR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("BUCHUNGSBLOCK_NR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_BUCHUNGSBLOCK_NR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("BUCHUNGSBLOCK_NR").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_BUCHUNGSBLOCK_NR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BUCHUNGSBLOCK_NR_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_BUCHUNGSBLOCK_NR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BUCHUNGSBLOCK_NR_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_BUCHUNGSBLOCK_NR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("BUCHUNGSBLOCK_NR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_BUCHUNGSBLOCK_NR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("BUCHUNGSBLOCK_NR").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_BUCHUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSDATUM");
	}

	public String get_BUCHUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSDATUM");	
	}

	public String get_BUCHUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSDATUM");
	}

	public String get_BUCHUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSDATUM",cNotNullValue);
	}

	public String get_BUCHUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", calNewValueFormated);
	}
		public String get_BUCHUNGSINFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSINFO");
	}

	public String get_BUCHUNGSINFO_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSINFO");	
	}

	public String get_BUCHUNGSINFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSINFO");
	}

	public String get_BUCHUNGSINFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSINFO",cNotNullValue);
	}

	public String get_BUCHUNGSINFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSINFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSINFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSINFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSINFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSINFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSINFO", calNewValueFormated);
	}
		public String get_BUCHUNGSTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSTYP");
	}

	public String get_BUCHUNGSTYP_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSTYP");	
	}

	public String get_BUCHUNGSTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSTYP");
	}

	public String get_BUCHUNGSTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSTYP",cNotNullValue);
	}

	public String get_BUCHUNGSTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", calNewValueFormated);
	}
		public String get_BUCHUNG_GESCHLOSSEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNG_GESCHLOSSEN");
	}

	public String get_BUCHUNG_GESCHLOSSEN_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNG_GESCHLOSSEN");	
	}

	public String get_BUCHUNG_GESCHLOSSEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNG_GESCHLOSSEN");
	}

	public String get_BUCHUNG_GESCHLOSSEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNG_GESCHLOSSEN",cNotNullValue);
	}

	public String get_BUCHUNG_GESCHLOSSEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNG_GESCHLOSSEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNG_GESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_GESCHLOSSEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNG_GESCHLOSSEN", calNewValueFormated);
	}
		public boolean is_BUCHUNG_GESCHLOSSEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BUCHUNG_GESCHLOSSEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BUCHUNG_GESCHLOSSEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BUCHUNG_GESCHLOSSEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DATUMVERAENDERUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUMVERAENDERUNG");
	}

	public String get_DATUMVERAENDERUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUMVERAENDERUNG");	
	}

	public String get_DATUMVERAENDERUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUMVERAENDERUNG");
	}

	public String get_DATUMVERAENDERUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUMVERAENDERUNG",cNotNullValue);
	}

	public String get_DATUMVERAENDERUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUMVERAENDERUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUMVERAENDERUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUMVERAENDERUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUMVERAENDERUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUMVERAENDERUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUMVERAENDERUNG", calNewValueFormated);
	}
		public String get_ENDBETRAG_BASIS_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ENDBETRAG_BASIS_WAEHRUNG");
	}

	public String get_ENDBETRAG_BASIS_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ENDBETRAG_BASIS_WAEHRUNG");	
	}

	public String get_ENDBETRAG_BASIS_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ENDBETRAG_BASIS_WAEHRUNG");
	}

	public String get_ENDBETRAG_BASIS_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ENDBETRAG_BASIS_WAEHRUNG",cNotNullValue);
	}

	public String get_ENDBETRAG_BASIS_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ENDBETRAG_BASIS_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDBETRAG_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public Double get_ENDBETRAG_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ENDBETRAG_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ENDBETRAG_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ENDBETRAG_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ENDBETRAG_BASIS_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ENDBETRAG_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ENDBETRAG_BASIS_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ENDBETRAG_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ENDBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ENDBETRAG_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ENDBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ENDBETRAG_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ENDBETRAG_FREMD_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ENDBETRAG_FREMD_WAEHRUNG");
	}

	public String get_ENDBETRAG_FREMD_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ENDBETRAG_FREMD_WAEHRUNG");	
	}

	public String get_ENDBETRAG_FREMD_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ENDBETRAG_FREMD_WAEHRUNG");
	}

	public String get_ENDBETRAG_FREMD_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ENDBETRAG_FREMD_WAEHRUNG",cNotNullValue);
	}

	public String get_ENDBETRAG_FREMD_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ENDBETRAG_FREMD_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public Double get_ENDBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ENDBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ENDBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ENDBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ENDBETRAG_FREMD_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ENDBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ENDBETRAG_FREMD_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ENDBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ENDBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ENDBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
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
		public String get_FAKTOR_BUCHUNG_PLUS_MINUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAKTOR_BUCHUNG_PLUS_MINUS");
	}

	public String get_FAKTOR_BUCHUNG_PLUS_MINUS_cF() throws myException
	{
		return this.get_FormatedValue("FAKTOR_BUCHUNG_PLUS_MINUS");	
	}

	public String get_FAKTOR_BUCHUNG_PLUS_MINUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAKTOR_BUCHUNG_PLUS_MINUS");
	}

	public String get_FAKTOR_BUCHUNG_PLUS_MINUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAKTOR_BUCHUNG_PLUS_MINUS",cNotNullValue);
	}

	public String get_FAKTOR_BUCHUNG_PLUS_MINUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAKTOR_BUCHUNG_PLUS_MINUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAKTOR_BUCHUNG_PLUS_MINUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAKTOR_BUCHUNG_PLUS_MINUS", calNewValueFormated);
	}
		public Long get_FAKTOR_BUCHUNG_PLUS_MINUS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FAKTOR_BUCHUNG_PLUS_MINUS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FAKTOR_BUCHUNG_PLUS_MINUS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FAKTOR_BUCHUNG_PLUS_MINUS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FAKTOR_BUCHUNG_PLUS_MINUS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FAKTOR_BUCHUNG_PLUS_MINUS").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_FAKTOR_BUCHUNG_PLUS_MINUS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FAKTOR_BUCHUNG_PLUS_MINUS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_FAKTOR_BUCHUNG_PLUS_MINUS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FAKTOR_BUCHUNG_PLUS_MINUS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FAKTOR_BUCHUNG_PLUS_MINUS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FAKTOR_BUCHUNG_PLUS_MINUS").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_FREMDBELEGNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("FREMDBELEGNUMMER");
	}

	public String get_FREMDBELEGNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("FREMDBELEGNUMMER");	
	}

	public String get_FREMDBELEGNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FREMDBELEGNUMMER");
	}

	public String get_FREMDBELEGNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FREMDBELEGNUMMER",cNotNullValue);
	}

	public String get_FREMDBELEGNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FREMDBELEGNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FREMDBELEGNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FREMDBELEGNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FREMDBELEGNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FREMDBELEGNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FREMDBELEGNUMMER", calNewValueFormated);
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
	
	
	public String get_ID_FIBU_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU");
	}

	public String get_ID_FIBU_cF() throws myException
	{
		return this.get_FormatedValue("ID_FIBU");	
	}

	public String get_ID_FIBU_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_FIBU");
	}

	public String get_ID_FIBU_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU",cNotNullValue);
	}

	public String get_ID_FIBU_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_FIBU",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_FIBU", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_FIBU", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_FIBU", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU", calNewValueFormated);
	}
		public Long get_ID_FIBU_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_FIBU").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_FIBU_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_FIBU").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_FIBU_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_FIBU").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_FIBU_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_FIBU_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_FIBU_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_FIBU_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
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
	
	
	public String get_ID_VKOPF_RG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VKOPF_RG");
	}

	public String get_ID_VKOPF_RG_cF() throws myException
	{
		return this.get_FormatedValue("ID_VKOPF_RG");	
	}

	public String get_ID_VKOPF_RG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VKOPF_RG");
	}

	public String get_ID_VKOPF_RG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VKOPF_RG",cNotNullValue);
	}

	public String get_ID_VKOPF_RG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VKOPF_RG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_RG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VKOPF_RG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_RG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_RG", calNewValueFormated);
	}
		public Long get_ID_VKOPF_RG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VKOPF_RG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VKOPF_RG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VKOPF_RG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VKOPF_RG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VKOPF_RG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_VKOPF_RG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VKOPF_RG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_VKOPF_RG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VKOPF_RG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VKOPF_RG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VKOPF_RG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VKOPF_RG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VKOPF_RG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_INTERN_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTERN_INFO");
	}

	public String get_INTERN_INFO_cF() throws myException
	{
		return this.get_FormatedValue("INTERN_INFO");	
	}

	public String get_INTERN_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTERN_INFO");
	}

	public String get_INTERN_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTERN_INFO",cNotNullValue);
	}

	public String get_INTERN_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTERN_INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTERN_INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTERN_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTERN_INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTERN_INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTERN_INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTERN_INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTERN_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTERN_INFO", calNewValueFormated);
	}
		public String get_KORREKTURBUCHUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("KORREKTURBUCHUNG");
	}

	public String get_KORREKTURBUCHUNG_cF() throws myException
	{
		return this.get_FormatedValue("KORREKTURBUCHUNG");	
	}

	public String get_KORREKTURBUCHUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KORREKTURBUCHUNG");
	}

	public String get_KORREKTURBUCHUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KORREKTURBUCHUNG",cNotNullValue);
	}

	public String get_KORREKTURBUCHUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KORREKTURBUCHUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KORREKTURBUCHUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KORREKTURBUCHUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KORREKTURBUCHUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KORREKTURBUCHUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KORREKTURBUCHUNG", calNewValueFormated);
	}
		public boolean is_KORREKTURBUCHUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KORREKTURBUCHUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KORREKTURBUCHUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KORREKTURBUCHUNG_cUF_NN("N").equals("N"))
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
		public String get_NETTOSUMME_BASIS_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("NETTOSUMME_BASIS_WAEHRUNG");
	}

	public String get_NETTOSUMME_BASIS_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("NETTOSUMME_BASIS_WAEHRUNG");	
	}

	public String get_NETTOSUMME_BASIS_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NETTOSUMME_BASIS_WAEHRUNG");
	}

	public String get_NETTOSUMME_BASIS_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NETTOSUMME_BASIS_WAEHRUNG",cNotNullValue);
	}

	public String get_NETTOSUMME_BASIS_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NETTOSUMME_BASIS_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOSUMME_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public Double get_NETTOSUMME_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NETTOSUMME_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NETTOSUMME_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NETTOSUMME_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_NETTOSUMME_BASIS_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOSUMME_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_NETTOSUMME_BASIS_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOSUMME_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_NETTOSUMME_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOSUMME_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NETTOSUMME_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOSUMME_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_NETTOSUMME_FREMD_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("NETTOSUMME_FREMD_WAEHRUNG");
	}

	public String get_NETTOSUMME_FREMD_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("NETTOSUMME_FREMD_WAEHRUNG");	
	}

	public String get_NETTOSUMME_FREMD_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NETTOSUMME_FREMD_WAEHRUNG");
	}

	public String get_NETTOSUMME_FREMD_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NETTOSUMME_FREMD_WAEHRUNG",cNotNullValue);
	}

	public String get_NETTOSUMME_FREMD_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NETTOSUMME_FREMD_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOSUMME_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public Double get_NETTOSUMME_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NETTOSUMME_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NETTOSUMME_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NETTOSUMME_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_NETTOSUMME_FREMD_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOSUMME_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_NETTOSUMME_FREMD_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOSUMME_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_NETTOSUMME_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOSUMME_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NETTOSUMME_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOSUMME_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_RESTBETRAG_FREMD_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("RESTBETRAG_FREMD_WAEHRUNG");
	}

	public String get_RESTBETRAG_FREMD_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("RESTBETRAG_FREMD_WAEHRUNG");	
	}

	public String get_RESTBETRAG_FREMD_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RESTBETRAG_FREMD_WAEHRUNG");
	}

	public String get_RESTBETRAG_FREMD_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RESTBETRAG_FREMD_WAEHRUNG",cNotNullValue);
	}

	public String get_RESTBETRAG_FREMD_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RESTBETRAG_FREMD_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RESTBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public Double get_RESTBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("RESTBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_RESTBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("RESTBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_RESTBETRAG_FREMD_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_RESTBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_RESTBETRAG_FREMD_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_RESTBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_RESTBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("RESTBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_RESTBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("RESTBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_SCHECKNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECKNUMMER");
	}

	public String get_SCHECKNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("SCHECKNUMMER");	
	}

	public String get_SCHECKNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECKNUMMER");
	}

	public String get_SCHECKNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECKNUMMER",cNotNullValue);
	}

	public String get_SCHECKNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECKNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECKNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECKNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECKNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECKNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECKNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECKNUMMER", calNewValueFormated);
	}
		public String get_SCHECK_VERWENDUNGSZWECK_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCHECK_VERWENDUNGSZWECK");
	}

	public String get_SCHECK_VERWENDUNGSZWECK_cF() throws myException
	{
		return this.get_FormatedValue("SCHECK_VERWENDUNGSZWECK");	
	}

	public String get_SCHECK_VERWENDUNGSZWECK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCHECK_VERWENDUNGSZWECK");
	}

	public String get_SCHECK_VERWENDUNGSZWECK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCHECK_VERWENDUNGSZWECK",cNotNullValue);
	}

	public String get_SCHECK_VERWENDUNGSZWECK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCHECK_VERWENDUNGSZWECK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCHECK_VERWENDUNGSZWECK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCHECK_VERWENDUNGSZWECK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCHECK_VERWENDUNGSZWECK", calNewValueFormated);
	}
		public String get_SKONTOBETRAG_BASIS_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("SKONTOBETRAG_BASIS_WAEHRUNG");
	}

	public String get_SKONTOBETRAG_BASIS_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("SKONTOBETRAG_BASIS_WAEHRUNG");	
	}

	public String get_SKONTOBETRAG_BASIS_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SKONTOBETRAG_BASIS_WAEHRUNG");
	}

	public String get_SKONTOBETRAG_BASIS_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SKONTOBETRAG_BASIS_WAEHRUNG",cNotNullValue);
	}

	public String get_SKONTOBETRAG_BASIS_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SKONTOBETRAG_BASIS_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTOBETRAG_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public Double get_SKONTOBETRAG_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SKONTOBETRAG_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SKONTOBETRAG_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SKONTOBETRAG_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_SKONTOBETRAG_BASIS_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SKONTOBETRAG_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_SKONTOBETRAG_BASIS_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SKONTOBETRAG_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_SKONTOBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SKONTOBETRAG_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SKONTOBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SKONTOBETRAG_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_SKONTOBETRAG_FREMD_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("SKONTOBETRAG_FREMD_WAEHRUNG");
	}

	public String get_SKONTOBETRAG_FREMD_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("SKONTOBETRAG_FREMD_WAEHRUNG");	
	}

	public String get_SKONTOBETRAG_FREMD_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SKONTOBETRAG_FREMD_WAEHRUNG");
	}

	public String get_SKONTOBETRAG_FREMD_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SKONTOBETRAG_FREMD_WAEHRUNG",cNotNullValue);
	}

	public String get_SKONTOBETRAG_FREMD_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SKONTOBETRAG_FREMD_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTOBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public Double get_SKONTOBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SKONTOBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SKONTOBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SKONTOBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_SKONTOBETRAG_FREMD_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SKONTOBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_SKONTOBETRAG_FREMD_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SKONTOBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SKONTOBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SKONTOBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_SKONTO_DATUM_UEBERSCHRITTEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("SKONTO_DATUM_UEBERSCHRITTEN");
	}

	public String get_SKONTO_DATUM_UEBERSCHRITTEN_cF() throws myException
	{
		return this.get_FormatedValue("SKONTO_DATUM_UEBERSCHRITTEN");	
	}

	public String get_SKONTO_DATUM_UEBERSCHRITTEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SKONTO_DATUM_UEBERSCHRITTEN");
	}

	public String get_SKONTO_DATUM_UEBERSCHRITTEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SKONTO_DATUM_UEBERSCHRITTEN",cNotNullValue);
	}

	public String get_SKONTO_DATUM_UEBERSCHRITTEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SKONTO_DATUM_UEBERSCHRITTEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTO_DATUM_UEBERSCHRITTEN", calNewValueFormated);
	}
		public boolean is_SKONTO_DATUM_UEBERSCHRITTEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SKONTO_DATUM_UEBERSCHRITTEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SKONTO_DATUM_UEBERSCHRITTEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SKONTO_DATUM_UEBERSCHRITTEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STEUERSUMME_BASIS_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSUMME_BASIS_WAEHRUNG");
	}

	public String get_STEUERSUMME_BASIS_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSUMME_BASIS_WAEHRUNG");	
	}

	public String get_STEUERSUMME_BASIS_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSUMME_BASIS_WAEHRUNG");
	}

	public String get_STEUERSUMME_BASIS_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSUMME_BASIS_WAEHRUNG",cNotNullValue);
	}

	public String get_STEUERSUMME_BASIS_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSUMME_BASIS_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSUMME_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public Double get_STEUERSUMME_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSUMME_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSUMME_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSUMME_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_STEUERSUMME_BASIS_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSUMME_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_STEUERSUMME_BASIS_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSUMME_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_STEUERSUMME_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSUMME_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSUMME_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSUMME_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_STEUERSUMME_FREMD_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSUMME_FREMD_WAEHRUNG");
	}

	public String get_STEUERSUMME_FREMD_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSUMME_FREMD_WAEHRUNG");	
	}

	public String get_STEUERSUMME_FREMD_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSUMME_FREMD_WAEHRUNG");
	}

	public String get_STEUERSUMME_FREMD_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSUMME_FREMD_WAEHRUNG",cNotNullValue);
	}

	public String get_STEUERSUMME_FREMD_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSUMME_FREMD_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSUMME_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public Double get_STEUERSUMME_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSUMME_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSUMME_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSUMME_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_STEUERSUMME_FREMD_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSUMME_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_STEUERSUMME_FREMD_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSUMME_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_STEUERSUMME_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSUMME_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSUMME_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSUMME_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_STORNIERT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNIERT");
	}

	public String get_STORNIERT_cF() throws myException
	{
		return this.get_FormatedValue("STORNIERT");	
	}

	public String get_STORNIERT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNIERT");
	}

	public String get_STORNIERT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNIERT",cNotNullValue);
	}

	public String get_STORNIERT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNIERT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNIERT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT", calNewValueFormated);
	}
		public boolean is_STORNIERT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNIERT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STORNIERT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNIERT_cUF_NN("N").equals("N"))
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
		public String get_STORNOGRUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNOGRUND");
	}

	public String get_STORNOGRUND_cF() throws myException
	{
		return this.get_FormatedValue("STORNOGRUND");	
	}

	public String get_STORNOGRUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNOGRUND");
	}

	public String get_STORNOGRUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNOGRUND",cNotNullValue);
	}

	public String get_STORNOGRUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNOGRUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNOGRUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNOGRUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNOGRUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNOGRUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNOGRUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNOGRUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOGRUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNOGRUND", calNewValueFormated);
	}
		public String get_STORNOKUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNOKUERZEL");
	}

	public String get_STORNOKUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("STORNOKUERZEL");	
	}

	public String get_STORNOKUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNOKUERZEL");
	}

	public String get_STORNOKUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNOKUERZEL",cNotNullValue);
	}

	public String get_STORNOKUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNOKUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNOKUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNOKUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNOKUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNOKUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNOKUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNOKUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNOKUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNOKUERZEL", calNewValueFormated);
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
	
	
	public String get_VORLAEUFIG_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORLAEUFIG");
	}

	public String get_VORLAEUFIG_cF() throws myException
	{
		return this.get_FormatedValue("VORLAEUFIG");	
	}

	public String get_VORLAEUFIG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORLAEUFIG");
	}

	public String get_VORLAEUFIG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORLAEUFIG",cNotNullValue);
	}

	public String get_VORLAEUFIG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORLAEUFIG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORLAEUFIG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORLAEUFIG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORLAEUFIG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORLAEUFIG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORLAEUFIG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORLAEUFIG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORLAEUFIG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORLAEUFIG", calNewValueFormated);
	}
		public boolean is_VORLAEUFIG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_VORLAEUFIG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_VORLAEUFIG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_VORLAEUFIG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_WAEHRUNG_FREMD_cUF() throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNG_FREMD");
	}

	public String get_WAEHRUNG_FREMD_cF() throws myException
	{
		return this.get_FormatedValue("WAEHRUNG_FREMD");	
	}

	public String get_WAEHRUNG_FREMD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WAEHRUNG_FREMD");
	}

	public String get_WAEHRUNG_FREMD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNG_FREMD",cNotNullValue);
	}

	public String get_WAEHRUNG_FREMD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WAEHRUNG_FREMD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WAEHRUNG_FREMD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WAEHRUNG_FREMD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNG_FREMD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNG_FREMD", calNewValueFormated);
	}
		public String get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBETRAG_BASIS_WAEHRUNG");
	}

	public String get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBETRAG_BASIS_WAEHRUNG");	
	}

	public String get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSBETRAG_BASIS_WAEHRUNG");
	}

	public String get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBETRAG_BASIS_WAEHRUNG",cNotNullValue);
	}

	public String get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBETRAG_BASIS_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_BASIS_WAEHRUNG", calNewValueFormated);
	}
		public Double get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAHLUNGSBETRAG_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAHLUNGSBETRAG_BASIS_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHLUNGSBETRAG_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHLUNGSBETRAG_BASIS_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBETRAG_FREMD_WAEHRUNG");
	}

	public String get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBETRAG_FREMD_WAEHRUNG");	
	}

	public String get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSBETRAG_FREMD_WAEHRUNG");
	}

	public String get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBETRAG_FREMD_WAEHRUNG",cNotNullValue);
	}

	public String get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBETRAG_FREMD_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBETRAG_FREMD_WAEHRUNG", calNewValueFormated);
	}
		public Double get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAHLUNGSBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAHLUNGSBETRAG_FREMD_WAEHRUNG").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHLUNGSBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHLUNGSBETRAG_FREMD_WAEHRUNG").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_ZAHLUNGSZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSZIEL");
	}

	public String get_ZAHLUNGSZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSZIEL");	
	}

	public String get_ZAHLUNGSZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSZIEL");
	}

	public String get_ZAHLUNGSZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSZIEL",cNotNullValue);
	}

	public String get_ZAHLUNGSZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSZIEL", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ANZAHL_SCHECK_DRUCK",MyRECORD.DATATYPES.NUMBER);
	put("BEARBEITERKUERZEL",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSBLOCK_NR",MyRECORD.DATATYPES.NUMBER);
	put("BUCHUNGSDATUM",MyRECORD.DATATYPES.DATE);
	put("BUCHUNGSINFO",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSTYP",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNG_GESCHLOSSEN",MyRECORD.DATATYPES.YESNO);
	put("DATUMVERAENDERUNG",MyRECORD.DATATYPES.DATE);
	put("ENDBETRAG_BASIS_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ENDBETRAG_FREMD_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FAKTOR_BUCHUNG_PLUS_MINUS",MyRECORD.DATATYPES.NUMBER);
	put("FREMDBELEGNUMMER",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_FIBU",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VKOPF_RG",MyRECORD.DATATYPES.NUMBER);
	put("INTERN_INFO",MyRECORD.DATATYPES.TEXT);
	put("KORREKTURBUCHUNG",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("NETTOSUMME_BASIS_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("NETTOSUMME_FREMD_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("RESTBETRAG_FREMD_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SCHECKNUMMER",MyRECORD.DATATYPES.TEXT);
	put("SCHECK_VERWENDUNGSZWECK",MyRECORD.DATATYPES.TEXT);
	put("SKONTOBETRAG_BASIS_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SKONTOBETRAG_FREMD_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SKONTO_DATUM_UEBERSCHRITTEN",MyRECORD.DATATYPES.YESNO);
	put("STEUERSUMME_BASIS_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERSUMME_FREMD_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STORNIERT",MyRECORD.DATATYPES.YESNO);
	put("STORNIERT_AM",MyRECORD.DATATYPES.DATE);
	put("STORNOGRUND",MyRECORD.DATATYPES.TEXT);
	put("STORNOKUERZEL",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("VORLAEUFIG",MyRECORD.DATATYPES.YESNO);
	put("WAEHRUNG_FREMD",MyRECORD.DATATYPES.TEXT);
	put("ZAHLUNGSBETRAG_BASIS_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ZAHLUNGSBETRAG_FREMD_WAEHRUNG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ZAHLUNGSZIEL",MyRECORD.DATATYPES.DATE);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_FIBU.HM_FIELDS;	
	}

}
