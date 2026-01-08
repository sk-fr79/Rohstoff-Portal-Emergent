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

public class RECORD_VKOPF_STD extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_VKOPF_STD";
    public static String IDFIELD   = "ID_VKOPF_STD";
    

	//erzeugen eines RECORDNEW_JT_VKOPF_STD - felds
	private RECORDNEW_VKOPF_STD   recNEW = null;

    private _TAB  tab = _TAB.vkopf_std;  



	public RECORD_VKOPF_STD() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_VKOPF_STD");
	}


	public RECORD_VKOPF_STD(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VKOPF_STD");
	}



	public RECORD_VKOPF_STD(RECORD_VKOPF_STD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VKOPF_STD");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VKOPF_STD.TABLENAME);
	}


	//2014-04-03
	public RECORD_VKOPF_STD(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VKOPF_STD");
		this.set_Tablename_To_FieldMetaDefs(RECORD_VKOPF_STD.TABLENAME);
	}




	public RECORD_VKOPF_STD(long lID_Unformated) throws myException
	{
		super("JT_VKOPF_STD","ID_VKOPF_STD",""+lID_Unformated);
		this.set_TABLE_NAME("JT_VKOPF_STD");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VKOPF_STD.TABLENAME);
	}

	public RECORD_VKOPF_STD(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_VKOPF_STD");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VKOPF_STD", "ID_VKOPF_STD="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VKOPF_STD", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VKOPF_STD.TABLENAME);
	}
	
	

	public RECORD_VKOPF_STD(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_VKOPF_STD","ID_VKOPF_STD",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_VKOPF_STD");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VKOPF_STD.TABLENAME);

	}


	public RECORD_VKOPF_STD(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VKOPF_STD");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VKOPF_STD", "ID_VKOPF_STD="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VKOPF_STD", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VKOPF_STD.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_VKOPF_STD();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_VKOPF_STD.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_VKOPF_STD";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_VKOPF_STD_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_VKOPF_STD_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VKOPF_STD was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VKOPF_STD", bibE2.cTO(), "ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VKOPF_STD was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VKOPF_STD", bibE2.cTO(), "ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF();
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
			if (S.isFull(this.get_ID_VKOPF_STD_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VKOPF_STD", "ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_VKOPF_STD get_RECORDNEW_VKOPF_STD() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_VKOPF_STD();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_VKOPF_STD(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_VKOPF_STD create_Instance() throws myException {
		return new RECORD_VKOPF_STD();
	}
	
	public static RECORD_VKOPF_STD create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_VKOPF_STD(Conn);
    }

	public static RECORD_VKOPF_STD create_Instance(RECORD_VKOPF_STD recordOrig) {
		return new RECORD_VKOPF_STD(recordOrig);
    }

	public static RECORD_VKOPF_STD create_Instance(long lID_Unformated) throws myException {
		return new RECORD_VKOPF_STD(lID_Unformated);
    }

	public static RECORD_VKOPF_STD create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_VKOPF_STD(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_VKOPF_STD create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_VKOPF_STD(lID_Unformated, Conn);
	}

	public static RECORD_VKOPF_STD create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_VKOPF_STD(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_VKOPF_STD create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_VKOPF_STD(recordOrig);	    
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
    public RECORD_VKOPF_STD build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF());
      }
      //return new RECORD_VKOPF_STD(this.get_cSQL_4_Build());
      RECORD_VKOPF_STD rec = new RECORD_VKOPF_STD();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_VKOPF_STD
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_VKOPF_STD-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_VKOPF_STD get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_VKOPF_STD_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_VKOPF_STD  recNew = new RECORDNEW_VKOPF_STD();
		
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
    public RECORD_VKOPF_STD set_recordNew(RECORDNEW_VKOPF_STD recnew) throws myException {
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




		private RECORD_USER UP_RECORD_USER_id_user_ansprech_intern = null;




		private RECORD_USER UP_RECORD_USER_id_user_sachbearb_intern = null;




		private RECORD_WAEHRUNG UP_RECORD_WAEHRUNG_id_waehrung_fremd = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse = null;




		private RECLIST_VKOPF_STD_DRUCK DOWN_RECLIST_VKOPF_STD_DRUCK_id_vkopf_std = null ;




		private RECLIST_VPOS_STD DOWN_RECLIST_VPOS_STD_id_vkopf_std = null ;




		private RECORD_ZAHLUNGSBEDINGUNGEN UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen = null;






	
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
	 * References the Field ID_USER_ANSPRECH_INTERN
	 * Falls keine this.get_ID_USER_ANSPRECH_INTERN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_ansprech_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_ANSPRECH_INTERN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_ansprech_intern==null)
		{
			this.UP_RECORD_USER_id_user_ansprech_intern = new RECORD_USER(this.get_ID_USER_ANSPRECH_INTERN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_ansprech_intern;
	}
	





	
	/**
	 * References the Field ID_USER_SACHBEARB_INTERN
	 * Falls keine this.get_ID_USER_SACHBEARB_INTERN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_id_user_sachbearb_intern() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_SACHBEARB_INTERN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_id_user_sachbearb_intern==null)
		{
			this.UP_RECORD_USER_id_user_sachbearb_intern = new RECORD_USER(this.get_ID_USER_SACHBEARB_INTERN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_id_user_sachbearb_intern;
	}
	





	
	/**
	 * References the Field ID_WAEHRUNG_FREMD
	 * Falls keine this.get_ID_WAEHRUNG_FREMD_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WAEHRUNG get_UP_RECORD_WAEHRUNG_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_FREMD_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WAEHRUNG_id_waehrung_fremd==null)
		{
			this.UP_RECORD_WAEHRUNG_id_waehrung_fremd = new RECORD_WAEHRUNG(this.get_ID_WAEHRUNG_FREMD_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WAEHRUNG_id_waehrung_fremd;
	}
	





	
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
	 * References the Field ID_VKOPF_STD 
	 * Falls keine get_ID_VKOPF_STD_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD_DRUCK get_DOWN_RECORD_LIST_VKOPF_STD_DRUCK_id_vkopf_std() throws myException
	{
		if (S.isEmpty(this.get_ID_VKOPF_STD_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_DRUCK_id_vkopf_std==null)
		{
			this.DOWN_RECLIST_VKOPF_STD_DRUCK_id_vkopf_std = new RECLIST_VKOPF_STD_DRUCK (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD_DRUCK WHERE ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF()+" ORDER BY ID_VKOPF_STD_DRUCK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_DRUCK_id_vkopf_std;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VKOPF_STD 
	 * Falls keine get_ID_VKOPF_STD_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD_DRUCK get_DOWN_RECORD_LIST_VKOPF_STD_DRUCK_id_vkopf_std(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VKOPF_STD_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_DRUCK_id_vkopf_std==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD_DRUCK WHERE ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_STD_DRUCK_id_vkopf_std = new RECLIST_VKOPF_STD_DRUCK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_DRUCK_id_vkopf_std;
	}


	





	/**
	 * References the Field ID_VKOPF_STD 
	 * Falls keine get_ID_VKOPF_STD_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_vkopf_std() throws myException
	{
		if (S.isEmpty(this.get_ID_VKOPF_STD_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_vkopf_std==null)
		{
			this.DOWN_RECLIST_VPOS_STD_id_vkopf_std = new RECLIST_VPOS_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF()+" ORDER BY ID_VPOS_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_vkopf_std;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VKOPF_STD 
	 * Falls keine get_ID_VKOPF_STD_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_vkopf_std(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VKOPF_STD_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_vkopf_std==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_VKOPF_STD="+this.get_ID_VKOPF_STD_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_STD_id_vkopf_std = new RECLIST_VPOS_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_vkopf_std;
	}


	





	
	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN
	 * Falls keine this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ZAHLUNGSBEDINGUNGEN get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen==null)
		{
			this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen = new RECORD_ZAHLUNGSBEDINGUNGEN(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen;
	}
	

	public static String FIELD__ABGESCHLOSSEN = "ABGESCHLOSSEN";
	public static String FIELD__BEMERKUNGEN_INTERN = "BEMERKUNGEN_INTERN";
	public static String FIELD__BEZUG = "BEZUG";
	public static String FIELD__BUCHUNGSNUMMER = "BUCHUNGSNUMMER";
	public static String FIELD__DELETED = "DELETED";
	public static String FIELD__DEL_DATE = "DEL_DATE";
	public static String FIELD__DEL_GRUND = "DEL_GRUND";
	public static String FIELD__DEL_KUERZEL = "DEL_KUERZEL";
	public static String FIELD__DRUCKDATUM = "DRUCKDATUM";
	public static String FIELD__DRUCKZAEHLER = "DRUCKZAEHLER";
	public static String FIELD__EMAIL_AUF_FORMULAR = "EMAIL_AUF_FORMULAR";
	public static String FIELD__ERSTELLUNGSDATUM = "ERSTELLUNGSDATUM";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FAX_ANSPRECH_INTERN = "FAX_ANSPRECH_INTERN";
	public static String FIELD__FAX_BEARBEITER_INTERN = "FAX_BEARBEITER_INTERN";
	public static String FIELD__FAX_SACHBEARB_INTERN = "FAX_SACHBEARB_INTERN";
	public static String FIELD__FIXMONAT = "FIXMONAT";
	public static String FIELD__FIXTAG = "FIXTAG";
	public static String FIELD__FORMULARTEXT_ANFANG = "FORMULARTEXT_ANFANG";
	public static String FIELD__FORMULARTEXT_ENDE = "FORMULARTEXT_ENDE";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GUELTIG_BIS = "GUELTIG_BIS";
	public static String FIELD__GUELTIG_VON = "GUELTIG_VON";
	public static String FIELD__HAUSNUMMER = "HAUSNUMMER";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_USER = "ID_USER";
	public static String FIELD__ID_USER_ANSPRECH_INTERN = "ID_USER_ANSPRECH_INTERN";
	public static String FIELD__ID_USER_SACHBEARB_INTERN = "ID_USER_SACHBEARB_INTERN";
	public static String FIELD__ID_VKOPF_STD = "ID_VKOPF_STD";
	public static String FIELD__ID_WAEHRUNG_FREMD = "ID_WAEHRUNG_FREMD";
	public static String FIELD__ID_ZAHLUNGSBEDINGUNGEN = "ID_ZAHLUNGSBEDINGUNGEN";
	public static String FIELD__IS_POB_ACTIVE = "IS_POB_ACTIVE";
	public static String FIELD__KDNR = "KDNR";
	public static String FIELD__LAENDERCODE = "LAENDERCODE";
	public static String FIELD__LETZTER_DRUCK = "LETZTER_DRUCK";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERADRESSE_AKTIV = "LIEFERADRESSE_AKTIV";
	public static String FIELD__LIEFERBEDINGUNGEN = "LIEFERBEDINGUNGEN";
	public static String FIELD__L_HAUSNUMMER = "L_HAUSNUMMER";
	public static String FIELD__L_LAENDERCODE = "L_LAENDERCODE";
	public static String FIELD__L_NAME1 = "L_NAME1";
	public static String FIELD__L_NAME2 = "L_NAME2";
	public static String FIELD__L_NAME3 = "L_NAME3";
	public static String FIELD__L_ORT = "L_ORT";
	public static String FIELD__L_ORTZUSATZ = "L_ORTZUSATZ";
	public static String FIELD__L_PLZ = "L_PLZ";
	public static String FIELD__L_STRASSE = "L_STRASSE";
	public static String FIELD__L_VORNAME = "L_VORNAME";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__NAME_ANSPRECHPARTNER = "NAME_ANSPRECHPARTNER";
	public static String FIELD__NAME_ANSPRECH_INTERN = "NAME_ANSPRECH_INTERN";
	public static String FIELD__NAME_BEARBEITER_INTERN = "NAME_BEARBEITER_INTERN";
	public static String FIELD__NAME_SACHBEARB_INTERN = "NAME_SACHBEARB_INTERN";
	public static String FIELD__OEFFNUNGSZEITEN = "OEFFNUNGSZEITEN";
	public static String FIELD__ORT = "ORT";
	public static String FIELD__ORTZUSATZ = "ORTZUSATZ";
	public static String FIELD__PLZ = "PLZ";
	public static String FIELD__PLZ_POB = "PLZ_POB";
	public static String FIELD__POB = "POB";
	public static String FIELD__SKONTO_PROZENT = "SKONTO_PROZENT";
	public static String FIELD__SPRACHE = "SPRACHE";
	public static String FIELD__STRASSE = "STRASSE";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TEILZAHLUNG_PROZENT = "TEILZAHLUNG_PROZENT";
	public static String FIELD__TELEFAX_AUF_FORMULAR = "TELEFAX_AUF_FORMULAR";
	public static String FIELD__TELEFON_AUF_FORMULAR = "TELEFON_AUF_FORMULAR";
	public static String FIELD__TEL_ANSPRECH_INTERN = "TEL_ANSPRECH_INTERN";
	public static String FIELD__TEL_BEARBEITER_INTERN = "TEL_BEARBEITER_INTERN";
	public static String FIELD__TEL_SACHBEARB_INTERN = "TEL_SACHBEARB_INTERN";
	public static String FIELD__VORGANGSGRUPPE = "VORGANGSGRUPPE";
	public static String FIELD__VORGANG_NR = "VORGANG_NR";
	public static String FIELD__VORGANG_TYP = "VORGANG_TYP";
	public static String FIELD__VORNAME = "VORNAME";
	public static String FIELD__ZAEHLER_ENTSPERRUNG = "ZAEHLER_ENTSPERRUNG";
	public static String FIELD__ZAHLTAGE = "ZAHLTAGE";
	public static String FIELD__ZAHLUNGSBEDINGUNGEN = "ZAHLUNGSBEDINGUNGEN";


	public String get_ABGESCHLOSSEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN");	
	}

	public String get_ABGESCHLOSSEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", calNewValueFormated);
	}
		public boolean is_ABGESCHLOSSEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABGESCHLOSSEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BEMERKUNGEN_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNGEN_INTERN");
	}

	public String get_BEMERKUNGEN_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNGEN_INTERN");	
	}

	public String get_BEMERKUNGEN_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNGEN_INTERN");
	}

	public String get_BEMERKUNGEN_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNGEN_INTERN",cNotNullValue);
	}

	public String get_BEMERKUNGEN_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNGEN_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNGEN_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNGEN_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNGEN_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN_INTERN", calNewValueFormated);
	}
		public String get_BEZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZUG");
	}

	public String get_BEZUG_cF() throws myException
	{
		return this.get_FormatedValue("BEZUG");	
	}

	public String get_BEZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZUG");
	}

	public String get_BEZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZUG",cNotNullValue);
	}

	public String get_BEZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZUG", calNewValueFormated);
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
		public String get_DELETED_cUF() throws myException
	{
		return this.get_UnFormatedValue("DELETED");
	}

	public String get_DELETED_cF() throws myException
	{
		return this.get_FormatedValue("DELETED");	
	}

	public String get_DELETED_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DELETED");
	}

	public String get_DELETED_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DELETED",cNotNullValue);
	}

	public String get_DELETED_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DELETED",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DELETED", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DELETED(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DELETED", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DELETED", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DELETED_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DELETED", calNewValueFormated);
	}
		public boolean is_DELETED_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DELETED_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DELETED_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DELETED_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DEL_DATE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_DATE");
	}

	public String get_DEL_DATE_cF() throws myException
	{
		return this.get_FormatedValue("DEL_DATE");	
	}

	public String get_DEL_DATE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_DATE");
	}

	public String get_DEL_DATE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_DATE",cNotNullValue);
	}

	public String get_DEL_DATE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_DATE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_DATE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_DATE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_DATE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_DATE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_DATE", calNewValueFormated);
	}
		public String get_DEL_GRUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_GRUND");
	}

	public String get_DEL_GRUND_cF() throws myException
	{
		return this.get_FormatedValue("DEL_GRUND");	
	}

	public String get_DEL_GRUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_GRUND");
	}

	public String get_DEL_GRUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_GRUND",cNotNullValue);
	}

	public String get_DEL_GRUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_GRUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_GRUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_GRUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_GRUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_GRUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_GRUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_GRUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_GRUND", calNewValueFormated);
	}
		public String get_DEL_KUERZEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEL_KUERZEL");
	}

	public String get_DEL_KUERZEL_cF() throws myException
	{
		return this.get_FormatedValue("DEL_KUERZEL");	
	}

	public String get_DEL_KUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEL_KUERZEL");
	}

	public String get_DEL_KUERZEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEL_KUERZEL",cNotNullValue);
	}

	public String get_DEL_KUERZEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEL_KUERZEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEL_KUERZEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEL_KUERZEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEL_KUERZEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEL_KUERZEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEL_KUERZEL", calNewValueFormated);
	}
		public String get_DRUCKDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("DRUCKDATUM");
	}

	public String get_DRUCKDATUM_cF() throws myException
	{
		return this.get_FormatedValue("DRUCKDATUM");	
	}

	public String get_DRUCKDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DRUCKDATUM");
	}

	public String get_DRUCKDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DRUCKDATUM",cNotNullValue);
	}

	public String get_DRUCKDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DRUCKDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DRUCKDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DRUCKDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DRUCKDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKDATUM", calNewValueFormated);
	}
		public String get_DRUCKZAEHLER_cUF() throws myException
	{
		return this.get_UnFormatedValue("DRUCKZAEHLER");
	}

	public String get_DRUCKZAEHLER_cF() throws myException
	{
		return this.get_FormatedValue("DRUCKZAEHLER");	
	}

	public String get_DRUCKZAEHLER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DRUCKZAEHLER");
	}

	public String get_DRUCKZAEHLER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DRUCKZAEHLER",cNotNullValue);
	}

	public String get_DRUCKZAEHLER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DRUCKZAEHLER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", calNewValueFormated);
	}
		public Long get_DRUCKZAEHLER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DRUCKZAEHLER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DRUCKZAEHLER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DRUCKZAEHLER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DRUCKZAEHLER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DRUCKZAEHLER").getDoubleValue();
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
	public String get_DRUCKZAEHLER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKZAEHLER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_DRUCKZAEHLER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DRUCKZAEHLER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_DRUCKZAEHLER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKZAEHLER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DRUCKZAEHLER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DRUCKZAEHLER").getBigDecimalValue();
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
	
	
	public String get_EMAIL_AUF_FORMULAR_cUF() throws myException
	{
		return this.get_UnFormatedValue("EMAIL_AUF_FORMULAR");
	}

	public String get_EMAIL_AUF_FORMULAR_cF() throws myException
	{
		return this.get_FormatedValue("EMAIL_AUF_FORMULAR");	
	}

	public String get_EMAIL_AUF_FORMULAR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL_AUF_FORMULAR");
	}

	public String get_EMAIL_AUF_FORMULAR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EMAIL_AUF_FORMULAR",cNotNullValue);
	}

	public String get_EMAIL_AUF_FORMULAR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EMAIL_AUF_FORMULAR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EMAIL_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EMAIL_AUF_FORMULAR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EMAIL_AUF_FORMULAR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EMAIL_AUF_FORMULAR", calNewValueFormated);
	}
		public String get_ERSTELLUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERSTELLUNGSDATUM");
	}

	public String get_ERSTELLUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("ERSTELLUNGSDATUM");	
	}

	public String get_ERSTELLUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERSTELLUNGSDATUM");
	}

	public String get_ERSTELLUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERSTELLUNGSDATUM",cNotNullValue);
	}

	public String get_ERSTELLUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERSTELLUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", calNewValueFormated);
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
		public String get_FAX_ANSPRECH_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAX_ANSPRECH_INTERN");
	}

	public String get_FAX_ANSPRECH_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("FAX_ANSPRECH_INTERN");	
	}

	public String get_FAX_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAX_ANSPRECH_INTERN");
	}

	public String get_FAX_ANSPRECH_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAX_ANSPRECH_INTERN",cNotNullValue);
	}

	public String get_FAX_ANSPRECH_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAX_ANSPRECH_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAX_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAX_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_ANSPRECH_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_ANSPRECH_INTERN", calNewValueFormated);
	}
		public String get_FAX_BEARBEITER_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAX_BEARBEITER_INTERN");
	}

	public String get_FAX_BEARBEITER_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("FAX_BEARBEITER_INTERN");	
	}

	public String get_FAX_BEARBEITER_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAX_BEARBEITER_INTERN");
	}

	public String get_FAX_BEARBEITER_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAX_BEARBEITER_INTERN",cNotNullValue);
	}

	public String get_FAX_BEARBEITER_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAX_BEARBEITER_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAX_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAX_BEARBEITER_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_BEARBEITER_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_BEARBEITER_INTERN", calNewValueFormated);
	}
		public String get_FAX_SACHBEARB_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAX_SACHBEARB_INTERN");
	}

	public String get_FAX_SACHBEARB_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("FAX_SACHBEARB_INTERN");	
	}

	public String get_FAX_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAX_SACHBEARB_INTERN");
	}

	public String get_FAX_SACHBEARB_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAX_SACHBEARB_INTERN",cNotNullValue);
	}

	public String get_FAX_SACHBEARB_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAX_SACHBEARB_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAX_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAX_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_SACHBEARB_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX_SACHBEARB_INTERN", calNewValueFormated);
	}
		public String get_FIXMONAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIXMONAT");
	}

	public String get_FIXMONAT_cF() throws myException
	{
		return this.get_FormatedValue("FIXMONAT");	
	}

	public String get_FIXMONAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIXMONAT");
	}

	public String get_FIXMONAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIXMONAT",cNotNullValue);
	}

	public String get_FIXMONAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIXMONAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIXMONAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIXMONAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIXMONAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXMONAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXMONAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXMONAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXMONAT", calNewValueFormated);
	}
		public Long get_FIXMONAT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FIXMONAT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FIXMONAT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FIXMONAT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FIXMONAT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FIXMONAT").getDoubleValue();
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
	public String get_FIXMONAT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FIXMONAT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_FIXMONAT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FIXMONAT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_FIXMONAT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FIXMONAT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FIXMONAT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FIXMONAT").getBigDecimalValue();
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
	
	
	public String get_FIXTAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIXTAG");
	}

	public String get_FIXTAG_cF() throws myException
	{
		return this.get_FormatedValue("FIXTAG");	
	}

	public String get_FIXTAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIXTAG");
	}

	public String get_FIXTAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIXTAG",cNotNullValue);
	}

	public String get_FIXTAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIXTAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIXTAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIXTAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIXTAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXTAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXTAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXTAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXTAG", calNewValueFormated);
	}
		public Long get_FIXTAG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FIXTAG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FIXTAG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FIXTAG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FIXTAG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FIXTAG").getDoubleValue();
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
	public String get_FIXTAG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FIXTAG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_FIXTAG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FIXTAG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_FIXTAG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FIXTAG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FIXTAG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FIXTAG").getBigDecimalValue();
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
	
	
	public String get_FORMULARTEXT_ANFANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("FORMULARTEXT_ANFANG");
	}

	public String get_FORMULARTEXT_ANFANG_cF() throws myException
	{
		return this.get_FormatedValue("FORMULARTEXT_ANFANG");	
	}

	public String get_FORMULARTEXT_ANFANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FORMULARTEXT_ANFANG");
	}

	public String get_FORMULARTEXT_ANFANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FORMULARTEXT_ANFANG",cNotNullValue);
	}

	public String get_FORMULARTEXT_ANFANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FORMULARTEXT_ANFANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FORMULARTEXT_ANFANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FORMULARTEXT_ANFANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FORMULARTEXT_ANFANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ANFANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTEXT_ANFANG", calNewValueFormated);
	}
		public String get_FORMULARTEXT_ENDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("FORMULARTEXT_ENDE");
	}

	public String get_FORMULARTEXT_ENDE_cF() throws myException
	{
		return this.get_FormatedValue("FORMULARTEXT_ENDE");	
	}

	public String get_FORMULARTEXT_ENDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FORMULARTEXT_ENDE");
	}

	public String get_FORMULARTEXT_ENDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FORMULARTEXT_ENDE",cNotNullValue);
	}

	public String get_FORMULARTEXT_ENDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FORMULARTEXT_ENDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FORMULARTEXT_ENDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FORMULARTEXT_ENDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FORMULARTEXT_ENDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FORMULARTEXT_ENDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FORMULARTEXT_ENDE", calNewValueFormated);
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
		public String get_GUELTIG_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUELTIG_BIS");
	}

	public String get_GUELTIG_BIS_cF() throws myException
	{
		return this.get_FormatedValue("GUELTIG_BIS");	
	}

	public String get_GUELTIG_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUELTIG_BIS");
	}

	public String get_GUELTIG_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUELTIG_BIS",cNotNullValue);
	}

	public String get_GUELTIG_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUELTIG_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUELTIG_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUELTIG_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUELTIG_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUELTIG_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUELTIG_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUELTIG_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUELTIG_BIS", calNewValueFormated);
	}
		public String get_GUELTIG_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("GUELTIG_VON");
	}

	public String get_GUELTIG_VON_cF() throws myException
	{
		return this.get_FormatedValue("GUELTIG_VON");	
	}

	public String get_GUELTIG_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GUELTIG_VON");
	}

	public String get_GUELTIG_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GUELTIG_VON",cNotNullValue);
	}

	public String get_GUELTIG_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GUELTIG_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GUELTIG_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GUELTIG_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GUELTIG_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GUELTIG_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUELTIG_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUELTIG_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GUELTIG_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GUELTIG_VON", calNewValueFormated);
	}
		public String get_HAUSNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("HAUSNUMMER");
	}

	public String get_HAUSNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("HAUSNUMMER");	
	}

	public String get_HAUSNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HAUSNUMMER");
	}

	public String get_HAUSNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HAUSNUMMER",cNotNullValue);
	}

	public String get_HAUSNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HAUSNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HAUSNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HAUSNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAUSNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAUSNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAUSNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAUSNUMMER", calNewValueFormated);
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
	
	
	public String get_ID_USER_ANSPRECH_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_ANSPRECH_INTERN");
	}

	public String get_ID_USER_ANSPRECH_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_ANSPRECH_INTERN");	
	}

	public String get_ID_USER_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_ANSPRECH_INTERN");
	}

	public String get_ID_USER_ANSPRECH_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_ANSPRECH_INTERN",cNotNullValue);
	}

	public String get_ID_USER_ANSPRECH_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_ANSPRECH_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_ANSPRECH_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_ANSPRECH_INTERN", calNewValueFormated);
	}
		public Long get_ID_USER_ANSPRECH_INTERN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_ANSPRECH_INTERN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_ANSPRECH_INTERN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_ANSPRECH_INTERN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_ANSPRECH_INTERN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_ANSPRECH_INTERN").getDoubleValue();
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
	public String get_ID_USER_ANSPRECH_INTERN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_ANSPRECH_INTERN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_ANSPRECH_INTERN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_ANSPRECH_INTERN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_ANSPRECH_INTERN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_ANSPRECH_INTERN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_ANSPRECH_INTERN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_ANSPRECH_INTERN").getBigDecimalValue();
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
	
	
	public String get_ID_USER_SACHBEARB_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARB_INTERN");
	}

	public String get_ID_USER_SACHBEARB_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARB_INTERN");	
	}

	public String get_ID_USER_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER_SACHBEARB_INTERN");
	}

	public String get_ID_USER_SACHBEARB_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER_SACHBEARB_INTERN",cNotNullValue);
	}

	public String get_ID_USER_SACHBEARB_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER_SACHBEARB_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_USER_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_USER_SACHBEARB_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_USER_SACHBEARB_INTERN", calNewValueFormated);
	}
		public Long get_ID_USER_SACHBEARB_INTERN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER_SACHBEARB_INTERN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_SACHBEARB_INTERN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARB_INTERN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_SACHBEARB_INTERN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER_SACHBEARB_INTERN").getDoubleValue();
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
	public String get_ID_USER_SACHBEARB_INTERN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARB_INTERN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_USER_SACHBEARB_INTERN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_SACHBEARB_INTERN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_USER_SACHBEARB_INTERN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARB_INTERN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_USER_SACHBEARB_INTERN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_USER_SACHBEARB_INTERN").getBigDecimalValue();
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
	
	
	public String get_ID_VKOPF_STD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VKOPF_STD");
	}

	public String get_ID_VKOPF_STD_cF() throws myException
	{
		return this.get_FormatedValue("ID_VKOPF_STD");	
	}

	public String get_ID_VKOPF_STD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VKOPF_STD");
	}

	public String get_ID_VKOPF_STD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VKOPF_STD",cNotNullValue);
	}

	public String get_ID_VKOPF_STD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VKOPF_STD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VKOPF_STD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_STD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VKOPF_STD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_STD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_STD", calNewValueFormated);
	}
		public Long get_ID_VKOPF_STD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VKOPF_STD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VKOPF_STD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VKOPF_STD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VKOPF_STD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VKOPF_STD").getDoubleValue();
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
	public String get_ID_VKOPF_STD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VKOPF_STD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VKOPF_STD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VKOPF_STD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VKOPF_STD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VKOPF_STD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VKOPF_STD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VKOPF_STD").getBigDecimalValue();
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
	
	
	public String get_ID_WAEHRUNG_FREMD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG_FREMD");
	}

	public String get_ID_WAEHRUNG_FREMD_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG_FREMD");	
	}

	public String get_ID_WAEHRUNG_FREMD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAEHRUNG_FREMD");
	}

	public String get_ID_WAEHRUNG_FREMD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG_FREMD",cNotNullValue);
	}

	public String get_ID_WAEHRUNG_FREMD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG_FREMD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", calNewValueFormated);
	}
		public Long get_ID_WAEHRUNG_FREMD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAEHRUNG_FREMD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAEHRUNG_FREMD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG_FREMD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAEHRUNG_FREMD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG_FREMD").getDoubleValue();
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
	public String get_ID_WAEHRUNG_FREMD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_FREMD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WAEHRUNG_FREMD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_FREMD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WAEHRUNG_FREMD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG_FREMD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAEHRUNG_FREMD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG_FREMD").getBigDecimalValue();
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
	
	
	public String get_ID_ZAHLUNGSBEDINGUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSBEDINGUNGEN");	
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN",cNotNullValue);
	}

	public String get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ZAHLUNGSBEDINGUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public Long get_ID_ZAHLUNGSBEDINGUNGEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ZAHLUNGSBEDINGUNGEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ZAHLUNGSBEDINGUNGEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getDoubleValue();
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
	public String get_ID_ZAHLUNGSBEDINGUNGEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSBEDINGUNGEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZAHLUNGSBEDINGUNGEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ZAHLUNGSBEDINGUNGEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ZAHLUNGSBEDINGUNGEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZAHLUNGSBEDINGUNGEN").getBigDecimalValue();
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
	
	
	public String get_IS_POB_ACTIVE_cUF() throws myException
	{
		return this.get_UnFormatedValue("IS_POB_ACTIVE");
	}

	public String get_IS_POB_ACTIVE_cF() throws myException
	{
		return this.get_FormatedValue("IS_POB_ACTIVE");	
	}

	public String get_IS_POB_ACTIVE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IS_POB_ACTIVE");
	}

	public String get_IS_POB_ACTIVE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IS_POB_ACTIVE",cNotNullValue);
	}

	public String get_IS_POB_ACTIVE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IS_POB_ACTIVE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IS_POB_ACTIVE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IS_POB_ACTIVE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IS_POB_ACTIVE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IS_POB_ACTIVE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IS_POB_ACTIVE", calNewValueFormated);
	}
		public boolean is_IS_POB_ACTIVE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_POB_ACTIVE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IS_POB_ACTIVE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IS_POB_ACTIVE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KDNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("KDNR");
	}

	public String get_KDNR_cF() throws myException
	{
		return this.get_FormatedValue("KDNR");	
	}

	public String get_KDNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KDNR");
	}

	public String get_KDNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KDNR",cNotNullValue);
	}

	public String get_KDNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KDNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KDNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KDNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KDNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KDNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KDNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KDNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KDNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KDNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KDNR", calNewValueFormated);
	}
		public String get_LAENDERCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE");
	}

	public String get_LAENDERCODE_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE");	
	}

	public String get_LAENDERCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE");
	}

	public String get_LAENDERCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE",cNotNullValue);
	}

	public String get_LAENDERCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE", calNewValueFormated);
	}
		public String get_LETZTER_DRUCK_cUF() throws myException
	{
		return this.get_UnFormatedValue("LETZTER_DRUCK");
	}

	public String get_LETZTER_DRUCK_cF() throws myException
	{
		return this.get_FormatedValue("LETZTER_DRUCK");	
	}

	public String get_LETZTER_DRUCK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LETZTER_DRUCK");
	}

	public String get_LETZTER_DRUCK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LETZTER_DRUCK",cNotNullValue);
	}

	public String get_LETZTER_DRUCK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LETZTER_DRUCK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LETZTER_DRUCK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LETZTER_DRUCK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LETZTER_DRUCK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LETZTER_DRUCK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LETZTER_DRUCK", calNewValueFormated);
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
		public String get_LIEFERADRESSE_AKTIV_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERADRESSE_AKTIV");
	}

	public String get_LIEFERADRESSE_AKTIV_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERADRESSE_AKTIV");	
	}

	public String get_LIEFERADRESSE_AKTIV_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERADRESSE_AKTIV");
	}

	public String get_LIEFERADRESSE_AKTIV_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERADRESSE_AKTIV",cNotNullValue);
	}

	public String get_LIEFERADRESSE_AKTIV_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERADRESSE_AKTIV",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERADRESSE_AKTIV(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERADRESSE_AKTIV", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERADRESSE_AKTIV_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERADRESSE_AKTIV", calNewValueFormated);
	}
		public boolean is_LIEFERADRESSE_AKTIV_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_LIEFERADRESSE_AKTIV_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_LIEFERADRESSE_AKTIV_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_LIEFERADRESSE_AKTIV_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LIEFERBEDINGUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERBEDINGUNGEN");
	}

	public String get_LIEFERBEDINGUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERBEDINGUNGEN");	
	}

	public String get_LIEFERBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERBEDINGUNGEN");
	}

	public String get_LIEFERBEDINGUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERBEDINGUNGEN",cNotNullValue);
	}

	public String get_LIEFERBEDINGUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERBEDINGUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", calNewValueFormated);
	}
		public String get_L_HAUSNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_HAUSNUMMER");
	}

	public String get_L_HAUSNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("L_HAUSNUMMER");	
	}

	public String get_L_HAUSNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_HAUSNUMMER");
	}

	public String get_L_HAUSNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_HAUSNUMMER",cNotNullValue);
	}

	public String get_L_HAUSNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_HAUSNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_HAUSNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_HAUSNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_HAUSNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_HAUSNUMMER", calNewValueFormated);
	}
		public String get_L_LAENDERCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_LAENDERCODE");
	}

	public String get_L_LAENDERCODE_cF() throws myException
	{
		return this.get_FormatedValue("L_LAENDERCODE");	
	}

	public String get_L_LAENDERCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_LAENDERCODE");
	}

	public String get_L_LAENDERCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_LAENDERCODE",cNotNullValue);
	}

	public String get_L_LAENDERCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_LAENDERCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_LAENDERCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_LAENDERCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_LAENDERCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_LAENDERCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_LAENDERCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_LAENDERCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_LAENDERCODE", calNewValueFormated);
	}
		public String get_L_NAME1_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_NAME1");
	}

	public String get_L_NAME1_cF() throws myException
	{
		return this.get_FormatedValue("L_NAME1");	
	}

	public String get_L_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_NAME1");
	}

	public String get_L_NAME1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_NAME1",cNotNullValue);
	}

	public String get_L_NAME1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_NAME1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_NAME1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_NAME1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_NAME1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME1", calNewValueFormated);
	}
		public String get_L_NAME2_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_NAME2");
	}

	public String get_L_NAME2_cF() throws myException
	{
		return this.get_FormatedValue("L_NAME2");	
	}

	public String get_L_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_NAME2");
	}

	public String get_L_NAME2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_NAME2",cNotNullValue);
	}

	public String get_L_NAME2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_NAME2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_NAME2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_NAME2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_NAME2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME2", calNewValueFormated);
	}
		public String get_L_NAME3_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_NAME3");
	}

	public String get_L_NAME3_cF() throws myException
	{
		return this.get_FormatedValue("L_NAME3");	
	}

	public String get_L_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_NAME3");
	}

	public String get_L_NAME3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_NAME3",cNotNullValue);
	}

	public String get_L_NAME3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_NAME3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_NAME3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_NAME3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_NAME3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_NAME3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_NAME3", calNewValueFormated);
	}
		public String get_L_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_ORT");
	}

	public String get_L_ORT_cF() throws myException
	{
		return this.get_FormatedValue("L_ORT");	
	}

	public String get_L_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_ORT");
	}

	public String get_L_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_ORT",cNotNullValue);
	}

	public String get_L_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORT", calNewValueFormated);
	}
		public String get_L_ORTZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_ORTZUSATZ");
	}

	public String get_L_ORTZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("L_ORTZUSATZ");	
	}

	public String get_L_ORTZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_ORTZUSATZ");
	}

	public String get_L_ORTZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_ORTZUSATZ",cNotNullValue);
	}

	public String get_L_ORTZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_ORTZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_ORTZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_ORTZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_ORTZUSATZ", calNewValueFormated);
	}
		public String get_L_PLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_PLZ");
	}

	public String get_L_PLZ_cF() throws myException
	{
		return this.get_FormatedValue("L_PLZ");	
	}

	public String get_L_PLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_PLZ");
	}

	public String get_L_PLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_PLZ",cNotNullValue);
	}

	public String get_L_PLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_PLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_PLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_PLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_PLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_PLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_PLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_PLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_PLZ", calNewValueFormated);
	}
		public String get_L_STRASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_STRASSE");
	}

	public String get_L_STRASSE_cF() throws myException
	{
		return this.get_FormatedValue("L_STRASSE");	
	}

	public String get_L_STRASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_STRASSE");
	}

	public String get_L_STRASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_STRASSE",cNotNullValue);
	}

	public String get_L_STRASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_STRASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_STRASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_STRASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_STRASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_STRASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_STRASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_STRASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_STRASSE", calNewValueFormated);
	}
		public String get_L_VORNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_VORNAME");
	}

	public String get_L_VORNAME_cF() throws myException
	{
		return this.get_FormatedValue("L_VORNAME");	
	}

	public String get_L_VORNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_VORNAME");
	}

	public String get_L_VORNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_VORNAME",cNotNullValue);
	}

	public String get_L_VORNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_VORNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_VORNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_VORNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_VORNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_VORNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_VORNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_VORNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_VORNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_VORNAME", calNewValueFormated);
	}
		public String get_NAME1_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME1");
	}

	public String get_NAME1_cF() throws myException
	{
		return this.get_FormatedValue("NAME1");	
	}

	public String get_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME1");
	}

	public String get_NAME1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME1",cNotNullValue);
	}

	public String get_NAME1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME1", calNewValueFormated);
	}
		public String get_NAME2_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME2");
	}

	public String get_NAME2_cF() throws myException
	{
		return this.get_FormatedValue("NAME2");	
	}

	public String get_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME2");
	}

	public String get_NAME2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME2",cNotNullValue);
	}

	public String get_NAME2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME2", calNewValueFormated);
	}
		public String get_NAME3_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME3");
	}

	public String get_NAME3_cF() throws myException
	{
		return this.get_FormatedValue("NAME3");	
	}

	public String get_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME3");
	}

	public String get_NAME3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME3",cNotNullValue);
	}

	public String get_NAME3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME3", calNewValueFormated);
	}
		public String get_NAME_ANSPRECHPARTNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME_ANSPRECHPARTNER");
	}

	public String get_NAME_ANSPRECHPARTNER_cF() throws myException
	{
		return this.get_FormatedValue("NAME_ANSPRECHPARTNER");	
	}

	public String get_NAME_ANSPRECHPARTNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME_ANSPRECHPARTNER");
	}

	public String get_NAME_ANSPRECHPARTNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME_ANSPRECHPARTNER",cNotNullValue);
	}

	public String get_NAME_ANSPRECHPARTNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME_ANSPRECHPARTNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME_ANSPRECHPARTNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME_ANSPRECHPARTNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECHPARTNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_ANSPRECHPARTNER", calNewValueFormated);
	}
		public String get_NAME_ANSPRECH_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME_ANSPRECH_INTERN");
	}

	public String get_NAME_ANSPRECH_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("NAME_ANSPRECH_INTERN");	
	}

	public String get_NAME_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME_ANSPRECH_INTERN");
	}

	public String get_NAME_ANSPRECH_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME_ANSPRECH_INTERN",cNotNullValue);
	}

	public String get_NAME_ANSPRECH_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME_ANSPRECH_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_ANSPRECH_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_ANSPRECH_INTERN", calNewValueFormated);
	}
		public String get_NAME_BEARBEITER_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME_BEARBEITER_INTERN");
	}

	public String get_NAME_BEARBEITER_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("NAME_BEARBEITER_INTERN");	
	}

	public String get_NAME_BEARBEITER_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME_BEARBEITER_INTERN");
	}

	public String get_NAME_BEARBEITER_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME_BEARBEITER_INTERN",cNotNullValue);
	}

	public String get_NAME_BEARBEITER_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME_BEARBEITER_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME_BEARBEITER_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_BEARBEITER_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_BEARBEITER_INTERN", calNewValueFormated);
	}
		public String get_NAME_SACHBEARB_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("NAME_SACHBEARB_INTERN");
	}

	public String get_NAME_SACHBEARB_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("NAME_SACHBEARB_INTERN");	
	}

	public String get_NAME_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME_SACHBEARB_INTERN");
	}

	public String get_NAME_SACHBEARB_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NAME_SACHBEARB_INTERN",cNotNullValue);
	}

	public String get_NAME_SACHBEARB_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NAME_SACHBEARB_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NAME_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NAME_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NAME_SACHBEARB_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NAME_SACHBEARB_INTERN", calNewValueFormated);
	}
		public String get_OEFFNUNGSZEITEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN");
	}

	public String get_OEFFNUNGSZEITEN_cF() throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN");	
	}

	public String get_OEFFNUNGSZEITEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("OEFFNUNGSZEITEN");
	}

	public String get_OEFFNUNGSZEITEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("OEFFNUNGSZEITEN",cNotNullValue);
	}

	public String get_OEFFNUNGSZEITEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("OEFFNUNGSZEITEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_OEFFNUNGSZEITEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("OEFFNUNGSZEITEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_OEFFNUNGSZEITEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("OEFFNUNGSZEITEN", calNewValueFormated);
	}
		public String get_ORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORT");
	}

	public String get_ORT_cF() throws myException
	{
		return this.get_FormatedValue("ORT");	
	}

	public String get_ORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORT");
	}

	public String get_ORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORT",cNotNullValue);
	}

	public String get_ORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORT", calNewValueFormated);
	}
		public String get_ORTZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ORTZUSATZ");
	}

	public String get_ORTZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("ORTZUSATZ");	
	}

	public String get_ORTZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ORTZUSATZ");
	}

	public String get_ORTZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ORTZUSATZ",cNotNullValue);
	}

	public String get_ORTZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ORTZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ORTZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ORTZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ORTZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORTZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORTZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ORTZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ORTZUSATZ", calNewValueFormated);
	}
		public String get_PLZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("PLZ");
	}

	public String get_PLZ_cF() throws myException
	{
		return this.get_FormatedValue("PLZ");	
	}

	public String get_PLZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PLZ");
	}

	public String get_PLZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PLZ",cNotNullValue);
	}

	public String get_PLZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PLZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PLZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PLZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PLZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ", calNewValueFormated);
	}
		public String get_PLZ_POB_cUF() throws myException
	{
		return this.get_UnFormatedValue("PLZ_POB");
	}

	public String get_PLZ_POB_cF() throws myException
	{
		return this.get_FormatedValue("PLZ_POB");	
	}

	public String get_PLZ_POB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PLZ_POB");
	}

	public String get_PLZ_POB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PLZ_POB",cNotNullValue);
	}

	public String get_PLZ_POB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PLZ_POB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PLZ_POB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PLZ_POB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PLZ_POB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PLZ_POB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ_POB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ_POB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PLZ_POB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PLZ_POB", calNewValueFormated);
	}
		public String get_POB_cUF() throws myException
	{
		return this.get_UnFormatedValue("POB");
	}

	public String get_POB_cF() throws myException
	{
		return this.get_FormatedValue("POB");	
	}

	public String get_POB_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POB");
	}

	public String get_POB_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POB",cNotNullValue);
	}

	public String get_POB_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POB",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POB(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POB", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POB(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POB", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POB", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POB", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POB", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POB_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POB", calNewValueFormated);
	}
		public String get_SKONTO_PROZENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("SKONTO_PROZENT");
	}

	public String get_SKONTO_PROZENT_cF() throws myException
	{
		return this.get_FormatedValue("SKONTO_PROZENT");	
	}

	public String get_SKONTO_PROZENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SKONTO_PROZENT");
	}

	public String get_SKONTO_PROZENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SKONTO_PROZENT",cNotNullValue);
	}

	public String get_SKONTO_PROZENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SKONTO_PROZENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SKONTO_PROZENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SKONTO_PROZENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SKONTO_PROZENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SKONTO_PROZENT", calNewValueFormated);
	}
		public Double get_SKONTO_PROZENT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SKONTO_PROZENT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SKONTO_PROZENT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SKONTO_PROZENT").getDoubleValue();
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
	public String get_SKONTO_PROZENT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SKONTO_PROZENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SKONTO_PROZENT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SKONTO_PROZENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SKONTO_PROZENT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SKONTO_PROZENT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SKONTO_PROZENT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SKONTO_PROZENT").getBigDecimalValue();
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
	
	
	public String get_SPRACHE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SPRACHE");
	}

	public String get_SPRACHE_cF() throws myException
	{
		return this.get_FormatedValue("SPRACHE");	
	}

	public String get_SPRACHE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SPRACHE");
	}

	public String get_SPRACHE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SPRACHE",cNotNullValue);
	}

	public String get_SPRACHE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SPRACHE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SPRACHE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SPRACHE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SPRACHE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SPRACHE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPRACHE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SPRACHE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPRACHE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SPRACHE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPRACHE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SPRACHE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SPRACHE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SPRACHE", calNewValueFormated);
	}
		public String get_STRASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("STRASSE");
	}

	public String get_STRASSE_cF() throws myException
	{
		return this.get_FormatedValue("STRASSE");	
	}

	public String get_STRASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STRASSE");
	}

	public String get_STRASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STRASSE",cNotNullValue);
	}

	public String get_STRASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STRASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STRASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STRASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STRASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STRASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STRASSE", calNewValueFormated);
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
	
	
	public String get_TEILZAHLUNG_PROZENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEILZAHLUNG_PROZENT");
	}

	public String get_TEILZAHLUNG_PROZENT_cF() throws myException
	{
		return this.get_FormatedValue("TEILZAHLUNG_PROZENT");	
	}

	public String get_TEILZAHLUNG_PROZENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEILZAHLUNG_PROZENT");
	}

	public String get_TEILZAHLUNG_PROZENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEILZAHLUNG_PROZENT",cNotNullValue);
	}

	public String get_TEILZAHLUNG_PROZENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEILZAHLUNG_PROZENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEILZAHLUNG_PROZENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEILZAHLUNG_PROZENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEILZAHLUNG_PROZENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEILZAHLUNG_PROZENT", calNewValueFormated);
	}
		public Double get_TEILZAHLUNG_PROZENT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("TEILZAHLUNG_PROZENT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_TEILZAHLUNG_PROZENT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("TEILZAHLUNG_PROZENT").getDoubleValue();
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
	public String get_TEILZAHLUNG_PROZENT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TEILZAHLUNG_PROZENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_TEILZAHLUNG_PROZENT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TEILZAHLUNG_PROZENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_TEILZAHLUNG_PROZENT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("TEILZAHLUNG_PROZENT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_TEILZAHLUNG_PROZENT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("TEILZAHLUNG_PROZENT").getBigDecimalValue();
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
	
	
	public String get_TELEFAX_AUF_FORMULAR_cUF() throws myException
	{
		return this.get_UnFormatedValue("TELEFAX_AUF_FORMULAR");
	}

	public String get_TELEFAX_AUF_FORMULAR_cF() throws myException
	{
		return this.get_FormatedValue("TELEFAX_AUF_FORMULAR");	
	}

	public String get_TELEFAX_AUF_FORMULAR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TELEFAX_AUF_FORMULAR");
	}

	public String get_TELEFAX_AUF_FORMULAR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TELEFAX_AUF_FORMULAR",cNotNullValue);
	}

	public String get_TELEFAX_AUF_FORMULAR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TELEFAX_AUF_FORMULAR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TELEFAX_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TELEFAX_AUF_FORMULAR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFAX_AUF_FORMULAR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFAX_AUF_FORMULAR", calNewValueFormated);
	}
		public String get_TELEFON_AUF_FORMULAR_cUF() throws myException
	{
		return this.get_UnFormatedValue("TELEFON_AUF_FORMULAR");
	}

	public String get_TELEFON_AUF_FORMULAR_cF() throws myException
	{
		return this.get_FormatedValue("TELEFON_AUF_FORMULAR");	
	}

	public String get_TELEFON_AUF_FORMULAR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TELEFON_AUF_FORMULAR");
	}

	public String get_TELEFON_AUF_FORMULAR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TELEFON_AUF_FORMULAR",cNotNullValue);
	}

	public String get_TELEFON_AUF_FORMULAR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TELEFON_AUF_FORMULAR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TELEFON_AUF_FORMULAR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TELEFON_AUF_FORMULAR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_AUF_FORMULAR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON_AUF_FORMULAR", calNewValueFormated);
	}
		public String get_TEL_ANSPRECH_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEL_ANSPRECH_INTERN");
	}

	public String get_TEL_ANSPRECH_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("TEL_ANSPRECH_INTERN");	
	}

	public String get_TEL_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEL_ANSPRECH_INTERN");
	}

	public String get_TEL_ANSPRECH_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEL_ANSPRECH_INTERN",cNotNullValue);
	}

	public String get_TEL_ANSPRECH_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEL_ANSPRECH_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEL_ANSPRECH_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEL_ANSPRECH_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_ANSPRECH_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_ANSPRECH_INTERN", calNewValueFormated);
	}
		public String get_TEL_BEARBEITER_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEL_BEARBEITER_INTERN");
	}

	public String get_TEL_BEARBEITER_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("TEL_BEARBEITER_INTERN");	
	}

	public String get_TEL_BEARBEITER_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEL_BEARBEITER_INTERN");
	}

	public String get_TEL_BEARBEITER_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEL_BEARBEITER_INTERN",cNotNullValue);
	}

	public String get_TEL_BEARBEITER_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEL_BEARBEITER_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEL_BEARBEITER_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEL_BEARBEITER_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_BEARBEITER_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_BEARBEITER_INTERN", calNewValueFormated);
	}
		public String get_TEL_SACHBEARB_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("TEL_SACHBEARB_INTERN");
	}

	public String get_TEL_SACHBEARB_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("TEL_SACHBEARB_INTERN");	
	}

	public String get_TEL_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEL_SACHBEARB_INTERN");
	}

	public String get_TEL_SACHBEARB_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TEL_SACHBEARB_INTERN",cNotNullValue);
	}

	public String get_TEL_SACHBEARB_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TEL_SACHBEARB_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TEL_SACHBEARB_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TEL_SACHBEARB_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TEL_SACHBEARB_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TEL_SACHBEARB_INTERN", calNewValueFormated);
	}
		public String get_VORGANGSGRUPPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORGANGSGRUPPE");
	}

	public String get_VORGANGSGRUPPE_cF() throws myException
	{
		return this.get_FormatedValue("VORGANGSGRUPPE");	
	}

	public String get_VORGANGSGRUPPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORGANGSGRUPPE");
	}

	public String get_VORGANGSGRUPPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORGANGSGRUPPE",cNotNullValue);
	}

	public String get_VORGANGSGRUPPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORGANGSGRUPPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORGANGSGRUPPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORGANGSGRUPPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORGANGSGRUPPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANGSGRUPPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANGSGRUPPE", calNewValueFormated);
	}
		public Long get_VORGANGSGRUPPE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("VORGANGSGRUPPE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_VORGANGSGRUPPE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("VORGANGSGRUPPE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_VORGANGSGRUPPE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("VORGANGSGRUPPE").getDoubleValue();
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
	public String get_VORGANGSGRUPPE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VORGANGSGRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_VORGANGSGRUPPE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VORGANGSGRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_VORGANGSGRUPPE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("VORGANGSGRUPPE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_VORGANGSGRUPPE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("VORGANGSGRUPPE").getBigDecimalValue();
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
	
	
	public String get_VORGANG_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORGANG_NR");
	}

	public String get_VORGANG_NR_cF() throws myException
	{
		return this.get_FormatedValue("VORGANG_NR");	
	}

	public String get_VORGANG_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORGANG_NR");
	}

	public String get_VORGANG_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORGANG_NR",cNotNullValue);
	}

	public String get_VORGANG_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORGANG_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORGANG_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORGANG_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORGANG_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORGANG_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_NR", calNewValueFormated);
	}
		public String get_VORGANG_TYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORGANG_TYP");
	}

	public String get_VORGANG_TYP_cF() throws myException
	{
		return this.get_FormatedValue("VORGANG_TYP");	
	}

	public String get_VORGANG_TYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORGANG_TYP");
	}

	public String get_VORGANG_TYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORGANG_TYP",cNotNullValue);
	}

	public String get_VORGANG_TYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORGANG_TYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_TYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_TYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_TYP", calNewValueFormated);
	}
		public String get_VORNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORNAME");
	}

	public String get_VORNAME_cF() throws myException
	{
		return this.get_FormatedValue("VORNAME");	
	}

	public String get_VORNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORNAME");
	}

	public String get_VORNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORNAME",cNotNullValue);
	}

	public String get_VORNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORNAME", calNewValueFormated);
	}
		public String get_ZAEHLER_ENTSPERRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAEHLER_ENTSPERRUNG");
	}

	public String get_ZAEHLER_ENTSPERRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ZAEHLER_ENTSPERRUNG");	
	}

	public String get_ZAEHLER_ENTSPERRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAEHLER_ENTSPERRUNG");
	}

	public String get_ZAEHLER_ENTSPERRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAEHLER_ENTSPERRUNG",cNotNullValue);
	}

	public String get_ZAEHLER_ENTSPERRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAEHLER_ENTSPERRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAEHLER_ENTSPERRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAEHLER_ENTSPERRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAEHLER_ENTSPERRUNG", calNewValueFormated);
	}
		public Long get_ZAEHLER_ENTSPERRUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ZAEHLER_ENTSPERRUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ZAEHLER_ENTSPERRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAEHLER_ENTSPERRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAEHLER_ENTSPERRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAEHLER_ENTSPERRUNG").getDoubleValue();
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
	public String get_ZAEHLER_ENTSPERRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAEHLER_ENTSPERRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ZAEHLER_ENTSPERRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAEHLER_ENTSPERRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ZAEHLER_ENTSPERRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAEHLER_ENTSPERRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAEHLER_ENTSPERRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAEHLER_ENTSPERRUNG").getBigDecimalValue();
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
	
	
	public String get_ZAHLTAGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLTAGE");
	}

	public String get_ZAHLTAGE_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLTAGE");	
	}

	public String get_ZAHLTAGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLTAGE");
	}

	public String get_ZAHLTAGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLTAGE",cNotNullValue);
	}

	public String get_ZAHLTAGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLTAGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLTAGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLTAGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLTAGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLTAGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLTAGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLTAGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLTAGE", calNewValueFormated);
	}
		public Long get_ZAHLTAGE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ZAHLTAGE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ZAHLTAGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAHLTAGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAHLTAGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAHLTAGE").getDoubleValue();
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
	public String get_ZAHLTAGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHLTAGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ZAHLTAGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHLTAGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ZAHLTAGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHLTAGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAHLTAGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHLTAGE").getBigDecimalValue();
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
	
	
	public String get_ZAHLUNGSBEDINGUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ZAHLUNGSBEDINGUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBEDINGUNGEN");	
	}

	public String get_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ZAHLUNGSBEDINGUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBEDINGUNGEN",cNotNullValue);
	}

	public String get_ZAHLUNGSBEDINGUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBEDINGUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABGESCHLOSSEN",MyRECORD.DATATYPES.YESNO);
	put("BEMERKUNGEN_INTERN",MyRECORD.DATATYPES.TEXT);
	put("BEZUG",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("DELETED",MyRECORD.DATATYPES.YESNO);
	put("DEL_DATE",MyRECORD.DATATYPES.DATE);
	put("DEL_GRUND",MyRECORD.DATATYPES.TEXT);
	put("DEL_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("DRUCKDATUM",MyRECORD.DATATYPES.DATE);
	put("DRUCKZAEHLER",MyRECORD.DATATYPES.NUMBER);
	put("EMAIL_AUF_FORMULAR",MyRECORD.DATATYPES.TEXT);
	put("ERSTELLUNGSDATUM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FAX_ANSPRECH_INTERN",MyRECORD.DATATYPES.TEXT);
	put("FAX_BEARBEITER_INTERN",MyRECORD.DATATYPES.TEXT);
	put("FAX_SACHBEARB_INTERN",MyRECORD.DATATYPES.TEXT);
	put("FIXMONAT",MyRECORD.DATATYPES.NUMBER);
	put("FIXTAG",MyRECORD.DATATYPES.NUMBER);
	put("FORMULARTEXT_ANFANG",MyRECORD.DATATYPES.TEXT);
	put("FORMULARTEXT_ENDE",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GUELTIG_BIS",MyRECORD.DATATYPES.DATE);
	put("GUELTIG_VON",MyRECORD.DATATYPES.DATE);
	put("HAUSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_ANSPRECH_INTERN",MyRECORD.DATATYPES.NUMBER);
	put("ID_USER_SACHBEARB_INTERN",MyRECORD.DATATYPES.NUMBER);
	put("ID_VKOPF_STD",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAEHRUNG_FREMD",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZAHLUNGSBEDINGUNGEN",MyRECORD.DATATYPES.NUMBER);
	put("IS_POB_ACTIVE",MyRECORD.DATATYPES.YESNO);
	put("KDNR",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE",MyRECORD.DATATYPES.TEXT);
	put("LETZTER_DRUCK",MyRECORD.DATATYPES.DATE);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERADRESSE_AKTIV",MyRECORD.DATATYPES.YESNO);
	put("LIEFERBEDINGUNGEN",MyRECORD.DATATYPES.TEXT);
	put("L_HAUSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("L_LAENDERCODE",MyRECORD.DATATYPES.TEXT);
	put("L_NAME1",MyRECORD.DATATYPES.TEXT);
	put("L_NAME2",MyRECORD.DATATYPES.TEXT);
	put("L_NAME3",MyRECORD.DATATYPES.TEXT);
	put("L_ORT",MyRECORD.DATATYPES.TEXT);
	put("L_ORTZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("L_PLZ",MyRECORD.DATATYPES.TEXT);
	put("L_STRASSE",MyRECORD.DATATYPES.TEXT);
	put("L_VORNAME",MyRECORD.DATATYPES.TEXT);
	put("NAME1",MyRECORD.DATATYPES.TEXT);
	put("NAME2",MyRECORD.DATATYPES.TEXT);
	put("NAME3",MyRECORD.DATATYPES.TEXT);
	put("NAME_ANSPRECHPARTNER",MyRECORD.DATATYPES.TEXT);
	put("NAME_ANSPRECH_INTERN",MyRECORD.DATATYPES.TEXT);
	put("NAME_BEARBEITER_INTERN",MyRECORD.DATATYPES.TEXT);
	put("NAME_SACHBEARB_INTERN",MyRECORD.DATATYPES.TEXT);
	put("OEFFNUNGSZEITEN",MyRECORD.DATATYPES.TEXT);
	put("ORT",MyRECORD.DATATYPES.TEXT);
	put("ORTZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("PLZ",MyRECORD.DATATYPES.TEXT);
	put("PLZ_POB",MyRECORD.DATATYPES.TEXT);
	put("POB",MyRECORD.DATATYPES.TEXT);
	put("SKONTO_PROZENT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SPRACHE",MyRECORD.DATATYPES.TEXT);
	put("STRASSE",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TEILZAHLUNG_PROZENT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("TELEFAX_AUF_FORMULAR",MyRECORD.DATATYPES.TEXT);
	put("TELEFON_AUF_FORMULAR",MyRECORD.DATATYPES.TEXT);
	put("TEL_ANSPRECH_INTERN",MyRECORD.DATATYPES.TEXT);
	put("TEL_BEARBEITER_INTERN",MyRECORD.DATATYPES.TEXT);
	put("TEL_SACHBEARB_INTERN",MyRECORD.DATATYPES.TEXT);
	put("VORGANGSGRUPPE",MyRECORD.DATATYPES.NUMBER);
	put("VORGANG_NR",MyRECORD.DATATYPES.TEXT);
	put("VORGANG_TYP",MyRECORD.DATATYPES.TEXT);
	put("VORNAME",MyRECORD.DATATYPES.TEXT);
	put("ZAEHLER_ENTSPERRUNG",MyRECORD.DATATYPES.NUMBER);
	put("ZAHLTAGE",MyRECORD.DATATYPES.NUMBER);
	put("ZAHLUNGSBEDINGUNGEN",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_VKOPF_STD.HM_FIELDS;	
	}

}
