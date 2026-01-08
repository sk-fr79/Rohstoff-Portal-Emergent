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

public class RECORD_MAHNUNG extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_MAHNUNG";
    public static String IDFIELD   = "ID_MAHNUNG";
    

	//erzeugen eines RECORDNEW_JT_MAHNUNG - felds
	private RECORDNEW_MAHNUNG   recNEW = null;

    private _TAB  tab = _TAB.mahnung;  



	public RECORD_MAHNUNG() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_MAHNUNG");
	}


	public RECORD_MAHNUNG(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MAHNUNG");
	}



	public RECORD_MAHNUNG(RECORD_MAHNUNG recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MAHNUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAHNUNG.TABLENAME);
	}


	//2014-04-03
	public RECORD_MAHNUNG(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MAHNUNG");
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAHNUNG.TABLENAME);
	}




	public RECORD_MAHNUNG(long lID_Unformated) throws myException
	{
		super("JT_MAHNUNG","ID_MAHNUNG",""+lID_Unformated);
		this.set_TABLE_NAME("JT_MAHNUNG");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAHNUNG.TABLENAME);
	}

	public RECORD_MAHNUNG(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_MAHNUNG");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAHNUNG", "ID_MAHNUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAHNUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAHNUNG.TABLENAME);
	}
	
	

	public RECORD_MAHNUNG(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_MAHNUNG","ID_MAHNUNG",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_MAHNUNG");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAHNUNG.TABLENAME);

	}


	public RECORD_MAHNUNG(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MAHNUNG");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAHNUNG", "ID_MAHNUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAHNUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAHNUNG.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_MAHNUNG();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_MAHNUNG.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_MAHNUNG";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_MAHNUNG_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_MAHNUNG_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MAHNUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MAHNUNG", bibE2.cTO(), "ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MAHNUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MAHNUNG", bibE2.cTO(), "ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF();
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
			if (S.isFull(this.get_ID_MAHNUNG_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAHNUNG", "ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_MAHNUNG get_RECORDNEW_MAHNUNG() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_MAHNUNG();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_MAHNUNG(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_MAHNUNG create_Instance() throws myException {
		return new RECORD_MAHNUNG();
	}
	
	public static RECORD_MAHNUNG create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_MAHNUNG(Conn);
    }

	public static RECORD_MAHNUNG create_Instance(RECORD_MAHNUNG recordOrig) {
		return new RECORD_MAHNUNG(recordOrig);
    }

	public static RECORD_MAHNUNG create_Instance(long lID_Unformated) throws myException {
		return new RECORD_MAHNUNG(lID_Unformated);
    }

	public static RECORD_MAHNUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_MAHNUNG(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_MAHNUNG create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_MAHNUNG(lID_Unformated, Conn);
	}

	public static RECORD_MAHNUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_MAHNUNG(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_MAHNUNG create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_MAHNUNG(recordOrig);	    
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
    public RECORD_MAHNUNG build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG WHERE ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF());
      }
      //return new RECORD_MAHNUNG(this.get_cSQL_4_Build());
      RECORD_MAHNUNG rec = new RECORD_MAHNUNG();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_MAHNUNG
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_MAHNUNG-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_MAHNUNG get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_MAHNUNG_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_MAHNUNG  recNew = new RECORDNEW_MAHNUNG();
		
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
    public RECORD_MAHNUNG set_recordNew(RECORDNEW_MAHNUNG recnew) throws myException {
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
	
	



		private RECORD_USER UP_RECORD_USER_id_user_sachbearbeiter_1 = null;




		private RECORD_USER UP_RECORD_USER_id_user_sachbearbeiter_2 = null;




		private RECORD_USER UP_RECORD_USER_id_user_sachbearbeiter_3 = null;




		private RECLIST_FIBU_MAHNUNG DOWN_RECLIST_FIBU_MAHNUNG_id_mahnung = null ;




		private RECLIST_MAHNUNG_POS DOWN_RECLIST_MAHNUNG_POS_id_mahnung = null ;






	
	/**
	 * References the Field ID_USER_SACHBEARBEITER_1
	 * Falls keine this.get_ID_USER_SACHBEARBEITER_1_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_sachbearbeiter_1() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_SACHBEARBEITER_1_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_sachbearbeiter_1==null)
		{
			this.UP_RECORD_USER_id_user_sachbearbeiter_1 = new RECORD_USER(this.get_ID_USER_SACHBEARBEITER_1_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_sachbearbeiter_1;
	}
	





	
	/**
	 * References the Field ID_USER_SACHBEARBEITER_2
	 * Falls keine this.get_ID_USER_SACHBEARBEITER_2_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_sachbearbeiter_2() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_SACHBEARBEITER_2_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_sachbearbeiter_2==null)
		{
			this.UP_RECORD_USER_id_user_sachbearbeiter_2 = new RECORD_USER(this.get_ID_USER_SACHBEARBEITER_2_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_sachbearbeiter_2;
	}
	





	
	/**
	 * References the Field ID_USER_SACHBEARBEITER_3
	 * Falls keine this.get_ID_USER_SACHBEARBEITER_3_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_sachbearbeiter_3() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_SACHBEARBEITER_3_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_sachbearbeiter_3==null)
		{
			this.UP_RECORD_USER_id_user_sachbearbeiter_3 = new RECORD_USER(this.get_ID_USER_SACHBEARBEITER_3_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_sachbearbeiter_3;
	}
	





	/**
	 * References the Field ID_MAHNUNG 
	 * Falls keine get_ID_MAHNUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_MAHNUNG get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung() throws myException
	{
		if (S.isEmpty(this.get_ID_MAHNUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_MAHNUNG_id_mahnung==null)
		{
			this.DOWN_RECLIST_FIBU_MAHNUNG_id_mahnung = new RECLIST_FIBU_MAHNUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_MAHNUNG WHERE ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF()+" ORDER BY ID_FIBU_MAHNUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_MAHNUNG_id_mahnung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MAHNUNG 
	 * Falls keine get_ID_MAHNUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_MAHNUNG get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MAHNUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_MAHNUNG_id_mahnung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_MAHNUNG WHERE ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_MAHNUNG_id_mahnung = new RECLIST_FIBU_MAHNUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_MAHNUNG_id_mahnung;
	}


	





	/**
	 * References the Field ID_MAHNUNG 
	 * Falls keine get_ID_MAHNUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG_POS get_DOWN_RECORD_LIST_MAHNUNG_POS_id_mahnung() throws myException
	{
		if (S.isEmpty(this.get_ID_MAHNUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_POS_id_mahnung==null)
		{
			this.DOWN_RECLIST_MAHNUNG_POS_id_mahnung = new RECLIST_MAHNUNG_POS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG_POS WHERE ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF()+" ORDER BY ID_MAHNUNG_POS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_POS_id_mahnung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MAHNUNG 
	 * Falls keine get_ID_MAHNUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAHNUNG_POS get_DOWN_RECORD_LIST_MAHNUNG_POS_id_mahnung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MAHNUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAHNUNG_POS_id_mahnung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAHNUNG_POS WHERE ID_MAHNUNG="+this.get_ID_MAHNUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAHNUNG_POS_id_mahnung = new RECLIST_MAHNUNG_POS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAHNUNG_POS_id_mahnung;
	}


	

	public static String FIELD__DATUM_FRIST = "DATUM_FRIST";
	public static String FIELD__DATUM_MAHNUNG = "DATUM_MAHNUNG";
	public static String FIELD__DATUM_ZAHLUNGEN_GEBUCHT = "DATUM_ZAHLUNGEN_GEBUCHT";
	public static String FIELD__EMAIL1_MAHNUNG = "EMAIL1_MAHNUNG";
	public static String FIELD__EMAIL2_MAHNUNG = "EMAIL2_MAHNUNG";
	public static String FIELD__EMAIL3_MAHNUNG = "EMAIL3_MAHNUNG";
	public static String FIELD__EMAIL4_MAHNUNG = "EMAIL4_MAHNUNG";
	public static String FIELD__EMAIL5_MAHNUNG = "EMAIL5_MAHNUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FAXNUMMER_MAHNUNG = "FAXNUMMER_MAHNUNG";
	public static String FIELD__FRIST_IN_TAGEN = "FRIST_IN_TAGEN";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MAHNUNG = "ID_MAHNUNG";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_USER_SACHBEARBEITER_1 = "ID_USER_SACHBEARBEITER_1";
	public static String FIELD__ID_USER_SACHBEARBEITER_2 = "ID_USER_SACHBEARBEITER_2";
	public static String FIELD__ID_USER_SACHBEARBEITER_3 = "ID_USER_SACHBEARBEITER_3";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MAHNGEBUEHR_BETRAG = "MAHNGEBUEHR_BETRAG";
	public static String FIELD__MAHNGEBUEHR_PROZ = "MAHNGEBUEHR_PROZ";
	public static String FIELD__MAHNSALDO_GESAMT = "MAHNSALDO_GESAMT";
	public static String FIELD__MAHNSTUFE = "MAHNSTUFE";
	public static String FIELD__MAHNTEXT_AUSLEITUNG = "MAHNTEXT_AUSLEITUNG";
	public static String FIELD__MAHNTEXT_EINLEITUNG = "MAHNTEXT_EINLEITUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_DATUM_FRIST_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_FRIST");
	}

	public String get_DATUM_FRIST_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_FRIST");	
	}

	public String get_DATUM_FRIST_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_FRIST");
	}

	public String get_DATUM_FRIST_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_FRIST",cNotNullValue);
	}

	public String get_DATUM_FRIST_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_FRIST",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_FRIST", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_FRIST(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_FRIST", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_FRIST", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_FRIST", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_FRIST", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_FRIST_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_FRIST", calNewValueFormated);
	}
		public String get_DATUM_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_MAHNUNG");
	}

	public String get_DATUM_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_MAHNUNG");	
	}

	public String get_DATUM_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_MAHNUNG");
	}

	public String get_DATUM_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_MAHNUNG",cNotNullValue);
	}

	public String get_DATUM_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_MAHNUNG", calNewValueFormated);
	}
		public String get_DATUM_ZAHLUNGEN_GEBUCHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ZAHLUNGEN_GEBUCHT");
	}

	public String get_DATUM_ZAHLUNGEN_GEBUCHT_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ZAHLUNGEN_GEBUCHT");	
	}

	public String get_DATUM_ZAHLUNGEN_GEBUCHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ZAHLUNGEN_GEBUCHT");
	}

	public String get_DATUM_ZAHLUNGEN_GEBUCHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ZAHLUNGEN_GEBUCHT",cNotNullValue);
	}

	public String get_DATUM_ZAHLUNGEN_GEBUCHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ZAHLUNGEN_GEBUCHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ZAHLUNGEN_GEBUCHT", calNewValueFormated);
	}
		public String get_EMAIL1_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL1_MAHNUNG");
	}

	public String get_EMAIL1_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL1_MAHNUNG");	
	}

	public String get_EMAIL1_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL1_MAHNUNG");
	}

	public String get_EMAIL1_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL1_MAHNUNG",cNotNullValue);
	}

	public String get_EMAIL1_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL1_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL1_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL1_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL1_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL1_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL1_MAHNUNG", calNewValueFormated);
	}
		public String get_EMAIL2_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL2_MAHNUNG");
	}

	public String get_EMAIL2_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL2_MAHNUNG");	
	}

	public String get_EMAIL2_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL2_MAHNUNG");
	}

	public String get_EMAIL2_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL2_MAHNUNG",cNotNullValue);
	}

	public String get_EMAIL2_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL2_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL2_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL2_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL2_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL2_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL2_MAHNUNG", calNewValueFormated);
	}
		public String get_EMAIL3_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL3_MAHNUNG");
	}

	public String get_EMAIL3_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL3_MAHNUNG");	
	}

	public String get_EMAIL3_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL3_MAHNUNG");
	}

	public String get_EMAIL3_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL3_MAHNUNG",cNotNullValue);
	}

	public String get_EMAIL3_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL3_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL3_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL3_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL3_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL3_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL3_MAHNUNG", calNewValueFormated);
	}
		public String get_EMAIL4_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL4_MAHNUNG");
	}

	public String get_EMAIL4_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL4_MAHNUNG");	
	}

	public String get_EMAIL4_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL4_MAHNUNG");
	}

	public String get_EMAIL4_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL4_MAHNUNG",cNotNullValue);
	}

	public String get_EMAIL4_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL4_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL4_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL4_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL4_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL4_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL4_MAHNUNG", calNewValueFormated);
	}
		public String get_EMAIL5_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL5_MAHNUNG");
	}

	public String get_EMAIL5_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL5_MAHNUNG");	
	}

	public String get_EMAIL5_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL5_MAHNUNG");
	}

	public String get_EMAIL5_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL5_MAHNUNG",cNotNullValue);
	}

	public String get_EMAIL5_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL5_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL5_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL5_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL5_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL5_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL5_MAHNUNG", calNewValueFormated);
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
		public String get_FAXNUMMER_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAXNUMMER_MAHNUNG");
	}

	public String get_FAXNUMMER_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("FAXNUMMER_MAHNUNG");	
	}

	public String get_FAXNUMMER_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAXNUMMER_MAHNUNG");
	}

	public String get_FAXNUMMER_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAXNUMMER_MAHNUNG",cNotNullValue);
	}

	public String get_FAXNUMMER_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAXNUMMER_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAXNUMMER_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAXNUMMER_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAXNUMMER_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAXNUMMER_MAHNUNG", calNewValueFormated);
	}
		public String get_FRIST_IN_TAGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FRIST_IN_TAGEN");
	}

	public String get_FRIST_IN_TAGEN_cF() throws myException
	{
		return this.get_FormatedValue("FRIST_IN_TAGEN");	
	}

	public String get_FRIST_IN_TAGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FRIST_IN_TAGEN");
	}

	public String get_FRIST_IN_TAGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FRIST_IN_TAGEN",cNotNullValue);
	}

	public String get_FRIST_IN_TAGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FRIST_IN_TAGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FRIST_IN_TAGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FRIST_IN_TAGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FRIST_IN_TAGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FRIST_IN_TAGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FRIST_IN_TAGEN", calNewValueFormated);
	}
		public Long get_FRIST_IN_TAGEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FRIST_IN_TAGEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FRIST_IN_TAGEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FRIST_IN_TAGEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FRIST_IN_TAGEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FRIST_IN_TAGEN").getDoubleValue();
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
	public String get_FRIST_IN_TAGEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FRIST_IN_TAGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_FRIST_IN_TAGEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FRIST_IN_TAGEN_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_FRIST_IN_TAGEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FRIST_IN_TAGEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FRIST_IN_TAGEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FRIST_IN_TAGEN").getBigDecimalValue();
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
		public String get_ID_MAHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MAHNUNG");
	}

	public String get_ID_MAHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_MAHNUNG");	
	}

	public String get_ID_MAHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MAHNUNG");
	}

	public String get_ID_MAHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MAHNUNG",cNotNullValue);
	}

	public String get_ID_MAHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MAHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MAHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MAHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MAHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MAHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAHNUNG", calNewValueFormated);
	}
		public Long get_ID_MAHNUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MAHNUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MAHNUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MAHNUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MAHNUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MAHNUNG").getDoubleValue();
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
	public String get_ID_MAHNUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAHNUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_MAHNUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAHNUNG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_MAHNUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAHNUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MAHNUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAHNUNG").getBigDecimalValue();
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
	
	
	public String get_ID_USER_SACHBEARBEITER_1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER_1");
	}

	public String get_ID_USER_SACHBEARBEITER_1_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER_1");	
	}

	public String get_ID_USER_SACHBEARBEITER_1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_SACHBEARBEITER_1");
	}

	public String get_ID_USER_SACHBEARBEITER_1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER_1",cNotNullValue);
	}

	public String get_ID_USER_SACHBEARBEITER_1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER_1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER_1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_1", calNewValueFormated);
	}
		public Long get_ID_USER_SACHBEARBEITER_1_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_SACHBEARBEITER_1").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_SACHBEARBEITER_1_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER_1").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_SACHBEARBEITER_1_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER_1").getDoubleValue();
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
	public String get_ID_USER_SACHBEARBEITER_1_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_SACHBEARBEITER_1_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_1_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_SACHBEARBEITER_1_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER_1").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_SACHBEARBEITER_1_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER_1").getBigDecimalValue();
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
	
	
	public String get_ID_USER_SACHBEARBEITER_2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER_2");
	}

	public String get_ID_USER_SACHBEARBEITER_2_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER_2");	
	}

	public String get_ID_USER_SACHBEARBEITER_2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_SACHBEARBEITER_2");
	}

	public String get_ID_USER_SACHBEARBEITER_2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER_2",cNotNullValue);
	}

	public String get_ID_USER_SACHBEARBEITER_2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER_2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER_2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_2", calNewValueFormated);
	}
		public Long get_ID_USER_SACHBEARBEITER_2_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_SACHBEARBEITER_2").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_SACHBEARBEITER_2_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER_2").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_SACHBEARBEITER_2_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER_2").getDoubleValue();
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
	public String get_ID_USER_SACHBEARBEITER_2_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_SACHBEARBEITER_2_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_2_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_SACHBEARBEITER_2_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER_2").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_SACHBEARBEITER_2_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER_2").getBigDecimalValue();
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
	
	
	public String get_ID_USER_SACHBEARBEITER_3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER_3");
	}

	public String get_ID_USER_SACHBEARBEITER_3_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER_3");	
	}

	public String get_ID_USER_SACHBEARBEITER_3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_SACHBEARBEITER_3");
	}

	public String get_ID_USER_SACHBEARBEITER_3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARBEITER_3",cNotNullValue);
	}

	public String get_ID_USER_SACHBEARBEITER_3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARBEITER_3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARBEITER_3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARBEITER_3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARBEITER_3", calNewValueFormated);
	}
		public Long get_ID_USER_SACHBEARBEITER_3_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_SACHBEARBEITER_3").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_SACHBEARBEITER_3_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER_3").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_SACHBEARBEITER_3_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARBEITER_3").getDoubleValue();
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
	public String get_ID_USER_SACHBEARBEITER_3_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_3_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_SACHBEARBEITER_3_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARBEITER_3_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_SACHBEARBEITER_3_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER_3").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_SACHBEARBEITER_3_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARBEITER_3").getBigDecimalValue();
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
		public String get_MAHNGEBUEHR_BETRAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAHNGEBUEHR_BETRAG");
	}

	public String get_MAHNGEBUEHR_BETRAG_cF() throws myException
	{
		return this.get_FormatedValue("MAHNGEBUEHR_BETRAG");	
	}

	public String get_MAHNGEBUEHR_BETRAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAHNGEBUEHR_BETRAG");
	}

	public String get_MAHNGEBUEHR_BETRAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAHNGEBUEHR_BETRAG",cNotNullValue);
	}

	public String get_MAHNGEBUEHR_BETRAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAHNGEBUEHR_BETRAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAHNGEBUEHR_BETRAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAHNGEBUEHR_BETRAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_BETRAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_BETRAG", calNewValueFormated);
	}
		public Double get_MAHNGEBUEHR_BETRAG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MAHNGEBUEHR_BETRAG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MAHNGEBUEHR_BETRAG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MAHNGEBUEHR_BETRAG").getDoubleValue();
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
	public String get_MAHNGEBUEHR_BETRAG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNGEBUEHR_BETRAG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_MAHNGEBUEHR_BETRAG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNGEBUEHR_BETRAG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_MAHNGEBUEHR_BETRAG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNGEBUEHR_BETRAG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MAHNGEBUEHR_BETRAG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNGEBUEHR_BETRAG").getBigDecimalValue();
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
	
	
	public String get_MAHNGEBUEHR_PROZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAHNGEBUEHR_PROZ");
	}

	public String get_MAHNGEBUEHR_PROZ_cF() throws myException
	{
		return this.get_FormatedValue("MAHNGEBUEHR_PROZ");	
	}

	public String get_MAHNGEBUEHR_PROZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAHNGEBUEHR_PROZ");
	}

	public String get_MAHNGEBUEHR_PROZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAHNGEBUEHR_PROZ",cNotNullValue);
	}

	public String get_MAHNGEBUEHR_PROZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAHNGEBUEHR_PROZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAHNGEBUEHR_PROZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAHNGEBUEHR_PROZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNGEBUEHR_PROZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNGEBUEHR_PROZ", calNewValueFormated);
	}
		public Double get_MAHNGEBUEHR_PROZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MAHNGEBUEHR_PROZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MAHNGEBUEHR_PROZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MAHNGEBUEHR_PROZ").getDoubleValue();
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
	public String get_MAHNGEBUEHR_PROZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNGEBUEHR_PROZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_MAHNGEBUEHR_PROZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNGEBUEHR_PROZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_MAHNGEBUEHR_PROZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNGEBUEHR_PROZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MAHNGEBUEHR_PROZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNGEBUEHR_PROZ").getBigDecimalValue();
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
	
	
	public String get_MAHNSALDO_GESAMT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAHNSALDO_GESAMT");
	}

	public String get_MAHNSALDO_GESAMT_cF() throws myException
	{
		return this.get_FormatedValue("MAHNSALDO_GESAMT");	
	}

	public String get_MAHNSALDO_GESAMT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAHNSALDO_GESAMT");
	}

	public String get_MAHNSALDO_GESAMT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAHNSALDO_GESAMT",cNotNullValue);
	}

	public String get_MAHNSALDO_GESAMT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAHNSALDO_GESAMT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAHNSALDO_GESAMT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAHNSALDO_GESAMT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAHNSALDO_GESAMT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSALDO_GESAMT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNSALDO_GESAMT", calNewValueFormated);
	}
		public Double get_MAHNSALDO_GESAMT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MAHNSALDO_GESAMT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MAHNSALDO_GESAMT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MAHNSALDO_GESAMT").getDoubleValue();
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
	public String get_MAHNSALDO_GESAMT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNSALDO_GESAMT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_MAHNSALDO_GESAMT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNSALDO_GESAMT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_MAHNSALDO_GESAMT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNSALDO_GESAMT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MAHNSALDO_GESAMT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNSALDO_GESAMT").getBigDecimalValue();
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
	
	
	public String get_MAHNSTUFE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAHNSTUFE");
	}

	public String get_MAHNSTUFE_cF() throws myException
	{
		return this.get_FormatedValue("MAHNSTUFE");	
	}

	public String get_MAHNSTUFE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAHNSTUFE");
	}

	public String get_MAHNSTUFE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAHNSTUFE",cNotNullValue);
	}

	public String get_MAHNSTUFE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAHNSTUFE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAHNSTUFE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAHNSTUFE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAHNSTUFE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAHNSTUFE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNSTUFE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNSTUFE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNSTUFE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNSTUFE", calNewValueFormated);
	}
		public Long get_MAHNSTUFE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MAHNSTUFE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MAHNSTUFE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MAHNSTUFE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MAHNSTUFE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MAHNSTUFE").getDoubleValue();
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
	public String get_MAHNSTUFE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNSTUFE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_MAHNSTUFE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MAHNSTUFE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_MAHNSTUFE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNSTUFE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MAHNSTUFE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MAHNSTUFE").getBigDecimalValue();
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
	
	
	public String get_MAHNTEXT_AUSLEITUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAHNTEXT_AUSLEITUNG");
	}

	public String get_MAHNTEXT_AUSLEITUNG_cF() throws myException
	{
		return this.get_FormatedValue("MAHNTEXT_AUSLEITUNG");	
	}

	public String get_MAHNTEXT_AUSLEITUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAHNTEXT_AUSLEITUNG");
	}

	public String get_MAHNTEXT_AUSLEITUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAHNTEXT_AUSLEITUNG",cNotNullValue);
	}

	public String get_MAHNTEXT_AUSLEITUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAHNTEXT_AUSLEITUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAHNTEXT_AUSLEITUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_AUSLEITUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNTEXT_AUSLEITUNG", calNewValueFormated);
	}
		public String get_MAHNTEXT_EINLEITUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAHNTEXT_EINLEITUNG");
	}

	public String get_MAHNTEXT_EINLEITUNG_cF() throws myException
	{
		return this.get_FormatedValue("MAHNTEXT_EINLEITUNG");	
	}

	public String get_MAHNTEXT_EINLEITUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAHNTEXT_EINLEITUNG");
	}

	public String get_MAHNTEXT_EINLEITUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAHNTEXT_EINLEITUNG",cNotNullValue);
	}

	public String get_MAHNTEXT_EINLEITUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAHNTEXT_EINLEITUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAHNTEXT_EINLEITUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAHNTEXT_EINLEITUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAHNTEXT_EINLEITUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAHNTEXT_EINLEITUNG", calNewValueFormated);
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
	put("DATUM_FRIST",MyRECORD.DATATYPES.DATE);
	put("DATUM_MAHNUNG",MyRECORD.DATATYPES.DATE);
	put("DATUM_ZAHLUNGEN_GEBUCHT",MyRECORD.DATATYPES.DATE);
	put("EMAIL1_MAHNUNG",MyRECORD.DATATYPES.TEXT);
	put("EMAIL2_MAHNUNG",MyRECORD.DATATYPES.TEXT);
	put("EMAIL3_MAHNUNG",MyRECORD.DATATYPES.TEXT);
	put("EMAIL4_MAHNUNG",MyRECORD.DATATYPES.TEXT);
	put("EMAIL5_MAHNUNG",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FAXNUMMER_MAHNUNG",MyRECORD.DATATYPES.TEXT);
	put("FRIST_IN_TAGEN",MyRECORD.DATATYPES.NUMBER);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MAHNUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_SACHBEARBEITER_1",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_SACHBEARBEITER_2",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_SACHBEARBEITER_3",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MAHNGEBUEHR_BETRAG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MAHNGEBUEHR_PROZ",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MAHNSALDO_GESAMT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MAHNSTUFE",MyRECORD.DATATYPES.NUMBER);
	put("MAHNTEXT_AUSLEITUNG",MyRECORD.DATATYPES.TEXT);
	put("MAHNTEXT_EINLEITUNG",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_MAHNUNG.HM_FIELDS;	
	}

}
