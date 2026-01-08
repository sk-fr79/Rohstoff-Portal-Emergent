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

public class RECORD_VPOS_KON extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_VPOS_KON";
    public static String IDFIELD   = "ID_VPOS_KON";
    

	//erzeugen eines RECORDNEW_JT_VPOS_KON - felds
	private RECORDNEW_VPOS_KON   recNEW = null;

    private _TAB  tab = _TAB.vpos_kon;  



	public RECORD_VPOS_KON() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_KON");
	}


	public RECORD_VPOS_KON(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_KON");
	}



	public RECORD_VPOS_KON(RECORD_VPOS_KON recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_KON");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_KON.TABLENAME);
	}


	//2014-04-03
	public RECORD_VPOS_KON(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_VPOS_KON");
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_KON.TABLENAME);
	}




	public RECORD_VPOS_KON(long lID_Unformated) throws myException
	{
		super("JT_VPOS_KON","ID_VPOS_KON",""+lID_Unformated);
		this.set_TABLE_NAME("JT_VPOS_KON");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_KON.TABLENAME);
	}

	public RECORD_VPOS_KON(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_VPOS_KON");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_KON", "ID_VPOS_KON="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_KON", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_KON.TABLENAME);
	}
	
	

	public RECORD_VPOS_KON(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_VPOS_KON","ID_VPOS_KON",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_VPOS_KON");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_KON.TABLENAME);

	}


	public RECORD_VPOS_KON(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_VPOS_KON");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_KON", "ID_VPOS_KON="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_KON", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_VPOS_KON.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_VPOS_KON();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_VPOS_KON.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_VPOS_KON";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_VPOS_KON_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_VPOS_KON_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_KON was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_KON", bibE2.cTO(), "ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_VPOS_KON was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_VPOS_KON", bibE2.cTO(), "ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
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
			if (S.isFull(this.get_ID_VPOS_KON_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_VPOS_KON", "ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_VPOS_KON get_RECORDNEW_VPOS_KON() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_VPOS_KON();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_VPOS_KON(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_VPOS_KON create_Instance() throws myException {
		return new RECORD_VPOS_KON();
	}
	
	public static RECORD_VPOS_KON create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_VPOS_KON(Conn);
    }

	public static RECORD_VPOS_KON create_Instance(RECORD_VPOS_KON recordOrig) {
		return new RECORD_VPOS_KON(recordOrig);
    }

	public static RECORD_VPOS_KON create_Instance(long lID_Unformated) throws myException {
		return new RECORD_VPOS_KON(lID_Unformated);
    }

	public static RECORD_VPOS_KON create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_VPOS_KON(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_VPOS_KON create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_VPOS_KON(lID_Unformated, Conn);
	}

	public static RECORD_VPOS_KON create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_VPOS_KON(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_VPOS_KON create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_VPOS_KON(recordOrig);	    
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
    public RECORD_VPOS_KON build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF());
      }
      //return new RECORD_VPOS_KON(this.get_cSQL_4_Build());
      RECORD_VPOS_KON rec = new RECORD_VPOS_KON();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_VPOS_KON
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_VPOS_KON-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_VPOS_KON get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_VPOS_KON_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_VPOS_KON  recNew = new RECORDNEW_VPOS_KON();
		
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
    public RECORD_VPOS_KON set_recordNew(RECORDNEW_VPOS_KON recnew) throws myException {
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
	
	



		private RECORD_WAEHRUNG UP_RECORD_WAEHRUNG_id_waehrung_fremd = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_lager = null;




		private RECORD_ARTIKEL UP_RECORD_ARTIKEL_id_artikel = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez = null;




		private RECORD_STRECKEN_DEF UP_RECORD_STRECKEN_DEF_id_strecken_def = null;




		private RECORD_VKOPF_KON UP_RECORD_VKOPF_KON_id_vkopf_kon = null;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_kon = null ;




		private RECLIST_BG_ATOM DOWN_RECLIST_BG_ATOM_id_vpos_kon = null ;




		private RECLIST_EK_VK_BEZUG DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_ek = null ;




		private RECLIST_EK_VK_BEZUG DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_vk = null ;




		private RECLIST_FUHREN_RECHNUNGEN DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_kon = null ;




		private RECLIST_VPOS_KON_AENDERUNGEN DOWN_RECLIST_VPOS_KON_AENDERUNGEN_id_vpos_kon = null ;




		private RECLIST_VPOS_KON_AVV DOWN_RECLIST_VPOS_KON_AVV_id_vpos_kon = null ;




		private RECLIST_VPOS_KON_LAGER DOWN_RECLIST_VPOS_KON_LAGER_id_vpos_kon = null ;




		private RECLIST_VPOS_KON_TERM DOWN_RECLIST_VPOS_KON_TERM_id_vpos_kon = null ;




		private RECLIST_VPOS_KON_TRAKT DOWN_RECLIST_VPOS_KON_TRAKT_id_vpos_kon = null ;




		private RECLIST_VPOS_RG DOWN_RECLIST_VPOS_RG_id_vpos_kon_zugeord = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_ek = null ;




		private RECLIST_VPOS_TPA_FUHRE DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_vk = null ;




		private RECLIST_VPOS_TPA_FUHRE_ORT DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon = null ;




		private RECORD_ZAHLUNGSBEDINGUNGEN UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen = null;






	
	/**
	 * References the Field ID_WAEHRUNG_FREMD
	 * Falls keine this.get_ID_WAEHRUNG_FREMD_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_WAEHRUNG get_UP_RECORD_WAEHRUNG_id_waehrung_fremd() throws myException
	{
		if (S.isEmpty(this.get_ID_WAEHRUNG_FREMD_cUF()))
			return null;
	
	
		if (this.UP_RECORD_WAEHRUNG_id_waehrung_fremd==null)
		{
			this.UP_RECORD_WAEHRUNG_id_waehrung_fremd = new RECORD_WAEHRUNG(this.get_ID_WAEHRUNG_FREMD_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_WAEHRUNG_id_waehrung_fremd;
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
	 * References the Field ID_ADRESSE_LAGER
	 * Falls keine this.get_ID_ADRESSE_LAGER_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_lager() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_LAGER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_lager==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_lager = new RECORD_ADRESSE(this.get_ID_ADRESSE_LAGER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_lager;
	}
	





	
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
	 * References the Field ID_ARTIKEL_BEZ
	 * Falls keine this.get_ID_ARTIKEL_BEZ_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ARTIKEL_BEZ get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez() throws myException
	{
		if (S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez==null)
		{
			this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez = new RECORD_ARTIKEL_BEZ(this.get_ID_ARTIKEL_BEZ_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ARTIKEL_BEZ_id_artikel_bez;
	}
	





	
	/**
	 * References the Field ID_STRECKEN_DEF
	 * Falls keine this.get_ID_STRECKEN_DEF_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_STRECKEN_DEF get_UP_RECORD_STRECKEN_DEF_id_strecken_def() throws myException
	{
		if (S.isEmpty(this.get_ID_STRECKEN_DEF_cUF()))
			return null;
	
	
		if (this.UP_RECORD_STRECKEN_DEF_id_strecken_def==null)
		{
			this.UP_RECORD_STRECKEN_DEF_id_strecken_def = new RECORD_STRECKEN_DEF(this.get_ID_STRECKEN_DEF_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_STRECKEN_DEF_id_strecken_def;
	}
	





	
	/**
	 * References the Field ID_VKOPF_KON
	 * Falls keine this.get_ID_VKOPF_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VKOPF_KON get_UP_RECORD_VKOPF_KON_id_vkopf_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VKOPF_KON_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VKOPF_KON_id_vkopf_kon==null)
		{
			this.UP_RECORD_VKOPF_KON_id_vkopf_kon = new RECORD_VKOPF_KON(this.get_ID_VKOPF_KON_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VKOPF_KON_id_vkopf_kon;
	}
	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_kon = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_kon = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_BG_ATOM_id_vpos_kon = new RECLIST_BG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_BG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BG_ATOM get_DOWN_RECORD_LIST_BG_ATOM_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BG_ATOM_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BG_ATOM WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BG_ATOM_id_vpos_kon = new RECLIST_BG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BG_ATOM_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON_EK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EK_VK_BEZUG get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_ek==null)
		{
			this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_ek = new RECLIST_EK_VK_BEZUG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_EK_VK_BEZUG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_ek;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON_EK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EK_VK_BEZUG get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_ek==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_ek = new RECLIST_EK_VK_BEZUG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_ek;
	}


	





	/**
	 * References the Field ID_VPOS_KON_VK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EK_VK_BEZUG get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_vk==null)
		{
			this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_vk = new RECLIST_EK_VK_BEZUG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_VK="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_EK_VK_BEZUG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_vk;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON_VK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EK_VK_BEZUG get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_vk==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_VK="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_vk = new RECLIST_EK_VK_BEZUG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EK_VK_BEZUG_id_vpos_kon_vk;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_kon = new RECLIST_FUHREN_RECHNUNGEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_FUHREN_RECHNUNGEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_FUHREN_RECHNUNGEN get_DOWN_RECORD_LIST_FUHREN_RECHNUNGEN_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_RECHNUNGEN WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_kon = new RECLIST_FUHREN_RECHNUNGEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_FUHREN_RECHNUNGEN_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_AENDERUNGEN get_DOWN_RECORD_LIST_VPOS_KON_AENDERUNGEN_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_AENDERUNGEN_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_VPOS_KON_AENDERUNGEN_id_vpos_kon = new RECLIST_VPOS_KON_AENDERUNGEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_AENDERUNGEN WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_KON_AENDERUNGEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_AENDERUNGEN_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_AENDERUNGEN get_DOWN_RECORD_LIST_VPOS_KON_AENDERUNGEN_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_AENDERUNGEN_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_AENDERUNGEN WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_AENDERUNGEN_id_vpos_kon = new RECLIST_VPOS_KON_AENDERUNGEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_AENDERUNGEN_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_AVV get_DOWN_RECORD_LIST_VPOS_KON_AVV_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_AVV_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_VPOS_KON_AVV_id_vpos_kon = new RECLIST_VPOS_KON_AVV (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_AVV WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_KON_AVV",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_AVV_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_AVV get_DOWN_RECORD_LIST_VPOS_KON_AVV_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_AVV_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_AVV WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_AVV_id_vpos_kon = new RECLIST_VPOS_KON_AVV (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_AVV_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_LAGER get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_LAGER_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_VPOS_KON_LAGER_id_vpos_kon = new RECLIST_VPOS_KON_LAGER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_KON_LAGER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_LAGER_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_LAGER get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_LAGER_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_LAGER_id_vpos_kon = new RECLIST_VPOS_KON_LAGER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_LAGER_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_TERM get_DOWN_RECORD_LIST_VPOS_KON_TERM_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_TERM_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_VPOS_KON_TERM_id_vpos_kon = new RECLIST_VPOS_KON_TERM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_TERM WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_KON_TERM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_TERM_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_TERM get_DOWN_RECORD_LIST_VPOS_KON_TERM_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_TERM_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_TERM WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_TERM_id_vpos_kon = new RECLIST_VPOS_KON_TERM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_TERM_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_TRAKT get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_TRAKT_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_VPOS_KON_TRAKT_id_vpos_kon = new RECLIST_VPOS_KON_TRAKT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_KON_TRAKT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_TRAKT_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_KON_TRAKT get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_KON_TRAKT_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_KON_TRAKT_id_vpos_kon = new RECLIST_VPOS_KON_TRAKT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_KON_TRAKT_id_vpos_kon;
	}


	





	/**
	 * References the Field ID_VPOS_KON_ZUGEORD 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_vpos_kon_zugeord==null)
		{
			this.DOWN_RECLIST_VPOS_RG_id_vpos_kon_zugeord = new RECLIST_VPOS_RG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_KON_ZUGEORD="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_RG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_vpos_kon_zugeord;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON_ZUGEORD 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_RG get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_RG_id_vpos_kon_zugeord==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_KON_ZUGEORD="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_RG_id_vpos_kon_zugeord = new RECLIST_VPOS_RG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_RG_id_vpos_kon_zugeord;
	}


	





	/**
	 * References the Field ID_VPOS_KON_EK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_ek==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_ek = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_KON_EK="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_ek;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON_EK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_ek==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_KON_EK="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_ek = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_ek;
	}


	





	/**
	 * References the Field ID_VPOS_KON_VK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_vk==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_vk = new RECLIST_VPOS_TPA_FUHRE (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_KON_VK="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_vk;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON_VK 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_vk==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_KON_VK="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_vk = new RECLIST_VPOS_TPA_FUHRE (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_id_vpos_kon_vk;
	}


	





	/**
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon==null)
		{
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon = new RECLIST_VPOS_TPA_FUHRE_ORT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF()+" ORDER BY ID_VPOS_TPA_FUHRE_ORT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_VPOS_KON 
	 * Falls keine get_ID_VPOS_KON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_VPOS_TPA_FUHRE_ORT get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_KON_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_KON="+this.get_ID_VPOS_KON_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon = new RECLIST_VPOS_TPA_FUHRE_ORT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon;
	}


	





	
	/**
	 * References the Field ID_ZAHLUNGSBEDINGUNGEN
	 * Falls keine this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ZAHLUNGSBEDINGUNGEN get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen() throws myException
	{
		if (S.isEmpty(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen==null)
		{
			this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen = new RECORD_ZAHLUNGSBEDINGUNGEN(this.get_ID_ZAHLUNGSBEDINGUNGEN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen;
	}
	

	public static String FIELD__ANR1 = "ANR1";
	public static String FIELD__ANR2 = "ANR2";
	public static String FIELD__ANZAHL = "ANZAHL";
	public static String FIELD__ANZAHL_ABZUG = "ANZAHL_ABZUG";
	public static String FIELD__ARTBEZ1 = "ARTBEZ1";
	public static String FIELD__ARTBEZ2 = "ARTBEZ2";
	public static String FIELD__AUSFUEHRUNGSDATUM = "AUSFUEHRUNGSDATUM";
	public static String FIELD__BEMERKUNG_INTERN = "BEMERKUNG_INTERN";
	public static String FIELD__BESTELLNUMMER = "BESTELLNUMMER";
	public static String FIELD__BOERSENKURS = "BOERSENKURS";
	public static String FIELD__BOERSE_DIFF_ABS = "BOERSE_DIFF_ABS";
	public static String FIELD__BOERSE_DIFF_PROZ = "BOERSE_DIFF_PROZ";
	public static String FIELD__DELETED = "DELETED";
	public static String FIELD__DEL_DATE = "DEL_DATE";
	public static String FIELD__DEL_GRUND = "DEL_GRUND";
	public static String FIELD__DEL_KUERZEL = "DEL_KUERZEL";
	public static String FIELD__EINHEITKURZ = "EINHEITKURZ";
	public static String FIELD__EINHEIT_PREIS_KURZ = "EINHEIT_PREIS_KURZ";
	public static String FIELD__EINZELPREIS = "EINZELPREIS";
	public static String FIELD__EINZELPREIS_ABZUG = "EINZELPREIS_ABZUG";
	public static String FIELD__EINZELPREIS_ABZUG_FW = "EINZELPREIS_ABZUG_FW";
	public static String FIELD__EINZELPREIS_FW = "EINZELPREIS_FW";
	public static String FIELD__EINZELPREIS_RESULT = "EINZELPREIS_RESULT";
	public static String FIELD__EINZELPREIS_RESULT_FW = "EINZELPREIS_RESULT_FW";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EUCODE = "EUCODE";
	public static String FIELD__EUNOTIZ = "EUNOTIZ";
	public static String FIELD__FIXIERUNGSTAG = "FIXIERUNGSTAG";
	public static String FIELD__FIXMONAT = "FIXMONAT";
	public static String FIELD__FIXTAG = "FIXTAG";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEBUCHT = "GEBUCHT";
	public static String FIELD__GESAMTPREIS = "GESAMTPREIS";
	public static String FIELD__GESAMTPREIS_ABZUG = "GESAMTPREIS_ABZUG";
	public static String FIELD__GESAMTPREIS_ABZUG_FW = "GESAMTPREIS_ABZUG_FW";
	public static String FIELD__GESAMTPREIS_FW = "GESAMTPREIS_FW";
	public static String FIELD__ID_ADRESSE = "ID_ADRESSE";
	public static String FIELD__ID_ADRESSE_LAGER = "ID_ADRESSE_LAGER";
	public static String FIELD__ID_ADRESSE_LAGER_START = "ID_ADRESSE_LAGER_START";
	public static String FIELD__ID_ADRESSE_LAGER_ZIEL = "ID_ADRESSE_LAGER_ZIEL";
	public static String FIELD__ID_ARTIKEL = "ID_ARTIKEL";
	public static String FIELD__ID_ARTIKEL_BEZ = "ID_ARTIKEL_BEZ";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_STRECKEN_DEF = "ID_STRECKEN_DEF";
	public static String FIELD__ID_VKOPF_KON = "ID_VKOPF_KON";
	public static String FIELD__ID_VPOS_KON = "ID_VPOS_KON";
	public static String FIELD__ID_VPOS_KON_ZUGEORD = "ID_VPOS_KON_ZUGEORD";
	public static String FIELD__ID_VPOS_TPA_FUHRE_ZUGEORD = "ID_VPOS_TPA_FUHRE_ZUGEORD";
	public static String FIELD__ID_WAEHRUNG_FREMD = "ID_WAEHRUNG_FREMD";
	public static String FIELD__ID_ZAHLUNGSBEDINGUNGEN = "ID_ZAHLUNGSBEDINGUNGEN";
	public static String FIELD__LAGER_VORZEICHEN = "LAGER_VORZEICHEN";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERBEDINGUNGEN = "LIEFERBEDINGUNGEN";
	public static String FIELD__MENGENDIVISOR = "MENGENDIVISOR";
	public static String FIELD__MENGEN_TOLERANZ_PROZENT = "MENGEN_TOLERANZ_PROZENT";
	public static String FIELD__POSITIONSKLASSE = "POSITIONSKLASSE";
	public static String FIELD__POSITIONSNUMMER = "POSITIONSNUMMER";
	public static String FIELD__POSITION_ACTIVE = "POSITION_ACTIVE";
	public static String FIELD__POSITION_TYP = "POSITION_TYP";
	public static String FIELD__PREIS_MANUELL = "PREIS_MANUELL";
	public static String FIELD__SKONTO_PROZENT = "SKONTO_PROZENT";
	public static String FIELD__STEUERSATZ = "STEUERSATZ";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TYP_25_TO = "TYP_25_TO";
	public static String FIELD__TYP_LADUNG = "TYP_LADUNG";
	public static String FIELD__VORGANG_TYP = "VORGANG_TYP";
	public static String FIELD__WAEHRUNGSKURS = "WAEHRUNGSKURS";
	public static String FIELD__WECHSELKURS = "WECHSELKURS";
	public static String FIELD__WIEGEKARTENKENNER = "WIEGEKARTENKENNER";
	public static String FIELD__ZAHLTAGE = "ZAHLTAGE";
	public static String FIELD__ZAHLUNGSBEDINGUNGEN = "ZAHLUNGSBEDINGUNGEN";
	public static String FIELD__ZAHLUNGSBED_CALC_DATUM = "ZAHLUNGSBED_CALC_DATUM";
	public static String FIELD__ZOLLTARIFNR = "ZOLLTARIFNR";


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
		public String get_ANZAHL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL");
	}

	public String get_ANZAHL_cF() throws myException
	{
		return this.get_FormatedValue("ANZAHL");	
	}

	public String get_ANZAHL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZAHL");
	}

	public String get_ANZAHL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZAHL",cNotNullValue);
	}

	public String get_ANZAHL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZAHL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZAHL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZAHL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZAHL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZAHL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL", calNewValueFormated);
	}
		public Double get_ANZAHL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZAHL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZAHL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZAHL").getDoubleValue();
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
	public String get_ANZAHL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ANZAHL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ANZAHL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZAHL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL").getBigDecimalValue();
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
	
	
	public String get_ANZAHL_ABZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_ABZUG");
	}

	public String get_ANZAHL_ABZUG_cF() throws myException
	{
		return this.get_FormatedValue("ANZAHL_ABZUG");	
	}

	public String get_ANZAHL_ABZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANZAHL_ABZUG");
	}

	public String get_ANZAHL_ABZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_ABZUG",cNotNullValue);
	}

	public String get_ANZAHL_ABZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANZAHL_ABZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANZAHL_ABZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANZAHL_ABZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANZAHL_ABZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANZAHL_ABZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANZAHL_ABZUG", calNewValueFormated);
	}
		public Double get_ANZAHL_ABZUG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ANZAHL_ABZUG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ANZAHL_ABZUG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ANZAHL_ABZUG").getDoubleValue();
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
	public String get_ANZAHL_ABZUG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ANZAHL_ABZUG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ANZAHL_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ANZAHL_ABZUG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_ABZUG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ANZAHL_ABZUG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ANZAHL_ABZUG").getBigDecimalValue();
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
		public String get_AUSFUEHRUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUSFUEHRUNGSDATUM");
	}

	public String get_AUSFUEHRUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("AUSFUEHRUNGSDATUM");	
	}

	public String get_AUSFUEHRUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUSFUEHRUNGSDATUM");
	}

	public String get_AUSFUEHRUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUSFUEHRUNGSDATUM",cNotNullValue);
	}

	public String get_AUSFUEHRUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUSFUEHRUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUSFUEHRUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUSFUEHRUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSFUEHRUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSFUEHRUNGSDATUM", calNewValueFormated);
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
		public String get_BESTELLNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER");
	}

	public String get_BESTELLNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER");	
	}

	public String get_BESTELLNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BESTELLNUMMER");
	}

	public String get_BESTELLNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BESTELLNUMMER",cNotNullValue);
	}

	public String get_BESTELLNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BESTELLNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BESTELLNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BESTELLNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BESTELLNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BESTELLNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BESTELLNUMMER", calNewValueFormated);
	}
		public String get_BOERSENKURS_cUF() throws myException
	{
		return this.get_UnFormatedValue("BOERSENKURS");
	}

	public String get_BOERSENKURS_cF() throws myException
	{
		return this.get_FormatedValue("BOERSENKURS");	
	}

	public String get_BOERSENKURS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BOERSENKURS");
	}

	public String get_BOERSENKURS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BOERSENKURS",cNotNullValue);
	}

	public String get_BOERSENKURS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BOERSENKURS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BOERSENKURS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BOERSENKURS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BOERSENKURS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BOERSENKURS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSENKURS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BOERSENKURS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSENKURS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSENKURS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSENKURS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSENKURS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSENKURS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSENKURS", calNewValueFormated);
	}
		public Double get_BOERSENKURS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("BOERSENKURS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_BOERSENKURS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("BOERSENKURS").getDoubleValue();
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
	public String get_BOERSENKURS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BOERSENKURS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_BOERSENKURS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BOERSENKURS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_BOERSENKURS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("BOERSENKURS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_BOERSENKURS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("BOERSENKURS").getBigDecimalValue();
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
	
	
	public String get_BOERSE_DIFF_ABS_cUF() throws myException
	{
		return this.get_UnFormatedValue("BOERSE_DIFF_ABS");
	}

	public String get_BOERSE_DIFF_ABS_cF() throws myException
	{
		return this.get_FormatedValue("BOERSE_DIFF_ABS");	
	}

	public String get_BOERSE_DIFF_ABS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BOERSE_DIFF_ABS");
	}

	public String get_BOERSE_DIFF_ABS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BOERSE_DIFF_ABS",cNotNullValue);
	}

	public String get_BOERSE_DIFF_ABS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BOERSE_DIFF_ABS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_ABS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BOERSE_DIFF_ABS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BOERSE_DIFF_ABS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BOERSE_DIFF_ABS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_ABS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BOERSE_DIFF_ABS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_ABS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSE_DIFF_ABS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_ABS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSE_DIFF_ABS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_ABS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSE_DIFF_ABS", calNewValueFormated);
	}
		public Double get_BOERSE_DIFF_ABS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("BOERSE_DIFF_ABS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_BOERSE_DIFF_ABS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("BOERSE_DIFF_ABS").getDoubleValue();
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
	public String get_BOERSE_DIFF_ABS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BOERSE_DIFF_ABS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_BOERSE_DIFF_ABS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BOERSE_DIFF_ABS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_BOERSE_DIFF_ABS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("BOERSE_DIFF_ABS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_BOERSE_DIFF_ABS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("BOERSE_DIFF_ABS").getBigDecimalValue();
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
	
	
	public String get_BOERSE_DIFF_PROZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("BOERSE_DIFF_PROZ");
	}

	public String get_BOERSE_DIFF_PROZ_cF() throws myException
	{
		return this.get_FormatedValue("BOERSE_DIFF_PROZ");	
	}

	public String get_BOERSE_DIFF_PROZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BOERSE_DIFF_PROZ");
	}

	public String get_BOERSE_DIFF_PROZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BOERSE_DIFF_PROZ",cNotNullValue);
	}

	public String get_BOERSE_DIFF_PROZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BOERSE_DIFF_PROZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_PROZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BOERSE_DIFF_PROZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BOERSE_DIFF_PROZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BOERSE_DIFF_PROZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_PROZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BOERSE_DIFF_PROZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_PROZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSE_DIFF_PROZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_PROZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSE_DIFF_PROZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BOERSE_DIFF_PROZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BOERSE_DIFF_PROZ", calNewValueFormated);
	}
		public Double get_BOERSE_DIFF_PROZ_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("BOERSE_DIFF_PROZ").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_BOERSE_DIFF_PROZ_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("BOERSE_DIFF_PROZ").getDoubleValue();
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
	public String get_BOERSE_DIFF_PROZ_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BOERSE_DIFF_PROZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_BOERSE_DIFF_PROZ_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BOERSE_DIFF_PROZ_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_BOERSE_DIFF_PROZ_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("BOERSE_DIFF_PROZ").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_BOERSE_DIFF_PROZ_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("BOERSE_DIFF_PROZ").getBigDecimalValue();
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
		public String get_EINHEITKURZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINHEITKURZ");
	}

	public String get_EINHEITKURZ_cF() throws myException
	{
		return this.get_FormatedValue("EINHEITKURZ");	
	}

	public String get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINHEITKURZ");
	}

	public String get_EINHEITKURZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINHEITKURZ",cNotNullValue);
	}

	public String get_EINHEITKURZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINHEITKURZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINHEITKURZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINHEITKURZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINHEITKURZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINHEITKURZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEITKURZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEITKURZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITKURZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEITKURZ", calNewValueFormated);
	}
		public String get_EINHEIT_PREIS_KURZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINHEIT_PREIS_KURZ");
	}

	public String get_EINHEIT_PREIS_KURZ_cF() throws myException
	{
		return this.get_FormatedValue("EINHEIT_PREIS_KURZ");	
	}

	public String get_EINHEIT_PREIS_KURZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINHEIT_PREIS_KURZ");
	}

	public String get_EINHEIT_PREIS_KURZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINHEIT_PREIS_KURZ",cNotNullValue);
	}

	public String get_EINHEIT_PREIS_KURZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINHEIT_PREIS_KURZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINHEIT_PREIS_KURZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINHEIT_PREIS_KURZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEIT_PREIS_KURZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEIT_PREIS_KURZ", calNewValueFormated);
	}
		public String get_EINZELPREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS");
	}

	public String get_EINZELPREIS_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS");	
	}

	public String get_EINZELPREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS");
	}

	public String get_EINZELPREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS",cNotNullValue);
	}

	public String get_EINZELPREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS", calNewValueFormated);
	}
		public Double get_EINZELPREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS").getDoubleValue();
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
	public String get_EINZELPREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EINZELPREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EINZELPREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS").getBigDecimalValue();
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
	
	
	public String get_EINZELPREIS_ABZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_ABZUG");
	}

	public String get_EINZELPREIS_ABZUG_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_ABZUG");	
	}

	public String get_EINZELPREIS_ABZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS_ABZUG");
	}

	public String get_EINZELPREIS_ABZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_ABZUG",cNotNullValue);
	}

	public String get_EINZELPREIS_ABZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_ABZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS_ABZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS_ABZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG", calNewValueFormated);
	}
		public Double get_EINZELPREIS_ABZUG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_ABZUG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_ABZUG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_ABZUG").getDoubleValue();
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
	public String get_EINZELPREIS_ABZUG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EINZELPREIS_ABZUG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EINZELPREIS_ABZUG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_ABZUG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_ABZUG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_ABZUG").getBigDecimalValue();
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
	
	
	public String get_EINZELPREIS_ABZUG_FW_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_ABZUG_FW");
	}

	public String get_EINZELPREIS_ABZUG_FW_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_ABZUG_FW");	
	}

	public String get_EINZELPREIS_ABZUG_FW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS_ABZUG_FW");
	}

	public String get_EINZELPREIS_ABZUG_FW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_ABZUG_FW",cNotNullValue);
	}

	public String get_EINZELPREIS_ABZUG_FW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_ABZUG_FW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS_ABZUG_FW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_ABZUG_FW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_ABZUG_FW", calNewValueFormated);
	}
		public Double get_EINZELPREIS_ABZUG_FW_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_ABZUG_FW").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_ABZUG_FW_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_ABZUG_FW").getDoubleValue();
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
	public String get_EINZELPREIS_ABZUG_FW_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_ABZUG_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EINZELPREIS_ABZUG_FW_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_ABZUG_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EINZELPREIS_ABZUG_FW_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_ABZUG_FW").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_ABZUG_FW_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_ABZUG_FW").getBigDecimalValue();
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
	
	
	public String get_EINZELPREIS_FW_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_FW");
	}

	public String get_EINZELPREIS_FW_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_FW");	
	}

	public String get_EINZELPREIS_FW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS_FW");
	}

	public String get_EINZELPREIS_FW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_FW",cNotNullValue);
	}

	public String get_EINZELPREIS_FW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_FW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS_FW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_FW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS_FW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_FW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_FW", calNewValueFormated);
	}
		public Double get_EINZELPREIS_FW_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_FW").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_FW_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_FW").getDoubleValue();
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
	public String get_EINZELPREIS_FW_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EINZELPREIS_FW_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EINZELPREIS_FW_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_FW").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_FW_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_FW").getBigDecimalValue();
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
	
	
	public String get_EINZELPREIS_RESULT_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_RESULT");
	}

	public String get_EINZELPREIS_RESULT_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_RESULT");	
	}

	public String get_EINZELPREIS_RESULT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS_RESULT");
	}

	public String get_EINZELPREIS_RESULT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_RESULT",cNotNullValue);
	}

	public String get_EINZELPREIS_RESULT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_RESULT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS_RESULT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_RESULT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS_RESULT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT", calNewValueFormated);
	}
		public Double get_EINZELPREIS_RESULT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_RESULT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_RESULT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_RESULT").getDoubleValue();
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
	public String get_EINZELPREIS_RESULT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_RESULT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EINZELPREIS_RESULT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_RESULT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EINZELPREIS_RESULT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_RESULT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_RESULT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_RESULT").getBigDecimalValue();
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
	
	
	public String get_EINZELPREIS_RESULT_FW_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_RESULT_FW");
	}

	public String get_EINZELPREIS_RESULT_FW_cF() throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_RESULT_FW");	
	}

	public String get_EINZELPREIS_RESULT_FW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINZELPREIS_RESULT_FW");
	}

	public String get_EINZELPREIS_RESULT_FW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINZELPREIS_RESULT_FW",cNotNullValue);
	}

	public String get_EINZELPREIS_RESULT_FW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINZELPREIS_RESULT_FW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINZELPREIS_RESULT_FW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINZELPREIS_RESULT_FW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINZELPREIS_RESULT_FW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINZELPREIS_RESULT_FW", calNewValueFormated);
	}
		public Double get_EINZELPREIS_RESULT_FW_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_RESULT_FW").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EINZELPREIS_RESULT_FW_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EINZELPREIS_RESULT_FW").getDoubleValue();
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
	public String get_EINZELPREIS_RESULT_FW_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_RESULT_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EINZELPREIS_RESULT_FW_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EINZELPREIS_RESULT_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EINZELPREIS_RESULT_FW_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_RESULT_FW").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EINZELPREIS_RESULT_FW_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EINZELPREIS_RESULT_FW").getBigDecimalValue();
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
		public String get_FIXIERUNGSTAG_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIXIERUNGSTAG");
	}

	public String get_FIXIERUNGSTAG_cF() throws myException
	{
		return this.get_FormatedValue("FIXIERUNGSTAG");	
	}

	public String get_FIXIERUNGSTAG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIXIERUNGSTAG");
	}

	public String get_FIXIERUNGSTAG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIXIERUNGSTAG",cNotNullValue);
	}

	public String get_FIXIERUNGSTAG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIXIERUNGSTAG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSTAG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIXIERUNGSTAG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIXIERUNGSTAG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIXIERUNGSTAG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSTAG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIXIERUNGSTAG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSTAG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXIERUNGSTAG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSTAG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXIERUNGSTAG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIXIERUNGSTAG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIXIERUNGSTAG", calNewValueFormated);
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
		public String get_GEBUCHT_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEBUCHT");
	}

	public String get_GEBUCHT_cF() throws myException
	{
		return this.get_FormatedValue("GEBUCHT");	
	}

	public String get_GEBUCHT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEBUCHT");
	}

	public String get_GEBUCHT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEBUCHT",cNotNullValue);
	}

	public String get_GEBUCHT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEBUCHT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEBUCHT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEBUCHT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEBUCHT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEBUCHT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEBUCHT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEBUCHT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEBUCHT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEBUCHT", calNewValueFormated);
	}
		public boolean is_GEBUCHT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GEBUCHT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GEBUCHT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GEBUCHT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_GESAMTPREIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS");
	}

	public String get_GESAMTPREIS_cF() throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS");	
	}

	public String get_GESAMTPREIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESAMTPREIS");
	}

	public String get_GESAMTPREIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS",cNotNullValue);
	}

	public String get_GESAMTPREIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESAMTPREIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESAMTPREIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESAMTPREIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS", calNewValueFormated);
	}
		public Double get_GESAMTPREIS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GESAMTPREIS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS").getDoubleValue();
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
	public String get_GESAMTPREIS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GESAMTPREIS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GESAMTPREIS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GESAMTPREIS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS").getBigDecimalValue();
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
	
	
	public String get_GESAMTPREIS_ABZUG_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_ABZUG");
	}

	public String get_GESAMTPREIS_ABZUG_cF() throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_ABZUG");	
	}

	public String get_GESAMTPREIS_ABZUG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESAMTPREIS_ABZUG");
	}

	public String get_GESAMTPREIS_ABZUG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_ABZUG",cNotNullValue);
	}

	public String get_GESAMTPREIS_ABZUG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_ABZUG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESAMTPREIS_ABZUG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_ABZUG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESAMTPREIS_ABZUG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG", calNewValueFormated);
	}
		public Double get_GESAMTPREIS_ABZUG_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_ABZUG").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GESAMTPREIS_ABZUG_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_ABZUG").getDoubleValue();
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
	public String get_GESAMTPREIS_ABZUG_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GESAMTPREIS_ABZUG_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_ABZUG_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GESAMTPREIS_ABZUG_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_ABZUG").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GESAMTPREIS_ABZUG_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_ABZUG").getBigDecimalValue();
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
	
	
	public String get_GESAMTPREIS_ABZUG_FW_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_ABZUG_FW");
	}

	public String get_GESAMTPREIS_ABZUG_FW_cF() throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_ABZUG_FW");	
	}

	public String get_GESAMTPREIS_ABZUG_FW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESAMTPREIS_ABZUG_FW");
	}

	public String get_GESAMTPREIS_ABZUG_FW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_ABZUG_FW",cNotNullValue);
	}

	public String get_GESAMTPREIS_ABZUG_FW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_ABZUG_FW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_ABZUG_FW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_ABZUG_FW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_ABZUG_FW", calNewValueFormated);
	}
		public Double get_GESAMTPREIS_ABZUG_FW_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_ABZUG_FW").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GESAMTPREIS_ABZUG_FW_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_ABZUG_FW").getDoubleValue();
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
	public String get_GESAMTPREIS_ABZUG_FW_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_ABZUG_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GESAMTPREIS_ABZUG_FW_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_ABZUG_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GESAMTPREIS_ABZUG_FW_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_ABZUG_FW").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GESAMTPREIS_ABZUG_FW_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_ABZUG_FW").getBigDecimalValue();
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
	
	
	public String get_GESAMTPREIS_FW_cUF() throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_FW");
	}

	public String get_GESAMTPREIS_FW_cF() throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_FW");	
	}

	public String get_GESAMTPREIS_FW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESAMTPREIS_FW");
	}

	public String get_GESAMTPREIS_FW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GESAMTPREIS_FW",cNotNullValue);
	}

	public String get_GESAMTPREIS_FW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GESAMTPREIS_FW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GESAMTPREIS_FW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GESAMTPREIS_FW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GESAMTPREIS_FW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GESAMTPREIS_FW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GESAMTPREIS_FW", calNewValueFormated);
	}
		public Double get_GESAMTPREIS_FW_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_FW").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GESAMTPREIS_FW_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GESAMTPREIS_FW").getDoubleValue();
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
	public String get_GESAMTPREIS_FW_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GESAMTPREIS_FW_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GESAMTPREIS_FW_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GESAMTPREIS_FW_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_FW").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GESAMTPREIS_FW_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GESAMTPREIS_FW").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LAGER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER");
	}

	public String get_ID_ADRESSE_LAGER_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER");	
	}

	public String get_ID_ADRESSE_LAGER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER");
	}

	public String get_ID_ADRESSE_LAGER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_LAGER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_LAGER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LAGER_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_START");
	}

	public String get_ID_ADRESSE_LAGER_START_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_START");	
	}

	public String get_ID_ADRESSE_LAGER_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER_START");
	}

	public String get_ID_ADRESSE_LAGER_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_START",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_START", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_START_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER_START").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_START_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_START").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_START_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_START").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_START_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_START_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_LAGER_START_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_START_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_LAGER_START_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_START").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_START_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_START").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_LAGER_ZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_ZIEL");
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_ZIEL");	
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_LAGER_ZIEL");
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_LAGER_ZIEL",cNotNullValue);
	}

	public String get_ID_ADRESSE_LAGER_ZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_LAGER_ZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER_ZIEL", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_LAGER_ZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_LAGER_ZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_LAGER_ZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getDoubleValue();
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
	public String get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_LAGER_ZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_LAGER_ZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_LAGER_ZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_LAGER_ZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_LAGER_ZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_STRECKEN_DEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_STRECKEN_DEF");
	}

	public String get_ID_STRECKEN_DEF_cF() throws myException
	{
		return this.get_FormatedValue("ID_STRECKEN_DEF");	
	}

	public String get_ID_STRECKEN_DEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_STRECKEN_DEF");
	}

	public String get_ID_STRECKEN_DEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_STRECKEN_DEF",cNotNullValue);
	}

	public String get_ID_STRECKEN_DEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_STRECKEN_DEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_STRECKEN_DEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_STRECKEN_DEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_STRECKEN_DEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_STRECKEN_DEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_STRECKEN_DEF", calNewValueFormated);
	}
		public Long get_ID_STRECKEN_DEF_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_STRECKEN_DEF").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_STRECKEN_DEF_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_STRECKEN_DEF").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_STRECKEN_DEF_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_STRECKEN_DEF").getDoubleValue();
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
	public String get_ID_STRECKEN_DEF_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_STRECKEN_DEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_STRECKEN_DEF_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_STRECKEN_DEF_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_STRECKEN_DEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_STRECKEN_DEF").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_STRECKEN_DEF_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_STRECKEN_DEF").getBigDecimalValue();
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
	
	
	public String get_ID_VKOPF_KON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VKOPF_KON");
	}

	public String get_ID_VKOPF_KON_cF() throws myException
	{
		return this.get_FormatedValue("ID_VKOPF_KON");	
	}

	public String get_ID_VKOPF_KON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VKOPF_KON");
	}

	public String get_ID_VKOPF_KON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VKOPF_KON",cNotNullValue);
	}

	public String get_ID_VKOPF_KON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VKOPF_KON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_KON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VKOPF_KON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VKOPF_KON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VKOPF_KON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_KON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VKOPF_KON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_KON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_KON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_KON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_KON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VKOPF_KON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VKOPF_KON", calNewValueFormated);
	}
		public Long get_ID_VKOPF_KON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VKOPF_KON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VKOPF_KON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VKOPF_KON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VKOPF_KON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VKOPF_KON").getDoubleValue();
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
	public String get_ID_VKOPF_KON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VKOPF_KON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VKOPF_KON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VKOPF_KON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VKOPF_KON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VKOPF_KON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VKOPF_KON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VKOPF_KON").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_KON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON");
	}

	public String get_ID_VPOS_KON_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON");	
	}

	public String get_ID_VPOS_KON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_KON");
	}

	public String get_ID_VPOS_KON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON",cNotNullValue);
	}

	public String get_ID_VPOS_KON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_KON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON", calNewValueFormated);
	}
		public Long get_ID_VPOS_KON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_KON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_KON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_KON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON").getDoubleValue();
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
	public String get_ID_VPOS_KON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_KON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_KON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_KON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_KON_ZUGEORD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON_ZUGEORD");
	}

	public String get_ID_VPOS_KON_ZUGEORD_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON_ZUGEORD");	
	}

	public String get_ID_VPOS_KON_ZUGEORD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_KON_ZUGEORD");
	}

	public String get_ID_VPOS_KON_ZUGEORD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_KON_ZUGEORD",cNotNullValue);
	}

	public String get_ID_VPOS_KON_ZUGEORD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_KON_ZUGEORD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_KON_ZUGEORD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_KON_ZUGEORD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_KON_ZUGEORD", calNewValueFormated);
	}
		public Long get_ID_VPOS_KON_ZUGEORD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_KON_ZUGEORD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_KON_ZUGEORD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON_ZUGEORD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_KON_ZUGEORD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_KON_ZUGEORD").getDoubleValue();
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
	public String get_ID_VPOS_KON_ZUGEORD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_ZUGEORD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_KON_ZUGEORD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_KON_ZUGEORD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_KON_ZUGEORD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON_ZUGEORD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_KON_ZUGEORD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_KON_ZUGEORD").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ZUGEORD");
	}

	public String get_ID_VPOS_TPA_FUHRE_ZUGEORD_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ZUGEORD");	
	}

	public String get_ID_VPOS_TPA_FUHRE_ZUGEORD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_ZUGEORD");
	}

	public String get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ZUGEORD",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_ZUGEORD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_ZUGEORD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ZUGEORD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ZUGEORD", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_ZUGEORD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_ZUGEORD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_ZUGEORD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ZUGEORD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_ZUGEORD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_ZUGEORD").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ZUGEORD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_ZUGEORD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_ZUGEORD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_ZUGEORD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ZUGEORD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_ZUGEORD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_ZUGEORD").getBigDecimalValue();
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
	
	
	public String get_ID_WAEHRUNG_FREMD_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG_FREMD");
	}

	public String get_ID_WAEHRUNG_FREMD_cF() throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG_FREMD");	
	}

	public String get_ID_WAEHRUNG_FREMD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_WAEHRUNG_FREMD");
	}

	public String get_ID_WAEHRUNG_FREMD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_WAEHRUNG_FREMD",cNotNullValue);
	}

	public String get_ID_WAEHRUNG_FREMD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_WAEHRUNG_FREMD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_WAEHRUNG_FREMD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_WAEHRUNG_FREMD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_WAEHRUNG_FREMD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_WAEHRUNG_FREMD", calNewValueFormated);
	}
		public Long get_ID_WAEHRUNG_FREMD_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_WAEHRUNG_FREMD").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_WAEHRUNG_FREMD_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG_FREMD").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_WAEHRUNG_FREMD_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_WAEHRUNG_FREMD").getDoubleValue();
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
	public String get_ID_WAEHRUNG_FREMD_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_FREMD_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_WAEHRUNG_FREMD_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_WAEHRUNG_FREMD_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_WAEHRUNG_FREMD_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG_FREMD").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_WAEHRUNG_FREMD_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_WAEHRUNG_FREMD").getBigDecimalValue();
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
	
	
	public String get_LAGER_VORZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAGER_VORZEICHEN");
	}

	public String get_LAGER_VORZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("LAGER_VORZEICHEN");	
	}

	public String get_LAGER_VORZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAGER_VORZEICHEN");
	}

	public String get_LAGER_VORZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAGER_VORZEICHEN",cNotNullValue);
	}

	public String get_LAGER_VORZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAGER_VORZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAGER_VORZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAGER_VORZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAGER_VORZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAGER_VORZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAGER_VORZEICHEN", calNewValueFormated);
	}
		public Long get_LAGER_VORZEICHEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LAGER_VORZEICHEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LAGER_VORZEICHEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LAGER_VORZEICHEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LAGER_VORZEICHEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LAGER_VORZEICHEN").getDoubleValue();
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
	public String get_LAGER_VORZEICHEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGER_VORZEICHEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LAGER_VORZEICHEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAGER_VORZEICHEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LAGER_VORZEICHEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LAGER_VORZEICHEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LAGER_VORZEICHEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LAGER_VORZEICHEN").getBigDecimalValue();
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
		public String get_LIEFERBEDINGUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERBEDINGUNGEN");
	}

	public String get_LIEFERBEDINGUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERBEDINGUNGEN");	
	}

	public String get_LIEFERBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERBEDINGUNGEN");
	}

	public String get_LIEFERBEDINGUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERBEDINGUNGEN",cNotNullValue);
	}

	public String get_LIEFERBEDINGUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERBEDINGUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", calNewValueFormated);
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
	
	
	public String get_MENGEN_TOLERANZ_PROZENT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MENGEN_TOLERANZ_PROZENT");
	}

	public String get_MENGEN_TOLERANZ_PROZENT_cF() throws myException
	{
		return this.get_FormatedValue("MENGEN_TOLERANZ_PROZENT");	
	}

	public String get_MENGEN_TOLERANZ_PROZENT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGEN_TOLERANZ_PROZENT");
	}

	public String get_MENGEN_TOLERANZ_PROZENT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MENGEN_TOLERANZ_PROZENT",cNotNullValue);
	}

	public String get_MENGEN_TOLERANZ_PROZENT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MENGEN_TOLERANZ_PROZENT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGEN_TOLERANZ_PROZENT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MENGEN_TOLERANZ_PROZENT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MENGEN_TOLERANZ_PROZENT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MENGEN_TOLERANZ_PROZENT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGEN_TOLERANZ_PROZENT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MENGEN_TOLERANZ_PROZENT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGEN_TOLERANZ_PROZENT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGEN_TOLERANZ_PROZENT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGEN_TOLERANZ_PROZENT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGEN_TOLERANZ_PROZENT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MENGEN_TOLERANZ_PROZENT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MENGEN_TOLERANZ_PROZENT", calNewValueFormated);
	}
		public Long get_MENGEN_TOLERANZ_PROZENT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("MENGEN_TOLERANZ_PROZENT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_MENGEN_TOLERANZ_PROZENT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("MENGEN_TOLERANZ_PROZENT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_MENGEN_TOLERANZ_PROZENT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("MENGEN_TOLERANZ_PROZENT").getDoubleValue();
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
	public String get_MENGEN_TOLERANZ_PROZENT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGEN_TOLERANZ_PROZENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_MENGEN_TOLERANZ_PROZENT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_MENGEN_TOLERANZ_PROZENT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_MENGEN_TOLERANZ_PROZENT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("MENGEN_TOLERANZ_PROZENT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_MENGEN_TOLERANZ_PROZENT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("MENGEN_TOLERANZ_PROZENT").getBigDecimalValue();
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
	
	
	public String get_POSITIONSKLASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSITIONSKLASSE");
	}

	public String get_POSITIONSKLASSE_cF() throws myException
	{
		return this.get_FormatedValue("POSITIONSKLASSE");	
	}

	public String get_POSITIONSKLASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSITIONSKLASSE");
	}

	public String get_POSITIONSKLASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSITIONSKLASSE",cNotNullValue);
	}

	public String get_POSITIONSKLASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSITIONSKLASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSITIONSKLASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSITIONSKLASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSITIONSKLASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSKLASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITIONSKLASSE", calNewValueFormated);
	}
		public String get_POSITIONSNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSITIONSNUMMER");
	}

	public String get_POSITIONSNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("POSITIONSNUMMER");	
	}

	public String get_POSITIONSNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSITIONSNUMMER");
	}

	public String get_POSITIONSNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSITIONSNUMMER",cNotNullValue);
	}

	public String get_POSITIONSNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSITIONSNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSITIONSNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSITIONSNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSITIONSNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITIONSNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITIONSNUMMER", calNewValueFormated);
	}
		public Long get_POSITIONSNUMMER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("POSITIONSNUMMER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_POSITIONSNUMMER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("POSITIONSNUMMER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_POSITIONSNUMMER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("POSITIONSNUMMER").getDoubleValue();
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
	public String get_POSITIONSNUMMER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSITIONSNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_POSITIONSNUMMER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSITIONSNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_POSITIONSNUMMER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("POSITIONSNUMMER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_POSITIONSNUMMER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("POSITIONSNUMMER").getBigDecimalValue();
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
	
	
	public String get_POSITION_ACTIVE_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSITION_ACTIVE");
	}

	public String get_POSITION_ACTIVE_cF() throws myException
	{
		return this.get_FormatedValue("POSITION_ACTIVE");	
	}

	public String get_POSITION_ACTIVE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSITION_ACTIVE");
	}

	public String get_POSITION_ACTIVE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSITION_ACTIVE",cNotNullValue);
	}

	public String get_POSITION_ACTIVE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSITION_ACTIVE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSITION_ACTIVE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSITION_ACTIVE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSITION_ACTIVE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_ACTIVE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_ACTIVE", calNewValueFormated);
	}
		public boolean is_POSITION_ACTIVE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_POSITION_ACTIVE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_POSITION_ACTIVE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_POSITION_ACTIVE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_POSITION_TYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSITION_TYP");
	}

	public String get_POSITION_TYP_cF() throws myException
	{
		return this.get_FormatedValue("POSITION_TYP");	
	}

	public String get_POSITION_TYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSITION_TYP");
	}

	public String get_POSITION_TYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSITION_TYP",cNotNullValue);
	}

	public String get_POSITION_TYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSITION_TYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSITION_TYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSITION_TYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSITION_TYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSITION_TYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_TYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_TYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_TYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_TYP", calNewValueFormated);
	}
		public String get_PREIS_MANUELL_cUF() throws myException
	{
		return this.get_UnFormatedValue("PREIS_MANUELL");
	}

	public String get_PREIS_MANUELL_cF() throws myException
	{
		return this.get_FormatedValue("PREIS_MANUELL");	
	}

	public String get_PREIS_MANUELL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREIS_MANUELL");
	}

	public String get_PREIS_MANUELL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PREIS_MANUELL",cNotNullValue);
	}

	public String get_PREIS_MANUELL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PREIS_MANUELL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PREIS_MANUELL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PREIS_MANUELL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PREIS_MANUELL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PREIS_MANUELL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_MANUELL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PREIS_MANUELL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_MANUELL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS_MANUELL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_MANUELL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS_MANUELL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PREIS_MANUELL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PREIS_MANUELL", calNewValueFormated);
	}
		public boolean is_PREIS_MANUELL_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREIS_MANUELL_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PREIS_MANUELL_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PREIS_MANUELL_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
	
	
	public String get_TYP_25_TO_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYP_25_TO");
	}

	public String get_TYP_25_TO_cF() throws myException
	{
		return this.get_FormatedValue("TYP_25_TO");	
	}

	public String get_TYP_25_TO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYP_25_TO");
	}

	public String get_TYP_25_TO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYP_25_TO",cNotNullValue);
	}

	public String get_TYP_25_TO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYP_25_TO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_25_TO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYP_25_TO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYP_25_TO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYP_25_TO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_25_TO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYP_25_TO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_25_TO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_25_TO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_25_TO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_25_TO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_25_TO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_25_TO", calNewValueFormated);
	}
		public boolean is_TYP_25_TO_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TYP_25_TO_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TYP_25_TO_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TYP_25_TO_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_TYP_LADUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("TYP_LADUNG");
	}

	public String get_TYP_LADUNG_cF() throws myException
	{
		return this.get_FormatedValue("TYP_LADUNG");	
	}

	public String get_TYP_LADUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TYP_LADUNG");
	}

	public String get_TYP_LADUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TYP_LADUNG",cNotNullValue);
	}

	public String get_TYP_LADUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TYP_LADUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_LADUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TYP_LADUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TYP_LADUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TYP_LADUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_LADUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TYP_LADUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_LADUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_LADUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_LADUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_LADUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TYP_LADUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TYP_LADUNG", calNewValueFormated);
	}
		public boolean is_TYP_LADUNG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_TYP_LADUNG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_TYP_LADUNG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_TYP_LADUNG_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_VORGANG_TYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("VORGANG_TYP");
	}

	public String get_VORGANG_TYP_cF() throws myException
	{
		return this.get_FormatedValue("VORGANG_TYP");	
	}

	public String get_VORGANG_TYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORGANG_TYP");
	}

	public String get_VORGANG_TYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VORGANG_TYP",cNotNullValue);
	}

	public String get_VORGANG_TYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VORGANG_TYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VORGANG_TYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VORGANG_TYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VORGANG_TYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_TYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_TYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VORGANG_TYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VORGANG_TYP", calNewValueFormated);
	}
		public String get_WAEHRUNGSKURS_cUF() throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNGSKURS");
	}

	public String get_WAEHRUNGSKURS_cF() throws myException
	{
		return this.get_FormatedValue("WAEHRUNGSKURS");	
	}

	public String get_WAEHRUNGSKURS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WAEHRUNGSKURS");
	}

	public String get_WAEHRUNGSKURS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WAEHRUNGSKURS",cNotNullValue);
	}

	public String get_WAEHRUNGSKURS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WAEHRUNGSKURS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WAEHRUNGSKURS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WAEHRUNGSKURS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WAEHRUNGSKURS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WAEHRUNGSKURS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WAEHRUNGSKURS", calNewValueFormated);
	}
		public Double get_WAEHRUNGSKURS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WAEHRUNGSKURS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WAEHRUNGSKURS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WAEHRUNGSKURS").getDoubleValue();
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
	public String get_WAEHRUNGSKURS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WAEHRUNGSKURS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_WAEHRUNGSKURS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WAEHRUNGSKURS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_WAEHRUNGSKURS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WAEHRUNGSKURS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WAEHRUNGSKURS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WAEHRUNGSKURS").getBigDecimalValue();
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
	
	
	public String get_WECHSELKURS_cUF() throws myException
	{
		return this.get_UnFormatedValue("WECHSELKURS");
	}

	public String get_WECHSELKURS_cF() throws myException
	{
		return this.get_FormatedValue("WECHSELKURS");	
	}

	public String get_WECHSELKURS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WECHSELKURS");
	}

	public String get_WECHSELKURS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WECHSELKURS",cNotNullValue);
	}

	public String get_WECHSELKURS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WECHSELKURS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WECHSELKURS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WECHSELKURS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WECHSELKURS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WECHSELKURS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELKURS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WECHSELKURS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELKURS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WECHSELKURS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELKURS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WECHSELKURS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WECHSELKURS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WECHSELKURS", calNewValueFormated);
	}
		public Double get_WECHSELKURS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WECHSELKURS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WECHSELKURS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WECHSELKURS").getDoubleValue();
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
	public String get_WECHSELKURS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WECHSELKURS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_WECHSELKURS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WECHSELKURS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_WECHSELKURS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WECHSELKURS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WECHSELKURS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WECHSELKURS").getBigDecimalValue();
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
	
	
	public String get_ZAHLUNGSBEDINGUNGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ZAHLUNGSBEDINGUNGEN_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBEDINGUNGEN");	
	}

	public String get_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSBEDINGUNGEN");
	}

	public String get_ZAHLUNGSBEDINGUNGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBEDINGUNGEN",cNotNullValue);
	}

	public String get_ZAHLUNGSBEDINGUNGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBEDINGUNGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public String get_ZAHLUNGSBED_CALC_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBED_CALC_DATUM");
	}

	public String get_ZAHLUNGSBED_CALC_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBED_CALC_DATUM");	
	}

	public String get_ZAHLUNGSBED_CALC_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHLUNGSBED_CALC_DATUM");
	}

	public String get_ZAHLUNGSBED_CALC_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHLUNGSBED_CALC_DATUM",cNotNullValue);
	}

	public String get_ZAHLUNGSBED_CALC_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHLUNGSBED_CALC_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHLUNGSBED_CALC_DATUM", calNewValueFormated);
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
	put("ANR1",MyRECORD.DATATYPES.TEXT);
	put("ANR2",MyRECORD.DATATYPES.TEXT);
	put("ANZAHL",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ANZAHL_ABZUG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ARTBEZ1",MyRECORD.DATATYPES.TEXT);
	put("ARTBEZ2",MyRECORD.DATATYPES.TEXT);
	put("AUSFUEHRUNGSDATUM",MyRECORD.DATATYPES.DATE);
	put("BEMERKUNG_INTERN",MyRECORD.DATATYPES.TEXT);
	put("BESTELLNUMMER",MyRECORD.DATATYPES.TEXT);
	put("BOERSENKURS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("BOERSE_DIFF_ABS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("BOERSE_DIFF_PROZ",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("DELETED",MyRECORD.DATATYPES.YESNO);
	put("DEL_DATE",MyRECORD.DATATYPES.DATE);
	put("DEL_GRUND",MyRECORD.DATATYPES.TEXT);
	put("DEL_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("EINHEITKURZ",MyRECORD.DATATYPES.TEXT);
	put("EINHEIT_PREIS_KURZ",MyRECORD.DATATYPES.TEXT);
	put("EINZELPREIS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EINZELPREIS_ABZUG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EINZELPREIS_ABZUG_FW",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EINZELPREIS_FW",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EINZELPREIS_RESULT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EINZELPREIS_RESULT_FW",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EUCODE",MyRECORD.DATATYPES.TEXT);
	put("EUNOTIZ",MyRECORD.DATATYPES.TEXT);
	put("FIXIERUNGSTAG",MyRECORD.DATATYPES.DATE);
	put("FIXMONAT",MyRECORD.DATATYPES.NUMBER);
	put("FIXTAG",MyRECORD.DATATYPES.NUMBER);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEBUCHT",MyRECORD.DATATYPES.YESNO);
	put("GESAMTPREIS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GESAMTPREIS_ABZUG",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GESAMTPREIS_ABZUG_FW",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GESAMTPREIS_FW",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("ID_ADRESSE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER_START",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_LAGER_ZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_STRECKEN_DEF",MyRECORD.DATATYPES.NUMBER);
	put("ID_VKOPF_KON",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_KON",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_KON_ZUGEORD",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_ZUGEORD",MyRECORD.DATATYPES.NUMBER);
	put("ID_WAEHRUNG_FREMD",MyRECORD.DATATYPES.NUMBER);
	put("ID_ZAHLUNGSBEDINGUNGEN",MyRECORD.DATATYPES.NUMBER);
	put("LAGER_VORZEICHEN",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERBEDINGUNGEN",MyRECORD.DATATYPES.TEXT);
	put("MENGENDIVISOR",MyRECORD.DATATYPES.NUMBER);
	put("MENGEN_TOLERANZ_PROZENT",MyRECORD.DATATYPES.NUMBER);
	put("POSITIONSKLASSE",MyRECORD.DATATYPES.TEXT);
	put("POSITIONSNUMMER",MyRECORD.DATATYPES.NUMBER);
	put("POSITION_ACTIVE",MyRECORD.DATATYPES.YESNO);
	put("POSITION_TYP",MyRECORD.DATATYPES.TEXT);
	put("PREIS_MANUELL",MyRECORD.DATATYPES.YESNO);
	put("SKONTO_PROZENT",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("STEUERSATZ",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TYP_25_TO",MyRECORD.DATATYPES.YESNO);
	put("TYP_LADUNG",MyRECORD.DATATYPES.YESNO);
	put("VORGANG_TYP",MyRECORD.DATATYPES.TEXT);
	put("WAEHRUNGSKURS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("WECHSELKURS",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("WIEGEKARTENKENNER",MyRECORD.DATATYPES.TEXT);
	put("ZAHLTAGE",MyRECORD.DATATYPES.NUMBER);
	put("ZAHLUNGSBEDINGUNGEN",MyRECORD.DATATYPES.TEXT);
	put("ZAHLUNGSBED_CALC_DATUM",MyRECORD.DATATYPES.DATE);
	put("ZOLLTARIFNR",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_VPOS_KON.HM_FIELDS;	
	}

}
