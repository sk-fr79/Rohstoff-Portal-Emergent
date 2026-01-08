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

public class RECORD_QUERY extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_QUERY";
    public static String IDFIELD   = "ID_QUERY";
    

	//erzeugen eines RECORDNEW_JT_QUERY - felds
	private RECORDNEW_QUERY   recNEW = null;

    private _TAB  tab = _TAB.query;  



	public RECORD_QUERY() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_QUERY");
	}


	public RECORD_QUERY(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_QUERY");
	}



	public RECORD_QUERY(RECORD_QUERY recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_QUERY");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_QUERY.TABLENAME);
	}


	//2014-04-03
	public RECORD_QUERY(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_QUERY");
		this.set_Tablename_To_FieldMetaDefs(RECORD_QUERY.TABLENAME);
	}




	public RECORD_QUERY(long lID_Unformated) throws myException
	{
		super("JT_QUERY","ID_QUERY",""+lID_Unformated);
		this.set_TABLE_NAME("JT_QUERY");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_QUERY.TABLENAME);
	}

	public RECORD_QUERY(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_QUERY");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_QUERY", "ID_QUERY="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_QUERY", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_QUERY.TABLENAME);
	}
	
	

	public RECORD_QUERY(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_QUERY","ID_QUERY",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_QUERY");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_QUERY.TABLENAME);

	}


	public RECORD_QUERY(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_QUERY");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_QUERY", "ID_QUERY="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_QUERY", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_QUERY.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_QUERY();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_QUERY.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_QUERY";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_QUERY_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_QUERY_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_QUERY was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_QUERY", bibE2.cTO(), "ID_QUERY="+this.get_ID_QUERY_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_QUERY was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_QUERY", bibE2.cTO(), "ID_QUERY="+this.get_ID_QUERY_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_QUERY WHERE ID_QUERY="+this.get_ID_QUERY_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_QUERY WHERE ID_QUERY="+this.get_ID_QUERY_cUF();
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
			if (S.isFull(this.get_ID_QUERY_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_QUERY", "ID_QUERY="+this.get_ID_QUERY_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_QUERY get_RECORDNEW_QUERY() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_QUERY();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_QUERY(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_QUERY create_Instance() throws myException {
		return new RECORD_QUERY();
	}
	
	public static RECORD_QUERY create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_QUERY(Conn);
    }

	public static RECORD_QUERY create_Instance(RECORD_QUERY recordOrig) {
		return new RECORD_QUERY(recordOrig);
    }

	public static RECORD_QUERY create_Instance(long lID_Unformated) throws myException {
		return new RECORD_QUERY(lID_Unformated);
    }

	public static RECORD_QUERY create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_QUERY(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_QUERY create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_QUERY(lID_Unformated, Conn);
	}

	public static RECORD_QUERY create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_QUERY(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_QUERY create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_QUERY(recordOrig);	    
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
    public RECORD_QUERY build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_QUERY WHERE ID_QUERY="+this.get_ID_QUERY_cUF());
      }
      //return new RECORD_QUERY(this.get_cSQL_4_Build());
      RECORD_QUERY rec = new RECORD_QUERY();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_QUERY
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_QUERY-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_QUERY get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_QUERY_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_QUERY  recNew = new RECORDNEW_QUERY();
		
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
    public RECORD_QUERY set_recordNew(RECORDNEW_QUERY recnew) throws myException {
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
	
	



		private RECLIST_QUERY_TEILNEHMER DOWN_RECLIST_QUERY_TEILNEHMER_id_query = null ;






	/**
	 * References the Field ID_QUERY 
	 * Falls keine get_ID_QUERY_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_QUERY_TEILNEHMER get_DOWN_RECORD_LIST_QUERY_TEILNEHMER_id_query() throws myException
	{
		if (S.isEmpty(this.get_ID_QUERY_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_QUERY_TEILNEHMER_id_query==null)
		{
			this.DOWN_RECLIST_QUERY_TEILNEHMER_id_query = new RECLIST_QUERY_TEILNEHMER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_QUERY_TEILNEHMER WHERE ID_QUERY="+this.get_ID_QUERY_cUF()+" ORDER BY ID_QUERY_TEILNEHMER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_QUERY_TEILNEHMER_id_query;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_QUERY 
	 * Falls keine get_ID_QUERY_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_QUERY_TEILNEHMER get_DOWN_RECORD_LIST_QUERY_TEILNEHMER_id_query(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_QUERY_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_QUERY_TEILNEHMER_id_query==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_QUERY_TEILNEHMER WHERE ID_QUERY="+this.get_ID_QUERY_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_QUERY_TEILNEHMER_id_query = new RECLIST_QUERY_TEILNEHMER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_QUERY_TEILNEHMER_id_query;
	}


	

	public static String FIELD__ANZEIGESQL = "ANZEIGESQL";
	public static String FIELD__BESCHREIB1 = "BESCHREIB1";
	public static String FIELD__BESCHREIB2 = "BESCHREIB2";
	public static String FIELD__BESCHREIB3 = "BESCHREIB3";
	public static String FIELD__BESCHREIB4 = "BESCHREIB4";
	public static String FIELD__DEFAULT01 = "DEFAULT01";
	public static String FIELD__DEFAULT02 = "DEFAULT02";
	public static String FIELD__DEFAULT03 = "DEFAULT03";
	public static String FIELD__DEFAULT04 = "DEFAULT04";
	public static String FIELD__DEFAULT05 = "DEFAULT05";
	public static String FIELD__DEFAULT06 = "DEFAULT06";
	public static String FIELD__DOWNLOAD = "DOWNLOAD";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_QUERY = "ID_QUERY";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LISTE_TITEL = "LISTE_TITEL";
	public static String FIELD__NAME = "NAME";
	public static String FIELD__PARAM01 = "PARAM01";
	public static String FIELD__PARAM02 = "PARAM02";
	public static String FIELD__PARAM03 = "PARAM03";
	public static String FIELD__PARAM04 = "PARAM04";
	public static String FIELD__PARAM05 = "PARAM05";
	public static String FIELD__PARAM06 = "PARAM06";
	public static String FIELD__PARAMDROPDOWNDEF01 = "PARAMDROPDOWNDEF01";
	public static String FIELD__PARAMDROPDOWNDEF02 = "PARAMDROPDOWNDEF02";
	public static String FIELD__PARAMDROPDOWNDEF03 = "PARAMDROPDOWNDEF03";
	public static String FIELD__PARAMDROPDOWNDEF04 = "PARAMDROPDOWNDEF04";
	public static String FIELD__PARAMDROPDOWNDEF05 = "PARAMDROPDOWNDEF05";
	public static String FIELD__PARAMDROPDOWNDEF06 = "PARAMDROPDOWNDEF06";
	public static String FIELD__SQLFELDLISTE = "SQLFELDLISTE";
	public static String FIELD__SQLFROMBLOCK = "SQLFROMBLOCK";
	public static String FIELD__SQLINDEXFELD = "SQLINDEXFELD";
	public static String FIELD__SQLORDERBLOCK = "SQLORDERBLOCK";
	public static String FIELD__SQLWHEREBLOCK = "SQLWHEREBLOCK";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_ANZEIGESQL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZEIGESQL");
	}

	public String get_ANZEIGESQL_cF() throws myException
	{
		return this.get_FormatedValue("ANZEIGESQL");	
	}

	public String get_ANZEIGESQL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZEIGESQL");
	}

	public String get_ANZEIGESQL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZEIGESQL",cNotNullValue);
	}

	public String get_ANZEIGESQL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZEIGESQL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZEIGESQL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZEIGESQL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZEIGESQL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZEIGESQL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZEIGESQL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZEIGESQL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGESQL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZEIGESQL", calNewValueFormated);
	}
		public boolean is_ANZEIGESQL_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ANZEIGESQL_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ANZEIGESQL_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ANZEIGESQL_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BESCHREIB1_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB1");
	}

	public String get_BESCHREIB1_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIB1");	
	}

	public String get_BESCHREIB1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIB1");
	}

	public String get_BESCHREIB1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB1",cNotNullValue);
	}

	public String get_BESCHREIB1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIB1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIB1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIB1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIB1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB1", calNewValueFormated);
	}
		public String get_BESCHREIB2_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB2");
	}

	public String get_BESCHREIB2_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIB2");	
	}

	public String get_BESCHREIB2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIB2");
	}

	public String get_BESCHREIB2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB2",cNotNullValue);
	}

	public String get_BESCHREIB2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIB2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIB2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIB2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIB2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB2", calNewValueFormated);
	}
		public String get_BESCHREIB3_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB3");
	}

	public String get_BESCHREIB3_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIB3");	
	}

	public String get_BESCHREIB3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIB3");
	}

	public String get_BESCHREIB3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB3",cNotNullValue);
	}

	public String get_BESCHREIB3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIB3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIB3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIB3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIB3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB3", calNewValueFormated);
	}
		public String get_BESCHREIB4_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB4");
	}

	public String get_BESCHREIB4_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIB4");	
	}

	public String get_BESCHREIB4_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIB4");
	}

	public String get_BESCHREIB4_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIB4",cNotNullValue);
	}

	public String get_BESCHREIB4_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIB4",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIB4", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIB4(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIB4", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIB4", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB4", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB4", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIB4_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIB4", calNewValueFormated);
	}
		public String get_DEFAULT01_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT01");
	}

	public String get_DEFAULT01_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT01");	
	}

	public String get_DEFAULT01_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT01");
	}

	public String get_DEFAULT01_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT01",cNotNullValue);
	}

	public String get_DEFAULT01_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT01",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT01", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT01(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT01", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT01", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT01", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT01", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT01_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT01", calNewValueFormated);
	}
		public String get_DEFAULT02_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT02");
	}

	public String get_DEFAULT02_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT02");	
	}

	public String get_DEFAULT02_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT02");
	}

	public String get_DEFAULT02_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT02",cNotNullValue);
	}

	public String get_DEFAULT02_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT02",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT02", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT02(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT02", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT02", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT02", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT02", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT02_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT02", calNewValueFormated);
	}
		public String get_DEFAULT03_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT03");
	}

	public String get_DEFAULT03_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT03");	
	}

	public String get_DEFAULT03_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT03");
	}

	public String get_DEFAULT03_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT03",cNotNullValue);
	}

	public String get_DEFAULT03_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT03",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT03", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT03(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT03", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT03", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT03", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT03", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT03_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT03", calNewValueFormated);
	}
		public String get_DEFAULT04_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT04");
	}

	public String get_DEFAULT04_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT04");	
	}

	public String get_DEFAULT04_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT04");
	}

	public String get_DEFAULT04_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT04",cNotNullValue);
	}

	public String get_DEFAULT04_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT04",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT04", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT04(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT04", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT04", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT04", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT04", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT04_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT04", calNewValueFormated);
	}
		public String get_DEFAULT05_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT05");
	}

	public String get_DEFAULT05_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT05");	
	}

	public String get_DEFAULT05_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT05");
	}

	public String get_DEFAULT05_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT05",cNotNullValue);
	}

	public String get_DEFAULT05_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT05",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT05", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT05(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT05", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT05", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT05", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT05", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT05_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT05", calNewValueFormated);
	}
		public String get_DEFAULT06_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT06");
	}

	public String get_DEFAULT06_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT06");	
	}

	public String get_DEFAULT06_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT06");
	}

	public String get_DEFAULT06_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT06",cNotNullValue);
	}

	public String get_DEFAULT06_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT06",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT06", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT06(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT06", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT06", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT06", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT06", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT06_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT06", calNewValueFormated);
	}
		public String get_DOWNLOAD_cUF() throws myException
	{
		return this.get_UnFormatedValue("DOWNLOAD");
	}

	public String get_DOWNLOAD_cF() throws myException
	{
		return this.get_FormatedValue("DOWNLOAD");	
	}

	public String get_DOWNLOAD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DOWNLOAD");
	}

	public String get_DOWNLOAD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DOWNLOAD",cNotNullValue);
	}

	public String get_DOWNLOAD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DOWNLOAD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DOWNLOAD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DOWNLOAD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DOWNLOAD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DOWNLOAD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOAD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOAD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOAD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOAD", calNewValueFormated);
	}
		public boolean is_DOWNLOAD_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DOWNLOAD_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DOWNLOAD_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DOWNLOAD_cUF_NN("N").equals("N"))
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
	
	
	public String get_ID_QUERY_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_QUERY");
	}

	public String get_ID_QUERY_cF() throws myException
	{
		return this.get_FormatedValue("ID_QUERY");	
	}

	public String get_ID_QUERY_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_QUERY");
	}

	public String get_ID_QUERY_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_QUERY",cNotNullValue);
	}

	public String get_ID_QUERY_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_QUERY",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_QUERY", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_QUERY(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_QUERY", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_QUERY", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_QUERY", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_QUERY", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_QUERY_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_QUERY", calNewValueFormated);
	}
		public Long get_ID_QUERY_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_QUERY").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_QUERY_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_QUERY").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_QUERY_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_QUERY").getDoubleValue();
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
	public String get_ID_QUERY_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_QUERY_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_QUERY_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_QUERY_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_QUERY_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_QUERY").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_QUERY_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_QUERY").getBigDecimalValue();
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
		public String get_LISTE_TITEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("LISTE_TITEL");
	}

	public String get_LISTE_TITEL_cF() throws myException
	{
		return this.get_FormatedValue("LISTE_TITEL");	
	}

	public String get_LISTE_TITEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LISTE_TITEL");
	}

	public String get_LISTE_TITEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LISTE_TITEL",cNotNullValue);
	}

	public String get_LISTE_TITEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LISTE_TITEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LISTE_TITEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LISTE_TITEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LISTE_TITEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LISTE_TITEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTE_TITEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTE_TITEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LISTE_TITEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LISTE_TITEL", calNewValueFormated);
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
		public String get_PARAM01_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM01");
	}

	public String get_PARAM01_cF() throws myException
	{
		return this.get_FormatedValue("PARAM01");	
	}

	public String get_PARAM01_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM01");
	}

	public String get_PARAM01_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM01",cNotNullValue);
	}

	public String get_PARAM01_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM01",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM01(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM01", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM01(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM01", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM01_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM01", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM01_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM01", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM01_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM01", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM01_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM01", calNewValueFormated);
	}
		public String get_PARAM02_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM02");
	}

	public String get_PARAM02_cF() throws myException
	{
		return this.get_FormatedValue("PARAM02");	
	}

	public String get_PARAM02_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM02");
	}

	public String get_PARAM02_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM02",cNotNullValue);
	}

	public String get_PARAM02_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM02",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM02(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM02", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM02(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM02", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM02_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM02", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM02_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM02", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM02_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM02", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM02_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM02", calNewValueFormated);
	}
		public String get_PARAM03_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM03");
	}

	public String get_PARAM03_cF() throws myException
	{
		return this.get_FormatedValue("PARAM03");	
	}

	public String get_PARAM03_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM03");
	}

	public String get_PARAM03_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM03",cNotNullValue);
	}

	public String get_PARAM03_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM03",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM03(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM03", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM03(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM03", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM03_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM03", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM03_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM03", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM03_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM03", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM03_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM03", calNewValueFormated);
	}
		public String get_PARAM04_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM04");
	}

	public String get_PARAM04_cF() throws myException
	{
		return this.get_FormatedValue("PARAM04");	
	}

	public String get_PARAM04_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM04");
	}

	public String get_PARAM04_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM04",cNotNullValue);
	}

	public String get_PARAM04_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM04",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM04(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM04", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM04(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM04", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM04_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM04", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM04_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM04", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM04_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM04", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM04_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM04", calNewValueFormated);
	}
		public String get_PARAM05_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM05");
	}

	public String get_PARAM05_cF() throws myException
	{
		return this.get_FormatedValue("PARAM05");	
	}

	public String get_PARAM05_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM05");
	}

	public String get_PARAM05_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM05",cNotNullValue);
	}

	public String get_PARAM05_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM05",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM05(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM05", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM05(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM05", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM05_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM05", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM05_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM05", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM05_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM05", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM05_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM05", calNewValueFormated);
	}
		public String get_PARAM06_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAM06");
	}

	public String get_PARAM06_cF() throws myException
	{
		return this.get_FormatedValue("PARAM06");	
	}

	public String get_PARAM06_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAM06");
	}

	public String get_PARAM06_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAM06",cNotNullValue);
	}

	public String get_PARAM06_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAM06",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAM06(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAM06", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAM06(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAM06", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM06_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAM06", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM06_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM06", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM06_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM06", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAM06_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAM06", calNewValueFormated);
	}
		public String get_PARAMDROPDOWNDEF01_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF01");
	}

	public String get_PARAMDROPDOWNDEF01_cF() throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF01");	
	}

	public String get_PARAMDROPDOWNDEF01_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMDROPDOWNDEF01");
	}

	public String get_PARAMDROPDOWNDEF01_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF01",cNotNullValue);
	}

	public String get_PARAMDROPDOWNDEF01_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF01",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMDROPDOWNDEF01", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF01(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMDROPDOWNDEF01", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF01_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF01", calNewValueFormated);
	}
		public String get_PARAMDROPDOWNDEF02_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF02");
	}

	public String get_PARAMDROPDOWNDEF02_cF() throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF02");	
	}

	public String get_PARAMDROPDOWNDEF02_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMDROPDOWNDEF02");
	}

	public String get_PARAMDROPDOWNDEF02_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF02",cNotNullValue);
	}

	public String get_PARAMDROPDOWNDEF02_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF02",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMDROPDOWNDEF02", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF02(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMDROPDOWNDEF02", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF02_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF02", calNewValueFormated);
	}
		public String get_PARAMDROPDOWNDEF03_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF03");
	}

	public String get_PARAMDROPDOWNDEF03_cF() throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF03");	
	}

	public String get_PARAMDROPDOWNDEF03_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMDROPDOWNDEF03");
	}

	public String get_PARAMDROPDOWNDEF03_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF03",cNotNullValue);
	}

	public String get_PARAMDROPDOWNDEF03_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF03",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMDROPDOWNDEF03", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF03(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMDROPDOWNDEF03", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF03_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF03", calNewValueFormated);
	}
		public String get_PARAMDROPDOWNDEF04_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF04");
	}

	public String get_PARAMDROPDOWNDEF04_cF() throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF04");	
	}

	public String get_PARAMDROPDOWNDEF04_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMDROPDOWNDEF04");
	}

	public String get_PARAMDROPDOWNDEF04_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF04",cNotNullValue);
	}

	public String get_PARAMDROPDOWNDEF04_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF04",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMDROPDOWNDEF04", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF04(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMDROPDOWNDEF04", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF04_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF04", calNewValueFormated);
	}
		public String get_PARAMDROPDOWNDEF05_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF05");
	}

	public String get_PARAMDROPDOWNDEF05_cF() throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF05");	
	}

	public String get_PARAMDROPDOWNDEF05_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMDROPDOWNDEF05");
	}

	public String get_PARAMDROPDOWNDEF05_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF05",cNotNullValue);
	}

	public String get_PARAMDROPDOWNDEF05_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF05",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMDROPDOWNDEF05", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF05(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMDROPDOWNDEF05", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF05_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF05", calNewValueFormated);
	}
		public String get_PARAMDROPDOWNDEF06_cUF() throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF06");
	}

	public String get_PARAMDROPDOWNDEF06_cF() throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF06");	
	}

	public String get_PARAMDROPDOWNDEF06_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PARAMDROPDOWNDEF06");
	}

	public String get_PARAMDROPDOWNDEF06_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PARAMDROPDOWNDEF06",cNotNullValue);
	}

	public String get_PARAMDROPDOWNDEF06_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PARAMDROPDOWNDEF06",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PARAMDROPDOWNDEF06", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PARAMDROPDOWNDEF06(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PARAMDROPDOWNDEF06", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PARAMDROPDOWNDEF06_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PARAMDROPDOWNDEF06", calNewValueFormated);
	}
		public String get_SQLFELDLISTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQLFELDLISTE");
	}

	public String get_SQLFELDLISTE_cF() throws myException
	{
		return this.get_FormatedValue("SQLFELDLISTE");	
	}

	public String get_SQLFELDLISTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQLFELDLISTE");
	}

	public String get_SQLFELDLISTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQLFELDLISTE",cNotNullValue);
	}

	public String get_SQLFELDLISTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQLFELDLISTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQLFELDLISTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQLFELDLISTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQLFELDLISTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQLFELDLISTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLFELDLISTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLFELDLISTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFELDLISTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLFELDLISTE", calNewValueFormated);
	}
		public String get_SQLFROMBLOCK_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQLFROMBLOCK");
	}

	public String get_SQLFROMBLOCK_cF() throws myException
	{
		return this.get_FormatedValue("SQLFROMBLOCK");	
	}

	public String get_SQLFROMBLOCK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQLFROMBLOCK");
	}

	public String get_SQLFROMBLOCK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQLFROMBLOCK",cNotNullValue);
	}

	public String get_SQLFROMBLOCK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQLFROMBLOCK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQLFROMBLOCK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQLFROMBLOCK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQLFROMBLOCK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLFROMBLOCK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLFROMBLOCK", calNewValueFormated);
	}
		public String get_SQLINDEXFELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQLINDEXFELD");
	}

	public String get_SQLINDEXFELD_cF() throws myException
	{
		return this.get_FormatedValue("SQLINDEXFELD");	
	}

	public String get_SQLINDEXFELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQLINDEXFELD");
	}

	public String get_SQLINDEXFELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQLINDEXFELD",cNotNullValue);
	}

	public String get_SQLINDEXFELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQLINDEXFELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQLINDEXFELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQLINDEXFELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQLINDEXFELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQLINDEXFELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLINDEXFELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLINDEXFELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLINDEXFELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLINDEXFELD", calNewValueFormated);
	}
		public String get_SQLORDERBLOCK_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQLORDERBLOCK");
	}

	public String get_SQLORDERBLOCK_cF() throws myException
	{
		return this.get_FormatedValue("SQLORDERBLOCK");	
	}

	public String get_SQLORDERBLOCK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQLORDERBLOCK");
	}

	public String get_SQLORDERBLOCK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQLORDERBLOCK",cNotNullValue);
	}

	public String get_SQLORDERBLOCK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQLORDERBLOCK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQLORDERBLOCK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQLORDERBLOCK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQLORDERBLOCK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLORDERBLOCK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLORDERBLOCK", calNewValueFormated);
	}
		public String get_SQLWHEREBLOCK_cUF() throws myException
	{
		return this.get_UnFormatedValue("SQLWHEREBLOCK");
	}

	public String get_SQLWHEREBLOCK_cF() throws myException
	{
		return this.get_FormatedValue("SQLWHEREBLOCK");	
	}

	public String get_SQLWHEREBLOCK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SQLWHEREBLOCK");
	}

	public String get_SQLWHEREBLOCK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SQLWHEREBLOCK",cNotNullValue);
	}

	public String get_SQLWHEREBLOCK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SQLWHEREBLOCK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SQLWHEREBLOCK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SQLWHEREBLOCK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SQLWHEREBLOCK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SQLWHEREBLOCK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SQLWHEREBLOCK", calNewValueFormated);
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
	put("ANZEIGESQL",MyRECORD.DATATYPES.YESNO);
	put("BESCHREIB1",MyRECORD.DATATYPES.TEXT);
	put("BESCHREIB2",MyRECORD.DATATYPES.TEXT);
	put("BESCHREIB3",MyRECORD.DATATYPES.TEXT);
	put("BESCHREIB4",MyRECORD.DATATYPES.TEXT);
	put("DEFAULT01",MyRECORD.DATATYPES.TEXT);
	put("DEFAULT02",MyRECORD.DATATYPES.TEXT);
	put("DEFAULT03",MyRECORD.DATATYPES.TEXT);
	put("DEFAULT04",MyRECORD.DATATYPES.TEXT);
	put("DEFAULT05",MyRECORD.DATATYPES.TEXT);
	put("DEFAULT06",MyRECORD.DATATYPES.TEXT);
	put("DOWNLOAD",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_QUERY",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LISTE_TITEL",MyRECORD.DATATYPES.TEXT);
	put("NAME",MyRECORD.DATATYPES.TEXT);
	put("PARAM01",MyRECORD.DATATYPES.TEXT);
	put("PARAM02",MyRECORD.DATATYPES.TEXT);
	put("PARAM03",MyRECORD.DATATYPES.TEXT);
	put("PARAM04",MyRECORD.DATATYPES.TEXT);
	put("PARAM05",MyRECORD.DATATYPES.TEXT);
	put("PARAM06",MyRECORD.DATATYPES.TEXT);
	put("PARAMDROPDOWNDEF01",MyRECORD.DATATYPES.TEXT);
	put("PARAMDROPDOWNDEF02",MyRECORD.DATATYPES.TEXT);
	put("PARAMDROPDOWNDEF03",MyRECORD.DATATYPES.TEXT);
	put("PARAMDROPDOWNDEF04",MyRECORD.DATATYPES.TEXT);
	put("PARAMDROPDOWNDEF05",MyRECORD.DATATYPES.TEXT);
	put("PARAMDROPDOWNDEF06",MyRECORD.DATATYPES.TEXT);
	put("SQLFELDLISTE",MyRECORD.DATATYPES.TEXT);
	put("SQLFROMBLOCK",MyRECORD.DATATYPES.TEXT);
	put("SQLINDEXFELD",MyRECORD.DATATYPES.TEXT);
	put("SQLORDERBLOCK",MyRECORD.DATATYPES.TEXT);
	put("SQLWHEREBLOCK",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_QUERY.HM_FIELDS;	
	}

}
