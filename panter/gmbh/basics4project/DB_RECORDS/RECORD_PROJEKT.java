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

public class RECORD_PROJEKT extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_PROJEKT";
    public static String IDFIELD   = "ID_PROJEKT";
    

	//erzeugen eines RECORDNEW_JT_PROJEKT - felds
	private RECORDNEW_PROJEKT   recNEW = null;

    private _TAB  tab = _TAB.projekt;  



	public RECORD_PROJEKT() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_PROJEKT");
	}


	public RECORD_PROJEKT(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_PROJEKT");
	}



	public RECORD_PROJEKT(RECORD_PROJEKT recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_PROJEKT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PROJEKT.TABLENAME);
	}


	//2014-04-03
	public RECORD_PROJEKT(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_PROJEKT");
		this.set_Tablename_To_FieldMetaDefs(RECORD_PROJEKT.TABLENAME);
	}




	public RECORD_PROJEKT(long lID_Unformated) throws myException
	{
		super("JT_PROJEKT","ID_PROJEKT",""+lID_Unformated);
		this.set_TABLE_NAME("JT_PROJEKT");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PROJEKT.TABLENAME);
	}

	public RECORD_PROJEKT(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_PROJEKT");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_PROJEKT", "ID_PROJEKT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_PROJEKT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PROJEKT.TABLENAME);
	}
	
	

	public RECORD_PROJEKT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_PROJEKT","ID_PROJEKT",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_PROJEKT");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PROJEKT.TABLENAME);

	}


	public RECORD_PROJEKT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_PROJEKT");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_PROJEKT", "ID_PROJEKT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_PROJEKT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_PROJEKT.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_PROJEKT();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_PROJEKT.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_PROJEKT";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_PROJEKT_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_PROJEKT_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_PROJEKT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_PROJEKT", bibE2.cTO(), "ID_PROJEKT="+this.get_ID_PROJEKT_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_PROJEKT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_PROJEKT", bibE2.cTO(), "ID_PROJEKT="+this.get_ID_PROJEKT_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_PROJEKT WHERE ID_PROJEKT="+this.get_ID_PROJEKT_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_PROJEKT WHERE ID_PROJEKT="+this.get_ID_PROJEKT_cUF();
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
			if (S.isFull(this.get_ID_PROJEKT_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_PROJEKT", "ID_PROJEKT="+this.get_ID_PROJEKT_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_PROJEKT get_RECORDNEW_PROJEKT() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_PROJEKT();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_PROJEKT(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_PROJEKT create_Instance() throws myException {
		return new RECORD_PROJEKT();
	}
	
	public static RECORD_PROJEKT create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_PROJEKT(Conn);
    }

	public static RECORD_PROJEKT create_Instance(RECORD_PROJEKT recordOrig) {
		return new RECORD_PROJEKT(recordOrig);
    }

	public static RECORD_PROJEKT create_Instance(long lID_Unformated) throws myException {
		return new RECORD_PROJEKT(lID_Unformated);
    }

	public static RECORD_PROJEKT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_PROJEKT(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_PROJEKT create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_PROJEKT(lID_Unformated, Conn);
	}

	public static RECORD_PROJEKT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_PROJEKT(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_PROJEKT create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_PROJEKT(recordOrig);	    
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
    public RECORD_PROJEKT build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_PROJEKT WHERE ID_PROJEKT="+this.get_ID_PROJEKT_cUF());
      }
      //return new RECORD_PROJEKT(this.get_cSQL_4_Build());
      RECORD_PROJEKT rec = new RECORD_PROJEKT();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_PROJEKT
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_PROJEKT-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_PROJEKT get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_PROJEKT_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_PROJEKT  recNew = new RECORDNEW_PROJEKT();
		
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
    public RECORD_PROJEKT set_recordNew(RECORDNEW_PROJEKT recnew) throws myException {
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
	
	



		private RECORD_USER UP_RECORD_USER_id_user = null;




		private RECLIST_PRO_ADRESSEN DOWN_RECLIST_PRO_ADRESSEN_id_projekt = null ;




		private RECLIST_PRO_MITARB DOWN_RECLIST_PRO_MITARB_id_projekt = null ;




		private RECORD_PROJEKTGEWICHT UP_RECORD_PROJEKTGEWICHT_id_projektgewicht = null;




		private RECORD_PROJEKTSTATUSVARIANTE UP_RECORD_PROJEKTSTATUSVARIANTE_id_projektstatusvariante = null;






	
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
	 * References the Field ID_PROJEKT 
	 * Falls keine get_ID_PROJEKT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_ADRESSEN get_DOWN_RECORD_LIST_PRO_ADRESSEN_id_projekt() throws myException
	{
		if (S.isEmpty(this.get_ID_PROJEKT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_ADRESSEN_id_projekt==null)
		{
			this.DOWN_RECLIST_PRO_ADRESSEN_id_projekt = new RECLIST_PRO_ADRESSEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PRO_ADRESSEN WHERE ID_PROJEKT="+this.get_ID_PROJEKT_cUF()+" ORDER BY ID_PRO_ADRESSEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_ADRESSEN_id_projekt;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_PROJEKT 
	 * Falls keine get_ID_PROJEKT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_ADRESSEN get_DOWN_RECORD_LIST_PRO_ADRESSEN_id_projekt(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_PROJEKT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_ADRESSEN_id_projekt==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PRO_ADRESSEN WHERE ID_PROJEKT="+this.get_ID_PROJEKT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PRO_ADRESSEN_id_projekt = new RECLIST_PRO_ADRESSEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_ADRESSEN_id_projekt;
	}


	





	/**
	 * References the Field ID_PROJEKT 
	 * Falls keine get_ID_PROJEKT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_MITARB get_DOWN_RECORD_LIST_PRO_MITARB_id_projekt() throws myException
	{
		if (S.isEmpty(this.get_ID_PROJEKT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_MITARB_id_projekt==null)
		{
			this.DOWN_RECLIST_PRO_MITARB_id_projekt = new RECLIST_PRO_MITARB (
			       "SELECT * FROM "+bibE2.cTO()+".JT_PRO_MITARB WHERE ID_PROJEKT="+this.get_ID_PROJEKT_cUF()+" ORDER BY ID_PRO_MITARB",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_MITARB_id_projekt;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_PROJEKT 
	 * Falls keine get_ID_PROJEKT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_PRO_MITARB get_DOWN_RECORD_LIST_PRO_MITARB_id_projekt(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_PROJEKT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_PRO_MITARB_id_projekt==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_PRO_MITARB WHERE ID_PROJEKT="+this.get_ID_PROJEKT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_PRO_MITARB_id_projekt = new RECLIST_PRO_MITARB (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_PRO_MITARB_id_projekt;
	}


	





	
	/**
	 * References the Field ID_PROJEKTGEWICHT
	 * Falls keine this.get_ID_PROJEKTGEWICHT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_PROJEKTGEWICHT get_UP_RECORD_PROJEKTGEWICHT_id_projektgewicht() throws myException
	{
		if (S.isEmpty(this.get_ID_PROJEKTGEWICHT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_PROJEKTGEWICHT_id_projektgewicht==null)
		{
			this.UP_RECORD_PROJEKTGEWICHT_id_projektgewicht = new RECORD_PROJEKTGEWICHT(this.get_ID_PROJEKTGEWICHT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_PROJEKTGEWICHT_id_projektgewicht;
	}
	





	
	/**
	 * References the Field ID_PROJEKTSTATUSVARIANTE
	 * Falls keine this.get_ID_PROJEKTSTATUSVARIANTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_PROJEKTSTATUSVARIANTE get_UP_RECORD_PROJEKTSTATUSVARIANTE_id_projektstatusvariante() throws myException
	{
		if (S.isEmpty(this.get_ID_PROJEKTSTATUSVARIANTE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_PROJEKTSTATUSVARIANTE_id_projektstatusvariante==null)
		{
			this.UP_RECORD_PROJEKTSTATUSVARIANTE_id_projektstatusvariante = new RECORD_PROJEKTSTATUSVARIANTE(this.get_ID_PROJEKTSTATUSVARIANTE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_PROJEKTSTATUSVARIANTE_id_projektstatusvariante;
	}
	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__BEENDET = "BEENDET";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_PROJEKT = "ID_PROJEKT";
	public static String FIELD__ID_PROJEKTGEWICHT = "ID_PROJEKTGEWICHT";
	public static String FIELD__ID_PROJEKTSTATUSVARIANTE = "ID_PROJEKTSTATUSVARIANTE";
	public static String FIELD__ID_USER = "ID_USER";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__PROJEKTBEGIN = "PROJEKTBEGIN";
	public static String FIELD__PROJEKTBESCHREIBUNG = "PROJEKTBESCHREIBUNG";
	public static String FIELD__PROJEKTDEADLINE = "PROJEKTDEADLINE";
	public static String FIELD__PROJEKTNAME = "PROJEKTNAME";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__WIEDERVORLAGE = "WIEDERVORLAGE";
	public static String FIELD__WIEDERVORLAGE_DELTA = "WIEDERVORLAGE_DELTA";


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
		public String get_BEENDET_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEENDET");
	}

	public String get_BEENDET_cF() throws myException
	{
		return this.get_FormatedValue("BEENDET");	
	}

	public String get_BEENDET_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEENDET");
	}

	public String get_BEENDET_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEENDET",cNotNullValue);
	}

	public String get_BEENDET_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEENDET",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEENDET(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEENDET", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEENDET(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEENDET", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEENDET_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEENDET", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEENDET_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEENDET", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEENDET_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEENDET", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEENDET_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEENDET", calNewValueFormated);
	}
		public boolean is_BEENDET_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BEENDET_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BEENDET_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BEENDET_cUF_NN("N").equals("N"))
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
	
	
	public String get_ID_PROJEKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_PROJEKT");
	}

	public String get_ID_PROJEKT_cF() throws myException
	{
		return this.get_FormatedValue("ID_PROJEKT");	
	}

	public String get_ID_PROJEKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_PROJEKT");
	}

	public String get_ID_PROJEKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_PROJEKT",cNotNullValue);
	}

	public String get_ID_PROJEKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_PROJEKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_PROJEKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_PROJEKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_PROJEKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_PROJEKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKT", calNewValueFormated);
	}
		public Long get_ID_PROJEKT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_PROJEKT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_PROJEKT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_PROJEKT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_PROJEKT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_PROJEKT").getDoubleValue();
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
	public String get_ID_PROJEKT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PROJEKT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_PROJEKT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PROJEKT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_PROJEKT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PROJEKT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_PROJEKT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PROJEKT").getBigDecimalValue();
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
	
	
	public String get_ID_PROJEKTGEWICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_PROJEKTGEWICHT");
	}

	public String get_ID_PROJEKTGEWICHT_cF() throws myException
	{
		return this.get_FormatedValue("ID_PROJEKTGEWICHT");	
	}

	public String get_ID_PROJEKTGEWICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_PROJEKTGEWICHT");
	}

	public String get_ID_PROJEKTGEWICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_PROJEKTGEWICHT",cNotNullValue);
	}

	public String get_ID_PROJEKTGEWICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_PROJEKTGEWICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_PROJEKTGEWICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_PROJEKTGEWICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_PROJEKTGEWICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTGEWICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKTGEWICHT", calNewValueFormated);
	}
		public Long get_ID_PROJEKTGEWICHT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_PROJEKTGEWICHT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_PROJEKTGEWICHT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_PROJEKTGEWICHT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_PROJEKTGEWICHT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_PROJEKTGEWICHT").getDoubleValue();
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
	public String get_ID_PROJEKTGEWICHT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PROJEKTGEWICHT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_PROJEKTGEWICHT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PROJEKTGEWICHT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_PROJEKTGEWICHT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PROJEKTGEWICHT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_PROJEKTGEWICHT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PROJEKTGEWICHT").getBigDecimalValue();
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
	
	
	public String get_ID_PROJEKTSTATUSVARIANTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_PROJEKTSTATUSVARIANTE");
	}

	public String get_ID_PROJEKTSTATUSVARIANTE_cF() throws myException
	{
		return this.get_FormatedValue("ID_PROJEKTSTATUSVARIANTE");	
	}

	public String get_ID_PROJEKTSTATUSVARIANTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_PROJEKTSTATUSVARIANTE");
	}

	public String get_ID_PROJEKTSTATUSVARIANTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_PROJEKTSTATUSVARIANTE",cNotNullValue);
	}

	public String get_ID_PROJEKTSTATUSVARIANTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_PROJEKTSTATUSVARIANTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PROJEKTSTATUSVARIANTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PROJEKTSTATUSVARIANTE", calNewValueFormated);
	}
		public Long get_ID_PROJEKTSTATUSVARIANTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_PROJEKTSTATUSVARIANTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_PROJEKTSTATUSVARIANTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_PROJEKTSTATUSVARIANTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_PROJEKTSTATUSVARIANTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_PROJEKTSTATUSVARIANTE").getDoubleValue();
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
	public String get_ID_PROJEKTSTATUSVARIANTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PROJEKTSTATUSVARIANTE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_PROJEKTSTATUSVARIANTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PROJEKTSTATUSVARIANTE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_PROJEKTSTATUSVARIANTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PROJEKTSTATUSVARIANTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_PROJEKTSTATUSVARIANTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PROJEKTSTATUSVARIANTE").getBigDecimalValue();
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
		public String get_PROJEKTBEGIN_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROJEKTBEGIN");
	}

	public String get_PROJEKTBEGIN_cF() throws myException
	{
		return this.get_FormatedValue("PROJEKTBEGIN");	
	}

	public String get_PROJEKTBEGIN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROJEKTBEGIN");
	}

	public String get_PROJEKTBEGIN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROJEKTBEGIN",cNotNullValue);
	}

	public String get_PROJEKTBEGIN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROJEKTBEGIN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROJEKTBEGIN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROJEKTBEGIN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROJEKTBEGIN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBEGIN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTBEGIN", calNewValueFormated);
	}
		public String get_PROJEKTBESCHREIBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROJEKTBESCHREIBUNG");
	}

	public String get_PROJEKTBESCHREIBUNG_cF() throws myException
	{
		return this.get_FormatedValue("PROJEKTBESCHREIBUNG");	
	}

	public String get_PROJEKTBESCHREIBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROJEKTBESCHREIBUNG");
	}

	public String get_PROJEKTBESCHREIBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROJEKTBESCHREIBUNG",cNotNullValue);
	}

	public String get_PROJEKTBESCHREIBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROJEKTBESCHREIBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROJEKTBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROJEKTBESCHREIBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTBESCHREIBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTBESCHREIBUNG", calNewValueFormated);
	}
		public String get_PROJEKTDEADLINE_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROJEKTDEADLINE");
	}

	public String get_PROJEKTDEADLINE_cF() throws myException
	{
		return this.get_FormatedValue("PROJEKTDEADLINE");	
	}

	public String get_PROJEKTDEADLINE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROJEKTDEADLINE");
	}

	public String get_PROJEKTDEADLINE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROJEKTDEADLINE",cNotNullValue);
	}

	public String get_PROJEKTDEADLINE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROJEKTDEADLINE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROJEKTDEADLINE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROJEKTDEADLINE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROJEKTDEADLINE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTDEADLINE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTDEADLINE", calNewValueFormated);
	}
		public String get_PROJEKTNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROJEKTNAME");
	}

	public String get_PROJEKTNAME_cF() throws myException
	{
		return this.get_FormatedValue("PROJEKTNAME");	
	}

	public String get_PROJEKTNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROJEKTNAME");
	}

	public String get_PROJEKTNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROJEKTNAME",cNotNullValue);
	}

	public String get_PROJEKTNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROJEKTNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROJEKTNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROJEKTNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROJEKTNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROJEKTNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROJEKTNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROJEKTNAME", calNewValueFormated);
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
	
	
	public String get_WIEDERVORLAGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEDERVORLAGE");
	}

	public String get_WIEDERVORLAGE_cF() throws myException
	{
		return this.get_FormatedValue("WIEDERVORLAGE");	
	}

	public String get_WIEDERVORLAGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEDERVORLAGE");
	}

	public String get_WIEDERVORLAGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEDERVORLAGE",cNotNullValue);
	}

	public String get_WIEDERVORLAGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEDERVORLAGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEDERVORLAGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEDERVORLAGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEDERVORLAGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEDERVORLAGE", calNewValueFormated);
	}
		public String get_WIEDERVORLAGE_DELTA_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEDERVORLAGE_DELTA");
	}

	public String get_WIEDERVORLAGE_DELTA_cF() throws myException
	{
		return this.get_FormatedValue("WIEDERVORLAGE_DELTA");	
	}

	public String get_WIEDERVORLAGE_DELTA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEDERVORLAGE_DELTA");
	}

	public String get_WIEDERVORLAGE_DELTA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEDERVORLAGE_DELTA",cNotNullValue);
	}

	public String get_WIEDERVORLAGE_DELTA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEDERVORLAGE_DELTA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEDERVORLAGE_DELTA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEDERVORLAGE_DELTA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEDERVORLAGE_DELTA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEDERVORLAGE_DELTA", calNewValueFormated);
	}
		public Long get_WIEDERVORLAGE_DELTA_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("WIEDERVORLAGE_DELTA").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_WIEDERVORLAGE_DELTA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WIEDERVORLAGE_DELTA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WIEDERVORLAGE_DELTA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WIEDERVORLAGE_DELTA").getDoubleValue();
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
	public String get_WIEDERVORLAGE_DELTA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WIEDERVORLAGE_DELTA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_WIEDERVORLAGE_DELTA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WIEDERVORLAGE_DELTA_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_WIEDERVORLAGE_DELTA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WIEDERVORLAGE_DELTA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WIEDERVORLAGE_DELTA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WIEDERVORLAGE_DELTA").getBigDecimalValue();
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
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("BEENDET",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_PROJEKT",MyRECORD.DATATYPES.NUMBER);
	put("ID_PROJEKTGEWICHT",MyRECORD.DATATYPES.NUMBER);
	put("ID_PROJEKTSTATUSVARIANTE",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("PROJEKTBEGIN",MyRECORD.DATATYPES.DATE);
	put("PROJEKTBESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("PROJEKTDEADLINE",MyRECORD.DATATYPES.DATE);
	put("PROJEKTNAME",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("WIEDERVORLAGE",MyRECORD.DATATYPES.DATE);
	put("WIEDERVORLAGE_DELTA",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_PROJEKT.HM_FIELDS;	
	}

}
