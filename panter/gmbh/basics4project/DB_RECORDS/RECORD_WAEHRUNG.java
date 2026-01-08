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

public class RECORD_WAEHRUNG extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JD_WAEHRUNG";
    public static String IDFIELD   = "ID_WAEHRUNG";
    

	//erzeugen eines RECORDNEW_JD_WAEHRUNG - felds
	private RECORDNEW_WAEHRUNG   recNEW = null;

    private _TAB  tab = _TAB.waehrung;  



	public RECORD_WAEHRUNG() throws myException
	{
		super();
		this.set_TABLE_NAME("JD_WAEHRUNG");
	}


	public RECORD_WAEHRUNG(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_WAEHRUNG");
	}



	public RECORD_WAEHRUNG(RECORD_WAEHRUNG recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_WAEHRUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEHRUNG.TABLENAME);
	}


	//2014-04-03
	public RECORD_WAEHRUNG(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_WAEHRUNG");
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEHRUNG.TABLENAME);
	}




	public RECORD_WAEHRUNG(long lID_Unformated) throws myException
	{
		super("JD_WAEHRUNG","ID_WAEHRUNG",""+lID_Unformated);
		this.set_TABLE_NAME("JD_WAEHRUNG");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEHRUNG.TABLENAME);
	}

	public RECORD_WAEHRUNG(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JD_WAEHRUNG");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_WAEHRUNG", "ID_WAEHRUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_WAEHRUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEHRUNG.TABLENAME);
	}
	
	

	public RECORD_WAEHRUNG(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JD_WAEHRUNG","ID_WAEHRUNG",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JD_WAEHRUNG");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEHRUNG.TABLENAME);

	}


	public RECORD_WAEHRUNG(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_WAEHRUNG");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_WAEHRUNG", "ID_WAEHRUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_WAEHRUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_WAEHRUNG.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_WAEHRUNG();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_WAEHRUNG.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_WAEHRUNG";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_WAEHRUNG_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_WAEHRUNG_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_WAEHRUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_WAEHRUNG", bibE2.cTO(), "ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_WAEHRUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_WAEHRUNG", bibE2.cTO(), "ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JD_WAEHRUNG WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JD_WAEHRUNG WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF();
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
			if (S.isFull(this.get_ID_WAEHRUNG_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_WAEHRUNG", "ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_WAEHRUNG get_RECORDNEW_WAEHRUNG() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_WAEHRUNG();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_WAEHRUNG(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_WAEHRUNG create_Instance() throws myException {
		return new RECORD_WAEHRUNG();
	}
	
	public static RECORD_WAEHRUNG create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_WAEHRUNG(Conn);
    }

	public static RECORD_WAEHRUNG create_Instance(RECORD_WAEHRUNG recordOrig) {
		return new RECORD_WAEHRUNG(recordOrig);
    }

	public static RECORD_WAEHRUNG create_Instance(long lID_Unformated) throws myException {
		return new RECORD_WAEHRUNG(lID_Unformated);
    }

	public static RECORD_WAEHRUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_WAEHRUNG(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_WAEHRUNG create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_WAEHRUNG(lID_Unformated, Conn);
	}

	public static RECORD_WAEHRUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_WAEHRUNG(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_WAEHRUNG create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_WAEHRUNG(recordOrig);	    
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
    public RECORD_WAEHRUNG build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JD_WAEHRUNG WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF());
      }
      //return new RECORD_WAEHRUNG(this.get_cSQL_4_Build());
      RECORD_WAEHRUNG rec = new RECORD_WAEHRUNG();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_WAEHRUNG
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_WAEHRUNG-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_WAEHRUNG get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_WAEHRUNG_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_WAEHRUNG  recNew = new RECORDNEW_WAEHRUNG();
		
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
    public RECORD_WAEHRUNG set_recordNew(RECORDNEW_WAEHRUNG recnew) throws myException {
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
	
	



		private RECLIST_MANDANT DOWN_RECLIST_MANDANT_id_waehrung = null ;




		private RECLIST_WAEHRUNGSQUERY DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_basis = null ;




		private RECLIST_WAEHRUNGSQUERY DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_ziel = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_waehrung = null ;




		private RECLIST_ADRESSE_WAEHRUNG DOWN_RECLIST_ADRESSE_WAEHRUNG_id_waehrung = null ;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_waehrung = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_id_waehrung_fremd = null ;




		private RECLIST_VKOPF_RG DOWN_RECLIST_VKOPF_RG_id_waehrung_fremd = null ;




		private RECLIST_VKOPF_STD DOWN_RECLIST_VKOPF_STD_id_waehrung_fremd = null ;




		private RECLIST_VKOPF_TPA DOWN_RECLIST_VKOPF_TPA_id_waehrung_fremd = null ;




		private RECLIST_VPOS_KON DOWN_RECLIST_VPOS_KON_id_waehrung_fremd = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_waehrung_fremd = null ;




		private RECLIST_VPOS_RG_VL DOWN_RECLIST_VPOS_RG_VL_id_waehrung_fremd = null ;




		private RECLIST_VPOS_STD DOWN_RECLIST_VPOS_STD_id_waehrung_fremd = null ;




		private RECLIST_VPOS_TPA DOWN_RECLIST_VPOS_TPA_id_waehrung_fremd = null ;






	/**
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT get_DOWN_RECORD_LIST_MANDANT_id_waehrung() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_id_waehrung==null)
		{
			this.DOWN_RECLIST_MANDANT_id_waehrung = new RECLIST_MANDANT (
			       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_MANDANT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_id_waehrung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT get_DOWN_RECORD_LIST_MANDANT_id_waehrung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_id_waehrung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MANDANT_id_waehrung = new RECLIST_MANDANT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_id_waehrung;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_BASIS 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WAEHRUNGSQUERY get_DOWN_RECORD_LIST_WAEHRUNGSQUERY_id_waehrung_basis() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_basis==null)
		{
			this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_basis = new RECLIST_WAEHRUNGSQUERY (
			       "SELECT * FROM "+bibE2.cTO()+".JD_WAEHRUNGSQUERY WHERE ID_WAEHRUNG_BASIS="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_WAEHRUNGSQUERY",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_basis;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_BASIS 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WAEHRUNGSQUERY get_DOWN_RECORD_LIST_WAEHRUNGSQUERY_id_waehrung_basis(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_basis==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_WAEHRUNGSQUERY WHERE ID_WAEHRUNG_BASIS="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_basis = new RECLIST_WAEHRUNGSQUERY (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_basis;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_ZIEL 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WAEHRUNGSQUERY get_DOWN_RECORD_LIST_WAEHRUNGSQUERY_id_waehrung_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_ziel==null)
		{
			this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_ziel = new RECLIST_WAEHRUNGSQUERY (
			       "SELECT * FROM "+bibE2.cTO()+".JD_WAEHRUNGSQUERY WHERE ID_WAEHRUNG_ZIEL="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_WAEHRUNGSQUERY",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_ZIEL 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WAEHRUNGSQUERY get_DOWN_RECORD_LIST_WAEHRUNGSQUERY_id_waehrung_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_WAEHRUNGSQUERY WHERE ID_WAEHRUNG_ZIEL="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_ziel = new RECLIST_WAEHRUNGSQUERY (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WAEHRUNGSQUERY_id_waehrung_ziel;
	}


	





	/**
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_waehrung() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_waehrung==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_waehrung = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_waehrung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_waehrung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_waehrung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_waehrung = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_waehrung;
	}


	





	/**
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_WAEHRUNG get_DOWN_RECORD_LIST_ADRESSE_WAEHRUNG_id_waehrung() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_waehrung==null)
		{
			this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_waehrung = new RECLIST_ADRESSE_WAEHRUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_WAEHRUNG WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_ADRESSE_WAEHRUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_waehrung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_WAEHRUNG get_DOWN_RECORD_LIST_ADRESSE_WAEHRUNG_id_waehrung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_waehrung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_WAEHRUNG WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_waehrung = new RECLIST_ADRESSE_WAEHRUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_WAEHRUNG_id_waehrung;
	}


	





	/**
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_waehrung() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_waehrung==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_waehrung = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_waehrung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_waehrung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_waehrung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_WAEHRUNG="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_waehrung = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_waehrung;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_id_waehrung_fremd = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_id_waehrung_fremd = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VKOPF_RG_id_waehrung_fremd = new RECLIST_VKOPF_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VKOPF_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_RG_id_waehrung_fremd = new RECLIST_VKOPF_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VKOPF_STD_id_waehrung_fremd = new RECLIST_VKOPF_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VKOPF_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_STD_id_waehrung_fremd = new RECLIST_VKOPF_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VKOPF_TPA_id_waehrung_fremd = new RECLIST_VKOPF_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VKOPF_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_TPA_id_waehrung_fremd = new RECLIST_VKOPF_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VPOS_KON_id_waehrung_fremd = new RECLIST_VPOS_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VPOS_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_id_waehrung_fremd = new RECLIST_VPOS_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_waehrung_fremd = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_waehrung_fremd = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VPOS_RG_VL_id_waehrung_fremd = new RECLIST_VPOS_RG_VL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VPOS_RG_VL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_VL_id_waehrung_fremd = new RECLIST_VPOS_RG_VL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VPOS_STD_id_waehrung_fremd = new RECLIST_VPOS_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VPOS_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_STD_id_waehrung_fremd = new RECLIST_VPOS_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_waehrung_fremd;
	}


	





	/**
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_waehrung_fremd==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_id_waehrung_fremd = new RECLIST_VPOS_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF()+" ORDER BY ID_VPOS_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_waehrung_fremd;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_WAEHRUNG_FREMD 
	 * Falls keine get_ID_WAEHRUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_waehrung_fremd(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_waehrung_fremd==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_WAEHRUNG_FREMD="+this.get_ID_WAEHRUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_id_waehrung_fremd = new RECLIST_VPOS_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_waehrung_fremd;
	}


	

	public static String FIELD__BEZEICHNUNG = "BEZEICHNUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_WAEHRUNG = "ID_WAEHRUNG";
	public static String FIELD__IST_STANDARD = "IST_STANDARD";
	public static String FIELD__KURZBEZEICHNUNG = "KURZBEZEICHNUNG";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__WAEHRUNGSSYMBOL = "WAEHRUNGSSYMBOL";


	public String get_BEZEICHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEZEICHNUNG");
	}

	public String get_BEZEICHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("BEZEICHNUNG");	
	}

	public String get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEZEICHNUNG");
	}

	public String get_BEZEICHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEZEICHNUNG",cNotNullValue);
	}

	public String get_BEZEICHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEZEICHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEZEICHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEZEICHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEZEICHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEZEICHNUNG", calNewValueFormated);
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
	
	
	public String get_ID_WAEHRUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG");
	}

	public String get_ID_WAEHRUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG");	
	}

	public String get_ID_WAEHRUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAEHRUNG");
	}

	public String get_ID_WAEHRUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG",cNotNullValue);
	}

	public String get_ID_WAEHRUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAEHRUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG", calNewValueFormated);
	}
		public Long get_ID_WAEHRUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAEHRUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAEHRUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAEHRUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG").getDoubleValue();
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
	public String get_ID_WAEHRUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WAEHRUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAEHRUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG").getBigDecimalValue();
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
	
	
	public String get_IST_STANDARD_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_STANDARD");
	}

	public String get_IST_STANDARD_cF() throws myException
	{
		return this.get_FormatedValue("IST_STANDARD");	
	}

	public String get_IST_STANDARD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_STANDARD");
	}

	public String get_IST_STANDARD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_STANDARD",cNotNullValue);
	}

	public String get_IST_STANDARD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_STANDARD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_STANDARD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_STANDARD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_STANDARD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_STANDARD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_STANDARD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_STANDARD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_STANDARD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_STANDARD", calNewValueFormated);
	}
		public boolean is_IST_STANDARD_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_STANDARD_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_STANDARD_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_STANDARD_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_KURZBEZEICHNUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("KURZBEZEICHNUNG");
	}

	public String get_KURZBEZEICHNUNG_cF() throws myException
	{
		return this.get_FormatedValue("KURZBEZEICHNUNG");	
	}

	public String get_KURZBEZEICHNUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KURZBEZEICHNUNG");
	}

	public String get_KURZBEZEICHNUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KURZBEZEICHNUNG",cNotNullValue);
	}

	public String get_KURZBEZEICHNUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KURZBEZEICHNUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KURZBEZEICHNUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KURZBEZEICHNUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KURZBEZEICHNUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KURZBEZEICHNUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KURZBEZEICHNUNG", calNewValueFormated);
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
	
	
	public String get_WAEHRUNGSSYMBOL_cUF() throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNGSSYMBOL");
	}

	public String get_WAEHRUNGSSYMBOL_cF() throws myException
	{
		return this.get_FormatedValue("WAEHRUNGSSYMBOL");	
	}

	public String get_WAEHRUNGSSYMBOL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WAEHRUNGSSYMBOL");
	}

	public String get_WAEHRUNGSSYMBOL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNGSSYMBOL",cNotNullValue);
	}

	public String get_WAEHRUNGSSYMBOL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WAEHRUNGSSYMBOL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSSYMBOL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WAEHRUNGSSYMBOL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNGSSYMBOL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WAEHRUNGSSYMBOL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSSYMBOL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WAEHRUNGSSYMBOL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSSYMBOL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSSYMBOL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSSYMBOL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSSYMBOL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSSYMBOL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSSYMBOL", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("BEZEICHNUNG",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAEHRUNG",MyRECORD.DATATYPES.NUMBER);
	put("IST_STANDARD",MyRECORD.DATATYPES.YESNO);
	put("KURZBEZEICHNUNG",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("WAEHRUNGSSYMBOL",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_WAEHRUNG.HM_FIELDS;	
	}

}
