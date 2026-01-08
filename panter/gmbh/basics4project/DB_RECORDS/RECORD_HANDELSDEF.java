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

public class RECORD_HANDELSDEF extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_HANDELSDEF";
    public static String IDFIELD   = "ID_HANDELSDEF";
    

	//erzeugen eines RECORDNEW_JT_HANDELSDEF - felds
	private RECORDNEW_HANDELSDEF   recNEW = null;

    private _TAB  tab = _TAB.handelsdef;  



	public RECORD_HANDELSDEF() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_HANDELSDEF");
	}


	public RECORD_HANDELSDEF(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_HANDELSDEF");
	}



	public RECORD_HANDELSDEF(RECORD_HANDELSDEF recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_HANDELSDEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_HANDELSDEF.TABLENAME);
	}


	//2014-04-03
	public RECORD_HANDELSDEF(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_HANDELSDEF");
		this.set_Tablename_To_FieldMetaDefs(RECORD_HANDELSDEF.TABLENAME);
	}




	public RECORD_HANDELSDEF(long lID_Unformated) throws myException
	{
		super("JT_HANDELSDEF","ID_HANDELSDEF",""+lID_Unformated);
		this.set_TABLE_NAME("JT_HANDELSDEF");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_HANDELSDEF.TABLENAME);
	}

	public RECORD_HANDELSDEF(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_HANDELSDEF");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_HANDELSDEF", "ID_HANDELSDEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_HANDELSDEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_HANDELSDEF.TABLENAME);
	}
	
	

	public RECORD_HANDELSDEF(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_HANDELSDEF","ID_HANDELSDEF",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_HANDELSDEF");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_HANDELSDEF.TABLENAME);

	}


	public RECORD_HANDELSDEF(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_HANDELSDEF");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_HANDELSDEF", "ID_HANDELSDEF="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_HANDELSDEF", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_HANDELSDEF.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_HANDELSDEF();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_HANDELSDEF.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_HANDELSDEF";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_HANDELSDEF_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_HANDELSDEF_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_HANDELSDEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_HANDELSDEF", bibE2.cTO(), "ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_HANDELSDEF was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_HANDELSDEF", bibE2.cTO(), "ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF();
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
			if (S.isFull(this.get_ID_HANDELSDEF_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_HANDELSDEF", "ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_HANDELSDEF get_RECORDNEW_HANDELSDEF() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_HANDELSDEF();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_HANDELSDEF(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_HANDELSDEF create_Instance() throws myException {
		return new RECORD_HANDELSDEF();
	}
	
	public static RECORD_HANDELSDEF create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_HANDELSDEF(Conn);
    }

	public static RECORD_HANDELSDEF create_Instance(RECORD_HANDELSDEF recordOrig) {
		return new RECORD_HANDELSDEF(recordOrig);
    }

	public static RECORD_HANDELSDEF create_Instance(long lID_Unformated) throws myException {
		return new RECORD_HANDELSDEF(lID_Unformated);
    }

	public static RECORD_HANDELSDEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_HANDELSDEF(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_HANDELSDEF create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_HANDELSDEF(lID_Unformated, Conn);
	}

	public static RECORD_HANDELSDEF create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_HANDELSDEF(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_HANDELSDEF create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_HANDELSDEF(recordOrig);	    
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
    public RECORD_HANDELSDEF build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF());
      }
      //return new RECORD_HANDELSDEF(this.get_cSQL_4_Build());
      RECORD_HANDELSDEF rec = new RECORD_HANDELSDEF();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_HANDELSDEF
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_HANDELSDEF-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_HANDELSDEF get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_HANDELSDEF_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_HANDELSDEF  recNew = new RECORDNEW_HANDELSDEF();
		
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
    public RECORD_HANDELSDEF set_recordNew(RECORDNEW_HANDELSDEF recnew) throws myException {
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
	
	



		private RECORD_LAND UP_RECORD_LAND_id_land_quelle_geo = null;




		private RECORD_LAND UP_RECORD_LAND_id_land_quelle_jur = null;




		private RECORD_LAND UP_RECORD_LAND_id_land_ziel_geo = null;




		private RECORD_LAND UP_RECORD_LAND_id_land_ziel_jur = null;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_handelsdef = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_handelsdef = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_handelsdef = null ;




		private RECORD_TAX UP_RECORD_TAX_id_tax_negativ_quelle = null;




		private RECORD_TAX UP_RECORD_TAX_id_tax_negativ_ziel = null;




		private RECORD_TAX UP_RECORD_TAX_id_tax_quelle = null;




		private RECORD_TAX UP_RECORD_TAX_id_tax_ziel = null;






	
	/**
	 * References the Field ID_LAND_QUELLE_GEO
	 * Falls keine this.get_ID_LAND_QUELLE_GEO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land_quelle_geo() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_QUELLE_GEO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land_quelle_geo==null)
		{
			this.UP_RECORD_LAND_id_land_quelle_geo = new RECORD_LAND(this.get_ID_LAND_QUELLE_GEO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land_quelle_geo;
	}
	





	
	/**
	 * References the Field ID_LAND_QUELLE_JUR
	 * Falls keine this.get_ID_LAND_QUELLE_JUR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land_quelle_jur() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_QUELLE_JUR_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land_quelle_jur==null)
		{
			this.UP_RECORD_LAND_id_land_quelle_jur = new RECORD_LAND(this.get_ID_LAND_QUELLE_JUR_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land_quelle_jur;
	}
	





	
	/**
	 * References the Field ID_LAND_ZIEL_GEO
	 * Falls keine this.get_ID_LAND_ZIEL_GEO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land_ziel_geo() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_ZIEL_GEO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land_ziel_geo==null)
		{
			this.UP_RECORD_LAND_id_land_ziel_geo = new RECORD_LAND(this.get_ID_LAND_ZIEL_GEO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land_ziel_geo;
	}
	





	
	/**
	 * References the Field ID_LAND_ZIEL_JUR
	 * Falls keine this.get_ID_LAND_ZIEL_JUR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAND get_UP_RECORD_LAND_id_land_ziel_jur() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_ZIEL_JUR_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAND_id_land_ziel_jur==null)
		{
			this.UP_RECORD_LAND_id_land_ziel_jur = new RECORD_LAND(this.get_ID_LAND_ZIEL_JUR_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAND_id_land_ziel_jur;
	}
	





	/**
	 * References the Field ID_HANDELSDEF 
	 * Falls keine get_ID_HANDELSDEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_handelsdef() throws myException
	{
		if (S.isEmpty(this.get_ID_HANDELSDEF_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_handelsdef==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_handelsdef = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_handelsdef;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_HANDELSDEF 
	 * Falls keine get_ID_HANDELSDEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_handelsdef(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_HANDELSDEF_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_handelsdef==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_handelsdef = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_handelsdef;
	}


	





	/**
	 * References the Field ID_HANDELSDEF 
	 * Falls keine get_ID_HANDELSDEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_handelsdef() throws myException
	{
		if (S.isEmpty(this.get_ID_HANDELSDEF_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_handelsdef==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_handelsdef = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_handelsdef;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_HANDELSDEF 
	 * Falls keine get_ID_HANDELSDEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_handelsdef(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_HANDELSDEF_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_handelsdef==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_handelsdef = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_handelsdef;
	}


	





	/**
	 * References the Field ID_HANDELSDEF 
	 * Falls keine get_ID_HANDELSDEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_handelsdef() throws myException
	{
		if (S.isEmpty(this.get_ID_HANDELSDEF_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_handelsdef==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_handelsdef = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_handelsdef;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_HANDELSDEF 
	 * Falls keine get_ID_HANDELSDEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_handelsdef(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_HANDELSDEF_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_handelsdef==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_HANDELSDEF="+this.get_ID_HANDELSDEF_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_handelsdef = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_handelsdef;
	}


	





	
	/**
	 * References the Field ID_TAX_NEGATIV_QUELLE
	 * Falls keine this.get_ID_TAX_NEGATIV_QUELLE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX get_UP_RECORD_TAX_id_tax_negativ_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_NEGATIV_QUELLE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_id_tax_negativ_quelle==null)
		{
			this.UP_RECORD_TAX_id_tax_negativ_quelle = new RECORD_TAX(this.get_ID_TAX_NEGATIV_QUELLE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_id_tax_negativ_quelle;
	}
	





	
	/**
	 * References the Field ID_TAX_NEGATIV_ZIEL
	 * Falls keine this.get_ID_TAX_NEGATIV_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX get_UP_RECORD_TAX_id_tax_negativ_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_NEGATIV_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_id_tax_negativ_ziel==null)
		{
			this.UP_RECORD_TAX_id_tax_negativ_ziel = new RECORD_TAX(this.get_ID_TAX_NEGATIV_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_id_tax_negativ_ziel;
	}
	





	
	/**
	 * References the Field ID_TAX_QUELLE
	 * Falls keine this.get_ID_TAX_QUELLE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX get_UP_RECORD_TAX_id_tax_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_QUELLE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_id_tax_quelle==null)
		{
			this.UP_RECORD_TAX_id_tax_quelle = new RECORD_TAX(this.get_ID_TAX_QUELLE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_id_tax_quelle;
	}
	





	
	/**
	 * References the Field ID_TAX_ZIEL
	 * Falls keine this.get_ID_TAX_ZIEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_TAX get_UP_RECORD_TAX_id_tax_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_TAX_ZIEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_TAX_id_tax_ziel==null)
		{
			this.UP_RECORD_TAX_id_tax_ziel = new RECORD_TAX(this.get_ID_TAX_ZIEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_TAX_id_tax_ziel;
	}
	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_HANDELSDEF = "ID_HANDELSDEF";
	public static String FIELD__ID_LAND_QUELLE_GEO = "ID_LAND_QUELLE_GEO";
	public static String FIELD__ID_LAND_QUELLE_JUR = "ID_LAND_QUELLE_JUR";
	public static String FIELD__ID_LAND_ZIEL_GEO = "ID_LAND_ZIEL_GEO";
	public static String FIELD__ID_LAND_ZIEL_JUR = "ID_LAND_ZIEL_JUR";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_TAX_NEGATIV_QUELLE = "ID_TAX_NEGATIV_QUELLE";
	public static String FIELD__ID_TAX_NEGATIV_ZIEL = "ID_TAX_NEGATIV_ZIEL";
	public static String FIELD__ID_TAX_QUELLE = "ID_TAX_QUELLE";
	public static String FIELD__ID_TAX_ZIEL = "ID_TAX_ZIEL";
	public static String FIELD__INTRASTAT_MELD_IN = "INTRASTAT_MELD_IN";
	public static String FIELD__INTRASTAT_MELD_OUT = "INTRASTAT_MELD_OUT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MELDUNG_FUER_USER = "MELDUNG_FUER_USER";
	public static String FIELD__QUELLE_IST_MANDANT = "QUELLE_IST_MANDANT";
	public static String FIELD__SORTE_DIENSTLEIST_QUELLE = "SORTE_DIENSTLEIST_QUELLE";
	public static String FIELD__SORTE_DIENSTLEIST_ZIEL = "SORTE_DIENSTLEIST_ZIEL";
	public static String FIELD__SORTE_EOW_QUELLE = "SORTE_EOW_QUELLE";
	public static String FIELD__SORTE_EOW_ZIEL = "SORTE_EOW_ZIEL";
	public static String FIELD__SORTE_PRODUKT_QUELLE = "SORTE_PRODUKT_QUELLE";
	public static String FIELD__SORTE_PRODUKT_ZIEL = "SORTE_PRODUKT_ZIEL";
	public static String FIELD__SORTE_RC_QUELLE = "SORTE_RC_QUELLE";
	public static String FIELD__SORTE_RC_ZIEL = "SORTE_RC_ZIEL";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TP_VERANTWORTUNG = "TP_VERANTWORTUNG";
	public static String FIELD__TRANSIT_EK = "TRANSIT_EK";
	public static String FIELD__TRANSIT_VK = "TRANSIT_VK";
	public static String FIELD__TYP_MELDUNG = "TYP_MELDUNG";
	public static String FIELD__UST_TEILNEHMER_QUELLE = "UST_TEILNEHMER_QUELLE";
	public static String FIELD__UST_TEILNEHMER_ZIEL = "UST_TEILNEHMER_ZIEL";
	public static String FIELD__VERSIONSZAEHLER = "VERSIONSZAEHLER";
	public static String FIELD__ZIEL_IST_MANDANT = "ZIEL_IST_MANDANT";


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
		public String get_ID_HANDELSDEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_HANDELSDEF");
	}

	public String get_ID_HANDELSDEF_cF() throws myException
	{
		return this.get_FormatedValue("ID_HANDELSDEF");	
	}

	public String get_ID_HANDELSDEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_HANDELSDEF");
	}

	public String get_ID_HANDELSDEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_HANDELSDEF",cNotNullValue);
	}

	public String get_ID_HANDELSDEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_HANDELSDEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_HANDELSDEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_HANDELSDEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_HANDELSDEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_HANDELSDEF", calNewValueFormated);
	}
		public Long get_ID_HANDELSDEF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_HANDELSDEF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_HANDELSDEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_HANDELSDEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_HANDELSDEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_HANDELSDEF").getDoubleValue();
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
	public String get_ID_HANDELSDEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_HANDELSDEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_HANDELSDEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_HANDELSDEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_HANDELSDEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_HANDELSDEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_HANDELSDEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_HANDELSDEF").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_QUELLE_GEO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_QUELLE_GEO");
	}

	public String get_ID_LAND_QUELLE_GEO_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND_QUELLE_GEO");	
	}

	public String get_ID_LAND_QUELLE_GEO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND_QUELLE_GEO");
	}

	public String get_ID_LAND_QUELLE_GEO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_QUELLE_GEO",cNotNullValue);
	}

	public String get_ID_LAND_QUELLE_GEO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND_QUELLE_GEO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_QUELLE_GEO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND_QUELLE_GEO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_GEO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_GEO", calNewValueFormated);
	}
		public Long get_ID_LAND_QUELLE_GEO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND_QUELLE_GEO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_QUELLE_GEO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND_QUELLE_GEO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_QUELLE_GEO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND_QUELLE_GEO").getDoubleValue();
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
	public String get_ID_LAND_QUELLE_GEO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_QUELLE_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAND_QUELLE_GEO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_QUELLE_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAND_QUELLE_GEO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_QUELLE_GEO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_QUELLE_GEO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_QUELLE_GEO").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_QUELLE_JUR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_QUELLE_JUR");
	}

	public String get_ID_LAND_QUELLE_JUR_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND_QUELLE_JUR");	
	}

	public String get_ID_LAND_QUELLE_JUR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND_QUELLE_JUR");
	}

	public String get_ID_LAND_QUELLE_JUR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_QUELLE_JUR",cNotNullValue);
	}

	public String get_ID_LAND_QUELLE_JUR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND_QUELLE_JUR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_QUELLE_JUR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND_QUELLE_JUR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_QUELLE_JUR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_QUELLE_JUR", calNewValueFormated);
	}
		public Long get_ID_LAND_QUELLE_JUR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND_QUELLE_JUR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_QUELLE_JUR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND_QUELLE_JUR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_QUELLE_JUR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND_QUELLE_JUR").getDoubleValue();
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
	public String get_ID_LAND_QUELLE_JUR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_QUELLE_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAND_QUELLE_JUR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_QUELLE_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAND_QUELLE_JUR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_QUELLE_JUR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_QUELLE_JUR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_QUELLE_JUR").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_ZIEL_GEO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_ZIEL_GEO");
	}

	public String get_ID_LAND_ZIEL_GEO_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND_ZIEL_GEO");	
	}

	public String get_ID_LAND_ZIEL_GEO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND_ZIEL_GEO");
	}

	public String get_ID_LAND_ZIEL_GEO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_ZIEL_GEO",cNotNullValue);
	}

	public String get_ID_LAND_ZIEL_GEO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND_ZIEL_GEO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_ZIEL_GEO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND_ZIEL_GEO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_GEO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_GEO", calNewValueFormated);
	}
		public Long get_ID_LAND_ZIEL_GEO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND_ZIEL_GEO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_ZIEL_GEO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND_ZIEL_GEO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_ZIEL_GEO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND_ZIEL_GEO").getDoubleValue();
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
	public String get_ID_LAND_ZIEL_GEO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_ZIEL_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAND_ZIEL_GEO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_ZIEL_GEO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAND_ZIEL_GEO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_ZIEL_GEO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_ZIEL_GEO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_ZIEL_GEO").getBigDecimalValue();
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
	
	
	public String get_ID_LAND_ZIEL_JUR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_ZIEL_JUR");
	}

	public String get_ID_LAND_ZIEL_JUR_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAND_ZIEL_JUR");	
	}

	public String get_ID_LAND_ZIEL_JUR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAND_ZIEL_JUR");
	}

	public String get_ID_LAND_ZIEL_JUR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAND_ZIEL_JUR",cNotNullValue);
	}

	public String get_ID_LAND_ZIEL_JUR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAND_ZIEL_JUR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAND_ZIEL_JUR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAND_ZIEL_JUR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAND_ZIEL_JUR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAND_ZIEL_JUR", calNewValueFormated);
	}
		public Long get_ID_LAND_ZIEL_JUR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAND_ZIEL_JUR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAND_ZIEL_JUR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAND_ZIEL_JUR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAND_ZIEL_JUR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAND_ZIEL_JUR").getDoubleValue();
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
	public String get_ID_LAND_ZIEL_JUR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_ZIEL_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAND_ZIEL_JUR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAND_ZIEL_JUR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAND_ZIEL_JUR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_ZIEL_JUR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAND_ZIEL_JUR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAND_ZIEL_JUR").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_NEGATIV_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_NEGATIV_QUELLE");
	}

	public String get_ID_TAX_NEGATIV_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_NEGATIV_QUELLE");	
	}

	public String get_ID_TAX_NEGATIV_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_NEGATIV_QUELLE");
	}

	public String get_ID_TAX_NEGATIV_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_NEGATIV_QUELLE",cNotNullValue);
	}

	public String get_ID_TAX_NEGATIV_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_NEGATIV_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_NEGATIV_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_QUELLE", calNewValueFormated);
	}
		public Long get_ID_TAX_NEGATIV_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_NEGATIV_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_NEGATIV_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_NEGATIV_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_NEGATIV_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_NEGATIV_QUELLE").getDoubleValue();
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
	public String get_ID_TAX_NEGATIV_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_NEGATIV_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_NEGATIV_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_NEGATIV_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_NEGATIV_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_NEGATIV_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_NEGATIV_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_NEGATIV_QUELLE").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_NEGATIV_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_NEGATIV_ZIEL");
	}

	public String get_ID_TAX_NEGATIV_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_NEGATIV_ZIEL");	
	}

	public String get_ID_TAX_NEGATIV_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_NEGATIV_ZIEL");
	}

	public String get_ID_TAX_NEGATIV_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_NEGATIV_ZIEL",cNotNullValue);
	}

	public String get_ID_TAX_NEGATIV_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_NEGATIV_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_NEGATIV_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_NEGATIV_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_NEGATIV_ZIEL", calNewValueFormated);
	}
		public Long get_ID_TAX_NEGATIV_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_NEGATIV_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_NEGATIV_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_NEGATIV_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_NEGATIV_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_NEGATIV_ZIEL").getDoubleValue();
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
	public String get_ID_TAX_NEGATIV_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_NEGATIV_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_NEGATIV_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_NEGATIV_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_NEGATIV_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_NEGATIV_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_NEGATIV_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_NEGATIV_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_QUELLE");
	}

	public String get_ID_TAX_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_QUELLE");	
	}

	public String get_ID_TAX_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_QUELLE");
	}

	public String get_ID_TAX_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_QUELLE",cNotNullValue);
	}

	public String get_ID_TAX_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_QUELLE", calNewValueFormated);
	}
		public Long get_ID_TAX_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_QUELLE").getDoubleValue();
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
	public String get_ID_TAX_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_QUELLE").getBigDecimalValue();
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
	
	
	public String get_ID_TAX_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_ZIEL");
	}

	public String get_ID_TAX_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_TAX_ZIEL");	
	}

	public String get_ID_TAX_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TAX_ZIEL");
	}

	public String get_ID_TAX_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TAX_ZIEL",cNotNullValue);
	}

	public String get_ID_TAX_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TAX_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TAX_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TAX_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TAX_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TAX_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TAX_ZIEL", calNewValueFormated);
	}
		public Long get_ID_TAX_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TAX_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TAX_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TAX_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TAX_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TAX_ZIEL").getDoubleValue();
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
	public String get_ID_TAX_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TAX_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TAX_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TAX_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TAX_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TAX_ZIEL").getBigDecimalValue();
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
	
	
	public String get_INTRASTAT_MELD_IN_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_IN");
	}

	public String get_INTRASTAT_MELD_IN_cF() throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_IN");	
	}

	public String get_INTRASTAT_MELD_IN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTRASTAT_MELD_IN");
	}

	public String get_INTRASTAT_MELD_IN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_IN",cNotNullValue);
	}

	public String get_INTRASTAT_MELD_IN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_IN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTRASTAT_MELD_IN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD_IN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTRASTAT_MELD_IN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_IN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_IN", calNewValueFormated);
	}
		public boolean is_INTRASTAT_MELD_IN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_MELD_IN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_INTRASTAT_MELD_IN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_MELD_IN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_INTRASTAT_MELD_OUT_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_OUT");
	}

	public String get_INTRASTAT_MELD_OUT_cF() throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_OUT");	
	}

	public String get_INTRASTAT_MELD_OUT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTRASTAT_MELD_OUT");
	}

	public String get_INTRASTAT_MELD_OUT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_MELD_OUT",cNotNullValue);
	}

	public String get_INTRASTAT_MELD_OUT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTRASTAT_MELD_OUT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTRASTAT_MELD_OUT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_MELD_OUT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTRASTAT_MELD_OUT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_MELD_OUT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_MELD_OUT", calNewValueFormated);
	}
		public boolean is_INTRASTAT_MELD_OUT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_MELD_OUT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_INTRASTAT_MELD_OUT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_MELD_OUT_cUF_NN("N").equals("N"))
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
		public String get_MELDUNG_FUER_USER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MELDUNG_FUER_USER");
	}

	public String get_MELDUNG_FUER_USER_cF() throws myException
	{
		return this.get_FormatedValue("MELDUNG_FUER_USER");	
	}

	public String get_MELDUNG_FUER_USER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MELDUNG_FUER_USER");
	}

	public String get_MELDUNG_FUER_USER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MELDUNG_FUER_USER",cNotNullValue);
	}

	public String get_MELDUNG_FUER_USER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MELDUNG_FUER_USER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MELDUNG_FUER_USER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_FUER_USER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MELDUNG_FUER_USER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_FUER_USER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MELDUNG_FUER_USER", calNewValueFormated);
	}
		public String get_QUELLE_IST_MANDANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("QUELLE_IST_MANDANT");
	}

	public String get_QUELLE_IST_MANDANT_cF() throws myException
	{
		return this.get_FormatedValue("QUELLE_IST_MANDANT");	
	}

	public String get_QUELLE_IST_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("QUELLE_IST_MANDANT");
	}

	public String get_QUELLE_IST_MANDANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("QUELLE_IST_MANDANT",cNotNullValue);
	}

	public String get_QUELLE_IST_MANDANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("QUELLE_IST_MANDANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("QUELLE_IST_MANDANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_QUELLE_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("QUELLE_IST_MANDANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_QUELLE_IST_MANDANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("QUELLE_IST_MANDANT", calNewValueFormated);
	}
		public boolean is_QUELLE_IST_MANDANT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_QUELLE_IST_MANDANT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_QUELLE_IST_MANDANT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_QUELLE_IST_MANDANT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_SORTE_DIENSTLEIST_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_DIENSTLEIST_QUELLE");
	}

	public String get_SORTE_DIENSTLEIST_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_DIENSTLEIST_QUELLE");	
	}

	public String get_SORTE_DIENSTLEIST_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_DIENSTLEIST_QUELLE");
	}

	public String get_SORTE_DIENSTLEIST_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_DIENSTLEIST_QUELLE",cNotNullValue);
	}

	public String get_SORTE_DIENSTLEIST_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_DIENSTLEIST_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_QUELLE", calNewValueFormated);
	}
		public Long get_SORTE_DIENSTLEIST_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_DIENSTLEIST_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_DIENSTLEIST_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_DIENSTLEIST_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_DIENSTLEIST_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_DIENSTLEIST_QUELLE").getDoubleValue();
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
	public String get_SORTE_DIENSTLEIST_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_DIENSTLEIST_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_DIENSTLEIST_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_DIENSTLEIST_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_DIENSTLEIST_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_DIENSTLEIST_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_DIENSTLEIST_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_DIENSTLEIST_QUELLE").getBigDecimalValue();
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
	
	
	public String get_SORTE_DIENSTLEIST_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_DIENSTLEIST_ZIEL");
	}

	public String get_SORTE_DIENSTLEIST_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_DIENSTLEIST_ZIEL");	
	}

	public String get_SORTE_DIENSTLEIST_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_DIENSTLEIST_ZIEL");
	}

	public String get_SORTE_DIENSTLEIST_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_DIENSTLEIST_ZIEL",cNotNullValue);
	}

	public String get_SORTE_DIENSTLEIST_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_DIENSTLEIST_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_DIENSTLEIST_ZIEL", calNewValueFormated);
	}
		public Long get_SORTE_DIENSTLEIST_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_DIENSTLEIST_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_DIENSTLEIST_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_DIENSTLEIST_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_DIENSTLEIST_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_DIENSTLEIST_ZIEL").getDoubleValue();
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
	public String get_SORTE_DIENSTLEIST_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_DIENSTLEIST_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_DIENSTLEIST_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_DIENSTLEIST_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_DIENSTLEIST_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_DIENSTLEIST_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_DIENSTLEIST_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_DIENSTLEIST_ZIEL").getBigDecimalValue();
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
	
	
	public String get_SORTE_EOW_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_EOW_QUELLE");
	}

	public String get_SORTE_EOW_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_EOW_QUELLE");	
	}

	public String get_SORTE_EOW_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_EOW_QUELLE");
	}

	public String get_SORTE_EOW_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_EOW_QUELLE",cNotNullValue);
	}

	public String get_SORTE_EOW_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_EOW_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_EOW_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_EOW_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_EOW_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_EOW_QUELLE", calNewValueFormated);
	}
		public Long get_SORTE_EOW_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_EOW_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_EOW_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_EOW_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_EOW_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_EOW_QUELLE").getDoubleValue();
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
	public String get_SORTE_EOW_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_EOW_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_EOW_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_EOW_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_EOW_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_EOW_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_EOW_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_EOW_QUELLE").getBigDecimalValue();
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
	
	
	public String get_SORTE_EOW_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_EOW_ZIEL");
	}

	public String get_SORTE_EOW_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_EOW_ZIEL");	
	}

	public String get_SORTE_EOW_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_EOW_ZIEL");
	}

	public String get_SORTE_EOW_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_EOW_ZIEL",cNotNullValue);
	}

	public String get_SORTE_EOW_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_EOW_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_EOW_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_EOW_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_EOW_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_EOW_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_EOW_ZIEL", calNewValueFormated);
	}
		public Long get_SORTE_EOW_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_EOW_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_EOW_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_EOW_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_EOW_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_EOW_ZIEL").getDoubleValue();
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
	public String get_SORTE_EOW_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_EOW_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_EOW_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_EOW_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_EOW_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_EOW_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_EOW_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_EOW_ZIEL").getBigDecimalValue();
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
	
	
	public String get_SORTE_PRODUKT_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_PRODUKT_QUELLE");
	}

	public String get_SORTE_PRODUKT_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_PRODUKT_QUELLE");	
	}

	public String get_SORTE_PRODUKT_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_PRODUKT_QUELLE");
	}

	public String get_SORTE_PRODUKT_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_PRODUKT_QUELLE",cNotNullValue);
	}

	public String get_SORTE_PRODUKT_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_PRODUKT_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_PRODUKT_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_PRODUKT_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_QUELLE", calNewValueFormated);
	}
		public Long get_SORTE_PRODUKT_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_PRODUKT_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_PRODUKT_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_PRODUKT_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_PRODUKT_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_PRODUKT_QUELLE").getDoubleValue();
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
	public String get_SORTE_PRODUKT_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_PRODUKT_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_PRODUKT_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_PRODUKT_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_PRODUKT_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_PRODUKT_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_PRODUKT_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_PRODUKT_QUELLE").getBigDecimalValue();
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
	
	
	public String get_SORTE_PRODUKT_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_PRODUKT_ZIEL");
	}

	public String get_SORTE_PRODUKT_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_PRODUKT_ZIEL");	
	}

	public String get_SORTE_PRODUKT_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_PRODUKT_ZIEL");
	}

	public String get_SORTE_PRODUKT_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_PRODUKT_ZIEL",cNotNullValue);
	}

	public String get_SORTE_PRODUKT_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_PRODUKT_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_PRODUKT_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_PRODUKT_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_PRODUKT_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_PRODUKT_ZIEL", calNewValueFormated);
	}
		public Long get_SORTE_PRODUKT_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_PRODUKT_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_PRODUKT_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_PRODUKT_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_PRODUKT_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_PRODUKT_ZIEL").getDoubleValue();
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
	public String get_SORTE_PRODUKT_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_PRODUKT_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_PRODUKT_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_PRODUKT_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_PRODUKT_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_PRODUKT_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_PRODUKT_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_PRODUKT_ZIEL").getBigDecimalValue();
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
	
	
	public String get_SORTE_RC_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_RC_QUELLE");
	}

	public String get_SORTE_RC_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_RC_QUELLE");	
	}

	public String get_SORTE_RC_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_RC_QUELLE");
	}

	public String get_SORTE_RC_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_RC_QUELLE",cNotNullValue);
	}

	public String get_SORTE_RC_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_RC_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_RC_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_RC_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_RC_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_RC_QUELLE", calNewValueFormated);
	}
		public Long get_SORTE_RC_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_RC_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_RC_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_RC_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_RC_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_RC_QUELLE").getDoubleValue();
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
	public String get_SORTE_RC_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_RC_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_RC_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_RC_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_RC_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_RC_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_RC_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_RC_QUELLE").getBigDecimalValue();
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
	
	
	public String get_SORTE_RC_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTE_RC_ZIEL");
	}

	public String get_SORTE_RC_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("SORTE_RC_ZIEL");	
	}

	public String get_SORTE_RC_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTE_RC_ZIEL");
	}

	public String get_SORTE_RC_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTE_RC_ZIEL",cNotNullValue);
	}

	public String get_SORTE_RC_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTE_RC_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTE_RC_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTE_RC_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTE_RC_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTE_RC_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTE_RC_ZIEL", calNewValueFormated);
	}
		public Long get_SORTE_RC_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTE_RC_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTE_RC_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTE_RC_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTE_RC_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTE_RC_ZIEL").getDoubleValue();
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
	public String get_SORTE_RC_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_RC_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTE_RC_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTE_RC_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTE_RC_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_RC_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTE_RC_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTE_RC_ZIEL").getBigDecimalValue();
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
	
	
	public String get_TP_VERANTWORTUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("TP_VERANTWORTUNG");
	}

	public String get_TP_VERANTWORTUNG_cF() throws myException
	{
		return this.get_FormatedValue("TP_VERANTWORTUNG");	
	}

	public String get_TP_VERANTWORTUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TP_VERANTWORTUNG");
	}

	public String get_TP_VERANTWORTUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TP_VERANTWORTUNG",cNotNullValue);
	}

	public String get_TP_VERANTWORTUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TP_VERANTWORTUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TP_VERANTWORTUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TP_VERANTWORTUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TP_VERANTWORTUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TP_VERANTWORTUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TP_VERANTWORTUNG", calNewValueFormated);
	}
		public String get_TRANSIT_EK_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_EK");
	}

	public String get_TRANSIT_EK_cF() throws myException
	{
		return this.get_FormatedValue("TRANSIT_EK");	
	}

	public String get_TRANSIT_EK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSIT_EK");
	}

	public String get_TRANSIT_EK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_EK",cNotNullValue);
	}

	public String get_TRANSIT_EK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSIT_EK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSIT_EK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSIT_EK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSIT_EK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSIT_EK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_EK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_EK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_EK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_EK", calNewValueFormated);
	}
		public boolean is_TRANSIT_EK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_EK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSIT_EK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_EK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_TRANSIT_VK_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_VK");
	}

	public String get_TRANSIT_VK_cF() throws myException
	{
		return this.get_FormatedValue("TRANSIT_VK");	
	}

	public String get_TRANSIT_VK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSIT_VK");
	}

	public String get_TRANSIT_VK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSIT_VK",cNotNullValue);
	}

	public String get_TRANSIT_VK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSIT_VK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSIT_VK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSIT_VK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSIT_VK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSIT_VK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_VK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_VK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSIT_VK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSIT_VK", calNewValueFormated);
	}
		public boolean is_TRANSIT_VK_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_VK_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TRANSIT_VK_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TRANSIT_VK_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_TYP_MELDUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYP_MELDUNG");
	}

	public String get_TYP_MELDUNG_cF() throws myException
	{
		return this.get_FormatedValue("TYP_MELDUNG");	
	}

	public String get_TYP_MELDUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYP_MELDUNG");
	}

	public String get_TYP_MELDUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYP_MELDUNG",cNotNullValue);
	}

	public String get_TYP_MELDUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYP_MELDUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYP_MELDUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYP_MELDUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYP_MELDUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYP_MELDUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_MELDUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_MELDUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_MELDUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_MELDUNG", calNewValueFormated);
	}
		public String get_UST_TEILNEHMER_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("UST_TEILNEHMER_QUELLE");
	}

	public String get_UST_TEILNEHMER_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("UST_TEILNEHMER_QUELLE");	
	}

	public String get_UST_TEILNEHMER_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UST_TEILNEHMER_QUELLE");
	}

	public String get_UST_TEILNEHMER_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UST_TEILNEHMER_QUELLE",cNotNullValue);
	}

	public String get_UST_TEILNEHMER_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UST_TEILNEHMER_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UST_TEILNEHMER_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UST_TEILNEHMER_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_QUELLE", calNewValueFormated);
	}
		public Long get_UST_TEILNEHMER_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("UST_TEILNEHMER_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_UST_TEILNEHMER_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("UST_TEILNEHMER_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_UST_TEILNEHMER_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("UST_TEILNEHMER_QUELLE").getDoubleValue();
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
	public String get_UST_TEILNEHMER_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_UST_TEILNEHMER_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_UST_TEILNEHMER_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_UST_TEILNEHMER_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_UST_TEILNEHMER_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("UST_TEILNEHMER_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_UST_TEILNEHMER_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("UST_TEILNEHMER_QUELLE").getBigDecimalValue();
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
	
	
	public String get_UST_TEILNEHMER_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("UST_TEILNEHMER_ZIEL");
	}

	public String get_UST_TEILNEHMER_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("UST_TEILNEHMER_ZIEL");	
	}

	public String get_UST_TEILNEHMER_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UST_TEILNEHMER_ZIEL");
	}

	public String get_UST_TEILNEHMER_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UST_TEILNEHMER_ZIEL",cNotNullValue);
	}

	public String get_UST_TEILNEHMER_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UST_TEILNEHMER_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UST_TEILNEHMER_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UST_TEILNEHMER_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_TEILNEHMER_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_TEILNEHMER_ZIEL", calNewValueFormated);
	}
		public Long get_UST_TEILNEHMER_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("UST_TEILNEHMER_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_UST_TEILNEHMER_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("UST_TEILNEHMER_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_UST_TEILNEHMER_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("UST_TEILNEHMER_ZIEL").getDoubleValue();
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
	public String get_UST_TEILNEHMER_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_UST_TEILNEHMER_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_UST_TEILNEHMER_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_UST_TEILNEHMER_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_UST_TEILNEHMER_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("UST_TEILNEHMER_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_UST_TEILNEHMER_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("UST_TEILNEHMER_ZIEL").getBigDecimalValue();
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
	
	
	public String get_VERSIONSZAEHLER_cUF() throws myException
	{
		return this.get_UnFormatedValue("VERSIONSZAEHLER");
	}

	public String get_VERSIONSZAEHLER_cF() throws myException
	{
		return this.get_FormatedValue("VERSIONSZAEHLER");	
	}

	public String get_VERSIONSZAEHLER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERSIONSZAEHLER");
	}

	public String get_VERSIONSZAEHLER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VERSIONSZAEHLER",cNotNullValue);
	}

	public String get_VERSIONSZAEHLER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VERSIONSZAEHLER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VERSIONSZAEHLER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VERSIONSZAEHLER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VERSIONSZAEHLER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VERSIONSZAEHLER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VERSIONSZAEHLER", calNewValueFormated);
	}
		public Long get_VERSIONSZAEHLER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("VERSIONSZAEHLER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_VERSIONSZAEHLER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("VERSIONSZAEHLER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_VERSIONSZAEHLER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("VERSIONSZAEHLER").getDoubleValue();
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
	public String get_VERSIONSZAEHLER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERSIONSZAEHLER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_VERSIONSZAEHLER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERSIONSZAEHLER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_VERSIONSZAEHLER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("VERSIONSZAEHLER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_VERSIONSZAEHLER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("VERSIONSZAEHLER").getBigDecimalValue();
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
	
	
	public String get_ZIEL_IST_MANDANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZIEL_IST_MANDANT");
	}

	public String get_ZIEL_IST_MANDANT_cF() throws myException
	{
		return this.get_FormatedValue("ZIEL_IST_MANDANT");	
	}

	public String get_ZIEL_IST_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZIEL_IST_MANDANT");
	}

	public String get_ZIEL_IST_MANDANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZIEL_IST_MANDANT",cNotNullValue);
	}

	public String get_ZIEL_IST_MANDANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZIEL_IST_MANDANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZIEL_IST_MANDANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZIEL_IST_MANDANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZIEL_IST_MANDANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZIEL_IST_MANDANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZIEL_IST_MANDANT", calNewValueFormated);
	}
		public boolean is_ZIEL_IST_MANDANT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIEL_IST_MANDANT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZIEL_IST_MANDANT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZIEL_IST_MANDANT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_HANDELSDEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND_QUELLE_GEO",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND_QUELLE_JUR",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND_ZIEL_GEO",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND_ZIEL_JUR",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_NEGATIV_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_NEGATIV_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("ID_TAX_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("INTRASTAT_MELD_IN",MyRECORD.DATATYPES.YESNO);
	put("INTRASTAT_MELD_OUT",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MELDUNG_FUER_USER",MyRECORD.DATATYPES.TEXT);
	put("QUELLE_IST_MANDANT",MyRECORD.DATATYPES.YESNO);
	put("SORTE_DIENSTLEIST_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("SORTE_DIENSTLEIST_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("SORTE_EOW_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("SORTE_EOW_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("SORTE_PRODUKT_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("SORTE_PRODUKT_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("SORTE_RC_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("SORTE_RC_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TP_VERANTWORTUNG",MyRECORD.DATATYPES.TEXT);
	put("TRANSIT_EK",MyRECORD.DATATYPES.YESNO);
	put("TRANSIT_VK",MyRECORD.DATATYPES.YESNO);
	put("TYP_MELDUNG",MyRECORD.DATATYPES.TEXT);
	put("UST_TEILNEHMER_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("UST_TEILNEHMER_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("VERSIONSZAEHLER",MyRECORD.DATATYPES.NUMBER);
	put("ZIEL_IST_MANDANT",MyRECORD.DATATYPES.YESNO);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_HANDELSDEF.HM_FIELDS;	
	}

}
