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

public class RECORD_MAIL_AUS_MASK extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_MAIL_AUS_MASK";
    public static String IDFIELD   = "ID_MAIL_AUS_MASK";
    

	//erzeugen eines RECORDNEW_JT_MAIL_AUS_MASK - felds
	private RECORDNEW_MAIL_AUS_MASK   recNEW = null;

    private _TAB  tab = _TAB.mail_aus_mask;  



	public RECORD_MAIL_AUS_MASK() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");
	}


	public RECORD_MAIL_AUS_MASK(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");
	}



	public RECORD_MAIL_AUS_MASK(RECORD_MAIL_AUS_MASK recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK.TABLENAME);
	}


	//2014-04-03
	public RECORD_MAIL_AUS_MASK(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK.TABLENAME);
	}




	public RECORD_MAIL_AUS_MASK(long lID_Unformated) throws myException
	{
		super("JT_MAIL_AUS_MASK","ID_MAIL_AUS_MASK",""+lID_Unformated);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK.TABLENAME);
	}

	public RECORD_MAIL_AUS_MASK(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK", "ID_MAIL_AUS_MASK="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK.TABLENAME);
	}
	
	

	public RECORD_MAIL_AUS_MASK(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_MAIL_AUS_MASK","ID_MAIL_AUS_MASK",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK.TABLENAME);

	}


	public RECORD_MAIL_AUS_MASK(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_MAIL_AUS_MASK");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK", "ID_MAIL_AUS_MASK="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MAIL_AUS_MASK.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_MAIL_AUS_MASK();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_MAIL_AUS_MASK.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_MAIL_AUS_MASK";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_MAIL_AUS_MASK_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_MAIL_AUS_MASK_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MAIL_AUS_MASK was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MAIL_AUS_MASK", bibE2.cTO(), "ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MAIL_AUS_MASK was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_MAIL_AUS_MASK", bibE2.cTO(), "ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK WHERE ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK WHERE ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF();
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
			if (S.isFull(this.get_ID_MAIL_AUS_MASK_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_MAIL_AUS_MASK", "ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_MAIL_AUS_MASK get_RECORDNEW_MAIL_AUS_MASK() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_MAIL_AUS_MASK();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_MAIL_AUS_MASK(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_MAIL_AUS_MASK create_Instance() throws myException {
		return new RECORD_MAIL_AUS_MASK();
	}
	
	public static RECORD_MAIL_AUS_MASK create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_MAIL_AUS_MASK(Conn);
    }

	public static RECORD_MAIL_AUS_MASK create_Instance(RECORD_MAIL_AUS_MASK recordOrig) {
		return new RECORD_MAIL_AUS_MASK(recordOrig);
    }

	public static RECORD_MAIL_AUS_MASK create_Instance(long lID_Unformated) throws myException {
		return new RECORD_MAIL_AUS_MASK(lID_Unformated);
    }

	public static RECORD_MAIL_AUS_MASK create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_MAIL_AUS_MASK(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_MAIL_AUS_MASK create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_MAIL_AUS_MASK(lID_Unformated, Conn);
	}

	public static RECORD_MAIL_AUS_MASK create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_MAIL_AUS_MASK(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_MAIL_AUS_MASK create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_MAIL_AUS_MASK(recordOrig);	    
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
    public RECORD_MAIL_AUS_MASK build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK WHERE ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF());
      }
      //return new RECORD_MAIL_AUS_MASK(this.get_cSQL_4_Build());
      RECORD_MAIL_AUS_MASK rec = new RECORD_MAIL_AUS_MASK();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_MAIL_AUS_MASK
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_MAIL_AUS_MASK-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_MAIL_AUS_MASK get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_MAIL_AUS_MASK_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_MAIL_AUS_MASK  recNew = new RECORDNEW_MAIL_AUS_MASK();
		
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
    public RECORD_MAIL_AUS_MASK set_recordNew(RECORDNEW_MAIL_AUS_MASK recnew) throws myException {
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
	
	



		private RECLIST_MAIL_AUS_MASK_EMAIL DOWN_RECLIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask = null ;




		private RECLIST_MAIL_AUS_MASK_JASPER DOWN_RECLIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask = null ;






	/**
	 * References the Field ID_MAIL_AUS_MASK 
	 * Falls keine get_ID_MAIL_AUS_MASK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAIL_AUS_MASK_EMAIL get_DOWN_RECORD_LIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask() throws myException
	{
		if (S.isEmpty(this.get_ID_MAIL_AUS_MASK_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask==null)
		{
			this.DOWN_RECLIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask = new RECLIST_MAIL_AUS_MASK_EMAIL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_EMAIL WHERE ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF()+" ORDER BY ID_MAIL_AUS_MASK_EMAIL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MAIL_AUS_MASK 
	 * Falls keine get_ID_MAIL_AUS_MASK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAIL_AUS_MASK_EMAIL get_DOWN_RECORD_LIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MAIL_AUS_MASK_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_EMAIL WHERE ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask = new RECLIST_MAIL_AUS_MASK_EMAIL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAIL_AUS_MASK_EMAIL_id_mail_aus_mask;
	}


	





	/**
	 * References the Field ID_MAIL_AUS_MASK 
	 * Falls keine get_ID_MAIL_AUS_MASK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAIL_AUS_MASK_JASPER get_DOWN_RECORD_LIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask() throws myException
	{
		if (S.isEmpty(this.get_ID_MAIL_AUS_MASK_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask==null)
		{
			this.DOWN_RECLIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask = new RECLIST_MAIL_AUS_MASK_JASPER (
			       "SELECT * FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER WHERE ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF()+" ORDER BY ID_MAIL_AUS_MASK_JASPER",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MAIL_AUS_MASK 
	 * Falls keine get_ID_MAIL_AUS_MASK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MAIL_AUS_MASK_JASPER get_DOWN_RECORD_LIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MAIL_AUS_MASK_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_JASPER WHERE ID_MAIL_AUS_MASK="+this.get_ID_MAIL_AUS_MASK_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask = new RECLIST_MAIL_AUS_MASK_JASPER (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MAIL_AUS_MASK_JASPER_id_mail_aus_mask;
	}


	

	public static String FIELD__BASISTABELLE = "BASISTABELLE";
	public static String FIELD__BETREFF = "BETREFF";
	public static String FIELD__BUTTONBESCHRIFTUNG = "BUTTONBESCHRIFTUNG";
	public static String FIELD__BUTTONKENNER = "BUTTONKENNER";
	public static String FIELD__ERLAUBE_FREIEMAILADRESSE = "ERLAUBE_FREIEMAILADRESSE";
	public static String FIELD__ERLAUBT_BEI_EDIT = "ERLAUBT_BEI_EDIT";
	public static String FIELD__ERLAUBT_BEI_NEUEINGABE = "ERLAUBT_BEI_NEUEINGABE";
	public static String FIELD__ERLAUBT_BEI_UNDEF = "ERLAUBT_BEI_UNDEF";
	public static String FIELD__ERLAUBT_BEI_VIEW = "ERLAUBT_BEI_VIEW";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GROOVY_BEDINGUNG = "GROOVY_BEDINGUNG";
	public static String FIELD__ID_MAIL_AUS_MASK = "ID_MAIL_AUS_MASK";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MAILTEXT = "MAILTEXT";
	public static String FIELD__MODULKENNER = "MODULKENNER";
	public static String FIELD__SICHERHEITSABFRAGE_VOR_START = "SICHERHEITSABFRAGE_VOR_START";
	public static String FIELD__SIGNATUR_ANHAENGEN = "SIGNATUR_ANHAENGEN";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_BASISTABELLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BASISTABELLE");
	}

	public String get_BASISTABELLE_cF() throws myException
	{
		return this.get_FormatedValue("BASISTABELLE");	
	}

	public String get_BASISTABELLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BASISTABELLE");
	}

	public String get_BASISTABELLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BASISTABELLE",cNotNullValue);
	}

	public String get_BASISTABELLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BASISTABELLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BASISTABELLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BASISTABELLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BASISTABELLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BASISTABELLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASISTABELLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASISTABELLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BASISTABELLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BASISTABELLE", calNewValueFormated);
	}
		public String get_BETREFF_cUF() throws myException
	{
		return this.get_UnFormatedValue("BETREFF");
	}

	public String get_BETREFF_cF() throws myException
	{
		return this.get_FormatedValue("BETREFF");	
	}

	public String get_BETREFF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BETREFF");
	}

	public String get_BETREFF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BETREFF",cNotNullValue);
	}

	public String get_BETREFF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BETREFF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BETREFF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BETREFF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BETREFF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BETREFF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BETREFF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETREFF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETREFF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BETREFF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BETREFF", calNewValueFormated);
	}
		public String get_BUTTONBESCHRIFTUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUTTONBESCHRIFTUNG");
	}

	public String get_BUTTONBESCHRIFTUNG_cF() throws myException
	{
		return this.get_FormatedValue("BUTTONBESCHRIFTUNG");	
	}

	public String get_BUTTONBESCHRIFTUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUTTONBESCHRIFTUNG");
	}

	public String get_BUTTONBESCHRIFTUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUTTONBESCHRIFTUNG",cNotNullValue);
	}

	public String get_BUTTONBESCHRIFTUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUTTONBESCHRIFTUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUTTONBESCHRIFTUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUTTONBESCHRIFTUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONBESCHRIFTUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONBESCHRIFTUNG", calNewValueFormated);
	}
		public String get_BUTTONKENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("BUTTONKENNER");
	}

	public String get_BUTTONKENNER_cF() throws myException
	{
		return this.get_FormatedValue("BUTTONKENNER");	
	}

	public String get_BUTTONKENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BUTTONKENNER");
	}

	public String get_BUTTONKENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BUTTONKENNER",cNotNullValue);
	}

	public String get_BUTTONKENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BUTTONKENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BUTTONKENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BUTTONKENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BUTTONKENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BUTTONKENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONKENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONKENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BUTTONKENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BUTTONKENNER", calNewValueFormated);
	}
		public String get_ERLAUBE_FREIEMAILADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERLAUBE_FREIEMAILADRESSE");
	}

	public String get_ERLAUBE_FREIEMAILADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("ERLAUBE_FREIEMAILADRESSE");	
	}

	public String get_ERLAUBE_FREIEMAILADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERLAUBE_FREIEMAILADRESSE");
	}

	public String get_ERLAUBE_FREIEMAILADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERLAUBE_FREIEMAILADRESSE",cNotNullValue);
	}

	public String get_ERLAUBE_FREIEMAILADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERLAUBE_FREIEMAILADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBE_FREIEMAILADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBE_FREIEMAILADRESSE", calNewValueFormated);
	}
		public boolean is_ERLAUBE_FREIEMAILADRESSE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBE_FREIEMAILADRESSE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ERLAUBE_FREIEMAILADRESSE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBE_FREIEMAILADRESSE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ERLAUBT_BEI_EDIT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_EDIT");
	}

	public String get_ERLAUBT_BEI_EDIT_cF() throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_EDIT");	
	}

	public String get_ERLAUBT_BEI_EDIT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERLAUBT_BEI_EDIT");
	}

	public String get_ERLAUBT_BEI_EDIT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_EDIT",cNotNullValue);
	}

	public String get_ERLAUBT_BEI_EDIT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_EDIT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_EDIT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERLAUBT_BEI_EDIT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_EDIT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_EDIT", calNewValueFormated);
	}
		public boolean is_ERLAUBT_BEI_EDIT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_EDIT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ERLAUBT_BEI_EDIT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_EDIT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ERLAUBT_BEI_NEUEINGABE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_NEUEINGABE");
	}

	public String get_ERLAUBT_BEI_NEUEINGABE_cF() throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_NEUEINGABE");	
	}

	public String get_ERLAUBT_BEI_NEUEINGABE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERLAUBT_BEI_NEUEINGABE");
	}

	public String get_ERLAUBT_BEI_NEUEINGABE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_NEUEINGABE",cNotNullValue);
	}

	public String get_ERLAUBT_BEI_NEUEINGABE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_NEUEINGABE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_NEUEINGABE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_NEUEINGABE", calNewValueFormated);
	}
		public boolean is_ERLAUBT_BEI_NEUEINGABE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_NEUEINGABE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ERLAUBT_BEI_NEUEINGABE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_NEUEINGABE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ERLAUBT_BEI_UNDEF_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_UNDEF");
	}

	public String get_ERLAUBT_BEI_UNDEF_cF() throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_UNDEF");	
	}

	public String get_ERLAUBT_BEI_UNDEF_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERLAUBT_BEI_UNDEF");
	}

	public String get_ERLAUBT_BEI_UNDEF_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_UNDEF",cNotNullValue);
	}

	public String get_ERLAUBT_BEI_UNDEF_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_UNDEF",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_UNDEF(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERLAUBT_BEI_UNDEF", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_UNDEF_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_UNDEF", calNewValueFormated);
	}
		public boolean is_ERLAUBT_BEI_UNDEF_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_UNDEF_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ERLAUBT_BEI_UNDEF_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_UNDEF_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ERLAUBT_BEI_VIEW_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_VIEW");
	}

	public String get_ERLAUBT_BEI_VIEW_cF() throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_VIEW");	
	}

	public String get_ERLAUBT_BEI_VIEW_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERLAUBT_BEI_VIEW");
	}

	public String get_ERLAUBT_BEI_VIEW_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERLAUBT_BEI_VIEW",cNotNullValue);
	}

	public String get_ERLAUBT_BEI_VIEW_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERLAUBT_BEI_VIEW",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERLAUBT_BEI_VIEW(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERLAUBT_BEI_VIEW", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERLAUBT_BEI_VIEW_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERLAUBT_BEI_VIEW", calNewValueFormated);
	}
		public boolean is_ERLAUBT_BEI_VIEW_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_VIEW_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ERLAUBT_BEI_VIEW_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ERLAUBT_BEI_VIEW_cUF_NN("N").equals("N"))
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
		public String get_GROOVY_BEDINGUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("GROOVY_BEDINGUNG");
	}

	public String get_GROOVY_BEDINGUNG_cF() throws myException
	{
		return this.get_FormatedValue("GROOVY_BEDINGUNG");	
	}

	public String get_GROOVY_BEDINGUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GROOVY_BEDINGUNG");
	}

	public String get_GROOVY_BEDINGUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GROOVY_BEDINGUNG",cNotNullValue);
	}

	public String get_GROOVY_BEDINGUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GROOVY_BEDINGUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GROOVY_BEDINGUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GROOVY_BEDINGUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GROOVY_BEDINGUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GROOVY_BEDINGUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GROOVY_BEDINGUNG", calNewValueFormated);
	}
		public String get_ID_MAIL_AUS_MASK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MAIL_AUS_MASK");
	}

	public String get_ID_MAIL_AUS_MASK_cF() throws myException
	{
		return this.get_FormatedValue("ID_MAIL_AUS_MASK");	
	}

	public String get_ID_MAIL_AUS_MASK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MAIL_AUS_MASK");
	}

	public String get_ID_MAIL_AUS_MASK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MAIL_AUS_MASK",cNotNullValue);
	}

	public String get_ID_MAIL_AUS_MASK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MAIL_AUS_MASK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MAIL_AUS_MASK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MAIL_AUS_MASK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MAIL_AUS_MASK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MAIL_AUS_MASK", calNewValueFormated);
	}
		public Long get_ID_MAIL_AUS_MASK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MAIL_AUS_MASK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MAIL_AUS_MASK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MAIL_AUS_MASK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MAIL_AUS_MASK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MAIL_AUS_MASK").getDoubleValue();
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
	public String get_ID_MAIL_AUS_MASK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAIL_AUS_MASK_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MAIL_AUS_MASK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MAIL_AUS_MASK_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MAIL_AUS_MASK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAIL_AUS_MASK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MAIL_AUS_MASK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MAIL_AUS_MASK").getBigDecimalValue();
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
		public String get_MAILTEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAILTEXT");
	}

	public String get_MAILTEXT_cF() throws myException
	{
		return this.get_FormatedValue("MAILTEXT");	
	}

	public String get_MAILTEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAILTEXT");
	}

	public String get_MAILTEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAILTEXT",cNotNullValue);
	}

	public String get_MAILTEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAILTEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAILTEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAILTEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAILTEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAILTEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILTEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILTEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILTEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILTEXT", calNewValueFormated);
	}
		public String get_MODULKENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MODULKENNER");
	}

	public String get_MODULKENNER_cF() throws myException
	{
		return this.get_FormatedValue("MODULKENNER");	
	}

	public String get_MODULKENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MODULKENNER");
	}

	public String get_MODULKENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MODULKENNER",cNotNullValue);
	}

	public String get_MODULKENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MODULKENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MODULKENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MODULKENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MODULKENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MODULKENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULKENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULKENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MODULKENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MODULKENNER", calNewValueFormated);
	}
		public String get_SICHERHEITSABFRAGE_VOR_START_cUF() throws myException
	{
		return this.get_UnFormatedValue("SICHERHEITSABFRAGE_VOR_START");
	}

	public String get_SICHERHEITSABFRAGE_VOR_START_cF() throws myException
	{
		return this.get_FormatedValue("SICHERHEITSABFRAGE_VOR_START");	
	}

	public String get_SICHERHEITSABFRAGE_VOR_START_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SICHERHEITSABFRAGE_VOR_START");
	}

	public String get_SICHERHEITSABFRAGE_VOR_START_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SICHERHEITSABFRAGE_VOR_START",cNotNullValue);
	}

	public String get_SICHERHEITSABFRAGE_VOR_START_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SICHERHEITSABFRAGE_VOR_START",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SICHERHEITSABFRAGE_VOR_START_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SICHERHEITSABFRAGE_VOR_START", calNewValueFormated);
	}
		public String get_SIGNATUR_ANHAENGEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("SIGNATUR_ANHAENGEN");
	}

	public String get_SIGNATUR_ANHAENGEN_cF() throws myException
	{
		return this.get_FormatedValue("SIGNATUR_ANHAENGEN");	
	}

	public String get_SIGNATUR_ANHAENGEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SIGNATUR_ANHAENGEN");
	}

	public String get_SIGNATUR_ANHAENGEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SIGNATUR_ANHAENGEN",cNotNullValue);
	}

	public String get_SIGNATUR_ANHAENGEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SIGNATUR_ANHAENGEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SIGNATUR_ANHAENGEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SIGNATUR_ANHAENGEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SIGNATUR_ANHAENGEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SIGNATUR_ANHAENGEN", calNewValueFormated);
	}
		public boolean is_SIGNATUR_ANHAENGEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_SIGNATUR_ANHAENGEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_SIGNATUR_ANHAENGEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_SIGNATUR_ANHAENGEN_cUF_NN("N").equals("N"))
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
	
	


	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("BASISTABELLE",MyRECORD.DATATYPES.TEXT);
	put("BETREFF",MyRECORD.DATATYPES.TEXT);
	put("BUTTONBESCHRIFTUNG",MyRECORD.DATATYPES.TEXT);
	put("BUTTONKENNER",MyRECORD.DATATYPES.TEXT);
	put("ERLAUBE_FREIEMAILADRESSE",MyRECORD.DATATYPES.YESNO);
	put("ERLAUBT_BEI_EDIT",MyRECORD.DATATYPES.YESNO);
	put("ERLAUBT_BEI_NEUEINGABE",MyRECORD.DATATYPES.YESNO);
	put("ERLAUBT_BEI_UNDEF",MyRECORD.DATATYPES.YESNO);
	put("ERLAUBT_BEI_VIEW",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GROOVY_BEDINGUNG",MyRECORD.DATATYPES.TEXT);
	put("ID_MAIL_AUS_MASK",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MAILTEXT",MyRECORD.DATATYPES.TEXT);
	put("MODULKENNER",MyRECORD.DATATYPES.TEXT);
	put("SICHERHEITSABFRAGE_VOR_START",MyRECORD.DATATYPES.TEXT);
	put("SIGNATUR_ANHAENGEN",MyRECORD.DATATYPES.YESNO);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_MAIL_AUS_MASK.HM_FIELDS;	
	}

}
