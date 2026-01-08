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

public class RECORD_LAUFZETTEL_EINTRAG extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_LAUFZETTEL_EINTRAG";
    public static String IDFIELD   = "ID_LAUFZETTEL_EINTRAG";
    

	//erzeugen eines RECORDNEW_JT_LAUFZETTEL_EINTRAG - felds
	private RECORDNEW_LAUFZETTEL_EINTRAG   recNEW = null;

    private _TAB  tab = _TAB.laufzettel_eintrag;  



	public RECORD_LAUFZETTEL_EINTRAG() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");
	}


	public RECORD_LAUFZETTEL_EINTRAG(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");
	}



	public RECORD_LAUFZETTEL_EINTRAG(RECORD_LAUFZETTEL_EINTRAG recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAUFZETTEL_EINTRAG.TABLENAME);
	}


	//2014-04-03
	public RECORD_LAUFZETTEL_EINTRAG(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAUFZETTEL_EINTRAG.TABLENAME);
	}




	public RECORD_LAUFZETTEL_EINTRAG(long lID_Unformated) throws myException
	{
		super("JT_LAUFZETTEL_EINTRAG","ID_LAUFZETTEL_EINTRAG",""+lID_Unformated);
		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAUFZETTEL_EINTRAG.TABLENAME);
	}

	public RECORD_LAUFZETTEL_EINTRAG(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG", "ID_LAUFZETTEL_EINTRAG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAUFZETTEL_EINTRAG.TABLENAME);
	}
	
	

	public RECORD_LAUFZETTEL_EINTRAG(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_LAUFZETTEL_EINTRAG","ID_LAUFZETTEL_EINTRAG",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAUFZETTEL_EINTRAG.TABLENAME);

	}


	public RECORD_LAUFZETTEL_EINTRAG(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_LAUFZETTEL_EINTRAG");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG", "ID_LAUFZETTEL_EINTRAG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAUFZETTEL_EINTRAG.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_LAUFZETTEL_EINTRAG();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_LAUFZETTEL_EINTRAG.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_LAUFZETTEL_EINTRAG";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_LAUFZETTEL_EINTRAG_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_LAUFZETTEL_EINTRAG_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAUFZETTEL_EINTRAG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_LAUFZETTEL_EINTRAG", bibE2.cTO(), "ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAUFZETTEL_EINTRAG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_LAUFZETTEL_EINTRAG", bibE2.cTO(), "ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF();
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
			if (S.isFull(this.get_ID_LAUFZETTEL_EINTRAG_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG", "ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_LAUFZETTEL_EINTRAG get_RECORDNEW_LAUFZETTEL_EINTRAG() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_LAUFZETTEL_EINTRAG();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_LAUFZETTEL_EINTRAG(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_LAUFZETTEL_EINTRAG create_Instance() throws myException {
		return new RECORD_LAUFZETTEL_EINTRAG();
	}
	
	public static RECORD_LAUFZETTEL_EINTRAG create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_LAUFZETTEL_EINTRAG(Conn);
    }

	public static RECORD_LAUFZETTEL_EINTRAG create_Instance(RECORD_LAUFZETTEL_EINTRAG recordOrig) {
		return new RECORD_LAUFZETTEL_EINTRAG(recordOrig);
    }

	public static RECORD_LAUFZETTEL_EINTRAG create_Instance(long lID_Unformated) throws myException {
		return new RECORD_LAUFZETTEL_EINTRAG(lID_Unformated);
    }

	public static RECORD_LAUFZETTEL_EINTRAG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_LAUFZETTEL_EINTRAG(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_LAUFZETTEL_EINTRAG create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_LAUFZETTEL_EINTRAG(lID_Unformated, Conn);
	}

	public static RECORD_LAUFZETTEL_EINTRAG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_LAUFZETTEL_EINTRAG(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_LAUFZETTEL_EINTRAG create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_LAUFZETTEL_EINTRAG(recordOrig);	    
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
    public RECORD_LAUFZETTEL_EINTRAG build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF());
      }
      //return new RECORD_LAUFZETTEL_EINTRAG(this.get_cSQL_4_Build());
      RECORD_LAUFZETTEL_EINTRAG rec = new RECORD_LAUFZETTEL_EINTRAG();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_LAUFZETTEL_EINTRAG
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_LAUFZETTEL_EINTRAG-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_LAUFZETTEL_EINTRAG get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_LAUFZETTEL_EINTRAG_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_LAUFZETTEL_EINTRAG  recNew = new RECORDNEW_LAUFZETTEL_EINTRAG();
		
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
    public RECORD_LAUFZETTEL_EINTRAG set_recordNew(RECORDNEW_LAUFZETTEL_EINTRAG recnew) throws myException {
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
	
	



		private RECORD_USER UP_RECORD_USER_id_user_abgeschlossen_von = null;




		private RECORD_USER UP_RECORD_USER_id_user_bearbeiter = null;




		private RECORD_USER UP_RECORD_USER_id_user_besitzer = null;




		private RECORD_LAUFZETTEL UP_RECORD_LAUFZETTEL_id_laufzettel = null;




		private RECLIST_LAUFZETTEL_EINTRAG DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_eintrag_parent = null ;




		private RECORD_LAUFZETTEL_EINTRAG UP_RECORD_LAUFZETTEL_EINTRAG_id_eintrag_parent = null;




		private RECLIST_LAUFZETTEL_TEILNEHMER DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag = null ;




		private RECORD_LAUFZETTEL_PRIO UP_RECORD_LAUFZETTEL_PRIO_id_laufzettel_prio = null;




		private RECORD_LAUFZETTEL_STATUS UP_RECORD_LAUFZETTEL_STATUS_id_laufzettel_status = null;






	
	/**
	 * References the Field ID_USER_ABGESCHLOSSEN_VON
	 * Falls keine this.get_ID_USER_ABGESCHLOSSEN_VON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_abgeschlossen_von() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_ABGESCHLOSSEN_VON_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_abgeschlossen_von==null)
		{
			this.UP_RECORD_USER_id_user_abgeschlossen_von = new RECORD_USER(this.get_ID_USER_ABGESCHLOSSEN_VON_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_abgeschlossen_von;
	}
	





	
	/**
	 * References the Field ID_USER_BEARBEITER
	 * Falls keine this.get_ID_USER_BEARBEITER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_bearbeiter() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_BEARBEITER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_bearbeiter==null)
		{
			this.UP_RECORD_USER_id_user_bearbeiter = new RECORD_USER(this.get_ID_USER_BEARBEITER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_bearbeiter;
	}
	





	
	/**
	 * References the Field ID_USER_BESITZER
	 * Falls keine this.get_ID_USER_BESITZER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_besitzer() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_BESITZER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_besitzer==null)
		{
			this.UP_RECORD_USER_id_user_besitzer = new RECORD_USER(this.get_ID_USER_BESITZER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_besitzer;
	}
	





	
	/**
	 * References the Field ID_LAUFZETTEL
	 * Falls keine this.get_ID_LAUFZETTEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAUFZETTEL get_UP_RECORD_LAUFZETTEL_id_laufzettel() throws myException
	{
		if (S.isEmpty(this.get_ID_LAUFZETTEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAUFZETTEL_id_laufzettel==null)
		{
			this.UP_RECORD_LAUFZETTEL_id_laufzettel = new RECORD_LAUFZETTEL(this.get_ID_LAUFZETTEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAUFZETTEL_id_laufzettel;
	}
	





	/**
	 * References the Field ID_EINTRAG_PARENT 
	 * Falls keine get_ID_LAUFZETTEL_EINTRAG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_eintrag_parent() throws myException
	{
		if (S.isEmpty(this.get_ID_LAUFZETTEL_EINTRAG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_eintrag_parent==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_eintrag_parent = new RECLIST_LAUFZETTEL_EINTRAG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_EINTRAG_PARENT="+this.get_ID_LAUFZETTEL_EINTRAG_cUF()+" ORDER BY ID_LAUFZETTEL_EINTRAG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_eintrag_parent;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINTRAG_PARENT 
	 * Falls keine get_ID_LAUFZETTEL_EINTRAG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_EINTRAG get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_eintrag_parent(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAUFZETTEL_EINTRAG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_eintrag_parent==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE ID_EINTRAG_PARENT="+this.get_ID_LAUFZETTEL_EINTRAG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_eintrag_parent = new RECLIST_LAUFZETTEL_EINTRAG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_EINTRAG_id_eintrag_parent;
	}


	





	
	/**
	 * References the Field ID_EINTRAG_PARENT
	 * Falls keine this.get_ID_EINTRAG_PARENT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAUFZETTEL_EINTRAG get_UP_RECORD_LAUFZETTEL_EINTRAG_id_eintrag_parent() throws myException
	{
		if (S.isEmpty(this.get_ID_EINTRAG_PARENT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAUFZETTEL_EINTRAG_id_eintrag_parent==null)
		{
			this.UP_RECORD_LAUFZETTEL_EINTRAG_id_eintrag_parent = new RECORD_LAUFZETTEL_EINTRAG(this.get_ID_EINTRAG_PARENT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAUFZETTEL_EINTRAG_id_eintrag_parent;
	}
	





	/**
	 * References the Field ID_LAUFZETTEL_EINTRAG 
	 * Falls keine get_ID_LAUFZETTEL_EINTRAG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_TEILNEHMER get_DOWN_RECORD_LIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag() throws myException
	{
		if (S.isEmpty(this.get_ID_LAUFZETTEL_EINTRAG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag==null)
		{
			this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag = new RECLIST_LAUFZETTEL_TEILNEHMER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_TEILNEHMER WHERE ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF()+" ORDER BY ID_LAUFZETTEL_TEILNEHMER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAUFZETTEL_EINTRAG 
	 * Falls keine get_ID_LAUFZETTEL_EINTRAG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAUFZETTEL_TEILNEHMER get_DOWN_RECORD_LIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAUFZETTEL_EINTRAG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAUFZETTEL_TEILNEHMER WHERE ID_LAUFZETTEL_EINTRAG="+this.get_ID_LAUFZETTEL_EINTRAG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag = new RECLIST_LAUFZETTEL_TEILNEHMER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAUFZETTEL_TEILNEHMER_id_laufzettel_eintrag;
	}


	





	
	/**
	 * References the Field ID_LAUFZETTEL_PRIO
	 * Falls keine this.get_ID_LAUFZETTEL_PRIO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAUFZETTEL_PRIO get_UP_RECORD_LAUFZETTEL_PRIO_id_laufzettel_prio() throws myException
	{
		if (S.isEmpty(this.get_ID_LAUFZETTEL_PRIO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAUFZETTEL_PRIO_id_laufzettel_prio==null)
		{
			this.UP_RECORD_LAUFZETTEL_PRIO_id_laufzettel_prio = new RECORD_LAUFZETTEL_PRIO(this.get_ID_LAUFZETTEL_PRIO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAUFZETTEL_PRIO_id_laufzettel_prio;
	}
	





	
	/**
	 * References the Field ID_LAUFZETTEL_STATUS
	 * Falls keine this.get_ID_LAUFZETTEL_STATUS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAUFZETTEL_STATUS get_UP_RECORD_LAUFZETTEL_STATUS_id_laufzettel_status() throws myException
	{
		if (S.isEmpty(this.get_ID_LAUFZETTEL_STATUS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAUFZETTEL_STATUS_id_laufzettel_status==null)
		{
			this.UP_RECORD_LAUFZETTEL_STATUS_id_laufzettel_status = new RECORD_LAUFZETTEL_STATUS(this.get_ID_LAUFZETTEL_STATUS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAUFZETTEL_STATUS_id_laufzettel_status;
	}
	

	public static String FIELD__ABGESCHLOSSEN_AM = "ABGESCHLOSSEN_AM";
	public static String FIELD__ANGELEGT_AM = "ANGELEGT_AM";
	public static String FIELD__AUFGABE = "AUFGABE";
	public static String FIELD__BERICHT = "BERICHT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FAELLIG_AM = "FAELLIG_AM";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GELOESCHT = "GELOESCHT";
	public static String FIELD__ID_EINTRAG_PARENT = "ID_EINTRAG_PARENT";
	public static String FIELD__ID_LAUFZETTEL = "ID_LAUFZETTEL";
	public static String FIELD__ID_LAUFZETTEL_EINTRAG = "ID_LAUFZETTEL_EINTRAG";
	public static String FIELD__ID_LAUFZETTEL_PRIO = "ID_LAUFZETTEL_PRIO";
	public static String FIELD__ID_LAUFZETTEL_STATUS = "ID_LAUFZETTEL_STATUS";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_PARENT_KADENZ = "ID_PARENT_KADENZ";
	public static String FIELD__ID_USER_ABGESCHLOSSEN_VON = "ID_USER_ABGESCHLOSSEN_VON";
	public static String FIELD__ID_USER_BEARBEITER = "ID_USER_BEARBEITER";
	public static String FIELD__ID_USER_BESITZER = "ID_USER_BESITZER";
	public static String FIELD__KADENZ_NACH_ABSCHLUSS = "KADENZ_NACH_ABSCHLUSS";
	public static String FIELD__KADENZ_NACH_FAELLIGKEIT = "KADENZ_NACH_FAELLIGKEIT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__NACHRICHT_SENT = "NACHRICHT_SENT";
	public static String FIELD__PRIVAT = "PRIVAT";
	public static String FIELD__SEND_NACHRICHT = "SEND_NACHRICHT";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_ABGESCHLOSSEN_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_AM");
	}

	public String get_ABGESCHLOSSEN_AM_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_AM");	
	}

	public String get_ABGESCHLOSSEN_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN_AM");
	}

	public String get_ABGESCHLOSSEN_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_AM",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", calNewValueFormated);
	}
		public String get_ANGELEGT_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANGELEGT_AM");
	}

	public String get_ANGELEGT_AM_cF() throws myException
	{
		return this.get_FormatedValue("ANGELEGT_AM");	
	}

	public String get_ANGELEGT_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANGELEGT_AM");
	}

	public String get_ANGELEGT_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANGELEGT_AM",cNotNullValue);
	}

	public String get_ANGELEGT_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANGELEGT_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANGELEGT_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANGELEGT_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANGELEGT_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANGELEGT_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANGELEGT_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANGELEGT_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANGELEGT_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANGELEGT_AM", calNewValueFormated);
	}
		public String get_AUFGABE_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUFGABE");
	}

	public String get_AUFGABE_cF() throws myException
	{
		return this.get_FormatedValue("AUFGABE");	
	}

	public String get_AUFGABE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUFGABE");
	}

	public String get_AUFGABE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUFGABE",cNotNullValue);
	}

	public String get_AUFGABE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUFGABE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUFGABE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUFGABE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUFGABE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUFGABE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFGABE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUFGABE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFGABE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFGABE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFGABE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFGABE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUFGABE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUFGABE", calNewValueFormated);
	}
		public String get_BERICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BERICHT");
	}

	public String get_BERICHT_cF() throws myException
	{
		return this.get_FormatedValue("BERICHT");	
	}

	public String get_BERICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BERICHT");
	}

	public String get_BERICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BERICHT",cNotNullValue);
	}

	public String get_BERICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BERICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BERICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BERICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BERICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BERICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BERICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BERICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BERICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BERICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BERICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BERICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BERICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BERICHT", calNewValueFormated);
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
		public String get_FAELLIG_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAELLIG_AM");
	}

	public String get_FAELLIG_AM_cF() throws myException
	{
		return this.get_FormatedValue("FAELLIG_AM");	
	}

	public String get_FAELLIG_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAELLIG_AM");
	}

	public String get_FAELLIG_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAELLIG_AM",cNotNullValue);
	}

	public String get_FAELLIG_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAELLIG_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAELLIG_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAELLIG_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAELLIG_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAELLIG_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAELLIG_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAELLIG_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAELLIG_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAELLIG_AM", calNewValueFormated);
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
		public String get_GELOESCHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("GELOESCHT");
	}

	public String get_GELOESCHT_cF() throws myException
	{
		return this.get_FormatedValue("GELOESCHT");	
	}

	public String get_GELOESCHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GELOESCHT");
	}

	public String get_GELOESCHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GELOESCHT",cNotNullValue);
	}

	public String get_GELOESCHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GELOESCHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GELOESCHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GELOESCHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GELOESCHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GELOESCHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELOESCHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELOESCHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELOESCHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELOESCHT", calNewValueFormated);
	}
		public boolean is_GELOESCHT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GELOESCHT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GELOESCHT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GELOESCHT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_EINTRAG_PARENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EINTRAG_PARENT");
	}

	public String get_ID_EINTRAG_PARENT_cF() throws myException
	{
		return this.get_FormatedValue("ID_EINTRAG_PARENT");	
	}

	public String get_ID_EINTRAG_PARENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EINTRAG_PARENT");
	}

	public String get_ID_EINTRAG_PARENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EINTRAG_PARENT",cNotNullValue);
	}

	public String get_ID_EINTRAG_PARENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EINTRAG_PARENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EINTRAG_PARENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EINTRAG_PARENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EINTRAG_PARENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINTRAG_PARENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINTRAG_PARENT", calNewValueFormated);
	}
		public Long get_ID_EINTRAG_PARENT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EINTRAG_PARENT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EINTRAG_PARENT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EINTRAG_PARENT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EINTRAG_PARENT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EINTRAG_PARENT").getDoubleValue();
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
	public String get_ID_EINTRAG_PARENT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINTRAG_PARENT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_EINTRAG_PARENT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINTRAG_PARENT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_EINTRAG_PARENT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINTRAG_PARENT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EINTRAG_PARENT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINTRAG_PARENT").getBigDecimalValue();
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
	
	
	public String get_ID_LAUFZETTEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL");
	}

	public String get_ID_LAUFZETTEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL");	
	}

	public String get_ID_LAUFZETTEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAUFZETTEL");
	}

	public String get_ID_LAUFZETTEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL",cNotNullValue);
	}

	public String get_ID_LAUFZETTEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAUFZETTEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAUFZETTEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL", calNewValueFormated);
	}
		public Long get_ID_LAUFZETTEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAUFZETTEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAUFZETTEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAUFZETTEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL").getDoubleValue();
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
	public String get_ID_LAUFZETTEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAUFZETTEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAUFZETTEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAUFZETTEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL").getBigDecimalValue();
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
	
	
	public String get_ID_LAUFZETTEL_EINTRAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL_EINTRAG");
	}

	public String get_ID_LAUFZETTEL_EINTRAG_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL_EINTRAG");	
	}

	public String get_ID_LAUFZETTEL_EINTRAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAUFZETTEL_EINTRAG");
	}

	public String get_ID_LAUFZETTEL_EINTRAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL_EINTRAG",cNotNullValue);
	}

	public String get_ID_LAUFZETTEL_EINTRAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL_EINTRAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL_EINTRAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_EINTRAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_EINTRAG", calNewValueFormated);
	}
		public Long get_ID_LAUFZETTEL_EINTRAG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAUFZETTEL_EINTRAG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAUFZETTEL_EINTRAG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL_EINTRAG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAUFZETTEL_EINTRAG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL_EINTRAG").getDoubleValue();
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
	public String get_ID_LAUFZETTEL_EINTRAG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_EINTRAG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAUFZETTEL_EINTRAG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_EINTRAG_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAUFZETTEL_EINTRAG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL_EINTRAG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAUFZETTEL_EINTRAG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL_EINTRAG").getBigDecimalValue();
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
	
	
	public String get_ID_LAUFZETTEL_PRIO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL_PRIO");
	}

	public String get_ID_LAUFZETTEL_PRIO_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL_PRIO");	
	}

	public String get_ID_LAUFZETTEL_PRIO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAUFZETTEL_PRIO");
	}

	public String get_ID_LAUFZETTEL_PRIO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL_PRIO",cNotNullValue);
	}

	public String get_ID_LAUFZETTEL_PRIO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL_PRIO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL_PRIO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAUFZETTEL_PRIO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_PRIO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_PRIO", calNewValueFormated);
	}
		public Long get_ID_LAUFZETTEL_PRIO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAUFZETTEL_PRIO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAUFZETTEL_PRIO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL_PRIO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAUFZETTEL_PRIO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL_PRIO").getDoubleValue();
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
	public String get_ID_LAUFZETTEL_PRIO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_PRIO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAUFZETTEL_PRIO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_PRIO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAUFZETTEL_PRIO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL_PRIO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAUFZETTEL_PRIO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL_PRIO").getBigDecimalValue();
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
	
	
	public String get_ID_LAUFZETTEL_STATUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL_STATUS");
	}

	public String get_ID_LAUFZETTEL_STATUS_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL_STATUS");	
	}

	public String get_ID_LAUFZETTEL_STATUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAUFZETTEL_STATUS");
	}

	public String get_ID_LAUFZETTEL_STATUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAUFZETTEL_STATUS",cNotNullValue);
	}

	public String get_ID_LAUFZETTEL_STATUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAUFZETTEL_STATUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAUFZETTEL_STATUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAUFZETTEL_STATUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAUFZETTEL_STATUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAUFZETTEL_STATUS", calNewValueFormated);
	}
		public Long get_ID_LAUFZETTEL_STATUS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAUFZETTEL_STATUS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAUFZETTEL_STATUS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL_STATUS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAUFZETTEL_STATUS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAUFZETTEL_STATUS").getDoubleValue();
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
	public String get_ID_LAUFZETTEL_STATUS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_STATUS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAUFZETTEL_STATUS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAUFZETTEL_STATUS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAUFZETTEL_STATUS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL_STATUS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAUFZETTEL_STATUS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAUFZETTEL_STATUS").getBigDecimalValue();
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
	
	
	public String get_ID_PARENT_KADENZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_PARENT_KADENZ");
	}

	public String get_ID_PARENT_KADENZ_cF() throws myException
	{
		return this.get_FormatedValue("ID_PARENT_KADENZ");	
	}

	public String get_ID_PARENT_KADENZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_PARENT_KADENZ");
	}

	public String get_ID_PARENT_KADENZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_PARENT_KADENZ",cNotNullValue);
	}

	public String get_ID_PARENT_KADENZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_PARENT_KADENZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_PARENT_KADENZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_PARENT_KADENZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_PARENT_KADENZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_PARENT_KADENZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_PARENT_KADENZ", calNewValueFormated);
	}
		public Long get_ID_PARENT_KADENZ_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_PARENT_KADENZ").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_PARENT_KADENZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_PARENT_KADENZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_PARENT_KADENZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_PARENT_KADENZ").getDoubleValue();
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
	public String get_ID_PARENT_KADENZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PARENT_KADENZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_PARENT_KADENZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_PARENT_KADENZ_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_PARENT_KADENZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PARENT_KADENZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_PARENT_KADENZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_PARENT_KADENZ").getBigDecimalValue();
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
	
	
	public String get_ID_USER_ABGESCHLOSSEN_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_ABGESCHLOSSEN_VON");
	}

	public String get_ID_USER_ABGESCHLOSSEN_VON_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_ABGESCHLOSSEN_VON");	
	}

	public String get_ID_USER_ABGESCHLOSSEN_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_ABGESCHLOSSEN_VON");
	}

	public String get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_ABGESCHLOSSEN_VON",cNotNullValue);
	}

	public String get_ID_USER_ABGESCHLOSSEN_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_ABGESCHLOSSEN_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ABGESCHLOSSEN_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ABGESCHLOSSEN_VON", calNewValueFormated);
	}
		public Long get_ID_USER_ABGESCHLOSSEN_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_ABGESCHLOSSEN_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_ABGESCHLOSSEN_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_ABGESCHLOSSEN_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_ABGESCHLOSSEN_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_ABGESCHLOSSEN_VON").getDoubleValue();
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
	public String get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_ABGESCHLOSSEN_VON_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_ABGESCHLOSSEN_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_ABGESCHLOSSEN_VON_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_ABGESCHLOSSEN_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_ABGESCHLOSSEN_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_ABGESCHLOSSEN_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_ABGESCHLOSSEN_VON").getBigDecimalValue();
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
	
	
	public String get_ID_USER_BEARBEITER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEARBEITER");
	}

	public String get_ID_USER_BEARBEITER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_BEARBEITER");	
	}

	public String get_ID_USER_BEARBEITER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_BEARBEITER");
	}

	public String get_ID_USER_BEARBEITER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEARBEITER",cNotNullValue);
	}

	public String get_ID_USER_BEARBEITER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_BEARBEITER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_BEARBEITER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEARBEITER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_BEARBEITER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITER", calNewValueFormated);
	}
		public Long get_ID_USER_BEARBEITER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_BEARBEITER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_BEARBEITER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_BEARBEITER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_BEARBEITER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_BEARBEITER").getDoubleValue();
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
	public String get_ID_USER_BEARBEITER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_BEARBEITER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEARBEITER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_BEARBEITER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEARBEITER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_BEARBEITER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEARBEITER").getBigDecimalValue();
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
	
	
	public String get_ID_USER_BESITZER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BESITZER");
	}

	public String get_ID_USER_BESITZER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_BESITZER");	
	}

	public String get_ID_USER_BESITZER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_BESITZER");
	}

	public String get_ID_USER_BESITZER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BESITZER",cNotNullValue);
	}

	public String get_ID_USER_BESITZER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_BESITZER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_BESITZER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BESITZER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_BESITZER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BESITZER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BESITZER", calNewValueFormated);
	}
		public Long get_ID_USER_BESITZER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_BESITZER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_BESITZER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_BESITZER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_BESITZER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_BESITZER").getDoubleValue();
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
	public String get_ID_USER_BESITZER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BESITZER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_USER_BESITZER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BESITZER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_USER_BESITZER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BESITZER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_BESITZER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BESITZER").getBigDecimalValue();
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
	
	
	public String get_KADENZ_NACH_ABSCHLUSS_cUF() throws myException
	{
		return this.get_UnFormatedValue("KADENZ_NACH_ABSCHLUSS");
	}

	public String get_KADENZ_NACH_ABSCHLUSS_cF() throws myException
	{
		return this.get_FormatedValue("KADENZ_NACH_ABSCHLUSS");	
	}

	public String get_KADENZ_NACH_ABSCHLUSS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KADENZ_NACH_ABSCHLUSS");
	}

	public String get_KADENZ_NACH_ABSCHLUSS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KADENZ_NACH_ABSCHLUSS",cNotNullValue);
	}

	public String get_KADENZ_NACH_ABSCHLUSS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KADENZ_NACH_ABSCHLUSS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KADENZ_NACH_ABSCHLUSS", calNewValueFormated);
	}
		public Long get_KADENZ_NACH_ABSCHLUSS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("KADENZ_NACH_ABSCHLUSS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_KADENZ_NACH_ABSCHLUSS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KADENZ_NACH_ABSCHLUSS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KADENZ_NACH_ABSCHLUSS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KADENZ_NACH_ABSCHLUSS").getDoubleValue();
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
	public String get_KADENZ_NACH_ABSCHLUSS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KADENZ_NACH_ABSCHLUSS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_KADENZ_NACH_ABSCHLUSS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KADENZ_NACH_ABSCHLUSS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_KADENZ_NACH_ABSCHLUSS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KADENZ_NACH_ABSCHLUSS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KADENZ_NACH_ABSCHLUSS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KADENZ_NACH_ABSCHLUSS").getBigDecimalValue();
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
	
	
	public String get_KADENZ_NACH_FAELLIGKEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("KADENZ_NACH_FAELLIGKEIT");
	}

	public String get_KADENZ_NACH_FAELLIGKEIT_cF() throws myException
	{
		return this.get_FormatedValue("KADENZ_NACH_FAELLIGKEIT");	
	}

	public String get_KADENZ_NACH_FAELLIGKEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KADENZ_NACH_FAELLIGKEIT");
	}

	public String get_KADENZ_NACH_FAELLIGKEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KADENZ_NACH_FAELLIGKEIT",cNotNullValue);
	}

	public String get_KADENZ_NACH_FAELLIGKEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KADENZ_NACH_FAELLIGKEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KADENZ_NACH_FAELLIGKEIT", calNewValueFormated);
	}
		public boolean is_KADENZ_NACH_FAELLIGKEIT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KADENZ_NACH_FAELLIGKEIT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KADENZ_NACH_FAELLIGKEIT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KADENZ_NACH_FAELLIGKEIT_cUF_NN("N").equals("N"))
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
		public String get_NACHRICHT_SENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("NACHRICHT_SENT");
	}

	public String get_NACHRICHT_SENT_cF() throws myException
	{
		return this.get_FormatedValue("NACHRICHT_SENT");	
	}

	public String get_NACHRICHT_SENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NACHRICHT_SENT");
	}

	public String get_NACHRICHT_SENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NACHRICHT_SENT",cNotNullValue);
	}

	public String get_NACHRICHT_SENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NACHRICHT_SENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NACHRICHT_SENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NACHRICHT_SENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NACHRICHT_SENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NACHRICHT_SENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NACHRICHT_SENT", calNewValueFormated);
	}
		public String get_PRIVAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRIVAT");
	}

	public String get_PRIVAT_cF() throws myException
	{
		return this.get_FormatedValue("PRIVAT");	
	}

	public String get_PRIVAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRIVAT");
	}

	public String get_PRIVAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRIVAT",cNotNullValue);
	}

	public String get_PRIVAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRIVAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRIVAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRIVAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRIVAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRIVAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRIVAT", calNewValueFormated);
	}
		public boolean is_PRIVAT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRIVAT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRIVAT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRIVAT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SEND_NACHRICHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("SEND_NACHRICHT");
	}

	public String get_SEND_NACHRICHT_cF() throws myException
	{
		return this.get_FormatedValue("SEND_NACHRICHT");	
	}

	public String get_SEND_NACHRICHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SEND_NACHRICHT");
	}

	public String get_SEND_NACHRICHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SEND_NACHRICHT",cNotNullValue);
	}

	public String get_SEND_NACHRICHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SEND_NACHRICHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SEND_NACHRICHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SEND_NACHRICHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SEND_NACHRICHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SEND_NACHRICHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SEND_NACHRICHT", calNewValueFormated);
	}
		public boolean is_SEND_NACHRICHT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SEND_NACHRICHT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SEND_NACHRICHT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SEND_NACHRICHT_cUF_NN("N").equals("N"))
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
	
	


	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABGESCHLOSSEN_AM",MyRECORD.DATATYPES.DATE);
	put("ANGELEGT_AM",MyRECORD.DATATYPES.DATE);
	put("AUFGABE",MyRECORD.DATATYPES.TEXT);
	put("BERICHT",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FAELLIG_AM",MyRECORD.DATATYPES.DATE);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GELOESCHT",MyRECORD.DATATYPES.YESNO);
	put("ID_EINTRAG_PARENT",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAUFZETTEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAUFZETTEL_EINTRAG",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAUFZETTEL_PRIO",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAUFZETTEL_STATUS",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_PARENT_KADENZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_ABGESCHLOSSEN_VON",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_BEARBEITER",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_BESITZER",MyRECORD.DATATYPES.NUMBER);
	put("KADENZ_NACH_ABSCHLUSS",MyRECORD.DATATYPES.NUMBER);
	put("KADENZ_NACH_FAELLIGKEIT",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("NACHRICHT_SENT",MyRECORD.DATATYPES.DATE);
	put("PRIVAT",MyRECORD.DATATYPES.YESNO);
	put("SEND_NACHRICHT",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_LAUFZETTEL_EINTRAG.HM_FIELDS;	
	}

}
