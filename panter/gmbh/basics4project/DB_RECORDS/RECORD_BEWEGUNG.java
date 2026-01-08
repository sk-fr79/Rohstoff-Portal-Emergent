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

public class RECORD_BEWEGUNG extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_BEWEGUNG";
    public static String IDFIELD   = "ID_BEWEGUNG";
    

	//erzeugen eines RECORDNEW_JT_BEWEGUNG - felds
	private RECORDNEW_BEWEGUNG   recNEW = null;

    private _TAB  tab = _TAB.bewegung;  



	public RECORD_BEWEGUNG() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_BEWEGUNG");
	}


	public RECORD_BEWEGUNG(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BEWEGUNG");
	}



	public RECORD_BEWEGUNG(RECORD_BEWEGUNG recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BEWEGUNG");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG.TABLENAME);
	}


	//2014-04-03
	public RECORD_BEWEGUNG(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BEWEGUNG");
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG.TABLENAME);
	}




	public RECORD_BEWEGUNG(long lID_Unformated) throws myException
	{
		super("JT_BEWEGUNG","ID_BEWEGUNG",""+lID_Unformated);
		this.set_TABLE_NAME("JT_BEWEGUNG");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG.TABLENAME);
	}

	public RECORD_BEWEGUNG(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_BEWEGUNG");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG", "ID_BEWEGUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG.TABLENAME);
	}
	
	

	public RECORD_BEWEGUNG(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_BEWEGUNG","ID_BEWEGUNG",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_BEWEGUNG");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG.TABLENAME);

	}


	public RECORD_BEWEGUNG(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BEWEGUNG");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG", "ID_BEWEGUNG="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_BEWEGUNG();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_BEWEGUNG.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_BEWEGUNG";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_BEWEGUNG_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_BEWEGUNG_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BEWEGUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BEWEGUNG", bibE2.cTO(), "ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BEWEGUNG was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BEWEGUNG", bibE2.cTO(), "ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF();
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
			if (S.isFull(this.get_ID_BEWEGUNG_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG", "ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_BEWEGUNG get_RECORDNEW_BEWEGUNG() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_BEWEGUNG();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_BEWEGUNG(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_BEWEGUNG create_Instance() throws myException {
		return new RECORD_BEWEGUNG();
	}
	
	public static RECORD_BEWEGUNG create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_BEWEGUNG(Conn);
    }

	public static RECORD_BEWEGUNG create_Instance(RECORD_BEWEGUNG recordOrig) {
		return new RECORD_BEWEGUNG(recordOrig);
    }

	public static RECORD_BEWEGUNG create_Instance(long lID_Unformated) throws myException {
		return new RECORD_BEWEGUNG(lID_Unformated);
    }

	public static RECORD_BEWEGUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_BEWEGUNG(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_BEWEGUNG create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_BEWEGUNG(lID_Unformated, Conn);
	}

	public static RECORD_BEWEGUNG create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_BEWEGUNG(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_BEWEGUNG create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_BEWEGUNG(recordOrig);	    
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
    public RECORD_BEWEGUNG build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF());
      }
      //return new RECORD_BEWEGUNG(this.get_cSQL_4_Build());
      RECORD_BEWEGUNG rec = new RECORD_BEWEGUNG();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_BEWEGUNG
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_BEWEGUNG-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_BEWEGUNG get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_BEWEGUNG_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_BEWEGUNG  recNew = new RECORDNEW_BEWEGUNG();
		
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
    public RECORD_BEWEGUNG set_recordNew(RECORDNEW_BEWEGUNG recnew) throws myException {
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
	
	



		private RECLIST_BEWEGUNG_MENGE DOWN_RECLIST_BEWEGUNG_MENGE_id_bewegung = null ;




		private RECLIST_BEWEGUNG_TRIG DOWN_RECLIST_BEWEGUNG_TRIG_id_bewegung = null ;




		private RECLIST_BEWEGUNG_VEKTOR DOWN_RECLIST_BEWEGUNG_VEKTOR_id_bewegung = null ;




		private RECORD_CONTAINERTYP UP_RECORD_CONTAINERTYP_id_containertyp_fp = null;




		private RECORD_MASCHINEN UP_RECORD_MASCHINEN_id_maschinen_anh_fp = null;




		private RECORD_MASCHINEN UP_RECORD_MASCHINEN_id_maschinen_lkw_fp = null;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre = null;






	/**
	 * References the Field ID_BEWEGUNG 
	 * Falls keine get_ID_BEWEGUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_MENGE get_DOWN_RECORD_LIST_BEWEGUNG_MENGE_id_bewegung() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_MENGE_id_bewegung==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_MENGE_id_bewegung = new RECLIST_BEWEGUNG_MENGE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_MENGE WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF()+" ORDER BY ID_BEWEGUNG_MENGE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_MENGE_id_bewegung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BEWEGUNG 
	 * Falls keine get_ID_BEWEGUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_MENGE get_DOWN_RECORD_LIST_BEWEGUNG_MENGE_id_bewegung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_MENGE_id_bewegung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_MENGE WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_MENGE_id_bewegung = new RECLIST_BEWEGUNG_MENGE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_MENGE_id_bewegung;
	}


	





	/**
	 * References the Field ID_BEWEGUNG 
	 * Falls keine get_ID_BEWEGUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_TRIG get_DOWN_RECORD_LIST_BEWEGUNG_TRIG_id_bewegung() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_TRIG_id_bewegung==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_TRIG_id_bewegung = new RECLIST_BEWEGUNG_TRIG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_TRIG WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF()+" ORDER BY ID_BEWEGUNG_TRIG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_TRIG_id_bewegung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BEWEGUNG 
	 * Falls keine get_ID_BEWEGUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_TRIG get_DOWN_RECORD_LIST_BEWEGUNG_TRIG_id_bewegung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_TRIG_id_bewegung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_TRIG WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_TRIG_id_bewegung = new RECLIST_BEWEGUNG_TRIG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_TRIG_id_bewegung;
	}


	





	/**
	 * References the Field ID_BEWEGUNG 
	 * Falls keine get_ID_BEWEGUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_bewegung() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_bewegung==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_bewegung = new RECLIST_BEWEGUNG_VEKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_bewegung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BEWEGUNG 
	 * Falls keine get_ID_BEWEGUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_bewegung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_bewegung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG="+this.get_ID_BEWEGUNG_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_bewegung = new RECLIST_BEWEGUNG_VEKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_id_bewegung;
	}


	





	
	/**
	 * References the Field ID_CONTAINERTYP_FP
	 * Falls keine this.get_ID_CONTAINERTYP_FP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_CONTAINERTYP get_UP_RECORD_CONTAINERTYP_id_containertyp_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_CONTAINERTYP_FP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_CONTAINERTYP_id_containertyp_fp==null)
		{
			this.UP_RECORD_CONTAINERTYP_id_containertyp_fp = new RECORD_CONTAINERTYP(this.get_ID_CONTAINERTYP_FP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_CONTAINERTYP_id_containertyp_fp;
	}
	





	
	/**
	 * References the Field ID_MASCHINEN_ANH_FP
	 * Falls keine this.get_ID_MASCHINEN_ANH_FP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MASCHINEN get_UP_RECORD_MASCHINEN_id_maschinen_anh_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_ANH_FP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MASCHINEN_id_maschinen_anh_fp==null)
		{
			this.UP_RECORD_MASCHINEN_id_maschinen_anh_fp = new RECORD_MASCHINEN(this.get_ID_MASCHINEN_ANH_FP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MASCHINEN_id_maschinen_anh_fp;
	}
	





	
	/**
	 * References the Field ID_MASCHINEN_LKW_FP
	 * Falls keine this.get_ID_MASCHINEN_LKW_FP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MASCHINEN get_UP_RECORD_MASCHINEN_id_maschinen_lkw_fp() throws myException
	{
		if (S.isEmpty(this.get_ID_MASCHINEN_LKW_FP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MASCHINEN_id_maschinen_lkw_fp==null)
		{
			this.UP_RECORD_MASCHINEN_id_maschinen_lkw_fp = new RECORD_MASCHINEN(this.get_ID_MASCHINEN_LKW_FP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MASCHINEN_id_maschinen_lkw_fp;
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
	

	public static String FIELD__ABLADE_DATUM_BIS = "ABLADE_DATUM_BIS";
	public static String FIELD__ABLADE_DATUM_VON = "ABLADE_DATUM_VON";
	public static String FIELD__ANRUFDATUM_FP = "ANRUFDATUM_FP";
	public static String FIELD__ANRUFER_FP = "ANRUFER_FP";
	public static String FIELD__ANZAHL_CONTAINER_FP = "ANZAHL_CONTAINER_FP";
	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__BEMERKUNG_SACHBEARBEITER = "BEMERKUNG_SACHBEARBEITER";
	public static String FIELD__BEMERKUNG_START_FP = "BEMERKUNG_START_FP";
	public static String FIELD__BEMERKUNG_ZIEL_FP = "BEMERKUNG_ZIEL_FP";
	public static String FIELD__BEWEGUNG_TYP = "BEWEGUNG_TYP";
	public static String FIELD__DAT_FAHRPLAN_FP = "DAT_FAHRPLAN_FP";
	public static String FIELD__DAT_VORGEMERKT_ENDE_FP = "DAT_VORGEMERKT_ENDE_FP";
	public static String FIELD__DAT_VORGEMERKT_FP = "DAT_VORGEMERKT_FP";
	public static String FIELD__DELETED = "DELETED";
	public static String FIELD__DEL_DATE = "DEL_DATE";
	public static String FIELD__DEL_GRUND = "DEL_GRUND";
	public static String FIELD__DEL_KUERZEL = "DEL_KUERZEL";
	public static String FIELD__EAN_CODE_FP = "EAN_CODE_FP";
	public static String FIELD__ERFASSER_FP = "ERFASSER_FP";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FAHRER_FP = "FAHRER_FP";
	public static String FIELD__FAHRPLANGRUPPE_FP = "FAHRPLANGRUPPE_FP";
	public static String FIELD__FAHRPLAN_SATZ = "FAHRPLAN_SATZ";
	public static String FIELD__FAHRT_ANFANG_FP = "FAHRT_ANFANG_FP";
	public static String FIELD__FAHRT_ENDE_FP = "FAHRT_ENDE_FP";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_BEWEGUNG = "ID_BEWEGUNG";
	public static String FIELD__ID_CONTAINERTYP_FP = "ID_CONTAINERTYP_FP";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_MASCHINEN_ANH_FP = "ID_MASCHINEN_ANH_FP";
	public static String FIELD__ID_MASCHINEN_LKW_FP = "ID_MASCHINEN_LKW_FP";
	public static String FIELD__ID_VPOS_TPA_FUHRE = "ID_VPOS_TPA_FUHRE";
	public static String FIELD__ID_VPOS_TPA_NG = "ID_VPOS_TPA_NG";
	public static String FIELD__IST_GEPLANT_FP = "IST_GEPLANT_FP";
	public static String FIELD__IST_LAGERBUCHUNG_ALT = "IST_LAGERBUCHUNG_ALT";
	public static String FIELD__LADE_DATUM_BIS = "LADE_DATUM_BIS";
	public static String FIELD__LADE_DATUM_VON = "LADE_DATUM_VON";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SORTIERUNG_FP = "SORTIERUNG_FP";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TAETIGKEIT_FP = "TAETIGKEIT_FP";
	public static String FIELD__ZEITSTEMPEL = "ZEITSTEMPEL";


	public String get_ABLADE_DATUM_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABLADE_DATUM_BIS");
	}

	public String get_ABLADE_DATUM_BIS_cF() throws myException
	{
		return this.get_FormatedValue("ABLADE_DATUM_BIS");	
	}

	public String get_ABLADE_DATUM_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABLADE_DATUM_BIS");
	}

	public String get_ABLADE_DATUM_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABLADE_DATUM_BIS",cNotNullValue);
	}

	public String get_ABLADE_DATUM_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABLADE_DATUM_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABLADE_DATUM_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABLADE_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABLADE_DATUM_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABLADE_DATUM_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADE_DATUM_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADE_DATUM_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADE_DATUM_BIS", calNewValueFormated);
	}
		public String get_ABLADE_DATUM_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABLADE_DATUM_VON");
	}

	public String get_ABLADE_DATUM_VON_cF() throws myException
	{
		return this.get_FormatedValue("ABLADE_DATUM_VON");	
	}

	public String get_ABLADE_DATUM_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABLADE_DATUM_VON");
	}

	public String get_ABLADE_DATUM_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABLADE_DATUM_VON",cNotNullValue);
	}

	public String get_ABLADE_DATUM_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABLADE_DATUM_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABLADE_DATUM_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABLADE_DATUM_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABLADE_DATUM_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABLADE_DATUM_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADE_DATUM_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADE_DATUM_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABLADE_DATUM_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABLADE_DATUM_VON", calNewValueFormated);
	}
		public String get_ANRUFDATUM_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANRUFDATUM_FP");
	}

	public String get_ANRUFDATUM_FP_cF() throws myException
	{
		return this.get_FormatedValue("ANRUFDATUM_FP");	
	}

	public String get_ANRUFDATUM_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANRUFDATUM_FP");
	}

	public String get_ANRUFDATUM_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANRUFDATUM_FP",cNotNullValue);
	}

	public String get_ANRUFDATUM_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANRUFDATUM_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANRUFDATUM_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANRUFDATUM_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANRUFDATUM_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFDATUM_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFDATUM_FP", calNewValueFormated);
	}
		public String get_ANRUFER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANRUFER_FP");
	}

	public String get_ANRUFER_FP_cF() throws myException
	{
		return this.get_FormatedValue("ANRUFER_FP");	
	}

	public String get_ANRUFER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANRUFER_FP");
	}

	public String get_ANRUFER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANRUFER_FP",cNotNullValue);
	}

	public String get_ANRUFER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANRUFER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANRUFER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANRUFER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANRUFER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANRUFER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANRUFER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANRUFER_FP", calNewValueFormated);
	}
		public String get_ANZAHL_CONTAINER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_CONTAINER_FP");
	}

	public String get_ANZAHL_CONTAINER_FP_cF() throws myException
	{
		return this.get_FormatedValue("ANZAHL_CONTAINER_FP");	
	}

	public String get_ANZAHL_CONTAINER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZAHL_CONTAINER_FP");
	}

	public String get_ANZAHL_CONTAINER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_CONTAINER_FP",cNotNullValue);
	}

	public String get_ANZAHL_CONTAINER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZAHL_CONTAINER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZAHL_CONTAINER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZAHL_CONTAINER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_CONTAINER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_CONTAINER_FP", calNewValueFormated);
	}
		public Long get_ANZAHL_CONTAINER_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ANZAHL_CONTAINER_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ANZAHL_CONTAINER_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZAHL_CONTAINER_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZAHL_CONTAINER_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZAHL_CONTAINER_FP").getDoubleValue();
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
	public String get_ANZAHL_CONTAINER_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_CONTAINER_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ANZAHL_CONTAINER_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_CONTAINER_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ANZAHL_CONTAINER_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_CONTAINER_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZAHL_CONTAINER_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_CONTAINER_FP").getBigDecimalValue();
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
		public String get_BEMERKUNG_SACHBEARBEITER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_SACHBEARBEITER");
	}

	public String get_BEMERKUNG_SACHBEARBEITER_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_SACHBEARBEITER");	
	}

	public String get_BEMERKUNG_SACHBEARBEITER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_SACHBEARBEITER");
	}

	public String get_BEMERKUNG_SACHBEARBEITER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_SACHBEARBEITER",cNotNullValue);
	}

	public String get_BEMERKUNG_SACHBEARBEITER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_SACHBEARBEITER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_SACHBEARBEITER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_SACHBEARBEITER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_SACHBEARBEITER", calNewValueFormated);
	}
		public String get_BEMERKUNG_START_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_START_FP");
	}

	public String get_BEMERKUNG_START_FP_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_START_FP");	
	}

	public String get_BEMERKUNG_START_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_START_FP");
	}

	public String get_BEMERKUNG_START_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_START_FP",cNotNullValue);
	}

	public String get_BEMERKUNG_START_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_START_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_START_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_START_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_START_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_START_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_START_FP", calNewValueFormated);
	}
		public String get_BEMERKUNG_ZIEL_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ZIEL_FP");
	}

	public String get_BEMERKUNG_ZIEL_FP_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ZIEL_FP");	
	}

	public String get_BEMERKUNG_ZIEL_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_ZIEL_FP");
	}

	public String get_BEMERKUNG_ZIEL_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_ZIEL_FP",cNotNullValue);
	}

	public String get_BEMERKUNG_ZIEL_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_ZIEL_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_ZIEL_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_ZIEL_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_ZIEL_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_ZIEL_FP", calNewValueFormated);
	}
		public String get_BEWEGUNG_TYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEWEGUNG_TYP");
	}

	public String get_BEWEGUNG_TYP_cF() throws myException
	{
		return this.get_FormatedValue("BEWEGUNG_TYP");	
	}

	public String get_BEWEGUNG_TYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEWEGUNG_TYP");
	}

	public String get_BEWEGUNG_TYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEWEGUNG_TYP",cNotNullValue);
	}

	public String get_BEWEGUNG_TYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEWEGUNG_TYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEWEGUNG_TYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEWEGUNG_TYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEWEGUNG_TYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEWEGUNG_TYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEWEGUNG_TYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEWEGUNG_TYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEWEGUNG_TYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEWEGUNG_TYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEWEGUNG_TYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEWEGUNG_TYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEWEGUNG_TYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEWEGUNG_TYP", calNewValueFormated);
	}
		public String get_DAT_FAHRPLAN_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("DAT_FAHRPLAN_FP");
	}

	public String get_DAT_FAHRPLAN_FP_cF() throws myException
	{
		return this.get_FormatedValue("DAT_FAHRPLAN_FP");	
	}

	public String get_DAT_FAHRPLAN_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DAT_FAHRPLAN_FP");
	}

	public String get_DAT_FAHRPLAN_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DAT_FAHRPLAN_FP",cNotNullValue);
	}

	public String get_DAT_FAHRPLAN_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DAT_FAHRPLAN_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DAT_FAHRPLAN_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DAT_FAHRPLAN_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DAT_FAHRPLAN_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_FAHRPLAN_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_FAHRPLAN_FP", calNewValueFormated);
	}
		public String get_DAT_VORGEMERKT_ENDE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_ENDE_FP");
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_cF() throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_ENDE_FP");	
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DAT_VORGEMERKT_ENDE_FP");
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_ENDE_FP",cNotNullValue);
	}

	public String get_DAT_VORGEMERKT_ENDE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_ENDE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_ENDE_FP", calNewValueFormated);
	}
		public String get_DAT_VORGEMERKT_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_FP");
	}

	public String get_DAT_VORGEMERKT_FP_cF() throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_FP");	
	}

	public String get_DAT_VORGEMERKT_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DAT_VORGEMERKT_FP");
	}

	public String get_DAT_VORGEMERKT_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DAT_VORGEMERKT_FP",cNotNullValue);
	}

	public String get_DAT_VORGEMERKT_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DAT_VORGEMERKT_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DAT_VORGEMERKT_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DAT_VORGEMERKT_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DAT_VORGEMERKT_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DAT_VORGEMERKT_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DAT_VORGEMERKT_FP", calNewValueFormated);
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
		public String get_EAN_CODE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("EAN_CODE_FP");
	}

	public String get_EAN_CODE_FP_cF() throws myException
	{
		return this.get_FormatedValue("EAN_CODE_FP");	
	}

	public String get_EAN_CODE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EAN_CODE_FP");
	}

	public String get_EAN_CODE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EAN_CODE_FP",cNotNullValue);
	}

	public String get_EAN_CODE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EAN_CODE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EAN_CODE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EAN_CODE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EAN_CODE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EAN_CODE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EAN_CODE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EAN_CODE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EAN_CODE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EAN_CODE_FP", calNewValueFormated);
	}
		public String get_ERFASSER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERFASSER_FP");
	}

	public String get_ERFASSER_FP_cF() throws myException
	{
		return this.get_FormatedValue("ERFASSER_FP");	
	}

	public String get_ERFASSER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERFASSER_FP");
	}

	public String get_ERFASSER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERFASSER_FP",cNotNullValue);
	}

	public String get_ERFASSER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERFASSER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERFASSER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERFASSER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERFASSER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERFASSER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERFASSER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERFASSER_FP", calNewValueFormated);
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
		public String get_FAHRER_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRER_FP");
	}

	public String get_FAHRER_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRER_FP");	
	}

	public String get_FAHRER_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRER_FP");
	}

	public String get_FAHRER_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRER_FP",cNotNullValue);
	}

	public String get_FAHRER_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRER_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRER_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRER_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRER_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRER_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRER_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRER_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRER_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRER_FP", calNewValueFormated);
	}
		public String get_FAHRPLANGRUPPE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRPLANGRUPPE_FP");
	}

	public String get_FAHRPLANGRUPPE_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRPLANGRUPPE_FP");	
	}

	public String get_FAHRPLANGRUPPE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRPLANGRUPPE_FP");
	}

	public String get_FAHRPLANGRUPPE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRPLANGRUPPE_FP",cNotNullValue);
	}

	public String get_FAHRPLANGRUPPE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRPLANGRUPPE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRPLANGRUPPE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRPLANGRUPPE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLANGRUPPE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLANGRUPPE_FP", calNewValueFormated);
	}
		public Long get_FAHRPLANGRUPPE_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("FAHRPLANGRUPPE_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_FAHRPLANGRUPPE_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("FAHRPLANGRUPPE_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_FAHRPLANGRUPPE_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("FAHRPLANGRUPPE_FP").getDoubleValue();
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
	public String get_FAHRPLANGRUPPE_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FAHRPLANGRUPPE_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_FAHRPLANGRUPPE_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_FAHRPLANGRUPPE_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_FAHRPLANGRUPPE_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("FAHRPLANGRUPPE_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_FAHRPLANGRUPPE_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("FAHRPLANGRUPPE_FP").getBigDecimalValue();
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
	
	
	public String get_FAHRPLAN_SATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRPLAN_SATZ");
	}

	public String get_FAHRPLAN_SATZ_cF() throws myException
	{
		return this.get_FormatedValue("FAHRPLAN_SATZ");	
	}

	public String get_FAHRPLAN_SATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRPLAN_SATZ");
	}

	public String get_FAHRPLAN_SATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRPLAN_SATZ",cNotNullValue);
	}

	public String get_FAHRPLAN_SATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRPLAN_SATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRPLAN_SATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRPLAN_SATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRPLAN_SATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRPLAN_SATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLAN_SATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRPLAN_SATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLAN_SATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLAN_SATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLAN_SATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLAN_SATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRPLAN_SATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRPLAN_SATZ", calNewValueFormated);
	}
		public boolean is_FAHRPLAN_SATZ_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_FAHRPLAN_SATZ_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_FAHRPLAN_SATZ_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_FAHRPLAN_SATZ_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_FAHRT_ANFANG_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ANFANG_FP");
	}

	public String get_FAHRT_ANFANG_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRT_ANFANG_FP");	
	}

	public String get_FAHRT_ANFANG_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRT_ANFANG_FP");
	}

	public String get_FAHRT_ANFANG_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ANFANG_FP",cNotNullValue);
	}

	public String get_FAHRT_ANFANG_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRT_ANFANG_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRT_ANFANG_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ANFANG_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRT_ANFANG_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ANFANG_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ANFANG_FP", calNewValueFormated);
	}
		public String get_FAHRT_ENDE_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ENDE_FP");
	}

	public String get_FAHRT_ENDE_FP_cF() throws myException
	{
		return this.get_FormatedValue("FAHRT_ENDE_FP");	
	}

	public String get_FAHRT_ENDE_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FAHRT_ENDE_FP");
	}

	public String get_FAHRT_ENDE_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FAHRT_ENDE_FP",cNotNullValue);
	}

	public String get_FAHRT_ENDE_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FAHRT_ENDE_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FAHRT_ENDE_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FAHRT_ENDE_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FAHRT_ENDE_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FAHRT_ENDE_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FAHRT_ENDE_FP", calNewValueFormated);
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
		public String get_ID_BEWEGUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG");
	}

	public String get_ID_BEWEGUNG_cF() throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG");	
	}

	public String get_ID_BEWEGUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BEWEGUNG");
	}

	public String get_ID_BEWEGUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG",cNotNullValue);
	}

	public String get_ID_BEWEGUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BEWEGUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BEWEGUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG", calNewValueFormated);
	}
		public Long get_ID_BEWEGUNG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BEWEGUNG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BEWEGUNG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BEWEGUNG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG").getDoubleValue();
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
	public String get_ID_BEWEGUNG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BEWEGUNG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BEWEGUNG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BEWEGUNG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG").getBigDecimalValue();
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
	
	
	public String get_ID_CONTAINERTYP_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINERTYP_FP");
	}

	public String get_ID_CONTAINERTYP_FP_cF() throws myException
	{
		return this.get_FormatedValue("ID_CONTAINERTYP_FP");	
	}

	public String get_ID_CONTAINERTYP_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_CONTAINERTYP_FP");
	}

	public String get_ID_CONTAINERTYP_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_CONTAINERTYP_FP",cNotNullValue);
	}

	public String get_ID_CONTAINERTYP_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_CONTAINERTYP_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_CONTAINERTYP_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINERTYP_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_CONTAINERTYP_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINERTYP_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_CONTAINERTYP_FP", calNewValueFormated);
	}
		public Long get_ID_CONTAINERTYP_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_CONTAINERTYP_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_CONTAINERTYP_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_CONTAINERTYP_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_CONTAINERTYP_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_CONTAINERTYP_FP").getDoubleValue();
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
	public String get_ID_CONTAINERTYP_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINERTYP_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_CONTAINERTYP_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_CONTAINERTYP_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_CONTAINERTYP_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINERTYP_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_CONTAINERTYP_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_CONTAINERTYP_FP").getBigDecimalValue();
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
	
	
	public String get_ID_MASCHINEN_ANH_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_ANH_FP");
	}

	public String get_ID_MASCHINEN_ANH_FP_cF() throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_ANH_FP");	
	}

	public String get_ID_MASCHINEN_ANH_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MASCHINEN_ANH_FP");
	}

	public String get_ID_MASCHINEN_ANH_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_ANH_FP",cNotNullValue);
	}

	public String get_ID_MASCHINEN_ANH_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_ANH_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_ANH_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MASCHINEN_ANH_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_ANH_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_ANH_FP", calNewValueFormated);
	}
		public Long get_ID_MASCHINEN_ANH_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MASCHINEN_ANH_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MASCHINEN_ANH_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_ANH_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MASCHINEN_ANH_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_ANH_FP").getDoubleValue();
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
	public String get_ID_MASCHINEN_ANH_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_ANH_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MASCHINEN_ANH_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_ANH_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MASCHINEN_ANH_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_ANH_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MASCHINEN_ANH_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_ANH_FP").getBigDecimalValue();
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
	
	
	public String get_ID_MASCHINEN_LKW_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_LKW_FP");
	}

	public String get_ID_MASCHINEN_LKW_FP_cF() throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_LKW_FP");	
	}

	public String get_ID_MASCHINEN_LKW_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MASCHINEN_LKW_FP");
	}

	public String get_ID_MASCHINEN_LKW_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MASCHINEN_LKW_FP",cNotNullValue);
	}

	public String get_ID_MASCHINEN_LKW_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MASCHINEN_LKW_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MASCHINEN_LKW_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MASCHINEN_LKW_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MASCHINEN_LKW_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MASCHINEN_LKW_FP", calNewValueFormated);
	}
		public Long get_ID_MASCHINEN_LKW_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MASCHINEN_LKW_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MASCHINEN_LKW_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_LKW_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MASCHINEN_LKW_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MASCHINEN_LKW_FP").getDoubleValue();
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
	public String get_ID_MASCHINEN_LKW_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_LKW_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MASCHINEN_LKW_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MASCHINEN_LKW_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MASCHINEN_LKW_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_LKW_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MASCHINEN_LKW_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MASCHINEN_LKW_FP").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_NG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_NG");
	}

	public String get_ID_VPOS_TPA_NG_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_NG");	
	}

	public String get_ID_VPOS_TPA_NG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_NG");
	}

	public String get_ID_VPOS_TPA_NG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_NG",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_NG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_NG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_NG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_NG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_NG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_NG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_NG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_NG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_NG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_NG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_NG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_NG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_NG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_NG", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_NG_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_NG").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_NG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_NG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_NG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_NG").getDoubleValue();
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
	public String get_ID_VPOS_TPA_NG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_NG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_NG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_NG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_NG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_NG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_NG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_NG").getBigDecimalValue();
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
	
	
	public String get_IST_GEPLANT_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_GEPLANT_FP");
	}

	public String get_IST_GEPLANT_FP_cF() throws myException
	{
		return this.get_FormatedValue("IST_GEPLANT_FP");	
	}

	public String get_IST_GEPLANT_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_GEPLANT_FP");
	}

	public String get_IST_GEPLANT_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_GEPLANT_FP",cNotNullValue);
	}

	public String get_IST_GEPLANT_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_GEPLANT_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_GEPLANT_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_GEPLANT_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_GEPLANT_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_GEPLANT_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_GEPLANT_FP", calNewValueFormated);
	}
		public boolean is_IST_GEPLANT_FP_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEPLANT_FP_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_GEPLANT_FP_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_GEPLANT_FP_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_LAGERBUCHUNG_ALT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_LAGERBUCHUNG_ALT");
	}

	public String get_IST_LAGERBUCHUNG_ALT_cF() throws myException
	{
		return this.get_FormatedValue("IST_LAGERBUCHUNG_ALT");	
	}

	public String get_IST_LAGERBUCHUNG_ALT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_LAGERBUCHUNG_ALT");
	}

	public String get_IST_LAGERBUCHUNG_ALT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_LAGERBUCHUNG_ALT",cNotNullValue);
	}

	public String get_IST_LAGERBUCHUNG_ALT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_LAGERBUCHUNG_ALT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERBUCHUNG_ALT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_LAGERBUCHUNG_ALT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_LAGERBUCHUNG_ALT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_LAGERBUCHUNG_ALT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERBUCHUNG_ALT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_LAGERBUCHUNG_ALT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERBUCHUNG_ALT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LAGERBUCHUNG_ALT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERBUCHUNG_ALT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LAGERBUCHUNG_ALT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAGERBUCHUNG_ALT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LAGERBUCHUNG_ALT", calNewValueFormated);
	}
		public boolean is_IST_LAGERBUCHUNG_ALT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LAGERBUCHUNG_ALT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_LAGERBUCHUNG_ALT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LAGERBUCHUNG_ALT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_LADE_DATUM_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("LADE_DATUM_BIS");
	}

	public String get_LADE_DATUM_BIS_cF() throws myException
	{
		return this.get_FormatedValue("LADE_DATUM_BIS");	
	}

	public String get_LADE_DATUM_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LADE_DATUM_BIS");
	}

	public String get_LADE_DATUM_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LADE_DATUM_BIS",cNotNullValue);
	}

	public String get_LADE_DATUM_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LADE_DATUM_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LADE_DATUM_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LADE_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LADE_DATUM_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LADE_DATUM_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADE_DATUM_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADE_DATUM_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADE_DATUM_BIS", calNewValueFormated);
	}
		public String get_LADE_DATUM_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("LADE_DATUM_VON");
	}

	public String get_LADE_DATUM_VON_cF() throws myException
	{
		return this.get_FormatedValue("LADE_DATUM_VON");	
	}

	public String get_LADE_DATUM_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LADE_DATUM_VON");
	}

	public String get_LADE_DATUM_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LADE_DATUM_VON",cNotNullValue);
	}

	public String get_LADE_DATUM_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LADE_DATUM_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LADE_DATUM_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LADE_DATUM_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LADE_DATUM_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LADE_DATUM_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADE_DATUM_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADE_DATUM_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LADE_DATUM_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LADE_DATUM_VON", calNewValueFormated);
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
		public String get_SORTIERUNG_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("SORTIERUNG_FP");
	}

	public String get_SORTIERUNG_FP_cF() throws myException
	{
		return this.get_FormatedValue("SORTIERUNG_FP");	
	}

	public String get_SORTIERUNG_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SORTIERUNG_FP");
	}

	public String get_SORTIERUNG_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SORTIERUNG_FP",cNotNullValue);
	}

	public String get_SORTIERUNG_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SORTIERUNG_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SORTIERUNG_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SORTIERUNG_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SORTIERUNG_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SORTIERUNG_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SORTIERUNG_FP", calNewValueFormated);
	}
		public Long get_SORTIERUNG_FP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("SORTIERUNG_FP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_SORTIERUNG_FP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("SORTIERUNG_FP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_SORTIERUNG_FP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("SORTIERUNG_FP").getDoubleValue();
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
	public String get_SORTIERUNG_FP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTIERUNG_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_SORTIERUNG_FP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_SORTIERUNG_FP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_SORTIERUNG_FP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("SORTIERUNG_FP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_SORTIERUNG_FP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("SORTIERUNG_FP").getBigDecimalValue();
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
	
	
	public String get_TAETIGKEIT_FP_cUF() throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT_FP");
	}

	public String get_TAETIGKEIT_FP_cF() throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT_FP");	
	}

	public String get_TAETIGKEIT_FP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TAETIGKEIT_FP");
	}

	public String get_TAETIGKEIT_FP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TAETIGKEIT_FP",cNotNullValue);
	}

	public String get_TAETIGKEIT_FP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TAETIGKEIT_FP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TAETIGKEIT_FP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TAETIGKEIT_FP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TAETIGKEIT_FP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TAETIGKEIT_FP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TAETIGKEIT_FP", calNewValueFormated);
	}
		public String get_ZEITSTEMPEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZEITSTEMPEL");
	}

	public String get_ZEITSTEMPEL_cF() throws myException
	{
		return this.get_FormatedValue("ZEITSTEMPEL");	
	}

	public String get_ZEITSTEMPEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZEITSTEMPEL");
	}

	public String get_ZEITSTEMPEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZEITSTEMPEL",cNotNullValue);
	}

	public String get_ZEITSTEMPEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZEITSTEMPEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZEITSTEMPEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZEITSTEMPEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZEITSTEMPEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZEITSTEMPEL", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ABLADE_DATUM_BIS",MyRECORD.DATATYPES.DATE);
	put("ABLADE_DATUM_VON",MyRECORD.DATATYPES.DATE);
	put("ANRUFDATUM_FP",MyRECORD.DATATYPES.DATE);
	put("ANRUFER_FP",MyRECORD.DATATYPES.TEXT);
	put("ANZAHL_CONTAINER_FP",MyRECORD.DATATYPES.NUMBER);
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_SACHBEARBEITER",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_START_FP",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_ZIEL_FP",MyRECORD.DATATYPES.TEXT);
	put("BEWEGUNG_TYP",MyRECORD.DATATYPES.TEXT);
	put("DAT_FAHRPLAN_FP",MyRECORD.DATATYPES.DATE);
	put("DAT_VORGEMERKT_ENDE_FP",MyRECORD.DATATYPES.DATE);
	put("DAT_VORGEMERKT_FP",MyRECORD.DATATYPES.DATE);
	put("DELETED",MyRECORD.DATATYPES.YESNO);
	put("DEL_DATE",MyRECORD.DATATYPES.DATE);
	put("DEL_GRUND",MyRECORD.DATATYPES.TEXT);
	put("DEL_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("EAN_CODE_FP",MyRECORD.DATATYPES.TEXT);
	put("ERFASSER_FP",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FAHRER_FP",MyRECORD.DATATYPES.TEXT);
	put("FAHRPLANGRUPPE_FP",MyRECORD.DATATYPES.NUMBER);
	put("FAHRPLAN_SATZ",MyRECORD.DATATYPES.YESNO);
	put("FAHRT_ANFANG_FP",MyRECORD.DATATYPES.TEXT);
	put("FAHRT_ENDE_FP",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_BEWEGUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_CONTAINERTYP_FP",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MASCHINEN_ANH_FP",MyRECORD.DATATYPES.NUMBER);
	put("ID_MASCHINEN_LKW_FP",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_NG",MyRECORD.DATATYPES.NUMBER);
	put("IST_GEPLANT_FP",MyRECORD.DATATYPES.YESNO);
	put("IST_LAGERBUCHUNG_ALT",MyRECORD.DATATYPES.YESNO);
	put("LADE_DATUM_BIS",MyRECORD.DATATYPES.DATE);
	put("LADE_DATUM_VON",MyRECORD.DATATYPES.DATE);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SORTIERUNG_FP",MyRECORD.DATATYPES.NUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TAETIGKEIT_FP",MyRECORD.DATATYPES.TEXT);
	put("ZEITSTEMPEL",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_BEWEGUNG.HM_FIELDS;	
	}

}
