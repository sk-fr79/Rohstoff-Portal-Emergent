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

public class RECORD_EINHEIT extends MyRECORD implements MyRECORD_IF_RECORDS
{

    public static String TABLENAME = "JT_EINHEIT";
    public static String IDFIELD   = "ID_EINHEIT";
    

	//erzeugen eines RECORDNEW_JT_EINHEIT - felds
	private RECORDNEW_EINHEIT   recNEW = null;

    private _TAB  tab = _TAB.einheit;  



	public RECORD_EINHEIT() throws myException
	{
		super();
		this.set_TABLE_NAME("JT_EINHEIT");
	}


	public RECORD_EINHEIT(MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EINHEIT");
	}



	public RECORD_EINHEIT(RECORD_EINHEIT recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EINHEIT");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EINHEIT.TABLENAME);
	}


	//2014-04-03
	public RECORD_EINHEIT(MyRECORD recordOrig)
	{
		super(recordOrig);
		this.set_TABLE_NAME("JT_EINHEIT");
		this.set_Tablename_To_FieldMetaDefs(RECORD_EINHEIT.TABLENAME);
	}




	public RECORD_EINHEIT(long lID_Unformated) throws myException
	{
		super("JT_EINHEIT","ID_EINHEIT",""+lID_Unformated);
		this.set_TABLE_NAME("JT_EINHEIT");

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EINHEIT.TABLENAME);
	}

	public RECORD_EINHEIT(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException 
	{
		super();
		this.set_TABLE_NAME("JT_EINHEIT");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EINHEIT", "ID_EINHEIT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EINHEIT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EINHEIT.TABLENAME);
	}
	
	

	public RECORD_EINHEIT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JT_EINHEIT","ID_EINHEIT",""+lID_Unformated, Conn);

		this.set_TABLE_NAME("JT_EINHEIT");


		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EINHEIT.TABLENAME);

	}


	public RECORD_EINHEIT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException 
	{
		super(Conn);
		this.set_TABLE_NAME("JT_EINHEIT");
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL))
		{
			this.PrepareAndBuild("*", bibE2.cTO()+".JT_EINHEIT", "ID_EINHEIT="+c_ID_or_WHEREBLOCK_OR_SQL);
		}
		else
		{
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT "))
			{
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			}
			else
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EINHEIT", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
		
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORD_EINHEIT.TABLENAME);
		
		
	}

	
	
	@Override
	public MyRECORD_NEW  get_RECORD_NEW() throws myException {
	   return this.get_RECORDNEW_EINHEIT();
	}

	
	
    @Override
	public String get_TABLENAME()
	{
		return RECORD_EINHEIT.TABLENAME;
	}
	
    @Override
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_EINHEIT";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_EINHEIT_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_EINHEIT_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields?this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS():this.get_StatementBuilderFilledWithActualValues());
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EINHEIT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EINHEIT", bibE2.cTO(), "ID_EINHEIT="+this.get_ID_EINHEIT_cUF(), vFieldsNotInUpdate);
	}
	

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder 
	 */
	public String get_SQL_UPDATE_STD() throws myException
	{
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		
		if (oSQLStatementBuilder.size()==0)    // sonst keine aenderungen
		{
			throw new myException(this,"Error: No field in RECORD_EINHEIT was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JT_EINHEIT", bibE2.cTO(), "ID_EINHEIT="+this.get_ID_EINHEIT_cUF(), null);
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
        return this.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_EINHEIT WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF()),true);
	}
	
	
	public String get_DELETE_STATEMENT()  throws myException
	{
		return "DELETE FROM "+bibE2.cTO()+".JT_EINHEIT WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF();
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
			if (S.isFull(this.get_ID_EINHEIT_cUF()))
			{
				this.PrepareAndBuild("*", bibE2.cTO()+".JT_EINHEIT", "ID_EINHEIT="+this.get_ID_EINHEIT_cUF());
			}
		}
		else
		{
			super.REBUILD();
		}
	}

	
	
	
	// 2013-09-20: gibt das recnew-objekt zurueck (falls leer, wird es erst gebaut) 
	public RECORDNEW_EINHEIT get_RECORDNEW_EINHEIT() throws myException {

	  if (this.size() ==0 && this.recNEW == null) {
	     this.recNEW = new RECORDNEW_EINHEIT();
	  } else if (this.size()>0) {
		  this.recNEW = new RECORDNEW_EINHEIT(this.get_hm_FieldMetaDefs());
	  }

	
	  return this.recNEW;
	}
	
	
	//2014-04-02: factory-klassen
	public static RECORD_EINHEIT create_Instance() throws myException {
		return new RECORD_EINHEIT();
	}
	
	public static RECORD_EINHEIT create_Instance(MyConnection Conn)   throws myException {
		return new RECORD_EINHEIT(Conn);
    }

	public static RECORD_EINHEIT create_Instance(RECORD_EINHEIT recordOrig) {
		return new RECORD_EINHEIT(recordOrig);
    }

	public static RECORD_EINHEIT create_Instance(long lID_Unformated) throws myException {
		return new RECORD_EINHEIT(lID_Unformated);
    }

	public static RECORD_EINHEIT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL)   throws myException  {
		return new RECORD_EINHEIT(c_ID_or_WHEREBLOCK_OR_SQL);
    }

	public static RECORD_EINHEIT create_Instance(long lID_Unformated, MyConnection Conn) throws myException {
		return new RECORD_EINHEIT(lID_Unformated, Conn);
	}

	public static RECORD_EINHEIT create_Instance(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)   throws myException {
		return new RECORD_EINHEIT(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}
	
	public static RECORD_EINHEIT create_Instance(MyRECORD recordOrig) throws myException {
		return new RECORD_EINHEIT(recordOrig);	    
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
    public RECORD_EINHEIT build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         //2016-05-10: falls der RECORD aus einem RECORD_VEKTOR kommt, hat er keinen sql-build-string und muss ihn hier zusammenbauen      
         // **alt**  throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
         this.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+".JT_EINHEIT WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF());
      }
      //return new RECORD_EINHEIT(this.get_cSQL_4_Build());
      RECORD_EINHEIT rec = new RECORD_EINHEIT();
      rec.set_DBToolBox_FAB(this.get_DBToolBox_FAB());
      rec.BuildRecord(this.get_cSQL_4_Build());
      return rec;
     }

	
	
     /**
     * 
     * martin: 2015-04-24: erzeugt ein gefuelltes RECORDNEW_EINHEIT
     * @param msg_sammler
     * @param changeIdWithSeq
     * @param bRemoveAutomaticField
     * @return RECORDNEW_EINHEIT-object filled with values of actual record (to generate copy)
     * @throws myException
     */
     public RECORDNEW_EINHEIT get_Record_NEW_FilledWithActualValues(MyE2_MessageVector msg_sammler, boolean changeIdWithSeq, boolean bRemoveAutomaticField) throws myException {
        if (S.isEmpty(this.get_TABLENAME())) {
            throw new myException(this, "get_RECORDNEW_EINHEIT_FilledWithActualValues(): no Tablename is present !" );
        }
		
        RECORDNEW_EINHEIT  recNew = new RECORDNEW_EINHEIT();
		
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
    public RECORD_EINHEIT set_recordNew(RECORDNEW_EINHEIT recnew) throws myException {
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
	
	



		private RECLIST_ARTIKEL DOWN_RECLIST_ARTIKEL_id_einheit = null ;




		private RECLIST_ARTIKEL DOWN_RECLIST_ARTIKEL_id_einheit_preis = null ;




		private RECLIST_ARTIKEL_DATENBLATT DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_abmessung = null ;




		private RECLIST_ARTIKEL_DATENBLATT DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_staerke = null ;




		private RECLIST_EINHEITEN_KOMBINATIONEN DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit = null ;




		private RECLIST_EINHEITEN_KOMBINATIONEN DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis = null ;




		private RECLIST_EINHEIT_FAKTOR DOWN_RECLIST_EINHEIT_FAKTOR_id_basiseinheit = null ;




		private RECLIST_EINHEIT_FAKTOR DOWN_RECLIST_EINHEIT_FAKTOR_id_einheit = null ;






	/**
	 * References the Field ID_EINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_einheit() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_einheit==null)
		{
			this.DOWN_RECLIST_ARTIKEL_id_einheit = new RECLIST_ARTIKEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_ARTIKEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_einheit;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_einheit(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_einheit==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_id_einheit = new RECLIST_ARTIKEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_einheit;
	}


	





	/**
	 * References the Field ID_EINHEIT_PREIS 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_einheit_preis() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_einheit_preis==null)
		{
			this.DOWN_RECLIST_ARTIKEL_id_einheit_preis = new RECLIST_ARTIKEL (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EINHEIT_PREIS="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_ARTIKEL",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_einheit_preis;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINHEIT_PREIS 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL get_DOWN_RECORD_LIST_ARTIKEL_id_einheit_preis(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_id_einheit_preis==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_EINHEIT_PREIS="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_id_einheit_preis = new RECLIST_ARTIKEL (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_id_einheit_preis;
	}


	





	/**
	 * References the Field ID_EINHEIT_ABMESSUNG 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_einheit_abmessung() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_abmessung==null)
		{
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_abmessung = new RECLIST_ARTIKEL_DATENBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_EINHEIT_ABMESSUNG="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_ARTIKEL_DATENBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_abmessung;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINHEIT_ABMESSUNG 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_einheit_abmessung(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_abmessung==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_EINHEIT_ABMESSUNG="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_abmessung = new RECLIST_ARTIKEL_DATENBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_abmessung;
	}


	





	/**
	 * References the Field ID_EINHEIT_STAERKE 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_einheit_staerke() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_staerke==null)
		{
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_staerke = new RECLIST_ARTIKEL_DATENBLATT (
			       "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_EINHEIT_STAERKE="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_ARTIKEL_DATENBLATT",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_staerke;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINHEIT_STAERKE 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_ARTIKEL_DATENBLATT get_DOWN_RECORD_LIST_ARTIKEL_DATENBLATT_id_einheit_staerke(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_staerke==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL_DATENBLATT WHERE ID_EINHEIT_STAERKE="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_staerke = new RECLIST_ARTIKEL_DATENBLATT (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_ARTIKEL_DATENBLATT_id_einheit_staerke;
	}


	





	/**
	 * References the Field ID_EINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEITEN_KOMBINATIONEN get_DOWN_RECORD_LIST_EINHEITEN_KOMBINATIONEN_id_einheit() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit==null)
		{
			this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit = new RECLIST_EINHEITEN_KOMBINATIONEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EINHEITEN_KOMBINATIONEN WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_EINHEITEN_KOMBINATIONEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEITEN_KOMBINATIONEN get_DOWN_RECORD_LIST_EINHEITEN_KOMBINATIONEN_id_einheit(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EINHEITEN_KOMBINATIONEN WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit = new RECLIST_EINHEITEN_KOMBINATIONEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit;
	}


	





	/**
	 * References the Field ID_EINHEIT_PREIS 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEITEN_KOMBINATIONEN get_DOWN_RECORD_LIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis==null)
		{
			this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis = new RECLIST_EINHEITEN_KOMBINATIONEN (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EINHEITEN_KOMBINATIONEN WHERE ID_EINHEIT_PREIS="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_EINHEITEN_KOMBINATIONEN",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINHEIT_PREIS 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEITEN_KOMBINATIONEN get_DOWN_RECORD_LIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EINHEITEN_KOMBINATIONEN WHERE ID_EINHEIT_PREIS="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis = new RECLIST_EINHEITEN_KOMBINATIONEN (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEITEN_KOMBINATIONEN_id_einheit_preis;
	}


	





	/**
	 * References the Field ID_BASISEINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEIT_FAKTOR get_DOWN_RECORD_LIST_EINHEIT_FAKTOR_id_basiseinheit() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEIT_FAKTOR_id_basiseinheit==null)
		{
			this.DOWN_RECLIST_EINHEIT_FAKTOR_id_basiseinheit = new RECLIST_EINHEIT_FAKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EINHEIT_FAKTOR WHERE ID_BASISEINHEIT="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_EINHEIT_FAKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEIT_FAKTOR_id_basiseinheit;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_BASISEINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEIT_FAKTOR get_DOWN_RECORD_LIST_EINHEIT_FAKTOR_id_basiseinheit(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEIT_FAKTOR_id_basiseinheit==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EINHEIT_FAKTOR WHERE ID_BASISEINHEIT="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EINHEIT_FAKTOR_id_basiseinheit = new RECLIST_EINHEIT_FAKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEIT_FAKTOR_id_basiseinheit;
	}


	





	/**
	 * References the Field ID_EINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEIT_FAKTOR get_DOWN_RECORD_LIST_EINHEIT_FAKTOR_id_einheit() throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEIT_FAKTOR_id_einheit==null)
		{
			this.DOWN_RECLIST_EINHEIT_FAKTOR_id_einheit = new RECLIST_EINHEIT_FAKTOR (
			       "SELECT * FROM "+bibE2.cTO()+".JT_EINHEIT_FAKTOR WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF()+" ORDER BY ID_EINHEIT_FAKTOR",
			       this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEIT_FAKTOR_id_einheit;
	}





	/**
	 * cZusatzWhere ODER NULL gibt eine weitere auswahl-Bedingung nach unten (z.B.NVL(DELETED,'N')='N')
	 * cORDERKEY ODER NULL bestimmt die sortierung des subselects
	 * buildNew  zwingt zum aufbau einer neuen subquery-list
	 * References the Field ID_EINHEIT 
	 * Falls keine get_ID_EINHEIT_cUF() leer ist, wird null zurueckgegeben 
	 */
	public RECLIST_EINHEIT_FAKTOR get_DOWN_RECORD_LIST_EINHEIT_FAKTOR_id_einheit(String cZusatzWhere, String cORDERKEY, boolean buildNew) throws myException
	{
		if (S.isEmpty(this.get_ID_EINHEIT_cUF()))
			return null;
	
		if (this.DOWN_RECLIST_EINHEIT_FAKTOR_id_einheit==null || buildNew)
		{
		    String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_EINHEIT_FAKTOR WHERE ID_EINHEIT="+this.get_ID_EINHEIT_cUF();
		    if (S.isFull(cZusatzWhere))
		    {
		    	cQuery += (" AND "+cZusatzWhere);
		    }
		    
		    if (S.isFull(cORDERKEY))
		    {
		    	cQuery += (" ORDER BY  "+cORDERKEY);
		    }
		    
			this.DOWN_RECLIST_EINHEIT_FAKTOR_id_einheit = new RECLIST_EINHEIT_FAKTOR (cQuery,this.get_oConn());
		}
		return this.DOWN_RECLIST_EINHEIT_FAKTOR_id_einheit;
	}


	

	public static String FIELD__EINHEITKURZ = "EINHEITKURZ";
	public static String FIELD__EINHEITLANG = "EINHEITLANG";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__ID_EINHEIT = "ID_EINHEIT";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__IST_LAENGE = "IST_LAENGE";
	public static String FIELD__IST_MENGE = "IST_MENGE";
	public static String FIELD__IST_STANDARD = "IST_STANDARD";
	public static String FIELD__IST_VOLUMEN = "IST_VOLUMEN";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__SYS_TRIGGER_TIMESTAMP = "SYS_TRIGGER_TIMESTAMP";
	public static String FIELD__SYS_TRIGGER_UUID = "SYS_TRIGGER_UUID";
	public static String FIELD__SYS_TRIGGER_VERSION = "SYS_TRIGGER_VERSION";


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
		public String get_EINHEITLANG_cUF() throws myException
	{
		return this.get_UnFormatedValue("EINHEITLANG");
	}

	public String get_EINHEITLANG_cF() throws myException
	{
		return this.get_FormatedValue("EINHEITLANG");	
	}

	public String get_EINHEITLANG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("EINHEITLANG");
	}

	public String get_EINHEITLANG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("EINHEITLANG",cNotNullValue);
	}

	public String get_EINHEITLANG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("EINHEITLANG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_EINHEITLANG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("EINHEITLANG", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_EINHEITLANG(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("EINHEITLANG", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITLANG_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("EINHEITLANG", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITLANG_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEITLANG", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITLANG_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEITLANG", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_EINHEITLANG_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("EINHEITLANG", calNewValueFormated);
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
	
	
	public String get_IST_LAENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_LAENGE");
	}

	public String get_IST_LAENGE_cF() throws myException
	{
		return this.get_FormatedValue("IST_LAENGE");	
	}

	public String get_IST_LAENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_LAENGE");
	}

	public String get_IST_LAENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_LAENGE",cNotNullValue);
	}

	public String get_IST_LAENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_LAENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LAENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_LAENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_LAENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_LAENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_LAENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LAENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LAENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_LAENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_LAENGE", calNewValueFormated);
	}
		public boolean is_IST_LAENGE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LAENGE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_LAENGE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_LAENGE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}
		public String get_IST_MENGE_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_MENGE");
	}

	public String get_IST_MENGE_cF() throws myException
	{
		return this.get_FormatedValue("IST_MENGE");	
	}

	public String get_IST_MENGE_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_MENGE");
	}

	public String get_IST_MENGE_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_MENGE",cNotNullValue);
	}

	public String get_IST_MENGE_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_MENGE",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_MENGE(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_MENGE", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_MENGE(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_MENGE", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_MENGE_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_MENGE", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_MENGE_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_MENGE", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_MENGE_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_MENGE", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_MENGE_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_MENGE", calNewValueFormated);
	}
		public boolean is_IST_MENGE_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_MENGE_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_MENGE_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_MENGE_cUF_NN("N").equals("N"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
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
		public String get_IST_VOLUMEN_cUF() throws myException
	{
		return this.get_UnFormatedValue("IST_VOLUMEN");
	}

	public String get_IST_VOLUMEN_cF() throws myException
	{
		return this.get_FormatedValue("IST_VOLUMEN");	
	}

	public String get_IST_VOLUMEN_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_VOLUMEN");
	}

	public String get_IST_VOLUMEN_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("IST_VOLUMEN",cNotNullValue);
	}

	public String get_IST_VOLUMEN_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("IST_VOLUMEN",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_VOLUMEN(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("IST_VOLUMEN", cNewValueFormated);
	}
	
	
	//2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_IST_VOLUMEN(String cNewValueFormated) throws myException
	{
		return super.check_NewValueForDatabase("IST_VOLUMEN", cNewValueFormated);
	}
	
	
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VOLUMEN_(long lNewValueFormated) throws myException	{
		 return super.set_NewValueForDatabase("IST_VOLUMEN", lNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VOLUMEN_(double dNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_VOLUMEN", dNewValueFormated);
	}

    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VOLUMEN_(BigDecimal bdNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_VOLUMEN", bdNewValueFormated);
	}
	
    //2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_IST_VOLUMEN_(GregorianCalendar calNewValueFormated) throws myException {
		 return super.set_NewValueForDatabase("IST_VOLUMEN", calNewValueFormated);
	}
		public boolean is_IST_VOLUMEN_YES() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_VOLUMEN_cUF_NN("N").equals("Y"))
		{ 
			bRueck = true; 
		}
		
		return bRueck; 
	}

	
	
	public boolean is_IST_VOLUMEN_NO() throws myException
	{
		boolean bRueck = false;
		
		if (get_IST_VOLUMEN_cUF_NN("N").equals("N"))
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
	put("EINHEITKURZ",MyRECORD.DATATYPES.TEXT);
	put("EINHEITLANG",MyRECORD.DATATYPES.TEXT);
	put("ERZEUGT_AM",MyRECORD.DATATYPES.DATE);
	put("ERZEUGT_VON",MyRECORD.DATATYPES.TEXT);
	put("GEAENDERT_VON",MyRECORD.DATATYPES.TEXT);
	put("ID_EINHEIT",MyRECORD.DATATYPES.NUMBER);
	put("ID_MANDANT",MyRECORD.DATATYPES.NUMBER);
	put("IST_LAENGE",MyRECORD.DATATYPES.YESNO);
	put("IST_MENGE",MyRECORD.DATATYPES.YESNO);
	put("IST_STANDARD",MyRECORD.DATATYPES.YESNO);
	put("IST_VOLUMEN",MyRECORD.DATATYPES.YESNO);
	put("LETZTE_AENDERUNG",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_TIMESTAMP",MyRECORD.DATATYPES.DATE);
	put("SYS_TRIGGER_UUID",MyRECORD.DATATYPES.TEXT);
	put("SYS_TRIGGER_VERSION",MyRECORD.DATATYPES.NUMBER);
	}};



    @Override 
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() 
	{ 		
		return RECORD_EINHEIT.HM_FIELDS;	
	}

}
