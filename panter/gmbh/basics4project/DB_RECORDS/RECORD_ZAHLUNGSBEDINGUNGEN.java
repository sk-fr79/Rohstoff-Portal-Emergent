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

public class RECORD_ZAHLUNGSBEDINGUNGEN extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ZAHLUNGSBEDINGUNGEN";
    public static String IDFIELD   = "ID_ZAHLUNGSBEDINGUNGEN";
    

	//erzeugen eines RECORDNEW_JT_ZAHLUNGSBEDINGUNGEN - felds
	private RECORDNEW_ZAHLUNGSBEDINGUNGEN   recNEW = null;

    private _TAB  tab = _TAB.zahlungsbedingungen;  



	public RECORD_ZAHLUNGSBEDINGUNGEN() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");
	}


	public RECORD_ZAHLUNGSBEDINGUNGEN(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");
	}



	public RECORD_ZAHLUNGSBEDINGUNGEN(RECORD_ZAHLUNGSBEDINGUNGEN recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZAHLUNGSBEDINGUNGEN.TABLENAME);
	}


	//2014-04-03
	public RECORD_ZAHLUNGSBEDINGUNGEN(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZAHLUNGSBEDINGUNGEN.TABLENAME);
	}




	public RECORD_ZAHLUNGSBEDINGUNGEN(long lID_Unformated) throws myException
	{
		super("JT_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZAHLUNGSBEDINGUNGEN.TABLENAME);
	}

	public RECORD_ZAHLUNGSBEDINGUNGEN(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN", "ID_ZAHLUNGSBEDINGUNGEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZAHLUNGSBEDINGUNGEN.TABLENAME);
	}
	
	

	public RECORD_ZAHLUNGSBEDINGUNGEN(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ZAHLUNGSBEDINGUNGEN","ID_ZAHLUNGSBEDINGUNGEN",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZAHLUNGSBEDINGUNGEN.TABLENAME);

	}


	public RECORD_ZAHLUNGSBEDINGUNGEN(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ZAHLUNGSBEDINGUNGEN");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN", "ID_ZAHLUNGSBEDINGUNGEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ZAHLUNGSBEDINGUNGEN.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ZAHLUNGSBEDINGUNGEN();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ZAHLUNGSBEDINGUNGEN.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ZAHLUNGSBEDINGUNGEN";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ZAHLUNGSBEDINGUNGEN_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ZAHLUNGSBEDINGUNGEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ZAHLUNGSBEDINGUNGEN", bibE2.cTO(), "ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ZAHLUNGSBEDINGUNGEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ZAHLUNGSBEDINGUNGEN", bibE2.cTO(), "ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
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
			if (S.isFull(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN", "ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ZAHLUNGSBEDINGUNGEN get_RECORDNEW_ZAHLUNGSBEDINGUNGEN() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ZAHLUNGSBEDINGUNGEN();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ZAHLUNGSBEDINGUNGEN(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance() throws myException {
		return new RECORD_ZAHLUNGSBEDINGUNGEN();
	}
	
	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ZAHLUNGSBEDINGUNGEN(Conn);
    }

	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance(RECORD_ZAHLUNGSBEDINGUNGEN recordOrig) {
		return new RECORD_ZAHLUNGSBEDINGUNGEN(recordOrig);
    }

	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ZAHLUNGSBEDINGUNGEN(lID_Unformated);
    }

	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ZAHLUNGSBEDINGUNGEN(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ZAHLUNGSBEDINGUNGEN(lID_Unformated, Conn);
	}

	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ZAHLUNGSBEDINGUNGEN(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ZAHLUNGSBEDINGUNGEN create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ZAHLUNGSBEDINGUNGEN(recordOrig);	    
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
    public RECORD_ZAHLUNGSBEDINGUNGEN build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF());
      }
      //return new RECORD_ZAHLUNGSBEDINGUNGEN(this.get_cSQL_4_Build());
      RECORD_ZAHLUNGSBEDINGUNGEN rec = new RECORD_ZAHLUNGSBEDINGUNGEN();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ZAHLUNGSBEDINGUNGEN
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ZAHLUNGSBEDINGUNGEN-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ZAHLUNGSBEDINGUNGEN get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ZAHLUNGSBEDINGUNGEN_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ZAHLUNGSBEDINGUNGEN  recNew = new RECORDNEW_ZAHLUNGSBEDINGUNGEN();
		
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
    public RECORD_ZAHLUNGSBEDINGUNGEN set_recordNew(RECORDNEW_ZAHLUNGSBEDINGUNGEN recnew) throws myException {
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
	
	



		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen = null ;




		private RECLIST_ADRESSE DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen_vk = null ;




		private RECLIST_ARTIKELBEZ_LIEF DOWN_RECLIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen = null ;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_zahlungsbedingungen = null ;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_zahlungsbedingungen = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_id_zahlungsbedingungen = null ;




		private RECLIST_VKOPF_RG DOWN_RECLIST_VKOPF_RG_id_zahlungsbedingungen = null ;




		private RECLIST_VKOPF_STD DOWN_RECLIST_VKOPF_STD_id_zahlungsbedingungen = null ;




		private RECLIST_VKOPF_TPA DOWN_RECLIST_VKOPF_TPA_id_zahlungsbedingungen = null ;




		private RECLIST_VPOS_KON DOWN_RECLIST_VPOS_KON_id_zahlungsbedingungen = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_zahlungsbedingungen = null ;




		private RECLIST_VPOS_RG_VL DOWN_RECLIST_VPOS_RG_VL_id_zahlungsbedingungen = null ;




		private RECLIST_VPOS_STD DOWN_RECLIST_VPOS_STD_id_zahlungsbedingungen = null ;




		private RECLIST_VPOS_TPA DOWN_RECLIST_VPOS_TPA_id_zahlungsbedingungen = null ;






	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN_VK 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_zahlungsbedingungen_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen_vk==null)
		{
			this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen_vk = new RECLIST_ADRESSE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ZAHLUNGSBEDINGUNGEN_VK="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_ADRESSE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen_vk;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN_VK 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE get_DOWN_RECORD_LIST_ADRESSE_id_zahlungsbedingungen_vk(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen_vk==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ZAHLUNGSBEDINGUNGEN_VK="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen_vk = new RECLIST_ADRESSE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_id_zahlungsbedingungen_vk;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen = new RECLIST_ARTIKELBEZ_LIEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_ARTIKELBEZ_LIEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen = new RECLIST_ARTIKELBEZ_LIEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_zahlungsbedingungen = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_zahlungsbedingungen = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_zahlungsbedingungen = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_zahlungsbedingungen = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_id_zahlungsbedingungen = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_id_zahlungsbedingungen = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VKOPF_RG_id_zahlungsbedingungen = new RECLIST_VKOPF_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VKOPF_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_RG get_DOWN_RECORD_LIST_VKOPF_RG_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_RG_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_RG_id_zahlungsbedingungen = new RECLIST_VKOPF_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_RG_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VKOPF_STD_id_zahlungsbedingungen = new RECLIST_VKOPF_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VKOPF_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_STD get_DOWN_RECORD_LIST_VKOPF_STD_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_STD_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_STD_id_zahlungsbedingungen = new RECLIST_VKOPF_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_STD_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VKOPF_TPA_id_zahlungsbedingungen = new RECLIST_VKOPF_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VKOPF_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_TPA get_DOWN_RECORD_LIST_VKOPF_TPA_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_TPA_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_TPA_id_zahlungsbedingungen = new RECLIST_VKOPF_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_TPA_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VPOS_KON_id_zahlungsbedingungen = new RECLIST_VPOS_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VPOS_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_id_zahlungsbedingungen = new RECLIST_VPOS_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_zahlungsbedingungen = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_zahlungsbedingungen = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VPOS_RG_VL_id_zahlungsbedingungen = new RECLIST_VPOS_RG_VL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VPOS_RG_VL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_VL_id_zahlungsbedingungen = new RECLIST_VPOS_RG_VL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VPOS_STD_id_zahlungsbedingungen = new RECLIST_VPOS_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VPOS_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_STD_id_zahlungsbedingungen = new RECLIST_VPOS_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_zahlungsbedingungen;
	}


	





	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_zahlungsbedingungen==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_id_zahlungsbedingungen = new RECLIST_VPOS_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()+" ORDER BY ID_VPOS_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_zahlungsbedingungen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN 
	 * Falls keine get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_zahlungsbedingungen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_zahlungsbedingungen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_id_zahlungsbedingungen = new RECLIST_VPOS_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_zahlungsbedingungen;
	}


	

	public static String FIELD__BEMERKUNGEN = "BEMERKUNGEN";
	public static String FIELD__BEZEICHNUNG = "BEZEICHNUNG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FIXMONAT = "FIXMONAT";
	public static String FIELD__FIXTAG = "FIXTAG";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_ZAHLUNGSBEDINGUNGEN = "ID_ZAHLUNGSBEDINGUNGEN";
	public static String FIELD__IST_STANDARD = "IST_STANDARD";
	public static String FIELD__KURZBEZEICHNUNG = "KURZBEZEICHNUNG";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SKONTO_PROZENT = "SKONTO_PROZENT";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__ZAHLDAT_CALC_RECHDAT = "ZAHLDAT_CALC_RECHDAT";
	public static String FIELD__ZAHLTAGE = "ZAHLTAGE";


	public String get_BEMERKUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNGEN");
	}

	public String get_BEMERKUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNGEN");	
	}

	public String get_BEMERKUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNGEN");
	}

	public String get_BEMERKUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNGEN",cNotNullValue);
	}

	public String get_BEMERKUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNGEN", calNewValueFormated);
	}
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
	
	
	public String get_ZAHLDAT_CALC_RECHDAT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLDAT_CALC_RECHDAT");
	}

	public String get_ZAHLDAT_CALC_RECHDAT_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLDAT_CALC_RECHDAT");	
	}

	public String get_ZAHLDAT_CALC_RECHDAT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLDAT_CALC_RECHDAT");
	}

	public String get_ZAHLDAT_CALC_RECHDAT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLDAT_CALC_RECHDAT",cNotNullValue);
	}

	public String get_ZAHLDAT_CALC_RECHDAT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLDAT_CALC_RECHDAT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLDAT_CALC_RECHDAT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLDAT_CALC_RECHDAT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLDAT_CALC_RECHDAT", calNewValueFormated);
	}
		public boolean is_ZAHLDAT_CALC_RECHDAT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZAHLDAT_CALC_RECHDAT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ZAHLDAT_CALC_RECHDAT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ZAHLDAT_CALC_RECHDAT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
	
	


	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("BEMERKUNGEN",MyRECORD.DATATYPES.TEXT);
	put("BEZEICHNUNG",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FIXMONAT",MyRECORD.DATATYPES.NUMBER);
	put("FIXTAG",MyRECORD.DATATYPES.NUMBER);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZAHLUNGSBEDINGUNGEN",MyRECORD.DATATYPES.NUMBER);
	put("IST_STANDARD",MyRECORD.DATATYPES.YESNO);
	put("KURZBEZEICHNUNG",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SKONTO_PROZENT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("ZAHLDAT_CALC_RECHDAT",MyRECORD.DATATYPES.YESNO);
	put("ZAHLTAGE",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ZAHLUNGSBEDINGUNGEN.HM_FIELDS;	
	}

}
