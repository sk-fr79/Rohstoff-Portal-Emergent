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

public class RECORD_ARCHIVMEDIEN extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_ARCHIVMEDIEN";
    public static String IDFIELD   = "ID_ARCHIVMEDIEN";
    

	//erzeugen eines RECORDNEW_JT_ARCHIVMEDIEN - felds
	private RECORDNEW_ARCHIVMEDIEN   recNEW = null;

    private _TAB  tab = _TAB.archivmedien;  



	public RECORD_ARCHIVMEDIEN() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");
	}


	public RECORD_ARCHIVMEDIEN(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");
	}



	public RECORD_ARCHIVMEDIEN(RECORD_ARCHIVMEDIEN recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARCHIVMEDIEN.TABLENAME);
	}


	//2014-04-03
	public RECORD_ARCHIVMEDIEN(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARCHIVMEDIEN.TABLENAME);
	}




	public RECORD_ARCHIVMEDIEN(long lID_Unformated) throws myException
	{
		super("JT_ARCHIVMEDIEN","ID_ARCHIVMEDIEN",""+lID_Unformated);
		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARCHIVMEDIEN.TABLENAME);
	}

	public RECORD_ARCHIVMEDIEN(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARCHIVMEDIEN", "ID_ARCHIVMEDIEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARCHIVMEDIEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARCHIVMEDIEN.TABLENAME);
	}
	
	

	public RECORD_ARCHIVMEDIEN(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_ARCHIVMEDIEN","ID_ARCHIVMEDIEN",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARCHIVMEDIEN.TABLENAME);

	}


	public RECORD_ARCHIVMEDIEN(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_ARCHIVMEDIEN");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARCHIVMEDIEN", "ID_ARCHIVMEDIEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARCHIVMEDIEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_ARCHIVMEDIEN.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_ARCHIVMEDIEN();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_ARCHIVMEDIEN.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_ARCHIVMEDIEN";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_ARCHIVMEDIEN_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_ARCHIVMEDIEN_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ARCHIVMEDIEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ARCHIVMEDIEN", bibE2.cTO(), "ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_ARCHIVMEDIEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_ARCHIVMEDIEN", bibE2.cTO(), "ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF();
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
			if (S.isFull(this.get_ID_ARCHIVMEDIEN_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_ARCHIVMEDIEN", "ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_ARCHIVMEDIEN get_RECORDNEW_ARCHIVMEDIEN() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_ARCHIVMEDIEN();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_ARCHIVMEDIEN(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_ARCHIVMEDIEN create_Instance() throws myException {
		return new RECORD_ARCHIVMEDIEN();
	}
	
	public static RECORD_ARCHIVMEDIEN create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_ARCHIVMEDIEN(Conn);
    }

	public static RECORD_ARCHIVMEDIEN create_Instance(RECORD_ARCHIVMEDIEN recordOrig) {
		return new RECORD_ARCHIVMEDIEN(recordOrig);
    }

	public static RECORD_ARCHIVMEDIEN create_Instance(long lID_Unformated) throws myException {
		return new RECORD_ARCHIVMEDIEN(lID_Unformated);
    }

	public static RECORD_ARCHIVMEDIEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_ARCHIVMEDIEN(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_ARCHIVMEDIEN create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_ARCHIVMEDIEN(lID_Unformated, Conn);
	}

	public static RECORD_ARCHIVMEDIEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_ARCHIVMEDIEN(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_ARCHIVMEDIEN create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_ARCHIVMEDIEN(recordOrig);	    
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
    public RECORD_ARCHIVMEDIEN build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF());
      }
      //return new RECORD_ARCHIVMEDIEN(this.get_cSQL_4_Build());
      RECORD_ARCHIVMEDIEN rec = new RECORD_ARCHIVMEDIEN();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_ARCHIVMEDIEN
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_ARCHIVMEDIEN-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_ARCHIVMEDIEN get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_ARCHIVMEDIEN_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_ARCHIVMEDIEN  recNew = new RECORDNEW_ARCHIVMEDIEN();
		
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
    public RECORD_ARCHIVMEDIEN set_recordNew(RECORDNEW_ARCHIVMEDIEN recnew) throws myException {
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
	
	



		private RECLIST_EMAIL_SEND_ATTACH DOWN_RECLIST_EMAIL_SEND_ATTACH_id_archivmedien = null ;




		private RECORD_MEDIENTYP UP_RECORD_MEDIENTYP_id_medientyp = null;






	/**
	 * References the Field ID_ARCHIVMEDIEN 
	 * Falls keine get_ID_ARCHIVMEDIEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL_SEND_ATTACH get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_archivmedien() throws myException
	{
		if (S.isEmpty(this.get_ID_ARCHIVMEDIEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_SEND_ATTACH_id_archivmedien==null)
		{
			this.DOWN_RECLIST_EMAIL_SEND_ATTACH_id_archivmedien = new RECLIST_EMAIL_SEND_ATTACH (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_SEND_ATTACH WHERE ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF()+" ORDER BY ID_EMAIL_SEND_ATTACH",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_SEND_ATTACH_id_archivmedien;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_ARCHIVMEDIEN 
	 * Falls keine get_ID_ARCHIVMEDIEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EMAIL_SEND_ATTACH get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_archivmedien(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_ARCHIVMEDIEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EMAIL_SEND_ATTACH_id_archivmedien==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EMAIL_SEND_ATTACH WHERE ID_ARCHIVMEDIEN="+this.get_ID_ARCHIVMEDIEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EMAIL_SEND_ATTACH_id_archivmedien = new RECLIST_EMAIL_SEND_ATTACH (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EMAIL_SEND_ATTACH_id_archivmedien;
	}


	





	
	/**
	 * References the Field ID_MEDIENTYP
	 * Falls keine this.get_ID_MEDIENTYP_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_MEDIENTYP get_UP_RECORD_MEDIENTYP_id_medientyp() throws myException
	{
		if (S.isEmpty(this.get_ID_MEDIENTYP_cUF()))
			return null;
	
	
		if (this.UP_RECORD_MEDIENTYP_id_medientyp==null)
		{
			this.UP_RECORD_MEDIENTYP_id_medientyp = new RECORD_MEDIENTYP(this.get_ID_MEDIENTYP_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_MEDIENTYP_id_medientyp;
	}
	

	public static String FIELD__AENDERUNGSDATUM = "AENDERUNGSDATUM";
	public static String FIELD__AKTIONSPATTERN = "AKTIONSPATTERN";
	public static String FIELD__DATEIBESCHREIBUNG = "DATEIBESCHREIBUNG";
	public static String FIELD__DATEINAME = "DATEINAME";
	public static String FIELD__DOWNLOADNAME = "DOWNLOADNAME";
	public static String FIELD__ERSTELLUNGSDATUM = "ERSTELLUNGSDATUM";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_ARCHIVMEDIEN = "ID_ARCHIVMEDIEN";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_MEDIENTYP = "ID_MEDIENTYP";
	public static String FIELD__ID_TABLE = "ID_TABLE";
	public static String FIELD__IST_ORIGINAL = "IST_ORIGINAL";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LFD_NR = "LFD_NR";
	public static String FIELD__MAILADRESSE = "MAILADRESSE";
	public static String FIELD__MAILERFOLGREICH = "MAILERFOLGREICH";
	public static String FIELD__MEDIENKENNER = "MEDIENKENNER";
	public static String FIELD__PFAD = "PFAD";
	public static String FIELD__PROGRAMM_KENNER = "PROGRAMM_KENNER";
	public static String FIELD__SONDERFELD = "SONDERFELD";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TABLENAME = "TABLENAME";
	public static String FIELD__VORGANG_TYP = "VORGANG_TYP";


	public String get_AENDERUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("AENDERUNGSDATUM");
	}

	public String get_AENDERUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("AENDERUNGSDATUM");	
	}

	public String get_AENDERUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AENDERUNGSDATUM");
	}

	public String get_AENDERUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AENDERUNGSDATUM",cNotNullValue);
	}

	public String get_AENDERUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AENDERUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AENDERUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AENDERUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AENDERUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AENDERUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AENDERUNGSDATUM", calNewValueFormated);
	}
		public String get_AKTIONSPATTERN_cUF() throws myException
	{
		return this.get_UnFormatedValue("AKTIONSPATTERN");
	}

	public String get_AKTIONSPATTERN_cF() throws myException
	{
		return this.get_FormatedValue("AKTIONSPATTERN");	
	}

	public String get_AKTIONSPATTERN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AKTIONSPATTERN");
	}

	public String get_AKTIONSPATTERN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AKTIONSPATTERN",cNotNullValue);
	}

	public String get_AKTIONSPATTERN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AKTIONSPATTERN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AKTIONSPATTERN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AKTIONSPATTERN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AKTIONSPATTERN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AKTIONSPATTERN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AKTIONSPATTERN", calNewValueFormated);
	}
		public String get_DATEIBESCHREIBUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATEIBESCHREIBUNG");
	}

	public String get_DATEIBESCHREIBUNG_cF() throws myException
	{
		return this.get_FormatedValue("DATEIBESCHREIBUNG");	
	}

	public String get_DATEIBESCHREIBUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATEIBESCHREIBUNG");
	}

	public String get_DATEIBESCHREIBUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATEIBESCHREIBUNG",cNotNullValue);
	}

	public String get_DATEIBESCHREIBUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATEIBESCHREIBUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATEIBESCHREIBUNG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATEIBESCHREIBUNG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATEIBESCHREIBUNG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEIBESCHREIBUNG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEIBESCHREIBUNG", calNewValueFormated);
	}
		public String get_DATEINAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("DATEINAME");
	}

	public String get_DATEINAME_cF() throws myException
	{
		return this.get_FormatedValue("DATEINAME");	
	}

	public String get_DATEINAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DATEINAME");
	}

	public String get_DATEINAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DATEINAME",cNotNullValue);
	}

	public String get_DATEINAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DATEINAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DATEINAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DATEINAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DATEINAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DATEINAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEINAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DATEINAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEINAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEINAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEINAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEINAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DATEINAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DATEINAME", calNewValueFormated);
	}
		public String get_DOWNLOADNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("DOWNLOADNAME");
	}

	public String get_DOWNLOADNAME_cF() throws myException
	{
		return this.get_FormatedValue("DOWNLOADNAME");	
	}

	public String get_DOWNLOADNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DOWNLOADNAME");
	}

	public String get_DOWNLOADNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DOWNLOADNAME",cNotNullValue);
	}

	public String get_DOWNLOADNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DOWNLOADNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DOWNLOADNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DOWNLOADNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DOWNLOADNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOADNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOADNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DOWNLOADNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DOWNLOADNAME", calNewValueFormated);
	}
		public String get_ERSTELLUNGSDATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERSTELLUNGSDATUM");
	}

	public String get_ERSTELLUNGSDATUM_cF() throws myException
	{
		return this.get_FormatedValue("ERSTELLUNGSDATUM");	
	}

	public String get_ERSTELLUNGSDATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERSTELLUNGSDATUM");
	}

	public String get_ERSTELLUNGSDATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERSTELLUNGSDATUM",cNotNullValue);
	}

	public String get_ERSTELLUNGSDATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERSTELLUNGSDATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ERSTELLUNGSDATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ERSTELLUNGSDATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ERSTELLUNGSDATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ERSTELLUNGSDATUM", calNewValueFormated);
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
		public String get_ID_ARCHIVMEDIEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ARCHIVMEDIEN");
	}

	public String get_ID_ARCHIVMEDIEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_ARCHIVMEDIEN");	
	}

	public String get_ID_ARCHIVMEDIEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ARCHIVMEDIEN");
	}

	public String get_ID_ARCHIVMEDIEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ARCHIVMEDIEN",cNotNullValue);
	}

	public String get_ID_ARCHIVMEDIEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ARCHIVMEDIEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ARCHIVMEDIEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ARCHIVMEDIEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ARCHIVMEDIEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ARCHIVMEDIEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ARCHIVMEDIEN", calNewValueFormated);
	}
		public Long get_ID_ARCHIVMEDIEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ARCHIVMEDIEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ARCHIVMEDIEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ARCHIVMEDIEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ARCHIVMEDIEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ARCHIVMEDIEN").getDoubleValue();
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
	public String get_ID_ARCHIVMEDIEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARCHIVMEDIEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ARCHIVMEDIEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ARCHIVMEDIEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ARCHIVMEDIEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARCHIVMEDIEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ARCHIVMEDIEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ARCHIVMEDIEN").getBigDecimalValue();
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
	
	
	public String get_ID_MEDIENTYP_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MEDIENTYP");
	}

	public String get_ID_MEDIENTYP_cF() throws myException
	{
		return this.get_FormatedValue("ID_MEDIENTYP");	
	}

	public String get_ID_MEDIENTYP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MEDIENTYP");
	}

	public String get_ID_MEDIENTYP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MEDIENTYP",cNotNullValue);
	}

	public String get_ID_MEDIENTYP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MEDIENTYP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MEDIENTYP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MEDIENTYP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MEDIENTYP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MEDIENTYP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MEDIENTYP", calNewValueFormated);
	}
		public Long get_ID_MEDIENTYP_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MEDIENTYP").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MEDIENTYP_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MEDIENTYP").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MEDIENTYP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MEDIENTYP").getDoubleValue();
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
	public String get_ID_MEDIENTYP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MEDIENTYP_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MEDIENTYP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MEDIENTYP_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MEDIENTYP_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MEDIENTYP").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MEDIENTYP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MEDIENTYP").getBigDecimalValue();
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
	
	
	public String get_ID_TABLE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_TABLE");
	}

	public String get_ID_TABLE_cF() throws myException
	{
		return this.get_FormatedValue("ID_TABLE");	
	}

	public String get_ID_TABLE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_TABLE");
	}

	public String get_ID_TABLE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_TABLE",cNotNullValue);
	}

	public String get_ID_TABLE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_TABLE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_TABLE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_TABLE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_TABLE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABLE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABLE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_TABLE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_TABLE", calNewValueFormated);
	}
		public Long get_ID_TABLE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_TABLE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_TABLE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_TABLE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_TABLE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_TABLE").getDoubleValue();
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
	public String get_ID_TABLE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TABLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_TABLE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_TABLE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_TABLE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TABLE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_TABLE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_TABLE").getBigDecimalValue();
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
	
	
	public String get_IST_ORIGINAL_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_ORIGINAL");
	}

	public String get_IST_ORIGINAL_cF() throws myException
	{
		return this.get_FormatedValue("IST_ORIGINAL");	
	}

	public String get_IST_ORIGINAL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_ORIGINAL");
	}

	public String get_IST_ORIGINAL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_ORIGINAL",cNotNullValue);
	}

	public String get_IST_ORIGINAL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_ORIGINAL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_ORIGINAL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_ORIGINAL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_ORIGINAL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_ORIGINAL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_ORIGINAL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_ORIGINAL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_ORIGINAL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_ORIGINAL", calNewValueFormated);
	}
		public boolean is_IST_ORIGINAL_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_ORIGINAL_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_ORIGINAL_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_ORIGINAL_cUF_NN("N").equals("N"))
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
		public String get_LFD_NR_cUF() throws myException
	{
		return this.get_UnFormatedValue("LFD_NR");
	}

	public String get_LFD_NR_cF() throws myException
	{
		return this.get_FormatedValue("LFD_NR");	
	}

	public String get_LFD_NR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LFD_NR");
	}

	public String get_LFD_NR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LFD_NR",cNotNullValue);
	}

	public String get_LFD_NR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LFD_NR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LFD_NR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFD_NR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFD_NR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LFD_NR", calNewValueFormated);
	}
		public Long get_LFD_NR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("LFD_NR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_LFD_NR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("LFD_NR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_LFD_NR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("LFD_NR").getDoubleValue();
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
	public String get_LFD_NR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LFD_NR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_LFD_NR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LFD_NR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_LFD_NR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("LFD_NR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_LFD_NR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("LFD_NR").getBigDecimalValue();
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
	
	
	public String get_MAILADRESSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAILADRESSE");
	}

	public String get_MAILADRESSE_cF() throws myException
	{
		return this.get_FormatedValue("MAILADRESSE");	
	}

	public String get_MAILADRESSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAILADRESSE");
	}

	public String get_MAILADRESSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAILADRESSE",cNotNullValue);
	}

	public String get_MAILADRESSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAILADRESSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAILADRESSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAILADRESSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAILADRESSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAILADRESSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILADRESSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILADRESSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILADRESSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILADRESSE", calNewValueFormated);
	}
		public String get_MAILERFOLGREICH_cUF() throws myException
	{
		return this.get_UnFormatedValue("MAILERFOLGREICH");
	}

	public String get_MAILERFOLGREICH_cF() throws myException
	{
		return this.get_FormatedValue("MAILERFOLGREICH");	
	}

	public String get_MAILERFOLGREICH_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAILERFOLGREICH");
	}

	public String get_MAILERFOLGREICH_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MAILERFOLGREICH",cNotNullValue);
	}

	public String get_MAILERFOLGREICH_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MAILERFOLGREICH",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MAILERFOLGREICH", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MAILERFOLGREICH(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MAILERFOLGREICH", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MAILERFOLGREICH_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MAILERFOLGREICH", calNewValueFormated);
	}
		public boolean is_MAILERFOLGREICH_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_MAILERFOLGREICH_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_MAILERFOLGREICH_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_MAILERFOLGREICH_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_MEDIENKENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MEDIENKENNER");
	}

	public String get_MEDIENKENNER_cF() throws myException
	{
		return this.get_FormatedValue("MEDIENKENNER");	
	}

	public String get_MEDIENKENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MEDIENKENNER");
	}

	public String get_MEDIENKENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MEDIENKENNER",cNotNullValue);
	}

	public String get_MEDIENKENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MEDIENKENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MEDIENKENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MEDIENKENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MEDIENKENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MEDIENKENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MEDIENKENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MEDIENKENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MEDIENKENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MEDIENKENNER", calNewValueFormated);
	}
		public String get_PFAD_cUF() throws myException
	{
		return this.get_UnFormatedValue("PFAD");
	}

	public String get_PFAD_cF() throws myException
	{
		return this.get_FormatedValue("PFAD");	
	}

	public String get_PFAD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PFAD");
	}

	public String get_PFAD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PFAD",cNotNullValue);
	}

	public String get_PFAD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PFAD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PFAD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PFAD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PFAD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PFAD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PFAD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PFAD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PFAD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PFAD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PFAD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PFAD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PFAD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PFAD", calNewValueFormated);
	}
		public String get_PROGRAMM_KENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("PROGRAMM_KENNER");
	}

	public String get_PROGRAMM_KENNER_cF() throws myException
	{
		return this.get_FormatedValue("PROGRAMM_KENNER");	
	}

	public String get_PROGRAMM_KENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PROGRAMM_KENNER");
	}

	public String get_PROGRAMM_KENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PROGRAMM_KENNER",cNotNullValue);
	}

	public String get_PROGRAMM_KENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PROGRAMM_KENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PROGRAMM_KENNER(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PROGRAMM_KENNER", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PROGRAMM_KENNER_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PROGRAMM_KENNER", calNewValueFormated);
	}
		public String get_SONDERFELD_cUF() throws myException
	{
		return this.get_UnFormatedValue("SONDERFELD");
	}

	public String get_SONDERFELD_cF() throws myException
	{
		return this.get_FormatedValue("SONDERFELD");	
	}

	public String get_SONDERFELD_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("SONDERFELD");
	}

	public String get_SONDERFELD_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("SONDERFELD",cNotNullValue);
	}

	public String get_SONDERFELD_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("SONDERFELD",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("SONDERFELD", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_SONDERFELD(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("SONDERFELD", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("SONDERFELD", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERFELD", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERFELD", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_SONDERFELD_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("SONDERFELD", calNewValueFormated);
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
	
	
	public String get_TABLENAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("TABLENAME");
	}

	public String get_TABLENAME_cF() throws myException
	{
		return this.get_FormatedValue("TABLENAME");	
	}

	public String get_TABLENAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TABLENAME");
	}

	public String get_TABLENAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TABLENAME",cNotNullValue);
	}

	public String get_TABLENAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TABLENAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TABLENAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TABLENAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TABLENAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TABLENAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TABLENAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TABLENAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TABLENAME", calNewValueFormated);
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
	

	public static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{
	put("AENDERUNGSDATUM",MyRECORD.DATATYPES.DATE);
	put("AKTIONSPATTERN",MyRECORD.DATATYPES.TEXT);
	put("DATEIBESCHREIBUNG",MyRECORD.DATATYPES.TEXT);
	put("DATEINAME",MyRECORD.DATATYPES.TEXT);
	put("DOWNLOADNAME",MyRECORD.DATATYPES.TEXT);
	put("ERSTELLUNGSDATUM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_ARCHIVMEDIEN",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MEDIENTYP",MyRECORD.DATATYPES.NUMBER);
	put("ID_TABLE",MyRECORD.DATATYPES.NUMBER);
	put("IST_ORIGINAL",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("LFD_NR",MyRECORD.DATATYPES.NUMBER);
	put("MAILADRESSE",MyRECORD.DATATYPES.TEXT);
	put("MAILERFOLGREICH",MyRECORD.DATATYPES.YESNO);
	put("MEDIENKENNER",MyRECORD.DATATYPES.TEXT);
	put("PFAD",MyRECORD.DATATYPES.TEXT);
	put("PROGRAMM_KENNER",MyRECORD.DATATYPES.TEXT);
	put("SONDERFELD",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TABLENAME",MyRECORD.DATATYPES.TEXT);
	put("VORGANG_TYP",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_ARCHIVMEDIEN.HM_FIELDS;	
	}

}
