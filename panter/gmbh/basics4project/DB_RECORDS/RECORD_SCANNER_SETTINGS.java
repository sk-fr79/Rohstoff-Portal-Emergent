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

public class RECORD_SCANNER_SETTINGS extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_SCANNER_SETTINGS";
    public static String IDFIELD   = "ID_SCANNER_SETTINGS";
    

	//erzeugen eines RECORDNEW_JT_SCANNER_SETTINGS - felds
	private RECORDNEW_SCANNER_SETTINGS   recNEW = null;

    private _TAB  tab = _TAB.scanner_settings;  



	public RECORD_SCANNER_SETTINGS() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");
	}


	public RECORD_SCANNER_SETTINGS(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");
	}



	public RECORD_SCANNER_SETTINGS(RECORD_SCANNER_SETTINGS recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_SCANNER_SETTINGS.TABLENAME);
	}


	//2014-04-03
	public RECORD_SCANNER_SETTINGS(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");
		this.set_Tablename_To_FieldMetaDefs(RECORD_SCANNER_SETTINGS.TABLENAME);
	}




	public RECORD_SCANNER_SETTINGS(long lID_Unformated) throws myException
	{
		super("JT_SCANNER_SETTINGS","ID_SCANNER_SETTINGS",""+lID_Unformated);
		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_SCANNER_SETTINGS.TABLENAME);
	}

	public RECORD_SCANNER_SETTINGS(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_SCANNER_SETTINGS", "ID_SCANNER_SETTINGS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_SCANNER_SETTINGS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_SCANNER_SETTINGS.TABLENAME);
	}
	
	

	public RECORD_SCANNER_SETTINGS(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_SCANNER_SETTINGS","ID_SCANNER_SETTINGS",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_SCANNER_SETTINGS.TABLENAME);

	}


	public RECORD_SCANNER_SETTINGS(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_SCANNER_SETTINGS");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_SCANNER_SETTINGS", "ID_SCANNER_SETTINGS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_SCANNER_SETTINGS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_SCANNER_SETTINGS.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_SCANNER_SETTINGS();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_SCANNER_SETTINGS.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_SCANNER_SETTINGS";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_SCANNER_SETTINGS_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_SCANNER_SETTINGS_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_SCANNER_SETTINGS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_SCANNER_SETTINGS", bibE2.cTO(), "ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_SCANNER_SETTINGS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_SCANNER_SETTINGS", bibE2.cTO(), "ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_SCANNER_SETTINGS WHERE ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_SCANNER_SETTINGS WHERE ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF();
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
			if (S.isFull(this.get_ID_SCANNER_SETTINGS_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_SCANNER_SETTINGS", "ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_SCANNER_SETTINGS get_RECORDNEW_SCANNER_SETTINGS() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_SCANNER_SETTINGS();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_SCANNER_SETTINGS(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_SCANNER_SETTINGS create_Instance() throws myException {
		return new RECORD_SCANNER_SETTINGS();
	}
	
	public static RECORD_SCANNER_SETTINGS create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_SCANNER_SETTINGS(Conn);
    }

	public static RECORD_SCANNER_SETTINGS create_Instance(RECORD_SCANNER_SETTINGS recordOrig) {
		return new RECORD_SCANNER_SETTINGS(recordOrig);
    }

	public static RECORD_SCANNER_SETTINGS create_Instance(long lID_Unformated) throws myException {
		return new RECORD_SCANNER_SETTINGS(lID_Unformated);
    }

	public static RECORD_SCANNER_SETTINGS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_SCANNER_SETTINGS(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_SCANNER_SETTINGS create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_SCANNER_SETTINGS(lID_Unformated, Conn);
	}

	public static RECORD_SCANNER_SETTINGS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_SCANNER_SETTINGS(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_SCANNER_SETTINGS create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_SCANNER_SETTINGS(recordOrig);	    
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
    public RECORD_SCANNER_SETTINGS build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_SCANNER_SETTINGS WHERE ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF());
      }
      //return new RECORD_SCANNER_SETTINGS(this.get_cSQL_4_Build());
      RECORD_SCANNER_SETTINGS rec = new RECORD_SCANNER_SETTINGS();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_SCANNER_SETTINGS
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_SCANNER_SETTINGS-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_SCANNER_SETTINGS get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_SCANNER_SETTINGS_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_SCANNER_SETTINGS  recNew = new RECORDNEW_SCANNER_SETTINGS();
		
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
    public RECORD_SCANNER_SETTINGS set_recordNew(RECORDNEW_SCANNER_SETTINGS recnew) throws myException {
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
	
	



		private RECLIST_SCANNER_GROUP_2_SETTING DOWN_RECLIST_SCANNER_GROUP_2_SETTING_id_scanner_settings = null ;




		private RECLIST_SCANNER_USER DOWN_RECLIST_SCANNER_USER_id_scanner_settings = null ;






	/**
	 * References the Field ID_SCANNER_SETTINGS 
	 * Falls keine get_ID_SCANNER_SETTINGS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SCANNER_GROUP_2_SETTING get_DOWN_RECORD_LIST_SCANNER_GROUP_2_SETTING_id_scanner_settings() throws myException
	{
		if (S.isEmpty(this.get_ID_SCANNER_SETTINGS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SCANNER_GROUP_2_SETTING_id_scanner_settings==null)
		{
			this.DOWN_RECLIST_SCANNER_GROUP_2_SETTING_id_scanner_settings = new RECLIST_SCANNER_GROUP_2_SETTING (
			       "SELECT * FROM "+bibE2.cTO()+".JT_SCANNER_GROUP_2_SETTING WHERE ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF()+" ORDER BY ID_SCANNER_GROUP_2_SETTING",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_SCANNER_GROUP_2_SETTING_id_scanner_settings;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_SCANNER_SETTINGS 
	 * Falls keine get_ID_SCANNER_SETTINGS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SCANNER_GROUP_2_SETTING get_DOWN_RECORD_LIST_SCANNER_GROUP_2_SETTING_id_scanner_settings(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_SCANNER_SETTINGS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SCANNER_GROUP_2_SETTING_id_scanner_settings==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_SCANNER_GROUP_2_SETTING WHERE ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_SCANNER_GROUP_2_SETTING_id_scanner_settings = new RECLIST_SCANNER_GROUP_2_SETTING (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_SCANNER_GROUP_2_SETTING_id_scanner_settings;
	}


	





	/**
	 * References the Field ID_SCANNER_SETTINGS 
	 * Falls keine get_ID_SCANNER_SETTINGS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SCANNER_USER get_DOWN_RECORD_LIST_SCANNER_USER_id_scanner_settings() throws myException
	{
		if (S.isEmpty(this.get_ID_SCANNER_SETTINGS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SCANNER_USER_id_scanner_settings==null)
		{
			this.DOWN_RECLIST_SCANNER_USER_id_scanner_settings = new RECLIST_SCANNER_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_SCANNER_USER WHERE ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF()+" ORDER BY ID_SCANNER_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_SCANNER_USER_id_scanner_settings;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_SCANNER_SETTINGS 
	 * Falls keine get_ID_SCANNER_SETTINGS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_SCANNER_USER get_DOWN_RECORD_LIST_SCANNER_USER_id_scanner_settings(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_SCANNER_SETTINGS_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_SCANNER_USER_id_scanner_settings==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_SCANNER_USER WHERE ID_SCANNER_SETTINGS="+this.get_ID_SCANNER_SETTINGS_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_SCANNER_USER_id_scanner_settings = new RECLIST_SCANNER_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_SCANNER_USER_id_scanner_settings;
	}


	

	public static String FIELD__BESCHREIBUNG = "BESCHREIBUNG";
	public static String FIELD__DPI = "DPI";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FILETYPE = "FILETYPE";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_SCANNER_SETTINGS = "ID_SCANNER_SETTINGS";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LOOP_SCAN = "LOOP_SCAN";
	public static String FIELD__LOOP_TIMEOUT_SECONDS = "LOOP_TIMEOUT_SECONDS";
	public static String FIELD__MODULE_KENNER = "MODULE_KENNER";
	public static String FIELD__PROGRAMM_KENNER = "PROGRAMM_KENNER";
	public static String FIELD__SCANNER_AUFRUF1 = "SCANNER_AUFRUF1";
	public static String FIELD__SCANNER_AUFRUF2 = "SCANNER_AUFRUF2";
	public static String FIELD__SCANNER_AUFRUF3 = "SCANNER_AUFRUF3";
	public static String FIELD__SCANNER_AUFRUF4 = "SCANNER_AUFRUF4";
	public static String FIELD__SCANNER_NAME = "SCANNER_NAME";
	public static String FIELD__STANDORT = "STANDORT";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


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
		public String get_DPI_cUF() throws myException
	{
		return this.get_UnFormatedValue("DPI");
	}

	public String get_DPI_cF() throws myException
	{
		return this.get_FormatedValue("DPI");	
	}

	public String get_DPI_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DPI");
	}

	public String get_DPI_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DPI",cNotNullValue);
	}

	public String get_DPI_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DPI",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DPI(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DPI", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DPI(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DPI", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DPI_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DPI", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DPI_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DPI", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DPI_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DPI", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DPI_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DPI", calNewValueFormated);
	}
		public Long get_DPI_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DPI").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DPI_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DPI").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DPI_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DPI").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_DPI_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DPI_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_DPI_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DPI_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_DPI_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DPI").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DPI_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DPI").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
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
		public String get_FILETYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("FILETYPE");
	}

	public String get_FILETYPE_cF() throws myException
	{
		return this.get_FormatedValue("FILETYPE");	
	}

	public String get_FILETYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FILETYPE");
	}

	public String get_FILETYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FILETYPE",cNotNullValue);
	}

	public String get_FILETYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FILETYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FILETYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FILETYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FILETYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FILETYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILETYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FILETYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILETYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILETYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILETYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILETYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FILETYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FILETYPE", calNewValueFormated);
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
	
	
	public String get_ID_SCANNER_SETTINGS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_SCANNER_SETTINGS");
	}

	public String get_ID_SCANNER_SETTINGS_cF() throws myException
	{
		return this.get_FormatedValue("ID_SCANNER_SETTINGS");	
	}

	public String get_ID_SCANNER_SETTINGS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_SCANNER_SETTINGS");
	}

	public String get_ID_SCANNER_SETTINGS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_SCANNER_SETTINGS",cNotNullValue);
	}

	public String get_ID_SCANNER_SETTINGS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_SCANNER_SETTINGS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_SCANNER_SETTINGS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_SCANNER_SETTINGS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_SCANNER_SETTINGS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_SCANNER_SETTINGS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_SCANNER_SETTINGS", calNewValueFormated);
	}
		public Long get_ID_SCANNER_SETTINGS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_SCANNER_SETTINGS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_SCANNER_SETTINGS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_SCANNER_SETTINGS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_SCANNER_SETTINGS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_SCANNER_SETTINGS").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_SCANNER_SETTINGS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_SCANNER_SETTINGS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_SCANNER_SETTINGS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_SCANNER_SETTINGS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_SCANNER_SETTINGS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_SCANNER_SETTINGS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_SCANNER_SETTINGS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_SCANNER_SETTINGS").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
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
		public String get_LOOP_SCAN_cUF() throws myException
	{
		return this.get_UnFormatedValue("LOOP_SCAN");
	}

	public String get_LOOP_SCAN_cF() throws myException
	{
		return this.get_FormatedValue("LOOP_SCAN");	
	}

	public String get_LOOP_SCAN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LOOP_SCAN");
	}

	public String get_LOOP_SCAN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LOOP_SCAN",cNotNullValue);
	}

	public String get_LOOP_SCAN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LOOP_SCAN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LOOP_SCAN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LOOP_SCAN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LOOP_SCAN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LOOP_SCAN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOOP_SCAN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOOP_SCAN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_SCAN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOOP_SCAN", calNewValueFormated);
	}
		public boolean is_LOOP_SCAN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_LOOP_SCAN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_LOOP_SCAN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_LOOP_SCAN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LOOP_TIMEOUT_SECONDS_cUF() throws myException
	{
		return this.get_UnFormatedValue("LOOP_TIMEOUT_SECONDS");
	}

	public String get_LOOP_TIMEOUT_SECONDS_cF() throws myException
	{
		return this.get_FormatedValue("LOOP_TIMEOUT_SECONDS");	
	}

	public String get_LOOP_TIMEOUT_SECONDS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LOOP_TIMEOUT_SECONDS");
	}

	public String get_LOOP_TIMEOUT_SECONDS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LOOP_TIMEOUT_SECONDS",cNotNullValue);
	}

	public String get_LOOP_TIMEOUT_SECONDS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LOOP_TIMEOUT_SECONDS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LOOP_TIMEOUT_SECONDS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LOOP_TIMEOUT_SECONDS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LOOP_TIMEOUT_SECONDS", calNewValueFormated);
	}
		public Long get_LOOP_TIMEOUT_SECONDS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LOOP_TIMEOUT_SECONDS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LOOP_TIMEOUT_SECONDS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LOOP_TIMEOUT_SECONDS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LOOP_TIMEOUT_SECONDS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LOOP_TIMEOUT_SECONDS").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LOOP_TIMEOUT_SECONDS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LOOP_TIMEOUT_SECONDS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LOOP_TIMEOUT_SECONDS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LOOP_TIMEOUT_SECONDS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_LOOP_TIMEOUT_SECONDS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LOOP_TIMEOUT_SECONDS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LOOP_TIMEOUT_SECONDS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LOOP_TIMEOUT_SECONDS").getBigDecimalValue();
		if (bdRueck == null)
		{
			bdRueck = bdValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (bdRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		} 
	}
	
	
	public String get_MODULE_KENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MODULE_KENNER");
	}

	public String get_MODULE_KENNER_cF() throws myException
	{
		return this.get_FormatedValue("MODULE_KENNER");	
	}

	public String get_MODULE_KENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MODULE_KENNER");
	}

	public String get_MODULE_KENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MODULE_KENNER",cNotNullValue);
	}

	public String get_MODULE_KENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MODULE_KENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MODULE_KENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MODULE_KENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MODULE_KENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULE_KENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULE_KENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULE_KENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULE_KENNER", calNewValueFormated);
	}
		public String get_PROGRAMM_KENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROGRAMM_KENNER");
	}

	public String get_PROGRAMM_KENNER_cF() throws myException
	{
		return this.get_FormatedValue("PROGRAMM_KENNER");	
	}

	public String get_PROGRAMM_KENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROGRAMM_KENNER");
	}

	public String get_PROGRAMM_KENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROGRAMM_KENNER",cNotNullValue);
	}

	public String get_PROGRAMM_KENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROGRAMM_KENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", calNewValueFormated);
	}
		public String get_SCANNER_AUFRUF1_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF1");
	}

	public String get_SCANNER_AUFRUF1_cF() throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF1");	
	}

	public String get_SCANNER_AUFRUF1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCANNER_AUFRUF1");
	}

	public String get_SCANNER_AUFRUF1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF1",cNotNullValue);
	}

	public String get_SCANNER_AUFRUF1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCANNER_AUFRUF1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCANNER_AUFRUF1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF1", calNewValueFormated);
	}
		public String get_SCANNER_AUFRUF2_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF2");
	}

	public String get_SCANNER_AUFRUF2_cF() throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF2");	
	}

	public String get_SCANNER_AUFRUF2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCANNER_AUFRUF2");
	}

	public String get_SCANNER_AUFRUF2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF2",cNotNullValue);
	}

	public String get_SCANNER_AUFRUF2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCANNER_AUFRUF2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCANNER_AUFRUF2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF2", calNewValueFormated);
	}
		public String get_SCANNER_AUFRUF3_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF3");
	}

	public String get_SCANNER_AUFRUF3_cF() throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF3");	
	}

	public String get_SCANNER_AUFRUF3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCANNER_AUFRUF3");
	}

	public String get_SCANNER_AUFRUF3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF3",cNotNullValue);
	}

	public String get_SCANNER_AUFRUF3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCANNER_AUFRUF3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCANNER_AUFRUF3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF3", calNewValueFormated);
	}
		public String get_SCANNER_AUFRUF4_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF4");
	}

	public String get_SCANNER_AUFRUF4_cF() throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF4");	
	}

	public String get_SCANNER_AUFRUF4_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCANNER_AUFRUF4");
	}

	public String get_SCANNER_AUFRUF4_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCANNER_AUFRUF4",cNotNullValue);
	}

	public String get_SCANNER_AUFRUF4_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCANNER_AUFRUF4",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCANNER_AUFRUF4", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCANNER_AUFRUF4(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCANNER_AUFRUF4", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_AUFRUF4_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_AUFRUF4", calNewValueFormated);
	}
		public String get_SCANNER_NAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("SCANNER_NAME");
	}

	public String get_SCANNER_NAME_cF() throws myException
	{
		return this.get_FormatedValue("SCANNER_NAME");	
	}

	public String get_SCANNER_NAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SCANNER_NAME");
	}

	public String get_SCANNER_NAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SCANNER_NAME",cNotNullValue);
	}

	public String get_SCANNER_NAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SCANNER_NAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SCANNER_NAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SCANNER_NAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SCANNER_NAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SCANNER_NAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_NAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_NAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SCANNER_NAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SCANNER_NAME", calNewValueFormated);
	}
		public String get_STANDORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STANDORT");
	}

	public String get_STANDORT_cF() throws myException
	{
		return this.get_FormatedValue("STANDORT");	
	}

	public String get_STANDORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STANDORT");
	}

	public String get_STANDORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STANDORT",cNotNullValue);
	}

	public String get_STANDORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STANDORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STANDORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STANDORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STANDORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STANDORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STANDORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STANDORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STANDORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STANDORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STANDORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STANDORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STANDORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STANDORT", calNewValueFormated);
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
	put("BESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("DPI",MyRECORD.DATATYPES.NUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FILETYPE",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_SCANNER_SETTINGS",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LOOP_SCAN",MyRECORD.DATATYPES.YESNO);
	put("LOOP_TIMEOUT_SECONDS",MyRECORD.DATATYPES.NUMBER);
	put("MODULE_KENNER",MyRECORD.DATATYPES.TEXT);
	put("PROGRAMM_KENNER",MyRECORD.DATATYPES.TEXT);
	put("SCANNER_AUFRUF1",MyRECORD.DATATYPES.TEXT);
	put("SCANNER_AUFRUF2",MyRECORD.DATATYPES.TEXT);
	put("SCANNER_AUFRUF3",MyRECORD.DATATYPES.TEXT);
	put("SCANNER_AUFRUF4",MyRECORD.DATATYPES.TEXT);
	put("SCANNER_NAME",MyRECORD.DATATYPES.TEXT);
	put("STANDORT",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_SCANNER_SETTINGS.HM_FIELDS;	
	}

}
