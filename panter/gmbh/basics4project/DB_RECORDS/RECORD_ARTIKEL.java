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

public class RECORD_ARTIKEL extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ARTIKEL";
    public static String IDFIELD   = "ID_ARTIKEL";
    

	//erzeugen eines RECORDNEW_JT_ARTIKEL - felds
	private RECORDNEW_ARTIKEL   recNEW = null;

    private _TAB  tab = _TAB.artikel;  



	public RECORD_ARTIKEL() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ARTIKEL");
	}


	public RECORD_ARTIKEL(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ARTIKEL");
	}



	public RECORD_ARTIKEL(RECORD_ARTIKEL recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ARTIKEL");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL.TABLENAME);
	}


	//2014-04-03
	public RECORD_ARTIKEL(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ARTIKEL");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL.TABLENAME);
	}




	public RECORD_ARTIKEL(long lID_Unformated) throws myException
	{
		super("JT_ARTIKEL","ID_ARTIKEL",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ARTIKEL");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL.TABLENAME);
	}

	public RECORD_ARTIKEL(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ARTIKEL");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL", "ID_ARTIKEL="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL.TABLENAME);
	}
	
	

	public RECORD_ARTIKEL(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ARTIKEL","ID_ARTIKEL",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ARTIKEL");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL.TABLENAME);

	}


	public RECORD_ARTIKEL(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ARTIKEL");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL", "ID_ARTIKEL="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ARTIKEL();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ARTIKEL.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ARTIKEL";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ARTIKEL_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ARTIKEL_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ARTIKEL was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ARTIKEL", bibE2.cTO(), "ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ARTIKEL was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ARTIKEL", bibE2.cTO(), "ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
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
			if (S.isFull(this.get_ID_ARTIKEL_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL", "ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ARTIKEL get_RECORDNEW_ARTIKEL() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ARTIKEL();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ARTIKEL(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ARTIKEL create_Instance() throws myException {
		return new RECORD_ARTIKEL();
	}
	
	public static RECORD_ARTIKEL create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ARTIKEL(Conn);
    }

	public static RECORD_ARTIKEL create_Instance(RECORD_ARTIKEL recordOrig) {
		return new RECORD_ARTIKEL(recordOrig);
    }

	public static RECORD_ARTIKEL create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ARTIKEL(lID_Unformated);
    }

	public static RECORD_ARTIKEL create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ARTIKEL(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ARTIKEL create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ARTIKEL(lID_Unformated, Conn);
	}

	public static RECORD_ARTIKEL create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ARTIKEL(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ARTIKEL create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ARTIKEL(recordOrig);	    
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
    public RECORD_ARTIKEL build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF());
      }
      //return new RECORD_ARTIKEL(this.get_cSQL_4_Build());
      RECORD_ARTIKEL rec = new RECORD_ARTIKEL();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ARTIKEL
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ARTIKEL-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ARTIKEL get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ARTIKEL_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ARTIKEL  recNew = new RECORDNEW_ARTIKEL();
		
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
    public RECORD_ARTIKEL set_recordNew(RECORDNEW_ARTIKEL recnew) throws myException {
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
	
	



		private RECLIST_ADRESSE_AQU_REL_KD_SORTE DOWN_RECLIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel = null ;




		private RECLIST_ARTIKEL_BEZ DOWN_RECLIST_ARTIKEL_BEZ_id_artikel = null ;




		private RECLIST_ARTIKEL_DATENBLATT DOWN_RECLIST_ARTIKEL_DATENBLATT_id_artikel = null ;




		private RECLIST_ARTIKEL_UMWANDLUNG DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_quell = null ;




		private RECLIST_ARTIKEL_UMWANDLUNG DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel = null ;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel = null ;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_artikel = null ;




		private RECLIST_BG_AUSWERT DOWN_RECLIST_BG_AUSWERT_id_artikel = null ;




		private RECLIST_BORDERCROSSING_ARTIKEL DOWN_RECLIST_BORDERCROSSING_ARTIKEL_id_artikel = null ;




		private RECLIST_CONTAINER_SORTEN DOWN_RECLIST_CONTAINER_SORTEN_id_artikel = null ;




		private RECLIST_CONTAINER_ZYKLUS DOWN_RECLIST_CONTAINER_ZYKLUS_id_artikel = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_artikel = null ;




		private RECLIST_FAHRPLANPOS DOWN_RECLIST_FAHRPLANPOS_id_artikel = null ;




		private RECLIST_FAHRTENVARIANTEN DOWN_RECLIST_FAHRTENVARIANTEN_id_artikel = null ;




		private RECLIST_FIBU_KONTENREGEL DOWN_RECLIST_FIBU_KONTENREGEL_id_artikel = null ;




		private RECLIST_FUHREN_RECHNUNGEN DOWN_RECLIST_FUHREN_RECHNUNGEN_id_artikel = null ;




		private RECLIST_KORRE_ABZUEGE DOWN_RECLIST_KORRE_ABZUEGE_id_artikel = null ;




		private RECLIST_LAGER_KONTO DOWN_RECLIST_LAGER_KONTO_id_artikel_sorte = null ;




		private RECLIST_LAND_RC_SORTEN DOWN_RECLIST_LAND_RC_SORTEN_id_artikel = null ;




		private RECLIST_MAT_SPEZ DOWN_RECLIST_MAT_SPEZ_id_artikel = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_fix_id_artikel = null ;




		private RECLIST_VPOS_KON DOWN_RECLIST_VPOS_KON_id_artikel = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_artikel = null ;




		private RECLIST_VPOS_RG_VL DOWN_RECLIST_VPOS_RG_VL_id_artikel = null ;




		private RECLIST_VPOS_STD DOWN_RECLIST_VPOS_STD_id_artikel = null ;




		private RECLIST_VPOS_TPA DOWN_RECLIST_VPOS_TPA_id_artikel = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_artikel_sorte = null ;




		private RECORD_ARTIKEL_GRUPPE UP_RECORD_ARTIKEL_GRUPPE_id_artikel_gruppe = null;




		private RECORD_ARTIKEL_GRUPPE_FIBU UP_RECORD_ARTIKEL_GRUPPE_FIBU_id_artikel_gruppe_fibu = null;




		private RECORD_EAK_CODE UP_RECORD_EAK_CODE_id_eak_code = null;




		private RECORD_EAK_CODE UP_RECORD_EAK_CODE_id_eak_code_ex_mandant = null;




		private RECORD_EINHEIT UP_RECORD_EINHEIT_id_einheit = null;




		private RECORD_EINHEIT UP_RECORD_EINHEIT_id_einheit_preis = null;




		private RECORD_ZOLLTARIFNUMMER UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer = null;






	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_AQU_REL_KD_SORTE get_DOWN_RECORD_LIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel==null)
		{
			this.DOWN_RECLIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel = new RECLIST_ADRESSE_AQU_REL_KD_SORTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_AQU_REL_KD_SORTE WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_ADRESSE_AQU_REL_KD_SORTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_AQU_REL_KD_SORTE get_DOWN_RECORD_LIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_AQU_REL_KD_SORTE WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel = new RECLIST_ADRESSE_AQU_REL_KD_SORTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_AQU_REL_KD_SORTE_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_BEZ get_DOWN_RECORD_LIST_ARTIKEL_BEZ_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_BEZ_id_artikel==null)
		{
			this.DOWN_RECLIST_ARTIKEL_BEZ_id_artikel = new RECLIST_ARTIKEL_BEZ (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_ARTIKEL_BEZ",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_BEZ_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_BEZ get_DOWN_RECORD_LIST_ARTIKEL_BEZ_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_BEZ_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_BEZ_id_artikel = new RECLIST_ARTIKEL_BEZ (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_BEZ_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_artikel==null)
		{
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_artikel = new RECLIST_ARTIKEL_DATENBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_ARTIKEL_DATENBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_artikel = new RECLIST_ARTIKEL_DATENBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL_QUELL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_UMWANDLUNG get_DOWN_RECORD_LIST_ARTIKEL_UMWANDLUNG_id_artikel_quell() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_quell==null)
		{
			this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_quell = new RECLIST_ARTIKEL_UMWANDLUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_UMWANDLUNG WHERE ID_ARTIKEL_QUELL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_ARTIKEL_UMWANDLUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_quell;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_QUELL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_UMWANDLUNG get_DOWN_RECORD_LIST_ARTIKEL_UMWANDLUNG_id_artikel_quell(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_quell==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_UMWANDLUNG WHERE ID_ARTIKEL_QUELL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_quell = new RECLIST_ARTIKEL_UMWANDLUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_quell;
	}


	





	/**
	 * References the Field ID_ARTIKEL_ZIEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_UMWANDLUNG get_DOWN_RECORD_LIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel==null)
		{
			this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel = new RECLIST_ARTIKEL_UMWANDLUNG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_UMWANDLUNG WHERE ID_ARTIKEL_ZIEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_ARTIKEL_UMWANDLUNG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_ZIEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_UMWANDLUNG get_DOWN_RECORD_LIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_UMWANDLUNG WHERE ID_ARTIKEL_ZIEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel = new RECLIST_ARTIKEL_UMWANDLUNG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_UMWANDLUNG_id_artikel_ziel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_artikel==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_artikel = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_artikel = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_AUSWERT get_DOWN_RECORD_LIST_BG_AUSWERT_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_AUSWERT_id_artikel==null)
		{
			this.DOWN_RECLIST_BG_AUSWERT_id_artikel = new RECLIST_BG_AUSWERT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_AUSWERT WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_BG_AUSWERT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_AUSWERT_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_AUSWERT get_DOWN_RECORD_LIST_BG_AUSWERT_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_AUSWERT_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_AUSWERT WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_AUSWERT_id_artikel = new RECLIST_BG_AUSWERT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_AUSWERT_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING_ARTIKEL get_DOWN_RECORD_LIST_BORDERCROSSING_ARTIKEL_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_ARTIKEL_id_artikel==null)
		{
			this.DOWN_RECLIST_BORDERCROSSING_ARTIKEL_id_artikel = new RECLIST_BORDERCROSSING_ARTIKEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING_ARTIKEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_BORDERCROSSING_ARTIKEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_ARTIKEL_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BORDERCROSSING_ARTIKEL get_DOWN_RECORD_LIST_BORDERCROSSING_ARTIKEL_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BORDERCROSSING_ARTIKEL_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BORDERCROSSING_ARTIKEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BORDERCROSSING_ARTIKEL_id_artikel = new RECLIST_BORDERCROSSING_ARTIKEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BORDERCROSSING_ARTIKEL_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_SORTEN get_DOWN_RECORD_LIST_CONTAINER_SORTEN_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_SORTEN_id_artikel==null)
		{
			this.DOWN_RECLIST_CONTAINER_SORTEN_id_artikel = new RECLIST_CONTAINER_SORTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_SORTEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_CONTAINER_SORTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_SORTEN_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_SORTEN get_DOWN_RECORD_LIST_CONTAINER_SORTEN_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_SORTEN_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_SORTEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_SORTEN_id_artikel = new RECLIST_CONTAINER_SORTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_SORTEN_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_artikel==null)
		{
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_artikel = new RECLIST_CONTAINER_ZYKLUS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_CONTAINER_ZYKLUS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_CONTAINER_ZYKLUS get_DOWN_RECORD_LIST_CONTAINER_ZYKLUS_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_CONTAINER_ZYKLUS WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_artikel = new RECLIST_CONTAINER_ZYKLUS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_CONTAINER_ZYKLUS_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_artikel==null)
		{
			this.DOWN_RECLIST_FAHRPLANPOS_id_artikel = new RECLIST_FAHRPLANPOS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_FAHRPLANPOS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRPLANPOS get_DOWN_RECORD_LIST_FAHRPLANPOS_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRPLANPOS_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRPLANPOS WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRPLANPOS_id_artikel = new RECLIST_FAHRPLANPOS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRPLANPOS_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRTENVARIANTEN get_DOWN_RECORD_LIST_FAHRTENVARIANTEN_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRTENVARIANTEN_id_artikel==null)
		{
			this.DOWN_RECLIST_FAHRTENVARIANTEN_id_artikel = new RECLIST_FAHRTENVARIANTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FAHRTENVARIANTEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_FAHRTENVARIANTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRTENVARIANTEN_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FAHRTENVARIANTEN get_DOWN_RECORD_LIST_FAHRTENVARIANTEN_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FAHRTENVARIANTEN_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FAHRTENVARIANTEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FAHRTENVARIANTEN_id_artikel = new RECLIST_FAHRTENVARIANTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FAHRTENVARIANTEN_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_id_artikel==null)
		{
			this.DOWN_RECLIST_FIBU_KONTENREGEL_id_artikel = new RECLIST_FIBU_KONTENREGEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_FIBU_KONTENREGEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FIBU_KONTENREGEL get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FIBU_KONTENREGEL_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FIBU_KONTENREGEL_id_artikel = new RECLIST_FIBU_KONTENREGEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FIBU_KONTENREGEL_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_artikel==null)
		{
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_artikel = new RECLIST_FUHREN_RECHNUNGEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_FUHREN_RECHNUNGEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_artikel = new RECLIST_FUHREN_RECHNUNGEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KORRE_ABZUEGE get_DOWN_RECORD_LIST_KORRE_ABZUEGE_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KORRE_ABZUEGE_id_artikel==null)
		{
			this.DOWN_RECLIST_KORRE_ABZUEGE_id_artikel = new RECLIST_KORRE_ABZUEGE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_KORRE_ABZUEGE WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_KORRE_ABZUEGE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_KORRE_ABZUEGE_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_KORRE_ABZUEGE get_DOWN_RECORD_LIST_KORRE_ABZUEGE_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_KORRE_ABZUEGE_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_KORRE_ABZUEGE WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_KORRE_ABZUEGE_id_artikel = new RECLIST_KORRE_ABZUEGE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_KORRE_ABZUEGE_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL_SORTE 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_KONTO get_DOWN_RECORD_LIST_LAGER_KONTO_id_artikel_sorte() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_KONTO_id_artikel_sorte==null)
		{
			this.DOWN_RECLIST_LAGER_KONTO_id_artikel_sorte = new RECLIST_LAGER_KONTO (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_ARTIKEL_SORTE="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_LAGER_KONTO",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_KONTO_id_artikel_sorte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_SORTE 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_KONTO get_DOWN_RECORD_LIST_LAGER_KONTO_id_artikel_sorte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_KONTO_id_artikel_sorte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE ID_ARTIKEL_SORTE="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_KONTO_id_artikel_sorte = new RECLIST_LAGER_KONTO (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_KONTO_id_artikel_sorte;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAND_RC_SORTEN get_DOWN_RECORD_LIST_LAND_RC_SORTEN_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAND_RC_SORTEN_id_artikel==null)
		{
			this.DOWN_RECLIST_LAND_RC_SORTEN_id_artikel = new RECLIST_LAND_RC_SORTEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAND_RC_SORTEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_LAND_RC_SORTEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAND_RC_SORTEN_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAND_RC_SORTEN get_DOWN_RECORD_LIST_LAND_RC_SORTEN_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAND_RC_SORTEN_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAND_RC_SORTEN WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAND_RC_SORTEN_id_artikel = new RECLIST_LAND_RC_SORTEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAND_RC_SORTEN_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAT_SPEZ get_DOWN_RECORD_LIST_MAT_SPEZ_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAT_SPEZ_id_artikel==null)
		{
			this.DOWN_RECLIST_MAT_SPEZ_id_artikel = new RECLIST_MAT_SPEZ (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAT_SPEZ WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_MAT_SPEZ",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAT_SPEZ_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAT_SPEZ get_DOWN_RECORD_LIST_MAT_SPEZ_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAT_SPEZ_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAT_SPEZ WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAT_SPEZ_id_artikel = new RECLIST_MAT_SPEZ (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAT_SPEZ_id_artikel;
	}


	





	/**
	 * References the Field FIX_ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_fix_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_fix_id_artikel==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_fix_id_artikel = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE FIX_ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_fix_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field FIX_ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_fix_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_fix_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE FIX_ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_fix_id_artikel = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_fix_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_artikel==null)
		{
			this.DOWN_RECLIST_VPOS_KON_id_artikel = new RECLIST_VPOS_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VPOS_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_id_artikel = new RECLIST_VPOS_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_artikel==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_artikel = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_artikel = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_artikel==null)
		{
			this.DOWN_RECLIST_VPOS_RG_VL_id_artikel = new RECLIST_VPOS_RG_VL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VPOS_RG_VL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_VL_id_artikel = new RECLIST_VPOS_RG_VL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_artikel==null)
		{
			this.DOWN_RECLIST_VPOS_STD_id_artikel = new RECLIST_VPOS_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VPOS_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_STD_id_artikel = new RECLIST_VPOS_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_artikel==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_id_artikel = new RECLIST_VPOS_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VPOS_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_id_artikel = new RECLIST_VPOS_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL_SORTE 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_artikel_sorte() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_artikel_sorte==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_artikel_sorte = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ARTIKEL_SORTE="+this.get_ID_ARTIKEL_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_artikel_sorte;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_SORTE 
	 * Falls keine get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_artikel_sorte(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_artikel_sorte==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ARTIKEL_SORTE="+this.get_ID_ARTIKEL_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_artikel_sorte = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_artikel_sorte;
	}


	





	
	/**
	 * References the Field ID_ARTIKEL_GRUPPE
	 * Falls keine this.get_ID_ARTIKEL_GRUPPE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_GRUPPE get_UP_RECORD_ARTIKEL_GRUPPE_id_artikel_gruppe() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_GRUPPE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_GRUPPE_id_artikel_gruppe==null)
		{
			this.UP_RECORD_ARTIKEL_GRUPPE_id_artikel_gruppe = new RECORD_ARTIKEL_GRUPPE(this.get_ID_ARTIKEL_GRUPPE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_GRUPPE_id_artikel_gruppe;
	}
	





	
	/**
	 * References the Field ID_ARTIKEL_GRUPPE_FIBU
	 * Falls keine this.get_ID_ARTIKEL_GRUPPE_FIBU_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_GRUPPE_FIBU get_UP_RECORD_ARTIKEL_GRUPPE_FIBU_id_artikel_gruppe_fibu() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_GRUPPE_FIBU_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_GRUPPE_FIBU_id_artikel_gruppe_fibu==null)
		{
			this.UP_RECORD_ARTIKEL_GRUPPE_FIBU_id_artikel_gruppe_fibu = new RECORD_ARTIKEL_GRUPPE_FIBU(this.get_ID_ARTIKEL_GRUPPE_FIBU_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_GRUPPE_FIBU_id_artikel_gruppe_fibu;
	}
	





	
	/**
	 * References the Field ID_EAK_CODE
	 * Falls keine this.get_ID_EAK_CODE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_EAK_CODE get_UP_RECORD_EAK_CODE_id_eak_code() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_EAK_CODE_id_eak_code==null)
		{
			this.UP_RECORD_EAK_CODE_id_eak_code = new RECORD_EAK_CODE(this.get_ID_EAK_CODE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_EAK_CODE_id_eak_code;
	}
	





	
	/**
	 * References the Field ID_EAK_CODE_EX_MANDANT
	 * Falls keine this.get_ID_EAK_CODE_EX_MANDANT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_EAK_CODE get_UP_RECORD_EAK_CODE_id_eak_code_ex_mandant() throws myException
	{
		if (S.isEmpty(this.get_ID_EAK_CODE_EX_MANDANT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_EAK_CODE_id_eak_code_ex_mandant==null)
		{
			this.UP_RECORD_EAK_CODE_id_eak_code_ex_mandant = new RECORD_EAK_CODE(this.get_ID_EAK_CODE_EX_MANDANT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_EAK_CODE_id_eak_code_ex_mandant;
	}
	





	
	/**
	 * References the Field ID_EINHEIT
	 * Falls keine this.get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_EINHEIT get_UP_RECORD_EINHEIT_id_einheit() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_EINHEIT_id_einheit==null)
		{
			this.UP_RECORD_EINHEIT_id_einheit = new RECORD_EINHEIT(this.get_ID_EINHEIT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_EINHEIT_id_einheit;
	}
	





	
	/**
	 * References the Field ID_EINHEIT_PREIS
	 * Falls keine this.get_ID_EINHEIT_PREIS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_EINHEIT get_UP_RECORD_EINHEIT_id_einheit_preis() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_PREIS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_EINHEIT_id_einheit_preis==null)
		{
			this.UP_RECORD_EINHEIT_id_einheit_preis = new RECORD_EINHEIT(this.get_ID_EINHEIT_PREIS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_EINHEIT_id_einheit_preis;
	}
	





	
	/**
	 * References the Field ID_ZOLLTARIFNUMMER
	 * Falls keine this.get_ID_ZOLLTARIFNUMMER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ZOLLTARIFNUMMER get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer() throws myException
	{
		if (S.isEmpty(this.get_ID_ZOLLTARIFNUMMER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer==null)
		{
			this.UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer = new RECORD_ZOLLTARIFNUMMER(this.get_ID_ZOLLTARIFNUMMER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer;
	}
	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__ANHANG7_3A_CODE = "ANHANG7_3A_CODE";
	public static String FIELD__ANHANG7_3A_TEXT = "ANHANG7_3A_TEXT";
	public static String FIELD__ANHANG7_3B_CODE = "ANHANG7_3B_CODE";
	public static String FIELD__ANHANG7_3B_TEXT = "ANHANG7_3B_TEXT";
	public static String FIELD__ANR1 = "ANR1";
	public static String FIELD__ARTBEZ1 = "ARTBEZ1";
	public static String FIELD__ARTBEZ2 = "ARTBEZ2";
	public static String FIELD__BASEL_CODE = "BASEL_CODE";
	public static String FIELD__BASEL_NOTIZ = "BASEL_NOTIZ";
	public static String FIELD__BEMERKUNG_INTERN = "BEMERKUNG_INTERN";
	public static String FIELD__DIENSTLEISTUNG = "DIENSTLEISTUNG";
	public static String FIELD__END_OF_WASTE = "END_OF_WASTE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EUCODE = "EUCODE";
	public static String FIELD__EUNOTIZ = "EUNOTIZ";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEFAHRGUT = "GEFAHRGUT";
	public static String FIELD__GENAUIGKEIT_MENGEN = "GENAUIGKEIT_MENGEN";
	public static String FIELD__ID_ARTIKEL = "ID_ARTIKEL";
	public static String FIELD__ID_ARTIKEL_GRUPPE = "ID_ARTIKEL_GRUPPE";
	public static String FIELD__ID_ARTIKEL_GRUPPE_FIBU = "ID_ARTIKEL_GRUPPE_FIBU";
	public static String FIELD__ID_EAK_CODE = "ID_EAK_CODE";
	public static String FIELD__ID_EAK_CODE_EX_MANDANT = "ID_EAK_CODE_EX_MANDANT";
	public static String FIELD__ID_EINHEIT = "ID_EINHEIT";
	public static String FIELD__ID_EINHEIT_PREIS = "ID_EINHEIT_PREIS";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_ZOLLTARIFNUMMER = "ID_ZOLLTARIFNUMMER";
	public static String FIELD__IST_LEERGUT = "IST_LEERGUT";
	public static String FIELD__IST_PRODUKT = "IST_PRODUKT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MENGENDIVISOR = "MENGENDIVISOR";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__ZOLLTARIFNOTIZ = "ZOLLTARIFNOTIZ";
	public static String FIELD__ZOLLTARIFNR = "ZOLLTARIFNR";


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
		public String get_ANHANG7_3A_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3A_CODE");
	}

	public String get_ANHANG7_3A_CODE_cF() throws myException
	{
		return this.get_FormatedValue("ANHANG7_3A_CODE");	
	}

	public String get_ANHANG7_3A_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANHANG7_3A_CODE");
	}

	public String get_ANHANG7_3A_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3A_CODE",cNotNullValue);
	}

	public String get_ANHANG7_3A_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANHANG7_3A_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANHANG7_3A_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3A_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANHANG7_3A_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3A_CODE", calNewValueFormated);
	}
		public String get_ANHANG7_3A_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3A_TEXT");
	}

	public String get_ANHANG7_3A_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("ANHANG7_3A_TEXT");	
	}

	public String get_ANHANG7_3A_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANHANG7_3A_TEXT");
	}

	public String get_ANHANG7_3A_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3A_TEXT",cNotNullValue);
	}

	public String get_ANHANG7_3A_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANHANG7_3A_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANHANG7_3A_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3A_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANHANG7_3A_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3A_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3A_TEXT", calNewValueFormated);
	}
		public String get_ANHANG7_3B_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3B_CODE");
	}

	public String get_ANHANG7_3B_CODE_cF() throws myException
	{
		return this.get_FormatedValue("ANHANG7_3B_CODE");	
	}

	public String get_ANHANG7_3B_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANHANG7_3B_CODE");
	}

	public String get_ANHANG7_3B_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3B_CODE",cNotNullValue);
	}

	public String get_ANHANG7_3B_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANHANG7_3B_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANHANG7_3B_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3B_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANHANG7_3B_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3B_CODE", calNewValueFormated);
	}
		public String get_ANHANG7_3B_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3B_TEXT");
	}

	public String get_ANHANG7_3B_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("ANHANG7_3B_TEXT");	
	}

	public String get_ANHANG7_3B_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANHANG7_3B_TEXT");
	}

	public String get_ANHANG7_3B_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANHANG7_3B_TEXT",cNotNullValue);
	}

	public String get_ANHANG7_3B_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANHANG7_3B_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANHANG7_3B_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANHANG7_3B_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANHANG7_3B_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHANG7_3B_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHANG7_3B_TEXT", calNewValueFormated);
	}
		public String get_ANR1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR1");
	}

	public String get_ANR1_cF() throws myException
	{
		return this.get_FormatedValue("ANR1");	
	}

	public String get_ANR1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR1");
	}

	public String get_ANR1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR1",cNotNullValue);
	}

	public String get_ANR1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR1", calNewValueFormated);
	}
		public String get_ARTBEZ1_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1");
	}

	public String get_ARTBEZ1_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ1");	
	}

	public String get_ARTBEZ1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ1");
	}

	public String get_ARTBEZ1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ1",cNotNullValue);
	}

	public String get_ARTBEZ1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ1", calNewValueFormated);
	}
		public String get_ARTBEZ2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2");
	}

	public String get_ARTBEZ2_cF() throws myException
	{
		return this.get_FormatedValue("ARTBEZ2");	
	}

	public String get_ARTBEZ2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ARTBEZ2");
	}

	public String get_ARTBEZ2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ARTBEZ2",cNotNullValue);
	}

	public String get_ARTBEZ2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ARTBEZ2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ARTBEZ2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ARTBEZ2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ARTBEZ2", calNewValueFormated);
	}
		public String get_BASEL_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BASEL_CODE");
	}

	public String get_BASEL_CODE_cF() throws myException
	{
		return this.get_FormatedValue("BASEL_CODE");	
	}

	public String get_BASEL_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BASEL_CODE");
	}

	public String get_BASEL_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BASEL_CODE",cNotNullValue);
	}

	public String get_BASEL_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BASEL_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BASEL_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BASEL_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BASEL_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_CODE", calNewValueFormated);
	}
		public String get_BASEL_NOTIZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("BASEL_NOTIZ");
	}

	public String get_BASEL_NOTIZ_cF() throws myException
	{
		return this.get_FormatedValue("BASEL_NOTIZ");	
	}

	public String get_BASEL_NOTIZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BASEL_NOTIZ");
	}

	public String get_BASEL_NOTIZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BASEL_NOTIZ",cNotNullValue);
	}

	public String get_BASEL_NOTIZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BASEL_NOTIZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BASEL_NOTIZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BASEL_NOTIZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASEL_NOTIZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASEL_NOTIZ", calNewValueFormated);
	}
		public String get_BEMERKUNG_INTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_INTERN");
	}

	public String get_BEMERKUNG_INTERN_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_INTERN");	
	}

	public String get_BEMERKUNG_INTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_INTERN");
	}

	public String get_BEMERKUNG_INTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_INTERN",cNotNullValue);
	}

	public String get_BEMERKUNG_INTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_INTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", calNewValueFormated);
	}
		public String get_DIENSTLEISTUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DIENSTLEISTUNG");
	}

	public String get_DIENSTLEISTUNG_cF() throws myException
	{
		return this.get_FormatedValue("DIENSTLEISTUNG");	
	}

	public String get_DIENSTLEISTUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DIENSTLEISTUNG");
	}

	public String get_DIENSTLEISTUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DIENSTLEISTUNG",cNotNullValue);
	}

	public String get_DIENSTLEISTUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DIENSTLEISTUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DIENSTLEISTUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DIENSTLEISTUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DIENSTLEISTUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DIENSTLEISTUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DIENSTLEISTUNG", calNewValueFormated);
	}
		public boolean is_DIENSTLEISTUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DIENSTLEISTUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DIENSTLEISTUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DIENSTLEISTUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_END_OF_WASTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("END_OF_WASTE");
	}

	public String get_END_OF_WASTE_cF() throws myException
	{
		return this.get_FormatedValue("END_OF_WASTE");	
	}

	public String get_END_OF_WASTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("END_OF_WASTE");
	}

	public String get_END_OF_WASTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("END_OF_WASTE",cNotNullValue);
	}

	public String get_END_OF_WASTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("END_OF_WASTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("END_OF_WASTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_END_OF_WASTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("END_OF_WASTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("END_OF_WASTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("END_OF_WASTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("END_OF_WASTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_END_OF_WASTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("END_OF_WASTE", calNewValueFormated);
	}
		public boolean is_END_OF_WASTE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_END_OF_WASTE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_END_OF_WASTE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_END_OF_WASTE_cUF_NN("N").equals("N"))
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
		public String get_EUCODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EUCODE");
	}

	public String get_EUCODE_cF() throws myException
	{
		return this.get_FormatedValue("EUCODE");	
	}

	public String get_EUCODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EUCODE");
	}

	public String get_EUCODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EUCODE",cNotNullValue);
	}

	public String get_EUCODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EUCODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EUCODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EUCODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EUCODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EUCODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUCODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUCODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUCODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUCODE", calNewValueFormated);
	}
		public String get_EUNOTIZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("EUNOTIZ");
	}

	public String get_EUNOTIZ_cF() throws myException
	{
		return this.get_FormatedValue("EUNOTIZ");	
	}

	public String get_EUNOTIZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EUNOTIZ");
	}

	public String get_EUNOTIZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EUNOTIZ",cNotNullValue);
	}

	public String get_EUNOTIZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EUNOTIZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EUNOTIZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EUNOTIZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EUNOTIZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUNOTIZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUNOTIZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EUNOTIZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EUNOTIZ", calNewValueFormated);
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
		public String get_GEFAHRGUT_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEFAHRGUT");
	}

	public String get_GEFAHRGUT_cF() throws myException
	{
		return this.get_FormatedValue("GEFAHRGUT");	
	}

	public String get_GEFAHRGUT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEFAHRGUT");
	}

	public String get_GEFAHRGUT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEFAHRGUT",cNotNullValue);
	}

	public String get_GEFAHRGUT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEFAHRGUT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEFAHRGUT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEFAHRGUT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEFAHRGUT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEFAHRGUT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEFAHRGUT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEFAHRGUT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEFAHRGUT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEFAHRGUT", calNewValueFormated);
	}
		public boolean is_GEFAHRGUT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GEFAHRGUT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GEFAHRGUT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GEFAHRGUT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GENAUIGKEIT_MENGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("GENAUIGKEIT_MENGEN");
	}

	public String get_GENAUIGKEIT_MENGEN_cF() throws myException
	{
		return this.get_FormatedValue("GENAUIGKEIT_MENGEN");	
	}

	public String get_GENAUIGKEIT_MENGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GENAUIGKEIT_MENGEN");
	}

	public String get_GENAUIGKEIT_MENGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GENAUIGKEIT_MENGEN",cNotNullValue);
	}

	public String get_GENAUIGKEIT_MENGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GENAUIGKEIT_MENGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GENAUIGKEIT_MENGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GENAUIGKEIT_MENGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GENAUIGKEIT_MENGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GENAUIGKEIT_MENGEN", calNewValueFormated);
	}
		public Long get_GENAUIGKEIT_MENGEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("GENAUIGKEIT_MENGEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_GENAUIGKEIT_MENGEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GENAUIGKEIT_MENGEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GENAUIGKEIT_MENGEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GENAUIGKEIT_MENGEN").getDoubleValue();
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
	public String get_GENAUIGKEIT_MENGEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GENAUIGKEIT_MENGEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GENAUIGKEIT_MENGEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GENAUIGKEIT_MENGEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GENAUIGKEIT_MENGEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GENAUIGKEIT_MENGEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GENAUIGKEIT_MENGEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GENAUIGKEIT_MENGEN").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL");
	}

	public String get_ID_ARTIKEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL");	
	}

	public String get_ID_ARTIKEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL");
	}

	public String get_ID_ARTIKEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL",cNotNullValue);
	}

	public String get_ID_ARTIKEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL").getDoubleValue();
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
	public String get_ID_ARTIKEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_GRUPPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_GRUPPE");
	}

	public String get_ID_ARTIKEL_GRUPPE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_GRUPPE");	
	}

	public String get_ID_ARTIKEL_GRUPPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_GRUPPE");
	}

	public String get_ID_ARTIKEL_GRUPPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_GRUPPE",cNotNullValue);
	}

	public String get_ID_ARTIKEL_GRUPPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_GRUPPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_GRUPPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_GRUPPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_GRUPPE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_GRUPPE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_GRUPPE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_GRUPPE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_GRUPPE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_GRUPPE").getDoubleValue();
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
	public String get_ID_ARTIKEL_GRUPPE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_GRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_GRUPPE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_GRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_GRUPPE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_GRUPPE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_GRUPPE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_GRUPPE").getBigDecimalValue();
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
	
	
	public String get_ID_ARTIKEL_GRUPPE_FIBU_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_GRUPPE_FIBU");
	}

	public String get_ID_ARTIKEL_GRUPPE_FIBU_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_GRUPPE_FIBU");	
	}

	public String get_ID_ARTIKEL_GRUPPE_FIBU_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_GRUPPE_FIBU");
	}

	public String get_ID_ARTIKEL_GRUPPE_FIBU_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_GRUPPE_FIBU",cNotNullValue);
	}

	public String get_ID_ARTIKEL_GRUPPE_FIBU_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_GRUPPE_FIBU",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_GRUPPE_FIBU_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_GRUPPE_FIBU", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_GRUPPE_FIBU_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_GRUPPE_FIBU").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_GRUPPE_FIBU_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_GRUPPE_FIBU").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_GRUPPE_FIBU_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_GRUPPE_FIBU").getDoubleValue();
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
	public String get_ID_ARTIKEL_GRUPPE_FIBU_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_GRUPPE_FIBU_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_GRUPPE_FIBU_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_GRUPPE_FIBU_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_GRUPPE_FIBU_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_GRUPPE_FIBU").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_GRUPPE_FIBU_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_GRUPPE_FIBU").getBigDecimalValue();
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
	
	
	public String get_ID_EAK_CODE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE");
	}

	public String get_ID_EAK_CODE_cF() throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE");	
	}

	public String get_ID_EAK_CODE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EAK_CODE");
	}

	public String get_ID_EAK_CODE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE",cNotNullValue);
	}

	public String get_ID_EAK_CODE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public Long get_ID_EAK_CODE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EAK_CODE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EAK_CODE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EAK_CODE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE").getDoubleValue();
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
	public String get_ID_EAK_CODE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EAK_CODE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EAK_CODE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EAK_CODE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE").getBigDecimalValue();
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
	
	
	public String get_ID_EAK_CODE_EX_MANDANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE_EX_MANDANT");
	}

	public String get_ID_EAK_CODE_EX_MANDANT_cF() throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE_EX_MANDANT");	
	}

	public String get_ID_EAK_CODE_EX_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EAK_CODE_EX_MANDANT");
	}

	public String get_ID_EAK_CODE_EX_MANDANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EAK_CODE_EX_MANDANT",cNotNullValue);
	}

	public String get_ID_EAK_CODE_EX_MANDANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EAK_CODE_EX_MANDANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EAK_CODE_EX_MANDANT", calNewValueFormated);
	}
		public Long get_ID_EAK_CODE_EX_MANDANT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EAK_CODE_EX_MANDANT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EAK_CODE_EX_MANDANT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE_EX_MANDANT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EAK_CODE_EX_MANDANT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EAK_CODE_EX_MANDANT").getDoubleValue();
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
	public String get_ID_EAK_CODE_EX_MANDANT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_EX_MANDANT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EAK_CODE_EX_MANDANT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EAK_CODE_EX_MANDANT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EAK_CODE_EX_MANDANT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE_EX_MANDANT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EAK_CODE_EX_MANDANT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EAK_CODE_EX_MANDANT").getBigDecimalValue();
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
	
	
	public String get_ID_EINHEIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT");
	}

	public String get_ID_EINHEIT_cF() throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT");	
	}

	public String get_ID_EINHEIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EINHEIT");
	}

	public String get_ID_EINHEIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT",cNotNullValue);
	}

	public String get_ID_EINHEIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EINHEIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EINHEIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT", calNewValueFormated);
	}
		public Long get_ID_EINHEIT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EINHEIT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EINHEIT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EINHEIT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT").getDoubleValue();
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
	public String get_ID_EINHEIT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EINHEIT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EINHEIT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EINHEIT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT").getBigDecimalValue();
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
	
	
	public String get_ID_EINHEIT_PREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT_PREIS");
	}

	public String get_ID_EINHEIT_PREIS_cF() throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT_PREIS");	
	}

	public String get_ID_EINHEIT_PREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_EINHEIT_PREIS");
	}

	public String get_ID_EINHEIT_PREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_EINHEIT_PREIS",cNotNullValue);
	}

	public String get_ID_EINHEIT_PREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_EINHEIT_PREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_EINHEIT_PREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_EINHEIT_PREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_EINHEIT_PREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_EINHEIT_PREIS", calNewValueFormated);
	}
		public Long get_ID_EINHEIT_PREIS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_EINHEIT_PREIS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_EINHEIT_PREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT_PREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_EINHEIT_PREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_EINHEIT_PREIS").getDoubleValue();
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
	public String get_ID_EINHEIT_PREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_EINHEIT_PREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_EINHEIT_PREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_EINHEIT_PREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT_PREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_EINHEIT_PREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_EINHEIT_PREIS").getBigDecimalValue();
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
	
	
	public String get_ID_ZOLLTARIFNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ZOLLTARIFNUMMER");
	}

	public String get_ID_ZOLLTARIFNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("ID_ZOLLTARIFNUMMER");	
	}

	public String get_ID_ZOLLTARIFNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ZOLLTARIFNUMMER");
	}

	public String get_ID_ZOLLTARIFNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ZOLLTARIFNUMMER",cNotNullValue);
	}

	public String get_ID_ZOLLTARIFNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ZOLLTARIFNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ZOLLTARIFNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ZOLLTARIFNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ZOLLTARIFNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ZOLLTARIFNUMMER", calNewValueFormated);
	}
		public Long get_ID_ZOLLTARIFNUMMER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ZOLLTARIFNUMMER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ZOLLTARIFNUMMER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ZOLLTARIFNUMMER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ZOLLTARIFNUMMER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ZOLLTARIFNUMMER").getDoubleValue();
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
	public String get_ID_ZOLLTARIFNUMMER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZOLLTARIFNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ZOLLTARIFNUMMER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ZOLLTARIFNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ZOLLTARIFNUMMER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZOLLTARIFNUMMER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ZOLLTARIFNUMMER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ZOLLTARIFNUMMER").getBigDecimalValue();
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
	
	
	public String get_IST_LEERGUT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_LEERGUT");
	}

	public String get_IST_LEERGUT_cF() throws myException
	{
		return this.get_FormatedValue("IST_LEERGUT");	
	}

	public String get_IST_LEERGUT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_LEERGUT");
	}

	public String get_IST_LEERGUT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_LEERGUT",cNotNullValue);
	}

	public String get_IST_LEERGUT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_LEERGUT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_LEERGUT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_LEERGUT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_LEERGUT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_LEERGUT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LEERGUT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LEERGUT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LEERGUT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LEERGUT", calNewValueFormated);
	}
		public boolean is_IST_LEERGUT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LEERGUT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_LEERGUT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LEERGUT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_PRODUKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_PRODUKT");
	}

	public String get_IST_PRODUKT_cF() throws myException
	{
		return this.get_FormatedValue("IST_PRODUKT");	
	}

	public String get_IST_PRODUKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_PRODUKT");
	}

	public String get_IST_PRODUKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_PRODUKT",cNotNullValue);
	}

	public String get_IST_PRODUKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_PRODUKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_PRODUKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_PRODUKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_PRODUKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PRODUKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PRODUKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PRODUKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PRODUKT", calNewValueFormated);
	}
		public boolean is_IST_PRODUKT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_PRODUKT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_PRODUKT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_PRODUKT_cUF_NN("N").equals("N"))
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
		public String get_MENGENDIVISOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGENDIVISOR");
	}

	public String get_MENGENDIVISOR_cF() throws myException
	{
		return this.get_FormatedValue("MENGENDIVISOR");	
	}

	public String get_MENGENDIVISOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGENDIVISOR");
	}

	public String get_MENGENDIVISOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGENDIVISOR",cNotNullValue);
	}

	public String get_MENGENDIVISOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGENDIVISOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGENDIVISOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGENDIVISOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGENDIVISOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENDIVISOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENDIVISOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGENDIVISOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGENDIVISOR", calNewValueFormated);
	}
		public Long get_MENGENDIVISOR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MENGENDIVISOR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MENGENDIVISOR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGENDIVISOR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGENDIVISOR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGENDIVISOR").getDoubleValue();
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
	public String get_MENGENDIVISOR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGENDIVISOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGENDIVISOR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGENDIVISOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGENDIVISOR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGENDIVISOR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGENDIVISOR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGENDIVISOR").getBigDecimalValue();
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
	
	
	public String get_ZOLLTARIFNOTIZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIFNOTIZ");
	}

	public String get_ZOLLTARIFNOTIZ_cF() throws myException
	{
		return this.get_FormatedValue("ZOLLTARIFNOTIZ");	
	}

	public String get_ZOLLTARIFNOTIZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZOLLTARIFNOTIZ");
	}

	public String get_ZOLLTARIFNOTIZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIFNOTIZ",cNotNullValue);
	}

	public String get_ZOLLTARIFNOTIZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZOLLTARIFNOTIZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZOLLTARIFNOTIZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNOTIZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZOLLTARIFNOTIZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNOTIZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNOTIZ", calNewValueFormated);
	}
		public String get_ZOLLTARIFNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIFNR");
	}

	public String get_ZOLLTARIFNR_cF() throws myException
	{
		return this.get_FormatedValue("ZOLLTARIFNR");	
	}

	public String get_ZOLLTARIFNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZOLLTARIFNR");
	}

	public String get_ZOLLTARIFNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZOLLTARIFNR",cNotNullValue);
	}

	public String get_ZOLLTARIFNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZOLLTARIFNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZOLLTARIFNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZOLLTARIFNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZOLLTARIFNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZOLLTARIFNR", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("ANHANG7_3A_CODE",MyRECORD.DATATYPES.TEXT);
	put("ANHANG7_3A_TEXT",MyRECORD.DATATYPES.TEXT);
	put("ANHANG7_3B_CODE",MyRECORD.DATATYPES.TEXT);
	put("ANHANG7_3B_TEXT",MyRECORD.DATATYPES.TEXT);
	put("ANR1",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ1",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ2",MyRECORD.DATATYPES.TEXT);
	put("BASEL_CODE",MyRECORD.DATATYPES.TEXT);
	put("BASEL_NOTIZ",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_INTERN",MyRECORD.DATATYPES.TEXT);
	put("DIENSTLEISTUNG",MyRECORD.DATATYPES.YESNO);
	put("END_OF_WASTE",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EUCODE",MyRECORD.DATATYPES.TEXT);
	put("EUNOTIZ",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEFAHRGUT",MyRECORD.DATATYPES.YESNO);
	put("GENAUIGKEIT_MENGEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_GRUPPE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_GRUPPE_FIBU",MyRECORD.DATATYPES.NUMBER);
	put("ID_EAK_CODE",MyRECORD.DATATYPES.NUMBER);
	put("ID_EAK_CODE_EX_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_EINHEIT",MyRECORD.DATATYPES.NUMBER);
	put("ID_EINHEIT_PREIS",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZOLLTARIFNUMMER",MyRECORD.DATATYPES.NUMBER);
	put("IST_LEERGUT",MyRECORD.DATATYPES.YESNO);
	put("IST_PRODUKT",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MENGENDIVISOR",MyRECORD.DATATYPES.NUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("ZOLLTARIFNOTIZ",MyRECORD.DATATYPES.TEXT);
	put("ZOLLTARIFNR",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ARTIKEL.HM_FIELDS;	
	}

}
