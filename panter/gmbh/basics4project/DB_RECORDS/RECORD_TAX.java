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

public class RECORD_TAX extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_TAX";
    public static String IDFIELD   = "ID_TAX";
    

	//erzeugen eines RECORDNEW_JT_TAX - felds
	private RECORDNEW_TAX   recNEW = null;

    private _TAB  tab = _TAB.tax;  



	public RECORD_TAX() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_TAX");
	}


	public RECORD_TAX(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TAX");
	}



	public RECORD_TAX(RECORD_TAX recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TAX");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TAX.TABLENAME);
	}


	//2014-04-03
	public RECORD_TAX(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_TAX");
		this.set_Tablename_To_FieldMetaDefs(RECORD_TAX.TABLENAME);
	}




	public RECORD_TAX(long lID_Unformated) throws myException
	{
		super("JT_TAX","ID_TAX",""+lID_Unformated);
		this.set_TABLE_NAME("JT_TAX");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TAX.TABLENAME);
	}

	public RECORD_TAX(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_TAX");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TAX", "ID_TAX="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TAX", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TAX.TABLENAME);
	}
	
	

	public RECORD_TAX(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_TAX","ID_TAX",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_TAX");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TAX.TABLENAME);

	}


	public RECORD_TAX(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_TAX");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_TAX", "ID_TAX="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TAX", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_TAX.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_TAX();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_TAX.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_TAX";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_TAX_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_TAX_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TAX was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TAX", bibE2.cTO(), "ID_TAX="+this.get_ID_TAX_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_TAX was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_TAX", bibE2.cTO(), "ID_TAX="+this.get_ID_TAX_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_TAX WHERE ID_TAX="+this.get_ID_TAX_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_TAX WHERE ID_TAX="+this.get_ID_TAX_cUF();
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
			if (S.isFull(this.get_ID_TAX_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_TAX", "ID_TAX="+this.get_ID_TAX_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_TAX get_RECORDNEW_TAX() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_TAX();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_TAX(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_TAX create_Instance() throws myException {
		return new RECORD_TAX();
	}
	
	public static RECORD_TAX create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_TAX(Conn);
    }

	public static RECORD_TAX create_Instance(RECORD_TAX recordOrig) {
		return new RECORD_TAX(recordOrig);
    }

	public static RECORD_TAX create_Instance(long lID_Unformated) throws myException {
		return new RECORD_TAX(lID_Unformated);
    }

	public static RECORD_TAX create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_TAX(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_TAX create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_TAX(lID_Unformated, Conn);
	}

	public static RECORD_TAX create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_TAX(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_TAX create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_TAX(recordOrig);	    
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
    public RECORD_TAX build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_TAX WHERE ID_TAX="+this.get_ID_TAX_cUF());
      }
      //return new RECORD_TAX(this.get_cSQL_4_Build());
      RECORD_TAX rec = new RECORD_TAX();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_TAX
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_TAX-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_TAX get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_TAX_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_TAX  recNew = new RECORDNEW_TAX();
		
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
    public RECORD_TAX set_recordNew(RECORDNEW_TAX recnew) throws myException {
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
	
	



		private RECORD_LAND UP_RECORD_LAND_id_land = null;




		private RECORD_FIBU_KONTO UP_RECORD_FIBU_KONTO_id_fibu_konto_gs = null;




		private RECORD_FIBU_KONTO UP_RECORD_FIBU_KONTO_id_fibu_konto_re = null;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_tax = null ;




		private RECLIST_FIBU_KONTENREGEL DOWN_RECLIST_FIBU_KONTENREGEL_id_tax = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_tax_negativ_quelle = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_tax_negativ_ziel = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_tax_quelle = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_tax_ziel = null ;




		private RECLIST_TAX_AENDERUNGEN DOWN_RECLIST_TAX_AENDERUNGEN_id_tax = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_tax = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_ek = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_vk = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_tax = null ;




		private RECORD_TAX_GROUP UP_RECORD_TAX_GROUP_id_tax_group = null;






	
	/**
	 * References the Field ID_LAND
	 * Falls keine this.get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land==null)
		{
			this.UP_RECORD_LAND_id_land = new RECORD_LAND(this.get_ID_LAND_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land;
	}
	





	
	/**
	 * References the Field ID_FIBU_KONTO_GS
	 * Falls keine this.get_ID_FIBU_KONTO_GS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_FIBU_KONTO get_UP_RECORD_FIBU_KONTO_id_fibu_konto_gs() throws myException
	{
		if (S.isEmpty(this.get_ID_FIBU_KONTO_GS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_FIBU_KONTO_id_fibu_konto_gs==null)
		{
			this.UP_RECORD_FIBU_KONTO_id_fibu_konto_gs = new RECORD_FIBU_KONTO(this.get_ID_FIBU_KONTO_GS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_FIBU_KONTO_id_fibu_konto_gs;
	}
	





	
	/**
	 * References the Field ID_FIBU_KONTO_RE
	 * Falls keine this.get_ID_FIBU_KONTO_RE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_FIBU_KONTO get_UP_RECORD_FIBU_KONTO_id_fibu_konto_re() throws myException
	{
		if (S.isEmpty(this.get_ID_FIBU_KONTO_RE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_FIBU_KONTO_id_fibu_konto_re==null)
		{
			this.UP_RECORD_FIBU_KONTO_id_fibu_konto_re = new RECORD_FIBU_KONTO(this.get_ID_FIBU_KONTO_RE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_FIBU_KONTO_id_fibu_konto_re;
	}
	





	/**
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_tax() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_tax==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_tax = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_TAX="+this.get_ID_TAX_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_tax;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_tax(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_tax==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_TAX="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_tax = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_tax;
	}


	





	/**
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_id_tax() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_id_tax==null)
		{
			this.DOWN_RECLIST_FIBU_KONTENREGEL_id_tax = new RECLIST_FIBU_KONTENREGEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE ID_TAX="+this.get_ID_TAX_cUF()+" ORDER BY ID_FIBU_KONTENREGEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_id_tax;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_id_tax(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_id_tax==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE ID_TAX="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_KONTENREGEL_id_tax = new RECLIST_FIBU_KONTENREGEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_id_tax;
	}


	





	/**
	 * References the Field ID_TAX_NEGATIV_QUELLE 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_negativ_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_quelle==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_quelle = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_NEGATIV_QUELLE="+this.get_ID_TAX_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_quelle;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX_NEGATIV_QUELLE 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_negativ_quelle(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_quelle==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_NEGATIV_QUELLE="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_quelle = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_quelle;
	}


	





	/**
	 * References the Field ID_TAX_NEGATIV_ZIEL 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_negativ_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_ziel==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_ziel = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_NEGATIV_ZIEL="+this.get_ID_TAX_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX_NEGATIV_ZIEL 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_negativ_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_NEGATIV_ZIEL="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_ziel = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_negativ_ziel;
	}


	





	/**
	 * References the Field ID_TAX_QUELLE 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_quelle==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_tax_quelle = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_QUELLE="+this.get_ID_TAX_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_quelle;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX_QUELLE 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_quelle(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_quelle==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_QUELLE="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_tax_quelle = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_quelle;
	}


	





	/**
	 * References the Field ID_TAX_ZIEL 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_ziel==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_tax_ziel = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_ZIEL="+this.get_ID_TAX_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX_ZIEL 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_tax_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_tax_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_TAX_ZIEL="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_tax_ziel = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_tax_ziel;
	}


	





	/**
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TAX_AENDERUNGEN get_DOWN_RECORD_LIST_TAX_AENDERUNGEN_id_tax() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TAX_AENDERUNGEN_id_tax==null)
		{
			this.DOWN_RECLIST_TAX_AENDERUNGEN_id_tax = new RECLIST_TAX_AENDERUNGEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_TAX_AENDERUNGEN WHERE ID_TAX="+this.get_ID_TAX_cUF()+" ORDER BY ID_TAX_AENDERUNGEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_TAX_AENDERUNGEN_id_tax;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TAX_AENDERUNGEN get_DOWN_RECORD_LIST_TAX_AENDERUNGEN_id_tax(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TAX_AENDERUNGEN_id_tax==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_TAX_AENDERUNGEN WHERE ID_TAX="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_TAX_AENDERUNGEN_id_tax = new RECLIST_TAX_AENDERUNGEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_TAX_AENDERUNGEN_id_tax;
	}


	





	/**
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_tax() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_tax==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_tax = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_TAX="+this.get_ID_TAX_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_tax;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_tax(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_tax==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_TAX="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_tax = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_tax;
	}


	





	/**
	 * References the Field ID_TAX_EK 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_tax_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_ek==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_ek = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_TAX_EK="+this.get_ID_TAX_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_ek;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX_EK 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_tax_ek(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_ek==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_TAX_EK="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_ek = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_ek;
	}


	





	/**
	 * References the Field ID_TAX_VK 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_tax_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_vk==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_vk = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_TAX_VK="+this.get_ID_TAX_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_vk;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX_VK 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_tax_vk(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_vk==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_TAX_VK="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_vk = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_tax_vk;
	}


	





	/**
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_tax() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_tax==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_tax = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_TAX="+this.get_ID_TAX_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_tax;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_TAX 
	 * Falls keine get_ID_TAX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_tax(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_tax==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_TAX="+this.get_ID_TAX_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_tax = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_tax;
	}


	





	
	/**
	 * References the Field ID_TAX_GROUP
	 * Falls keine this.get_ID_TAX_GROUP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX_GROUP get_UP_RECORD_TAX_GROUP_id_tax_group() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_GROUP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_GROUP_id_tax_group==null)
		{
			this.UP_RECORD_TAX_GROUP_id_tax_group = new RECORD_TAX_GROUP(this.get_ID_TAX_GROUP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_GROUP_id_tax_group;
	}
	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__DROPDOWN_TEXT = "DROPDOWN_TEXT";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__HISTORISCH = "HISTORISCH";
	public static String FIELD__ID_FIBU_KONTO_GS = "ID_FIBU_KONTO_GS";
	public static String FIELD__ID_FIBU_KONTO_RE = "ID_FIBU_KONTO_RE";
	public static String FIELD__ID_LAND = "ID_LAND";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TAX = "ID_TAX";
	public static String FIELD__ID_TAX_GROUP = "ID_TAX_GROUP";
	public static String FIELD__INFO_TEXT_USER = "INFO_TEXT_USER";
	public static String FIELD__LEERVERMERK = "LEERVERMERK";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SORT = "SORT";
	public static String FIELD__STEUERSATZ = "STEUERSATZ";
	public static String FIELD__STEUERSATZ_NEU = "STEUERSATZ_NEU";
	public static String FIELD__STEUERVERMERK = "STEUERVERMERK";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TAXTYP = "TAXTYP";
	public static String FIELD__WECHSELDATUM = "WECHSELDATUM";


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
		public String get_DROPDOWN_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("DROPDOWN_TEXT");
	}

	public String get_DROPDOWN_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("DROPDOWN_TEXT");	
	}

	public String get_DROPDOWN_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DROPDOWN_TEXT");
	}

	public String get_DROPDOWN_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DROPDOWN_TEXT",cNotNullValue);
	}

	public String get_DROPDOWN_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DROPDOWN_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DROPDOWN_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DROPDOWN_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DROPDOWN_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DROPDOWN_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DROPDOWN_TEXT", calNewValueFormated);
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
		public String get_HISTORISCH_cUF() throws myException
	{
		return this.get_UnFormatedValue("HISTORISCH");
	}

	public String get_HISTORISCH_cF() throws myException
	{
		return this.get_FormatedValue("HISTORISCH");	
	}

	public String get_HISTORISCH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HISTORISCH");
	}

	public String get_HISTORISCH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HISTORISCH",cNotNullValue);
	}

	public String get_HISTORISCH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HISTORISCH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HISTORISCH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HISTORISCH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HISTORISCH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HISTORISCH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HISTORISCH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HISTORISCH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HISTORISCH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HISTORISCH", calNewValueFormated);
	}
		public boolean is_HISTORISCH_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_HISTORISCH_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_HISTORISCH_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_HISTORISCH_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_FIBU_KONTO_GS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU_KONTO_GS");
	}

	public String get_ID_FIBU_KONTO_GS_cF() throws myException
	{
		return this.get_FormatedValue("ID_FIBU_KONTO_GS");	
	}

	public String get_ID_FIBU_KONTO_GS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_FIBU_KONTO_GS");
	}

	public String get_ID_FIBU_KONTO_GS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU_KONTO_GS",cNotNullValue);
	}

	public String get_ID_FIBU_KONTO_GS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_FIBU_KONTO_GS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_FIBU_KONTO_GS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_KONTO_GS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_FIBU_KONTO_GS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_GS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_GS", calNewValueFormated);
	}
		public Long get_ID_FIBU_KONTO_GS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_FIBU_KONTO_GS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_FIBU_KONTO_GS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_FIBU_KONTO_GS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_FIBU_KONTO_GS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_FIBU_KONTO_GS").getDoubleValue();
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
	public String get_ID_FIBU_KONTO_GS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_KONTO_GS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_FIBU_KONTO_GS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_KONTO_GS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_FIBU_KONTO_GS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU_KONTO_GS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_FIBU_KONTO_GS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU_KONTO_GS").getBigDecimalValue();
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
	
	
	public String get_ID_FIBU_KONTO_RE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU_KONTO_RE");
	}

	public String get_ID_FIBU_KONTO_RE_cF() throws myException
	{
		return this.get_FormatedValue("ID_FIBU_KONTO_RE");	
	}

	public String get_ID_FIBU_KONTO_RE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_FIBU_KONTO_RE");
	}

	public String get_ID_FIBU_KONTO_RE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_FIBU_KONTO_RE",cNotNullValue);
	}

	public String get_ID_FIBU_KONTO_RE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_FIBU_KONTO_RE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_FIBU_KONTO_RE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_FIBU_KONTO_RE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_FIBU_KONTO_RE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_FIBU_KONTO_RE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_FIBU_KONTO_RE", calNewValueFormated);
	}
		public Long get_ID_FIBU_KONTO_RE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_FIBU_KONTO_RE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_FIBU_KONTO_RE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_FIBU_KONTO_RE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_FIBU_KONTO_RE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_FIBU_KONTO_RE").getDoubleValue();
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
	public String get_ID_FIBU_KONTO_RE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_KONTO_RE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_FIBU_KONTO_RE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_FIBU_KONTO_RE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_FIBU_KONTO_RE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU_KONTO_RE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_FIBU_KONTO_RE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_FIBU_KONTO_RE").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND");
	}

	public String get_ID_LAND_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND");	
	}

	public String get_ID_LAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND");
	}

	public String get_ID_LAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND",cNotNullValue);
	}

	public String get_ID_LAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND", calNewValueFormated);
	}
		public Long get_ID_LAND_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND").getDoubleValue();
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
	public String get_ID_LAND_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAND_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAND_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX");
	}

	public String get_ID_TAX_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX");	
	}

	public String get_ID_TAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX");
	}

	public String get_ID_TAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX",cNotNullValue);
	}

	public String get_ID_TAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX", calNewValueFormated);
	}
		public Long get_ID_TAX_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX").getDoubleValue();
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
	public String get_ID_TAX_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_GROUP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_GROUP");
	}

	public String get_ID_TAX_GROUP_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_GROUP");	
	}

	public String get_ID_TAX_GROUP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_GROUP");
	}

	public String get_ID_TAX_GROUP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_GROUP",cNotNullValue);
	}

	public String get_ID_TAX_GROUP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_GROUP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_GROUP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_GROUP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_GROUP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_GROUP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_GROUP", calNewValueFormated);
	}
		public Long get_ID_TAX_GROUP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_GROUP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_GROUP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_GROUP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_GROUP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_GROUP").getDoubleValue();
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
	public String get_ID_TAX_GROUP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_GROUP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_GROUP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_GROUP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_GROUP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_GROUP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_GROUP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_GROUP").getBigDecimalValue();
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
	
	
	public String get_INFO_TEXT_USER_cUF() throws myException
	{
		return this.get_UnFormatedValue("INFO_TEXT_USER");
	}

	public String get_INFO_TEXT_USER_cF() throws myException
	{
		return this.get_FormatedValue("INFO_TEXT_USER");	
	}

	public String get_INFO_TEXT_USER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INFO_TEXT_USER");
	}

	public String get_INFO_TEXT_USER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INFO_TEXT_USER",cNotNullValue);
	}

	public String get_INFO_TEXT_USER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INFO_TEXT_USER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INFO_TEXT_USER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INFO_TEXT_USER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INFO_TEXT_USER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INFO_TEXT_USER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INFO_TEXT_USER", calNewValueFormated);
	}
		public String get_LEERVERMERK_cUF() throws myException
	{
		return this.get_UnFormatedValue("LEERVERMERK");
	}

	public String get_LEERVERMERK_cF() throws myException
	{
		return this.get_FormatedValue("LEERVERMERK");	
	}

	public String get_LEERVERMERK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LEERVERMERK");
	}

	public String get_LEERVERMERK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LEERVERMERK",cNotNullValue);
	}

	public String get_LEERVERMERK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LEERVERMERK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LEERVERMERK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LEERVERMERK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LEERVERMERK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LEERVERMERK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEERVERMERK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEERVERMERK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEERVERMERK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEERVERMERK", calNewValueFormated);
	}
		public boolean is_LEERVERMERK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_LEERVERMERK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_LEERVERMERK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_LEERVERMERK_cUF_NN("N").equals("N"))
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
		public String get_SORT_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORT");
	}

	public String get_SORT_cF() throws myException
	{
		return this.get_FormatedValue("SORT");	
	}

	public String get_SORT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORT");
	}

	public String get_SORT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORT",cNotNullValue);
	}

	public String get_SORT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORT", calNewValueFormated);
	}
		public Long get_SORT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORT").getDoubleValue();
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
	public String get_SORT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORT").getBigDecimalValue();
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
	
	
	public String get_STEUERSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ");
	}

	public String get_STEUERSATZ_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ");	
	}

	public String get_STEUERSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ");
	}

	public String get_STEUERSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ",cNotNullValue);
	}

	public String get_STEUERSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ", calNewValueFormated);
	}
		public Double get_STEUERSATZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ").getDoubleValue();
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
	public String get_STEUERSATZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_STEUERSATZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_STEUERSATZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ").getBigDecimalValue();
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
	
	
	public String get_STEUERSATZ_NEU_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_NEU");
	}

	public String get_STEUERSATZ_NEU_cF() throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_NEU");	
	}

	public String get_STEUERSATZ_NEU_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERSATZ_NEU");
	}

	public String get_STEUERSATZ_NEU_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERSATZ_NEU",cNotNullValue);
	}

	public String get_STEUERSATZ_NEU_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERSATZ_NEU",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERSATZ_NEU", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERSATZ_NEU(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERSATZ_NEU", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERSATZ_NEU_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERSATZ_NEU", calNewValueFormated);
	}
		public Double get_STEUERSATZ_NEU_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_NEU").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_STEUERSATZ_NEU_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("STEUERSATZ_NEU").getDoubleValue();
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
	public String get_STEUERSATZ_NEU_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_NEU_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_STEUERSATZ_NEU_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_STEUERSATZ_NEU_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_STEUERSATZ_NEU_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_NEU").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_STEUERSATZ_NEU_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("STEUERSATZ_NEU").getBigDecimalValue();
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
	
	
	public String get_STEUERVERMERK_cUF() throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK");
	}

	public String get_STEUERVERMERK_cF() throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK");	
	}

	public String get_STEUERVERMERK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STEUERVERMERK");
	}

	public String get_STEUERVERMERK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STEUERVERMERK",cNotNullValue);
	}

	public String get_STEUERVERMERK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STEUERVERMERK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STEUERVERMERK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STEUERVERMERK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STEUERVERMERK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STEUERVERMERK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STEUERVERMERK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STEUERVERMERK", calNewValueFormated);
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
	
	
	public String get_TAXTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("TAXTYP");
	}

	public String get_TAXTYP_cF() throws myException
	{
		return this.get_FormatedValue("TAXTYP");	
	}

	public String get_TAXTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TAXTYP");
	}

	public String get_TAXTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TAXTYP",cNotNullValue);
	}

	public String get_TAXTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TAXTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TAXTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TAXTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TAXTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TAXTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAXTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TAXTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAXTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAXTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAXTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAXTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAXTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAXTYP", calNewValueFormated);
	}
		public String get_WECHSELDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("WECHSELDATUM");
	}

	public String get_WECHSELDATUM_cF() throws myException
	{
		return this.get_FormatedValue("WECHSELDATUM");	
	}

	public String get_WECHSELDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WECHSELDATUM");
	}

	public String get_WECHSELDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WECHSELDATUM",cNotNullValue);
	}

	public String get_WECHSELDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WECHSELDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WECHSELDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WECHSELDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WECHSELDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WECHSELDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WECHSELDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WECHSELDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WECHSELDATUM", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("DROPDOWN_TEXT",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("HISTORISCH",MyRECORD.DATATYPES.YESNO);
	put("ID_FIBU_KONTO_GS",MyRECORD.DATATYPES.NUMBER);
	put("ID_FIBU_KONTO_RE",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_GROUP",MyRECORD.DATATYPES.NUMBER);
	put("INFO_TEXT_USER",MyRECORD.DATATYPES.TEXT);
	put("LEERVERMERK",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SORT",MyRECORD.DATATYPES.NUMBER);
	put("STEUERSATZ",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERSATZ_NEU",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERVERMERK",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TAXTYP",MyRECORD.DATATYPES.TEXT);
	put("WECHSELDATUM",MyRECORD.DATATYPES.DATE);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_TAX.HM_FIELDS;	
	}

}
