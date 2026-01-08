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

public class RECORD_BEWEGUNG_VEKTOR extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_BEWEGUNG_VEKTOR";
    public static String IDFIELD   = "ID_BEWEGUNG_VEKTOR";
    

	//erzeugen eines RECORDNEW_JT_BEWEGUNG_VEKTOR - felds
	private RECORDNEW_BEWEGUNG_VEKTOR   recNEW = null;

    private _TAB  tab = _TAB.bewegung_vektor;  



	public RECORD_BEWEGUNG_VEKTOR() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");
	}


	public RECORD_BEWEGUNG_VEKTOR(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");
	}



	public RECORD_BEWEGUNG_VEKTOR(RECORD_BEWEGUNG_VEKTOR recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG_VEKTOR.TABLENAME);
	}


	//2014-04-03
	public RECORD_BEWEGUNG_VEKTOR(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG_VEKTOR.TABLENAME);
	}




	public RECORD_BEWEGUNG_VEKTOR(long lID_Unformated) throws myException
	{
		super("JT_BEWEGUNG_VEKTOR","ID_BEWEGUNG_VEKTOR",""+lID_Unformated);
		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG_VEKTOR.TABLENAME);
	}

	public RECORD_BEWEGUNG_VEKTOR(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG_VEKTOR", "ID_BEWEGUNG_VEKTOR="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG_VEKTOR", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG_VEKTOR.TABLENAME);
	}
	
	

	public RECORD_BEWEGUNG_VEKTOR(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_BEWEGUNG_VEKTOR","ID_BEWEGUNG_VEKTOR",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG_VEKTOR.TABLENAME);

	}


	public RECORD_BEWEGUNG_VEKTOR(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_BEWEGUNG_VEKTOR");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG_VEKTOR", "ID_BEWEGUNG_VEKTOR="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG_VEKTOR", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_BEWEGUNG_VEKTOR.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_BEWEGUNG_VEKTOR();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_BEWEGUNG_VEKTOR.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_BEWEGUNG_VEKTOR";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_BEWEGUNG_VEKTOR_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_BEWEGUNG_VEKTOR_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BEWEGUNG_VEKTOR was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BEWEGUNG_VEKTOR", bibE2.cTO(), "ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_BEWEGUNG_VEKTOR was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_BEWEGUNG_VEKTOR", bibE2.cTO(), "ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF();
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
			if (S.isFull(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_BEWEGUNG_VEKTOR", "ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_BEWEGUNG_VEKTOR get_RECORDNEW_BEWEGUNG_VEKTOR() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_BEWEGUNG_VEKTOR();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_BEWEGUNG_VEKTOR(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_BEWEGUNG_VEKTOR create_Instance() throws myException {
		return new RECORD_BEWEGUNG_VEKTOR();
	}
	
	public static RECORD_BEWEGUNG_VEKTOR create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_BEWEGUNG_VEKTOR(Conn);
    }

	public static RECORD_BEWEGUNG_VEKTOR create_Instance(RECORD_BEWEGUNG_VEKTOR recordOrig) {
		return new RECORD_BEWEGUNG_VEKTOR(recordOrig);
    }

	public static RECORD_BEWEGUNG_VEKTOR create_Instance(long lID_Unformated) throws myException {
		return new RECORD_BEWEGUNG_VEKTOR(lID_Unformated);
    }

	public static RECORD_BEWEGUNG_VEKTOR create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_BEWEGUNG_VEKTOR(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_BEWEGUNG_VEKTOR create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_BEWEGUNG_VEKTOR(lID_Unformated, Conn);
	}

	public static RECORD_BEWEGUNG_VEKTOR create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_BEWEGUNG_VEKTOR(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_BEWEGUNG_VEKTOR create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_BEWEGUNG_VEKTOR(recordOrig);	    
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
    public RECORD_BEWEGUNG_VEKTOR build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF());
      }
      //return new RECORD_BEWEGUNG_VEKTOR(this.get_cSQL_4_Build());
      RECORD_BEWEGUNG_VEKTOR rec = new RECORD_BEWEGUNG_VEKTOR();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_BEWEGUNG_VEKTOR
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_BEWEGUNG_VEKTOR-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_BEWEGUNG_VEKTOR get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_BEWEGUNG_VEKTOR_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_BEWEGUNG_VEKTOR  recNew = new RECORDNEW_BEWEGUNG_VEKTOR();
		
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
    public RECORD_BEWEGUNG_VEKTOR set_recordNew(RECORDNEW_BEWEGUNG_VEKTOR recnew) throws myException {
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
	
	



		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_fremdware = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_spedition = null;




		private RECORD_ADRESSE UP_RECORD_ADRESSE_id_adresse_start_logistik = null;




		private RECORD_BEWEGUNG UP_RECORD_BEWEGUNG_id_bewegung = null;




		private RECLIST_BEWEGUNG_ATOM DOWN_RECLIST_BEWEGUNG_ATOM_id_bewegung_vektor = null ;




		private RECLIST_BEWEGUNG_VEKTOR_LOG DOWN_RECLIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor = null ;




		private RECLIST_BEWEGUNG_VEKTOR_PN DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor = null ;




		private RECLIST_BEWEGUNG_VEKTOR_POS DOWN_RECLIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor = null ;




		private RECORD_EAK_CODE UP_RECORD_EAK_CODE_id_eak_code = null;




		private RECORD_UMA_KONTRAKT UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt = null;




		private RECORD_VERPACKUNGSART UP_RECORD_VERPACKUNGSART_id_verpackungsart = null;






	
	/**
	 * References the Field ID_ADRESSE_FREMDWARE
	 * Falls keine this.get_ID_ADRESSE_FREMDWARE_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_fremdware() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_FREMDWARE_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_fremdware==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_fremdware = new RECORD_ADRESSE(this.get_ID_ADRESSE_FREMDWARE_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_fremdware;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_SPEDITION
	 * Falls keine this.get_ID_ADRESSE_SPEDITION_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_spedition() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_SPEDITION_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_spedition==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_spedition = new RECORD_ADRESSE(this.get_ID_ADRESSE_SPEDITION_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_spedition;
	}
	





	
	/**
	 * References the Field ID_ADRESSE_START_LOGISTIK
	 * Falls keine this.get_ID_ADRESSE_START_LOGISTIK_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_ADRESSE get_UP_RECORD_ADRESSE_id_adresse_start_logistik() throws myException
	{
		if (S.isEmpty(this.get_ID_ADRESSE_START_LOGISTIK_cUF()))
			return null;
	
	
		if (this.UP_RECORD_ADRESSE_id_adresse_start_logistik==null)
		{
			this.UP_RECORD_ADRESSE_id_adresse_start_logistik = new RECORD_ADRESSE(this.get_ID_ADRESSE_START_LOGISTIK_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_ADRESSE_id_adresse_start_logistik;
	}
	





	
	/**
	 * References the Field ID_BEWEGUNG
	 * Falls keine this.get_ID_BEWEGUNG_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_BEWEGUNG get_UP_RECORD_BEWEGUNG_id_bewegung() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_cUF()))
			return null;
	
	
		if (this.UP_RECORD_BEWEGUNG_id_bewegung==null)
		{
			this.UP_RECORD_BEWEGUNG_id_bewegung = new RECORD_BEWEGUNG(this.get_ID_BEWEGUNG_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_BEWEGUNG_id_bewegung;
	}
	





	/**
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_bewegung_vektor==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_bewegung_vektor = new RECLIST_BEWEGUNG_ATOM (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF()+" ORDER BY ID_BEWEGUNG_ATOM",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_bewegung_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_ATOM get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_ATOM_id_bewegung_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_ATOM_id_bewegung_vektor = new RECLIST_BEWEGUNG_ATOM (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_ATOM_id_bewegung_vektor;
	}


	





	/**
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_LOG get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor = new RECLIST_BEWEGUNG_VEKTOR_LOG (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_LOG WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_LOG",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_LOG get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_LOG WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor = new RECLIST_BEWEGUNG_VEKTOR_LOG (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_LOG_id_bewegung_vektor;
	}


	





	/**
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor = new RECLIST_BEWEGUNG_VEKTOR_PN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_PN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_PN get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_PN WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor = new RECLIST_BEWEGUNG_VEKTOR_PN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_PN_id_bewegung_vektor;
	}


	





	/**
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_POS get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor() throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor==null)
		{
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor = new RECLIST_BEWEGUNG_VEKTOR_POS (
			       "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_POS WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF()+" ORDER BY ID_BEWEGUNG_VEKTOR_POS",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BEWEGUNG_VEKTOR 
	 * Falls keine get_ID_BEWEGUNG_VEKTOR_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_BEWEGUNG_VEKTOR_POS get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_BEWEGUNG_VEKTOR_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR_POS WHERE ID_BEWEGUNG_VEKTOR="+this.get_ID_BEWEGUNG_VEKTOR_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor = new RECLIST_BEWEGUNG_VEKTOR_POS (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor;
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
	 * References the Field ID_UMA_KONTRAKT
	 * Falls keine this.get_ID_UMA_KONTRAKT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_UMA_KONTRAKT get_UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt() throws myException
	{
		if (S.isEmpty(this.get_ID_UMA_KONTRAKT_cUF()))
			return null;
	
	
		if (this.UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt==null)
		{
			this.UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt = new RECORD_UMA_KONTRAKT(this.get_ID_UMA_KONTRAKT_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt;
	}
	





	
	/**
	 * References the Field ID_VERPACKUNGSART
	 * Falls keine this.get_ID_VERPACKUNGSART_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECORD_VERPACKUNGSART get_UP_RECORD_VERPACKUNGSART_id_verpackungsart() throws myException
	{
		if (S.isEmpty(this.get_ID_VERPACKUNGSART_cUF()))
			return null;
	
	
		if (this.UP_RECORD_VERPACKUNGSART_id_verpackungsart==null)
		{
			this.UP_RECORD_VERPACKUNGSART_id_verpackungsart = new RECORD_VERPACKUNGSART(this.get_ID_VERPACKUNGSART_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_VERPACKUNGSART_id_verpackungsart;
	}
	

	public static String FIELD__ABGESCHLOSSEN = "ABGESCHLOSSEN";
	public static String FIELD__ABGESCHLOSSEN_AM = "ABGESCHLOSSEN_AM";
	public static String FIELD__ABGESCHLOSSEN_VON = "ABGESCHLOSSEN_VON";
	public static String FIELD__ANHAENGERKENNZEICHEN = "ANHAENGERKENNZEICHEN";
	public static String FIELD__AVV_AUSSTELLUNG_DATUM = "AVV_AUSSTELLUNG_DATUM";
	public static String FIELD__A_DATUM_BIS = "A_DATUM_BIS";
	public static String FIELD__A_DATUM_VON = "A_DATUM_VON";
	public static String FIELD__BEMERKUNG = "BEMERKUNG";
	public static String FIELD__BEMERKUNG_FUER_KUNDE = "BEMERKUNG_FUER_KUNDE";
	public static String FIELD__BEMERKUNG_SACHBEARBEITER = "BEMERKUNG_SACHBEARBEITER";
	public static String FIELD__DELETED = "DELETED";
	public static String FIELD__DEL_DATE = "DEL_DATE";
	public static String FIELD__DEL_GRUND = "DEL_GRUND";
	public static String FIELD__DEL_KUERZEL = "DEL_KUERZEL";
	public static String FIELD__EK_VK_ZUORD_ZWANG = "EK_VK_ZUORD_ZWANG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__EU_BLATT_MENGE = "EU_BLATT_MENGE";
	public static String FIELD__EU_BLATT_VOLUMEN = "EU_BLATT_VOLUMEN";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GELANGENSBESTAETIGUNG_ERHALTEN = "GELANGENSBESTAETIGUNG_ERHALTEN";
	public static String FIELD__ID_ADRESSE_FREMDWARE = "ID_ADRESSE_FREMDWARE";
	public static String FIELD__ID_ADRESSE_SPEDITION = "ID_ADRESSE_SPEDITION";
	public static String FIELD__ID_ADRESSE_START_LOGISTIK = "ID_ADRESSE_START_LOGISTIK";
	public static String FIELD__ID_BEWEGUNG = "ID_BEWEGUNG";
	public static String FIELD__ID_BEWEGUNG_ATOM_TRIGSTART = "ID_BEWEGUNG_ATOM_TRIGSTART";
	public static String FIELD__ID_BEWEGUNG_ATOM_TRIGZIEL = "ID_BEWEGUNG_ATOM_TRIGZIEL";
	public static String FIELD__ID_BEWEGUNG_VEKTOR = "ID_BEWEGUNG_VEKTOR";
	public static String FIELD__ID_EAK_CODE = "ID_EAK_CODE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_UMA_KONTRAKT = "ID_UMA_KONTRAKT";
	public static String FIELD__ID_VEKTOR_GRUPPE = "ID_VEKTOR_GRUPPE";
	public static String FIELD__ID_VERPACKUNGSART = "ID_VERPACKUNGSART";
	public static String FIELD__KOSTEN_PRODUKT_WA = "KOSTEN_PRODUKT_WA";
	public static String FIELD__KOSTEN_PRODUKT_WE = "KOSTEN_PRODUKT_WE";
	public static String FIELD__KOSTEN_TRANSPORT_WA = "KOSTEN_TRANSPORT_WA";
	public static String FIELD__KOSTEN_TRANSPORT_WE = "KOSTEN_TRANSPORT_WE";
	public static String FIELD__LAENDERCODE_TRANSIT1 = "LAENDERCODE_TRANSIT1";
	public static String FIELD__LAENDERCODE_TRANSIT2 = "LAENDERCODE_TRANSIT2";
	public static String FIELD__LAENDERCODE_TRANSIT3 = "LAENDERCODE_TRANSIT3";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__L_DATUM_BIS = "L_DATUM_BIS";
	public static String FIELD__L_DATUM_VON = "L_DATUM_VON";
	public static String FIELD__POSNR = "POSNR";
	public static String FIELD__PRINT_EU_AMTSBLATT = "PRINT_EU_AMTSBLATT";
	public static String FIELD__STATISTIK_TIMESTAMP = "STATISTIK_TIMESTAMP";
	public static String FIELD__STATUS = "STATUS";
	public static String FIELD__STORNIERT = "STORNIERT";
	public static String FIELD__STORNIERT_AM = "STORNIERT_AM";
	public static String FIELD__STORNIERT_GRUND = "STORNIERT_GRUND";
	public static String FIELD__STORNIERT_VON = "STORNIERT_VON";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";
	public static String FIELD__TRANSPORTKENNZEICHEN = "TRANSPORTKENNZEICHEN";
	public static String FIELD__TRANSPORTMITTEL = "TRANSPORTMITTEL";
	public static String FIELD__VARIANTE = "VARIANTE";
	public static String FIELD__WARENKLASSE = "WARENKLASSE";
	public static String FIELD__ZAHL_GUTPOS = "ZAHL_GUTPOS";
	public static String FIELD__ZAHL_RECHPOS = "ZAHL_RECHPOS";
	public static String FIELD__ZEITSTEMPEL = "ZEITSTEMPEL";


	public String get_ABGESCHLOSSEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN");	
	}

	public String get_ABGESCHLOSSEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN");
	}

	public String get_ABGESCHLOSSEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN", calNewValueFormated);
	}
		public boolean is_ABGESCHLOSSEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_ABGESCHLOSSEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_ABGESCHLOSSEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ABGESCHLOSSEN_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_AM");
	}

	public String get_ABGESCHLOSSEN_AM_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_AM");	
	}

	public String get_ABGESCHLOSSEN_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN_AM");
	}

	public String get_ABGESCHLOSSEN_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_AM",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_AM", calNewValueFormated);
	}
		public String get_ABGESCHLOSSEN_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_VON");
	}

	public String get_ABGESCHLOSSEN_VON_cF() throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_VON");	
	}

	public String get_ABGESCHLOSSEN_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ABGESCHLOSSEN_VON");
	}

	public String get_ABGESCHLOSSEN_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ABGESCHLOSSEN_VON",cNotNullValue);
	}

	public String get_ABGESCHLOSSEN_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ABGESCHLOSSEN_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ABGESCHLOSSEN_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ABGESCHLOSSEN_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ABGESCHLOSSEN_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ABGESCHLOSSEN_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ABGESCHLOSSEN_VON", calNewValueFormated);
	}
		public String get_ANHAENGERKENNZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("ANHAENGERKENNZEICHEN");
	}

	public String get_ANHAENGERKENNZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("ANHAENGERKENNZEICHEN");	
	}

	public String get_ANHAENGERKENNZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANHAENGERKENNZEICHEN");
	}

	public String get_ANHAENGERKENNZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ANHAENGERKENNZEICHEN",cNotNullValue);
	}

	public String get_ANHAENGERKENNZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ANHAENGERKENNZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ANHAENGERKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ANHAENGERKENNZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ANHAENGERKENNZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ANHAENGERKENNZEICHEN", calNewValueFormated);
	}
		public String get_AVV_AUSSTELLUNG_DATUM_cUF() throws myException
	{
		return this.get_UnFormatedValue("AVV_AUSSTELLUNG_DATUM");
	}

	public String get_AVV_AUSSTELLUNG_DATUM_cF() throws myException
	{
		return this.get_FormatedValue("AVV_AUSSTELLUNG_DATUM");	
	}

	public String get_AVV_AUSSTELLUNG_DATUM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("AVV_AUSSTELLUNG_DATUM");
	}

	public String get_AVV_AUSSTELLUNG_DATUM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("AVV_AUSSTELLUNG_DATUM",cNotNullValue);
	}

	public String get_AVV_AUSSTELLUNG_DATUM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("AVV_AUSSTELLUNG_DATUM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_AVV_AUSSTELLUNG_DATUM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_AVV_AUSSTELLUNG_DATUM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("AVV_AUSSTELLUNG_DATUM", calNewValueFormated);
	}
		public String get_A_DATUM_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_DATUM_BIS");
	}

	public String get_A_DATUM_BIS_cF() throws myException
	{
		return this.get_FormatedValue("A_DATUM_BIS");	
	}

	public String get_A_DATUM_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_DATUM_BIS");
	}

	public String get_A_DATUM_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_DATUM_BIS",cNotNullValue);
	}

	public String get_A_DATUM_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_DATUM_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_DATUM_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_DATUM_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_DATUM_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_DATUM_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_DATUM_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_DATUM_BIS", calNewValueFormated);
	}
		public String get_A_DATUM_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("A_DATUM_VON");
	}

	public String get_A_DATUM_VON_cF() throws myException
	{
		return this.get_FormatedValue("A_DATUM_VON");	
	}

	public String get_A_DATUM_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("A_DATUM_VON");
	}

	public String get_A_DATUM_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("A_DATUM_VON",cNotNullValue);
	}

	public String get_A_DATUM_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("A_DATUM_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("A_DATUM_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_A_DATUM_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("A_DATUM_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("A_DATUM_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_DATUM_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_DATUM_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_A_DATUM_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("A_DATUM_VON", calNewValueFormated);
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
		public String get_BEMERKUNG_FUER_KUNDE_cUF() throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_FUER_KUNDE");
	}

	public String get_BEMERKUNG_FUER_KUNDE_cF() throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_FUER_KUNDE");	
	}

	public String get_BEMERKUNG_FUER_KUNDE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("BEMERKUNG_FUER_KUNDE");
	}

	public String get_BEMERKUNG_FUER_KUNDE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("BEMERKUNG_FUER_KUNDE",cNotNullValue);
	}

	public String get_BEMERKUNG_FUER_KUNDE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("BEMERKUNG_FUER_KUNDE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_FUER_KUNDE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_FUER_KUNDE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("BEMERKUNG_FUER_KUNDE", calNewValueFormated);
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
		public String get_EK_VK_ZUORD_ZWANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EK_VK_ZUORD_ZWANG");
	}

	public String get_EK_VK_ZUORD_ZWANG_cF() throws myException
	{
		return this.get_FormatedValue("EK_VK_ZUORD_ZWANG");	
	}

	public String get_EK_VK_ZUORD_ZWANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EK_VK_ZUORD_ZWANG");
	}

	public String get_EK_VK_ZUORD_ZWANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EK_VK_ZUORD_ZWANG",cNotNullValue);
	}

	public String get_EK_VK_ZUORD_ZWANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EK_VK_ZUORD_ZWANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EK_VK_ZUORD_ZWANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EK_VK_ZUORD_ZWANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EK_VK_ZUORD_ZWANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EK_VK_ZUORD_ZWANG", calNewValueFormated);
	}
		public boolean is_EK_VK_ZUORD_ZWANG_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_EK_VK_ZUORD_ZWANG_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_EK_VK_ZUORD_ZWANG_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_EK_VK_ZUORD_ZWANG_cUF_NN("N").equals("N"))
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
		public String get_EU_BLATT_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_MENGE");
	}

	public String get_EU_BLATT_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("EU_BLATT_MENGE");	
	}

	public String get_EU_BLATT_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BLATT_MENGE");
	}

	public String get_EU_BLATT_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_MENGE",cNotNullValue);
	}

	public String get_EU_BLATT_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BLATT_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BLATT_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_MENGE", calNewValueFormated);
	}
		public Double get_EU_BLATT_MENGE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EU_BLATT_MENGE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EU_BLATT_MENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EU_BLATT_MENGE").getDoubleValue();
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
	public String get_EU_BLATT_MENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EU_BLATT_MENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_MENGE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EU_BLATT_MENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_MENGE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EU_BLATT_MENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_MENGE").getBigDecimalValue();
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
	
	
	public String get_EU_BLATT_VOLUMEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_VOLUMEN");
	}

	public String get_EU_BLATT_VOLUMEN_cF() throws myException
	{
		return this.get_FormatedValue("EU_BLATT_VOLUMEN");	
	}

	public String get_EU_BLATT_VOLUMEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EU_BLATT_VOLUMEN");
	}

	public String get_EU_BLATT_VOLUMEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EU_BLATT_VOLUMEN",cNotNullValue);
	}

	public String get_EU_BLATT_VOLUMEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EU_BLATT_VOLUMEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EU_BLATT_VOLUMEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EU_BLATT_VOLUMEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EU_BLATT_VOLUMEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EU_BLATT_VOLUMEN", calNewValueFormated);
	}
		public Double get_EU_BLATT_VOLUMEN_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("EU_BLATT_VOLUMEN").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_EU_BLATT_VOLUMEN_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("EU_BLATT_VOLUMEN").getDoubleValue();
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
	public String get_EU_BLATT_VOLUMEN_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_VOLUMEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_EU_BLATT_VOLUMEN_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EU_BLATT_VOLUMEN_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_EU_BLATT_VOLUMEN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_VOLUMEN").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_EU_BLATT_VOLUMEN_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("EU_BLATT_VOLUMEN").getBigDecimalValue();
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
		public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN");
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cF() throws myException
	{
		return this.get_FormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN");	
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GELANGENSBESTAETIGUNG_ERHALTEN");
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN",cNotNullValue);
	}

	public String get_GELANGENSBESTAETIGUNG_ERHALTEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GELANGENSBESTAETIGUNG_ERHALTEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_GELANGENSBESTAETIGUNG_ERHALTEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("GELANGENSBESTAETIGUNG_ERHALTEN", calNewValueFormated);
	}
		public boolean is_GELANGENSBESTAETIGUNG_ERHALTEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_GELANGENSBESTAETIGUNG_ERHALTEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_GELANGENSBESTAETIGUNG_ERHALTEN_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_ID_ADRESSE_FREMDWARE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_FREMDWARE");
	}

	public String get_ID_ADRESSE_FREMDWARE_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_FREMDWARE");	
	}

	public String get_ID_ADRESSE_FREMDWARE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_FREMDWARE");
	}

	public String get_ID_ADRESSE_FREMDWARE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_FREMDWARE",cNotNullValue);
	}

	public String get_ID_ADRESSE_FREMDWARE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_FREMDWARE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_FREMDWARE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_FREMDWARE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_FREMDWARE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_FREMDWARE", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_FREMDWARE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_FREMDWARE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_FREMDWARE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_FREMDWARE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_FREMDWARE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_FREMDWARE").getDoubleValue();
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
	public String get_ID_ADRESSE_FREMDWARE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_FREMDWARE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_FREMDWARE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_FREMDWARE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_FREMDWARE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_FREMDWARE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_FREMDWARE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_FREMDWARE").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_SPEDITION_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_SPEDITION");
	}

	public String get_ID_ADRESSE_SPEDITION_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_SPEDITION");	
	}

	public String get_ID_ADRESSE_SPEDITION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_SPEDITION");
	}

	public String get_ID_ADRESSE_SPEDITION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_SPEDITION",cNotNullValue);
	}

	public String get_ID_ADRESSE_SPEDITION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_SPEDITION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_SPEDITION_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_SPEDITION").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_SPEDITION_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_SPEDITION").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_SPEDITION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_SPEDITION").getDoubleValue();
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
	public String get_ID_ADRESSE_SPEDITION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_SPEDITION_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_SPEDITION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_SPEDITION_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_SPEDITION_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_SPEDITION").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_SPEDITION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_SPEDITION").getBigDecimalValue();
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
	
	
	public String get_ID_ADRESSE_START_LOGISTIK_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_START_LOGISTIK");
	}

	public String get_ID_ADRESSE_START_LOGISTIK_cF() throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_START_LOGISTIK");	
	}

	public String get_ID_ADRESSE_START_LOGISTIK_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_ADRESSE_START_LOGISTIK");
	}

	public String get_ID_ADRESSE_START_LOGISTIK_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_ADRESSE_START_LOGISTIK",cNotNullValue);
	}

	public String get_ID_ADRESSE_START_LOGISTIK_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_ADRESSE_START_LOGISTIK",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_START_LOGISTIK(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_START_LOGISTIK_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_ADRESSE_START_LOGISTIK", calNewValueFormated);
	}
		public Long get_ID_ADRESSE_START_LOGISTIK_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_ADRESSE_START_LOGISTIK").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_ADRESSE_START_LOGISTIK_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_START_LOGISTIK").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_ADRESSE_START_LOGISTIK_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_ADRESSE_START_LOGISTIK").getDoubleValue();
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
	public String get_ID_ADRESSE_START_LOGISTIK_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_START_LOGISTIK_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_ADRESSE_START_LOGISTIK_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_ADRESSE_START_LOGISTIK_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_ADRESSE_START_LOGISTIK_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_START_LOGISTIK").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_ADRESSE_START_LOGISTIK_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_ADRESSE_START_LOGISTIK").getBigDecimalValue();
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
	
	
	public String get_ID_BEWEGUNG_ATOM_TRIGSTART_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG_ATOM_TRIGSTART");
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGSTART_cF() throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG_ATOM_TRIGSTART");	
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGSTART_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BEWEGUNG_ATOM_TRIGSTART");
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGSTART_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG_ATOM_TRIGSTART",cNotNullValue);
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGSTART_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG_ATOM_TRIGSTART",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGSTART", calNewValueFormated);
	}
		public Long get_ID_BEWEGUNG_ATOM_TRIGSTART_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BEWEGUNG_ATOM_TRIGSTART").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BEWEGUNG_ATOM_TRIGSTART_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG_ATOM_TRIGSTART").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BEWEGUNG_ATOM_TRIGSTART_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG_ATOM_TRIGSTART").getDoubleValue();
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
	public String get_ID_BEWEGUNG_ATOM_TRIGSTART_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_ATOM_TRIGSTART_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BEWEGUNG_ATOM_TRIGSTART_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_ATOM_TRIGSTART_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BEWEGUNG_ATOM_TRIGSTART_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG_ATOM_TRIGSTART").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BEWEGUNG_ATOM_TRIGSTART_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG_ATOM_TRIGSTART").getBigDecimalValue();
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
	
	
	public String get_ID_BEWEGUNG_ATOM_TRIGZIEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG_ATOM_TRIGZIEL");
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGZIEL_cF() throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG_ATOM_TRIGZIEL");	
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGZIEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BEWEGUNG_ATOM_TRIGZIEL");
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGZIEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG_ATOM_TRIGZIEL",cNotNullValue);
	}

	public String get_ID_BEWEGUNG_ATOM_TRIGZIEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG_ATOM_TRIGZIEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_ATOM_TRIGZIEL", calNewValueFormated);
	}
		public Long get_ID_BEWEGUNG_ATOM_TRIGZIEL_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BEWEGUNG_ATOM_TRIGZIEL").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BEWEGUNG_ATOM_TRIGZIEL_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG_ATOM_TRIGZIEL").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BEWEGUNG_ATOM_TRIGZIEL_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG_ATOM_TRIGZIEL").getDoubleValue();
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
	public String get_ID_BEWEGUNG_ATOM_TRIGZIEL_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_ATOM_TRIGZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BEWEGUNG_ATOM_TRIGZIEL_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_ATOM_TRIGZIEL_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BEWEGUNG_ATOM_TRIGZIEL_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG_ATOM_TRIGZIEL").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BEWEGUNG_ATOM_TRIGZIEL_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG_ATOM_TRIGZIEL").getBigDecimalValue();
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
	
	
	public String get_ID_BEWEGUNG_VEKTOR_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG_VEKTOR");
	}

	public String get_ID_BEWEGUNG_VEKTOR_cF() throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG_VEKTOR");	
	}

	public String get_ID_BEWEGUNG_VEKTOR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_BEWEGUNG_VEKTOR");
	}

	public String get_ID_BEWEGUNG_VEKTOR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_BEWEGUNG_VEKTOR",cNotNullValue);
	}

	public String get_ID_BEWEGUNG_VEKTOR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_BEWEGUNG_VEKTOR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_BEWEGUNG_VEKTOR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_BEWEGUNG_VEKTOR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_BEWEGUNG_VEKTOR", calNewValueFormated);
	}
		public Long get_ID_BEWEGUNG_VEKTOR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_BEWEGUNG_VEKTOR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_BEWEGUNG_VEKTOR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG_VEKTOR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_BEWEGUNG_VEKTOR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_BEWEGUNG_VEKTOR").getDoubleValue();
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
	public String get_ID_BEWEGUNG_VEKTOR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_VEKTOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_BEWEGUNG_VEKTOR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_BEWEGUNG_VEKTOR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_BEWEGUNG_VEKTOR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG_VEKTOR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_BEWEGUNG_VEKTOR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_BEWEGUNG_VEKTOR").getBigDecimalValue();
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
	
	
	public String get_ID_UMA_KONTRAKT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_UMA_KONTRAKT");
	}

	public String get_ID_UMA_KONTRAKT_cF() throws myException
	{
		return this.get_FormatedValue("ID_UMA_KONTRAKT");	
	}

	public String get_ID_UMA_KONTRAKT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_UMA_KONTRAKT");
	}

	public String get_ID_UMA_KONTRAKT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_UMA_KONTRAKT",cNotNullValue);
	}

	public String get_ID_UMA_KONTRAKT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_UMA_KONTRAKT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_UMA_KONTRAKT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_UMA_KONTRAKT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_UMA_KONTRAKT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_UMA_KONTRAKT", calNewValueFormated);
	}
		public Long get_ID_UMA_KONTRAKT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_UMA_KONTRAKT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_UMA_KONTRAKT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_UMA_KONTRAKT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_UMA_KONTRAKT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_UMA_KONTRAKT").getDoubleValue();
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
	public String get_ID_UMA_KONTRAKT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_UMA_KONTRAKT_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_UMA_KONTRAKT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_UMA_KONTRAKT_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_UMA_KONTRAKT_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_UMA_KONTRAKT").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_UMA_KONTRAKT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_UMA_KONTRAKT").getBigDecimalValue();
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
	
	
	public String get_ID_VEKTOR_GRUPPE_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VEKTOR_GRUPPE");
	}

	public String get_ID_VEKTOR_GRUPPE_cF() throws myException
	{
		return this.get_FormatedValue("ID_VEKTOR_GRUPPE");	
	}

	public String get_ID_VEKTOR_GRUPPE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VEKTOR_GRUPPE");
	}

	public String get_ID_VEKTOR_GRUPPE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VEKTOR_GRUPPE",cNotNullValue);
	}

	public String get_ID_VEKTOR_GRUPPE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VEKTOR_GRUPPE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VEKTOR_GRUPPE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VEKTOR_GRUPPE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VEKTOR_GRUPPE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VEKTOR_GRUPPE", calNewValueFormated);
	}
		public Long get_ID_VEKTOR_GRUPPE_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VEKTOR_GRUPPE").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VEKTOR_GRUPPE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VEKTOR_GRUPPE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VEKTOR_GRUPPE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VEKTOR_GRUPPE").getDoubleValue();
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
	public String get_ID_VEKTOR_GRUPPE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VEKTOR_GRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VEKTOR_GRUPPE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VEKTOR_GRUPPE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VEKTOR_GRUPPE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VEKTOR_GRUPPE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VEKTOR_GRUPPE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VEKTOR_GRUPPE").getBigDecimalValue();
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
	
	
	public String get_ID_VERPACKUNGSART_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_VERPACKUNGSART");
	}

	public String get_ID_VERPACKUNGSART_cF() throws myException
	{
		return this.get_FormatedValue("ID_VERPACKUNGSART");	
	}

	public String get_ID_VERPACKUNGSART_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_VERPACKUNGSART");
	}

	public String get_ID_VERPACKUNGSART_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_VERPACKUNGSART",cNotNullValue);
	}

	public String get_ID_VERPACKUNGSART_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_VERPACKUNGSART",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_VERPACKUNGSART(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ID_VERPACKUNGSART", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_VERPACKUNGSART_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ID_VERPACKUNGSART", calNewValueFormated);
	}
		public Long get_ID_VERPACKUNGSART_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_VERPACKUNGSART").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_VERPACKUNGSART_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_VERPACKUNGSART").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_VERPACKUNGSART_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_VERPACKUNGSART").getDoubleValue();
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
	public String get_ID_VERPACKUNGSART_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VERPACKUNGSART_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ID_VERPACKUNGSART_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_VERPACKUNGSART_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ID_VERPACKUNGSART_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VERPACKUNGSART").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ID_VERPACKUNGSART_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ID_VERPACKUNGSART").getBigDecimalValue();
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
	
	
	public String get_KOSTEN_PRODUKT_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_PRODUKT_WA");
	}

	public String get_KOSTEN_PRODUKT_WA_cF() throws myException
	{
		return this.get_FormatedValue("KOSTEN_PRODUKT_WA");	
	}

	public String get_KOSTEN_PRODUKT_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTEN_PRODUKT_WA");
	}

	public String get_KOSTEN_PRODUKT_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_PRODUKT_WA",cNotNullValue);
	}

	public String get_KOSTEN_PRODUKT_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTEN_PRODUKT_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_PRODUKT_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTEN_PRODUKT_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WA", calNewValueFormated);
	}
		public Double get_KOSTEN_PRODUKT_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KOSTEN_PRODUKT_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KOSTEN_PRODUKT_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KOSTEN_PRODUKT_WA").getDoubleValue();
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
	public String get_KOSTEN_PRODUKT_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_PRODUKT_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KOSTEN_PRODUKT_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_PRODUKT_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KOSTEN_PRODUKT_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_PRODUKT_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KOSTEN_PRODUKT_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_PRODUKT_WA").getBigDecimalValue();
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
	
	
	public String get_KOSTEN_PRODUKT_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_PRODUKT_WE");
	}

	public String get_KOSTEN_PRODUKT_WE_cF() throws myException
	{
		return this.get_FormatedValue("KOSTEN_PRODUKT_WE");	
	}

	public String get_KOSTEN_PRODUKT_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTEN_PRODUKT_WE");
	}

	public String get_KOSTEN_PRODUKT_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_PRODUKT_WE",cNotNullValue);
	}

	public String get_KOSTEN_PRODUKT_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTEN_PRODUKT_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_PRODUKT_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTEN_PRODUKT_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_PRODUKT_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_PRODUKT_WE", calNewValueFormated);
	}
		public Double get_KOSTEN_PRODUKT_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KOSTEN_PRODUKT_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KOSTEN_PRODUKT_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KOSTEN_PRODUKT_WE").getDoubleValue();
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
	public String get_KOSTEN_PRODUKT_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_PRODUKT_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KOSTEN_PRODUKT_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_PRODUKT_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KOSTEN_PRODUKT_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_PRODUKT_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KOSTEN_PRODUKT_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_PRODUKT_WE").getBigDecimalValue();
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
	
	
	public String get_KOSTEN_TRANSPORT_WA_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_TRANSPORT_WA");
	}

	public String get_KOSTEN_TRANSPORT_WA_cF() throws myException
	{
		return this.get_FormatedValue("KOSTEN_TRANSPORT_WA");	
	}

	public String get_KOSTEN_TRANSPORT_WA_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTEN_TRANSPORT_WA");
	}

	public String get_KOSTEN_TRANSPORT_WA_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_TRANSPORT_WA",cNotNullValue);
	}

	public String get_KOSTEN_TRANSPORT_WA_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTEN_TRANSPORT_WA",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_TRANSPORT_WA(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTEN_TRANSPORT_WA", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WA_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WA", calNewValueFormated);
	}
		public Double get_KOSTEN_TRANSPORT_WA_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KOSTEN_TRANSPORT_WA").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KOSTEN_TRANSPORT_WA_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KOSTEN_TRANSPORT_WA").getDoubleValue();
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
	public String get_KOSTEN_TRANSPORT_WA_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_TRANSPORT_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KOSTEN_TRANSPORT_WA_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_TRANSPORT_WA_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KOSTEN_TRANSPORT_WA_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_TRANSPORT_WA").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KOSTEN_TRANSPORT_WA_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_TRANSPORT_WA").getBigDecimalValue();
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
	
	
	public String get_KOSTEN_TRANSPORT_WE_cUF() throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_TRANSPORT_WE");
	}

	public String get_KOSTEN_TRANSPORT_WE_cF() throws myException
	{
		return this.get_FormatedValue("KOSTEN_TRANSPORT_WE");	
	}

	public String get_KOSTEN_TRANSPORT_WE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("KOSTEN_TRANSPORT_WE");
	}

	public String get_KOSTEN_TRANSPORT_WE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("KOSTEN_TRANSPORT_WE",cNotNullValue);
	}

	public String get_KOSTEN_TRANSPORT_WE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("KOSTEN_TRANSPORT_WE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_KOSTEN_TRANSPORT_WE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("KOSTEN_TRANSPORT_WE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_KOSTEN_TRANSPORT_WE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("KOSTEN_TRANSPORT_WE", calNewValueFormated);
	}
		public Double get_KOSTEN_TRANSPORT_WE_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("KOSTEN_TRANSPORT_WE").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_KOSTEN_TRANSPORT_WE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("KOSTEN_TRANSPORT_WE").getDoubleValue();
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
	public String get_KOSTEN_TRANSPORT_WE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_TRANSPORT_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_KOSTEN_TRANSPORT_WE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_KOSTEN_TRANSPORT_WE_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_KOSTEN_TRANSPORT_WE_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_TRANSPORT_WE").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_KOSTEN_TRANSPORT_WE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("KOSTEN_TRANSPORT_WE").getBigDecimalValue();
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
	
	
	public String get_LAENDERCODE_TRANSIT1_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT1");
	}

	public String get_LAENDERCODE_TRANSIT1_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT1");	
	}

	public String get_LAENDERCODE_TRANSIT1_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_TRANSIT1");
	}

	public String get_LAENDERCODE_TRANSIT1_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT1",cNotNullValue);
	}

	public String get_LAENDERCODE_TRANSIT1_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT1",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT1(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT1", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT1_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT1", calNewValueFormated);
	}
		public String get_LAENDERCODE_TRANSIT2_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT2");
	}

	public String get_LAENDERCODE_TRANSIT2_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT2");	
	}

	public String get_LAENDERCODE_TRANSIT2_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_TRANSIT2");
	}

	public String get_LAENDERCODE_TRANSIT2_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT2",cNotNullValue);
	}

	public String get_LAENDERCODE_TRANSIT2_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT2",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT2(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT2", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT2_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT2", calNewValueFormated);
	}
		public String get_LAENDERCODE_TRANSIT3_cUF() throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT3");
	}

	public String get_LAENDERCODE_TRANSIT3_cF() throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT3");	
	}

	public String get_LAENDERCODE_TRANSIT3_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAENDERCODE_TRANSIT3");
	}

	public String get_LAENDERCODE_TRANSIT3_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LAENDERCODE_TRANSIT3",cNotNullValue);
	}

	public String get_LAENDERCODE_TRANSIT3_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LAENDERCODE_TRANSIT3",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_LAENDERCODE_TRANSIT3(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("LAENDERCODE_TRANSIT3", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_LAENDERCODE_TRANSIT3_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("LAENDERCODE_TRANSIT3", calNewValueFormated);
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
		public String get_L_DATUM_BIS_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_DATUM_BIS");
	}

	public String get_L_DATUM_BIS_cF() throws myException
	{
		return this.get_FormatedValue("L_DATUM_BIS");	
	}

	public String get_L_DATUM_BIS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_DATUM_BIS");
	}

	public String get_L_DATUM_BIS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_DATUM_BIS",cNotNullValue);
	}

	public String get_L_DATUM_BIS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_DATUM_BIS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_DATUM_BIS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_DATUM_BIS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_DATUM_BIS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_DATUM_BIS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_DATUM_BIS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_DATUM_BIS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_BIS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_DATUM_BIS", calNewValueFormated);
	}
		public String get_L_DATUM_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("L_DATUM_VON");
	}

	public String get_L_DATUM_VON_cF() throws myException
	{
		return this.get_FormatedValue("L_DATUM_VON");	
	}

	public String get_L_DATUM_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("L_DATUM_VON");
	}

	public String get_L_DATUM_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("L_DATUM_VON",cNotNullValue);
	}

	public String get_L_DATUM_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("L_DATUM_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("L_DATUM_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_L_DATUM_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("L_DATUM_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("L_DATUM_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_DATUM_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_DATUM_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_L_DATUM_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("L_DATUM_VON", calNewValueFormated);
	}
		public String get_POSNR_cUF() throws myException
	{
		return this.get_UnFormatedValue("POSNR");
	}

	public String get_POSNR_cF() throws myException
	{
		return this.get_FormatedValue("POSNR");	
	}

	public String get_POSNR_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("POSNR");
	}

	public String get_POSNR_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("POSNR",cNotNullValue);
	}

	public String get_POSNR_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("POSNR",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_POSNR(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("POSNR", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_POSNR(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("POSNR", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("POSNR", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSNR", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSNR", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_POSNR_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("POSNR", calNewValueFormated);
	}
		public Long get_POSNR_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("POSNR").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_POSNR_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("POSNR").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_POSNR_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("POSNR").getDoubleValue();
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
	public String get_POSNR_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSNR_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_POSNR_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_POSNR_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_POSNR_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("POSNR").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_POSNR_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("POSNR").getBigDecimalValue();
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
	
	
	public String get_PRINT_EU_AMTSBLATT_cUF() throws myException
	{
		return this.get_UnFormatedValue("PRINT_EU_AMTSBLATT");
	}

	public String get_PRINT_EU_AMTSBLATT_cF() throws myException
	{
		return this.get_FormatedValue("PRINT_EU_AMTSBLATT");	
	}

	public String get_PRINT_EU_AMTSBLATT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("PRINT_EU_AMTSBLATT");
	}

	public String get_PRINT_EU_AMTSBLATT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("PRINT_EU_AMTSBLATT",cNotNullValue);
	}

	public String get_PRINT_EU_AMTSBLATT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("PRINT_EU_AMTSBLATT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_PRINT_EU_AMTSBLATT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("PRINT_EU_AMTSBLATT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_PRINT_EU_AMTSBLATT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("PRINT_EU_AMTSBLATT", calNewValueFormated);
	}
		public boolean is_PRINT_EU_AMTSBLATT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_EU_AMTSBLATT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_PRINT_EU_AMTSBLATT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_PRINT_EU_AMTSBLATT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STATISTIK_TIMESTAMP_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATISTIK_TIMESTAMP");
	}

	public String get_STATISTIK_TIMESTAMP_cF() throws myException
	{
		return this.get_FormatedValue("STATISTIK_TIMESTAMP");	
	}

	public String get_STATISTIK_TIMESTAMP_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATISTIK_TIMESTAMP");
	}

	public String get_STATISTIK_TIMESTAMP_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATISTIK_TIMESTAMP",cNotNullValue);
	}

	public String get_STATISTIK_TIMESTAMP_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATISTIK_TIMESTAMP",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATISTIK_TIMESTAMP", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATISTIK_TIMESTAMP(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATISTIK_TIMESTAMP", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATISTIK_TIMESTAMP_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATISTIK_TIMESTAMP", calNewValueFormated);
	}
		public String get_STATUS_cUF() throws myException
	{
		return this.get_UnFormatedValue("STATUS");
	}

	public String get_STATUS_cF() throws myException
	{
		return this.get_FormatedValue("STATUS");	
	}

	public String get_STATUS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STATUS");
	}

	public String get_STATUS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STATUS",cNotNullValue);
	}

	public String get_STATUS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STATUS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STATUS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STATUS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STATUS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STATUS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STATUS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STATUS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STATUS", calNewValueFormated);
	}
		public String get_STORNIERT_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNIERT");
	}

	public String get_STORNIERT_cF() throws myException
	{
		return this.get_FormatedValue("STORNIERT");	
	}

	public String get_STORNIERT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNIERT");
	}

	public String get_STORNIERT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNIERT",cNotNullValue);
	}

	public String get_STORNIERT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNIERT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNIERT(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNIERT", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNIERT", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT", calNewValueFormated);
	}
		public boolean is_STORNIERT_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNIERT_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_STORNIERT_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_STORNIERT_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_STORNIERT_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_AM");
	}

	public String get_STORNIERT_AM_cF() throws myException
	{
		return this.get_FormatedValue("STORNIERT_AM");	
	}

	public String get_STORNIERT_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNIERT_AM");
	}

	public String get_STORNIERT_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_AM",cNotNullValue);
	}

	public String get_STORNIERT_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNIERT_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_AM", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_AM", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_AM", calNewValueFormated);
	}
		public String get_STORNIERT_GRUND_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_GRUND");
	}

	public String get_STORNIERT_GRUND_cF() throws myException
	{
		return this.get_FormatedValue("STORNIERT_GRUND");	
	}

	public String get_STORNIERT_GRUND_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNIERT_GRUND");
	}

	public String get_STORNIERT_GRUND_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_GRUND",cNotNullValue);
	}

	public String get_STORNIERT_GRUND_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNIERT_GRUND",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNIERT_GRUND", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_GRUND(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNIERT_GRUND", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_GRUND_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_GRUND", calNewValueFormated);
	}
		public String get_STORNIERT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_VON");
	}

	public String get_STORNIERT_VON_cF() throws myException
	{
		return this.get_FormatedValue("STORNIERT_VON");	
	}

	public String get_STORNIERT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("STORNIERT_VON");
	}

	public String get_STORNIERT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("STORNIERT_VON",cNotNullValue);
	}

	public String get_STORNIERT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("STORNIERT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_VON", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_VON", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("STORNIERT_VON", calNewValueFormated);
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
	
	
	public String get_TRANSPORTKENNZEICHEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTKENNZEICHEN");
	}

	public String get_TRANSPORTKENNZEICHEN_cF() throws myException
	{
		return this.get_FormatedValue("TRANSPORTKENNZEICHEN");	
	}

	public String get_TRANSPORTKENNZEICHEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSPORTKENNZEICHEN");
	}

	public String get_TRANSPORTKENNZEICHEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTKENNZEICHEN",cNotNullValue);
	}

	public String get_TRANSPORTKENNZEICHEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSPORTKENNZEICHEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTKENNZEICHEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSPORTKENNZEICHEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTKENNZEICHEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTKENNZEICHEN", calNewValueFormated);
	}
		public String get_TRANSPORTMITTEL_cUF() throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTMITTEL");
	}

	public String get_TRANSPORTMITTEL_cF() throws myException
	{
		return this.get_FormatedValue("TRANSPORTMITTEL");	
	}

	public String get_TRANSPORTMITTEL_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("TRANSPORTMITTEL");
	}

	public String get_TRANSPORTMITTEL_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("TRANSPORTMITTEL",cNotNullValue);
	}

	public String get_TRANSPORTMITTEL_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("TRANSPORTMITTEL",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_TRANSPORTMITTEL(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("TRANSPORTMITTEL", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_TRANSPORTMITTEL_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("TRANSPORTMITTEL", calNewValueFormated);
	}
		public String get_VARIANTE_cUF() throws myException
	{
		return this.get_UnFormatedValue("VARIANTE");
	}

	public String get_VARIANTE_cF() throws myException
	{
		return this.get_FormatedValue("VARIANTE");	
	}

	public String get_VARIANTE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("VARIANTE");
	}

	public String get_VARIANTE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("VARIANTE",cNotNullValue);
	}

	public String get_VARIANTE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("VARIANTE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_VARIANTE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("VARIANTE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_VARIANTE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("VARIANTE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VARIANTE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("VARIANTE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VARIANTE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VARIANTE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VARIANTE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VARIANTE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_VARIANTE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("VARIANTE", calNewValueFormated);
	}
		public String get_WARENKLASSE_cUF() throws myException
	{
		return this.get_UnFormatedValue("WARENKLASSE");
	}

	public String get_WARENKLASSE_cF() throws myException
	{
		return this.get_FormatedValue("WARENKLASSE");	
	}

	public String get_WARENKLASSE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("WARENKLASSE");
	}

	public String get_WARENKLASSE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("WARENKLASSE",cNotNullValue);
	}

	public String get_WARENKLASSE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("WARENKLASSE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("WARENKLASSE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_WARENKLASSE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("WARENKLASSE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("WARENKLASSE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENKLASSE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENKLASSE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_WARENKLASSE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("WARENKLASSE", calNewValueFormated);
	}
		public String get_ZAHL_GUTPOS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHL_GUTPOS");
	}

	public String get_ZAHL_GUTPOS_cF() throws myException
	{
		return this.get_FormatedValue("ZAHL_GUTPOS");	
	}

	public String get_ZAHL_GUTPOS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHL_GUTPOS");
	}

	public String get_ZAHL_GUTPOS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHL_GUTPOS",cNotNullValue);
	}

	public String get_ZAHL_GUTPOS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHL_GUTPOS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHL_GUTPOS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHL_GUTPOS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHL_GUTPOS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_GUTPOS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_GUTPOS", calNewValueFormated);
	}
		public Long get_ZAHL_GUTPOS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ZAHL_GUTPOS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ZAHL_GUTPOS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAHL_GUTPOS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAHL_GUTPOS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAHL_GUTPOS").getDoubleValue();
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
	public String get_ZAHL_GUTPOS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHL_GUTPOS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ZAHL_GUTPOS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHL_GUTPOS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ZAHL_GUTPOS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHL_GUTPOS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAHL_GUTPOS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHL_GUTPOS").getBigDecimalValue();
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
	
	
	public String get_ZAHL_RECHPOS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ZAHL_RECHPOS");
	}

	public String get_ZAHL_RECHPOS_cF() throws myException
	{
		return this.get_FormatedValue("ZAHL_RECHPOS");	
	}

	public String get_ZAHL_RECHPOS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ZAHL_RECHPOS");
	}

	public String get_ZAHL_RECHPOS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ZAHL_RECHPOS",cNotNullValue);
	}

	public String get_ZAHL_RECHPOS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ZAHL_RECHPOS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ZAHL_RECHPOS", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ZAHL_RECHPOS(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("ZAHL_RECHPOS", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ZAHL_RECHPOS_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("ZAHL_RECHPOS", calNewValueFormated);
	}
		public Long get_ZAHL_RECHPOS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ZAHL_RECHPOS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ZAHL_RECHPOS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ZAHL_RECHPOS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ZAHL_RECHPOS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ZAHL_RECHPOS").getDoubleValue();
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
	public String get_ZAHL_RECHPOS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHL_RECHPOS_dValue(dValueWhenNULL,iNachkommaRunden);
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
	public String get_ZAHL_RECHPOS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ZAHL_RECHPOS_dValue(dValueWhenNULL,iNachkommaRunden);
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
		public BigDecimal get_ZAHL_RECHPOS_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHL_RECHPOS").getBigDecimalValue();
		if (bdRueck==null) 
		{ 
			return bdValueWhenNULL; 
		} 
		else 
		{ 
			return bdRueck;
		} 
	}
	

	
	public BigDecimal get_ZAHL_RECHPOS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException
	{
		BigDecimal bdRueck = this.get("ZAHL_RECHPOS").getBigDecimalValue();
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
	put("ABGESCHLOSSEN",MyRECORD.DATATYPES.YESNO);
	put("ABGESCHLOSSEN_AM",MyRECORD.DATATYPES.DATE);
	put("ABGESCHLOSSEN_VON",MyRECORD.DATATYPES.TEXT);
	put("ANHAENGERKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("AVV_AUSSTELLUNG_DATUM",MyRECORD.DATATYPES.DATE);
	put("A_DATUM_BIS",MyRECORD.DATATYPES.DATE);
	put("A_DATUM_VON",MyRECORD.DATATYPES.DATE);
	put("BEMERKUNG",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_FUER_KUNDE",MyRECORD.DATATYPES.TEXT);
	put("BEMERKUNG_SACHBEARBEITER",MyRECORD.DATATYPES.TEXT);
	put("DELETED",MyRECORD.DATATYPES.YESNO);
	put("DEL_DATE",MyRECORD.DATATYPES.DATE);
	put("DEL_GRUND",MyRECORD.DATATYPES.TEXT);
	put("DEL_KUERZEL",MyRECORD.DATATYPES.TEXT);
	put("EK_VK_ZUORD_ZWANG",MyRECORD.DATATYPES.YESNO);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("EU_BLATT_MENGE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("EU_BLATT_VOLUMEN",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("GELANGENSBESTAETIGUNG_ERHALTEN",MyRECORD.DATATYPES.YESNO);
	put("ID_ADRESSE_FREMDWARE",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_SPEDITION",MyRECORD.DATATYPES.NUMBER);
	put("ID_ADRESSE_START_LOGISTIK",MyRECORD.DATATYPES.NUMBER);
	put("ID_BEWEGUNG",MyRECORD.DATATYPES.NUMBER);
	put("ID_BEWEGUNG_ATOM_TRIGSTART",MyRECORD.DATATYPES.NUMBER);
	put("ID_BEWEGUNG_ATOM_TRIGZIEL",MyRECORD.DATATYPES.NUMBER);
	put("ID_BEWEGUNG_VEKTOR",MyRECORD.DATATYPES.NUMBER);
	put("ID_EAK_CODE",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("ID_UMA_KONTRAKT",MyRECORD.DATATYPES.NUMBER);
	put("ID_VEKTOR_GRUPPE",MyRECORD.DATATYPES.NUMBER);
	put("ID_VERPACKUNGSART",MyRECORD.DATATYPES.NUMBER);
	put("KOSTEN_PRODUKT_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("KOSTEN_PRODUKT_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("KOSTEN_TRANSPORT_WA",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("KOSTEN_TRANSPORT_WE",MyRECORD.DATATYPES.DECIMALNUMBER);
	put("LAENDERCODE_TRANSIT1",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_TRANSIT2",MyRECORD.DATATYPES.TEXT);
	put("LAENDERCODE_TRANSIT3",MyRECORD.DATATYPES.TEXT);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("L_DATUM_BIS",MyRECORD.DATATYPES.DATE);
	put("L_DATUM_VON",MyRECORD.DATATYPES.DATE);
	put("POSNR",MyRECORD.DATATYPES.NUMBER);
	put("PRINT_EU_AMTSBLATT",MyRECORD.DATATYPES.YESNO);
	put("STATISTIK_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("STATUS",MyRECORD.DATATYPES.TEXT);
	put("STORNIERT",MyRECORD.DATATYPES.YESNO);
	put("STORNIERT_AM",MyRECORD.DATATYPES.DATE);
	put("STORNIERT_GRUND",MyRECORD.DATATYPES.TEXT);
	put("STORNIERT_VON",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	put("TRANSPORTKENNZEICHEN",MyRECORD.DATATYPES.TEXT);
	put("TRANSPORTMITTEL",MyRECORD.DATATYPES.TEXT);
	put("VARIANTE",MyRECORD.DATATYPES.TEXT);
	put("WARENKLASSE",MyRECORD.DATATYPES.TEXT);
	put("ZAHL_GUTPOS",MyRECORD.DATATYPES.NUMBER);
	put("ZAHL_RECHPOS",MyRECORD.DATATYPES.NUMBER);
	put("ZEITSTEMPEL",MyRECORD.DATATYPES.TEXT);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_BEWEGUNG_VEKTOR.HM_FIELDS;	
	}

}
