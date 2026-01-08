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

public class RECORD_ABRECHBLATT_POS extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ABRECHBLATT_POS";
    public static String IDFIELD   = "ID_ABRECHBLATT_POS";
    

	//erzeugen eines RECORDNEW_JT_ABRECHBLATT_POS - felds
	private RECORDNEW_ABRECHBLATT_POS   recNEW = null;

    private _TAB  tab = _TAB.abrechblatt_pos;  



	public RECORD_ABRECHBLATT_POS() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");
	}


	public RECORD_ABRECHBLATT_POS(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");
	}



	public RECORD_ABRECHBLATT_POS(RECORD_ABRECHBLATT_POS recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT_POS.TABLENAME);
	}


	//2014-04-03
	public RECORD_ABRECHBLATT_POS(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT_POS.TABLENAME);
	}




	public RECORD_ABRECHBLATT_POS(long lID_Unformated) throws myException
	{
		super("JT_ABRECHBLATT_POS","ID_ABRECHBLATT_POS",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT_POS.TABLENAME);
	}

	public RECORD_ABRECHBLATT_POS(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT_POS", "ID_ABRECHBLATT_POS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT_POS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT_POS.TABLENAME);
	}
	
	

	public RECORD_ABRECHBLATT_POS(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ABRECHBLATT_POS","ID_ABRECHBLATT_POS",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT_POS.TABLENAME);

	}


	public RECORD_ABRECHBLATT_POS(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ABRECHBLATT_POS");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT_POS", "ID_ABRECHBLATT_POS="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT_POS", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ABRECHBLATT_POS.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ABRECHBLATT_POS();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ABRECHBLATT_POS.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ABRECHBLATT_POS";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ABRECHBLATT_POS_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ABRECHBLATT_POS_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ABRECHBLATT_POS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ABRECHBLATT_POS", bibE2.cTO(), "ID_ABRECHBLATT_POS="+this.get_ID_ABRECHBLATT_POS_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ABRECHBLATT_POS was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ABRECHBLATT_POS", bibE2.cTO(), "ID_ABRECHBLATT_POS="+this.get_ID_ABRECHBLATT_POS_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_ABRECHBLATT_POS="+this.get_ID_ABRECHBLATT_POS_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_ABRECHBLATT_POS="+this.get_ID_ABRECHBLATT_POS_cUF();
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
			if (S.isFull(this.get_ID_ABRECHBLATT_POS_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ABRECHBLATT_POS", "ID_ABRECHBLATT_POS="+this.get_ID_ABRECHBLATT_POS_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ABRECHBLATT_POS get_RECORDNEW_ABRECHBLATT_POS() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ABRECHBLATT_POS();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ABRECHBLATT_POS(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ABRECHBLATT_POS create_Instance() throws myException {
		return new RECORD_ABRECHBLATT_POS();
	}
	
	public static RECORD_ABRECHBLATT_POS create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ABRECHBLATT_POS(Conn);
    }

	public static RECORD_ABRECHBLATT_POS create_Instance(RECORD_ABRECHBLATT_POS recordOrig) {
		return new RECORD_ABRECHBLATT_POS(recordOrig);
    }

	public static RECORD_ABRECHBLATT_POS create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ABRECHBLATT_POS(lID_Unformated);
    }

	public static RECORD_ABRECHBLATT_POS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ABRECHBLATT_POS(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ABRECHBLATT_POS create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ABRECHBLATT_POS(lID_Unformated, Conn);
	}

	public static RECORD_ABRECHBLATT_POS create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ABRECHBLATT_POS(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ABRECHBLATT_POS create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ABRECHBLATT_POS(recordOrig);	    
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
    public RECORD_ABRECHBLATT_POS build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ABRECHBLATT_POS WHERE ID_ABRECHBLATT_POS="+this.get_ID_ABRECHBLATT_POS_cUF());
      }
      //return new RECORD_ABRECHBLATT_POS(this.get_cSQL_4_Build());
      RECORD_ABRECHBLATT_POS rec = new RECORD_ABRECHBLATT_POS();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ABRECHBLATT_POS
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ABRECHBLATT_POS-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ABRECHBLATT_POS get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ABRECHBLATT_POS_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ABRECHBLATT_POS  recNew = new RECORDNEW_ABRECHBLATT_POS();
		
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
    public RECORD_ABRECHBLATT_POS set_recordNew(RECORDNEW_ABRECHBLATT_POS recnew) throws myException {
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
	
	



		private RECORD_ABRECHBLATT UP_RECORD_ABRECHBLATT_id_abrechblatt = null;




		private RECORD_LAGER_KONTO UP_RECORD_LAGER_KONTO_id_lager_konto = null;




		private RECORD_VPOS_TPA_FUHRE UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_quelle = null;






	
	/**
	 * References the Field ID_ABRECHBLATT
	 * Falls keine this.get_ID_ABRECHBLATT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ABRECHBLATT get_UP_RECORD_ABRECHBLATT_id_abrechblatt() throws myException
	{
		if (S.isEmpty(this.get_ID_ABRECHBLATT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ABRECHBLATT_id_abrechblatt==null)
		{
			this.UP_RECORD_ABRECHBLATT_id_abrechblatt = new RECORD_ABRECHBLATT(this.get_ID_ABRECHBLATT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ABRECHBLATT_id_abrechblatt;
	}
	





	
	/**
	 * References the Field ID_LAGER_KONTO
	 * Falls keine this.get_ID_LAGER_KONTO_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_LAGER_KONTO get_UP_RECORD_LAGER_KONTO_id_lager_konto() throws myException
	{
		if (S.isEmpty(this.get_ID_LAGER_KONTO_cUF()))
			return null;
	
	
		if (this.UP_RECORD_LAGER_KONTO_id_lager_konto==null)
		{
			this.UP_RECORD_LAGER_KONTO_id_lager_konto = new RECORD_LAGER_KONTO(this.get_ID_LAGER_KONTO_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_LAGER_KONTO_id_lager_konto;
	}
	





	
	/**
	 * References the Field ID_VPOS_TPA_FUHRE_QUELLE
	 * Falls keine this.get_ID_VPOS_TPA_FUHRE_QUELLE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VPOS_TPA_FUHRE get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_quelle() throws myException
	{
		if (S.isEmpty(this.get_ID_VPOS_TPA_FUHRE_QUELLE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_quelle==null)
		{
			this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_quelle = new RECORD_VPOS_TPA_FUHRE(this.get_ID_VPOS_TPA_FUHRE_QUELLE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_quelle;
	}
	

	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ABRECHBLATT = "ID_ABRECHBLATT";
	public static String FIELD__ID_ABRECHBLATT_POS = "ID_ABRECHBLATT_POS";
	public static String FIELD__ID_LAGER_KONTO = "ID_LAGER_KONTO";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_VPOS_TPA_FUHRE_QUELLE = "ID_VPOS_TPA_FUHRE_QUELLE";
	public static String FIELD__KFZ_KENNZ_KOPIE = "KFZ_KENNZ_KOPIE";
	public static String FIELD__KUNDENNACHRICHT_ZUSATZ = "KUNDENNACHRICHT_ZUSATZ";
	public static String FIELD__LEISTUNGSDATUM_KOPIE = "LEISTUNGSDATUM_KOPIE";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LIEFERSCHEIN_FREMD_ZUSATZ = "LIEFERSCHEIN_FREMD_ZUSATZ";
	public static String FIELD__MATERIAL_TEXT_KOPIE = "MATERIAL_TEXT_KOPIE";
	public static String FIELD__POSITION_FIXIERT = "POSITION_FIXIERT";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__WA_LADEMENGE_EIGEN_KOPIE = "WA_LADEMENGE_EIGEN_KOPIE";
	public static String FIELD__WE_ABLADEMENGE_EIGEN_KOPIE = "WE_ABLADEMENGE_EIGEN_KOPIE";
	public static String FIELD__WE_LADEMENGE_FREMD_KOPIE = "WE_LADEMENGE_FREMD_KOPIE";
	public static String FIELD__WIEGEKARTE_EIGEN_KOPIE = "WIEGEKARTE_EIGEN_KOPIE";
	public static String FIELD__WIEGESCHEIN_FREMD_KOPIE = "WIEGESCHEIN_FREMD_KOPIE";


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
		public String get_ID_ABRECHBLATT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ABRECHBLATT");
	}

	public String get_ID_ABRECHBLATT_cF() throws myException
	{
		return this.get_FormatedValue("ID_ABRECHBLATT");	
	}

	public String get_ID_ABRECHBLATT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ABRECHBLATT");
	}

	public String get_ID_ABRECHBLATT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ABRECHBLATT",cNotNullValue);
	}

	public String get_ID_ABRECHBLATT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ABRECHBLATT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ABRECHBLATT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ABRECHBLATT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ABRECHBLATT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT", calNewValueFormated);
	}
		public Long get_ID_ABRECHBLATT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ABRECHBLATT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ABRECHBLATT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ABRECHBLATT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ABRECHBLATT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ABRECHBLATT").getDoubleValue();
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
	public String get_ID_ABRECHBLATT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ABRECHBLATT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ABRECHBLATT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ABRECHBLATT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ABRECHBLATT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ABRECHBLATT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ABRECHBLATT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ABRECHBLATT").getBigDecimalValue();
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
	
	
	public String get_ID_ABRECHBLATT_POS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ABRECHBLATT_POS");
	}

	public String get_ID_ABRECHBLATT_POS_cF() throws myException
	{
		return this.get_FormatedValue("ID_ABRECHBLATT_POS");	
	}

	public String get_ID_ABRECHBLATT_POS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ABRECHBLATT_POS");
	}

	public String get_ID_ABRECHBLATT_POS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ABRECHBLATT_POS",cNotNullValue);
	}

	public String get_ID_ABRECHBLATT_POS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ABRECHBLATT_POS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ABRECHBLATT_POS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ABRECHBLATT_POS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ABRECHBLATT_POS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ABRECHBLATT_POS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ABRECHBLATT_POS", calNewValueFormated);
	}
		public Long get_ID_ABRECHBLATT_POS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ABRECHBLATT_POS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ABRECHBLATT_POS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ABRECHBLATT_POS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ABRECHBLATT_POS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ABRECHBLATT_POS").getDoubleValue();
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
	public String get_ID_ABRECHBLATT_POS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ABRECHBLATT_POS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_ABRECHBLATT_POS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ABRECHBLATT_POS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_ABRECHBLATT_POS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ABRECHBLATT_POS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ABRECHBLATT_POS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ABRECHBLATT_POS").getBigDecimalValue();
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
	
	
	public String get_ID_LAGER_KONTO_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_KONTO");
	}

	public String get_ID_LAGER_KONTO_cF() throws myException
	{
		return this.get_FormatedValue("ID_LAGER_KONTO");	
	}

	public String get_ID_LAGER_KONTO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_LAGER_KONTO");
	}

	public String get_ID_LAGER_KONTO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_LAGER_KONTO",cNotNullValue);
	}

	public String get_ID_LAGER_KONTO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_LAGER_KONTO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_LAGER_KONTO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_LAGER_KONTO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGER_KONTO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_LAGER_KONTO", calNewValueFormated);
	}
		public Long get_ID_LAGER_KONTO_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_LAGER_KONTO").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_LAGER_KONTO_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_LAGER_KONTO").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_LAGER_KONTO_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_LAGER_KONTO").getDoubleValue();
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
	public String get_ID_LAGER_KONTO_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_KONTO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_LAGER_KONTO_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_LAGER_KONTO_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_LAGER_KONTO_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_KONTO").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_LAGER_KONTO_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_LAGER_KONTO").getBigDecimalValue();
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
	
	
	public String get_ID_VPOS_TPA_FUHRE_QUELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_QUELLE");
	}

	public String get_ID_VPOS_TPA_FUHRE_QUELLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_QUELLE");	
	}

	public String get_ID_VPOS_TPA_FUHRE_QUELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VPOS_TPA_FUHRE_QUELLE");
	}

	public String get_ID_VPOS_TPA_FUHRE_QUELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_QUELLE",cNotNullValue);
	}

	public String get_ID_VPOS_TPA_FUHRE_QUELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VPOS_TPA_FUHRE_QUELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_QUELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_QUELLE", calNewValueFormated);
	}
		public Long get_ID_VPOS_TPA_FUHRE_QUELLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VPOS_TPA_FUHRE_QUELLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VPOS_TPA_FUHRE_QUELLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_QUELLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VPOS_TPA_FUHRE_QUELLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VPOS_TPA_FUHRE_QUELLE").getDoubleValue();
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
	public String get_ID_VPOS_TPA_FUHRE_QUELLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_ID_VPOS_TPA_FUHRE_QUELLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VPOS_TPA_FUHRE_QUELLE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_ID_VPOS_TPA_FUHRE_QUELLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_QUELLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VPOS_TPA_FUHRE_QUELLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VPOS_TPA_FUHRE_QUELLE").getBigDecimalValue();
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
	
	
	public String get_KFZ_KENNZ_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KFZ_KENNZ_KOPIE");
	}

	public String get_KFZ_KENNZ_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("KFZ_KENNZ_KOPIE");	
	}

	public String get_KFZ_KENNZ_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KFZ_KENNZ_KOPIE");
	}

	public String get_KFZ_KENNZ_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KFZ_KENNZ_KOPIE",cNotNullValue);
	}

	public String get_KFZ_KENNZ_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KFZ_KENNZ_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KFZ_KENNZ_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KFZ_KENNZ_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KFZ_KENNZ_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KFZ_KENNZ_KOPIE", calNewValueFormated);
	}
		public String get_KUNDENNACHRICHT_ZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("KUNDENNACHRICHT_ZUSATZ");
	}

	public String get_KUNDENNACHRICHT_ZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("KUNDENNACHRICHT_ZUSATZ");	
	}

	public String get_KUNDENNACHRICHT_ZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KUNDENNACHRICHT_ZUSATZ");
	}

	public String get_KUNDENNACHRICHT_ZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KUNDENNACHRICHT_ZUSATZ",cNotNullValue);
	}

	public String get_KUNDENNACHRICHT_ZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KUNDENNACHRICHT_ZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KUNDENNACHRICHT_ZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KUNDENNACHRICHT_ZUSATZ", calNewValueFormated);
	}
		public String get_LEISTUNGSDATUM_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("LEISTUNGSDATUM_KOPIE");
	}

	public String get_LEISTUNGSDATUM_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("LEISTUNGSDATUM_KOPIE");	
	}

	public String get_LEISTUNGSDATUM_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LEISTUNGSDATUM_KOPIE");
	}

	public String get_LEISTUNGSDATUM_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LEISTUNGSDATUM_KOPIE",cNotNullValue);
	}

	public String get_LEISTUNGSDATUM_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LEISTUNGSDATUM_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LEISTUNGSDATUM_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LEISTUNGSDATUM_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LEISTUNGSDATUM_KOPIE", calNewValueFormated);
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
		public String get_LIEFERSCHEIN_FREMD_ZUSATZ_cUF() throws myException
	{
		return this.get_UnFormatedValue("LIEFERSCHEIN_FREMD_ZUSATZ");
	}

	public String get_LIEFERSCHEIN_FREMD_ZUSATZ_cF() throws myException
	{
		return this.get_FormatedValue("LIEFERSCHEIN_FREMD_ZUSATZ");	
	}

	public String get_LIEFERSCHEIN_FREMD_ZUSATZ_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LIEFERSCHEIN_FREMD_ZUSATZ");
	}

	public String get_LIEFERSCHEIN_FREMD_ZUSATZ_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LIEFERSCHEIN_FREMD_ZUSATZ",cNotNullValue);
	}

	public String get_LIEFERSCHEIN_FREMD_ZUSATZ_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LIEFERSCHEIN_FREMD_ZUSATZ",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LIEFERSCHEIN_FREMD_ZUSATZ_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LIEFERSCHEIN_FREMD_ZUSATZ", calNewValueFormated);
	}
		public String get_MATERIAL_TEXT_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MATERIAL_TEXT_KOPIE");
	}

	public String get_MATERIAL_TEXT_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("MATERIAL_TEXT_KOPIE");	
	}

	public String get_MATERIAL_TEXT_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MATERIAL_TEXT_KOPIE");
	}

	public String get_MATERIAL_TEXT_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MATERIAL_TEXT_KOPIE",cNotNullValue);
	}

	public String get_MATERIAL_TEXT_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MATERIAL_TEXT_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MATERIAL_TEXT_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MATERIAL_TEXT_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MATERIAL_TEXT_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MATERIAL_TEXT_KOPIE", calNewValueFormated);
	}
		public String get_POSITION_FIXIERT_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSITION_FIXIERT");
	}

	public String get_POSITION_FIXIERT_cF() throws myException
	{
		return this.get_FormatedValue("POSITION_FIXIERT");	
	}

	public String get_POSITION_FIXIERT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSITION_FIXIERT");
	}

	public String get_POSITION_FIXIERT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSITION_FIXIERT",cNotNullValue);
	}

	public String get_POSITION_FIXIERT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSITION_FIXIERT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSITION_FIXIERT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSITION_FIXIERT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSITION_FIXIERT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSITION_FIXIERT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSITION_FIXIERT", calNewValueFormated);
	}
		public boolean is_POSITION_FIXIERT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_POSITION_FIXIERT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_POSITION_FIXIERT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_POSITION_FIXIERT_cUF_NN("N").equals("N"))
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
	
	
	public String get_WA_LADEMENGE_EIGEN_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WA_LADEMENGE_EIGEN_KOPIE");
	}

	public String get_WA_LADEMENGE_EIGEN_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("WA_LADEMENGE_EIGEN_KOPIE");	
	}

	public String get_WA_LADEMENGE_EIGEN_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WA_LADEMENGE_EIGEN_KOPIE");
	}

	public String get_WA_LADEMENGE_EIGEN_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WA_LADEMENGE_EIGEN_KOPIE",cNotNullValue);
	}

	public String get_WA_LADEMENGE_EIGEN_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WA_LADEMENGE_EIGEN_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WA_LADEMENGE_EIGEN_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WA_LADEMENGE_EIGEN_KOPIE", calNewValueFormated);
	}
		public Double get_WA_LADEMENGE_EIGEN_KOPIE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WA_LADEMENGE_EIGEN_KOPIE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WA_LADEMENGE_EIGEN_KOPIE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WA_LADEMENGE_EIGEN_KOPIE").getDoubleValue();
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
	public String get_WA_LADEMENGE_EIGEN_KOPIE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WA_LADEMENGE_EIGEN_KOPIE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_WA_LADEMENGE_EIGEN_KOPIE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WA_LADEMENGE_EIGEN_KOPIE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_WA_LADEMENGE_EIGEN_KOPIE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WA_LADEMENGE_EIGEN_KOPIE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WA_LADEMENGE_EIGEN_KOPIE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WA_LADEMENGE_EIGEN_KOPIE").getBigDecimalValue();
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
	
	
	public String get_WE_ABLADEMENGE_EIGEN_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WE_ABLADEMENGE_EIGEN_KOPIE");
	}

	public String get_WE_ABLADEMENGE_EIGEN_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("WE_ABLADEMENGE_EIGEN_KOPIE");	
	}

	public String get_WE_ABLADEMENGE_EIGEN_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WE_ABLADEMENGE_EIGEN_KOPIE");
	}

	public String get_WE_ABLADEMENGE_EIGEN_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WE_ABLADEMENGE_EIGEN_KOPIE",cNotNullValue);
	}

	public String get_WE_ABLADEMENGE_EIGEN_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WE_ABLADEMENGE_EIGEN_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_ABLADEMENGE_EIGEN_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WE_ABLADEMENGE_EIGEN_KOPIE", calNewValueFormated);
	}
		public Double get_WE_ABLADEMENGE_EIGEN_KOPIE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WE_ABLADEMENGE_EIGEN_KOPIE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WE_ABLADEMENGE_EIGEN_KOPIE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WE_ABLADEMENGE_EIGEN_KOPIE").getDoubleValue();
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
	public String get_WE_ABLADEMENGE_EIGEN_KOPIE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WE_ABLADEMENGE_EIGEN_KOPIE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_WE_ABLADEMENGE_EIGEN_KOPIE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WE_ABLADEMENGE_EIGEN_KOPIE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_WE_ABLADEMENGE_EIGEN_KOPIE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WE_ABLADEMENGE_EIGEN_KOPIE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WE_ABLADEMENGE_EIGEN_KOPIE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WE_ABLADEMENGE_EIGEN_KOPIE").getBigDecimalValue();
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
	
	
	public String get_WE_LADEMENGE_FREMD_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WE_LADEMENGE_FREMD_KOPIE");
	}

	public String get_WE_LADEMENGE_FREMD_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("WE_LADEMENGE_FREMD_KOPIE");	
	}

	public String get_WE_LADEMENGE_FREMD_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WE_LADEMENGE_FREMD_KOPIE");
	}

	public String get_WE_LADEMENGE_FREMD_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WE_LADEMENGE_FREMD_KOPIE",cNotNullValue);
	}

	public String get_WE_LADEMENGE_FREMD_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WE_LADEMENGE_FREMD_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WE_LADEMENGE_FREMD_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WE_LADEMENGE_FREMD_KOPIE", calNewValueFormated);
	}
		public Double get_WE_LADEMENGE_FREMD_KOPIE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("WE_LADEMENGE_FREMD_KOPIE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_WE_LADEMENGE_FREMD_KOPIE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("WE_LADEMENGE_FREMD_KOPIE").getDoubleValue();
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
	public String get_WE_LADEMENGE_FREMD_KOPIE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WE_LADEMENGE_FREMD_KOPIE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
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
	public String get_WE_LADEMENGE_FREMD_KOPIE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_WE_LADEMENGE_FREMD_KOPIE_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public BigDecimal get_WE_LADEMENGE_FREMD_KOPIE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("WE_LADEMENGE_FREMD_KOPIE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_WE_LADEMENGE_FREMD_KOPIE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("WE_LADEMENGE_FREMD_KOPIE").getBigDecimalValue();
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
	
	
	public String get_WIEGEKARTE_EIGEN_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTE_EIGEN_KOPIE");
	}

	public String get_WIEGEKARTE_EIGEN_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("WIEGEKARTE_EIGEN_KOPIE");	
	}

	public String get_WIEGEKARTE_EIGEN_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEGEKARTE_EIGEN_KOPIE");
	}

	public String get_WIEGEKARTE_EIGEN_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEGEKARTE_EIGEN_KOPIE",cNotNullValue);
	}

	public String get_WIEGEKARTE_EIGEN_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEGEKARTE_EIGEN_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGEKARTE_EIGEN_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGEKARTE_EIGEN_KOPIE", calNewValueFormated);
	}
		public String get_WIEGESCHEIN_FREMD_KOPIE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WIEGESCHEIN_FREMD_KOPIE");
	}

	public String get_WIEGESCHEIN_FREMD_KOPIE_cF() throws myException
	{
		return this.get_FormatedValue("WIEGESCHEIN_FREMD_KOPIE");	
	}

	public String get_WIEGESCHEIN_FREMD_KOPIE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WIEGESCHEIN_FREMD_KOPIE");
	}

	public String get_WIEGESCHEIN_FREMD_KOPIE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WIEGESCHEIN_FREMD_KOPIE",cNotNullValue);
	}

	public String get_WIEGESCHEIN_FREMD_KOPIE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WIEGESCHEIN_FREMD_KOPIE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WIEGESCHEIN_FREMD_KOPIE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WIEGESCHEIN_FREMD_KOPIE", calNewValueFormated);
	}
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ABRECHBLATT",MyRECORD.DATATYPES.NUMBER);
	put("ID_ABRECHBLATT_POS",MyRECORD.DATATYPES.NUMBER);
	put("ID_LAGER_KONTO",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VPOS_TPA_FUHRE_QUELLE",MyRECORD.DATATYPES.NUMBER);
	put("KFZ_KENNZ_KOPIE",MyRECORD.DATATYPES.TEXT);
	put("KUNDENNACHRICHT_ZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("LEISTUNGSDATUM_KOPIE",MyRECORD.DATATYPES.DATE);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LIEFERSCHEIN_FREMD_ZUSATZ",MyRECORD.DATATYPES.TEXT);
	put("MATERIAL_TEXT_KOPIE",MyRECORD.DATATYPES.TEXT);
	put("POSITION_FIXIERT",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("WA_LADEMENGE_EIGEN_KOPIE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("WE_ABLADEMENGE_EIGEN_KOPIE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("WE_LADEMENGE_FREMD_KOPIE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("WIEGEKARTE_EIGEN_KOPIE",MyRECORD.DATATYPES.TEXT);
	put("WIEGESCHEIN_FREMD_KOPIE",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ABRECHBLATT_POS.HM_FIELDS;	
	}

}
