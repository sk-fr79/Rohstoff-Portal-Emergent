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

public class RECORD_LAND extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JD_LAND";
    public static String IDFIELD   = "ID_LAND";
    

	//erzeugen eines RECORDNEW_JD_LAND - felds
	private RECORDNEW_LAND   recNEW = null;

    private _TAB  tab = _TAB.land;  



	public RECORD_LAND() throws myException
	{
		super();
		this.set_TABLE_NAME("JD_LAND");
	}


	public RECORD_LAND(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_LAND");
	}



	public RECORD_LAND(RECORD_LAND recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_LAND");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAND.TABLENAME);
	}


	//2014-04-03
	public RECORD_LAND(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_LAND");
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAND.TABLENAME);
	}




	public RECORD_LAND(long lID_Unformated) throws myException
	{
		super("JD_LAND","ID_LAND",""+lID_Unformated);
		this.set_TABLE_NAME("JD_LAND");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAND.TABLENAME);
	}

	public RECORD_LAND(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JD_LAND");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_LAND", "ID_LAND="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_LAND", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAND.TABLENAME);
	}
	
	

	public RECORD_LAND(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JD_LAND","ID_LAND",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JD_LAND");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAND.TABLENAME);

	}


	public RECORD_LAND(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_LAND");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_LAND", "ID_LAND="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_LAND", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAND.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_LAND();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_LAND.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_LAND";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_LAND_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_LAND_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAND was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_LAND", bibE2.cTO(), "ID_LAND="+this.get_ID_LAND_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAND was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_LAND", bibE2.cTO(), "ID_LAND="+this.get_ID_LAND_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JD_LAND WHERE ID_LAND="+this.get_ID_LAND_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JD_LAND WHERE ID_LAND="+this.get_ID_LAND_cUF();
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
			if (S.isFull(this.get_ID_LAND_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_LAND", "ID_LAND="+this.get_ID_LAND_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_LAND get_RECORDNEW_LAND() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_LAND();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_LAND(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_LAND create_Instance() throws myException {
		return new RECORD_LAND();
	}
	
	public static RECORD_LAND create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_LAND(Conn);
    }

	public static RECORD_LAND create_Instance(RECORD_LAND recordOrig) {
		return new RECORD_LAND(recordOrig);
    }

	public static RECORD_LAND create_Instance(long lID_Unformated) throws myException {
		return new RECORD_LAND(lID_Unformated);
    }

	public static RECORD_LAND create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_LAND(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_LAND create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_LAND(lID_Unformated, Conn);
	}

	public static RECORD_LAND create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_LAND(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_LAND create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_LAND(recordOrig);	    
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
    public RECORD_LAND build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JD_LAND WHERE ID_LAND="+this.get_ID_LAND_cUF());
      }
      //return new RECORD_LAND(this.get_cSQL_4_Build());
      RECORD_LAND rec = new RECORD_LAND();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_LAND
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_LAND-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_LAND get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_LAND_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_LAND  recNew = new RECORDNEW_LAND();
		
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
    public RECORD_LAND set_recordNew(RECORDNEW_LAND recnew) throws myException {
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
	
	



		private RECLIST_MANDANT DOWN_RECLIST_MANDANT_id_land = null ;




		private RECLIST_MANDANT_STEUERVERMERK DOWN_RECLIST_MANDANT_STEUERVERMERK_id_land = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_land = null ;




		private RECLIST_ADRESSE_AQUISE DOWN_RECLIST_ADRESSE_AQUISE_id_land = null ;




		private RECLIST_ADRESSE_UST_ID DOWN_RECLIST_ADRESSE_UST_ID_id_land = null ;




		private RECLIST_BG_STATION DOWN_RECLIST_BG_STATION_id_land = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_land_transit1 = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_land_transit2 = null ;




		private RECLIST_BG_VEKTOR DOWN_RECLIST_BG_VEKTOR_id_land_transit3 = null ;




		private RECLIST_BORDERCROSSING DOWN_RECLIST_BORDERCROSSING_id_land_quelle = null ;




		private RECLIST_BORDERCROSSING DOWN_RECLIST_BORDERCROSSING_id_land_ziel = null ;




		private RECLIST_FIBU_KONTENREGEL_LAND DOWN_RECLIST_FIBU_KONTENREGEL_LAND_id_land = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_land_quelle_geo = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_land_quelle_jur = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_land_ziel_geo = null ;




		private RECLIST_HANDELSDEF DOWN_RECLIST_HANDELSDEF_id_land_ziel_jur = null ;




		private RECLIST_INTRASTAT_LAND DOWN_RECLIST_INTRASTAT_LAND_id_land = null ;




		private RECLIST_LAND_RC_SORTEN DOWN_RECLIST_LAND_RC_SORTEN_id_land = null ;




		private RECLIST_MWSTSCHLUESSEL DOWN_RECLIST_MWSTSCHLUESSEL_id_land = null ;




		private RECLIST_TAX DOWN_RECLIST_TAX_id_land = null ;






	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT get_DOWN_RECORD_LIST_MANDANT_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_id_land==null)
		{
			this.DOWN_RECLIST_MANDANT_id_land = new RECLIST_MANDANT (
			       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_MANDANT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT get_DOWN_RECORD_LIST_MANDANT_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MANDANT_id_land = new RECLIST_MANDANT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_STEUERVERMERK get_DOWN_RECORD_LIST_MANDANT_STEUERVERMERK_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_land==null)
		{
			this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_land = new RECLIST_MANDANT_STEUERVERMERK (
			       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_STEUERVERMERK WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_MANDANT_STEUERVERMERK",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_STEUERVERMERK get_DOWN_RECORD_LIST_MANDANT_STEUERVERMERK_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_STEUERVERMERK WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_land = new RECLIST_MANDANT_STEUERVERMERK (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_STEUERVERMERK_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_land==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_land = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_land = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_AQUISE get_DOWN_RECORD_LIST_ADRESSE_AQUISE_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_AQUISE_id_land==null)
		{
			this.DOWN_RECLIST_ADRESSE_AQUISE_id_land = new RECLIST_ADRESSE_AQUISE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_AQUISE WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_ADRESSE_AQUISE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_AQUISE_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_AQUISE get_DOWN_RECORD_LIST_ADRESSE_AQUISE_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_AQUISE_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_AQUISE WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_AQUISE_id_land = new RECLIST_ADRESSE_AQUISE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_AQUISE_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_UST_ID get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_UST_ID_id_land==null)
		{
			this.DOWN_RECLIST_ADRESSE_UST_ID_id_land = new RECLIST_ADRESSE_UST_ID (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_UST_ID WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_ADRESSE_UST_ID",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_UST_ID_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_UST_ID get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_UST_ID_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_UST_ID WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_UST_ID_id_land = new RECLIST_ADRESSE_UST_ID (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_UST_ID_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_land==null)
		{
			this.DOWN_RECLIST_BG_STATION_id_land = new RECLIST_BG_STATION (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_BG_STATION",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_STATION get_DOWN_RECORD_LIST_BG_STATION_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_STATION_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_STATION_id_land = new RECLIST_BG_STATION (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_STATION_id_land;
	}


	





	/**
	 * References the Field ID_LAND_TRANSIT1 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_land_transit1() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_land_transit1==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_land_transit1 = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_LAND_TRANSIT1="+this.get_ID_LAND_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_land_transit1;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_TRANSIT1 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_land_transit1(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_land_transit1==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_LAND_TRANSIT1="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_land_transit1 = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_land_transit1;
	}


	





	/**
	 * References the Field ID_LAND_TRANSIT2 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_land_transit2() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_land_transit2==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_land_transit2 = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_LAND_TRANSIT2="+this.get_ID_LAND_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_land_transit2;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_TRANSIT2 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_land_transit2(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_land_transit2==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_LAND_TRANSIT2="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_land_transit2 = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_land_transit2;
	}


	





	/**
	 * References the Field ID_LAND_TRANSIT3 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_land_transit3() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_land_transit3==null)
		{
			this.DOWN_RECLIST_BG_VEKTOR_id_land_transit3 = new RECLIST_BG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_LAND_TRANSIT3="+this.get_ID_LAND_cUF()+" ORDER BY ID_BG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_land_transit3;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_TRANSIT3 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_VEKTOR get_DOWN_RECORD_LIST_BG_VEKTOR_id_land_transit3(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_VEKTOR_id_land_transit3==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_VEKTOR WHERE ID_LAND_TRANSIT3="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_VEKTOR_id_land_transit3 = new RECLIST_BG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_VEKTOR_id_land_transit3;
	}


	





	/**
	 * References the Field ID_LAND_QUELLE 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING get_DOWN_RECORD_LIST_BORDERCROSSING_id_land_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_id_land_quelle==null)
		{
			this.DOWN_RECLIST_BORDERCROSSING_id_land_quelle = new RECLIST_BORDERCROSSING (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING WHERE ID_LAND_QUELLE="+this.get_ID_LAND_cUF()+" ORDER BY ID_BORDERCROSSING",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_id_land_quelle;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_QUELLE 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING get_DOWN_RECORD_LIST_BORDERCROSSING_id_land_quelle(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_id_land_quelle==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING WHERE ID_LAND_QUELLE="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BORDERCROSSING_id_land_quelle = new RECLIST_BORDERCROSSING (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_id_land_quelle;
	}


	





	/**
	 * References the Field ID_LAND_ZIEL 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING get_DOWN_RECORD_LIST_BORDERCROSSING_id_land_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_id_land_ziel==null)
		{
			this.DOWN_RECLIST_BORDERCROSSING_id_land_ziel = new RECLIST_BORDERCROSSING (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING WHERE ID_LAND_ZIEL="+this.get_ID_LAND_cUF()+" ORDER BY ID_BORDERCROSSING",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_id_land_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_ZIEL 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING get_DOWN_RECORD_LIST_BORDERCROSSING_id_land_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_id_land_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING WHERE ID_LAND_ZIEL="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BORDERCROSSING_id_land_ziel = new RECLIST_BORDERCROSSING (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_id_land_ziel;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL_LAND get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_LAND_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_LAND_id_land==null)
		{
			this.DOWN_RECLIST_FIBU_KONTENREGEL_LAND_id_land = new RECLIST_FIBU_KONTENREGEL_LAND (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_LAND WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_FIBU_KONTENREGEL_LAND",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_LAND_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL_LAND get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_LAND_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_LAND_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_LAND WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_KONTENREGEL_LAND_id_land = new RECLIST_FIBU_KONTENREGEL_LAND (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_LAND_id_land;
	}


	





	/**
	 * References the Field ID_LAND_QUELLE_GEO 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_quelle_geo() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_geo==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_geo = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_QUELLE_GEO="+this.get_ID_LAND_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_geo;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_QUELLE_GEO 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_quelle_geo(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_geo==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_QUELLE_GEO="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_geo = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_geo;
	}


	





	/**
	 * References the Field ID_LAND_QUELLE_JUR 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_quelle_jur() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_jur==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_jur = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_QUELLE_JUR="+this.get_ID_LAND_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_jur;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_QUELLE_JUR 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_quelle_jur(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_jur==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_QUELLE_JUR="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_jur = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_quelle_jur;
	}


	





	/**
	 * References the Field ID_LAND_ZIEL_GEO 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_ziel_geo() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_geo==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_geo = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_ZIEL_GEO="+this.get_ID_LAND_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_geo;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_ZIEL_GEO 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_ziel_geo(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_geo==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_ZIEL_GEO="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_geo = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_geo;
	}


	





	/**
	 * References the Field ID_LAND_ZIEL_JUR 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_ziel_jur() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_jur==null)
		{
			this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_jur = new RECLIST_HANDELSDEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_ZIEL_JUR="+this.get_ID_LAND_cUF()+" ORDER BY ID_HANDELSDEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_jur;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND_ZIEL_JUR 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_HANDELSDEF get_DOWN_RECORD_LIST_HANDELSDEF_id_land_ziel_jur(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_jur==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE ID_LAND_ZIEL_JUR="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_jur = new RECLIST_HANDELSDEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_HANDELSDEF_id_land_ziel_jur;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INTRASTAT_LAND get_DOWN_RECORD_LIST_INTRASTAT_LAND_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INTRASTAT_LAND_id_land==null)
		{
			this.DOWN_RECLIST_INTRASTAT_LAND_id_land = new RECLIST_INTRASTAT_LAND (
			       "SELECT * FROM "+bibE2.cTO()+".JT_INTRASTAT_LAND WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_INTRASTAT_LAND",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_INTRASTAT_LAND_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_INTRASTAT_LAND get_DOWN_RECORD_LIST_INTRASTAT_LAND_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_INTRASTAT_LAND_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_INTRASTAT_LAND WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_INTRASTAT_LAND_id_land = new RECLIST_INTRASTAT_LAND (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_INTRASTAT_LAND_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAND_RC_SORTEN get_DOWN_RECORD_LIST_LAND_RC_SORTEN_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAND_RC_SORTEN_id_land==null)
		{
			this.DOWN_RECLIST_LAND_RC_SORTEN_id_land = new RECLIST_LAND_RC_SORTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAND_RC_SORTEN WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_LAND_RC_SORTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAND_RC_SORTEN_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAND_RC_SORTEN get_DOWN_RECORD_LIST_LAND_RC_SORTEN_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAND_RC_SORTEN_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAND_RC_SORTEN WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAND_RC_SORTEN_id_land = new RECLIST_LAND_RC_SORTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAND_RC_SORTEN_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MWSTSCHLUESSEL get_DOWN_RECORD_LIST_MWSTSCHLUESSEL_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MWSTSCHLUESSEL_id_land==null)
		{
			this.DOWN_RECLIST_MWSTSCHLUESSEL_id_land = new RECLIST_MWSTSCHLUESSEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MWSTSCHLUESSEL WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_MWSTSCHLUESSEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MWSTSCHLUESSEL_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MWSTSCHLUESSEL get_DOWN_RECORD_LIST_MWSTSCHLUESSEL_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MWSTSCHLUESSEL_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MWSTSCHLUESSEL WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MWSTSCHLUESSEL_id_land = new RECLIST_MWSTSCHLUESSEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MWSTSCHLUESSEL_id_land;
	}


	





	/**
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TAX get_DOWN_RECORD_LIST_TAX_id_land() throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TAX_id_land==null)
		{
			this.DOWN_RECLIST_TAX_id_land = new RECLIST_TAX (
			       "SELECT * FROM "+bibE2.cTO()+".JT_TAX WHERE ID_LAND="+this.get_ID_LAND_cUF()+" ORDER BY ID_TAX",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_TAX_id_land;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAND 
	 * Falls keine get_ID_LAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_TAX get_DOWN_RECORD_LIST_TAX_id_land(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAND_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_TAX_id_land==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_TAX WHERE ID_LAND="+this.get_ID_LAND_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_TAX_id_land = new RECLIST_TAX (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_TAX_id_land;
	}


	

	public static String FIELD__ANZEIGEREIHENFOLGE = "ANZEIGEREIHENFOLGE";
	public static String FIELD__BESCHREIBUNG = "BESCHREIBUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GENERELLE_EINFUHR_NOTI = "GENERELLE_EINFUHR_NOTI";
	public static String FIELD__HAT_POSTCODE = "HAT_POSTCODE";
	public static String FIELD__ID_LAND = "ID_LAND";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__INTRASTAT_JN = "INTRASTAT_JN";
	public static String FIELD__ISO_COUNTRY_CODE = "ISO_COUNTRY_CODE";
	public static String FIELD__ISTSTANDARD = "ISTSTANDARD";
	public static String FIELD__LAENDERCODE = "LAENDERCODE";
	public static String FIELD__LAENDERNAME = "LAENDERNAME";
	public static String FIELD__LAENDERVORWAHL = "LAENDERVORWAHL";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__POST_CODE = "POST_CODE";
	public static String FIELD__SONDERFALL_GELANGENSBESTAET = "SONDERFALL_GELANGENSBESTAET";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__UST_PRAEFIX = "UST_PRAEFIX";


	public String get_ANZEIGEREIHENFOLGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZEIGEREIHENFOLGE");
	}

	public String get_ANZEIGEREIHENFOLGE_cF() throws myException
	{
		return this.get_FormatedValue("ANZEIGEREIHENFOLGE");	
	}

	public String get_ANZEIGEREIHENFOLGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZEIGEREIHENFOLGE");
	}

	public String get_ANZEIGEREIHENFOLGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZEIGEREIHENFOLGE",cNotNullValue);
	}

	public String get_ANZEIGEREIHENFOLGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZEIGEREIHENFOLGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZEIGEREIHENFOLGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZEIGEREIHENFOLGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZEIGEREIHENFOLGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZEIGEREIHENFOLGE", calNewValueFormated);
	}
		public String get_BESCHREIBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG");
	}

	public String get_BESCHREIBUNG_cF() throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG");	
	}

	public String get_BESCHREIBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESCHREIBUNG");
	}

	public String get_BESCHREIBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESCHREIBUNG",cNotNullValue);
	}

	public String get_BESCHREIBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESCHREIBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESCHREIBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESCHREIBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESCHREIBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESCHREIBUNG", calNewValueFormated);
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
		public String get_GENERELLE_EINFUHR_NOTI_cUF() throws myException
	{
		return this.get_UnFormatedValue("GENERELLE_EINFUHR_NOTI");
	}

	public String get_GENERELLE_EINFUHR_NOTI_cF() throws myException
	{
		return this.get_FormatedValue("GENERELLE_EINFUHR_NOTI");	
	}

	public String get_GENERELLE_EINFUHR_NOTI_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GENERELLE_EINFUHR_NOTI");
	}

	public String get_GENERELLE_EINFUHR_NOTI_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GENERELLE_EINFUHR_NOTI",cNotNullValue);
	}

	public String get_GENERELLE_EINFUHR_NOTI_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GENERELLE_EINFUHR_NOTI",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GENERELLE_EINFUHR_NOTI(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENERELLE_EINFUHR_NOTI_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GENERELLE_EINFUHR_NOTI", calNewValueFormated);
	}
		public boolean is_GENERELLE_EINFUHR_NOTI_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GENERELLE_EINFUHR_NOTI_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GENERELLE_EINFUHR_NOTI_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GENERELLE_EINFUHR_NOTI_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_HAT_POSTCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("HAT_POSTCODE");
	}

	public String get_HAT_POSTCODE_cF() throws myException
	{
		return this.get_FormatedValue("HAT_POSTCODE");	
	}

	public String get_HAT_POSTCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HAT_POSTCODE");
	}

	public String get_HAT_POSTCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HAT_POSTCODE",cNotNullValue);
	}

	public String get_HAT_POSTCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HAT_POSTCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HAT_POSTCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HAT_POSTCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HAT_POSTCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HAT_POSTCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_POSTCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_POSTCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAT_POSTCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAT_POSTCODE", calNewValueFormated);
	}
		public boolean is_HAT_POSTCODE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_HAT_POSTCODE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_HAT_POSTCODE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_HAT_POSTCODE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
	
	
	public String get_INTRASTAT_JN_cUF() throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_JN");
	}

	public String get_INTRASTAT_JN_cF() throws myException
	{
		return this.get_FormatedValue("INTRASTAT_JN");	
	}

	public String get_INTRASTAT_JN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("INTRASTAT_JN");
	}

	public String get_INTRASTAT_JN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("INTRASTAT_JN",cNotNullValue);
	}

	public String get_INTRASTAT_JN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("INTRASTAT_JN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("INTRASTAT_JN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_INTRASTAT_JN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("INTRASTAT_JN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("INTRASTAT_JN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_JN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_JN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_INTRASTAT_JN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("INTRASTAT_JN", calNewValueFormated);
	}
		public boolean is_INTRASTAT_JN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_JN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_INTRASTAT_JN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_INTRASTAT_JN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ISO_COUNTRY_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ISO_COUNTRY_CODE");
	}

	public String get_ISO_COUNTRY_CODE_cF() throws myException
	{
		return this.get_FormatedValue("ISO_COUNTRY_CODE");	
	}

	public String get_ISO_COUNTRY_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ISO_COUNTRY_CODE");
	}

	public String get_ISO_COUNTRY_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ISO_COUNTRY_CODE",cNotNullValue);
	}

	public String get_ISO_COUNTRY_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ISO_COUNTRY_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ISO_COUNTRY_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ISO_COUNTRY_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ISO_COUNTRY_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISO_COUNTRY_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ISO_COUNTRY_CODE", calNewValueFormated);
	}
		public String get_ISTSTANDARD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ISTSTANDARD");
	}

	public String get_ISTSTANDARD_cF() throws myException
	{
		return this.get_FormatedValue("ISTSTANDARD");	
	}

	public String get_ISTSTANDARD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ISTSTANDARD");
	}

	public String get_ISTSTANDARD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ISTSTANDARD",cNotNullValue);
	}

	public String get_ISTSTANDARD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ISTSTANDARD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ISTSTANDARD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ISTSTANDARD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ISTSTANDARD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ISTSTANDARD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ISTSTANDARD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ISTSTANDARD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ISTSTANDARD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ISTSTANDARD", calNewValueFormated);
	}
		public boolean is_ISTSTANDARD_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ISTSTANDARD_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ISTSTANDARD_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ISTSTANDARD_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_LAENDERNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERNAME");
	}

	public String get_LAENDERNAME_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERNAME");	
	}

	public String get_LAENDERNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERNAME");
	}

	public String get_LAENDERNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERNAME",cNotNullValue);
	}

	public String get_LAENDERNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERNAME", calNewValueFormated);
	}
		public String get_LAENDERVORWAHL_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERVORWAHL");
	}

	public String get_LAENDERVORWAHL_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERVORWAHL");	
	}

	public String get_LAENDERVORWAHL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERVORWAHL");
	}

	public String get_LAENDERVORWAHL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERVORWAHL",cNotNullValue);
	}

	public String get_LAENDERVORWAHL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERVORWAHL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERVORWAHL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERVORWAHL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERVORWAHL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERVORWAHL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERVORWAHL", calNewValueFormated);
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
		public String get_POST_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("POST_CODE");
	}

	public String get_POST_CODE_cF() throws myException
	{
		return this.get_FormatedValue("POST_CODE");	
	}

	public String get_POST_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POST_CODE");
	}

	public String get_POST_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POST_CODE",cNotNullValue);
	}

	public String get_POST_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POST_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POST_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POST_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POST_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POST_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POST_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POST_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POST_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POST_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POST_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POST_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POST_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POST_CODE", calNewValueFormated);
	}
		public String get_SONDERFALL_GELANGENSBESTAET_cUF() throws myException
	{
		return this.get_UnFormatedValue("SONDERFALL_GELANGENSBESTAET");
	}

	public String get_SONDERFALL_GELANGENSBESTAET_cF() throws myException
	{
		return this.get_FormatedValue("SONDERFALL_GELANGENSBESTAET");	
	}

	public String get_SONDERFALL_GELANGENSBESTAET_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SONDERFALL_GELANGENSBESTAET");
	}

	public String get_SONDERFALL_GELANGENSBESTAET_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SONDERFALL_GELANGENSBESTAET",cNotNullValue);
	}

	public String get_SONDERFALL_GELANGENSBESTAET_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SONDERFALL_GELANGENSBESTAET",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SONDERFALL_GELANGENSBESTAET(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFALL_GELANGENSBESTAET_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERFALL_GELANGENSBESTAET", calNewValueFormated);
	}
		public boolean is_SONDERFALL_GELANGENSBESTAET_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SONDERFALL_GELANGENSBESTAET_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SONDERFALL_GELANGENSBESTAET_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SONDERFALL_GELANGENSBESTAET_cUF_NN("N").equals("N"))
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
	
	
	public String get_UST_PRAEFIX_cUF() throws myException
	{
		return this.get_UnFormatedValue("UST_PRAEFIX");
	}

	public String get_UST_PRAEFIX_cF() throws myException
	{
		return this.get_FormatedValue("UST_PRAEFIX");	
	}

	public String get_UST_PRAEFIX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UST_PRAEFIX");
	}

	public String get_UST_PRAEFIX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UST_PRAEFIX",cNotNullValue);
	}

	public String get_UST_PRAEFIX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UST_PRAEFIX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UST_PRAEFIX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UST_PRAEFIX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UST_PRAEFIX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UST_PRAEFIX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_PRAEFIX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_PRAEFIX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UST_PRAEFIX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UST_PRAEFIX", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ANZEIGEREIHENFOLGE",MyRECORD.DATATYPES.TEXT);
	put("BESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GENERELLE_EINFUHR_NOTI",MyRECORD.DATATYPES.YESNO);
	put("HAT_POSTCODE",MyRECORD.DATATYPES.YESNO);
	put("ID_LAND",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("INTRASTAT_JN",MyRECORD.DATATYPES.YESNO);
	put("ISO_COUNTRY_CODE",MyRECORD.DATATYPES.TEXT);
	put("ISTSTANDARD",MyRECORD.DATATYPES.YESNO);
	put("LAENDERCODE",MyRECORD.DATATYPES.TEXT);
	put("LAENDERNAME",MyRECORD.DATATYPES.TEXT);
	put("LAENDERVORWAHL",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("POST_CODE",MyRECORD.DATATYPES.TEXT);
	put("SONDERFALL_GELANGENSBESTAET",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("UST_PRAEFIX",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_LAND.HM_FIELDS;	
	}

}
