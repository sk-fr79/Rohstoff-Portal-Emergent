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

public class RECORD_EAK_CODE extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_EAK_CODE";
    public static String IDFIELD   = "ID_EAK_CODE";
    

	//erzeugen eines RECORDNEW_JT_EAK_CODE - felds
	private RECORDNEW_EAK_CODE   recNEW = null;

    private _TAB  tab = _TAB.eak_code;  



	public RECORD_EAK_CODE() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_EAK_CODE");
	}


	public RECORD_EAK_CODE(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EAK_CODE");
	}



	public RECORD_EAK_CODE(RECORD_EAK_CODE recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EAK_CODE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EAK_CODE.TABLENAME);
	}


	//2014-04-03
	public RECORD_EAK_CODE(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EAK_CODE");
		this.set_Tablename_To_FieldMetaDefs(RECORD_EAK_CODE.TABLENAME);
	}




	public RECORD_EAK_CODE(long lID_Unformated) throws myException
	{
		super("JT_EAK_CODE","ID_EAK_CODE",""+lID_Unformated);
		this.set_TABLE_NAME("JT_EAK_CODE");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EAK_CODE.TABLENAME);
	}

	public RECORD_EAK_CODE(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_EAK_CODE");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EAK_CODE", "ID_EAK_CODE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EAK_CODE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EAK_CODE.TABLENAME);
	}
	
	

	public RECORD_EAK_CODE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_EAK_CODE","ID_EAK_CODE",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_EAK_CODE");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EAK_CODE.TABLENAME);

	}


	public RECORD_EAK_CODE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EAK_CODE");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EAK_CODE", "ID_EAK_CODE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EAK_CODE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EAK_CODE.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_EAK_CODE();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_EAK_CODE.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_EAK_CODE";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_EAK_CODE_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_EAK_CODE_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EAK_CODE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EAK_CODE", bibE2.cTO(), "ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EAK_CODE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EAK_CODE", bibE2.cTO(), "ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_EAK_CODE WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_EAK_CODE WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
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
			if (S.isFull(this.get_ID_EAK_CODE_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EAK_CODE", "ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_EAK_CODE get_RECORDNEW_EAK_CODE() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_EAK_CODE();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_EAK_CODE(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_EAK_CODE create_Instance() throws myException {
		return new RECORD_EAK_CODE();
	}
	
	public static RECORD_EAK_CODE create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_EAK_CODE(Conn);
    }

	public static RECORD_EAK_CODE create_Instance(RECORD_EAK_CODE recordOrig) {
		return new RECORD_EAK_CODE(recordOrig);
    }

	public static RECORD_EAK_CODE create_Instance(long lID_Unformated) throws myException {
		return new RECORD_EAK_CODE(lID_Unformated);
    }

	public static RECORD_EAK_CODE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_EAK_CODE(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_EAK_CODE create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_EAK_CODE(lID_Unformated, Conn);
	}

	public static RECORD_EAK_CODE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_EAK_CODE(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_EAK_CODE create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_EAK_CODE(recordOrig);	    
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
    public RECORD_EAK_CODE build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_EAK_CODE WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF());
      }
      //return new RECORD_EAK_CODE(this.get_cSQL_4_Build());
      RECORD_EAK_CODE rec = new RECORD_EAK_CODE();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_EAK_CODE
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_EAK_CODE-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_EAK_CODE get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_EAK_CODE_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_EAK_CODE  recNew = new RECORDNEW_EAK_CODE();
		
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
    public RECORD_EAK_CODE set_recordNew(RECORDNEW_EAK_CODE recnew) throws myException {
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
	
	



		private RECLIST_ADRESSE_EAK_CODE DOWN_RECLIST_ADRESSE_EAK_CODE_id_eak_code = null ;




		private RECLIST_ARTIKEL DOWN_RECLIST_ARTIKEL_id_eak_code = null ;




		private RECLIST_ARTIKEL DOWN_RECLIST_ARTIKEL_id_eak_code_ex_mandant = null ;




		private RECLIST_ARTIKELBEZ_LIEF DOWN_RECLIST_ARTIKELBEZ_LIEF_id_eak_code = null ;




		private RECLIST_BEWEGUNG_VEKTOR DOWN_RECLIST_BEWEGUNG_VEKTOR_id_eak_code = null ;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_eak_code = null ;




		private RECLIST_EAK_CODE_SP DOWN_RECLIST_EAK_CODE_SP_id_eak_code = null ;




		private RECLIST_VPOS_KON_AVV DOWN_RECLIST_VPOS_KON_AVV_id_eak_code = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_eak_code = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_eak_code = null ;




		private RECORD_EAK_GRUPPE UP_RECORD_EAK_GRUPPE_id_eak_gruppe = null;






	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_EAK_CODE get_DOWN_RECORD_LIST_ADRESSE_EAK_CODE_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_eak_code==null)
		{
			this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_eak_code = new RECLIST_ADRESSE_EAK_CODE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_EAK_CODE WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_ADRESSE_EAK_CODE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_EAK_CODE get_DOWN_RECORD_LIST_ADRESSE_EAK_CODE_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_EAK_CODE WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_eak_code = new RECLIST_ADRESSE_EAK_CODE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_EAK_CODE_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_eak_code==null)
		{
			this.DOWN_RECLIST_ARTIKEL_id_eak_code = new RECLIST_ARTIKEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_ARTIKEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_id_eak_code = new RECLIST_ARTIKEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE_EX_MANDANT 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_eak_code_ex_mandant() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_eak_code_ex_mandant==null)
		{
			this.DOWN_RECLIST_ARTIKEL_id_eak_code_ex_mandant = new RECLIST_ARTIKEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EAK_CODE_EX_MANDANT="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_ARTIKEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_eak_code_ex_mandant;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE_EX_MANDANT 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_eak_code_ex_mandant(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_eak_code_ex_mandant==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EAK_CODE_EX_MANDANT="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_id_eak_code_ex_mandant = new RECLIST_ARTIKEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_eak_code_ex_mandant;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_eak_code==null)
		{
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_eak_code = new RECLIST_ARTIKELBEZ_LIEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_ARTIKELBEZ_LIEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_eak_code = new RECLIST_ARTIKELBEZ_LIEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_eak_code==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_eak_code = new RECLIST_BEWEGUNG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_eak_code = new RECLIST_BEWEGUNG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_eak_code==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_eak_code = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_eak_code = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EAK_CODE_SP get_DOWN_RECORD_LIST_EAK_CODE_SP_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EAK_CODE_SP_id_eak_code==null)
		{
			this.DOWN_RECLIST_EAK_CODE_SP_id_eak_code = new RECLIST_EAK_CODE_SP (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EAK_CODE_SP WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_EAK_CODE_SP",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EAK_CODE_SP_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EAK_CODE_SP get_DOWN_RECORD_LIST_EAK_CODE_SP_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EAK_CODE_SP_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EAK_CODE_SP WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EAK_CODE_SP_id_eak_code = new RECLIST_EAK_CODE_SP (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EAK_CODE_SP_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_AVV get_DOWN_RECORD_LIST_VPOS_KON_AVV_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_AVV_id_eak_code==null)
		{
			this.DOWN_RECLIST_VPOS_KON_AVV_id_eak_code = new RECLIST_VPOS_KON_AVV (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_AVV WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_VPOS_KON_AVV",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_AVV_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_AVV get_DOWN_RECORD_LIST_VPOS_KON_AVV_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_AVV_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_AVV WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_AVV_id_eak_code = new RECLIST_VPOS_KON_AVV (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_AVV_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_eak_code==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_eak_code = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_eak_code = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_eak_code;
	}


	





	/**
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_eak_code==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_eak_code = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_eak_code;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EAK_CODE 
	 * Falls keine get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_eak_code(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_eak_code==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_eak_code = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_eak_code;
	}


	





	
	/**
	 * References the Field ID_EAK_GRUPPE
	 * Falls keine this.get_ID_EAK_GRUPPE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_EAK_GRUPPE get_UP_RECORD_EAK_GRUPPE_id_eak_gruppe() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_GRUPPE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_EAK_GRUPPE_id_eak_gruppe==null)
		{
			this.UP_RECORD_EAK_GRUPPE_id_eak_gruppe = new RECORD_EAK_GRUPPE(this.get_ID_EAK_GRUPPE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_EAK_GRUPPE_id_eak_gruppe;
	}
	

	public static String FIELD__CODE = "CODE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEFAEHRLICH = "GEFAEHRLICH";
	public static String FIELD__ID_EAK_CODE = "ID_EAK_CODE";
	public static String FIELD__ID_EAK_GRUPPE = "ID_EAK_GRUPPE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__KEY_CODE = "KEY_CODE";
	public static String FIELD__LAGERUNG_OK = "LAGERUNG_OK";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TRANSPORT_OK = "TRANSPORT_OK";
	public static String FIELD__VERARBEITUNG_OK = "VERARBEITUNG_OK";


	public String get_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("CODE");
	}

	public String get_CODE_cF() throws myException
	{
		return this.get_FormatedValue("CODE");	
	}

	public String get_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("CODE");
	}

	public String get_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("CODE",cNotNullValue);
	}

	public String get_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CODE", calNewValueFormated);
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
		public String get_GEFAEHRLICH_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEFAEHRLICH");
	}

	public String get_GEFAEHRLICH_cF() throws myException
	{
		return this.get_FormatedValue("GEFAEHRLICH");	
	}

	public String get_GEFAEHRLICH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEFAEHRLICH");
	}

	public String get_GEFAEHRLICH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEFAEHRLICH",cNotNullValue);
	}

	public String get_GEFAEHRLICH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEFAEHRLICH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEFAEHRLICH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEFAEHRLICH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEFAEHRLICH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEFAEHRLICH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEFAEHRLICH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEFAEHRLICH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAEHRLICH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEFAEHRLICH", calNewValueFormated);
	}
		public boolean is_GEFAEHRLICH_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GEFAEHRLICH_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GEFAEHRLICH_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GEFAEHRLICH_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_EAK_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE");
	}

	public String get_ID_EAK_CODE_cF() throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE");	
	}

	public String get_ID_EAK_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EAK_CODE");
	}

	public String get_ID_EAK_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE",cNotNullValue);
	}

	public String get_ID_EAK_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public Long get_ID_EAK_CODE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EAK_CODE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EAK_CODE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EAK_CODE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE").getDoubleValue();
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
	public String get_ID_EAK_CODE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EAK_CODE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EAK_CODE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EAK_CODE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE").getBigDecimalValue();
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
	
	
	public String get_ID_EAK_GRUPPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_GRUPPE");
	}

	public String get_ID_EAK_GRUPPE_cF() throws myException
	{
		return this.get_FormatedValue("ID_EAK_GRUPPE");	
	}

	public String get_ID_EAK_GRUPPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EAK_GRUPPE");
	}

	public String get_ID_EAK_GRUPPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_GRUPPE",cNotNullValue);
	}

	public String get_ID_EAK_GRUPPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EAK_GRUPPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EAK_GRUPPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_GRUPPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EAK_GRUPPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_GRUPPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_GRUPPE", calNewValueFormated);
	}
		public Long get_ID_EAK_GRUPPE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EAK_GRUPPE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EAK_GRUPPE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EAK_GRUPPE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EAK_GRUPPE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EAK_GRUPPE").getDoubleValue();
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
	public String get_ID_EAK_GRUPPE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_GRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EAK_GRUPPE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_GRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EAK_GRUPPE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_GRUPPE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EAK_GRUPPE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_GRUPPE").getBigDecimalValue();
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
	
	
	public String get_KEY_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KEY_CODE");
	}

	public String get_KEY_CODE_cF() throws myException
	{
		return this.get_FormatedValue("KEY_CODE");	
	}

	public String get_KEY_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KEY_CODE");
	}

	public String get_KEY_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KEY_CODE",cNotNullValue);
	}

	public String get_KEY_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KEY_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KEY_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KEY_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KEY_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KEY_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEY_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEY_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KEY_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KEY_CODE", calNewValueFormated);
	}
		public String get_LAGERUNG_OK_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAGERUNG_OK");
	}

	public String get_LAGERUNG_OK_cF() throws myException
	{
		return this.get_FormatedValue("LAGERUNG_OK");	
	}

	public String get_LAGERUNG_OK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAGERUNG_OK");
	}

	public String get_LAGERUNG_OK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAGERUNG_OK",cNotNullValue);
	}

	public String get_LAGERUNG_OK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAGERUNG_OK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAGERUNG_OK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAGERUNG_OK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAGERUNG_OK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAGERUNG_OK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERUNG_OK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERUNG_OK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERUNG_OK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERUNG_OK", calNewValueFormated);
	}
		public boolean is_LAGERUNG_OK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_LAGERUNG_OK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_LAGERUNG_OK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_LAGERUNG_OK_cUF_NN("N").equals("N"))
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
	
	
	public String get_TRANSPORT_OK_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSPORT_OK");
	}

	public String get_TRANSPORT_OK_cF() throws myException
	{
		return this.get_FormatedValue("TRANSPORT_OK");	
	}

	public String get_TRANSPORT_OK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSPORT_OK");
	}

	public String get_TRANSPORT_OK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSPORT_OK",cNotNullValue);
	}

	public String get_TRANSPORT_OK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSPORT_OK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSPORT_OK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSPORT_OK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSPORT_OK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSPORT_OK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORT_OK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORT_OK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORT_OK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORT_OK", calNewValueFormated);
	}
		public boolean is_TRANSPORT_OK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSPORT_OK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSPORT_OK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSPORT_OK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_VERARBEITUNG_OK_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERARBEITUNG_OK");
	}

	public String get_VERARBEITUNG_OK_cF() throws myException
	{
		return this.get_FormatedValue("VERARBEITUNG_OK");	
	}

	public String get_VERARBEITUNG_OK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERARBEITUNG_OK");
	}

	public String get_VERARBEITUNG_OK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERARBEITUNG_OK",cNotNullValue);
	}

	public String get_VERARBEITUNG_OK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERARBEITUNG_OK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERARBEITUNG_OK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERARBEITUNG_OK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERARBEITUNG_OK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNG_OK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERARBEITUNG_OK", calNewValueFormated);
	}
		public boolean is_VERARBEITUNG_OK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_VERARBEITUNG_OK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_VERARBEITUNG_OK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_VERARBEITUNG_OK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("CODE",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEFAEHRLICH",MyRECORD.DATATYPES.YESNO);
	put("ID_EAK_CODE",MyRECORD.DATATYPES.NUMBER);
	put("ID_EAK_GRUPPE",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("KEY_CODE",MyRECORD.DATATYPES.TEXT);
	put("LAGERUNG_OK",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TRANSPORT_OK",MyRECORD.DATATYPES.YESNO);
	put("VERARBEITUNG_OK",MyRECORD.DATATYPES.YESNO);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_EAK_CODE.HM_FIELDS;	
	}

}
