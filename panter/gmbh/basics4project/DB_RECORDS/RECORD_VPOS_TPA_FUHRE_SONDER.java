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

public class RECORD_VPOS_TPA_FUHRE_SONDER extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_VPOS_TPA_FUHRE_SONDER";
    public static String IDFIELD   = "ID_VPOS_TPA_FUHRE_SONDER";
    

	//erzeugen eines RECORDNEW_JT_VPOS_TPA_FUHRE_SONDER - felds
	private RECORDNEW_VPOS_TPA_FUHRE_SONDER   recNEW = null;

    private _TAB  tab = _TAB.vpos_tpa_fuhre_sonder;  



	public RECORD_VPOS_TPA_FUHRE_SONDER() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");
	}


	public RECORD_VPOS_TPA_FUHRE_SONDER(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");
	}



	public RECORD_VPOS_TPA_FUHRE_SONDER(RECORD_VPOS_TPA_FUHRE_SONDER recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_SONDER.TABLENAME);
	}


	//2014-04-03
	public RECORD_VPOS_TPA_FUHRE_SONDER(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_SONDER.TABLENAME);
	}




	public RECORD_VPOS_TPA_FUHRE_SONDER(long lID_Unformated) throws myException
	{
		super("JT_VPOS_TPA_FUHRE_SONDER","ID_VPOS_TPA_FUHRE_SONDER",""+lID_Unformated);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_SONDER.TABLENAME);
	}

	public RECORD_VPOS_TPA_FUHRE_SONDER(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER", "ID_VPOS_TPA_FUHRE_SONDER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_SONDER.TABLENAME);
	}
	
	

	public RECORD_VPOS_TPA_FUHRE_SONDER(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_VPOS_TPA_FUHRE_SONDER","ID_VPOS_TPA_FUHRE_SONDER",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_SONDER.TABLENAME);

	}


	public RECORD_VPOS_TPA_FUHRE_SONDER(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_TPA_FUHRE_SONDER");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER", "ID_VPOS_TPA_FUHRE_SONDER="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_TPA_FUHRE_SONDER.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_VPOS_TPA_FUHRE_SONDER();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_VPOS_TPA_FUHRE_SONDER.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_VPOS_TPA_FUHRE_SONDER";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_SONDER_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_TPA_FUHRE_SONDER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_SONDER", bibE2.cTO(), "ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_TPA_FUHRE_SONDER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_SONDER", bibE2.cTO(), "ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF();
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
			if (S.isFull(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER", "ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_VPOS_TPA_FUHRE_SONDER get_RECORDNEW_VPOS_TPA_FUHRE_SONDER() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_VPOS_TPA_FUHRE_SONDER();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_VPOS_TPA_FUHRE_SONDER(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance() throws myException {
		return new RECORD_VPOS_TPA_FUHRE_SONDER();
	}
	
	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_VPOS_TPA_FUHRE_SONDER(Conn);
    }

	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance(RECORD_VPOS_TPA_FUHRE_SONDER recordOrig) {
		return new RECORD_VPOS_TPA_FUHRE_SONDER(recordOrig);
    }

	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance(long lID_Unformated) throws myException {
		return new RECORD_VPOS_TPA_FUHRE_SONDER(lID_Unformated);
    }

	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_VPOS_TPA_FUHRE_SONDER(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_VPOS_TPA_FUHRE_SONDER(lID_Unformated, Conn);
	}

	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_VPOS_TPA_FUHRE_SONDER(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_VPOS_TPA_FUHRE_SONDER create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_VPOS_TPA_FUHRE_SONDER(recordOrig);	    
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
    public RECORD_VPOS_TPA_FUHRE_SONDER build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF());
      }
      //return new RECORD_VPOS_TPA_FUHRE_SONDER(this.get_cSQL_4_Build());
      RECORD_VPOS_TPA_FUHRE_SONDER rec = new RECORD_VPOS_TPA_FUHRE_SONDER();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_VPOS_TPA_FUHRE_SONDER
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_VPOS_TPA_FUHRE_SONDER-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_VPOS_TPA_FUHRE_SONDER get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_VPOS_TPA_FUHRE_SONDER_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_VPOS_TPA_FUHRE_SONDER  recNew = new RECORDNEW_VPOS_TPA_FUHRE_SONDER();
		
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
    public RECORD_VPOS_TPA_FUHRE_SONDER set_recordNew(RECORDNEW_VPOS_TPA_FUHRE_SONDER recnew) throws myException {
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
	
	



		private RECLIST_FUHRE_SONDER_VL DOWN_RECLIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder = null ;






	/**
	 * References the Field ID_VPOS_TPA_FUHRE_SONDER 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_SONDER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHRE_SONDER_VL get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder==null)
		{
			this.DOWN_RECLIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder = new RECLIST_FUHRE_SONDER_VL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FUHRE_SONDER_VL WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()+" ORDER BY ID_FUHRE_SONDER_VL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_SONDER 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_SONDER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHRE_SONDER_VL get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FUHRE_SONDER_VL WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder = new RECLIST_FUHRE_SONDER_VL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_SONDER 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_SONDER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_SONDER 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_SONDER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_sonder;
	}


	





	/**
	 * References the Field ID_VPOS_TPA_FUHRE_SONDER 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_SONDER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_TPA_FUHRE_SONDER 
	 * Falls keine get_ID_VPOS_TPA_FUHRE_SONDER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_SONDER="+this.get_ID_VPOS_TPA_FUHRE_SONDER_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_sonder;
	}


	

	public static String FIELD__AUSNAHME = "AUSNAHME";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VPOS_TPA_FUHRE_SONDER = "ID_VPOS_TPA_FUHRE_SONDER";
	public static String FIELD__KEIN_FREMDWAREN_LAGER_CHECK = "KEIN_FREMDWAREN_LAGER_CHECK";
	public static String FIELD__KEIN_KONTRAKT_NOETIG = "KEIN_KONTRAKT_NOETIG";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__OHNE_ABRECHNUNG = "OHNE_ABRECHNUNG";
	public static String FIELD__OHNE_AVV_VERTRAG_CHECK = "OHNE_AVV_VERTRAG_CHECK";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_AUSNAHME_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUSNAHME");
	}

	public String get_AUSNAHME_cF() throws myException
	{
		return this.get_FormatedValue("AUSNAHME");	
	}

	public String get_AUSNAHME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUSNAHME");
	}

	public String get_AUSNAHME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUSNAHME",cNotNullValue);
	}

	public String get_AUSNAHME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUSNAHME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUSNAHME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUSNAHME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUSNAHME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUSNAHME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSNAHME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSNAHME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSNAHME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSNAHME", calNewValueFormated);
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_SONDER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_SONDER");
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_SONDER");	
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_SONDER");
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_SONDER",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_SONDER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_SONDER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_SONDER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_SONDER", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_SONDER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_SONDER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_SONDER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_SONDER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_SONDER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_SONDER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_SONDER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_SONDER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_SONDER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_SONDER").getBigDecimalValue();
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
	
	
	public String get_KEIN_FREMDWAREN_LAGER_CHECK_cUF() throws myException
	{
		return this.get_UnFormatedValue("KEIN_FREMDWAREN_LAGER_CHECK");
	}

	public String get_KEIN_FREMDWAREN_LAGER_CHECK_cF() throws myException
	{
		return this.get_FormatedValue("KEIN_FREMDWAREN_LAGER_CHECK");	
	}

	public String get_KEIN_FREMDWAREN_LAGER_CHECK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KEIN_FREMDWAREN_LAGER_CHECK");
	}

	public String get_KEIN_FREMDWAREN_LAGER_CHECK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KEIN_FREMDWAREN_LAGER_CHECK",cNotNullValue);
	}

	public String get_KEIN_FREMDWAREN_LAGER_CHECK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KEIN_FREMDWAREN_LAGER_CHECK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_FREMDWAREN_LAGER_CHECK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_FREMDWAREN_LAGER_CHECK", calNewValueFormated);
	}
		public boolean is_KEIN_FREMDWAREN_LAGER_CHECK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEIN_FREMDWAREN_LAGER_CHECK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KEIN_FREMDWAREN_LAGER_CHECK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEIN_FREMDWAREN_LAGER_CHECK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KEIN_KONTRAKT_NOETIG_cUF() throws myException
	{
		return this.get_UnFormatedValue("KEIN_KONTRAKT_NOETIG");
	}

	public String get_KEIN_KONTRAKT_NOETIG_cF() throws myException
	{
		return this.get_FormatedValue("KEIN_KONTRAKT_NOETIG");	
	}

	public String get_KEIN_KONTRAKT_NOETIG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KEIN_KONTRAKT_NOETIG");
	}

	public String get_KEIN_KONTRAKT_NOETIG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KEIN_KONTRAKT_NOETIG",cNotNullValue);
	}

	public String get_KEIN_KONTRAKT_NOETIG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KEIN_KONTRAKT_NOETIG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KEIN_KONTRAKT_NOETIG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEIN_KONTRAKT_NOETIG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEIN_KONTRAKT_NOETIG", calNewValueFormated);
	}
		public boolean is_KEIN_KONTRAKT_NOETIG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEIN_KONTRAKT_NOETIG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_KEIN_KONTRAKT_NOETIG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_KEIN_KONTRAKT_NOETIG_cUF_NN("N").equals("N"))
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
		public String get_OHNE_ABRECHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("OHNE_ABRECHNUNG");
	}

	public String get_OHNE_ABRECHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("OHNE_ABRECHNUNG");	
	}

	public String get_OHNE_ABRECHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OHNE_ABRECHNUNG");
	}

	public String get_OHNE_ABRECHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OHNE_ABRECHNUNG",cNotNullValue);
	}

	public String get_OHNE_ABRECHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OHNE_ABRECHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OHNE_ABRECHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OHNE_ABRECHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_ABRECHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_ABRECHNUNG", calNewValueFormated);
	}
		public boolean is_OHNE_ABRECHNUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_ABRECHNUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OHNE_ABRECHNUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_ABRECHNUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_OHNE_AVV_VERTRAG_CHECK_cUF() throws myException
	{
		return this.get_UnFormatedValue("OHNE_AVV_VERTRAG_CHECK");
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_cF() throws myException
	{
		return this.get_FormatedValue("OHNE_AVV_VERTRAG_CHECK");	
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OHNE_AVV_VERTRAG_CHECK");
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OHNE_AVV_VERTRAG_CHECK",cNotNullValue);
	}

	public String get_OHNE_AVV_VERTRAG_CHECK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OHNE_AVV_VERTRAG_CHECK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OHNE_AVV_VERTRAG_CHECK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OHNE_AVV_VERTRAG_CHECK", calNewValueFormated);
	}
		public boolean is_OHNE_AVV_VERTRAG_CHECK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_AVV_VERTRAG_CHECK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_OHNE_AVV_VERTRAG_CHECK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_OHNE_AVV_VERTRAG_CHECK_cUF_NN("N").equals("N"))
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
	put("AUSNAHME",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_SONDER",MyRECORD.DATATYPES.NUMBER);
	put("KEIN_FREMDWAREN_LAGER_CHECK",MyRECORD.DATATYPES.YESNO);
	put("KEIN_KONTRAKT_NOETIG",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("OHNE_ABRECHNUNG",MyRECORD.DATATYPES.YESNO);
	put("OHNE_AVV_VERTRAG_CHECK",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_VPOS_TPA_FUHRE_SONDER.HM_FIELDS;	
	}

}
