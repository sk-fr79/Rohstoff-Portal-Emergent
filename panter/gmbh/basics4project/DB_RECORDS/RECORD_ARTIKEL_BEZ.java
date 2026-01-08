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

public class RECORD_ARTIKEL_BEZ extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ARTIKEL_BEZ";
    public static String IDFIELD   = "ID_ARTIKEL_BEZ";
    

	//erzeugen eines RECORDNEW_JT_ARTIKEL_BEZ - felds
	private RECORDNEW_ARTIKEL_BEZ   recNEW = null;

    private _TAB  tab = _TAB.artikel_bez;  



	public RECORD_ARTIKEL_BEZ() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");
	}


	public RECORD_ARTIKEL_BEZ(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");
	}



	public RECORD_ARTIKEL_BEZ(RECORD_ARTIKEL_BEZ recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL_BEZ.TABLENAME);
	}


	//2014-04-03
	public RECORD_ARTIKEL_BEZ(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL_BEZ.TABLENAME);
	}




	public RECORD_ARTIKEL_BEZ(long lID_Unformated) throws myException
	{
		super("JT_ARTIKEL_BEZ","ID_ARTIKEL_BEZ",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL_BEZ.TABLENAME);
	}

	public RECORD_ARTIKEL_BEZ(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL_BEZ", "ID_ARTIKEL_BEZ="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL_BEZ", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL_BEZ.TABLENAME);
	}
	
	

	public RECORD_ARTIKEL_BEZ(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ARTIKEL_BEZ","ID_ARTIKEL_BEZ",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL_BEZ.TABLENAME);

	}


	public RECORD_ARTIKEL_BEZ(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ARTIKEL_BEZ");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL_BEZ", "ID_ARTIKEL_BEZ="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL_BEZ", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARTIKEL_BEZ.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ARTIKEL_BEZ();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ARTIKEL_BEZ.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ARTIKEL_BEZ";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ARTIKEL_BEZ_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ARTIKEL_BEZ_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ARTIKEL_BEZ was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ARTIKEL_BEZ", bibE2.cTO(), "ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ARTIKEL_BEZ was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ARTIKEL_BEZ", bibE2.cTO(), "ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
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
			if (S.isFull(this.get_ID_ARTIKEL_BEZ_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARTIKEL_BEZ", "ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ARTIKEL_BEZ get_RECORDNEW_ARTIKEL_BEZ() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ARTIKEL_BEZ();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ARTIKEL_BEZ(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ARTIKEL_BEZ create_Instance() throws myException {
		return new RECORD_ARTIKEL_BEZ();
	}
	
	public static RECORD_ARTIKEL_BEZ create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ARTIKEL_BEZ(Conn);
    }

	public static RECORD_ARTIKEL_BEZ create_Instance(RECORD_ARTIKEL_BEZ recordOrig) {
		return new RECORD_ARTIKEL_BEZ(recordOrig);
    }

	public static RECORD_ARTIKEL_BEZ create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ARTIKEL_BEZ(lID_Unformated);
    }

	public static RECORD_ARTIKEL_BEZ create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ARTIKEL_BEZ(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ARTIKEL_BEZ create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ARTIKEL_BEZ(lID_Unformated, Conn);
	}

	public static RECORD_ARTIKEL_BEZ create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ARTIKEL_BEZ(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ARTIKEL_BEZ create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ARTIKEL_BEZ(recordOrig);	    
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
    public RECORD_ARTIKEL_BEZ build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF());
      }
      //return new RECORD_ARTIKEL_BEZ(this.get_cSQL_4_Build());
      RECORD_ARTIKEL_BEZ rec = new RECORD_ARTIKEL_BEZ();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ARTIKEL_BEZ
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ARTIKEL_BEZ-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ARTIKEL_BEZ get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ARTIKEL_BEZ_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ARTIKEL_BEZ  recNew = new RECORDNEW_ARTIKEL_BEZ();
		
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
    public RECORD_ARTIKEL_BEZ set_recordNew(RECORDNEW_ARTIKEL_BEZ recnew) throws myException {
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
	
	



		private RECORD_ARTIKEL UP_RECORD_ARTIKEL_id_artikel = null;




		private RECLIST_MANDANT DOWN_RECLIST_MANDANT_id_artikel_bez_dummy = null ;




		private RECLIST_ABRECHBLATT DOWN_RECLIST_ABRECHBLATT_id_artikel_bez_dl = null ;




		private RECLIST_ABRECHBLATT_ARTBEZ_IN DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez = null ;




		private RECLIST_ABRECHBLATT_ARTBEZ_OUT DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez = null ;




		private RECLIST_ADRESSE_ARTIKEL DOWN_RECLIST_ADRESSE_ARTIKEL_id_artikel = null ;




		private RECLIST_ARTIKELBEZ_LIEF DOWN_RECLIST_ARTIKELBEZ_LIEF_id_artikel_bez = null ;




		private RECLIST_ARTIKEL_BEZ_MWST DOWN_RECLIST_ARTIKEL_BEZ_MWST_id_artikel_bez = null ;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel_bez = null ;




		private RECLIST_BEWEGUNG_VEKTOR_PN DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez = null ;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_artikel_bez = null ;




		private RECLIST_BG_AUSWERT DOWN_RECLIST_BG_AUSWERT_id_artikel_bez = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_dl = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_quelle = null ;




		private RECLIST_DLP_PROFIL DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_ziel = null ;




		private RECLIST_LAGER_PALETTE DOWN_RECLIST_LAGER_PALETTE_id_artikel_bez = null ;




		private RECLIST_MAT_SPEZ DOWN_RECLIST_MAT_SPEZ_id_artikel_bez = null ;




		private RECLIST_UMA_KON_ARTB_LIEF DOWN_RECLIST_UMA_KON_ARTB_LIEF_id_artikel_bez = null ;




		private RECLIST_UMA_KON_ARTB_RUECKLIEF DOWN_RECLIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez = null ;




		private RECLIST_UMA_KONTRAKT DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg = null ;




		private RECLIST_UMA_KONTRAKT DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg = null ;




		private RECLIST_VKOPF_KON DOWN_RECLIST_VKOPF_KON_fix_id_artbez = null ;




		private RECLIST_VPOS_KON DOWN_RECLIST_VPOS_KON_id_artikel_bez = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_artikel_bez = null ;




		private RECLIST_VPOS_RG_VL DOWN_RECLIST_VPOS_RG_VL_id_artikel_bez = null ;




		private RECLIST_VPOS_STD DOWN_RECLIST_VPOS_STD_id_artikel_bez = null ;




		private RECLIST_VPOS_TPA DOWN_RECLIST_VPOS_TPA_id_artikel_bez = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_ek = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_vk = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez = null ;




		private RECLIST_WIEGEKARTE DOWN_RECLIST_WIEGEKARTE_id_artikel_bez = null ;






	
	/**
	 * References the Field ID_ARTIKEL
	 * Falls keine this.get_ID_ARTIKEL_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL get_UP_RECORD_ARTIKEL_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_id_artikel==null)
		{
			this.UP_RECORD_ARTIKEL_id_artikel = new RECORD_ARTIKEL(this.get_ID_ARTIKEL_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_id_artikel;
	}
	





	/**
	 * References the Field ID_ARTIKEL_BEZ_DUMMY 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT get_DOWN_RECORD_LIST_MANDANT_id_artikel_bez_dummy() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_id_artikel_bez_dummy==null)
		{
			this.DOWN_RECLIST_MANDANT_id_artikel_bez_dummy = new RECLIST_MANDANT (
			       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_ARTIKEL_BEZ_DUMMY="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_MANDANT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_id_artikel_bez_dummy;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ_DUMMY 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT get_DOWN_RECORD_LIST_MANDANT_id_artikel_bez_dummy(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_id_artikel_bez_dummy==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT WHERE ID_ARTIKEL_BEZ_DUMMY="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MANDANT_id_artikel_bez_dummy = new RECLIST_MANDANT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_id_artikel_bez_dummy;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ_DL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_artikel_bez_dl() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_artikel_bez_dl==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_id_artikel_bez_dl = new RECLIST_ABRECHBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ARTIKEL_BEZ_DL="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_ABRECHBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_artikel_bez_dl;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ_DL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT get_DOWN_RECORD_LIST_ABRECHBLATT_id_artikel_bez_dl(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_id_artikel_bez_dl==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT WHERE ID_ARTIKEL_BEZ_DL="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_id_artikel_bez_dl = new RECLIST_ABRECHBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_id_artikel_bez_dl;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_IN get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez = new RECLIST_ABRECHBLATT_ARTBEZ_IN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_IN WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_ABRECHBLATT_ARTBEZ_IN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_IN get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_IN WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez = new RECLIST_ABRECHBLATT_ARTBEZ_IN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_IN_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_OUT get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez = new RECLIST_ABRECHBLATT_ARTBEZ_OUT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_OUT WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_ABRECHBLATT_ARTBEZ_OUT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ABRECHBLATT_ARTBEZ_OUT get_DOWN_RECORD_LIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_ARTBEZ_OUT WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez = new RECLIST_ABRECHBLATT_ARTBEZ_OUT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ABRECHBLATT_ARTBEZ_OUT_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_ARTIKEL get_DOWN_RECORD_LIST_ADRESSE_ARTIKEL_id_artikel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_artikel==null)
		{
			this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_artikel = new RECLIST_ADRESSE_ARTIKEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_ARTIKEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_ADRESSE_ARTIKEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_artikel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ADRESSE_ARTIKEL get_DOWN_RECORD_LIST_ADRESSE_ARTIKEL_id_artikel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_artikel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ADRESSE_ARTIKEL WHERE ID_ARTIKEL="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_artikel = new RECLIST_ADRESSE_ARTIKEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ADRESSE_ARTIKEL_id_artikel;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_artikel_bez = new RECLIST_ARTIKELBEZ_LIEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_ARTIKELBEZ_LIEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKELBEZ_LIEF get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_artikel_bez = new RECLIST_ARTIKELBEZ_LIEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKELBEZ_LIEF_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_BEZ_MWST get_DOWN_RECORD_LIST_ARTIKEL_BEZ_MWST_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_BEZ_MWST_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_ARTIKEL_BEZ_MWST_id_artikel_bez = new RECLIST_ARTIKEL_BEZ_MWST (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ_MWST WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_ARTIKEL_BEZ_MWST",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_BEZ_MWST_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_BEZ_MWST get_DOWN_RECORD_LIST_ARTIKEL_BEZ_MWST_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_BEZ_MWST_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ_MWST WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_BEZ_MWST_id_artikel_bez = new RECLIST_ARTIKEL_BEZ_MWST (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_BEZ_MWST_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel_bez = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel_bez = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez = new RECLIST_BEWEGUNG_VEKTOR_PN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_PN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez = new RECLIST_BEWEGUNG_VEKTOR_PN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_artikel_bez = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_artikel_bez = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_AUSWERT get_DOWN_RECORD_LIST_BG_AUSWERT_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_AUSWERT_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_BG_AUSWERT_id_artikel_bez = new RECLIST_BG_AUSWERT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_AUSWERT WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_BG_AUSWERT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_AUSWERT_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_AUSWERT get_DOWN_RECORD_LIST_BG_AUSWERT_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_AUSWERT_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_AUSWERT WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_AUSWERT_id_artikel_bez = new RECLIST_BG_AUSWERT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_AUSWERT_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ_DL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel_bez_dl() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_dl==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_dl = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL_BEZ_DL="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_dl;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ_DL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel_bez_dl(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_dl==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL_BEZ_DL="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_dl = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_dl;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ_QUELLE 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel_bez_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_quelle==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_quelle = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL_BEZ_QUELLE="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_quelle;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ_QUELLE 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel_bez_quelle(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_quelle==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL_BEZ_QUELLE="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_quelle = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_quelle;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ_ZIEL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel_bez_ziel() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_ziel==null)
		{
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_ziel = new RECLIST_DLP_PROFIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL_BEZ_ZIEL="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_DLP_PROFIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_ziel;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ_ZIEL 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_DLP_PROFIL get_DOWN_RECORD_LIST_DLP_PROFIL_id_artikel_bez_ziel(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_ziel==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_DLP_PROFIL WHERE ID_ARTIKEL_BEZ_ZIEL="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_ziel = new RECLIST_DLP_PROFIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_DLP_PROFIL_id_artikel_bez_ziel;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_id_artikel_bez = new RECLIST_LAGER_PALETTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_LAGER_PALETTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE get_DOWN_RECORD_LIST_LAGER_PALETTE_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_id_artikel_bez = new RECLIST_LAGER_PALETTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAT_SPEZ get_DOWN_RECORD_LIST_MAT_SPEZ_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAT_SPEZ_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_MAT_SPEZ_id_artikel_bez = new RECLIST_MAT_SPEZ (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAT_SPEZ WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_MAT_SPEZ",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAT_SPEZ_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAT_SPEZ get_DOWN_RECORD_LIST_MAT_SPEZ_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAT_SPEZ_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAT_SPEZ WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAT_SPEZ_id_artikel_bez = new RECLIST_MAT_SPEZ (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAT_SPEZ_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KON_ARTB_LIEF get_DOWN_RECORD_LIST_UMA_KON_ARTB_LIEF_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KON_ARTB_LIEF_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_UMA_KON_ARTB_LIEF_id_artikel_bez = new RECLIST_UMA_KON_ARTB_LIEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KON_ARTB_LIEF WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_UMA_KON_ARTB_LIEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KON_ARTB_LIEF_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KON_ARTB_LIEF get_DOWN_RECORD_LIST_UMA_KON_ARTB_LIEF_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KON_ARTB_LIEF_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KON_ARTB_LIEF WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_UMA_KON_ARTB_LIEF_id_artikel_bez = new RECLIST_UMA_KON_ARTB_LIEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KON_ARTB_LIEF_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KON_ARTB_RUECKLIEF get_DOWN_RECORD_LIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez = new RECLIST_UMA_KON_ARTB_RUECKLIEF (
			       "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KON_ARTB_RUECKLIEF WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_UMA_KON_ARTB_RUECKLIEF",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KON_ARTB_RUECKLIEF get_DOWN_RECORD_LIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KON_ARTB_RUECKLIEF WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez = new RECLIST_UMA_KON_ARTB_RUECKLIEF (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KON_ARTB_RUECKLIEF_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_A_BEZ_AUSGANG_KANN_WEG 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg==null)
		{
			this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg = new RECLIST_UMA_KONTRAKT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_A_BEZ_AUSGANG_KANN_WEG="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_UMA_KONTRAKT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_A_BEZ_AUSGANG_KANN_WEG 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_A_BEZ_AUSGANG_KANN_WEG="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg = new RECLIST_UMA_KONTRAKT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ausgang_kann_weg;
	}


	





	/**
	 * References the Field ID_A_BEZ_ZIEL_KANN_WEG 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg==null)
		{
			this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg = new RECLIST_UMA_KONTRAKT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_A_BEZ_ZIEL_KANN_WEG="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_UMA_KONTRAKT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_A_BEZ_ZIEL_KANN_WEG 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_UMA_KONTRAKT get_DOWN_RECORD_LIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE ID_A_BEZ_ZIEL_KANN_WEG="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg = new RECLIST_UMA_KONTRAKT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_UMA_KONTRAKT_id_a_bez_ziel_kann_weg;
	}


	





	/**
	 * References the Field FIX_ID_ARTBEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_fix_id_artbez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_fix_id_artbez==null)
		{
			this.DOWN_RECLIST_VKOPF_KON_fix_id_artbez = new RECLIST_VKOPF_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE FIX_ID_ARTBEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VKOPF_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_fix_id_artbez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field FIX_ID_ARTBEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VKOPF_KON get_DOWN_RECORD_LIST_VKOPF_KON_fix_id_artbez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VKOPF_KON_fix_id_artbez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE FIX_ID_ARTBEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VKOPF_KON_fix_id_artbez = new RECLIST_VKOPF_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VKOPF_KON_fix_id_artbez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_VPOS_KON_id_artikel_bez = new RECLIST_VPOS_KON (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_KON",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON get_DOWN_RECORD_LIST_VPOS_KON_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_id_artikel_bez = new RECLIST_VPOS_KON (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_artikel_bez = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_artikel_bez = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_VPOS_RG_VL_id_artikel_bez = new RECLIST_VPOS_RG_VL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_RG_VL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG_VL get_DOWN_RECORD_LIST_VPOS_RG_VL_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_VL_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_VL_id_artikel_bez = new RECLIST_VPOS_RG_VL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_VL_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_VPOS_STD_id_artikel_bez = new RECLIST_VPOS_STD (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_STD",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_STD get_DOWN_RECORD_LIST_VPOS_STD_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_STD_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_STD_id_artikel_bez = new RECLIST_VPOS_STD (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_STD_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_id_artikel_bez = new RECLIST_VPOS_TPA (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_TPA",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA get_DOWN_RECORD_LIST_VPOS_TPA_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_id_artikel_bez = new RECLIST_VPOS_TPA (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ_EK 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_artikel_bez_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_ek==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_ek = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ARTIKEL_BEZ_EK="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_ek;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ_EK 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_artikel_bez_ek(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_ek==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ARTIKEL_BEZ_EK="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_ek = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_ek;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ_VK 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_artikel_bez_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_vk==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_vk = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ARTIKEL_BEZ_VK="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_vk;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ_VK 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_artikel_bez_vk(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_vk==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_ARTIKEL_BEZ_VK="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_vk = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_artikel_bez_vk;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_artikel_bez;
	}


	





	/**
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_artikel_bez==null)
		{
			this.DOWN_RECLIST_WIEGEKARTE_id_artikel_bez = new RECLIST_WIEGEKARTE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF()+" ORDER BY ID_WIEGEKARTE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_artikel_bez;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARTIKEL_BEZ 
	 * Falls keine get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_WIEGEKARTE get_DOWN_RECORD_LIST_WIEGEKARTE_id_artikel_bez(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_WIEGEKARTE_id_artikel_bez==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_WIEGEKARTE_id_artikel_bez = new RECLIST_WIEGEKARTE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_WIEGEKARTE_id_artikel_bez;
	}


	

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__ANR2 = "ANR2";
	public static String FIELD__ARTBEZ1 = "ARTBEZ1";
	public static String FIELD__ARTBEZ2 = "ARTBEZ2";
	public static String FIELD__BEMERKUNG_INTERN = "BEMERKUNG_INTERN";
	public static String FIELD__DEF_SONDER_RECH_POS = "DEF_SONDER_RECH_POS";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ARTIKEL = "ID_ARTIKEL";
	public static String FIELD__ID_ARTIKEL_BEZ = "ID_ARTIKEL_BEZ";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


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
		public String get_ANR2_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANR2");
	}

	public String get_ANR2_cF() throws myException
	{
		return this.get_FormatedValue("ANR2");	
	}

	public String get_ANR2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANR2");
	}

	public String get_ANR2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANR2",cNotNullValue);
	}

	public String get_ANR2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANR2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANR2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANR2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANR2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANR2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANR2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANR2", calNewValueFormated);
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
		public String get_DEF_SONDER_RECH_POS_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEF_SONDER_RECH_POS");
	}

	public String get_DEF_SONDER_RECH_POS_cF() throws myException
	{
		return this.get_FormatedValue("DEF_SONDER_RECH_POS");	
	}

	public String get_DEF_SONDER_RECH_POS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEF_SONDER_RECH_POS");
	}

	public String get_DEF_SONDER_RECH_POS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEF_SONDER_RECH_POS",cNotNullValue);
	}

	public String get_DEF_SONDER_RECH_POS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEF_SONDER_RECH_POS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEF_SONDER_RECH_POS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEF_SONDER_RECH_POS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEF_SONDER_RECH_POS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEF_SONDER_RECH_POS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_SONDER_RECH_POS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEF_SONDER_RECH_POS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_SONDER_RECH_POS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEF_SONDER_RECH_POS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_SONDER_RECH_POS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEF_SONDER_RECH_POS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEF_SONDER_RECH_POS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEF_SONDER_RECH_POS", calNewValueFormated);
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
	
	
	public String get_ID_ARTIKEL_BEZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ");
	}

	public String get_ID_ARTIKEL_BEZ_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ");	
	}

	public String get_ID_ARTIKEL_BEZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARTIKEL_BEZ");
	}

	public String get_ID_ARTIKEL_BEZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ",cNotNullValue);
	}

	public String get_ID_ARTIKEL_BEZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARTIKEL_BEZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", calNewValueFormated);
	}
		public Long get_ID_ARTIKEL_BEZ_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARTIKEL_BEZ").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARTIKEL_BEZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARTIKEL_BEZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARTIKEL_BEZ").getDoubleValue();
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
	public String get_ID_ARTIKEL_BEZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARTIKEL_BEZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARTIKEL_BEZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARTIKEL_BEZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARTIKEL_BEZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARTIKEL_BEZ").getBigDecimalValue();
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
	put("AKTIV",MyRECORD.DATATYPES.YESNO);
	put("ANR2",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ1",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ2",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_INTERN",MyRECORD.DATATYPES.TEXT);
	put("DEF_SONDER_RECH_POS",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ARTIKEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ARTIKEL_BEZ.HM_FIELDS;	
	}

}
