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

public class RECORD_MANDANT_ZUSATZ_FELDNAMEN extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JD_MANDANT_ZUSATZ_FELDNAMEN";
    public static String IDFIELD   = "ID_MANDANT_ZUSATZ_FELDNAMEN";
    

	//erzeugen eines RECORDNEW_JD_MANDANT_ZUSATZ_FELDNAMEN - felds
	private RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN   recNEW = null;

    private _TAB  tab = _TAB.mandant_zusatz_feldnamen;  



	public RECORD_MANDANT_ZUSATZ_FELDNAMEN() throws myException
	{
		super();
		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");
	}


	public RECORD_MANDANT_ZUSATZ_FELDNAMEN(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");
	}



	public RECORD_MANDANT_ZUSATZ_FELDNAMEN(RECORD_MANDANT_ZUSATZ_FELDNAMEN recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MANDANT_ZUSATZ_FELDNAMEN.TABLENAME);
	}


	//2014-04-03
	public RECORD_MANDANT_ZUSATZ_FELDNAMEN(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");
		this.set_Tablename_To_FieldMetaDefs(RECORD_MANDANT_ZUSATZ_FELDNAMEN.TABLENAME);
	}




	public RECORD_MANDANT_ZUSATZ_FELDNAMEN(long lID_Unformated) throws myException
	{
		super("JD_MANDANT_ZUSATZ_FELDNAMEN","ID_MANDANT_ZUSATZ_FELDNAMEN",""+lID_Unformated);
		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MANDANT_ZUSATZ_FELDNAMEN.TABLENAME);
	}

	public RECORD_MANDANT_ZUSATZ_FELDNAMEN(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN", "ID_MANDANT_ZUSATZ_FELDNAMEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MANDANT_ZUSATZ_FELDNAMEN.TABLENAME);
	}
	
	

	public RECORD_MANDANT_ZUSATZ_FELDNAMEN(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JD_MANDANT_ZUSATZ_FELDNAMEN","ID_MANDANT_ZUSATZ_FELDNAMEN",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MANDANT_ZUSATZ_FELDNAMEN.TABLENAME);

	}


	public RECORD_MANDANT_ZUSATZ_FELDNAMEN(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JD_MANDANT_ZUSATZ_FELDNAMEN");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN", "ID_MANDANT_ZUSATZ_FELDNAMEN="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_MANDANT_ZUSATZ_FELDNAMEN.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_MANDANT_ZUSATZ_FELDNAMEN.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_MANDANT_ZUSATZ_FELDNAMEN";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MANDANT_ZUSATZ_FELDNAMEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_MANDANT_ZUSATZ_FELDNAMEN", bibE2.cTO(), "ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_MANDANT_ZUSATZ_FELDNAMEN was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_MANDANT_ZUSATZ_FELDNAMEN", bibE2.cTO(), "ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN WHERE ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN WHERE ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF();
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
			if (S.isFull(this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN", "ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN get_RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance() throws myException {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN();
	}
	
	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(Conn);
    }

	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance(RECORD_MANDANT_ZUSATZ_FELDNAMEN recordOrig) {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(recordOrig);
    }

	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance(long lID_Unformated) throws myException {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(lID_Unformated);
    }

	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(lID_Unformated, Conn);
	}

	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_MANDANT_ZUSATZ_FELDNAMEN create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(recordOrig);	    
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
    public RECORD_MANDANT_ZUSATZ_FELDNAMEN build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN WHERE ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF());
      }
      //return new RECORD_MANDANT_ZUSATZ_FELDNAMEN(this.get_cSQL_4_Build());
      RECORD_MANDANT_ZUSATZ_FELDNAMEN rec = new RECORD_MANDANT_ZUSATZ_FELDNAMEN();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN  recNew = new RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN();
		
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
    public RECORD_MANDANT_ZUSATZ_FELDNAMEN set_recordNew(RECORDNEW_MANDANT_ZUSATZ_FELDNAMEN recnew) throws myException {
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
	
	



		private RECLIST_MANDANT_ZUSATZ DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen = null ;






	/**
	 * References the Field ID_MANDANT_ZUSATZ_FELDNAMEN 
	 * Falls keine get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_ZUSATZ get_DOWN_RECORD_LIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen() throws myException
	{
		if (S.isEmpty(this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen==null)
		{
			this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen = new RECLIST_MANDANT_ZUSATZ (
			       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ WHERE ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF()+" ORDER BY ID_MANDANT_ZUSATZ",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_MANDANT_ZUSATZ_FELDNAMEN 
	 * Falls keine get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_MANDANT_ZUSATZ get_DOWN_RECORD_LIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ WHERE ID_MANDANT_ZUSATZ_FELDNAMEN="+this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen = new RECLIST_MANDANT_ZUSATZ (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_MANDANT_ZUSATZ_id_mandant_zusatz_feldnamen;
	}


	

	public static String FIELD__DEFAULT_FLOAT_VALUE = "DEFAULT_FLOAT_VALUE";
	public static String FIELD__DEFAULT_LONG_VALUE = "DEFAULT_LONG_VALUE";
	public static String FIELD__DEFAULT_TEXT_VALUE = "DEFAULT_TEXT_VALUE";
	public static String FIELD__DEFAULT_YES_NO_VALUE = "DEFAULT_YES_NO_VALUE";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FIELDNAME = "FIELDNAME";
	public static String FIELD__FIELD_TYPE = "FIELD_TYPE";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_MANDANT_ZUSATZ_FELDNAMEN = "ID_MANDANT_ZUSATZ_FELDNAMEN";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__MASK_TEXT = "MASK_TEXT";
	public static String FIELD__RELATION_INFO = "RELATION_INFO";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


	public String get_DEFAULT_FLOAT_VALUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_FLOAT_VALUE");
	}

	public String get_DEFAULT_FLOAT_VALUE_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT_FLOAT_VALUE");	
	}

	public String get_DEFAULT_FLOAT_VALUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT_FLOAT_VALUE");
	}

	public String get_DEFAULT_FLOAT_VALUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_FLOAT_VALUE",cNotNullValue);
	}

	public String get_DEFAULT_FLOAT_VALUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT_FLOAT_VALUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_FLOAT_VALUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT_FLOAT_VALUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT_FLOAT_VALUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT_FLOAT_VALUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_FLOAT_VALUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT_FLOAT_VALUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_FLOAT_VALUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_FLOAT_VALUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_FLOAT_VALUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_FLOAT_VALUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_FLOAT_VALUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_FLOAT_VALUE", calNewValueFormated);
	}
		public Double get_DEFAULT_FLOAT_VALUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DEFAULT_FLOAT_VALUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DEFAULT_FLOAT_VALUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DEFAULT_FLOAT_VALUE").getDoubleValue();
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
	public String get_DEFAULT_FLOAT_VALUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DEFAULT_FLOAT_VALUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_DEFAULT_FLOAT_VALUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DEFAULT_FLOAT_VALUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_DEFAULT_FLOAT_VALUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DEFAULT_FLOAT_VALUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DEFAULT_FLOAT_VALUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DEFAULT_FLOAT_VALUE").getBigDecimalValue();
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
	
	
	public String get_DEFAULT_LONG_VALUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_LONG_VALUE");
	}

	public String get_DEFAULT_LONG_VALUE_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT_LONG_VALUE");	
	}

	public String get_DEFAULT_LONG_VALUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT_LONG_VALUE");
	}

	public String get_DEFAULT_LONG_VALUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_LONG_VALUE",cNotNullValue);
	}

	public String get_DEFAULT_LONG_VALUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT_LONG_VALUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_LONG_VALUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT_LONG_VALUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT_LONG_VALUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT_LONG_VALUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_LONG_VALUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT_LONG_VALUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_LONG_VALUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_LONG_VALUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_LONG_VALUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_LONG_VALUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_LONG_VALUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_LONG_VALUE", calNewValueFormated);
	}
		public Long get_DEFAULT_LONG_VALUE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("DEFAULT_LONG_VALUE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_DEFAULT_LONG_VALUE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("DEFAULT_LONG_VALUE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_DEFAULT_LONG_VALUE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("DEFAULT_LONG_VALUE").getDoubleValue();
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
	public String get_DEFAULT_LONG_VALUE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DEFAULT_LONG_VALUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_DEFAULT_LONG_VALUE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_DEFAULT_LONG_VALUE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_DEFAULT_LONG_VALUE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("DEFAULT_LONG_VALUE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_DEFAULT_LONG_VALUE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("DEFAULT_LONG_VALUE").getBigDecimalValue();
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
	
	
	public String get_DEFAULT_TEXT_VALUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_TEXT_VALUE");
	}

	public String get_DEFAULT_TEXT_VALUE_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT_TEXT_VALUE");	
	}

	public String get_DEFAULT_TEXT_VALUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT_TEXT_VALUE");
	}

	public String get_DEFAULT_TEXT_VALUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_TEXT_VALUE",cNotNullValue);
	}

	public String get_DEFAULT_TEXT_VALUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT_TEXT_VALUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_TEXT_VALUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT_TEXT_VALUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT_TEXT_VALUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT_TEXT_VALUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_TEXT_VALUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT_TEXT_VALUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_TEXT_VALUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_TEXT_VALUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_TEXT_VALUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_TEXT_VALUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_TEXT_VALUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_TEXT_VALUE", calNewValueFormated);
	}
		public String get_DEFAULT_YES_NO_VALUE_cUF() throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_YES_NO_VALUE");
	}

	public String get_DEFAULT_YES_NO_VALUE_cF() throws myException
	{
		return this.get_FormatedValue("DEFAULT_YES_NO_VALUE");	
	}

	public String get_DEFAULT_YES_NO_VALUE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEFAULT_YES_NO_VALUE");
	}

	public String get_DEFAULT_YES_NO_VALUE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("DEFAULT_YES_NO_VALUE",cNotNullValue);
	}

	public String get_DEFAULT_YES_NO_VALUE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("DEFAULT_YES_NO_VALUE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_YES_NO_VALUE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("DEFAULT_YES_NO_VALUE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEFAULT_YES_NO_VALUE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("DEFAULT_YES_NO_VALUE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_YES_NO_VALUE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("DEFAULT_YES_NO_VALUE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_YES_NO_VALUE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_YES_NO_VALUE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_YES_NO_VALUE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_YES_NO_VALUE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEFAULT_YES_NO_VALUE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("DEFAULT_YES_NO_VALUE", calNewValueFormated);
	}
		public boolean is_DEFAULT_YES_NO_VALUE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_DEFAULT_YES_NO_VALUE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_DEFAULT_YES_NO_VALUE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_DEFAULT_YES_NO_VALUE_cUF_NN("N").equals("N"))
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
		public String get_FIELDNAME_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIELDNAME");
	}

	public String get_FIELDNAME_cF() throws myException
	{
		return this.get_FormatedValue("FIELDNAME");	
	}

	public String get_FIELDNAME_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIELDNAME");
	}

	public String get_FIELDNAME_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIELDNAME",cNotNullValue);
	}

	public String get_FIELDNAME_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIELDNAME",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIELDNAME(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIELDNAME", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIELDNAME", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELDNAME_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELDNAME", calNewValueFormated);
	}
		public String get_FIELD_TYPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("FIELD_TYPE");
	}

	public String get_FIELD_TYPE_cF() throws myException
	{
		return this.get_FormatedValue("FIELD_TYPE");	
	}

	public String get_FIELD_TYPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("FIELD_TYPE");
	}

	public String get_FIELD_TYPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("FIELD_TYPE",cNotNullValue);
	}

	public String get_FIELD_TYPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("FIELD_TYPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_FIELD_TYPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("FIELD_TYPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_FIELD_TYPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("FIELD_TYPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELD_TYPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("FIELD_TYPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELD_TYPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELD_TYPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELD_TYPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELD_TYPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_FIELD_TYPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("FIELD_TYPE", calNewValueFormated);
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
	
	
	public String get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MANDANT_ZUSATZ_FELDNAMEN");
	}

	public String get_ID_MANDANT_ZUSATZ_FELDNAMEN_cF() throws myException
	{
		return this.get_FormatedValue("ID_MANDANT_ZUSATZ_FELDNAMEN");	
	}

	public String get_ID_MANDANT_ZUSATZ_FELDNAMEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MANDANT_ZUSATZ_FELDNAMEN");
	}

	public String get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MANDANT_ZUSATZ_FELDNAMEN",cNotNullValue);
	}

	public String get_ID_MANDANT_ZUSATZ_FELDNAMEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MANDANT_ZUSATZ_FELDNAMEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_MANDANT_ZUSATZ_FELDNAMEN", calNewValueFormated);
	}
		public Long get_ID_MANDANT_ZUSATZ_FELDNAMEN_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MANDANT_ZUSATZ_FELDNAMEN").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MANDANT_ZUSATZ_FELDNAMEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MANDANT_ZUSATZ_FELDNAMEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MANDANT_ZUSATZ_FELDNAMEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MANDANT_ZUSATZ_FELDNAMEN").getDoubleValue();
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
	public String get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_MANDANT_ZUSATZ_FELDNAMEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_ZUSATZ_FELDNAMEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_MANDANT_ZUSATZ_FELDNAMEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MANDANT_ZUSATZ_FELDNAMEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_MANDANT_ZUSATZ_FELDNAMEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_MANDANT_ZUSATZ_FELDNAMEN").getBigDecimalValue();
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
		public String get_MASK_TEXT_cUF() throws myException
	{
		return this.get_UnFormatedValue("MASK_TEXT");
	}

	public String get_MASK_TEXT_cF() throws myException
	{
		return this.get_FormatedValue("MASK_TEXT");	
	}

	public String get_MASK_TEXT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MASK_TEXT");
	}

	public String get_MASK_TEXT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MASK_TEXT",cNotNullValue);
	}

	public String get_MASK_TEXT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MASK_TEXT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MASK_TEXT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MASK_TEXT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MASK_TEXT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("MASK_TEXT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASK_TEXT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("MASK_TEXT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASK_TEXT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MASK_TEXT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASK_TEXT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MASK_TEXT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MASK_TEXT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("MASK_TEXT", calNewValueFormated);
	}
		public String get_RELATION_INFO_cUF() throws myException
	{
		return this.get_UnFormatedValue("RELATION_INFO");
	}

	public String get_RELATION_INFO_cF() throws myException
	{
		return this.get_FormatedValue("RELATION_INFO");	
	}

	public String get_RELATION_INFO_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("RELATION_INFO");
	}

	public String get_RELATION_INFO_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("RELATION_INFO",cNotNullValue);
	}

	public String get_RELATION_INFO_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("RELATION_INFO",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_RELATION_INFO(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("RELATION_INFO", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_RELATION_INFO(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("RELATION_INFO", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELATION_INFO_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("RELATION_INFO", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELATION_INFO_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELATION_INFO", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELATION_INFO_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELATION_INFO", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_RELATION_INFO_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("RELATION_INFO", calNewValueFormated);
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
	put("DEFAULT_FLOAT_VALUE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("DEFAULT_LONG_VALUE",MyRECORD.DATATYPES.NUMBER);
	put("DEFAULT_TEXT_VALUE",MyRECORD.DATATYPES.TEXT);
	put("DEFAULT_YES_NO_VALUE",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("FIELDNAME",MyRECORD.DATATYPES.TEXT);
	put("FIELD_TYPE",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT_ZUSATZ_FELDNAMEN",MyRECORD.DATATYPES.NUMBER);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("MASK_TEXT",MyRECORD.DATATYPES.TEXT);
	put("RELATION_INFO",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_MANDANT_ZUSATZ_FELDNAMEN.HM_FIELDS;	
	}

}
