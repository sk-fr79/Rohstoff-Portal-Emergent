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

public class RECORD_LAGER_PALETTE extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_LAGER_PALETTE";
    public static String IDFIELD   = "ID_LAGER_PALETTE";
    

	//erzeugen eines RECORDNEW_JT_LAGER_PALETTE - felds
	private RECORDNEW_LAGER_PALETTE   recNEW = null;

    private _TAB  tab = _TAB.lager_palette;  



	public RECORD_LAGER_PALETTE() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_LAGER_PALETTE");
	}


	public RECORD_LAGER_PALETTE(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_LAGER_PALETTE");
	}



	public RECORD_LAGER_PALETTE(RECORD_LAGER_PALETTE recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_LAGER_PALETTE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_PALETTE.TABLENAME);
	}


	//2014-04-03
	public RECORD_LAGER_PALETTE(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_LAGER_PALETTE");
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_PALETTE.TABLENAME);
	}




	public RECORD_LAGER_PALETTE(long lID_Unformated) throws myException
	{
		super("JT_LAGER_PALETTE","ID_LAGER_PALETTE",""+lID_Unformated);
		this.set_TABLE_NAME("JT_LAGER_PALETTE");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_PALETTE.TABLENAME);
	}

	public RECORD_LAGER_PALETTE(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_LAGER_PALETTE");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_PALETTE", "ID_LAGER_PALETTE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_PALETTE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_PALETTE.TABLENAME);
	}
	
	

	public RECORD_LAGER_PALETTE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_LAGER_PALETTE","ID_LAGER_PALETTE",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_LAGER_PALETTE");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_PALETTE.TABLENAME);

	}


	public RECORD_LAGER_PALETTE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_LAGER_PALETTE");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_PALETTE", "ID_LAGER_PALETTE="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_PALETTE", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_LAGER_PALETTE.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_LAGER_PALETTE();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_LAGER_PALETTE.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_LAGER_PALETTE";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_LAGER_PALETTE_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_LAGER_PALETTE_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAGER_PALETTE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_LAGER_PALETTE", bibE2.cTO(), "ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_LAGER_PALETTE was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_LAGER_PALETTE", bibE2.cTO(), "ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF();
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
			if (S.isFull(this.get_ID_LAGER_PALETTE_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_LAGER_PALETTE", "ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_LAGER_PALETTE get_RECORDNEW_LAGER_PALETTE() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_LAGER_PALETTE();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_LAGER_PALETTE(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_LAGER_PALETTE create_Instance() throws myException {
		return new RECORD_LAGER_PALETTE();
	}
	
	public static RECORD_LAGER_PALETTE create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_LAGER_PALETTE(Conn);
    }

	public static RECORD_LAGER_PALETTE create_Instance(RECORD_LAGER_PALETTE recordOrig) {
		return new RECORD_LAGER_PALETTE(recordOrig);
    }

	public static RECORD_LAGER_PALETTE create_Instance(long lID_Unformated) throws myException {
		return new RECORD_LAGER_PALETTE(lID_Unformated);
    }

	public static RECORD_LAGER_PALETTE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_LAGER_PALETTE(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_LAGER_PALETTE create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_LAGER_PALETTE(lID_Unformated, Conn);
	}

	public static RECORD_LAGER_PALETTE create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_LAGER_PALETTE(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_LAGER_PALETTE create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_LAGER_PALETTE(recordOrig);	    
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
    public RECORD_LAGER_PALETTE build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE WHERE ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF());
      }
      //return new RECORD_LAGER_PALETTE(this.get_cSQL_4_Build());
      RECORD_LAGER_PALETTE rec = new RECORD_LAGER_PALETTE();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_LAGER_PALETTE
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_LAGER_PALETTE-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_LAGER_PALETTE get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_LAGER_PALETTE_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_LAGER_PALETTE  recNew = new RECORDNEW_LAGER_PALETTE();
		
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
    public RECORD_LAGER_PALETTE set_recordNew(RECORDNEW_LAGER_PALETTE recnew) throws myException {
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
	
	



		private RECORD_USER UP_RECORD_USER_endkontrolle_von = null;




		private RECORD_USER UP_RECORD_USER_gepresst_von = null;




		private RECORD_USER UP_RECORD_USER_palettiert_von = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_ausbuch_hand = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_einbuch_hand = null;




		private RECORD_ARTIKEL_BEZ UP_RECORD_ARTIKEL_BEZ_id_artikel_bez = null;




		private RECORD_LAGER_BOX UP_RECORD_LAGER_BOX_id_lager_box = null;




		private RECLIST_LAGER_PALETTE_BOX DOWN_RECLIST_LAGER_PALETTE_BOX_id_lager_palette = null ;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_aus = null;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_ein = null;






	
	/**
	 * References the Field ENDKONTROLLE_VON
	 * Falls keine this.get_ENDKONTROLLE_VON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_endkontrolle_von() throws myException
	{
		if (S.isEmpty(this.get_ENDKONTROLLE_VON_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_endkontrolle_von==null)
		{
			this.UP_RECORD_USER_endkontrolle_von = new RECORD_USER(this.get_ENDKONTROLLE_VON_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_endkontrolle_von;
	}
	





	
	/**
	 * References the Field GEPRESST_VON
	 * Falls keine this.get_GEPRESST_VON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_gepresst_von() throws myException
	{
		if (S.isEmpty(this.get_GEPRESST_VON_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_gepresst_von==null)
		{
			this.UP_RECORD_USER_gepresst_von = new RECORD_USER(this.get_GEPRESST_VON_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_gepresst_von;
	}
	





	
	/**
	 * References the Field PALETTIERT_VON
	 * Falls keine this.get_PALETTIERT_VON_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_USER get_UP_RECORD_USER_palettiert_von() throws myException
	{
		if (S.isEmpty(this.get_PALETTIERT_VON_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER_palettiert_von==null)
		{
			this.UP_RECORD_USER_palettiert_von = new RECORD_USER(this.get_PALETTIERT_VON_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER_palettiert_von;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_AUSBUCH_HAND
	 * Falls keine this.get_ID_ADRESSE_AUSBUCH_HAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_ausbuch_hand() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_AUSBUCH_HAND_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_ausbuch_hand==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_ausbuch_hand = new RECORD_ADRESSE(this.get_ID_ADRESSE_AUSBUCH_HAND_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_ausbuch_hand;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_EINBUCH_HAND
	 * Falls keine this.get_ID_ADRESSE_EINBUCH_HAND_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_einbuch_hand() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_EINBUCH_HAND_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_einbuch_hand==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_einbuch_hand = new RECORD_ADRESSE(this.get_ID_ADRESSE_EINBUCH_HAND_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_einbuch_hand;
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
	 * References the Field ID_LAGER_BOX
	 * Falls keine this.get_ID_LAGER_BOX_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAGER_BOX get_UP_RECORD_LAGER_BOX_id_lager_box() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_BOX_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAGER_BOX_id_lager_box==null)
		{
			this.UP_RECORD_LAGER_BOX_id_lager_box = new RECORD_LAGER_BOX(this.get_ID_LAGER_BOX_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAGER_BOX_id_lager_box;
	}
	





	/**
	 * References the Field ID_LAGER_PALETTE 
	 * Falls keine get_ID_LAGER_PALETTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE_BOX get_DOWN_RECORD_LIST_LAGER_PALETTE_BOX_id_lager_palette() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_PALETTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_BOX_id_lager_palette==null)
		{
			this.DOWN_RECLIST_LAGER_PALETTE_BOX_id_lager_palette = new RECLIST_LAGER_PALETTE_BOX (
			       "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE_BOX WHERE ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF()+" ORDER BY ID_LAGER_PALETTE_BOX",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_BOX_id_lager_palette;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_LAGER_PALETTE 
	 * Falls keine get_ID_LAGER_PALETTE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_LAGER_PALETTE_BOX get_DOWN_RECORD_LIST_LAGER_PALETTE_BOX_id_lager_palette(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_PALETTE_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_LAGER_PALETTE_BOX_id_lager_palette==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_LAGER_PALETTE_BOX WHERE ID_LAGER_PALETTE="+this.get_ID_LAGER_PALETTE_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_LAGER_PALETTE_BOX_id_lager_palette = new RECLIST_LAGER_PALETTE_BOX (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_LAGER_PALETTE_BOX_id_lager_palette;
	}


	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE_AUS
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_AUS_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_aus() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_AUS_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_aus==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_aus = new RECORD_VPOS_TPA_FUHRE(this.get_ID_VPOS_TPA_FUHRE_AUS_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_aus;
	}
	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE_EIN
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_EIN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_ein() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_EIN_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_ein==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_ein = new RECORD_VPOS_TPA_FUHRE(this.get_ID_VPOS_TPA_FUHRE_EIN_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_ein;
	}
	

	public static String FIELD__AUSBUCHUNG_HAND = "AUSBUCHUNG_HAND";
	public static String FIELD__BRUTTOMENGE = "BRUTTOMENGE";
	public static String FIELD__BUCHUNGSNR_HAND = "BUCHUNGSNR_HAND";
	public static String FIELD__CHARGENNUMMER = "CHARGENNUMMER";
	public static String FIELD__DATUM_VERARBEITET = "DATUM_VERARBEITET";
	public static String FIELD__EINBUCHUNG_HAND = "EINBUCHUNG_HAND";
	public static String FIELD__ENDKONTROLLE_VON = "ENDKONTROLLE_VON";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GEPRESST_VON = "GEPRESST_VON";
	public static String FIELD__HAND_AUSBUCHUNG_BEMERKUNG = "HAND_AUSBUCHUNG_BEMERKUNG";
	public static String FIELD__ID_ADRESSE_AUSBUCH_HAND = "ID_ADRESSE_AUSBUCH_HAND";
	public static String FIELD__ID_ADRESSE_EINBUCH_HAND = "ID_ADRESSE_EINBUCH_HAND";
	public static String FIELD__ID_ARTIKEL_BEZ = "ID_ARTIKEL_BEZ";
	public static String FIELD__ID_LAGER_BOX = "ID_LAGER_BOX";
	public static String FIELD__ID_LAGER_PALETTE = "ID_LAGER_PALETTE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VPOS_TPA_FUHRE_AUS = "ID_VPOS_TPA_FUHRE_AUS";
	public static String FIELD__ID_VPOS_TPA_FUHRE_EIN = "ID_VPOS_TPA_FUHRE_EIN";
	public static String FIELD__IST_PALETTE = "IST_PALETTE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MATERIAL_ZUSATZINFO = "MATERIAL_ZUSATZINFO";
	public static String FIELD__NETTOMENGE = "NETTOMENGE";
	public static String FIELD__PALETTIERT_VON = "PALETTIERT_VON";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TARAMENGE = "TARAMENGE";


	public String get_AUSBUCHUNG_HAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("AUSBUCHUNG_HAND");
	}

	public String get_AUSBUCHUNG_HAND_cF() throws myException
	{
		return this.get_FormatedValue("AUSBUCHUNG_HAND");	
	}

	public String get_AUSBUCHUNG_HAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUSBUCHUNG_HAND");
	}

	public String get_AUSBUCHUNG_HAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AUSBUCHUNG_HAND",cNotNullValue);
	}

	public String get_AUSBUCHUNG_HAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AUSBUCHUNG_HAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AUSBUCHUNG_HAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AUSBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AUSBUCHUNG_HAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AUSBUCHUNG_HAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AUSBUCHUNG_HAND", calNewValueFormated);
	}
		public boolean is_AUSBUCHUNG_HAND_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_AUSBUCHUNG_HAND_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_AUSBUCHUNG_HAND_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_AUSBUCHUNG_HAND_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_BRUTTOMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BRUTTOMENGE");
	}

	public String get_BRUTTOMENGE_cF() throws myException
	{
		return this.get_FormatedValue("BRUTTOMENGE");	
	}

	public String get_BRUTTOMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BRUTTOMENGE");
	}

	public String get_BRUTTOMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BRUTTOMENGE",cNotNullValue);
	}

	public String get_BRUTTOMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BRUTTOMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BRUTTOMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BRUTTOMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BRUTTOMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BRUTTOMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BRUTTOMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BRUTTOMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BRUTTOMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BRUTTOMENGE", calNewValueFormated);
	}
		public Double get_BRUTTOMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("BRUTTOMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_BRUTTOMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("BRUTTOMENGE").getDoubleValue();
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
	public String get_BRUTTOMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BRUTTOMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_BRUTTOMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_BRUTTOMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_BRUTTOMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("BRUTTOMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_BRUTTOMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("BRUTTOMENGE").getBigDecimalValue();
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
	
	
	public String get_BUCHUNGSNR_HAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNR_HAND");
	}

	public String get_BUCHUNGSNR_HAND_cF() throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNR_HAND");	
	}

	public String get_BUCHUNGSNR_HAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUCHUNGSNR_HAND");
	}

	public String get_BUCHUNGSNR_HAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUCHUNGSNR_HAND",cNotNullValue);
	}

	public String get_BUCHUNGSNR_HAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUCHUNGSNR_HAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUCHUNGSNR_HAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUCHUNGSNR_HAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUCHUNGSNR_HAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUCHUNGSNR_HAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUCHUNGSNR_HAND", calNewValueFormated);
	}
		public String get_CHARGENNUMMER_cUF() throws myException
	{
		return this.get_UnFormatedValue("CHARGENNUMMER");
	}

	public String get_CHARGENNUMMER_cF() throws myException
	{
		return this.get_FormatedValue("CHARGENNUMMER");	
	}

	public String get_CHARGENNUMMER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("CHARGENNUMMER");
	}

	public String get_CHARGENNUMMER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("CHARGENNUMMER",cNotNullValue);
	}

	public String get_CHARGENNUMMER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("CHARGENNUMMER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("CHARGENNUMMER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_CHARGENNUMMER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("CHARGENNUMMER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("CHARGENNUMMER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CHARGENNUMMER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CHARGENNUMMER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_CHARGENNUMMER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("CHARGENNUMMER", calNewValueFormated);
	}
		public Long get_CHARGENNUMMER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("CHARGENNUMMER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_CHARGENNUMMER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("CHARGENNUMMER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_CHARGENNUMMER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("CHARGENNUMMER").getDoubleValue();
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
	public String get_CHARGENNUMMER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_CHARGENNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_CHARGENNUMMER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_CHARGENNUMMER_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_CHARGENNUMMER_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("CHARGENNUMMER").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_CHARGENNUMMER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("CHARGENNUMMER").getBigDecimalValue();
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
	
	
	public String get_DATUM_VERARBEITET_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATUM_VERARBEITET");
	}

	public String get_DATUM_VERARBEITET_cF() throws myException
	{
		return this.get_FormatedValue("DATUM_VERARBEITET");	
	}

	public String get_DATUM_VERARBEITET_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATUM_VERARBEITET");
	}

	public String get_DATUM_VERARBEITET_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATUM_VERARBEITET",cNotNullValue);
	}

	public String get_DATUM_VERARBEITET_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATUM_VERARBEITET",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATUM_VERARBEITET", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATUM_VERARBEITET(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATUM_VERARBEITET", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATUM_VERARBEITET_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATUM_VERARBEITET", calNewValueFormated);
	}
		public String get_EINBUCHUNG_HAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINBUCHUNG_HAND");
	}

	public String get_EINBUCHUNG_HAND_cF() throws myException
	{
		return this.get_FormatedValue("EINBUCHUNG_HAND");	
	}

	public String get_EINBUCHUNG_HAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINBUCHUNG_HAND");
	}

	public String get_EINBUCHUNG_HAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINBUCHUNG_HAND",cNotNullValue);
	}

	public String get_EINBUCHUNG_HAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINBUCHUNG_HAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINBUCHUNG_HAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINBUCHUNG_HAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINBUCHUNG_HAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINBUCHUNG_HAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINBUCHUNG_HAND", calNewValueFormated);
	}
		public boolean is_EINBUCHUNG_HAND_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_EINBUCHUNG_HAND_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_EINBUCHUNG_HAND_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_EINBUCHUNG_HAND_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ENDKONTROLLE_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ENDKONTROLLE_VON");
	}

	public String get_ENDKONTROLLE_VON_cF() throws myException
	{
		return this.get_FormatedValue("ENDKONTROLLE_VON");	
	}

	public String get_ENDKONTROLLE_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ENDKONTROLLE_VON");
	}

	public String get_ENDKONTROLLE_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ENDKONTROLLE_VON",cNotNullValue);
	}

	public String get_ENDKONTROLLE_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ENDKONTROLLE_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ENDKONTROLLE_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ENDKONTROLLE_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ENDKONTROLLE_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ENDKONTROLLE_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ENDKONTROLLE_VON", calNewValueFormated);
	}
		public Long get_ENDKONTROLLE_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ENDKONTROLLE_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ENDKONTROLLE_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ENDKONTROLLE_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ENDKONTROLLE_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ENDKONTROLLE_VON").getDoubleValue();
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
	public String get_ENDKONTROLLE_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ENDKONTROLLE_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ENDKONTROLLE_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ENDKONTROLLE_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ENDKONTROLLE_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ENDKONTROLLE_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ENDKONTROLLE_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ENDKONTROLLE_VON").getBigDecimalValue();
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
		public String get_GEPRESST_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEPRESST_VON");
	}

	public String get_GEPRESST_VON_cF() throws myException
	{
		return this.get_FormatedValue("GEPRESST_VON");	
	}

	public String get_GEPRESST_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEPRESST_VON");
	}

	public String get_GEPRESST_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEPRESST_VON",cNotNullValue);
	}

	public String get_GEPRESST_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEPRESST_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEPRESST_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GEPRESST_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GEPRESST_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GEPRESST_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEPRESST_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEPRESST_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GEPRESST_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GEPRESST_VON", calNewValueFormated);
	}
		public Long get_GEPRESST_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("GEPRESST_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_GEPRESST_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("GEPRESST_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_GEPRESST_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("GEPRESST_VON").getDoubleValue();
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
	public String get_GEPRESST_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEPRESST_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_GEPRESST_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_GEPRESST_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_GEPRESST_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("GEPRESST_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_GEPRESST_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("GEPRESST_VON").getBigDecimalValue();
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
	
	
	public String get_HAND_AUSBUCHUNG_BEMERKUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("HAND_AUSBUCHUNG_BEMERKUNG");
	}

	public String get_HAND_AUSBUCHUNG_BEMERKUNG_cF() throws myException
	{
		return this.get_FormatedValue("HAND_AUSBUCHUNG_BEMERKUNG");	
	}

	public String get_HAND_AUSBUCHUNG_BEMERKUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HAND_AUSBUCHUNG_BEMERKUNG");
	}

	public String get_HAND_AUSBUCHUNG_BEMERKUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HAND_AUSBUCHUNG_BEMERKUNG",cNotNullValue);
	}

	public String get_HAND_AUSBUCHUNG_BEMERKUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HAND_AUSBUCHUNG_BEMERKUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_HAND_AUSBUCHUNG_BEMERKUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("HAND_AUSBUCHUNG_BEMERKUNG", calNewValueFormated);
	}
		public String get_ID_ADRESSE_AUSBUCH_HAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_AUSBUCH_HAND");
	}

	public String get_ID_ADRESSE_AUSBUCH_HAND_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_AUSBUCH_HAND");	
	}

	public String get_ID_ADRESSE_AUSBUCH_HAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_AUSBUCH_HAND");
	}

	public String get_ID_ADRESSE_AUSBUCH_HAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_AUSBUCH_HAND",cNotNullValue);
	}

	public String get_ID_ADRESSE_AUSBUCH_HAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_AUSBUCH_HAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_AUSBUCH_HAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_AUSBUCH_HAND", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_AUSBUCH_HAND_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_AUSBUCH_HAND").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_AUSBUCH_HAND_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_AUSBUCH_HAND").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_AUSBUCH_HAND_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_AUSBUCH_HAND").getDoubleValue();
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
	public String get_ID_ADRESSE_AUSBUCH_HAND_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_AUSBUCH_HAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_AUSBUCH_HAND_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_AUSBUCH_HAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_AUSBUCH_HAND_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_AUSBUCH_HAND").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_AUSBUCH_HAND_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_AUSBUCH_HAND").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_EINBUCH_HAND_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_EINBUCH_HAND");
	}

	public String get_ID_ADRESSE_EINBUCH_HAND_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_EINBUCH_HAND");	
	}

	public String get_ID_ADRESSE_EINBUCH_HAND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_EINBUCH_HAND");
	}

	public String get_ID_ADRESSE_EINBUCH_HAND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_EINBUCH_HAND",cNotNullValue);
	}

	public String get_ID_ADRESSE_EINBUCH_HAND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_EINBUCH_HAND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_EINBUCH_HAND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_EINBUCH_HAND", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_EINBUCH_HAND_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_EINBUCH_HAND").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_EINBUCH_HAND_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_EINBUCH_HAND").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_EINBUCH_HAND_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_EINBUCH_HAND").getDoubleValue();
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
	public String get_ID_ADRESSE_EINBUCH_HAND_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_EINBUCH_HAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_EINBUCH_HAND_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_EINBUCH_HAND_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_EINBUCH_HAND_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_EINBUCH_HAND").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_EINBUCH_HAND_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_EINBUCH_HAND").getBigDecimalValue();
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
	
	
	public String get_ID_LAGER_BOX_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_BOX");
	}

	public String get_ID_LAGER_BOX_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGER_BOX");	
	}

	public String get_ID_LAGER_BOX_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGER_BOX");
	}

	public String get_ID_LAGER_BOX_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_BOX",cNotNullValue);
	}

	public String get_ID_LAGER_BOX_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGER_BOX",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGER_BOX", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_BOX(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGER_BOX", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_BOX_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_BOX", calNewValueFormated);
	}
		public Long get_ID_LAGER_BOX_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGER_BOX").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGER_BOX_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGER_BOX").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGER_BOX_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGER_BOX").getDoubleValue();
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
	public String get_ID_LAGER_BOX_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_BOX_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAGER_BOX_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_BOX_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAGER_BOX_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_BOX").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGER_BOX_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_BOX").getBigDecimalValue();
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
	
	
	public String get_ID_LAGER_PALETTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_PALETTE");
	}

	public String get_ID_LAGER_PALETTE_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGER_PALETTE");	
	}

	public String get_ID_LAGER_PALETTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGER_PALETTE");
	}

	public String get_ID_LAGER_PALETTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_PALETTE",cNotNullValue);
	}

	public String get_ID_LAGER_PALETTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGER_PALETTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGER_PALETTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_PALETTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGER_PALETTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_PALETTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_PALETTE", calNewValueFormated);
	}
		public Long get_ID_LAGER_PALETTE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGER_PALETTE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGER_PALETTE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGER_PALETTE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGER_PALETTE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGER_PALETTE").getDoubleValue();
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
	public String get_ID_LAGER_PALETTE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_PALETTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_LAGER_PALETTE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_PALETTE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_LAGER_PALETTE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_PALETTE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGER_PALETTE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_PALETTE").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_AUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_AUS");
	}

	public String get_ID_VPOS_TPA_FUHRE_AUS_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_AUS");	
	}

	public String get_ID_VPOS_TPA_FUHRE_AUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_AUS");
	}

	public String get_ID_VPOS_TPA_FUHRE_AUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_AUS",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_AUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_AUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_AUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_AUS", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_AUS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_AUS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_AUS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_AUS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_AUS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_AUS").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_AUS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_AUS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_AUS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_AUS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_AUS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_AUS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_AUS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_AUS").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_EIN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_EIN");
	}

	public String get_ID_VPOS_TPA_FUHRE_EIN_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_EIN");	
	}

	public String get_ID_VPOS_TPA_FUHRE_EIN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_EIN");
	}

	public String get_ID_VPOS_TPA_FUHRE_EIN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_EIN",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_EIN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_EIN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_EIN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_EIN", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_EIN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_EIN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_EIN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_EIN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_EIN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_EIN").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_EIN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_EIN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VPOS_TPA_FUHRE_EIN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_EIN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VPOS_TPA_FUHRE_EIN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_EIN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_EIN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_EIN").getBigDecimalValue();
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
	
	
	public String get_IST_PALETTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_PALETTE");
	}

	public String get_IST_PALETTE_cF() throws myException
	{
		return this.get_FormatedValue("IST_PALETTE");	
	}

	public String get_IST_PALETTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_PALETTE");
	}

	public String get_IST_PALETTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_PALETTE",cNotNullValue);
	}

	public String get_IST_PALETTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_PALETTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_PALETTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_PALETTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_PALETTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_PALETTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PALETTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PALETTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_PALETTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_PALETTE", calNewValueFormated);
	}
		public boolean is_IST_PALETTE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_PALETTE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_PALETTE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_PALETTE_cUF_NN("N").equals("N"))
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
		public String get_MATERIAL_ZUSATZINFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("MATERIAL_ZUSATZINFO");
	}

	public String get_MATERIAL_ZUSATZINFO_cF() throws myException
	{
		return this.get_FormatedValue("MATERIAL_ZUSATZINFO");	
	}

	public String get_MATERIAL_ZUSATZINFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MATERIAL_ZUSATZINFO");
	}

	public String get_MATERIAL_ZUSATZINFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MATERIAL_ZUSATZINFO",cNotNullValue);
	}

	public String get_MATERIAL_ZUSATZINFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MATERIAL_ZUSATZINFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MATERIAL_ZUSATZINFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MATERIAL_ZUSATZINFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_ZUSATZINFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MATERIAL_ZUSATZINFO", calNewValueFormated);
	}
		public String get_NETTOMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("NETTOMENGE");
	}

	public String get_NETTOMENGE_cF() throws myException
	{
		return this.get_FormatedValue("NETTOMENGE");	
	}

	public String get_NETTOMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("NETTOMENGE");
	}

	public String get_NETTOMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("NETTOMENGE",cNotNullValue);
	}

	public String get_NETTOMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("NETTOMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("NETTOMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_NETTOMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("NETTOMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("NETTOMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_NETTOMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("NETTOMENGE", calNewValueFormated);
	}
		public Double get_NETTOMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("NETTOMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_NETTOMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("NETTOMENGE").getDoubleValue();
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
	public String get_NETTOMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_NETTOMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_NETTOMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_NETTOMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_NETTOMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("NETTOMENGE").getBigDecimalValue();
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
	
	
	public String get_PALETTIERT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("PALETTIERT_VON");
	}

	public String get_PALETTIERT_VON_cF() throws myException
	{
		return this.get_FormatedValue("PALETTIERT_VON");	
	}

	public String get_PALETTIERT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PALETTIERT_VON");
	}

	public String get_PALETTIERT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PALETTIERT_VON",cNotNullValue);
	}

	public String get_PALETTIERT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PALETTIERT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PALETTIERT_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PALETTIERT_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PALETTIERT_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PALETTIERT_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PALETTIERT_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PALETTIERT_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PALETTIERT_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PALETTIERT_VON", calNewValueFormated);
	}
		public Long get_PALETTIERT_VON_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("PALETTIERT_VON").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_PALETTIERT_VON_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("PALETTIERT_VON").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_PALETTIERT_VON_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("PALETTIERT_VON").getDoubleValue();
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
	public String get_PALETTIERT_VON_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PALETTIERT_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_PALETTIERT_VON_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_PALETTIERT_VON_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_PALETTIERT_VON_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("PALETTIERT_VON").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_PALETTIERT_VON_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("PALETTIERT_VON").getBigDecimalValue();
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
	
	
	public String get_TARAMENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("TARAMENGE");
	}

	public String get_TARAMENGE_cF() throws myException
	{
		return this.get_FormatedValue("TARAMENGE");	
	}

	public String get_TARAMENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TARAMENGE");
	}

	public String get_TARAMENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TARAMENGE",cNotNullValue);
	}

	public String get_TARAMENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TARAMENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TARAMENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TARAMENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TARAMENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TARAMENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TARAMENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TARAMENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TARAMENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TARAMENGE", calNewValueFormated);
	}
		public Double get_TARAMENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("TARAMENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_TARAMENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("TARAMENGE").getDoubleValue();
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
	public String get_TARAMENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TARAMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_TARAMENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_TARAMENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_TARAMENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("TARAMENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_TARAMENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("TARAMENGE").getBigDecimalValue();
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
	put("AUSBUCHUNG_HAND",MyRECORD.DATATYPES.YESNO);
	put("BRUTTOMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("BUCHUNGSNR_HAND",MyRECORD.DATATYPES.TEXT);
	put("CHARGENNUMMER",MyRECORD.DATATYPES.NUMBER);
	put("DATUM_VERARBEITET",MyRECORD.DATATYPES.DATE);
	put("EINBUCHUNG_HAND",MyRECORD.DATATYPES.YESNO);
	put("ENDKONTROLLE_VON",MyRECORD.DATATYPES.NUMBER);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEPRESST_VON",MyRECORD.DATATYPES.NUMBER);
	put("HAND_AUSBUCHUNG_BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("ID_ADRESSE_AUSBUCH_HAND",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_EINBUCH_HAND",MyRECORD.DATATYPES.NUMBER);
	put("ID_ARTIKEL_BEZ",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGER_BOX",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGER_PALETTE",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_AUS",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_EIN",MyRECORD.DATATYPES.NUMBER);
	put("IST_PALETTE",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MATERIAL_ZUSATZINFO",MyRECORD.DATATYPES.TEXT);
	put("NETTOMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("PALETTIERT_VON",MyRECORD.DATATYPES.NUMBER);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TARAMENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_LAGER_PALETTE.HM_FIELDS;	
	}

}
