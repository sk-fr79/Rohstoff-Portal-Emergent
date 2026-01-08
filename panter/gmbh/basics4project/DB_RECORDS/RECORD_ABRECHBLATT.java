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

public class RECORD_ABRECHBLATT extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ABRECHBLATT";
    public static String IDFIELD   = "ID_ABRECHBLATT";
    

	//erzeugen eines RECORDNEW_JT_ABRECHBLATT - felds
	private RECORDNEW_ABRECHBLATT   recNEW = null;

    private _TAB  tab = _TAB.abrechblatt;  



	public RECORD_ABRECHBLATT() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ABRECHBLATT");
	}


	public RECORD_ABRECHBLATT(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ABRECHBLATT");
	}



	public RECORD_ABRECHBLATT(RECORD_ABRECHBLATT recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ABRECHBLATT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT.TABLENAME);
	}


	//2014-04-03
	public RECORD_ABRECHBLATT(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ABRECHBLATT");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT.TABLENAME);
	}




	public RECORD_ABRECHBLATT(long lID_Unformated) throws myException
	{
		super("JT_ABRECHBLATT","ID_ABRECHBLATT",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ABRECHBLATT");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT.TABLENAME);
	}

	public RECORD_ABRECHBLATT(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ABRECHBLATT");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT", "ID_ABRECHBLATT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT.TABLENAME);
	}
	
	

	public RECORD_ABRECHBLATT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ABRECHBLATT","ID_ABRECHBLATT",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ABRECHBLATT");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT.TABLENAME);

	}


	public RECORD_ABRECHBLATT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ABRECHBLATT");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT", "ID_ABRECHBLATT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ABRECHBLATT();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ABRECHBLATT.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ABRECHBLATT";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ABRECHBLATT_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ABRECHBLATT_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ABRECHBLATT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ABRECHBLATT", bibE2.cTO(), "ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ABRECHBLATT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ABRECHBLATT", bibE2.cTO(), "ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF();
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
			if (S.isFull(this.get_ID_ABRECHBLATT_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT", "ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ABRECHBLATT get_RECORDNEW_ABRECHBLATT() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ABRECHBLATT();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ABRECHBLATT(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ABRECHBLATT create_Instance() throws myException {
		return new RECORD_ABRECHBLATT();
	}
	
	public static RECORD_ABRECHBLATT create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ABRECHBLATT(Conn);
    }

	public static RECORD_ABRECHBLATT create_Instance(RECORD_ABRECHBLATT recordOrig) {
		return new RECORD_ABRECHBLATT(recordOrig);
    }

	public static RECORD_ABRECHBLATT create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ABRECHBLATT(lID_Unformated);
    }

	public static RECORD_ABRECHBLATT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ABRECHBLATT(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ABRECHBLATT create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ABRECHBLATT(lID_Unformated, Conn);
	}

	public static RECORD_ABRECHBLATT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ABRECHBLATT(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ABRECHBLATT create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ABRECHBLATT(recordOrig);	    
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
    public RECORD_ABRECHBLATT build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF());
      }
      //return new RECORD_ABRECHBLATT(this.get_cSQL_4_Build());
      RECORD_ABRECHBLATT rec = new RECORD_ABRECHBLATT();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ABRECHBLATT
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ABRECHBLATT-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ABRECHBLATT get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ABRECHBLATT_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ABRECHBLATT  recNew = new RECORDNEW_ABRECHBLATT();
		
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
    public RECORD_ABRECHBLATT set_recordNew(RECORDNEW_ABRECHBLATT recnew) throws myException {
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
	
	



		private RECLIST_ABRECHBLATT_ARTBEZ_IN DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt = null ;




		private RECLIST_ABRECHBLATT_ARTBEZ_OUT DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt = null ;




		private RECLIST_ABRECHBLATT_POS DOWN_RECLIST_ABRECHBLATT_POS_id_abrechblatt = null ;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_kunde = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lager_eigen = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lieferant = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dl = null;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_abrech = null;






	/**
	 * References the Field ID_ABRECHBLATT 
	 * Falls keine get_ID_ABRECHBLATT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_IN get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt() throws myException
	{
		if (S.isEmpty(this.get_ID_ABRECHBLATT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt = new RECLIST_ABRECHBLATT_ARTBEZ_IN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_IN WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF()+" ORDER BY ID_ABRECHBLATT_ARTBEZ_IN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ABRECHBLATT 
	 * Falls keine get_ID_ABRECHBLATT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_IN get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ABRECHBLATT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_IN WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt = new RECLIST_ABRECHBLATT_ARTBEZ_IN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_abrechblatt;
	}


	





	/**
	 * References the Field ID_ABRECHBLATT 
	 * Falls keine get_ID_ABRECHBLATT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_OUT get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt() throws myException
	{
		if (S.isEmpty(this.get_ID_ABRECHBLATT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt = new RECLIST_ABRECHBLATT_ARTBEZ_OUT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_OUT WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF()+" ORDER BY ID_ABRECHBLATT_ARTBEZ_OUT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ABRECHBLATT 
	 * Falls keine get_ID_ABRECHBLATT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_OUT get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ABRECHBLATT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_OUT WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt = new RECLIST_ABRECHBLATT_ARTBEZ_OUT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_abrechblatt;
	}


	





	/**
	 * References the Field ID_ABRECHBLATT 
	 * Falls keine get_ID_ABRECHBLATT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_POS get_DOWN_RECORD_LIST_ABRECHBLATT_POS_id_abrechblatt() throws myException
	{
		if (S.isEmpty(this.get_ID_ABRECHBLATT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_POS_id_abrechblatt==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_POS_id_abrechblatt = new RECLIST_ABRECHBLATT_POS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF()+" ORDER BY ID_ABRECHBLATT_POS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_POS_id_abrechblatt;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ABRECHBLATT 
	 * Falls keine get_ID_ABRECHBLATT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_POS get_DOWN_RECORD_LIST_ABRECHBLATT_POS_id_abrechblatt(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ABRECHBLATT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_POS_id_abrechblatt==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_ABRECHBLATT="+this.get_ID_ABRECHBLATT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_POS_id_abrechblatt = new RECLIST_ABRECHBLATT_POS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_POS_id_abrechblatt;
	}


	





	
	/**
	 * References the Field ID_ADRESSE_KUNDE
	 * Falls keine this.get_ID_ADRESSE_KUNDE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_kunde() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_KUNDE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_kunde==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_kunde = new RECORD_ADRESSE(this.get_ID_ADRESSE_KUNDE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_kunde;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_LAGER_EIGEN
	 * Falls keine this.get_ID_ADRESSE_LAGER_EIGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lager_eigen() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LAGER_EIGEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lager_eigen==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lager_eigen = new RECORD_ADRESSE(this.get_ID_ADRESSE_LAGER_EIGEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lager_eigen;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_LIEFERANT
	 * Falls keine this.get_ID_ADRESSE_LIEFERANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lieferant() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LIEFERANT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lieferant==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lieferant = new RECORD_ADRESSE(this.get_ID_ADRESSE_LIEFERANT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lieferant;
	}
	





	
	/**
	 * References the Field ID_ARTIKEL_BEZ_DL
	 * Falls keine this.get_ID_ARTIKEL_BEZ_DL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_BEZ get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dl() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_DL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dl==null)
		{
			this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dl = new RECORD_ARTIKEL_BEZ(this.get_ID_ARTIKEL_BEZ_DL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_dl;
	}
	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE_ABRECH
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_ABRECH_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_abrech() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_ABRECH_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_abrech==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_abrech = new RECORD_VPOS_TPA_FUHRE(this.get_ID_VPOS_TPA_FUHRE_ABRECH_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_abrech;
	}
	

	public static String FIELD__BEMERKUNG_ABRECHUNG_INTERN = "BEMERKUNG_ABRECHUNG_INTERN";
	public static String FIELD__BEMERKUNG_ABRECHUNG_KUNDE = "BEMERKUNG_ABRECHUNG_KUNDE";
	public static String FIELD__BLATT_ABGESCHLOSSEN = "BLATT_ABGESCHLOSSEN";
	public static String FIELD__DATUM_ABRECHNUNG_BEGINN = "DATUM_ABRECHNUNG_BEGINN";
	public static String FIELD__DATUM_ABRECHNUNG_ENDE = "DATUM_ABRECHNUNG_ENDE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ABRECHBLATT = "ID_ABRECHBLATT";
	public static String FIELD__ID_ADRESSE_KUNDE = "ID_ADRESSE_KUNDE";
	public static String FIELD__ID_ADRESSE_LAGER_EIGEN = "ID_ADRESSE_LAGER_EIGEN";
	public static String FIELD__ID_ADRESSE_LIEFERANT = "ID_ADRESSE_LIEFERANT";
	public static String FIELD__ID_ARTIKEL_BEZ_DL = "ID_ARTIKEL_BEZ_DL";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ABRECH = "ID_VPOS_TPA_FUHRE_ABRECH";
	public static String FIELD__LAGERBESTAND_BEGINN = "LAGERBESTAND_BEGINN";
	public static String FIELD__LAGERBESTAND_ENDE = "LAGERBESTAND_ENDE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_BEMERKUNG_ABRECHUNG_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ABRECHUNG_INTERN");
	}

	public String get_BEMERKUNG_ABRECHUNG_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ABRECHUNG_INTERN");	
	}

	public String get_BEMERKUNG_ABRECHUNG_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_ABRECHUNG_INTERN");
	}

	public String get_BEMERKUNG_ABRECHUNG_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ABRECHUNG_INTERN",cNotNullValue);
	}

	public String get_BEMERKUNG_ABRECHUNG_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ABRECHUNG_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_ABRECHUNG_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_ABRECHUNG_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_INTERN", calNewValueFormated);
	}
		public String get_BEMERKUNG_ABRECHUNG_KUNDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ABRECHUNG_KUNDE");
	}

	public String get_BEMERKUNG_ABRECHUNG_KUNDE_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ABRECHUNG_KUNDE");	
	}

	public String get_BEMERKUNG_ABRECHUNG_KUNDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_ABRECHUNG_KUNDE");
	}

	public String get_BEMERKUNG_ABRECHUNG_KUNDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ABRECHUNG_KUNDE",cNotNullValue);
	}

	public String get_BEMERKUNG_ABRECHUNG_KUNDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ABRECHUNG_KUNDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_KUNDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_KUNDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_ABRECHUNG_KUNDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_ABRECHUNG_KUNDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_KUNDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_KUNDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_KUNDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_KUNDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_KUNDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_KUNDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ABRECHUNG_KUNDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ABRECHUNG_KUNDE", calNewValueFormated);
	}
		public String get_BLATT_ABGESCHLOSSEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BLATT_ABGESCHLOSSEN");
	}

	public String get_BLATT_ABGESCHLOSSEN_cF() throws myException
	{
		return this.get_FormatedValue("BLATT_ABGESCHLOSSEN");	
	}

	public String get_BLATT_ABGESCHLOSSEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BLATT_ABGESCHLOSSEN");
	}

	public String get_BLATT_ABGESCHLOSSEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BLATT_ABGESCHLOSSEN",cNotNullValue);
	}

	public String get_BLATT_ABGESCHLOSSEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BLATT_ABGESCHLOSSEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BLATT_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BLATT_ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BLATT_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BLATT_ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BLATT_ABGESCHLOSSEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BLATT_ABGESCHLOSSEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BLATT_ABGESCHLOSSEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BLATT_ABGESCHLOSSEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BLATT_ABGESCHLOSSEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BLATT_ABGESCHLOSSEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BLATT_ABGESCHLOSSEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BLATT_ABGESCHLOSSEN", calNewValueFormated);
	}
		public boolean is_BLATT_ABGESCHLOSSEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_BLATT_ABGESCHLOSSEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_BLATT_ABGESCHLOSSEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_BLATT_ABGESCHLOSSEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_DATUM_ABRECHNUNG_BEGINN_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABRECHNUNG_BEGINN");
	}

	public String get_DATUM_ABRECHNUNG_BEGINN_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ABRECHNUNG_BEGINN");	
	}

	public String get_DATUM_ABRECHNUNG_BEGINN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ABRECHNUNG_BEGINN");
	}

	public String get_DATUM_ABRECHNUNG_BEGINN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABRECHNUNG_BEGINN",cNotNullValue);
	}

	public String get_DATUM_ABRECHNUNG_BEGINN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ABRECHNUNG_BEGINN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_BEGINN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ABRECHNUNG_BEGINN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABRECHNUNG_BEGINN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ABRECHNUNG_BEGINN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_BEGINN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_BEGINN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_BEGINN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_BEGINN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_BEGINN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_BEGINN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_BEGINN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_BEGINN", calNewValueFormated);
	}
		public String get_DATUM_ABRECHNUNG_ENDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABRECHNUNG_ENDE");
	}

	public String get_DATUM_ABRECHNUNG_ENDE_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_ABRECHNUNG_ENDE");	
	}

	public String get_DATUM_ABRECHNUNG_ENDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_ABRECHNUNG_ENDE");
	}

	public String get_DATUM_ABRECHNUNG_ENDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_ABRECHNUNG_ENDE",cNotNullValue);
	}

	public String get_DATUM_ABRECHNUNG_ENDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_ABRECHNUNG_ENDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_ENDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_ABRECHNUNG_ENDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_ABRECHNUNG_ENDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_ABRECHNUNG_ENDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_ENDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_ENDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_ENDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_ENDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_ENDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_ENDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_ABRECHNUNG_ENDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_ABRECHNUNG_ENDE", calNewValueFormated);
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
		public String get_ID_ABRECHBLATT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ABRECHBLATT");
	}

	public String get_ID_ABRECHBLATT_cF() throws myException
	{
		return this.get_FormatedValue("ID_ABRECHBLATT");	
	}

	public String get_ID_ABRECHBLATT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ABRECHBLATT");
	}

	public String get_ID_ABRECHBLATT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ABRECHBLATT",cNotNullValue);
	}

	public String get_ID_ABRECHBLATT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ABRECHBLATT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ABRECHBLATT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ABRECHBLATT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ABRECHBLATT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", calNewValueFormated);
	}
		public Long get_ID_ABRECHBLATT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ABRECHBLATT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ABRECHBLATT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ABRECHBLATT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ABRECHBLATT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ABRECHBLATT").getDoubleValue();
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
	public String get_ID_ABRECHBLATT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ABRECHBLATT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ABRECHBLATT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ABRECHBLATT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ABRECHBLATT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ABRECHBLATT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ABRECHBLATT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ABRECHBLATT").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_KUNDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_KUNDE");
	}

	public String get_ID_ADRESSE_KUNDE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_KUNDE");	
	}

	public String get_ID_ADRESSE_KUNDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_KUNDE");
	}

	public String get_ID_ADRESSE_KUNDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_KUNDE",cNotNullValue);
	}

	public String get_ID_ADRESSE_KUNDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_KUNDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KUNDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_KUNDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_KUNDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_KUNDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KUNDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_KUNDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KUNDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_KUNDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KUNDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_KUNDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_KUNDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_KUNDE", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_KUNDE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_KUNDE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_KUNDE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_KUNDE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_KUNDE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_KUNDE").getDoubleValue();
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
	public String get_ID_ADRESSE_KUNDE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_KUNDE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_KUNDE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_KUNDE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_KUNDE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_KUNDE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_KUNDE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_KUNDE").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LAGER_EIGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_EIGEN");
	}

	public String get_ID_ADRESSE_LAGER_EIGEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_EIGEN");	
	}

	public String get_ID_ADRESSE_LAGER_EIGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER_EIGEN");
	}

	public String get_ID_ADRESSE_LAGER_EIGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_EIGEN",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_EIGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_EIGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_EIGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER_EIGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_EIGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_EIGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_EIGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_EIGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_EIGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_EIGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_EIGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_EIGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_EIGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_EIGEN", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_EIGEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER_EIGEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_EIGEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_EIGEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_EIGEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_EIGEN").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_EIGEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_EIGEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_LAGER_EIGEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_EIGEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_LAGER_EIGEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_EIGEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_EIGEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_EIGEN").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LIEFERANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LIEFERANT");
	}

	public String get_ID_ADRESSE_LIEFERANT_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LIEFERANT");	
	}

	public String get_ID_ADRESSE_LIEFERANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LIEFERANT");
	}

	public String get_ID_ADRESSE_LIEFERANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LIEFERANT",cNotNullValue);
	}

	public String get_ID_ADRESSE_LIEFERANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LIEFERANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LIEFERANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LIEFERANT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LIEFERANT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LIEFERANT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LIEFERANT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LIEFERANT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LIEFERANT").getDoubleValue();
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
	public String get_ID_ADRESSE_LIEFERANT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LIEFERANT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_LIEFERANT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LIEFERANT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_LIEFERANT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LIEFERANT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LIEFERANT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LIEFERANT").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_BEZ_DL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_DL");
	}

	public String get_ID_ARTIKEL_BEZ_DL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_DL");	
	}

	public String get_ID_ARTIKEL_BEZ_DL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_BEZ_DL");
	}

	public String get_ID_ARTIKEL_BEZ_DL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ_DL",cNotNullValue);
	}

	public String get_ID_ARTIKEL_BEZ_DL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ_DL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ_DL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ_DL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_DL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ_DL", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_BEZ_DL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_BEZ_DL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_BEZ_DL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_DL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_BEZ_DL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ_DL").getDoubleValue();
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
	public String get_ID_ARTIKEL_BEZ_DL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_DL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_BEZ_DL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_DL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_BEZ_DL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_DL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_BEZ_DL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ_DL").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_ABRECH_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ABRECH");
	}

	public String get_ID_VPOS_TPA_FUHRE_ABRECH_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ABRECH");	
	}

	public String get_ID_VPOS_TPA_FUHRE_ABRECH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_ABRECH");
	}

	public String get_ID_VPOS_TPA_FUHRE_ABRECH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ABRECH",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_ABRECH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ABRECH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ABRECH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ABRECH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ABRECH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ABRECH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ABRECH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ABRECH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ABRECH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ABRECH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ABRECH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ABRECH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ABRECH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ABRECH", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_ABRECH_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_ABRECH").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_ABRECH_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ABRECH").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_ABRECH_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ABRECH").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_ABRECH_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ABRECH_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_ABRECH_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ABRECH_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_ABRECH_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ABRECH").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_ABRECH_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ABRECH").getBigDecimalValue();
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
	
	
	public String get_LAGERBESTAND_BEGINN_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAGERBESTAND_BEGINN");
	}

	public String get_LAGERBESTAND_BEGINN_cF() throws myException
	{
		return this.get_FormatedValue("LAGERBESTAND_BEGINN");	
	}

	public String get_LAGERBESTAND_BEGINN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAGERBESTAND_BEGINN");
	}

	public String get_LAGERBESTAND_BEGINN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAGERBESTAND_BEGINN",cNotNullValue);
	}

	public String get_LAGERBESTAND_BEGINN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAGERBESTAND_BEGINN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_BEGINN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAGERBESTAND_BEGINN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAGERBESTAND_BEGINN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAGERBESTAND_BEGINN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_BEGINN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAGERBESTAND_BEGINN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_BEGINN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERBESTAND_BEGINN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_BEGINN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERBESTAND_BEGINN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_BEGINN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERBESTAND_BEGINN", calNewValueFormated);
	}
		public Double get_LAGERBESTAND_BEGINN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LAGERBESTAND_BEGINN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LAGERBESTAND_BEGINN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LAGERBESTAND_BEGINN").getDoubleValue();
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
	public String get_LAGERBESTAND_BEGINN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGERBESTAND_BEGINN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LAGERBESTAND_BEGINN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGERBESTAND_BEGINN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LAGERBESTAND_BEGINN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LAGERBESTAND_BEGINN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LAGERBESTAND_BEGINN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LAGERBESTAND_BEGINN").getBigDecimalValue();
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
	
	
	public String get_LAGERBESTAND_ENDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAGERBESTAND_ENDE");
	}

	public String get_LAGERBESTAND_ENDE_cF() throws myException
	{
		return this.get_FormatedValue("LAGERBESTAND_ENDE");	
	}

	public String get_LAGERBESTAND_ENDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAGERBESTAND_ENDE");
	}

	public String get_LAGERBESTAND_ENDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAGERBESTAND_ENDE",cNotNullValue);
	}

	public String get_LAGERBESTAND_ENDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAGERBESTAND_ENDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_ENDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAGERBESTAND_ENDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAGERBESTAND_ENDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAGERBESTAND_ENDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_ENDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAGERBESTAND_ENDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_ENDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERBESTAND_ENDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_ENDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERBESTAND_ENDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGERBESTAND_ENDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGERBESTAND_ENDE", calNewValueFormated);
	}
		public Double get_LAGERBESTAND_ENDE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LAGERBESTAND_ENDE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LAGERBESTAND_ENDE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LAGERBESTAND_ENDE").getDoubleValue();
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
	public String get_LAGERBESTAND_ENDE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGERBESTAND_ENDE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LAGERBESTAND_ENDE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGERBESTAND_ENDE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LAGERBESTAND_ENDE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LAGERBESTAND_ENDE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LAGERBESTAND_ENDE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LAGERBESTAND_ENDE").getBigDecimalValue();
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
	
	


	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("BEMERKUNG_ABRECHUNG_INTERN",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_ABRECHUNG_KUNDE",MyRECORD.DATATYPES.TEXT);
	put("BLATT_ABGESCHLOSSEN",MyRECORD.DATATYPES.YESNO);
	put("DATUM_ABRECHNUNG_BEGINN",MyRECORD.DATATYPES.DATE);
	put("DATUM_ABRECHNUNG_ENDE",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ABRECHBLATT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_KUNDE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER_EIGEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LIEFERANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ_DL",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ABRECH",MyRECORD.DATATYPES.NUMBER);
	put("LAGERBESTAND_BEGINN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("LAGERBESTAND_ENDE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ABRECHBLATT.HM_FIELDS;	
	}

}
