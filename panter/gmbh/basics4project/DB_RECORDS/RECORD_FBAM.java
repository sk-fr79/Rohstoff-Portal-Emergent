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

public class RECORD_FBAM extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_FBAM";
    public static String IDFIELD   = "ID_FBAM";
    

	//erzeugen eines RECORDNEW_JT_FBAM - felds
	private RECORDNEW_FBAM   recNEW = null;

    private _TAB  tab = _TAB.fbam;  



	public RECORD_FBAM() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_FBAM");
	}


	public RECORD_FBAM(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FBAM");
	}



	public RECORD_FBAM(RECORD_FBAM recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FBAM");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FBAM.TABLENAME);
	}


	//2014-04-03
	public RECORD_FBAM(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_FBAM");
		this.set_Tablename_To_FieldMetaDefs(RECORD_FBAM.TABLENAME);
	}




	public RECORD_FBAM(long lID_Unformated) throws myException
	{
		super("JT_FBAM","ID_FBAM",""+lID_Unformated);
		this.set_TABLE_NAME("JT_FBAM");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FBAM.TABLENAME);
	}

	public RECORD_FBAM(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_FBAM");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FBAM", "ID_FBAM="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FBAM", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FBAM.TABLENAME);
	}
	
	

	public RECORD_FBAM(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_FBAM","ID_FBAM",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_FBAM");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FBAM.TABLENAME);

	}


	public RECORD_FBAM(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_FBAM");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_FBAM", "ID_FBAM="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FBAM", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_FBAM.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_FBAM();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_FBAM.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_FBAM";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_FBAM_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_FBAM_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FBAM was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FBAM", bibE2.cTO(), "ID_FBAM="+this.get_ID_FBAM_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_FBAM was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_FBAM", bibE2.cTO(), "ID_FBAM="+this.get_ID_FBAM_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_FBAM="+this.get_ID_FBAM_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_FBAM="+this.get_ID_FBAM_cUF();
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
			if (S.isFull(this.get_ID_FBAM_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_FBAM", "ID_FBAM="+this.get_ID_FBAM_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_FBAM get_RECORDNEW_FBAM() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_FBAM();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_FBAM(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_FBAM create_Instance() throws myException {
		return new RECORD_FBAM();
	}
	
	public static RECORD_FBAM create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_FBAM(Conn);
    }

	public static RECORD_FBAM create_Instance(RECORD_FBAM recordOrig) {
		return new RECORD_FBAM(recordOrig);
    }

	public static RECORD_FBAM create_Instance(long lID_Unformated) throws myException {
		return new RECORD_FBAM(lID_Unformated);
    }

	public static RECORD_FBAM create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_FBAM(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_FBAM create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_FBAM(lID_Unformated, Conn);
	}

	public static RECORD_FBAM create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_FBAM(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_FBAM create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_FBAM(recordOrig);	    
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
    public RECORD_FBAM build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_FBAM="+this.get_ID_FBAM_cUF());
      }
      //return new RECORD_FBAM(this.get_cSQL_4_Build());
      RECORD_FBAM rec = new RECORD_FBAM();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_FBAM
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_FBAM-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_FBAM get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_FBAM_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_FBAM  recNew = new RECORDNEW_FBAM();
		
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
    public RECORD_FBAM set_recordNew(RECORDNEW_FBAM recnew) throws myException {
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
	
	



		private RECORD_USER UP_RECORD_USER_id_user_ausstellung = null;




		private RECORD_USER UP_RECORD_USER_id_user_behebung = null;




		private RECORD_USER UP_RECORD_USER_id_user_kontrolle = null;




		private RECLIST_FBAM_DRUCK DOWN_RECLIST_FBAM_DRUCK_id_fbam = null ;




		private RECLIST_FBAM_INFOBLOCK DOWN_RECLIST_FBAM_INFOBLOCK_id_fbam = null ;




		private RECLIST_FBAM_USER DOWN_RECLIST_FBAM_USER_id_fbam = null ;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre = null;




		private RECORD_VPOS_TPA_FUHRE_ORT UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort = null;






	
	/**
	 * References the Field ID_USER_AUSSTELLUNG
	 * Falls keine this.get_ID_USER_AUSSTELLUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_ausstellung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_AUSSTELLUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_ausstellung==null)
		{
			this.UP_RECORD_USER_id_user_ausstellung = new RECORD_USER(this.get_ID_USER_AUSSTELLUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_ausstellung;
	}
	





	
	/**
	 * References the Field ID_USER_BEHEBUNG
	 * Falls keine this.get_ID_USER_BEHEBUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_behebung() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_BEHEBUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_behebung==null)
		{
			this.UP_RECORD_USER_id_user_behebung = new RECORD_USER(this.get_ID_USER_BEHEBUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_behebung;
	}
	





	
	/**
	 * References the Field ID_USER_KONTROLLE
	 * Falls keine this.get_ID_USER_KONTROLLE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_kontrolle() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_KONTROLLE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_kontrolle==null)
		{
			this.UP_RECORD_USER_id_user_kontrolle = new RECORD_USER(this.get_ID_USER_KONTROLLE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_kontrolle;
	}
	





	/**
	 * References the Field ID_FBAM 
	 * Falls keine get_ID_FBAM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_DRUCK get_DOWN_RECORD_LIST_FBAM_DRUCK_id_fbam() throws myException
	{
		if (S.isEmpty(this.get_ID_FBAM_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_DRUCK_id_fbam==null)
		{
			this.DOWN_RECLIST_FBAM_DRUCK_id_fbam = new RECLIST_FBAM_DRUCK (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_DRUCK WHERE ID_FBAM="+this.get_ID_FBAM_cUF()+" ORDER BY ID_FBAM_DRUCK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_DRUCK_id_fbam;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_FBAM 
	 * Falls keine get_ID_FBAM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_DRUCK get_DOWN_RECORD_LIST_FBAM_DRUCK_id_fbam(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_FBAM_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_DRUCK_id_fbam==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_DRUCK WHERE ID_FBAM="+this.get_ID_FBAM_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_DRUCK_id_fbam = new RECLIST_FBAM_DRUCK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_DRUCK_id_fbam;
	}


	





	/**
	 * References the Field ID_FBAM 
	 * Falls keine get_ID_FBAM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_INFOBLOCK get_DOWN_RECORD_LIST_FBAM_INFOBLOCK_id_fbam() throws myException
	{
		if (S.isEmpty(this.get_ID_FBAM_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_INFOBLOCK_id_fbam==null)
		{
			this.DOWN_RECLIST_FBAM_INFOBLOCK_id_fbam = new RECLIST_FBAM_INFOBLOCK (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_INFOBLOCK WHERE ID_FBAM="+this.get_ID_FBAM_cUF()+" ORDER BY ID_FBAM_INFOBLOCK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_INFOBLOCK_id_fbam;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_FBAM 
	 * Falls keine get_ID_FBAM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_INFOBLOCK get_DOWN_RECORD_LIST_FBAM_INFOBLOCK_id_fbam(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_FBAM_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_INFOBLOCK_id_fbam==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_INFOBLOCK WHERE ID_FBAM="+this.get_ID_FBAM_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_INFOBLOCK_id_fbam = new RECLIST_FBAM_INFOBLOCK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_INFOBLOCK_id_fbam;
	}


	





	/**
	 * References the Field ID_FBAM 
	 * Falls keine get_ID_FBAM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_USER get_DOWN_RECORD_LIST_FBAM_USER_id_fbam() throws myException
	{
		if (S.isEmpty(this.get_ID_FBAM_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_USER_id_fbam==null)
		{
			this.DOWN_RECLIST_FBAM_USER_id_fbam = new RECLIST_FBAM_USER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_USER WHERE ID_FBAM="+this.get_ID_FBAM_cUF()+" ORDER BY ID_FBAM_USER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_USER_id_fbam;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_FBAM 
	 * Falls keine get_ID_FBAM_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FBAM_USER get_DOWN_RECORD_LIST_FBAM_USER_id_fbam(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_FBAM_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FBAM_USER_id_fbam==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FBAM_USER WHERE ID_FBAM="+this.get_ID_FBAM_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FBAM_USER_id_fbam = new RECLIST_FBAM_USER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FBAM_USER_id_fbam;
	}


	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre = new RECORD_VPOS_TPA_FUHRE(this.get_ID_VPOS_TPA_FUHRE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre;
	}
	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ORT
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_ORT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE_ORT get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort = new RECORD_VPOS_TPA_FUHRE_ORT(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort;
	}
	

	public static String FIELD__ABGESCHLOSSEN_BEHEBUNG = "ABGESCHLOSSEN_BEHEBUNG";
	public static String FIELD__ABGESCHLOSSEN_KONTROLLE = "ABGESCHLOSSEN_KONTROLLE";
	public static String FIELD__AUSWIRKUNGEN = "AUSWIRKUNGEN";
	public static String FIELD__BAM_DATUM = "BAM_DATUM";
	public static String FIELD__BAM_GRUND = "BAM_GRUND";
	public static String FIELD__BAM_ORT = "BAM_ORT";
	public static String FIELD__BEARBEITUNG = "BEARBEITUNG";
	public static String FIELD__BEARBEITUNG_DATUM = "BEARBEITUNG_DATUM";
	public static String FIELD__BEHEB_VERMERK = "BEHEB_VERMERK";
	public static String FIELD__BEHEB_VORSCHLAG = "BEHEB_VORSCHLAG";
	public static String FIELD__BETREFF_BAM = "BETREFF_BAM";
	public static String FIELD__BUCHUNGSNUMMER = "BUCHUNGSNUMMER";
	public static String FIELD__DATUM_AUSSTELLUNG = "DATUM_AUSSTELLUNG";
	public static String FIELD__DATUM_BEHEBUNG = "DATUM_BEHEBUNG";
	public static String FIELD__DATUM_KONTROLLE = "DATUM_KONTROLLE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FEHLERBESCHREIBUNG = "FEHLERBESCHREIBUNG";
	public static String FIELD__FEHLERINFO_EXTERN = "FEHLERINFO_EXTERN";
	public static String FIELD__FEHLERURSACHE = "FEHLERURSACHE";
	public static String FIELD__FESTSTELLUNG_BAM = "FESTSTELLUNG_BAM";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_FBAM = "ID_FBAM";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_USER_AUSSTELLUNG = "ID_USER_AUSSTELLUNG";
	public static String FIELD__ID_USER_BEARBEITUNG = "ID_USER_BEARBEITUNG";
	public static String FIELD__ID_USER_BEHEBUNG = "ID_USER_BEHEBUNG";
	public static String FIELD__ID_USER_KONTROLLE = "ID_USER_KONTROLLE";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ORT = "ID_VPOS_TPA_FUHRE_ORT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__WM_ABHOLDATUM = "WM_ABHOLDATUM";
	public static String FIELD__WM_ABNEHMER = "WM_ABNEHMER";
	public static String FIELD__WM_ANLIEFERDATUM = "WM_ANLIEFERDATUM";
	public static String FIELD__WM_ANSPRECHPARTNER_INHOUSE = "WM_ANSPRECHPARTNER_INHOUSE";
	public static String FIELD__WM_ANSPRECHPARTNER_LIEFERANT = "WM_ANSPRECHPARTNER_LIEFERANT";
	public static String FIELD__WM_ARTBEZ1 = "WM_ARTBEZ1";
	public static String FIELD__WM_DATUM = "WM_DATUM";
	public static String FIELD__WM_FAXNUMMER = "WM_FAXNUMMER";
	public static String FIELD__WM_GESPERRT = "WM_GESPERRT";
	public static String FIELD__WM_LETZTERDRUCK_DATUM = "WM_LETZTERDRUCK_DATUM";
	public static String FIELD__WM_LETZTERDRUCK_UHRZEIT = "WM_LETZTERDRUCK_UHRZEIT";
	public static String FIELD__WM_MENGE_ABLADEN = "WM_MENGE_ABLADEN";
	public static String FIELD__WM_ORT = "WM_ORT";
	public static String FIELD__WM_UEBERNAHMEVORSCHLAG = "WM_UEBERNAHMEVORSCHLAG";
	public static String FIELD__WM_UHRZEIT = "WM_UHRZEIT";
	public static String FIELD__WM_ZAEHLER_ENTSPERRUNG = "WM_ZAEHLER_ENTSPERRUNG";


	public String get_ABGESCHLOSSEN_BEHEBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_BEHEBUNG");
	}

	public String get_ABGESCHLOSSEN_BEHEBUNG_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_BEHEBUNG");	
	}

	public String get_ABGESCHLOSSEN_BEHEBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN_BEHEBUNG");
	}

	public String get_ABGESCHLOSSEN_BEHEBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_BEHEBUNG",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_BEHEBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_BEHEBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_BEHEBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_BEHEBUNG", calNewValueFormated);
	}
		public boolean is_ABGESCHLOSSEN_BEHEBUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_BEHEBUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABGESCHLOSSEN_BEHEBUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_BEHEBUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ABGESCHLOSSEN_KONTROLLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_KONTROLLE");
	}

	public String get_ABGESCHLOSSEN_KONTROLLE_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_KONTROLLE");	
	}

	public String get_ABGESCHLOSSEN_KONTROLLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN_KONTROLLE");
	}

	public String get_ABGESCHLOSSEN_KONTROLLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_KONTROLLE",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_KONTROLLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_KONTROLLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_KONTROLLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_KONTROLLE", calNewValueFormated);
	}
		public boolean is_ABGESCHLOSSEN_KONTROLLE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_KONTROLLE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABGESCHLOSSEN_KONTROLLE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_KONTROLLE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_AUSWIRKUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUSWIRKUNGEN");
	}

	public String get_AUSWIRKUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("AUSWIRKUNGEN");	
	}

	public String get_AUSWIRKUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUSWIRKUNGEN");
	}

	public String get_AUSWIRKUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUSWIRKUNGEN",cNotNullValue);
	}

	public String get_AUSWIRKUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUSWIRKUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUSWIRKUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUSWIRKUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUSWIRKUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSWIRKUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSWIRKUNGEN", calNewValueFormated);
	}
		public String get_BAM_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("BAM_DATUM");
	}

	public String get_BAM_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("BAM_DATUM");	
	}

	public String get_BAM_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BAM_DATUM");
	}

	public String get_BAM_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BAM_DATUM",cNotNullValue);
	}

	public String get_BAM_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BAM_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BAM_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BAM_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BAM_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BAM_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_DATUM", calNewValueFormated);
	}
		public String get_BAM_GRUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("BAM_GRUND");
	}

	public String get_BAM_GRUND_cF() throws myException
	{
		return this.get_FormatedValue("BAM_GRUND");	
	}

	public String get_BAM_GRUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BAM_GRUND");
	}

	public String get_BAM_GRUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BAM_GRUND",cNotNullValue);
	}

	public String get_BAM_GRUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BAM_GRUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BAM_GRUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BAM_GRUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BAM_GRUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BAM_GRUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_GRUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_GRUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_GRUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_GRUND", calNewValueFormated);
	}
		public String get_BAM_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BAM_ORT");
	}

	public String get_BAM_ORT_cF() throws myException
	{
		return this.get_FormatedValue("BAM_ORT");	
	}

	public String get_BAM_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BAM_ORT");
	}

	public String get_BAM_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BAM_ORT",cNotNullValue);
	}

	public String get_BAM_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BAM_ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BAM_ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BAM_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BAM_ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BAM_ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BAM_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BAM_ORT", calNewValueFormated);
	}
		public String get_BEARBEITUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEARBEITUNG");
	}

	public String get_BEARBEITUNG_cF() throws myException
	{
		return this.get_FormatedValue("BEARBEITUNG");	
	}

	public String get_BEARBEITUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEARBEITUNG");
	}

	public String get_BEARBEITUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEARBEITUNG",cNotNullValue);
	}

	public String get_BEARBEITUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEARBEITUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEARBEITUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEARBEITUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEARBEITUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITUNG", calNewValueFormated);
	}
		public boolean is_BEARBEITUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BEARBEITUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BEARBEITUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BEARBEITUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BEARBEITUNG_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEARBEITUNG_DATUM");
	}

	public String get_BEARBEITUNG_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("BEARBEITUNG_DATUM");	
	}

	public String get_BEARBEITUNG_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEARBEITUNG_DATUM");
	}

	public String get_BEARBEITUNG_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEARBEITUNG_DATUM",cNotNullValue);
	}

	public String get_BEARBEITUNG_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEARBEITUNG_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEARBEITUNG_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEARBEITUNG_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEARBEITUNG_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEARBEITUNG_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEARBEITUNG_DATUM", calNewValueFormated);
	}
		public String get_BEHEB_VERMERK_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEHEB_VERMERK");
	}

	public String get_BEHEB_VERMERK_cF() throws myException
	{
		return this.get_FormatedValue("BEHEB_VERMERK");	
	}

	public String get_BEHEB_VERMERK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEHEB_VERMERK");
	}

	public String get_BEHEB_VERMERK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEHEB_VERMERK",cNotNullValue);
	}

	public String get_BEHEB_VERMERK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEHEB_VERMERK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEHEB_VERMERK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEHEB_VERMERK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEHEB_VERMERK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VERMERK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEHEB_VERMERK", calNewValueFormated);
	}
		public String get_BEHEB_VORSCHLAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEHEB_VORSCHLAG");
	}

	public String get_BEHEB_VORSCHLAG_cF() throws myException
	{
		return this.get_FormatedValue("BEHEB_VORSCHLAG");	
	}

	public String get_BEHEB_VORSCHLAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEHEB_VORSCHLAG");
	}

	public String get_BEHEB_VORSCHLAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEHEB_VORSCHLAG",cNotNullValue);
	}

	public String get_BEHEB_VORSCHLAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEHEB_VORSCHLAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEHEB_VORSCHLAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEHEB_VORSCHLAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEHEB_VORSCHLAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEHEB_VORSCHLAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEHEB_VORSCHLAG", calNewValueFormated);
	}
		public String get_BETREFF_BAM_cUF() throws myException
	{
		return this.get_UnFormatedValue("BETREFF_BAM");
	}

	public String get_BETREFF_BAM_cF() throws myException
	{
		return this.get_FormatedValue("BETREFF_BAM");	
	}

	public String get_BETREFF_BAM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BETREFF_BAM");
	}

	public String get_BETREFF_BAM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BETREFF_BAM",cNotNullValue);
	}

	public String get_BETREFF_BAM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BETREFF_BAM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BETREFF_BAM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BETREFF_BAM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BETREFF_BAM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BETREFF_BAM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETREFF_BAM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETREFF_BAM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_BAM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETREFF_BAM", calNewValueFormated);
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
		public String get_DATUM_AUSSTELLUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_AUSSTELLUNG");
	}

	public String get_DATUM_AUSSTELLUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_AUSSTELLUNG");	
	}

	public String get_DATUM_AUSSTELLUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_AUSSTELLUNG");
	}

	public String get_DATUM_AUSSTELLUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_AUSSTELLUNG",cNotNullValue);
	}

	public String get_DATUM_AUSSTELLUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_AUSSTELLUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_AUSSTELLUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_AUSSTELLUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_AUSSTELLUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_AUSSTELLUNG", calNewValueFormated);
	}
		public String get_DATUM_BEHEBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_BEHEBUNG");
	}

	public String get_DATUM_BEHEBUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_BEHEBUNG");	
	}

	public String get_DATUM_BEHEBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_BEHEBUNG");
	}

	public String get_DATUM_BEHEBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_BEHEBUNG",cNotNullValue);
	}

	public String get_DATUM_BEHEBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_BEHEBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_BEHEBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_BEHEBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_BEHEBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_BEHEBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_BEHEBUNG", calNewValueFormated);
	}
		public String get_DATUM_KONTROLLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_KONTROLLE");
	}

	public String get_DATUM_KONTROLLE_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_KONTROLLE");	
	}

	public String get_DATUM_KONTROLLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_KONTROLLE");
	}

	public String get_DATUM_KONTROLLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_KONTROLLE",cNotNullValue);
	}

	public String get_DATUM_KONTROLLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_KONTROLLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_KONTROLLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_KONTROLLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_KONTROLLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_KONTROLLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_KONTROLLE", calNewValueFormated);
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
		public String get_FEHLERBESCHREIBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("FEHLERBESCHREIBUNG");
	}

	public String get_FEHLERBESCHREIBUNG_cF() throws myException
	{
		return this.get_FormatedValue("FEHLERBESCHREIBUNG");	
	}

	public String get_FEHLERBESCHREIBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FEHLERBESCHREIBUNG");
	}

	public String get_FEHLERBESCHREIBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FEHLERBESCHREIBUNG",cNotNullValue);
	}

	public String get_FEHLERBESCHREIBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FEHLERBESCHREIBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FEHLERBESCHREIBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FEHLERBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FEHLERBESCHREIBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERBESCHREIBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERBESCHREIBUNG", calNewValueFormated);
	}
		public String get_FEHLERINFO_EXTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FEHLERINFO_EXTERN");
	}

	public String get_FEHLERINFO_EXTERN_cF() throws myException
	{
		return this.get_FormatedValue("FEHLERINFO_EXTERN");	
	}

	public String get_FEHLERINFO_EXTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FEHLERINFO_EXTERN");
	}

	public String get_FEHLERINFO_EXTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FEHLERINFO_EXTERN",cNotNullValue);
	}

	public String get_FEHLERINFO_EXTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FEHLERINFO_EXTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FEHLERINFO_EXTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FEHLERINFO_EXTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FEHLERINFO_EXTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERINFO_EXTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERINFO_EXTERN", calNewValueFormated);
	}
		public String get_FEHLERURSACHE_cUF() throws myException
	{
		return this.get_UnFormatedValue("FEHLERURSACHE");
	}

	public String get_FEHLERURSACHE_cF() throws myException
	{
		return this.get_FormatedValue("FEHLERURSACHE");	
	}

	public String get_FEHLERURSACHE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FEHLERURSACHE");
	}

	public String get_FEHLERURSACHE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FEHLERURSACHE",cNotNullValue);
	}

	public String get_FEHLERURSACHE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FEHLERURSACHE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FEHLERURSACHE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FEHLERURSACHE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FEHLERURSACHE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FEHLERURSACHE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERURSACHE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERURSACHE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FEHLERURSACHE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FEHLERURSACHE", calNewValueFormated);
	}
		public String get_FESTSTELLUNG_BAM_cUF() throws myException
	{
		return this.get_UnFormatedValue("FESTSTELLUNG_BAM");
	}

	public String get_FESTSTELLUNG_BAM_cF() throws myException
	{
		return this.get_FormatedValue("FESTSTELLUNG_BAM");	
	}

	public String get_FESTSTELLUNG_BAM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FESTSTELLUNG_BAM");
	}

	public String get_FESTSTELLUNG_BAM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FESTSTELLUNG_BAM",cNotNullValue);
	}

	public String get_FESTSTELLUNG_BAM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FESTSTELLUNG_BAM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FESTSTELLUNG_BAM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FESTSTELLUNG_BAM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FESTSTELLUNG_BAM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FESTSTELLUNG_BAM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FESTSTELLUNG_BAM", calNewValueFormated);
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
		public String get_ID_FBAM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_FBAM");
	}

	public String get_ID_FBAM_cF() throws myException
	{
		return this.get_FormatedValue("ID_FBAM");	
	}

	public String get_ID_FBAM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_FBAM");
	}

	public String get_ID_FBAM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_FBAM",cNotNullValue);
	}

	public String get_ID_FBAM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_FBAM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_FBAM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_FBAM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_FBAM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_FBAM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FBAM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FBAM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FBAM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FBAM", calNewValueFormated);
	}
		public Long get_ID_FBAM_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_FBAM").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_FBAM_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_FBAM").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_FBAM_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_FBAM").getDoubleValue();
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
	public String get_ID_FBAM_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FBAM_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_FBAM_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FBAM_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_FBAM_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FBAM").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_FBAM_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FBAM").getBigDecimalValue();
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
	
	
	public String get_ID_USER_AUSSTELLUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUSSTELLUNG");
	}

	public String get_ID_USER_AUSSTELLUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_AUSSTELLUNG");	
	}

	public String get_ID_USER_AUSSTELLUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_AUSSTELLUNG");
	}

	public String get_ID_USER_AUSSTELLUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_AUSSTELLUNG",cNotNullValue);
	}

	public String get_ID_USER_AUSSTELLUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_AUSSTELLUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_AUSSTELLUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_AUSSTELLUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_AUSSTELLUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_AUSSTELLUNG", calNewValueFormated);
	}
		public Long get_ID_USER_AUSSTELLUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_AUSSTELLUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_AUSSTELLUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_AUSSTELLUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_AUSSTELLUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_AUSSTELLUNG").getDoubleValue();
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
	public String get_ID_USER_AUSSTELLUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUSSTELLUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_AUSSTELLUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_AUSSTELLUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_AUSSTELLUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUSSTELLUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_AUSSTELLUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_AUSSTELLUNG").getBigDecimalValue();
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
	
	
	public String get_ID_USER_BEARBEITUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEARBEITUNG");
	}

	public String get_ID_USER_BEARBEITUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_BEARBEITUNG");	
	}

	public String get_ID_USER_BEARBEITUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_BEARBEITUNG");
	}

	public String get_ID_USER_BEARBEITUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEARBEITUNG",cNotNullValue);
	}

	public String get_ID_USER_BEARBEITUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_BEARBEITUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_BEARBEITUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEARBEITUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_BEARBEITUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEARBEITUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEARBEITUNG", calNewValueFormated);
	}
		public Long get_ID_USER_BEARBEITUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_BEARBEITUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_BEARBEITUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_BEARBEITUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_BEARBEITUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_BEARBEITUNG").getDoubleValue();
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
	public String get_ID_USER_BEARBEITUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEARBEITUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_BEARBEITUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEARBEITUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_BEARBEITUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEARBEITUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_BEARBEITUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEARBEITUNG").getBigDecimalValue();
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
	
	
	public String get_ID_USER_BEHEBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEHEBUNG");
	}

	public String get_ID_USER_BEHEBUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_BEHEBUNG");	
	}

	public String get_ID_USER_BEHEBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_BEHEBUNG");
	}

	public String get_ID_USER_BEHEBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_BEHEBUNG",cNotNullValue);
	}

	public String get_ID_USER_BEHEBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_BEHEBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_BEHEBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_BEHEBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_BEHEBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_BEHEBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_BEHEBUNG", calNewValueFormated);
	}
		public Long get_ID_USER_BEHEBUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_BEHEBUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_BEHEBUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_BEHEBUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_BEHEBUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_BEHEBUNG").getDoubleValue();
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
	public String get_ID_USER_BEHEBUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEHEBUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_BEHEBUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_BEHEBUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_BEHEBUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEHEBUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_BEHEBUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_BEHEBUNG").getBigDecimalValue();
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
	
	
	public String get_ID_USER_KONTROLLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_KONTROLLE");
	}

	public String get_ID_USER_KONTROLLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_KONTROLLE");	
	}

	public String get_ID_USER_KONTROLLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_KONTROLLE");
	}

	public String get_ID_USER_KONTROLLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_KONTROLLE",cNotNullValue);
	}

	public String get_ID_USER_KONTROLLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_KONTROLLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_KONTROLLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_KONTROLLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_KONTROLLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_KONTROLLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_KONTROLLE", calNewValueFormated);
	}
		public Long get_ID_USER_KONTROLLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_KONTROLLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_KONTROLLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_KONTROLLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_KONTROLLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_KONTROLLE").getDoubleValue();
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
	public String get_ID_USER_KONTROLLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_KONTROLLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_KONTROLLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_KONTROLLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_KONTROLLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_KONTROLLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_KONTROLLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_KONTROLLE").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
	}

	public String get_ID_VPOS_TPA_FUHRE_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE");	
	}

	public String get_ID_VPOS_TPA_FUHRE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE");
	}

	public String get_ID_VPOS_TPA_FUHRE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT");
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ORT");	
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_ORT");
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_ORT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_ORT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_ORT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_ORT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_ORT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_ORT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ORT").getBigDecimalValue();
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
	
	
	public String get_WM_ABHOLDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ABHOLDATUM");
	}

	public String get_WM_ABHOLDATUM_cF() throws myException
	{
		return this.get_FormatedValue("WM_ABHOLDATUM");	
	}

	public String get_WM_ABHOLDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ABHOLDATUM");
	}

	public String get_WM_ABHOLDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ABHOLDATUM",cNotNullValue);
	}

	public String get_WM_ABHOLDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ABHOLDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ABHOLDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ABHOLDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ABHOLDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABHOLDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ABHOLDATUM", calNewValueFormated);
	}
		public String get_WM_ABNEHMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ABNEHMER");
	}

	public String get_WM_ABNEHMER_cF() throws myException
	{
		return this.get_FormatedValue("WM_ABNEHMER");	
	}

	public String get_WM_ABNEHMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ABNEHMER");
	}

	public String get_WM_ABNEHMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ABNEHMER",cNotNullValue);
	}

	public String get_WM_ABNEHMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ABNEHMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ABNEHMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ABNEHMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ABNEHMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ABNEHMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ABNEHMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ABNEHMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ABNEHMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ABNEHMER", calNewValueFormated);
	}
		public String get_WM_ANLIEFERDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ANLIEFERDATUM");
	}

	public String get_WM_ANLIEFERDATUM_cF() throws myException
	{
		return this.get_FormatedValue("WM_ANLIEFERDATUM");	
	}

	public String get_WM_ANLIEFERDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ANLIEFERDATUM");
	}

	public String get_WM_ANLIEFERDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ANLIEFERDATUM",cNotNullValue);
	}

	public String get_WM_ANLIEFERDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ANLIEFERDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ANLIEFERDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ANLIEFERDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ANLIEFERDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANLIEFERDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANLIEFERDATUM", calNewValueFormated);
	}
		public String get_WM_ANSPRECHPARTNER_INHOUSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ANSPRECHPARTNER_INHOUSE");
	}

	public String get_WM_ANSPRECHPARTNER_INHOUSE_cF() throws myException
	{
		return this.get_FormatedValue("WM_ANSPRECHPARTNER_INHOUSE");	
	}

	public String get_WM_ANSPRECHPARTNER_INHOUSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ANSPRECHPARTNER_INHOUSE");
	}

	public String get_WM_ANSPRECHPARTNER_INHOUSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ANSPRECHPARTNER_INHOUSE",cNotNullValue);
	}

	public String get_WM_ANSPRECHPARTNER_INHOUSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ANSPRECHPARTNER_INHOUSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_INHOUSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_INHOUSE", calNewValueFormated);
	}
		public String get_WM_ANSPRECHPARTNER_LIEFERANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ANSPRECHPARTNER_LIEFERANT");
	}

	public String get_WM_ANSPRECHPARTNER_LIEFERANT_cF() throws myException
	{
		return this.get_FormatedValue("WM_ANSPRECHPARTNER_LIEFERANT");	
	}

	public String get_WM_ANSPRECHPARTNER_LIEFERANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ANSPRECHPARTNER_LIEFERANT");
	}

	public String get_WM_ANSPRECHPARTNER_LIEFERANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ANSPRECHPARTNER_LIEFERANT",cNotNullValue);
	}

	public String get_WM_ANSPRECHPARTNER_LIEFERANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ANSPRECHPARTNER_LIEFERANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ANSPRECHPARTNER_LIEFERANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ANSPRECHPARTNER_LIEFERANT", calNewValueFormated);
	}
		public String get_WM_ARTBEZ1_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ARTBEZ1");
	}

	public String get_WM_ARTBEZ1_cF() throws myException
	{
		return this.get_FormatedValue("WM_ARTBEZ1");	
	}

	public String get_WM_ARTBEZ1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ARTBEZ1");
	}

	public String get_WM_ARTBEZ1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ARTBEZ1",cNotNullValue);
	}

	public String get_WM_ARTBEZ1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ARTBEZ1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ARTBEZ1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ARTBEZ1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ARTBEZ1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ARTBEZ1", calNewValueFormated);
	}
		public String get_WM_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_DATUM");
	}

	public String get_WM_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("WM_DATUM");	
	}

	public String get_WM_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_DATUM");
	}

	public String get_WM_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_DATUM",cNotNullValue);
	}

	public String get_WM_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_DATUM", calNewValueFormated);
	}
		public String get_WM_FAXNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_FAXNUMMER");
	}

	public String get_WM_FAXNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("WM_FAXNUMMER");	
	}

	public String get_WM_FAXNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_FAXNUMMER");
	}

	public String get_WM_FAXNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_FAXNUMMER",cNotNullValue);
	}

	public String get_WM_FAXNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_FAXNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_FAXNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_FAXNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_FAXNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_FAXNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_FAXNUMMER", calNewValueFormated);
	}
		public String get_WM_GESPERRT_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_GESPERRT");
	}

	public String get_WM_GESPERRT_cF() throws myException
	{
		return this.get_FormatedValue("WM_GESPERRT");	
	}

	public String get_WM_GESPERRT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_GESPERRT");
	}

	public String get_WM_GESPERRT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_GESPERRT",cNotNullValue);
	}

	public String get_WM_GESPERRT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_GESPERRT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_GESPERRT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_GESPERRT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_GESPERRT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_GESPERRT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_GESPERRT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_GESPERRT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_GESPERRT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_GESPERRT", calNewValueFormated);
	}
		public boolean is_WM_GESPERRT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_WM_GESPERRT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_WM_GESPERRT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_WM_GESPERRT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_WM_LETZTERDRUCK_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_LETZTERDRUCK_DATUM");
	}

	public String get_WM_LETZTERDRUCK_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("WM_LETZTERDRUCK_DATUM");	
	}

	public String get_WM_LETZTERDRUCK_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_LETZTERDRUCK_DATUM");
	}

	public String get_WM_LETZTERDRUCK_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_LETZTERDRUCK_DATUM",cNotNullValue);
	}

	public String get_WM_LETZTERDRUCK_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_LETZTERDRUCK_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_LETZTERDRUCK_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_DATUM", calNewValueFormated);
	}
		public String get_WM_LETZTERDRUCK_UHRZEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_LETZTERDRUCK_UHRZEIT");
	}

	public String get_WM_LETZTERDRUCK_UHRZEIT_cF() throws myException
	{
		return this.get_FormatedValue("WM_LETZTERDRUCK_UHRZEIT");	
	}

	public String get_WM_LETZTERDRUCK_UHRZEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_LETZTERDRUCK_UHRZEIT");
	}

	public String get_WM_LETZTERDRUCK_UHRZEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_LETZTERDRUCK_UHRZEIT",cNotNullValue);
	}

	public String get_WM_LETZTERDRUCK_UHRZEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_LETZTERDRUCK_UHRZEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_LETZTERDRUCK_UHRZEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_LETZTERDRUCK_UHRZEIT", calNewValueFormated);
	}
		public String get_WM_MENGE_ABLADEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_MENGE_ABLADEN");
	}

	public String get_WM_MENGE_ABLADEN_cF() throws myException
	{
		return this.get_FormatedValue("WM_MENGE_ABLADEN");	
	}

	public String get_WM_MENGE_ABLADEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_MENGE_ABLADEN");
	}

	public String get_WM_MENGE_ABLADEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_MENGE_ABLADEN",cNotNullValue);
	}

	public String get_WM_MENGE_ABLADEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_MENGE_ABLADEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_MENGE_ABLADEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_MENGE_ABLADEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_MENGE_ABLADEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_MENGE_ABLADEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_MENGE_ABLADEN", calNewValueFormated);
	}
		public Double get_WM_MENGE_ABLADEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WM_MENGE_ABLADEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WM_MENGE_ABLADEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WM_MENGE_ABLADEN").getDoubleValue();
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
	public String get_WM_MENGE_ABLADEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WM_MENGE_ABLADEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_WM_MENGE_ABLADEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WM_MENGE_ABLADEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_WM_MENGE_ABLADEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WM_MENGE_ABLADEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WM_MENGE_ABLADEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WM_MENGE_ABLADEN").getBigDecimalValue();
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
	
	
	public String get_WM_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ORT");
	}

	public String get_WM_ORT_cF() throws myException
	{
		return this.get_FormatedValue("WM_ORT");	
	}

	public String get_WM_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ORT");
	}

	public String get_WM_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ORT",cNotNullValue);
	}

	public String get_WM_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ORT", calNewValueFormated);
	}
		public String get_WM_UEBERNAHMEVORSCHLAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_UEBERNAHMEVORSCHLAG");
	}

	public String get_WM_UEBERNAHMEVORSCHLAG_cF() throws myException
	{
		return this.get_FormatedValue("WM_UEBERNAHMEVORSCHLAG");	
	}

	public String get_WM_UEBERNAHMEVORSCHLAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_UEBERNAHMEVORSCHLAG");
	}

	public String get_WM_UEBERNAHMEVORSCHLAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_UEBERNAHMEVORSCHLAG",cNotNullValue);
	}

	public String get_WM_UEBERNAHMEVORSCHLAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_UEBERNAHMEVORSCHLAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UEBERNAHMEVORSCHLAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_UEBERNAHMEVORSCHLAG", calNewValueFormated);
	}
		public String get_WM_UHRZEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_UHRZEIT");
	}

	public String get_WM_UHRZEIT_cF() throws myException
	{
		return this.get_FormatedValue("WM_UHRZEIT");	
	}

	public String get_WM_UHRZEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_UHRZEIT");
	}

	public String get_WM_UHRZEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_UHRZEIT",cNotNullValue);
	}

	public String get_WM_UHRZEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_UHRZEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_UHRZEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_UHRZEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_UHRZEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_UHRZEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_UHRZEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_UHRZEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_UHRZEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_UHRZEIT", calNewValueFormated);
	}
		public String get_WM_ZAEHLER_ENTSPERRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("WM_ZAEHLER_ENTSPERRUNG");
	}

	public String get_WM_ZAEHLER_ENTSPERRUNG_cF() throws myException
	{
		return this.get_FormatedValue("WM_ZAEHLER_ENTSPERRUNG");	
	}

	public String get_WM_ZAEHLER_ENTSPERRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WM_ZAEHLER_ENTSPERRUNG");
	}

	public String get_WM_ZAEHLER_ENTSPERRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WM_ZAEHLER_ENTSPERRUNG",cNotNullValue);
	}

	public String get_WM_ZAEHLER_ENTSPERRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WM_ZAEHLER_ENTSPERRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WM_ZAEHLER_ENTSPERRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WM_ZAEHLER_ENTSPERRUNG", calNewValueFormated);
	}
		public Long get_WM_ZAEHLER_ENTSPERRUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("WM_ZAEHLER_ENTSPERRUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_WM_ZAEHLER_ENTSPERRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WM_ZAEHLER_ENTSPERRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WM_ZAEHLER_ENTSPERRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WM_ZAEHLER_ENTSPERRUNG").getDoubleValue();
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
	public String get_WM_ZAEHLER_ENTSPERRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WM_ZAEHLER_ENTSPERRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_WM_ZAEHLER_ENTSPERRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WM_ZAEHLER_ENTSPERRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_WM_ZAEHLER_ENTSPERRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WM_ZAEHLER_ENTSPERRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WM_ZAEHLER_ENTSPERRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WM_ZAEHLER_ENTSPERRUNG").getBigDecimalValue();
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
	put("ABGESCHLOSSEN_BEHEBUNG",MyRECORD.DATATYPES.YESNO);
	put("ABGESCHLOSSEN_KONTROLLE",MyRECORD.DATATYPES.YESNO);
	put("AUSWIRKUNGEN",MyRECORD.DATATYPES.TEXT);
	put("BAM_DATUM",MyRECORD.DATATYPES.DATE);
	put("BAM_GRUND",MyRECORD.DATATYPES.TEXT);
	put("BAM_ORT",MyRECORD.DATATYPES.TEXT);
	put("BEARBEITUNG",MyRECORD.DATATYPES.YESNO);
	put("BEARBEITUNG_DATUM",MyRECORD.DATATYPES.DATE);
	put("BEHEB_VERMERK",MyRECORD.DATATYPES.TEXT);
	put("BEHEB_VORSCHLAG",MyRECORD.DATATYPES.TEXT);
	put("BETREFF_BAM",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("DATUM_AUSSTELLUNG",MyRECORD.DATATYPES.DATE);
	put("DATUM_BEHEBUNG",MyRECORD.DATATYPES.DATE);
	put("DATUM_KONTROLLE",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FEHLERBESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("FEHLERINFO_EXTERN",MyRECORD.DATATYPES.TEXT);
	put("FEHLERURSACHE",MyRECORD.DATATYPES.TEXT);
	put("FESTSTELLUNG_BAM",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_FBAM",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_AUSSTELLUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_BEARBEITUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_BEHEBUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_KONTROLLE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ORT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("WM_ABHOLDATUM",MyRECORD.DATATYPES.DATE);
	put("WM_ABNEHMER",MyRECORD.DATATYPES.TEXT);
	put("WM_ANLIEFERDATUM",MyRECORD.DATATYPES.DATE);
	put("WM_ANSPRECHPARTNER_INHOUSE",MyRECORD.DATATYPES.TEXT);
	put("WM_ANSPRECHPARTNER_LIEFERANT",MyRECORD.DATATYPES.TEXT);
	put("WM_ARTBEZ1",MyRECORD.DATATYPES.TEXT);
	put("WM_DATUM",MyRECORD.DATATYPES.DATE);
	put("WM_FAXNUMMER",MyRECORD.DATATYPES.TEXT);
	put("WM_GESPERRT",MyRECORD.DATATYPES.YESNO);
	put("WM_LETZTERDRUCK_DATUM",MyRECORD.DATATYPES.DATE);
	put("WM_LETZTERDRUCK_UHRZEIT",MyRECORD.DATATYPES.TEXT);
	put("WM_MENGE_ABLADEN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("WM_ORT",MyRECORD.DATATYPES.TEXT);
	put("WM_UEBERNAHMEVORSCHLAG",MyRECORD.DATATYPES.TEXT);
	put("WM_UHRZEIT",MyRECORD.DATATYPES.TEXT);
	put("WM_ZAEHLER_ENTSPERRUNG",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_FBAM.HM_FIELDS;	
	}

}
