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

public class RECORD_AH7_PROFIL extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_AH7_PROFIL";
    public static String IDFIELD   = "ID_AH7_PROFIL";
    

	//erzeugen eines RECORDNEW_JT_AH7_PROFIL - felds
	private RECORDNEW_AH7_PROFIL   recNEW = null;

    private _TAB  tab = _TAB.ah7_profil;  



	public RECORD_AH7_PROFIL() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_AH7_PROFIL");
	}


	public RECORD_AH7_PROFIL(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_AH7_PROFIL");
	}



	public RECORD_AH7_PROFIL(RECORD_AH7_PROFIL recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_AH7_PROFIL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_PROFIL.TABLENAME);
	}


	//2014-04-03
	public RECORD_AH7_PROFIL(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_AH7_PROFIL");
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_PROFIL.TABLENAME);
	}




	public RECORD_AH7_PROFIL(long lID_Unformated) throws myException
	{
		super("JT_AH7_PROFIL","ID_AH7_PROFIL",""+lID_Unformated);
		this.set_TABLE_NAME("JT_AH7_PROFIL");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_PROFIL.TABLENAME);
	}

	public RECORD_AH7_PROFIL(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_AH7_PROFIL");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_PROFIL", "ID_AH7_PROFIL="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_PROFIL", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_PROFIL.TABLENAME);
	}
	
	

	public RECORD_AH7_PROFIL(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_AH7_PROFIL","ID_AH7_PROFIL",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_AH7_PROFIL");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_PROFIL.TABLENAME);

	}


	public RECORD_AH7_PROFIL(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_AH7_PROFIL");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_PROFIL", "ID_AH7_PROFIL="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_PROFIL", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_AH7_PROFIL.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_AH7_PROFIL();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_AH7_PROFIL.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_AH7_PROFIL";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_AH7_PROFIL_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_AH7_PROFIL_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_AH7_PROFIL was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_AH7_PROFIL", bibE2.cTO(), "ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_AH7_PROFIL was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_AH7_PROFIL", bibE2.cTO(), "ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_AH7_PROFIL WHERE ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_AH7_PROFIL WHERE ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF();
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
			if (S.isFull(this.get_ID_AH7_PROFIL_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_AH7_PROFIL", "ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_AH7_PROFIL get_RECORDNEW_AH7_PROFIL() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_AH7_PROFIL();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_AH7_PROFIL(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_AH7_PROFIL create_Instance() throws myException {
		return new RECORD_AH7_PROFIL();
	}
	
	public static RECORD_AH7_PROFIL create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_AH7_PROFIL(Conn);
    }

	public static RECORD_AH7_PROFIL create_Instance(RECORD_AH7_PROFIL recordOrig) {
		return new RECORD_AH7_PROFIL(recordOrig);
    }

	public static RECORD_AH7_PROFIL create_Instance(long lID_Unformated) throws myException {
		return new RECORD_AH7_PROFIL(lID_Unformated);
    }

	public static RECORD_AH7_PROFIL create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_AH7_PROFIL(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_AH7_PROFIL create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_AH7_PROFIL(lID_Unformated, Conn);
	}

	public static RECORD_AH7_PROFIL create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_AH7_PROFIL(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_AH7_PROFIL create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_AH7_PROFIL(recordOrig);	    
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
    public RECORD_AH7_PROFIL build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_AH7_PROFIL WHERE ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF());
      }
      //return new RECORD_AH7_PROFIL(this.get_cSQL_4_Build());
      RECORD_AH7_PROFIL rec = new RECORD_AH7_PROFIL();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_AH7_PROFIL
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_AH7_PROFIL-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_AH7_PROFIL get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_AH7_PROFIL_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_AH7_PROFIL  recNew = new RECORDNEW_AH7_PROFIL();
		
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
    public RECORD_AH7_PROFIL set_recordNew(RECORDNEW_AH7_PROFIL recnew) throws myException {
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
	
	



		private RECLIST_AH7_STEUERDATEI DOWN_RECLIST_AH7_STEUERDATEI_id_ah7_profil = null ;






	/**
	 * References the Field ID_AH7_PROFIL 
	 * Falls keine get_ID_AH7_PROFIL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_ah7_profil() throws myException
	{
		if (S.isEmpty(this.get_ID_AH7_PROFIL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_ah7_profil==null)
		{
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_ah7_profil = new RECLIST_AH7_STEUERDATEI (
			       "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF()+" ORDER BY ID_AH7_STEUERDATEI",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_ah7_profil;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_AH7_PROFIL 
	 * Falls keine get_ID_AH7_PROFIL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_AH7_STEUERDATEI get_DOWN_RECORD_LIST_AH7_STEUERDATEI_id_ah7_profil(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_AH7_PROFIL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_AH7_STEUERDATEI_id_ah7_profil==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE ID_AH7_PROFIL="+this.get_ID_AH7_PROFIL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_AH7_STEUERDATEI_id_ah7_profil = new RECLIST_AH7_STEUERDATEI (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_AH7_STEUERDATEI_id_ah7_profil;
	}


	

	public static String FIELD__ABFALLERZEUGER_1 = "ABFALLERZEUGER_1";
	public static String FIELD__ABFALLERZEUGER_2 = "ABFALLERZEUGER_2";
	public static String FIELD__ABFALLERZEUGER_3 = "ABFALLERZEUGER_3";
	public static String FIELD__AH7_QUELLE_SICHER = "AH7_QUELLE_SICHER";
	public static String FIELD__AH7_ZIEL_SICHER = "AH7_ZIEL_SICHER";
	public static String FIELD__BEZEICHNUNG = "BEZEICHNUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_AH7_PROFIL = "ID_AH7_PROFIL";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__IMPORT_EMPFAENGER_1 = "IMPORT_EMPFAENGER_1";
	public static String FIELD__IMPORT_EMPFAENGER_2 = "IMPORT_EMPFAENGER_2";
	public static String FIELD__IMPORT_EMPFAENGER_3 = "IMPORT_EMPFAENGER_3";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__PROFILE4ALLOTHERS = "PROFILE4ALLOTHERS";
	public static String FIELD__STARTLAGER_FREMDBESITZ = "STARTLAGER_FREMDBESITZ";
	public static String FIELD__START_EIGENES_LAGER = "START_EIGENES_LAGER";
	public static String FIELD__START_INLAND = "START_INLAND";
	public static String FIELD__STATUS_RELATION = "STATUS_RELATION";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__VERBR_VERANLASSER_1 = "VERBR_VERANLASSER_1";
	public static String FIELD__VERBR_VERANLASSER_2 = "VERBR_VERANLASSER_2";
	public static String FIELD__VERBR_VERANLASSER_3 = "VERBR_VERANLASSER_3";
	public static String FIELD__VERWERTUNGSANLAGE_1 = "VERWERTUNGSANLAGE_1";
	public static String FIELD__VERWERTUNGSANLAGE_2 = "VERWERTUNGSANLAGE_2";
	public static String FIELD__VERWERTUNGSANLAGE_3 = "VERWERTUNGSANLAGE_3";
	public static String FIELD__ZIELLAGER_FREMDBESITZ = "ZIELLAGER_FREMDBESITZ";
	public static String FIELD__ZIEL_EIGENES_LAGER = "ZIEL_EIGENES_LAGER";
	public static String FIELD__ZIEL_INLAND = "ZIEL_INLAND";


	public String get_ABFALLERZEUGER_1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABFALLERZEUGER_1");
	}

	public String get_ABFALLERZEUGER_1_cF() throws myException
	{
		return this.get_FormatedValue("ABFALLERZEUGER_1");	
	}

	public String get_ABFALLERZEUGER_1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABFALLERZEUGER_1");
	}

	public String get_ABFALLERZEUGER_1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABFALLERZEUGER_1",cNotNullValue);
	}

	public String get_ABFALLERZEUGER_1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABFALLERZEUGER_1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABFALLERZEUGER_1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABFALLERZEUGER_1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABFALLERZEUGER_1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_1", calNewValueFormated);
	}
		public String get_ABFALLERZEUGER_2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABFALLERZEUGER_2");
	}

	public String get_ABFALLERZEUGER_2_cF() throws myException
	{
		return this.get_FormatedValue("ABFALLERZEUGER_2");	
	}

	public String get_ABFALLERZEUGER_2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABFALLERZEUGER_2");
	}

	public String get_ABFALLERZEUGER_2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABFALLERZEUGER_2",cNotNullValue);
	}

	public String get_ABFALLERZEUGER_2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABFALLERZEUGER_2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABFALLERZEUGER_2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABFALLERZEUGER_2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABFALLERZEUGER_2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_2", calNewValueFormated);
	}
		public String get_ABFALLERZEUGER_3_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABFALLERZEUGER_3");
	}

	public String get_ABFALLERZEUGER_3_cF() throws myException
	{
		return this.get_FormatedValue("ABFALLERZEUGER_3");	
	}

	public String get_ABFALLERZEUGER_3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABFALLERZEUGER_3");
	}

	public String get_ABFALLERZEUGER_3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABFALLERZEUGER_3",cNotNullValue);
	}

	public String get_ABFALLERZEUGER_3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABFALLERZEUGER_3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABFALLERZEUGER_3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABFALLERZEUGER_3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABFALLERZEUGER_3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABFALLERZEUGER_3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABFALLERZEUGER_3", calNewValueFormated);
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
		public String get_BEZEICHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZEICHNUNG");
	}

	public String get_BEZEICHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("BEZEICHNUNG");	
	}

	public String get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZEICHNUNG");
	}

	public String get_BEZEICHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZEICHNUNG",cNotNullValue);
	}

	public String get_BEZEICHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZEICHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", calNewValueFormated);
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
		public String get_ID_AH7_PROFIL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_AH7_PROFIL");
	}

	public String get_ID_AH7_PROFIL_cF() throws myException
	{
		return this.get_FormatedValue("ID_AH7_PROFIL");	
	}

	public String get_ID_AH7_PROFIL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_AH7_PROFIL");
	}

	public String get_ID_AH7_PROFIL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_AH7_PROFIL",cNotNullValue);
	}

	public String get_ID_AH7_PROFIL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_AH7_PROFIL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_AH7_PROFIL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_AH7_PROFIL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_AH7_PROFIL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_AH7_PROFIL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_AH7_PROFIL", calNewValueFormated);
	}
		public Long get_ID_AH7_PROFIL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_AH7_PROFIL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_AH7_PROFIL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_AH7_PROFIL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_AH7_PROFIL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_AH7_PROFIL").getDoubleValue();
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
	public String get_ID_AH7_PROFIL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_AH7_PROFIL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_AH7_PROFIL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_AH7_PROFIL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_AH7_PROFIL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_AH7_PROFIL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_AH7_PROFIL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_AH7_PROFIL").getBigDecimalValue();
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
	
	
	public String get_IMPORT_EMPFAENGER_1_cUF() throws myException
	{
		return this.get_UnFormatedValue("IMPORT_EMPFAENGER_1");
	}

	public String get_IMPORT_EMPFAENGER_1_cF() throws myException
	{
		return this.get_FormatedValue("IMPORT_EMPFAENGER_1");	
	}

	public String get_IMPORT_EMPFAENGER_1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IMPORT_EMPFAENGER_1");
	}

	public String get_IMPORT_EMPFAENGER_1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IMPORT_EMPFAENGER_1",cNotNullValue);
	}

	public String get_IMPORT_EMPFAENGER_1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IMPORT_EMPFAENGER_1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IMPORT_EMPFAENGER_1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IMPORT_EMPFAENGER_1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_1", calNewValueFormated);
	}
		public String get_IMPORT_EMPFAENGER_2_cUF() throws myException
	{
		return this.get_UnFormatedValue("IMPORT_EMPFAENGER_2");
	}

	public String get_IMPORT_EMPFAENGER_2_cF() throws myException
	{
		return this.get_FormatedValue("IMPORT_EMPFAENGER_2");	
	}

	public String get_IMPORT_EMPFAENGER_2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IMPORT_EMPFAENGER_2");
	}

	public String get_IMPORT_EMPFAENGER_2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IMPORT_EMPFAENGER_2",cNotNullValue);
	}

	public String get_IMPORT_EMPFAENGER_2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IMPORT_EMPFAENGER_2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IMPORT_EMPFAENGER_2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IMPORT_EMPFAENGER_2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_2", calNewValueFormated);
	}
		public String get_IMPORT_EMPFAENGER_3_cUF() throws myException
	{
		return this.get_UnFormatedValue("IMPORT_EMPFAENGER_3");
	}

	public String get_IMPORT_EMPFAENGER_3_cF() throws myException
	{
		return this.get_FormatedValue("IMPORT_EMPFAENGER_3");	
	}

	public String get_IMPORT_EMPFAENGER_3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IMPORT_EMPFAENGER_3");
	}

	public String get_IMPORT_EMPFAENGER_3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IMPORT_EMPFAENGER_3",cNotNullValue);
	}

	public String get_IMPORT_EMPFAENGER_3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IMPORT_EMPFAENGER_3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IMPORT_EMPFAENGER_3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IMPORT_EMPFAENGER_3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IMPORT_EMPFAENGER_3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IMPORT_EMPFAENGER_3", calNewValueFormated);
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
		public String get_PROFILE4ALLOTHERS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROFILE4ALLOTHERS");
	}

	public String get_PROFILE4ALLOTHERS_cF() throws myException
	{
		return this.get_FormatedValue("PROFILE4ALLOTHERS");	
	}

	public String get_PROFILE4ALLOTHERS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROFILE4ALLOTHERS");
	}

	public String get_PROFILE4ALLOTHERS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROFILE4ALLOTHERS",cNotNullValue);
	}

	public String get_PROFILE4ALLOTHERS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROFILE4ALLOTHERS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROFILE4ALLOTHERS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROFILE4ALLOTHERS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROFILE4ALLOTHERS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROFILE4ALLOTHERS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROFILE4ALLOTHERS", calNewValueFormated);
	}
		public String get_STARTLAGER_FREMDBESITZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("STARTLAGER_FREMDBESITZ");
	}

	public String get_STARTLAGER_FREMDBESITZ_cF() throws myException
	{
		return this.get_FormatedValue("STARTLAGER_FREMDBESITZ");	
	}

	public String get_STARTLAGER_FREMDBESITZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STARTLAGER_FREMDBESITZ");
	}

	public String get_STARTLAGER_FREMDBESITZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STARTLAGER_FREMDBESITZ",cNotNullValue);
	}

	public String get_STARTLAGER_FREMDBESITZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STARTLAGER_FREMDBESITZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STARTLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STARTLAGER_FREMDBESITZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STARTLAGER_FREMDBESITZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STARTLAGER_FREMDBESITZ", calNewValueFormated);
	}
		public boolean is_STARTLAGER_FREMDBESITZ_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STARTLAGER_FREMDBESITZ_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STARTLAGER_FREMDBESITZ_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STARTLAGER_FREMDBESITZ_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_START_EIGENES_LAGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("START_EIGENES_LAGER");
	}

	public String get_START_EIGENES_LAGER_cF() throws myException
	{
		return this.get_FormatedValue("START_EIGENES_LAGER");	
	}

	public String get_START_EIGENES_LAGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("START_EIGENES_LAGER");
	}

	public String get_START_EIGENES_LAGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("START_EIGENES_LAGER",cNotNullValue);
	}

	public String get_START_EIGENES_LAGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("START_EIGENES_LAGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("START_EIGENES_LAGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_START_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("START_EIGENES_LAGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_EIGENES_LAGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("START_EIGENES_LAGER", calNewValueFormated);
	}
		public boolean is_START_EIGENES_LAGER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_START_EIGENES_LAGER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_START_EIGENES_LAGER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_START_EIGENES_LAGER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_START_INLAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("START_INLAND");
	}

	public String get_START_INLAND_cF() throws myException
	{
		return this.get_FormatedValue("START_INLAND");	
	}

	public String get_START_INLAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("START_INLAND");
	}

	public String get_START_INLAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("START_INLAND",cNotNullValue);
	}

	public String get_START_INLAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("START_INLAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_START_INLAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("START_INLAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_START_INLAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("START_INLAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_INLAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("START_INLAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_INLAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("START_INLAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_INLAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("START_INLAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_START_INLAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("START_INLAND", calNewValueFormated);
	}
		public boolean is_START_INLAND_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_START_INLAND_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_START_INLAND_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_START_INLAND_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STATUS_RELATION_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATUS_RELATION");
	}

	public String get_STATUS_RELATION_cF() throws myException
	{
		return this.get_FormatedValue("STATUS_RELATION");	
	}

	public String get_STATUS_RELATION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATUS_RELATION");
	}

	public String get_STATUS_RELATION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATUS_RELATION",cNotNullValue);
	}

	public String get_STATUS_RELATION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATUS_RELATION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATUS_RELATION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATUS_RELATION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATUS_RELATION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATUS_RELATION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_RELATION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_RELATION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_RELATION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_RELATION", calNewValueFormated);
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
	
	
	public String get_VERBR_VERANLASSER_1_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERBR_VERANLASSER_1");
	}

	public String get_VERBR_VERANLASSER_1_cF() throws myException
	{
		return this.get_FormatedValue("VERBR_VERANLASSER_1");	
	}

	public String get_VERBR_VERANLASSER_1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERBR_VERANLASSER_1");
	}

	public String get_VERBR_VERANLASSER_1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERBR_VERANLASSER_1",cNotNullValue);
	}

	public String get_VERBR_VERANLASSER_1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERBR_VERANLASSER_1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERBR_VERANLASSER_1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERBR_VERANLASSER_1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERBR_VERANLASSER_1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_1", calNewValueFormated);
	}
		public String get_VERBR_VERANLASSER_2_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERBR_VERANLASSER_2");
	}

	public String get_VERBR_VERANLASSER_2_cF() throws myException
	{
		return this.get_FormatedValue("VERBR_VERANLASSER_2");	
	}

	public String get_VERBR_VERANLASSER_2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERBR_VERANLASSER_2");
	}

	public String get_VERBR_VERANLASSER_2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERBR_VERANLASSER_2",cNotNullValue);
	}

	public String get_VERBR_VERANLASSER_2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERBR_VERANLASSER_2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERBR_VERANLASSER_2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERBR_VERANLASSER_2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERBR_VERANLASSER_2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_2", calNewValueFormated);
	}
		public String get_VERBR_VERANLASSER_3_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERBR_VERANLASSER_3");
	}

	public String get_VERBR_VERANLASSER_3_cF() throws myException
	{
		return this.get_FormatedValue("VERBR_VERANLASSER_3");	
	}

	public String get_VERBR_VERANLASSER_3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERBR_VERANLASSER_3");
	}

	public String get_VERBR_VERANLASSER_3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERBR_VERANLASSER_3",cNotNullValue);
	}

	public String get_VERBR_VERANLASSER_3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERBR_VERANLASSER_3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERBR_VERANLASSER_3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERBR_VERANLASSER_3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERBR_VERANLASSER_3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERBR_VERANLASSER_3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERBR_VERANLASSER_3", calNewValueFormated);
	}
		public String get_VERWERTUNGSANLAGE_1_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSANLAGE_1");
	}

	public String get_VERWERTUNGSANLAGE_1_cF() throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSANLAGE_1");	
	}

	public String get_VERWERTUNGSANLAGE_1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERWERTUNGSANLAGE_1");
	}

	public String get_VERWERTUNGSANLAGE_1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSANLAGE_1",cNotNullValue);
	}

	public String get_VERWERTUNGSANLAGE_1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSANLAGE_1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSANLAGE_1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERWERTUNGSANLAGE_1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_1", calNewValueFormated);
	}
		public String get_VERWERTUNGSANLAGE_2_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSANLAGE_2");
	}

	public String get_VERWERTUNGSANLAGE_2_cF() throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSANLAGE_2");	
	}

	public String get_VERWERTUNGSANLAGE_2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERWERTUNGSANLAGE_2");
	}

	public String get_VERWERTUNGSANLAGE_2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSANLAGE_2",cNotNullValue);
	}

	public String get_VERWERTUNGSANLAGE_2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSANLAGE_2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSANLAGE_2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERWERTUNGSANLAGE_2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_2", calNewValueFormated);
	}
		public String get_VERWERTUNGSANLAGE_3_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSANLAGE_3");
	}

	public String get_VERWERTUNGSANLAGE_3_cF() throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSANLAGE_3");	
	}

	public String get_VERWERTUNGSANLAGE_3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERWERTUNGSANLAGE_3");
	}

	public String get_VERWERTUNGSANLAGE_3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERWERTUNGSANLAGE_3",cNotNullValue);
	}

	public String get_VERWERTUNGSANLAGE_3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERWERTUNGSANLAGE_3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERWERTUNGSANLAGE_3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERWERTUNGSANLAGE_3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERWERTUNGSANLAGE_3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERWERTUNGSANLAGE_3", calNewValueFormated);
	}
		public String get_ZIELLAGER_FREMDBESITZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZIELLAGER_FREMDBESITZ");
	}

	public String get_ZIELLAGER_FREMDBESITZ_cF() throws myException
	{
		return this.get_FormatedValue("ZIELLAGER_FREMDBESITZ");	
	}

	public String get_ZIELLAGER_FREMDBESITZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZIELLAGER_FREMDBESITZ");
	}

	public String get_ZIELLAGER_FREMDBESITZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZIELLAGER_FREMDBESITZ",cNotNullValue);
	}

	public String get_ZIELLAGER_FREMDBESITZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZIELLAGER_FREMDBESITZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZIELLAGER_FREMDBESITZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIELLAGER_FREMDBESITZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIELLAGER_FREMDBESITZ", calNewValueFormated);
	}
		public boolean is_ZIELLAGER_FREMDBESITZ_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIELLAGER_FREMDBESITZ_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZIELLAGER_FREMDBESITZ_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIELLAGER_FREMDBESITZ_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZIEL_EIGENES_LAGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZIEL_EIGENES_LAGER");
	}

	public String get_ZIEL_EIGENES_LAGER_cF() throws myException
	{
		return this.get_FormatedValue("ZIEL_EIGENES_LAGER");	
	}

	public String get_ZIEL_EIGENES_LAGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZIEL_EIGENES_LAGER");
	}

	public String get_ZIEL_EIGENES_LAGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZIEL_EIGENES_LAGER",cNotNullValue);
	}

	public String get_ZIEL_EIGENES_LAGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZIEL_EIGENES_LAGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZIEL_EIGENES_LAGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZIEL_EIGENES_LAGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_EIGENES_LAGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_EIGENES_LAGER", calNewValueFormated);
	}
		public boolean is_ZIEL_EIGENES_LAGER_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIEL_EIGENES_LAGER_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZIEL_EIGENES_LAGER_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIEL_EIGENES_LAGER_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ZIEL_INLAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZIEL_INLAND");
	}

	public String get_ZIEL_INLAND_cF() throws myException
	{
		return this.get_FormatedValue("ZIEL_INLAND");	
	}

	public String get_ZIEL_INLAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZIEL_INLAND");
	}

	public String get_ZIEL_INLAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZIEL_INLAND",cNotNullValue);
	}

	public String get_ZIEL_INLAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZIEL_INLAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZIEL_INLAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZIEL_INLAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZIEL_INLAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZIEL_INLAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_INLAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_INLAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_INLAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_INLAND", calNewValueFormated);
	}
		public boolean is_ZIEL_INLAND_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIEL_INLAND_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZIEL_INLAND_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIEL_INLAND_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABFALLERZEUGER_1",MyRECORD.DATATYPES.TEXT);
	put("ABFALLERZEUGER_2",MyRECORD.DATATYPES.TEXT);
	put("ABFALLERZEUGER_3",MyRECORD.DATATYPES.TEXT);
	put("AH7_QUELLE_SICHER",MyRECORD.DATATYPES.YESNO);
	put("AH7_ZIEL_SICHER",MyRECORD.DATATYPES.YESNO);
	put("BEZEICHNUNG",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_AH7_PROFIL",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("IMPORT_EMPFAENGER_1",MyRECORD.DATATYPES.TEXT);
	put("IMPORT_EMPFAENGER_2",MyRECORD.DATATYPES.TEXT);
	put("IMPORT_EMPFAENGER_3",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("PROFILE4ALLOTHERS",MyRECORD.DATATYPES.TEXT);
	put("STARTLAGER_FREMDBESITZ",MyRECORD.DATATYPES.YESNO);
	put("START_EIGENES_LAGER",MyRECORD.DATATYPES.YESNO);
	put("START_INLAND",MyRECORD.DATATYPES.YESNO);
	put("STATUS_RELATION",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("VERBR_VERANLASSER_1",MyRECORD.DATATYPES.TEXT);
	put("VERBR_VERANLASSER_2",MyRECORD.DATATYPES.TEXT);
	put("VERBR_VERANLASSER_3",MyRECORD.DATATYPES.TEXT);
	put("VERWERTUNGSANLAGE_1",MyRECORD.DATATYPES.TEXT);
	put("VERWERTUNGSANLAGE_2",MyRECORD.DATATYPES.TEXT);
	put("VERWERTUNGSANLAGE_3",MyRECORD.DATATYPES.TEXT);
	put("ZIELLAGER_FREMDBESITZ",MyRECORD.DATATYPES.YESNO);
	put("ZIEL_EIGENES_LAGER",MyRECORD.DATATYPES.YESNO);
	put("ZIEL_INLAND",MyRECORD.DATATYPES.YESNO);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_AH7_PROFIL.HM_FIELDS;	
	}

}
