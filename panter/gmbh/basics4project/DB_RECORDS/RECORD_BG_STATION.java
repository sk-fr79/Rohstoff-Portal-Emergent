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

public class RECORD_BG_STATION extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_BG_STATION";
    public static String IDFIELD   = "ID_BG_STATION";
    

	//erzeugen eines RECORDNEW_JT_BG_STATION - felds
	private RECORDNEW_BG_STATION   recNEW = null;

    private _TAB  tab = _TAB.bg_station;  



	public RECORD_BG_STATION() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_BG_STATION");
	}


	public RECORD_BG_STATION(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BG_STATION");
	}



	public RECORD_BG_STATION(RECORD_BG_STATION recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BG_STATION");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_STATION.TABLENAME);
	}


	//2014-04-03
	public RECORD_BG_STATION(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BG_STATION");
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_STATION.TABLENAME);
	}




	public RECORD_BG_STATION(long lID_Unformated) throws myException
	{
		super("JT_BG_STATION","ID_BG_STATION",""+lID_Unformated);
		this.set_TABLE_NAME("JT_BG_STATION");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_STATION.TABLENAME);
	}

	public RECORD_BG_STATION(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_BG_STATION");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_STATION", "ID_BG_STATION="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_STATION", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_STATION.TABLENAME);
	}
	
	

	public RECORD_BG_STATION(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_BG_STATION","ID_BG_STATION",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_BG_STATION");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_STATION.TABLENAME);

	}


	public RECORD_BG_STATION(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BG_STATION");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_STATION", "ID_BG_STATION="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_STATION", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BG_STATION.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_BG_STATION();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_BG_STATION.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_BG_STATION";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_BG_STATION_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_BG_STATION_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BG_STATION was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BG_STATION", bibE2.cTO(), "ID_BG_STATION="+this.get_ID_BG_STATION_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BG_STATION was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BG_STATION", bibE2.cTO(), "ID_BG_STATION="+this.get_ID_BG_STATION_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_BG_STATION="+this.get_ID_BG_STATION_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_BG_STATION="+this.get_ID_BG_STATION_cUF();
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
			if (S.isFull(this.get_ID_BG_STATION_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BG_STATION", "ID_BG_STATION="+this.get_ID_BG_STATION_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_BG_STATION get_RECORDNEW_BG_STATION() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_BG_STATION();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_BG_STATION(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_BG_STATION create_Instance() throws myException {
		return new RECORD_BG_STATION();
	}
	
	public static RECORD_BG_STATION create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_BG_STATION(Conn);
    }

	public static RECORD_BG_STATION create_Instance(RECORD_BG_STATION recordOrig) {
		return new RECORD_BG_STATION(recordOrig);
    }

	public static RECORD_BG_STATION create_Instance(long lID_Unformated) throws myException {
		return new RECORD_BG_STATION(lID_Unformated);
    }

	public static RECORD_BG_STATION create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_BG_STATION(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_BG_STATION create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_BG_STATION(lID_Unformated, Conn);
	}

	public static RECORD_BG_STATION create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_BG_STATION(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_BG_STATION create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_BG_STATION(recordOrig);	    
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
    public RECORD_BG_STATION build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_BG_STATION WHERE ID_BG_STATION="+this.get_ID_BG_STATION_cUF());
      }
      //return new RECORD_BG_STATION(this.get_cSQL_4_Build());
      RECORD_BG_STATION rec = new RECORD_BG_STATION();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_BG_STATION
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_BG_STATION-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_BG_STATION get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_BG_STATION_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_BG_STATION  recNew = new RECORDNEW_BG_STATION();
		
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
    public RECORD_BG_STATION set_recordNew(RECORDNEW_BG_STATION recnew) throws myException {
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




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_basis = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_besitz_ldg = null;




		private RECORD_BG_DEL_INFO UP_RECORD_BG_DEL_INFO_id_bg_del_info = null;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_bg_station_quelle = null ;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_bg_station_ziel = null ;




		private RECLIST_BG_AUSWERT DOWN_RECLIST_BG_AUSWERT_id_bg_station = null ;




		private RECORD_BG_STORNO_INFO UP_RECORD_BG_STORNO_INFO_id_bg_storno_info = null;




		private RECORD_BG_VEKTOR UP_RECORD_BG_VEKTOR_id_bg_vektor = null;




		private RECORD_WIEGEKARTE UP_RECORD_WIEGEKARTE_id_wiegekarte = null;






	
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
	 * References the Field ID_ADRESSE_BASIS
	 * Falls keine this.get_ID_ADRESSE_BASIS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_basis() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_BASIS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_basis==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_basis = new RECORD_ADRESSE(this.get_ID_ADRESSE_BASIS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_basis;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_BESITZ_LDG
	 * Falls keine this.get_ID_ADRESSE_BESITZ_LDG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_besitz_ldg() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_BESITZ_LDG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_besitz_ldg==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_besitz_ldg = new RECORD_ADRESSE(this.get_ID_ADRESSE_BESITZ_LDG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_besitz_ldg;
	}
	





	
	/**
	 * References the Field ID_BG_DEL_INFO
	 * Falls keine this.get_ID_BG_DEL_INFO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_DEL_INFO get_UP_RECORD_BG_DEL_INFO_id_bg_del_info() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_DEL_INFO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_DEL_INFO_id_bg_del_info==null)
		{
			this.UP_RECORD_BG_DEL_INFO_id_bg_del_info = new RECORD_BG_DEL_INFO(this.get_ID_BG_DEL_INFO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_DEL_INFO_id_bg_del_info;
	}
	





	/**
	 * References the Field ID_BG_STATION_QUELLE 
	 * Falls keine get_ID_BG_STATION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_bg_station_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STATION_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_bg_station_quelle==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_bg_station_quelle = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_BG_STATION_QUELLE="+this.get_ID_BG_STATION_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_bg_station_quelle;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_STATION_QUELLE 
	 * Falls keine get_ID_BG_STATION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_bg_station_quelle(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STATION_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_bg_station_quelle==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_BG_STATION_QUELLE="+this.get_ID_BG_STATION_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_bg_station_quelle = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_bg_station_quelle;
	}


	





	/**
	 * References the Field ID_BG_STATION_ZIEL 
	 * Falls keine get_ID_BG_STATION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_bg_station_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STATION_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_bg_station_ziel==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_bg_station_ziel = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_BG_STATION_ZIEL="+this.get_ID_BG_STATION_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_bg_station_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_STATION_ZIEL 
	 * Falls keine get_ID_BG_STATION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_bg_station_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STATION_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_bg_station_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_BG_STATION_ZIEL="+this.get_ID_BG_STATION_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_bg_station_ziel = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_bg_station_ziel;
	}


	





	/**
	 * References the Field ID_BG_STATION 
	 * Falls keine get_ID_BG_STATION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_AUSWERT get_DOWN_RECORD_LIST_BG_AUSWERT_id_bg_station() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STATION_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_AUSWERT_id_bg_station==null)
		{
			this.DOWN_RECLIST_BG_AUSWERT_id_bg_station = new RECLIST_BG_AUSWERT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_AUSWERT WHERE ID_BG_STATION="+this.get_ID_BG_STATION_cUF()+" ORDER BY ID_BG_AUSWERT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_AUSWERT_id_bg_station;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BG_STATION 
	 * Falls keine get_ID_BG_STATION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_AUSWERT get_DOWN_RECORD_LIST_BG_AUSWERT_id_bg_station(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STATION_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_AUSWERT_id_bg_station==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_AUSWERT WHERE ID_BG_STATION="+this.get_ID_BG_STATION_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_AUSWERT_id_bg_station = new RECLIST_BG_AUSWERT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_AUSWERT_id_bg_station;
	}


	





	
	/**
	 * References the Field ID_BG_STORNO_INFO
	 * Falls keine this.get_ID_BG_STORNO_INFO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_STORNO_INFO get_UP_RECORD_BG_STORNO_INFO_id_bg_storno_info() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_STORNO_INFO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_STORNO_INFO_id_bg_storno_info==null)
		{
			this.UP_RECORD_BG_STORNO_INFO_id_bg_storno_info = new RECORD_BG_STORNO_INFO(this.get_ID_BG_STORNO_INFO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_STORNO_INFO_id_bg_storno_info;
	}
	





	
	/**
	 * References the Field ID_BG_VEKTOR
	 * Falls keine this.get_ID_BG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BG_VEKTOR get_UP_RECORD_BG_VEKTOR_id_bg_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BG_VEKTOR_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BG_VEKTOR_id_bg_vektor==null)
		{
			this.UP_RECORD_BG_VEKTOR_id_bg_vektor = new RECORD_BG_VEKTOR(this.get_ID_BG_VEKTOR_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BG_VEKTOR_id_bg_vektor;
	}
	





	
	/**
	 * References the Field ID_WIEGEKARTE
	 * Falls keine this.get_ID_WIEGEKARTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WIEGEKARTE get_UP_RECORD_WIEGEKARTE_id_wiegekarte() throws myException
	{
		if (S.isEmpty(this.get_ID_WIEGEKARTE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WIEGEKARTE_id_wiegekarte==null)
		{
			this.UP_RECORD_WIEGEKARTE_id_wiegekarte = new RECORD_WIEGEKARTE(this.get_ID_WIEGEKARTE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WIEGEKARTE_id_wiegekarte;
	}
	

	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FAX = "FAX";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__HAUSNUMMER = "HAUSNUMMER";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_ADRESSE_BASIS = "ID_ADRESSE_BASIS";
	public static String FIELD__ID_ADRESSE_BESITZ_LDG = "ID_ADRESSE_BESITZ_LDG";
	public static String FIELD__ID_BG_DEL_INFO = "ID_BG_DEL_INFO";
	public static String FIELD__ID_BG_STATION = "ID_BG_STATION";
	public static String FIELD__ID_BG_STORNO_INFO = "ID_BG_STORNO_INFO";
	public static String FIELD__ID_BG_VEKTOR = "ID_BG_VEKTOR";
	public static String FIELD__ID_LAND = "ID_LAND";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_WIEGEKARTE = "ID_WIEGEKARTE";
	public static String FIELD__KONTROLLMENGE = "KONTROLLMENGE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__OEFFNUNGSZEITEN = "OEFFNUNGSZEITEN";
	public static String FIELD__ORT = "ORT";
	public static String FIELD__ORTZUSATZ = "ORTZUSATZ";
	public static String FIELD__PLZ = "PLZ";
	public static String FIELD__POS_IN_MASK = "POS_IN_MASK";
	public static String FIELD__STRASSE = "STRASSE";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TELEFON = "TELEFON";
	public static String FIELD__TIMESTAMP_IN_MASK = "TIMESTAMP_IN_MASK";
	public static String FIELD__UMSATZSTEUERID = "UMSATZSTEUERID";
	public static String FIELD__UMSATZSTEUERLKZ = "UMSATZSTEUERLKZ";
	public static String FIELD__WIEGEKARTENKENNER = "WIEGEKARTENKENNER";


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
		public String get_FAX_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAX");
	}

	public String get_FAX_cF() throws myException
	{
		return this.get_FormatedValue("FAX");	
	}

	public String get_FAX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAX");
	}

	public String get_FAX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAX",cNotNullValue);
	}

	public String get_FAX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAX", calNewValueFormated);
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
	
	
	public String get_ID_ADRESSE_BASIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_BASIS");
	}

	public String get_ID_ADRESSE_BASIS_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_BASIS");	
	}

	public String get_ID_ADRESSE_BASIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_BASIS");
	}

	public String get_ID_ADRESSE_BASIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_BASIS",cNotNullValue);
	}

	public String get_ID_ADRESSE_BASIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_BASIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BASIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_BASIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BASIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BASIS", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_BASIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_BASIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_BASIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_BASIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_BASIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_BASIS").getDoubleValue();
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
	public String get_ID_ADRESSE_BASIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_BASIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_BASIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_BASIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_BASIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_BASIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_BASIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_BASIS").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_BESITZ_LDG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_BESITZ_LDG");
	}

	public String get_ID_ADRESSE_BESITZ_LDG_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_BESITZ_LDG");	
	}

	public String get_ID_ADRESSE_BESITZ_LDG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_BESITZ_LDG");
	}

	public String get_ID_ADRESSE_BESITZ_LDG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_BESITZ_LDG",cNotNullValue);
	}

	public String get_ID_ADRESSE_BESITZ_LDG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_BESITZ_LDG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_BESITZ_LDG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_BESITZ_LDG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_BESITZ_LDG", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_BESITZ_LDG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_BESITZ_LDG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_BESITZ_LDG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_BESITZ_LDG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_BESITZ_LDG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_BESITZ_LDG").getDoubleValue();
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
	public String get_ID_ADRESSE_BESITZ_LDG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_BESITZ_LDG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_BESITZ_LDG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_BESITZ_LDG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_BESITZ_LDG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_BESITZ_LDG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_BESITZ_LDG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_BESITZ_LDG").getBigDecimalValue();
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
	
	
	public String get_ID_BG_DEL_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_DEL_INFO");
	}

	public String get_ID_BG_DEL_INFO_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_DEL_INFO");	
	}

	public String get_ID_BG_DEL_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_DEL_INFO");
	}

	public String get_ID_BG_DEL_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_DEL_INFO",cNotNullValue);
	}

	public String get_ID_BG_DEL_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_DEL_INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_DEL_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_DEL_INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_DEL_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_DEL_INFO", calNewValueFormated);
	}
		public Long get_ID_BG_DEL_INFO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_DEL_INFO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_DEL_INFO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_DEL_INFO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_DEL_INFO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_DEL_INFO").getDoubleValue();
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
	public String get_ID_BG_DEL_INFO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_DEL_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BG_DEL_INFO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_DEL_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BG_DEL_INFO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_DEL_INFO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_DEL_INFO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_DEL_INFO").getBigDecimalValue();
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
	
	
	public String get_ID_BG_STATION_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_STATION");
	}

	public String get_ID_BG_STATION_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_STATION");	
	}

	public String get_ID_BG_STATION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_STATION");
	}

	public String get_ID_BG_STATION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_STATION",cNotNullValue);
	}

	public String get_ID_BG_STATION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_STATION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_STATION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STATION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_STATION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_STATION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STATION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STATION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STATION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STATION", calNewValueFormated);
	}
		public Long get_ID_BG_STATION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_STATION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_STATION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_STATION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_STATION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_STATION").getDoubleValue();
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
	public String get_ID_BG_STATION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_STATION_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BG_STATION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_STATION_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BG_STATION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_STATION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_STATION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_STATION").getBigDecimalValue();
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
	
	
	public String get_ID_BG_STORNO_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_STORNO_INFO");
	}

	public String get_ID_BG_STORNO_INFO_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_STORNO_INFO");	
	}

	public String get_ID_BG_STORNO_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_STORNO_INFO");
	}

	public String get_ID_BG_STORNO_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_STORNO_INFO",cNotNullValue);
	}

	public String get_ID_BG_STORNO_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_STORNO_INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_STORNO_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_STORNO_INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_STORNO_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_STORNO_INFO", calNewValueFormated);
	}
		public Long get_ID_BG_STORNO_INFO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_STORNO_INFO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_STORNO_INFO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_STORNO_INFO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_STORNO_INFO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_STORNO_INFO").getDoubleValue();
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
	public String get_ID_BG_STORNO_INFO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_STORNO_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BG_STORNO_INFO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_STORNO_INFO_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BG_STORNO_INFO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_STORNO_INFO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_STORNO_INFO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_STORNO_INFO").getBigDecimalValue();
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
	
	
	public String get_ID_BG_VEKTOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR");
	}

	public String get_ID_BG_VEKTOR_cF() throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR");	
	}

	public String get_ID_BG_VEKTOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BG_VEKTOR");
	}

	public String get_ID_BG_VEKTOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BG_VEKTOR",cNotNullValue);
	}

	public String get_ID_BG_VEKTOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BG_VEKTOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BG_VEKTOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BG_VEKTOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BG_VEKTOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BG_VEKTOR", calNewValueFormated);
	}
		public Long get_ID_BG_VEKTOR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BG_VEKTOR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BG_VEKTOR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BG_VEKTOR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BG_VEKTOR").getDoubleValue();
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
	public String get_ID_BG_VEKTOR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BG_VEKTOR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BG_VEKTOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BG_VEKTOR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BG_VEKTOR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BG_VEKTOR").getBigDecimalValue();
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
	
	
	public String get_ID_WIEGEKARTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE");
	}

	public String get_ID_WIEGEKARTE_cF() throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE");	
	}

	public String get_ID_WIEGEKARTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WIEGEKARTE");
	}

	public String get_ID_WIEGEKARTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WIEGEKARTE",cNotNullValue);
	}

	public String get_ID_WIEGEKARTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WIEGEKARTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
	}
		public Long get_ID_WIEGEKARTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WIEGEKARTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WIEGEKARTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WIEGEKARTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WIEGEKARTE").getDoubleValue();
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
	public String get_ID_WIEGEKARTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WIEGEKARTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WIEGEKARTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WIEGEKARTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WIEGEKARTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WIEGEKARTE").getBigDecimalValue();
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
	
	
	public String get_KONTROLLMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KONTROLLMENGE");
	}

	public String get_KONTROLLMENGE_cF() throws myException
	{
		return this.get_FormatedValue("KONTROLLMENGE");	
	}

	public String get_KONTROLLMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KONTROLLMENGE");
	}

	public String get_KONTROLLMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KONTROLLMENGE",cNotNullValue);
	}

	public String get_KONTROLLMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KONTROLLMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KONTROLLMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KONTROLLMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KONTROLLMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KONTROLLMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTROLLMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTROLLMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KONTROLLMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KONTROLLMENGE", calNewValueFormated);
	}
		public Double get_KONTROLLMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KONTROLLMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KONTROLLMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KONTROLLMENGE").getDoubleValue();
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
	public String get_KONTROLLMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KONTROLLMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KONTROLLMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KONTROLLMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KONTROLLMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KONTROLLMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KONTROLLMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KONTROLLMENGE").getBigDecimalValue();
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
		public String get_POS_IN_MASK_cUF() throws myException
	{
		return this.get_UnFormatedValue("POS_IN_MASK");
	}

	public String get_POS_IN_MASK_cF() throws myException
	{
		return this.get_FormatedValue("POS_IN_MASK");	
	}

	public String get_POS_IN_MASK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POS_IN_MASK");
	}

	public String get_POS_IN_MASK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POS_IN_MASK",cNotNullValue);
	}

	public String get_POS_IN_MASK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POS_IN_MASK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POS_IN_MASK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POS_IN_MASK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POS_IN_MASK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POS_IN_MASK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POS_IN_MASK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POS_IN_MASK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POS_IN_MASK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POS_IN_MASK", calNewValueFormated);
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
	
	
	public String get_TELEFON_cUF() throws myException
	{
		return this.get_UnFormatedValue("TELEFON");
	}

	public String get_TELEFON_cF() throws myException
	{
		return this.get_FormatedValue("TELEFON");	
	}

	public String get_TELEFON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TELEFON");
	}

	public String get_TELEFON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TELEFON",cNotNullValue);
	}

	public String get_TELEFON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TELEFON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TELEFON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TELEFON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TELEFON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TELEFON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TELEFON", calNewValueFormated);
	}
		public String get_TIMESTAMP_IN_MASK_cUF() throws myException
	{
		return this.get_UnFormatedValue("TIMESTAMP_IN_MASK");
	}

	public String get_TIMESTAMP_IN_MASK_cF() throws myException
	{
		return this.get_FormatedValue("TIMESTAMP_IN_MASK");	
	}

	public String get_TIMESTAMP_IN_MASK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TIMESTAMP_IN_MASK");
	}

	public String get_TIMESTAMP_IN_MASK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TIMESTAMP_IN_MASK",cNotNullValue);
	}

	public String get_TIMESTAMP_IN_MASK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TIMESTAMP_IN_MASK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TIMESTAMP_IN_MASK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TIMESTAMP_IN_MASK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TIMESTAMP_IN_MASK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TIMESTAMP_IN_MASK", calNewValueFormated);
	}
		public String get_UMSATZSTEUERID_cUF() throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERID");
	}

	public String get_UMSATZSTEUERID_cF() throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERID");	
	}

	public String get_UMSATZSTEUERID_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UMSATZSTEUERID");
	}

	public String get_UMSATZSTEUERID_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERID",cNotNullValue);
	}

	public String get_UMSATZSTEUERID_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERID",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UMSATZSTEUERID", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERID(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UMSATZSTEUERID", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERID_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERID", calNewValueFormated);
	}
		public String get_UMSATZSTEUERLKZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERLKZ");
	}

	public String get_UMSATZSTEUERLKZ_cF() throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERLKZ");	
	}

	public String get_UMSATZSTEUERLKZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("UMSATZSTEUERLKZ");
	}

	public String get_UMSATZSTEUERLKZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("UMSATZSTEUERLKZ",cNotNullValue);
	}

	public String get_UMSATZSTEUERLKZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("UMSATZSTEUERLKZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("UMSATZSTEUERLKZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_UMSATZSTEUERLKZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("UMSATZSTEUERLKZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_UMSATZSTEUERLKZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("UMSATZSTEUERLKZ", calNewValueFormated);
	}
		public String get_WIEGEKARTENKENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER");
	}

	public String get_WIEGEKARTENKENNER_cF() throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER");	
	}

	public String get_WIEGEKARTENKENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEGEKARTENKENNER");
	}

	public String get_WIEGEKARTENKENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTENKENNER",cNotNullValue);
	}

	public String get_WIEGEKARTENKENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEGEKARTENKENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTENKENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEGEKARTENKENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTENKENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTENKENNER", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FAX",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("HAUSNUMMER",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_BASIS",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_BESITZ_LDG",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_DEL_INFO",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_STATION",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_STORNO_INFO",MyRECORD.DATATYPES.NUMBER);
	put("ID_BG_VEKTOR",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAND",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_WIEGEKARTE",MyRECORD.DATATYPES.NUMBER);
	put("KONTROLLMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("NAME1",MyRECORD.DATATYPES.TEXT);
	put("NAME2",MyRECORD.DATATYPES.TEXT);
	put("NAME3",MyRECORD.DATATYPES.TEXT);
	put("OEFFNUNGSZEITEN",MyRECORD.DATATYPES.TEXT);
	put("ORT",MyRECORD.DATATYPES.TEXT);
	put("ORTZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("PLZ",MyRECORD.DATATYPES.TEXT);
	put("POS_IN_MASK",MyRECORD.DATATYPES.TEXT);
	put("STRASSE",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TELEFON",MyRECORD.DATATYPES.TEXT);
	put("TIMESTAMP_IN_MASK",MyRECORD.DATATYPES.TEXT);
	put("UMSATZSTEUERID",MyRECORD.DATATYPES.TEXT);
	put("UMSATZSTEUERLKZ",MyRECORD.DATATYPES.TEXT);
	put("WIEGEKARTENKENNER",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_BG_STATION.HM_FIELDS;	
	}

}
