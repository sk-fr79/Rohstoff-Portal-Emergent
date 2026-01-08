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

public class RECORD_LAGER_KONTO extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_LAGER_KONTO";
    public static String IDFIELD   = "ID_LAGER_KONTO";
    

	//erzeugen eines RECORDNEW_JT_LAGER_KONTO - felds
	private RECORDNEW_LAGER_KONTO   recNEW = null;

    private _TAB  tab = _TAB.lager_konto;  



	public RECORD_LAGER_KONTO() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_LAGER_KONTO");
	}


	public RECORD_LAGER_KONTO(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_LAGER_KONTO");
	}



	public RECORD_LAGER_KONTO(RECORD_LAGER_KONTO recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_LAGER_KONTO");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_KONTO.TABLENAME);
	}


	//2014-04-03
	public RECORD_LAGER_KONTO(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_LAGER_KONTO");
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_KONTO.TABLENAME);
	}




	public RECORD_LAGER_KONTO(long lID_Unformated) throws myException
	{
		super("JT_LAGER_KONTO","ID_LAGER_KONTO",""+lID_Unformated);
		this.set_TABLE_NAME("JT_LAGER_KONTO");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_KONTO.TABLENAME);
	}

	public RECORD_LAGER_KONTO(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_LAGER_KONTO");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_KONTO", "ID_LAGER_KONTO="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_KONTO", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_KONTO.TABLENAME);
	}
	
	

	public RECORD_LAGER_KONTO(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_LAGER_KONTO","ID_LAGER_KONTO",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_LAGER_KONTO");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_KONTO.TABLENAME);

	}


	public RECORD_LAGER_KONTO(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_LAGER_KONTO");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_KONTO", "ID_LAGER_KONTO="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_KONTO", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_KONTO.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_LAGER_KONTO();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_LAGER_KONTO.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_LAGER_KONTO";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_LAGER_KONTO_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_LAGER_KONTO_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAGER_KONTO was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_LAGER_KONTO", bibE2.cTO(), "ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAGER_KONTO was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_LAGER_KONTO", bibE2.cTO(), "ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF();
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
			if (S.isFull(this.get_ID_LAGER_KONTO_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_KONTO", "ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_LAGER_KONTO get_RECORDNEW_LAGER_KONTO() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_LAGER_KONTO();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_LAGER_KONTO(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_LAGER_KONTO create_Instance() throws myException {
		return new RECORD_LAGER_KONTO();
	}
	
	public static RECORD_LAGER_KONTO create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_LAGER_KONTO(Conn);
    }

	public static RECORD_LAGER_KONTO create_Instance(RECORD_LAGER_KONTO recordOrig) {
		return new RECORD_LAGER_KONTO(recordOrig);
    }

	public static RECORD_LAGER_KONTO create_Instance(long lID_Unformated) throws myException {
		return new RECORD_LAGER_KONTO(lID_Unformated);
    }

	public static RECORD_LAGER_KONTO create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_LAGER_KONTO(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_LAGER_KONTO create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_LAGER_KONTO(lID_Unformated, Conn);
	}

	public static RECORD_LAGER_KONTO create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_LAGER_KONTO(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_LAGER_KONTO create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_LAGER_KONTO(recordOrig);	    
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
    public RECORD_LAGER_KONTO build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF());
      }
      //return new RECORD_LAGER_KONTO(this.get_cSQL_4_Build());
      RECORD_LAGER_KONTO rec = new RECORD_LAGER_KONTO();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_LAGER_KONTO
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_LAGER_KONTO-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_LAGER_KONTO get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_LAGER_KONTO_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_LAGER_KONTO  recNew = new RECORDNEW_LAGER_KONTO();
		
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
    public RECORD_LAGER_KONTO set_recordNew(RECORDNEW_LAGER_KONTO recnew) throws myException {
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
	
	



		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lager = null;




		private RECORD_ARTIKEL UP_RECORD_ARTIKEL_id_artikel_sorte = null;




		private RECLIST_ABRECHBLATT_POS DOWN_RECLIST_ABRECHBLATT_POS_id_lager_konto = null ;




		private RECLIST_LAGER_BEWEGUNG DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_ausgang = null ;




		private RECLIST_LAGER_BEWEGUNG DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_eingang = null ;




		private RECLIST_LAGER_KONTO DOWN_RECLIST_LAGER_KONTO_id_lager_konto_parent = null ;




		private RECORD_LAGER_KONTO UP_RECORD_LAGER_KONTO_id_lager_konto_parent = null;






	
	/**
	 * References the Field ID_ADRESSE_LAGER
	 * Falls keine this.get_ID_ADRESSE_LAGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LAGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lager==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lager = new RECORD_ADRESSE(this.get_ID_ADRESSE_LAGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lager;
	}
	





	
	/**
	 * References the Field ID_ARTIKEL_SORTE
	 * Falls keine this.get_ID_ARTIKEL_SORTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL get_UP_RECORD_ARTIKEL_id_artikel_sorte() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_SORTE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_id_artikel_sorte==null)
		{
			this.UP_RECORD_ARTIKEL_id_artikel_sorte = new RECORD_ARTIKEL(this.get_ID_ARTIKEL_SORTE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_id_artikel_sorte;
	}
	





	/**
	 * References the Field ID_LAGER_KONTO 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_POS get_DOWN_RECORD_LIST_ABRECHBLATT_POS_id_lager_konto() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_POS_id_lager_konto==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_POS_id_lager_konto = new RECLIST_ABRECHBLATT_POS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF()+" ORDER BY ID_ABRECHBLATT_POS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_POS_id_lager_konto;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAGER_KONTO 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_POS get_DOWN_RECORD_LIST_ABRECHBLATT_POS_id_lager_konto(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_POS_id_lager_konto==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_LAGER_KONTO="+this.get_ID_LAGER_KONTO_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_POS_id_lager_konto = new RECLIST_ABRECHBLATT_POS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_POS_id_lager_konto;
	}


	





	/**
	 * References the Field ID_LAGER_KONTO_AUSGANG 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_BEWEGUNG get_DOWN_RECORD_LIST_LAGER_BEWEGUNG_id_lager_konto_ausgang() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_ausgang==null)
		{
			this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_ausgang = new RECLIST_LAGER_BEWEGUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_BEWEGUNG WHERE ID_LAGER_KONTO_AUSGANG="+this.get_ID_LAGER_KONTO_cUF()+" ORDER BY ID_LAGER_BEWEGUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_ausgang;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAGER_KONTO_AUSGANG 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_BEWEGUNG get_DOWN_RECORD_LIST_LAGER_BEWEGUNG_id_lager_konto_ausgang(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_ausgang==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_BEWEGUNG WHERE ID_LAGER_KONTO_AUSGANG="+this.get_ID_LAGER_KONTO_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_ausgang = new RECLIST_LAGER_BEWEGUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_ausgang;
	}


	





	/**
	 * References the Field ID_LAGER_KONTO_EINGANG 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_BEWEGUNG get_DOWN_RECORD_LIST_LAGER_BEWEGUNG_id_lager_konto_eingang() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_eingang==null)
		{
			this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_eingang = new RECLIST_LAGER_BEWEGUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_BEWEGUNG WHERE ID_LAGER_KONTO_EINGANG="+this.get_ID_LAGER_KONTO_cUF()+" ORDER BY ID_LAGER_BEWEGUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_eingang;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAGER_KONTO_EINGANG 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_BEWEGUNG get_DOWN_RECORD_LIST_LAGER_BEWEGUNG_id_lager_konto_eingang(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_eingang==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_BEWEGUNG WHERE ID_LAGER_KONTO_EINGANG="+this.get_ID_LAGER_KONTO_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_eingang = new RECLIST_LAGER_BEWEGUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_BEWEGUNG_id_lager_konto_eingang;
	}


	





	/**
	 * References the Field ID_LAGER_KONTO_PARENT 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_KONTO get_DOWN_RECORD_LIST_LAGER_KONTO_id_lager_konto_parent() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_KONTO_id_lager_konto_parent==null)
		{
			this.DOWN_RECLIST_LAGER_KONTO_id_lager_konto_parent = new RECLIST_LAGER_KONTO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_LAGER_KONTO_PARENT="+this.get_ID_LAGER_KONTO_cUF()+" ORDER BY ID_LAGER_KONTO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_KONTO_id_lager_konto_parent;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAGER_KONTO_PARENT 
	 * Falls keine get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_KONTO get_DOWN_RECORD_LIST_LAGER_KONTO_id_lager_konto_parent(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_KONTO_id_lager_konto_parent==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_LAGER_KONTO_PARENT="+this.get_ID_LAGER_KONTO_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_KONTO_id_lager_konto_parent = new RECLIST_LAGER_KONTO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_KONTO_id_lager_konto_parent;
	}


	





	
	/**
	 * References the Field ID_LAGER_KONTO_PARENT
	 * Falls keine this.get_ID_LAGER_KONTO_PARENT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAGER_KONTO get_UP_RECORD_LAGER_KONTO_id_lager_konto_parent() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_PARENT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAGER_KONTO_id_lager_konto_parent==null)
		{
			this.UP_RECORD_LAGER_KONTO_id_lager_konto_parent = new RECORD_LAGER_KONTO(this.get_ID_LAGER_KONTO_PARENT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAGER_KONTO_id_lager_konto_parent;
	}
	

	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__BUCHUNGSDATUM = "BUCHUNGSDATUM";
	public static String FIELD__BUCHUNGSTYP = "BUCHUNGSTYP";
	public static String FIELD__BUCHUNGSZEIT = "BUCHUNGSZEIT";
	public static String FIELD__BUCHUNG_HAND = "BUCHUNG_HAND";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ADRESSE_LAGER = "ID_ADRESSE_LAGER";
	public static String FIELD__ID_ARTIKEL_SORTE = "ID_ARTIKEL_SORTE";
	public static String FIELD__ID_LAGER_KONTO = "ID_LAGER_KONTO";
	public static String FIELD__ID_LAGER_KONTO_PARENT = "ID_LAGER_KONTO_PARENT";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VPOS_RG = "ID_VPOS_RG";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ORT = "ID_VPOS_TPA_FUHRE_ORT";
	public static String FIELD__IST_GEAENDERT = "IST_GEAENDERT";
	public static String FIELD__IST_KOMPLETT = "IST_KOMPLETT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MENGE = "MENGE";
	public static String FIELD__MENGE_BUCH = "MENGE_BUCH";
	public static String FIELD__PREIS = "PREIS";
	public static String FIELD__SALDO = "SALDO";
	public static String FIELD__STATUS_PREIS = "STATUS_PREIS";
	public static String FIELD__STORNO = "STORNO";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_BEMERKUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG");
	}

	public String get_BEMERKUNG_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG");	
	}

	public String get_BEMERKUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG");
	}

	public String get_BEMERKUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG",cNotNullValue);
	}

	public String get_BEMERKUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG", calNewValueFormated);
	}
		public String get_BUCHUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSDATUM");
	}

	public String get_BUCHUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSDATUM");	
	}

	public String get_BUCHUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSDATUM");
	}

	public String get_BUCHUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSDATUM",cNotNullValue);
	}

	public String get_BUCHUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSDATUM", calNewValueFormated);
	}
		public String get_BUCHUNGSTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSTYP");
	}

	public String get_BUCHUNGSTYP_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSTYP");	
	}

	public String get_BUCHUNGSTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSTYP");
	}

	public String get_BUCHUNGSTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSTYP",cNotNullValue);
	}

	public String get_BUCHUNGSTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSTYP", calNewValueFormated);
	}
		public String get_BUCHUNGSZEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSZEIT");
	}

	public String get_BUCHUNGSZEIT_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSZEIT");	
	}

	public String get_BUCHUNGSZEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSZEIT");
	}

	public String get_BUCHUNGSZEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSZEIT",cNotNullValue);
	}

	public String get_BUCHUNGSZEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSZEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSZEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSZEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSZEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSZEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSZEIT", calNewValueFormated);
	}
		public String get_BUCHUNG_HAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNG_HAND");
	}

	public String get_BUCHUNG_HAND_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNG_HAND");	
	}

	public String get_BUCHUNG_HAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNG_HAND");
	}

	public String get_BUCHUNG_HAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNG_HAND",cNotNullValue);
	}

	public String get_BUCHUNG_HAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNG_HAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNG_HAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNG_HAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNG_HAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNG_HAND", calNewValueFormated);
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
		public String get_ID_ADRESSE_LAGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER");
	}

	public String get_ID_ADRESSE_LAGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER");	
	}

	public String get_ID_ADRESSE_LAGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER");
	}

	public String get_ID_ADRESSE_LAGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_LAGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_LAGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_SORTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_SORTE");
	}

	public String get_ID_ARTIKEL_SORTE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_SORTE");	
	}

	public String get_ID_ARTIKEL_SORTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_SORTE");
	}

	public String get_ID_ARTIKEL_SORTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_SORTE",cNotNullValue);
	}

	public String get_ID_ARTIKEL_SORTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_SORTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_SORTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_SORTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_SORTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_SORTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_SORTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_SORTE").getDoubleValue();
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
	public String get_ID_ARTIKEL_SORTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_SORTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_SORTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_SORTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_SORTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_SORTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_SORTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_SORTE").getBigDecimalValue();
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
	
	
	public String get_ID_LAGER_KONTO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_KONTO");
	}

	public String get_ID_LAGER_KONTO_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGER_KONTO");	
	}

	public String get_ID_LAGER_KONTO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGER_KONTO");
	}

	public String get_ID_LAGER_KONTO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_KONTO",cNotNullValue);
	}

	public String get_ID_LAGER_KONTO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGER_KONTO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", calNewValueFormated);
	}
		public Long get_ID_LAGER_KONTO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGER_KONTO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGER_KONTO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGER_KONTO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGER_KONTO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGER_KONTO").getDoubleValue();
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
	public String get_ID_LAGER_KONTO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_KONTO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAGER_KONTO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_KONTO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAGER_KONTO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_KONTO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGER_KONTO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_KONTO").getBigDecimalValue();
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
	
	
	public String get_ID_LAGER_KONTO_PARENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_KONTO_PARENT");
	}

	public String get_ID_LAGER_KONTO_PARENT_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGER_KONTO_PARENT");	
	}

	public String get_ID_LAGER_KONTO_PARENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGER_KONTO_PARENT");
	}

	public String get_ID_LAGER_KONTO_PARENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_KONTO_PARENT",cNotNullValue);
	}

	public String get_ID_LAGER_KONTO_PARENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGER_KONTO_PARENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_KONTO_PARENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGER_KONTO_PARENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_PARENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO_PARENT", calNewValueFormated);
	}
		public Long get_ID_LAGER_KONTO_PARENT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGER_KONTO_PARENT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGER_KONTO_PARENT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGER_KONTO_PARENT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGER_KONTO_PARENT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGER_KONTO_PARENT").getDoubleValue();
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
	public String get_ID_LAGER_KONTO_PARENT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_KONTO_PARENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAGER_KONTO_PARENT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_KONTO_PARENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAGER_KONTO_PARENT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_KONTO_PARENT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGER_KONTO_PARENT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_KONTO_PARENT").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_RG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_RG");
	}

	public String get_ID_VPOS_RG_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_RG");	
	}

	public String get_ID_VPOS_RG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_RG");
	}

	public String get_ID_VPOS_RG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_RG",cNotNullValue);
	}

	public String get_ID_VPOS_RG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_RG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_RG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_RG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_RG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_RG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_RG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_RG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_RG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_RG", calNewValueFormated);
	}
		public Long get_ID_VPOS_RG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_RG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_RG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_RG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_RG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_RG").getDoubleValue();
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
	public String get_ID_VPOS_RG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_RG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_RG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_RG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_RG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_RG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_RG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_RG").getBigDecimalValue();
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
	
	
	public String get_IST_GEAENDERT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_GEAENDERT");
	}

	public String get_IST_GEAENDERT_cF() throws myException
	{
		return this.get_FormatedValue("IST_GEAENDERT");	
	}

	public String get_IST_GEAENDERT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_GEAENDERT");
	}

	public String get_IST_GEAENDERT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_GEAENDERT",cNotNullValue);
	}

	public String get_IST_GEAENDERT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_GEAENDERT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_GEAENDERT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_GEAENDERT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_GEAENDERT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_GEAENDERT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEAENDERT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEAENDERT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEAENDERT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEAENDERT", calNewValueFormated);
	}
		public boolean is_IST_GEAENDERT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEAENDERT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_GEAENDERT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEAENDERT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_KOMPLETT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_KOMPLETT");
	}

	public String get_IST_KOMPLETT_cF() throws myException
	{
		return this.get_FormatedValue("IST_KOMPLETT");	
	}

	public String get_IST_KOMPLETT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_KOMPLETT");
	}

	public String get_IST_KOMPLETT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_KOMPLETT",cNotNullValue);
	}

	public String get_IST_KOMPLETT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_KOMPLETT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_KOMPLETT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_KOMPLETT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_KOMPLETT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_KOMPLETT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_KOMPLETT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_KOMPLETT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_KOMPLETT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_KOMPLETT", calNewValueFormated);
	}
		public boolean is_IST_KOMPLETT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_KOMPLETT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_KOMPLETT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_KOMPLETT_cUF_NN("N").equals("N"))
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
		public String get_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE");
	}

	public String get_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("MENGE");	
	}

	public String get_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE");
	}

	public String get_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE",cNotNullValue);
	}

	public String get_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE", calNewValueFormated);
	}
		public Double get_MENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE").getDoubleValue();
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
	public String get_MENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE").getBigDecimalValue();
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
	
	
	public String get_MENGE_BUCH_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGE_BUCH");
	}

	public String get_MENGE_BUCH_cF() throws myException
	{
		return this.get_FormatedValue("MENGE_BUCH");	
	}

	public String get_MENGE_BUCH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGE_BUCH");
	}

	public String get_MENGE_BUCH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGE_BUCH",cNotNullValue);
	}

	public String get_MENGE_BUCH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGE_BUCH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGE_BUCH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGE_BUCH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGE_BUCH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGE_BUCH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_BUCH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_BUCH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGE_BUCH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGE_BUCH", calNewValueFormated);
	}
		public Double get_MENGE_BUCH_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGE_BUCH").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGE_BUCH_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGE_BUCH").getDoubleValue();
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
	public String get_MENGE_BUCH_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_BUCH_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGE_BUCH_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGE_BUCH_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGE_BUCH_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_BUCH").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGE_BUCH_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGE_BUCH").getBigDecimalValue();
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
	
	
	public String get_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREIS");
	}

	public String get_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("PREIS");	
	}

	public String get_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREIS");
	}

	public String get_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREIS",cNotNullValue);
	}

	public String get_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS", calNewValueFormated);
	}
		public Double get_PREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("PREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_PREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("PREIS").getDoubleValue();
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
	public String get_PREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_PREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_PREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("PREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_PREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("PREIS").getBigDecimalValue();
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
	
	
	public String get_SALDO_cUF() throws myException
	{
		return this.get_UnFormatedValue("SALDO");
	}

	public String get_SALDO_cF() throws myException
	{
		return this.get_FormatedValue("SALDO");	
	}

	public String get_SALDO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SALDO");
	}

	public String get_SALDO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SALDO",cNotNullValue);
	}

	public String get_SALDO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SALDO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SALDO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SALDO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SALDO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SALDO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SALDO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SALDO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SALDO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SALDO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SALDO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SALDO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SALDO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SALDO", calNewValueFormated);
	}
		public Double get_SALDO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SALDO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SALDO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SALDO").getDoubleValue();
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
	public String get_SALDO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SALDO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SALDO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SALDO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SALDO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SALDO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SALDO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SALDO").getBigDecimalValue();
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
	
	
	public String get_STATUS_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATUS_PREIS");
	}

	public String get_STATUS_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("STATUS_PREIS");	
	}

	public String get_STATUS_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATUS_PREIS");
	}

	public String get_STATUS_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATUS_PREIS",cNotNullValue);
	}

	public String get_STATUS_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATUS_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATUS_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATUS_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATUS_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATUS_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS_PREIS", calNewValueFormated);
	}
		public String get_STORNO_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNO");
	}

	public String get_STORNO_cF() throws myException
	{
		return this.get_FormatedValue("STORNO");	
	}

	public String get_STORNO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNO");
	}

	public String get_STORNO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNO",cNotNullValue);
	}

	public String get_STORNO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNO", calNewValueFormated);
	}
		public boolean is_STORNO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STORNO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNO_cUF_NN("N").equals("N"))
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
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSDATUM",MyRECORD.DATATYPES.DATE);
	put("BUCHUNGSTYP",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNGSZEIT",MyRECORD.DATATYPES.TEXT);
	put("BUCHUNG_HAND",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE_LAGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_SORTE",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGER_KONTO",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGER_KONTO_PARENT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_RG",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ORT",MyRECORD.DATATYPES.NUMBER);
	put("IST_GEAENDERT",MyRECORD.DATATYPES.YESNO);
	put("IST_KOMPLETT",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("MENGE_BUCH",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("PREIS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SALDO",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STATUS_PREIS",MyRECORD.DATATYPES.TEXT);
	put("STORNO",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_LAGER_KONTO.HM_FIELDS;	
	}

}
